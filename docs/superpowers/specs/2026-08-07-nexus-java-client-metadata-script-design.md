# nexus-java-client — MetadataService + ScriptService + Java 17 + Maven Central (Design)

Date: 2026-08-07
Status: Approved design — ready for implementation plan
Layer: 2 of 3. Upstream: Nexus server metadata endpoints (L1, PR Gero-Labs/nexus#772). Downstream: `cardano-client-lib` `backend-modules/nexus` (L3).

## Why

The `cardano-client-lib` Nexus backend module (L3) wraps this SDK to implement bloxbean's
`BackendService`. Two of bloxbean's sub-services — `MetadataService` and `ScriptService` —
have no counterpart in this SDK yet. L1 added the server endpoints they need (metadata) and
the server already served scripts/datums. This layer adds the two SDK service verticals so
L3 has full coverage, retargets the SDK to Java 17 (cardano-client-lib's baseline — L3 must
consume this jar on JDK 17), and publishes to Maven Central (so L3's Gradle build resolves it
without GitHub-Packages auth).

## Scope

- Add `MetadataService` (5 methods) + `ScriptService` (2 methods), each a full vertical
  (`XxxApi` Retrofit interface → `XxxService` → `XxxServiceImpl` → models), wired into
  `BackendService` + `BackendServiceImpl`.
- Retarget `maven.compiler.release` 21 → 17. Version bump 1.0.4 → 1.1.0.
- Replace GitHub-Packages publishing with Maven Central publishing config (signed bundle;
  the credentialed release is a manual human step).

Out of scope: Utxo/NetworkInfo work (SDK already exposes Network + tx/address utxos that L3
synthesizes from); any server change; the L3 module itself.

## Pattern (established, mirror exactly)

Every existing SDK service follows this shape (reference: `transaction` vertical):
- `adlabs.nexus.client.backend.api.<domain>.<Domain>Api` — Retrofit interface, `@GET("api/...")`,
  path via `@Path`, `network` via `@Query("network")`, returns `Call<T>`.
- `<Domain>Service` — public interface, methods take `Network` + throw `ApiException`, return
  `Result<T>`.
- `impl.<Domain>ServiceImpl(Retrofit)` — `api = retrofit.create(<Domain>Api.class)`; each method
  `return ApiUtil.process(api.call(<path args>, network.queryValue()));`.
- `model.*` — Jackson POJOs (Lombok), `@JsonProperty` only where the wire name differs from the
  Java field.
- Registered in `factory.BackendService` (getter) + `factory.impl.BackendServiceImpl` (field +
  constructor `new <Domain>ServiceImpl(retrofit)` + getter).

`Network.queryValue()` yields e.g. `cardano-mainnet`; the server binds that to its own
`Network` enum (same mechanism the existing tx endpoints already use). `ApiUtil.process` maps
2xx→`Result.success`, non-2xx→`Result.error(code, body)`, `IOException`→`ApiException`.

## MetadataService

Package `adlabs.nexus.client.backend.api.metadata`.

```java
public interface MetadataService {
    Result<List<TxMetadataJson>>     getTxMetadata(Network network, String txHash) throws ApiException;
    Result<List<TxMetadataCbor>>     getTxMetadataCbor(Network network, String txHash) throws ApiException;
    Result<List<MetadataLabel>>      getMetadataLabels(Network network, int page, int pageSize) throws ApiException;
    Result<List<LabelMetadataJson>>  getMetadataByLabel(Network network, String label, int page, int pageSize) throws ApiException;
    Result<List<LabelMetadataCbor>>  getMetadataCborByLabel(Network network, String label, int page, int pageSize) throws ApiException;
}
```

`MetadataApi` (Retrofit):

```java
@GET("api/transactions/{txHash}/metadata")
Call<List<TxMetadataJson>> getTxMetadata(@Path("txHash") String txHash, @Query("network") String network);

@GET("api/transactions/{txHash}/metadata/cbor")
Call<List<TxMetadataCbor>> getTxMetadataCbor(@Path("txHash") String txHash, @Query("network") String network);

@GET("api/metadata/txs/labels")
Call<List<MetadataLabel>> getMetadataLabels(@Query("network") String network,
                                            @Query("page") int page, @Query("pageSize") int pageSize);

@GET("api/metadata/txs/labels/{label}")
Call<List<LabelMetadataJson>> getMetadataByLabel(@Path("label") String label, @Query("network") String network,
                                                 @Query("page") int page, @Query("pageSize") int pageSize);

@GET("api/metadata/txs/labels/{label}/cbor")
Call<List<LabelMetadataCbor>> getMetadataCborByLabel(@Path("label") String label, @Query("network") String network,
                                                     @Query("page") int page, @Query("pageSize") int pageSize);
```

Models (mirror the L1 server DTOs field-exact; camelCase wire names, no snake_case overrides):
- `TxMetadataJson{ String label; JsonNode json; }`
- `TxMetadataCbor{ String label; String cbor; }`
- `MetadataLabel{ String label; String cip10; Long count; }`
- `LabelMetadataJson{ String txHash; JsonNode json; }`
- `LabelMetadataCbor{ String txHash; String cbor; }`

`label` is `String` end-to-end (uint64 metadatum labels). `json` is `com.fasterxml.jackson.databind.JsonNode`.

Behavioral notes to carry into javadoc (from L1): `cip10`/`count` are best-effort (populated
only when Blockfrost serves; may be null). by-label on preview networks yields a 5xx (no
provider) → surfaces as `Result.error` — document, don't special-case.

## ScriptService

Package `adlabs.nexus.client.backend.api.script`.

```java
public interface ScriptService {
    Result<ScriptDetail> getScriptByHash(Network network, String scriptHash) throws ApiException;
    Result<Datum>        getDatumByHash(Network network, String datumHash) throws ApiException;
}
```

`ScriptApi`:
```java
@GET("api/scripts/{scriptHash}")
Call<ScriptDetail> getScriptByHash(@Path("scriptHash") String scriptHash, @Query("network") String network);

@GET("api/scripts/datum/{datumHash}")
Call<Datum> getDatumByHash(@Path("datumHash") String datumHash, @Query("network") String network);
```

Models (mirror server `ScriptDetailDto` / `DatumDto`):
- `ScriptDetail{ String hash; String type; String cbor; Integer size; JsonNode json; }`
  (`type` ∈ `plutusV1|plutusV2|plutusV3|native`; `cbor` for Plutus, `json` for native).
- `Datum{ String hash; String cbor; JsonNode json; }`

## BackendService wiring

- `factory.BackendService`: add `MetadataService getMetadataService();` and
  `ScriptService getScriptService();`.
- `factory.impl.BackendServiceImpl`: add two fields, construct in the ctor
  (`new MetadataServiceImpl(retrofit)`, `new ScriptServiceImpl(retrofit)`), add getters.

## Java 17 retarget

- `pom.xml`: `<maven.compiler.release>17</maven.compiler.release>` (was 21).
- Verify no Java-18+ API/syntax is used anywhere (the codebase is plain Retrofit + Jackson +
  Lombok — low risk; confirm by a clean `mvn -DskipTests package` under JDK 17 and a scan for
  any 21-only usage). If anything 21-only surfaces, replace with a 17-compatible equivalent.
- Version 1.0.4 → 1.1.0 (additive features).

## Maven Central publishing config

Replace the GitHub-Packages `distributionManagement` with Central Portal publishing:
- `central-publishing-maven-plugin` (Sonatype Central Portal) as the deploy plugin.
- `maven-gpg-plugin` — sign artifacts (activated by a release profile).
- `maven-source-plugin` + `maven-javadoc-plugin` — attach `-sources` and `-javadoc` jars
  (Central requires both).
- POM metadata Central requires: `<name>`, `<description>`, `<url>`, `<licenses>`,
  `<developers>`, `<scm>`.
- Credentials are NOT in the repo: the Central token goes in the user's `~/.m2/settings.xml`
  `<server>` entry; the GPG key + passphrase are the user's. **The signed-bundle build is
  automated; the authenticated release to Central is a documented manual step.**

Document the release runbook in the repo README (namespace `io.github.gero-labs`, the
`settings.xml` server id, GPG key requirement, `mvn -Prelease deploy`, and the Central Portal
"publish" confirmation).

## Testing

Mirror `TransactionServiceTest`: per-method MockWebServer tests that (a) enqueue a canned JSON
body and (b) assert the exact request path + query string AND the JSON→model mapping.
- `MetadataServiceTest`: 5 methods — assert paths `/api/transactions/{tx}/metadata`,
  `/metadata/cbor`, `/api/metadata/txs/labels?...`, `/labels/{label}`, `/labels/{label}/cbor`,
  each with `network=cardano-mainnet` + pagination query where applicable; assert list mapping
  (label/json/cbor/txHash/cip10/count fields).
- `ScriptServiceTest`: 2 methods — assert `/api/scripts/{hash}`, `/api/scripts/datum/{hash}`;
  assert `ScriptDetail`/`Datum` field mapping incl. `JsonNode json`.
- `BackendServiceFactoryTest` (extend existing): assert `getMetadataService()` /
  `getScriptService()` return non-null wired instances.
- Empty-body / non-2xx cases: assert `Result.isSuccessful()==false` + code propagation for at
  least one method per service.

## Open items for the plan

1. Confirm the metadata DTO wire field names against the L1 branch (`Gero-Labs/nexus#772`):
   camelCase `label/json/cbor/txHash/cip10/count` expected — pin via a MockWebServer round-trip.
2. Confirm `central-publishing-maven-plugin` is the current Sonatype-recommended path (vs the
   legacy `nexus-staging-maven-plugin`) and its exact coordinates/version at plan time.
3. Decide the license value for the POM (match whatever `LICENSE` the repo already carries).
4. Confirm JDK 17 is available in the build/CI environment for the retarget verification.

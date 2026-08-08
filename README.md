# nexus-java-client

Java client for the [Nexus](https://nexus.gerowallet.io) Cardano data API.

## Install

`nexus-java-client` is published to Maven Central — no extra repository needed:

```xml
<dependency>
  <groupId>io.github.gero-labs</groupId>
  <artifactId>nexus-java-client</artifactId>
  <version>1.1.0</version>
</dependency>
```

## Usage

```java
BackendService nexus = BackendServiceFactory.getNexusBackendService(
        "https://nexus.gerowallet.io", System.getenv("NEXUS_API_KEY"));

Result<Block> latest = nexus.getBlockService().getLatestBlock(Network.MAINNET);
if (latest.isSuccessful()) {
    System.out.println(latest.getValue().getHash());
}
```

Every service method returns a `Result<T>`: `isSuccessful()`, `getCode()`,
`getResponse()`, `getValue()`. Transport failures throw `ApiException`.

## Resources

`getAccountService()`, `getAddressService()`, `getAssetService()`,
`getBlockService()`, `getEpochService()`, `getPoolService()`,
`getTransactionService()`.

## Build

```bash
mvn clean test
```

## Release

Releases publish to Maven Central under `io.github.gero-labs:nexus-java-client`. The
tag-triggered `publish.yml` workflow runs this automatically; the steps below are for
setting that up or running a release manually.

One-time setup:

1. Verify the `io.github.gero-labs` namespace with Sonatype Central
   (https://central.sonatype.org/register/namespace/) — it's tied to the GitHub org.
2. Generate a Central Portal user token
   (https://central.sonatype.com/account) and add it to `~/.m2/settings.xml`:
   ```xml
   <servers>
     <server>
       <id>central</id>
       <username>YOUR_TOKEN_USERNAME</username>
       <password>YOUR_TOKEN_PASSWORD</password>
     </server>
   </servers>
   ```
3. Have a GPG key available for signing (`gpg --gen-key` if you don't have one) — Central
   requires all release artifacts to be signed.

To release:

```bash
mvn -Prelease deploy
```

This builds the jar, sources jar, and javadoc jar, signs all three with GPG, and uploads
a deployment bundle to the Central Portal. The deployment lands in `PENDING`/`VALIDATED`
state — sign in to https://central.sonatype.com/publishing and click **Publish** to make
it live (this repo's `autoPublish` is `false` by design, so nothing goes live unattended).

# nexus-java-client

Java client for the [Nexus](https://nexus.gerowallet.io) Cardano data API.

## Install

Add the GitHub Packages repository and the dependency to your `pom.xml`:

```xml
<repositories>
  <repository>
    <id>github-nexus-java-client</id>
    <url>https://maven.pkg.github.com/Gero-Labs/nexus-java-client</url>
  </repository>
</repositories>

<dependency>
  <groupId>io.github.gero-labs</groupId>
  <artifactId>nexus-java-client</artifactId>
  <version>1.0.0</version>
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

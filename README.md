# jcifs-pool

[English](./README.md) | [简体中文](./README.zh-CN.md)

> **Status**: maintained on the `feature/1.0.x` line (JDK 8). Artifacts are not yet published to Maven Central; they are distributed through the project's private repository and GitHub Releases.

## Table of Contents

- [1. Project Overview](#1-project-overview)
- [2. Features & Status](#2-features--status)
- [3. Requirements & Compatibility](#3-requirements--compatibility)
- [4. Architecture & Modules](#4-architecture--modules)
- [5. Installation](#5-installation)
- [6. Quick Start](#6-quick-start)
- [7. Configuration](#7-configuration)
- [8. Core Usage / API](#8-core-usage--api)
- [9. Testing & Build](#9-testing--build)
- [10. Versioning & Branches](#10-versioning--branches)
- [11. Contributing & License](#11-contributing--license)

## 1. Project Overview

`jcifs-pool` provides a connection-pooled SMB/CIFS file-access toolkit on top of the [org.codelibs jcifs](https://github.com/codelibs/jcifs) 2.1.11 client (an SMB2-capable fork of JCIFS). It wraps `SmbFile` clients with Apache Commons Pool 2, offers a high-level `ISMBClient` interface (upload / download / list / delete / rename / stream I/O), and ships file filters, stream utilities and rename policies for building SMB file operations in Java applications.

What it is:

- **SMB2 API family** (`jcifs.smb.*`) — `SmbFile2` + `SmbFile2Builder` + `SmbFile2Config`, the `ISMBClient` interface and `SmbFile2ResourceClient` / `SmbFile2PooledResourceClient` implementations, plus `SmbFilePool` (Commons Pool 2 based);
- **SMB1 legacy API family** (`jcifs.smb1.*`) — the same shape for the old SMB1 `SmbFile` API (`SmbFile1`, `SmbFile1Builder`, `SmbFile1Config`, `SmbFile1ResourceClient`, `SmbFile1PooledResourceClient`, `SmbFile1Pool`);
- **Utilities** (`jcifs.utils.*`) — `SMBPathUtils` (shared-URL building), `Smb2FileUtils` / `Smb1FileUtils` / `SmbFileUtils` (append, list, makeDir, remove, rename, retrieveToFile/Dir/Stream, getInputStream, ...), channel/stream helpers and `SMBCopyListenerUtils`;
- **File filters & I/O helpers** — Apache Commons-IO-style filter chains (`SuffixFileFilter`, `AndFileFilter`, ...) for both SMB1 and SMB2, `CopyStreamProcessListener` progress listeners and `DateFileRenamePolicy` / `UUIDFileRenamePolicy` rename policies.

What it is not:

- Not a reimplementation of the SMB protocol — all protocol work is delegated to `org.codelibs:jcifs`;
- Not a Spring integration module — it is a plain Java library (servlet API is only a `provided` dependency used by `downloadToResponse`).

Typical scenarios:

| Scenario | What to use |
| :--- | :--- |
| Upload / download files on a Windows share | `SmbFile2ResourceClient` (implements `ISMBClient`) |
| Reuse SMB connections under concurrency | `SmbFilePool` + `SmbFilePooledFactory` + `SmbFilePoolConfig` |
| Append / retrieve with buffers and progress | `Smb2FileUtils.appendFile` / `appendStream`, `CopyStreamProcessListener` |
| Legacy SMB1-only servers | `jcifs.smb1` package (`SmbFile1*`) |
| Filtering directory listings | `Smb2FileFilter` implementations (`SuffixFileFilter`, `AgeFileFilter`, ...) |

## 2. Features & Status

| Capability | Status | Notes |
| :--- | :--- | :--- |
| SMB2 client wrapper | Implemented | `SmbFile2` (extends `jcifs.smb.SmbFile`), `SmbFile2Builder`, `SmbFile2Config` |
| High-level file operations | Implemented | `ISMBClient` (makeDir / upload / downloadTo* / listFiles / remove / rename / getInputStream / downloadToResponse) |
| Connection pooling | Implemented | `SmbFilePool` extends `GenericObjectPool<SmbFile>`, `SmbFilePooledFactory`, `SmbFilePoolConfig` |
| Pool-aware resource client | Implemented | `SmbFile2PooledResourceClient` (borrow / return per operation via `ThreadLocal`) |
| SMB1 legacy support | Implemented | `jcifs.smb1` package with its own pool, builder and filters |
| Utility helpers | Implemented | `Smb2FileUtils`, `Smb1FileUtils`, `SmbFileUtils`, `SMBPathUtils`, channel/stream/copy-listener utils |
| File filters (Commons-IO style) | Implemented | SMB1 + SMB2 filter sets (17 classes each side) |
| Rename policies | Implemented | `DateFileRenamePolicy`, `UUIDFileRenamePolicy` (`jcifs.io.rename`) |
| Automated tests | Minimal | A single placeholder test class exists in `src/test`; SMB flows are not covered by automated tests in this branch |

## 3. Requirements & Compatibility

| Item | Requirement |
| :--- | :--- |
| JDK | 8+ |
| Maven | 3.0+ (Maven Wrapper `mvnw` included) |
| Core deps | org.codelibs jcifs 2.1.11, commons-pool2 2.7.0, commons-io 2.22.0, commons-beanutils 1.11.0, commons-lang3 3.20.0, commons-net 3.6, slf4j-api 2.0.18, lombok (provided) |
| Optional | javax.servlet-api 3.0.1 (provided, for `downloadToResponse`) |
| Test deps | junit 4.13.2, slf4j-simple, contiperf 2.3.4 |

Version lines:

| Branch | JDK | Version pattern |
| :--- | :---: | :--- |
| `feature/1.0.x` | 8 | `1.0.x.*` |
| `feature/2.0.x` | 17 | `2.0.x.*` |
| `feature/3.0.x` | 21 | `3.0.x.*` |

## 4. Architecture & Modules

```text
Application code
      |
      v
ISMBClient (upload/download/list/remove/rename/stream)
      |
      +--> SmbFile2ResourceClient  (ThreadLocal SmbFile2)
      |         |
      |         +--> SmbFile2Builder -> SmbFile2Config (host/user/pass/share/timeouts)
      |
      +--> SmbFile2PooledResourceClient -> SmbFilePool (Commons Pool 2)
      |                                        |--> SmbFilePooledFactory
      |                                        `--> SmbFilePoolConfig
      |
      `--> jcifs.utils.* (Smb2FileUtils / SMBPathUtils / stream & channel utils)
                            |
                            v
              org.codelibs jcifs 2.1.11 (SmbFile, SMB2 protocol)
```

Single-module jar. Top-level packages:

| Package | Contents |
| :--- | :--- |
| `jcifs.smb` | SMB2 family: `ISMBClient`, `SmbFile2`, `SmbFile2Builder`, `SmbFile2Config`, `SmbFile2ResourceClient`, `SmbFile2PooledResourceClient`, `SmbFilePool`, `SmbFilePoolConfig`, `SmbFilePooledFactory`, `jcifs.smb.filter` (SMB2 filters), `jcifs.smb.pool` |
| `jcifs.smb1` | SMB1 legacy family with the same shape (`SmbFile1`, `SmbFile1Builder`, `SmbFile1Config`, `SmbFile1ResourceClient`, `SmbFile1PooledResourceClient`, filters, pool) |
| `jcifs.utils` | `SMBPathUtils`, `SmbFileUtils`, `Smb1FileUtils`, `Smb2FileUtils`, stream/channel/copy-listener utils, `Assert` variants |
| `jcifs.io` | `CopyStreamProcessListener`, `PrintCopyStreamProcessListener`, `jcifs.io.rename` (`FileRenamePolicy`, `DateFileRenamePolicy`, `UUIDFileRenamePolicy`) |

## 5. Installation

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>jcifs-pool</artifactId>
    <version>1.0.x.20260630-SNAPSHOT</version>
</dependency>
```

Gradle:

```groovy
implementation 'io.github.easy4j:jcifs-pool:1.0.x.20260630-SNAPSHOT'
```

The snapshot is served from the project's private repository (see `distributionManagement` in the pom). No Maven Central release is available yet.

## 6. Quick Start

Build a pooled SMB2 client and download a directory (real API):

```java
import jcifs.smb.SmbFile2Config;
import jcifs.smb.SmbFile2PooledResourceClient;
import jcifs.smb.pool.SmbFilePool;
import jcifs.smb.pool.SmbFilePooledFactory;
import jcifs.smb.pool.SmbFilePoolConfig;

SmbFile2Config config = new SmbFile2Config();
config.setHost("192.168.1.10");
config.setUsername("user");
config.setPassword("password");
config.setSharedDir("/share");          // share name
config.setConnectTimeout(30 * 1000);    // default 30s
config.setReadTimeout(30 * 1000);       // default 30s

SmbFilePool pool = new SmbFilePool(new SmbFilePooledFactory(null), new SmbFilePoolConfig());
SmbFile2PooledResourceClient client = new SmbFile2PooledResourceClient(pool, config);

client.downloadToDir("/share/docs", "/tmp/docs"); // copy share dir to a local dir
client.upload(new java.io.File("/tmp/a.txt"), "/share", "a.txt");
client.releaseClient(null);                       // no-op guard; resources are
                                                  // returned to the pool per call
```

Without a pool, use `SmbFile2ResourceClient` (each call obtains a `SmbFile2` from the `SmbFile2Builder` and keeps it in a `ThreadLocal`).

## 7. Configuration

Configured through `SmbFile2Config` (SMB1: `SmbFile1Config`) — a plain POJO, no property-file prefix:

| Property | Meaning |
| :--- | :--- |
| `host` | SMB server host |
| `username` / `password` / `domain` | credentials (anonymous = `"anonymous"`) |
| `sharedDir` | share directory name |
| `connectTimeout` / `readTimeout` | default 30 000 ms each |
| `bufferSize` | internal buffer, default 8 MiB |
| `channelReadBufferSize` / `channelWriteBufferSize` | file-channel buffers, default 2 MiB each |
| `autoFlush` / `autoFlushBlockSize` | auto-flush for stream upload/download |
| `copyStreamProcessListener( Name)` | progress listener instance or class name |

Pool behavior is tuned with the Commons Pool 2 `SmbFilePoolConfig` (size, idle eviction, etc.).

## 8. Core Usage / API

### 8.1 High-level SMB operations (`ISMBClient`)

```java
import jcifs.smb.ISMBClient;
import jcifs.smb.SmbFile;

SmbFile2PooledResourceClient client = /* as in Quick Start */;

String[] names = client.listNames("/share/docs");        // directory listing
SmbFile[] files = client.listFiles("/share/docs", true); // recursive listing
client.downloadToFile("/share/docs/a.txt", "/tmp/a.txt");// download a file
client.remove("/share/docs/old.txt");                    // delete a file
boolean ok = client.renameTo("/share/docs/a.txt", "/share/docs/b.txt");
```

### 8.2 Stream utilities

```java
import jcifs.utils.Smb2FileUtils;

SmbFile2 smb = client.getSMBClient();
try {
    Smb2FileUtils.appendFile(smb, "/share/docs.log", new java.io.File("/tmp/line.log"));
    Smb2FileUtils.retrieveToFile(smb, new java.io.File("/tmp/copy.log"));
} finally {
    client.releaseClient(smb);
}
```

## 9. Testing & Build

```bash
./mvnw clean verify
```

The build is configured with:

- JUnit 4 + Maven Surefire; `contiperf` is available as a test-scope dependency for performance runs. Note: `src/test` currently contains only a placeholder test class — the SMB flows require a reachable SMB server and are validated in real deployments rather than by automated tests in this branch;
- JaCoCo coverage reporting plus a line-coverage check rule with a 90% minimum target (`haltOnFailure=false`);
- Source and Javadoc jars attached at package time;
- a `central` release profile (GPG signing + Central publishing) reserved for official releases.

## 10. Versioning & Branches

Three parallel version lines, each bound to a JDK baseline:

| Branch | JDK | Version pattern | Maintenance |
| :--- | :---: | :--- | :--- |
| `feature/1.0.x` | 8 | `1.0.x.*` | Current development line |
| `feature/2.0.x` | 17 | `2.0.x.*` | Maintained in parallel |
| `feature/3.0.x` | 21 | `3.0.x.*` | Maintained in parallel |

Snapshots on this branch are versioned `1.0.x.20260630-SNAPSHOT`.

## 11. Contributing & License

Contributions are welcome — open an issue or pull request on GitHub. All source files are licensed under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt).

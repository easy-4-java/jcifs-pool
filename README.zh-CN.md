[English](./README.md) | [简体中文](./README.zh-CN.md)

# jcifs-pool

[项目概述](#1-项目概述) | [功能与状态](#2-功能与状态) | [环境要求](#3-环境要求与兼容性) | [架构与模块](#4-架构与模块) | [安装](#5-安装) | [快速开始](#6-快速开始) | [配置](#7-配置) | [核心用法](#8-核心用法--api) | [测试与构建](#9-测试与构建) | [版本管理](#10-版本与分支) | [许可协议](#11-贡献与许可)

> **项目状态**：`feature/1.0.x` 版本线维护中（JDK 8）。制品尚未发布到 Maven Central，通过项目私服与 GitHub Releases 分发。

## 1. 项目概述

`jcifs-pool` 基于 [org.codelibs jcifs](https://github.com/codelibs/jcifs) 2.1.11（支持 SMB2 的 JCIFS 分支）提供连接池化的 SMB/CIFS 文件访问工具集。它用 Apache Commons Pool 2 包装 `SmbFile` 客户端，提供高层 `ISMBClient` 接口（上传 / 下载 / 列表 / 删除 / 重命名 / 流式 IO），并附带文件过滤器、流工具与重命名策略，便于在 Java 应用中构建 SMB 文件操作。

是什么：

- **SMB2 API 家族**（`jcifs.smb.*`）——`SmbFile2` + `SmbFile2Builder` + `SmbFile2Config`、`ISMBClient` 接口及其实现 `SmbFile2ResourceClient` / `SmbFile2PooledResourceClient`，以及基于 Commons Pool 2 的 `SmbFilePool`；
- **SMB1 遗留 API 家族**（`jcifs.smb1.*`）——面向旧版 SMB1 `SmbFile` API 的同类结构（`SmbFile1`、`SmbFile1Builder`、`SmbFile1Config`、`SmbFile1ResourceClient`、`SmbFile1PooledResourceClient`、`SmbFile1Pool`）；
- **工具类**（`jcifs.utils.*`）——`SMBPathUtils`（共享 URL 构建）、`Smb2FileUtils` / `Smb1FileUtils` / `SmbFileUtils`（append、list、makeDir、remove、rename、retrieveToFile/Dir/Stream、getInputStream 等）、channel/stream 辅助与 `SMBCopyListenerUtils`；
- **文件过滤器与 IO 辅助**——仿 Commons-IO 的过滤器链（`SuffixFileFilter`、`AndFileFilter` 等，SMB1 与 SMB2 各一套）、`CopyStreamProcessListener` 进度监听与 `DateFileRenamePolicy` / `UUIDFileRenamePolicy` 重命名策略。

不是什么：

- 不是 SMB 协议的重实现——协议层全部委托给 `org.codelibs:jcifs`；
- 不是 Spring 集成模块——纯 Java 库（servlet API 仅以 `provided` 作用域供 `downloadToResponse` 使用）。

典型场景：

| 场景 | 使用 |
| :--- | :--- |
| 在 Windows 共享上上传/下载文件 | `SmbFile2ResourceClient`（实现 `ISMBClient`） |
| 并发场景下复用 SMB 连接 | `SmbFilePool` + `SmbFilePooledFactory` + `SmbFilePoolConfig` |
| 带缓冲与进度的追加/拉取 | `Smb2FileUtils.appendFile` / `appendStream`、`CopyStreamProcessListener` |
| 仅支持 SMB1 的遗留服务器 | `jcifs.smb1` 包（`SmbFile1*`） |
| 过滤目录列表 | `Smb2FileFilter` 实现（`SuffixFileFilter`、`AgeFileFilter` 等） |

## 2. 功能与状态

| 能力 | 状态 | 说明 |
| :--- | :--- | :--- |
| SMB2 客户端包装 | 已实现 | `SmbFile2`（继承 `jcifs.smb.SmbFile`）、`SmbFile2Builder`、`SmbFile2Config` |
| 高层文件操作 | 已实现 | `ISMBClient`（makeDir / upload / downloadTo* / listFiles / remove / rename / getInputStream / downloadToResponse） |
| 连接池 | 已实现 | `SmbFilePool` 继承 `GenericObjectPool<SmbFile>`、`SmbFilePooledFactory`、`SmbFilePoolConfig` |
| 池感知资源客户端 | 已实现 | `SmbFile2PooledResourceClient`（每次操作通过 `ThreadLocal` 借出/归还） |
| SMB1 遗留支持 | 已实现 | `jcifs.smb1` 包，含独立连接池、构建器与过滤器 |
| 工具辅助 | 已实现 | `Smb2FileUtils`、`Smb1FileUtils`、`SmbFileUtils`、`SMBPathUtils`、channel/stream/复制监听工具 |
| 文件过滤器（Commons-IO 风格） | 已实现 | SMB1 + SMB2 两套过滤器（每侧 17 个类） |
| 重命名策略 | 已实现 | `DateFileRenamePolicy`、`UUIDFileRenamePolicy`（`jcifs.io.rename`） |
| 自动化测试 | 极少 | `src/test` 仅有 1 个占位测试类；SMB 流程本分支暂无自动化测试覆盖 |

## 3. 环境要求与兼容性

| 项目 | 要求 |
| :--- | :--- |
| JDK | 8+ |
| Maven | 3.0+（内置 Maven Wrapper `mvnw`） |
| 核心依赖 | org.codelibs jcifs 2.1.11、commons-pool2 2.7.0、commons-io 2.22.0、commons-beanutils 1.11.0、commons-lang3 3.20.0、commons-net 3.6、slf4j-api 2.0.18、lombok（provided） |
| 可选 | javax.servlet-api 3.0.1（provided，用于 `downloadToResponse`） |
| 测试依赖 | junit 4.13.2、slf4j-simple、contiperf 2.3.4 |

版本线：

| 分支 | JDK | 版本模式 |
| :--- | :---: | :--- |
| `feature/1.0.x` | 8 | `1.0.x.*` |
| `feature/2.0.x` | 17 | `2.0.x.*` |
| `feature/3.0.x` | 21 | `3.0.x.*` |

## 4. 架构与模块

```text
应用代码
      |
      v
ISMBClient (upload/download/list/remove/rename/stream)
      |
      +--> SmbFile2ResourceClient  (ThreadLocal SmbFile2)
      |         |
      |         +--> SmbFile2Builder -> SmbFile2Config (host/user/pass/share/超时)
      |
      +--> SmbFile2PooledResourceClient -> SmbFilePool (Commons Pool 2)
      |                                        |--> SmbFilePooledFactory
      |                                        `--> SmbFilePoolConfig
      |
      `--> jcifs.utils.* (Smb2FileUtils / SMBPathUtils / stream & channel 工具)
                            |
                            v
              org.codelibs jcifs 2.1.11 (SmbFile, SMB2 协议)
```

单模块 jar。顶层包：

| 包 | 内容 |
| :--- | :--- |
| `jcifs.smb` | SMB2 家族：`ISMBClient`、`SmbFile2`、`SmbFile2Builder`、`SmbFile2Config`、`SmbFile2ResourceClient`、`SmbFile2PooledResourceClient`、`SmbFilePool`、`SmbFilePoolConfig`、`SmbFilePooledFactory`、`jcifs.smb.filter`（SMB2 过滤器）、`jcifs.smb.pool` |
| `jcifs.smb1` | SMB1 遗留家族，结构与 SMB2 对称（`SmbFile1`、`SmbFile1Builder`、`SmbFile1Config`、`SmbFile1ResourceClient`、`SmbFile1PooledResourceClient`、过滤器、pool） |
| `jcifs.utils` | `SMBPathUtils`、`SmbFileUtils`、`Smb1FileUtils`、`Smb2FileUtils`、stream/channel/复制监听工具、`Assert` 系列 |
| `jcifs.io` | `CopyStreamProcessListener`、`PrintCopyStreamProcessListener`、`jcifs.io.rename`（`FileRenamePolicy`、`DateFileRenamePolicy`、`UUIDFileRenamePolicy`） |

## 5. 安装

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>jcifs-pool</artifactId>
    <version>1.0.x.20260630-SNAPSHOT</version>
</dependency>
```

Gradle：

```groovy
implementation 'io.github.easy4j:jcifs-pool:1.0.x.20260630-SNAPSHOT'
```

快照版本由项目私服提供（见 pom 中 `distributionManagement`）。尚未发布 Maven Central 正式版。

## 6. 快速开始

构建带连接池的 SMB2 客户端并下载目录（真实 API）：

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
config.setSharedDir("/share");          // 共享名
config.setConnectTimeout(30 * 1000);    // 默认 30s
config.setReadTimeout(30 * 1000);       // 默认 30s

SmbFilePool pool = new SmbFilePool(new SmbFilePooledFactory(null), new SmbFilePoolConfig());
SmbFile2PooledResourceClient client = new SmbFile2PooledResourceClient(pool, config);

client.downloadToDir("/share/docs", "/tmp/docs"); // 将共享目录复制到本地目录
client.upload(new java.io.File("/tmp/a.txt"), "/share", "a.txt");
client.releaseClient(null);                       // 空操作保护；资源在每次调用
                                                  // 内部已归还连接池
```

不使用连接池时，可用 `SmbFile2ResourceClient`（每次调用从 `SmbFile2Builder` 获取 `SmbFile2` 并保存在 `ThreadLocal` 中）。

## 7. 配置

通过 `SmbFile2Config`（SMB1 为 `SmbFile1Config`）配置——纯 POJO，无属性文件前缀：

| 属性 | 含义 |
| :--- | :--- |
| `host` | SMB 服务器地址 |
| `username` / `password` / `domain` | 凭据（匿名 = `"anonymous"`） |
| `sharedDir` | 共享目录名 |
| `connectTimeout` / `readTimeout` | 默认各 30 000 ms |
| `bufferSize` | 内部缓冲区，默认 8 MiB |
| `channelReadBufferSize` / `channelWriteBufferSize` | 文件通道缓冲区，默认各 2 MiB |
| `autoFlush` / `autoFlushBlockSize` | 流式上传/下载自动刷新 |
| `copyStreamProcessListener( Name)` | 进度监听实例或类名 |

连接池行为通过 Commons Pool 2 的 `SmbFilePoolConfig` 调整（容量、空闲驱逐等）。

## 8. 核心用法 / API

### 8.1 高层 SMB 操作（`ISMBClient`）

```java
import jcifs.smb.ISMBClient;
import jcifs.smb.SmbFile;

SmbFile2PooledResourceClient client = /* 同快速开始 */;

String[] names = client.listNames("/share/docs");        // 目录列表
SmbFile[] files = client.listFiles("/share/docs", true); // 递归列表
client.downloadToFile("/share/docs/a.txt", "/tmp/a.txt");// 下载文件
client.remove("/share/docs/old.txt");                    // 删除文件
boolean ok = client.renameTo("/share/docs/a.txt", "/share/docs/b.txt");
```

### 8.2 流式工具

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

## 9. 测试与构建

```bash
./mvnw clean verify
```

构建配置：

- JUnit 4 + Maven Surefire；`contiperf` 以 test 作用域提供，可用于性能测试。说明：`src/test` 目前仅含 1 个占位测试类——SMB 流程需要可达的 SMB 服务器，本分支以真实环境验证为主，暂无自动化测试覆盖；
- JaCoCo 覆盖率报告 + 行覆盖率检查规则，最低目标 90%（`haltOnFailure=false`）；
- package 阶段附加源码包与 Javadoc 包；
- 提供 `central` 发布 profile（GPG 签名 + Central 发布插件），仅用于正式发布。

## 10. 版本与分支

三条并行版本线，各自绑定一个 JDK 基线：

| 分支 | JDK | 版本模式 | 维护状态 |
| :--- | :---: | :--- | :--- |
| `feature/1.0.x` | 8 | `1.0.x.*` | 当前开发线 |
| `feature/2.0.x` | 17 | `2.0.x.*` | 并行维护 |
| `feature/3.0.x` | 21 | `3.0.x.*` | 并行维护 |

本分支快照版本为 `1.0.x.20260630-SNAPSHOT`。

## 11. 贡献与许可

欢迎通过 GitHub Issue 或 Pull Request 参与贡献。所有源码基于 [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt) 许可。

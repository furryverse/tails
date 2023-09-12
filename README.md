<h1 align="center">😼 Tails</h1>

## ✨ 项目结构

| 子模块         | 功能                | ⭐           |
|-------------|-------------------|-------------|
| Alnitak     | 标签（分区） / 动态（帖子）服务 | 参宿星（参宿一）    |
| Arcturus    | 评论 / 点赞 / 收藏服务    | 大角星         |
| Ascella     | 身份基础设施（认证 / 账号系统） | 天府星         |
| Borealis    | 管理端服务             | 天相星         |
| Hecatebolus | 图片 / 文件托管服务       | 天梁星         |
| Mira        | 好友服务              | 米拉星（蒭藁增二）   |
| Naos        | 实时位置社交服务          | 弧矢星（弧矢增二十二） |
| Nanto       | 匿名私信服务            | 天同星         |
| Nunki       | 网关                | 天机星         |
| Polis       | 第三方远端资源服务         | 七杀星         |

## 🔨 架构设计

正在积极地使用微服务和云原生架构提高稳定性，目前在我们部署的线上环境中使用了 【数据删除】 台服务器进行小型集群。

**方案如下：**

| 服务         | 开源代码仓库                                               | 开源协议                                                                                             | 是否多机运行 | 运行方式          | 描述                  |
|------------|------------------------------------------------------|--------------------------------------------------------------------------------------------------|--------|---------------|---------------------|
| Consul     | [consul](https://github.com/hashicorp/consul)        | [Business Source License 1.1](https://github.com/hashicorp/consul/blob/main/LICENSE)             | 是      | 一主多从          | 配置中心与注册中心           |
| Zookeeper  | [zookeeper](https://github.com/apache/zookeeper)     | [Apache License 2.0](https://github.com/apache/zookeeper/blob/master/LICENSE.txt)                | 是      | 一主多从          | 配置中心与注册中心（Dubbo 使用） |
| Mongodb    | [mongo](https://github.com/mongodb/mongo)            | [Server Side Public License](https://github.com/mongodb/mongo/blob/master/LICENSE-Community.txt) | 是      | 副本集 一主多从      | 数据存储（数据库）           |
| Redis      | [redis](https://github.com/redis/redis)              | [BSD 3-Clause "New" or "Revised" License](https://github.com/redis/redis/blob/unstable/COPYING)  | 是      | 一主对一从 多主从集群模式 | 数据存储（缓存）            |
| MinIO      | [minio](https://github.com/minio/minio)              | [GNU Affero General Public License v3.0](https://github.com/minio/minio/blob/master/LICENSE)     | 是      | 纠删码模式         | 数据存储（对象存储）          |
| Drone CI   | [drone](https://github.com/harness/drone)            | [Drone Non-Commercial License](https://github.com/harness/drone/blob/master/LICENSE)             | 否      | 单机            | 构建与 Docker 镜像提交     |
| Harbor     | [harbor](https://github.com/goharbor/harbor)         | [Apache License 2.0](https://github.com/goharbor/harbor/blob/main/LICENSE)                       | 否      | 单机            | 存储容器文件与维护容器注册表      |
| Reposilite | [reposilite](https://github.com/dzikoysk/reposilite) | [Apache License 2.0](https://github.com/dzikoysk/reposilite/blob/main/LICENSE)                   | 否      | 单机            | 存储 Maven 包          |

## 🚀 构建

项目使用 Gradle （Kotlin DSL） 作为构建系统：

```bash
# clone from our repository.
$ git clone https://github.com/furryverse/tails
# source dir.
$ cd tails
# complie and package with gradle build.
$ gradle build
```

## ⚠ 注意事项

1. 每一个服务都已经配置了 `Consul` 服务发现，如果需要启动其中一个服务也就意味着需要一个正常运行的 `Consul`
   单体或集群，如果运行的目标机器有多张网卡（包括虚拟网卡）可能需要修改 `Consul` 的配置项指定网卡。
2. 为了方便本地进行调试可以尝试使用 `milkyway` 子项目，该子项目已配置扫描其他服务包下的 Spring 组件，也就是一个完整的传统单体应用。
3. 推荐使用 Linux 部署应用（真的会有用户嘛 uwu）。
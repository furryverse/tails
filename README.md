<h1 align="center">😼 Tails</h1>

## 🔨 架构设计

该项目是一个单体服务，所有的数据库、对象存储或 CI / CD 构件都可以单机部署也可以多个物理服务器各自运行一个单体服务作为均衡负载，以下是我们的实例采用的方案，仅供参考。

**方案如下：**

| 服务        | 开源代码仓库                                              | 开源协议                                                     | 是否多机运行 | 运行方式                  | 描述                         |
| ----------- | --------------------------------------------------------- | ------------------------------------------------------------ | ------------ | ------------------------- | ---------------------------- |
| Mongodb     | [mongo](https://github.com/mongodb/mongo)                 | [Server Side Public License](https://github.com/mongodb/mongo/blob/master/LICENSE-Community.txt) | 是           | 副本集 一主多从           | 数据存储（数据库）           |
| Redis       | [redis](https://github.com/redis/redis)                   | [BSD 3-Clause "New" or "Revised" License](https://github.com/redis/redis/blob/unstable/COPYING) | 是           | 一主对一从 多主从集群模式 | 数据存储（缓存）             |
| MinIO       | [minio](https://github.com/minio/minio)                   | [GNU Affero General Public License v3.0](https://github.com/minio/minio/blob/master/LICENSE) | 是           | 纠删码模式                | 数据存储（对象存储）         |
| Meilisearch | [meilisearch](https://github.com/meilisearch/meilisearch) | [MIT License](https://github.com/meilisearch/meilisearch/blob/main/LICENSE) | 否           | 单机                      | 文本搜索引擎                 |
| Drone CI    | [drone](https://github.com/harness/drone)                 | [Drone Non-Commercial License](https://github.com/harness/drone/blob/master/LICENSE) | 否           | 单机                      | 构建与 Docker 镜像提交       |
| Harbor      | [harbor](https://github.com/goharbor/harbor)              | [Apache License 2.0](https://github.com/goharbor/harbor/blob/main/LICENSE) | 否           | 单机                      | 存储容器文件与维护容器注册表 |
| Reposilite  | [reposilite](https://github.com/dzikoysk/reposilite)      | [Apache License 2.0](https://github.com/dzikoysk/reposilite/blob/main/LICENSE) | 否           | 单机                      | 存储 Maven 包                |

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

## ⚖ 开源许可

本项目使用 [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.html) 许可协议进行开源。

## 🐱 喵喵 

### 项目包结构

按照 Spring 项目通用结构建立的包，以下部分包的详细信息：

| 包          | 完整包路径                           | 描述                                               |
|------------|---------------------------------|--------------------------------------------------|
| Controller | moe.furryverse.tails.controller | 控制层，向外提供 API，检查传入的参数是否会为空和校验数据合法性等，本层参数必须使用包装类   |
| Model      | moe.furryverse.tails.model      | 数据表结构和传输层结构，本项目 DO 和 DTO 并没有很大的差异，因此并没有将两种对象分开编写 |
| Repository | moe.furryverse.tails.repository | 数据库抽象层，用于封装数据库方法                                 |
| Service    | moe.furryverse.tails.service    | 服务层，本层参数使用非包装类型，操作数据库缓存等                         |

### 数据结构

在 `moe.furryverse.tails.model` 中包含了大部分软件中的数据结构，以下是详细描述：

| 类名       | 描述                                                         | 别名       |
| ---------- | ------------------------------------------------------------ | ---------- |
| Account    | 账号                                                         |            |
| Activity   | 活动                                                         | 展会       |
| Category   | 分类                                                         | 分区       |
| Chapter    | `Novel` 小说的章节                                           |            |
| Chat       | 双人聊天                                                     |            |
| Comment    | 对于 `Post` 帖子的回复（也允许对 `Novel` 小说和它的 `Chapter` 章节进行回复） | 回复       |
| Commission | 委托                                                         | 约稿       |
| FileRecord | 文件记录 作为对象存储系统的索引                              |            |
| Group      | 多人聊天                                                     |            |
| History    | 编辑历史记录                                                 |            |
| Item       | 商品 需要与 `Shop` 商城进行关联                              |            |
| Novel      | 小说                                                         |            |
| OAuth      | OAuth 登录记录，记录与账号的第三方登录关联性                 |            |
| Order      | 订单 需要与 `Item` 商品或 `Ticket` 票进行关联                |            |
| Post       | 帖子 只作为一个单一的帖子索引为回复提供关联性 帖子在与第一条回复关联后才能形成一个完整帖子 |            |
| Reaction   | 反应 这种类型的回复将只能回复一个表情和附带一段简短的话      | 反应类回复 |
| Shop       | 商城                                                         | 店铺       |
| Stub       | 凭据 需要与 `Ticket` 票进行关联                              | 票据       |
| Tag        | 标签                                                         |            |
| Thought    | 思绪 这种类型的回复将会附加在帖子的某一个版本的某一段选定语句中 | 关联类回复 |
| Ticket     | 票                                                           |            |
| Workflow   | 一次委托的流程 与 `Commission` 进行关联                      | 工作流     |

这是数据结构间的关系图表（非关系型数据库结构图）：

![Model](https://i.miji.bid/2023/12/23/f851ac75c900b91e9433bf31ddde929f.png)

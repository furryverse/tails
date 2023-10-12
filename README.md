<h1 align="center">😼 Tails</h1>

## 🔨 架构设计

该项目是一个单体服务，所有的数据库、对象存储或 CI / CD 构件都可以单机部署也可以多个物理服务器各自运行一个单体服务作为均衡负载，以下是我们的实例采用的方案，仅供参考。

**方案如下：**

| 服务       | 开源代码仓库                                         | 开源协议                                                     | 是否多机运行 | 运行方式                  | 描述                         |
| ---------- | ---------------------------------------------------- | ------------------------------------------------------------ | ------------ | ------------------------- | ---------------------------- |
| Mongodb    | [mongo](https://github.com/mongodb/mongo)            | [Server Side Public License](https://github.com/mongodb/mongo/blob/master/LICENSE-Community.txt) | 是           | 副本集 一主多从           | 数据存储（数据库）           |
| Redis      | [redis](https://github.com/redis/redis)              | [BSD 3-Clause "New" or "Revised" License](https://github.com/redis/redis/blob/unstable/COPYING) | 是           | 一主对一从 多主从集群模式 | 数据存储（缓存）             |
| MinIO      | [minio](https://github.com/minio/minio)              | [GNU Affero General Public License v3.0](https://github.com/minio/minio/blob/master/LICENSE) | 是           | 纠删码模式                | 数据存储（对象存储）         |
| Drone CI   | [drone](https://github.com/harness/drone)            | [Drone Non-Commercial License](https://github.com/harness/drone/blob/master/LICENSE) | 否           | 单机                      | 构建与 Docker 镜像提交       |
| Harbor     | [harbor](https://github.com/goharbor/harbor)         | [Apache License 2.0](https://github.com/goharbor/harbor/blob/main/LICENSE) | 否           | 单机                      | 存储容器文件与维护容器注册表 |
| Reposilite | [reposilite](https://github.com/dzikoysk/reposilite) | [Apache License 2.0](https://github.com/dzikoysk/reposilite/blob/main/LICENSE) | 否           | 单机                      | 存储 Maven 包                |

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

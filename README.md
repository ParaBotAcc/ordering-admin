# ordering-admin 点餐管理后台

基于 [ELADMIN](https://github.com/elunez/eladmin) JPA 版搭建的点餐系统管理后台。

## 快速开始

### 前置条件

项目需要共享 `ordering-system` 的数据库和 Redis，确保以下服务已启动：

```bash
cd ../ordering-system
docker compose up -d          # MySQL + Redis + phpMyAdmin
```

### 初始化数据库

ELADMIN 的系统表（`sys_*`, `code_*`, `mnt_*` 等）已通过 SQL 文件导入到 `ordering` 数据库，与业务表 `orders` / `menu` 共存，互不冲突。

如需重新导入：

```bash
docker exec -i ordering-mysql mysql -uordering -pordering123 ordering < sql/eladmin.sql
```

### 启动

```bash
cd eladmin-system
mvn package -DskipTests
java -jar target/eladmin-system-2.7.18.jar
```

访问 http://localhost:8000
账号: admin / 123456

### 开发模式

```bash
mvn spring-boot:run -pl eladmin-system
```

## 配置

配置文件：`eladmin-system/src/main/resources/config/`

| 文件 | 说明 |
|------|------|
| `application.yml` | 全局配置，激活 dev profile |
| `application-dev.yml` | 开发环境，数据库连接 ordering |
| `application-prod.yml` | 生产环境 |

## 集成说明

本项目和 `ordering-system` 是**两个独立的 Spring Boot 应用**，共享同一个 MySQL 数据库：

```
ordering 数据库
├── orders        ← 点餐 API 写入/读取
├── menu          ← 点餐 API 写入/读取
├── sys_*         ← 管理后台(ELADMIN) 系统表
├── code_*        ← 代码生成器表
└── mnt_*         ← 运维管理表
```

- 前端: Vue 2 + Element UI（ELADMIN 自带）
- 端口: 8000（点餐 API 在 8080）
- 鉴权: JWT（ELADMIN 自带），独立于点餐 API

## License

Apache 2.0（同 ELADMIN 原项目）

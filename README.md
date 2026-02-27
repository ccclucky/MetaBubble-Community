# MetaBubble-Community

MetaBubble-Community 是一个前后端分离的社区平台，聚焦内容发布、互动关系与权限治理三大能力，支持从用户发布到社区互动再到后台管理的完整业务链路。

> 当前项目推荐使用：
> - 前端：`metabubble-client2`
> - 后端：`metabubble-server`
> - `metabubble-client` 为历史前端实现，可忽略。

---

## 核心功能

- **用户与认证体系**
  - 用户注册、登录、登出
  - 基于 JWT 的登录态维持
  - Spring Security 鉴权与访问控制

- **内容生产与互动**
  - 帖子发布、浏览与管理
  - 评论/回复链路
  - 点赞、收藏等互动行为

- **社区关系网络**
  - 关注 / 取关机制
  - 关注关系驱动的社交连接

- **后台权限管理**
  - 用户、角色、菜单的权限模型
  - 基于角色的菜单访问控制（RBAC）

---

## 亮点机制设定

- **JWT + Redis 会话协同**
  - 登录后通过 JWT 进行请求身份识别
  - 用户会话与关键状态落 Redis，提升鉴权与访问效率

- **高频互动数据缓存化**
  - 点赞、收藏、关注等高频操作优先走缓存
  - 通过定时任务进行数据持久化，平衡实时体验与数据库压力

- **前后端代理解耦**
  - 前端以 `/api` 统一代理后端接口
  - 开发态与部署态都便于切换和扩展

- **结构化权限治理**
  - 角色-菜单映射清晰，便于后续扩展管理端功能与接口权限策略

---

## 项目结构

```text
MetaBubble-Community/
├── metabubble-client2/     # 当前前端（Nuxt 3）
├── metabubble-server/      # 后端服务（Spring Boot）
├── metabubble-client/      # 旧版前端（可忽略）
└── demo2/                  # 其他实验目录
```

---

## 技术栈

### 前端（metabubble-client2）
- Nuxt 3
- Vue 3
- Pinia
- Tailwind CSS + DaisyUI
- Element Plus

### 后端（metabubble-server）
- Spring Boot 2.7
- Spring Security
- Spring Data JPA
- MySQL
- Redis
- JWT

---

## 环境要求

- Node.js 18+
- npm 9+
- JDK 8
- Maven 3.8+
- MySQL 8.x
- Redis 6+

---

## 快速开始

### 1) 启动后端（metabubble-server）

#### 1.1 初始化数据库

1. 在 MySQL 中创建数据库（例如 `community`）。
2. 导入初始化 SQL：

```bash
mysql -u root -p community < metabubble-server/src/main/resources/sql/community.sql
```

#### 1.2 配置后端连接

请在 `metabubble-server/src/main/resources/` 下补充 `application.yml`（或 `application.properties`），至少包含：

- `spring.datasource.*`
- `spring.redis.*`
- `server.port`（建议使用 `9999`，与前端代理一致）

#### 1.3 启动后端服务

```bash
cd metabubble-server
mvn spring-boot:run
```

> 若未配置端口，Spring Boot 默认端口为 `8080`。

---

### 2) 启动前端（metabubble-client2）

```bash
cd metabubble-client2
npm install
npm run dev
```

开发地址默认：`http://localhost:3000`

当前 `nuxt.config.ts` 已配置 `/api` 代理，请按本地后端端口调整 `target`。

---

## 常用命令

### 前端（metabubble-client2）

```bash
npm run dev       # 本地开发
npm run build     # 生产构建
npm run preview   # 预览构建结果
npm run lint      # 代码检查
```

### 后端（metabubble-server）

```bash
mvn spring-boot:run   # 启动服务
mvn test              # 运行测试
mvn clean package     # 打包
```

---

## 联调与部署建议

- 前端通过 `/api` 转发请求到后端。
- 若后端端口不是 `9999`，请修改 `metabubble-client2/nuxt.config.ts` 的代理目标。
- 生产环境建议通过 Nginx / 网关统一配置 `/api` 反向代理。


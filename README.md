## 项目介绍

本项目的初衷是打造一门兼具实用性和易学性的测试课程。本项目将以 TDD 为切入口，讲解 Web 应用常见功能的测试，如：

- 数据库测试
- 测试表单验证
- 测试文件上传
- 测试邮件发送
- 测试远程 API 调用
- 测试「异常」抛出
- 模拟登录用户
- contract 测试

涉及了 Web 应用常见的测试层级，包括：
- 单元测试
- 集成测试

### 技术选型

| 技术                 | 说明                | 官网                                                 |
| -------------------- | ------------------- | ---------------------------------------------------- |
| SpringBoot           | 基本框架        | https://spring.io/projects/spring-boot               |
| SpringSecurity       | 认证和授权框架      | https://spring.io/projects/spring-security           |
| MyBatis              | ORM 框架             | http://www.mybatis.org/mybatis-3/zh/index.html       |
| MyBatisGenerator     | 数据层代码生成      | http://www.mybatis.org/generator/index.html          |
| PageHelper           | MyBatis 分页插件 | http://git.oschina.net/free/Mybatis_PageHelper       |
| Swagger-UI           | 文档生产工具        | https://github.com/swagger-api/swagger-ui            |
| Hibernator-Validator | 验证框架            | http://hibernate.org/validator                       |
| RabbitMQ             | 消息队列            | https://www.rabbitmq.com/                            |
| Redis                | 分布式缓存          | https://redis.io/                                    |
| Docker               | 应用容器引擎        | https://www.docker.com                               |
| Druid                | 数据库连接池        | https://github.com/alibaba/druid                     |
| JWT                  | JWT登录支持         | https://github.com/jwtk/jjwt                         |
| Lombok               | 简化对象封装工具    | https://github.com/rzwitserloot/lombok               |

### 开发环境

| 工具          | 版本号 | 下载                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 5.7    | https://www.mysql.com/                                       |
| Redis         | 5.0    | https://redis.io/download                                    |
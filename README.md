课程文档参见：[build-zhihu-with-springboot-and-tdd-manual](https://github.com/qianhuihuiji/build-zhihu-with-springboot-and-tdd-manual)


参考文章：
- [junit and test containers](https://www.freecodecamp.org/news/integration-testing-using-junit-5-testcontainers-with-springboot-example/)
这篇文章里面介绍了利用docker创建跟生产环境一样的容器进行测试的概念，可以用来最后跑集成测试的时候使用。尤其是mysql部分，搭配 Liquibase 技术进行数据库构建非常适合，这样可以对项目进行充分的集成测试。不过这块还有待研究，未实践过。这篇文章没有代码。

- [spring boot and unit tests](https://www.freecodecamp.org/news/unit-testing-services-endpoints-and-repositories-in-spring-boot-4b7d9dc2b772/)

这篇文章介绍了controller、service、jpa的单元测试，有代码。较常规，无甚出彩。

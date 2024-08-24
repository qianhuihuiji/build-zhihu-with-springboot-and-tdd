课程文档参见：[build-zhihu-with-springboot-and-tdd-manual](https://github.com/qianhuihuiji/build-zhihu-with-springboot-and-tdd-manual)


参考文章：
- [junit and test containers](https://www.freecodecamp.org/news/integration-testing-using-junit-5-testcontainers-with-springboot-example/)

这篇文章里面介绍了利用docker创建跟生产环境一样的容器进行测试的概念，可以用来最后跑集成测试的时候使用。尤其是mysql部分，搭配 Liquibase 技术进行数据库构建非常适合，这样可以对项目进行充分的集成测试。不过这块还有待研究，未实践过。这篇文章没有代码。

- [spring boot and unit tests](https://www.freecodecamp.org/news/unit-testing-services-endpoints-and-repositories-in-spring-boot-4b7d9dc2b772/)

这篇文章介绍了controller、service、jpa的单元测试，有代码。较常规，无甚出彩。

- https://github.com/SalithaUCSC/spring-boot-testing

这个项目里面涉及到一个注解：`AutoConfigureTestDatabase`，可以在测试的时候使用 h2 内存数据库进行测试。

- [https://www.springcloud.io/post/2022-09/spring-boot-micro-service-test/](https://www.springcloud.io/post/2022-09/spring-boot-micro-service-test/)

有一些集成测试的示例，并且有前置 sql 条件的执行操作，可以借鉴。

- [https://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services](https://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services)、[https://www.springboottutorial.com/integration-testing-for-spring-boot-rest-services](https://www.springboottutorial.com/integration-testing-for-spring-boot-rest-services)

对 MockMvc、 Mockito 应用较多。

- [https://reflectoring.io/unit-testing-spring-boot/](https://reflectoring.io/unit-testing-spring-boot/)

这是个系列文章的其中一篇，该系列文章包括 unit test、@WebMvcTest、 @DataJpaTest、Integration Test。其中提到的观点是：unit test 应该要运行的非常快，并且不依赖 spring。这点我十分赞同，如果一次测试需要消耗秒级别的时间，那么 tdd 开发将会十分痛苦，光是运行测试就要耗费大量的时间。unit test 运行时间应该是毫秒级别的。

第3篇文章使用到了 Flyway、Liquibase，这是两个数据库版本控制的库，值得借鉴参考。

- [https://docs.spring.io/spring-boot/docs/current/reference/html/test-auto-configuration.html](https://docs.spring.io/spring-boot/docs/current/reference/html/test-auto-configuration.html) 

Spring 对不同组件，例如redis、mysql、mongodb 进行测试的支持

- [https://www.javaguides.net/2022/03/spring-boot-unit-testing-service-layer.html](https://www.javaguides.net/2022/03/spring-boot-unit-testing-service-layer.html) 

一系列比较全的教程，不同层级的单元测试，还有 TestContainer 的使用（结合docker的）。TestContainer 是个用来搞集成测试的好东西，唯一的依赖就是 docker，通过命令行创建不用的容器（如redis、mysql、rabbitMq 等等），可以模拟一个跟生产环境一样的测试环境，最大范围地避免环境问题对测试代理的影响。


- [SpringBoot Test及注解详解](https://www.cnblogs.com/myitnews/p/12330297.html) 

 这篇文章详细里面介绍了 springboot test 里面的大量注解，对于理解springboot 测试非常重要！

- [保姆级教程 Spring Boot 单元测试](https://cloud.tencent.com/developer/article/1779117)

确实是保姆级教程

- [SpringBoot Test 人类使用指南](https://zhuanlan.zhihu.com/p/111418479)

- [优化基于@SpringBootTest 的测试案例，让你的测试飞起来](https://segmentfault.com/a/1190000041591890)

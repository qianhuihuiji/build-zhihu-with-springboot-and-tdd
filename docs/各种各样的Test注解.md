- @SpringBootTest
  创建测试中使用的ApplicationContext，原理是从包含测试的包开始向上搜索，直到找到一个用 @SpringBootApplication 或
  @SpringBootConfiguration 注解的类，这样就能启动Spring容器。

- @WebMvcTest
  自动配置Spring MVC基础设施，并将扫描的Bean限制在
  @Controller、@ControllerAdvice、@JsonComponent、Converter、GenericConverter、Filter、HandlerInterceptor、WebMvcConfigurer、WebMvcRegistrations
  以及 HandlerMethodArgumentResolver。当使用 @WebMvcTest 注解时，常规的 @Component 和 @ConfigurationProperties`
  Bean不会被扫描。@EnableConfigurationProperties 可以用来包括 @ConfigurationProperties Bean。

如果你需要注册额外的组件，比如Jackson Module，你可以通过在测试中使用 @Import 导入额外的配置类。

通常情况下，@WebMvcTest 仅限于一个controller，并与 @MockBean 结合使用，为需要的合作者提供mock实现。

@WebMvcTest 也自动配置了 MockMvc。 Mock MVC提供了一种强大的方式来快速测试MVC controller，而不需要启动一个完整的HTTP服务器。


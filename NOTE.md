- 如果你的项目依赖了spring-security，那么测试类（或方法）一般需要加@WithMockUser注解，
  其中POST测试都需要设CSRF token，如.with(csrf())，否则会报403。

- 由于脱离了Spring环境，所以它是个@Mock，不是@MockBean。
- 它有个注解@InjectMocks，意思是为该对象进行依赖注入（Mockito提供的功能）
- @SpringBootTest后，Spring将加载所有被管理的bean，基本等同于启动了整个服务
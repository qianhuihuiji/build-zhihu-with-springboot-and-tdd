- 如果你的项目依赖了spring-security，那么测试类（或方法）一般需要加@WithMockUser注解，
  其中POST测试都需要设CSRF token，如.with(csrf())，否则会报403。

- 由于脱离了Spring环境，所以它是个@Mock，不是@MockBean。
- 它有个注解@InjectMocks，意思是为该对象进行依赖注入（Mockito提供的功能）
- @SpringBootTest后，Spring将加载所有被管理的bean，基本等同于启动了整个服务

- PostAnswersTests 与 ViewQuestionsTests 两个类，启动测试的两种方式非常典型：

```text
由于加入了 SpringSecurity 与 JWT，因为自己实现了JwtAuthenticationFilter等一系列bean
 ，因此以前的方式启动容器会失败。而@SpringBootTest 注解会启动整个上下文，所以一定会成功；
 而@Import与@MockBean相结合，把需要的bean自己准备好，也能启动成功，并且速度会快很多，
 而且随着项目的变大，对比会越来越明显。
 
 这两种方式都保留，后续写文档的时候可以提出来进行对比。
```

- HandlerMethodArgumentResolver，来给 controller 注入一个当前登录的用户进来

- [laravel的消息通知](https://blog.csdn.net/gengfu_php/article/details/77823339)
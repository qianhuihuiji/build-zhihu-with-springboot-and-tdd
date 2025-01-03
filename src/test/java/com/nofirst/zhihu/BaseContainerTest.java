package com.nofirst.zhihu;

import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.TestcontainersConfiguration;

@TestPropertySource(
        properties = {
                "spring.kafka.consumer.auto-offset-reset=earliest"
        }
)
@SpringBootTest(classes = BuildZhihuWithSpringbootAndTddApplication.class)
public abstract class BaseContainerTest {

    @BeforeAll
    public static void start() {
        TestcontainersConfiguration.getInstance().updateUserConfig("testcontainers.reuse.enable", "true");

        mysqlContainer.start();
        kafkaContainer.start();
        redisContainer.start();
    }

    // 这里的 mysql:8.0 镜像最好先本地下载，不然启动测试会先尝试下载，测试时间会变得非常长
    public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("zhihu")
            .withUsername("root")
            .withPassword("root")
            .withReuse(true);

    public static final KafkaContainer kafkaContainer = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.6.1")
    ).withReuse(true);

    public static final RedisContainer redisContainer =
            new RedisContainer(DockerImageName.parse("redis:7.2.3")).withExposedPorts(6379).withReuse(true);

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        // 手动启动
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);

        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);

        registry.add("spring.data.redis.host", redisContainer::getHost);
        registry.add("spring.data.redis.port", redisContainer::getFirstMappedPort);

    }


}

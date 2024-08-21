package Configuration;


import container.MongoContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Slf4j
@Configuration
public class MongoContainerConfiguration {
    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoContainer mongoContainer) {
        return new SimpleMongoClientDatabaseFactory(
                String.format("mongodb://%s:%s/test",
                        mongoContainer.getHost(),
                        mongoContainer.getPort()));
    }

    /**
     * Release MongoContainer
     */
    @Bean(destroyMethod = "stop")
    public MongoContainer mongoContainer() {
        var mongoContainer = new MongoContainer();
        log.info("Starting mongo container, named={}", mongoContainer.getDockerImageName());
        mongoContainer.start();
        log.info("Started mongo container, named={} mapped to port={}",
                mongoContainer.getDockerImageName(),
                mongoContainer.getPort());

        return mongoContainer;
    }
}

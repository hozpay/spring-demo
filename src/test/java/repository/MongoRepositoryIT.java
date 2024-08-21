package repository;

import Configuration.MongoContainerConfiguration;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Mongo repository test.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {
                MongoRepository.class,
                MongoContainerConfiguration.class,
                MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class})
@Slf4j
class MongoRepositoryIT {
    @Autowired
    private MongoRepository mongoRepository;

    @Test
    @DisplayName("Test to check CRUD operation")
    void testToCheckCRUDOperationViaTestContainer() {
        String collectionName = "TestContainers";
        Containers containerEntity = new Containers("key", "mongo", "mongo:latest", 4.8);

        assertTrue(mongoRepository.createCollection(collectionName));
        log.info("Collection successfully created={}", collectionName);

        // Create Entity
        mongoRepository.saveEntity(collectionName, containerEntity);
        log.info("Collection successfully created={} with entity={}", collectionName, containerEntity);

        // Read Entity
        final Containers entity = mongoRepository.readEntity(collectionName, "key");
        assertEquals(containerEntity, entity);
        log.info("successfully read entity={} for collection={}", entity, collectionName);

        // Update Entity
        final UpdateResult updateResult = mongoRepository.updateEntity(collectionName, "key", 5);
        final Containers updatedEntity = mongoRepository.readEntity(collectionName, "key");
        assertEquals(1, updateResult.getModifiedCount());
        log.info("successfully updated version to={} for collection={}.Updated entity is={}",
                updatedEntity.version(),
                collectionName,
                updatedEntity);

        // Remove Entity
        final DeleteResult deleteResult = mongoRepository.removeEntity(collectionName, "key");
        assertEquals(1, deleteResult.getDeletedCount());
        final boolean existEntity = mongoRepository.existEntity(collectionName, "key");
        assertFalse(existEntity);
        log.info("Entity exist in collection={} is={}", collectionName, existEntity);


    }
}

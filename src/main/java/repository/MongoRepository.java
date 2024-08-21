package repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static repository.Containers.IMAGE_VERSION;
import static repository.Containers.KEY;


@Slf4j
@AllArgsConstructor
@Repository
public class MongoRepository {
    /**
     * The Mongo template.
     */
    final MongoTemplate mongoTemplate;

    /**
     * Create collection boolean.
     *
     * @param collectionName the collection name
     * @return the boolean
     */
    public boolean createCollection(String collectionName) {
        mongoTemplate.createCollection(collectionName);
        return mongoTemplate.collectionExists(collectionName);
    }

    /**
     * Save entity.
     *
     * @param collectionName the collection name
     * @param containers     the containers or entity
     */
    public void saveEntity(String collectionName, Containers containers) {
        mongoTemplate.save(containers, collectionName);
    }

    /**
     * Read entity containers.
     *
     * @param collectionName the collection name
     * @param id             the id
     * @return the containers
     */
    public Containers readEntity(String collectionName, String id) {
        return mongoTemplate.findById(id, Containers.class, collectionName);
    }

    /**
     * Update entity update result.
     *
     * @param collectionName the collection name
     * @param id             the id
     * @param object         the object
     * @return the update result
     */
// update a particular attribute
    public UpdateResult updateEntity(String collectionName, String id, int object) {
        final var query = getQuery(id);
        var update = new Update().set(IMAGE_VERSION, object);
        return mongoTemplate.updateFirst(query, update, collectionName);
    }

    /**
     * Remove entity delete result.
     *
     * @param collectionName the collection name
     * @param id             the id
     * @return the delete result
     */
    public DeleteResult removeEntity(String collectionName, String id) {
        final var query = getQuery(id);
        return mongoTemplate.remove(query, collectionName);
    }

    /**
     * Exist entity boolean.
     *
     * @param collectionName the collection name
     * @param id             the id
     * @return the boolean
     */
    public boolean existEntity(String collectionName, String id) {
        final var query = getQuery(id);
        return mongoTemplate.exists(query, collectionName);
    }
// query creation on where condition of Id
    private static Query getQuery(String id) {
        return new Query(
                (
                        where(KEY).is(id)
                )
        );
    }
}

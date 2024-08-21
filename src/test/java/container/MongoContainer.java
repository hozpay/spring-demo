package container;


import Configuration.TestNetwork;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

public class MongoContainer extends GenericContainer<MongoContainer> {

    public static final String MONGO_LATEST_IMAGE = "mongo:latest";
    public static final int DEFAULT_PORT = 27017;

    /**
     * Instantiate a {@link MongoContainer}
     */
    public MongoContainer() {
        super(MONGO_LATEST_IMAGE);
        withNetwork(Network.newNetwork())
                .withExposedPorts(DEFAULT_PORT);
    }

    public Integer getPort() {
        return getMappedPort(DEFAULT_PORT);
    }

}

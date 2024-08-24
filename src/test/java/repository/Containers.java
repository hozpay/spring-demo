package repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public record Containers(
        @Id  String id,
        @Field (CONTAINER_NAME) String name,
        @Field (IMAGE_NAME) String imageName,
        @Field (IMAGE_VERSION) double version) {

    public static final String KEY = "_id";
    public static final String CONTAINER_NAME = "DOC_CONTAINER_NAME";
    public static final String IMAGE_NAME = "DOC_IMAGE_NAME";
    public static final String IMAGE_VERSION = "DOC_IMAGE_VERSION";

}

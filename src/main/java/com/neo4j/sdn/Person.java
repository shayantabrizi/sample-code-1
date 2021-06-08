package com.neo4j.sdn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.neo4j.driver.internal.value.NullValue;
import org.springframework.data.neo4j.core.convert.Neo4jConversionService;
import org.springframework.data.neo4j.core.convert.Neo4jPersistentPropertyToMapConverter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Node(labels = "Person")
public class Person {

    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private UUID id;
    @Property
    private String name;
    @CompositeProperty(converter = SourceConverter.class)
    Source source;

    public Person(String name, Source source) {
        this.name = name;
        this.source = source;
    }

    public static class SourceConverter implements Neo4jPersistentPropertyToMapConverter<String, Source> {

        @Override
        public Source compose(Map<String, Value> map, Neo4jConversionService neo4jConversionService) {
            Value userId = map.get("source_userId");
            Value userName = map.get("source_userName");
            Value size = map.get("source_size");

            if (userId != null || userName != null || size != null) {
                return new Source(
                        userId != null ? userId.asString() : null,
                        userName != null ? userName.asString() : null,
                        size != null ? size.asLong() : null
                );
            } else {
                return null;
            }
        }

        @Override
        public Map<String, Value> decompose(Source source, Neo4jConversionService neo4jConversionService) {
            Map map = new LinkedHashMap<>();
            if (source != null) {
                map.put("source_userId", source.getUserId() != null ? Values.value(source.getUserId()) : NullValue.NULL);
                map.put("source_userName", source.getUserName() != null ? Values.value(source.getUserName()) : NullValue.NULL);
                map.put("source_size", source.getSize() != null ? Values.value(source.getSize()) : NullValue.NULL);
            }
            return map;

        }
    }
}


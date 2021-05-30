package com.neo4j.sdn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Node
public class Club {
    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private UUID id;

    @Property
    private String clubName;

    public Club(String clubName) {
        this.clubName = clubName;
    }
}

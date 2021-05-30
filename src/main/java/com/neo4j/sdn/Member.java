package com.neo4j.sdn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@RelationshipProperties
public class Member {
    @Id @GeneratedValue Long id;

    @TargetNode
    private Club club;
    private String date;

    public Member(Club club, String date) {
        this.club = club;
        this.date = date;
    }
}

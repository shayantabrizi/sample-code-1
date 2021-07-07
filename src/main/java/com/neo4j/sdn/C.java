package com.neo4j.sdn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@Node
@NoArgsConstructor
public class C {
    @Id
    private String id;

    public C(String id) {
        this.id = id;
    }
}

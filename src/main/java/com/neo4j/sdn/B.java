package com.neo4j.sdn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Getter
@Setter
@Node
public class B {
    @Id
    @GeneratedValue
    Long id;

    @Relationship
    public List<C2> c2s;

    public B(List<C2> c2s) {
        this.c2s = c2s;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @RelationshipProperties
    public static class C2 {
        @Id
        @GeneratedValue
        private Long id;
        @TargetNode
        private C c;

        public C2(C c) {
            this.c = c;
        }
    }
}

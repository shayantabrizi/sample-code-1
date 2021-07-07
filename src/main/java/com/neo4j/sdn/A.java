package com.neo4j.sdn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Node
public class A {
    @Id
    @GeneratedValue
    Long id;
    @Relationship
    Collection<C> cs;
    @Relationship
    Collection<B> bs;

    public A(Collection<C> cs, Collection<B> bs) {
        this.cs = cs;
        this.bs = bs;
    }
}

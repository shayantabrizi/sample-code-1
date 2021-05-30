package com.neo4j.sdn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
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
    @Property
    private String family;
    @Property
    private int age;
    @Relationship(type = "Member Of",direction = Relationship.Direction.OUTGOING)
    private Member member;

    public Person(String name, String family, int age, Member member) {
        this.name = name;
        this.family = family;
        this.age = age;
        this.member = member;
    }

    public Person(Person person) {
        this.name = person.name;
        this.family = person.family;
        this.age = person.age;
        this.member=person.member;
    }

    public Person withId(UUID uuid) {
        if (uuid.equals(id))
            return this;
        Person person = new Person(this);
        person.id = uuid;
        return person;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(family, person.family);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, family, age);
    }
}


package com.neo4j.sdn;

import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface PersonRepository extends Repository<Person, UUID> {
    Person save(Person person);
    Person findByName(String name);
}

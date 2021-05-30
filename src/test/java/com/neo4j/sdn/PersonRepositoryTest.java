package com.neo4j.sdn;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataNeo4jTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;
    @Autowired
    Driver driver;
    @Container
    private static final Neo4jContainer<?> neo4jContainer = new Neo4jContainer<>("neo4j:3.5.0");
    @Autowired
    Neo4jTemplate template;

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", neo4jContainer::getAdminPassword);
    }

    @AfterEach
    void cleanUp() {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> tx.run("MATCH (n) DETACH DELETE n"));
        }
    }

    @Test
    public void save_a_person() {
        repository.save(new Person("Ali", "Alavi", 20, new Member(new Club("Iran"), "2020")));
        try (Session session = driver.session()) {
            session.readTransaction(tx -> {
                Result result = tx.run("MATCH (n:Person) return n");
                Record personRecord = result.single();
                Assertions.assertThat(personRecord.get("name").asString()).isEqualTo("Ali");
                return null;
            });
        }
    }
}

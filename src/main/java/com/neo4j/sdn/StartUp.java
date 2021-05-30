package com.neo4j.sdn;

import org.neo4j.configuration.GraphDatabaseSettings;
import org.neo4j.configuration.connectors.BoltConnector;
import org.neo4j.configuration.helpers.SocketAddress;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
import org.neo4j.driver.*;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;

@Component
public class StartUp implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    Neo4jTemplate template;
    @Autowired
    PersonRepository repository;
    @Autowired
    Driver driver;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        method();

    }

    @Transactional
    void method() {
            template.deleteAll(Person.class);
            template.deleteAll(Club.class);
            // save by Neo4jTemplate
            Person person_1 = template.save(new Person("Reza", "Mahdavi", 22, new Member(new Club("Iran"), "2021")));
            // Save by Repository
            Person person_2 = repository.save(new Person("Ali" + new Random(System.currentTimeMillis()).nextInt(), "Alavi", 20, new Member(new Club("Iran"), "2020")));
            // Query by Repository
            Person ali = repository.findByName("Ali");
            // Query by Neo4jTemplate
            Optional<Person> reza = template.findById(person_1.getId(), Person.class);
            // Custom query
            Result result = driver.session().run("Match(n) return count(n) as count", TransactionConfig.builder().build());
            System.out.println("Entity Count: " + result.single().values().get(0));
    }
}

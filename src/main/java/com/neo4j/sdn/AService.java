package com.neo4j.sdn;

import java.util.Optional;
import java.util.Random;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.TransactionConfig;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AService {

    private final Neo4jTemplate template;

    public AService(Neo4jTemplate template) {
        this.template = template;
    }

    @Transactional
    public void method() {
        template.deleteAll(Person.class);
        Person person = template.save(new Person("John", new Source("123", "john-smith", 280L)));
        person.setSource(null);
        person.setName("Jack");
        Person updatedPerson = template.save(person);
        System.out.println("The updated person: " + updatedPerson.getName() + " " + updatedPerson.getSource() + " " +(updatedPerson.getSource() != null ? updatedPerson.getSource().getUserId() : null));
        Person savedPerson = template.findById(updatedPerson.getId(), Person.class).get();
        System.out.println("The found person: " + savedPerson.getName() + " " + savedPerson.getSource() + " " +(savedPerson.getSource() != null ? savedPerson.getSource().getUserId() : null));
    }
}
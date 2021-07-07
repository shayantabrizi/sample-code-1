package com.neo4j.sdn;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.TransactionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class AService {

    @Autowired
    Neo4jTemplate template;
    @Autowired
    Neo4jClient client;

    public void method() {
        client.query("match (n) detach delete n").run();
        final C c = new C("att");
        final B b = new B(List.of(new B.C2(c)));
        final A a = new A(List.of(c), List.of(b));

        a.getCs().forEach(definition -> template.save(definition));
        a.getBs().forEach(definition -> template.save(definition));
        template.save(a);

        final Optional<Map<String, Object>> one = client.query("Match (n{id:'att'})-[r]-(m:A) return count(r)").fetch().one();
        System.out.println(one.get().get("count(r)"));
    }
}
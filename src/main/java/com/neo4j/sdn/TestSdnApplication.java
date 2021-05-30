package com.neo4j.sdn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableNeo4jRepositories
public class TestSdnApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSdnApplication.class, args);
    }

}

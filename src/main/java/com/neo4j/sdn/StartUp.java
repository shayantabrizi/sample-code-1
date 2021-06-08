package com.neo4j.sdn;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartUp implements ApplicationListener<ContextRefreshedEvent> {
    private final AService aService;

    public StartUp(AService aService) {
        this.aService = aService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        aService.method();

    }

}
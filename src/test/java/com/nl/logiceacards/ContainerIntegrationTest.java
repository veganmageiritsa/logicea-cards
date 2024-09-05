package com.nl.logiceacards;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
@ActiveProfiles(value = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class ContainerIntegrationTest {
    
    static final MySQLContainer<?> mySQLContainer;
    
    static {
        mySQLContainer =
            new MySQLContainer<>("mysql:latest")
                .withReuse(true)
                .withDatabaseName("testcontainer")
                .withUsername("user")
                .withPassword("pass");
        
        mySQLContainer.start();
    }
    
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        r.add("spring.datasource.username", mySQLContainer::getUsername);
        r.add("spring.datasource.password", mySQLContainer::getPassword);
    }
    
}

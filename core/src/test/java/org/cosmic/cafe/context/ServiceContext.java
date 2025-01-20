package org.cosmic.cafe.context;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceContext {

    @Autowired
    DatabaseCleaner databaseCleaner;

    @AfterEach
    void clear() {
        databaseCleaner.clear();
    }
}

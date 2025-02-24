package org.cosmic.cafe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CrawlingSchedulerTest {

    @MockitoSpyBean
    CrawlingScheduler crawlingScheduler;

    @Test
    public void 크롤링은_1시간마다_실행된다() {
        await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> {
                    verify(crawlingScheduler, atLeast(2)).crawling();
                });
    }
}
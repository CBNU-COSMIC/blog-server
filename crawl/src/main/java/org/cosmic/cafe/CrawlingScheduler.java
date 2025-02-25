package org.cosmic.cafe;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrawlingScheduler {

    private final CrawlingService crawlingService;

    @Scheduled(fixedRateString = "${crawling.fixedRate}")
    public void crawling() {
        crawlingService.crawlPosts();
    }
}

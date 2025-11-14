package com.schwartz.github.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import com.github.benmanes.caffeine.cache.Caffeine;

public class CacheConfigurationTest {

    private final CacheConfiguration cacheConfiguration = new CacheConfiguration();

    @Test
    void cacheManagerBeanShouldBeCreated() {
        CacheManager cacheManager = cacheConfiguration.cacheManager();
        assertThat(cacheManager).isNotNull();
        assertThat(cacheManager).isInstanceOf(CaffeineCacheManager.class);
    }

    @Test
    void cacheManagerShouldProvideCaches() {
        CacheManager cacheManager = cacheConfiguration.cacheManager();
        Cache userCache = cacheManager.getCache("user");
        Cache repoCache = cacheManager.getCache("repository");

        assertThat(userCache).isNotNull();
        assertThat(repoCache).isNotNull();
    }
}

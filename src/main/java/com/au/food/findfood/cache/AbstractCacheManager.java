package com.au.food.findfood.cache;


import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

/**
 * Cache manager to be used to define any new cache.
 * <p>
 * Client should override the abstract {@code getValueLoader} to provide actual
 * cache loading logic.
 * <p>
 * @param <K> : Key for the get or set values from cache
 * @param <V> : Value to be stored in the cache
 */
@Slf4j
public abstract class AbstractCacheManager<K, V> {

    /**
     * Specifies the maximum number of entries the cache may contain.
     * <p>
     * When size is zero, elements will be evicted immediately after being loaded
     * into the cache. This can be useful in testing, or to disable caching
     * temporarily without a code change.
     */
    @Getter
    @Value("${cache.max.size}")
    private int maxCacheSize;
    
    /**
     * Specifies the number of days require to reload the cache.
     * <p>
     */
    @Getter
    @Value("${cache.reload.days}")
    private int reloadCacheInDays;

    private LoadingCache<K, V> loadingCache;

    /**
     * Get cached value for a given key
     *
     * @param key : Key provided by client
     * @return : Value cached or loaded
     */
    public V getValue(K key) throws ExecutionException {

            return loadingCache.get(key);

    }

    @PostConstruct
    private void initCache() {
        loadingCache = CacheBuilder.newBuilder().maximumSize(maxCacheSize)
                .refreshAfterWrite(reloadCacheInDays, TimeUnit.DAYS)
                .build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K key) throws Exception {
                        return getValueLoader().apply(key);
                    }
                });
    }

    /**
     * The actual caching logic. Client must override this and provide the
     * functionality to load/create cache.
     *
     * @return : Function to load cache value using cache key
     */
    protected abstract Function<K, V> getValueLoader();
}

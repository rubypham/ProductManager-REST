package com.minachi.rediscache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class ControlledCacheService {

	private static final String CONTROLLED_PREFIX = "myControlledPrefix_";
	private static final String KEY = "T(com.minachi.rediscache).getCacheKey(#relevant)";
	
	public static String getCacheKey(String relevant) {
		return CONTROLLED_PREFIX + relevant;
	}

	@Cacheable(cacheNames = Cache.DEFAULT_NAME, key = KEY )
	public String getFromCache(String relevant) {
		return null;
	}

	@CachePut(cacheNames = Cache.DEFAULT_NAME, key = KEY)
	public String populateCache(String relevant, String unrelevantTrackingId) {
		return "this is it again!";
	}

	@CacheEvict(cacheNames = Cache.DEFAULT_NAME, key = KEY)
	public void removeFromCache(String relevant) {
	}


	public static void main(String[] args) {
	}
}

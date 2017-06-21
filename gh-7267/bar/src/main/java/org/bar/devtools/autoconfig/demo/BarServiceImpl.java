package org.bar.devtools.autoconfig.demo;

import org.bar.devtools.foo.BarService;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author zaunerm
 */
class BarServiceImpl implements BarService {

    BarServiceImpl() {

    }

    @Cacheable(value = "bar-cache", key = "#foo")
    @Override
    public String bar(String foo) {
        return foo + "bar";
    }
}

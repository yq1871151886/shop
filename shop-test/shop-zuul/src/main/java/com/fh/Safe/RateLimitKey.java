package com.fh.Safe;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.DefaultRateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitUtils;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;

public class RateLimitKey {
    @Bean
    public RateLimitKeyGenerator rateLimitKeyGenerator(final RateLimitProperties properties, final RateLimitUtils rateLimitUtils) {
        //RateLimitPreFilter
        return new DefaultRateLimitKeyGenerator(properties, rateLimitUtils) {
            @Override
            public String key(final HttpServletRequest request, final Route route, final RateLimitProperties.Policy policy) {
                String name = request.getParameter("name");
                return super.key(request, route, policy)+":"+name;

            }
        };

    }





}

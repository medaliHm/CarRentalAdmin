package com.medach.location.admin.config.filters;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.medach.location.admin.config.MedAchProperties;

public class CachingHttpHeadersFilter implements Filter {

    /** Constant <code>DEFAULT_DAYS_TO_LIVE=1461</code> */
    public static final int DEFAULT_DAYS_TO_LIVE = 1461; // 4 years
    /** Constant <code>DEFAULT_SECONDS_TO_LIVE=TimeUnit.DAYS.toMillis(DEFAULT_DAYS_TO_LIVE)</code> */
    public static final long DEFAULT_SECONDS_TO_LIVE = TimeUnit.DAYS.toMillis(DEFAULT_DAYS_TO_LIVE);

    private long cacheTimeToLive = DEFAULT_SECONDS_TO_LIVE;

    private MedAchProperties jHipsterProperties;

    /**
     * <p>Constructor for CachingHttpHeadersFilter.</p>
     *
     * @param jHipsterProperties a {@link io.github.jhipster.config.JHipsterProperties} object.
     */
    public CachingHttpHeadersFilter(MedAchProperties jHipsterProperties) {
        this.jHipsterProperties = jHipsterProperties;
    }

    /** {@inheritDoc} */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        cacheTimeToLive = TimeUnit.DAYS.toMillis(jHipsterProperties.getHttp().getCache().getTimeToLiveInDays());
    }

    /** {@inheritDoc} */
    @Override
    public void destroy() {
        // Nothing to destroy
    }

    /** {@inheritDoc} */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Cache-Control", "max-age=" + cacheTimeToLive + ", public");
        httpResponse.setHeader("Pragma", "cache");

        // Setting Expires header, for proxy caching
        httpResponse.setDateHeader("Expires", cacheTimeToLive + System.currentTimeMillis());

        chain.doFilter(request, response);
    }
}
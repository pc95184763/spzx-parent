package com.spzx.common.core.interceptor;

import com.spzx.common.core.constant.SecurityConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignAddInnerHeaderInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate ) {
        requestTemplate.header(SecurityConstants.FROM_SOURCE, SecurityConstants.INNER);
    }


}

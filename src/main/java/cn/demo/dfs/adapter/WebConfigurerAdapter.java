package cn.demo.dfs.adapter;

import cn.demo.dfs.interceptor.ParamterInterceptor;
import cn.demo.dfs.interceptor.ReturnHandler;
import cn.demo.dfs.interceptor.SignValidateInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Component
@Configuration
public class WebConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加拦截器
        registry.addInterceptor(new ParamterInterceptor())
                .addPathPatterns("/**");
//        registry.addInterceptor(new SignValidateInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/notify/**")
//                .excludePathPatterns("/bankQuota/bankList")
//                .excludePathPatterns("/recharge/getSupportBanks")
//                .excludePathPatterns("/**/nv*/**")
//                .excludePathPatterns("/account/getBanks")
//                .excludePathPatterns("/storedAccountRecharge/detailList")
//                .excludePathPatterns("/storedAccountRecharge/exportDetailListToShop");

        super.addInterceptors(registry);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new ReturnHandler());
        super.addReturnValueHandlers(returnValueHandlers);
    }
}
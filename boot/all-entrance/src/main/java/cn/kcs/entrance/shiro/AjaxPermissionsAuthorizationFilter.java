package cn.kcs.entrance.shiro;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author kcs
 * @date 2018-10-26 11:20
 **/
public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger("AjaxPermissionsAuthorizationFilter");

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "201");
        jsonObject.put("message", "登录过期，请重新登录！");
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = response.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registration(AjaxPermissionsAuthorizationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }
}

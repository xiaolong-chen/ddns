package world.xiaolong.ddns.filter;

import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import world.xiaolong.ddns.util.CommonUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class TraceIDFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CommonUtil.confTraceID(ServletUtil.getClientIP((HttpServletRequest) servletRequest));
        filterChain.doFilter(servletRequest, servletResponse);
    }

}

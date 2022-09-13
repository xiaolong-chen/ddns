package world.xiaolong.ddns.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import world.xiaolong.ddns.util.CommonUtil;

import javax.servlet.*;
import java.io.IOException;

@Component
@Order(1)
public class TraceIDFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CommonUtil.confTraceID();
        filterChain.doFilter(servletRequest, servletResponse);
    }

}

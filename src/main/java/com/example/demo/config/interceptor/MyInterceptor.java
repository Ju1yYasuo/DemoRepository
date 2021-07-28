package com.example.demo.config.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.demo.config.annotation.ControllerAnn;
import com.example.demo.config.annotation.InterceptorAnn;
import com.example.demo.util.entity.ResponseEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拦截器
 *
 * @author luox
 * @date 2021/7/19
 */
@Component
@InterceptorAnn
public class MyInterceptor implements HandlerInterceptor {

    @Autowired

    private static final Log sysLog = LogFactory.getLog(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //跨域的第一次预请求处理
        if ("OPTIONS".equals(request.getMethod()) || !(handler instanceof HandlerMethod)) {
            return true;
        }

        //日志查看类
        //Log log = new Log();
        //log.setContent(request.getRequestURI());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ControllerAnn controllerAnn = handlerMethod.getMethodAnnotation(ControllerAnn.class);
        //需要进行权限认证
        if (ObjectUtil.isNull(controllerAnn) || controllerAnn.needLogin()) {
            if (!StpUtil.isLogin()) {
                return flush(response, ResponseEntity.errorMessage("未登录").toString());
            }
            //log.setUserId(Integer.parseInt(StpUtil.getLoginId().toString()));
        } else {
            //log.setRemarks(request.getRemoteAddr());
        }
        //logService.save(log);
        return true;
    }

    /**
     * 拦截器中断后返回给前台消息
     *
     * @param res      res
     * @param errorMsg 错误消息
     * @return boolean
     * @throws IOException ioexception
     * @author luox
     * @date 2021/07/19
     */
    public boolean flush(HttpServletResponse res, String errorMsg) throws IOException {
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = res.getWriter();
        pw.write(errorMsg);
        pw.flush();
        pw.close();
        return false;
    }
}

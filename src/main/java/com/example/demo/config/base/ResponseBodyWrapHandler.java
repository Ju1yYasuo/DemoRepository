package com.example.demo.config.base;

import cn.hutool.json.JSONUtil;
import com.example.demo.util.entity.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 对结果集的包装
 *
 * @author luox
 * @date 2022/05/23
 */
@Slf4j
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        super();
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return delegate.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        Class<?> containingClass = methodParameter.getContainingClass();
        log.debug("==> {}.{}", containingClass.getCanonicalName(), methodParameter.getMethod().getName());
        log.debug("==> 响应结果：{}", JSONUtil.toJsonStr(o));
        if (containingClass.isAnnotationPresent(com.example.demo.config.annotation.ResponseEntity.class)
                || methodParameter.hasMethodAnnotation(com.example.demo.config.annotation.ResponseEntity.class)) {
            if (o instanceof Boolean) {
                Boolean bool = (Boolean) o;
                o = ResponseEntity.info(bool);
            }else{
                o = ResponseEntity.success(o);
            }
        }

        delegate.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
    }

}

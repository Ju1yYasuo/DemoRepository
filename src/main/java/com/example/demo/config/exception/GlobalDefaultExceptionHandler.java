package com.example.demo.config.exception;

import com.example.demo.util.entity.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 全局异常默认处理
 *
 * @author luox
 * @date 2021/6/24
 */
@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Boolean> exceptionHandler(HttpServletRequest req, Exception e) {
        StringBuilder sb = new StringBuilder("error report ---\n");
        sb.append("→Class    : ").append(req.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingHandler")).append("\n");
        sb.append("→Message  : ").append(e.getMessage()).append("()\n");
        Enumeration<String> enumeration = req.getParameterNames();
        if (enumeration.hasMoreElements()) {
            sb.append("→Parameter: ");
            for(; enumeration.hasMoreElements(); sb.append("  ")) {
                String name = enumeration.nextElement();
                String[] values = req.getParameterValues(name);
                if (values.length == 1) {
                    sb.append(name).append("=").append(values[0]);
                } else {
                    sb.append(name).append("[]={");
                    for(int i = 0; i < values.length; ++i) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        sb.append(values[i]);
                    }
                    sb.append("}");
                }
            }
            sb.append("\n");
        }
        log.error(sb.toString(),e);
        return new ResponseEntity<>(ResponseEntity.SERVER_ERROR,e.toString(),false);
    }

//    /**
//     * 应用到所有被@RequestMapping注解的方法，在其执行之前初始化数据绑定器
//     * @param binder
//     */
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {}
//
//    /**
//     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
//     * @param model
//     */
//    @ModelAttribute
//    public void addAttributes(Model model) {
//        model.addAttribute("words", "hello world");
//    }

}
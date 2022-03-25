package com.example.demo.config.exception;

import com.example.demo.util.entity.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常默认处理
 *
 * @author luox
 * @date 2021/6/24
 */
@ControllerAdvice
@ResponseBody
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Boolean> exceptionHandler(Exception e) {
        return new ResponseEntity<>(ResponseEntity.SERVER_ERROR,e.getMessage(),false);
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
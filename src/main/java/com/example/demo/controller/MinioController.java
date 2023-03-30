package com.example.demo.controller;

import com.example.demo.config.annotation.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * minio 前端控制器
 *
 * @author luox
 * @date 2022/11/25
 */
@RestController
@RequestMapping("/minio")
@ResponseEntity
public class MinioController {


    @PostMapping("/upload")
    public boolean upload(MultipartFile file){


        return true;
    }



}

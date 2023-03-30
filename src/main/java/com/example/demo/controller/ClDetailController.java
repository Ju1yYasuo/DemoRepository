package com.example.demo.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.annotation.ResponseEntity;
import com.example.demo.entity.ClDetail;
import com.example.demo.service.ClDetailService;
import com.example.demo.util.vo.BaseBodyVo;
import com.example.demo.util.vo.BaseQueryVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 *  前端控制器
 *
 * @author luox
 * @since 2023-03-30
 */
@RestController
@RequestMapping("/cl_detail")
@ResponseEntity
public class ClDetailController {

    @Autowired
    private ClDetailService clDetailService;

    /**
     * 根据id查询详情
     *
     * @param id id
     * @return {@link ClDetail }
     * @author luox
     * @date 2023-03-30
     */
    @GetMapping("/get/{id}")
    public ClDetail getById(@PathVariable("id") Long id){
        return clDetailService.getById(id);
    }

    /**
     * 分页查询
     *
     * @param pageVo 分页vo
     * @param clDetail 
     * @return {@link Page<ClDetail> }
     * @author luox
     * @date 2023-03-30
     */
    @GetMapping("/page")
    public Page<ClDetail> page(BaseQueryVo<ClDetail> pageVo, ClDetail clDetail) {
        return clDetailService.page(pageVo, clDetail);
    }

    /**
     * 保存
     *
     * @param clDetail 
     * @return {@link Boolean }
     * @author luox
     * @date 2023-03-30
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody ClDetail clDetail){
        return clDetailService.save(clDetail);
    }

    /**
     * 更新
     *
     * @param clDetail 
     * @return {@link Boolean }
     * @author luox
     * @date 2023-03-30
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody ClDetail clDetail){
        return clDetailService.updateById(clDetail);
    }

    /**
     * 删除
     *
     * @param baseBodyVo 请求body基础vo
     * @return {@link Boolean }
     * @author luox
     * @date 2023-03-30
     */
    @PostMapping("/delete")
    public Boolean delete(@RequestBody @Validated BaseBodyVo baseBodyVo){
        return clDetailService.removeByIds(baseBodyVo.getIds());
    }

    /**
     *  获取重复报销
     *
     * @param response 响应
     * @throws IOException ioexception
     * @author luox
     * @date 2023/03/21
     */
    @PostMapping("/getRepeatDetail")
    public void getRepeatDetail(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = clDetailService.getRepeatDetail();
        String currentTimeString = DateUtil.now();
        String fileName = "差旅重复报销" + currentTimeString;

        response.setCharacterEncoding("UTF-8");
        //允许除默认的首部可以作为响应的一部分暴露给外部
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        //通过响应头告诉浏览器该文件为下载文件，并指定默认文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8") + ".xlsx");
        response.setContentType("application/octet-stream");

        workbook.write(response.getOutputStream());
        workbook.close();
    }

}

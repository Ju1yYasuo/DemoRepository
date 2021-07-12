package com.example.demo.modules.sys.controller;

import com.example.demo.common.entity.QueryEntity;
import com.example.demo.common.entity.ResponseEntity;
import com.example.demo.modules.sys.entity.Department;
import com.example.demo.modules.sys.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门信息 前端控制器
 * </p>
 *
 * @author luox
 * @since 2021-07-12
 */
@RestController
@RequestMapping("/sys/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取部门信息
     *
     * @param queryEntity 查询实体
     * @return {@link ResponseEntity<List<Department>> }
     * @author luox
     * @date 2021-07-12
     */
    @PostMapping("/getDepartment")
    public ResponseEntity<List<Department>> getDepartment(@RequestBody QueryEntity<Department> queryEntity) {
        return departmentService.getDepartment(queryEntity);
    }

    /**
     * 保存部门信息
     *
     * @param list 列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-12
     */
    @PostMapping("/saveDepartment")
    @Transactional
    public ResponseEntity<Boolean> saveDepartment(@RequestBody List<Department> list){
        return departmentService.saveDepartment(list);
    }

    /**
     * 更新部门信息
     *
     * @param list 列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-12
     */
    @PutMapping("/updateDepartment")
    @Transactional
    public ResponseEntity<Boolean> updateDepartment(@RequestBody List<Department> list){
        return departmentService.updateDepartment(list);
    }

    /**
     * 删除部门信息
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author luox
     * @date 2021-07-12
     */
    @DeleteMapping("/deleteDepartment")
    @Transactional
    public ResponseEntity<Boolean> deleteDepartment(@RequestBody List<Long> idList){
        return departmentService.deleteDepartment(idList);
    }

}

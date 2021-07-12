package com.example.demo.modules.sys.service;

import com.example.demo.common.entity.QueryEntity;
import com.example.demo.common.entity.ResponseEntity;
import com.example.demo.modules.sys.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门信息 服务类
 * </p>
 *
 * @author luox
 * @since 2021-07-12
 */
public interface DepartmentService extends IService<Department> {

    ResponseEntity<List<Department>> getDepartment(QueryEntity<Department> queryEntity);

    ResponseEntity<Boolean> saveDepartment(List<Department> list);

    ResponseEntity<Boolean> updateDepartment(List<Department> list);

    ResponseEntity<Boolean> deleteDepartment(List<Long> idList);

}

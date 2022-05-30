package com.example.demo.config.es.repository;

import com.example.demo.config.es.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 产品dao类
 *
 * @author luox
 * @date 2022/5/26
 */
public interface ProductsRepository
        //extends ElasticsearchRepository<Products,Integer>
 {

     //分页查询
    Page<Products> findByTitle(String title, PageRequest pageable);

}

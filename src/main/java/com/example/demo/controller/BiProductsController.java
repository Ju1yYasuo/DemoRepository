package com.example.demo.controller;

import com.example.demo.config.annotation.ResponseEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.es.entity.Products;
import com.example.demo.config.es.repository.ProductsRepository;
import com.example.demo.util.json.JsonUtil;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.analysis.AnalyzerComponents;
import org.elasticsearch.rest.RestStatus;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.completion.Completion;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.example.demo.service.BiProductsService;
import com.example.demo.entity.BiProducts;
import com.example.demo.util.vo.BaseBodyVo;
import com.example.demo.util.vo.BaseQueryVo;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * es-产品信息 前端控制器
 *
 * @author luox
 * @since 2022-05-31
 */
@RestController
@RequestMapping("/bi_products")
@ResponseEntity
public class BiProductsController {

    @Autowired
    private BiProductsService biProductsService;

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private ProductsRepository productsRepository;

    /**
     * 根据id查询es-产品信息详情
     *
     * @param id id
     * @return {@link BiProducts }
     * @author luox
     * @date 2022-05-31
     */
    @GetMapping("/get/{id}")
    public BiProducts getById(@PathVariable("id") Long id){
        return biProductsService.getById(id);
    }

    /**
     * 分页查询es-产品信息
     *
     * @param pageVo 分页vo
     * @param biProducts es-产品信息
     * @return {@link Page<BiProducts> }
     * @author luox
     * @date 2022-05-31
     */
    @GetMapping("/page")
    public Page<BiProducts> page(BaseQueryVo<BiProducts> pageVo, BiProducts biProducts) {
        return biProductsService.page(pageVo, biProducts);
    }

    /**
     * 保存es-产品信息
     *
     * @param biProducts es-产品信息
     * @return {@link Boolean }
     * @author luox
     * @date 2022-05-31
     */
    @PostMapping("/save")
    @Transactional
    public Boolean save(@RequestBody BiProducts biProducts) throws IOException {
        biProductsService.save(biProducts);
        Products products = new Products();
        BeanUtils.copyProperties(biProducts,products);
        products.setPrice(biProducts.getPrice().doubleValue());
        //搜索建议，可以请求分词器来设置搜索建议词
        products.setSuggest(new Completion(biProducts.getSuggests().split(",")));

        IndexRequest request = new IndexRequest(EsController.INDEX_NAME);
        request.id(biProducts.getId().toString()).source(JsonUtil.toJsonString(products), XContentType.JSON);
        int status = client.index(request, RequestOptions.DEFAULT).status().getStatus();
        //productsRepository.save(products);

        return status == RestStatus.CREATED.getStatus();
    }

    /**
     * 更新es-产品信息
     *
     * @param biProducts es-产品信息
     * @return {@link Boolean }
     * @author luox
     * @date 2022-05-31
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody BiProducts biProducts) throws IOException {
        Products products = new Products();
        BeanUtils.copyProperties(biProducts,products);
        UpdateRequest updateRequest = new UpdateRequest(EsController.INDEX_NAME,products.getId().toString());

        updateRequest.doc(JsonUtil.toJsonString(products), XContentType.JSON);

        client.update(updateRequest,RequestOptions.DEFAULT);
        return biProductsService.updateById(biProducts);
    }

    /**
     * 删除es-产品信息
     *
     * @param baseBodyVo 请求body基础vo
     * @return {@link Boolean }
     * @author luox
     * @date 2022-05-31
     */
    @PostMapping("/delete")
    public Boolean delete(@RequestBody @Validated BaseBodyVo baseBodyVo) throws IOException {

        //for(Long id : baseBodyVo.getIds()){
        //    DeleteRequest deleteRequest = new DeleteRequest(EsController.INDEX_NAME,id.toString());
        //    client.delete(deleteRequest, RequestOptions.DEFAULT);
        //}
        List<String> ids = baseBodyVo.getIds().stream().map(Object::toString).collect(Collectors.toList());
        productsRepository.deleteAllById(ids);
        return biProductsService.removeByIds(baseBodyVo.getIds());
    }

}

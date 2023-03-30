package com.example.demo;

import com.example.demo.entity.ClDetail;
import com.example.demo.mapper.ClDetailMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Autowired
    private ClDetailMapper clDetailMapper;

    @Test
    public void test1(){
        List<ClDetail> list = clDetailMapper.getRepeatDetail();
        Map<String,Map<String,Map<String, Map<String,List<ClDetail>>>>> map = list.stream().collect(
                Collectors.groupingBy(ClDetail::get费用项目编码,Collectors.groupingBy(ClDetail::get报销人,
                        Collectors.groupingBy(ClDetail::get出发日期,Collectors.groupingBy(ClDetail::get到达日期)))));

        List<ClDetail> outList = new LinkedList<>();
        map.forEach((k1,v1) -> {
            v1.forEach((k2,v2) -> {
                v2.forEach((k3,v3) -> {
                    v3.forEach((k4,v4) -> {
                        //排序
                        v4.sort(Comparator.comparing(ClDetail::get单据编号));
                        //v4.size()一定大于1
                        for(int i = 0,size = v4.size() - 1;i < size;i++){
                            if(!v4.get(i).get单据编号().equals(v4.get(i + 1).get单据编号())){
                                outList.add(v4.get(i));
                                outList.add(v4.get(i + 1));
                            }
                        }
                    });
                });
            });
        });

        outList.forEach(System.out::println);

    }
}

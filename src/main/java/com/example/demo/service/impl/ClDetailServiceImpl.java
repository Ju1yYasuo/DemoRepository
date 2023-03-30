package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.ClDetail;
import com.example.demo.mapper.ClDetailMapper;
import com.example.demo.service.ClDetailService;
import com.example.demo.util.vo.BaseQueryVo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  服务实现类
 *
 * @author luox
 * @since 2023-03-30
 */
@Service
public class ClDetailServiceImpl extends ServiceImpl<ClDetailMapper, ClDetail> implements ClDetailService {

    @Override
    public Page<ClDetail> page(BaseQueryVo<ClDetail> pageVo, ClDetail clDetail){
        LambdaQueryWrapper<ClDetail> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.setEntity(clDetail);
        return page(pageVo.toPage(), queryWrapper);
    }

    @Override
    public XSSFWorkbook getRepeatDetail() throws IOException {
        InputStream inputStream = new ClassPathResource("document/差旅重复报销.xlsx").getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        inputStream.close();

        Sheet sheet = workbook.getSheetAt(0);
        short defaultHeight = sheet.getRow(0).getHeight();
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        List<ClDetail> list = baseMapper.getRepeatDetail();
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


        for(int i = 0,size = outList.size();i < size;i++) {
            ClDetail clDetail = outList.get(i);
            Row row = sheet.createRow(i + 1);
            row.setHeight(defaultHeight);

            Cell cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(clDetail.get费用项目编码());

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(clDetail.get报销人());

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(clDetail.get出发日期());

            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(clDetail.get到达日期());

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(clDetail.get单据编号());
        }

        return workbook;
    }

}

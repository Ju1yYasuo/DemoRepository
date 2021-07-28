package com.example.demo.util.document;

import cn.hutool.poi.excel.cell.CellUtil;
import com.example.demo.config.exception.MyException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Poi文档处理
 *
 * @author luox
 * @date 2021/7/21
 */
public class PoiUtil {

    /**
     * 获得cell值
     *
     * @param cell  cell
     * @param clazz clazz
     * @return {@link Object }
     * @author luox
     * @date 2021/07/26
     */
    public static Object getCellValue(Cell cell,Class clazz){
        return getCellValue(cell,clazz,false);
    }

    /**
     * 获得cell值
     *
     * @param cell       cell
     * @param clazz      clazz
     * @param canBeEmpty 能否为空值
     * @return {@link Object }
     * @author luox
     * @date 2021/07/26
     */
    public static Object getCellValue(Cell cell,Class clazz,boolean canBeEmpty){
        Object cellValue = null;
        String className = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
        switch (className){
            case "String" :
                cellValue = getStringValue(cell,canBeEmpty);
                break;
            case "Integer" :
                cellValue = getIntegerValue(cell,canBeEmpty);
                break;
            case "BigDecimal" :
                cellValue = getBigDecimalValue(cell,canBeEmpty);
                break;
            case "Date" :
                cellValue = getDateValue(cell,canBeEmpty);
            default:
        }
        return cellValue;
    }

    /**
     * 得到字符串值
     *
     * @param cell cell
     * @return {@link String }
     * @author luox
     * @date 2021/07/26
     */
    public static String getStringValue(Cell cell) {
        return getStringValue(cell,false);
    }

    /**
     * 得到字符串值
     *
     * @param cell       cell
     * @param canBeEmpty 能否为空值
     * @return {@link String }
     * @author luox
     * @date 2021/07/26
     */
    public static String getStringValue(Cell cell,boolean canBeEmpty) {
        Object value = getValue(cell,canBeEmpty);
        return value.toString();
    }

    /**
     * 得到整数值
     *
     * @param cell cell
     * @return {@link Integer }
     * @author luox
     * @date 2021/07/26
     */
    public static Integer getIntegerValue(Cell cell) {
        return getIntegerValue(cell,false);
    }

    /**
     * 得到整数值
     *
     * @param cell       cell
     * @param canBeEmpty 能否为空值
     * @return {@link Integer }
     * @author luox
     * @date 2021/07/26
     */
    public static Integer getIntegerValue(Cell cell,boolean canBeEmpty) {
        Long value = (Long) getValue(cell,canBeEmpty);
        return value.intValue();
    }

    /**
     * 得到BigDecimal值
     *
     * @param cell cell
     * @return {@link BigDecimal }
     * @author luox
     * @date 2021/07/26
     */
    public static BigDecimal getBigDecimalValue(Cell cell) {
        return getBigDecimalValue(cell,false);
    }

    /**
     * 得到BigDecimal值
     *
     * @param cell       cell
     * @param canBeEmpty 能否为空值
     * @return {@link BigDecimal }
     * @author luox
     * @date 2021/07/26
     */
    public static BigDecimal getBigDecimalValue(Cell cell,boolean canBeEmpty) {
        Object value = getValue(cell,canBeEmpty);
        if(value instanceof Long){
            return BigDecimal.valueOf((Long) value);
        }
        return BigDecimal.valueOf((Double) value);
    }

    /**
     * 获取日期值
     *
     * @param cell cell
     * @return {@link Date }
     * @author luox
     * @date 2021/07/26
     */
    public static Date getDateValue(Cell cell){
        return getDateValue(cell,false);
    }

    /**
     * 获取日期值
     *
     * @param cell       cell
     * @param canBeEmpty 能否为空值
     * @return {@link Date }
     * @author luox
     * @date 2021/07/26
     */
    public static Date getDateValue(Cell cell,boolean canBeEmpty) {
        Object value = getValue(cell,canBeEmpty);
        return (Date) value;
    }

    /**
     * 获取值
     *
     * @param cell       cell
     * @param canBeEmpty 能否为空值
     * @return {@link Object }
     * @author luox
     * @date 2021/07/26
     */
    public static Object getValue(Cell cell,boolean canBeEmpty){
        Object value = CellUtil.getCellValue(cell);
        if(!canBeEmpty && isBlank(value)){
            throw new MyException("不能为空.");
        }
        return value;
    }

    /**
     * CellUtil.getCellValue()至少返回""
     * 是否为空字符串
     *
     * @param object 对象
     * @return boolean
     * @author luox
     * @date 2021/07/22
     */
    private static boolean isBlank(Object object){
        if(object == null){
            return true;
        }
        return StringUtils.isBlank(object.toString());
    }

}

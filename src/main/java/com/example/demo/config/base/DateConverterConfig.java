package com.example.demo.config.base;

import cn.hutool.core.util.StrUtil;
import com.example.demo.config.exception.MyException;
import com.example.demo.util.common.MathUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期转换配置
 *
 * @author luox
 * @date 2022/05/26
 */
//@Component
public class DateConverterConfig implements Converter<String, Date> {

    private static final List<String> formatters = new ArrayList<>(MathUtil.getHashMapBestLength(4));

    static {
        formatters.add("yyyy-MM");
        formatters.add("yyyy-MM-dd");
        formatters.add("yyyy-MM-dd HH:mm");
        formatters.add("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public Date convert(String source) {
        if(StrUtil.isBlank(source)){
            return null;
        }
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source, formatters.get(0));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, formatters.get(1));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formatters.get(2));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formatters.get(3));
        } else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    private Date parseDate(String dateStr, String format) {
        Date date;
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new MyException("parseDate error");
        }
        return date;
    }
}

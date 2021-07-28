package com.example.demo.util.common;

/**
 * 常量
 *
 * @author luox
 * @date 2021/7/20
 */
public class Constant {

    /**
     * excel默认文件类型,xlsx文件类型
     */
    public static final String XLSX_FILE_TYPE = "xlsx";

    /**
     * 环境信息上传标题
     */
    public static final String[] ENVIRONMENT_INFO_UPLOAD_TITLE = {"日期(yyyy-MM-dd)*","PM2.5(ug)*","PM10(ug)*","风速(级)*",
            "噪音(dB)*","湿度(%)*","温度(℃)*","渣土防护率(%)*","表土保护率(%)*","环境整改率(%)*"};

    /**
     * 人员信息上传标题
     */
    public static final String[] USER_UPLOAD_TITLE = {"用户名*","姓名*","性别*","年龄*","卡号*","部门*","岗位*","工种*","职业代码*",
            "职业等级*","安全帽类型*","绑定安全帽*","人员编号*","是否进入大屏*","电话号码","身份证号码","紧急联系人","紧急联系人电话"};

    /**
     * 部门上传标题
     */
    public static final String[] DEPARTMENT_UPLOAD_TITLE = {"部门名称*","上级部门*","部门编码*","是否是公司*"};

}

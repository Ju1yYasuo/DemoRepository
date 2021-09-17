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
    public static final String[] USER_UPLOAD_TITLE = {"用户名*","姓名*","性别*","年龄","实名制卡号","部门*","岗位*","工种*","职业",
            "职业等级","安全帽类型*","安全帽编号*","用户工号","进入大屏*","电话","身份证","紧急联系人","联系人电话","有效期*"};

    /**
     * 部门上传标题
     */
    public static final String[] DEPARTMENT_UPLOAD_TITLE = {"部门名称*","上级部门*","部门编码*","是否是公司*"};

    /**
     * 安全整改上传标题
     */
    public static final String[] SAFETY_INFO_UPLOAD_TITLE = {"安全整改编号","工程名称*","整改情况*","检查类型*","安全类型*",
            "被检查单位*","检查时间","参加检测人员*","要求时间*","检测(隐患)记录*","完成时间","整改责任人","整改详情","是否符合要求",
            "施工单位安全员意见","施工单位项目经理意见","监理单位总监意见","建设单位项目主管意见","备注","创建时间"};

    /**
     * 阈值预案上传标题
     */
    public static final String[] THRESHOLD_UPLOAD_TITLE = {"预案类型*","预案级别*","告警阈值*","KPI*","告警处理人*",
            "预案标题*","预案描述*","预案负责人*","状态","备注","创建时间"};

    /**
     * 安全生产天数开始时间键
     */
    public static final String BI_START_TIME_SAFE_DAYS_KEY = "bi_start_time_safe_days";

    /**
     * 系统文件上传url键
     */
    public static final String SYS_FILE_UPLOAD_URL_KEY = "sys_file_upload_url";

    /**
     * eFace接口域名端口键
     */
    public static final String EFACE_URL_KEY = "eFace_url";

    /**
     * eFace登录url键
     */
    public static final String EFACE_LOGIN_URL = "/cloudIntercom/login";

    /**
     * eFace获取部门信息url
     */
    public static final String EFACE_DEPARTMENT_URL = "/cloudIntercom/selectDepartmentTree";

    /**
     * eFace getcardid url
     */
    public static final String EFACE_GET_CARDID_URL = "/cloudIntercom/selectNotOccupyCard";

    /**
     * eFace用户添加url
     */
    public static final String EFACE_USER_ADD_URL = "/cloudIntercom/insertPerson";

    /**
     * eFace用户查询url
     */
    public static final String EFACE_USER_QUERY_URL = "/cloudIntercom/selectPersonByQueryVo";

    /**
     * eFace人脸添加url
     */
    public static final String EFACE_FACE_ADD_URL = "/cloudIntercom/insertFaceFile";

    /**
     * eFace设备信息的url
     */
    public static final String EFACE_EQUIP_INFO_URL = "/cloudIntercom/selectGateEquipByQueryVo";

    /**
     * eFace远程开门url
     */
    public static final String EFACE_REMOTE_OPEN_URL = "/cloudIntercom/remoteGateEquipmentRelease";

    /**
     * eFace昨日考勤url
     */
    public static final String EFACE_YESTERDAY_ATTENDANCE_URL = "/cloudIntercom/getAttendRecStatistics";

    /**
     * eFace主页统计url
     */
    public static final String EFACE_HOME_STATISTICS_URL = "/cloudIntercom/getHomePageStatistics";

    /**
     * eFace最新通行人url
     */
    public static final String EFACE_NEWEST_ACCESS_URL = "/cloudIntercom/selectNewTenAccessRec";

    /**
     * eFace数据源名称
     */
    public static final String EFACE_DATASOURCE_NAME = "db_eFace";

    /**
     * 安全帽接口域名端口键
     */
    public static final String HELMET_URL_KEY = "helmet_url";

    /**
     * 安全帽登录url
     */
    public static final String HELMET_LOGIN_URL = "/userServer/user/login";

    /**
     * 安全帽获取告警信息url
     */
    public static final String HELMET_GET_EMERGENCY_URL = "/planServer/emergency/getUsersCurrentEmergencyByTime";

    /**
     * 出入人员流程类型
     */
    public static final String INANDOUT_PERSONNEL_PROCESS_TYPE = "inandout_personnel";

    /**
     * 项目计划流程类型
     */
    public static final String PROJECT_PLAN_PROCESS_TYPE = "project_plan";

    /**
     * 项目进度流程类型
     */
    public static final String PROJECT_PROGRESS_PROCESS_TYPE = "project_progress";

    /**
     * 告警流程类型
     */
    public static final String ALERT_INFO_PROCESS_TYPE = "alert_info";

}

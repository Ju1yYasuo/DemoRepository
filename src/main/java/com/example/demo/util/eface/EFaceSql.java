package com.example.demo.util.eface;

/**
 * eFace sql
 *
 * @author luox
 * @date 2021/8/27
 */
public class EFaceSql {

    /**
     * 得到最新hrinfo人
     */
    public static final String GET_HRINFO_NEWESTPERSONID = "SELECT * FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car ORDER BY car.record_time DESC  LIMIT 1";

    /**
     * 获取hrinfo人员信息
     */
    public static final String GET_HRINFO_PERSON = "SELECT cp.*, cd.dept_name FROM ci_person cp, ci_department cd WHERE cp.dept_id = cd.id  AND cp.id = ?  AND cp.`status` = 1";

    /**
     * 获取hrinfo人员信息 访客
     */
    public static final String GET_HRINFO_PERSON_VISITOR = "SELECT cv.* FROM ci_visitor cv WHERE cv.id = ?";

    /**
     * hrinfo进入记录
     */
    public static final String GET_HRINFO_ENTRYTIME = "SELECT * FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car WHERE car.valid_code = ? AND car.direction = 1 ORDER BY car.record_time  LIMIT 1";

    /**
     * 得到hrinfo离开记录
     */
    public static final String GET_HRINFO_LEFTTIME = "SELECT * FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car WHERE car.valid_code = ? AND car.direction = 2 ORDER BY car.record_time desc LIMIT 1";

    /**
     * 得到hrinfo出场记录数量
     */
    public static final String GET_HRINFO_LEFTNUMBER = "SELECT count( 0 ) total FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car WHERE to_days( car.record_time ) = TO_DAYS( now( ) )  AND car.pass_type != 9 AND car.direction = 2";

    /**
     * 得到hrinfo入场记录数量
     */
    public static final String GET_HRINFO_ENTRYNUMBER = "SELECT count( 0 ) total FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car WHERE to_days( car.record_time ) = TO_DAYS( now( ) )  AND car.pass_type != 9 AND car.direction = 1";

    /**
     * 获取人资信息工种信息
     */
    public static final String GET_HRINFO_JOBINFO = "SELECT p.job,COUNT( 0 ) amount FROM ci_person p WHERE p.`status` = 1 AND p.is_dimiss = 1 AND p.job IS NOT NULL GROUP BY  p.job ORDER BY amount DESC";

    /**
     * 获取人资信息所有人员信息
     */
    public static final String GET_HRINFO_PERSONINFO = "SELECT cp.id, cp.NAME, cp.sex, cp.job, cp.phone, cp.dept_id, cd.dept_name FROM ci_person cp, ci_department cd WHERE cp.dept_id = cd.id AND cp.`status` = 1";

    /**
     * 得到hrinfo游客
     */
    public static final String GET_HRINFO_VISITOR = "SELECT DISTINCT car.valid_code FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car WHERE to_days( car.record_time ) = TO_DAYS( now( ) ) AND car.direction = 1  AND car.pass_type = 10";

    /**
     * 得到hrinfo陌生数量
     */
    public static final String GET_HRINFO_STRANGERNUMBER = "SELECT car.* FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car WHERE to_days( car.record_time ) = TO_DAYS( now( ) ) AND car.direction = 1  AND car.pass_type = 9";

    /**
     * 得到hrinfo发热人
     */
    public static final String GET_HRINFO_FEVER = "SELECT car.* FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car WHERE to_days( car.record_time ) = TO_DAYS( now( ) )  AND car.direction = 1   AND car.pass_type != 9  AND car.is_fever = 1";

    /**
     * 得到hrinfo有效的总人数
     */
    public static final String GET_HRINFO_EFFECTIVETOTALNUMBER = "SELECT count( 0 ) total FROM ci_person WHERE `status` = 1";

    /**
     * 得到hrinfo刷脸总次数
     */
    public static final String GET_HRINFO_FACETOTALNUMBER = "SELECT count(0) total FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car WHERE to_days( car.record_time ) = TO_DAYS( now( ) )  AND car.pass_type != 9  AND car.pass_type = 2";

    /**
     * 得到hrinfo打卡总次数
     */
    public static final String GET_HRINFO_TOTALNUMBER = "SELECT count(0) total FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car WHERE to_days( car.record_time ) = TO_DAYS( now( ) )  AND car.pass_type != 9";

    /**
     * 得到hrinfo加班人
     */
    public static final String GET_HRINFO_OVERTIME = "SELECT car.*, timestampdiff( MINUTE, ( STR_TO_DATE( CONCAT( DATE_FORMAT( now( ), '%Y-%m-%d' ), ' ', ftr.close_time ), '%Y-%m-%d %H:%i:%s' ) + INTERVAL fa.after_overtime MINUTE  ), car.leftTime  ) overTime FROM (SELECT car66.*, car55.leftTime FROM (SELECT max( STR_TO_DATE( car5.record_time, '%Y-%m-%d %H:%i:%s' ) ) leftTime FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car5 WHERE to_days( car5.record_time ) = TO_DAYS( now( ) )  AND car5.direction = 2 GROUP BY car5.valid_code  ) car55 INNER JOIN (SELECT * FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car6  ) car66 ON car55.leftTime = car66.record_time  ) car, fr_time_frame ftr, fr_attendance fa WHERE car.gate_equip_id = ftr.attend_id  AND ftr.attend_id = fa.id  AND car.leftTime >= ( STR_TO_DATE( CONCAT( DATE_FORMAT( now( ), '%Y-%m-%d' ), ' ', ftr.close_time ), '%Y-%m-%d %H:%i:%s' ) + INTERVAL fa.after_overtime MINUTE  )";

    /**
     * 子大屏 人员实名制 获取出勤趋势
     */
    public static final String GET_REALNAME_ATTENDANCETREND = "SELECT c.month_time, count( 0 ) amount FROM (SELECT DISTINCT car.valid_code, DATE_FORMAT( car.record_time, '%Y-%m' ) month_time FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car WHERE car.pass_type != 9  AND to_days( car.record_time ) >= to_days( DATE_FORMAT( now( ) - INTERVAL 5 MONTH, '%Y-%m-01' ) )  ) c GROUP BY c.month_time";

    /**
     *子大屏 人员实名制 得到人员实名制最新10人的打卡记录
     */
    public static final String GET_REALNAME_NEWESTPERSONID = "SELECT car.gate_equip_id, car.record_time, car.pass_type, car.phone, car.photo_url, car.valid_code, car.temperature, car.is_fever, cge.sn, cge.equip_name FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car, ci_gate_equipment cge WHERE car.gate_equip_id = cge.id ORDER BY car.record_time DESC  LIMIT 10";

    /**
     * n天未刷脸告警 员工最近刷脸打卡时间
     */
    public static final String GET_RECENTTIME_FACE = "SELECT max(car5.ar_id) recentArId, max( car5.record_time ) recentTime, cp.*, cd.dept_name FROM (SELECT car0.* FROM ci_access_record_0 car0 UNION ALL SELECT car1.* FROM ci_access_record_1 car1 UNION ALL SELECT car2.* FROM ci_access_record_2 car2 UNION ALL SELECT car3.* FROM ci_access_record_3 car3 UNION ALL SELECT car4.* FROM ci_access_record_4 car4  ) car5, ci_person cp, ci_department cd WHERE car5.valid_code = cp.id  AND cp.dept_id = cd.id  AND car5.pass_type = 2  AND cp.`status` = 1 GROUP BY car5.valid_code";
}

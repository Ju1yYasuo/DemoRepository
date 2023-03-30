package com.example.demo.util.common;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 告警信息工具类
 *
 * @author luox
 * @date 2021/9/10
 */
public class AlertInfoUtil {

    //告警标题
    public static final String TITLE_STRANGER = "陌生人告警";
    public static final String TITLE_FEVER = "发热告警";
    public static final String TITLE_OVERTIME = "加班超时告警";
    public static final String TITLE_HELMET = "安全帽告警";
    public static final String TITLE_VIDEO = "视频告警";
    public static final String TITLE_DAYS_FACE = "天未刷脸告警";
    //告警类型
    public static final int TYPE_STRANGER = 0;
    public static final int TYPE_FEVER = 1;
    public static final int TYPE_OVERTIME = 2;
    public static final int TYPE_HELMET = 3;
    public static final int TYPE_VIDEO = 4;
    public static final int TYPE_DAYS_FACE = 5;
    //告警安全类型字典
    public static final String TYPE_ALERT_TYPE_SAFE = "alert_type_safe";
    public static final String TYPE_ALERT_TYPE_NOSAFE = "alert_type_noSafe";
    //告警等级字典
    public static final String LEVEL_ALERT_LEVEL_ONE = "alert_level_one";
    public static final String LEVEL_ALERT_LEVEL_TWO = "alert_level_two";
    public static final String LEVEL_ALERT_LEVEL_THREE = "alert_level_three";
    //告警来源字典
    public static final String SOURCE_PERSONNEL_SYSTEM = "personnel_system";
    public static final String SOURCE_HELMET_SYSTEM = "helmet_system";
    public static final String SOURCE_VIDEO_SYSTEM = "video_system";

    //private static final AlertService alertService = BeanUtil.getBean(AlertServiceImpl.class);
    //private static final AlertHistoryService alertHistoryService = BeanUtil.getBean(AlertHistoryServiceImpl.class);

    /**
     * 添加告警信息
     *
     * @param list 列表
     * @author luox
     * @date 2021/09/14
     */
    //public static void saveAlert(List<AlertSaveVo> list){
    //    //今天的告警
    //    String currentDate = DateUtil.today();
    //    saveAlert(list,currentDate);
    //}

    /**
     * 添加告警信息
     *
     * @param list 列表
     * @author luox
     * @date 2021/09/14
     */
    //public static void saveAlert(List<AlertSaveVo> list,String alertDate){
    //    if(CollUtil.isEmpty(list)){
    //        return ;
    //    }
    //    //查询告警历史记录
    //    LambdaQueryWrapper<AlertHistory> queryWrapper = new LambdaQueryWrapper<>();
    //    List<Integer> typeList = list.stream().map(AlertSaveVo::getType).collect(Collectors.toList());
    //    queryWrapper.eq(AlertHistory::getAlertDate,alertDate);
    //    queryWrapper.in(AlertHistory::getType,typeList);
    //    List<AlertHistory> alertHistoryList = alertHistoryService.list(queryWrapper);
    //    List<String> markList = alertHistoryList.stream().map(AlertHistory::getMark).collect(Collectors.toList());
    //    for(AlertSaveVo vo : list){
    //        if(markList.contains(vo.getMark())){
    //            continue;
    //        }
    //        //保存告警历史记录
    //        AlertHistory alertHistory = AlertHistory.builder().mark(vo.getMark()).alertDate(alertDate).type(vo.getType()).build();
    //        alertHistoryService.save(alertHistory);
    //        //保存告警信息
    //        Alert alert = Alert.builder().title(vo.getTitle()).type(vo.getAlertType()).level(vo.getAlertLevel())
    //                .source(vo.getAlertSource()).status(-2).description(vo.getDescription()).build();
    //        alertService.save(alert);
    //        //发送消息
    //        MessageInfoUtil.sendMessage(vo.getTitle() + "," + vo.getDescription(),vo.getUserIdList(),alert.getId());
    //    }
    //}

}

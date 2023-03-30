package com.example.demo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Task;
import net.sf.mpxj.mpp.MPPReader;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * project文档测试
 *
 * @author luox
 * @date 2022/4/14
 */
public class ProjectTest {

    public static void main(String[] args) {

        try {
            //test1();
            test2();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void test2() throws IOException {



        URI uri =  new ClassPathResource("documentTemplates/月计划任务导入模板V1.0.mpp").getURI();
        print(uri.getPath());
        File file = new File(uri);
        print(file.exists());

        print(new ClassPathResource("documentTemplates/月计划任务导入模板V1.0.mpp").getFile().exists());
    }


    public static void test1() throws IOException, MPXJException {
        //这个是读取文件的组件
        MPPReader mppRead = new MPPReader();
        ProjectFile pf = mppRead.read(new ClassPathResource("test7.mpp").getInputStream());
        //从文件中获取的任务对象
        // 0为root
        List<Task> tasks = pf.getChildTasks().get(0).getChildTasks();

        //List<MonthTask> monthTaskList = new ArrayList<>();
        ////避免深度递归栈溢出
        //int index = 0;
        //while(index < tasks.size()){
        //    Task task = tasks.get(index);
        //
        //    MonthTask monthTask = MonthTask.builder().taskName(task.getName()).build();
        //    monthTask.setDuration((int) task.getDuration().getDuration());
        //    monthTask.setActualStartTime(DateUtil.formatDate(task.getStart()));
        //    monthTask.setActualEndTime(DateUtil.formatDate(task.getFinish()));
        //    monthTask.setPlanStartTime(DateUtil.formatDate(task.getStart(1)));
        //    monthTask.setPlanEndTime(DateUtil.formatDate(task.getFinish(1)));
        //    monthTask.setPredictStartTime(DateUtil.formatDate(task.getStart(2)));
        //    monthTask.setPredictEndTime(DateUtil.formatDate(task.getFinish(2)));
        //    //int deviation = task.getNumber(1).intValue();
        //    //if(deviation == 0){
        //    //    deviation =  (monthTask.getDuration() -
        //    //            (int)(DateUtil.betweenDay(monthTask.getPredictStartTime(),monthTask.getPredictEndTime(),true)));
        //    //}
        //    //monthTask.setDeviation(deviation);
        //    monthTask.setDeviation(task.getNumber(1).intValue());
        //    //monthTask.setDeviation(Integer.valueOf(task.getFieldByAlias("偏差天").toString()));
        //    monthTaskList.add(monthTask);
        //    //print("index=" + index + "getFieldByAlias(偏差天)=" + task.getFieldByAlias("偏差天"));getNumber(1).intValue()
        //    List<Task> children = task.getChildTasks();
        //    if(CollUtil.isNotEmpty(children)){
        //        tasks.addAll(children);
        //    }
        //    index++;
        //}

        //print(monthTaskList);


    }

    private static void print(Object obj){
        System.out.println(obj);
    }
}

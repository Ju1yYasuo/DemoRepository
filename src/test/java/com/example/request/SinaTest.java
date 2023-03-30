package com.example.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author luox
 * @date 2022/5/6
 */
public class SinaTest {

    //应用ID
    private static final String appId = "zhqyfz";
    //应用秘钥
    private static final String appSecret = "059c333800e04250a3daceb2384ae048";
    //认证码
    private static String authorizeCode;
    //授权校验参数
    private static String accessToken;

    private static final String baseUrl = "https://api-open.51wyq.cn/dataapp/api";
    private static final String authorizeCode_url = "/oauth2/authorize";
    private static final String accessToken_url = "/oauth2/token";
    private static final String two_level_url = "/data/basic/list/level/two";

    //检索条件控制参数
    private static String paramsJson;
    //方案标识
    private static final String keywordTicket = "3af87620f20048a1a1f85bcc666cdaf0";

    @Test
    public void test1(){
        ParamsJson params = new ParamsJson();
        //方案标识
        params.setKeywordTicket(keywordTicket);
        //页数设置
        params.setPage(1);
        //每页大小设置
        params.setPageSize(1);
        //显示图片
        params.setShowImg(1);
        paramsJson = JSONObject.toJSON(params).toString();
        print(paramsJson);
    }

    public static void main(String[] args) {
        //1、获取accessToken(2小时允许获取5次，1个accessToken有效期24小时)
        getAccessToken();
        //2、设置检索条件
        ParamsJson params = new ParamsJson();
        //方案标识
        params.setKeywordTicket(keywordTicket);
        //页数设置
        params.setPage(1);
        //每页大小设置
        params.setPageSize(1);
        //显示图片
        params.setShowImg(1);
        paramsJson = JSONObject.toJSON(params).toString();
        print(paramsJson);
        //String json = "[{\"name\":\"雅诗兰黛\",\"keyword\":\"雅诗兰黛+小棕瓶\",\"filterKeyword\":\"\"},{\"name\":\"兰蔻\",\"keyword\":\"兰蔻+小黑瓶\",\"filterKeyword\":\"\"}]";
        ////3、请求Api接口
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("accessToken", accessToken);
        paramsMap.put("paramsJson", paramsJson);
        //paramsMap.put("ticket", "1f6323a7e91e41538727c6dca5043c37");
        //paramsMap.put("keyword", "D.MARTINA QUEEN");
        //
        //
        //paramsMap.put("startTime", "2020-08-01 00:00:00");
        //paramsMap.put("endTime", "2020-08-05 12:00:00");
        //paramsMap.put("json", json);
        //paramsMap.put("createType", "1");
        //String resultUrl = baseUrl + one_level_url;
        String resultUrl = baseUrl + two_level_url;
        System.out.println("resultUrl:" + resultUrl);


        HttpResponse response = HttpRequest.post(resultUrl).form(paramsMap).timeout(1000 * 5).execute();
        //post方式请求并输出接口响应结果

        System.out.println(response.body());
        //get方式请求并输出接口响应结果
//        System.out.println(sendGet(baseUrl+api_url,paramsMap));

    }

    /**
     * 获取授权码
     */
    public static void getAccessToken() {
        //1、申请认证码
        String authorizeCodeRequestUrl = baseUrl+authorizeCode_url;
        //String authorizeCodeRequestUrl = "https://api-open.51wyq.cn/dataapp/api/oauth2/authorize";
        Map<String, Object> authorizeCodeParams = new HashMap<>();
        authorizeCodeParams.put("appId", appId);
        authorizeCodeParams.put("responseType", "code");
        authorizeCodeParams.put("state", "state");
        //post方式

        HttpResponse response = HttpRequest.post(authorizeCodeRequestUrl).form(authorizeCodeParams).timeout(1000 * 5).execute();
        JSONObject result = JSON.parseObject(response.body());
        authorizeCode = result.getJSONObject("authorizeCode").getString("authorizeCode");

        System.out.println("authorizeCode:" + authorizeCode);

//        String authorizeCodeJson =  sendGet(authorizeCodeRequestUrl,authorizeCodeParams);
//        System.out.println(authorizeCodeJson);
//        authorizeCode = JSON.parseObject(authorizeCodeJson).getJSONObject("authorizeCode").getString("authorizeCode");

        //2、获取授权码
        authorizeCodeParams = new HashMap<>();
        authorizeCodeParams.put("appId", appId);
        authorizeCodeParams.put("appSecret", appSecret);
        authorizeCodeParams.put("grantType", "authorization_code");
        authorizeCodeParams.put("authorizeCode", authorizeCode);
        String accessTokenRequestUrl = baseUrl + accessToken_url;
//        String accessTokenJson = sendGet(accessTokenRequestUrl, authorizeCodeParams);
        //post方式

        HttpResponse response1 = HttpRequest.post(accessTokenRequestUrl).form(authorizeCodeParams).timeout(1000 * 5).execute();
        //String accessTokenJson =  sendPost(accessTokenRequestUrl,authorizeCodeParams);
        accessToken = JSON.parseObject(response1.body()).getJSONObject("accessToken").getString("accessToken");
        System.out.println("accessToken:" + accessToken);
    }

    private static void print(Object obj){
        System.out.println(obj);
    }

}

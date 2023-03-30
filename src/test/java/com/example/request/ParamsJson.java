package com.example.request;

import lombok.Data;

/**
 * @author luox
 * @date 2022/5/6
 */
@Data
public class ParamsJson {

    private String keywordTicket; // 方案ticket
    private String keywordId; // 方案Id
    private String publishedStartTime; // 发布开始时间(格式：yyyy-MM-dd HH:mm:ss)
    private String publishedEndTime; // 发布结束时间(格式：yyyy-MM-dd HH:mm:ss)
    private Integer order; // 排序字段(1:相似度 2:发布时间 3:相似文章 4:采集时间)
    private Integer sort; // 排序类型(1:升序 2:降序)
    private Integer page; // 页数(default:1)
    private Integer pageSize; // 每页条数(最大:200 default:10)
    private Integer similarMerge; // 相似文章(1:合并 2:不合并 default:2)
    private Integer sensitivityType; // 敏感属性(1：敏感 2：非敏感)
    private Integer matchType; // 匹配方式(1:按全文 2:按标题 3:按正文 default:1)
    private String originType; // 来源类型((1:全部,2互动论坛,3微博,4微信,5客户端,6视频,7数字报,8网站, default:1，多个以英文,分隔))
    private String similarityTag; // 相似性标签
    private Integer searchType; // 搜索模式(1:精确搜索 2:模糊搜索 default:1)
    private String secondKeyword; // 二次匹配关键词
    private Integer secondKeywordMatchType; // 二次匹配关键词匹配模式(1:按全文 2:按标题 3:按正文 default:1)
    private Integer weiboRange; // 微博范围(1:全量 2:根微博 3:转发微博 default:1)
    private Integer enableHighlightedContent; // 内容是否高亮(0:否 1:是 default:1)
    private Integer enableHighlightedTitle; // 标题是否高亮(0:否 1:是 default:1)
    private String extraKeyword; // 扩展方案
    private String directWebsites; // 定向网站名称列表(多个以英文,分隔)
    private String excludeWebsites; // 定向排除网站名称列表(多个以英文,分隔)
    private String directProvinces; // 定向省份(多个以英文,分隔)
    private String excludeProvinces; // 定向排除省份(多个以英文,分隔)
    private String directSecondTrades; // 定向行业标签列表(多个以英文,分隔)
    private String excludeSecondTrades; // 定向排除行业标签列表(多个以英文,分隔)
    private Integer slop; // 词距(default:60)
    private String noise; // 噪音(1:全量 2:正常数据 3:噪音数据 default:1)
    private Integer resultShow; // 结果呈现(1:正常信息 2:精准信息 3:疑似垃圾 default:1)
    private Integer involvedType; // 涉及方式(0:内容涉及 1:定位涉及 2:全部信息 default:2)
    private Integer showImg; // 是否显示图片(0:否 1:是 default:0)
}

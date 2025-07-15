/*
 * Copyright Notice
 * © [2024 - 2025] Winfxk. All rights reserved.
 * The software, its source code, and all related documentation are the intellectual property of Winfxk. Any reproduction or distribution of this software or any part thereof must be clearly attributed to Winfxk and the original author. Unauthorized copying, reproduction, or distribution without proper attribution is strictly prohibited.
 * For inquiries, support, or to request permission for use, please contact us at:
 * Email: admin@winfxk.cn
 * QQ: 2508543202
 * Visit our homepage for more information: http://Winfxk.cn
 *
 * --------- Create message ---------
 * Created by IntelliJ ID
 * Author： Winfxk
 * Created PCUser: kc4064
 * Web: http://winfxk.com
 * Created Date: 2025/7/15  17:23 */
package cn.winfxk.lexy.wms.im.complete.row.storeissue;

import com.alibaba.fastjson.JSONObject;

public class Demo {
    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("tc_wsg01", "61500-250500608");//源工单号(最上层)
        json.put("tc_wsg02", "61500-250500655");//本工单号
        json.put("tc_wsg04", "kcl3360");//员工KC号
        json.put("tc_wsg05", "6000");//部门编码
        json.put("tc_wsg11", "SEA03801D13R");//料号
        json.put("tc_wsg12", "2034");//仓库代码
        json.put("tc_wsg13", "");//库位号
        json.put("tc_wsg14", "");//批次号
        json.put("tc_wsg15", "PCS");//单位
        json.put("tc_wsg16", "1");//数量
        String acc="KC04";//账套
        Result result = Rowstoreissue.sendMessage(acc, json);
        System.out.println(result.isSuccess());//true 为成功 false为失败
        System.out.println(result.getDescription());//返回结果，成功固定返回“成功”，失败返回失败原因
        System.out.println(result.getID());//请求ID
    }
}

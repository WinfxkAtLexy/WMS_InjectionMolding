/*
 * Copyright Notice
 * © [2024] Winfxk. All rights reserved.
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
 * Created Date: 2025/5/6  16:02 */
package cn.winfxk.lexy.wms.im;

import cn.winfxk.lexy.wms.im.other.input.Result;
import cn.winfxk.lexy.wms.im.other.input.SendOther;
import cn.winfxk.lexy.wms.im.other.tab.Transfer;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
    public static void send(String[] args) {
        JSONObject title = new JSONObject();
        title.put("acc", "KC04");//账套
        title.put("ina00", "1");//固定值
        title.put("ina02", "2023-05-06");//扣账日期 'yyyy-MM-dd'
        title.put("ina03", "2023-05-06");//生成日期 'yyyy-MM-dd'
        title.put("ina04", "1604");//部门编号
        title.put("ina07", "备注");
        title.put("ina11", "kc");//操作人
        JSONArray array = new JSONArray();

        JSONObject value = new JSONObject();
        value.put("inb03", "1");//项次
        value.put("inb04", "D9000084166R");//料号
        value.put("inb05", "1334");//仓库
        value.put("inb06", "M");//库位
        value.put("inb08", "PCS");//单位
        value.put("inb15", "BMLY");//理由码
        value.put("inb16", 200);//数量)
        value.put("inb17", " ");//备注
        array.add(value);
        Result result= SendOther.sendMessage(title, title, array);
    }
    public static void tab(){
        JSONObject title = new JSONObject();
        title.put("acc", "KC04");//账套
        title.put("imm02","2023-05-06");//操作时间 'yyyy-MM-dd'
        title.put("imm14","1604");//工厂代码
        title.put("imm16","kc");//用户
        JSONArray array = new JSONArray();
        JSONObject value = new JSONObject();
        value.put("imn02", 1);//项次
        value.put("imn03", "Y12V9700058");//料号
        value.put("imn04", 1334);//仓库代码
        value.put("imn041", "M");//库位
        value.put("imn09", "PCS");//单位
        value.put("imn10", 1);//数量
        value.put("imn15", 1013);//接收端仓库代码
        value.put("imn16", "M");//接收端库位码
        value.put("imn20", "PCS");//接收端单位
        value.put("imn22", 1);//接收端数量
        array.add(value);
        Result result= Transfer.sendMessage(title, title, array);
    }
}

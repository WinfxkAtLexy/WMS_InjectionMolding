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
* Created Date: 2025/4/15  08:53 */
package cn.winfxk.lexy.wms.im.other.input

import cn.winfxk.libk.tool.Tool
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject

class Test {
}

val list = hashMapOf(
    "Y12V9700058" to 192,
    "Y1D19700005" to 2283,
    "D9000084166R" to 214,
    "Y0000101632" to 7,
    "J001P000027R" to 100,
    "Y0000102089" to 200,
    "Y0H10100006" to 1000,
    "Y1C59700006" to 2175,
    "Y0000101082" to 135,
    "J0010001647R" to 600,
    "Y0000102091" to 200,
);

fun main() {
    val result = Storeinput.sendMessage(
        Json("kc04" to "KC04"),
        Json(
            "ina00" to "3",
            "ina02" to Tool.getDate(),//扣账日期 'yyyy-MM-dd'
            "ina03" to Tool.getDate(),//生成日期 'yyyy-MM-dd'
            "ina04" to "1604",//部门编号
            "ina07" to "备注",
            "ina11" to "kc"//操作人
        ),
        JSONArray().also {
            for ((index, pair) in list.entries.withIndex())
                it.add(Json(
                    "inb03" to index + 1,//想吃
                    "inb04" to pair.key,//料号
                    "inb05" to 1334,//仓库
               /*     "inb06" to "M",//库位*/
                    "inb08" to "PCS",//单位
                    "inb15" to "BMLY",//理由码
                    "inb16" to pair.value,//数量
                ))
        }
    );
    println(result.post)
    println(result.xml)
    println(result)
}

fun Json(vararg entity: Pair<String, Any?>): JSONObject {
    val json = JSONObject()
    entity.forEach {
        json[it.first] = it.second
    }
    return json
}
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

fun main() {
   val result=Storeinput.sendMessage(
        Json("kc04" to "KC04"),
        Json(
            "ina00" to "3",
            "ina02" to Tool.getDate(),
            "ina03" to Tool.getDate(),
            "ina04" to "1604",
            "ina07" to "备注",
            "ina11" to "kc"
        ),
        JSONArray(Json(
            "inb03" to 1,
            "inb04" to "Q10X0070860",
            "inb05" to 1334,
            "inb08" to "PCS",
            "inb15" to "BMLY",
            "inb16" to 1,
        ))
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
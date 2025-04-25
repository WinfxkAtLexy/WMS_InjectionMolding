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
* Created Date: 2025/4/21  08:08 */
package cn.winfxk.lexy.wms.im.other.tab

import cn.winfxk.libk.tool.Tool
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject

fun main() {
  val a=  Transfer.sendMessage(JSONObject(mapOf(
        "kc04" to "KC04"
    )), JSONObject(mapOf(
        "imm02" to Tool.getDate(),//操作时间
        "imm14" to "1604",//工厂代码
        "imm16" to "kc",//用户
    )), JSONArray(arrayListOf(mapOf(
        "imn02" to 1,//项次
        "imn03" to "Y12V9700058",//料号
        "imn04" to 1334,//长裤代码
        //"imn041" to "M"`//库位,
        "imn09" to "PCS",//单位
        "imn10" to 1,//数量
        "imn15" to 1013,//接收端仓库代码
        "imn16" to "M",//接收端库位码
        "imn20" to "PCS",//接收端单位
        "imn22" to 1,//接收端数量
    ))))
    println(a.post)
    println(a.xml)
    println(a)
}
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
* Created Date: 2025/4/15  13:39 */
package cn.winfxk.lexy.wms.im.other.input

import cn.winfxk.libk.tool.utils.toArray
import cn.winfxk.libk.tool.utils.toJson
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject

class InputOther {
    companion object {
        /**
         * 处理杂收请求
         * @param title 请求头
         * @param message 请求报文(JsonString)
         */
        @JvmStatic
        fun sendMessage(title: String, message: String, list: String): Result {
            return sendMessage(title.toJson(), message.toJson(), list.toArray());
        }
        /**
         * 处理杂收请求
         * @param title 请求头
         * @param json 请求报文(兼容Fastjson1)
         */
        @JvmStatic
        fun sendMessage(title: com.alibaba.fastjson.JSONObject, json: com.alibaba.fastjson.JSONObject, array: com.alibaba.fastjson.JSONArray): Result {
            return sendMessage(JSONObject(title), JSONObject(json), JSONArray(array));
        }
        /**
         * 处理杂收请求
         * @param title 请求头
         * @param json 请求报文
         */
        @JvmStatic
        fun sendMessage(title: JSONObject, json: JSONObject, array: JSONArray) = Storeinput.sendMessage(title, json.apply { this["ina00"] = 3 }, array)
        @JvmStatic
        fun sendMessage(sb: com.alibaba.fastjson.JSONObject): Result {
            val acc = sb["acc"];
            val title = JSONObject();
            val array = JSONObject()
            sb.forEach { (k, v) ->
                if (k.startsWith("ina", ignoreCase = true) || k.startsWith("ta_ina", ignoreCase = true)) title[k] = v;
                if (k.startsWith("inb", ignoreCase = true) || k.startsWith("ta_inb", ignoreCase = true)) array[k] = v;
            }
            return sendMessage(Json("kc04" to acc), title, JSONArray().also { it.add(array) })
        }
    }
}

fun main() {
    val t = "{\"acc\":\"KC04\",\"ina03\":\"2025-05-10\",\"ina02\":\"2025-05-10\",\"ina00\":\"3\",\"ina11\":\"kc08237\",\"ina07\":\" \",\"ina04\":\"1604\"}".toJson();
    val rs = InputOther.sendMessage(t, t, "[{\"inb06\":\" \",\"inb05\":\"1334\",\"inb16\":\"100\",\"inb04\":\"S0MFF850B11\",\"inb15\":\"CLTZ\",\"inb03\":1,\"ipc\":\"1604_13343_20250510135308\",\"inb08\":\"PCS\"}]".toArray())
    println(rs)
}
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
* Created Date: 2025/4/15  08:00 */
package cn.winfxk.lexy.wms.im.other.input

import cn.winfxk.lexy.wms.im.client.test.CwsAimt370CreateRequestCwsAimt370CreateRequest
import cn.winfxk.lexy.wms.im.client.test.TIPTOPServiceGateWay
import cn.winfxk.libk.log.Log
import cn.winfxk.libk.tool.utils.toArray
import cn.winfxk.libk.tool.utils.toJson
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject

/**
 * 杂收请求
 */
class Storeinput private constructor(private val title: JSONObject, private val json: JSONObject, private val array: JSONArray) {
    init {
        log.i(  "正在初始化数据...")
    }
    /**
     *开始处理请求
     */
    private fun start(): Result {
        val xml = XmlString(title, json, array).getXml();
        try {
            log.i("创建请求端口..")
            val post = port.cwsAimt370Create(CwsAimt370CreateRequestCwsAimt370CreateRequest().apply {
                log.i("设置请求")
                request = xml
            })
            if (post == null) log.e("请求失败！")
            val response = post.response;
            if (response.isNullOrBlank()) log.e("返回的数据为空！")
            return response.let {
                log.i("正在处理结果..")
                val result = Result(it, xml, null, json)
                log.i("请求完毕!");
                result;
            }
        } catch (e: Exception) {
            return error(e.message ?: "未知错误", xml, e)
        }
    }

    private fun error(msg: String, xml: String?, e: Throwable) = Result("""
        <Response>
                <Execution>
                    <Status code="-1" sqlcode="" description="$msg"/>
                </Execution>
            </Response>
    """.trimIndent(), xml, e, json)

    companion object {
        private val log = Log(Storeinput::class.java.simpleName)
        private val service = TIPTOPServiceGateWay();
        private val port = service.tiptopServiceGateWayPortType;
        /**
         * 处理杂收请求
         * @param title 请求头
         * @param message 请求报文(JsonString)
         */
        @JvmStatic
        fun sendMessage(title: String, message: String, list: String): Result {
            log.i("接收到字符型数据，转换为Fastjson2进行处理")
            return sendMessage(title.toJson(), message.toJson(), list.toArray());
        }
        /**
         * 处理杂收请求
         * @param title 请求头
         * @param json 请求报文(兼容Fastjson1)
         */
        @JvmStatic
        fun sendMessage(title: com.alibaba.fastjson.JSONObject, json: com.alibaba.fastjson.JSONObject, array: com.alibaba.fastjson.JSONArray): Result {
            log.i("接收到Fastjson1数据，转换为Fastjson2进行处理")
            return sendMessage(JSONObject(title), JSONObject(json), JSONArray(array));
        }
        /**
         * 处理杂收请求
         * @param title 请求头
         * @param json 请求报文
         */
        @JvmStatic
        fun sendMessage(title: JSONObject, json: JSONObject, array: JSONArray) = Storeinput(title, json, array).start();
    }
}
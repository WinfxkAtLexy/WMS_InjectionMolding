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
* Created Date: 2025/4/15  08:13 */
package cn.winfxk.lexy.wms.im.other.tab

import cn.winfxk.libk.tool.Tool
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class XmlString(private val title: JSONObject, private val json: JSONObject, private val array: JSONArray) {
    private val postDate = System.currentTimeMillis();
    private val postID = Tool.CompressNumber(postDate) + "-" + Tool.CompressNumber(random.nextInt(9999999).toLong())
    private fun getAccess(): String = """
                                <Authentication user="" password=""/>
                                <Connection application="" source=""/>          
                                <Organization name="${title["kc04"]}"/>                     --填ERP的法人编号 例如 KC04
                                <Locale language="zh_cn"/>                      --language="zh_cn"
    """.trimIndent()
    /**
     * 请求的单头
     */
    private fun getIna(): String {
        var msg = StringBuilder();
        for ((key, value) in json) {
            if (! key.startsWith("imm", ignoreCase = true)
                && ! key.startsWith("ta_imm", ignoreCase = true)) continue;
            if (msg.isNotBlank()) msg = msg.append("\n")
            msg = msg.append("""
                <Field name ="$key" value="$value" />
            """.trimIndent())
        }
        return msg.toString();
    }
    /**
     * 请求体的数组
     */
    private fun getInb(): String {
        var msg = StringBuilder();
        for (item in array) {
            if (item !is Map<*, *>) continue
            if (msg.isNotBlank()) msg = msg.append("\n")
            msg = msg.append(getInbItem(item))
        }
        return msg.toString();
    }
    /**
     * 请求体的某项
     */
    private fun getInbItem(map: Map<*, *>): String {
        var msg = StringBuilder();
        for ((key, value) in map) {
            if (! key.toString().startsWith("imn", ignoreCase = true)
                && ! key.toString().startsWith("ta_imn", ignoreCase = true)) continue;
            if (msg.isNotBlank()) msg = msg.append("\n")
            msg = msg.append("""
                <Field name ="$key" value="$value" />
            """.trimIndent())
        }
        return "\n<Record>\n$msg\n</Record>\n"
    }
    /**
     * 返回序列化的Xml文本
     */
    fun getXml(): String {
        return """<Request>
                            <Access>
                                ${getAccess()}
                            </Access>
                            <RequestContent>
                                <Parameter>
                                    <Record>
                                        <Field name ="reqno" value="$postID" />                --请求编号
                                        <Field name ="reqtime" value="${format.format(postDate)}" />    --请求时间   yyyy-MM-dd HH:mm:ss
                                        <Field name ="reqclient" value="zzgxb" />               --请求来源   固定值 "zzgxb"
                                        <Field name ="reqtype" value="0" />                     --类型       固定值 '0'
                                    </Record>
                                </Parameter>
                                <Document>
                                    <RecordSet id="1" >
                                        <Master name="ina_file">
                                            <Record>
                                                ${getIna()}
                                            </Record>
                                        </Master>
                                        <Detail name="inb_file">
                                            ${getInb()}
                                        </Detail>
                                    </RecordSet>
                                </Document>
                            </RequestContent>
                          </Request>
        """.trimIndent()
    }

    companion object {
        private val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private val random = Random();
    }
}
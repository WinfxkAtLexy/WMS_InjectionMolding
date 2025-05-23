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
* Created Date: 2024/11/19  15:00 */
package cn.winfxk.lexy.wms.im.item

import cn.winfxk.lexy.wms.im.client.release.TIPTOPServiceGateWay
import cn.winfxk.lexy.wms.im.client.release.ZzgxbAsft620CreateRequestZzgxbAsft620CreateRequest
import cn.winfxk.libk.log.Log
import cn.winfxk.libk.tool.Tool
import cn.winfxk.libk.tool.utils.write
import com.alibaba.fastjson.JSONObject
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.*

class InputItem {
    companion object {
        private val log = Log(InputItem::class.java.simpleName)
        private const val tab = "接口："
        private val service = TIPTOPServiceGateWay();
        private val port = service.tiptopServiceGateWayPortType;
        @JvmStatic
        fun getTab() = tab;
        @JvmStatic
        fun sendMessage(string: String): Response = sendMessage(string.toJson())
        @JvmStatic
        fun sendMessage(json: JSONObject): Response {
            val im_po = json["im_po"];
            val sfu02 = json["sfu02"];
            if (sfu02.isNotExist()) return getErrorMessage("入库时间[sfu02]不能为空！")
            val sfu04 = json["sfu04"];
            if (sfu04.isNotExist()) return getErrorMessage("部门编码[sfu04]不能为空！")
            val sfu07 = json["sfu07"];
            if (sfu07.isNotExist()) return getErrorMessage("机台编号（备注）[sfu07]不能为空！")
            val sfu_file = """
            <Field name ="sfu02" value="$sfu02" />
            <Field name ="sfu04" value="$sfu04" />
            <Field name ="sfu07" value="$sfu07" />
            <Field name ="sfu16" value="${json["sfu16"]}" />
            <Field name ="sfu10" value="1" />
        """.trimIndent();
            val sfv04 = json["sfv04"];
            if (sfv04.isNotExist()) return getErrorMessage("字段[sfv04]不能为空！")
            val sfv05 = json["sfv05"];
            if (sfv05.isNotExist()) return getErrorMessage("字段[sfv05]不能为空！")
            val sfv06 = json["sfv06"];
            val sfv08 = json["sfv08"];
            if (sfv08.isNotExist()) return getErrorMessage("字段[sfv08]不能为空！")
            val sfv09 = json["sfv09"];
            if (sfv09.isNotExist()) return getErrorMessage("字段[sfv09]不能为空！")
            val sfv11 = json["sfv11"];
            if (sfv11.isNotExist()) return getErrorMessage("字段[sfv11]不能为空！")
            val sfv12 = json["sfv12"];
            if (sfv12.isNotExist()) return getErrorMessage("字段[sfv12]不能为空！")
            val kc = json["kc"];
            if (kc.isNotExist()) return getErrorMessage("账套不能为空！");
            val sfv_file = """
            <Field name ="sfv04" value="$sfv04" />
            <Field name ="sfv05" value="$sfv05" />
            <Field name ="sfv06" value="$sfv06" />
            <Field name ="sfv08" value="$sfv08" />
            <Field name ="sfv09" value="$sfv09" />
            <Field name ="sfv11" value="$sfv11" />
            <Field name ="sfv12" value="$sfv12" />
        """.trimIndent()
            val xml = Xmlstring.getXml("$kc", sfu_file, sfv_file);
            try {
                return port.zzgxbAsft620Create(ZzgxbAsft620CreateRequestZzgxbAsft620CreateRequest().also { it.request = xml })?.let {
                    it.response?.let { aa ->
                        val ret = Response(aa, xml);
                        ret.sfu16 = json["sfu16"]?.toString();
                        ret.fac = sfu04?.toString()
                        if (! ret.isSuccess()) {
                            log.i("提交到ERP失败！正在保存日志..")
                            PostLog(ret).post();
                        } else log.i("提交到ERP成功！")
                        ret;
                    }
                } ?: getErrorMessage("ERP返回了空数据！")
            } catch (e: Exception) {
                val log = PostLog();
                log.IPC = UUID.randomUUID().toString();
                log.SYS_C = "WMS";
                log.ITEM_C = "SIMFSH_Magt_entery_chk_fshentry_frm";
                log.ITEM_N = "注塑件收货";
                log.STATE = "NG";
                log.CONTEXT = xml;
                log.RESULT = StringWriter().also { e.printStackTrace(PrintWriter(it)) }.toString()
                log.MARK = e.message;
                log.CNAME = json["sfu16"]?.toString();
                log.FAC_C = json["sfu04"]?.toString();
                log.MCODE = "1001";
                log.post()
                return getErrorMessage("接口运行异常！");
            }
        }

        private fun Any?.isNotExist(): Boolean {
            if (this == null) return true;
            return this.toString().isBlank()
        }

        fun String?.toJson(): JSONObject {
            val json = JSONObject();
            if (this == null) return json;
            return JSONObject.parseObject(this);
        }

        private fun getErrorMessage(message: String): Response = Response("""
           <Response>
                <Execution>
                    <Status code="-1" sqlcode="-1" description="$tab $message"/>
                </Execution>
                <ResponseContent>
                    <Parameter/>
                    <Document/>
                </ResponseContent>
            </Response>
       """.trimIndent())
    }
}

fun main() {
    val date = Date();
    val file = File("正式区/${Tool.CompressNumber(date.time)}");
    if (! file.exists() || ! file.isDirectory) file.mkdirs();
    println(InputItem.sendMessage("""
            {
              "sfu02": "${SimpleDateFormat("yyyy-MM-dd").format(date)}",
              "sfu04": "5704",
              "sfv04": "Q04AB78AAE3",
              "sfu16": "kcl0047",
              "sfv11": "61900-210400145",
              "sfv09": "1.000",
              "sfv05": "1013",
              "sfv06": "C",
              "sfu07": "570052",
              "sfv08": "PCS",
              "sfv12": "570052",
              "kc": "KC24"
            }
        """.trimIndent()).also {
        println(it.getRequest().also { a -> File(file, "请求.xml").write(a ?: "") })
        println(it.backtrack().also { a -> File(file, "报文.xml").write(a) })
    }.isSuccess())
    Thread.sleep(10000)
}
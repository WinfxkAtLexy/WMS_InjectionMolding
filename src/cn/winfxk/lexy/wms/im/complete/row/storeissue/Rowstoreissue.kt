package cn.winfxk.lexy.wms.im.complete.row.storeissue

import cn.winfxk.lexy.wms.im.client.test.CwsCecp980CreateRequestCwsCecp980CreateRequest
import cn.winfxk.lexy.wms.im.client.test.TIPTOPServiceGateWay
import cn.winfxk.lexy.wms.im.item.PostLog
import cn.winfxk.libk.log.Log
import cn.winfxk.libk.tool.Tool
import cn.winfxk.libk.tool.utils.toJson
import com.alibaba.fastjson.JSONObject
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * 原材料发料
 * @param acc 账套
 * @param tc_wsg01 源工单号
 * @param tc_wsg02 工单号
 * @param tc_wsg03 类型: 1发料 2成品入库 3退料
 * @param tc_wsg04 人员（KC号）
 * @param tc_wsg05 部门
 * @param tc_wsg11 料号
 * @param tc_wsg12 仓库
 * @param tc_wsg13 储位
 * @param tc_wsg14 批号
 * @param tc_wsg15 单位
 * @param tc_wsg16 数量
 */
class Rowstoreissue(
    val acc: String,
    val tc_wsg01: String,
    val tc_wsg02: String,
    val tc_wsg03: String = "1",
    val tc_wsg04: String,
    val tc_wsg05: String,
    val tc_wsg11: String,
    val tc_wsg12: String,
    val tc_wsg13: String,
    val tc_wsg14: String,
    val tc_wsg15: String,
    val tc_wsg16: String,
) {
    companion object {
        private val sfYMDHMS by lazy { SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); }
        private val service by lazy { TIPTOPServiceGateWay() };
        private val port by lazy { service.tiptopServiceGateWayPortType };
        @JvmStatic
        fun main(args: Array<String>) {
            sendMessage("KC04",
                """
                    {
                    "tc_wsg01": "61500-250500608",
                    "tc_wsg02": "61500-250500655",
                    "tc_wsg03": "1",
                    "tc_wsg04": "kcl3360",
                    "tc_wsg05": "6000",
                    "tc_wsg11": "SEA03801D13R",
                    "tc_wsg12": "2034",
                    "tc_wsg13": "",
                    "tc_wsg14": "",
                    "tc_wsg15": "PCS",
                    "tc_wsg16": "1"
                    }
                """.trimIndent().toJson()
            ).apply {
                println(isSuccess)
                println(description)
            }
        }
        @JvmStatic
        fun sendMessage(acc: String, json: com.alibaba.fastjson2.JSONObject) = sendMessage(acc, JSONObject(json));

        @JvmStatic
        fun sendMessage(acc: String, json: String) = sendMessage(acc, json.toJson());
        /**
         * 原材料发料
         */
        @JvmStatic
        fun sendMessage(acc: String, json: JSONObject): Result =
            Rowstoreissue(acc,
                json.getString("tc_wsg01"),
                json.getString("tc_wsg02"),
                "1",
                json.getString("tc_wsg04"),
                json.getString("tc_wsg05"),
                json.getString("tc_wsg11"),
                json.getString("tc_wsg12"),
                json.getString("tc_wsg13"),
                json.getString("tc_wsg14"),
                json.getString("tc_wsg15"),
                json.getString("tc_wsg16")
            ).sendMessage()
    }

    private val log = Log(this.javaClass.simpleName)
    private val date = Date();
    val reqno: String by lazy {
        val time = System.currentTimeMillis();
        val random = Random();
        return@lazy "${Tool.CompressNumber(time)}-${Tool.CompressNumber(random.nextInt(9999999).toLong())}"
    }

    val xml: String by lazy {
        """
        <Request>
            <Access>
                <Authentication user="" password=""/>
                <Connection application="" source=""/>
                <Organization name="$acc"/>
                --填ERP的当前法人编号 例如 KC04
                <Locale language="zh_cn"/>
                --language="zh_cn"
            </Access>
            <RequestContent>
                <Parameter>
                    <Record>
                        <Field name="reqno" value="$reqno"/>
                        --请求编号 VARCHAR2(20)
                        <Field name="reqtime" value="${sfYMDHMS.format(date)}"/>
                        --请求时间 yyyy-MM-dd HH:mm:ss
                        <Field name="reqclient" value="MES_WMS"/>
                        --请求来源 固定值 自己系统的代号
                        <Field name="reqtype" value="0"/>
                        --类型 固定值 '0'
                    </Record>
                </Parameter>
                <Document>
                    <RecordSet id="1">
                        <Master name="header">
                            <Record>
                                <Field name="tc_wsg01" value="$tc_wsg01"/>
                                --varchar2(20) 源工单
                                <Field name="tc_wsg02" value="$tc_wsg02"/>
                                --varchar2(20) 当前工单
                                <Field name="tc_wsg03" value="$tc_wsg03"/>
                                --类型: 1发料 2成品入库 3退料
                                <Field name="tc_wsg04" value="$tc_wsg04"/>
                                --人员
                                <Field name="tc_wsg05" value="$tc_wsg05"/>
                                --部门
                                <Field name="tc_wsg06" value="$acc"/>
                                --当前法人
                            </Record>
                        </Master>
                        <Detail name="body">
                            <Record>
                                <Field name="tc_wsg11" value="$tc_wsg11"/>
                                --varchar2(40) 料号
                                <Field name="tc_wsg12" value="$tc_wsg12"/>
                                --varchar2(10) 仓库
                                <Field name="tc_wsg13" value="$tc_wsg13"/>
                                --varchar2(10) 储位
                                <Field name="tc_wsg14" value="$tc_wsg14"/>
                                --varchar2(40) 批号
                                <Field name="tc_wsg15" value="$tc_wsg15"/>
                                --varchar2(10) 单位
                                <Field name="tc_wsg16" value="$tc_wsg16"/>
                                --number(15,3) 数量
                            </Record>
                        </Detail>
                    </RecordSet>
                </Document>
            </RequestContent>
        </Request>
    """.trimIndent()
    }

    fun sendMessage(): Result {
        try {
            port.cwsCecp980Create(CwsCecp980CreateRequestCwsCecp980CreateRequest().apply {
                this.request = xml;
            }).apply {
                return Result(this@Rowstoreissue, response).apply {
                    if (isSuccess) return@apply;
                    val log = PostLog();
                    log.IPC = UUID.randomUUID().toString();
                    log.SYS_C = "WMS";
                    log.ITEM_C = "注塑发货";
                    log.ITEM_N = "注塑件收货";
                    log.STATE = "NG";
                    log.CONTEXT = xml;
                    log.RESULT = response;
                    log.MARK = description;
                    log.CNAME = tc_wsg04
                    log.FAC_C = tc_wsg05;
                    log.MCODE = "1001";
                    log.post()
                }
            }
        } catch (e: Exception) {
            log.e("请求ERP时出现异常！", e);
            val log = PostLog();
            log.IPC = UUID.randomUUID().toString();
            log.SYS_C = "WMS";
            log.ITEM_C = "注塑发货";
            log.ITEM_N = "注塑件收货";
            log.STATE = "NG";
            log.CONTEXT = xml;
            log.RESULT = StringWriter().also { e.printStackTrace(PrintWriter(it)) }.toString()
            log.MARK = e.message;
            log.CNAME = tc_wsg04
            log.FAC_C = tc_wsg05;
            log.MCODE = "1001";
            log.throwable = e;
            log.post()
            return Result(this, null, e)
        }
    }
}
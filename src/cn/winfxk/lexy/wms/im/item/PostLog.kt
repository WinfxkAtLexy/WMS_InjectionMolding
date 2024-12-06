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
* Created Date: 2024/12/3  14:37 */
package cn.winfxk.lexy.wms.im.item

import cn.winfxk.libk.log.Log
import com.mchange.v2.c3p0.ComboPooledDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class PostLog(var IPC: String?, var SYS_C: String?, var ITEM_C: String?, var ITEM_N: String?, var STATE: String?, var CONTEXT: String?, var RESULT: String?, var MARK: String?, var CNAME: String?, var FAC_C: String?, var MCODE: String?) {
    constructor() : this(null, null, null, null, null, null, null, null, null, null, null)
    constructor(xml: Response) : this("${xml.fac}_SIMFSH_Magt_entery_chk_fshentry_frm_${UUID.randomUUID()}", "WMS", "SIMFSH_Magt_entery_chk_fshentry_frm", "注塑件收货", "NG", xml.getRequest(), xml.backtrack(), xml.getDescription(), xml.sfu16, xml.fac, "1001")

    companion object {
        private val log = Log(PostLog::class.java.simpleName)
        private val scope = CoroutineScope(Dispatchers.Default);
        private val dataSource = ComboPooledDataSource().also {
            log.i("初始化数据库链接")
            it.driverClass = "oracle.jdbc.driver.OracleDriver"
            it.jdbcUrl = "jdbc:oracle:thin:@192.168.0.92:1521:wmsdb"
            it.user = "wmswr"
            it.password = "A_123456AA"
            it.initialPoolSize = 5
            it.maxPoolSize = 20
            it.minPoolSize = 5
            it.maxIdleTime = 300
            it.acquireIncrement = 5
        }
        private const val sql = "insert into WMS_LOG_RECORD (IPC, SYS_C, ITEM_C, ITEM_N, STATE, CONTEXT, RESULT, MARK, CNAME, FAC_C,MCODE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    fun post() {
        scope.launch {
            try {
                dataSource.connection.use { connection ->
                    val stmt = connection.prepareStatement(sql);
                    stmt.setString(1, IPC);
                    stmt.setString(2, SYS_C);
                    stmt.setString(3, ITEM_C);
                    stmt.setString(4, ITEM_N);
                    stmt.setString(5, STATE);
                    stmt.setString(6, CONTEXT);
                    stmt.setString(7, RESULT);
                    stmt.setString(8, MARK);
                    stmt.setString(9, CNAME);
                    stmt.setString(10, FAC_C);
                    stmt.setString(11, MCODE);
                    if (stmt.executeUpdate() > 0) log.i("日志插入成功！")
                    else log.e("日志插入失败！")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
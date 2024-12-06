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
* Created Date: 2024/11/18  14:02 */
package cn.winfxk.lexy.wms.im.item

import cn.winfxk.libk.tool.Tool
import java.text.SimpleDateFormat
import java.util.*

class Xmlstring {
    companion object {
        private val sfYMDHMS = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fun getXml(kc04: String, sfu_file: String, sfv_file: String): String {
            return """
            <Request>
                <Access>
                    <Authentication user="" password=""/>
                    <Connection application="" source=""/>
                    <Organization name="$kc04"/>      <!--账套  法人编号-->     
                    <Locale language="zh_cn"/>                   
                </Access>
                <RequestContent>
                    <Parameter>
                        <Record>
                            <Field name ="reqno" value="${getNO()}" />               
                            <Field name ="reqtime" value="${sfYMDHMS.format(Date())}" />   
                            <Field name ="reqclient" value="zzgxb" />              
                            <Field name ="reqtype" value="0" />                    
                        </Record>
                    </Parameter>
                    <Document>
                        <RecordSet id="1" >
                            <Master name="sfu_file">
                                <Record>
                                $sfu_file
                                </Record>
                            </Master>
                            <Detail name="sfv_file">
                                <Record>
                                $sfv_file
                                </Record>
                            </Detail>
                        </RecordSet>
                    </Document>
                </RequestContent>
              </Request>
        """
        }


    }

}

private fun getNO(): String {
    val time = System.currentTimeMillis();
    val random = Random();
    return "${Tool.CompressNumber(time)}-${Tool.CompressNumber(random.nextInt(9999999).toLong())}"
}


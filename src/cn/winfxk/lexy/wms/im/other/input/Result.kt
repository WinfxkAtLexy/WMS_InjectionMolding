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
* Created Date: 2025/4/15  08:03 */
package cn.winfxk.lexy.wms.im.other.input

import cn.winfxk.lexy.wms.im.item.PostLog
import com.alibaba.fastjson2.JSONObject
import java.io.StringReader
import java.util.*
import javax.xml.namespace.QName
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamException
import javax.xml.stream.events.Attribute
import javax.xml.stream.events.StartElement


class Result(val xml: String, val post: String?,val error: Throwable? = null,private val json: JSONObject) {
    private var code: String? = null;
    private var sqlCode: String? = null;
    private var description: String? = null;
    fun isSuccess() = "0" == code;
    override fun toString(): String {
        return "{code: $code, sqlCode: $sqlCode, description: $description}"
    }

    init {
        parseResponseXml();
        if(!isSuccess()){
            val log = PostLog();
            log.IPC = UUID.randomUUID().toString();
            log.SYS_C = "WMS";
            log.ITEM_C = "other_input/output";
            log.ITEM_N = "杂收发";
            log.STATE = "NG";
            log.CONTEXT = post;
            if(error!=null)
            log.RESULT = xml
            log.throwable=error;
            log.MARK = error?.message?:description;
            log.CNAME = json["ina11"]?.toString();
            log.FAC_C = json["ina04"]?.toString();
            log.MCODE = "1003";
            log.post()
        }
    }
    @Throws(XMLStreamException::class)
    fun parseResponseXml() {
        val factory = XMLInputFactory.newInstance()
        val reader = factory.createXMLEventReader(StringReader(xml))
        while (reader.hasNext()) {
            val event = reader.nextEvent()
            if (event.isStartElement) {
                val startElement = event.asStartElement()
                val elementName = startElement.name.localPart
                if ("Status" == elementName) {
                    // 解析属性
                    this.code = getAttributeValue(startElement, "code")
                    this.sqlCode = getAttributeValue(startElement, "sqlcode")
                    this.description = getAttributeValue(startElement, "description")
                    break // 找到目标后立即退出
                }
            }
        }
        reader.close()
    }

    private fun getAttributeValue(element: StartElement, attrName: String): String {
        val attribute: Attribute? = element.getAttributeByName(QName.valueOf(attrName))
        return if (attribute != null) attribute.value else ""
    }
}
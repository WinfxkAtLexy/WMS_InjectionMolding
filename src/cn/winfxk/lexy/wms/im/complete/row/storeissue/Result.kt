/* 
* Copyright Notice
* © [2024 - 2025] Winfxk. All rights reserved.
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
* Created Date: 2025/7/15  16:38 */
package cn.winfxk.lexy.wms.im.complete.row.storeissue

import java.io.StringReader
import javax.xml.namespace.QName
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamException
import javax.xml.stream.events.StartElement

class Result(private val main: Rowstoreissue, val response: String?, val throwable: Throwable? = null) {
    val xml: String get() = main.xml;
    val ID: String get() = main.reqno;
    val isSuccess get() = "0" == code
    val description: String? get() = message;
    private var code: String? = null;
    private var sqlCode: String? = null;
    private var message: String? = null;
    private val docnoMap = HashMap<String, String>()

    init {
        parseXml();
    }

    @Throws(XMLStreamException::class)
    fun parseXml() {
        val factory = XMLInputFactory.newInstance()
        val reader = factory.createXMLEventReader(StringReader(response ?: """
            <Response>
                <Execution>
                    <Status code="-1" sqlcode="-1" description="${throwable?.message ?: "未知原因"}"/>
                </Execution>
                <ResponseContent>
                    <Parameter/>
                    <Document/>
                </ResponseContent>
            </Response>
        """.trimIndent()))
        while (reader.hasNext()) {
            val event = reader.nextEvent()
            if (event.isStartElement) {
                val element = event.asStartElement()
                val elementName = element.name.localPart
                if ("Status" == elementName) {
                    this.code = getAttribute(element, "code")
                    this.sqlCode = getAttribute(element, "sqlcode")
                    this.message = getAttribute(element, "description")
                }
                if ("Results" == elementName) {
                    val recordSet = getAttribute(element, "RecordSet")
                    val docno = getAttribute(element, "Docno")
                    if (recordSet != null && docno != null) {
                        docnoMap[recordSet] = docno
                    }
                }
            }
        }
        reader.close()
    }

    private fun getAttribute(element: StartElement, name: String): String? {
        val attr = element.getAttributeByName(
            QName.valueOf(name)
        )
        return attr?.value
    }
}
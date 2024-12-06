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
* Created Date: 2024/11/18  11:06 */
package cn.winfxk.lexy.wms.im.item

import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

open class Response(private val message: String, private val xml: String? = null) {
    private var code: String? = null;
    private var sqlcode: String? = null;
    private var description: String? = null;
    var sfu16: String? = null;
    var fac: String? = null;

    init {
        DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(message.byteInputStream()).also {
            it.documentElement.normalize();
            val statusElements = it.getElementsByTagName("Status")
            if (statusElements.length > 0) (statusElements.item(0) as Element).also { statusElement ->
                code = statusElement.getAttribute("code")
                sqlcode = statusElement.getAttribute("sqlcode")
                description = statusElement.getAttribute("description")
            }
        }
    }

    fun backtrack() = message;
    fun getRequest() = xml;
    /**
     * 执行状态
     */
    fun getCode(): String? = code;
    /**
     * SQL执行状态
     */
    fun getSqlcode(): String? = sqlcode;
    /**
     * 执行回执
     */
    fun getDescription(): String? = description;
    /**
     * 是否执行成功
     */
    fun isSuccess(): Boolean = "0" == code;
    override fun toString(): String {
        return this.javaClass.simpleName + ": {xml: [$message], code: [$code], sqlcode: [$sqlcode], description: [$description], isSuccess: [${isSuccess()}]}"
    }
}
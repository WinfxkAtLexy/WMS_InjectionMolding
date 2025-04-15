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
* Created Date: 2025/4/15  11:01 */
package cn.winfxk.lexy.wms.im.other.input

enum class Type {
    /**
     *办公劳保(部门费用统计）
     **/
    BGLB,
    /**
     *备件杂发
     **/
    BJZF,
    /**
     *备件杂收
     **/
    BJZS,
    /**
     *部门领用(部门费用统计）
     **/
    BMLY,
    /**
     *保内备件
     **/
    BNBJ,
    /**
     *材料调整
     **/
    CLTZ,
    /**
     *产品报废(部门费用统计）
     **/
    CPBF,
    /**
     *产品领用
     **/
    CPLY,
    /**
     *变卖物料
     **/
    DLBF,
    /**
     *客供物料
     **/
    KGWL,
    /**
     *票面调整
     **/
    PMTZ,
    /**
     *材料转换
     **/
    SZHB,
    /**
     *退货质量报废(部门费用统计）
     **/
    THBF,
    /**
     *物料杂收(部门费用统计）
     **/
    WLZS,
    /**
     *消耗性料件领用
     **/
    XHLY,
    /**
     * 研发-试产余量(部门费用统计）
     **/
    YFCC,
    /**
     * 研发-工装夹具及检具(部门费用统计）
     **/
    YFGZ,
    /**
     * 研发-测试食材(耗材)领用(部门费用统计）
     **/
    YFHC,
    /**
     * 研发-样机零部件的领用(部门费用统计）
     **/
    YFLJ,
    /**
     * 研发-试模/试产塑料粒子领用(部门费用统计）
     **/
    YFSM,
    /**
     * 研发-成品样机领用(部门费用统计）
     **/
    YFYJ,
    /**
     *一件代发杂收
     **/
    YJDF,
    /**
     *样机领用
     **/
    YJLY,
    /**
     *质量保修配件(部门费用统计）
     **/
    ZBPJ,
    /**
     *工厂质量报废(部门费用统计）
     **/
    ZLBF,
    /**
     *质量检验(部门费用统计）
     **/
    ZLJY;

    val key get() = this.name.uppercase();
    override fun toString(): String = key
}
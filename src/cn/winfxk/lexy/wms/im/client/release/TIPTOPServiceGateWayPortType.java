
package cn.winfxk.lexy.wms.im.client.release;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "TIPTOPServiceGateWayPortType", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TIPTOPServiceGateWayPortType {


    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.PajieToErpResponsePajieToErpResponse
     */
    @WebMethod(operationName = "PajieToErp")
    @WebResult(name = "PajieToErpResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public PajieToErpResponsePajieToErpResponse pajieToErp(
        @WebParam(name = "PajieToErpRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        PajieToErpRequestPajieToErpRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.LihuaToErpResponseLihuaToErpResponse
     */
    @WebMethod(operationName = "LihuaToErp")
    @WebResult(name = "LihuaToErpResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public LihuaToErpResponseLihuaToErpResponse lihuaToErp(
        @WebParam(name = "LihuaToErpRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        LihuaToErpRequestLihuaToErpRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.LiHuaWoStockinResponseLiHuaWoStockinResponse
     */
    @WebMethod(operationName = "LiHua_Wo_Stockin")
    @WebResult(name = "LiHua_Wo_StockinResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public LiHuaWoStockinResponseLiHuaWoStockinResponse liHuaWoStockin(
        @WebParam(name = "LiHua_Wo_StockinRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        LiHuaWoStockinRequestLiHuaWoStockinRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.LiHuaMiscellaneousResponseLiHuaMiscellaneousResponse
     */
    @WebMethod(operationName = "LiHua_Miscellaneous")
    @WebResult(name = "LiHua_MiscellaneousResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public LiHuaMiscellaneousResponseLiHuaMiscellaneousResponse liHuaMiscellaneous(
        @WebParam(name = "LiHua_MiscellaneousRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        LiHuaMiscellaneousRequestLiHuaMiscellaneousRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.LhTransferCreateResponseLhTransferCreateResponse
     */
    @WebMethod(operationName = "lh.transfer.create")
    @WebResult(name = "lh.transfer.createResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public LhTransferCreateResponseLhTransferCreateResponse lhTransferCreate(
        @WebParam(name = "lh.transfer.createRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        LhTransferCreateRequestLhTransferCreateRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.XnyWarehouseMiscellaneousCreateResponseXnyWarehouseMiscellaneousCreateResponse
     */
    @WebMethod(operationName = "xny.warehouse.miscellaneous.create")
    @WebResult(name = "xny.warehouse.miscellaneous.createResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public XnyWarehouseMiscellaneousCreateResponseXnyWarehouseMiscellaneousCreateResponse xnyWarehouseMiscellaneousCreate(
        @WebParam(name = "xny.warehouse.miscellaneous.createRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        XnyWarehouseMiscellaneousCreateRequestXnyWarehouseMiscellaneousCreateRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.LiHuaFcstMrpResponseLiHuaFcstMrpResponse
     */
    @WebMethod(operationName = "LiHuaFcstMrp")
    @WebResult(name = "LiHuaFcstMrpResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public LiHuaFcstMrpResponseLiHuaFcstMrpResponse liHuaFcstMrp(
        @WebParam(name = "LiHuaFcstMrpRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        LiHuaFcstMrpRequestLiHuaFcstMrpRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.XnyWoStockinCreateResponseXnyWoStockinCreateResponse
     */
    @WebMethod(operationName = "xny.wo.stockin.create")
    @WebResult(name = "xny.wo.stockin.createResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public XnyWoStockinCreateResponseXnyWoStockinCreateResponse xnyWoStockinCreate(
        @WebParam(name = "xny.wo.stockin.createRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        XnyWoStockinCreateRequestXnyWoStockinCreateRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.XnyWarehouseTransferCreateResponseXnyWarehouseTransferCreateResponse
     */
    @WebMethod(operationName = "xny.warehouse.transfer.create")
    @WebResult(name = "xny.warehouse.transfer.createResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public XnyWarehouseTransferCreateResponseXnyWarehouseTransferCreateResponse xnyWarehouseTransferCreate(
        @WebParam(name = "xny.warehouse.transfer.createRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        XnyWarehouseTransferCreateRequestXnyWarehouseTransferCreateRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.XnyOrderDeliveryCreateResponseXnyOrderDeliveryCreateResponse
     */
    @WebMethod(operationName = "xny.order.delivery.create")
    @WebResult(name = "xny.order.delivery.createResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public XnyOrderDeliveryCreateResponseXnyOrderDeliveryCreateResponse xnyOrderDeliveryCreate(
        @WebParam(name = "xny.order.delivery.createRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        XnyOrderDeliveryCreateRequestXnyOrderDeliveryCreateRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.MomAsft620CreateResponseMomAsft620CreateResponse
     */
    @WebMethod(operationName = "mom.asft620.create")
    @WebResult(name = "mom.asft620.createResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public MomAsft620CreateResponseMomAsft620CreateResponse momAsft620Create(
        @WebParam(name = "mom.asft620.createRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        MomAsft620CreateRequestMomAsft620CreateRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.MomAqct110CreateResponseMomAqct110CreateResponse
     */
    @WebMethod(operationName = "mom.aqct110.create")
    @WebResult(name = "mom.aqct110.createResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public MomAqct110CreateResponseMomAqct110CreateResponse momAqct110Create(
        @WebParam(name = "mom.aqct110.createRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        MomAqct110CreateRequestMomAqct110CreateRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.MomAqct410CreateResponseMomAqct410CreateResponse
     */
    @WebMethod(operationName = "mom.aqct410.create")
    @WebResult(name = "mom.aqct410.createResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public MomAqct410CreateResponseMomAqct410CreateResponse momAqct410Create(
        @WebParam(name = "mom.aqct410.createRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        MomAqct410CreateRequestMomAqct410CreateRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.ZzgxbAsft620CreateResponseZzgxbAsft620CreateResponse
     */
    @WebMethod(operationName = "zzgxb.asft620.create")
    @WebResult(name = "zzgxb.asft620.createResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public ZzgxbAsft620CreateResponseZzgxbAsft620CreateResponse zzgxbAsft620Create(
        @WebParam(name = "zzgxb.asft620.createRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        ZzgxbAsft620CreateRequestZzgxbAsft620CreateRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.StmsAxmt670CreateResponseStmsAxmt670CreateResponse
     */
    @WebMethod(operationName = "stms.axmt670.create")
    @WebResult(name = "stms.axmt670.createResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public StmsAxmt670CreateResponseStmsAxmt670CreateResponse stmsAxmt670Create(
        @WebParam(name = "stms.axmt670.createRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        StmsAxmt670CreateRequestStmsAxmt670CreateRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.StmsDeliveryorderGetResponseStmsDeliveryorderGetResponse
     */
    @WebMethod(operationName = "stms.deliveryorder.get")
    @WebResult(name = "stms.deliveryorder.getResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public StmsDeliveryorderGetResponseStmsDeliveryorderGetResponse stmsDeliveryorderGet(
        @WebParam(name = "stms.deliveryorder.getRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        StmsDeliveryorderGetRequestStmsDeliveryorderGetRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns cn.winfxk.lexy.wms.im.client.release.ScmCxmt634ConfirmResponseScmCxmt634ConfirmResponse
     */
    @WebMethod(operationName = "scm.cxmt634.confirm")
    @WebResult(name = "scm.cxmt634.confirmResponse", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
    public ScmCxmt634ConfirmResponseScmCxmt634ConfirmResponse scmCxmt634Confirm(
        @WebParam(name = "scm.cxmt634.confirmRequest", targetNamespace = "http://www.dsc.com.tw/tiptop/TIPTOPServiceGateWay", partName = "parameters")
        ScmCxmt634ConfirmRequestScmCxmt634ConfirmRequest parameters);

}

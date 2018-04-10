/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package testsoap;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.json.*;

/**
 *
 * @author lehoa
 */
public class TestSOAP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, Exception {

//====================LẤY VỀ CHUỖI SOAP, CHUỖI JSON LIMITUSAGE & ACCUMULATE ======================= 
//SOAPResponse[0] = response
//SOAPResponse[1] = limitusage
//SOAPResponse[2] = accumulateData

        String[] SOAPResponse = checkSOAP("841278066688");
//====================XỬ LÝ CHUỖI JSON=======================
        List<reportingGroups> listRG = handleRG(SOAPResponse[1]);
        List<accumulateData> listAD = handleAD(SOAPResponse[2]);
//====================HẾT XỬ LÝ CHUỖI JSON===DATA====================
//Xử lý XML
//Parse chuỗi String XML thành DOM
        Document doc = getDOCfromXML(SOAPResponse[0]);

//        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList subsID = doc.getElementsByTagName("ns:pcSubscriberId");
        NodeList group = doc.getElementsByTagName("ns:pcGroup");
        NodeList notify = doc.getElementsByTagName("ns:pcNotificationData");
        NodeList limit = doc.getElementsByTagName("ns:pcLimitUsage");
        NodeList accumulate = doc.getElementsByTagName("ns:pcAccumulatedData");
        NodeList specific_info = doc.getElementsByTagName("ns:pcOperatorSpecificInfo");

//--------------------------------------------------------------------
//Xử lý ns:pcSubscriberId
        System.out.println("MSISDN: " + subsID.item(0).getTextContent());
//Xử lý ns:pcGroup
//Đếm số lượng pcGroup:
        System.out.println("Số lượng gói: " + group.getLength());
//Xử lý pcGroup:
        for (int i = 0; i < group.getLength(); i++) {
            Node pcGroup = group.item(i);
            if (pcGroup.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) pcGroup;
                System.out.println("Tên gói: " + eElement.getElementsByTagName("ns:pcGroupId").item(0).getTextContent());
                if (eElement.getElementsByTagName("ns:pcGroupStartDate").getLength() > 0) {
                    System.out.println("\tNgày bắt đầu: " + eElement.getElementsByTagName("ns:pcGroupStartDate").item(0).getTextContent());
                }
                if (eElement.getElementsByTagName("ns:pcGroupEndDate").getLength() > 0) {
                    System.out.println("\tNgày kết thúc: " + eElement.getElementsByTagName("ns:pcGroupEndDate").item(0).getTextContent());
                }
                if (eElement.getElementsByTagName("ns:pcGroupPriority").getLength() > 0) {
                    System.out.println("\tPriority: " + eElement.getElementsByTagName("ns:pcGroupPriority").item(0).getTextContent());
                }
            }
        }
//--------------------------------------------------------------------
//Xử lý ns:NotificationData
//Đếm số lượng Notification:
//        System.out.println("Số lượng notification: " + notify.getLength());
        System.out.println("Nofitication to: " + notify.item(0).getTextContent());
//--------------------------------------------------------------------
//--------------------------------------------------------------------
//Xử lý ns:FamilyId
//Đếm số lượng FamilyId:
int countFamily = checkFamily(doc);
List<String> listFamilyID = getFamilyId(doc);
//--------------------------------------------------------------------
//Xử lý ns:SpecificInfo
//Đếm số lượng SpecificInfo:
        System.out.println("Số lượng SpecificInfo: " + specific_info.getLength());
//Xử lý SpecificInfo:
        if (specific_info.getLength() > 0) {
            Node info = specific_info.item(1);
            if (info.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) info;
                System.out.println("Thông tin gói Family: " + eElement.getElementsByTagName("ns:pcOperatorSpecificInfo").item(0).getTextContent());
            }
        }
    }

    public static String[] checkSOAP(String MSISDN) {
        //Lấy thông tin thuê bao ra String response
        Cai3gRequest request = new Cai3gRequest();
        String sessionId = request.login();
//        System.out.println(request.querrySubSapc(sessionId, "84944431868"));
        String response = request.querrySubSapc(sessionId, MSISDN);
        response = response.substring(response.indexOf("<MOAttributes>") + 14, response.indexOf("</MOAttributes>"));
//        System.out.println(response);

        String limitusage = "";
        String accumulatedata = "";
        if (response.indexOf("<ns:pcLimitUsage>") > 0) {
            limitusage = response.substring(response.indexOf("<ns:pcLimitUsage>") + 17, response.indexOf("</ns:pcLimitUsage>"));
        }
        if (response.indexOf("<ns:pcAccumulatedData>") > 0) {
            accumulatedata = response.substring(response.indexOf("<ns:pcAccumulatedData>") + 22, response.indexOf("</ns:pcAccumulatedData>"));
        }
        
        String[] SOAPResponse = new String[3];
        SOAPResponse[0] = response;
        SOAPResponse[1] = limitusage;
        SOAPResponse[2] = accumulatedata;
        
        return SOAPResponse;
    }
    
    public static List<reportingGroups> handleRG(String limitUsage) throws JSONException{
        List<reportingGroups> listRG = new ArrayList<reportingGroups>();
        //Lấy object limutusageObj từ String limitusage
        JSONObject limitusageObj = new JSONObject(limitUsage);
        //Lấy JSONArray groupArr từ object limitusageObj.reportingGroups
        JSONArray groupArr = limitusageObj.getJSONArray("reportingGroups");
        System.out.println("Thuê bao có " + groupArr.length() + " group LimitUsage");
        for (int i = 0; i < groupArr.length(); i++) {
            //Lấy object limutusageObj từ array groupArr
            JSONObject groupObj = groupArr.getJSONObject(i);
            reportingGroups rg = new reportingGroups();
            rg.name = groupObj.getString("name");
            rg.sliceVolume = groupObj.getInt("sliceVolume");
            rg.description = groupObj.getString("description");
            rg.subscriptionDate = groupObj.getString("subscriptionDate");
            rg.subscriptionType = groupObj.getString("subscriptionType");
            rg.resetPeriod = groupObj.getJSONObject("absoluteLimits").getJSONObject("resetPeriod").getString("volume");
            rg.birdirVolume = groupObj.getJSONObject("absoluteLimits").getInt("bidirVolume");
            listRG.add(rg);
        }
        return listRG;
    }
    
    public static List<accumulateData> handleAD(String accumulateData) throws JSONException {
        List<accumulateData> listAD = new ArrayList<accumulateData>();
//Lấy object limutusageObj từ String limitusage
        JSONObject accumulatedataObj = new JSONObject(accumulateData);
//Lấy JSONArray groupArr từ object limitusageObj.reportingGroups
        JSONArray accData = accumulatedataObj.getJSONArray("reportingGroups");

        for (int i = 0; i < accData.length(); i++) {
            JSONObject accDataObj = accData.getJSONObject(i);
            accumulateData ad = new accumulateData();
            ad.birdirVolume = accDataObj.getJSONObject("absoluteAccumulated").getInt("bidirVolume");
            ad.expiryDate = accDataObj.getJSONObject("absoluteAccumulated").getJSONObject("expiryDate").getString("volume");
            ad.previousExpiryDate_time = accDataObj.getJSONObject("absoluteAccumulated").getJSONObject("previousExpiryDate").getString("time");
            ad.previousExpiryDate_volume = accDataObj.getJSONObject("absoluteAccumulated").getJSONObject("previousExpiryDate").getString("volume");
//            ad.reservedQuota = accDataObj.getJSONObject("absoluteAccumulated").getJSONObject("reservedQuota").getDouble("bidirVolume");
            ad.resetPeriod = accDataObj.getJSONObject("absoluteAccumulated").getJSONObject("resetPeriod").getString("volume");
            ad.name = accDataObj.getString("name");
            ad.selected = accDataObj.getString("selected");
            ad.subscriberGroupName = accDataObj.getString("subscriberGroupName");
            ad.subscriptionDate = accDataObj.getString("subscriptionDate");
            ad.validityTime = accDataObj.getInt("validityTime");
            System.out.println(ad.birdirVolume);
            System.out.println(ad.expiryDate);
            System.out.println(ad.previousExpiryDate_time);
            System.out.println(ad.previousExpiryDate_volume);
            System.out.println(ad.reservedQuota);
            System.out.println(ad.resetPeriod);
            System.out.println(ad.name);
            System.out.println(ad.selected);
            System.out.println(ad.subscriberGroupName);
            System.out.println(ad.subscriptionDate);
            System.out.println(ad.validityTime);
            listAD.add(ad);
        }
        return listAD;
    }

    public static Document getDOCfromXML(String xml) throws SAXException, ParserConfigurationException, IOException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
        doc.getDocumentElement().normalize();
        return doc;
    }
       
    public static int checkFamily(Document doc) {
        NodeList family = doc.getElementsByTagName("ns:pcFamilyId");
        return family.getLength();
    }
    public static List<String> getFamilyId(Document doc){
        NodeList family = doc.getElementsByTagName("ns:pcFamilyId");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < family.getLength(); i++) {
            Node familyId = family.item(i);
            list.add(familyId.getTextContent());
        }
        return list;
    }
    
}

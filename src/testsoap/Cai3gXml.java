/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsoap;

/**
 *
 * @author lehoa
 */
public class Cai3gXml {

    public static String LOGIN
            = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\">\n"
            + "   <soapenv:Header/>\n"
            + "   <soapenv:Body>\n"
            + "      <cai3:Login>\n"
            + "         <cai3:userId>%USER%</cai3:userId>\n"
            + "         <cai3:pwd>%PASSWORD%</cai3:pwd>\n"
            + "      </cai3:Login>\n"
            + "   </soapenv:Body>\n"
            + "</soapenv:Envelope>";

    public static String QUERRY_SUBS
            = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\" xmlns:hss=\"http://schemas.ericsson.com/ma/HSS/\">\n"
            + "<soapenv:Header>\n"
            + "<cai3:SequenceId>?</cai3:SequenceId>\n"
            + "<cai3:TransactionId>?</cai3:TransactionId>\n"
            + "<cai3:SessionId>%SESSION_ID%</cai3:SessionId>\n"
            + "</soapenv:Header>\n"
            + "<soapenv:Body>\n"
            + "<cai3:Get>\n"
            + "<cai3:MOType>EPSMultiSC@http://schemas.ericsson.com/ma/HSS/</cai3:MOType>\n"
            + "<cai3:MOId>\n"
            + "<!--You have a CHOICE of the next 2 items at this level-->\n"
            + "<hss:msisdn>%MSISDN%</hss:msisdn>\n"
            + "</cai3:MOId>\n"
            + "</cai3:Get>\n"
            + "</soapenv:Body>\n"
            + "</soapenv:Envelope>";

    public static String QUERRY_SUBS_SAPC
            = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\" xmlns:sapc=\"http://schemas.ericsson.com/ma/SAPC/\">\n"
            + "   <soapenv:Header>\n"
            + "      <cai3:SessionId>%SESSION_ID%</cai3:SessionId>\n"
            + "   </soapenv:Header>\n"
            + "   <soapenv:Body>\n"
            + "      <cai3:Get>\n"
            + "         <cai3:MOType>SAPCSubscription@http://schemas.ericsson.com/ma/SAPC/</cai3:MOType>\n"
            + "         <cai3:MOId>\n"
            + "            <sapc:pcSubscriberId>%MSISDN%</sapc:pcSubscriberId>\n"
            + "         </cai3:MOId>\n"
            + "      </cai3:Get>\n"
            + "   </soapenv:Body>\n"
            + "</soapenv:Envelope>";

    public static String QUERRY_SUBS_SAPC_FAMILY
            = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\" xmlns:sapc=\"http://schemas.ericsson.com/ma/SAPC/\">"
            + "<soapenv:Header>"
            + "   <cai3:SessionId>%SESSION_ID%</cai3:SessionId>"
            + "</soapenv:Header>"
            + "<soapenv:Body>"
            + "   <cai3:Get>"
            + "      <cai3:MOType>SAPCFamily@http://schemas.ericsson.com/ma/SAPC/</cai3:MOType>"
            + "     <cai3:MOId>"
            + "         <sapc:pcFamilyId>%FAMILY_ID%</sapc:pcFamilyId>"
            + "      </cai3:MOId>"
            + "  </cai3:Get>"
            + "</soapenv:Body>"
            + "</soapenv:Envelope>";

}

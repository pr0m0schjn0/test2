/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsoap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author lehoa
 */
public class Cai3gRequest {

    private String url, user, password;
    private long interval;
    static org.apache.commons.logging.Log logger = LogFactory.getLog(Cai3gRequest.class);

    public Cai3gRequest() {
        /* url = props.getString("cai3g.url");
        user = props.getString("cai3g.user");
        password = props.getString("cai3g.password");*/
        url = "http://10.204.11.122:8080/CAI3G1.2/services/CAI3G1.2";
        user = "longlh";
        password = "hanoi#01";
//        interval = props.getLong("cai3g.interval");
    }

    private String send(String msg) {
        PostMethod post;
        String resp = "";
        try {
            post = new PostMethod(url);
            HttpClient httpClient = new HttpClient();
            ByteArrayRequestEntity entity = new ByteArrayRequestEntity(msg.getBytes("UTF-8"));
            post.setRequestEntity(entity);

            int result = httpClient.executeMethod(post);
            BufferedReader br = null;
            if (result == HttpStatus.SC_NOT_IMPLEMENTED) {
                System.err.println("The Post method is not implemented by this URI");
                // still consume the response body
                resp = post.getResponseBodyAsString();
            } else {
                br = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
                String readLine;
                while (((readLine = br.readLine()) != null)) {
                    resp += readLine;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp = "<errordetails>" + e.getMessage() + "</errordetails>";
            logger.error(e);
        }
        return resp;
    }
    static String SID = null;

    public String login() {
        if (SID != null) {
            return SID;
        } else {
            SID = pLogin();
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    SID = pLogin();
////                    System.out.println("GET NEW SID: " + SID);
//                }
//            }, 300000, 300000);
            logger.info("Login session ID:" + SID);
            return SID;
        }

    }

    private String pLogin() {
        String xml = Cai3gXml.LOGIN;
        xml = xml.replaceAll("%USER%", user);
        xml = xml.replaceAll("%PASSWORD%", password);
//        System.out.println(xml);
        String resp = send(xml);
        String sessionId = resp.substring(resp.indexOf("<sessionId>") + 11, resp.indexOf("</sessionId>"));
        return sessionId;
    }

    public String querrySub(String sessionId, String msisdn) {

        String xml = Cai3gXml.QUERRY_SUBS;
        xml = xml.replaceAll("%SESSION_ID%", sessionId);
        xml = xml.replaceAll("%MSISDN%", msisdn);
//        System.out.println(xml);
        String resp = send(xml);
//        System.out.println(resp);
        return resp;
    }
    
    public String querrySubSapc(String sessionId, String msisdn) {

        String xml = Cai3gXml.QUERRY_SUBS_SAPC;
        xml = xml.replaceAll("%SESSION_ID%", sessionId);
        xml = xml.replaceAll("%MSISDN%", msisdn);
//        System.out.println(xml);
        String resp = send(xml);
//        System.out.println(resp);
        return resp;
    }
}

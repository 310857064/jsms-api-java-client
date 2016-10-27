package cn.jsms.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;


public class JSMSExample {

	protected static final Logger LOG = LoggerFactory.getLogger(JSMSExample.class);

    private static final String appkey = "242780bfdd7315dc1989fe2b";
    private static final String masterSecret = "2f5ced2bef64167950e63d13";
    
    public static void main(String[] args) {
    	testSendSMSCode();
//    	testSendValidSMSCode();
        testSendVoiceSMSCode();
        testSendTemplateSMS();
    }
    
    public static void testSendSMSCode() {
    	SMSClient client = new SMSClient(masterSecret, appkey);
    	SMSPayload payload = SMSPayload.newBuilder()
				.setMobildNumber("13800138000")
				.setTempId(1)
				.build();
    	try {
			SendSMSResult res = client.sendSMSCode(payload);
			LOG.info(res.toString());
		} catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
    }
    
    public static void testSendValidSMSCode() {
    	SMSClient client = new SMSClient(masterSecret, appkey);
		try {
			ValidSMSResult res = client.sendValidSMSCode("23956732-d63f-438b-b940-e1578cc0199f", "745141");
			LOG.info(res.toString());
		} catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
	}

    /**
     *  The default value of ttl is 60 seconds.
     */
	public static void testSendVoiceSMSCode() {
	    SMSClient client = new SMSClient(masterSecret, appkey);
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobildNumber("13800138000")
                .setTTL(90)
                .build();
        try {
            SendSMSResult res = client.sendVoiceSMSCode(payload);
            LOG.info(res.toString());
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        }
    }

	public static void testSendTemplateSMS() {
	    SMSClient client = new SMSClient(masterSecret, appkey);
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobildNumber("13800138000")
                .setTempId(1)
                .addTempPara("test", "jpush")
                .build();
        try {
            SendSMSResult res = client.sendTemplateSMS(payload);
            LOG.info(res.toString());
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        }
    }
    
}

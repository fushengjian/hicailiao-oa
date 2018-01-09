package com.tomowork.oa.message;

import java.io.IOException;
import java.rmi.Remote;

/**
 * 短信发送
 *
 * @author zlei
 */
public interface SmsMessageManager extends Remote {
	boolean sendSMS(String mobile, String content) throws IOException;
}

package com.tomowork.oa.message;

import java.io.IOException;
import java.rmi.Remote;

/**
 * 邮件发送
 *
 * @author zlei
 */
public interface MailMessageManager extends Remote {
	boolean sendMail(String to, String subject, String content) throws IOException;
}

package com.tomowork.oa.tools;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tomowork.oa.message.MailMessageManager;
import com.tomowork.oa.message.SmsMessageManager;

@Named
public class MsgTools {
	private static final Logger log = LoggerFactory.getLogger(MsgTools.class);

	@Inject
	private MailMessageManager mailMessageManager;

	@Inject
	private SmsMessageManager smsMessageManager;

	/**
	 * 发送短信
	 * @param mobile
	 * @param content
	 * @return
	 */
	public boolean sendSMS(String mobile, String content) {
		try {
			return smsMessageManager.sendSMS(mobile, content);
		} catch (IOException e) {
			log.error("error sendSMS", e);
		}
		return false;
	}

	/**
	 * 发送邮件
	 * @param email
	 * @param subject
	 * @param content
	 * @return
	 */
	public boolean sendEmail(String email, String subject, String content) {
		try {
			return mailMessageManager.sendMail(email, subject, content);
		} catch (IOException e) {
			log.error("error sendEmail", e);
		}
		return false;
	}
}

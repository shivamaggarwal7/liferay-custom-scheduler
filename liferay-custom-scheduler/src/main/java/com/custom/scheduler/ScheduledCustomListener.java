package com.custom.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class ScheduledCustomListener implements MessageListener {

	@Override
	public void receive(Message arg0) throws MessageListenerException {
		
          Log log=LogFactoryUtil.getLog(ScheduledCustomListener.class);		
		 
		  System.out.println(arg0.getString("message"));

	}

}

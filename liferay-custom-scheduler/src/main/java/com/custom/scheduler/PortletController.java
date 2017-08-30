package com.custom.scheduler;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.quartz.CronExpression;

import com.liferay.portal.kernel.cal.Recurrence;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.CronTrigger;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class PortletController extends MVCPortlet {

	
	public void createScheduler(ActionRequest req, ActionResponse res) {
		
		Logger log = Logger.getLogger(PortletController.class.getName());
		
		String msg = ParamUtil.getString(req, "message");
		String cron = ParamUtil.getString(req, "cron");
		
		if(!CronExpression.isValidExpression(cron))
		{
			SessionErrors.add(req, "invalidCron");
		}
	/*  int recurrence = ParamUtil.getInteger(req, "recurrence");
		int day = ParamUtil.getInteger(req, "day");
		int month = ParamUtil.getInteger(req, "month");
		int year = ParamUtil.getInteger(req, "year");

		int min = ParamUtil.getInteger(req, "min");
		int hour = ParamUtil.getInteger(req, "hour");
		int amPm = ParamUtil.getInteger(req, "amPm");*/

		else
		{
		String jobName = ScheduledCustomListener.class.getName();
		String portletId= (String)req.getAttribute(WebKeys.PORTLET_ID);

		/*Calendar startCalendar = new GregorianCalendar(year, month, day, hour, min);
		
		
		String jobCronPattern = SchedulerEngineHelperUtil.getCronText(req, startCalendar, false,recurrence);

		log.info(jobCronPattern);
*/
		Trigger trigger = new CronTrigger(jobName, jobName, cron);

		Message message = new Message();
		message.put(SchedulerEngine.MESSAGE_LISTENER_CLASS_NAME, jobName);
		message.put(SchedulerEngine.PORTLET_ID, portletId);
		message.put("message", msg);

		try {
			
			SchedulerEngineHelperUtil.schedule(trigger, StorageType.PERSISTED, msg,
					DestinationNames.SCHEDULER_DISPATCH, message, 0);

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	}

}

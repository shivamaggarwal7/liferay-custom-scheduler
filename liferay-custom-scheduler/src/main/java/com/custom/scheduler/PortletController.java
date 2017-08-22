package com.custom.scheduler;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.cal.Recurrence;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.CronTrigger;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class PortletController extends MVCPortlet {

	/*
	 * @Override public void render(RenderRequest req,RenderResponse res) throws
	 * PortletException, IOException { LocalDate date=LocalDate.now();
	 * req.setAttribute("date", date); super.render(req, res); }
	 */
	
	public void createScheduler(ActionRequest req, ActionResponse res) {
		Logger log = Logger.getLogger(PortletController.class.getName());
		int day = ParamUtil.getInteger(req, "day");
		int month = ParamUtil.getInteger(req, "month");
		int year = ParamUtil.getInteger(req, "year");

		int min = ParamUtil.getInteger(req, "min");
		int hour = ParamUtil.getInteger(req, "hour");

		String msg = ParamUtil.getString(req, "message");
		String jobName = ScheduledCustomListenerNew.class.getName();
		String portletId= (String)req.getAttribute(WebKeys.PORTLET_ID);

		Calendar startCalendar = new GregorianCalendar(year, month, day, hour, min);

		String jobCronPattern = SchedulerEngineHelperUtil.getCronText(req, startCalendar, false, Recurrence.DAILY);

		log.info(jobCronPattern);

		Trigger trigger = new CronTrigger(jobName, jobName, jobCronPattern);
		log.info("Calendar start" + startCalendar);

		Message message = new Message();
		message.put(SchedulerEngine.MESSAGE_LISTENER_CLASS_NAME, jobName);
		message.put(SchedulerEngine.PORTLET_ID, portletId);
		message.put("message", msg);

		try {
			
			SchedulerEngineHelperUtil.schedule(trigger, StorageType.PERSISTED, "Message_Desc",
					DestinationNames.SCHEDULER_DISPATCH, message, 0);

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

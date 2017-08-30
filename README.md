# liferay-custom-scheduler
This portlet will initiate Kaleo scheduler based on user input

# Take aways from this portlet
- Attempted to use Date and time input from user interaction but it made things real messy
- Initial draft required start date and time from user and using SchedulerEngineHelperUtil.getCronText to generate Cron,which was not 
  as per requirement,either due to data format required or some other reason
- Finally Cron text is now directly expected from user,along with any Scheduler message to scheduler an activity
- Cron can be generated from any online utility such as <http://www.cronmaker.com> ,as per user needs
- Currently a user provided message is displayed in activity,for real scenarios,an actual activity/task
  can be performed in the same 
	

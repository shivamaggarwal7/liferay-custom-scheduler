<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.Instant"%>
<%@page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<portlet:defineObjects />
<%
LocalDateTime date = LocalDateTime.now();
%>
<portlet:actionURL name="createScheduler" var="scheduleURL"/>


<aui:form action="${scheduleURL}" method="post">

<aui:field-wrapper label="Scheduler date and time">
<liferay-ui:input-date formName="date" yearValue="<%=date.getYear()%>" 
           monthValue="<%=date.getMonthValue() %>" dayValue="<%=date.getDayOfMonth() %>"
            dayParam="day" monthParam="month" yearParam="year" />
            
<liferay-ui:input-time
							amPmParam="defaultValueAmPm"
							
							hourParam="hour"
							hourValue="<%= date.getHour()%>"
							minuteParam="min"
							minuteValue="<%= date.getMinute()%>"
						/>
</aui:field-wrapper>					
<aui:input name="message" type="text" label="Message"/>						
<aui:button type="submit" value="Submit"/>						
  
</aui:form>

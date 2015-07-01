<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<fmt:formatDate var="nowDate" value="${now}" pattern="dd.MM.yyyy" />

	<div class="from">
		<span class="frmt">From :</span> <input type="text" value="${nowDate}"
			id="searchTimeBarFromDate" onblur="javascript:getOrdersByFromDate();" class="inpfm">
	</div>
	<div class="from1">
		<c:choose>
			<c:when test="${param.status=='PENDING'}">
				<span class="frmt">To:</span>
				<input type="text" disabled value="${nowDate}"
					id="searchTimeBarToDate" 
					onblur="javascript:getOrdersByFromDate();" class="disable" class="inpfm"/>
			</c:when>
			<c:otherwise>
				<span class="frmt">To:</span>
				<input type="text" value="${nowDate}" id="searchTimeBarToDate"
					onblur="javascript:getOrdersByFromDate();" class="inpfm"/>
			</c:otherwise>
		</c:choose>
	</div>


	<div class="from2 crbm">
		<span class="frmt">From:</span> <input type="text" value="00:00 AM"
			id="searchTimeBarFromTime" onblur="javascript:getOrdersByFromDate();" class="inpfm"/>
	</div>
	<div class="from2">
		<span class="frmt">To:</span>
		<fmt:formatDate var="nowTime" value="${now}" pattern="hh:mm aa" />
		<input type="text" value="${nowTime}" id="searchTimeBarToTime"
			onblur="javascript:getOrdersByFromDate();" class="inpfm"/>
	</div>


<%-- ---------------------------------------------------------------------------------------------------------------------------------
<div class="datetime_block">
	<div class="date_block">
		<ul>
			<li><a href="#"><img src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/calender_icon.png" alt="calender" border="0"/></a></li>
			<li>From :</li>
			<li>
				<input type="text" value="${nowDate}" id="searchTimeBarFromDate" onblur="javascript:getOrdersByFromDate();">
			</li>
			<c:choose>
			
		<c:when test="${param.status=='PENDING'}"> 
		
			<li>To :</li>
			<li>
				<input type="text" disabled value="${nowDate}" id="searchTimeBarToDate" class="disable" onblur="javascript:getOrdersByFromDate();">
			</li>
			</c:when>
			
			<c:otherwise> 
			<li>To :</li>
			<li>
				<input type="text"  value="${nowDate}" id="searchTimeBarToDate"  onblur="javascript:getOrdersByFromDate();">
			</li> 
			
			</c:otherwise>
			</c:choose> 
		
		</ul>
	</div>
	<div class="time_block">
		<ul>
			<li><a href="#"><img src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/clock_icon.png" alt="clock" border="0"/></a></li>
			<li>From :</li>
			<li>
				<input type="text" value="00:00 AM" id="searchTimeBarFromTime" onblur="javascript:getOrdersByFromDate();">
			</li>
			<li>To :</li>
			<li>
				<fmt:formatDate var="nowTime" value="${now}" pattern="hh:mm aa"/>
				<input type="text" value="${nowTime}" id="searchTimeBarToTime" onblur="javascript:getOrdersByFromDate();">
			</li>
		</ul>
	</div>
</div> --%>
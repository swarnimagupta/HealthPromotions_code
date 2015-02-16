<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ctsrc">
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<fmt:formatDate var="nowDate" value="${now}" pattern="MM/dd/yyyy" />
	<div class="from">
		<span class="frmt">From:</span> 
		<input type="text" value="${nowDate}" id="datepicker" onblur="javascript:getCustomersByFromDate();"	class="inpfm"/>
	</div>
	<div class="from1">
		<c:choose>
			<c:when test="${param.status=='PENDING'}">
				<span class="frmt">To:</span>
				<input type="text" disabled value="${nowDate}" id="datepicker1"	class="disable" onblur="javascript:getCustomersByFromDate();" class="inpfm"/>
			</c:when>
			<c:otherwise>
				<span class="frmt">To:</span>
				<input type="text" value="${nowDate}" id="datepicker1" onblur="javascript:getCustomersByFromDate();" class="inpfm"/>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="from2 crbm">
		<span class="frmt">From:</span> 
		<input type="text" value="00:00 AM"	id="searchTimeBarFromTime"	onblur="javascript:getCustomersByFromDate();" class="inpfm"/>
	</div>
	<div class="from2">
		<span class="frmt">To:</span>
		<fmt:formatDate var="nowTime" value="${now}" pattern="hh:mm aa" />
		<input type="text" value="${nowTime}" id="searchTimeBarToTime"	onblur="javascript:getCustomersByFromDate();" class="inpfm"/>
	</div>
</div>
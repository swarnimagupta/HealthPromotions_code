<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		//run the first time; all subsequent calls will take care of themselves -->
		if(document.getElementById("currentUserId")!=null)
		{
			getCustomerDetails(document.getElementById("currentUserId").value);
			setTimeout(function () {$("#accordion").accordion();}, 3000);
		}
	});
	
</script>
<!--Left Menu Area Starts here-->
<div class="srch">
	<input type="text" value="Search" class="inpt" id="customername" onblur="javascript:searchByCustomerName();">
</div>
<c:forEach items="${customerLoggedInDataList}" var="logedInUser" varStatus="counter">
	<c:set var="currentClass" value="" />
	<c:if test="${counter.count==1}">
		<c:set var="currentClass" value='class="active"' />
		<input type="hidden" id="currentUserId"	value="${logedInUser.storeCustomerPK}" />
	</c:if>
	<ul id="slimtest">
		<li>
			<a id="${logedInUser.storeCustomerPK}" onclick="javascript:getCustomerDetails('${logedInUser.storeCustomerPK}');" ${currentClass}> 
				<c:set var="imageUrl" value="${logedInUser.profilePictureURL}" /> 
				<c:if test="${empty imageUrl}">
					<c:set var="imageUrl" value="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/new/person2.png" />
				</c:if> 
				<img src="${imageUrl}" class="fl img-width" />
				<div class="fl pdg">
					${logedInUser.customerName}<br />
					<span class="time">${logedInUser.loginTime}</span>
				</div> 
				<c:if test="${param.status=='INSERVICE' || param.status=='COMPLETED'}">
					<span> assisted by ${logedInUser.processedBy} </span>
				</c:if>
			</a>
		</li>
	</ul>
</c:forEach>
<!--Left Menu Area Ends here-->
 
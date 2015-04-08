<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<json:object>
	<json:property name="customerslist" escapeXml="false">
		<div class="srch"><input type="text" value="" placeholder="Search" class="inpt" id="customername" onblur="javascript:searchByCustomerName();"/></div>
		<c:set var="length" value="${fn:length(customerLoggedInDataList)}"></c:set>
		<ul id="slimtest">
			<c:forEach items="${customerLoggedInDataList}" var="logedInUser" varStatus="counter">
				<c:set var="currentClass" value="" />
				<c:if test="${counter.count==1}">
					<c:set var="currentClass" value='class="active"' />
					<input type="hidden" id="currentUserId"	value="${logedInUser.storeCustomerPK}" />
				</c:if>
				<li>
					<a id="${logedInUser.storeCustomerPK}" onclick="javascript:getCustomerDetails('${logedInUser.storeCustomerPK}');" ${currentClass}> 
						<c:set var="imageUrl" value="${logedInUser.profilePictureURL}" /> 
						<c:if test="${empty imageUrl}">
							<c:set var="imageUrl" value="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/new/person2.png" />
						</c:if> 
						<img src="${imageUrl}" class="fl img-width" style="width: 74px;"/>
						<div class="fl pdg" style="width: 74px;">
							${logedInUser.customerName}<br />
							<span class="time">${logedInUser.loginTime}</span>
							<c:if test="${param.status=='INSERVICE' || param.status=='COMPLETED'}">
								<span> assisted by ${logedInUser.processedBy} </span>
							</c:if>
						</div> 
						
					</a>
				</li>
			</c:forEach>
		</ul>
	</json:property>
</json:object>
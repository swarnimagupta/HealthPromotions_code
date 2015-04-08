<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="bnc" tagdir="/WEB-INF/tags/addons/qrcodeaddon/desktop/bnc_csr" %>
<json:object>
	<json:property name="searchby_time" escapeXml="false">
		<div class="srch"><input type="text" value="" placeholder="Search" class="inpt" id="customername" onblur="javascript:searchByCustomerName();"/></div>
		<ul id="slimtest">
			<c:forEach items="${csrCustomerDataList}" var="logedInUser" varStatus="counter">
				<c:set var="currentClass" value=""/>
				<c:if test="${counter.count==1}">
					<c:set var="currentClass" value='class="active"'/>
					<input type="hidden" id="currentUserId" value="${logedInUser.pk}"/>
				</c:if>
				<li>
					<a id="${logedInUser.pk}" onclick="javascript:getCustomerDetails('${logedInUser.pk}');" ${currentClass}>
						<c:set var="imageUrl" value="${logedInUser.profilePictureURL}"/>
						<c:if test="${empty imageUrl}">
							<c:set var="imageUrl" value="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/new/person2.png"/>
						</c:if>
						<img src="${imageUrl}" class="fl img-width"/>
						<div class="fl pdg">
							${logedInUser.customerName}<br/>
							<span class="time">
								<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${logedInUser.loginTime}"/>
							</span>
						</div>
						<br>
						<c:if test="${logedInUser.status=='INSERVICE' || logedInUser.status=='COMPLETED'}">
						<br><br>
							<span class="time">
								${logedInUser.status} assisted by ${logedInUser.processedBy}
							</span>
						</c:if>
					</a>
				</li>
			</c:forEach>
		</ul>
	</json:property>
</json:object>

<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${not empty UCOID}">
	<div>
		<spring:theme code="text.account.order.UCOID" text="UCOID is {0}"
			arguments="${UCOID}" />
		<font size="1" color="red"><b> <spring:theme
					code="text.account.order.secretUCOIDMessage" /></b></font>
	</div>
	<div>
		<font size="1" color="red"> <b> <spring:theme
					code="text.customer.collect.order.completed.status" /></b></font>
	</div>
</c:if>
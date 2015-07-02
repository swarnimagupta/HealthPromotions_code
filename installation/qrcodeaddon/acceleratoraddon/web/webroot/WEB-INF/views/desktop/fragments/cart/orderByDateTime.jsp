<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="bnc"
	tagdir="/WEB-INF/tags/addons/qrcodeaddon/desktop/bnc_csr"%>
<json:object>
	<json:property name="searchby_ucoid" escapeXml="false">
		<div class="srch">
			<input type="text" placeholder="Search " name="q"
				class="inpt" id="ucoid"
				onblur="javascript:doAjaxPost();">
			<ul id="slimtest">
				<c:forEach items="${collectOrderDataByUcoid}" var="CollectOrderData"
					varStatus="counter">
					<c:url value="/orderslist/order/${CollectOrderData.orderId}"
						var="orderDetailsUrl" />
					<c:set var="currentClass" value='' />
					<c:if test="${counter.count==1}">
						<c:set var="currentClass" value='class="current"' />
					</c:if>
					<li><a id="${CollectOrderData.orderId}"
						onclick="javascript:OrderDetailsByOrderID('${CollectOrderData.orderId}');"
						${currentClass}> ${CollectOrderData.orderId}<br /> <span>
								<fmt:formatDate type="time"
									value="${CollectOrderData.createdTS}"
									pattern="MM/dd/yyyy hh:mm aa" /><br />
						</span>
					</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="tab">
			<div class="tab tab-horiz">
				<ul class="tab-legend">

					<li id="orderDetailsTab"><a href="#" class="tabmenuselect">Order
							Details</a></li>
					<li class="divider"></li>
					<li id="personalDetails"><a
						onclick='javascript:PersonalDetailsByUserID("${orderData.user.uid}", "${orderData.code}");'>Personal
							Details</a></li>
				</ul>

				<ul class="tab-content">
					<bnc:orderDetails />
				</ul>
			</div>
		</div>

	</json:property>
</json:object>

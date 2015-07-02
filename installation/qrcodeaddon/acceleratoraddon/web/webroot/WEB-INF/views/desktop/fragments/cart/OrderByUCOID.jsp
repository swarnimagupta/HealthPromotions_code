<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="bnc" tagdir="/WEB-INF/tags/addons/qrcodeaddon/desktop/bnc_csr" %>
<json:object>
	<json:property name="searchby_ucoid" escapeXml="false">
			<div class="srch">
					<input type="text" placeholder="Search " name="q" class="inpt" id="ucoid" onblur="javascript:doAjaxPost();">
			<ul id="slimtest">
						<c:url value="/orderslist/order/${collectOrderDataByUcoid.orderId}" var="orderDetailsUrl"/>
						<li>
							<a id="${collectOrderDataByUcoid.orderId}" onclick="javascript:OrderDetailsByOrderID('${collectOrderDataByUcoid.orderId}');" class="current">
								${collectOrderDataByUcoid.orderId}<br />
								<span>
									<fmt:formatDate type="time" value="${collectOrderDataByUcoid.createdTS}" pattern="MM/dd/yyyy hh:mm aa"/><br />
								</span>
							</a>
						</li>
					</ul>
				</div>
		<div class="tab">
			<div class="tab tab-horiz">
				<ul class="tab-legend">
									
							<li id="orderDetailsTab"><a href="#" class="tabmenuselect">Order Details</a></li>
							<li class="divider"></li>
							<li id="personalDetails"><a onclick='javascript:PersonalDetailsByUserID("${orderData.user.uid}", "${orderData.code}");'>Personal Details</a></li>
						</ul>
					
				  <ul class="tab-content">
			  <bnc:orderDetails />
			  </ul>
			</div>
		</div>
	</json:property>
</json:object>

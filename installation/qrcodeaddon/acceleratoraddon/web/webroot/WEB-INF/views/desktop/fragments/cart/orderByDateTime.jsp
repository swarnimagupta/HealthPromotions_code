<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="bnc"
	tagdir="/WEB-INF/tags/addons/qrcodeaddon/desktop/bnc_csr"%>
<json:object>
	<json:property name="searchby_ucoid" escapeXml="false">
	<script type="text/javascript">
	if('${collectOrderDataByUcoid[0].orderId}'!='')
	{
		OrderDetailsByOrderID('${collectOrderDataByUcoid[0].orderId}');
	}
	else
	{
		alert("Please enter dates in proper format! Dates as MM/dd/YYYY and Time as HH:MM AM/PM!!\n\n FromDate should be before ToDate!!");
		$("#CSROrderDetails").html("");
		$("#personalDetailsContentId").html("");
		$("#personalDetails").html("<a href='#' style='text-decoration:none;'>Personal Details</a>");
	}
	</script>
		<div class="srch"><input type="text" placeholder="Search " name="q" class="inpt" id="ucoid" onblur="javascript:doAjaxPost();"></div>
		<ul id="slimtest">
			<c:forEach items="${collectOrderDataByUcoid}" var="CollectOrderData" varStatus="counter">
				<c:url value="/orderslist/order/${CollectOrderData.orderId}" var="orderDetailsUrl"/>
				<c:set var="activeClass" value=''/>
				<c:if test="${counter.count==1}">
					<c:set var="activeClass" value='class="active"'/>
				</c:if>
				<li>
					<div class="fl">
						<a id="${CollectOrderData.orderId}" onclick="javascript:OrderDetailsByOrderID('${CollectOrderData.orderId}');" ${activeClass}>
							${CollectOrderData.orderId}<br />
							<span class="time">
								<fmt:formatDate type="time" value="${CollectOrderData.createdTS}" pattern="MM/dd/yyyy hh:mm aa"/><br />
							</span>
						</a>
					</div>
				</li>
			</c:forEach>
		</ul>
	
	
	
	
	
	
	
	
	
	
	
<!-- 		<div class="tab"> -->
<!-- 			<div class="tab tab-horiz"> -->
<!-- 				<ul class="tab-legend"> -->

<!-- 					<li id="orderDetailsTab"><a href="#" class="tabmenuselect"  style='text-decoration:none;'>Order -->
<!-- 							Details</a></li> -->
<!-- 					<li class="divider"></li> -->
<!-- 					<li id="personalDetails"><a  style='text-decoration:none;'  -->
<%-- 						onclick='javascript:PersonalDetailsByUserID("${orderData.user.uid}", "${orderData.code}");'>Personal --%>
<!-- 							Details</a></li> -->
<!-- 				</ul> -->

<!-- 				<ul class="tab-content"> -->
<%-- 					<bnc:orderDetails /> --%>
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->

	</json:property>
</json:object>

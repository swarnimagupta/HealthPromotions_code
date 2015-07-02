<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="order_menu">
		<div class="srch"><input type="text" placeholder="Search " name="q" class="inpt" id="ucoid" onblur="javascript:doAjaxPost();"></div>
	<ul id="slimtest">
			<c:forEach items="${collectOrdersDataList}" var="CollectOrderData" varStatus="counter">
			<c:url value="/orderslist/order/${CollectOrderData.orderId}" var="orderDetailsUrl"/>
			<c:set var="activeClass" value=''/>
			<c:if test="${counter.count==1}">
				<c:set var="activeClass" value='class="active"'/>
			</c:if>
			<li>
			<div class="fl pdg">	<a id="${CollectOrderData.orderId}" onclick="javascript:OrderDetailsByOrderID('${CollectOrderData.orderId}');" ${activeClass}>
					${CollectOrderData.orderId}<br />
					<span class="time">
						<fmt:formatDate type="time" value="${CollectOrderData.createdTS}" pattern="MM/dd/yyyy hh:mm aa"/><br />
					</span>
				</a>
				</div>
			</li>
		</c:forEach>
		</ul>
	</div>
	
	
	<%-- ----------------------------------------------------------------
<div id="order_menu">
	<ul id="slimtest">
		<li class="search_padding">
			<input type="text" placeholder="Search " name="q" class="search-text placeholder" id="ucoid" onblur="javascript:doAjaxPost();">
		</li>
		<c:forEach items="${collectOrdersDataList}" var="CollectOrderData" varStatus="counter">
			<c:url value="/orderslist/order/${CollectOrderData.orderId}" var="orderDetailsUrl"/>
			<c:set var="currentClass" value=''/>
			<c:if test="${counter.count==1}">
				<c:set var="currentClass" value='class="current"'/>
			</c:if>
			<li>
				<a id="${CollectOrderData.orderId}" onclick="javascript:OrderDetailsByOrderID('${CollectOrderData.orderId}');" ${currentClass}>
					${CollectOrderData.orderId}<br />
					<span>
						<fmt:formatDate type="time" value="${CollectOrderData.createdTS}" pattern="MM/dd/yyyy hh:mm aa"/><br />
					</span>
				</a>
			</li>
		</c:forEach>
	</ul>
</div>
 --%>
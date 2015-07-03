<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function assistCustomer()
	{
		alert('${param.customerPK}');
		window.location = "${contextPath}/customerlist/statusUpdate?status=InService&customerPK=${param.customerPK}";
	}
	function noThanksUpdate()
	{
		alert('${param.customerPK}');
		window.location = "${contextPath}/customerlist/statusUpdate?status=NoThanks&customerPK=${param.customerPK}";
	}
	function servicedCustomer()
	{
		alert('${param.customerPK}');
		window.location = "${contextPath}/customerlist/statusUpdate?status=Completed&customerPK=${param.customerPK}";
	}
</script>
<div class="tab_menu_block">
	<div class="tab_menu_profile">
		<ul>
			<c:choose>
				<c:when test="${storecustomerData.custStatus=='InService' && not empty storecustomerData.processedBy && storecustomerData.processedBy==CSR_USER}">
					<li class="space"><input name="" class="assist_btn"	type="button" value="Serviced" onclick="javascript:servicedCustomer();"></li>
					<li class="divider space"></li>
				</c:when>
				<c:when test="${storecustomerData.custStatus=='Completed'}">
				</c:when>
				<c:otherwise>
					<li class="space"><input name="" class="assist_btn"	type="button" value="Assist" onclick="javascript:assistCustomer();"></li>
					<li class="divider space"></li>
					<li class="space"><input name="" class="assist_btn" type="button" value="No Thanks" onclick="javascript:noThanksUpdate();"></li>
					<li class="divider space"></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>
<div class="clearboth"></div>
<div class="tab_menu_block">
	<div class="tab_menu">
		<ul>
			<li><a href="#"  style='text-decoration:none;'>Personal Data</a></li>
		</ul>
	</div>
</div>

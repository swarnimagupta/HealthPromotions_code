<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="bnc" tagdir="/WEB-INF/tags/addons/qrcodeaddon/desktop/bnc_csr" %>
<!DOCTYPE html>
<html>
	<head>
	<script  src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/datepicker.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/library.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/tabModule.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/jquery.tinycarousel.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/slideshow.min.js"></script>
	<script type="text/javascript">
	function loadOrderDetails(orderCode)
	{
		$.ajax({
			type : 'GET',
			url : "${contextPath}/customerlist/order/orderCode",
			data: "orderCode="+orderCode,
			dataType : 'json',
			success : function(response) {
			$("#orderDetails_"+orderCode).html(response.customer_dashboard_order_details_for_accordion);
			},
			error : function(e) {
			}
		});
	}
	function getCustomerDetails(customerPK)
	{
		$.ajax({
			type : 'GET',
			url : "${contextPath}/customerlist/assistcustomer",
			data : "customerPK="+customerPK,
			dataType : 'json',
			success : function(response) {
				$("a").removeClass("active");
				$("#"+customerPK).addClass("active");
				$("#customer_details_block").html(response.customer_details);
			},
			error : function(e) {
				alert("No customer has logged in yet");
			}
		});
	}
	
	function searchByCustomerName() {
		
		var customername = document.getElementById('customername').value;
		 if (document.getElementById('customername').value =='' ) { 
		alert("Please enter the customername!");
			document.getElementById('customername').focus();
			return false;
		}
		$.ajax({
			type : 'GET',
			url : "${contextPath}/customerlist/customerName",
			data : "customername=" + customername,
			dataType : 'json',
			success : function(response) {
				$("#customer_list_block").html(response.searchby_customername);
				if(document.getElementById("currentUserId")!=null)
				{
					getCustomerDetails(document.getElementById("currentUserId").value);
					setTimeout(function () {$("#accordion").accordion();}, 3000);
				} 
			},
			error : function(e) {
				alert("Please enter correct customername");
			}
		});
	}
	
	function getCustomersByFromDate()
	{
		
		
		var fdate = document.getElementById('datepicker').value;
		var tdate = document.getElementById('datepicker1').value;
		var ftime = document.getElementById('searchTimeBarFromTime').value;
		var ttime = document.getElementById('searchTimeBarToTime').value;
		if (fdate =='' ) 
		{ 
			alert("Please enter the from Date!");
			document.getElementById('datepicker').focus();
			return false;
		}
		if (tdate =='' ) 
		{ 
			alert("Please enter the to Date!");
			document.getElementById('datepicker1').focus();
			return false;
		}
		if (ftime =='' ) 
		{ 
			alert("Please enter the from Time!");
			document.getElementById('searchTimeBarFromTime').focus();
			return false;
		}
		if (ttime =='' ) 
		{ 
			alert("Please enter the to Time!");
			document.getElementById('searchTimeBarToTime').focus();
			return false;
		}
		$.ajax({
			type : 'GET',
			url : "${contextPath}/customerlist/datetime",
			data : "fdate=" + fdate+"&tdate="+tdate+"&ftime="+ftime+"&ttime="+ttime,
			dataType : 'json',
			success : function(response) {
				$("#customer_list_block").html(response.searchby_time);
				if(document.getElementById("currentUserId")!=null)
				{
					getCustomerDetails(document.getElementById("currentUserId").value);
					setTimeout(function () {$("#accordion").accordion();}, 5000);
				}
				else
				{
					$("#customer_details_block").html("<p style='color:red;'>Please enter dates in proper format! Dates as DD.MM.YYYY and Time as HH:MM AM/PM!!\n\n FromDate should be before ToDate!! OR No customers logged in for the given dates!!</p>");
				}
			},
			error : function(e) {
				alert("Please enter dates in proper format! Dates as DD.MM.YYYY and Time as HH:MM AM/PM!!\n\n FromDate should be before ToDate!!");
			}
		});
	}
	
	$(document).ready(function() {
		setTimeout(function () {window.location.href="${contextPath}/customerlist/customerdeatils?size="+'${Queued}'+"&status="+'${param.status}';}, 30000);
	});
	</script>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
	<title>Customers Dashboard</title>
	<link type="text/css" rel="stylesheet" href="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_css/style_new.css" />
	</head>
	<body>
		<bnc:csr_customers_header/>
		<div class="clearboth"></div>
		<!--Content Starts here-->
		<div class="cnt">
			<!--Left Menu Area Starts here-->
			<div class="cntl" id="customer_list_block">
				<bnc:customerslist/>
			</div>
			<!--Left Menu Area Ends here-->
			<div class="cntr">
				<bnc:cust_chart/>
				<bnc:cust_dateTime/>
				<div id="customer_details_block">
					<!-- Customer Details will go here -->
				</div>
			</div>
		</div>
		<!--Content Ends here-->
		<!--Footer Area Starts here-->
  		<div class="ftr"> © Accenture 2015, All Rights Reserved.</div>
  		<!--Footer Area Ends here-->

		<!-- Script includes -->
		<script src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/launcher.js"></script>
		<c:if test="${not empty param.size && Queued!=param.size}">
			<script type="text/javascript">
				var audio = {};
				audio["walk"] = new Audio();
				audio["walk"].src = '${commonResourcePath}'+"/../../addons/qrcodeaddon/desktop/common/bnc_audio/bellring01.mp3"			
				audio["walk"].play();
				document.getElementById("bell_number").innerHTML = ${Queued};
			</script>
		</c:if>
	</body>
</html>
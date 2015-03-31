<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="bnc" tagdir="/WEB-INF/tags/addons/qrcodeaddon/desktop/bnc_csr" %>
<!DOCTYPE html>
<html>
	<head>
	<link type="text/css" rel="stylesheet" href="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_css/style_new.css" />
	<script  type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/library.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/datepicker.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/tabModule.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/jquery.tinycarousel.min.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/dependencies.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/modernizr.js"></script>
	<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/pizza.js"></script>
	<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
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
					setTimeout(function () {$("#accordion").accordion({
						  active: false,
						  collapsible: true,});}, 5000);
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
					setTimeout(function () {$("#accordion").accordion({
						  active: false,
						  collapsible: true,});}, 5000);
				}
				else
				{
					$("#customer_details_block").html("<p style='color:red;'>Please enter dates in proper format! Dates as MM/dd/YYYY and Time as HH:MM AM/PM!!\n\n FromDate should be before ToDate!! OR No customers logged in for the given dates!!</p>");
				}
			},
			error : function(e) {
				alert("Please enter dates in proper format! Dates as DD.MM.YYYY and Time as HH:MM AM/PM!!\n\n FromDate should be before ToDate!!");
			}
		});
	}
	
	$(document).ready(function() {
		setInterval(function () {
			$.ajax({
				type : 'GET',
				url : "${contextPath}/customerlist/customerdetails",
				data : "size="+'${Queued}'+"&status=LOGGEDIN",
				dataType : 'json',
				success : function(response) {
					if('${param.status}'=='LOGGEDIN' || '${param.status}'=='')
					{
						$("#customer_list_block").html(response.customerslist);
						var customerId = document.getElementById("currentUserId");
						if(customerId!=null && '${Queued}'==0)
						{
							getCustomerDetails(customerId.value);
						}
					}
					$.ajax({
						type : 'GET',
						url : "${contextPath}/customerlist/getChartFragment",
						data : "status="+'${param.status}',
						dataType : 'json',
						success : function(response) {
							$("#chartsRefresh").html(response.chartsRefresh);
							$(".dial").knob({
								readOnly: true,
								fgColor: "#465bdc",
								bgColor: "#C9C8C8",
								thickness: 0.20
								});
						}
					});
				}
			});
			}, 30000);
	});
	</script>
	<script src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/jquery.knob.js"></script>
	<style>
	.circle_progress_bar{font:normal 20px Arial!important; text-align: center; color:#626262!important; }
	</style>
	<script type="text/javascript">
		$(window).load(function(){
		$(".dial").knob({
			readOnly: true,
			fgColor: "#465bdc",
			bgColor: "#C9C8C8",
			thickness: 0.20
						});
	});
	
	</script>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
	<title>Customers Dashboard</title>
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
  		<div class="ftr"> <div class="ftri">© Accenture 2015, All Rights Reserved.</div></div>
  		<!--Footer Area Ends here-->

		<!-- Script includes -->
		<c:if test="${not empty param.size && Queued!=param.size}">
			<script type="text/javascript">
				var audio = {};
				audio["walk"] = new Audio();
				audio["walk"].src = '${commonResourcePath}'+"/../../addons/qrcodeaddon/desktop/common/bnc_audio/bellring01.mp3"			
				audio["walk"].play();
				document.getElementById("bell_number").innerHTML = ${Queued};
			</script>
		</c:if>
		<script src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/jquery.diagram.js"></script> 
	</body>
</html>
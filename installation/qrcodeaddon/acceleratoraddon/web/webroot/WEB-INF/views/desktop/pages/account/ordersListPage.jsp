<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="bnc" tagdir="/WEB-INF/tags/addons/qrcodeaddon/desktop/bnc_csr" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="${currentLanguage.isocode}">
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
		<link type="text/css" rel="stylesheet" href="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_css/style_new.css" />
		<script type="text/javascript">
	
		$(document).ready(function() {
			
			setTimeout(function () {
			
			window.location.href="${contextPath}/orderslist/vieworders?size="+'${Queued}'+"&status="+'${param.status}';}, 90000);
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
		<title>Order Dashboard</title>
	</head>
	<body>
	
		<!--Header Starts here-->
		<bnc:csr_orders_header/>
		<!-- Header Ends here-->
		<div class="clearboth"></div>
		<!--Content Starts here-->
 		<div class="cnt">
 			<div class="cntl" id="ordersDivId">
				<bnc:ordersList />
			</div>
			<div class="cntr">
				<bnc:chart />
				<bnc:dateTime />


			
			<div class="tab">
				<div class="tab tab-horiz">
					<ul class="tab-legend">
						<li id="orderDetailsTab" class="active">
							<a href="#"	class="tabmenuselect" style='text-decoration:none;color: #2d3a9a;'>Order Details</a>
						</li>
						<li id="personalDetails">
							<a href='#' style='text-decoration:none;color: #fff;' onclick='javascript:PersonalDetailsByUserID("${orderData.user.uid}", "${orderData.code}");'>Personal Details</a>
						</li>
					</ul>
					<ul class="tab-content">
						<c:if test="${not empty orderData.code}">
							<li id="CSROrderDetails">
							<bnc:orderDetails />
							</li>
							<li id="personalDetailsContentId">
							</li>
						</c:if>
					</ul>
				</div>
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
		<bnc:csr_script />
	</body>
</html>
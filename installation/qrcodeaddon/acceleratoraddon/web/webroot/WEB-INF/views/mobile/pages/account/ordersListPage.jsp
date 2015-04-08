<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="bnc" tagdir="/WEB-INF/tags/addons/qrcodeaddon/mobile/bnc_csr" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/mobile/product" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/mobile/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="${currentLanguage.isocode}">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
		<title>Orders Dashboard</title>
		<link type="text/css" rel="stylesheet" href="${commonResourcePath}/../../addons/qrcodeaddon/mobile/common/bnc_css/style.css" />
		<script type="text/javascript">
		$(document).ready(function() {
		setTimeout(function () {
			
			window.location.href="${contextPath}/orderslist/vieworders?size="+'${Queued}'+"&status="+'${param.status}';}, 10000);
		});
		</script> 
		<script src="${commonResourcePath}/../../addons/qrcodeaddon/mobile/common/bnc_js/jquery.knob.js"></script>
		<style>
		.circle_progress_bar{font:normal 20px Arial!important; text-align: center; color:#626262!important; }
		</style>
		<script type="text/javascript">
			$(window).load(function(){
			$(".dial").knob({
				readOnly: true,
				fgColor: "#13ccde",
				bgColor: "#efefef",
				thickness: 0.20
							});
		});
		
		</script>
	</head>
	<body>
		<div id="main_wrapper" style="position:relative"> 
			<!--Header Starts here-->
			<bnc:csr_orders_header/>
			<!-- Header Ends here-->
			<div class="clearboth"></div>
			<!--Content Starts here-->
			<div id="main_content_blk">
				<div class="top_banner">
					<bnc:chart />
					<div class="clearboth"></div>
					<bnc:dateTime />
				</div>
				<div class="clearboth"></div>
				<div class="inner_content_blk" id="ordersDivId">
					<div class="left_block">
						<bnc:ordersList />
					</div>
					<div class="right_block">
						<div class="tab_menu_block">
							<div class="tab_menu">
								<ul>
									<li id="orderDetailsTab"><a href="#" class="tabmenuselect">Order Details</a></li>
									<li class="divider"></li>
									<li id="personalDetails"><a onclick='javascript:PersonalDetailsByUserID("${orderData.user.uid}", "${orderData.code}");'>Personal Details</a></li>
								</ul>
							</div>
						  <div class="tab_button"></div>
						</div>
					<c:if test="${not empty orderData.code}">
					  <bnc:orderDetails />
					  </c:if>
					</div>
					<div class="clearboth"></div>
				</div>
			</div>
			<!--Content Ends here--> 
		</div>
		<c:if test="${not empty param.size && Queued!=param.size}">
			<script type="text/javascript">
				var audio = {};
				audio["walk"] = new Audio();
				audio["walk"].src = '${commonResourcePath}'+"/../../addons/qrcodeaddon/mobile/common/bnc_audio/bellring01.mp3"			
				audio["walk"].play();
				document.getElementById("bell_number").innerHTML = ${Queued};
			</script>
		</c:if>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
		<script src="${commonResourcePath}/../../addons/qrcodeaddon/mobile/common/bnc_js/jquery.diagram.js"></script> 
		<script src="${commonResourcePath}/../../addons/qrcodeaddon/mobile/common/bnc_js/script.js"></script>
		<bnc:csr_script />
	</body>
</html>
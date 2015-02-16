<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script	src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/datepicker.js"></script>
<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/library.js"></script>
<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/tabModule.js"></script>
<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/jquery.tinycarousel.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/slideshow.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#accordion").accordion();
	});
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

<div class="tab">
	<div class="tab tab-horiz">
		<ul class="tab-legend">
			<li class="active">Customer Details</li>
			<li>Orders Details</li>
		</ul>
		<ul class="tab-content">
			<li>
				<!--TAB CONTENT-->
				<div class="slmscr">
					<div class="oh pdg">
						<div class="oh">
							<c:set var="imageUrl"
								value="${storecustomerData.profilePictureURL}" />
							<c:if test="${empty storecustomerData.profilePictureURL}">
								<c:set var="imageUrl"
									value="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/personal_photos/person1.jpg" />
							</c:if>
							<img src="${imageUrl}" class="fl img-width" /> <span class="tabt">${storecustomerData.customerName}</span>
						</div>

						<div class="oh pdtb20">
							<span class="fl lftt">Date of Birth:</span> <span class="fl rgtt">${informationDto.dob}</span>
						</div>

						<div class="oh pdtb20">
							<span class="fl lftt">Address:</span> <span class="fl rgtt">${informationDto.line1}&nbsp;${informationDto.line2}&nbsp;${informationDto.apartment}&nbsp;${informationDto.postalCode}</span>
						</div>

						<div class="oh pdtb20">
							<span class="fl lftt">UID:</span> <span class="fl rgtt">${storecustomerData.customerId}
							</span>
						</div>
						<div class="oh pdtb20">
							<c:choose>
								<c:when test="${storecustomerData.custStatus=='InService' && not empty storecustomerData.processedBy && storecustomerData.processedBy==CSR_USER}">
									<button id="btn1" class="assbt" onclick="javascript:servicedCustomer();">Serviced</button>
								</c:when>
								<c:when test="${storecustomerData.custStatus=='Completed'}">
								</c:when>
								<c:otherwise>
									<button id="btn2" class="assbt" onclick="javascript:assistCustomer();">Assist</button>&nbsp;&nbsp;
									<button id="btn3" class="assbt" onclick="javascript:noThanksUpdate();">No Thanks</button>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="slmscr1">
					<c:if test="${not empty customerOrderDataList}">
						<div id="accordion">
							<c:forEach items="${customerOrderDataList}" var="order" begin="0"
								end="0">
								<h3 onclick="javascript:loadOrderDetails('${order.orderCode}');">
									<span
										style="font: normal 16px/20px Arial, Helvetica, sans-serif; color: #6f6f6f;">${order.orderCode}&nbsp;&nbsp;&nbsp;&nbsp;Total:${order.total}&nbsp;&nbsp;&nbsp;&nbsp;Placed
										on ${order.placedDate}</span>
								</h3>
								<div id="orderDetails_${order.orderCode}">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										class="orderitemtabel">
										<c:forEach items="${orderData.entries}" var="entry">
											<c:url value="${entry.product.url}" var="productUrl" />
											<tr>
												<td><table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td width="8%"><product:productPrimaryImage
																	product="${entry.product}" format="thumbnail" /></td>
															<td width="2%"></td>
															<td width="25%">${entry.product.name}</td>
															<td width="12%">${entry.quantity}</td>
															<td width="11%"><format:price
																	priceData="${entry.basePrice}"
																	displayFreeForZero="true" /></td>
															<td width="21%">$${entry.quantity *
																entry.basePrice.value}</td>
															<td width="3%">&nbsp;</td>
														</tr>
													</table></td>
											</tr>
											<tr>
												<td height="10"></td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</c:forEach>
							<c:forEach items="${customerOrderDataList}" var="order" begin="1">
								<h3 onclick="javascript:loadOrderDetails('${order.orderCode}');">
									<span
										style="font: normal 16px/20px Arial, Helvetica, sans-serif; color: #6f6f6f;">${order.orderCode}&nbsp;&nbsp;&nbsp;&nbsp;Total:${order.total}&nbsp;&nbsp;&nbsp;&nbsp;Placed
										on ${order.placedDate}</span>
								</h3>
								<div id="orderDetails_${order.orderCode}"></div>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</li>

		</ul>
	</div>
</div>

<div class="wshltc">
	<div id="slideshow" class="disabled">
		<button class="previous" id="prevSlideShow">
			<b>« Previous</b>
		</button>
		<button class="next" id="nextSlideShow">
			<b>Next »</b>
		</button>

		<div class="strip">
			<div class="slide sticky">Wish List</div>
			<div class="slide">Recently View</div>
			<div class="slide">Recommendations</div>
		</div>
		<div id="slider1"> 
			<a class="buttons prev" href="#">left</a>
			<div class="viewport">
				<div id="wishlistProducts" style="display:block;">
					<ul class="overview">
						<c:if test="${not empty wishlist.entries}">
							<c:forEach items="${wishlist.entries}" var="wishlist">
								<li>
									<product:productPrimaryImage product="${wishlist.product}" format="thumbnail" />
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<div id="recentlyViewedProducts" style="display:none;">
					<ul class="overview">
						<c:forEach items="${productData}" var="product">
							<c:url value="${product.url}" var="productQuickViewUrl" />
							<li>
								<product:productPrimaryImage product="${product}" format="thumbnail" />
							</li>
						</c:forEach>
					</ul>
				</div>
				<div id="recommendedProducts" style="display:none;">
					<ul class="overview">
						<c:forEach items="${recommendedProductsData}" var="product">
							<c:url value="${product.url}" var="productQuickViewUrl" />
							<li>
								<product:productPrimaryImage product="${product}" format="thumbnail" />
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<a class="buttons next" href="#">right</a>
		</div>
	</div>
</div>
<script src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/launcher.js"></script>
<script type="text/javascript">
	$("#nextSlideShow").click(
		function()
		{
			if($(".sticky").html()=='Wish List')
			{
				$("#wishlistProducts").css('display','none');
				$("#recentlyViewedProducts").css('display','block');
				$("#recommendedProducts").css('display','none');
			}
			if($(".sticky").html()=='Recently View')
			{
				$("#wishlistProducts").css('display','none');
				$("#recentlyViewedProducts").css('display','none');
				$("#recommendedProducts").css('display','block');
			}
		}
	);
	$("#prevSlideShow").click(
		function()
		{
			if($(".sticky").html()=='Recently View')
			{
				$("#wishlistProducts").css('display','block');
				$("#recentlyViewedProducts").css('display','none');
				$("#recommendedProducts").css('display','none');
			}
			if($(".sticky").html()=='Recommendations')
			{
				$("#wishlistProducts").css('display','none');
				$("#recentlyViewedProducts").css('display','block');
				$("#recommendedProducts").css('display','none');
			}
		}
	);
</script>
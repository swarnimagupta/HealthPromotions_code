<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- <script type="text/javascript" src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_js/new/library.js"></script> --%>

<script type="text/javascript">
	$(document).ready(function() {
		tabModule.init();	
		$("#slider1").tinycarousel();
		$("#slider2").tinycarousel();
		$("#slider3").tinycarousel();
		 $(".slmscr").slimScroll({railVisible: true, railColor: '#f00'});
		 $(".slmscr1").slimScroll({railVisible: true, railColor: '#f00'});
		$("#accordion").accordion({
			active: false,
		    collapsible: true
		   
		});
	});
	function assistCustomer()
	{
		window.location = "${contextPath}/customerlist/statusUpdate?status=InService&customerPK=${param.customerPK}";
	}
	function noThanksUpdate()
	{
		window.location = "${contextPath}/customerlist/statusUpdate?status=NoThanks&customerPK=${param.customerPK}";
	}
	function servicedCustomer()
	{
		window.location = "${contextPath}/customerlist/statusUpdate?status=Completed&customerPK=${param.customerPK}";
	}
</script>

<div class="tab">
	<div class="tab tab-horiz">
		<ul class="tab-legend">
			<li class="active">Customer Details</li>
			<li>Orders Details</li>
			<li>History</li>
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
						<%-- <div class="oh pdtb20">
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
						</div> --%>
					</div>
				</div>
			</li>
			<li>
				<div class="slmscr1">
					<c:if test="${not empty customerOrderDataList}">
						<div id="accordion"  style="position: relative; clear:both; display:-moz-groupbox;margin-top: -95px; margin-left: -258px; top: 13%; left: 42%;">
							<c:forEach items="${customerOrderDataList}" var="order" begin="0">
								<h3 onclick="javascript:loadOrderDetails('${order.orderCode}');">
									<span
										style="font: normal 15px/20px Helvetica, sans-serif; color: #181818;text-align: left;"><b>Order #</b>${order.orderCode}&emsp;&emsp;<b>Total</b> - &#36; ${order.total}&emsp;&emsp;<b>Date Placed</b>  ${order.placedDate}</span>
								</h3>
								<div id="orderDetails_${order.orderCode}" style="background-color:#FFFFFF"></div>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</li>

			<li>
				<div class="slmscr1">
					<c:if test="${not empty wishlist.entries}">
						<div class="tabt1">Wish List</div>
						<div id="slider1">
							<a class="buttons prev" href="javascript:void(0);">left</a>
							<div class="viewport">
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
							<a class="buttons next" href="javascript:void(0);">right</a>
						</div>
					</c:if>
					<c:if test="${not empty productData}">
						<div class="tabt1 pt20">Recently Viewed</div>
						<div id="slider2">
							<a class="buttons prev" href="javascript:void(0);">left</a>
							<div class="viewport">
								<ul class="overview">
									<c:forEach items="${productData}" var="product">
										<c:url value="${product.url}" var="productQuickViewUrl" />
										<li>
											<product:productPrimaryImage product="${product}" format="thumbnail" />
										</li>
									</c:forEach>
								</ul>
							</div>
							<a class="buttons next" href="javascript:void(0);">right</a>
						</div>
					</c:if>
					<c:if test="${not empty recommendedProductsData}">
						<div class="tabt1 pt20">Recommendations</div>
						<div id="slider3">
							<a class="buttons prev" href="javascript:void(0);">left</a>
							<div class="viewport">
								<ul class="overview">
									<c:forEach items="${recommendedProductsData}" var="product">
										<c:url value="${product.url}" var="productQuickViewUrl" />
										<li>
											<product:productPrimaryImage product="${product}" format="thumbnail" />
										</li>
									</c:forEach>
								</ul>
							</div>
							<a class="buttons next" href="javascript:void(0);">right</a>
						</div>
					</c:if>
					
				</div>
			</li>
		</ul>
	</div>
</div>

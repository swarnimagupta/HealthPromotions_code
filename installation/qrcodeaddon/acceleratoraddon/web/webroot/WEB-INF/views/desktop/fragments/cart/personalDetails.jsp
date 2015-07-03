<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="g" uri="http://granule.com/tags/accelerator" %>
<%@ taglib prefix="compressible" tagdir="/WEB-INF/tags/desktop/template/compressible" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<json:object>
	<json:property name="Personal_Details" escapeXml="false">
		<script type="text/javascript">
		  document.getElementById("orderDetailsTab").innerHTML = 
			  "<a onclick='javascript:OrderDetailsByOrderID(\"${orderCode}\");' style='text-decoration:none;color: #fff;'>Order Details</a>"; 
			  document.getElementById("personalDetails").innerHTML = 
				  "<a href='#' class='tabmenuselect' style='text-decoration:none;color: #2d3a9a;'>Personal Details</a>"; 
				  
				  $(document).ready(function() {
						tabModule.init();	
						$("#slider1").tinycarousel();
						$("#slider2").tinycarousel();
						$("#slider3").tinycarousel();
						 $(".slmscr").slimScroll({railVisible: true, railColor: '#f00'});
						 $(".slmscr1").slimScroll({railVisible: true, railColor: '#f00'});
						 $(".slmscr2").slimScroll({railVisible: true, railColor: '#f00'});
						$("#accordion").accordion({
							active: false,
						    collapsible: true
						   
						});
						$("#mi").hover(function() {
							$(".mip").show();
							$(".mip").css("display", "block");
						});
						$("#mi").mouseleave(function() {
							$(".mip").hide();
						});
						
					});
		  </script>
				<div class="slmscr1">
					<div class="oh pdg">
						<div class="oh">
						<c:set var="imageUrl" value="${storeCustomerData.profilePictureURL}"/>
						<c:if test="${empty storeCustomerData.profilePictureURL}">
								<c:set var="imageUrl" value="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/personal_photos/person1.jpg"/>
						</c:if>
						<img src="${imageUrl}" class="fl"/> <span class="tabt">${storeCustomerData.customerName}</span></div>
						<div class="oh pdtb20">
							<span class="fl lftt">Date of Birth:</span>
							<span class="fl rgtt">${useraddress.dateOfBirth}</span>
						</div>
						
						<div class="oh pdtb20">
							<span class="fl lftt">Address:</span>
							<span class="fl rgtt">${useraddress.line1} <br/>${useraddress.line2} <br/> ${useraddress.town}<br/> ${useraddress.country.name} </span>
						</div>
						
						<div class="oh pdtb20">
							<span class="fl lftt">Postal Code:</span>
							<span class="fl rgtt">${useraddress.postalcode}</span>
						</div>
						<div class="oh pdtb20">
							<span class="fl lftt">Email:</span>
							<span class="fl rgtt">${useraddress.email}</span>
						</div>
						<div class="oh pdtb20">
							<span class="fl lftt">Phone Number:</span>
							<span class="fl rgtt">${customerModel.phoneNumbers} </span>
						</div>
					</div>
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
				
       <%--  
 ----------------------------------------------------------------------------------------------------				
       
       <!--------Personal Details Tabel Starts Here-------->
        <div class="personal_details_tabel">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="43%">
              		<c:set var="imageUrl" value="${storeCustomerData.profilePictureURL}"/>
						<c:if test="${empty storeCustomerData.profilePictureURL}">
							<c:set var="imageUrl" value="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/personal_photos/person1.jpg"/>
						</c:if>
              		<img src="${imageUrl}" alt=""/></td>
              <td width="57%">&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2" height="10"></td>
            </tr>
            <tr>
              <td class="bluetext">${storeCustomerData.customerName}</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                      <td width="43%" class="graytext">UID</td>
                     <!--  <td width="57%" class="graytext">Date Of Birth</td> -->
                    </tr>
                    <tr>
                      <td class="bluetext">${customerModel.uid}</td>
                      <td class="bluetext">${dob}</td>
                    </tr>
                </table></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                      <td width="43%" class="graytext">Address</td>
                      <td width="57%" class="graytext">Postal Code</td> 
                    </tr>
                    <tr>
                     <td class="bluetext"> ${useraddress.line1} <br/>${useraddress.line2} <br/> ${useraddress.town}<br/> ${useraddress.country.name} </td>				   
                     <td class="bluetext">${useraddress.postalcode}</td>
                    </tr>
                </table></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                   <tr>
                      <td width="43%" class="graytext">Email</td>
                      <td width="57%" class="graytext">Phone Number</td>
                    </tr>
                    <tr>
                      <td class="bluetext">${useraddress.email}</td>
                      <td class="bluetext">${customerModel.phoneNumbers}</td>
                    </tr>
                </table></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td colspan="2" class="grayheading">Shopping Trend</td>
                  </tr>
                  <tr>
                    <td width="16%">&nbsp;</td>
                    <td width="84%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td class="smallgraytxt">Electronics</td>
                    <td><div class="meter orange nostripes"> <span style="width: 160px; height:6px"></span> </div></td>
                  </tr>
                  <tr>
                    <td colspan="2" height="10"></td>
                  </tr>
                  <tr>
                    <td class="smallgraytxt">Groceries</td>
                    <td><div class="meter orange nostripes"> <span style="width: 250px; height:6px"></span> </div></td>
                  </tr>
                  <tr>
                    <td colspan="2" height="10"></td>
                  </tr>
                  <tr>
                    <td class="smallgraytxt">Stationary</td>
                    <td><div class="meter orange nostripes"> <span style="width: 40px; height:6px"></span> </div></td>
                  </tr>
                  <tr>
                    <td colspan="2" height="10"></td>
                  </tr>
                  <tr>
                    <td class="smallgraytxt">Home Decor</td>
                    <td><div class="meter orange nostripes"> <span style="width: 140px; height:6px"></span> </div></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                </table></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <c:if test="${not empty productData}">
            <tr>
              <td colspan="2" class="grayheading">Recently Viewed</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td colspan="2">
				<div id="caurosel_block">
					<div class="left_arrow">
						<a href="#">
							<img src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/arrow_left.png" alt="" border="0"/>
						</a>
					</div>
					<div class="caurosel_center_block">
						<c:forEach items="${productData}" var="product">
							<c:url value="${product.url}" var="productQuickViewUrl" />
							<div class="caurosel_widget">
							  <div class="caurosel_img">
									<div class="thumb">
										<product:productPrimaryImage product="${product}" format="thumbnail"/>
									</div>
							  </div>
							  <div class="caurosel_text">${fn:substring(product.name,0,20)}<br>
							    <format:fromPrice priceData="${product.price}"/>
							   </div>
							</div>
						</c:forEach>
					</div>
					<div class="right_arrow"><a href="#"><img src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/arrow_right.png" alt=""  border="0"/></a></div>
				 </div>
               </td>
            </tr>
            </c:if>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <c:if test="${not empty wishlist.entries}">
            <tr>
              <td colspan="2" class="grayheading">Wishlist</td>
            </tr>
           <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            
             <tr>
              
              <td colspan="2"><div id="caurosel_block">
             
                  <div class="left_arrow"><a href="#"><img src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/arrow_left.png" alt="" border="0"/></a></div>
                  <div class="caurosel_center_block">
                  	<c:forEach items="${wishlist.entries}" var="wishlist">
						<div class="caurosel_widget">
	                      <div class="caurosel_img">
								<div class="thumb">
									<product:productPrimaryImage product="${wishlist.product}" format="thumbnail"/>
								</div>
							</div>
	                      <div class="caurosel_text">${fn:substring(wishlist.product.name,0,20)}<br>
	                        <format:fromPrice priceData="${wishlist.product.price}"/></div>
	                    </div>
					</c:forEach>
                  </div>
                  <div class="right_arrow"><a href="#"><img src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/arrow_right.png" alt=""  border="0"/></a></div>
                 
                </div>
                </td>
                
            </tr>
             </c:if>
             <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
             <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <c:if test="${not empty recommendedProductsData}">
	            <tr>
	              <td colspan="2" class="grayheading">Recommended Products</td>
	            </tr>
	            <tr>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
	            <tr>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
	            <tr>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
	            <tr>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
	            <tr>
	              <td colspan="2">
					<div id="caurosel_block">
						<div class="left_arrow">
							<a href="#">
								<img src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/arrow_left.png" alt="" border="0"/>
							</a>
						</div>
						<div class="caurosel_center_block">
							<c:forEach items="${recommendedProductsData}" var="product">
								<c:url value="${product.url}" var="productQuickViewUrl" />
								<div class="caurosel_widget">
								  <div class="caurosel_img">
										<div class="thumb">
											<product:productPrimaryImage product="${product}" format="thumbnail"/>
										</div>
								  </div>
								  <div class="caurosel_text">${fn:substring(product.name,0,20)}<br>
								    <format:fromPrice priceData="${product.price}"/>
								   </div>
								</div>
							</c:forEach>
						</div>
						<div class="right_arrow"><a href="#"><img src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/arrow_right.png" alt=""  border="0"/></a></div>
					 </div>
	               </td>
	            </tr>
            </c:if>
          </table>
        </div> --%>
        <!--------Personal Details Tabel Starts Here--------> 
  <!--Content Ends here--> 
	</json:property>
	</json:object>
	  <%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="breadcrumb"
	tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<json:object>
	<json:property name="CSROrder_Details" escapeXml="false">
	  <script type="text/javascript">
	  	document.getElementById("personalDetails").innerHTML = 
		  "<a style='text-decoration:none;color: #fff;' onclick='javascript:PersonalDetailsByUserID(\"${orderData.user.uid}\", \"${orderData.code}\");'>Personal Details</a>"; 
		  document.getElementById("orderDetailsTab").innerHTML = 
			  "<a style='text-decoration:none;color: #2d3a9a;' href='#' class='tabmenuselect'>Order Details</a>";
	
	  </script>
 		<!--------Order Details Tabel Starts Here-------->
<div class="slmscr"><table width="100%" cellspacing="0" cellpadding="0" border="0">
 <tbody><tr>
 <td><table width="100%" cellspacing="0" cellpadding="0" border="0">
                    <tbody><tr>
                      <td width="62%" class="order_number"><strong class="st">${orderData.code}</strong></td>
                      <td width="27%"><span style="float:left">Status:</span>
                        <div class="styled-select">
                           <c:url value="/orderslist/order/${orderData.code}" var="orderDetailsURL"></c:url>
						<form:form action="${orderDetailsURL}" method="post" commandName="collectOrderData">
							<form:hidden path="pk"/>
							<form:select path="status" onchange="javascript:collectOrderData.submit();">
								<c:forEach items="${collectOrderStatusList}" var="stat">
									<form:option value="${stat}">${stat}</form:option>
								</c:forEach>
							</form:select>
						</form:form>
                        </div></td>
                      <td width="11%">&nbsp;</td>
                    </tr>
                    <tr>
                      <td colspan="3">&nbsp;</td>
                    </tr>
                  </tbody></table></td>
              </tr>
              <tr>
                <td><table width="100%" cellspacing="0" cellpadding="0" border="0" class="orderitemtable">
                	<c:forEach items="${orderData.entries}" var="entry">
								<c:url value="${entry.product.url}" var="productUrl" />
                    <tbody><tr>
                      <td class="odd"><table width="100%" cellspacing="0" cellpadding="0" border="0">
                          <tbody><tr>
                            <td width="19%"><product:productPrimaryImage	product="${entry.product}" format="thumbnail" /></td>
                            <td width="2%"></td>
                            <td width="32%">${entry.product.name}</td>
                            <td width="12%">${entry.quantity}</td>
                            <td width="11%"><format:price priceData="${entry.basePrice}"	displayFreeForZero="true" /></td>
                            <td width="21%">$${entry.quantity * entry.basePrice.value}</td>
                          </tr>
                        </tbody></table></td>
                    </tr>
                  </tbody>
                  </c:forEach>
                  </table></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td class="order_number"><table width="100%" cellspacing="0" cellpadding="0" border="0">
                    <tbody><tr>
                      <td width="9%"><strong class="st">Total</strong></td>
                      <td width="91%"><strong class="st">$${orderData.totalPrice.value}</strong></td>
                    </tr>
                  </tbody></table></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
            </tbody></table>
              </div>
          <!--------Order Details Tabel Ends Here-------->      
	</json:property>
</json:object>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
			<c:when test="${not empty orderData.code}">
<!--TAB CONTENT-->
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
            </c:when>
			<c:otherwise>
				<div class="order_details_tabel" style="color:red;">
					<c:out value="Please enter dates in proper format! Dates as MM/dd/YYYY and Time as HH:MM AM/PM!! FromDate should be before ToDate!!"></c:out>
				</div>
				
			</c:otherwise>
		</c:choose>
	
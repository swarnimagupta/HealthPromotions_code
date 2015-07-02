<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="chart_block" style="text-align: center;margin-bottom: 20px;margin-top: 12px;font-size: 13px" id="chartsRefresh">
	<c:choose>
		<c:when test="${param.status=='COMPLETED'}">
			<c:set var="queuedCurrent" value=""/>
			<c:set var="activeCurrent" value=" diagramcurrent"/>
			<c:set var="servicedCurrent" value=""/>
		</c:when>
		<c:when test="${param.status=='COLLECTED'}">
			<c:set var="queuedCurrent" value=""/>
			<c:set var="activeCurrent" value=""/>
			<c:set var="servicedCurrent" value=" diagramcurrent"/>
		</c:when>
		<c:otherwise>
			<c:set var="queuedCurrent" value=" diagramcurrent"/>
			<c:set var="activeCurrent" value=""/>
			<c:set var="servicedCurrent" value=""/>
		</c:otherwise>
	</c:choose>
	<style>
	.diagramcurrent
	{
		border-bottom: 4px solid #465bdc;
		height: 29px;
	}
	</style>
	<div class="diagram_block" style="float: left;margin: auto;padding-left: 55px;padding-top: 10px;width: 18%;">
		<input type="text" class="dial circle_progress_bar" value="${Queued}" readonly="readonly" id="queuedNumber"/>
		<div class="${queuedCurrent}" style="margin-top:6px">
			<c:url value="/orderslist/vieworders" var="queuedURL">
				<c:param name="status" value="PENDING"></c:param>
			</c:url>
			<a href="${queuedURL}"  style="text-decoration: none;color:#6f6f6f;">Queued</a>
		</div>
	</div>
	<div class="diagram_block" style="float: left;margin: auto;padding-left: 40px;padding-top: 10px;width: 18%;">
		<input type="text" class="dial circle_progress_bar" value="${Active}" readonly="readonly" />
		<div class="${activeCurrent}"  style="margin-top:6px" >
			<c:url value="/orderslist/vieworders" var="activeURL">
				<c:param name="status" value="COMPLETED"></c:param>
			</c:url>
			<a href="${activeURL}" style="text-decoration: none;color:#6f6f6f;">Active</a>
		</div>
	</div>
	<div class="diagram_block" style="float: left;margin: auto;padding-left: 40px;padding-top: 10px;width: 18%;">
		<input type="text" class="dial circle_progress_bar" value="${Serviced}" readonly="readonly" />
		<div class="${servicedCurrent}"  style="margin-top:6px">
			<c:url value="/orderslist/vieworders" var="servicedURL">
				<c:param name="status" value="COLLECTED"></c:param>
			</c:url>
			<a href="${servicedURL}"  style="text-decoration: none;color:#6f6f6f;">Serviced</a>
		</div>
	</div>
	<div class="diagram_block" style="float: left;margin: auto;padding-left:40px;padding-top: 10px;width: 18%;">
		<c:set var="Total" value="${Queued+Active+Serviced}"/>
		<fmt:formatNumber var="target" value="${(Serviced/Total)*100}" maxFractionDigits="0"/>
		<c:if test="${Serviced==0 or  Total==0}">
			<fmt:formatNumber var="target" value="0" maxFractionDigits="0"/>
		</c:if>
		<input type="text" class="dial circle_progress_bar" value="${target}%" readonly="readonly" />
		<div class="digram_txt" style="margin-top:6px">Target</div>
	</div>
</div>
<br><br><br><br>
<br><br>


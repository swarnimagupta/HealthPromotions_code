<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="chart">
	<c:choose>
		<c:when test="${param.status=='INSERVICE'}">
			<c:set var="queuedCurrent" value=""/>
			<c:set var="activeCurrent" value=" diagramcurrent"/>
			<c:set var="servicedCurrent" value=""/>
		</c:when>
		<c:when test="${param.status=='COMPLETED'}">
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
	<c:set var="Total" value="${Queued+Active+Serviced}"/>
	<div class="row">
		<c:url value="/customerlist/customerdeatils" var="queuedURL">
			<c:param name="status" value="LOGGEDIN"></c:param>
		</c:url>
		<div class="large-4 small-4 columns">
			<fmt:formatNumber var="queuedValue" value="${(Queued/Total)*100}" maxFractionDigits="0"/>
			<c:if test="${Queued==0 or  Total==0}">
				<fmt:formatNumber var="queuedValue" value="0" maxFractionDigits="0"/>
			</c:if>
			<fmt:formatNumber var="remainingValue" value="${100-queuedValue}" maxFractionDigits="0"/>
			<ul data-pie-id="pie">
				<li data-value="${queuedValue}"></li>
				<li data-value="${remainingValue}"></li>
			</ul>
		</div>
		<div class="large-8 small-8 columns">
			<div id="pie"></div>
			<div class="chrtt"><a href="${queuedURL}"  style="text-decoration: none;color: #3f5c9a;">${queuedValue}% - Queued</a></div>
		</div>
	</div>
	<div class="row">
		<c:url value="/customerlist/customerdeatils" var="activeURL">
			<c:param name="status" value="INSERVICE"></c:param>
		</c:url>
		<div class="large-4 small-4 columns">
			<fmt:formatNumber var="activeValue" value="${(Active/Total)*100}" maxFractionDigits="0"/>
			<c:if test="${Active==0 or  Total==0}">
				<fmt:formatNumber var="activeValue" value="0" maxFractionDigits="0"/>
			</c:if>
			<fmt:formatNumber var="remainingValue" value="${100-activeValue}" maxFractionDigits="0"/>
			<ul data-pie-id="pie1">
				<li data-value="${activeValue}"></li>
				<li data-value="${remainingValue}"></li>
			</ul>
		</div>
		<div class="large-8 small-8 columns">
			<div id="pie1"></div>
			<div class="chrtt"><a href="${activeURL}"  style="text-decoration: none;color: #3f5c9a;">${activeValue}% - Active</a></div>
		</div>
	</div>
	<div class="row">
		<c:url value="/customerlist/customerdeatils" var="servicedURL">
			<c:param name="status" value="COMPLETED"></c:param>
		</c:url>
		<div class="large-4 small-4 columns">
			<fmt:formatNumber var="servicedValue" value="${(Serviced/Total)*100}" maxFractionDigits="0"/>
			<c:if test="${Serviced==0 or  Total==0}">
				<fmt:formatNumber var="servicedValue" value="0" maxFractionDigits="0"/>
			</c:if>
			<fmt:formatNumber var="remainingValue" value="${100-servicedValue}" maxFractionDigits="0"/>
			<ul data-pie-id="pie2">
				<li data-value="${servicedValue}"></li>
				<li data-value="${remainingValue}"></li>
			</ul>
		</div>
		<div class="large-8 small-8 columns">
			<div id="pie2"></div>
			<div class="chrtt"><a href="${servicedURL}"  style="text-decoration: none;color: #3f5c9a;">${servicedValue}% - Serviced</a></div>
		</div>
	</div>
	<div class="row">
		<c:set var="Total" value="${Queued+Active+Serviced}"/>
		<fmt:formatNumber var="target" value="${(Serviced/Total)*100}" maxFractionDigits="0"/>
		<c:if test="${Serviced==0 or  Total==0}">
			<fmt:formatNumber var="target" value="0" maxFractionDigits="0"/>
		</c:if>
		<fmt:formatNumber var="remainingValue" value="${100-target}" maxFractionDigits="0"/>
		<div class="large-4 small-4 columns">
			<ul data-pie-id="pie3">
				<li data-value="${target}"></li>
				<li data-value="${remainingValue}"></li>
			</ul>
		</div>
		<div class="large-8 small-8 columns">
			<div id="pie3"></div>
			<div class="chrtt">${target}% - Target</div>
		</div>
	</div>
</div>
<%-- 
<div class="chart_block">
	<c:choose>
		<c:when test="${param.status=='INSERVICE'}">
			<c:set var="queuedCurrent" value=""/>
			<c:set var="activeCurrent" value=" diagramcurrent"/>
			<c:set var="servicedCurrent" value=""/>
		</c:when>
		<c:when test="${param.status=='COMPLETED'}">
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
	<div class="diagram_block${queuedCurrent}">
		<input type="text" class="dial circle_progress_bar" value="${Queued}" readonly="readonly" />
		<div class="digram_txt">
			<c:url value="/customerlist/customerdeatils" var="queuedURL">
				<c:param name="status" value="LOGGEDIN"></c:param>
			</c:url>
			<a href="${queuedURL}"  style="text-decoration: none;color:#6f6f6f;">Queued</a>
		</div>
	</div>
	<div class="diagram_block${activeCurrent}">
		<input type="text" class="dial circle_progress_bar" value="${Active}" readonly="readonly" />
		<div class="digram_txt">
			<c:url value="/customerlist/customerdeatils" var="activeURL">
				<c:param name="status" value="INSERVICE"></c:param>
			</c:url>
			<a href="${activeURL}" style="text-decoration: none;color:#6f6f6f;">Active</a>
		</div>
	</div>
	<div class="diagram_block${servicedCurrent}">
		<input type="text" class="dial circle_progress_bar" value="${Serviced}" readonly="readonly" />
		<div class="digram_txt">
			<c:url value="/customerlist/customerdeatils" var="servicedURL">
				<c:param name="status" value="COMPLETED"></c:param>
			</c:url>
			<a href="${servicedURL}"  style="text-decoration: none;color:#6f6f6f;">Serviced</a>
		</div>
	</div>
	<div class="diagram_block">
		<c:set var="Total" value="${Queued+Active+Serviced}"/>
		<fmt:formatNumber var="target" value="${(Serviced/Total)*100}" maxFractionDigits="0"/>
		<c:if test="${Serviced==0 or  Total==0}">
			<fmt:formatNumber var="target" value="0" maxFractionDigits="0"/>
		</c:if>
		<input type="text" class="dial circle_progress_bar" value="${target}%" readonly="readonly" />
		<div class="digram_txt">Target</div>
	</div> --%>


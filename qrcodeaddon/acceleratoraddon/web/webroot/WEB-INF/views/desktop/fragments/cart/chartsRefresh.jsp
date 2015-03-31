<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:object>
	<json:property name="chartsRefresh" escapeXml="false">
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
		<style>
		.diagramcurrent
		{
			border-bottom: 4px solid #465bdc;
			height: 29px;
		}
		</style>
		<div class="diagram_block" style="float: left;margin: auto;padding-left: 55px;padding-top: 10px;width: 18%;">
			<input type="text" class="dial circle_progress_bar" value="${Queue}" readonly="readonly" id="queuedNumber"/>
			<div class="${queuedCurrent}" style="margin-top:6px">
				<c:url value="/customerlist/customerdeatils" var="queuedURL">
					<c:param name="status" value="LOGGEDIN"></c:param>
				</c:url>
				<a href="${queuedURL}" style="text-decoration: none;color:#6f6f6f;">Queued</a>
			</div>
		</div>
		<div class="diagram_block" style="float: left;margin: auto;padding-left: 40px;padding-top: 10px;width: 18%;">
			<input type="text" class="dial circle_progress_bar" value="${Activ}" readonly="readonly" />
			<div  class="${activeCurrent}" style="margin-top:6px">
				<c:url value="/customerlist/customerdeatils" var="activeURL">
					<c:param name="status" value="INSERVICE"></c:param>
				</c:url>
				<a href="${activeURL}" style="text-decoration: none;color:#6f6f6f;">Active</a>
			</div>
		</div>
		<div class="diagram_block" style="float: left;margin: auto;padding-left: 40px;padding-top: 10px;width: 18%;">
			<input type="text" class="dial circle_progress_bar" value="${Service}" readonly="readonly" />
			<div class="${servicedCurrent}" style="margin-top:6px">
				<c:url value="/customerlist/customerdeatils" var="servicedURL">
					<c:param name="status" value="COMPLETED"></c:param>
				</c:url>
				<a href="${servicedURL}" style="text-decoration: none;color:#6f6f6f;">Serviced</a>
			</div>
		</div>
		<div class="diagram_block" style="float: left;margin: auto;padding-left: 40px;padding-top: 10px;width: 18%;">
			<c:set var="Total" value="${Queue+Activ+Service}"/>
			<fmt:formatNumber var="target" value="${(Service/Total)*100}" maxFractionDigits="0"/>
			<c:if test="${Service==0 or  Total==0}">
				<fmt:formatNumber var="target" value="0" maxFractionDigits="0"/>
			</c:if>
			<input type="text" class="dial circle_progress_bar" value="${target}%" readonly="readonly" />
			<div class="digram_txt" style="margin-top:6px">Target</div>
		</div>
		<script>
		$(document).ready(function() {
				if($("#bell_number").html()!='${Queue}')
				{
					$("#bell_number").html('${Queue}');
					var audio = {};
					audio["walk"] = new Audio();
					audio["walk"].src = '${commonResourcePath}'+"/../../addons/qrcodeaddon/desktop/common/bnc_audio/bellring01.mp3"			
					audio["walk"].play();
				}
			});
		</script>
	</json:property>
</json:object>
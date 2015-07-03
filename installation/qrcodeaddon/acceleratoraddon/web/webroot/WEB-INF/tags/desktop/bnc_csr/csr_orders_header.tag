<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
    function redirectToOrdersDashboard()
    {
    	window.location.href="${contextPath}/orderslist/vieworders";
    }
    function redirectToCustomersDashboard()
    {
    	window.location.href="${contextPath}/customerlist/customerdeatils";
    }
    </script>
<div id="hdr">
	<div class="hdrc">
		<div class="hdrl">

			<a href="javascript:void(0);" class="mClose"> 
			<img src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/new/menu-tile.png" /></a>
			<ul class="dnon" id="lmenu">
				<li><a href="#" onClick="redirectToCustomersDashboard()"
					style="border-top: 1px solid #494949"> 
					<img src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/new/dashboard_icon.png"
						alt="" /> <span>Customers Dashboard</span>
				</a></li>
				<li><a href="#" class="select"
					onClick="redirectToOrdersDashboard()"> <img
						src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/new/dashboard_icon.png"
						alt="" /> <span>Orders Dashboard
					</span>
				</a></li>
				<li style="opacity: .4;"><a href="#"> <img
							src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/new/search_icon.png"
							alt="" /> <span class="singleline">Search</span>
					</a></li>
					<li style="opacity: .4;"><a href="#"> <img
							src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/new/notification_icon.png"
							alt="" /> <span class="singleline">Notifications</span>
					</a></li>
					<li style="opacity: .4;"><a href="#"> <img
							src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/new/myteam_icon.png"
							alt="" /> <span class="singleline">My Team</span>
					</a></li>
					<li style="opacity: .4;"><a href="#"> <img
							src="${commonResourcePath}/../../addons/qrcodeaddon/desktop/common/bnc_images/new/mytargets_icon.png"
							alt="" /> <span>My Targets <br>&amp; Performance
						</span>
					</a></li>
			</ul>


			<span class="title">Order Dashboard</span>
		</div>
		<div class="hdrr">
			<a href="javascript:void(0);" class="ntfn" id="bell_number">${Queued}</a>
			<a href="javascript:void(0);" id="usrdd" class="usrd"> <c:if
					test="${CSR_USER eq null or CSR_USER eq ''}">
					<c:redirect url="/login/csrLogin" />
				</c:if> ${fn:toUpperCase(CSR_USER)}<span class="darr"></span>
			</a>
			<div class="ddmenu">
				<ul class="ddmenuul">
					<li><a href="<c:url value='/logout'/>">Signout</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>




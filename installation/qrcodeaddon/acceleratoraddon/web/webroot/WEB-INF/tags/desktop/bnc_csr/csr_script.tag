<script>
function doAjaxPost() {
	var ucoid = document.getElementById('ucoid').value;
	 if (document.getElementById('ucoid').value =='' ) { 
	alert("Please enter the UCOID!");
		document.getElementById('ucoid').focus();
		return false;
	}
	//alert("Adding "+ucoid);
	$.ajax({
		type : 'GET',
		url : "${contextPath}/orderslist/ucoid",
		data : "ucoid=" + ucoid,
		dataType : 'json',
		success : function(response) {
		$("#ordersDivId").html(response.searchby_ucoid);
		},
		error : function(e) {
			alert("Please enter correct UCOID");
		}
	});
}

function OrderDetailsByOrderID(orderId) {
		$.ajax({
			type : 'GET',
			url : "${contextPath}/orderslist/order/orderCode",
			data: "orderCode="+orderId,
			dataType : 'json',
			success : function(response) {
				$("a").removeClass("active");
				$("#"+orderId).addClass("active");
				$("#personalDetails").removeClass("active");
				$("#orderDetailsTab").addClass("active");
				$('#CSROrderDetails').html(response.CSROrder_Details);
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
			},
			error : function(e) {
			}
		});
	}


function getOrdersByFromDate()
{
	var fdate = document.getElementById('datepicker').value;
	var tdate = document.getElementById('datepicker1').value;
	var ftime = document.getElementById('searchTimeBarFromTime').value;
	var ttime = document.getElementById('searchTimeBarToTime').value;
	if (fdate =='' ) 
	{ 
		alert("Please enter the from Date!");
		document.getElementById('datepicker').focus();
		return false;
	}
	if (tdate =='' ) 
	{ 
		alert("Please enter the to Date!");
		document.getElementById('datepicker1').focus();
		return false;
	}
	if (ftime =='' ) 
	{ 
		alert("Please enter the from Time!");
		document.getElementById('searchTimeBarFromTime').focus();
		return false;
	}
	if (ttime =='' ) 
	{ 
		alert("Please enter the to Time!");
		document.getElementById('searchTimeBarToTime').focus();
		return false;
	}
	$.ajax({
		type : 'GET',
		url : "${contextPath}/orderslist/datetime",
		data : "fdate=" + fdate+"&tdate="+tdate+"&ftime="+ftime+"&ttime="+ttime,
		dataType : 'json',
		success : function(response) {
			$("#ordersDivId").html(response.searchby_ucoid);
		},
		error : function(e) {
			alert("Please enter dates in proper format! Dates as MM/dd/YYYY and Time as HH:MM AM/PM!!\n\n FromDate should be before ToDate!!");
		}
	});
}
	
function PersonalDetailsByUserID(uid, orderCode) {
	 $.ajax({
		type : 'GET',
		url : "${contextPath}/orderslist/personaldetails",
		data: "uid="+uid+"&code="+orderCode,
		dataType : 'json',
		success : function(response) {
			$("#orderDetailsTab").removeClass("active");
			$("#personalDetails").addClass("active");
			$('#personalDetailsContentId').html(response.Personal_Details);
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
		},
		error : function(e) {
			alert("error"+e);
		}
	}); 
}	
	

</script>
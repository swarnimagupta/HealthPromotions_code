$(document).ready(function(){
	tabModule.init();	

$("#slider1").tinycarousel();
$( "#datepicker" ).datepicker({
showOn: "button",
buttonImage: "images/cal.png",
buttonImageOnly: true,
buttonText: "Select date"
});

$( "#datepicker1" ).datepicker({
showOn: "button",
buttonImage: "images/cal.png",
buttonImageOnly: true,
buttonText: "Select date"
});

$("#usrdd").hover(function() {
$(".ddmenu").show();
})

$("#hdr").mouseleave(function() {
$(".ddmenu").hide();
})

$("#lmenu").hide();
$(".mClose").click(function() {
$("#lmenu").animate({width: 'toggle'},200);
});

 $(".slmscr").slimScroll({railVisible: true, railColor: '#f00'});
 $(".slmscr1").slimScroll({railVisible: true, railColor: '#f00'});
$("#slimtest").slimScroll();


Pizza.init();
$(document).foundation();


});


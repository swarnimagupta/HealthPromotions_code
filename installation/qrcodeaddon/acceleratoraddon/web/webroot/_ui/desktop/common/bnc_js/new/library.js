$(document).ready(function(){

$("#slimtest").slimScroll();

var loc = location.href;
var imagePath = "${commonResourcePath}/../../_ui/addons/qrcodeaddon/desktop/common/bnc_images/new/cal.png";
if(loc.contains("electronics\/en"))
{
	imagePath = imagePath.replace("${commonResourcePath}\/..\/..\/","${commonResourcePath}\/..\/..\/..\/..\/");
}
$( "#datepicker" ).datepicker({
showOn: "button",
buttonImage: imagePath,
buttonImageOnly: true,
buttonText: "Select date"
});

$( "#datepicker1" ).datepicker({
showOn: "button",
buttonImage: imagePath,
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


Pizza.init();
$(document).foundation();




});


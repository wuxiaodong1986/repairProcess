<a class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<footer>
  <hr>
  <p class="am-padding-horizontal">© 河南师范大学新联学院</p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/polyfill/rem.min.js"></script>
<script src="assets/js/polyfill/respond.min.js"></script>
<script src="assets/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${applicationScope.oss}ui/amaze/js/jquery.min.js"></script>
<script src="${applicationScope.oss}ui/amaze/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="${applicationScope.oss}ui/amaze/js/app.js"></script>
<script type="text/javascript">
$("#accordion .eface-menu").click(function()
{
	$("#accordion .am-in").collapse('close');
	$(this).next().collapse('toggle');
});
</script>
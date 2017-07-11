<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，本站暂不支持。 请升级浏览器以获得更好的体验！建议使用最新的谷歌浏览器或360浏览器</p>
<![endif]-->

<header class="am-topbar admin-header">
  <div class="am-topbar-brand" style="height: 100px;">
  	<!-- <strong>报修管理系统</strong> <small>后台管理中心</small> -->
    <img width="520px" src="common/img/logo.png" alt="..." class="am-radius">
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> 个人信息 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="preOperatorUpdateSelf.htm"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="preUpdatePassword.htm"><span class="am-icon-cog"></span> 修改密码</a></li>
          <li><a href="logout.htm"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>
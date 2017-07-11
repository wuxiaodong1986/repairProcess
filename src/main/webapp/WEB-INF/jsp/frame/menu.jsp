<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
  <!-- 菜单 公告 start -->
  <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
      <ul class="am-list admin-sidebar-list" id="accordion">
        <c:forEach var="menu" items="${sessionScope.function.nodes}">
        <c:if test="${menu.ishow==1}">
        <li class="admin-parent">
          <a class="am-cf eface-menu" style="cursor: pointer;"><span class="${applicationScope.functionMap[menu.id].logo}"></span> ${applicationScope.functionMap[menu.id].name} <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
          <ul class="am-list am-collapse admin-sidebar-sub eface-menuitems <c:if test="${openMenuId==menu.id}">am-in</c:if> " id="collapse-nav${menu.id}">
          <c:forEach var="menuitem" items="${menu.nodes}">
          <c:if test="${menuitem.ishow==1}">
            <li><a href="${applicationScope.functionMap[menuitem.id].url}" class="am-cf"><span class="${applicationScope.functionMap[menuitem.id].logo}"></span> ${applicationScope.functionMap[menuitem.id].name}</a></li>
          </c:if>
          </c:forEach>
          </ul>
        </li>
        </c:if>
        </c:forEach>
        <li><a href="#"><span class="am-icon-sign-out"></span> 注销</a></li>
      </ul>

      <div class="am-panel am-panel-default admin-sidebar-panel">
        <div class="am-panel-bd">
          <p><span class="am-icon-bookmark"></span> 公告</p>
          <p>时光静好，与君语；细水流年，与君同。—— Amaze UI</p>
        </div>
      </div>

    </div>
  </div>
  
  <!-- 菜单 公告end -->
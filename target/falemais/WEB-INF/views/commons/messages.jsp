<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>

<div id="messagesSection">
  <div id="error_section" class="alert alert-danger "  style="${not empty errorMessages ? '' : 'display: none'}">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
    <c:forEach items="${errorMessages}" var="msg">
      <p>${msg}</p>
    </c:forEach>
  </div>
</div>
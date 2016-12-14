<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<%@ include file="/WEB-INF/views/commons/header.jsp"%>

<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"></a>
    </div>
    <h2><spring:message code="simulation.title"/> </h2>
  </div>
</nav>

<div class="container" style="min-height: 600px">

  <div class="starter-template">

    <div id="messagesSection">
      <div id="error_section" class="alert alert-danger">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
          <p><spring:message code="error.unexpected"/> </p>
      </div>
    </div>

  </div>

</div>

<%@ include file="/WEB-INF/views/commons/footer.jsp"%>



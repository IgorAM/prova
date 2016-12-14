<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="pt_BR">
<head>
  <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
  <link href="${bootstrapCss}" rel="stylesheet" />

  <spring:url value="/resources/css/style.css" var="styleCss" />
  <link href="${styleCss}" rel="stylesheet" />

  <spring:url value="/resources/js/jquery-3.1.1.min.js" var="jqueryJs" />
  <script src="${jqueryJs}"></script>

  <spring:url value="/resources/js/inputmask.min.js" var="inputMaskJs" />
  <script src="${inputMaskJs}"></script>

  <spring:url value="/resources/js/jquery.inputmask.min.js" var="jqueryInputMaskJs" />
  <script src="${jqueryInputMaskJs}"></script>

  <spring:url value="/resources/js/accounting.js" var="accountingJs" />
  <script src="${accountingJs}"></script>

  <spring:url value="/resources/js/MoneyFormatter.js" var="MoneyFormatterJs" />
  <script src="${MoneyFormatterJs}"></script>

  <title><spring:message code="simulation.title"/></title>
</head>
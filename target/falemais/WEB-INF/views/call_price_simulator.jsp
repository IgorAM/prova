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
    <h1><spring:message code="simulation.subtitle"/></h1>
    <br>

    <%@ include file="/WEB-INF/views/commons/messages.jsp"%>

    <form:form cssClass="form-horizontal" id="simulate-form" modelAttribute="simulationForm">

      <div class="form-group form-group-sm">
        <label class="col-sm-2 control-label"><spring:message code="simulation.input.call.time"/></label>
        <div class="col-sm-1">
          <input type=text class="form-control" id="call_time">
        </div>
      </div>

      <div class="form-group form-group-sm">
        <label class="col-sm-2 control-label"><spring:message code="simulation.input.origin.city"/></label>
        <div class="col-sm-1">
          <form:select cssClass="form-control" id="origin_city"  path="originCity">
            <form:options items="${cityCodes}" itemLabel="code" itemValue="id"/>
          </form:select>
        </div>
      </div>

      <div class="form-group form-group-sm">
        <label class="col-sm-2 control-label"><spring:message code="simulation.input.destination.city"/></label>
        <div class="col-sm-1">
          <select class="form-control" id="destination_city" name="destinationCity"></select>
          <%--<form:select cssClass="form-control" id="destination_city"  path="destinationCity">
            <form:options  items="${cityCodes}" itemLabel="code" itemValue="id"/>
          </form:select>--%>
        </div>
      </div>

      <div class="form-group form-group-sm">
        <label class="col-sm-2 control-label"><spring:message code="simulation.input.plan"/></label>
        <div class="col-sm-2  ">
          <form:select cssClass="form-control" path="plan" id="plan">
            <form:options  items="${plans}" itemLabel="name" itemValue="id"/>
          </form:select>
        </div>
      </div>

      <br/>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" id="btn-simulate" class="btn btn-primary btn-lg"><spring:message code="simulation.btn.simulate"/></button>
        </div>
      </div>
    </form:form>


    <br/>
    <div id="feedback"></div>

  </div>


</div>

<%@ include file="/WEB-INF/views/commons/footer.jsp"%>


<script>
  $(document).ready(function(){

    $("#call_time").inputmask("99999", {"placeholder": ""});

    findDestinations();

    $("#origin_city").change(function() {
      findDestinations();
    });

    $("#simulate-form").submit(function(event) {

      disableSimulateButton(true);
      event.preventDefault();
      simulate();
    });

  });

  function findDestinations() {

    var origin = {}
    origin["originCity"] = $("#origin_city").val();

    $.ajax({
      type : "POST",
      contentType : "application/json",
      url : "${pageContext.request.contextPath}/destinations_city_code",
      data : JSON.stringify(origin),
      dataType : 'json',
      timeout : 10000,
      success : function(response) {

        if(response.code == 200) {
          var sel = $("#destination_city");
          sel.html("");
          $.each( response.destinations, function( index, el ) {
            sel.append($("<option>").attr('value',el.id).text(el.code));
          });
        }
      }

    });

  }

  function simulate() {

    var simulation = {}
    simulation["callTime"] = $("#call_time").val();
    simulation["originCity"] = $("#origin_city").val();
    simulation["destinationCity"] = $("#destination_city").val();
    simulation["plan"] = $("#plan").val();

    $.ajax({
      type : "POST",
      contentType : "application/json",
      url : "${pageContext.request.contextPath}/simulation",
      data : JSON.stringify(simulation),
      dataType : 'json',
      timeout : 10000,
      success : function(response) {

        if(response.code == 200) {
          display(getSuccessMessage(response));

        }else if(response.code == 204){
          var message ='<spring:message code="info.simulation.no.content.found"/>';
          var finalMessage = "<pre>"+message+"</pre>";
          display(finalMessage);

        }else{
          displayError(response)
        }

        disableSimulateButton(false);
      }

    });

  }

  function getSuccessMessage(response){
    var falemaisPlan = response.falemaisPlanDetailed;
    var noPlan = response.noPlanDetailed;

    var messageFaleMaisFree1 ='<spring:message code="info.simulation.falemais.free1"/>';
    var messageFaleMaisFree2 ='<spring:message code="info.simulation.falemais.free2"/>';
    var comparativeMessage1 ='<spring:message code="info.simulation.comparative1"/>';
    var comparativeMessage2 ='<spring:message code="info.simulation.comparative2"/>';

    var message = "";

    if(falemaisPlan.price == 0){
      message = "<pre>"+ messageFaleMaisFree1 + "<br/>" + messageFaleMaisFree2 + " " + MoneyFormatter.format(noPlan.price) + ".</pre>";
    }else{
      message = "<pre>"+ comparativeMessage1 + " " + MoneyFormatter.format(falemaisPlan.price) + "!<br/>" + comparativeMessage2 + " "  + MoneyFormatter.format(noPlan.price) + ".</pre>";
    }

    return message;
  }

  function disableSimulateButton(flag) {
    $("#btn-simulate").prop("disabled", flag);
  }


  function display(message) {
    $("#error_section").hide();
    $("#error_section").html("");

    $("#feedback").show();
    $('#feedback').html(message);
  }

  function displayError(response){
    $("#feedback").hide();

    $("#error_section").show();
    $("#error_section").html("");
    $.each( response.errorMessages, function( index, msg ) {
      $("#error_section").append("<p>" + msg + "</p>");
    });

  }
</script>

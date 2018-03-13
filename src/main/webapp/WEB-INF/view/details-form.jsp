<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

<!-- If IE use the latest rendering engine -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Set the page to the width of the device and set the zoon level -->
<meta name="viewport" content="width = device-width, initial-scale = 1">

<title>Player Management</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">

<style>
.jumbotron{
    background-color:darkgrey;
    color:white;
}
/* Adds borders for tabs */
.tab-content {
    border-left: 1px solid #ddd;
    border-right: 1px solid #ddd;
    border-bottom: 1px solid #ddd;
    padding: 10px;
}
.nav-tabs {
    margin-bottom: 0;
}
</style>

</head>
<body>


<div class = "container">

    <!-- Basic vertical menu -->
<ul class="list-inline">
 <li class="list-inline-item"><a class="social-icon text-xs-center" href="list">Home</a></li>

<security:authorize access="hasRole('ADMIN')">
<li class="list-inline-item"><a class="social-icon text-xs-center" href="admin/deleteAllPlayers">Delete all Players</a></li>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
<li class="list-inline-item"><a class="social-icon text-xs-center"  href="admin/resetPlayersPK">Reset Players PK</a></li>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
<li class="list-inline-item"><a class="social-icon text-xs-center"   href="admin/deleteAllPlayersDetails">Delete all Player Details</a></li>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
<li class="list-inline-item"><a class="social-icon text-xs-center"  href="admin/resetPlayersDetailsPK">Reset Player's Details PK</a></li>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
<li class="list-inline-item"><a class="social-icon text-xs-center"   href="admin/putTestData">Insert Demo Data</a></li>
</security:authorize>


<li class="list-inline-item text-right">

<form:form action="${pageContext.request.contextPath}/logout" method="POST">

<button type="submit" class = "btn btn-warning btn-xs">Logout</button>

</form:form>

</li>





</ul>

    <div class = "jumbotron">
<div class="page-header">
<h1>Add details:</h1>
<h2>${player.firstName} ${player.lastName}</h2>
<h3>${player.position}</h3>
</div>
<form:form action="savePlayerDetails" modelAttribute="details" method="POST">
<form:hidden path="id"/>
<table class="table table-hover table-bordered table-striped">
  <thead>
    <tr >
      <th class="bg-info"> Date of birth [DD-MM-YYYY]:</th><td class="text-md-left" >

      <form:input path="dateOfBirth" id="text" maxlength="5" />
      <input type="button" class = "btn btn-warning btn-sm" value="Add year" id="button" />
      <form:errors path = "dateOfBirth" cssClass="error"/>

      </td>
    </tr>


     <tr>
          <th class="bg-info">Email</th><td class="text-md-left">
          <form:input path="email"/>
                   <form:errors path="email" cssClass="error"/>

          </td>
      </tr>
       <tr>
            <th class="bg-info">Preferred foot</th><td class="text-md-left">


            <form:input path="preferredFoot"/>

            </td>
       </tr>
        <tr>
         <th class="bg-info">Short info</th><td class="text-md-left">
         <form:input path="shortInfo"/>
                  <form:errors path="shortInfo" cssClass="error"/>
                   <input type="hidden" name="playerId" value="${player.playerId}"/>
                  </td>
        </tr>

  </thead>
  <tbody>

  </tbody>

</table>

<input type="submit" class = "btn btn-warning btn-md" value="Save"/>
</form:form>




<script type="text/javascript">
document.getElementById("button").addEventListener('click', function () {
    var text = document.getElementById('text');
    text.value += '-${details.yearOfBirth}';
});
</script>





</body>
</html>
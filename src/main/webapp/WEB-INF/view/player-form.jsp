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
<h1>Add/Update player:</h1>
<h2>${player.firstName} ${player.lastName}</h2>
<h3>${player.position}</h3>
</div>



<form:form action="savePlayer" modelAttribute="player" method="POST">
<form:hidden path="playerId"/>


<table class="table table-hover table-bordered table-striped">
  <thead>
    <tr >
      <th class="bg-info">First name</th><td class="text-md-left" >
        <form:input path="firstName"/>
      </td>
    </tr>


     <tr>
          <th class="bg-info">Last name</th><td class="text-md-left">
          <form:input path="lastName"/>

          </td>
      </tr>
       <tr>
            <th class="bg-info">Age</th><td class="text-md-left">


          <form:input path="age"/>

            </td>
       </tr>
        <tr>
         <th class="bg-info">Position</th><td class="text-md-left">

                  <form:input path="position"/>
                  </td>
        </tr>

  </thead>
  <tbody>

  </tbody>

</table>


<input type="submit" class = "btn btn-warning btn-md" value="Save" class="save"/>
</form:form>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

</body>
</html>
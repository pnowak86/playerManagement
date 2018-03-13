<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
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
<h1>Football Player Management</h1>
</div>
You are logged as: <security:authentication property="principal.username"/>,
<security:authentication property="principal.authorities"/>


<form:form action="search" method="POST">
    <div class="input-group">
      <input type="text" class="form-control" placeholder="Search by name or surname" name="theSearchName">
      <div class="input-group-btn">
        <button class="btn btn-default" type="submit">Search<i class="glyphicon glyphicon-search"></i></button>

      </div>
    </div>
  </form:form>
   <br>
   <button type="submit" class = "btn btn-warning btn-md" onclick="window.location.href='showFormForAdd'; return false;" >Add Player</button>
    <br> <br>

  <div class="panel panel-default">
<div class="table-responsive">
<table class="table table-hover table-bordered table-striped">
  <thead>
    <tr class="bg-info">
      <th>#</th>
      <th class="text-md-center">First Name</th>
      <th class="text-md-center">Last Name</th>
      <th class="text-md-center">Age</th>
      <th class="text-md-center">Position</th>
      <th class="text-md-center">Details</th>
      <th class="text-md-center">Action</th>
    </tr>
  </thead>
  <tbody>

   <c:forEach var="tempPlayer" items="${players}">

      <c:url var="updateLink" value="/players/showFormForUpdate">
           <c:param name="playerId" value="${tempPlayer.playerId}"/>
      </c:url>

      <c:url var="deleteLink" value="/players/deletePlayer">
           <c:param name="playerId" value="${tempPlayer.playerId}"/>
      </c:url>

      <c:url var="detailsLink" value="/players/details">
           <c:param name="playerId" value="${tempPlayer.playerId}"/>
      </c:url>


    <tr>

          <td>${tempPlayer.playerId}</td>
          <td>${tempPlayer.firstName}</td>
          <td>${tempPlayer.lastName}</td>
          <td>${tempPlayer.age}</td>
          <td>${tempPlayer.position}</td>
           <td class="text-md-center"><input type="button" class = "btn btn-warning btn-md" value="Details" onclick="location.href='${detailsLink}';"/></td>
           <td class="text-md-center">
            <div class="btn-group">
              <input type="button" class = "btn btn-warning btn-md" onclick="location.href='${updateLink}';" value="Update" align="right" />
               <input type="button" class = "btn btn-danger btn-md" onclick="if((confirm('Are you sure want to delete this player?'))) return location.href='${deleteLink}'" value="Delete" />
</div>
            </td>

      </tr>
      </c:forEach>
  </tbody>
</table>
</div>
</div>
    </div>
 </div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

</body>
</html>
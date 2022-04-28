<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="sidenav">
	<h3 id="logo">
		Administrative <br /> Academy Portal
	</h3>
	<c:url var="classesLink" value="AdminControllerServlet">
		<c:param name="command" value="CLASSES" />
	</c:url>

	<c:url var="subjectsLink" value="AdminControllerServlet">
		<c:param name="command" value="SUBJECTS" />
	</c:url>

	<c:url var="teachersLink" value="AdminControllerServlet">
		<c:param name="command" value="TEACHERS" />
	</c:url>

	<c:url var="studentsLink" value="AdminControllerServlet">
		<c:param name="command" value="STUDENTS" />
	</c:url>
	
 

 
 	 
	
	<a class="bar-item" href="${classesLink}">CLASS</a> 
	<a class="bar-item" href="${studentsLink}">STUDENT</a> 
		
		<a class="bar-item" href="${teachersLink}">TEACHER</a> 
		<a class="bar-item" href="${subjectsLink}">SUBJECT</a>
		<a class="bar-item" href="login.jsp">LOG OUT</a>

</div>


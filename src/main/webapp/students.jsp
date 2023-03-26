<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Students List</title>
	</head>
	<body>
		<h2>Welcome ${requestScope.userId}</h2>

		<table>
			<thead>
				<tr>
					<th>Department</th>
					<th>Student Id</th>
					<th>Marks</th>
					<th>Pass</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.students}" var="student">
					<tr>
						<td>${student.department}</td>
						<td>
							<a
								href="#${student.studentId}"
								onclick="showStudentName('${student.studentName}');"
								>${student.studentId}</a
							>
						</td>
						<td>${student.mark}</td>
						<td>
							<c:set
								var="passPercent"
								value="${100.0 * student.mark}"
							/><c:out value="${passPercent}" />%
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script>
			function showStudentName(name) {
				alert("Student Name: " + name);
			}
		</script>
	</body>
</html>

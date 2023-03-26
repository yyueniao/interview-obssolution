<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Students List</title>
	</head>
	<body>
		<h2>Welcome ${requestScope.userId}</h2>

		<table border="1">
			<thead>
				<tr>
					<th>Department</th>
					<th>Student Id</th>
					<th>Marks</th>
					<th>Pass %</th>
				</tr>
			</thead>
			<tbody>
				<c:set
					var="map"
					value="${requestScope.departmentStudentsMap}"
				/>
				<c:forEach items="${requestScope.departments}" var="department">
					<c:set var="students" value="${map.get(department)}" />
					<c:set var="passCount" value="${0}" />
					<c:forEach items="${students}" var="student">
						<c:if test="${student.mark >= requestScope.passMark}">
							<c:set var="passCount" value="${passCount+1}" />
						</c:if>
					</c:forEach>
					<c:set var="isFirst" value="${true}" />
					<c:forEach items="${students}" var="student">
						<tr>
							<c:if test="${isFirst}">
								<td rowspan="${students.size()}">
									${student.department}
								</td>
							</c:if>
							<td>
								<a
									href="#${student.studentId}"
									onclick="showStudentName('${student.studentName}');"
									>${student.studentId}</a
								>
							</td>
							<td>${student.mark}</td>
							<c:if test="${isFirst}">
								<td rowspan="${students.size()}">
									${100.0 * passCount / students.size()}
								</td>
							</c:if>
						</tr>
						<c:set var="isFirst" value="${false}" />
					</c:forEach>
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

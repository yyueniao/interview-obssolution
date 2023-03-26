<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Students List</title>
	</head>
	<style>
		#container {
			position: absolute;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			width: 400px;
			height: 300px;
			background-color: #f1f1f1;
			padding: 50px;
			border: 1px solid black;

			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
		}

		table {
			border-collapse: collapse;
			border: 1px solid black;
		}
		th,
		td {
			padding: 5px;
			border: 1px solid black;
		}
		th {
			background-color: blue;
			color: white;
		}
		.center {
			text-align: center;
		}
		.right {
			text-align: right;
		}
	</style>
	<body>
		<div id="container">
			<h2>Welcome ${requestScope.userId}</h2>

			<table>
				<thead>
					<tr>
						<th class="left">Department</th>
						<th class="left">Student Id</th>
						<th class="left">Marks</th>
						<th class="left">Pass %</th>
					</tr>
				</thead>
				<tbody>
					<c:set
						var="map"
						value="${requestScope.departmentStudentsMap}"
					/>
					<c:forEach
						items="${requestScope.departments}"
						var="department"
					>
						<c:set var="students" value="${map.get(department)}" />
						<c:set var="passCount" value="${0}" />
						<c:forEach items="${students}" var="student">
							<c:if
								test="${student.mark >= requestScope.passMark}"
							>
								<c:set var="passCount" value="${passCount+1}" />
							</c:if>
						</c:forEach>
						<c:set var="isFirst" value="${true}" />
						<c:forEach items="${students}" var="student">
							<tr>
								<c:if test="${isFirst}">
									<td
										rowspan="${students.size()}"
										class="center"
									>
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
								<td class="right">${student.mark}</td>
								<c:if test="${isFirst}">
									<td
										rowspan="${students.size()}"
										class="center"
									>
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
		</div>
	</body>
</html>

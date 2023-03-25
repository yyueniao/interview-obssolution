<html>
	<head>
		<title>Login Page</title>
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
				align-items: center;
				justify-content: center;
			}

			#input-field {
				width: 100%;
				display: flex;
				flex-direction: row;
				align-items: center;
				gap: 10px;
			}

			form {
				height: 100%;
				width: 100%;
				display: flex;
				flex-direction: column;
				align-items: center;
				justify-content: center;
				gap: 20px;
			}

			label {
				flex-basis: 30%;
			}

			input[type="text"],
			input[type="password"] {
				width: 70%;
				padding: 12px 20px;
				margin: 8px 0;
				box-sizing: border-box;
				border: none;
				border-bottom: 2px solid #ccc;
				flex-basis: 70%;
			}

			input[type="submit"] {
				background-color: #4caf50;
				color: white;
				padding: 12px 20px;
				border: none;
				border-radius: 4px;
				cursor: pointer;
			}

			input[type="submit"]:hover {
				background-color: #45a049;
			}
		</style>
	</head>
	<body>
		<div id="container">
			<form id="loginForm" action="students" method="post">
				<div id="input-field">
					<label for="userId">User ID:</label>
					<input id="userId" type="text" name="userId" /><br />
				</div>
				<div id="input-field">
					<label for="password">Password:</label>
					<input
						id="password"
						type="password"
						name="password"
					/><br />
				</div>
				<input type="submit" value="Login" />
			</form>
		</div>

		<script>
			const loginForm = document.getElementById("loginForm");
			loginForm.addEventListener("submit", function (event) {
				event.preventDefault();

				const userId = document.getElementById("userId").value;
				const password = document.getElementById("password").value;
				const url = "api/login";

				fetch(url, {
					method: "POST",
					headers: {
						"Content-Type": "application/json",
					},
					body: JSON.stringify({
						userId,
						password,
					}),
				})
					.then((response) => {
						if (response.status === 200) {
							window.location.href += "students";
						} else {
							alert("Invalid username or password.");
						}
					})
					.catch((error) => {
						console.error(error);
					});
			});
		</script>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">

html, body{ 
	height: 100%;
	width: 100%;
	margin: 0;
}

body{
	display:flex;
	background-color: rgba(238,238,238);
}

form {
	height: 200px;
	width: 200px;
	margin: auto;
	text-align: center;
	background-color: white;
	border-radius: 25px;
}

input {
	margin-top: 10px;
}

.error{
	margin-top: 40px;
}
</style>

<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<form name="form" action="Login" method="post">
		<label for="form">Log in</label><br>
		<input name="email" type="email" placeholder="email">
		<input name="password" type="password" placeholder="password">
		<input type="reset" value="Reset">
		<input type="submit" value="Log in">
		
		<label class="error"> ${errorMessage} </label>
	</form>
	
</body>
</html>
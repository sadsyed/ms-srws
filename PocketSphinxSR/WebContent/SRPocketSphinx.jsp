<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>PocketSphinx Speech Recognizer</title>
</head>
<body>
	<div>
		<form action="upload"  method="post"  enctype="multipart/form-data">
			<h1>Speech Recognizer Uploaded</h1>
			<h3> Choose File (Corpus or Audio file) to Upload in Server</h3>
			<input id="fileName"  type="file" name="fileName" size ="50"/>
			<input type="submit"  name="submit" value="Upload">
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recognizer</title>
</head>
<body>
<f:view>
<h:form>
	  		<h3>Successfully uploaded an audio sample. </h3>
	  		<h3>Press Launch button to preform speech recognition.</h3>
	<h:commandButton action="#{speechRecognizer.recognize}" value="Launch Recognition"></h:commandButton>
	<h:messages layout="table"> </h:messages>
</h:form>
		  <h:panelGroup rendered="#{speechRecognizer.initial!=true}">
	  		<h3> Result </h3>
	  		<h:outputLabel value="Recognized Ouput: "></h:outputLabel>
	  		<h:outputLabel value="#{speechRecognizer.result}" ></h:outputLabel>
	  	  </h:panelGroup>
</f:view>
</body>
</html>
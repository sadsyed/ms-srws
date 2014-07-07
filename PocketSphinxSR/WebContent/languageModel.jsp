<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Language Model</title>
</head>
<body>
<jsp:useBean id="languageModelGenerator" class="ms.sr.pocketsphinx.LanguageModelGenerator"/>
<f:view>
	<h:form>
		<h3>File was successfully uploaded to the server.</h3>
	  		Language Model Name:
	  		<h:inputText value="#{languageModelGenerator.name}"/>
	  		<br>
	  		<center>
		<h:commandButton action="#{languageModelGenerator.generate}" value="Generate Language Model"></h:commandButton>
		<h:messages layout="table"> </h:messages>
	</center>
	</h:form>
			 <h:panelGroup rendered="#{languageModelGenerator.initial!=true}">
	  		<h3> Result </h3>
	  		<h:outputLabel value="Generate Language Model :  "></h:outputLabel>
	  		<h:outputText value="#{languageModelGenerator.name}"/>
	  		<h:outputText value=" was generated."/>
			</h:panelGroup>
</f:view>
</body>
</html>
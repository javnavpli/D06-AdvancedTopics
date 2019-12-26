<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.messageThread.list.label.title" path="title" width="50%"/>
	<acme:list-column code="authenticated.messageThread.list.label.moment" path="moment" width="50%"/>
</acme:list>

<acme:form>
	<acme:form-submit code="authenticated.messageThread.list.button.create" action="/authenticated/message-thread/create" method="get"/>
</acme:form>
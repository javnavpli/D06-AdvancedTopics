<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="authenticated.userThread.list.label.user" path="userUsername" width="50%"/>
</acme:list>

<acme:form>
	<acme:form-return code="authenticated.userThread.form.button.return"/>	
</acme:form>

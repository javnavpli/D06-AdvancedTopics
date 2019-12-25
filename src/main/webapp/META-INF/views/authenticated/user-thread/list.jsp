<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
	<acme:list-column code="authenticated.userThread.list.label.title" path="userUsername" width="50%"/>
</acme:list>

<acme:form>
	<acme:form-submit
				code='authenticated.userThread.form.button.create'
				action='/authenticated/user-thread/create?${pageContext.request.queryString}'/>
	<acme:form-return code="authenticated.userThread.form.button.return"/>	
</acme:form>

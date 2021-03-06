 <%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command != 'create' }">
		<acme:form-textbox code="authenticated.userThread.form.label.username" path="userUsername" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command == 'create' }">
		<acme:form-select code="authenticated.userThread.form.label.userToAdd" path="userToAdd">
			<jstl:forEach items="${systemUsers}" var="auth">
				<acme:form-option code="${ auth.userAccount.username}" value="${ auth.id}"/>
			</jstl:forEach>
		</acme:form-select>
	</jstl:if>
	

	<acme:form-submit test="${command == 'create'}"
		code = "authenticated.userThread.form.button.create"
		action="/authenticated/user-thread/create?${pageContext.request.queryString}"/>
		
	<acme:form-submit test="${command != 'create' && removable}"
		code = "authenticated.userThread.form.button.delete"
		action="/authenticated/user-thread/delete"/>
		
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
	
</acme:form>
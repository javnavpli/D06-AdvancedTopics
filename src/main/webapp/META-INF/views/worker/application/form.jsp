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
	<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber"/>
	<acme:check-access test="${command != 'create' }">
		<acme:form-textbox code="worker.application.form.label.status" path="status" readonly="true"/>
		<acme:check-access test="${!justification.isEmpty() && justification != null}">
		<acme:form-textbox code="worker.application.form.label.justification" path="justification" readonly="true"/>
		</acme:check-access>
		<acme:form-moment code="worker.application.form.label.moment" path="moment" readonly="true"/>
	</acme:check-access>
	<acme:form-textbox code="worker.application.form.label.statement" path="statement"/>
	<acme:form-textarea code="worker.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications"/>
	<acme:form-errors path="cantComplete"/>
	<acme:form-textbox code="worker.application.form.label.answer" path="answer"/>
	<acme:form-textbox code="worker.application.form.label.xxx4" path="XXX4"/>
	<acme:form-password code="worker.application.form.label.password" path="password"/>
	
	<acme:form-submit test="${command == 'create'}"
			code='worker.application.form.button.create'
			action='/worker/application/create?${pageContext.request.queryString}' />

	<acme:form-return code="worker.application.form.button.return"/>
	

</acme:form>

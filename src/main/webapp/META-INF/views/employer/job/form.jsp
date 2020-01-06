
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
	<acme:form-textbox code="employer.job.form.label.reference" path="reference"/>
	<acme:check-access test="${command != 'create' && upgradeable}">
		<acme:form-checkbox code="employer.job.form.label.status" path="finalMode"/>
	</acme:check-access>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="employer.job.form.label.description" path="description"/>
	<acme:form-textarea code="employer.job.form.label.xxx1.text" path="XXX1.text"/>
	<acme:form-url code="employer.job.form.label.xxx1.xxx2" path="XXX1.XXX2"/>
	
	<acme:form-return code="employer.job.form.button.return"/>
	<acme:form-submit test="${command == 'create'}"
		code="employer.job.form.button.createJob"
		action="/employer/job/create"/>
	<acme:check-access test="${ upgradeable }">
		<acme:form-submit test="${command == 'show'}"
			code="employer.job.form.button.update"
			action="/employer/job/update"/>
		<acme:form-submit test="${command == 'update'}"
			code="employer.job.form.button.update"
			action="/employer/job/update"/>
	</acme:check-access>
		
	<acme:check-access test="${ removable }">
		<acme:form-submit test="${command == 'show'}"
			code="employer.job.form.button.delete"
			action="/employer/job/delete"/>
		<acme:form-submit test="${command == 'delete'}"
			code="employer.job.form.button.delete"
			action="/employer/job/delete"/>
	</acme:check-access>
		
	<acme:check-access test="${command == 'show'}">
		<acme:form-submit code="employer.job.form.button.listDuties" method="get" action="/employer/duty/list?${pageContext.request.queryString}" />
		<acme:check-access test="${ upgradeable}">
			<acme:form-submit code="employer.job.form.button.createDuties" method="get" action="/employer/duty/create?${pageContext.request.queryString}" />
		</acme:check-access>
	</acme:check-access>
	
</acme:form>




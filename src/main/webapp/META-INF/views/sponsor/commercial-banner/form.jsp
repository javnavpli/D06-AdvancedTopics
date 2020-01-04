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
	<acme:check-access test="${cantCreate}">
		<acme:alert-error>
			<acme:message code="sponsor.banner.form.error.noCreditCard"/>
		</acme:alert-error>
	</acme:check-access>
	<acme:check-access test="${!cantCreate}">
	<acme:form-url code="sponsor.commercial-banner.form.label.picture" path="picture"/>
	<acme:form-url code="sponsor.commercial-banner.form.label.url" path="url"/>
	<acme:form-textarea code="sponsor.commercial-banner.form.label.slogan" path="slogan"/>
	<acme:form-textbox code="sponsor.commercial-banner.form.label.ccHoler" path="creditCard.holder"/>
	<acme:form-textbox code="sponsor.commercial-banner.form.label.ccBrand" path="creditCard.brand"/>
	<acme:form-textbox code="sponsor.commercial-banner.form.label.ccDeadline" path="creditCard.deadline"/>
	<acme:form-textbox code="sponsor.commercial-banner.form.label.ccNumber" path="creditCard.number"/>
	<acme:form-textbox code="sponsor.commercial-banner.form.label.ccCcv" path="creditCard.cvv"/>
	
	<acme:form-submit test="${command == 'create'}"
		code="sponsor.commercial-banner.form.button.create"
		action="/sponsor/commercial-banner/create"/>
	<acme:form-submit test="${command == 'show'}"
		code="sponsor.commercial-banner.form.button.update"
		action="/sponsor/commercial-banner/update"/>
	<acme:form-submit test="${command == 'update'}"
		code="sponsor.commercial-banner.form.button.update"
		action="/sponsor/commercial-banner/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="sponsor.commercial-banner.form.button.delete"
		action="/sponsor/commercial-banner/delete"/>
	<acme:form-submit test="${command == 'delete'}"
		code="sponsor.commercial-banner.form.button.delete"
		action="/sponsor/commercial-banner/delete"/>
		
	<acme:form-return code="sponsor.commercial-banner.form.button.return"/>
	</acme:check-access>
</acme:form>

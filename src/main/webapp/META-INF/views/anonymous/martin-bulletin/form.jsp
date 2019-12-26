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
    <acme:form-textbox code="anonymous.martin-bulletin.form.label.name" path="name" placeholder="Nombre test"/>
    <acme:form-textbox code="anonymous.martin-bulletin.form.label.surname" path="surname" placeholder="Apellido test"/>
    <acme:form-integer code="anonymous.martin-bulletin.form.label.age" path="age" placeholder="20"/>
    <acme:form-textbox code="anonymous.martin-bulletin.form.label.text" path="text" placeholder="Test"/>
    
    <acme:form-submit code="anonymous.martin-bulletin.form.button.create" action="/anonymous/martin-bulletin/create"/>
    <acme:form-return code="anonymous.martin-bulletin.form.button.return"/>
</acme:form>
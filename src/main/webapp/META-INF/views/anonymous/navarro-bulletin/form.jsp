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
    <acme:form-textbox code="anonymous.navarro-bulletin.form.label.name" path="name" placeholder="Juan Martin"/>
    <acme:form-integer code="anonymous.navarro-bulletin.form.label.age" path="age" placeholder="15"/>
    <acme:form-double code="anonymous.navarro-bulletin.form.label.height" path="height" placeholder="1.68"/>
    <acme:form-double code="anonymous.navarro-bulletin.form.label.weight" path="weight" placeholder="1.51"/>
    
    <acme:form-submit code="anonymous.navarro-bulletin.form.button.create" action="/anonymous/navarro-bulletin/create"/>
    <acme:form-return code="anonymous.navarro-bulletin.form.button.return"/>
</acme:form>
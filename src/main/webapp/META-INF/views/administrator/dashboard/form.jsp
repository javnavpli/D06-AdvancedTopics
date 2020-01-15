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


<acme:form readonly="true">
	<acme:form-textbox code="administrator.list-data.form.label.nAnn" path="numberAnnouncements"/>
    <acme:form-textbox code="administrator.list-data.form.label.nCom" path="numberCompanies"/>
    <acme:form-textbox code="administrator.list-data.form.label.nInv" path="numberInvestors"/>
    
    <acme:form-textbox code="administrator.list-data.form.label.jobsPerEmployer" path="jobsPerEmployer"/>
    <acme:form-textbox code="administrator.list-data.form.label.applicationsPerEmployer" path="applicationsPerEmployer"/>
    <acme:form-textbox code="administrator.list-data.form.label.applicationsPerWorker" path="applicationsPerWorker"/>
</acme:form>







<h2>
	<acme:message code="administrator.charts.form.applications"/>
</h2>

<div>
	<canvas id="canvas1"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		
		var data = {
				labels: [ <jstl:forEach var="label" items="${applicationsDates}">
							"<jstl:out value="${label.toString().split(' ')[0]}"/>",
						  </jstl:forEach>
					
				],
				datasets: [
					{	label:"<acme:message code="administrator.charts.form.applications.pending"/>",
						data : [
							<jstl:forEach var="label" items="${applicationsDates}">

								<jstl:out value="{x: '${label.toString().split(' ')[0]}', y: ${pendingApplications.get(label)}}" escapeXml="false"/>,

							</jstl:forEach>
						],
						borderColor: [
					          "#f38b4a"
					        ]
					},{	label:"<acme:message code="administrator.charts.form.applications.accepted"/>",
						data : [
							<jstl:forEach var="label" items="${applicationsDates}">

								<jstl:out value="{x: '${label.toString().split(' ')[0]}', y: ${acceptedApplications.get(label)}}" escapeXml="false"/>,
								//"<jstl:out value="${acceptedApplications.get(label)}"/>",

							</jstl:forEach>
						],
						borderColor: [
					          "#56d798"
					        ]
					},{	label:"<acme:message code="administrator.charts.form.applications.rejected"/>",
						data : [
							<jstl:forEach var="label" items="${applicationsDates}">
							<jstl:out value="{x: '${label.toString().split(' ')[0]}', y: ${rejectedApplications.get(label)}}" escapeXml="false"/>,
								//"<jstl:out value="${rejectedApplications.get(label)}"/>",

							</jstl:forEach>
						],
						borderColor: [
					          "#ff8397"
					        ]
					}
				]
		};
		
			
		var options = {
				scales : {

					xAxes:[{
						type:"time",
						distribution: "series",
					}],

					yAxes : [
						{
							ticks: {
								suggestedMin : 0.0,
								suggestedMax : 5.0
							}
						}
					]
				},
				legend : {
					display : true
				}
		};
	
		var canvas, context;
		
		canvas = document.getElementById("canvas1");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "line",
			data : data,
			options : options
		});
	});
</script>





<h2>
	<acme:message code="administrator.charts.form.title"/>
</h2>

<div>
	<canvas id="canvas2"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		
		var data = {
				labels: [
					<jstl:forEach var="label" items="${companiesSectors.keySet()}">
						"<jstl:out value="${label}"/>",
					</jstl:forEach>
				],
				datasets: [
					{	label:"Companies",
						data : [
							<jstl:forEach var="label" items="${companiesSectors.keySet()}">
								"<jstl:out value="${companiesSectors.get(label)}"/>",
							</jstl:forEach>
						],
						backgroundColor: [
							<jstl:forEach var="label" items="${companiesSectors.keySet()}">
								"#56d798",
							</jstl:forEach>
				        ]
					},{	label:"Investors",
						data : [
							<jstl:forEach var="label" items="${companiesSectors.keySet()}">
								"<jstl:out value="${investorsSectors.get(label)}"/>",
							</jstl:forEach>
						],
						backgroundColor: [
							<jstl:forEach var="label" items="${companiesSectors.keySet()}">
								"#ff8397",
							</jstl:forEach>
				          
				        ]
					}
				]
		};
		
		var options = {
				scales : {
					yAxes : [
						{
							ticks: {
								suggestedMin : 0.0,
								suggestedMax : 7.0
							}
						}
					]
				},
				legend : {
					display : true
				}
		};		
		
	
		var canvas, context;
	
		canvas = document.getElementById("canvas2");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>



<h2>
	<acme:message code="administrator.charts.form.title"/>
</h2>

<div>
	<canvas id="canvasJobs"></canvas>
	<canvas id="canvasApplications"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		
		var dataJobs = {
				labels: [ "Draft", "Published"
					
				],
				datasets: [
					{	label:"Jobs",
						data : [
							<jstl:forEach var="label" items="${jobsStatus.keySet()}">
								"<jstl:out value="${jobsStatus.get(label)}"/>",
							</jstl:forEach>
						],
						backgroundColor: [
								"#56d798","#ff8397"
				        ]
					}
				]
		};
		
		var dataApplications = {
				labels: [ "Accepted", "Pending", "Rejected"
					
				],
				datasets: [
					{	label:"Applications",
						data : [
							<jstl:forEach var="label" items="${applicationStatus.keySet()}">
								"<jstl:out value="${applicationStatus.get(label)}"/>",
							</jstl:forEach>
						],
						backgroundColor: [
							"#f38b4a", "#56d798", "#ff8397"
				        ]
					}
				]
		};
			
		var options = {
				scales : {
					yAxes : [
						{
							ticks: {
								suggestedMin : 0.0,
								suggestedMax : 7.0
							}
						}
					]
				},
				legend : {
					display : false
				}
		};
	
		var canvasJobs, contextJobs, canvasApplications, contextApplications;
	
		canvasJobs = document.getElementById("canvasJobs");
		contextJobs = canvasJobs.getContext("2d");
		new Chart(contextJobs, {
			type : "bar",
			data : dataJobs,
			options : options
		});
		
		canvasApplications = document.getElementById("canvasApplications");
		contextApplications = canvasApplications.getContext("2d");
		new Chart(contextApplications, {
			type : "bar",
			data : dataApplications,
			options : options
		});
	});
</script>



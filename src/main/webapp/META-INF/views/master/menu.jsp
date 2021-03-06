<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
		
			<acme:menu-suboption code="master.menu.anonymous.listJesus" action="/anonymous/gamez-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listJoseManuel" action="/anonymous/sanchez-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listJavier" action="/anonymous/navarro-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listJose" action="/anonymous/martin-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listIsaac" action="/anonymous/muniz-bulletin/list"/>
			
			<acme:menu-separator/>
						
			<acme:menu-suboption code="master.menu.anonymous.listAnnouncements" action="/anonymous/announcement/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listCompaniesRecord" action="/anonymous/company-records/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listInvestorRecord" action="/anonymous/investor-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.listTopCompanies" action="/anonymous/company-records/list-top"/>
			<acme:menu-suboption code="master.menu.anonymous.listTopInvestors" action="/anonymous/investor-record/list-top"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.listAnnouncement" action="/authenticated/announcement/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listCompaniesRecord" action="/authenticated/company-records/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listInvestorRecord" action="/authenticated/investor-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listChallenge" action="/authenticated/challenge/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listJobs" action="/authenticated/job/list"/>
			<acme:menu-suboption code="master.menu.authenticated.listMessageThreads" action="/authenticated/message-thread/list-mine"/>
			<acme:menu-suboption code="master.menu.authenticated.message-thread.create" action="/authenticated/message-thread/create"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/chart"/>
			<acme:menu-suboption code="master.menu.administrator.listAnnouncement" action="/administrator/announcement/list"/>
			<acme:menu-suboption code="master.menu.administrator.spam" action="/administrator/spam/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.companyRecords" action="/administrator/company-records/list"/>
			<acme:menu-suboption code="master.menu.administrator.investorRecord" action="/administrator/investor-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.listChallenge" action="/administrator/challenge/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.listRequestAuditor" action="/administrator/request-auditor/list"/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.application.list" action="/worker/application/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.sponsor.banner.list" action="/sponsor/commercial-banner/list-mine"/>
			<acme:menu-suboption code="master.menu.sponsor.nonCommercialBanner.list" action="/sponsor/non-commercial-banner/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.job.list" action="/employer/job/list-mine"/>
			<acme:menu-suboption code="master.menu.employer.application.list" action="/employer/application/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.job.list-mine" action="/auditor/job/list-mine"/>
			<acme:menu-suboption code="master.menu.auditor.job.list-not-mine" action="/auditor/job/list-not-mine"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/update" access="hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update" access="hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update" access="hasRole('Worker')"/>
			<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update" access="hasRole('Sponsor')"/>
			
			<acme:menu-suboption code="master.menu.user-account.become-auditor" action="/authenticated/request-auditor/create" access="!hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create" access="!hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create" access="!hasRole('Worker')"/>
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create" access="!hasRole('Sponsor')"/>
			
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

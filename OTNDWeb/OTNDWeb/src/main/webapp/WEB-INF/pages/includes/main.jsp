<c:set var="isAdmin" scope="session" value="${isAdmin}" />
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Income Details</a></li>
		<c:if test="${isAdmin}">
		<li><a href="#tabs-5">Pending Approvals</a></li>
		<li><a href="#tabs-4">Payroll Report</a></li>
		<li><a href="#tabs-3">Payroll Period</a></li>
		<li><a href="#tabs-2">Employee</a></li>

		</c:if>
	</ul>
	<div id="tabs-1" class="tab_div">
		<div class="load_pay">
			<label class="detailsLbl">Payroll Period</label> 
			<select id="pp_select">
				<option></option>
			</select> &nbsp;
			<input type="button" id="load_payroll" value="Load" />
		</div>
		<form id="income-form">
			<fieldset>
				<span class="detailSpan"> <label class="detailsLbl">Income Type</label>
					<select id="income_type" name="income_type" disabled="disabled" required>
						<option></option>
					</select>
				</span> <span class="detailSpan codeSpan"> <label class="detailsLbl">Code</label>
					<select id="income_code" name="income_code" disabled="disabled" required>
						<option></option>
					</select>
				</span> 
				<span class="detailSpan"> 
					<span id="amountSpan">
						<label class="detailsLbl" id="amtHrLbl">Amount </label> 
						<input id="amount_in" type="text" name="amount_in"  disabled="disabled" required/></span>
					<span id="dateSpan">
						<label class="detailsLbl">Date </label> 
						<input type="text" id="createDt_in" name="createDt_in" disabled="disabled" required/>
					</span>
				</span> 
				<span class="detailSpan detailRemarks">
					<label class="detailsLbl">Remarks </label> 
					<textarea id="txtRemarks" name="txtRemarks" class="text ui-widget-content" rows="3" cols="38"  disabled="disabled" required></textarea>
				</span> 
				<br class="clearfix" /> 
				<div id="action_btn">
				<input type="button" id="addPay_btn" value="Add" disabled="disabled"/> 
				<input type="reset" id="resetPay_btn" disabled="disabled"/>
			</div>
			</fieldset>
		</form>
			
			<hr class="seperator">

			<div id="tableSpace">
				<table class="display" id="resultGrid"></table>
				<input type="button" id="delete_btn" value="Delete" />
			</div>
	</div>
<c:if test="${isAdmin}">
	<div id="tabs-2" class="tab_div">
		<h2>Add Employee</h2>
		<form id="addUser_form">
			<fieldset>
				<span class="addSpan"> <label for="username" class="addLbl">Username</label>
					<input type="text" name="username" id="uname_user" required/>
				</span> <span class="addSpan"> <label for="empID" class="addLbl">Employee
						ID</label> <input type="text" name="empID" id="empID_user" required/>
				</span> <span class="addSpan"> <label for="firstname" class="addLbl">First
						Name</label> 
						<input type="text" name="firstname" id="firstname_user"	required />
				</span> <span class="addSpan"> <label for="lastname" class="addLbl">Last
						Name</label> 
						<input type="text" name="lastname" id="lastname_user" required />
				</span> 
				<span class="addSpan"> <label for="role" class="addLbl">Role</label>
					<select id="role_user" name="role_user" required>
						<option value="1">Admin</option>
						<option value="2">Regular</option>
					</select>
				</span> 
				<span class="addSpan"> 
					<label for="project" class="addLbl">Project</label> 
					<input type="text" name="project" id="project_user" value="" />
				</span> 
				<span class="addSpan"> 
					<label for="manager" class="addLbl">Manager</label> 
					<input type="text" name="manager" id="manager_user" value="" />
				</span>


				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<input type="button" id="addUser_btn" value="Submit" name="addUser_btn"/>
				<input type="reset" />
			</fieldset>
		</form>
	
	</div>
	<div id="tabs-3" class="tab_div">
		<div class="two_column_left two_column">
		<h2>Add Payroll Period</h2>
		<form id="payperiod_form">
			<fieldset>
				<span class="addSpan"> 
					<label for="payPeriod" class="addLbl">Payroll Period </label> 
					<input type="text" name="payPeriod" id="payperiod_add" required>
				</span> 
				<span class="addSpan"> 
					<label class="addLbl">Status</label>
					<select id="pp_status" name="pp_status" required>
						<option></option>
						<option value="Open">Open</option>
						<option value="Closed">Closed</option>
					</select>
				</span>
				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<input type="button" id="add_pperiod_btn" name="add_pperiod_btn" value="Add" />
			</fieldset>
		</form>
		
		</div>
		<div class="two_column_right two_column">
		<h2>Close Payroll Period</h2>

		<table class="display" id="openPayrollGrid"></table>
		<input type="button" id="close_pperiod_btn" value="Close" />
		</div>
	</div>
	<div id="tabs-4" class="tab_div">
		<div class="load_pay">
			<label class="detailsLbl">Payroll Period</label> <select
				id="pp_select_admin">
				<option></option>
			</select> &nbsp; <input type="button" id="load_payroll_admin" value="Load" />
		</div>
		<div id="inqTableSpace">
			<table class="display" id="report_grid"></table>
			<input type="button" id="generate_btn" value="Generate Excel Report" />
		</div>
	</div>
	
	<div id="tabs-5" class="tab_div">
		<div class="load_pay">
			<label class="detailsLbl">Payroll Period</label> 
			<select	id="pa_select_admin">
				<option></option>
			</select> &nbsp; <input type="button" id="load_pa_admin" value="Load" />
		</div>
		<div id="paTableSpace">
			<table class="display" id="pa_grid"></table>
			<input type="button" id="approve_btn" value="Approve" />
		</div>
	</div>
	</c:if>
</div>
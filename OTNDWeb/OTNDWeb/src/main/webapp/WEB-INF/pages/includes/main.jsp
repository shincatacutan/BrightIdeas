<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Income Details</a></li>
		<li><a href="#tabs-2">Add Employee</a></li>
		<li><a href="#tabs-3">Add Pay Period</a></li>
		<li><a href="#tabs-4">Payroll Report</a></li>
	</ul>
	<div id="tabs-1" class="tab_div">
		<div class="load_pay">
			<label class="detailsLbl">Payroll Period</label> 
			<select id="pp_select">	</select> &nbsp;
			<input type="button" id="load_payroll" value="Load" />
		</div>
		<form id="income-form">
			<fieldset>
				<span class="detailSpan"> <label class="detailsLbl">Income Type</label>
					<select id="income_type">
						<option>Select Type...</option>
					</select>
				</span> <span class="detailSpan codeSpan"> <label class="detailsLbl">Code</label>
					<select id="income_code">
						<option>Select Code...</option>
					</select>
				</span> 
				<span class="detailSpan"> 
					<span id="amountSpan">
						<label class="detailsLbl" id="amtHrLbl">Amount </label> 
						<input id="amount_in" type="text"/></span>
					<span id="dateSpan">
						<label class="detailsLbl">Date </label> 
						<input type="text" id="createDt_in"></input>
					</span>
				</span> 
				<span class="detailSpan detailRemarks">
					<label class="detailsLbl">Remarks </label> 
					<textarea id="txtRemarks" class="text ui-widget-content" rows="3" cols="38"></textarea>
				</span> 
			</fieldset>
			<br class="clearfix" /> 
			<input type="button" id="addPay_btn"
				value="Add" /> <input type="reset" />
			<hr class="seperator">

			<div id="tableSpace">
				<table class="display" id="resultGrid"></table>
				<input type="button" id="delete_btn" value="Delete" />
			</div>
		</form>
	</div>
	<div id="tabs-2" class="tab_div">
		<form id="addUser_form">
			<fieldset>
				<span class="addSpan"> <label for="username" class="addLbl">Username</label>
					<input type="text" name="username" id="uname_user" value="" />
				</span> <span class="addSpan"> <label for="empID" class="addLbl">Employee
						ID</label> <input type="text" name="empID" id="empID_user" value="" />
				</span> <span class="addSpan"> <label for="firstname" class="addLbl">First
						Name</label> <input type="text" name="firstname" id="firstname_user"
					value="" />
				</span> <span class="addSpan"> <label for="lastname" class="addLbl">Last
						Name</label> <input type="text" name="lastname" id="lastname_user"
					value="" />
				</span> 
				<span class="addSpan"> <label for="role" class="addLbl">Role</label>
					<select id="role_user">
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
				<input type="button" id="addUser_btn" value="Submit" />
			</fieldset>
		</form>
		<div class="dialog-fix">
			<div id="dialog-form" title="Update Details">
				<p class="validateTips">All form fields are required.</p>

				<form>
					<fieldset>
						<span class="addSpan"> <label for="name" class="addLbl">Title</label>
							<input type="text" name="title" id="title_modal" value=""
							class="text ui-widget-content ui-corner-all">
						</span> <span class="addSpan"> <label for="email" class="addLbl">Description</label>
							<textarea name="desc" id="desc_modal"
								class="text ui-widget-content ui-corner-all" rows="3" cols="38"></textarea>
						</span>
						<!-- Allow form submission with keyboard without duplicating the dialog button -->
						<input type="submit" tabindex="-1"
							style="position absolute; top -1000px">
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<div id="tabs-3" class="tab_div">
		<form id="payperiod_form">
			<fieldset>
				<span class="addSpan"> <label for="payPeriod" class="addLbl">Pay
						Period </label> <input type="text" name="payPeriod" id="payperiod_add"">
				</span> <span class="addSpan"> <label class="addLbl">Status</label>
					<select id="pp_status">
						<option value="Open">Open</option>
						<option value="Closed">Closed</option>
				</select>
				</span>
				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<input type="button" id="add_pperiod_btn" value="Add" />
			</fieldset>
		</form>
		<div id="inqTableSpace">
			<table class="display" id="inquiry_grid"></table>
		</div>
	</div>
	<div id="tabs-4" class="tab_div">
		<h4>Under Construction</h4>
		<p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus
			gravida ante, ut pharetra massa metus id nunc. Duis scelerisque
			molestie turpis. Sed fringilla, massa eget luctus malesuada, metus
			eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet
			fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam.
			Praesent in eros vestibulum mi adipiscing adipiscing. Morbi
			facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut
			posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis.
			Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem
			euismod felis, eu ornare leo nisi vel felis. Mauris consectetur
			tortor et purus.</p>
		<div id="inqTableSpace">
			<table cellpadding="0" cellspacing="0" border="0" class="display"
				id="inquiry_grid"></table>
		</div>
	</div>
</div>
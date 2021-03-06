$(function() {
	// getUser();
	initButtons();
	getPayPeriods("#pp_select");

	initAddPayDetails();
	initLoadPayrollBtn();
	initFunctionBtns()

	/* Admin Functions */
	initAdminAddUser();
	initAddPayPeriod();
	initPayrollTabs();
	initPendingApprovals();

	var amount = $("#amount_in");
	amount.blur(function(e) {
		var x = $(this).val();
		$(this).val(x.replace(",", ""));
	});

	$("#txtRemarks").on('change keyup paste', function() {
		var txtRemarks = $("#txtRemarks");
		var remarksLength = txtRemarks.val().length;
		var maxLength = 250;
		if (remarksLength > maxLength) {
			txtRemarks.val(txtRemarks.val().substring(0, maxLength));
		}
		$("#availableChar").html(250 - txtRemarks.val().length);
	});

});

jQuery.extend(jQuery.fn, {
	projects : function(data) {
		var input = $(this);
		input.find('option').remove().end();
		$.each(data, function(i, data) {
			input.append($('<option>', {
				value : data.id,
				text : data.code
			}));
		});

	}
});

var format = function(val) {
	return val.replace('\d+(\.\d{1,2})?')
}

var initPayrollTabs = function() {
	$("#tabs").tabs({
		activate : function(event, ui) {
			switch (ui.newPanel.selector) {
			case "#tabs-4":
				initLoadPayperiodsAdmin();
				initGenerateExcelBtn();
				break;
			case "#tabs-3":
				loadOpenPeriods();
				break;
			case "#tabs-1":
				getPayPeriods("#pp_select");
				break;
			case "#tabs-5":
				getPayPeriods("#pa_select_admin");
				break;
			default:
				// tab2
				getProjects();
			}
		}
	});
}

var getProjects = function() {
	//
	var select = $("#user_project");
	$.ajax({
		url : "/OTNDWeb/getProjects",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			select.projects(data);
		},
		error : function(e) {
			// console.log(e);
		}
	});
}

var initPendingApprovals = function() {
	$('#load_pa_admin').click(function(event) {
		var selectedOption = $('#pa_select_admin option:selected').text();
		if (selectedOption == "") {
			alert("Please select a period.");
			return;
		}
		loadIncomePeriodDetails(2, '#pa_grid');
		$('#approve_btn').css("visibility", "visible");
		$('#reject_btn').css("visibility", "visible");
	});
}

var loadOpenPeriods = function() {
	$.ajax({
		url : "/OTNDWeb/getOpenPayrolls",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			// console.log(data)
			loadToPayrollGrid(data, "#openPayrollGrid");
		},
		error : function(e) {
			// console.log(e);
		}
	});
}

var loadToPayrollGrid = function(data, tableID) {
	if ($.fn.dataTable.isDataTable(tableID)) {
		var table = $(tableID).DataTable();
		table.clear();
		table.rows.add(data);
		table.draw();
	} else {
		var table = $(tableID).dataTable({
			"data" : data,
			"columns" : [ {
				"title" : "Payroll Period",
				"data" : "period",
				"class" : "dt-left"
			}, {
				"title" : "Status",
				"class" : "dt-left",
				"data" : "status"
			} ]
		});

		$(tableID + ' tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('#close_pperiod_btn').click(function() {
			var newInstance = $(tableID).DataTable();
			var closeMe = newInstance.row('.selected').data();
			if (confirm("Close selected period?")) {
				var period = closeMe.period;

				$.ajax({
					url : "/OTNDWeb/closePayrollPeriod",
					type : "GET",
					data : {
						'payPeriod' : period
					},
					accept : 'application/json',
					success : function(data) {
						newInstance.row('.selected').remove().draw();
					},
					error : function(e) {
						// console.log(e);
					}
				});
			}
		});
	}

}

var initGenerateExcelBtn = function() {
	var payperiod = $("#pp_select_admin");
	var generateBtn = $('#generate_btn');

	generateBtn.on('click', function() {
		var dataGrid = $("#report_grid").DataTable();
		var size = dataGrid.rows()[0].length;

		if (size > 0) {
			window.location = "/OTNDWeb/download.do?payPeriod="
					+ payperiod.val();
		} else {
			alert("No data to generate.")
		}

	});
}

var initLoadPayrollBtn = function() {
	var payrollBtn = $('#load_payroll');

	payrollBtn.click(function(event) {

		// add button event onclick for load//
		$.ajax({
			url : "/OTNDWeb/getIncomeTypes",
			type : "POST",
			accept : 'application/json',
			success : function(data) {
				var incomeTypeInput = $('#income_type');
				incomeTypeInput.empty();
				incomeTypeInput.append($('<option>', {
					text : ""
				}));
				$.each(data, function(i, data) {
					incomeTypeInput.append($('<option>', {
						value : data,
						text : data
					}));
				});
			},
			error : function(e) {
				// console.log(e);
			}
		});

		if (typeof String.prototype.trim !== 'function') {
			String.prototype.trim = function() {
				return this.replace(/^\s+|\s+$/g, '');
			}
		}

		loadIncomePeriodDetails(0, '#resultGrid');

		var selected = $("#pp_select option:selected").text();
		var status = selected.indexOf('Closed');
		if (status > 0) {
			alert("Payroll period is already closed.");
			enableIncomeDetailForm(false);
			showHideButtonDelete(false);
		} else {
			enableIncomeDetailForm(true);
			showHideButtonDelete(true);
		}

	});

	$('#income_type').change(function(event) {
		loadIncomeCodesByType(this.value);
		changeInputByIncomeType(this.value);
		$("#full-desc").html("");
	})
}

var enableIncomeDetailForm = function(boolean) {
	var ids = [ "#income_type", "#income_code", "#amount_in", "#createDt_in",
			"#txtRemarks" ]

	$.each(ids, function(i, ids) {
		$(ids).prop("disabled", !boolean)
	});

	var btnid = [ "#addPay_btn", "#resetPay_btn" ]
	$.each(btnid, function(i, btnid) {
		$(btnid).button("option", "disabled", !boolean);
	});
}

var loadIncomePeriodDetails = function(detailLevel, tableID) {
	var payperiod;
	switch (detailLevel) {
	case 0: // regular user
		payperiod = $("#pp_select").val();
		break;
	case 1: // admin report generate
		payperiod = $("#pp_select_admin").val();
		break;
	case 2: // admin pending approvals
		payperiod = $("#pa_select_admin").val();
		break;
	default:

	}

	$.ajax({
		url : "/OTNDWeb/getIncomeDetails",
		type : "POST",
		data : {
			'payPeriod' : payperiod,
			'detailLevel' : detailLevel
		},
		accept : 'application/json',
		success : function(data) {
			paintTable(data, tableID);
		},
		error : function(e) {
			// console.log(e);
		}
	});
}

var initAddPayDetails = function() {
	jQuery.validator.setDefaults({
		debug : true,
		success : "valid"
	});
	$("#addPay_btn").click(
			function(event) {
				var incomeDetailsIds = [ "#income_type", "#income_code",
						"#amount_in", "#txtRemarks", "#createDt_in" ];
				var incomeForm = $("#income-form")
				incomeForm.validate({
					rules : {
						amount_in : {
							required : true,
							number : true
						}
					}
				});

				if (incomeForm.valid()) {
					var payperiod = $("#pp_select").val();
					var incomeType = $("#income_type").val();
					var incomeCode = $("#income_code").val();
					var detailValue = isDateEntry == true ? $("#createDt_in")
							.val() : $("#amount_in").val();
					var remarks = $("#txtRemarks").val();
					$.ajax({
						url : "/OTNDWeb/addPayrollDetails",
						type : "POST",
						data : {
							'payPeriod' : payperiod,
							'incomeType' : incomeType,
							'incomeCode' : incomeCode,
							'detailValue' : detailValue,
							'remarks' : remarks
						},
						accept : 'application/json',
						success : function(data) {
							// console.log(data);
							alert("Income detail is saved")
							$("#income-form")[0].reset();
							loadIncomePeriodDetails(0, '#resultGrid');
						},
						error : function(e) {
							// console.log(e);
						}

					});
				}

				event.preventDefault();
			});

}
var initAddPayPeriod = function() {

	$("#add_pperiod_btn").click(function(event) {
		var payPeriodForm = $('#payperiod_form');
		payPeriodForm.validate();

		if (payPeriodForm.valid()) {
			var payperiod = $("#payperiod_add").val();
			var status = $("#pp_status").val();
			$.ajax({
				url : "/OTNDWeb/addPayPeriod",
				type : "POST",
				data : {
					'payPeriod' : payperiod,
					'status' : status
				},
				accept : 'application/json',
				success : function(data) {
					alert("Payperiod added.")
					$("#payperiod_form")[0].reset();
					loadOpenPeriods();
				},
				error : function(e) {
				}

			});
		}

		event.preventDefault();
	});
}

var initLoadPayperiodsAdmin = function() {
	getPayPeriods("#pp_select_admin");
	$('#load_payroll_admin').click(function(event) {
		var selectedOption = $('#pp_select_admin option:selected').text();
		if (selectedOption == "") {
			alert("Please select a period.");
			return;
		}
		loadIncomePeriodDetails(1, '#report_grid');
		$('#generate_btn').css("visibility", "visible");
	});
}

var initAdminAddUser = function() {
	$("#addUser_btn").click(function(event) {
		var addUserForm = $('#addUser_form');
		addUserForm.validate();

		if (addUserForm.valid()) {
			var username = $("#uname_user").val();
			var empID = $("#empID_user").val();
			var firstname = $("#firstname_user").val();
			var lastname = $("#lastname_user").val();
			var role = $("#role_user").val();
			var project = $("#user_project").val();

			$.ajax({
				url : "/OTNDWeb/addUser",
				type : "POST",
				accept : 'application/json',
				data : {
					'ntID' : username,
					'empID' : empID,
					'firstName' : firstname,
					'lastName' : lastname,
					'roleId' : role,
					'project' : project
				},
				success : function(data) {
					alert("User added.")
					$('#addUser_form')[0].reset();
				},
				error : function(e) {
				}
			});
		}

	});

}

var getPayPeriods = function(id) {
	$.ajax({
		url : "/OTNDWeb/getPayPeriods",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			$(id).empty();
			$.each(data, function(i, data) {
				if (id == "#pp_select") {
					// if (data.status == "Open")
					$(id).append($('<option>', {
						value : data.period,
						text : data.period + " (" + data.status + ") "
					}));
				} else {
					$(id).append($('<option>', {
						value : data.period,
						text : data.period + " (" + data.status + ") "
					}));
				}

			});
		},
		error : function(e) {
			// console.log(e);
		}
	});

}
var isDateEntry;

var changeInputByIncomeType = function(incomeType) {
	if (incomeType == "LWOP") {
		hideDetailInput("dateSpan");
		isDateEntry = true;
	} else {
		hideDetailInput("amountSpan");
		isDateEntry = false;
		var amtHrLbl = document.getElementById("amtHrLbl");
		if (incomeType == "OT_ND") {
			amtHrLbl.innerHTML = "Hours";
		} else {
			amtHrLbl.innerHTML = "Amount";
		}
	}
}

var hideDetailInput = function(id) {
	if (id == "dateSpan") {
		$('#amountSpan').css("visibility", "hidden");
		$('#amountSpan').css("display", "none");
		$('#dateSpan').css("visibility", "visible");
		$('#dateSpan').css("display", "inline");
	} else {
		$('#dateSpan').css("visibility", "hidden");
		$('#dateSpan').css("display", "none");
		$('#amountSpan').css("visibility", "visible");
		$('#amountSpan').css("display", "inline");
	}
}

var loadIncomeCodesByType = function(incomeType) {
	$.ajax({
		url : "/OTNDWeb/getCodesByType",
		type : "POST",
		accept : 'application/json',
		data : {
			'incomeType' : incomeType
		},
		success : function(data) {
			var codeInput = $('#income_code');
			codeInput.empty();
			codeInput.append($('<option>', {
				text : ""
			}));
			$.each(data, function(i, data) {
				$('#income_code').append($('<option>', {
					value : data.id,
					text : data.desc
				}));
			});

			$('#income_code').change(
					function() {
						var val = this.value;
						var result = $.grep(data, function(e) {
							return e.id == val;
						});
						if (val != "" || null != val) {
							if (result.length != 0) {
								var fullDesc = null == result[0].fullDesc ? ""
										: result[0].fullDesc;
								$("#full-desc").html(fullDesc);
							}
						}
					});
		},
		error : function(e) {
			// console.log(e);
		}
	});
}
var getUser = function() {
	var network = new ActiveXObject("WScript.Network");
	var networkId = network.UserName;
	var networkId = "asanju3";

	$
			.ajax({
				url : "/OTNDWeb/getUser",
				type : "POST",
				accept : 'application/json',
				data : {
					"empID" : networkId
				},
				success : function(emp) {
					// console.log(emp)
					// $("#fullname").html(emp.firstName + " " + emp.lastName);
					// $("#empID").html(emp.empID);
					// $("#ntid").html(emp.networkID);
					// $("#manager").html(emp.manager);
					// $("#project").html(emp.project);
					// $('#load_payroll').button("option", "disabled", false);
				},
				error : function(e) {
					alert("User is not yet in the database. Please contact administrator.");
					$('#load_payroll').button("option", "disabled", true);

				}
			});
}

var initButtons = function() {
	// $( "#tabs" ).tabs();
	$(":button").button();
	$(":reset").button();
	$('#createDt_in').datepicker({
		maxDate : '0'
	});
	$('#payperiod_add').datepicker();
	$("#tabs").tabs();
	showHideButtonDelete(false);
	hideDetailInput("amountSpan");
	$('#generate_btn').css("visibility", "hidden");
	$('#approve_btn').css("visibility", "hidden");
	$('#reject_btn').css("visibility", "hidden");
}

var showHideButtonDelete = function(show) {
	if (show) {
		$('#delete_btn').css("visibility", "visible");
	} else {
		$('#delete_btn').css("visibility", "hidden");
	}
}

var paintTable = function(oData, tableID) {
	// console.log(oData)
	if ($.fn.dataTable.isDataTable(tableID)) {
		var table = $(tableID).DataTable();
		table.clear();
		table.rows.add(oData);
		table.draw();
	} else {
		var table = $(tableID).dataTable(
				{
					"data" : oData,
					"columns" : [
							{
								"title" : "Pay Period",
								"data" : "payrollPeriod",
								"class" : "dt-left",
								"render" : function(obj) {
									return '<span>' + obj.period.monthOfYear
											+ '/' + obj.period.dayOfMonth + '/'
											+ obj.period.year + '</span>'
								}
							},
							{
								"title" : "Type",
								"class" : "dt-left",
								"data" : "incomeType.id"
							},
							{
								"title" : "Description",
								"class" : "dt-left",
								"data" : "incomeType.desc"
							},
							{
								"title" : "Remarks",
								"class" : "dt-longer",
								"data" : "remarks",

							},
							{
								"title" : "Hours/Amount/Date",
								"class" : "dt-left",
								"data" : "prodHrsAmt"
							},
							{
								"title" : "Employee",
								"class" : "dt-left",
								"data" : "empId.networkID"
							},
							{
								"title" : "Team",
								"class" : "dt-left",
								"data" : "empId.project.code"
							},
							{
								"title" : "Create Date",
								"class" : "dt-left",
								"data" : "createDate",
								"render" : function(obj) {
									return '<span>' + obj.monthOfYear + '/'
											+ obj.dayOfMonth + '/' + obj.year
											+ '</span>'
								}
							}, {
								"title" : "Approval Status",
								"class" : "dt-left",
								"data" : "status"
							} ]
				});

		$(tableID + ' tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
	}
};

var initFunctionBtns = function() {
	$('#delete_btn').click(function() {
		var newInstance = $("#resultGrid").DataTable();
		var deletePayIds = newInstance.rows('.selected').data();
		if (deletePayIds.length == 0) {
			alert("Please select a row to delete.")
		} else {
			if (confirm("Delete selected item?")) {
				$.each(deletePayIds, function(key, value) {
					deletePayrollDetail(value["id"]);
					newInstance.row('.selected').remove().draw();
				});
			}
		}
	});

	$('#approve_btn').click(function() {
		approvePayroll(true);
	});

	$('#reject_btn').click(function() {
		approvePayroll(false);
	});
}

var approvePayroll = function(isApproved) {
	var newInstance = $("#pa_grid").DataTable();
	var approveIDs = newInstance.rows('.selected').data();
	var message = '';
	if (approveIDs.length != 0) {
		if (isApproved) {
			message = "Approve selected item?";
		} else {
			message = "Reject selected item?";
		}
		if (confirm(message)) {
			$.each(approveIDs, function(key, value) {
				approvePayrollDetail(value["id"], isApproved);
				newInstance.row('.selected').remove().draw();
			});
		}
	} else {
		var alertMsg;
		if (isApproved) {
			alertMsg = "approve";
		} else {
			alertMsg = "reject";
		}
		alert("Please select a row to " + alertMsg + ".");
	}

}

var approvePayrollDetail = function(incomeId, isApproved) {
	$.ajax({
		url : "/OTNDWeb/approveIncomeDetail",
		type : "POST",
		data : {
			"incomeID" : incomeId,
			"isApproved" : isApproved
		},
		accept : 'application/json',
		success : function(msg) {
			// console.log(msg)
		},
		error : function(e) {
			alert("Error encountered during detail approval.");
		}
	});
}

var deletePayrollDetail = function(incomeId) {
	$.ajax({
		url : "/OTNDWeb/deleteIncomeDetail",
		type : "POST",
		data : {
			"incomeID" : incomeId
		},
		accept : 'application/json',
		success : function(msg) {
		},
		error : function(e) {
			alert("Error encountered. Please refresh browser.");
		}
	});
}
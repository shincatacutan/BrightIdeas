$(function() {
	getUser();
	initButtons();
	getPayPeriods("#pp_select");
	
	initAddPayDetails();
	initLoadPayrollBtn();
	
	initUpdateDialog();
	
	/*Admin Functions*/
	initAdminAddUser();
	initAddPayPeriod();
	initLoadPayperiodsAdmin();
});

var initLoadPayrollBtn = function(){
	var payrollBtn = $('#load_payroll');
	
	payrollBtn.click(function(event) {
		var selectedOption = $('#pp_select option:selected').text();	
		var status = selectedOption.split(':')[0].trim();
		if(status=="Open"){
			loadIncomePeriodDetails(false, '#resultGrid');
			enableIncomeDetailForm(true);
		}else{
			alert("Please choose an valid payroll period.");
			enableIncomeDetailForm(false);
		}
	});
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

var loadIncomePeriodDetails = function(isAdmin, tableID){
	var payperiod;
	if(isAdmin){
		payperiod = $("#pp_select_admin").val();
	}else{
		payperiod = $("#pp_select").val();
	}
	
	$.ajax({
		url : "/OTNDWeb/getIncomeDetails",
		type : "POST",
		data : {'payPeriod' : payperiod, 'isAdmin':isAdmin},
		accept : 'application/json',
		success : function(data) {
			paintTable(data,tableID );
		},
		error : function(e) {
			//console.log(e);
		}
	});
}

var initAddPayDetails = function(){
	$("#addPay_btn").click(function(event) {
		var payperiod = $("#pp_select").val();
		var incomeType = $("#income_type").val();
		var incomeCode = $("#income_code").val();
		var detailValue = isDateEntry==true?$("#createDt_in").val():$("#amount_in").val();
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
				//console.log(data);
				alert("Income detail is saved")
				$("#income-form")[0].reset();
				loadIncomePeriodDetails(false, '#resultGrid');
			},
			error : function(e) {
				//console.log(e);
			}

		});
		event.preventDefault();
	});
	
}
var initAddPayPeriod = function(){
	$("#add_pperiod_btn").click(function(event) {
		var payperiod = $("#payperiod_add").val();
		var status = $("#pp_status").val();
		//console.log(event);
		$.ajax({
			url : "/OTNDWeb/addPayPeriod",
			type : "POST",
			data : {
				'payPeriod' : payperiod,
				'status' : status
			},
			accept : 'application/json',
			success : function(data) {
				//console.log(data);
				$("#payperiod_form")[0].reset();
			},
			error : function(e) {
				//console.log(e);
			}

		});
		event.preventDefault();
	});
}


var initLoadPayperiodsAdmin = function(){
	getPayPeriods("#pp_select_admin");
	$('#load_payroll_admin').click(function(event) {
		var selectedOption = $('#pp_select_admin option:selected').text();
		if(selectedOption=="Select Period..."){
			alert("Please select a period.");
			return;
		}
		loadIncomePeriodDetails(true, '#inquiry_grid');
	});
}

var initAdminAddUser = function() {
	$("#addUser_btn").click(function(event) {
		var username = $("#uname_user").val();
		var empID = $("#empID_user").val();
		var firstname = $("#firstname_user").val();
		var lastname = $("#lastname_user").val();
		var role = $("#role_user").val();
		var project = $("#project_user").val();
		var manager = $("#manager_user").val();

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
				'project': project,
				'manager' : manager
			},
			success : function(data) {
				//console.log(data);
				$('#addUser_form')[0].reset();
			},
			error : function(e) {
				//console.log(e);
			}
		});
	});

}


var getPayPeriods = function(id) {
	var title = $("#title_in").val();
	var author = $("#author_in").val();
	var date = $("#createDt_in").val();
	$.ajax({
		url : "/OTNDWeb/getPayPeriods",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			$.each(data, function (i, data) {
			    $(id).append($('<option>', { 
			        value: data.period,
			        text : data.status  +" : "+ data.period
			    }));
			});
		},
		error : function(e) {
			//console.log(e);
		}
	});
	
	//add button event onclick for load//
	$.ajax({
		url : "/OTNDWeb/getIncomeTypes",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			$.each(data, function (i, data) {
			    $('#income_type').append($('<option>', { 
			        value: data,
			        text : data
			    }));
			});
		},
		error : function(e) {
			//console.log(e);
		}
	});
	
	$('#income_type').change(function(event){
		//console.log(this.value)
		loadIncomeCodesByType(this.value);
		changeInputByIncomeType(this.value);
	})
}
var isDateEntry;

var changeInputByIncomeType = function(incomeType){
	//console.log(incomeType)
	if(incomeType=="LWOP"){
		hideDetailInput("dateSpan");
		isDateEntry = true;
	}else{
		hideDetailInput("amountSpan");
		isDateEntry = false;
		var amtHrLbl = document.getElementById("amtHrLbl");
		if(incomeType=="OT"){
			amtHrLbl.innerHTML = "Hours";
		}else{
			amtHrLbl.innerHTML = "Amount";
		}
	}
}


var hideDetailInput = function(id){
	if(id=="dateSpan"){
		$('#amountSpan').css("visibility", "hidden");
		$('#amountSpan').css("display", "none");
		$('#dateSpan').css("visibility", "visible");
		$('#dateSpan').css("display", "inline");
	}else{
		$('#dateSpan').css("visibility", "hidden");
		$('#dateSpan').css("display", "none");
		$('#amountSpan').css("visibility", "visible");
		$('#amountSpan').css("display", "inline");
	}
}
var loadIncomeCodesByType = function(incomeType){
	$.ajax({
		url : "/OTNDWeb/getCodesByType",
		type : "POST",
		accept : 'application/json',
		data: {'incomeType':incomeType},
		success : function(data) {
			var codeInput = $('#income_code');
			codeInput.empty();
			codeInput.append($('<option>', { 
		        text : "Select..."
		    }));
			$.each(data, function (i, data) {
			    $('#income_code').append($('<option>', { 
			        value: data.id,
			        text : data.desc
			    }));
			});
		},
		error : function(e) {
			//console.log(e);
		}
	});
}
var getUser = function() {
	$.ajax({
		url : "/OTNDWeb/getUser",
		type : "POST",
		accept : 'application/json',
		success : function(emp) {
			$("#fullname").html(emp.firstName + " " + emp.lastName);
			$("#empID").html(emp.empID);
			$("#ntid").html(emp.networkID);
			$("#manager").html(emp.manager);
			$("#project").html(emp.project);
		},
		error : function(e) {
			alert("Error encountered. Please refresh browser.");
		}
	});
}

var loadDataToUpdate = function() {
	var selected = $('#resultGrid').DataTable().rows('.selected').data();
	var rowData = selected[0];

	$("#title_modal").val(rowData["title"])
	$("#desc_modal").val(rowData["description"])
}

var initUpdateDialog = function() {
	var dialog, form,
	// From
	// http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
	emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/, title = $("#title_modal"), desc = $("#desc_modal"), allFields = $(
			[]).add(title).add(desc), tips = $(".validateTips");

	function updateTips(t) {
		tips.text(t).addClass("ui-state-highlight");
		setTimeout(function() {
			tips.removeClass("ui-state-highlight", 1500);
		}, 500);
	}

	function checkLength(o, n, min, max) {
		if (o.val().length > max || o.val().length < min) {
			o.addClass("ui-state-error");
			updateTips("Length of " + n + " must be between " + min + " and "
					+ max + ".");
			return false;
		} else {
			return true;
		}
	}

	function checkRegexp(o, regexp, n) {
		if (!(regexp.test(o.val()))) {
			o.addClass("ui-state-error");
			updateTips(n);
			return false;
		} else {
			return true;
		}
	}

	function updateLine() {
		//console.log("update user...")
		var valid = true;
		allFields.removeClass("ui-state-error");

		valid = valid && checkLength(title, "title", 3, 50);
		valid = valid && checkLength(desc, "desc", 6, 200);

		// valid = valid && checkRegexp( name, /^[a-z]([0-9a-z_\s])+$/i,
		// "Username may consist of a-z, 0-9, underscores, spaces and must begin
		// with a letter." );
		// valid = valid && checkRegexp( email, emailRegex, "eg. ui@jquery.com"
		// );
		// valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password
		// field only allow : a-z 0-9" );

		if (valid) {
			$("#users tbody").append(
					"<tr>" + "<td>" + title.val() + "</td>" + "<td>"
							+ desc.val() + "</td>" +
							// "<td>" + password.val() + "</td>" +
							"</tr>");
			dialog.dialog("close");
		}
		return valid;
	}

	dialog = $("#dialog-form").dialog({
		autoOpen : false,
		height : 300,
		width : 350,
		modal : true,
		buttons : {
			"Update" : updateLine,
			Cancel : function() {
				dialog.dialog("close");
			}
		},
		close : function() {
			form[0].reset();
			allFields.removeClass("ui-state-error");
		}
	});

	form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
		updateLine();
	});

	$("#update_btn").button().on("click", function() {
		loadDataToUpdate();
		dialog.dialog("open");
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

}

var showHideButtonDelete = function(show) {
	if (show) {	
		$('#delete_btn').css("visibility", "visible");
	} else {
		$('#delete_btn').css("visibility", "hidden");
	}
}

var paintTable = function(oData, tableID) {
	//console.log(oData)
	if ($.fn.dataTable.isDataTable(tableID)) {
		var table = $(tableID).DataTable();
		table.clear();
		table.rows.add(oData);
		table.draw();
	} else {
		var table = $(tableID).dataTable({
			"data" : oData,
			"columns" : [ {
				"title" : "Pay Period",
				"data" : "payrollPeriod",
				"class" : "dt-left",
				"render" : function(obj) {
					return '<span>' + obj.period.monthOfYear
					+ '/' + obj.period.dayOfMonth
					+ '/' + obj.period.year
					+ '</span>'
				}
			}, {
				"title" : "Type",
				"class" : "dt-left",
				"data" : "incomeType.id"
			}, {
				"title" : "Description",
				"class" : "dt-left",
				"data" : "incomeType.desc"
			}, {
				"title" : "Remarks",
				"class" : "dt-longer",
				"data" : "remarks",
				
			}, {
				"title" : "Hours/Amount/Date",
				"class" : "dt-left",
				"data" : "prodHrsAmt"
			}, {
				"title" : "Employee",
				"class" : "dt-left",
				"data" : "empId.networkID"
			}, {
				"title" : "Create Date",
				"class" : "dt-left",
				"data" : "createDate",
				"render":function(obj) {
					return '<span>' + obj.monthOfYear
					+ '/' + obj.dayOfMonth
					+ '/' + obj.year
					+ '</span>'
				}
			}]
		});

		
		$(tableID+' tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('#delete_btn').click(function() {
			var newInstance = tableID.DataTable();
			var deletePayIds = newInstance.rows('.selected').data();
			if (confirm("Delete selected item?")) {
				$.each(deletePayIds, function(key, value) {
					deletePayrollDetail(value["id"]); 
					newInstance.row('.selected').remove().draw();
				});
			}
		});
	}

	showHideButtonDelete(true);
};

var deletePayrollDetail = function(incomeId){
	$.ajax({
		url : "/OTNDWeb/deleteIncomeDetail",
		type : "POST",
		data : {"incomeID": incomeId },
		accept : 'application/json',
		success : function(msg) {
			console.log(msg)
		},
		error : function(e) {
			alert("Error encountered. Please refresh browser.");
		}
	});
}
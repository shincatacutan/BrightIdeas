$(function() {
	initButtons();
	handleSearch();
	handleAddInq();
	initUpdateDialog();
	handleViewInquiries();
	initAdminAddUser();
	initAddUpdate();
	initAutoComplete();
	initInquiryReplyModal();
	jQuery.validator.setDefaults({
		success : "valid"
	});

});

var replyButtons = function(){
	return {
		Submit : addReply,
		Cancel : function() {
			$("#reply-dialog-form").dialog("close");
		}
	}
}

var initInquiryReplyModal = function() {
	var dialog = $("#reply-dialog-form").dialog({
		autoOpen : false,
		height : 500,
		width : 580,
		modal : true,
		buttons : replyButtons,
		close : function() {
			form[0].reset();
		}
	});

	var form = dialog.find("form").on("submit", function(event) {
		event.preventDefault();
	});

	$("#reply_btn").button().customModalClick(dialog, false);
	$("#view_btn").button().customModalClick(dialog, true);
}
jQuery.extend(jQuery.fn, {
	categories : function(data) {
		var input = $(this);
		input.find('option').remove().end();
		input.append($('<option>', ""));
		$.each(data, function(i, data) {
			input.append($('<option>', {
				value : data.id,
				text : data.name
			}));
		});
	},

	customModalClick : function(dialog, readonly) {
		var button = $(this);
		button.on("click", function() {
			var selected = $('#inquiry_grid').DataTable().rows('.selected')
					.data();
			var rowData = selected[0];
			$("input#inqId").val(rowData.id);
			$("span#authorName").html(rowData.createUser);
			$("span#createDate").html(rowData.createDate);
			$("span#modifiedBy").html(rowData.updateUser);
			$("span#modifiedDate").html(rowData.updateDate);
			$("span#inquiryTitle").html(rowData.title);
			$("span#inquiryBody").html(rowData.body);
			$("textarea#reply_modal").val(rowData.reply);
			$("select#inq_status_in").val(rowData.status);
			
			if (readonly) {
				$("textarea#reply_modal").prop('readonly', true);
				$("select#inq_status_in").prop('disabled', true);
				dialog.dialog('option', {
					buttons : {
						Close : function() {
							dialog.dialog("close");
						}
					}
				});
			} else {
				$("textarea#reply_modal").prop('readonly', false);
				$("select#inq_status_in").prop('disabled', false);
				dialog.dialog('option', {
					buttons : replyButtons()
					}
				);
			}
			dialog.dialog("open");
		})
	}

});

var userList = [];

var addReply = function() {
	var id = $("input#inqId").val();
	var replyMsg = $("#reply_modal").val();
	var status = $("#inq_status_in").val();

	$.ajax({
		url : "/MOMLibrary/addReply",
		type : "POST",
		accept : 'application/json',
		data : {
			'id' : id,
			'reply' : replyMsg,
			'status' : status
		},
		success : function(data) {
			loadViewInquiries();
			$("#reply-dialog-form").dialog("close");
		},
		error : function(e) {
			// console.log(e);
		}
	});
}
var initAddUpdate = function() {
	$("#add_btn").click(function(event) {
		var addTab = $("#add_tab");
		addTab.validate();
		if (addTab.valid()) {
			$.ajax({
				url : "/MOMLibrary/addUpdate",
				type : "POST",
				accept : 'application/json',
				data : {
					'category' : $("#addCat_in").val(),
					'dateCascaded' : $("#createDt_in").val(),
					'detailedInfo' : $("#desc_in").val(),
					'status' : $("#status_in").val(),
					'title' : $("#title_in").val(),
					'link' : $("#link_in").val(),
					'author' : $("#author_in").val()
				},
				success : function(data) {
					alert("Update has been added.");
					addTab[0].reset();
				},
				error : function(e) {
					// console.log(e);
				}
			});
		}

	});
}

var initAutoComplete = function() {
	$.ajax({
		url : "/MOMLibrary/getAllUsers",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			$.each(data, function(i, val) {
				userList.push(val.lanID)
				$("#author_in").autocomplete({
					source : userList
				});
				$("#author_search").autocomplete({
					source : userList
				});
				$("#uploader_search").autocomplete({
					source : userList
				});
			});
		},
		error : function(e) {
			// console.log(e);
		}
	});

	$.ajax({
		url : "/MOMLibrary/getCategories",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			$('#searchCat_in').categories(data);
			$('#addCat_in').categories(data);
		},
		error : function(e) {
			// console.log(e);
		}
	});

}

var initAdminAddUser = function() {
	$("#addUser_btn").click(function(event) {
		var username = $("#uname_user").val();
		var empID = $("#empID_user").val();
		var firstname = $("#firstname_user").val();
		var lastname = $("#lastname_user").val();
		var role = $("#role_user").val();

		$.ajax({
			url : "/MOMLibrary/addUser",
			type : "POST",
			accept : 'application/json',
			data : {
				'lanID' : username,
				'empID' : empID,
				'firstName' : firstname,
				'lastName' : lastname,
				'roleId' : role
			},
			success : function(data) {
				// console.log(data);
				$('#addUser_form')[0].reset();
			},
			error : function(e) {
				// console.log(e);
			}
		});
	});

}

var handleViewInquiries = function() {
	$("#tabs").tabs({
		activate : function(event, ui) {
			if (ui.newPanel.selector == "#tabs-3") {
				loadViewInquiries();
			}
		}
	});
}

var loadViewInquiries = function() {

	$.ajax({
		url : "/MOMLibrary/getAllInquiry",
		type : "POST",
		accept : 'application/json',
		success : function(data) {
			// console.log(data);
			if ($.fn.dataTable.isDataTable('#inquiry_grid')) {
				var table = $('#inquiry_grid').DataTable();
				table.clear();
				table.rows.add(data);
				table.draw();
			} else {
				var table = $('#inquiry_grid').dataTable({
					"data" : data,
					"columns" : [ {
						"title" : "ID",
						"data" : "id",
						"class" : "dt-left"
					}, {
						"title" : "Title",
						"data" : "title",
						"class" : "dt-left"
					}, {
						"title" : "Message",
						"data" : "body",
						"class" : "dt-left"
					}, {
						"title" : "Author",
						"data" : "createUser",
						"class" : "dt-left"
					}, {
						"title" : "Create Date",
						"data" : "createDate",
						"class" : "dt-left",
						"type" : "date"

					}, {
						"title" : "Update User",
						"data" : "updateUser",
						"class" : "dt-left"
					}, {
						"title" : "Update Date",
						"data" : "updateDate",
						"class" : "dt-left",
						"type" : "date"

					}, {
						"title" : "Status",
						"data" : "status",
						"class" : "dt-left"

					} ]
				});

				$('#inquiry_grid tbody').on('click', 'tr', function() {
					if ($(this).hasClass('selected')) {
						$(this).removeClass('selected');
					} else {
						table.$('tr.selected').removeClass('selected');
						$(this).addClass('selected');
					}
				});

				$('#reply_btn').click(function() {
					var newInstance = $('#inquiry_grid').DataTable();
					var deletePayIds = newInstance.rows('.selected').data();

				});
			}
		},
		error : function(e) {
			// console.log(e);
		}

	});

}

var parseDate = function(obj) {
	return '<span>' + obj.monthOfYear + '/' + obj.dayOfMonth + '/' + obj.year
			+ '</span>'
}

var handleAddInq = function() {
	$("#add_inq_btn").click(function(event) {
		var title = $("#inq_title_in").val();
		var body = $("#inq_in").val();
		$.ajax({
			url : "/MOMLibrary/addInquiry",
			type : "POST",
			data : {
				'title' : title,
				'body' : body
			},
			accept : 'application/json',
			success : function(data) {
				// console.log(data);
				alert("Inquiry successfully added.")
				$("#view_tab")[0].reset();
			},
			error : function(e) {
				// console.log(e);
			}

		});
		event.preventDefault();
	});
}

var handleSearch = function() {
	$("#search_btn").click(function(event) {
		// alert("Handler for .click() called.");
		// var title = $("#title_in").val();
		// var author = $("#author_in").val();
		// var date = $("#createDt_in").val();
		$.ajax({
			url : "/MOMLibrary/searchMOM",
			type : "POST",
			// data : {
			// 'title' : title,
			// 'author' : author,
			// 'createDate' : date
			// },
			accept : 'application/json',
			success : function(data) {
				// console.log(data);
				paintTable(data)
			},
			error : function(e) {
				// console.log(e);
			}

		});
		event.preventDefault();
	});
}
var getUser = function() {

	// var WinNetwork = new ActiveXObject("WScript.Network");
	// var user = WinNetwork.UserName
	var user = "scatacut";
	$.ajax({
		url : "/MOMLibrary/getUser",
		type : "POST",
		accept : 'application/json',
		data : {
			"username" : user
		},
		success : function(emp) {
			// //console.log(emp);
			// $("#fullname").html(emp.firstName + " " + emp.lastName);
			// $("#empID").html(emp.empID);
		},
		error : function(e) {
			// console.log(e);
		}
	});
}

var loadDataToUpdate = function() {
	var selected = $('#resultGrid').DataTable().rows('.selected').data();
	var rowData = selected[0];

	$("#title_modal").val(rowData["title"])
	$("#desc_modal").val(rowData["detailedInfo"])
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

	showHideButtons(false);
};

var showHideButtons = function(show) {
	if (show) {
		$('#update_btn').css("visibility", "visible");
		$('#update_btn').css("display", "inline");
		$('#delete_btn').css("visibility", "visible");
		$('#delete_btn').css("display", "inline");
	} else {
		$('#update_btn').css("visibility", "hidden");
		$('#update_btn').css("display", "none");
		$('#delete_btn').css("visibility", "hidden");
		$('#delete_btn').css("display", "none");

	}
}

var paintTable = function(oData) {
	if ($.fn.dataTable.isDataTable('#resultGrid')) {
		var table = $('#resultGrid').DataTable();
		table.clear();
		table.rows.add(oData);
		table.draw();
	} else {
		var table = $('#resultGrid').dataTable({
			"data" : oData,
			"columns" : [ {
				"title" : "ID",
				"data" : "id",
				"class" : "dt-left"
			}, {
				"title" : "Title",
				"data" : "title",
				"class" : "dt-left"
			}, {
				"title" : "Category Name",
				"data" : "categoryName",
				"class" : "dt-left"
			}, {
				"title" : "Description",
				"data" : "detailedInfo",
				"class" : "dt-left"
			}, {
				"title" : "Link",
				"data" : "link",
				"class" : "dt-left",
				"render" : function(obj) {
					return '<a href="http://' + obj + '">' + obj + '</a>'
				}
			}, {
				"title" : "Uploader",
				"data" : "author",
				"class" : "dt-left",
				"type" : "date"
			}, {
				"title" : "Date Cascaded",
				"data" : "cascadedDate",
				"class" : "dt-left",
				"type" : "date"
			}, {
				"title" : "Status",
				"data" : "status",
				"class" : "dt-left"
			} ]
		});

		$('#resultGrid tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('#delete_btn').click(function() {
			var newInstance = $('#resultGrid').DataTable();
			var deletePayIds = newInstance.rows('.selected').data();
			if (confirm("Delete selected item?")) {
				$.each(deletePayIds, function(key, value) {
					// deletePayrollDetail(value["payid"]); ajax call here!
					// console.log("deleting..." + value["id"]);
					newInstance.row('.selected').remove().draw();
				});
			}
		});
	}

	showHideButtons(true);
};
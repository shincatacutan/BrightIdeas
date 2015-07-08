<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Search Updates</a></li>
		<li><a href="#tabs-2">Add Updates</a></li>
		<li><a href="#tabs-3">View Inquiry</a></li>
		<li><a href="#tabs-4">Add Inquiry</a></li>
	</ul>
	<div id="tabs-1" class="tab_div">
		<form id="search_tab">
			<fieldset>
				<span class="detailSpan"> <label class="detailsLbl">Title:</label>
					<input type="text" id="title_in"></input>
				</span> <span class="detailSpan"> <label class="detailsLbl">Author:</label>
					<input type="text" id="author_in"></input>
				</span> <span class="detailSpan"> <label class="detailsLbl">Create
						Date:</label> <input type="text" id="createDt_in"></input>
				</span>
			</fieldset>
			<br class="clearfix" /> <input type="button" id="search_btn"
				value="Filter" /> <input type="reset" />
			<hr class="seperator">

			<div id="tableSpace">
				<table class="display" id="resultGrid"></table>
				<input type="button" id="update_btn" value="Update" /> <input
					type="button" id="delete_btn" value="Delete" />
			</div>

		</form>


	</div>
	<div id="tabs-2" class="tab_div">
		<form id="add_tab">
			<fieldset>
				<span class="addSpan"> <label class="addLbl">Title</label> <input
					type="text" id="title_in"></input>
				</span> <span class="addSpan"> <label class="addLbl">Description</label>
					<textarea cols="40" rows="5" id="desc_in"></textarea></span> <span
					class="addSpan"> <label class="addLbl">File</label> <input
					type="file" id="file_in"></input>
				</span>
			</fieldset>
			<br class="clearfix" /> <input type="button" id="add_btn"
				value="Add" /> <input type="reset" />
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
							style="position: absolute; top: -1000px">
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<div id="tabs-3" class="tab_div">
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
	<div id="tabs-4" class="tab_div">
		<form id="view_tab">
			<fieldset>
				<span class="addSpan"> <label class="addLbl">Title</label> <input
					type="text" id="inq_title_in"></input>
				</span> <span class="addSpan"> <label class="addLbl">Inquiry</label>
					<textarea cols="40" rows="5" id="inq_in"></textarea></span>
			</fieldset>
			<br class="clearfix" /> <input type="button" id="add_inq_btn"
				value="Add" /> <input type="reset" />
		</form>

	</div>
</div>
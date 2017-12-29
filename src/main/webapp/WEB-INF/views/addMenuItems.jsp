<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html class="js">
<head>
<title>Kafeneio</title>
<link rel="stylesheet" href="static/css/kafeneio.css"></link>
<link rel="stylesheet" href="static/css/bootstrap.css"></link>
 <link rel="stylesheet" href="static/css/font-awesome.min-4.7.0.css"></link>

<link href="static/css/app.css" rel="stylesheet"></link>
<link href="./static/css/theme.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="static/css/free-jqgrid/4.14.1/ui.jqgrid.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
<link href = "static/css/ui/1.10.4/jquery-ui.css" rel = "stylesheet">
<link href="static/css/pnotify.custom.css" rel="stylesheet"></link>

<script src="static/js/app.js" /></script>
<script src="static/JSlib/jquery/1.12.4/jquery.min.js"></script>
<script src="static/JSlib/jquery.validate.js"></script>

<script src="static/JSlib/twitter-bootstrap/3.3.7/bootstrap.min.js"></script>
<script src="static/JSlib/moment-2.10.6.min.js"></script> 

<script src="static/JSlib/free-jqgrid/4.14.1/jquery.jqgrid.min.js"></script>
<script src="static/JSlib/bootstrap-datetimepicker-4.17.37.min.js"></script>
<script src="static/JSlib/pnotify.custom.js" /></script>

<script src="static/JSlib/addMenuItems.js"></script>
</head>
<form:form action="" id = "foodItems" commandName="foodCategory">
	<div class="container">
		<%@include file="menu.jsp"%>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<div class="widget-content">
					<fieldset>
							<div class="form-group">
							<div class="col-md-6">
								<label for="prepended-input" class="control-label">Category</label>
								<form:select path="id" class="form-control" id="category"
									required="required">
									<option value="0">SELECT</option>
									<form:options items="${categoryList}" itemValue="id" itemLabel="foodDesc" />
								</form:select>
							</div>

							<div class="col-md-6">
								<label for="prepended-input" class="control-label">Status</label>
								<form:select path="id" class="form-control" id="status">
									<option value="1">Active</option>
									<option value="0">Inactive</option>
									<%-- <form:options items="${categoryList}" itemValue="id" itemLabel="foodDesc" /> --%>
								</form:select>
							</div>
							
							</div>
							
							<div class="form-group">
								<div class="col-md-6">
									<label for="normal-field" class="control-label">Item
										Code</label> <input type="text" name="foodItemCode" 
										placeholder="" class="form-control"
										id="foodItemCode" required>
								</div>
							
								<div class="col-md-6">
									<label for="normal-field" class="control-label">Item
										Description</label> <input type="text" name="foodItemDesc" 
										placeholder="" class="form-control"
										id="foodItemDesc" required>
								</div>
							
							</div>
						<div class="form-group">
							<div class="col-md-6">
									<label for="prepended-input" class="control-label">Amount</label>
									<input type="text" class="form-control" name="amount"
										id="amount" placeholder="" required>
								</div>
									
						</div>
						
					</fieldset>
					<div class="form-actions">
						<div>
							<button class="btn btn-default" id= "addFoodItemsbutton" type="button" >Add</button>
							<button class="btn btn-default" id= "searchAndEditFoodItemsbutton" type="button" onclick = "searchAndEditFoodItems()" >Search & Edit</button>
			
						</div>
					</div>
				</div>
				<table id="foodItemsGrid"></table>
				<br>
				<table id="editFoodItemsGrid"></table>
				 <div id="pager" style="height: 50;"></div>
				<br>
				<div style="text-align: left;">
					<button class="btn btn-default" type="button"  onclick="saveFoodItems()">Save</button>
					<button class="btn btn-default" type="button" onclick="">Clear</button>
				</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>
</form:form>

<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="searchedFoodItemsModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Edit Food Items</h4>
        </div>
        <div class="modal-body">
        <fieldset>
						<input type="text" id="foodItemsRowId" name="foodItemsRowId" />
						<div class="form-group">
							<div class="col-md-6">
								<label for="normal-field" class="control-label">Item
									Code</label> <input type="text" name="code" placeholder=""
									class="form-control" id="code" required>
							</div>

							<div class="col-md-6">
								<label for="normal-field" class="control-label">Item
									Description</label> <input type="text" name="description"
									placeholder="" class="form-control" id="description" required>
							</div>
														
							<div class="form-group">
							<div class="col-md-6">
									<label for="prepended-input" class="control-label">Amount</label>
									<input type="text" class="form-control" name="amount"
										id="amount" placeholder="" required>
								</div>									
						</div>
						
							<div class="col-md-6">
								<label for="prepended-input" class="control-label">
								Status</label> 
								<select path="id" class="form-control"
									id="itemStatusModal" required="required">
									<option value="1">Active</option>
									<option value="0">Inactive</option>
								</select>
							</div>
							
						</div>
					</fieldset>
		 
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"  id= "updateUnitButton" onclick="updateFoodItem()">Update</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" id="cancelFoodItemButton" >Close</button>
        </div>
      </div>      
    </div>
  </div>
</div>
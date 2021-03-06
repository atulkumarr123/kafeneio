<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html class="js">
<head>
<title>Inventory Rules</title>

<title><spring:message code="kafeneio.main.title"/></title>
<link rel="stylesheet" href="static/css/bootstrap.css"></link>
<link rel="stylesheet" href="static/css/font-awesome.min-4.7.0.css"></link>
<link href="static/css/app.css" rel="stylesheet"></link>
<link href="./static/css/theme.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="static/css/free-jqgrid/4.14.1/ui.jqgrid.min.css">
<link href = "static/css/bootstrap-datetimepicker-4.17.37.min.css" rel = "stylesheet">
<link href = "static/css/ui/1.10.4/jquery-ui.css" rel = "stylesheet">
<link href="static/css/pnotify.custom.css" rel="stylesheet"></link>
<link href="static/css/bootstrap-tagsinput.css" rel="stylesheet"></link>

<link rel="stylesheet" href="static/css/kafeneio.css"></link>

<script src="static/js/app.js" /></script>
<script src="static/JSlib/jquery/1.12.4/jquery.min.js"></script>
<script src="static/JSlib/jquery.validate.js"></script>
<script src="static/JSlib/twitter-bootstrap/3.3.7/bootstrap.min.js"></script>
<script src="static/JSlib/moment-2.10.6.min.js"></script> 
<script src="static/JSlib/free-jqgrid/4.14.1/jquery.jqgrid.min.js"></script>
<script src="static/JSlib/bootstrap-datetimepicker-4.17.37.min.js"></script>
<script src="static/JSlib/pnotify.custom.js" /></script>
<script src="static/JSlib/bootstrap-tagsinput.js" /></script>
<script src="static/JSlib/twitter-bootstrap/waitingfor.js"></script>

<script src="static/JSlib/inventory.js"></script>
</head>
<form:form action="" name="inventory" commandName = "inventory" >
 <input type="hidden" name="currentDate" id="currentDate" value="${currentDate}"/>
	<div class="container">
		<%@include file="../menu.jsp"%>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-10">
						<div class="page-header-kafeneio" ><label class="control-label"><h3>Inventory Rules</h3></label></div>
			
				<div class="widget-content">
					<fieldset>
					<input type="hidden" id="inventoryRowId" name="inventoryRowId"/>
						
							<div class="form-group">
								
						 <div class="col-md-6">
								<label for="prepended-input" class="control-label">Food Item</label>
								<form:select path="id" class="form-control" id="foodItems" required="required">
									<option value="0">SELECT</option>
									<form:options items="${foodItemsList}" itemValue="id" itemLabel="foodItemDesc" />
								</form:select>
							</div>
						 	
							 <div class="col-md-6">
								<label for="prepended-input" class="control-label">Raw Material</label>
								<form:select path="id" class="form-control" id="rawMaterialonInv" required="required">
									<option value="0">SELECT</option>
									<form:options items="${rawMaterialList}" itemValue="id" itemLabel="rawMaterialDesc" />
								</form:select>
							</div>
							 
							</div>
						<div class="form-group">
						<%-- 
						<div class="col-md-6">
								<label for="prepended-input" class="control-label">Units</label>
								<form:select path="id" class="form-control" id="unitsOnInv" required="required">
									<option value="0">SELECT</option>
									<form:options items="${unitList}" itemValue="id" itemLabel="description" />
								</form:select>
							</div>
							 --%>
							<div class="col-md-6">
									<label for="prepended-input" class="control-label" name="quantity">Quantity</label>
									<input type="text" class="form-control" name="quantity"
										id="quantity" placeholder="" required>
								</div>
								
							<div class="col-md-6">
									<label for="prepended-input" class="control-label" name="remarks">Remarks</label>
									<textarea class="form-control" name="remarks" id="remarks"></textarea>
								</div>
								</div>
								</fieldset>
								<div class="form-actions">
						<div>
							<button class="btn btn-default" id= "addInventoryButton" type="button" >Add</button>
							<button class="btn btn-default" id= "searchAndEditInventoryButton" type="button" onclick = "searchAndEditInventory()" >Search & Edit</button>
						</div>
					</div>
						</div>
				
				<table id="inventoryGrid"></table>
				<table id="searchAndEditInventoryGrid"></table>
				<br>
				<div style="text-align: left;">
					<button class="btn btn-default" type="button"  onclick="saveInventory()" id="saveInventoryButton">Save</button>
					<button class="btn btn-default" type="button" onclick="clearInventory()" id= "clearRInventoryButton">Clear</button>
				</div>
			</div>
			
			</div>
			<div class="col-lg-1"></div>
		</div>


<div class="container">
  <div class="modal fade" id="myModalInventory" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Edit Inventory</h4>
        </div>
        <div class="modal-body">
        <fieldset>
        <div class="form-group">
								
						 <div class="col-md-6">
								<label for="prepended-input" class="control-label">Food Item</label>
								<form:select path="id" class="form-control" id="foodItemModalInv" required="required">
									<option value="0">SELECT</option>
									<form:options items="${foodItemsList}" itemValue="id" itemLabel="foodItemDesc" />
								</form:select>
							</div>
						 	
							 <div class="col-md-6">
								<label for="prepended-input" class="control-label">Raw Material</label>
								<form:select path="id" class="form-control" id="rawMaterialModalInv" required="required">
									<option value="0">SELECT</option>
									<form:options items="${rawMaterialList}" itemValue="id" itemLabel="rawMaterialDesc" />
								</form:select>
							</div>
							 
							</div>
						<div class="form-group">
						
						<div class="col-md-6">
								<label for="prepended-input" class="control-label">Units</label>
								<form:select path="id" class="form-control" id="unitsModalInv" required="required">
									<option value="0">SELECT</option>
									<form:options items="${unitList}" itemValue="id" itemLabel="description" />
								</form:select>
							</div>
							
							<div class="col-md-6">
									<label for="prepended-input" class="control-label" name="quantity">Quantity</label>
									<input type="text" class="form-control" id="quantityModalInv"
										id="quantity" placeholder="" required>
								</div>
								
							<div class="col-md-6">
									<label for="prepended-input" class="control-label" name="remarks">Remarks</label>
									<textarea class="form-control" name="remarks" id="remarksModalInv"></textarea>
								</div>
								</div>
								</fieldset>
						        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"  id= "updateInventoryButton" onclick="updateInventory()">Update</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" id="cancelInventoryButton" >Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
</form:form>
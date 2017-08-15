<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(function() {
	var ctx = $("#contextPath").val();
	//var jsonData = JSON.stringify({ category : $("#category").val() });
	$('#getFood').click(function() {
		$.ajax({
			url : ctx+"/food",
			data : { category : $("#category").val()},
			success : function(responseText) {
				alert(JSON.stringify(responseText));
				$('#outputLabel').text(JSON.stringify(responseText));
			},
			error:function(responseText) {
				alert("error"+responseText);
				$('#outputLabel').text("Error");
			}
				
		});
	});
});
</script>
	
<form action="">
                <div class="row">
                
                <div class="col-lg-3">
                </div>
                 <div class="col-lg-6">
              <div class="widget">
            <div class="widget-header">
             <div class="errorText"></div>
              <div></div>
            </div>
            <div class="widget-content">
                <fieldset>
                    <div class="control-group">
                  <div class="col-md-4">
                    <label for="normal-field" class="control-label">SELECT A Category</label>
                    </div>
                    <div class="col-md-8">
                    <div class="form-group">
         
                    <select  class="form-control" style="width:100%; height:47px;"  name="position" id="category">
                    <option value ="" selected>SELECT Category</option>
						<c:forEach items="${foodList}" var="item">
						<option value="${item.foodCode}">${item.foodDesc}</option>
						</c:forEach>
                        </select>
                        
                    </div>
                    </div>
                  </div>
                  
                                  </fieldset>
                <div class="form-actions">
                  <div>
<!--                     <input type="submit" class="btn btn-primary"  value="Get Food"/>
 -->                    <input type="button" id="getFood" class="btn btn-primary"  value="Get Food Ajax"/>
                    <button class="btn btn-default" type="button">Cancel</button>
                  </div>
                   <label id="outputLabel" class="control-label"></label>
                </div>
          
            </div>
          </div>
          </div>
          </div> 
</form>

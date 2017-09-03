$( document ).ready(function() {
   /* $("#expensesGrid").jqGrid({
        colModel: [
        	{ name: "orderNo", label: "Order No",  align: "center"},
            { name: "amount", label: "Amount",  align: "center" },
            { name: "orderDetails.foodDesc", label: "Food Description", editable: true, align: "center" },
            { name: "orderDetails.quantity", label: "Quantity",  editable: true,align: "center" },
        ],
        data: [
        	 
                {"id":"12345","orderNo":"111","amount":"1111","orderDetails":{"foodDesc":"Cheese", "quantity": "11" }}, 
                {"id":"12345","orderNo":"111","amount":"1111","orderDetails":{"foodDesc":"Cheese", "quantity": "11" }}, 
                {"id":"12345","orderNo":"111","amount":"1111","orderDetails":{"foodDesc":"Cheese", "quantity": "11" }}, 
                {"id":"12345","orderNo":"111","amount":"1111","orderDetails":{"foodDesc":"Cheese", "quantity": "11" }}, 
            
         ],
        guiStyle: "bootstrap",
        iconSet: "fontAwesome",
        rownumbers: false,
        sortname: "invdate",
        sortorder: "desc",
        caption: "Order Details",       
        height: 'auto',
       
      
    });*/
	
	  $(function () {
          'use strict';
          var myData = {
                  _id: "509403957ae7d3929edcb812",
                  name: "MYBOOK",
                  layout: {
                      chapters: [
                          {
                              name: "myfirstchapter",
                              sequence: 1,
                              title: "My First Chapter",
                              files: [
                                  { filetype: "tex", name: "myfirstfile" },
                                  { filetype: "tmpl", name: "myfileb" }
                              ]
                          },
                          {
                              name: "mysecondchapter",
                              sequence: 2,
                              title: "My Second Chapter",
                              files: [
                                  { filetype: "tex", name: "myintro" },
                                  { filetype: "tex", name: "myfilec" }
                              ]
                          }
                      ]
                  }
              },
              $grid = $("#orderReportGrid");

          $grid.jqGrid({
              datatype: "local",
              data: myData.layout.chapters,
              colNames: ["Sequence", "Name", "Title"],
              colModel: [
                  {name: "sequence", width: 65, key: true },
                  {name: "name", width: 150 },
                  {name: "title", width: 150}
              ],
              rowNum: 10,
              rowList: [5, 10, 20],
              pager: "#pager",
              gridview: true,
              ignoreCase: true,
              rownumbers: true,
              sortname: "sequence",
              viewrecords: true,
              height: "auto",
              subGrid: true,
              subGridRowExpanded: function (subgridId, rowid) {
                  var subgridTableId = subgridId + "_t";
                  $("#" + subgridId).html("<table id='" + subgridTableId + "'></table>");
                  $("#" + subgridTableId).jqGrid({
                      datatype: "local",
                      data: $(this).jqGrid("getLocalRow", rowid).files,
                      colNames: ["Name", "Filetype"],
                      colModel: [
                        {name: "name", width: 130, key: true},
                        {name: "filetype", width: 130}
                      ],
                      height: "100%",
                      rowNum: 10,
                      sortname: "name",
                      idPrefix: "s_" + rowid + "_"
                  });
              }
          });
          $grid.jqGrid("navGrid", "#pager", {add: false, edit: false, del: false});
      });

    
});



$( document ).ready(function() {
    $('#fromDateTimePicker').datetimepicker({
    	 format: 'DD-MM-YYYY'
    });
    $('#toDateTimePicker').datetimepicker({
   	 	format: 'DD-MM-YYYY'
   });
  
});



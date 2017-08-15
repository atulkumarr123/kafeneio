mainApp.service('commonService', function() {
	var _dataDetails = [];
	this.setdataDetails = function(dataDetails) {
		_dataDetails = dataDetails;
	}
	this.getdataDetails = function() {
		return _dataDetails;
	}
});
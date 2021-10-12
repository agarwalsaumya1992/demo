var app = angular.module('app', []);

app.controller('MyController', ['$scope', 'MyService', function($scope, MyService) {


	$scope.reset = function() {
		$scope.product = null;
		$scope.buttontype = "create";
		$scope.getRecords();
		
    
	};

	$scope.pickRecord = function(c) {

		$scope.buttontype = "update";
		var temp = JSON.parse(JSON.stringify(c));
		$scope.product = temp;
		
	};

	$scope.getRecords = function() {

		MyService.getRecords()
			.then(function success(response) {
				$scope.productList = response.data.list;
			},
				function error(response) {
					$scope.message = '';
					$scope.errorMessage = response.data.message;
				});
	};


	$scope.reset();

	$scope.createRecord = function() {

		var product = JSON.stringify($scope.product);

		MyService.createRecord($scope.myFile,product)
			.then(function success(response) {
				$scope.message = response.data.message;
				$scope.errorMessage = '';
				$scope.reset();
			},
				function error(response) {
					$scope.errorMessage = response.data.message;
					$scope.message = '';
				});
	};

	$scope.updateRecord = function() {
	
		if ($scope.myFile!=null)
		{
		MyService.updateFile($scope.product.photo, $scope.myFile)
		.then(function success(response) {
				$scope.message = response.data.message;
				$scope.errorMessage = '';
			},
			function error(response) {
					$scope.errorMessage = response.data.message;
					$scope.message = '';

			});
		}
		
		MyService.updateRecord($scope.product)
			.then(function success(response) {
				$scope.message = $scope.message +" , "+ response.data.message;
				$scope.reset();
			},
			function error(response) {
					$scope.errorMessage =$scope.errorMessage +" , "+ response.data.message;

			});
	};




	$scope.deleteRecord = function(id) {
		MyService.deleteRecord(id)
			.then(function success(response) {
				$scope.message = response.data.message;
				$scope.errorMessage = '';
				$scope.reset();
			},
				function error(response) {
					$scope.errorMessage = response.data.message;
					$scope.message = '';

				});
	};

}]);


app.directive('fileModel', ['$parse', function($parse) {
	return {
		restrict: 'A',
		link: function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
}]);


app.service('MyService', ['$http', function($http) {


	this.getRecords = function getRecords() {
		return $http({
			method: 'GET',
			url: 'products'
		});
	}

	this.createRecord = function createRecord(file,product) {

		var fd = new FormData();
		fd.append('file', file);
		fd.append('product', product);

		return $http({
			method: 'POST',
			url: 'products',
			data: fd,
			headers: { 'Content-Type': undefined },
			transformRequest: angular.identity
		});
		
	}

	this.deleteRecord = function deleteRecord(id) {
		return $http({
			method: 'DELETE',
			url: 'products/' + id
		});
	}

	this.updateRecord = function updateRecord(product) {
		return $http({
			method: 'PUT',
			url: 'products',
			data: product
		});
	}
	
	this.updateFile = function updateFile(filename,file) {

		var fd = new FormData();
		fd.append('file', file);
		fd.append('filename', filename);

		return $http({
			method: 'POST',
			url: 'products/file',
			data: fd,
			headers: { 'Content-Type': undefined },
			transformRequest: angular.identity
		});
		
	}

}]);




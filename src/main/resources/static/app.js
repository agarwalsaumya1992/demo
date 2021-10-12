var app = angular.module('app', []);

app.controller('CustomerController', ['$scope', 'CustomerService', function($scope, CustomerService) {


	$scope.reset = function() {
		$scope.customer = null;
		$scope.buttontype = "create";
		$scope.getCustomers();
		
    
	};

	$scope.pickCustomer = function(c) {

		$scope.buttontype = "update";
		var temp = JSON.parse(JSON.stringify(c));
		$scope.customer = temp;
		
	};

	$scope.getCustomers = function() {

		CustomerService.getCustomers()
			.then(function success(response) {
				$scope.customerList = response.data.list;
			},
				function error(response) {
					$scope.message = '';
					$scope.errorMessage = response.data.message;
				});
	};


	$scope.reset();

	$scope.createCustomer = function() {

		var customer = JSON.stringify($scope.customer);

		CustomerService.createCustomer($scope.myFile,customer)
			.then(function success(response) {
				console.log(response.data);
				$scope.message = response.data.message;
				$scope.errorMessage = '';
				$scope.reset();
			},
				function error(response) {
					console.log(response.data);
					$scope.errorMessage = response.data.message;
					$scope.message = '';
				});
	};

	$scope.updateCustomer = function() {
		console.log($scope.myFile);
		if ($scope.myFile!=null)
		{
		console.log($scope.myFile);
		CustomerService.updateFile($scope.customer.photo, $scope.myFile)
		.then(function success(response) {
				$scope.message = response.data.message;
				$scope.errorMessage = '';
			},
			function error(response) {
					$scope.errorMessage = response.data.message;
					$scope.message = '';

			});
		}
		CustomerService.updateCustomer($scope.customer.id, $scope.customer)
			.then(function success(response) {
				$scope.message = $scope.message +" , "+ response.data.message;
				$scope.reset();
			},
			function error(response) {
					$scope.errorMessage =$scope.errorMessage +" , "+ response.data.message;

			});
	};




	$scope.deleteCustomer = function(id) {
		CustomerService.deleteCustomer(id)
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


app.service('CustomerService', ['$http', function($http) {


	this.getCustomers = function getCustomers() {
		return $http({
			method: 'GET',
			url: 'customers'
		});
	}

	this.createCustomer = function createCustomer(file,customer) {

		var fd = new FormData();
		fd.append('file', file);
		fd.append('customer', customer);

		return $http({
			method: 'POST',
			url: 'customers',
			data: fd,
			headers: { 'Content-Type': undefined },
			transformRequest: angular.identity
		});
		
	}

	this.deleteCustomer = function deleteCustomer(id) {
		return $http({
			method: 'DELETE',
			url: 'customers/' + id
		});
	}

	this.updateCustomer = function updateCustomer(id, customer) {
		return $http({
			method: 'PUT',
			url: 'customers/' + id,
			data: customer
		});
	}
	
	this.updateFile = function updateFile(filename,file) {

		var fd = new FormData();
		fd.append('file', file);
		fd.append('filename', filename);

		return $http({
			method: 'POST',
			url: 'customers/file',
			data: fd,
			headers: { 'Content-Type': undefined },
			transformRequest: angular.identity
		});
		
	}

}]);




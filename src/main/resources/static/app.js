var app = angular.module('app',[]);

app.controller('CustomerController', ['$scope','CustomerService', function ($scope,CustomerService) {


 	$scope.reset = function () {
        $scope.customer = null;
        $scope.buttontype = "create";
		$scope.getCustomers();
    };
    
     $scope.pickCustomer = function (c) {

        $scope.buttontype = "update";
        var temp = JSON.parse(JSON.stringify(c));
        $scope.customer = temp;
    };
	  
	$scope.getCustomers = function () {
		
        CustomerService.getCustomers()
          .then(function success(response){
              $scope.customerList = response.data.list;
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = response.data.message;
          });
    }
	  
	
	$scope.reset();
	
	 $scope.createCustomer = function () {
        
            CustomerService.createCustomer($scope.customer)
              .then (function success(response){
              console.log(response.data);
                  $scope.message = response.data.message;
                  $scope.errorMessage = '';
                  $scope.reset();
              },
              function error(response){
              console.log(response.data);
                  $scope.errorMessage = response.data.message;
                  $scope.message = '';
            });
        
        
    }
		  
    $scope.updateCustomer = function () {
        CustomerService.updateCustomer($scope.customer.id,$scope.customer)
          .then(function success(response){
              $scope.message = response.data.message;
              $scope.errorMessage = '';
              $scope.reset();
          },
          function error(response){
              $scope.errorMessage = response.data.message;
              $scope.message = '';

          });
    }
    
   
   
    
    $scope.deleteCustomer = function (id) {
        CustomerService.deleteCustomer(id)
          .then (function success(response){
              $scope.message = response.data.message;
              $scope.errorMessage='';
              $scope.reset();
          },
          function error(response){
              $scope.errorMessage = response.data.message;
              $scope.message='';
              
          })
    }
    
    

}]);

app.service('CustomerService',['$http', function ($http) {
	
 
 	 this.getCustomers = function getCustomers(){
        return $http({
          method: 'GET',
          url: 'customers'
        });
    }
	
    this.createCustomer = function createCustomer(customer){
        return $http({
          method: 'POST',
          url: 'customers',
          data: customer
        });
    }
	
    this.deleteCustomer = function deleteCustomer(id){
        return $http({
          method: 'DELETE',
          url: 'customers/'+id
        })
    }
	
    this.updateCustomer = function updateCustomer(id,customer){
        return $http({
          method: 'PUT',
          url: 'customers/'+id,
          data: customer
        })
    }
	
   

}]);
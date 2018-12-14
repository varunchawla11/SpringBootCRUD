var app = angular.module('nameAdderAndDisplay', []);
app.controller('nameAdder', function($scope, $location, $anchorScroll, $http) {
    $scope.names=[];
    $scope.showAll=false;
    $scope.add = function(name){
        $scope.names.push(name);
    };
    $scope.displayAll = function(){
        $scope.showAll=true;
    };
    $scope.scrollTo = function(scrollLocation){
        $location.hash(scrollLocation);
        $anchorScroll();
    };


    $scope.user = {};
  $scope.users = [];
  $scope.entity = {};
  $scope.operationType = 'none';
//   $scope.firstName = null;
//   $scope.lastName = null;
//   $scope.age = null;
//   $scope.department = null;
//   $scope.id = null;
  $scope.message = 'Hey user!';

//   $scope.createUserData = function(){
//     $scope.user.firstName = $scope.firstname;
//     $scope.user.lastName = $scope.lastname;
//     $scope.user.age = $scope.age;
//     $scope.user.department = $scope.department;
//   };

  $scope.addUser = function(){
    // $scope.createUserData();
    if($scope.user.age == null || $scope.user.age.isEmpty() || $scope.user.firstName == null ||
    $scope.user.firstName.isEmpty() || $scope.user.lastName == null || $scope.user.lastName().isEmpty() ||
    $scope.user.department == null || $scope.user.department.isEmpty()) 
      $scope.message = "NO FIELD SHOULD BE EMPTY"
    else
      $http.post('http://localhost:8080/api/add', $scope.user).then(function(resp){
        
    })
    .catch(function(resp){
      $scope.message = resp;

    });
  };

  $scope.alterUserData = function(id){
    // $scope.createUserData();
    if($scope.user.age == null || $scope.user.age.isEmpty() || $scope.user.firstName == null ||
    $scope.user.firstName.isEmpty() || $scope.user.lastName == null || $scope.user.lastName().isEmpty() ||
    $scope.user.department == null || $scope.user.department.isEmpty()) 
      $scope.message = "NO FIELD SHOULD BE EMPTY"
    else
      $http.put('http://localhost:8080/api/alter/'+id, $scope.user).then(function(resp){
        $scope.message = "SUCCESS";
    })
    .catch(function(resp){
      $scope.message = "INVALID FIELD ID";

    });
  };

  $scope.deleteUser = function(id){
    if(id)
      $http.delete('http://localhost:8080/api/delete/'+id).then(function(resp){
        $scope.message = "SUCCESS";    
      }).catch(function(resp){
        $scope.message = "INVALID FIELD ID";
      });
  };

  $scope.viewAllUsers = function(){
    $http.get('http://localhost:8080/api/view').then(function(resp){
        $scope.users = resp.data;
    });
  };

  $scope.viewUser = function(id){
    if(id)
    $http.get('http://localhost:8080/api/view/'+id).then(function(resp){
        $scope.entity = resp.data;
    }).catch(function(resp){
      $scope.message = "INVALID FIELD ID";
    });      
  };


  $scope.setOperationType = function(type){
    $scope.operationType = type;
  };

});
var app = angular.module('myApp', []);
app.controller('InsertUserController', function ($scope) {
    $scope.name = "";
    $scope.email = "";
    $scope.password = "";
    $scope.users = [];
    $scope.listId = [];
    $scope.page = 0;
    $scope.searchEmail = "";
    $scope.searchPage = 0;
    // Handle call ajax to login user:
    $scope.login = function () {
        var user = {
            'email' :  $scope.email,
            'password' : $scope.password         
        }
        // Call Ajax to check login
        $.ajax({
            async: true,
            crossDomain: true,
            dataType: 'json',
            contentType: 'application/json',
            url: "http://localhost:8090/users/login" ,
            method: "POST",
            data: JSON.stringify(user),
            crossDomain: true,
            success: function(response) {
                console.log(response.name + " email " + response.email);
                if (response.id != null) alert(" Login successs ! ");
            },
            error: function(error) {
                console.log(" fial " + JSON.stringify(error));
            },
        });
    };
    // Handle call ajax to register user:
    $scope.register = function () {
        var user = {
            'id' : "",
            'name' :  $scope.name,
            'email' :  $scope.email,
            'password' : $scope.password         
        }
        // Call Ajax to insert user to DB
        $.ajax({
            async: true,
            crossDomain: true,
            dataType: 'json',
            contentType: 'application/json',
            url: "http://localhost:8090/users/create" ,
            method: "POST",
            data: JSON.stringify(user),
            success: function(response) {
                //$scope.users.push(response);
                $scope.$apply();
                if (response.id != null){
                    alert(" register success ! ");
                }
            },
            error: function(error) {
                console.log(" fial " + JSON.stringify(error));
            },
        });
    };
    // Handle call ajax to register user:
    $scope.update = function () {
        var user = {
            'id' :  $scope.id,
            'name' :  $scope.name,
            'email' :  $scope.email,
            'password' : $scope.password         
        }
         console.log(" id = " +$scope.id);
        // Call Ajax to insert user to DB
        $.ajax({
            async: true,
            crossDomain: true,
            dataType: 'json',
            contentType: 'application/json',
            url: "http://localhost:8090/users/update" ,
            method: "PUT",
            data: JSON.stringify(user),
            success: function(response) {
                // Update list 
                //$scope.users.push(response);
                if (response.id != null){
                    alert(" update success ! ");
                }
            },
            error: function(error) {
                console.log(" fial " + JSON.stringify(error));
            },
        });
    };
    // Handle reset form
    $scope.resetForm = function () {
        $scope.name = "";
        $scope.email = "";
        $scope.password = "";
        $scope.users = [];
        $scope.$apply();

    };
    
    // Hanle list all user in DB:
    $scope.list = function () {
        $.ajax({
            async: true,
            crossDomain: true,
            url: "http://localhost:8090/users/list",
            method: "GET",
            crossDomain: true,
            success: function(response) {
                console.log(response);
                $scope.users = response;
                $scope.users.forEach(element => {
                    console.log(" id "  + element.id + " name " +element.name);
                });
            },
            error: function(error) {
                console.log(JSON.stringify(error));
            },
        });
    };

    // Hanle search email:
    $scope.search = function () {
        $.ajax({
            async: true,
            crossDomain: true,
            url: "http://localhost:8090/users/search/"+ $scope.searchEmail + "/" + $scope.searchPage,
            method: "GET",
            crossDomain: true,
            success: function(response) {
                console.log(response);
                if (response.length > 0){
                    for(var i = 0; i < response.length; i++) {
                        $scope.users.push(response[i]);
                     }
                     $scope.searchPage = $scope.searchPage + 1;
                     $scope.$apply();
                }
            },
            error: function(error) {
                console.log(JSON.stringify(error));
            },
        });
    };

     // Show more record when seach a lot of email:
     $scope.viewMore = function () {
        $.ajax({
            async: true,
            crossDomain: true,
            url: "http://localhost:8090/users/search/"+ $scope.searchEmail + "/" + $scope.searchPage,
            method: "GET",
            crossDomain: true,
            success: function(response) {
                console.log(response);
                if (response.length > 0){
                    for(var i = 0; i < response.length; i++) {
                        $scope.users.push(response[i]);
                     }
                     $scope.searchPage = $scope.searchPage + 1;
                } else return;
              
               $scope.$apply();
            },
            error: function(error) {
                console.log(JSON.stringify(error));
            },
        });
    };
 
    // Hanle list all user in DB:
    $scope.getListId = function () {
        $.ajax({
            async: true,
            crossDomain: true,
            url: "http://localhost:8090/users/list",
            method: "GET",
            crossDomain: true,
            success: function(response) {
                console.log(response);
                $scope.listId = response;
                $scope.users.forEach(element => {
                    console.log(" id "  + element.id + " name " +element.name);
                });
                $scope.$apply();
            },
            error: function(error) {
                console.log(JSON.stringify(error));
            },
        });
    };
    
     // Hanle list all user in DB:
     $scope.updateRow = function () {
        var id = $scope.id;
        $.ajax({
            async: true,
            crossDomain: true,
            url: "http://localhost:8090/users/get/"+ id,
            method: "GET",
            crossDomain: true,
            success: function(response) {
                console.log(response);
                $scope.name = response.name;
                $scope.email = response.email;
                $scope.$apply();
            },
            error: function(error) {
                console.log(JSON.stringify(error));
            },
        });
    };
    
});

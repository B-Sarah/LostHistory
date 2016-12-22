var CLIENT = '716488363133-pgegunmkn66imjm8sabperm1gt910b25.apps.googleusercontent.com';

var app = angular.module('losthistory',['angular-google-gapi']);

app.run(['GAuth', function(GAuth) {

    GAuth.setClient(CLIENT);
    GAuth.setScope('https://www.googleapis.com/auth/userinfo.profile');

    GAuth.load();

}]);

app.controller('MainController', ['$scope','GAuth', function($scope, GAuth) {
    $scope.user = null;
    //$sope.loggedin = false;
    $scope.textLogin = "Login";
    
    $scope.login = function() {
        GAuth.login().then(function(user) {
            $scope.user = user;
            $scope.textLogin = "<img ng-src=\"{{user.picture}}\" class=\"img-circle\" width=\"40px\" height=\"40px\" ></img>".trustAsHtml();
            console.log(user);
        }, function() {
            console.log("Oups! Failure to connect...");
        });
   
    };
     
  

}]);

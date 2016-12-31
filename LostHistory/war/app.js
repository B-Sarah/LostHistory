var CLIENT = '716488363133-pgegunmkn66imjm8sabperm1gt910b25.apps.googleusercontent.com';

var app = angular.module('losthistory',['angular-google-gapi']);

app.run(['GAuth', function(GAuth) {

    GAuth.setClient(CLIENT);
    GAuth.setScope('https://www.googleapis.com/auth/userinfo.profile');

    GAuth.load();

}]);

app.controller('MainController', ['$scope','GAuth', function($scope, GAuth) {
    $scope.user = null;
    $scope.page = null;
    $scope.user = null;
    $scope.highscores = false;
    $scope.loggedin = false;
    $scope.loggedin = false;
    $scope.textLogin = "Login";

    
    $scope.login = function() {
        GAuth.login().then(function(user) {
            $scope.user = user;
            //$scope.textLogin = "<img ng-src=\"{{user.picture}}\" class=\"img-circle\" width=\"40px\" height=\"40px\" ></img>".trustAsHtml();
            $scope.loggedin = true;
            console.log("USER : " + user);
            console.log("PICTURE : " + user.picture);
        }, function() {
            console.log("Oups! Failure to connect...");
        });
   
    };
    
   
    
    $scope.logout = function() {
        GAuth.logout();
        $scope.user = null;
        $scope.loggedin = false;
        $scope.openPage('home');
        console.log("Bye .. ");
   
    };
    
    
    
    
	$scope.answerClick = function(index) {
		if ($scope.question.answered === false) {
			$scope.question.answered = index;
			$scope.questions.answered++;
			if($scope.question.good_answer === $scope.question.answered) {
				$scope.questions.well_answered++;
			}
		}
	}

	$scope.restart = function() {

		$scope.questions = {
			answered: 0,
			well_answered: 0,
		}

		$scope.nextQuestion();
	}

	$scope.nextQuestion = function() {

		if ($scope.questions.answered < 10) {

			$scope.question = {
				text: "Text of the question",
				answers: [
					"First answer",
					"Second answer",
					"Third answer",
				],
				good_answer: Math.floor(Math.random()*3),
				answered: false
			}

		} else {
			//$scope.insertScore({id: Math.floor(Math.random()*1000000)+1, score: $scope.questions.well_answered*10});
			$scope.openPage('gameover');
		}
		
	}

	$scope.openPage = function(page) {
		$scope.page = page;
			if (page == 'play') {
				if(!$scope.loggedin)
					$scope.login();
					$scope.restart();
					
				
				
			}
			else if (page == 'highscores') {
				$scope.highscores = null;
				$scope.listScores(function(data) {
				$scope.highscores = data;
			});
		}
	   else if (page == 'home') {
		}

			
	}		

	$scope.listScores = function(callback) {
		GApi.execute('scoreentityendpoint','listScoreEntity').then(function(resp) {
			callback(resp.items);
		}, function() {
			console.log('Error!');
		});
	}
	$scope.openPage('home');
    

     
  

}]);

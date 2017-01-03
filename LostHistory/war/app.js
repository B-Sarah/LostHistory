var CLIENT = '716488363133-pgegunmkn66imjm8sabperm1gt910b25.apps.googleusercontent.com';

var app = angular.module('losthistory',['angular-google-gapi', 'ngMap']);

app.run(['GAuth','GApi', function(GAuth,GApi) {
	
	var BASE = 'https://1-dot-lost-history.appspot.com/_ah/api';
    GApi.load('battleEndpoint','v1',BASE).then(function(resp) {
        console.log('api: ' + resp.api + ', version: ' + resp.version + ' loaded');
    }, function(resp) {
        console.log('an error occured during loading api: ' + resp.api + ', resp.version: ' + version);
    });

    GAuth.setClient(CLIENT);
    GAuth.setScope('https://www.googleapis.com/auth/userinfo.profile');

    GAuth.load();

}]);

app.controller('MainController', ['GApi','$scope','GAuth', 'NgMap', '$timeout', function(GApi,$scope,GAuth,NgMap, $timeout) {
	
    $scope.user = null;
    $scope.page = null;
    $scope.highscores = false;
    $scope.loggedin = false;
    $scope.loadingBattles = false;
    $scope.textLogin = "Sign in";
    
    $scope.battleLoadingText = "Waiting for battles to load ...";
    
    $scope.score = 0;
    
    $scope.battles = null;
    $scope.currentBattle = -1;
    
    $scope.validate = false;
    $scope.question = [];
    
    $scope.map = null;
    
    $scope.initMap = function(map){
    	$scope.map = map;
    }

    $scope.login = function() {
        GAuth.login().then(function(user) {
            $scope.user = user;
            $scope.loggedin = true;
        }, function() {
        	console.log("failed to log !!"); 
            $scope.openPage('home');
        });
   
    };

    $scope.logout = function() {
        GAuth.logout();
        $scope.user = null;
        $scope.loggedin = false;
        $scope.battles = null;
        $scope.openPage('home');
        console.log("Bye .. ");
   
    };
    
    
    
    
	/*$scope.answerClick = function(index) {
		if ($scope.question.answered === false) {
			$scope.question.answered = index;
			$scope.questions.answered++;
			if($scope.question.good_answer === $scope.question.answered) {
				$scope.questions.well_answered++;
			}
		}
	}*/
    
   

	$scope.restart = function() {

		if($scope.battles == null && !$scope.loadingBattles){
			$scope.loadingBattles = true;
			console.log("load battles");
			$scope.retrieveBattles(function(data) {
				$scope.battles = data;
				$scope.loadingBattles = false;
				$scope.nextQuestion();
				$timeout($scope.fixMap, 500);
			});
		}

	}
	
	$scope.fixMap = function(){
		if(!$scope.$$phase) {
			$scope.$apply();
		}
		google.maps.event.trigger($scope.map,'resize');
    	$scope.map.setCenter(new google.maps.LatLng(41,-87));
	}

	$scope.nextQuestion = function() {
		$scope.validate = false;
		$scope.currentBattle++;
		console.log("next question ... with current : " + $scope.currentBattle)

		if ($scope.currentBattle < 10) {

			$scope.question[0] = {
				text: "1. When did the battle happened ?",
				answers: [
					$scope.battles[$scope.currentBattle].formattedDates[0],
					$scope.battles[$scope.currentBattle].formattedDates[1],
					$scope.battles[$scope.currentBattle].formattedDates[2],
					$scope.battles[$scope.currentBattle].formattedDates[3],
				],
				answered: false
			}
			$scope.question[1] = {
				text: "2. Who had participated to it ?",
				answers: [
					$scope.battles[$scope.currentBattle].formattedParticipants[0],
					$scope.battles[$scope.currentBattle].formattedParticipants[1],
					$scope.battles[$scope.currentBattle].formattedParticipants[2],
					$scope.battles[$scope.currentBattle].formattedParticipants[3],
				],
				answered: []
			}
			
			$scope.question[2] = {
				text: "3. Where did it took place ?",
				answers: [],
				answered: []
			}
			
		} else {
			$scope.insertScore($scope.user.name,$scope.score);
			$scope.openPage('gameover');
		}
		
	}
	
	 $scope.answerYear = function(index) {
		 if(!$scope.validate){
			$scope.question[0].answered = index;
		 }
	 }
	 
	 $scope.answerParticipant = function(index) {
		 if(!$scope.validate){
			var i = $scope.question[1].answered.indexOf(index)
			if(i === -1){
				$scope.question[1].answered.push(index);	
			} else {
				$scope.question[1].answered.splice(i, 1);
			}
			
		 }
	 }
	 
	 $scope.answerPlace = function(event){
		 console.log("clicked on map : " + event.latLng)
		 if(!$scope.validate){
			 $scope.question[2].answered[0] = event.latLng;
		 }
	 }
	 
	 $scope.validatePlace = function(){
		 
		 var distMin = google.maps.geometry.spherical.computeDistanceBetween ($scope.question[2].answered[0], new google.maps.LatLng({lat: $scope.question[2].answers[0].latitude, lng: $scope.question[2].answers[0].longitude}));
		 
		 for(place in $scope.question[2].answers){
			 if(google.maps.geometry.spherical.computeDistanceBetween ($scope.question[2].answered[0], new google.maps.LatLng({lat: place.latitude, lng: place.longitude})) < distMin){
				 distMin = google.maps.geometry.spherical.computeDistanceBetween ($scope.question[2].answered[0], new google.maps.LatLng({lat: place.latitude, lng: place.longitude}));
			 }
		 }
		 
		 if(distMin < 10000)
			 $scope.score += 30;
		 else if(distMin < 100000)
			 $scope.score += 20;
		 else if(distMin < 1000000)
			 $scope.score += 10;
	 }
	 
	 $scope.validateAnswer = function(){
		 if(!$scope.validate && $scope.question[0].answered !== false && $scope.question[1].answered.length != 0 && $scope.question[2].answered.length != 0){
			 if($scope.question[0].answers[$scope.question[0].answered].correct) {
				$scope.score += 10;
			 }
			 
			 for(i = 0; i < $scope.question[1].answered.length; i++){
				 if($scope.question[1].answers[$scope.question[1].answered[i]].correct) {
					 $scope.score += 10;
				 }
			 }
			 
			 $scope.question[2].answers = $scope.battles[$scope.currentBattle].places;
			 $scope.validatePlace();
			 
			 $scope.validate = true;
			 console.log("validate");
		 }
	 }

	$scope.openPage = function(page) {
		$scope.battleLoadingText = "Waiting for battles to load ...";
		$scope.score = 0;
		$scope.page = page;
			if (page == 'play') {
				if(!$scope.loggedin)
					$scope.login();
				console.log("restart");
				$scope.restart();
			}
			else if (page == 'highscores') {
				$scope.highscores = null;
				$scope.listScores(function(data) {
				$scope.highscores = data;
			});
				console.log($scope.highscores);
		}
	   else if (page == 'home') {
		   
		}
	   else if(page == 'gameover'){
		   $scope.battles = null;
		   $scope.currentBattle = -1;
	   }

			
	}		

	$scope.listScores = function(callback) {
		GApi.execute('battleEndpoint','getScores').then(function(resp) {
			callback(resp.items);
		}, function() {
			console.log('Error!');
		});
	}
	
	$scope.insertScore = function(name,sc) {
		GApi.execute('battleEndpoint','insertScore', {score:sc, username:name}).then(function(resp) {
			$scope.openPage('highscores');
		}, function() {
			console.log('Error during score insertion!');
			$scope.openPage('home');
		});
	}
	
	$scope.retrieveBattles = function(callback) {
		GApi.execute('battleEndpoint','request', {nbBattles:10}).then(function(resp) {
			callback(resp.items);
			console.log(resp.items);
		}, function() {
			console.log('Error during battles request!');
			$scope.battleLoadingText = "Error while loading battles, please retry ..."
			$scope.loadingBattle = false;
		});
	}
	
	$scope.openPage('home');
    

     
  

}]);

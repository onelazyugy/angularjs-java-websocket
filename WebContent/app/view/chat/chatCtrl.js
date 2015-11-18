controllers.controller('chatCtrl', ['$scope', 'socketUtils', '$rootScope', function($scope, socketUtils, $rootScope) {
		$scope.msg = "Let's send some message shall we?";
		var port = "8087";
		var uri = "chatsocket";
		$scope.ws;
		var name;
		
		$scope.sendMsg = function(){
			if(!$scope.ws){		
				socketUtils.setSocket(port, uri);
				$scope.ws = socketUtils.getSocket(); 
			} else {
				$scope.ws.$emit($scope.message);
			}
			if($scope.ws.$status() == 3){
				$scope.ws.$open();
			}
			console.log("status after sumbit: " + $scope.ws.$status());			
			//callback to hanlde opne and send a message to server
			$scope.ws.$on('$open', function () {
				console.log("callback: $open");
				if($scope.ws.$status() == 1){					
					console.log("chat socket Opened...");
					$scope.ws.$emit(name + " say: " + $scope.message);
				}
			});
			
			//callback function to handle a message coming from server
			$scope.ws.$on('$message', function (messageFromServer) { 
				console.log("callback: $message");
				console.log("status inside $message: " + $scope.ws.$status());

				console.log('something incoming from the server: ' + messageFromServer);
				$scope.msg = messageFromServer;
				$rootScope.$apply()
			});	
			
			//callback function to handle when the socket get closed
			$scope.ws.$on('$close', function () {
				console.log("callback: $close");
				if($scope.ws != null){					
					console.log("status inside $close: " + $scope.ws.$status());
					$scope.ws = null;
					console.log("chat connection closed.");
				}				
		    });
			
			$scope.ws.$on('$error', function (){
				console.log("callback: $error");
				console.log("status inside $error: " + $scope.ws.$status());
				alert('Error...');
			});
		};
		
		$scope.close = function(){
			$scope.ws.$close();
		};
		
		$scope.logIn = function(){
			name = $scope.name;
			console.log("name is: " + name);
		};
    }
]);
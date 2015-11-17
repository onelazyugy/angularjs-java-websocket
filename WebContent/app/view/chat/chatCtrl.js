controllers.controller('chatCtrl', ['$scope', 'socketUtils', function($scope, socketUtils) {
		$scope.msg = "Let's send some message shall we?";
		var port = "8087";
		var uri = "chatsocket";
		$scope.sendMsg = function(){
			$scope.msg = $scope.message;
			socketUtils.setSocket(port, uri);
			var ws = socketUtils.getSocket();
			console.log("status: " + ws.$status());
		};
    }
]);
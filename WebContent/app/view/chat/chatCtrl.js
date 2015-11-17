controllers.controller('chatCtrl', ['$scope', '$http', '$websocket', '$q', '$rootScope', function($scope, $http, $websocket, $q, $rootScope) {
		$scope.msg = "Let's send some message shall we?";
		$scope.sendMsg = function(){
			$scope.msg = $scope.message;
		};
    }
]);
services.factory('socketUtils', ['$websocket', function($websocket) {
	var ws = null;
	var socketData = {
	        setSocket: function(port, uri) {
	        	ws = $websocket.$new('ws://localhost:'+ port +'/angularjs-java-websocket/' + uri);
	        },
	        getSocket: function() {
	            return ws;
	        }
	    };
	return socketData;
}]);
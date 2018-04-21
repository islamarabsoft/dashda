$(document).ready(function() {
    $.ajax({
    	async: true,
    	crossDomain: false,
    	method: "GET",
    	headers: {
		    "Authorization": "Basic YWRtaW46YWRtaW4="
		  },
        url: "https://dashda-197912.appspot.com/user/user-info"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.username);
       $('.greeting-my').append(22);
    });
});

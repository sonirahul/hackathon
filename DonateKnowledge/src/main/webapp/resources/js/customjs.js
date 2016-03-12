$(document).ready(function () {
	areCookiesEnabled();
	$('.megaSearch').autocomplete(
    {
        serviceUrl : contextPath+'/getTags',
        paramName : "tagName",
        delimiter : ",",
        onSelect: function (item) {
        	if (this.id == 'uploadquery') {
        		addeditproduct();
        	}
        	if (this.id == 'query') {
        		var url = item.value.replace(/ /g, '_');
        		if(url != '#') {
        			location.href = contextPath+'/product/' + url;
        		}
        	}
        },
        transformResult : function(response) {

            return {
                suggestions : $.map($.parseJSON(response),
                    function(item) {
                        return {
                            value : item.tagName,
                            data : item.id
                        };
                    })
            };
        }
    });
	$("#re-email").blur(function() {
		var email = $("#email").val();passCodeValue
		var reEmail = $("#re-email").val();
		
		if (email != '' && reEmail != '' && email == reEmail) {
			checkIfUserExists();
		}
		else {
			alert("both aren't equal");
		}
	});
	$('#loginModal1').modal({ show: false});
	$('#loginModal2').modal({ show: false});
});

function addeditproduct() {
	var search = $("#uploadquery").val();
	console.log(search);
	$("#myForm").show();
	$("#mainSel").hide();
	event.preventDefault();
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : contextPath + "/addeditproduct",
		data : JSON.stringify(search),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			//console.log("SUCCESS: ", data);
			display1(data);
		},
		error : function(e) {
			//console.log("ERROR: ", e);
			display1(e);
		},
		done : function(e) {
			//console.log("DONE");
			//enableSearchButton(true);
		}
	});
}
function checkIfUserExists() {
	var search = $("#email").val();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : contextPath + "/fetchUserByEmail",
		data : JSON.stringify(search),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			//console.log("SUCCESS: ", data);
			display(data);
		},
		error : function(e) {
			//console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			//console.log("DONE");
			//enableSearchButton(true);
		}
	});
}
function display(data) {
	var json = JSON.stringify(data.userExists, null, 4);
	alert("user exists: " + json);
	
}
function claimPointsFunc(isbn,index) {
	var reqData=isbn+","+$("#passCodeValue_" + index).val();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : contextPath + "/validatePasscode",
		data : JSON.stringify(reqData),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			var json = JSON.stringify(data.passCodeValidated, null, 4);
			
			if (json == 'true') {
				$('#loginModal1').modal('show');
			}
			else{
				$('#loginModal2').modal('show');
			}

		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
			//enableSearchButton(true);
		}
	});
}

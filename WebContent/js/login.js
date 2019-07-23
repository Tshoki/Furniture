function goLoginAction(){
	document.getElementById("loginForm").action="LoginAction"; //login.jspのid = loginFormのタグに属性 action = "LoginAction"を追加
}
function goCreateUserAction(){
	document.getElementById("loginForm").action="CreateUserAction";
}
function goResetPasswordAction(){
	document.getElementById("loginForm").action="ResetPasswordAction";
}

/*//テキストボックス の placeholder 操作
$(function(){
	$('input[type="text"]').eq(1).focus( function(){
		$('input[type="text"]').eq(1).removeAttr('placeholder');
	});
	$('input[type="text"]').eq(1).blur( function(){
		if($('input[type="text"]').eq(1).val().length == 0){
			$('input[type="text"]').eq(1).attr('placeholder', 'ユーザーID');
		}
	});
});

$(function(){
	$('input[type="password"]').eq(0).focus( function(){
		$('input[type="password"]').eq(0).removeAttr('placeholder');
	});
	$('input[type="password"]').eq(0).blur( function(){
		if($('input[type="password"]').eq(0).val().length == 0){
			$('input[type="password"]').eq(0).attr('placeholder', 'パスワード');
		}
	});
});*/
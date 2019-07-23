function goResetPasswordAction(){
	document.getElementById("backFlg").value="1";
	document.getElementById("resetPasswordForm").action="ResetPasswordAction";
}

function goResetPasswordCompleteAction(){
	document.getElementById("resetPasswordForm").action="ResetPasswordCompleteAction";
}
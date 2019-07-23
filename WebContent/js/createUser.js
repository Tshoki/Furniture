//createUserActionへ遷移する為のメソッド
function goCreateUserAction(){
	//createUserConfirm.jspのidが"backFlg"であるタグのvalueを"1"に書き換える
	document.getElementById("backFlg").value="1";
	//createUserConfirm.jspのidが"createUserForm"であるタグにaction="CreateUserAction"を追加する
	document.getElementById("createUserForm").action="CreateUserAction";
}

//CreateUserCompleteActionへ遷移する為のメソッド
function goCreateUserCompleteAction(){
	//createUserConfirm.jspのidが"createUserForm"であるタグにaction="CreateUserCompleteAction"を追加する
	document.getElementById("createUserForm").action="CreateUserCompleteAction";
}
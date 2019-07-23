function GOtoCompleteAction(){	//完了画面に行く
	document.getElementById("destinationInfo").action="CreateDestinationCompleteAction";
}

function BACKtoAction(){	//戻る
	document.getElementById("backFlg").value="1";
	document.getElementById("destinationInfo").action="CreateDestinationAction";
}
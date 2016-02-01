function check() {
	var factoryNum = document.getElementById("factoryNum").value;
	var loginName = document.getElementById("loginName").value;
	var password = document.getElementById("password").value;
	if (factoryNum == "") {
		alert("请选择生产基地！");
		return false;
	}
	if (loginName == "") {
		alert("请输入用户名!");
		document.getElementById("loginName").focus();
		return false;
	}
	if (password == "") {
		alert("请输入密码!");
		document.getElementById("password").focus();
		return false;
	}

	Ext.Ajax.request({
		url: "/doLogin.do?time=" + new Date().getTime(),
		success: function(response, option) {
			var data =Ext.decode(response.responseText);
			if(data.success){
				//document.location = "/role_getMainMenu.do";
				Ext.Msg.alert("提示", "登陆成功");
			} else {
				if (data.errorMsg == "weakPassword") {
					if (confirm("您的密码为弱口令,请点击【确定】按钮修改密码，再登录！")) {　
						var changePassword = document.getElementById("changePassword");
						if (changePassword) {
							changePassword.click();
						}
					}
				}else{
					Ext.Msg.alert("提示", data.errorMsg);
				}
			}
		},
		params: {
			loginName: loginName,
			factoryNum: factoryNum,
			password: password
		}
	});
}

function enterIn(evt) {
	var evt = evt ? evt : (window.event ? window.event : null); //兼容IE和FF
	if (evt.keyCode == 13) {
		check();
	} else {
		return false;
	}
}

function reset() {
	document.getElementById("loginName").value = "";
	document.getElementById("password").value = "";
	document.getElementById("loginName").focus();
}

function openWin(url) {　
	window.open(url, 'newwindow', 'height=255, width=500, top=270, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
}
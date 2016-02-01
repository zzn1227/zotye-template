	//分页
	
	function pageTo(requrl,page) {
		document.pagingForm.action=requrl;
		if(document.getElementById("currentPage")!=null){
			document.getElementById("currentPage").value=page;
		}
		document.pagingForm.target="";
		document.pagingForm.submit();
	}
	
	function goPage(requrl,page) {
		if(document.getElementById("pageGo").value==""){
			alert('请输入页数！');
			return ;
		}
		document.getElementById("currentPage").value=page;
		document.pagingForm.action=requrl;
		document.pagingForm.target="";
		document.pagingForm.submit();
	}
	function validNum(obj) {
		var r = /^[0-9]*[1-9][0-9]*$/;
		if ((!r.test(obj.value)) || parseInt(obj.value,10) > parseInt(document.getElementById("pageCount").innerHTML,10)) {
			obj.value = obj.value.substring(0,obj.value.length - 1);
		}
	}
	 //pagingForm的提交  requrl为提交地址
	function pagingFormSubmit(requrl){
	    document.pagingForm.action=requrl;
		document.pagingForm.target="";
		document.pagingForm.submit();
	}
	function validCountPerPage(obj) {
		var r = /^[0-9]*[1-9][0-9]*$/;
		if ((!r.test(obj.value)) ) {
			obj.value =30;
		}
	}
	//按回车键时提交
	function submitOnEnterKey(obj,requrl){
		validCountPerPage(obj);
		var event=window.event|| arguments.callee.caller.arguments[0];//消除浏览器差异  
		  if(event && event.keyCode==13){ // enter 键
              pageTo(requrl,1);    
            }
	}
	//按回车键时提交
	function submitOnEnterKeyWithRequrl(requrl,page){
		var event=window.event|| arguments.callee.caller.arguments[0];//消除浏览器差异  
		  if(event && event.keyCode==13){ // enter 键
              pageTo(requrl,page);    
            }
	}

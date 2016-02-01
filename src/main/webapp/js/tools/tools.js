
var activeX_Print_Err = "对不起！您目前无法打印！\n\n成功打印的前提：\n" 
   + "1、必须使用IE浏览器；\n" 
   +"2、将网址设为信任站点：在IE的工具--》internet选项--》安全--》可信站点--》站点，把http://les.zotye.com添加进去，如不能添加请先去掉左下角的勾，取消https验证；\n"
   	+"3、必须对IE开启ActiveX：工具->Internet选项->安全->可信站点->自定义级别：ActiveX控件和插件->对未标记为安全的ActiveX控件初始化并执行脚本：设置为“启用”。\n"
   	+"以上设置确定后，再重新登录ERP，即可导出打印。";


//打印设置
function printSetupPage(){
	try {
	 document.all.WebBrowser.ExecWB(8,1)
	} catch (e) {
	 alert(activeX_Print_Err);
	}
}

//打印预览
function printViewPage(){
	try {
		document.all.WebBrowser.ExecWB(7,1)
	} catch (e) {
	 alert(activeX_Print_Err);
	}
}

//打印设置
function printPage(){
	try {
		document.all.WebBrowser.ExecWB(6,1)
	} catch (e) {
	 alert(activeX_Print_Err);
	}
}

//验证数字(整数、浮点数都可以通过)

	function isfloat(oNum){

	  if(!oNum) return false;

	  var strP=/^(-)?\d+(\.\d+)?$/;

	  if(!strP.test(oNum)) return false;

	  return true;

	}

//验证正整数

function isNumber(oNum){

  if(!oNum) return false;

  var strP=/^\d+$/; //正整数

  if(!strP.test(oNum)) return false;

  return true;

}
//传入name 得到多选框选中的值 用','分割
  function getValueStrFromCheckBox(name){
   	  var result;
   	  var obj=document.getElementsByName(name);
      var length=obj.length;
      if(length>0){
      	var e;
      	 for(i=0;i<length;i++){
      	 	e=obj[i];
      	 	 if(e.checked){
      	 	   if(result){
      	 	   	 result+=','+e.value;
      	 	   }else{
      	 	    result=e.value;
      	 	   }
      	 	 }
      	  }
      	  return result;
      }else{
       return "";
      }
   } 
   
   //传入name 和 value  设置多选框选中的值 value用','分割
  function setCheckBoxValue(name,value){
   	  if(!name || name==''){
       return ;
   	  }
   	  if(!value){
   	  value="";
   	  }
   	  var obj=document.getElementsByName(name);
      var length=obj.length;
      value=","+value+",";
      if(length>0){
      	var e;
      	var eVal;
      	 for(i=0;i<length;i++){
      	 	e=obj[i];
      	 	eVal=","+e.value+",";
      	 	if(value.indexOf(eVal)>-1){
      	 		e.checked=true;
      	 	}else{
      	 	   	e.checked=false;
      	 	}
      	  }
      }
   }
  
//全选或反全选  currenctCheckbox:调用这方法的  name为要全选的
	
	function changeAllCheckboxSelected(currenctCheckbox,checkboxName){
		 var array=document.getElementsByName(checkboxName);
		 var length;
		 if(array&&(length=array.length)>0){
			 var hasChecked=currenctCheckbox.checked;
			  if(hasChecked){
				  //全选
				 for( i=0 ; i<length; i++){
					 array[i].checked=true;
				 }
			  }else{
				//去除全选  
				  for( i=0;i<length;i++){
						  array[i].checked=false;
					 } 
			  }
		 }
	 }
   
   var activeX_Err = "对不起！您目前无法导出EXCEL！\n\n成功导出EXCEL的前提：\n1、您的电脑必须安装EXCEL；\n" 
   + "2、必须使用IE浏览器；\n" 
   +"3、将网址设为信任站点：在IE的工具--》internet选项--》安全--》可信站点--》站点，把http://192.168.8.245添加进去；\n"
   	+"4、必须对IE开启ActiveX：工具->Internet选项->安全->可信站点->自定义级别：ActiveX控件和插件->对未标记为安全的ActiveX控件初始化并执行脚本：设置为“启用”。\n"
   	+"以上设置确定后，再重新登录ERP，即可导出EXCEL。";
		
function excelExport() {
	var mytable = document.getElementById("ext-gen12");
	var mytablename = "ext-gen12";
	 
	if (mytable == null)
		return;
	excelExportByTableName(mytablename);
}
function excelExportPagingList() {
	var mytable = document.getElementById("pagingList");
	if (mytable == null)
		return;
		var mytablename = "pagingList";
	excelExportByTableName(mytablename);
}
function excelExportByTableName(tableName) {
	var mytable = document.getElementById(tableName);
	if (mytable != null) {
		var oXL = null;
		try {
			oXL = new ActiveXObject("Excel.Application");
			var oWB = oXL.Workbooks.Add();
			var oSheet = oWB.ActiveSheet;
			var sel = document.body.createTextRange();
			sel.moveToElementText(mytable);
			sel.select();
			sel.execCommand("Copy");
			oSheet.Paste();
			oXL.Visible = true;
		} catch (e) {
		//MessageHelper.info({msg:e});
		//MessageHelper.info({msg:activeX_Err});
		alert(activeX_Err);
		}
		oXL = null;
	}
	 
}




/**
 * Map 对象   put  get  remove  size isEmpty的实现
 */
 function Map() {
 var struct = function(key, value) {
  this.key = key;
  this.value = value;
 }
 
 var put = function(key, value){
  for (var i = 0; i < this.arr.length; i++) {
   if ( this.arr[i].key === key ) {
    this.arr[i].value = value;
    return;
   }
  }
   this.arr[this.arr.length] = new struct(key, value);
 }
 
 var get = function(key) {
  for (var i = 0; i < this.arr.length; i++) {
   if ( this.arr[i].key === key ) {
     return this.arr[i].value;
   }
  }
  return null;
 }
 
 var remove = function(key) {
  var v;
  for (var i = 0; i < this.arr.length; i++) {
   v = this.arr.pop();
   if ( v.key ===key ) {
    continue;
   }
   this.arr.unshift(v);
  }
 }
 
 var size = function() {
  return this.arr.length;
 }
 
 var isEmpty = function() {
  return this.arr.length <= 0;
 }
 this.arr = new Array();
 this.get = get;
 this.put = put;
 this.remove = remove;
 this.size = size;
 this.isEmpty = isEmpty;
}
 


/**
 * @class FormHelper
 * Form帮助类

 * @singleton
 */
FormHelper = function () {
	/**
	 * 从当前活动的页面查找Form
	 * @return {Ext.Form.BasicForm} 若form被修改过，则返回之，若所有form没有修改过，则返回第一个form
	 * @author chennp
	 */
	function findForm(){
		var allItemsOfPage = Ext.ComponentMgr.all.items;
		var allFormPanels = new Array();
		for (var index = 0,size = allItemsOfPage.length ; index < size; index++) {
			var cmp = allItemsOfPage[index];
			if(cmp instanceof Ext.Component){
				if(cmp.isXType('form')){
					if(cmp.getForm().isDirty()){
						return cmp.getForm();
					}
					allFormPanels.push(cmp);
				}
			}
		}
		if(allFormPanels.length > 0){
			return allFormPanels[0].getForm();
		}
		/*var allFormPanels = Ext.WindowMgr.getActive().findByType('form');
		for(var i= 0,size = allFormPanels.length; i < size ;i ++){
			var form = allFormPanels[i].getForm();
			if(form.isDirty()){
				return form;
			}
		}
		if(allFormPanels.length > 0){
			 return allFormPanels[0].getForm();
		}*/
	 	MessageHelper.error({
        	msg: '没有找到要提交表单(Form)!'
		});
		return;
	}
	// private
	function doAction(action, method, url, success, params, configForm) {
		var form = configForm ? configForm : findForm();//Ext.WindowMgr.getActive().items.get(0).form;
		var waitMsg = "操作处理中...";
		if(action=="submit" && !form.isValid()) {
			MessageHelper.info({
	        	msg: '您填写的数据项有误，请根据红色框提示更改输入后重试！'
			});
			return;
		}
 		if(action=="load") {
			waitMsg = "数据加载中...";
		}
		form.doAction(action, {
			failure: function(form, action) {   
				var msg;
				if(action.result)
					msg = action.result.data;
				if(!msg)
					msg = "您的请求服务器未响应！请刷新页面或重新登陆后重试；如问题依然存在，请与管理员联系！";
				if (typeof action.result != 'undefined'
						&& 'true' == action.result.multi) {
					MessageHelper.multiError( {
						msg : msg
					});
				} else {
					MessageHelper.error( {
						msg : msg
					});
				}
			},
			method: method,
			params: params,
			success: success,
			url: Ext.getDom("root").value + url,
			//url: Ext.getDom("root").value + url,
			waitMsg: waitMsg,
			waitTitle: '请稍候'
		});
	}
	
	return {
		fourColumnedRow: function(leftColumnItem, middleOneColumnItem,middleTwoColumnItem, rightColumnItem,w1,w2,w3,w4) {
			var w11 = w1?w1:0.25;
			var w22 = w2?w2:0.25;
			var w33 = w3?w3:0.25;
			var w44 = w3?w4:0.25;
			return {
				baseCls: 'x-plain',
				layout: 'column',
				defaults: {
					baseCls: 'x-plain',
					defaults: { anchor: '100%'},
					layout: 'form'
				},
				items: [
					{ items: leftColumnItem, columnWidth: w11 },{ items: middleOneColumnItem, columnWidth: w22 },{ items: middleTwoColumnItem, columnWidth: w33 },{ items: rightColumnItem, columnWidth: w44 }
				]
			};
		},
		threeColumnedRow: function(leftColumnItem, middleColumnItem, rightColumnItem,w1,w2,w3) {
			var w11 = w1?w1:0.33;
			var w22 = w2?w2:0.33;
			var w33 = w3?w3:0.33;
			return {
				baseCls: 'x-plain',
				layout: 'column',
				defaults: {
					baseCls: 'x-plain',
					defaults: { anchor: '100%'},
					layout: 'form'
				},
				items: [
					{ items: leftColumnItem, columnWidth: w11 },{ items: middleColumnItem, columnWidth: w22 },{ items: rightColumnItem, columnWidth: w33 }
				]
			};
		},
		/**
		 * twoColumnedRow
		 */
		twoColumnedRow: function(leftColumnItem, rightColumnItem,w1,w2) {
			var w11 = w1?w1:0.5;
			var w22 = w2?w2:0.5;
			return {
				baseCls: 'x-plain',
				layout: 'column',
				defaults: {
					baseCls: 'x-plain',
					defaults: { anchor: '100%'},
					layout: 'form'
				},
				items: [
					{ items: leftColumnItem, columnWidth: w11 },{ items: rightColumnItem, columnWidth: w22 }
				]
			};
		},
		/**
		 * oneColumnedRow		占据的是一列的位置
		 */
		oneColumnedRow: function(columnItem) {
			return {
				baseCls: 'x-plain',
				layout: 'column',
				defaults: {
					baseCls: 'x-plain',
					defaults: { anchor: '93%'},
					layout: 'form'
				},
				items: [
					{ items: columnItem, columnWidth: 0.5 }
				]
			};
		},
		/**
		 * wholeOneColumnedRow		占据的是两列的位置,一般是用于TextArea组件
		 */
		wholeOneColumnedRow: function(columnItem) {
			return {
				baseCls: 'x-plain',
				layout: 'column',
				defaults: {
					baseCls: 'x-plain',
					defaults: { anchor: '95%'},
					layout: 'form'
				},
				items: [
					{ items: columnItem, columnWidth: 1 }
				]
			};
		},
		/**
		 * hiddenColumns
		 * 
		 * @param {Array} hiddenColumns 
		 */
		hiddenColumns: function(hiddenColumns) {
			return {
	            title: '隐藏信息(你不应该看到的)',
	            hidden: true,
		        items: hiddenColumns
			};
		},
		/**
		 * load
		 */
		load: function (url, params ,success ,configForm) {
			var _params = Ext.apply(params || {}, { formOrGrid: "form" });
			var _success = success ? success : function(form, action) {
				var data = Ext.util.JSON.decode(action.result.data);
				form.clearInvalid();
				form.setValues(data);
			};
			doAction('load', 'POST', url, _success, _params , configForm);
		},
		/**
		 * submit
		 */
		submit: function (url, success, params ,configForm) {
			var _params = Ext.apply(params || {}, { formOrGrid: "form" });
			var _success = success || function(form, action) {
				var msg;
				if(action.result)
					msg = action.result.data;
				MessageHelper.succuss({
					msg: msg
				});
			};
			doAction('submit', 'POST', url, _success, _params, configForm);
		},
		/**
		 * reset
		 */
		reset: function (_winActive) {
			var winActive = _winActive || Ext.WindowMgr.getActive();
			winActive.items.get(0).form.items.each(function(f){
	            f.setValue("");
	        });
		}
	}
}();

/**
 * @class ActionHelper
 * Action帮助类

 * @singleton
 */
ActionHelper = function () {
	return {
		request: function(url, params, callback, success) {
			var _params = Ext.apply(params || {}, { formOrGrid: "grid" });
			var _success = success ? success : function(response,options) {
				if(response.responseText.indexOf("data")!=-1){
					response.responseText="";
				}
				MessageHelper.succuss({
					msg: response.responseText,
					callback: callback
				});
			};
			
			Ext.Ajax.request({
                url: Ext.getDom("root").value + url,
                params : _params,
                success : _success,
                failure : function(response, options) {
					MessageHelper.error({
			        	msg: response.responseText || "您的请求服务器未响应！请刷新页面或重新登陆后重试；如问题依然存在，请与管理员联系！"
					});
				}
            });
		}
	}
}();

/**
 * @class WindowHelper
 * Window帮助类

 * @singleton
 */
WindowHelper = function () {
	return {
		/**
		 * 关闭窗口，对当前激活的Form重置，同时调动传入的方法.
		 * @param {Boolean} resetForm: true: reset, false: no action.
		 * @param {Function} callback：关闭窗口后的回调函数（无参函数）.
		 */
		hide: function(resetForm, callback) {
			var winActive = Ext.WindowMgr.getActive();
			if(resetForm) {
				FormHelper.reset(winActive);
			}
			winActive.hide();
			
			if(callback) {
				callback.call();
			}
		}
	}
}();

/**
 * @class StoreHelper
 * Store帮助类

 * @singleton
 */
StoreHelper = function () {
	return {
		/**
		* 为Store添加业务查询条件
		* @param {String} store: store
		* @param (String) baseParams: {method: 'queryPaged', name: 'Ruby'...}
		*/
		beforeload: function(store, baseParams) {
			if(!store) {alert("<StoreHelper.beforeload>store is null...");return;}
			var _baseParams = Ext.apply(baseParams || {}, { formOrGrid: "grid" });
			
			store.on('beforeload', function() {
		        store.baseParams = _baseParams;
		    }.createDelegate(this));
		}
	}
}();

/**
 * @class MessageHelper
 * Message帮助类

 * @singleton
 */
MessageHelper = function () {
	// private
	function alert(config) {
		Ext.MessageBox.show({
           title: config.title || '未定义标题',
           msg: config.msg || '未定义信息',
           buttons: config.buttons || Ext.MessageBox.OK,
           icon: config.icon || Ext.MessageBox.INFO,
           fn: config.callback
       	});
	}
	return {
		/**
		 * @param {String} title：标题

		 * @param {String} msg：提示消息

		 * @param {Function} action：确认后执行的回调函数

		 */
		confirm: function(msg, action, title) {
			var _msg = msg || "未定义的确认消息";
			var _title = title || "确认";
			
			Ext.Msg.confirm(_title, _msg, function(btn, text) {
                if (btn == 'yes') {
					if(action) {
						action.call();
					}
                }
            });
		},
		/**
		 * @param {String} title：标题

		 * @param {String} msg：提示消息

		 * @param {Function} callback：确认后执行的回调函数

		 */
		info: function(config) {
			alert({
				title: config.title || '提示',
	        	msg: config.msg || "未定义提示信息",
	        	callback: config.callback
			});
		},
		/**
		 * @param {String} title：标题

		 * @param {String} msg：警告消息

		 * @param {Function} callback：确认后执行的回调函数

		 */
		warn: function(config) {
			alert({
				title: config.title || '警告',
	        	msg: config.msg || "未定义警告信息",
	        	icon: Ext.MessageBox.WARNING,
	        	callback: config.callback
			});
		},
		/**
		 * @param {String} title：标题

		 * @param {String} msg：错误消息

		 * @param {Function} callback：确认后执行的回调函数

		 */
		error: function(config) { 
		alert({
			title: config.title || '错误',
	        	msg: config.msg || "未定义错误信息",
	        	icon: Ext.MessageBox.ERROR,
	        	callback: config.callback
			});
		  },
		multiError: function(config){
		var msgPanel = new Ext.Panel({html:config.msg + '' || "未定义错误信息",autoScroll:true});
		var msgWin = new Ext.Window({title:config.title || '错误'
					     ,width: 380
					     ,height:300
					     ,hideBorders :true,autoScroll:true
					     ,items:msgPanel,
					      buttons:[new Ext.Button({
					      		text:'确定',
					      		handler:function(){msgWin.hide();}
					      })]
	     });
		msgWin.show();
		}
	    ,
		/**
		 * @param {String} title：标题

		 * @param {String} msg：成功消息

		 * @param {Function} callback：确认后执行的回调函数

		 */
		succuss: function(config) {
			alert({
				title: config.title || '成功',
	        	msg: config.msg || "操作成功！",
	        	callback: config.callback
			});
		}
	}
}();

function trim(str){ //删除左右两端的空格
　　     		return str.replace(/(^\s*)|(\s*$)/g, "");
　　 	}

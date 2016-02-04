/**
 * 菜单管理
 */
var MenuRecord = [{
		name: "id"
	}, {
		name: "name"
	}, {
		name: "url"
	}, {
		name: "parentId"
	}, {
		name: "orderNum"
	}, {
		name: "isFunction"
	}

];
/**
 * 菜单数的链接
 */
var MenuURL = {
	treeUrl: '/menu/menuTreeList.do?isFunction=0',
	formUrl: '/menu/get.do',
	saveUrl: '/menu/save.do',
	updateUrl: '/menu/update.do',
	deleteMenuURL: '/menu/delete.do',
	treeRootId: ''
};
var MenuChooser = function(config) {
	this.config = config;

};
MenuChooser.prototype = {
	showPanel: function(el, callback) {
		if (!this.panel) {
			this.getTree();
			this.getForm();
			this.tree.expandPath('/0/1');
			var cfg = {
				renderTo: 'menu',
				layout: 'column',
				//border: true,
				width: 950,
				items: [{
					columnWidth: 0.4,
					items: this.tree
				}, {
					columnWidth: 0.6,
					items: this.form
				}]
			};

			this.panel = new Ext.Panel(cfg);
		}
	},
	/**
	 * 返回数据回调函数
	 */
	doCallback: function(node) {
		//alert(node.text);
	},
	/**
	 * 新增一个子节点
	 */
	addMenu: function() {
		var selNode = this.tree.getSelectionModel().getSelectedNode();
		if (!selNode) {
			MessageHelper.info({
				msg: "请选择节点,添加其子节点!"
			});
			return;
		}
		if (selNode) {
			selNode.expand(false, false)
			var node = selNode.appendChild(new Ext.tree.TreeNode({
				text: '<span style="color:red;">请输入名称 </span>',
				cls: 'album-node',
				isLeaf: true,
				allowDrag: true,
				allowDelete: true,
				allowEdit: true,
				allowChildren: true
			}));
			node.id = -1;
			this.tree.getSelectionModel().select(node);
			var tempForm = this.form.getForm();
			tempForm.clearInvalid();
			tempForm.setValues({
				parentId: selNode.id,
				name: '',
				orderNum: '0',
				id: '',
				url: ''
			});
			tempForm.findField('name').focus(true);
		} else {
			MessageHelper.alert('请选择一个节点！');
		}

	},
	deleteMenu: function() {
		var selNode = this.tree.getSelectionModel().getSelectedNode();
		var tempForm = this.form.getForm();

		if (!selNode) {
			MessageHelper.info({
				msg: "请选择要删除的节点!"
			});
			return;
		}
		var aParentNode = selNode.parentNode;
		var aTree = this.tree;
		if (selNode) {
			if (selNode.id == -1) {
				selNode.remove();
			} else {
				MessageHelper.confirm(
					"确认删除所选节点吗？",
					function() {
						Ext.Ajax.request({
							url: Ext.getDom('root').value + MenuURL.deleteMenuURL,
							method: 'post',
							success: function(result, request) {
								var data =Ext.decode(result.responseText);
								if(data.success){
									Ext.Msg.alert("成功", '操作成功！');
									selNode.remove();
									aTree.getSelectionModel().select(aParentNode);
									tempForm.setValues({
										parentId: selNode.id,
										name: '',
										orderNum: '',
										id: '',
										url: ''
									});
								}else{
									Ext.Msg.alert("提示", data.errorMsg);
								}
							},
							failure: function(result, request) {
								Ext.Msg.alert("失败", Ext.util.JSON.decode(result.responseText).errorMsg);
							},
							params: {
								id: selNode.id
							}
						});
					}
				);
			}
		} else {
			MessageHelper.alert('请选择一个部门节点！');
		}
	},
	/**
	 * 保存修改的菜单数据

	 */
	saveMenu: function() {
		var selNode = this.tree.getSelectionModel().getSelectedNode();
		if (!selNode) {
			MessageHelper.info({
				msg: "请选择节点!"
			});
			return;
		}
		var aTree = this.tree;
		var aParentNode = selNode.parentNode;
		//Ext.getDom('id').value=selNode.id;
		var url = MenuURL.saveUrl;
		if (Ext.getDom("id").value != "") {
			url = MenuURL.updateUrl;
		}
		FormHelper.submit(
			url,
			function(form, action) {
				MessageHelper.succuss({
					callback: function() {
						if (selNode.id == -1) {
							aTree.getLoader().load(aParentNode, function() {
								aTree.expandPath(Ext.getDom('root').value + MenuURL.treeUrl);
								aParentNode.expand();
								aTree.getSelectionModel().select(aParentNode);
							});
						}
					}
				});
			}, {},
			this.form.getForm());

	},

	/**
	 * 生产一个菜单Form
	 */
	makeForm: function(config) {
		var form = new Ext.FormPanel({
			items: [{
				title: '菜单信息',
				layout: "form",
				items: [
					new Ext.form.TextField({
						fieldLabel: '名称',
						name: 'name',
						id: 'name',
						width: 300,
						allowBlank: false,
						listeners: {
							'change': {
								fn: function(o) {
									this.tree.getSelectionModel().getSelectedNode().setText(String(o.getValue()));
								},
								scope: this
							}
						}
					}),
					new Ext.form.TextField({
						fieldLabel: '链接地址',
						name: 'url',
						id: 'url',
						width: 300,
						allowBlank: true
					}),
					new Ext.form.TextField({
						fieldLabel: '同级排序',
						name: 'orderNum',
						id: 'orderNum',
						width: 300,
						allowBlank: true
					}),
					new Ext.form.TextField({
						name: 'id',
						id: 'id',
						hidden: true
					}),
					new Ext.form.TextField({
						name: 'isFunction',
						id: 'isFunction',
						hidden: true,
						value: '0'
					}),
					new Ext.form.TextField({
						name: 'parentId',
						id: 'parentId',
						hidden: true
					})
				],
				buttons: [new Ext.Button({
					text: '确定保存',
					handler: this.saveMenu,
					scope: this
				})],
				buttonAlign: 'center'
			}]
		});

		Ext.apply(form, config);
		return form;
	},
	/**
	 *  刷新菜单form详细信息
	 */
	resetFormDetail: function(node) {
		var _params = Ext.apply({}, {
			formOrGrid: "form",
			id: node.id
		});
		//		hs.FormHelper.load(this.config.formUrl,_params,function(form,action){
		//				var data = Ext.util.JSON.decode(action.result.data);
		//				form.clearInvalid();
		//				form.setValues(data);
		//			},this.form.getForm());
		this.form.getForm().doAction('load', {
			failure: function(form, action) {
				//				alert(action.result)  ;
				var msg;
				if (action.result)
					msg = action.result.data;
				if (!msg)
					msg = "您的请求服务器未响应！请刷新页面或重新登陆后重试；如问题依然存在，请与管理员联系！";
				if ('true' == action.result.multi) {
					MessageHelper.multiError({
						msg: msg
					});
				} else {
					MessageHelper.error({
						msg: msg
					});
				}
			},
			method: 'POST',
			params: _params,
			success: function(form, action) {
				var data = action.result.data;
				form.clearInvalid();
				form.setValues(data);
			},
			url: this.config.formUrl,
			waitMsg: '',
			waitTitle: '请稍候'

		});
	},
	/**
	 * 获取MenuChooser菜单树

	 */
	getTree: function() {
		if (!this.tree) {
			this.tree = new Ext.tree.TreePanel({
				title: this.config.treeTitle || '菜单树',
				bodyStyle: 'background:white',
				animate: true,
				region: 'west',
				split: true,
				frame: true,
				containerScroll: true,
				autoScroll: true,
				width: 380,
				height: 550,
				loader: new Ext.tree.TreeLoader({
					dataUrl: this.config.treeurl,
					//            	baseParams:{parentId:'0'},
					listeners: {
						'load': {
							fn: function() {
								this.tree.root.firstChild.select();
								this.showDetails(this.tree.root.firstChild)
							},
							scope: this,
							single: true
						}
					}
				}),
				buttons: [{
					text: '添加下级节点',
					handler: this.addMenu,
					scope: this
				}, {
					text: '删除节点',
					handler: this.deleteMenu,
					scope: this
				}],
				rootVisible: false,
				listeners: {
					'click': {
						fn: this.showDetails,
						scope: this
					},
					'dblclick': {
						fn: this.doCallback,
						scope: this
					}
				},
				root: new Ext.tree.AsyncTreeNode({
					text: '数据结构设置',
					draggable: false,
					allowDrag: false,
					id: '0'
				})
			})
		}
		return this.tree;
	},
	/**
	 * 获取MenuChooser菜单Form
	 */
	getForm: function() {
		if (!this.form) {
			var saveBtn = new Ext.Button({
				text: '确定保存',
				handler: this.saveMenu,
				scope: this
			});
			var formCfg = {
				//bodyStyle: 'padding-bottom:15px;background:#eee;',
				region: 'center',
				// frame: true,
				title: '',
				autoScroll: true,
				buttons: [saveBtn],
				buttonAlign: 'center'
			};
			this.form = this.makeForm(formCfg);
		}
		return this.form;
	},
	showDetails: function(node) {
		var detailEl = this.form.getEl();
		if (node) {
			//                detailEl.hide();  // 动画效果
			this.resetFormDetail(node);
			//                detailEl.slideIn('l', {stopFx:true,duration:.2});
		} else {
			detailEl.update('');
		}
	}

};
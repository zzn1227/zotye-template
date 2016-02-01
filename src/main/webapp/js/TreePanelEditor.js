Ext.ns("zotye.ux");  
/** 
 * @createtime 2010-01-02 
 * @author tcl 
 * @class TreePanelEditor 
 * @extends Ext.tree.TreePanel 
 * @description 树菜单编辑器,可带右键操作菜单，使用方式示例见ArchiveTypeTempView.js 
 */  
zotye.ux.TreePanelEditor=Ext.extend(Ext.tree.TreePanel,{  
    showContextMenu:true,  
    url:null,  
    /** 
     * 右键菜单 
     * @type  
     */  
    contextMenu:null,  
    /** 
     * 右键菜单项 
     * @type  
     */  
    contextMenuItems:null,  
    /** 
     * 选择树节点 
     * @type  
     */  
    selectedNode:null,  
    /** 
     * 构造函数 
     */  
    constructor:function(_cfg){  
        if(_cfg==null){  
            _cfg={};  
        }  
        Ext.apply(this,_cfg);  
        //从父类中拷贝构造  
        zotye.ux.TreePanelEditor.superclass.constructor.call(this,{  
            tbar:new Ext.Toolbar({items:[  
                                {  
                                    xtype : 'button',  
                                    iconCls : 'btn-refresh',  
                                    text : '刷新',  
                                    handler : function() {  
                                        Ext.getCmp(_cfg.id).root.reload();  
                                    }  
                                }
//                                ,{  
//                                    xtype : 'button',  
//                                    text : '展开',  
//                                    iconCls : 'btn-expand',  
//                                    handler : function() {  
//                                        Ext.getCmp(_cfg.id).expandAll();  
//                                    }  
//                                }, {  
//                                    xtype : 'button',  
//                                    text : '收起',  
//                                    iconCls : 'btn-collapse',  
//                                    handler : function() {  
//                                        Ext.getCmp(_cfg.id).collapseAll();  
//                                    }  
//                                }  
            ]}),  
            loader : new Ext.tree.TreeLoader({  
                    url:this.url  
            }),  
            root : new Ext.tree.AsyncTreeNode({  
                    expanded : true  
            }),  
            rootVisible : false  
        });  
          
        //初始化右键的菜单  
        this.initContextMenu();  
          
    },//end of constructor  
      
//  initComponent: function(){  
//      zotye.ux.TreePanelEditor.superclass.initComponent.call(this);        
//  },  
  
    /** 
     * 右键菜单 
     */  
    initContextMenu:function(){  
        if(this.showContextMenu){  
            this.contextMenu= new Ext.menu.Menu({});  
            if(this.contextMenuItems!=null){  
                this.contextMenu.add(this.contextMenuItems);  
            }  
            //树的右键菜单的  
            this.on('contextmenu', this.contextHandler, this);  
        }  
    },  
    contextHandler:function contextmenu(node, e) {  
        this.selectedNode = new Ext.tree.TreeNode({  
                    id : node.id,  
                    text : node.text  
        });  
        this.contextMenu.showAt(e.getXY());  
    }  
});  
//Ext.reg("treePanelEditor",zotye.ux.TreePanelEditor);  
/**
 * 使用：
 * new zotye.ux.TreePanelEditor({
 *        xtype:'treePanelEditor',  
 *           id:'archivesTypeTree',  
  *          region : 'west',  
 *         title:'公文分类',  
 *           collapsible : true,  
 *           split : true,  
 *           width:200,  
 *           url:__ctxPath+'/archive/treeArchivesType.do',  
 *           contextMenuItems:[  
 *                       {  
 *                           text : '新建分类',  
 *                           scope : this,  
 *                           iconCls:'btn-add',  
 *                           handler : function(){  
 *                               new ArchivesTypeForm().show();  
 *                           }  
 *                       }, {  
 *                           text : '修改分类',  
 *                           scope : this,  
 *                           iconCls:'btn-edit',  
 *                           handler: function(){  
 *                               new ArchivesTypeForm({typeId:Ext.getCmp('archivesTypeTree').selectedNode.id}).show();  
 *                           }  
 *                       },{  
 *                           text : '删除分类',  
 *                           scope : this,  
 *                           iconCls:'btn-delete',  
 *                           handler : function(){  
 *                               var typeId=Ext.getCmp('archivesTypeTree').selectedNode.id;  
 *                               Ext.Ajax.request({  
 *                                   url:__ctxPath+'/archive/multiDelArchivesType.do',  
 *                                   params:{ids:typeId},  
 *                                   method:'POST',  
 *                                   success:function(response,options){  
 *                                       Ext.ux.Toast.msg('操作信息','成功删除该公文分类！');  
 *                                       Ext.getCmp('archivesTypeTree').root.reload();  
 *                                   },  
 *                                   failure:function(response,options){  
 *                                       Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');  
 *                                   }  
 *                               });  
 *                           }  
 *                       }  
 *           ]  
 *   
 * });
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */


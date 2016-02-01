 Ext.onReady(function(){
  	
      	var date = new Ext.form.DateField({
						applyTo : 'date',
						width : 120,
						format : 'Y-m-d',
						emptyText: document.getElementById("datetmp").value.substring(0,10)
					});
	 
    //根据返回产品编号 
       var partnoStore = new Ext.data.Store({   
   	
       url: 'loctPart.do?action=loctpart_no',
       
       reader: new Ext.data.JsonReader({   
            root: 'items',
            totalProperty: 'results',
   //         autoload: true,
            
            fields: [
                {name: 'partno', type: 'string'},
                {name: 'partname', type: 'string'}         
       ]
       })
    });
    
        
    // 物料编号   
    var partnobox = new Ext.form.ComboBox({ 
    	        id: 'partnoCombo',
               store: partnoStore,   
               valueField :"partno",   
               displayField: "partno",   
               mode: 'local',    
               emptyText:'请选择物料编号...',//默认值 
               hiddenName:'partno',  
               editable: true,
               applyTo : 'part_no',
               listeners:{          
                   render: function(){
                   	partnoStore.load();
                   },
                   select : function(combo, record, index)     
                     { 
                     	document.getElementById("part_name").value= record.get("partname");                  
                     }
                },
                
               triggerAction: 'all'
    });    
    
        //实现模糊联想
    beforequery(partnobox);
   
    
    // 物料名称   
    var partnamebox = new Ext.form.ComboBox({ 
    	id: 'partnameCombo',
               store: partnoStore,   
               valueField :"partname",   
               displayField: "partname",   
               mode: 'local',   
             //  forceSelection: true,//必须选择一项   
               emptyText:'请选择物料名称...',//默认值 
               hiddenName:'partname',  
               applyTo : 'part_name',
               editable: true,
               listeners:{          
                   render: function(){
                     partnoStore.load({params:{SupplierID:document.getElementById("loctno").value}});
                   },
                   select : function(combo, record, index)     
                     { 
                     	  document.getElementById("part_no").value= record.get("partno");              
                     }
                },
              
               triggerAction: 'all'
    });  
    
     //实现模糊联想
    beforequery(partnamebox);
    
    //库位
    var loctstore =  new Ext.data.Store({ 
       url: 'loct.do?action=loct_select',
       
       reader: new Ext.data.JsonReader({   
            root: 'items',
            totalProperty: 'results',
   //         autoload: true,
            
            fields: [
                {name: 'loct_no', type: 'string'},
                {name: 'loct_name', type: 'string'}
            ]
        })
    });
     // 库位号   
    var loctnobox = new Ext.form.ComboBox({ 
    	       id: 'loctnoCombo',   	
               store: loctstore,   
               valueField :"loct_no",   
               displayField: "loct_no",   
               mode: 'local',     
               emptyText:'请选择供应商编号...',//默认值 
               hiddenName:'loct_no',  
               editable: true,
               applyTo : 'loctno',
                listeners:{          
                 render: function(){
                 	loctstore.load(); 
                 },
                 select : function(combo, record, index)     
                     { 
                     	
                     	document.getElementById("loctname").value= record.get("loct_name");                 
                     }
               },
               triggerAction: 'all'             
    });  
    //实现模糊联想
      beforequery(loctnobox);
   
    // 库位名称  
    var loctnamebox = new Ext.form.ComboBox({ 
    	       id: 'loctnameCombo',   	
               store: loctstore,   
               valueField :"loct_name",   
               displayField: "loct_name",   
               mode: 'local',     
               emptyText:'请选择供应商名称...',//默认值 
               hiddenName:'loct_name',  
               editable: true,
               applyTo : 'loctname',
                listeners:{          
                 render: function(){
                 	loctstore.load(); 
                 },
                 select : function(combo, record, index)     
                     {                        	
                     	document.getElementById("loctno").value= record.get("loct_no");                 
                     }
               },
               triggerAction: 'all'             
    });   
    //实现模糊联想
   beforequery(loctnamebox);

 //实现模糊联想的函数
 function beforequery(combox){
  combox.on('beforequery',function(qe){   
    var combo = qe.combo;   
    //q is the text that user inputed.   
    var q = qe.query;   
    forceAll = qe.forceAll;   
    if(forceAll === true || (q.length >= combo.minChars)){   
        if(combo.lastQuery !== q){   
            combo.lastQuery = q;   
            if(combo.mode == 'local'){   
                combo.selectedIndex = -1;   
                if(forceAll){   
                    combo.store.clearFilter();   
                }else{   
                    combo.store.filterBy(function(record,id){   
                        var text = record.get(combo.displayField);   
                        //在这里写自己的过滤代码   
                        return (text.indexOf(q)!=-1);   
                    });   
                }   
                combo.onLoad();   
            }else{   
                combo.store.baseParams[combo.queryParam] = q;   
                combo.store.load({   
                    params: combo.getParams(q)   
                });   
                combo.expand();   
            }   
        }else{   
            combo.selectedIndex = -1;   
            combo.onLoad();   
        }   
    }   
    return false;   
});  
 }
 
  var enddate = new Ext.form.DateField({
						applyTo : 'enddate',
						width : 120,
						format : 'Y-m-d'
					})
    
})
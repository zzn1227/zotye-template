// tab为表格ID
function downloadViewData(tab,title,args) {
	var i, j;
	try {
		var xls = new ActiveXObject("Excel.Application");
	} catch (e) {
		alert("您必须安装Excel2000或以上，同时浏览器须使用“ActiveX 控件”，您的浏览器须允许执行控件。请选择Internet设置--安全选项--本地Intranet--改区域的安全级别设置为低，才能导出到Excel！");
		return "";
	}

	xls.visible = true; // 设置excel为可见

	var xlBook = xls.Workbooks.Add;
	var xlsheet = xlBook.Worksheets(1);
    xlsheet.Rows(1).RowHeight = 25;
	xlsheet.Rows(2).RowHeight = 20;
	var oTable = document.all[tab];
	var rowNum = oTable.rows.length;
	var colNum = oTable.rows[0].cells.length;
	//var colNum = 10;
		// <!--合并--> 第一列
	xlsheet.Range(xlsheet.Cells(1, 1), xlsheet.Cells(1, colNum)).mergecells = true;
	xlsheet.Range(xlsheet.Cells(1, 1), xlsheet.Cells(1, colNum)).value = title;
	// <!--合并--> 第二列
	xlsheet.Range(xlsheet.Cells(2, 1), xlsheet.Cells(2, colNum)).mergecells = true;
	xlsheet.Range(xlsheet.Cells(2, 1), xlsheet.Cells(2, colNum)).value = args;
		// <!--设置行高-->
	xlsheet.Rows(1).RowHeight = 25;
	xlsheet.Rows(2).RowHeight = 20;
	// <!--设置字体 ws.Range(ws.Cells(i0+1,j0), ws.Cells(i0+1,j1)).Font.Size = 13
	// -->
	xlsheet.Rows(1).Font.Size = 14;
	// <!--设置字体 设置选定区的字体 xlsheet.Range(xlsheet.Cells(i0,j0),
	// ws.Cells(i0,j0)).Font.Name = "黑体" -->
	xlsheet.Rows(1).Font.Name = "黑体";
	xlsheet.Rows(2).Font.Name = "黑体";
	xlsheet.Range(xlsheet.Cells(1, 1), xlsheet.Cells(rowNum + 4, colNum)).HorizontalAlignment = -4108;// 居中
	xlsheet.Range(xlsheet.Cells(1, 1), xlsheet.Cells(1, colNum)).VerticalAlignment = -4108;
	xlsheet.Range(xlsheet.Cells(1, 1), xlsheet.Cells(2, colNum)).VerticalAlignment = -4108;
	for (i = 1; i <= rowNum; i++) {
		for (j = 1; j <= colNum; j++) {
			// html table类容写到excel
			xlsheet.Cells(i+2, j).Value = oTable.rows(i-1).cells(j-1).innerText;
		}
	}
	xls.UserControl = true; // 很重要,不能省略,不然会出问题 意思是excel交由用户控制
	xls = null;
	xlBook = null;
	xlsheet = null;

}

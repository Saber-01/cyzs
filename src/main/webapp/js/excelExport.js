/**
 * 
 */
 var idTmr;  
        function  getExplorer() {  
            var explorer = window.navigator.userAgent ;  
            //ie  
            if (explorer.indexOf("MSIE") >= 0 || (explorer.indexOf("Windows NT 6.1;") >= 0 && explorer.indexOf("Trident/7.0;") >= 0)) {  
             
                return 'ie';  
            }  
            //firefox  
            else if (explorer.indexOf("Firefox") >= 0) {  
                return 'Firefox';  
            }  
            //Chrome  
            else if(explorer.indexOf("Chrome") >= 0){  
                return 'Chrome';  
            }  
            //Opera  
            else if(explorer.indexOf("Opera") >= 0){  
                return 'Opera';  
            }  
            //Safari  
            else if(explorer.indexOf("Safari") >= 0){  
                return 'Safari';  
            }  
        }  
        //另一种表格导出方法
        function excelExport(tableid) {  
            if(getExplorer()=='ie')  
            {  
            	 var curTbl = document.getElementById(tableid);
				 var oXL = new ActiveXObject("Excel.Application");
				 var oWB = oXL.Workbooks.Add();
				var xlsheet = oWB.Worksheets(1);
				var sel = document.body.createTextRange();
				sel.moveToElementText(curTbl);
				sel.execCommand("Copy");
				xlsheet.ActiveSheet.Cells(row,col).Borders.Weight = 2;
				xlsheet.Paste();
				var fname = oXL.Application.GetSaveAsFilename(name+".xls", "Excel Spreadsheets (*.xls), *.xls");
				oWB.SaveAs(fname);
				oWB.Close(savechanges = false);
				oXL.Quit();
				oXL = null;
            } else {  
                tableToExcel(tableid)  
            } 
    }  
        function Cleanup() {  
            window.clearInterval(idTmr);  
            CollectGarbage();  
        }  
        var tableToExcel = (function() {  
            var uri = 'data:application/vnd.ms-excel;base64,',  
                    template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>',  
                    base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },  
                    format = function(s, c) {  
                        return s.replace(/{(\w+)}/g,  
                                function(m, p) { return c[p]; }) }  
            return function(table, name) {  
                if (!table.nodeType) table = document.getElementById(table)  
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}  
                window.location.href = uri + base64(format(template, ctx))  
            }  
        })()  
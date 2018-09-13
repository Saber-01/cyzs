package com.org.cygs.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;























import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
















import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.Job;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.Contract;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PartPosition;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.TimePrice;
import com.org.cygs.pojo.Unit;
import com.org.cygs.service.CheckUnitService;
import com.org.cygs.service.ContractService;
import com.org.cygs.service.JobService;
import com.org.cygs.service.PartService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.service.TimePriceService;
import com.org.cygs.service.YUnitService;
import com.org.cygs.util.common.StringUtil;


@Controller
@RequestMapping("/timePrice")
public class TimePriceController {
	@Autowired
	private ContractService  contractService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private PartService PartService;
	@Autowired
	private YUnitService unitService;
	@Autowired
	private JobService JobService;
	@Autowired
	private CheckUnitService checkUnitService;
	@Autowired
	private TimePriceService timePriceService;
	

	//动态变更栋号选项
	@RequestMapping("/changeUnitNumber")
	public @ResponseBody String changeUnitNumber(@RequestBody String prId ) throws JsonParseException, JsonMappingException, IOException{
		
		System.out.println(prId+"onChange");
		ObjectMapper mapper=new ObjectMapper();
		Contract contract=mapper.readValue(prId, Contract.class);
		
		String id=contract.getPrId();
		System.out.println(id+"onChange");
		List<PrIndexPC> pList=projectService.getProjectDetailById(id);
		System.out.println(pList.size()+"123");
		
		String jsonList=new String();
		if(pList.size()>0){
		jsonList=mapper.writeValueAsString(pList);
		}
		
		return jsonList;		
	}





    //转到修改合同单价页面
	@RequestMapping("/toEditTimePrice/{jspId}")
	public String toEditTimePrice(@PathVariable("jspId") String jspId,Model model){
		System.out.println("toEditTimePrice");
		TimePrice timePrice=timePriceService.selectTimePriceByJspId(jspId);
		model.addAttribute("timePrice", timePrice);
		System.out.println(timePrice.toString());
		return "editTimePrice";
	}
	//修改合同单价
	@RequestMapping("/editTimePrice")
	public @ResponseBody String editTimePrice(HttpServletRequest request){
		TimePrice timePrice=new TimePrice();
		timePrice.setJspId(request.getParameter("jspId"));
		timePrice.setPrice(Double.parseDouble(request.getParameter("price")));
		try {
			timePriceService.updateTimePrice(timePrice);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";// TODO: handle exception
		}		
		return "1";
	}
	//批量删除合同单价
	@RequestMapping("/deletePrice")
	public @ResponseBody String deletePrice(@RequestBody String[] ids){
		System.out.println("deletePrice");
		System.out.println(ids.length);
		try {
			for(String jspId:ids){
				System.out.println(jspId);
				timePriceService.deleteTimePrice(jspId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "0";// TODO: handle exception
		}
		
	
		return "1";
		
	}
	
	//转到添加计时合同单价
	@RequestMapping("/toAddTimePrice")
	public String toAddTimePrice(Model model){
		String pId="PART201708290001";
		Part part=PartService.getPartById(pId);
		String psId="PPOS201709010003";//平工
		PartPosition position=PartService.getPartPosition(psId);
		String psId1="PPOS201709010004";//技工
		PartPosition position1=PartService.getPartPosition(psId1);
		List<Project> prList=projectService.getAllProjectName();
		List<CheckUnit> cuList=checkUnitService.getAllCheckUnit();
		
		List<Unit>  unList=unitService.getAllUnit();
		List<PartPosition> psList=new ArrayList<PartPosition>();
		psList.add(position);
		psList.add(position1);
		
		model.addAttribute("part", part);
		model.addAttribute("prList", prList);
		model.addAttribute("cuList", cuList);
		model.addAttribute("psList", psList);
		model.addAttribute("unList", unList);
		System.out.println("toaddPrice");
		return "addTimePrice";
	}
	
	//添加合同单价
	@RequestMapping("addPrice")
	public @ResponseBody String addPrice(HttpServletRequest request){
		System.out.println("addPrice");
		String prId=(String)request.getParameter("prId");
		String[] pcpIds=request.getParameterValues("pcpIdIsSelect");
		String psId=(String)request.getParameter("psId");
		String unId=(String)request.getParameter("unId");
		String cuId=(String)request.getParameter("cuId");
		Double price=Double.parseDouble(request.getParameter("price"));
		String jobKey=(String)request.getParameter("jobKey");
		String pId="PART201708290001";
		
		TimePrice timePrice=new TimePrice();
		timePrice.setPrId(prId);
		timePrice.setPsId(psId);
		timePrice.setCuId(cuId);
		timePrice.setUnId(unId);
		timePrice.setPrice(price);
		timePrice.setJobKey(jobKey);
		timePrice.setpId(pId);
		
		try {
			for(String pcpId:pcpIds){
				timePrice.setPcpId(pcpId);
				timePriceService.insertTimePrice(timePrice);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "0";// TODO: handle exception
		}				
		return "1";
	}

	//转到计时合同单价管理页面
	@RequestMapping("/manageTimePrice")
	public String manageTimePrice(HttpServletRequest request,Model model){
		List<Project> prList=projectService.getAllProjectName();
		List<CheckUnit> cuList=checkUnitService.getAllCheckUnit();
		
		model.addAttribute("prList", prList);
		model.addAttribute("cuList", cuList);
		
		return "manageTimePrice";
	}
	//查看计时单价
	@RequestMapping("/selectTimePrice")
	public @ResponseBody Page<TimePrice> selectTimePrice(@RequestBody Map<String, Object> map,HttpSession session){
		
		
		System.out.println("selectTimePrice");
	
		Page<TimePrice> pageTimePrice=timePriceService.selectTimePriceList(map);
		return pageTimePrice;
	}
	
	@RequestMapping("/selectTimePrice1")
	public @ResponseBody Map<String, Object> selectTimePrice1(@RequestParam Map<String, Object> map,HttpSession session){
		

		return timePriceService.selectTimePrice(map);
	}
    //转到批量导入计时单价
	@RequestMapping("/toImportTimePrice")
	public String toImportTimePrice(HttpServletRequest request,Model model){
		String filename=request.getParameter("filename");
		model.addAttribute("filename", filename);
		return "importTimePrice";
	}
	//批量导入计时单价
	@RequestMapping("uploadFile")
	public String uploadFile(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam("uploadfile") MultipartFile uploadfile, RedirectAttributes attr, Model model) throws IOException {
		
		
		String filename = uploadfile.getOriginalFilename();
		//设置允许上传文件类型
        String suffixList = ".xlsx,.xls";
        // 获取文件后缀
        String suffix = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

		String path = "D:/cyjz_file/price/" ;
		long gfilesize = 0;
		String gfilename = "";
		
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 获取文件名称
		int dot = filename.lastIndexOf('.'); 
		gfilename = filename.substring(0, dot);
		gfilesize = uploadfile.getSize();
		
		
		if (filename == null || filename == "") {
			System.out.println("上传失败!----文件名为空或路径不正确!");
			attr.addFlashAttribute("msg", "上传失败!<br/>文件名为:" + gfilename + "<br/>大小为:"+ gfilesize + "字节" + "<br/>出错信息为:<br/>--- 文件名为空或路径不正确!");
		}
		else if (uploadfile.getSize() > 20 * 1024 * 1024) {
			System.out.println("上传失败!----所传文件过大!");
			attr.addFlashAttribute("msg", "上传失败!<br/>文件名为:" + gfilename + "<br/>大小为:"+ gfilesize + "字节" + "<br/>出错信息为:<br/>--- 所传文件过大!");
		}
		else if (!suffixList.contains(suffix.trim().toLowerCase())) {
			System.out.println("文件类型不合法!只能为xlsx,xls,类型的文件");
			attr.addFlashAttribute("msg", "上传失败!<br/>文件名为:"+ gfilename+ "<br/>大小为:"+ gfilesize + "字节" + "<br/>出错信息为:<br/>--- 文件类型不合法!只能为xlsx,xls类型的文件");
		}

		else {
			uploadfile.transferTo(new File(path + File.separator + filename));
			System.out.println("上传成功!<br>文件名为:" + filename + "<br/>大小为:" + gfilesize  + "字节");
			attr.addFlashAttribute("msgsuccess", "上传成功!<br>文件名为:" + filename + "<br/>大小为:" + gfilesize + "字节");
		}
		attr.addAttribute("filename", filename);
		return "redirect:/timePrice/toImportTimePrice";
	}
	
	@RequestMapping(value="/importTimePriceExcel",produces="text/html;charset=UTF-8")
	public  String importTimePriceExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			  Model model) throws IOException {
		String filename=request.getParameter("filename");
		System.out.println("importExcel");
		System.out.println(filename);
		String filepath = "原本是空的";
		filepath = "D:/cyjz_file/price/" + filename;
		System.out.println("filepath=" + filepath);
		// 获取excel 文件
		List<TimePrice> timePrices = new ArrayList<TimePrice>();
		// 根据杨师兄提供的数据库表来命名
		String prId;
		String prName = "";// 工程名称
		String cuName = "";// 班组（结算单位）
		String cuId;
		String pcpId;
		String unitNumber = "";// 栋号		
		
		String unId;// 单位
		
		String price;
		String price1;// 单价
		String message = "";// 提示信息未关联
		int sumCount = 0;// 导入总条数
		int existCount = 0;// 数据库已存在的数据的条数
		int saveCount = 0;// 成功导入的条数
		
		
		unId="UNIT201707130001";
		String unName=unitService.getUnitById(unId).getUnName();
		String pId="PART201708290001";
		String pName=PartService.getPart(pId).getpName();
		String psId="PPOS201709010003";//平工
		String psName=PartService.getPartPosition(psId).getPsName();
		String psId1="PPOS201709010004";//技工
		String psName1=PartService.getPartPosition(psId1).getPsName();
		String jobKey="OPER201709010005";//平工其他
		String jobName=JobService.selectJob(jobKey).getJobName();
		String jobKey1="OPER201709010006";//技工其他
		String jobName1=JobService.selectJob(jobKey1).getJobName();
		// 创建对Excel工作簿文件的引用
		HSSFWorkbook hssfworkbook = new HSSFWorkbook(new FileInputStream(
				filepath));
		HSSFSheet hssfsheet;
		HSSFRow[] hssfrow = new HSSFRow[50];// 预计50行 hssfrow里存有了所有的行。
		HSSFRow hssfrow1;
		hssfsheet = hssfworkbook.getSheetAt(0);// 直接取第一个表单.三个表单的第一个。
		
		for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
			hssfrow[j] = hssfsheet.getRow(j);
			// 判断是否还存在需要导入的数据
			if (null == hssfsheet.getRow(j)) {
				System.out.println("这里已没有数据，在第" + j + "行");
				break;
			}
		}
		// ///////////////////////////////////////////蒙泳行-开始修改//////////////
		// 判断要独立出来
		boolean right = true;
		prName = hssfrow[0].getCell(1).getStringCellValue().trim(); // 工程
		List<Project> prList  = projectService.getProjectByPrName(prName);
		
		if (prList.size() == 0) {
			right = false;
			message = "工程名称(" + prName + ")不存在,请检查";
		}
		Project project =prList.get(0);
		prId=project.getPrId();	
		
		List<CheckUnit> cuList=new ArrayList<CheckUnit>();
		for (int cu = 1; cu < hssfrow[1].getLastCellNum(); cu++) {			
			if (hssfrow[1].getCell(cu) == null) {
				continue;
			} else if (hssfrow[1].getCell(cu).getCellType() == 0) {
				cuName = new Double(hssfrow[1].getCell(cu)
						.getNumericCellValue()).toString().trim();
			} else {// 如果EXCEL表格中的数据类型为字符串型
				cuName = hssfrow[1].getCell(cu).getStringCellValue().trim();
				if (cuName.equals("")) {
					continue;
				}				
			}	
			cuList = checkUnitService.selectCheckUnitListByName(cuName);
			if (cuList.size() == 0) {
				right = false;
				message = "班组（结算单位）(" + cuName + ")不存在,请检查";
				break;
			}
		}
		
		
		List<PrIndexPC> pcpList=new ArrayList<PrIndexPC>();
		for (int pcp = 1; pcp < hssfrow[2].getLastCellNum(); pcp++)// 第三行取栋号
		{
			if (hssfrow[2].getCell(pcp) == null) {
				continue;
			} else if (hssfrow[2].getCell(pcp).getCellType() == 0) {
				unitNumber = new Double(hssfrow[2].getCell(pcp).getNumericCellValue()).toString().trim();
			} else {// 如果EXCEL表格中的数据类型为字符串型
				unitNumber = hssfrow[2].getCell(pcp).getStringCellValue().trim();
				if (unitNumber.equals("")) {
					continue;
				}				
				 pcpList= projectService.getPrIndexPCByOption(prId, unitNumber);
				if (pcpList.size() == 0) {
					right = false;
					message = "栋号不存在(" + unitNumber + ")不存在,请检查";
					break;
				}
			} 			
		}
		
		
		
		
		
		//////////////////////////////////判断结束，开始处理///////////////////////////////////////////////////
		if (right) {
			for (int cu = 1; cu < hssfrow[1].getLastCellNum(); cu++) {//取结算单位
				if (hssfrow[1].getCell(cu) == null) {
					continue;
				} else if (hssfrow[1].getCell(cu).getCellType() == 0) {
					cuName = new Double(hssfrow[1].getCell(cu)
							.getNumericCellValue()).toString().trim();
				} else {// 如果EXCEL表格中的数据类型为字符串型
					cuName = hssfrow[1].getCell(cu)
							.getStringCellValue().trim();
					if (cuName.equals("")) {
						continue;
					}
				}
				cuList = checkUnitService.selectCheckUnitListByName(cuName);
				cuId=cuList.get(0).getCuId();
				for (int pcp = 1; pcp < hssfrow[2].getLastCellNum(); pcp++)// 第三行取栋号
				{
					if (hssfrow[2].getCell(pcp) == null) {
						continue;
					} else if (hssfrow[2].getCell(pcp).getCellType() == 0) {
						unitNumber = new Double(hssfrow[2].getCell(pcp).getNumericCellValue()).toString().trim();
					} else {// 如果EXCEL表格中的数据类型为字符串型
						unitNumber = hssfrow[2].getCell(pcp)
								.getStringCellValue().trim();
						if (unitNumber.equals("")) {
							continue;
						}
					}
					pcpList= projectService.getPrIndexPCByOption(prId, unitNumber);
					PrIndexPC prpc=pcpList.get(0);
				    pcpId=prpc.getPcPId();																		
	                
				    //先插入平工
				    TimePrice tPrice=new TimePrice();
					tPrice.setCuId(cuId);
					tPrice.setPrId(prId);
					tPrice.setPcpId(pcpId);
					tPrice.setJobKey(jobKey);
				   
					List<TimePrice> tpList=timePriceService.selectTimePrices(tPrice);
					hssfrow1 = hssfsheet.getRow(4);	
					if (tpList.size()>0) {
						TimePrice tp=tpList.get(0);
						price = new Double(hssfrow1.getCell(4).getNumericCellValue()).toString().trim(); 
						Double fp =new Double(price.toString());
						tp.setPrice(fp);
						timePriceService.updateTimePrice(tp);
						timePrices.add(tp);
						System.out.println(tp.toString());
						existCount++;
					}else{
						// 分部 固定其他  新建单价																
						
						price = new Double(hssfrow1.getCell(4).getNumericCellValue()).toString().trim(); 
						Double fp =new Double(price.toString());
						tPrice.setpId(pId);
						tPrice.setPsId(psId);
						tPrice.setCuName(cuName);
						tPrice.setPrName(prName);
						tPrice.setPcpNumber(unitNumber);
						tPrice.setpName(pName);
						tPrice.setPsName(psName);
						tPrice.setJobName(jobName);
						tPrice.setUnName(unName);
						tPrice.setPrice(fp);
						tPrice.setUnId(unId);
						timePriceService.insertTimePrice(tPrice);;
						timePrices.add(tPrice);
						System.out.println(tPrice.toString());
						saveCount++;							
					}
						
					
					//插入技工
					TimePrice tPrice1=new TimePrice();
					tPrice1.setPcpId(pcpId);
					tPrice1.setPrId(prId);
					tPrice1.setCuId(cuId);
					tPrice1.setJobKey(jobKey1);
					
					hssfrow1 = hssfsheet.getRow(5);	
					List<TimePrice> tpList1=timePriceService.selectTimePrices(tPrice1);
					if (tpList1.size()>0) {
						TimePrice tp1=tpList1.get(0);
						price1 = new Double(hssfrow1.getCell(4).getNumericCellValue()).toString().trim(); 
						Double fp1 =new Double(price1.toString());
						tp1.setPrice(fp1);
						timePriceService.updateTimePrice(tp1);
						timePrices.add(tp1);
						System.out.println(tp1.toString());
						existCount++;
					}else{																		
						price1 = new Double(hssfrow1.getCell(4).getNumericCellValue()).toString().trim(); 
						Double fp1 =new Double(price1.toString());
						tPrice1.setpId(pId);
						tPrice1.setPsId(psId1);
						tPrice1.setPrice(fp1);
						tPrice1.setUnId(unId);
						tPrice1.setpId(pId);
						tPrice1.setPsId(psId);
						tPrice1.setCuName(cuName);
						tPrice1.setPrName(prName);
						tPrice1.setPcpNumber(unitNumber);
						tPrice1.setpName(pName);
						tPrice1.setPsName(psName1);
						tPrice1.setJobName(jobName1);
						tPrice1.setUnName(unName);
						timePriceService.insertTimePrice(tPrice1);;
						timePrices.add(tPrice1);
						System.out.println(tPrice1.toString());
						saveCount++;							
					}		
						
					
				}
			}
		}
		// ////////////////////////////////////////////////////////////////////
        System.out.println(message);
		model.addAttribute("timePrices", timePrices);
		model.addAttribute("message", message);
		model.addAttribute("sumCount", sumCount);
		model.addAttribute("existCount", existCount);
		model.addAttribute("saveCount", saveCount);
		return "importedTimePriceExcel";		
	}
	
	
	// 下载附件
			@RequestMapping("/downloadFile")
			public void downloadFile(HttpServletRequest request, HttpSession session, HttpServletResponse response, RedirectAttributes attr, Model model) throws IOException {
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				try {
					String filepath = "D:/cyjz_file/price/model/JStemplate.xls";			
					response.setContentType("application/x-msdownload");
					response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("JStemplate.xls", "utf-8"));
					bis = new BufferedInputStream(new FileInputStream(new File(filepath)));
					bos = new BufferedOutputStream(response.getOutputStream());
					byte[] buff = new byte[20480]; // 缓冲20K
					int bytesRead;
					while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
						bos.write(buff, 0, bytesRead);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null)
						bis.close();
					if (bos != null)
						bos.close();
				}
			}
}

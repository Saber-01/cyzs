package com.org.cygs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.EditableValueHolder;
import javax.json.Json;
import javax.json.JsonArray;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;





















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

import com.org.cygs.pojo.Budget;
import com.org.cygs.pojo.BudgetContent;
import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.Contract;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.User;
import com.org.cygs.service.BudgetContentService;
import com.org.cygs.service.BudgetService;
import com.org.cygs.service.CheckUnitService;
import com.org.cygs.service.ContractService;
import com.org.cygs.service.JobService;
import com.org.cygs.service.MissionContentService;
import com.org.cygs.service.PartService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.service.YUnitService;
import com.org.cygs.util.common.CygsConst;


@Controller
@RequestMapping("/budget")
public class BudgetController {
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
	private MissionContentService missionContentService;
	@Autowired
	private BudgetService budgetService;
	@Autowired
	private BudgetContentService budgetContentService;
	
	private DecimalFormat    df   = new DecimalFormat("######0.00");
	
	
	
	@RequestMapping("/toAddBudget")
	public String toAddBudget(Model model){
		System.out.println("toNewBudget");
		List<Project> prList=projectService.getAllProjectName();
		List<CheckUnit> cuList=checkUnitService.getAllCheckUnit();
		List<Part>  partList=PartService.getAllPart();
		model.addAttribute("prList", prList);
		model.addAttribute("cuList", cuList);
		model.addAttribute("partList", partList);
		return "addBudget";
	}
	//转到管理预算清单
	@RequestMapping("/toManageBudget")
	public String toManageBudget(Model model){
		System.out.println("toManageBudget");
		List<Project> prList=projectService.getAllProjectName();		
		List<Part>  partList=PartService.getAllPart();
		model.addAttribute("prList", prList);		
		model.addAttribute("partList", partList);
		return "manageBudget";
	}
	
	//批量删除预算
	@RequestMapping("/deleteBudget")
	public @ResponseBody String deleteBudget(@RequestBody String[] ids){
		System.out.println("deleteBudget");
		System.out.println(ids.length);
		
		for(String bId:ids){
			System.out.println(bId);
			contractService.updateBIdNullByBId(bId);
			budgetContentService.deleteBudgetContentByBId(bId);
		}
		budgetService.deleteBudgetByBatch(ids);
		return "redirect:/budget/toManageBudget";
		
	}
	
	
	//转到修改预算信息
	@RequestMapping("/toEditBudget/{bId}")
	public String toEditBudget(@PathVariable("bId") String bId,Model model){
		Budget budget=budgetService.selectBudget(bId);
		model.addAttribute("budget", budget);		
		return "editBudget";
	}
	
	//修改预算信息
	@RequestMapping("/editBudget")
	public String editBudget(HttpServletRequest request,HttpSession session){
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
    	Date tdate = new Date(); // 现在的时间
		String bId=request.getParameter("bId");
		Double account=Double.parseDouble(request.getParameter("budgetAccount"));
		BigDecimal budgetAccount=new BigDecimal(account.doubleValue());
    	budgetAccount = budgetAccount.setScale(4, RoundingMode.HALF_UP);
		Budget budget=new Budget();
		budget.setbId(bId);
		Budget budget2=budgetService.selectBudget(bId);
		Double discAccount=account-(budget2.getBudgetAccount()).doubleValue();
		budget.setBudgetPos(request.getParameter("budgetPos"));
		budget.setBudgetName(request.getParameter("budgetName"));
		budget.setBudgetAccount(budgetAccount);
		budget.setRemark(request.getParameter("remark"));
		budgetService.updateBudget(budget);
		
		
		BigDecimal disc=new BigDecimal(discAccount.doubleValue());
		disc = disc.setScale(4, RoundingMode.HALF_UP);
		int serial=0;
		List<BudgetContent> bcList=budgetContentService.selectBudgetContentByBId(bId);
		if(bcList!=null&&bcList.size()>0)
		{
			BudgetContent bcs=bcList.get(0);
		    serial=bcs.getSerial()+1;
		}
    	BudgetContent bc=new BudgetContent();
		bc.setbId(bId);
		bc.setAccount(disc);
		bc.setBudgetTime(tdate);
		bc.setSerial(serial);
		bc.setuId(user.getuId());
		budgetContentService.addBudgetContent(bc);
		return "redirect:/budget/toManageBudget";
	}
	
	
	//转到修改预算单价信息
	@RequestMapping("/toBudgetPrice/{bId}")
	public String toBudgetPrice(@PathVariable("bId") String bId1,Model model){
		Contract contract=new Contract();
		contract.setbId(bId1);

		List<Contract> cList=contractService.selectContracts(contract);
        System.out.println(cList.size());
        
        
        if (cList !=null&&cList.size()>0) {
        	for (int i = 0; i < cList.size(); i++){
        		String bId=cList.get(i).getbId();
        		
                if (bId !=null) {
             	   Budget budget=budgetService.selectBudget(bId);
             	   cList.get(i).setBudgetPos(budget.getBudgetPos());
             	   cList.get(i).setBudgetName(budget.getBudgetName());
                }else {
                   cList.get(i).setBudgetPos("");
              	   cList.get(i).setBudgetName("");
				}
        		
            	String pcpId=cList.get(i).getPcpId();
            	String oId=cList.get(i).getJobKey();
                String cuId=cList.get(i).getCuId();

              
                //根据任务书内容查找已开量与已审量,将已开量和已审量写入数组中
                Float ykl=missionContentService.selectYKRealQuantity( cuId, pcpId, oId);
                System.out.println(ykl);
                if(ykl != null)
                	 cList.get(i).setYkl(ykl);
                else
                	 cList.get(i).setYkl(0);
                
                Float ysl=missionContentService.selectYSRealQuantity( cuId, pcpId, oId);
                System.out.println(ysl);
                if(ysl !=null)
                     cList.get(i).setYsl(ysl);
                else 
                	 cList.get(i).setYsl(0);
			
        	}
            System.out.println(cList.get(0).toString());
		}

		model.addAttribute("cList", cList);		
		return "budgetPrice";
	}
	
	//移除所选单价
	@RequestMapping("/deletePriceFromBudget")
	public @ResponseBody String deletePriceFromBudget(HttpServletRequest request){
		String[] opIds=request.getParameterValues("isAdd");
		try {
			if (opIds.length!=0&&opIds!=null) {
				for (int i = 0; i < opIds.length; i++) {				
					contractService.updateBIdNull(opIds[i]);
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "false";// TODO: handle exception
		}
		
		return "success";	
	}
	
	//转到查看预算清单
	@RequestMapping("/toBudgetDetail/{bId}")
	public String toBudgetDetial(@PathVariable("bId") String bId,Model model){
		System.out.println(bId);
		Budget budget=budgetService.selectBudget(bId);
		
		List<BudgetContent> bcList=budgetContentService.selectBudgetContentByBId(bId);
		model.addAttribute("bcList",bcList);
		model.addAttribute("budget", budget);	
		System.out.println(budget.toString());
		System.out.println(bcList.size());
		return "budgetDetail";
	}
	//添加预算详情
    @RequestMapping("/addBudgetContent1")
    public @ResponseBody String addBudgetContent1(HttpServletRequest request,HttpSession session,Model model){
    	User user = (User) session.getAttribute(CygsConst.USER_SESSION);
    	Date tdate = new Date(); // 现在的时间   	
    	String bId=request.getParameter("bId");
    	System.out.println("1"+bId);
    	String account1=request.getParameter("account");
    	System.out.println("2"+account1);
    	Double account=Double.parseDouble((String)request.getParameter("account"));
    	System.out.println(account);
    	String remark=request.getParameter("remark");
    	Budget budget1=budgetService.selectBudget(bId);
    	System.out.println("addBudgetContent"+budget1.toString());
    	Double acco=budget1.getBudgetAccount().doubleValue()+account;
    	BigDecimal budgetAccount=new BigDecimal(acco.doubleValue());
    	budgetAccount = budgetAccount.setScale(4, RoundingMode.HALF_UP);
    	BigDecimal bcAccount=new BigDecimal(account.doubleValue());
    	bcAccount = bcAccount.setScale(4, RoundingMode.HALF_UP);
    	budget1.setBudgetAccount(budgetAccount);
    	
    	try {
    		budgetService.updateBudget(budget1);
        	
        	int serial=0;
        	List<BudgetContent> bctList=budgetContentService.selectBudgetContentByBId(bId);
        	if(bctList!=null&&bctList.size()>0)
    		{
    			BudgetContent bcs=bctList.get(0);
    		    serial=bcs.getSerial()+1;
    		}
        	
        	BudgetContent bc=new BudgetContent();    	
    		bc.setbId(bId);
    		bc.setAccount(bcAccount);
    		bc.setBudgetTime(tdate);
    		bc.setSerial(serial);
    		bc.setuId(user.getuId());
    		bc.setRemark(remark);
    		budgetContentService.addBudgetContent(bc);
    		return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";// TODO: handle exception
		}
    
    	
		
		//System.out.println(bId);
		//Budget budget=budgetService.selectBudget(bId);		
		//List<BudgetContent> bcList=budgetContentService.selectBudgetContentByBId(bId);
		//model.addAttribute("bcList",bcList);
		//model.addAttribute("budget", budget);	
		//System.out.println(budget.toString());
		//System.out.println(bcList.size());
		//return "budgetDetail";
    	
    }
	//添加单价到预算
	@RequestMapping("/toAddPriceToBudget")
	public String toAddPriceToBudget(Model model){
		System.out.println("toNewBudget");
		List<Project> prList=projectService.getAllProjectName();
		List<CheckUnit> cuList=checkUnitService.getAllCheckUnit();
		List<Part>  partList=PartService.getAllPart();
		model.addAttribute("prList", prList);
		model.addAttribute("cuList", cuList);
		model.addAttribute("partList", partList);
		return "addPriceToBudget";
	}
	
	//单价加入已有预算
	@RequestMapping("/addPriceToBudget")
	public String addPriceToBudget(HttpServletRequest request)throws IOException{
		String[] opIds=request.getParameterValues("isAdd");
		String bId=request.getParameter("bId");
		System.out.println(bId+"test2");
		if (opIds!=null&&opIds.length>0&&bId!=null&&!bId.equals("")) {
			for (int i = 0; i < opIds.length; i++) {
				Contract contract=new Contract();
				contract.setbId(bId);
				contract.setOpId(opIds[i]);
				contractService.updateContract(contract);
				
			}
		}
		else {
			
			return null;
		}
		return "redirect:toAddPriceToBudget";
	}
	
	

	 //添加预算与已开量对比
	@RequestMapping("/toBudgetComparison")
	public String toBudgetComparison(Model model){
		System.out.println("toNewBudget");
		List<Project> prList=projectService.getAllProjectName();
		List<CheckUnit> cuList=checkUnitService.getAllCheckUnit();
		List<Part>  partList=PartService.getAllPart();
		model.addAttribute("prList", prList);
		model.addAttribute("cuList", cuList);
		model.addAttribute("partList", partList);
		return "budgetComparison";
	}
	
	
	//新建预算清单
    @RequestMapping("/addBudget")
    public @ResponseBody String addBudget(HttpServletRequest request,HttpSession session){
    	
    	System.out.println("addbudget");
    	User user = (User) session.getAttribute(CygsConst.USER_SESSION);
    	Date tdate = new Date(); // 现在的时间
    	
    	Double account=Double.parseDouble(request.getParameter("budgetAccount"));
    	BigDecimal budgetAccount=new BigDecimal(account.doubleValue());
    	budgetAccount = budgetAccount.setScale(4, RoundingMode.HALF_UP);
    	
    	Budget budget=new Budget();
    	budget.setPrId(request.getParameter("budgetPrId"));
    	budget.setPcPId(request.getParameter("budgetPcpId"));
    	budget.setpId(request.getParameter("budgetPId"));
    	budget.setUnId(request.getParameter("budgetUnId"));
    	budget.setBudgetPos(request.getParameter("budgetPos"));
    	budget.setBudgetName(request.getParameter("budgetName"));
    	budget.setBudgetAccount(budgetAccount);
    	budget.setuId(user.getuId());
    	budget.setBudgetTime(tdate);
    	System.out.println(budget.toString());   	
    	budgetService.addBudget(budget);
    	String bId=budgetService.getNewBudget().getbId();
    	System.out.println(bId+"zhen de  fan");
    	String[] opIds=request.getParameterValues("isAdd");
    	System.out.println(opIds.length);
    	System.out.println(opIds.toString());
    	try {
    		if (opIds.length>0) {
        		for (int i = 0; i < opIds.length; i++) {
        			Contract contract=new Contract();
        			contract.setOpId(opIds[i]);
        			contract.setbId(bId);
    				contractService.updateContract(contract);
    				Contract contract2=new Contract();
    				contract2=contractService.selectContractByOpId(opIds[i]);
    				System.out.println(contract2);	
    			}	
    		}
        	
        	BudgetContent firstbc=new BudgetContent();    	
    		firstbc.setbId(bId);
    		firstbc.setAccount(budgetAccount);
    		firstbc.setBudgetTime(tdate);
    		firstbc.setSerial(0001);
    		firstbc.setuId(user.getuId());
    		budgetContentService.addBudgetContent(firstbc);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";// TODO: handle exception
		}  			
    	return "success";
    }
    
	
	//合并预算清单
    @RequestMapping("/mergeBudget")
    public String mergeBudget(HttpServletRequest request,HttpSession session){
    	User user = (User) session.getAttribute(CygsConst.USER_SESSION);
    	Date tdate = new Date(); // 现在的时间
    	
    	Double account=Double.parseDouble(request.getParameter("budgetAccount"));
    	BigDecimal budgetAccount=new BigDecimal(account.doubleValue());
    	budgetAccount = budgetAccount.setScale(4, RoundingMode.HALF_UP);
    	
    	Budget budget=new Budget();
    	budget.setPrId(request.getParameter("budgetPrId"));
    	budget.setPcPId(request.getParameter("budgetPcpId"));
    	budget.setpId(request.getParameter("budgetPId"));
    	budget.setUnId(request.getParameter("budgetUnId"));
    	budget.setBudgetPos(request.getParameter("budgetPos"));
    	budget.setBudgetName(request.getParameter("budgetName"));
    	budget.setBudgetAccount(budgetAccount);
    	budget.setuId(user.getuId());
    	budget.setBudgetTime(tdate);
    	System.out.println(budget.toString());   	
    	budgetService.addBudgetTest(budget);
    	String bId=budgetService.getNewBudget().getbId();
    	System.out.println(bId+"zhen de  fan");
    	String[] bIds=request.getParameterValues("isAdd");
    	System.out.println(bIds.length);
    	System.out.println(bIds.toString());
    	if (bIds.length>0) {
    		for (int i = 0; i < bIds.length; i++) {
    			budgetContentService.deleteBudgetContentByBId(bIds[i]);   			
    		    contractService.updateBIdByBID(bId, bIds[i]);
    		}
		}
    	budgetService.deleteBudgetByBatch(bIds);
    	
    	BudgetContent firstbc=new BudgetContent();   	
		firstbc.setbId(bId);
		firstbc.setAccount(budgetAccount);
		firstbc.setBudgetTime(tdate);
		firstbc.setSerial(0001);
		firstbc.setuId(user.getuId());
		budgetContentService.addBudgetContent(firstbc);
		
    	return "redirect:/budget/toManageBudget";
    }
    
    
	//动态变更分部选项
	@RequestMapping("/changePart")
	public @ResponseBody List<Part> changePart(@RequestBody Map<String, Object> map) throws JsonParseException, JsonMappingException, IOException{
		System.out.println("changePart");
		System.out.println(map.toString()+"onChange1");
		Part part=new Part();
		part.setPrId((String)map.get("prId"));
		
		System.out.println(part.toString()+"onChange2");
		List<Part> pList=new ArrayList<Part>();
		if (part.getPrId()!=null && !part.getPrId().equals("")) {
			 pList=PartService.getPartList1(part);
		}		
		System.out.println(pList.size());	
		return pList;		
	}
	
    //查询可以加入预算的单价信息
    @RequestMapping("/selectPrice")
    public @ResponseBody String selectPrice(@RequestBody Map<String, Object> map) throws JsonGenerationException, JsonMappingException, IOException{
    	
    	System.out.println("selectPrice");
        Contract contract=new Contract();
        contract.setPrId((String) map.get("prId"));
        contract.setPcpId((String)map.get("pcpId"));
        contract.setpName((String)map.get("pName"));
        contract.setCuId((String)map.get("cuId"));
        String classOrNot=(String)map.get("classOrNot");
        System.out.println(classOrNot+"分类");
        contract.setClassOrNot(classOrNot);
        System.out.println(contract.toString());
        
        List<Contract> cList=contractService.selectContracts(contract);
        System.out.println(cList.size());
        
        
        if (cList !=null&&cList.size()>0) {
        	for (int i = 0; i < cList.size(); i++){
        		String bId=cList.get(i).getbId();
        		
                if (bId !=null) {
             	   Budget budget=budgetService.selectBudget(bId);
             	   cList.get(i).setBudgetPos(budget.getBudgetPos());
             	   cList.get(i).setBudgetName(budget.getBudgetName());
                }else {
                   cList.get(i).setBudgetPos("");
              	   cList.get(i).setBudgetName("");
				}
        		
            	String pcpId=cList.get(i).getPcpId();
            	String oId=cList.get(i).getJobKey();
                String cuId=cList.get(i).getCuId();

              
                //根据任务书内容查找已开量与已审量,将已开量和已审量写入数组中
                Float ykl=missionContentService.selectYKRealQuantity( cuId, pcpId, oId);
                System.out.println(ykl);
                if(ykl != null)
                	 cList.get(i).setYkl(ykl);
                else
                	 cList.get(i).setYkl(0);
                
                Float ysl=missionContentService.selectYSRealQuantity( cuId, pcpId, oId);
                System.out.println(ysl);
                if(ysl !=null)
                     cList.get(i).setYsl(ysl);
                else 
                	 cList.get(i).setYsl(0);
			
        	}
            System.out.println(cList.get(0).toString());
		}
        
        Budget budget=new Budget();
        budget.setPrId((String) map.get("prId"));
        budget.setPcPId((String)map.get("pcpId"));
        budget.setpName((String)map.get("pName"));
        List<Budget> bList=budgetService.selectBudgetList(budget);
        System.out.println(cList.size()+"and"+bList.size());
        ObjectMapper mapper=new ObjectMapper();
        String jsonList=mapper.writeValueAsString(cList);
        
    	return jsonList; 	
    }
    
	
    @RequestMapping("/selectPriceNew")
    public @ResponseBody Map<String, Object> selectPriceNew(@RequestParam Map<String , Object> map){
    	int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = contractService.getContractCount(map);
		System.out.println(totals);
		List<Contract> cList = contractService.selectContractListNew(map);
		if (cList !=null&&cList.size()>0) {
        	for (int i = 0; i < cList.size(); i++){
        		String bId=cList.get(i).getbId();
        		
                if (bId !=null) {
             	   Budget budget=budgetService.selectBudget(bId);
             	   cList.get(i).setBudgetPos(budget.getBudgetPos());
             	   cList.get(i).setBudgetName(budget.getBudgetName());
                }else {
                   cList.get(i).setBudgetPos("");
              	   cList.get(i).setBudgetName("");
				}
        		
            	String pcpId=cList.get(i).getPcpId();
            	String oId=cList.get(i).getJobKey();
                String cuId=cList.get(i).getCuId();

              
                //根据任务书内容查找已开量与已审量,将已开量和已审量写入数组中
                Float ykl=missionContentService.selectYKRealQuantity( cuId, pcpId, oId);
                System.out.println(ykl);
                if(ykl != null)
                	 cList.get(i).setYkl(ykl);
                else
                	 cList.get(i).setYkl(0);
                
                Float ysl=missionContentService.selectYSRealQuantity( cuId, pcpId, oId);
                System.out.println(ysl);
                if(ysl !=null)
                     cList.get(i).setYsl(ysl);
                else 
                	 cList.get(i).setYsl(0);
			
        	}
            System.out.println(cList.get(0).toString());
		}
		Map<String, Object> pages = new HashMap<String, Object>();
		pages.put("total", totals);
		pages.put("rows", cList);
		return pages;
    }
    
    //查询可以加入预算的单价信息
    @RequestMapping("/selectPrice1")
    public @ResponseBody Map<String , Object> selectPrice1(@RequestBody Map<String, Object> map) throws JsonGenerationException, JsonMappingException, IOException{
    	
    	System.out.println("selectPrice");
        Contract contract=new Contract();
        contract.setPrId((String) map.get("prId"));
        contract.setPcpId((String)map.get("pcpId"));
        contract.setpName((String)map.get("pName"));
        contract.setCuId((String)map.get("cuId"));
        String classOrNot=(String)map.get("classOrNot");
        System.out.println(classOrNot+"分类");
        contract.setClassOrNot(classOrNot);
        System.out.println(contract.toString());
        
        List<Contract> cList=contractService.selectContracts(contract);
        System.out.println(cList.size());
        
        
        if (cList !=null&&cList.size()>0) {
        	for (int i = 0; i < cList.size(); i++){
        		String bId=cList.get(i).getbId();
        		
                if (bId !=null) {
             	   Budget budget=budgetService.selectBudget(bId);
             	   cList.get(i).setBudgetPos(budget.getBudgetPos());
             	   cList.get(i).setBudgetName(budget.getBudgetName());
                }else {
                   cList.get(i).setBudgetPos("");
              	   cList.get(i).setBudgetName("");
				}
        		
            	String pcpId=cList.get(i).getPcpId();
            	String oId=cList.get(i).getJobKey();
                String cuId=cList.get(i).getCuId();

              
                //根据任务书内容查找已开量与已审量,将已开量和已审量写入数组中
                Float ykl=missionContentService.selectYKRealQuantity( cuId, pcpId, oId);
                System.out.println(ykl);
                if(ykl != null)
                	 cList.get(i).setYkl(ykl);
                else
                	 cList.get(i).setYkl(0);
                
                Float ysl=missionContentService.selectYSRealQuantity( cuId, pcpId, oId);
                System.out.println(ysl);
                if(ysl !=null)
                     cList.get(i).setYsl(ysl);
                else 
                	 cList.get(i).setYsl(0);
			
        	}
            System.out.println(cList.get(0).toString());
		}
        
        Budget budget=new Budget();
        budget.setPrId((String) map.get("prId"));
        budget.setPcPId((String)map.get("pcpId"));
        budget.setpName((String)map.get("pName"));
        List<Budget> bList=budgetService.selectBudgetList(budget);
        System.out.println(cList.size()+"and"+bList.size());
       
        Map<String , Object> result=new  HashMap<String, Object>();
        result.put("cList", cList);
        result.put("bList", bList);
        
    	return result; 	
    }
    
    
    //查询可以加入预算的单价信息
    @RequestMapping("/selectBudget")
    public @ResponseBody List<Budget> selectBudget(@RequestBody Map<String, Object> map){
    	
    	System.out.println("selectBudget");
        Budget budget1=new Budget();
        budget1.setPrId((String) map.get("prId"));
        budget1.setPcPId((String)map.get("pcpId"));
        budget1.setpName((String)map.get("pName"));

        System.out.println(budget1.toString());
        List<Budget> bList=budgetService.selectBudgetList(budget1);
        
        System.out.println(bList.size());
        if (bList !=null&&bList.size()>0) {
        	for (int j = 0; j < bList.size(); j++){
        		    String bId=bList.get(j).getbId();
        		    if (bList.get(j).getRemark()==null) {
						bList.get(j).setRemark("");
					}
        		    Contract contract=new Contract();
        		    contract.setbId(bId);
        		    List<Contract> cList=contractService.selectContracts(contract);
        	        System.out.println(cList.size());  
        	        Double yklSum=new Double(0.0);
        	        Double yslSum=new Double(0.0);
        	        if (cList !=null&&cList.size()>0) {
        	        	System.out.println(cList.size());
        	        	for (int i = 0; i < cList.size(); i++){       	           	        		
        	            	String pcpId=cList.get(i).getPcpId();
        	            	String oId=cList.get(i).getJobKey();
        	                String cuId=cList.get(i).getCuId();        	              
        	                //根据任务书内容查找已开量与已审量,将已开量和已审量写入数组中
        	                Float ykl=missionContentService.selectYKRealQuantity( cuId, pcpId, oId);
        	                
        	                if(ykl != null)
        	                	yklSum+=(double)ykl;
        	                System.out.println(yklSum);
        	                Float ysl=missionContentService.selectYSRealQuantity( cuId, pcpId, oId);
        	                System.out.println(ysl);
        	                if(ysl !=null)
        	                     yslSum+=(double)ysl;
        	                System.out.println(yslSum);
        	        	}
        			}
        	        yklSum=Double.parseDouble(df.format(yklSum));
        	        yslSum=Double.parseDouble(df.format(yslSum));
        	        Double disc=Double.parseDouble(df.format(bList.get(j).getBudgetAccount()))-yklSum;
        	        disc=Double.parseDouble(df.format(disc));
        	        System.out.println(yslSum+"  "+yklSum+"   "+disc);
        	     bList.get(j).setYkl(yklSum);
        	     bList.get(j).setYsl(yslSum);
        	     bList.get(j).setDisc(disc);
        	}    		
    }
        return bList; 
    }
}

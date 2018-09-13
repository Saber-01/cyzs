package com.org.cygs.controller;

import java.util.List;









import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.poi.util.StringUtil;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PartPosition;
import com.org.cygs.service.PartService;

@Controller
@RequestMapping("/part")
public class PartPositionController {
	@Autowired
	private PartService partService;
	

   //跳转到分部位置界面	
	@RequestMapping("/partPositionList")
	public String partPositionList(HttpServletRequest request,Model model){
		
		List<Part> partList=partService.getAllPart();
		
		model.addAttribute("partList",partList);
		
		return "listPartPosition";
	}
	
	//显示工程部位
	@RequestMapping("/selectPosition")
	public @ResponseBody PagePojo selectPosition(@RequestBody Map<String, String> map){
		System.out.println("selectPosition");
		PartPosition partPosition=new PartPosition();
		partPosition.setPsName(map.get("psName").toString());
		partPosition.setpName(map.get("pName").toString());
		System.out.println(map.get("psName").toString()+map.get("pName").toString()+"test");
		int pageNum=Integer.parseInt(map.get("pageNo"));
		PagePojo<PartPosition> positionPage=partService.partPositionPage(pageNum, 15, partPosition);
		
		return positionPage;
		
	}
	
	@RequestMapping(value="/selectPosition1")
	public @ResponseBody Map<String, Object> selectPosition1(@RequestParam Map<String, Object> map){
		
		return partService.getPartPositionList(map);
	}
	
	//跳转到添加分部位置界面
	@RequestMapping("/toAddPosition")
	public String toAddPosition(Model model){
		List<Part> partList=partService.getAllPart();
		model.addAttribute("partList",partList);
		return "addPosition";
	}
 
	//添加分部位置
    @RequestMapping("/addPosition")
    public @ResponseBody String addPosition(HttpServletRequest request){
    	PartPosition position=new PartPosition();
    	position.setpId(request.getParameter("pName"));
    	position.setPsName(request.getParameter("psName"));
    	position.setRemark(request.getParameter("remark"));
    	System.out.println(position.toString());
    	try {
    		partService.addPartPosition(position);
    		return "1";
		} catch (Exception e) {
			e.printStackTrace();
			
		    return "0";
		}   	
    	
    }
    
    //跳转到修改分部位置界面
    @RequestMapping("/toEditPosition/{psId}")
    public String toEditPosition(@PathVariable("psId")  String psId,Model model){
    	System.out.println(psId);
    	PartPosition position=partService.getPartPosition(psId);
    	List<Part> partList=partService.getAllPart();
		model.addAttribute("partList",partList);
    	model.addAttribute("partPosition",position);
    	return "editPosition";
    }
    
	//修改分部信息
	@RequestMapping("/editPosition")
	public @ResponseBody String editPosition(HttpServletRequest request){
		PartPosition position=new PartPosition();
		position.setPsId((String)request.getParameter("psId"));
		position.setpId((String)request.getParameter("pId"));
		position.setPsName((String)request.getParameter("psName"));
		position.setRemark((String)request.getParameter("remark"));
		System.out.println(position.getpId());
		try {
			partService.updatePartPosition(position);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";// TODO: handle exception
		}
		
		return "1";
	}
	
	//删除分部位置
	@RequestMapping("/deletePosition/{psId}")
	public @ResponseBody String deletePosition(@PathVariable("psId") String psId){
		System.out.println("deletePosition");
		try {
			partService.deletePartPosition(psId);
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}				
	}
	
		
}

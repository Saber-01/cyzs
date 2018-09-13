package com.org.cygs.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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






import com.org.cygs.pojo.Job;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PartPosition;
import com.org.cygs.pojo.Unit;
import com.org.cygs.service.JobService;
import com.org.cygs.service.PartPositionService;
import com.org.cygs.service.PartService;
import com.org.cygs.service.YUnitService;
import com.org.cygs.util.common.StringUtil;

@Controller
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobService jobService;
	@Autowired
	private PartService PartService;
	@Autowired
	private PartPositionService partPositionService;
	@Autowired
	private YUnitService unitService;
	
	
	@RequestMapping("/jobList")
	public String jobList(HttpServletRequest request,Model model){
		
		List<PartPosition> pList=PartService.getDistinctPsName();		
		model.addAttribute("pList",pList);
		return "listJob";
	}
	
	@RequestMapping("/selectJob1")
	public @ResponseBody PagePojo jobPage(@RequestBody Map<String, String> map){
		Job job=new Job();
		System.out.println(map.get("jobName").toString()+map.get("psName").toString()+"test");
		job.setJobName(map.get("jobName").toString());
		job.setPsName(map.get("psName").toString());
		int pageNo = Integer.parseInt(map.get("pageNo"));
		PagePojo<Job> jobPage=jobService.slelectJobList(pageNo, 15, job);
		
		return jobPage;
	}
	
	@RequestMapping("/selectJob")
	public @ResponseBody Map<String, Object> selectJob(@RequestParam Map<String, Object> map){
		return jobService.getJobList(map);
	}
	//跳转到添加工作项目界面
		@RequestMapping("/toAddJob")
		public String toAddJob(Model model){
		    List<Part> partList=PartService.getAllPart();
		    List<Unit> unitList=unitService.getAllUnit();
		    model.addAttribute(unitList);
		    model.addAttribute(partList);
 			return "addJob";
		}
	   
		//添加分部位置
	    @RequestMapping("/addJob")
	    public @ResponseBody String addJob(HttpServletRequest request){
	    	System.out.println("addjob");
	    	
			Job jobNew=jobService.getNewJobKey();
			String jobId;
			if (jobNew!=null) {
				jobId=jobNew.getJobId();
				jobId=com.org.cygs.util.common.StringUtil.autoIncrement(jobId);
			}else {
				jobId="0001";
			}
			
	    	Job job=new Job();
	    	job.setJobKey(request.getParameter("jobKey"));
	    	job.setJobId(jobId);
	    	job.setJobName(request.getParameter("jobName"));
	    	job.setPsId(request.getParameter("psId"));
	    	job.setUnId(request.getParameter("unId"));
	        job.setRemark(request.getParameter("remark"));
	        System.out.println(job.toString());
	        try {
	        	jobService.addJob(job);
			} catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
				return "0";
			}	    		    	
	    	return "1";
	    }
	    
	    //跳转到修改分部位置界面
	    @RequestMapping("/toEditJob/{jobKey}")
	    public String toEditJob(@PathVariable("jobKey")  String jobKey,Model model){
	    	System.out.println(jobKey);
	    	Job job=jobService.selectJob(jobKey);	    	
	    	System.out.println(job.toString());
	    	String psId=job.getPsId();
	    	PartPosition partPosition=PartService.getPartPosition(psId);
	    	String unId=job.getUnId();
	    	Unit unit=unitService.getUnitById(unId);
	    	List<Part> partList=PartService.getAllPart();
		    List<Unit> unitList=unitService.getAllUnit();
		   
		    model.addAttribute(unitList);
		    model.addAttribute(partList);
	    	model.addAttribute("job",job);
	    	model.addAttribute("partPosition",partPosition);
	    	model.addAttribute("unit",unit);
	    	return "editJob";
	    }
	    
		//修改分部信息
		@RequestMapping("/editJob")
		public @ResponseBody String editJob(HttpServletRequest request){
		    System.out.println("editJob");
			Job job=new Job();			
			job.setJobKey(request.getParameter("jobKey"));
	    	job.setJobId(request.getParameter("jobId"));
	    	job.setJobName(request.getParameter("jobName"));
	    	job.setPsId(request.getParameter("psId"));
	    	job.setUnId(request.getParameter("unId"));
	        job.setRemark(request.getParameter("remark"));
			System.out.println(job.toString());
			try {
				jobService.updateJob(job);
			} catch (Exception e) {
				e.printStackTrace();
				return "0";// TODO: handle exception
			}			
			return "1";
		}
		
		//删除分部位置
		@RequestMapping("/deleteJob/{jobKey}")
		public @ResponseBody String deletePart(@PathVariable("jobKey") String jobKey){
			try {
				jobService.deleteJob(jobKey);
			} catch (Exception e) {
				e.printStackTrace();
				return "0";// TODO: handle exception
			}
			
			return "1";
		}
		
		@RequestMapping("/onChange")
		public @ResponseBody String onChange(@RequestBody String pId ) throws JsonParseException, JsonMappingException, IOException{
			
			System.out.println(pId+"onChange");
			ObjectMapper mapper=new ObjectMapper();
			Part part=mapper.readValue(pId, Part.class);								
			String id=part.getpId();
			System.out.println(id+"onChange");
			//List<PartPosition> pList=PartService.getPartPositionByPId(id);
			List<PartPosition> pList= partPositionService.selectPartPositionByPId(id);
			System.out.println(pList.size()+"123");
			String jsonList=new String();				
			if(pList.size()>0){
		       jsonList=mapper.writeValueAsString(pList);
			}
			
			return jsonList;		
		}
		
	    //跳转到查看备注界面
	    @RequestMapping("/toViewRemark/{jobKey}")
	    public String toViewRemark(@PathVariable("jobKey")  String jobKey,Model model){
	    	System.out.println(jobKey);
	    	Job job=jobService.selectJob(jobKey);	    	
	    	System.out.println(job.toString());
	    
	    	model.addAttribute("job",job);

	    	return "viewJobRemark";
	    }
}

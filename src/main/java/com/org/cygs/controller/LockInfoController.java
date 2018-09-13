package com.org.cygs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.CheckUnitVO;
import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.InCheckUnit;
import com.org.cygs.pojo.InPrIndexPc;
import com.org.cygs.pojo.InProject;
import com.org.cygs.pojo.InProjectVo;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.ProjectIndexS;
import com.org.cygs.pojo.UnitNameVO;
import com.org.cygs.pojo.User;
import com.org.cygs.service.CheckUnitService;
import com.org.cygs.service.LockInfoService;
import com.org.cygs.service.ProjectIndexPCService;
import com.org.cygs.service.ProjectIndexSService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.service.UserService;
import com.org.cygs.util.common.CygsConst;
/**
 * 个人信息管理模块
 * 1. 修改密码
 * 2. 工程信息锁定
 * 3. 审计工程信息锁定
 * 4. 安装工程锁定
 * @author hnn
 *
 */
@Controller
public class LockInfoController {
	@Autowired
	private UserService userService;
	@Autowired
	private LockInfoService lockInfoService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectIndexSService prIndexSService;
	@Autowired
	private ProjectIndexPCService prIndexPcService;
	@Autowired
	private CheckUnitService checkUnitService;

	/**
	 * 1. 修改密码
	 */
	// 修改密码页面跳转
	@RequestMapping("/viewInfo")
	public String viewInfo(HttpServletRequest request, HttpSession session, Model model) {
		/*User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		model.addAttribute("userIfo", user);*/
		return "viewPersonalInfo";
	}
	
	// json格式
	@RequestMapping(value="/listUserInfo")
	public @ResponseBody User jsonTest(HttpServletRequest request, HttpSession session, Model model) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		if(user.getUserRemark()==null || user.getUserRemark()=="") {
			user.setUserRemark("无");
		}
		return user;
	}

	// 修改密码页面跳转
	@RequestMapping("/editPassword")
	public String viewPassword(HttpServletRequest request, HttpSession session, Model model) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		model.addAttribute("password", user.getUserPassword());
		return "editPassword";
	}
	
	// 修改密码
	@RequestMapping(value="/alterPassword")
	public String alterpassword(HttpServletRequest request, HttpSession session, 
			RedirectAttributes attr, Model model) {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		
		if(oldPassword.equals(user.getUserPassword())) {
			userService.alterPassword(user.getuId(), newPassword);
			user.setUserPassword(newPassword);
			session.setAttribute(CygsConst.USER_SESSION, user);
			attr.addFlashAttribute("message", "修改密码成功！");
			return "redirect:/viewInfo";
			
		} else {
			// 重定向传参
			attr.addFlashAttribute("message", "旧密码输入错误！请重新输入");
			return "redirect:/editPassword";
		}
	}
	
	/**
	 * 2. 工程信息锁定
	 */
	// 工程信息锁定页面
	@RequestMapping(value = "/prelockProject")
	public String prelockProject(HttpServletRequest request, HttpSession session, Model model) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String uId = user.getuId();
		List<ProjectIndexS> list = prIndexSService.selectSProject(uId);
		System.out.println("显示工程锁定列表");
		model.addAttribute("prList", list);
		model.addAttribute("user", user);
		
		return "information/listLockInfo";
	}
	
	// xml转为json
	@RequestMapping(value = "/toShowLockInfo")
	public @ResponseBody List<Map<String, Object>> toChangeFormat(HttpServletRequest request, HttpSession session, Model model) throws IOException, ParserConfigurationException, SAXException {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
	
		// 创建DocumentBuilderFactory对象
		DocumentBuilderFactory a = DocumentBuilderFactory.newInstance();
		// 创建DocumentBuilder对象
		DocumentBuilder b = a.newDocumentBuilder();
		// 通过DocumentBuilder对象的parse方法返回一个Document对象
		org.w3c.dom.Document document = (org.w3c.dom.Document) b.parse("D:/cyjz_file/xmle/" + user.getUserLoginName() + ".xml");
		// 通过Document对象的getElementsByTagName()返根节点的一个list集合
		NodeList prlist = ((org.w3c.dom.Document) document).getElementsByTagName("project");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < prlist.getLength(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 循环遍历获取每一个project
			Node project = prlist.item(i);
			// 通过Node对象的getAttributes()方法获取全的属性值
			NamedNodeMap prmap = project.getAttributes();
			// 循环遍每一个project的属性值
			for (int j = 0; j < prmap.getLength(); j++) {
				Node node = prmap.item(j);
				map.put(node.getNodeName(), node.getNodeValue());
			}
			// 解析project节点的子节点
			NodeList childlist = project.getChildNodes();
			for (int t = 0; t < childlist.getLength(); t++) {
				// 区分出text类型的node以及element类型的node
				if (childlist.item(t).getNodeType() == Node.ELEMENT_NODE) {
					if (childlist.item(t).hasChildNodes()) {
						List<Object> li = new ArrayList<>();
						NodeList rootNode = childlist.item(t).getChildNodes();
						for (int m = 0; m < rootNode.getLength(); m++){
							li.add(rootNode.item(m).getTextContent());
						}
						map.put(childlist.item(t).getNodeName(), li);
					}else
						map.put(childlist.item(t).getNodeName(), childlist.item(t).getTextContent());
				}
			}
			list.add(map);
			System.out.print("\nlist" + i + ":"+ " " + list.get(i));
		}
		return list;
	}
	
	// 添加工程信息页面
	@RequestMapping(value = "/tolockProject/{prId}")
	public String tolockProject(HttpServletRequest request, @PathVariable(value = "prId") String prId, HttpSession session, Model model) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		Project project = projectService.getProjectById(prId);
	    Vector<UnitNameVO> unitVector=new Vector<UnitNameVO>();
	    Vector<CheckUnitVO> cuVector=new Vector<CheckUnitVO>();
	    
		String filename = "D:/cyjz_file/xmle/" + user.getUserLoginName() + ".xml";

		Document document = null;
		File file = new File(filename);
		if (!file.getParentFile().isDirectory()) { // 如果xmle文件夹不存在则创建它
			file.getParentFile().mkdirs();
		}
		ArrayList<String> units = new ArrayList<String>();
		//ArrayList<String> departments = new ArrayList<String>();
		ArrayList<String> checkunits = new ArrayList<String>();
		
		if (!file.exists()) { // 如果第一次进入，xml文件不存在,则不做勾选
			Document adocument = DocumentHelper.createDocument(); // 建立document对象
			adocument.addElement("lockinf"); // 建立XML文档的根lockinf
			try {
				XMLWriter writer = new XMLWriter(new FileOutputStream(filename)); // 将document中的内容写入文件中
				writer.write(adocument);
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				SAXReader saxReader = new SAXReader();
				document = saxReader.read(file);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Element root = document.getRootElement();

			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element eproject = (Element) i.next();
				if (prId.equals(eproject.attributeValue("prid"))) {
					// 栋号
					Element eunits = eproject.element("units");
					List<?> nodes = eunits.elements("unit");
					for (Iterator<?> eut = nodes.iterator(); eut.hasNext();) {
						Element unit = (Element) eut.next();
						units.add(unit.attributeValue("unitid"));
					}
					// 结算单位
					Element echeckunits = eproject.element("checkunits");
					List<?> cunodes = echeckunits.elements("checkunit");
					for (Iterator<?> ecu = cunodes.iterator(); ecu.hasNext();) {
						Element checkunit = (Element) ecu.next();
						checkunits.add(checkunit.attributeValue("checkunitid"));
					}
					break;
				}
			}
		}
		// 获得栋号
		List<PrIndexPC> prunits = prIndexPcService.selectUnitByPrId(prId);
		System.out.println(prId);
		System.out.println(prunits.size());
		if (prunits != null && prunits.size() > 0) {
			for (int i = 0; i < prunits.size(); i++) {
				String pcpid = prunits.get(i).getPcPId();
				String unitname = prunits.get(i).getUnitNumber();
				UnitNameVO un = new UnitNameVO();
				un.setUnitnumId(pcpid);
				un.setUnitnumName(unitname);
				if (units.size() > 0) {
					for (int j = 0; j < units.size(); j++) {
						if (pcpid.equals(units.get(j))) {
							un.setCheck("checked");
							break;
						}
					}
				} else {
					un.setCheck("1");
				}
				unitVector.add(un);
			}
		}
		
		// 结算单位
		List<CheckUnit> culist = checkUnitService.selectAllCheckUnit();
		if (culist != null && culist.size() > 0) {
			for (int i = 0; i < culist.size(); i++) {
				CheckUnit cu = new CheckUnit();
				cu = (CheckUnit) culist.get(i);
				
				CheckUnitVO cuvo=new CheckUnitVO();
				cuvo.setCuId(cu.getCuId());
				cuvo.setCuName(cu.getCuName());
				if(checkunits.size()>0){
				for (int j = 0; j < checkunits.size(); j++) {
					if (cu.getCuId().equals(checkunits.get(j))) {
						cuvo.setCheck("checked");
						break;
					}
				}
				}else{
					cuvo.setCheck("1");
				}
				cuVector.add(cuvo);
			}
		}
		model.addAttribute("prId", prId);
		model.addAttribute("prName", project.getPrName());
		model.addAttribute("unitVector", unitVector);
		model.addAttribute("cuVector", cuVector);
		
		return "information/addLockInfo";
	}

	// 添加工程锁定信息
	@RequestMapping(value = "/addLockProject")
	public String addLockProject(HttpServletRequest request, HttpSession session, Model model) throws DocumentException {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);

		String prId = request.getParameter("prId").toString();
		String[] unit = request.getParameterValues("units");
		String[] checkunit = request.getParameterValues("checkunits");
		
		System.out.println("执行添加锁定工程");
		//String units = request.getParameter("units").toString();
		//String departments = request.getParameter("departments").toString();
		//String checkunits = request.getParameter("checkunits").toString();
		//String[] unit=units.split("\\|");
		//String[] department=departments.split("\\|");
		//String[] checkunit=checkunits.split("\\|");
		
		String filename = "D:/cyjz_file/xmle/" + user.getUserLoginName() + ".xml";

		File file = new File(filename);
		if (!file.getParentFile().isDirectory()) { // 如果xmle文件夹不存在则创建它
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) { // 如果第一次进入，xml文件不存在则创建它
			Document adocument = DocumentHelper.createDocument(); // 建立document对象
			adocument.addElement("lockinf"); // 建立XML文档的根lockinf
			try {
				XMLWriter writer = new XMLWriter(new FileOutputStream(filename)); // 将document中的内容写入文件中
				writer.write(adocument);
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		SAXReader reader = new SAXReader();
		Document document = reader.read(filename);
		Element lockinf = document.getRootElement();
		List<?> list = document.selectNodes("/lockinf/project");
		if (!list.isEmpty()) {
			Iterator<?> iter = list.iterator();
			while (iter.hasNext()) {
				Element project = (Element) iter.next();
				if (project.attribute("prid").getValue().equals(prId)) {
					lockinf.remove(project);
				}
			}
		}

		// 加入第一个project节点
		Element projectElement = lockinf.addElement("project");
		// 加入参数内容
		projectElement.addAttribute("prid", prId);

		// 加入prname节点
		Project pro = projectService.getProjectById(prId);
		String prname = pro.getPrName();
		Element prnameElement = projectElement.addElement("prname");
		prnameElement.setText(prname);

		// 加入units节点
		Element unitsElement = projectElement.addElement("units");
		// 为units设置内容
		if (unit[0] == "") {
			Element unitElement = unitsElement.addElement("unit");
			unitElement.addAttribute("unitid", "null");
			unitElement.setText("null");
		} else {
			for (int i = 0; i < unit.length; i++) {
				Element unitElement = unitsElement.addElement("unit");
				unitElement.addAttribute("unitid", unit[i]);
				PrIndexPC prpcobj = prIndexPcService.selectPrPcByPrimaryKey(unit[i]);
				unitElement.setText(prpcobj.getUnitNumber());
			}
		}

		// 加入checkunits节点
		Element checkunitsElement = projectElement.addElement("checkunits");
		// 为checkunits设置内容

		if (checkunit[0] == "") {
			Element checkunitElement = checkunitsElement.addElement("checkunit");
			checkunitElement.addAttribute("checkunitid", "null");
			checkunitElement.setText("null");
		} else {
			for (int i = 0; i < checkunit.length; i++) {
				Element checkunitElement = checkunitsElement.addElement("checkunit");
				checkunitElement.addAttribute("checkunitid", checkunit[i]);
				CheckUnit cu = checkUnitService.getCheckUnitById(checkunit[i]);
				checkunitElement.setText(cu.getCuName());
			}
		}

		try {
			// 将document中的内容写入文件中
			XMLWriter writer = new XMLWriter(new FileOutputStream(filename));
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "redirect:/prelockProject";
	}
	
	// 删除工程锁定信息
	@RequestMapping(value = "/delLockProject/{prId}")
	public String delLockProject(HttpServletRequest request, @PathVariable(value = "prId") String prId, 
			HttpSession session, Model model) throws DocumentException {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		System.out.println("删除工程锁定信息"+prId);
		String filename = "D:/cyjz_file/xmle/" + user.getUserLoginName() + ".xml";
		SAXReader reader = new SAXReader();
		Document document = reader.read(filename);
		Element lockinf = document.getRootElement();
		List<?> list = document.selectNodes("/lockinf/project");
		Iterator<?> iter = list.iterator();
		
		while (iter.hasNext()) {
			Element project = (Element) iter.next();
			if (project.attribute("prid").getValue().equals(prId)) {
				lockinf.remove(project);
			}
		}
		try {
			// 将document中的内容写入文件中
			XMLWriter writer = new XMLWriter(new FileOutputStream(filename));
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/prelockProject";
	}
	
	
	/**
	 * 3. 审计工程信息锁定 
	 */
	// 到审计工程信息锁定页面
	@RequestMapping(value = "/prelockSj")
	public String prelockSj(HttpServletRequest request, HttpSession session,
			Model model) throws DocumentException {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);

		List<Project> prlist = projectService.getAllProjectName();
		Vector<Project> lockedprojects = new Vector<Project>();
		
		String filename = "D:/cyjz_file/xmlsj/" + user.getUserLoginName() + ".xml";
		File file = new File(filename);
		if (!file.getParentFile().isDirectory()) { // 如果xmlsj文件夹不存在则创建它
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) { // 如果第一次进入，xml文件不存在则创建它
			Document adocument = DocumentHelper.createDocument(); // 建立document对象
			adocument.addElement("lockinf"); // 建立XML文档的根lockinf
			try {
				XMLWriter writer = new XMLWriter(new FileOutputStream(filename)); // 将document中的内容写入文件中
				writer.write(adocument);
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		SAXReader reader = new SAXReader();
		Document document = reader.read(filename);
		document.getRootElement();
		List<?> list = document.selectNodes("/lockinf/projects/project");
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			Element prs = (Element) iter.next();
			if (!prs.getText().equals("")) {
				Project pr = projectService.getProjectById(prs.getText());
				System.out.println("已锁定的审计工程：" + pr.getPrName());
				lockedprojects.add(pr);
			}
		}

		model.addAttribute("projects", prlist);
		model.addAttribute("lockedprojects", lockedprojects);

		return "information/addSjLockInfo";
	}
	
	// 添加审计工程锁定信息
	@RequestMapping("/addSjLockInfo")
	public String addSjLockInfo(@RequestParam("prids") String prids, HttpSession session, Model model) throws DocumentException {
		String projects = prids;
		String[] prid = projects.split("\\|");
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);

		String filename = "D:/cyjz_file/xmlsj/" + user.getUserLoginName() + ".xml";
		File file = new File(filename);
		
		if (!file.getParentFile().isDirectory()) { // 如果xmlsj文件夹不存在则创建它
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) { // 如果第一次进入，xml文件不存在则创建它
			Document adocument = DocumentHelper.createDocument(); // 建立document对象
			adocument.addElement("lockinf"); // 建立XML文档的根lockinf
			try {
				XMLWriter writer = new XMLWriter(new FileOutputStream(filename)); // 将document中的内容写入文件中
				writer.write(adocument);
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		SAXReader reader = new SAXReader();
		Document document = reader.read(filename);
		Element lockinf = document.getRootElement();

		List<?> list = document.selectNodes("/lockinf/projects");
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			Element prs = (Element) iter.next();
			lockinf.remove(prs);
		}

		// 加入prid节点
		Element projectsElement = lockinf.addElement("projects");
		// 为prid设置内容

		for (int i = 0; i < prid.length; i++) {
			Element projectElement = projectsElement.addElement("project");
			projectElement.setText(prid[i]);
		}

		try {
			// 将document中的内容写入文件中
			XMLWriter writer = new XMLWriter(new FileOutputStream(filename));
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/prelockSj";
	}
	
	/**
	 * 4. 安装工程锁定
	 */
	// 转到添加安装锁定页面
	@RequestMapping("/preLockInProject")
	public String preLockInProject(HttpSession session, Model model) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String uId = user.getuId();
		String rolename = user.getRoleName();
		
		Vector<InProject> projects = new Vector<InProject>();
		if (rolename.equals("安装施工负责人") || rolename.equals("安装预算") || rolename.equals("安装项目经理")) {
			List<InProject> inprList = lockInfoService.selectInPrByUid(uId);
			if (inprList != null && inprList.size() > 0) {
				for (int i = 0; i < inprList.size(); i++) {
					projects.add(inprList.get(i));
				}
			}
		}
		else;
		model.addAttribute("inPrList", projects);
		model.addAttribute("user", user);
		
		return "information/listInLockInfo";
	}
	
	// 添加安装工程信息页面
	@RequestMapping(value = "/toLockInProject/{inPrId}")
	public String toLockInProject(HttpServletRequest request, @PathVariable(value = "inPrId") String inPrId,
			@RequestParam("inPrName") String inPrName,
			HttpSession session, Model model) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		
		Vector<UnitNameVO> unitVector = new Vector<UnitNameVO>();
		Vector<CheckUnitVO> cuVector = new Vector<CheckUnitVO>();
		List<InProjectVo> inPrInfo = lockInfoService.selectInPrPcByInPrId(inPrId);

		String filename = "D:/cyjz_file/xmlin/" + user.getUserLoginName() + ".xml";

		Document document = null;
		File file = new File(filename);
		if (!file.getParentFile().isDirectory()) { // 如果xmle文件夹不存在则创建它
			file.getParentFile().mkdirs();
		}
		ArrayList<String> units = new ArrayList<String>();
		ArrayList<String> checkunits = new ArrayList<String>();

		if (!file.exists()) { // 如果第一次进入，xml文件不存在,则不做勾选
			Document adocument = DocumentHelper.createDocument(); // 建立document对象
			adocument.addElement("lockinf"); // 建立XML文档的根lockinf
			try {
				XMLWriter writer = new XMLWriter(new FileOutputStream(filename)); // 将document中的内容写入文件中
				writer.write(adocument);
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				SAXReader saxReader = new SAXReader();
				document = saxReader.read(file);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Element root = document.getRootElement();

			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element eproject = (Element) i.next();
				if (inPrId.equals(eproject.attributeValue("prid"))) {
					// 栋号
					Element eunits = eproject.element("units");
					List<?> nodes = eunits.elements("unit");
					for (Iterator<?> eut = nodes.iterator(); eut.hasNext();) {
						Element unit = (Element) eut.next();
						units.add(unit.attributeValue("unitid"));
					}
					// 结算单位
					Element echeckunits = eproject.element("checkunits");
					List<?> cunodes = echeckunits.elements("checkunit");
					for (Iterator<?> ecu = cunodes.iterator(); ecu.hasNext();) {
						Element checkunit = (Element) ecu.next();
						checkunits.add(checkunit.attributeValue("checkunitid"));
					}
					break;
				}
			}
		}
		// 获得栋号
		if (inPrInfo != null && inPrInfo.size() > 0) {
			for (int i = 0; i < inPrInfo.size(); i++) {
				InPrIndexPc inprpc = inPrInfo.get(i).getInPrIndexPc();
				UnitNameVO un = new UnitNameVO();
				un.setUnitnumId(inprpc.getInPcPId());
				un.setUnitnumName(inprpc.getUnitNumber());
				if (units.size() > 0) {
					for (int j = 0; j < units.size(); j++) {
						if (inprpc.getInPcPId().equals(units.get(j))) {
							un.setCheck("checked");
							break;
						}
					}
				} else {
					un.setCheck("1");
				}
				unitVector.add(un);
			}
		}
		// 结算单位
		List<InCheckUnit> culist = lockInfoService.selectAllInCu();
		if (culist != null && culist.size() > 0) {
			for (int i = 0; i < culist.size(); i++) {
				InCheckUnit cu = new InCheckUnit();
				cu = (InCheckUnit) culist.get(i);

				CheckUnitVO cuvo = new CheckUnitVO();
				cuvo.setCuId(cu.getInCuId());
				cuvo.setCuName(cu.getCuName());
				if (checkunits.size() > 0) {
					for (int j = 0; j < checkunits.size(); j++) {
						if (cu.getInCuId().equals(checkunits.get(j))) {
							cuvo.setCheck("checked");
							break;
						}
					}
				} else {
					cuvo.setCheck("1");
				}
				cuVector.add(cuvo);
			}
		}
		model.addAttribute("inPrId", inPrId);
		model.addAttribute("inPrName", inPrName);
		model.addAttribute("unitVector", unitVector);
		model.addAttribute("cuVector", cuVector);

		return "information/addInLockInfo";
	}

	// 添加安装工程锁定信息
	@RequestMapping(value="/addLockInProject", method={RequestMethod.GET})
	public String addLockInProject(HttpServletRequest request, HttpSession session, Model model) throws DocumentException {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		System.out.println("添加安装工程锁定信息");
		
		String inPrId = request.getParameter("inPrId").toString();
		String inPrName = request.getParameter("inPrName").toString();
		String units = request.getParameter("units").toString();
		String checkunits = request.getParameter("checkunits").toString();
		
		/*// GET方法得到的中文字符是乱码
		try {
			inPrName = new String(inPrName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		
		String[] unit = units.split("\\|");
		String[] checkunit = checkunits.split("\\|");

		String filename = "D:/cyjz_file/xmlin/" + user.getUserLoginName() + ".xml";

		File file = new File(filename);
		if (!file.getParentFile().isDirectory()) { // 如果xmle文件夹不存在则创建它
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) { // 如果第一次进入，xml文件不存在则创建它
			Document adocument = DocumentHelper.createDocument(); // 建立document对象
			adocument.addElement("lockinf"); // 建立XML文档的根lockinf
			try {
				XMLWriter writer = new XMLWriter(new FileOutputStream(filename)); // 将document中的内容写入文件中
				writer.write(adocument);
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		SAXReader reader = new SAXReader();
		Document document = reader.read(filename);
		Element lockinf = document.getRootElement();
		List<?> list = document.selectNodes("/lockinf/project");
		if (!list.isEmpty()) {
			Iterator<?> iter = list.iterator();
			while (iter.hasNext()) {
				Element project = (Element) iter.next();
				if (project.attribute("prid").getValue().equals(inPrId)) {
					lockinf.remove(project);
				}
			}
		}
		List<InProjectVo> inPrInfo = lockInfoService.selectInPrPcByInPrId(inPrId);

		// 加入第一个project节点
		Element projectElement = lockinf.addElement("project");
		// 加入参数内容
		projectElement.addAttribute("prid", inPrId);

		// 加入prname节点
		Element prnameElement = projectElement.addElement("prname");
		prnameElement.setText(inPrName);

		// 加入units节点
		Element unitsElement = projectElement.addElement("units");
		// 为units设置内容
		if (unit[0] == "") {
			Element unitElement = unitsElement.addElement("unit");
			unitElement.addAttribute("unitid", "null");
			unitElement.setText("null");
		} else {
			for (int i = 0; i < unit.length; i++) {
				Element unitElement = unitsElement.addElement("unit");
				unitElement.addAttribute("unitid", unit[i]);
				unitElement.setText(inPrInfo.get(i).getPrName());
			}
		}

		// 加入checkunits节点
		Element checkunitsElement = projectElement.addElement("checkunits");
		// 为checkunits设置内容
		if (checkunit[0] == "") {
			Element checkunitElement = checkunitsElement
					.addElement("checkunit");
			checkunitElement.addAttribute("checkunitid", "null");
			checkunitElement.setText("null");
		} else {
			for (int i = 0; i < checkunit.length; i++) {
				Element checkunitElement = checkunitsElement
						.addElement("checkunit");
				checkunitElement.addAttribute("checkunitid", checkunit[i]);
				InCheckUnit cu = lockInfoService
						.selectInCuByPrimaryKey(checkunit[i]);
				checkunitElement.setText(cu.getCuName());
			}
		}
		try {
			// 将document中的内容写入文件中
			XMLWriter writer = new XMLWriter(new FileOutputStream(filename));
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "redirect:/preLockInProject";
	}

	// 删除工程锁定信息
	@RequestMapping(value = "/delLockInProject/{inPrId}")
	public String delLockInProject(HttpServletRequest request, @PathVariable(value = "inPrId") String inPrId, HttpSession session,
			Model model) throws DocumentException {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String filename = "D:/cyjz_file/xmlin/" + user.getUserLoginName() + ".xml";
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(filename);
		Element lockinf = document.getRootElement();
		List<?> list = document.selectNodes("/lockinf/project");
		Iterator<?> iter = list.iterator();

		while (iter.hasNext()) {
			Element project = (Element) iter.next();
			if (project.attribute("prid").getValue().equals(inPrId)) {
				lockinf.remove(project);
			}
		}
		try {
			// 将document中的内容写入文件中
			XMLWriter writer = new XMLWriter(new FileOutputStream(filename));
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/preLockInProject";
	}

}
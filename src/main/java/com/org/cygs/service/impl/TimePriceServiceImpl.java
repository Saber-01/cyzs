package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.TimePriceDao;
import com.org.cygs.pojo.Job;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.TimePrice;
import com.org.cygs.service.TimePriceService;

@Service("timePriceService")
public class TimePriceServiceImpl implements TimePriceService{
	@Resource
	private TimePriceDao timePriceDao;
	
	
	public void updateTimePrice(TimePrice timePrice){
		timePriceDao.updateTimePrice(timePrice);
	}
	
	public void deleteTimePrice(String jspId){
		timePriceDao.deleteTimePriceByBatch(jspId);
	}
	
	public void insertTimePriceByBatch(List<TimePrice> timePrices){
		timePriceDao.insertTimePriceByBatch(timePrices);
	}
	
	public TimePrice selectTimePriceByJspId(String jspId){
		TimePrice timePrice=new TimePrice();
		timePrice.setJspId(jspId);
		List<TimePrice> timePrices=timePriceDao.selectTimePrices(timePrice);
		TimePrice timePrice2=timePrices.get(0);
		return timePrice2;
	}
	
	public List<TimePrice>  selectTimePrices(TimePrice timePrice){
		return timePriceDao.selectTimePrices(timePrice);
	}
	
	public Page<TimePrice> selectTimePriceList(Map<String, Object> map){
		int pageNo = (int) map.get("pageNo");
		int offset = (pageNo-1)*15;
		map.put("offset", offset);
		map.put("pageSize", 15);
		int pageNum = pageNo;
		int totals = timePriceDao.getTimePriceCount(map);
		System.out.println(totals);
		List<TimePrice> timePriceList = timePriceDao.selectTimePriceList(map);		
		Page<TimePrice> timePricePage= new Page<TimePrice>(timePriceList,totals,pageNum,15,timePriceList.size());
		return timePricePage;
	}
	
	public PagePojo<TimePrice> selectTimePrice(int pageNum, int pageSize, TimePrice timePrice){
		PageHelper.startPage(pageNum, pageSize);
    	List<TimePrice> tList = timePriceDao.selectTimePrices(timePrice);
    	return new PagePojo<>(tList);
	}
	
	public void insertTimePrice(TimePrice timePrice){
		 timePriceDao.insertTimePrice(timePrice);
	}
	
	
	public Map<String, Object> selectTimePrice(Map<String, Object> map){
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = timePriceDao.getTimePriceCount(map);
		List<TimePrice> list = timePriceDao.selectTimePriceList(map);
		Map<String, Object> pages = new HashMap<String, Object>();
		pages.put("total", totals);
		pages.put("rows", list);
		return pages;
	}
}

package com.org.cygs.service;

import java.util.List;
import java.util.Map;


import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.TimePrice;

public interface TimePriceService {
	public void updateTimePrice(TimePrice timePrice);
	
	public void deleteTimePrice(String jspId);
	
	public void insertTimePriceByBatch(List<TimePrice> timePrices);
	
	public TimePrice selectTimePriceByJspId(String jspId);
		
	public List<TimePrice>  selectTimePrices(TimePrice timePrice);
    
	public Page<TimePrice> selectTimePriceList(Map<String, Object> map);
	
	public PagePojo<TimePrice> selectTimePrice(int pageNum, int pageSize, TimePrice timePrice);
	
	public void insertTimePrice(TimePrice timePrice);
	
	public Map<String, Object> selectTimePrice(Map<String, Object> map);
		
	
}

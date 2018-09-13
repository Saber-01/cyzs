package com.org.cygs.util.common;

import java.util.Map;

public class MapUtil {
	
	public static void MapPage(Map<String, Object> map){
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		map.remove("page");
		map.remove("rows");
	}

}

package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.JishiPriceDao;
import com.org.cygs.pojo.JishiPrice;
import com.org.cygs.service.JishiPriceService;

@Service("jishiPriceService")
public class JishiPriceServiceImpl implements JishiPriceService {

	@Resource
	private JishiPriceDao jishiPriceDao;

	public List<JishiPrice> selectJishiUnitPrice(String prId, String cuId, String pcpId) {
		return jishiPriceDao.selectJishiUnitPrice(prId, cuId, pcpId);
	}

}

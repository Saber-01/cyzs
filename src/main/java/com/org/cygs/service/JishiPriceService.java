package com.org.cygs.service;

import java.util.List;

import com.org.cygs.pojo.JishiPrice;

public interface JishiPriceService {

	public List<JishiPrice> selectJishiUnitPrice(String prId, String cuId, String pcpId);
}

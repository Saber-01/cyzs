package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.BudgetVo;
import com.org.cygs.pojo.OperationPrice;
import com.org.cygs.pojo.OperationPriceVo;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.YklHistory;

public interface OperationPriceService {
	public OperationPrice selectByPrimaryKey(String opId);
	public List<OperationPriceVo> selectAllByOpid(String opId);
	
	//���ݹ��̺�¥���Ż�ȡԤ������Ϣ
	List<OperationPriceVo> getYKL(Map<String,Object> map);
	
	//�޸�Ԥ����
	int editBudgetSum(Map<String,Object>map);

	//����opId��ȡOperetionPriceVo
	OperationPriceVo getOperationPriceVoByKey(String opId);
	
	//����Ԥ�����޸ļ�¼
	int addYKLmendRecord(YklHistory yh);
	
	//���ݹ��̡�¥��������㵥λ����ȡ���е�Ԥ�����޸ļ�¼
	List<YklHistory> getYklHistory(Map<String,Object> map);
	//easyUI
	Map<String,Object> getYKLList(Map<String,Object> map);
	
	public int setPageStatus(Map<String, Object> map);   //���÷�ҳ����,���ص�ǰҳ��
	
	public Page<YklHistory> selectYKLHistoryList(Map<String, Object> map);
	
	public Map<String,Object> getYKLHistoryList(Map<String,Object> map);
	//��ȡԤ�����޸ļ�¼������
	public int getYKLHistoryCount(Map<String,Object> map);
	
	//��ȡ�ѿ����󹤳��� ��Ϣ
	List<OperationPriceVo> getYklBudgetSum(Map<String,Object> map);
	//easyUI����ȡ�ѿ����󹤳�����Ϣ
	Map<String,Object> selectYklBudgetSum(Map<String,Object> map);

	//���ݹ��̡�¥�����Լ��ֲ�����ȡ���е�BudgetVo
	List<BudgetVo> getBudgetVo(Map<String,String> map);
	//����budgetVo��ȡ���е�operationPrice
	List<OperationPrice> getOperationPriceByBudget(BudgetVo bv);
	
	List<OperationPrice> selectPriceByOid(String oId);
	
	public List<OperationPriceVo> selectOperationPrice(String prId, String cuId, String pcpId);
	
	//public List<OperationPriceVo> queryUnitInfoByOid(String oId);
	
	//public List<OperationPriceVo> selectUnitPriceByOid(String oId);
	
	public List<OperationPriceVo> queryOperationPriceUnit(String prId, String pcpId, String pId, String psId, String oId, String cuId);
	
	public List<OperationPriceVo> queryUnitBySeven(String prId, String pcpId, String pId, String psId, String oId, String unId, String cuId);

}

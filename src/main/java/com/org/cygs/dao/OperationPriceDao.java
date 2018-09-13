package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.BudgetVo;
import com.org.cygs.pojo.OperationPrice;
import com.org.cygs.pojo.OperationPriceVo;
import com.org.cygs.pojo.YklHistory;

public interface OperationPriceDao {
	
	//����prId cuId pcpId ��ѯ����
	List<OperationPriceVo> selectOperationPrice(String prId, String cuId, String pcpId);
	
	OperationPrice selectByPrimaryKey(String opId);
	List<OperationPriceVo> selectAllByOpid(String opId);
	
	//���ݹ��̺�¥���Ż�ȡԤ������Ϣ
	List<OperationPriceVo> getYKL(Map<String,Object> map);
	
	//���ݹ��̡�¥���ŵȻ�ȡԤ������Ŀ
	int getYKLCount(Map<String,Object> map);
	//�޸�Ԥ����
	int editBudgetSum(Map<String,Object>map);

	//����opId��ȡOperetionPriceVo
	OperationPriceVo getOperationPriceVoByKey(String opId);
	
	//����Ԥ�����޸ļ�¼
	int addYKLmendRecord(YklHistory yh);
	
	//���ݹ��̡�¥��������㵥λ����ȡ���е�Ԥ�����޸ļ�¼
	List<YklHistory> getYklHistory(Map<String,Object> map);
	
	//��ȡԤ�����޸ļ�¼��Ŀ��
	int getYKLHistoryCount(Map<String,Object> map);
	
	//���ݹ��̡�¥�����Լ��ֲ�����ȡ���е�BudgetVo
	List<BudgetVo> getBudgetVo(Map<String,String> map);
	//����budgetVo��ȡ���е�operationPrice
	List<OperationPrice> getOperationPriceByBudget(BudgetVo bv);
	//����OperationPrice ��ȡ�Ƽ��ѿ���
	Double getJjyk(OperationPrice op);
	//����OperationPrice ��ȡ�Ƽ�������
	Double getJjys(OperationPrice op);
	
	// ����O_ID��ѯ����
	List<OperationPrice> selectPriceByOid(String oId);
	//��ȡÿһ������3���ѿ�������
	Double getYkgcl(OperationPriceVo op);
	
	// ����oId ��ѯ��λ��Ϣ--�½�������ʱʹ��
	//List<OperationPriceVo> queryUnitInfoByOid(String oId);
	
	// ����oId ��ѯ��λ��Ϣ--�½�������ʱʹ��
	//List<OperationPriceVo> selectUnitPriceByOid(String oId);
	
	List<OperationPriceVo> queryOperationPriceUnit(String prId, String pcpId, String pId, String psId, String oId, String cuId);
	
	// �½�������--��ѯ�ѽ��ۼƹ�����--�鵥��
	List<OperationPriceVo> queryUnitBySeven(String prId, String pcpId, String pId, String psId, String oId, String unId, String cuId);

}

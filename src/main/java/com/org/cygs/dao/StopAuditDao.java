package com.org.cygs.dao;

import com.org.cygs.pojo.StopAudit;
public interface StopAuditDao {
	
	
	//���ʱ����ɾ���ݲ�����������
    public void deleteStopAudit(String mId,String uId);
    
    //����midɾ��
    public void deleteStopAuditByMid(String mId);
    
  //���ѡ���ݲ�����   ��Ӽ�¼�� �ݲ��������
    public void addStopAudit(StopAudit stopAudit);

}

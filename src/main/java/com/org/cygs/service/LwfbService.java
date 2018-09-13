package com.org.cygs.service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Lwfb;
import com.org.cygs.pojo.Lwfbjs;
import com.org.cygs.pojo.Lwgcldb;
import com.org.cygs.pojo.Lwgzzf;
import com.org.cygs.pojo.PieceworkServiceTotal;
import com.org.cygs.pojo.MissionVo;


public interface LwfbService {

	//�����¶ȱ���
	public List<Lwfb> getLWFB(Map<String,Object> map);
	//������֧����ϸ
	public List<Lwgzzf> getLwgzzf(Map<String,Object> map);
	//������֧������
	public List<Lwgzzf> getLwgzzfhz(Map<String,Object>map);

	//����Ƽ��ۼ�
    public List<PieceworkServiceTotal> getPieceworkServiceTotal(Map<String, Object> map);

	//��Ŀ�¶ȱ���
	public List<Lwfbjs> getLwfbjs(Map<String,Object> map);
	//��Ŀ�¶ȱ����У�������������ϸ��Ϣ��ȡ
	public List<MissionVo> getAllMissionNew(Map<String,Object> map);
	
	//��ȡ���񹤳����Ա���Ϣ
	public List<Lwgcldb> getLwgcldb(Map<String,Object> map);

}

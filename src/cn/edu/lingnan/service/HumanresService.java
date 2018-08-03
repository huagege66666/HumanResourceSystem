package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Humanres;

public interface HumanresService {

	/**
	 * ����ID���Ҷ�Ӧ���˲���Ϣ
	 * @param id
	 * @return
	 */
	Humanres findHumanresByID(long id);
	
	/**
	 * ��ҳ��ѯ�����˲���Ϣ
	 * @param pageSize
	 * @param pageNo
	 * @param humanres
	 * @return
	 */
	List<Humanres> queryHumanresByPage(int pageSize,int pageNo);
	
	long getAllCount(Humanres example);
}

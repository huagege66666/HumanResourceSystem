package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.pojo.Humanres;

public interface HumanresService {

	/**
	 * 根据ID查找对应的人才信息
	 * @param id
	 * @return
	 */
	Humanres findHumanresByID(long id);
	
	/**
	 * 分页查询所有人才信息
	 * @param pageSize
	 * @param pageNo
	 * @param humanres
	 * @return
	 */
	List<Humanres> queryHumanresByPage(int pageSize,int pageNo);
	
	long getAllCount(Humanres example);
}

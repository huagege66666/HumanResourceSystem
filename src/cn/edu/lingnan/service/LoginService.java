package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Account;
import cn.edu.lingnan.pojo.Emp;

public interface LoginService {

	/**
	 * 登录，并返回对应的员工信息
	 * @param account
	 * @return
	 */
	Emp login(Account account);
	
	/**
	 * 修改用户密码
	 * @param empid
	 * @param password
	 */
	boolean alterPassword(String alterpass,Account account);
	
	/**
	 * 获得用户权限
	 * @param id
	 * @return
	 */
	boolean getAuthorityById(long id);
	
}

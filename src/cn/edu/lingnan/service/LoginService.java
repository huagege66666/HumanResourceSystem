package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Account;
import cn.edu.lingnan.pojo.Emp;

public interface LoginService {

	/**
	 * ��¼�������ض�Ӧ��Ա����Ϣ
	 * @param account
	 * @return
	 */
	Emp login(Account account);
	
	/**
	 * �޸��û�����
	 * @param empid
	 * @param password
	 */
	boolean alterPassword(String alterpass,Account account);
	
	/**
	 * ����û�Ȩ��
	 * @param id
	 * @return
	 */
	boolean getAuthorityById(long id);
	
}

package cn.edu.lingnan.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.lingnan.dao.AccountDao;
import cn.edu.lingnan.dao.EmpDao;
import cn.edu.lingnan.pojo.Account;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private EmpDao empDao;

	@Override//µÇÂ¼
	public Emp login(Account account) {
		List<Account> list = (List<Account>) accountDao.getListByHQL("from Account where empid= " +  account.getEmpid() + " and " +
				" password = '" +  account.getPassword()+"'");
		//System.out.println(list.size());
		if (list != null && list.size()>0) {
			return empDao.findById(account.getEmpid());
		}
		return null;
	}

	@Override//ÐÞ¸ÄÃÜÂë
	public boolean alterPassword(String alterpass,Account account) {
		Account quoaccount=accountDao.findById(account.getEmpid());
		if(!quoaccount.getPassword().equals(account.getPassword())){
			return false;
		}
		quoaccount.setPassword(alterpass);
		accountDao.merge(quoaccount);	
		return true;
	}

	@Override
	public boolean getAuthorityById(long id) {
		Account acc=accountDao.findById(id);
		return acc.getPriv();
	}

}

package cn.edu.lingnan.action;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Account;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.service.LoginService;

public class LoginAction extends BaseAction {
	
	@Autowired
	private LoginService loginService; //登录
	
	private Account registeAccount; //注册
	
	private Account account;   //接收账号信息
	private String alterpass;    //修改后密码
	private String confpass;   //确认密码
	
	private Emp example;
	
	/**
	 * 登录
	 * author 梁成彰
	 * @return
	 */
	
	public String login(){
		Emp loginAccount=loginService.login(account);
		if (loginAccount != null) {
			this.session.put("sessionemp", loginAccount);
			if(loginService.getAuthorityById(account.getEmpid())){
				this.session.put("isadmin", true);
				return "adminuser";
			}else{
				example=loginAccount;
				this.session.put("isadmin", false);
				return "user";
			}
		}else{
			super.addActionMessage("用户ID或密码不正确");
			return ERROR;
		}
	}
	
	/**
	 * 登出（注销）
	 * 清空session
	 *  author 梁成彰
	 * @return
	 */
	public String logout(){
		this.session.clear();
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * author 赵桂钊
	 * @return
	 */
	public String alterPasswordSubmit(){
		if(loginService.alterPassword(alterpass,account)){
			super.addActionMessage("修改成功");
			return SUCCESS;
		}
		else{
			//返回提示，原密码错误
			super.addActionMessage("原密码错误，请重新输入");
			return ERROR;
		}
	}
	
	public void validateAlterPasswordSubmit(){
		if(confpass.trim().equals("")||!alterpass.equals(confpass)){
			super.addActionMessage( "两次输入密码不一致，请重新输入");
			super.addFieldError("", "");
		}
	}
	
	public Account getRegisteAccount() {
		return registeAccount;
	}
	public void setRegisteAccount(Account registeAccount) {
		this.registeAccount = registeAccount;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	public String getAlterpass() {
		return alterpass;
	}

	public void setAlterpass(String alterpass) {
		this.alterpass = alterpass;
	}

	public String getConfpass() {
		return confpass;
	}

	public void setConfpass(String confpass) {
		this.confpass = confpass;
	}

	public Emp getExample() {
		return example;
	}

	public void setExample(Emp example) {
		this.example = example;
	}

	
}

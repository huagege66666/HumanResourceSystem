package cn.edu.lingnan.action;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.lingnan.pojo.Account;
import cn.edu.lingnan.pojo.Emp;
import cn.edu.lingnan.service.LoginService;

public class LoginAction extends BaseAction {
	
	@Autowired
	private LoginService loginService; //��¼
	
	private Account registeAccount; //ע��
	
	private Account account;   //�����˺���Ϣ
	private String alterpass;    //�޸ĺ�����
	private String confpass;   //ȷ������
	
	private Emp example;
	
	/**
	 * ��¼
	 * author ������
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
			super.addActionMessage("�û�ID�����벻��ȷ");
			return ERROR;
		}
	}
	
	/**
	 * �ǳ���ע����
	 * ���session
	 *  author ������
	 * @return
	 */
	public String logout(){
		this.session.clear();
		return SUCCESS;
	}
	
	/**
	 * �޸�����
	 * author �Թ���
	 * @return
	 */
	public String alterPasswordSubmit(){
		if(loginService.alterPassword(alterpass,account)){
			super.addActionMessage("�޸ĳɹ�");
			return SUCCESS;
		}
		else{
			//������ʾ��ԭ�������
			super.addActionMessage("ԭ�����������������");
			return ERROR;
		}
	}
	
	public void validateAlterPasswordSubmit(){
		if(confpass.trim().equals("")||!alterpass.equals(confpass)){
			super.addActionMessage( "�����������벻һ�£�����������");
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

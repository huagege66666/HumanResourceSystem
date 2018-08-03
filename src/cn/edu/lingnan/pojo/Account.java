package cn.edu.lingnan.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCOUNT", schema = "SSH")
public class Account implements java.io.Serializable {

	// Fields

	private Long empid;
	private Emp emp;
	private String password;
	private Boolean priv;

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(Emp emp, String password) {
		this.emp = emp;
		this.password = password;
	}

	/** full constructor */
	public Account(Emp emp, String password, Boolean priv) {
		this.emp = emp;
		this.password = password;
		this.priv = priv;
	}

	// Property accessors
	@Id
	@Column(name = "EMPID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getEmpid() {
		return this.empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPID", unique = true, nullable = false, insertable = false, updatable = false)
	public Emp getEmp() {
		return this.emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	@Column(name = "PASSWORD", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PRIV", precision = 1, scale = 0)
	public Boolean getPriv() {
		return this.priv;
	}

	public void setPriv(Boolean priv) {
		this.priv = priv;
	}

}
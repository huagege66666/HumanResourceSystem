package cn.edu.lingnan.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Leavejob entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LEAVEJOB", schema = "SSH")
public class Leavejob implements java.io.Serializable {

	// Fields

	private Long empid;
	private Emp emp;
	private Date leavedate;
	private String type;
	private String reason;
	private String direction;

	// Constructors

	/** default constructor */
	public Leavejob() {
	}

	/** minimal constructor */
	public Leavejob(Emp emp, Date leavedate) {
		this.emp = emp;
		this.leavedate = leavedate;
	}

	/** full constructor */
	public Leavejob(Emp emp, Date leavedate, String type, String reason,
			String direction) {
		this.emp = emp;
		this.leavedate = leavedate;
		this.type = type;
		this.reason = reason;
		this.direction = direction;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "LEAVEDATE", nullable = false, length = 7)
	public Date getLeavedate() {
		return this.leavedate;
	}

	public void setLeavedate(Date leavedate) {
		this.leavedate = leavedate;
	}

	@Column(name = "TYPE", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "REASON", length = 50)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "DIRECTION", length = 50)
	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
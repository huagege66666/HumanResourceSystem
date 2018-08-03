package cn.edu.lingnan.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Deptadjust entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEPTADJUST", schema = "SSH")
public class Deptadjust implements java.io.Serializable {

	// Fields

	private Integer adjustid;
	private Emp emp;
	private Dept dept;
	private Boolean type;
	private String reason;
	private String descr;
	private Date adjustdate;

	// Constructors

	/** default constructor */
	public Deptadjust() {
	}

	/** minimal constructor */
	public Deptadjust(Emp emp, Dept dept, Boolean type, Date adjustdate) {
		this.emp = emp;
		this.dept = dept;
		this.type = type;
		this.adjustdate = adjustdate;
	}

	/** full constructor */
	public Deptadjust(Emp emp, Dept dept, Boolean type, String reason,
			String descr, Date adjustdate) {
		this.emp = emp;
		this.dept = dept;
		this.type = type;
		this.reason = reason;
		this.descr = descr;
		this.adjustdate = adjustdate;
	}

	// Property accessors
	@SequenceGenerator(name = "generator_adjust",allocationSize=1,sequenceName="seq_adjust")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator_adjust")
	@Column(name = "ADJUSTID", unique = true, nullable = false, precision = 6, scale = 0)
	public Integer getAdjustid() {
		return this.adjustid;
	}

	public void setAdjustid(Integer adjustid) {
		this.adjustid = adjustid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPID", nullable = false)
	public Emp getEmp() {
		return this.emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPTID", nullable = false)
	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Column(name = "TYPE", nullable = false, precision = 1, scale = 0)
	public Boolean getType() {
		return this.type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	@Column(name = "REASON", length = 50)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "DESCR", length = 50)
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ADJUSTDATE", nullable = false, length = 7)
	public Date getAdjustdate() {
		return this.adjustdate;
	}

	public void setAdjustdate(Date adjustdate) {
		this.adjustdate = adjustdate;
	}

}
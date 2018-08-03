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
 * Jobadjust entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JOBADJUST", schema = "SSH")
public class Jobadjust implements java.io.Serializable {

	// Fields

	private Integer adjustid;
	private Emp emp;
	private Long jobid;
	private String type;
	private String descr;
	private Date adjustdate;

	// Constructors

	/** default constructor */
	public Jobadjust() {
	}

	/** minimal constructor */
	public Jobadjust(Emp emp, Long jobid, String type, Date adjustdate) {
		this.emp = emp;
		this.jobid = jobid;
		this.type = type;
		this.adjustdate = adjustdate;
	}

	/** full constructor */
	public Jobadjust(Emp emp, Long jobid, String type, String descr,
			Date adjustdate) {
		this.emp = emp;
		this.jobid = jobid;
		this.type = type;
		this.descr = descr;
		this.adjustdate = adjustdate;
	}

	// Property accessors
	@SequenceGenerator(name = "generator_jadjust",allocationSize=1,sequenceName="seq_adjust")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator_jadjust")
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

	@Column(name = "JOBID", nullable = false, precision = 10, scale = 0)
	public Long getJobid() {
		return this.jobid;
	}

	public void setJobid(Long jobid) {
		this.jobid = jobid;
	}

	@Column(name = "TYPE", nullable = false, length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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
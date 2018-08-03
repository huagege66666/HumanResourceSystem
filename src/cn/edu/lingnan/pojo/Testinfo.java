package cn.edu.lingnan.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Testinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TESTINFO", schema = "SSH")
public class Testinfo implements java.io.Serializable {

	// Fields

	private Long testid;
	private Emp emp;
	private Date startdate;
	private Date enddate;
	private Set<Testmanager> testmanagers = new HashSet<Testmanager>(0);

	// Constructors

	/** default constructor */
	public Testinfo() {
	}

	/** minimal constructor */
	public Testinfo(Date startdate, Date enddate) {
		this.startdate = startdate;
		this.enddate = enddate;
	}

	/** full constructor */
	public Testinfo(Emp emp, Date startdate, Date enddate,
			Set<Testmanager> testmanagers) {
		this.emp = emp;
		this.startdate = startdate;
		this.enddate = enddate;
		this.testmanagers = testmanagers;
	}

	// Property accessors
	@SequenceGenerator(name = "generator_infoid",allocationSize=1,sequenceName="seq_infoid")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator_infoid")
	@Column(name = "TESTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getTestid() {
		return this.testid;
	}

	public void setTestid(Long testid) {
		this.testid = testid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPID")
	public Emp getEmp() {
		return this.emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDATE", nullable = false, length = 7)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", nullable = false, length = 7)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "testinfo")
	public Set<Testmanager> getTestmanagers() {
		return this.testmanagers;
	}

	public void setTestmanagers(Set<Testmanager> testmanagers) {
		this.testmanagers = testmanagers;
	}

}
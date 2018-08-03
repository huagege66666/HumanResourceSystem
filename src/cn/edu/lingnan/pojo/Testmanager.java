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
 * Testmanager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TESTMANAGER", schema = "SSH")
public class Testmanager implements java.io.Serializable {

	// Fields

	private Long manid;
	private Testinfo testinfo;
	private String judge;
	private Integer result;
	private Date mandate;

	// Constructors

	/** default constructor */
	public Testmanager() {
	}

	/** full constructor */
	public Testmanager(Testinfo testinfo, String judge, Integer result,
			Date mandate) {
		this.testinfo = testinfo;
		this.judge = judge;
		this.result = result;
		this.mandate = mandate;
	}

	// Property accessors
	@SequenceGenerator(name = "generator_manid",allocationSize=1,sequenceName="seq_manaid")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator_manid")
	@Column(name = "MANID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getManid() {
		return this.manid;
	}

	public void setManid(Long manid) {
		this.manid = manid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TESTID")
	public Testinfo getTestinfo() {
		return this.testinfo;
	}

	public void setTestinfo(Testinfo testinfo) {
		this.testinfo = testinfo;
	}

	@Column(name = "JUDGE", length = 50)
	public String getJudge() {
		return this.judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	@Column(name = "RESULT", precision = 1, scale = 0)
	public Integer getResult() {
		return this.result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MANDATE", length = 7)
	public Date getMandate() {
		return this.mandate;
	}

	public void setMandate(Date mandate) {
		this.mandate = mandate;
	}

}
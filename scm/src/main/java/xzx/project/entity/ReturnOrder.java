package xzx.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReturnOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4668868239527252203L;

	private String roId;

    private Integer supId;

    private String shId;

    private Date roDate;

    private BigDecimal roPayable;

    private BigDecimal roPaid;

    private String roRemark;

    private String roAttn;

    private Integer roOperator;
    
    

    @Override
	public String toString() {
		return "ReturnOrder [roId=" + roId + ", supId=" + supId + ", shId=" + shId + ", roDate=" + roDate
				+ ", roPayable=" + roPayable + ", roPaid=" + roPaid + ", roRemark=" + roRemark + ", roAttn=" + roAttn
				+ ", roOperator=" + roOperator + "]";
	}

	public String getRoId() {
        return roId;
    }

    public void setRoId(String roId) {
        this.roId = roId;
    }

    public Integer getSupId() {
        return supId;
    }

    public void setSupId(Integer supId) {
        this.supId = supId;
    }

    public String getShId() {
        return shId;
    }

    public void setShId(String shId) {
        this.shId = shId;
    }

    public Date getRoDate() {
        return roDate;
    }

    public void setRoDate(Date roDate) {
        this.roDate = roDate;
    }

    public BigDecimal getRoPayable() {
        return roPayable;
    }

    public void setRoPayable(BigDecimal roPayable) {
        this.roPayable = roPayable;
    }

    public BigDecimal getRoPaid() {
        return roPaid;
    }

    public void setRoPaid(BigDecimal roPaid) {
        this.roPaid = roPaid;
    }

    public String getRoRemark() {
        return roRemark;
    }

    public void setRoRemark(String roRemark) {
        this.roRemark = roRemark;
    }

    public String getRoAttn() {
        return roAttn;
    }

    public void setRoAttn(String roAttn) {
        this.roAttn = roAttn;
    }

    public Integer getRoOperator() {
        return roOperator;
    }

    public void setRoOperator(Integer roOperator) {
        this.roOperator = roOperator;
    }
}
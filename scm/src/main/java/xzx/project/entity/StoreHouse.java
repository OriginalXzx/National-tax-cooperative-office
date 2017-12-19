package xzx.project.entity;

import java.io.Serializable;

public class StoreHouse implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1132840223910933970L;

	private String shId;  //仓库编号

    private String shName; //仓库名称

    private String shResponsible; //责任人

    private String shPhone; //联系电话

    private String shAddress; //联系地址

    private String shType; //仓库类型

    private String shRemark; //备注

    public String getShId() {
        return shId;
    }

    public void setShId(String shId) {
        this.shId = shId;
    }

    public String getShName() {
        return shName;
    }

    public void setShName(String shName) {
        this.shName = shName;
    }

    public String getShResponsible() {
        return shResponsible;
    }

    public void setShResponsible(String shResponsible) {
        this.shResponsible = shResponsible;
    }

    public String getShPhone() {
        return shPhone;
    }

    public void setShPhone(String shPhone) {
        this.shPhone = shPhone;
    }

    public String getShAddress() {
        return shAddress;
    }

    public void setShAddress(String shAddress) {
        this.shAddress = shAddress;
    }

    public String getShType() {
        return shType;
    }

    public void setShType(String shType) {
        this.shType = shType;
    }

    public String getShRemark() {
        return shRemark;
    }

    public void setShRemark(String shRemark) {
        this.shRemark = shRemark;
    }

	@Override
	public String toString() {
		return "StoreHouse [shId=" + shId + ", shName=" + shName + ", shResponsible=" + shResponsible + ", shPhone="
				+ shPhone + ", shAddress=" + shAddress + ", shType=" + shType + ", shRemark=" + shRemark + "]";
	}
    
}
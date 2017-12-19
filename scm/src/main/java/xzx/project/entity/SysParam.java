package xzx.project.entity;

import java.io.Serializable;

public class SysParam implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6443280719130700413L;

	private Long sysParamId;

    private String sysParamField;

    private String sysParamValue;

    private String sysParamText;
    
    private String sysParamType;
    

    public String getSysParamType() {
		return sysParamType;
	}

	public void setSysParamType(String sysParamType) {
		this.sysParamType = sysParamType;
	}

	public Long getSysParamId() {
        return sysParamId;
    }

    public void setSysParamId(Long sysParamId) {
        this.sysParamId = sysParamId;
    }

    public String getSysParamField() {
        return sysParamField;
    }

    public void setSysParamField(String sysParamField) {
        this.sysParamField = sysParamField;
    }

    public String getSysParamValue() {
        return sysParamValue;
    }

    public void setSysParamValue(String sysParamValue) {
        this.sysParamValue = sysParamValue;
    }

    public String getSysParamText() {
        return sysParamText;
    }

    public void setSysParamText(String sysParamText) {
        this.sysParamText = sysParamText;
    }

	@Override
	public String toString() {
		return "SysParam [sysParamId=" + sysParamId + ", sysParamField=" + sysParamField + ", sysParamValue="
				+ sysParamValue + ", sysParamText=" + sysParamText + ", sysParamType=" + sysParamType + "]";
	}

	
    
}
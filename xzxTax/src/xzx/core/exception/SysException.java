package xzx.core.exception;
/**
 * 系统异常设计,总异常
 * @author xzx
 *
 */
public abstract class SysException extends Exception {
    private String errorMsg;

    public SysException() {
		
	}
    
    public SysException(String errorMsg, Throwable cause) {
		super(errorMsg, cause);
		this.errorMsg = errorMsg;
	}
    
	public SysException(Throwable cause) {
		super(cause);
	}

	public SysException(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
       
}

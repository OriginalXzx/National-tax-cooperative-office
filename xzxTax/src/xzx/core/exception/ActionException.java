package xzx.core.exception;

public class ActionException extends SysException {

	public ActionException() {
	     super("Action层异常，请求操作失败！！");
	}


	public ActionException(String errorMsg) {
		super(errorMsg);
	}

}

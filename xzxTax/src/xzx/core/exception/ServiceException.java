package xzx.core.exception;
/**
 * service层异常设计
 * @author xzx
 *
 */
public class ServiceException extends SysException {

	public ServiceException() {
	    super("service层异常，业务操作失败！");
	}

	public ServiceException(String errorMsg) {
		super(errorMsg);
	}

}

package xzx.nsfw.info.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import xzx.core.action.BaseAction;
import xzx.core.page.PageResult;
import xzx.core.util.QueryHelper;
import xzx.nsfw.info.entity.Info;
import xzx.nsfw.info.service.InfoService;

public class InfoAction extends BaseAction {
	@Resource
	private InfoService infoService;
	private List<Info> infoList;
	private Info info;

	// 列表页面
	public String listUI() throws Exception {
		// 创建一个查询助手对象
		QueryHelper qh = new QueryHelper(Info.class, "i");
		if (info != null) {
			if (StringUtils.isNotBlank(info.getTitle())) {
				info.setTitle(URLDecoder.decode(info.getTitle(), "utf-8"));
				qh.addCondition("i.title like ?", "%" + info.getTitle() + "%");//控制查询条件回显，与中文乱码问题
			}
			// 根据创建时间降序排序
		}
		qh.addOrderByProperty("i.createTime", QueryHelper.ORDER_BY_DESC);
		pageResult = infoService.getPageResult(qh,getPageNo(),getPageSize());
		// 加载信息分类Map
		ActionContext.getContext().getContextMap().put("infoTypeMap", info.INFO_TYPE_MAP);
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		// 加载功能分类集合
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (info != null) {

				infoService.save(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		// 加载功能分类集合
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		if (info != null && info.getInfoId() != null) {
			// System.out.println("info不为空哦");
			info = infoService.findById(info.getInfoId());

		}
		return "editUI";
	}

	// 保存编辑
	public String edit() {

		infoService.update(info);
		return "list";
	}

	// 删除
	public String delete() {
		if (info != null && info.getInfoId() != null) {
			infoService.delete(info.getInfoId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				infoService.delete(id);
			}
		}
		return "list";
	}

	// 异步发布信息
	public void publicInfo() {
		try {
			if (info != null) {
				// 1、更新信息状态
				Info tem = infoService.findById(info.getInfoId());
				tem.setState(info.getState());
				infoService.update(tem);
				// int i = 0/0;

				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("更新状态成功".getBytes("utf-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Info> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

}

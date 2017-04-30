package xzx.nsfw.home.action;

import xzx.core.action.BaseAction;

public class HomeAction extends BaseAction {
     //跳转到纳税访问首页
	public String frame(){
		return "frame";
	}
	//跳转到纳税访问首页-顶部
	public String top(){
		return "top";
	}
	//跳转到纳税访问首页-左边菜单
	public String left(){
		return "left";
	}
}

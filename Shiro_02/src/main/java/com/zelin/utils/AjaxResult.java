/**
 * 
 */
package com.zelin.utils;

/********************************
 * 公司:  深圳市泽林信息公司			<br>
 * 作者:  王峰						<br>
 * 类名:  AjaxResult			<br>
 * 日期:  2018年9月12日 下午2:49:54			<br>
 * 功能:  						
 ********************************/
public class AjaxResult {

	private String message;
	private boolean status;
	
	public AjaxResult() {
		super();
	}
	public AjaxResult(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AjaxResult [message=" + message + ", status=" + status + "]";
	}
	
}

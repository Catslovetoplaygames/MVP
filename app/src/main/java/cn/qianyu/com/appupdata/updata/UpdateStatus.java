package cn.qianyu.com.appupdata.updata;

/**
 * 
 * @author wenjie
 *	检测版本的状态类
 */
public interface UpdateStatus {
	/**
	 * 没有新版本
	 */
	int NO = 1;
	
	/**
	 * 有新版本
	 */
	int YES = 2;
	
	/**
	 * 链接超时
	 */
	int TIMEOUT = 3;
	
	/**
	 * 没有wifi
	 */
	int NOWIFI = 4;
	
	/**
	 * 数据解析出错
	 */
	int ERROR = -1;
}

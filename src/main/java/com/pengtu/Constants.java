package com.pengtu;
/**
 * Constant values used throughout the application.
 * 
 * <p>
 * <a href="Constants.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author
 */
public class Constants {
	public static final String NO_MATCH_PATH=".*/(login).*";
	/** double型运算时的误差范围为0.01 */
	public static final double ERRORRANGE = 0.01;// double型运算时的误差范围为0.01
	public static final String BUNDLE_KEY = "messages";
	public static final String ENC_ALGORITHM = "SHA";
	public static final String ENCRYPT_PASSWORD = "encryptPassword";
	public static final String FILE_SEP = System.getProperty("file.separator");
	public static final String USER_HOME = System.getProperty("user.home")+ FILE_SEP;
	public static final String TEMPPATH=System.getProperty("java.io.tmpdir");
	public static final String CONFIG = "appConfig";
	public static final String APP_NAME = "szyh";
	public static final String EXT_JSON_SUCCESS_STR = "success";
	//接口返回错误信息标识
	public static final String EXT_JSON_MSG = "msg";
	public static final String EXT_JSON_RESULTS_STR = "results";
	public static final String ACTION = "1";
	public static final String UNACTION = "0";
	/** 当前记录未删除 */
	public static final Integer DELFLAG_IN = 0;
	/** 当前记录删除 */
	public static final Integer DELFLAG_UN = 1;
	/** 乐观锁，新增时默认1 */
	public static final Integer REVISION = 1;
	/** 每页分页大小 默认是每页10行 */
	public static final Integer PAGE_SIZE = 10;
	public static final String ERR_INVALID_SESSION = "ERR_INVALID_SESSION";
	public static final String ERR_CHECK_LOGIN_FAILED = "ERR_CHECK_LOGIN_FAILED";
    //默认图片缩略图的比例
    public static final float DEFAULT_SCALE = 0.8f;
	//SpringSecurity中默认的角色/授权名前缀.
	public static final String AUTHORITY_PREFIX = "ROLE_";	
	// SpringSecurity中默认的角色/授权名前缀(默认角色).
	public static final String AUTHORITY_PREFIXYH = "ROLE_MB";
	
	/**
	 * The name of the CSS Theme setting.
	 */
	public static final String CSS_THEME = "csstheme";
	public static final String UPLOAD_PATH = "upload" + FILE_SEP;
	public static final String UPLOAD_ROOT_PATH = "upload" + FILE_SEP;
	public static final String UPLOAD_MSG_FILE_PATH = "通知文档" + FILE_SEP;
	public static final String TEMP_PATH = "temp" + FILE_SEP;
	public static final String CANNOT_FOUND_IMAGE_PATH = "resources" + FILE_SEP + "cannotfoundimage.gif";
	public static final String NOT_UPLOAD_IMAGE_PATH = "resources" + "/" + "notupload.gif";
	public static final String CANNOT_FOUND_FILE_PATH = "resources" + FILE_SEP + "cannotfound.gif";
	public static final String NO_IMAGE_PATH = "resources" + FILE_SEP + "nopic.gif";
	
	/**
	 *  @是否  
	 *  1：是
	 *  0：否
	 */
	public final static String IS_OR_NOT_1 = "1";
	public final static String IS_OR_NOT_0 = "0";
	
	/**
	 * @单位类型 
	 */
	public static final String ORG_GL_TYPE = "gl";// 管养单位
	public static final String ORG_SG_TYPE = "yh";// 施工单位
	public static final String ORG_JL_TYPE = "jl";// 监理单位
	
	/**
	 * @角色类型 
	 */
	//角色列表 CATEGORY 1000
	public static final String HASSUPADMIN_ROLE = "research";// 超级管理员 administrator

	

	
	/**
	 * @用户登录账号和密码过期，锁定,未启用
	 */
	public static final String ERR_USER_NOTENABLED= "ERR_USER_NOTENABLED";  //账号未启用
	public static final String ERR_USER_TIMEOUT= "ERR_USER_TIMEOUT";  //账号或密码过期
	public static final String ERR_USER_LOCK= "ERR_USER_LOCK";        //用户锁定
	

	
	/**
	 *@事件处置状态
	 * 0 未处理
	 * 1处理中
	 * 2已处理
	 */
	public static final String EVENT_DEAL_WCL = "0";
	public static final String EVENT_DEAL_CLZ = "1";
	public static final String EVENT_DEAL_YCL = "2";
	
	
	
	/**
	 * @生成的doc、pdf等文件的tableField
	 */
	public static final String APPLY_DOC = "apply-doc";
	public static final String APPLY_PDF = "apply-pdf";
	public static final String APPLY_PDF_DOC = "apply-pdf-doc";
	public static final String APPLY_CHECK_DOC = "apply-check-doc";
	public static final String APPLY_CHECK_ORIGINREPORT = "apply-check-originReport";
	public static final String APPLY_CHECK_COMPAREREPORT = "apply-check-comPareReport";
	public static final String APPLY_RECORD_DOC = "apply-record-doc";
	public static final String APPLY_CONTRACT_DOC = "apply-contract-doc";

	/**
	 * 综合管理常量类
	 * 
	 * @author gzp
	 * @date 2021年4月13日 上午9:32:24
	 *
	 */
	public class SynthesisConstants {
		/**
		 * 否
		 */
		public static final String NO = "1";
		/**
		 * 是
		 */
		public static final String YES = "0";
		/**
		 * 外部科研机构
		 */
		public static final String EXTERIOR = "1";
		/**
		 * 内部科研机构
		 */
		public static final String INTERIOR = "0";
		public static final String EXTERIOR_ORG = "外部单位";
	}
	/**
	 *@事件处置状态
	 * 0 未处理
	 * 1处理中
	 * 2已处理
	 */
}

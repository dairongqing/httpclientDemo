
package com.pengtu.controller.bus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pengtu.controller.base.DefaultBaseController;
import com.pengtu.service.gps.VehMonitorService;

/**
 * 深圳市交通运输行业GPS监管平台 车辆管理数据
 * @description VehMonitorController
 * @author dairongqing
 * @since 2021-07-29 09:57:57
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/gps/vehmonitor")
public class BusDataController extends DefaultBaseController{
	@Autowired
	private VehMonitorService vehMonitorService;

	/**
	 * 获取病害数据
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject login() throws Exception{
		System.out.println("hello world");
		return vehMonitorService.login();
	}

	/**
	 * 获取日常巡逻用户数据
	 */
	@RequestMapping(value = "newVehAll", method = RequestMethod.GET)
	@ResponseBody
	public String newVehAll()throws Exception{
		return vehMonitorService.newVehAll();
	}
	@RequestMapping(value = "newVehAllList", method = RequestMethod.GET)
	@ResponseBody
	public List<String> newVehAlLlist()throws Exception{
		 
		return vehMonitorService.newVehAllList();
	}
	/**
	 * 获取所有用户数据
	 */
	@RequestMapping(value = "vehTrack", method = RequestMethod.GET)
	@ResponseBody
	public String vehTrack() throws Exception{
		return vehMonitorService.vehTrack();
	}
	
	@RequestMapping(value = "createJsonFile", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> vehTrack(String curPage,String transtypeCode,String starttime,String endtime) throws Exception{
		return vehMonitorService.createJsonFile(curPage,transtypeCode,starttime,endtime);
	}
	
}

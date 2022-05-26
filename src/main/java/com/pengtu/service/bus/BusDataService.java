
package com.pengtu.service.bus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pengtu.controller.base.DefaultBaseController;
import com.pengtu.utils.ConfigUtils;
import com.pengtu.utils.DateUtils;
import com.pengtu.utils.FileUtils;
import com.pengtu.utils.httpclientutil.HttpClientUtil;
import com.pengtu.utils.web.SpringMvcUtils;

/**
 * 深圳市交通运输行业GPS监管平台 车辆管理数据
 * @description VehMonitorService
 * @author dairongqing
 * @since 2021-07-29 09:57:57
 * @version v1.0
 *
 */
@Service
public class BusDataService extends DefaultBaseController{
	/**
	 * 保持登陆有效
	 * @return
	 * @throws Exception
	 */
	public JSONObject login() throws Exception{
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Cookie", ConfigUtils.getConfig("Cookie"));
		Map<String,String> bodyMap = new HashMap<>();
		bodyMap.put("loginName", ConfigUtils.getConfig("username"));
		String result = HttpClientUtil.sendSSLPost(ConfigUtils.getConfig("loginurl"), headerMap, bodyMap);
		return JSONObject.parseObject(result);
	}
	
	/**
	 * 查询区域车辆
	 * @return
	 * @param vehicleno 车牌号
	 * @param transtypeCode "011"班车客运  012" 包车客运 "020" 货运 "030" 危险品 "050:驾培 "080"公交 090出租 "900" 其他
	 * @param isonline
	 * @throws Exception
	 */
	public String newVehAll() throws Exception{
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Cookie", ConfigUtils.getConfig("Cookie"));
		headerMap.put("Host", ConfigUtils.getConfig("host"));
		headerMap.put("Content-Type",ConfigUtils.getConfig("Content_Type"));
		Map<String,String> bodyMap = new HashMap<>();
		String vehicleno = SpringMvcUtils.getParameterV2("vehicleno");
		if(StringUtils.isNotBlank(vehicleno)) {
			bodyMap.put("queryValue[vehicleno]", vehicleno);
		}else {
			bodyMap.put("queryValue[vehicleno]", "");
		}
		String vehiclebelong = SpringMvcUtils.getParameterV2("vehiclebelong");
		if(StringUtils.isNotBlank(vehiclebelong)) {
			bodyMap.put("queryValue[vehiclebelong]", vehiclebelong);
		}else {
			bodyMap.put("queryValue[vehiclebelong]", "1");//车辆归属 1本省 0外省
		}
		bodyMap.put("queryValue[runStatus]", "");
		bodyMap.put("queryValue[monitorgroup]", "");//所属组
		bodyMap.put("queryValue[linetypeid]", "");//班线类型 1234 四类 全部则不传 
		bodyMap.put("queryValue[corpName]", "");//所属企业
		bodyMap.put("queryValue[nativeAreaCode]", "440306");//籍贯地
		bodyMap.put("queryValue[currentAreaCode]", "440306");//当前地
		bodyMap.put("queryValue[accessName]", "");//所属平台
//		"011"班车客运  012" 包车客运 "020" 货运 "030" 危险品 "050:驾培 "080"公交 090出租 "900" 其他
		String transtypeCode = SpringMvcUtils.getParameterV2("transtypeCode");
		if(StringUtils.isNotBlank(transtypeCode)) {
			bodyMap.put("queryValue[transtypeCode]", transtypeCode);//所属行业
		}else {
			bodyMap.put("queryValue[transtypeCode]", "011, 012,020,030,050,080,090,900");//所属行业
		}
		String isonline = SpringMvcUtils.getParameterV2("isonline");
		if(StringUtils.isNotBlank(isonline)) {
			bodyMap.put("queryValue[isonline]", isonline);//是否在线 1在线 0离线
		}else {
			bodyMap.put("queryValue[isonline]", "1");//是否在线 1在线 0离线
		}
		bodyMap.put("queryValue[managestatus]", "");//是否匹配 0 匹配 1不匹配
		bodyMap.put("queryType", "advVehQuery");
		String curPage = SpringMvcUtils.getParameterV2("curPage");
		if(StringUtils.isNotBlank(curPage)) {
			bodyMap.put("curPage", curPage);
		}else {
			bodyMap.put("curPage", "1");
		}
		String result = HttpClientUtil.sendSSLPost(ConfigUtils.getConfig("newVehAllurl"), headerMap, bodyMap);
		return result;
	}
	public List<String> newVehAllList() throws Exception{
		String vehicleno = SpringMvcUtils.getParameterV2("vehicleno");
		String transtypeCode = SpringMvcUtils.getParameterV2("transtypeCode");
		String isonline = SpringMvcUtils.getParameterV2("isonline");
		String vehiclebelong = SpringMvcUtils.getParameterV2("vehiclebelong");
	
		String result = getVeh("1",vehicleno,transtypeCode,isonline,vehiclebelong);
		List<String> list = new ArrayList<>();
		if(StringUtils.isNotBlank(result)) {
			list.add(result);
			JSONObject object = (JSONObject) JSON.parse(result);
			System.out.print(object.get("pages"));
			int pages =Integer.parseInt(object.get("pages").toString());
			if(pages >=2) {
				for(int i=2;i<=pages;i++) {
					String data = getVeh(i+"",vehicleno,transtypeCode,isonline,vehiclebelong);
					if(StringUtils.isNotBlank(data)) {
						list.add(data);
					}
				}
			}
		}
		return list;
	}
	private static String getVeh(String curPage,String vehicleno,String transtypeCode,String isonline,String vehiclebelong) throws Exception{
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Cookie", ConfigUtils.getConfig("Cookie"));
		headerMap.put("Host", ConfigUtils.getConfig("host"));
		headerMap.put("Content-Type",ConfigUtils.getConfig("Content_Type"));
		Map<String,String> bodyMap = new HashMap<>();
		if(StringUtils.isNotBlank(vehiclebelong)) {
			bodyMap.put("queryValue[vehiclebelong]", vehiclebelong);
		}else {
			bodyMap.put("queryValue[vehiclebelong]", "1");//车辆归属 1本省 0外省
		}
		if(StringUtils.isNotBlank(vehicleno)) {
			bodyMap.put("queryValue[vehicleno]", vehicleno);
		}else {
			bodyMap.put("queryValue[vehicleno]", "");
		}
		bodyMap.put("queryValue[runStatus]", "");
		bodyMap.put("queryValue[monitorgroup]", "");//所属组
		bodyMap.put("queryValue[linetypeid]", "");//班线类型 1234 四类 全部则不传 
		bodyMap.put("queryValue[corpName]", "");//所属企业
		bodyMap.put("queryValue[nativeAreaCode]", "440306");//籍贯地
		bodyMap.put("queryValue[currentAreaCode]", "440306");//当前地
		bodyMap.put("queryValue[accessName]", "");//所属平台
//		"011"班车客运  012" 包车客运 "020" 货运 "030" 危险品 "050:驾培 "080"公交 090出租 "900" 其他
		if(StringUtils.isNotBlank(transtypeCode)) {
			bodyMap.put("queryValue[transtypeCode]", transtypeCode);//所属行业
		}else {
			bodyMap.put("queryValue[transtypeCode]", "011, 012,020,030,050,080,090,900");//所属行业
		}
		if(StringUtils.isNotBlank(isonline)) {
			bodyMap.put("queryValue[isonline]", isonline);//是否在线 1在线 0离线
		}else {
			bodyMap.put("queryValue[isonline]", "1");//是否在线 1在线 0离线
		}
		bodyMap.put("queryValue[managestatus]", "");//是否匹配 0 匹配 1不匹配
		bodyMap.put("queryType", "advVehQuery");
		bodyMap.put("curPage", curPage);
		String result = HttpClientUtil.sendSSLPost(ConfigUtils.getConfig("newVehAllurl"), headerMap, bodyMap);
		return result;
	}
	/**
	 * 查询车辆轨迹
	 * @return
	 * @param macid 车牌号
	 * @param starttime 20210727235959 
	 * @param endtime 20210729000000
	 * @throws Exception
	 */
	public String vehTrack() throws Exception{
		String macid = SpringMvcUtils.getParameterV2("macid");
		String starttime = SpringMvcUtils.getParameterV2("starttime");
		String endtime = SpringMvcUtils.getParameterV2("endtime");
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Cookie", ConfigUtils.getConfig("Cookie"));
		headerMap.put("Host", ConfigUtils.getConfig("host"));
		headerMap.put("Content-Type",ConfigUtils.getConfig("Content_Type"));
		Map<String,String> bodyMap = new HashMap<>();
		bodyMap.put("macid", "2_"+macid);
		bodyMap.put("startTime", starttime);
		bodyMap.put("endTime", endtime);
		String result = HttpClientUtil.sendSSLPost(ConfigUtils.getConfig("vehTrackurl"), headerMap, bodyMap);
		return result;
	}
	
	public static ResponseEntity<byte[]> createJsonFile(String curPage,String transtypeCode,String startTime,String endTime) throws Exception {
		if(StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
			Date yesterday = DateUtils.getYesterday(new Date());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String format = formatter.format(yesterday);
			startTime = format+"000000";
			endTime = format+"235959";
		}
		if(StringUtils.isBlank(transtypeCode)) {
			transtypeCode = "011,012,020";
		}
		if(StringUtils.isBlank(curPage)) {
			curPage = "4";
		}
		String rootPath = ConfigUtils.getConfig("filepath");
		String fileName = transtypeCode+"_vehTrack.json";
		File parent = new File(rootPath);
		if(!parent.exists()) {
			parent.mkdir();
		}
		File file = new File(parent, fileName);
		if(!file.exists()) {
			file.createNewFile();
		}
		JSONArray jsonArray=new JSONArray();//创建JSONArray对象
//		"011"班车客运  012" 包车客运 "020" 货运 "030" 危险品 "050:驾培 "080"公交 090出租 "900" 其他
		int num = Integer.parseInt(curPage);
		for(int i=1;i<=num;i++) {
			String result0 = getVeh(i+"","",transtypeCode,"","");
			JSONObject object0 = (JSONObject) JSON.parse(result0);
			String rows0 = object0.getString("Rows");
			JSONArray array0 = JSONArray.parseArray(rows0);
			for(int j=0;j<array0.size();j++) {
				JSONObject parse = (JSONObject) JSONObject.parse(array0.get(j).toString());
				String macid = parse.getString("strVehNum");
				String track = vehTrack2(macid,startTime,endTime);
				jsonArray.add(track);
			}
		}
		FileOutputStream fileOutputStream=new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"utf-8");
        BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(jsonArray.toString());
        bufferedWriter.flush();
        bufferedWriter.close();		
        return FileUtils.downTemplet(rootPath+fileName, fileName);
	}
	
	public static String vehTrack2(String macid,String starttime,String endtime) throws Exception{
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Cookie", ConfigUtils.getConfig("Cookie"));
		headerMap.put("Host", ConfigUtils.getConfig("host"));
		headerMap.put("Content-Type",ConfigUtils.getConfig("Content_Type"));
		Map<String,String> bodyMap = new HashMap<>();
		bodyMap.put("macid", "2_"+macid);
		bodyMap.put("startTime", starttime);
		bodyMap.put("endTime", endtime);
		String result = HttpClientUtil.sendSSLPost(ConfigUtils.getConfig("vehTrackurl"), headerMap, bodyMap);
		return result;
	}
	
}

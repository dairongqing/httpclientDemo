package com.pengtu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RiskUtils {
	private static Map<String, Object> pointAndRisk = new HashMap<>();
	private static Map<String, Object> speedAndRisk = new HashMap<>();
	private static Map<String, Object> aadtAndRisk = new HashMap<>();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 公路指标风险系数
	 * @return
	 */
	public static Map<String, Object> getPointAndRiskMap(){
		if (pointAndRisk.size() == 0) {
			Resource resource = resourceLoader.getResource("risk/pointAndRisk.json");
			InputStream is = null;
			
			try {
				is = resource.getInputStream();
				pointAndRisk = mapper.readValue(is, new TypeReference<HashMap<String,Object>>(){});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pointAndRisk;
	}
	
	/***
	 * 运行速度风险系数
	 * @return
	 */
	public static Map<String, Object> getSpeedAndRiskMap(){
		if (speedAndRisk.size() == 0) {
			Resource resource = resourceLoader.getResource("risk/speedAndRisk.json");
			InputStream is = null;
			
			try {
				is = resource.getInputStream();
				speedAndRisk = mapper.readValue(is, new TypeReference<HashMap<String,Object>>(){});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return speedAndRisk;
	}
	
	/***
	 * 交通量风险系数
	 * @return
	 */
	public static Map<String, Object> getAadtAndRiskMap(){
		if (aadtAndRisk.size() == 0) {
			Resource resource = resourceLoader.getResource("risk/aadtAndRisk.json");
			InputStream is = null;
			
			try {
				is = resource.getInputStream();
				aadtAndRisk = mapper.readValue(is, new TypeReference<HashMap<String,Object>>(){});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return aadtAndRisk;
	}
	
	public static void main(String[] args){
		System.out.println(getPointAndRiskMap());
	}
}

package com.pengtu.controller.schedule;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.pengtu.service.gps.VehMonitorService;

/**
 * 
 * @author Administrator
 *
 */
@Component("scheduleTask")
public class ScheduleTask {
	
	@Autowired
	private VehMonitorService vehMonitorService;
	
//	@Scheduled(cron = "*/10 * * * * ? ")
	@Scheduled(cron = "0 0 23 * * ? ")
	public void sendMessageToUser() throws Exception {
		System.out.println("开始生成json");
		vehMonitorService.createJsonFile(null,null,null, null);
	}
	  //表示每隔3秒
    @Scheduled(fixedRate = 3000*10*6*10)
    public void fixedRateJob() {
        System.out.println("fixedRate 每隔30分钟" + new Date());
        try {
			JSONObject login = vehMonitorService.login();
			System.out.println(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}

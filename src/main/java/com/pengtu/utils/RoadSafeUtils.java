//package com.pengtu.utils;
//
//import java.util.Map;
//
//import com.pengtu.entity.roadsafe.RoadPoint;
//
//public class RoadSafeUtils {
//	
//	private static final double CR30 = 0.05;
//	private static final double CR50 = 0.20;
//	private static final double CR70 = 0.33;
//	private static final double CR90 = 1.33;
//	
//	/**对于高级、一级、二级公路
//	 * 根据公路风险等级和交通事故风险等级判断路段类型
//	 * @param roadRiskLevel("1"表示Ⅰ级,"2"表示Ⅱ级,"3"表示Ⅲ级,"4"表示Ⅳ级,"5"表示Ⅴ级,"0"表示非高风险路段,"6"表示高风险路段)
//	 * @param roadAccidentLevel("1"表示Ⅰ级,"2"表示Ⅱ级,"3"表示Ⅲ级,"4"表示Ⅳ级,"5"表示Ⅴ级,"0"表示非事故多发点段,"6"表示事故多发点段)
//	 * @return
//	 */
//	public static String getRoadType(String roadRiskLevel,String roadAccidentLevel){
//		if (StringUtils.isEmpty(roadRiskLevel)) {
//			return null;
//		}else {
//			if ("0".equals(roadAccidentLevel) || "1".equals(roadAccidentLevel) || "2".equals(roadAccidentLevel) || "3".equals(roadAccidentLevel)) {
//				if ("4".equals(roadRiskLevel) || "5".equals(roadRiskLevel) || "6".equals(roadRiskLevel)) {
//					return "B";
//				}else {
//					return "D";
//				}
//			}else {
//				if ("4".equals(roadRiskLevel) || "5".equals(roadRiskLevel) || "6".equals(roadRiskLevel)) {
//					if (StringUtils.isEmpty(roadAccidentLevel)) {
//						return "B";
//					}else {
//						return "A";
//					}	
//				}else {
//					return "C";
//				}
//			}
//		}
//		
//		
//	}
//	
//	/**
//	 * 对于三级、四级公路
//	 * 根据综合指标判别法进行排查
//	 * @param accidentPoint
//	 * @param roadSkillPoint
//	 * @param roadSidePoint
//	 * @param roadEnvironmentPoint
//	 * @param roadAadtPoint
//	 * @param goThroughPoint
//	 * @return
//	 */
//	public static String getRoadType(String accidentPoint,String roadSkillPoint,String roadSidePoint,String roadEnvironmentPoint,String roadAadtPoint,String goThroughPoint){
//		if ("1".equals(goThroughPoint)) {
//			if ("1".equals(roadSkillPoint)) {
//				return "A1";
//			}else{
//				if ("1".equals(roadSidePoint)) {
//					return  "A2";
//				}
//			}
//		}
//		if ("1".equals(roadSkillPoint)) {
//			if ("1".equals(accidentPoint)) {
//				return "A3";
//			}else{
//				if ("1".equals(roadEnvironmentPoint) && "1".equals(roadAadtPoint)) {
//					return "B2";
//				}
//				if ("1".equals(roadSidePoint)) {
//					if ("1".equals(roadAadtPoint)) {
//						return "B1";
//					}
//					return "B3";
//				}
//				return "B4";
//			}
//		}else {
//			if ("1".equals(accidentPoint)) {
//				return "C";
//			}else{
//				return "D";
//			}
//		}
//	}
//	
//    /**
//     * 根据公路风险系数HR判断公路风险等级
//     * "1"表示Ⅰ级，"2"表示Ⅱ级，"3"表示Ⅲ级，"4"表示Ⅳ级，"5"表示Ⅴ级
//     * @param HR
//     * @return
//     */
//    public static String getRoadRiskLevel(Double HR){
//    	if (HR!=null) {
//    		if (HR < 3) {
//    			return "1";
//    		}else if (HR < 5) {
//    			return "2";
//    		}else if (HR < 13) {
//    			return "3";
//    		}else if (HR < 23) {
//    			return "4";
//    		}else {
//    			return "5";
//    		}
//		}else{
//			return null;
//		}
//    	
//    }
//    
//    /**
//     * 根据事故风险系数CR判断交通事故风险等级
//     * "1"表示Ⅰ级，"2"表示Ⅱ级，"3"表示Ⅲ级，"4"表示Ⅳ级，"5"表示Ⅴ级
//     * @param CR
//     * @return
//     */
//    public static String getRoadAccidentLevel(double CR){
//    	if (CR <= CR30) {
//			return "1";
//		}else if (CR <= CR50) {
//			return "2";
//		}else if (CR <= CR70) {
//			return "3";
//		}else if (CR <= CR90) {
//			return "4";
//		}else {
//			return "5";
//		}
//    }
//    
//    /**
//     * 
//     * getRoadRisk  适用于：计算公路风险系数
//     * 公路指标风险类型：1.车辆失控引起的正面相撞风险严重性;2.车辆超车引起的正面相撞风险严重性;3.接入口风险可能性;
//	 * 		       4.接入口风险严重性;5交叉口风险可能性;6.交叉口风险指标严重性
//	 * 运行速度风险类型：1.驶出路外风险;2.车辆失控引起的正面相撞风险;3.车辆超车引起的正面相撞风险;4.交叉口风险;5.接入口风险
//	 * 交通量风险类型：1.驶出路外风险;2.车辆失控引起的正面相撞风险;3.车辆超车引起的正面相撞风险;4.交叉口风险;5.接入口风险
//     * @param roadPoint
//     * @return
//     * @return double
//     * @author zbw
//     * @since  1.0.0
//     */
//    @SuppressWarnings("unchecked")
//	public static Double getRoadRisk(RoadPoint roadPoint) {
//		// TODO Auto-generated method stub
//		Map<String, Object> pointAndRisk = RiskUtils.getPointAndRiskMap();//公路风险指标属性对应的风险系数
//		Map<String, Object> speedAndRisk = RiskUtils.getSpeedAndRiskMap();//运行速度对应的风险系数
//		Map<String, Object> aadtAndRisk = RiskUtils.getAadtAndRiskMap();//交通量对应的风险系数
//		
//		boolean b = false;//判断有无中央分隔带
//		String village = roadPoint.getVillage();//穿村路段
//		if (village == null || "".equals(village)) {
//			village = "0";
//		}
//		String crossType = roadPoint.getCrossType();//交叉口类型值
//		if (crossType == null || "".equals(crossType)) {
//			return null;
//		}
//		String middleStripType = roadPoint.getMiddleStripType();//中间带类型值
//		if (middleStripType == null || "".equals(middleStripType)) {
//			return null;
//		}
//		String singleRoadNum = roadPoint.getSingleRoadNum();//单向车道数
//		if (singleRoadNum == null || "".equals(singleRoadNum)) {
//			return null;
//		}
//		String entryPorts = roadPoint.getEntryPorts();//接入口数
//		if (entryPorts == null || "".equals(entryPorts)) {
//			return null;
//		}
//		String speed = roadPoint.getSpeed();//运行速度
//		if (speed == null || "".equals(speed)) {
//			return null;
//		}
//		String aadt = roadPoint.getAADT();//交通量
//		if (aadt == null || "".equals(aadt)) {
//			return null;
//		}
//		if (roadPoint.getDrivewayWidth() == null || "".equals(roadPoint.getDrivewayWidth())) {
//			return null;
//		}
//		double drivewayWidthRisk = intToDouble(((Map<String, Map<String,Object>>)pointAndRisk.get("drivewayWidth")).get(village).get(roadPoint.getDrivewayWidth()));//车道宽度
//		if (roadPoint.getHorizontalRadius() == null || "".equals(roadPoint.getHorizontalRadius())) {
//			return null;
//		}
//		double horizontalRadius = intToDouble(((Map<String, Object>)pointAndRisk.get("horizontalRadius")).get(roadPoint.getHorizontalRadius()));//平面线半径
//		if (roadPoint.getBendSafty() == null || "".equals(roadPoint.getBendSafty())) {
//			return null;
//		}
//		double bendSafty = intToDouble(((Map<String, Object>)pointAndRisk.get("bendSafty")).get(roadPoint.getBendSafty())); //弯道安全性
//		if (roadPoint.getDirectionSign() == null || "".equals(roadPoint.getDirectionSign())) {
//			return null;
//		}
//		double directionSign = intToDouble(((Map<String, Object>)pointAndRisk.get("directionSign")).get(roadPoint.getDirectionSign()));//诱导标志标线
//		if (roadPoint.getShoulderVibrationLine() == null || "".equals(roadPoint.getShoulderVibrationLine())) {
//			return null;
//		}
//		double shoulderVibrationLine = intToDouble(((Map<String, Object>)pointAndRisk.get("shoulderVibrationLine")).get(roadPoint.getShoulderVibrationLine()));//路肩振动标线
//		if (roadPoint.getRoadState() == null || "".equals(roadPoint.getRoadState())) {
//			return null;
//		}
//		double roadState = intToDouble(((Map<String, Object>)pointAndRisk.get("roadState")).get(roadPoint.getRoadState()));//路面状况
//		if (roadPoint.getSlope() == null || "".equals(roadPoint.getSlope())) {
//			return null;
//		}
//		double slope = intToDouble(((Map<String, Object>)pointAndRisk.get("slope")).get(roadPoint.getSlope()));//坡度
//		if (roadPoint.getSkidResistance() == null || "".equals(roadPoint.getSkidResistance())) {
//			return null;
//		}
//		double skidResistance = intToDouble(((Map<String, Object>)pointAndRisk.get("skidResistance")).get(roadPoint.getSkidResistance()));//抗滑性
//		if (roadPoint.getLighting() == null || "".equals(roadPoint.getLighting())) {
//			return null;
//		}
//		double lighting = intToDouble(((Map<String, Object>)pointAndRisk.get("lighting")).get(roadPoint.getLighting()));//照明
//		
//		//左侧危险物
//		double leftDanger = 0.0;
//		if (roadPoint.getLeftDanger() != null && !"".equals(roadPoint.getLeftDanger())) {
//			leftDanger = intToDouble(((Map<String, Object>)pointAndRisk.get("leftDanger")).get(roadPoint.getLeftDanger()));
//		}
//		
//		//右侧危险物
//		double rightDanger = 0.0;
//		if (roadPoint.getRightDanger() != null &&  !"".equals(roadPoint.getRightDanger())) {
//			rightDanger = intToDouble(((Map<String, Object>)pointAndRisk.get("rightDanger")).get(roadPoint.getRightDanger()));
//		}
//		
//		//距左侧危险物距离
//		double leftDistance = 0.0;
//		if (roadPoint.getLeftDistance() != null && !"".equals(roadPoint.getLeftDistance())) {
//			leftDistance = intToDouble(((Map<String, Object>)pointAndRisk.get("leftDistance")).get(roadPoint.getLeftDistance()));
//		}
//		
//		//距右侧危险物距离
//		double rightDistance = 0.0;
//		if(!StringUtils.isEmpty(roadPoint.getRightDistance())){
//			rightDistance = intToDouble(((Map<String, Object>)pointAndRisk.get("rightDistance")).get(roadPoint.getRightDistance()));
//		}
//		
//		//左侧硬路肩宽度
//		double leftWidth = 0.0;
//		if(!StringUtils.isEmpty(roadPoint.getLeftWidth())){
//			leftWidth = intToDouble(((Map<String, Object>)pointAndRisk.get("leftWidth")).get(roadPoint.getLeftWidth())); 
//		}
//
//		//右侧硬路肩宽度
//		double rightWidth = 0.0;
//		if(!StringUtils.isEmpty(roadPoint.getRightWidth())){
//			rightWidth =intToDouble(((Map<String, Object>)pointAndRisk.get("rightWidth")).get(roadPoint.getRightWidth())); 
//		}
//
//		if(StringUtils.isEmpty(roadPoint.getHasTunnel())){
//			return null;
//		}
//		double hasTunnel = intToDouble(((Map<String, Object>)pointAndRisk.get("hasTunnel")).get(roadPoint.getHasTunnel()));//隧道
//		Map<String, Map<String,Object>>  middleStripTypeMap = (Map<String, Map<String, Object>>) pointAndRisk.get("middleStripType");
//		//中间带类型  : 车辆失控引起的正面相撞风险严重性
//		double middleStripType1 =intToDouble(middleStripTypeMap.get("1").get(middleStripType));
//		//中间带类型  : 车辆超车引起的正面相撞风险严重性
//		double middleStripType2 = intToDouble(middleStripTypeMap.get("2").get(middleStripType));
//		//中间带类型  : 接入口风险可能性
//		double middleStripType3 = intToDouble(middleStripTypeMap.get("3").get(middleStripType));
//		double singleRoadNumRisk = intToDouble(((Map<String, Object>)pointAndRisk.get("singleRoadNum")).get(singleRoadNum));//单向车道数
//		Map<String, Map<String,Object>> crossTypeMap = (Map<String, Map<String, Object>>) pointAndRisk.get("crossType");
//		//交叉口类型：交叉口风险可能性
//		double crossType5 = intToDouble(crossTypeMap.get("5").get(crossType));
//		//交叉口类型：交叉口风险指标严重性
//		double crossType6 = intToDouble(crossTypeMap.get("6").get(crossType));
//		if(StringUtils.isEmpty(roadPoint.getSpeedDifference())){
//			return null;
//		}
//		double speedDifference = intToDouble(((Map<String, Object>)pointAndRisk.get("speedDifference")).get(roadPoint.getSpeedDifference()));//不同车型运行速度差
//		if(StringUtils.isEmpty(roadPoint.getCrossAngel())){
//			return null;
//		}
//		double crossAngel = intToDouble(((Map<String, Object>)pointAndRisk.get("crossAngel")).get(roadPoint.getCrossAngel()));//交叉口角度
//		if(StringUtils.isEmpty(roadPoint.getCrossSafty())){
//			return null;
//		}
//		double crossSafty = intToDouble(((Map<String, Object>)pointAndRisk.get("crossSafty")).get(roadPoint.getCrossSafty()));//交叉口安全性
//		if(StringUtils.isEmpty(roadPoint.getSightDistance())){
//			return null;
//		}
//		double sightDistance = intToDouble(((Map<String, Object>)pointAndRisk.get("sightDistance")).get(roadPoint.getSightDistance()));//视距
//		if(StringUtils.isEmpty(roadPoint.getCrossCanalization())){
//			return null;
//		}
//		double crossCanalization = intToDouble(((Map<String, Object>)pointAndRisk.get("crossCanalization")).get(roadPoint.getCrossCanalization()));//渠化
//		if(StringUtils.isEmpty(roadPoint.getPave())){
//			return null;
//		}
//		double pave = intToDouble(((Map<String, Object>)pointAndRisk.get("pave")).get(roadPoint.getPave()));//辅路
//		if(StringUtils.isEmpty(roadPoint.getSlowMeasure())){
//			return null;
//		}
//		double slowMeasure = intToDouble(((Map<String, Object>)pointAndRisk.get("slowMeasure")).get(roadPoint.getSlowMeasure()));//速度管理
//		Map<String, Map<String,Object>> entryPortsMap = (Map<String, Map<String, Object>>) pointAndRisk.get("entryPorts");
//		//接入口：接入口风险可能性
//		double entryPorts3 = intToDouble(entryPortsMap.get("3").get(entryPorts));
//		//接入口：接入口风险严重性
//		double entryPorts4 = intToDouble(entryPortsMap.get("4").get(entryPorts));
//		
//		//判断有无中间带
//		if (!"10".equals(middleStripType) || !"9".equals(middleStripType) || !"8".equals(middleStripType) || !"7".equals(middleStripType)) {
//			b = true;
//		}
//		
//		//驶出路外风险：
//		//1.运行速度
//		Map<String, Map<String,Object>> speedMap1 = (Map<String, Map<String, Object>>) speedAndRisk.get("1");
//		if (StringUtils.isEmpty(roadPoint.getRoadSide())) {
//			return null;
//		}
//		double speed1 = intToDouble((speedMap1.get(roadPoint.getRoadSide()).get(speed)));
//		//2.交通量
//		Map<String, Map<String,Object>> aadtMap1 = (Map<String, Map<String, Object>>) aadtAndRisk.get("1");
//		double aadt1 = 0.0;
//		if (b) {
//			aadt1 = 0.5;
//		}else {
//			aadt1 = intToDouble(aadtMap1.get(singleRoadNum).get(aadt));
//		}
//		//驶出路外(左侧)风险可能性
//		double leftProbable = drivewayWidthRisk*horizontalRadius*bendSafty*directionSign*shoulderVibrationLine*roadState*slope*skidResistance*lighting;
//		//驶出路外(左侧)风险严重性
//		double leftBadly = leftDanger*leftDistance*leftWidth;
//		//驶出路外(右侧)风险可能性
//		double rightProbable = drivewayWidthRisk*horizontalRadius*bendSafty*directionSign*shoulderVibrationLine*roadState*slope*skidResistance*lighting;
//		//驶出路外(右侧)风险严重性
//		double rightBadly = rightDanger*rightDistance*rightWidth;
//		//驶出路外风险(左侧)
//		double leftOutRoad = speed1*aadt1*leftProbable*leftBadly;
//		//驶出路外风险(右侧)
//		double rightOutRoad = speed1*aadt1*rightProbable*rightBadly;
//
//		//车辆失控引起的正面相撞风险：
//		//1.运行速度(设中央隔离带的穿村路段)
//		double speed2 = 0.0;
//		Map<String, Map<String,Object>> speedMap2 = (Map<String, Map<String, Object>>) speedAndRisk.get("2");
//		if (b && "1".equals(village)) {
//			speed2 = intToDouble(speedMap2.get("1").get(speed));
//		}else{
//			speed2 = intToDouble(speedMap2.get("0").get(speed));
//		}
//		//2.交通量
//		Map<String, Map<String,Object>> aadtMap2 = (Map<String, Map<String, Object>>) aadtAndRisk.get("2");
//		double aadt2 = intToDouble(aadtMap2.get(singleRoadNum).get(aadt));
//		//车辆失控引起的正面相撞风险(可能性)
//		double outControlProbable = hasTunnel*leftProbable;
//		//车辆失控引起的正面相撞风险(严重性)
//		double outControlBadly = middleStripType1;
//		double outControl = outControlProbable*outControlBadly*speed2*aadt2;//车辆失控引起的正面相撞风险
//
//		//车辆超车引起的正面相撞风险：
//		//1.运行速度
//		double speed3 = intToDouble(((Map<String, Object>)speedAndRisk.get("3")).get(speed));
//		//2.交通量
//		Map<String,Object> aadtMap3 = (Map<String,Object>) aadtAndRisk.get("3");
//		double aadt3 = 0.0;
//		if (!b) {
//			aadt3 = intToDouble(aadtMap3.get(aadt));
//		}
//		//车辆超车引起的正面相撞风险(可能性)
//		double overTakeProbable = hasTunnel*singleRoadNumRisk*slope*skidResistance*speedDifference*lighting;
//		//车辆超车引起的正面相撞风险(严重性)
//		double overTakeBadly = middleStripType2;
//		double overTake = overTakeProbable*overTakeBadly*speed3*aadt3;//车辆超车引起的正面相撞风险
//		
//		//交叉口风险：
//		//1.运行速度(公路与铁路平面相交路段)
//		double speed4 = 0.0;
//		Map<String, Map<String,Object>> speedMap4 = (Map<String, Map<String, Object>>) speedAndRisk.get("4");
//		if ("11".equals(crossType) || "12".equals(crossType)) {
//			speed4 = intToDouble(speedMap4.get("1").get(speed));
//		}else{
//			speed4 = intToDouble(speedMap4.get("0").get(speed));
//		}
//		//2.交通量
//		Map<String,Object> aadtMap4 = (Map<String,Object>) aadtAndRisk.get("4");
//		double aadt4 = intToDouble(aadtMap4.get(roadPoint.getCrossFlow()));
//		//交叉口风险可能性
//		double crossRiskProbable = crossType5*crossAngel*crossSafty*slope*lighting*skidResistance*sightDistance*crossCanalization*slowMeasure;
//		//交叉口风险严重性
//		double crossRiskBadly = crossType6;
//		double crossRisk = crossRiskProbable*crossRiskBadly*speed4*aadt4;//交叉口风险
//		
//		//接入口风险：
//		//1.运行速度
//		double speed5= intToDouble(((Map<String, Object>)speedAndRisk.get("5")).get(speed));
//		//2.交通量
//		Map<String,Object> aadtMap5 = (Map<String,Object>) aadtAndRisk.get("5");
//		double aadt5 = intToDouble(aadtMap5.get(entryPorts));
//		//接入口风险可能性
//		double entryRiskProbable = entryPorts3*pave*middleStripType3*lighting;
//		//接入口风险严重性
//		double entryRiskBadly = entryPorts4;
//		double entryRisk = entryRiskProbable*entryRiskBadly*speed5*aadt5;
//		
//		return (double)Math.round((leftOutRoad+rightOutRoad+outControl+overTake+crossRisk+entryRisk)*100)/100;
//		
//	}
//    
//	public static Double intToDouble(Object obj){
//		try {
//			if (obj instanceof Integer) {
//				return Double.parseDouble(obj.toString());
//			}
//			return (double) obj;
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("*********************"+obj);
//		}
//		return null;
//		
//	}
//	
//	public static void main(String[] arg){
//		
//		String result = getRoadType("0","1","0","0","1","1");
//		System.out.println(result);
//	}
//}

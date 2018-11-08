package com.example.transportservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.transportservice.bean.car;
import com.example.transportservice.dao.carDao;
import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/transportservice/action/")
public class Action {

    @Autowired
    carDao dao;

    //设置小车动作
    @RequestMapping(value = "SetCarMove.do")
    @ResponseBody
    public String SetCarMove(@RequestBody JSONObject jsonObject) {
        int i=jsonObject.getInteger("CarId");
        if (i<=0||i>6){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\"}";
    }

    //查询小车账户余额
    @RequestMapping(value = "GetCarAccountBalance.do")
    @ResponseBody
    public String GetCarAccountBalance(@RequestBody JSONObject jsonObject) {
        int id=jsonObject.getInteger("CarId");
        if (id<=0||id>5){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        car c=dao.getmoneybycarid(id);
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"Balance\":"+c.getMoney()+"}";
    }

    //小车账户充值
    @RequestMapping(value = "SetCarAccountRecharge.do")
    @ResponseBody
    public String SetCarAccountRecharge(@RequestBody JSONObject jsonObject) {
        int id=jsonObject.getInteger("CarId");
        if (id<=0||id>5){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        int money=jsonObject.getInteger("Money");
        car c=dao.getmoneybycarid(id);
        dao.setmoney(id,money+c.getMoney());
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\"}";
    }

    //查询车辆违章记录
    @RequestMapping(value = "GetCarPeccancy.do")
    @ResponseBody
    public String GetCarPeccancy() {
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"ROWS_DETAIL\":[{\"carnumber\":\"鲁B10001\",\"pcode\":\"1001A\",\"paddr\":\"学院路\",\"datetime\":\" 2016/5/21 8:19:21\"},{\"carnumber\":\"鲁B10022\",\"pcode\":\"1221A\",\"paddr\":\"xx路\",\"datetime\":\" 2016/5/21 8:19:21\"}]},{\"carnumber\":\"鲁B12201\",\"pcode\":\"1222A\",\"paddr\":\"yy路\",\"datetime\":\" 2016/5/21 8:19:21\"}";
    }

    //查询所有车辆违章记录（管理员权限）
    @RequestMapping(value = "GetAllCarPeccancy.do")
    @ResponseBody
    public String GetAllCarPeccancy(@RequestBody JSONObject jsonObject) {
        String carnumber=jsonObject.getString("carnumber");
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"ROWS_DETAIL\":[{\"carnumber\":\""+carnumber+"\",\"pcode\":\"1001A\",\"paddr\":\"学院路\",\"datetime\":\" 2016/5/21 8:19:21\"},{\"carnumber\":\""+carnumber+"\",\"pcode\":\"1221A\",\"paddr\":\"xx路\",\"datetime\":\" 2016/5/21 8:19:21\"}]},{\"carnumber\":\""+carnumber+"\",\"pcode\":\"1222A\",\"paddr\":\"yy路\",\"datetime\":\" 2016/5/21 8:19:21\"}";
    }

    //设置红绿灯当前状态（管理员权限）
    @RequestMapping(value = "SetTrafficLightNowStatus.do")
    @ResponseBody
    public String SetTrafficLightNowStatus(@RequestBody JSONObject jsonObject) {
        int i=jsonObject.getInteger("TrafficLightId");
        if (i<=0||i>5){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\"}";
    }

    //查询红绿灯当前状态（管理员权限）
    @RequestMapping(value = "GetTrafficLightNowStatus.do")
    @ResponseBody
    public String GetTrafficLightNowStatus(@RequestBody JSONObject jsonObject) {
        int i=jsonObject.getInteger("TrafficLightId");
        if (i<=0||i>5){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        int x = (int) (Math.random() * 3);
        String Status = "";
        switch (x) {
            case 0:
                Status = "Red";
                break;
            case 1:
                Status = "Green";
                break;
            case 2:
                Status = "Yellow";
                break;
        }
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"查询成功\",\"Status\":\"" + Status + "\",\"Time\":" + (int) (Math.random() * 30) + "}";
    }

    //设置红绿灯的配置信息（管理员权限）
    @RequestMapping(value = "SetTrafficLightConfig.do")
    @ResponseBody
    public String SetTrafficLightConfig(@RequestBody JSONObject jsonObject) {
        int i=jsonObject.getInteger("TrafficLightId");
        if (i<=0||i>5){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\"}";
    }

    //查询红绿灯的配置信息（管理员权限）
    @RequestMapping(value = "GetTrafficLightConfigAction.do")
    @ResponseBody
    public String GetTrafficLightConfigAction() {
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"设置成功\",\"RedTime\":\"" + (int) (Math.random() * 40) + "\",\"GreenTime\":\"" + (int) (Math.random() * 40) + "\",\"YellowTime\":\"" + (int) (Math.random() * 40) + "\"}";
    }

    //设置自动/手工控制模式（管理员权限）
    @RequestMapping(value = "SetRoadLightControlMode.do")
    @ResponseBody
    public String SetRoadLightControlMode() {
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\"}";
    }

    //手动打开/关闭定路灯（管理员权限）
    @RequestMapping(value = "SetRoadLightStatusAction.do")
    @ResponseBody
    public String SetRoadLightStatusAction(@RequestBody JSONObject jsonObject) {
        int i=jsonObject.getInteger("RoadLightId");
        if (i<=0||i>3){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\"}";
    }

    //查询当前路灯状态（管理员权限）
    @RequestMapping(value = "GetRoadLightStatus.do")
    @ResponseBody
    public String GetRoadLightStatus(@RequestBody JSONObject jsonObject) {
        int i=jsonObject.getInteger("RoadLightId");
        if (i<=0||i>3){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        int t = (int) (Math.random() * 2);
        String Status = "";
        if (t == 0) {
            Status = "Close";
        } else {
            Status = "Open";
        }
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"Status\":\"" + Status + "\"}";
    }

    //查询“所有传感器”的当前值
    @RequestMapping(value = "GetAllSense.do")
    @ResponseBody
    public String GetAllSense() {
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"pm2.5\":" + (int) (Math.random() * 15) + ",\"co2\":" + (int) (Math.random() * 6000) + ",\"LightIntensity\":" + (int) (Math.random() * 4000) + ",\"humidity\":" + (int) (Math.random() * 60) + ",\"temperature\":" + (int) (Math.random() * 40) + "}";
    }

    // 查询单个传感器
    @RequestMapping(value = "GetSenseByName.do")
    @ResponseBody
    public String GetSenseByName(@RequestBody JSONObject jsonObject) {
        String SenseName = jsonObject.getString("SenseName");
        int xxx = 0;
        switch (SenseName) {
            case "temperature":
                xxx = (int) (Math.random() * 40);
                break;
            case "humidity":
                xxx = (int) (Math.random() * 60);
                break;
            case "co2":
                xxx = (int) (Math.random() * 6000);
                break;
            case "LightIntensity":
                xxx = (int) (Math.random() * 4000);
                break;
            case "pm2.5":
                xxx = (int) (Math.random() * 15);
                break;
        }
        if (xxx != 0) {
            return "{\"RESULT\":\"S\",\"ERRMSG\":\"查询成功\",\"" + SenseName + "\":\"" + xxx + "\"}";
        } else {
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }

    }

    //车载容量查询
    @RequestMapping(value = "GetBusCapacity.do")
    @ResponseBody
    public String GetBusCapacity(@RequestBody JSONObject jsonObject) {
        int i=jsonObject.getInteger("BusId");
        if (i<=0||i>15){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"BusCapacity\":" + (int) (Math.random() * 30) + "}";
    }

    //查询道路状态
    @RequestMapping(value = "GetRoadStatus.do")
    @ResponseBody
    public String GetRoadStatus(@RequestBody JSONObject jsonObject) {
        int i=jsonObject.getInteger("RoadId");
        if (i<=0||i>12){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"Status\":" + (int) (Math.random() * 5 + 1) + "}";
    }

    //站台信息查询
    @RequestMapping(value = "GetBusStationInfo.do")
    @ResponseBody
    public String GetBusStationInfo(@RequestBody JSONObject jsonObject) {
        int i=jsonObject.getInteger("BusStationId");
        if (i<=0||i>3){
            return "{\"RESULT\":\"F\",\"ERRMSG\":\"失败\"}";
        }
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"查询成功\",\"ROWS_DETAIL\":[{\"Distance\":" + (int) (Math.random() * 100000) + ",\"BusId\":1},{\"Distance\":" + (int) (Math.random() * 100000) + ",\"BusId\":2}]}";
    }

    //车辆信息（管理员权限）
    @RequestMapping(value = "GetCarInfo.do")
    @ResponseBody
    public String GetCarInfo() {
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"ROWS_DETAIL\":[{\"carnumber\":\"鲁 B10001\",\"number\":1,\" pcardid \":\"370101196101011001\",\"buydata\":\"2016.5.1\",\"carbrand\":\"audi\"},{\"carnumber\":\"鲁 B10002\",\"number\":2,\" pcardid \":\"370101196101011001\",\"buydata\":\"2016.5.1\",\"carbrand\":\"audi\"},{\"carnumber\":\"鲁 B10003\",\"number\":3,\" pcardid \":\"370101196101011001\",\"buydata\":\"2016.5.1\",\"carbrand\":\"benchi\"},{\"carnumber\":\"鲁 B10004\",\"number\":4,\" pcardid \":\"370101196101011001\",\"buydata\":\"2016.5.1\",\"carbrand\":\"baoma\"}]}";
    }

    //违章代码
    @RequestMapping(value = "GetPeccancyType.do")
    @ResponseBody
    public String GetPeccancyType() {
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"ROWS_DETAIL\":[{\"pcode\":\"1001A\",\"pmoney\":1000,\"pscore\":0,\"premarks\":\"A 驾驶拼装的非汽车类机动车上道路行驶的\"},{\"pcode\":\"1001A\",\"pmoney\":1000,\"pscore\":0,\"premarks\":\"A 驾驶拼装的非汽车类机动车上道路行驶的\"},{\"pcode\":\"1001A\",\"pmoney\":1000,\"pscore\":0,\"premarks\":\"A 驾驶拼装的非汽车类机动车上道路行驶的\"}]}";
    }

    //用户登录
    @RequestMapping(value = "user_login.do")
    @ResponseBody
    public String user_login() {
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"登录成功\",\"UserRole\":\"R01\"}";
    }

    //获取所有用户信息（管理员权限）
    @RequestMapping(value = "GetSUserInfo.do")
    @ResponseBody
    public String GetSUserInfo() {
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"ROWS_DETAIL\":[{\"pname\":\"何颖升\",\"pcardid\":\"370213196502141014\",\"psex\":\"男\",\"username\":\"user14\",\"ptel\":\"13804110014\",\"pregistdate\":\"1990/6/314:19:21\"},{\"pname\":\"张三\",\"pcardid\":\"370213196502141014\",\"psex\":\"男\",\"username\":\"user14\",\"ptel\":\"13804110014\",\"pregistdate\":\"1990/6/314:19:21\"},{\"pname\":\"李四\",\"pcardid\":\"370213196502141014\",\"psex\":\"男\",\"username\":\"user14\",\"ptel\":\"13804110014\",\"pregistdate\":\"1990/6/314:19:21\"},{\"pname\":\"王五\",\"pcardid\":\"370213196502141014\",\"psex\":\"男\",\"username\":\"user14\",\"ptel\":\"13804110014\",\"pregistdate\":\"1990/6/314:19:21\"}]}";
    }

    //气象信息查询
    @RequestMapping(value = "GetWeather.do")
    @ResponseBody
    public String GetWeather() {
        int WCurrent = (int) (Math.random() * 40);
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"查询成功\",\"WCurrent\":\"" + WCurrent + "\",\"ROWS_DETAIL\":[{\"WData\":\""+getDateTime(0)+"\",\"temperature\":\""+getTemperature()+"\"},{\"WData\":\""+getDateTime(1)+"\",\"temperature\":\""+getTemperature()+"\"},{\"WData\":\""+getDateTime(2)+"\",\"temperature\":\""+getTemperature()+"\"},{\"WData\":\""+getDateTime(3)+"\",\"temperature\":\""+getTemperature()+"\"},{\"WData\":\""+getDateTime(4)+"\",\"temperature\":\""+getTemperature()+"\"},{\"WData\":\""+getDateTime(5)+"\",\"temperature\":\""+getTemperature()+"\"}]}";
    }

    private String getDateTime(int i){
        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.DATE, i);
        Date date=cd.getTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private String getTemperature(){
        int i= (int) (Math.random()*30);
        int t=i+(int)(Math.random()*12);
        return i+"~ "+t;
    }
}

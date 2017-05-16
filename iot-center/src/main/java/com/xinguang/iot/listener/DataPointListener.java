package com.xinguang.iot.listener;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.xinguang.iot.dao.entity.DataHumidity;
import com.xinguang.iot.dao.entity.DataTemperature;
import com.xinguang.iot.dao.entity.SysSensor;
import com.xinguang.iot.service.DataService;
import com.xinguang.iot.service.SensorService;
import com.xinguang.iot.service.SpringContextHolder;
import com.xinguang.msgprotocol.client.transfer.IotDataPointPB;
import com.xinguang.msgprotocol.client.transfer.MCProtocolPB;
import com.xinguang.msgprotocol.internal.transfer.InternalMCProtocolPB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by jinx on 2017/5/16.
 */
public class DataPointListener implements MessageListener {

    @Autowired
    DataService dataService;
    @Autowired
    SensorService sensorService;
    private Logger logger = LoggerFactory.getLogger(DataPointListener.class);
    @Override
    public void onMessage(Message message) {
        try {
            byte[] data = message.getBody();
            InternalMCProtocolPB.InternalMCProtocol internalMCProtocol = InternalMCProtocolPB.InternalMCProtocol.parseFrom(data);
            MCProtocolPB.MCProtocol mcProtocol = internalMCProtocol.getBody().unpack(MCProtocolPB.MCProtocol.class);
            IotDataPointPB.IotDataPoint dataPoint = IotDataPointPB.IotDataPoint.parseFrom(mcProtocol.getBody().toByteArray());
            long time = dataPoint.getDate();
            String topic = dataPoint.getTopic();
            String[] bodys = dataPoint.getPayload().split(",");//传过来的数据为设备id，传感器id，数值
            logger.info("receive message from device:{}",bodys.toString());
            SysSensor sensor = sensorService.findSensorByCode(bodys[1]);
            if(Topic.TEM.getLable().equals(topic)){
                DataTemperature temperature = new DataTemperature();
                temperature.setTime(new Date(time));
                if (sensor!=null)
                    temperature.setSensorId(sensor.getId());
                temperature.setValue(bodys[2]);
                dataService.saveTemp(temperature);
            }
            if(Topic.HUM.getLable().equals(topic)){
                DataHumidity humidity = new DataHumidity();
                humidity.setTime(new Date(time));
                if (sensor!=null)
                    humidity.setSensorId(sensor.getId());
                humidity.setValue(bodys[2]);
                dataService.saveHumi(humidity);
            }
        } catch (Exception e) {
            logger.error("exception:{}",e.getMessage());
        }
    }

    public enum Topic {
        TEM("0", "Temperature")
        ,HUM("1", "Humidity")
        ,GPS("2", "GPS")
        ;

        public static Topic valueOfCode(String code) {
            if (null == code || code.length() < 1) {
                return null;
            }
            for (Topic topic : Topic.values()) {
                if (topic.getCode().equals(code)) {
                    return topic;
                }
            }
            return null;
        }
        Topic(String code, String lable) {
            this.code = code;
            this.lable = lable;
        }

        private String code;
        private String lable;

        public String getCode() {
            return code;
        }
        public String getLable() {
            return lable;
        }
    }
}

package com.xinguang.iot.mqttprotocol;

import com.xinguang.iot.mqttprotocol.message.ConnAckMessage;
import com.xinguang.iot.mqttprotocol.message.ConnAckVariableHeader;
import com.xinguang.iot.mqttprotocol.message.ConnectMessage;
import com.xinguang.iot.mqttprotocol.message.ConnectPayload;
import com.xinguang.iot.mqttprotocol.message.ConnectVariableHeader;
import com.xinguang.iot.mqttprotocol.message.FixedHeader;
import com.xinguang.iot.mqttprotocol.message.Message;
import com.xinguang.iot.mqttprotocol.message.PackageIdVariableHeader;
import com.xinguang.iot.mqttprotocol.message.PublishMessage;
import com.xinguang.iot.mqttprotocol.message.PublishVariableHeader;
import com.xinguang.iot.mqttprotocol.message.SubAckMessage;
import com.xinguang.iot.mqttprotocol.message.SubAckPayload;
import com.xinguang.iot.mqttprotocol.message.SubscribeMessage;
import com.xinguang.iot.mqttprotocol.message.SubscribePayload;
import com.xinguang.iot.mqttprotocol.message.UnSubscribeMessage;
import com.xinguang.iot.mqttprotocol.message.UnSubscribePayload;

import io.netty.buffer.ByteBuf;

public final class MQTTMesageFactory {

    public static Message newMessage(FixedHeader fixedHeader, Object variableHeader, Object payload) {
        switch (fixedHeader.getMessageType()) {
            case CONNECT :
                return new ConnectMessage(fixedHeader, 
                		(ConnectVariableHeader)variableHeader, 
                		(ConnectPayload)payload);

            case CONNACK:
                return new ConnAckMessage(fixedHeader, (ConnAckVariableHeader) variableHeader);

            case SUBSCRIBE:
                return new SubscribeMessage(
                        fixedHeader,
                        (PackageIdVariableHeader) variableHeader,
                        (SubscribePayload) payload);

            case SUBACK:
                return new SubAckMessage(
                        fixedHeader,
                        (PackageIdVariableHeader) variableHeader,
                        (SubAckPayload) payload);

            case UNSUBSCRIBE:
                return new UnSubscribeMessage(
                        fixedHeader,
                        (PackageIdVariableHeader) variableHeader,
                        (UnSubscribePayload) payload);

            case PUBLISH:
                return new PublishMessage(
                        fixedHeader,
                        (PublishVariableHeader) variableHeader,
                        (ByteBuf) payload);

            case PUBACK:
            case UNSUBACK:
            case PUBREC:
            case PUBREL:
            case PUBCOMP:
                return new Message(fixedHeader, variableHeader);

            case PINGREQ:
            case PINGRESP:
            case DISCONNECT:
                return new Message(fixedHeader);

            default:
                throw new IllegalArgumentException("unknown message type: " + fixedHeader.getMessageType());
        }
    }

    private MQTTMesageFactory() { }
}
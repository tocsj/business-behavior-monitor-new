package com.tkck.monitor.sdk.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.tkck.monitor.sdk.model.LogMessage;
import com.tkck.monitor.sdk.push.IPush;
import com.tkck.monitor.sdk.push.Impl.RedisPush;

import java.util.Arrays;

public class CustomAppender<E> extends AppenderBase<E> {
    // 系统名称
    private String systemName;
    // 只采集指定范围的日志
    private String groupId;
    // redis 连接端口
    private int port;
    // redis 连接地址
    private String host;
    private final IPush push=new RedisPush();


    @Override
    protected void append(E eventObject) {
        // 开启推送
        push.open(host, port);
        if(eventObject instanceof ILoggingEvent){
            ILoggingEvent event = (ILoggingEvent) eventObject;
            StackTraceElement[] callerDataArray = event.getCallerData();
            String className= "unknown";
            String methodName = "unknown";

            if(null != callerDataArray && callerDataArray.length > 0){
                StackTraceElement callerData = callerDataArray[0];
                className = callerData.getClassName();
                methodName = callerData.getMethodName();

            }
            if(!className.startsWith(groupId)){
                return;
            }

            // 构建日志
            LogMessage logMessage = new LogMessage(systemName, className, methodName, Arrays.asList(event.getFormattedMessage().split(" ")));
            // 推送日志
            push.send(logMessage);
        }


    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}

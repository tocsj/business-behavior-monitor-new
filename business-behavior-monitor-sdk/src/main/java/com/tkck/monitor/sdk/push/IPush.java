package com.tkck.monitor.sdk.push;

import com.tkck.monitor.sdk.model.LogMessage;

public interface IPush {
    void open(String host, int port);

    void send(LogMessage logMessage);

}

package com.tkck.monitor.domain.service;

import com.tkck.monitor.domain.model.entity.MonitorDataMapEntity;
import com.tkck.monitor.domain.model.valobj.MonitorTreeConfigVO;
import ognl.OgnlException;

import java.util.List;

public interface ILogAnalyticalService {

    void doAnalytical(String systemName, String className, String methodName, List<String> logList) throws OgnlException;


    List<MonitorDataMapEntity> queryMonitorDataMapEntityList();

    MonitorTreeConfigVO queryMonitorFlowData(String monitorId);
}

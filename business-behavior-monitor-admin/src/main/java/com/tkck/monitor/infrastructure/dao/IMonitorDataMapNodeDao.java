package com.tkck.monitor.infrastructure.dao;

import com.tkck.monitor.infrastructure.po.MonitorDataMapNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataMapNodeDao {
    List<MonitorDataMapNode> queryMonitoryDataMapNodeList(MonitorDataMapNode monitorDataMapNodeReq);

    List<MonitorDataMapNode> queryMonitorNodeConfigByMonitorId(String monitorId);
}

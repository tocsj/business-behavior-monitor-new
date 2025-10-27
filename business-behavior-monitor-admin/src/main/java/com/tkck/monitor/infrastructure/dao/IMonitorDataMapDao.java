package com.tkck.monitor.infrastructure.dao;

import com.tkck.monitor.domain.model.entity.MonitorDataMapEntity;
import com.tkck.monitor.infrastructure.po.MonitorDataMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataMapDao {
    String queryMonitorNameByMonitoryId(String monitorId);

    List<MonitorDataMap> queryMonitorDataMapEntityList();
}

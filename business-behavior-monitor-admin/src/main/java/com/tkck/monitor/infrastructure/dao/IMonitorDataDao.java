package com.tkck.monitor.infrastructure.dao;


import com.tkck.monitor.infrastructure.po.MonitorData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMonitorDataDao {
    List<MonitorData> queryMonitorData();
}

package com.tkck.monitor.domain.repository;

import com.tkck.monitor.domain.model.entity.MonitorDataEntity;
import com.tkck.monitor.domain.model.entity.MonitorDataMapEntity;
import com.tkck.monitor.domain.model.valobj.GatherNodeExpressionVO;

import java.util.List;

public interface IMonitorRepository {
    List<GatherNodeExpressionVO> queryGatherNodeExpressionVO(String systemName, String className, String methodName);

    String queryMonitoryNameByMonitoryId(String monitorId);

    void saveMonitoryData(MonitorDataEntity monitorDataEntity);

    List<MonitorDataMapEntity> queryMonitorDataMapEntityList();
}

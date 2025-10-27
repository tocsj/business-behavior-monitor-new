package com.tkck.monitor.trigger.http;

import com.tkck.monitor.domain.model.entity.MonitorDataMapEntity;
import com.tkck.monitor.domain.service.ILogAnalyticalService;
import com.tkck.monitor.trigger.http.dto.MonitorDataMapDTO;
import com.tkck.monitor.types.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/v1/monitor/")
public class MonitorController {
    @Resource
    private ILogAnalyticalService logAnalyticalService;

    @RequestMapping(value = "query_monitor_data_map_entity_list", method = RequestMethod.GET)
    public Response<List<MonitorDataMapDTO>> queryMonitorDataMapEntityList() {
        log.info("查询监控列表数据开始");
        try {
            List<MonitorDataMapEntity> monitorDataMapEntityList = logAnalyticalService.queryMonitorDataMapEntityList();
            List<MonitorDataMapDTO> monitorDataMapDTOList = new ArrayList<>(monitorDataMapEntityList.size());
            for (MonitorDataMapEntity monitorDataMapEntity : monitorDataMapEntityList) {
                MonitorDataMapDTO monitorDataMapDTO = new MonitorDataMapDTO();
                monitorDataMapDTO.setMonitorId(monitorDataMapEntity.getMonitorId());
                monitorDataMapDTO.setMonitorName(monitorDataMapEntity.getMonitorName());
                monitorDataMapDTOList.add(monitorDataMapDTO);
            }
            return Response.<List<MonitorDataMapDTO>>builder()
                    .code("0000")
                    .info("调用成功")
                    .data(monitorDataMapDTOList)
                    .build();
        } catch (Exception e) {
            log.error("查询监控列表数据失败", e);
            return Response.<List<MonitorDataMapDTO>>builder()
                    .code("0001")
                    .info("调用失败")
                    .build();

        }
    }


}

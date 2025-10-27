package com.tkck.monitor.trigger.http;

import com.tkck.monitor.domain.model.entity.MonitorDataMapEntity;
import com.tkck.monitor.domain.model.valobj.MonitorTreeConfigVO;
import com.tkck.monitor.domain.service.ILogAnalyticalService;
import com.tkck.monitor.trigger.http.dto.MonitorDataMapDTO;
import com.tkck.monitor.trigger.http.dto.MonitorFlowDataDTO;
import com.tkck.monitor.types.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "query_monitor_flow_map", method = RequestMethod.GET)
    public Response<MonitorFlowDataDTO> queryMonitorFlowMap(@RequestParam String monitorId) {
        log.info("查询监控列表数据开始");
        try {
            MonitorTreeConfigVO monitorTreeConfigVO = logAnalyticalService.queryMonitorFlowData(monitorId);
            List<MonitorTreeConfigVO.Node> nodeList = monitorTreeConfigVO.getNodeList();
            List<MonitorTreeConfigVO.Link> linkList = monitorTreeConfigVO.getLinkList();

            List<MonitorFlowDataDTO.NodeData> nodeDataList = new ArrayList<>(nodeList.size());
            for (MonitorTreeConfigVO.Node node : nodeList) {
                MonitorFlowDataDTO.NodeData nodeData = new MonitorFlowDataDTO.NodeData();
                nodeData.setKey(node.getMonitorNodeId());
                nodeData.setText(node.getMonitorNodeName());
                nodeData.setFill(node.getColor());
                nodeData.setLoc(node.getLoc());
                nodeDataList.add(nodeData);
            }
            List<MonitorFlowDataDTO.LinkData> linkDataList = new ArrayList<>(linkList.size());
            for (MonitorTreeConfigVO.Link link : linkList) {
                String linkValue = link.getLinkValue();
                linkDataList.add("0".equals(linkValue) ?
                        new MonitorFlowDataDTO.LinkData(link.getFromMonitorNodeId(), link.getToMonitorNodeId()) :
                        new MonitorFlowDataDTO.LinkData(link.getFromMonitorNodeId(), link.getToMonitorNodeId(), link.getLinkKey(), linkValue));
            }
            MonitorFlowDataDTO monitorFlowDataDTO = new MonitorFlowDataDTO();

            monitorFlowDataDTO.setNodeDataArray(nodeDataList);
            monitorFlowDataDTO.setLinkDataArray(linkDataList);

            return Response.<MonitorFlowDataDTO>builder()
                    .code("0000")
                    .info("调用成功")
                    .data(monitorFlowDataDTO)
                    .build();
        } catch (Exception e) {

            log.error("查询监控列表数据失败", e);
            return Response.<MonitorFlowDataDTO>builder()
                    .code("0001")
                    .info("调用失败")
                    .build();
        }

    }


}

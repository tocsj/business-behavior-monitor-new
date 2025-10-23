package com.tkck.monitor.infrastructure.po;

import lombok.Data;

import java.security.PrivateKey;
import java.util.Date;

@Data
public class MonitorData {
   private Long id;

   private String monitorId;

   private String monitorName;

   private String monitorNodeId;

   private String systemName;

   private String clazzName;

   private String methodName;

   private String attributeName;

   private String attributeField;

   private String attributeValue;

   private Date createTime;

   private Date updateTime;

}

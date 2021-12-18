/**
  * Copyright 2021 bejson.com 
  */
package com.tao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Auto-generated: 2021-08-19 14:53:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class List {

    private String instanceId;
    private String ip;
    private int port;
    private int weight;
    private boolean healthy;
    private boolean enabled;
    private boolean ephemeral;
    private String clusterName;
    private String serviceName;
    @JSONField(serialize = false)
    private Metadata metadata;
    private long lastBeat;
    private boolean mockValid;
    private boolean marked;
    private String tenant;
    private String app;
    private String defaultKey;
    private int checkRT;
    private int failCount;
    private String datumKey;
    private int okcount;
    private int instanceHeartBeatInterval;
    private String instanceIdGenerator;
    private int instanceHeartBeatTimeOut;
    private int ipDeleteTimeout;
    public void setInstanceId(String instanceId) {
         this.instanceId = instanceId;
     }
     public String getInstanceId() {
         return instanceId;
     }

    public void setIp(String ip) {
         this.ip = ip;
     }
     public String getIp() {
         return ip;
     }

    public void setPort(int port) {
         this.port = port;
     }
     public int getPort() {
         return port;
     }

    public void setWeight(int weight) {
         this.weight = weight;
     }
     public int getWeight() {
         return weight;
     }

    public void setHealthy(boolean healthy) {
         this.healthy = healthy;
     }
     public boolean getHealthy() {
         return healthy;
     }

    public void setEnabled(boolean enabled) {
         this.enabled = enabled;
     }
     public boolean getEnabled() {
         return enabled;
     }

    public void setEphemeral(boolean ephemeral) {
         this.ephemeral = ephemeral;
     }
     public boolean getEphemeral() {
         return ephemeral;
     }

    public void setClusterName(String clusterName) {
         this.clusterName = clusterName;
     }
     public String getClusterName() {
         return clusterName;
     }

    public void setServiceName(String serviceName) {
         this.serviceName = serviceName;
     }
     public String getServiceName() {
         return serviceName;
     }

    public void setMetadata(Metadata metadata) {
         this.metadata = metadata;
     }
     public Metadata getMetadata() {
         return metadata;
     }

    public void setLastBeat(long lastBeat) {
         this.lastBeat = lastBeat;
     }
     public long getLastBeat() {
         return lastBeat;
     }

    public void setMockValid(boolean mockValid) {
         this.mockValid = mockValid;
     }
     public boolean getMockValid() {
         return mockValid;
     }

    public void setMarked(boolean marked) {
         this.marked = marked;
     }
     public boolean getMarked() {
         return marked;
     }

    public void setTenant(String tenant) {
         this.tenant = tenant;
     }
     public String getTenant() {
         return tenant;
     }

    public void setApp(String app) {
         this.app = app;
     }
     public String getApp() {
         return app;
     }

    public void setDefaultKey(String defaultKey) {
         this.defaultKey = defaultKey;
     }
     public String getDefaultKey() {
         return defaultKey;
     }

    public void setCheckRT(int checkRT) {
         this.checkRT = checkRT;
     }
     public int getCheckRT() {
         return checkRT;
     }

    public void setFailCount(int failCount) {
         this.failCount = failCount;
     }
     public int getFailCount() {
         return failCount;
     }

    public void setDatumKey(String datumKey) {
         this.datumKey = datumKey;
     }
     public String getDatumKey() {
         return datumKey;
     }

    public void setOkcount(int okcount) {
         this.okcount = okcount;
     }
     public int getOkcount() {
         return okcount;
     }

    public void setInstanceHeartBeatInterval(int instanceHeartBeatInterval) {
         this.instanceHeartBeatInterval = instanceHeartBeatInterval;
     }
     public int getInstanceHeartBeatInterval() {
         return instanceHeartBeatInterval;
     }

    public void setInstanceIdGenerator(String instanceIdGenerator) {
         this.instanceIdGenerator = instanceIdGenerator;
     }
     public String getInstanceIdGenerator() {
         return instanceIdGenerator;
     }

    public void setInstanceHeartBeatTimeOut(int instanceHeartBeatTimeOut) {
         this.instanceHeartBeatTimeOut = instanceHeartBeatTimeOut;
     }
     public int getInstanceHeartBeatTimeOut() {
         return instanceHeartBeatTimeOut;
     }

    public void setIpDeleteTimeout(int ipDeleteTimeout) {
         this.ipDeleteTimeout = ipDeleteTimeout;
     }
     public int getIpDeleteTimeout() {
         return ipDeleteTimeout;
     }

}
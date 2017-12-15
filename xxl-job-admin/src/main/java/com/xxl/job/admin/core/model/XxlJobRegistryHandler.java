package com.xxl.job.admin.core.model;

import java.util.Date;

public class XxlJobRegistryHandler {
    private int id;
    private String registryGroup;
    private String registryKey;
    private String handlerName;
    private String handlerParameters;
    private String handlerDescription;
    private String handlerMaintainOwner;
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistryGroup() {
        return registryGroup;
    }

    public void setRegistryGroup(String registryGroup) {
        this.registryGroup = registryGroup;
    }

    public String getRegistryKey() {
        return registryKey;
    }

    public void setRegistryKey(String registryKey) {
        this.registryKey = registryKey;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getHandlerParameters() {
        return handlerParameters;
    }

    public void setHandlerParameters(String handlerParameters) {
        this.handlerParameters = handlerParameters;
    }

    public String getHandlerDescription() {
        return handlerDescription;
    }

    public void setHandlerDescription(String handlerDescription) {
        this.handlerDescription = handlerDescription;
    }

    public String getHandlerMaintainOwner() {
        return handlerMaintainOwner;
    }

    public void setHandlerMaintainOwner(String handlerMaintainOwner) {
        this.handlerMaintainOwner = handlerMaintainOwner;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

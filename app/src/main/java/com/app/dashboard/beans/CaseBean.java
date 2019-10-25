package com.app.dashboard.beans;

import com.orm.SugarRecord;

/**
 * Created by khushalb on 23/10/19.
 */

public class CaseBean extends SugarRecord {
    private String componentName;
    private String name;
    private String caseId;
    private String shortId;
    private String caseStatus;
    private String startTime;
    private String keywords;
    private long elapsedTime;
    private boolean reRun;
    private int count;

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public boolean isReRun() {
        return reRun;
    }

    public void setReRun(boolean reRun) {
        this.reRun = reRun;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CaseBean{" +
                "componentName='" + componentName + '\'' +
                ", name='" + name + '\'' +
                ", caseId='" + caseId + '\'' +
                ", shortId='" + shortId + '\'' +
                ", caseStatus='" + caseStatus + '\'' +
                ", startTime='" + startTime + '\'' +
                ", keywords='" + keywords + '\'' +
                ", elapsedTime=" + elapsedTime +
                ", reRun=" + reRun +
                '}';
    }
}

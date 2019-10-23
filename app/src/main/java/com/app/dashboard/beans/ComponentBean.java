package com.app.dashboard.beans;

import com.orm.SugarRecord;

/**
 * Created by khushalb on 23/10/19.
 */

public class ComponentBean extends SugarRecord {
    private String name;
    private String functionalArea;
    private long elapsedTime;
    private int nbTests;
    private int nbFailures;
    private int nbErrors;
    private int nbSkipped;
    private int nbNotRun;
    private int nbReRun;
    private int nbPerfFailures;
    private String email;
    private long totalElapsedTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionalArea() {
        return functionalArea;
    }

    public void setFunctionalArea(String functionalArea) {
        this.functionalArea = functionalArea;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getNbTests() {
        return nbTests;
    }

    public void setNbTests(int nbTests) {
        this.nbTests = nbTests;
    }

    public int getNbFailures() {
        return nbFailures;
    }

    public void setNbFailures(int nbFailures) {
        this.nbFailures = nbFailures;
    }

    public int getNbErrors() {
        return nbErrors;
    }

    public void setNbErrors(int nbErrors) {
        this.nbErrors = nbErrors;
    }

    public int getNbSkipped() {
        return nbSkipped;
    }

    public void setNbSkipped(int nbSkipped) {
        this.nbSkipped = nbSkipped;
    }

    public int getNbNotRun() {
        return nbNotRun;
    }

    public void setNbNotRun(int nbNotRun) {
        this.nbNotRun = nbNotRun;
    }

    public int getNbReRun() {
        return nbReRun;
    }

    public void setNbReRun(int nbReRun) {
        this.nbReRun = nbReRun;
    }

    public int getNbPerfFailures() {
        return nbPerfFailures;
    }

    public void setNbPerfFailures(int nbPerfFailures) {
        this.nbPerfFailures = nbPerfFailures;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTotalElapsedTime() {
        return totalElapsedTime;
    }

    public void setTotalElapsedTime(long totalElapsedTime) {
        this.totalElapsedTime = totalElapsedTime;
    }

    @Override
    public String toString() {
        return "ComponentBean{" +
                "name='" + name + '\'' +
                ", functionalArea='" + functionalArea + '\'' +
                ", elapsedTime=" + elapsedTime +
                ", nbTests=" + nbTests +
                ", nbFailures=" + nbFailures +
                ", nbErrors=" + nbErrors +
                ", nbSkipped=" + nbSkipped +
                ", nbNotRun=" + nbNotRun +
                ", nbReRun=" + nbReRun +
                ", nbPerfFailures=" + nbPerfFailures +
                ", email='" + email + '\'' +
                ", totalElapsedTime=" + totalElapsedTime +
                '}';
    }
}

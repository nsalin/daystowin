package com.ind.dw.zephyr.core;

public class DefaultVersionDetector implements VersionDetector {
    @Override
    public synchronized String getVersion() {
        return "Unscheduled";
    }
}

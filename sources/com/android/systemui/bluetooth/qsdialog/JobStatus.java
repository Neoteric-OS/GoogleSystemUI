package com.android.systemui.bluetooth.qsdialog;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class JobStatus {
    public static final /* synthetic */ JobStatus[] $VALUES;
    public static final JobStatus CANCELLED;
    public static final JobStatus FINISHED;

    static {
        JobStatus jobStatus = new JobStatus("FINISHED", 0);
        FINISHED = jobStatus;
        JobStatus jobStatus2 = new JobStatus("CANCELLED", 1);
        CANCELLED = jobStatus2;
        JobStatus[] jobStatusArr = {jobStatus, jobStatus2};
        $VALUES = jobStatusArr;
        EnumEntriesKt.enumEntries(jobStatusArr);
    }

    public static JobStatus valueOf(String str) {
        return (JobStatus) Enum.valueOf(JobStatus.class, str);
    }

    public static JobStatus[] values() {
        return (JobStatus[]) $VALUES.clone();
    }
}

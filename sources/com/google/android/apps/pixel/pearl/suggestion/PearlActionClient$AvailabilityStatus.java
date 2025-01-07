package com.google.android.apps.pixel.pearl.suggestion;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PearlActionClient$AvailabilityStatus {
    public static final /* synthetic */ PearlActionClient$AvailabilityStatus[] $VALUES;
    public static final PearlActionClient$AvailabilityStatus AVAILABLE;
    public static final PearlActionClient$AvailabilityStatus UNAVAILABLE;

    static {
        PearlActionClient$AvailabilityStatus pearlActionClient$AvailabilityStatus = new PearlActionClient$AvailabilityStatus("UNAVAILABLE", 0);
        UNAVAILABLE = pearlActionClient$AvailabilityStatus;
        PearlActionClient$AvailabilityStatus pearlActionClient$AvailabilityStatus2 = new PearlActionClient$AvailabilityStatus("AVAILABLE", 1);
        AVAILABLE = pearlActionClient$AvailabilityStatus2;
        PearlActionClient$AvailabilityStatus[] pearlActionClient$AvailabilityStatusArr = {pearlActionClient$AvailabilityStatus, pearlActionClient$AvailabilityStatus2};
        $VALUES = pearlActionClient$AvailabilityStatusArr;
        EnumEntriesKt.enumEntries(pearlActionClient$AvailabilityStatusArr);
    }

    public static PearlActionClient$AvailabilityStatus valueOf(String str) {
        return (PearlActionClient$AvailabilityStatus) Enum.valueOf(PearlActionClient$AvailabilityStatus.class, str);
    }

    public static PearlActionClient$AvailabilityStatus[] values() {
        return (PearlActionClient$AvailabilityStatus[]) $VALUES.clone();
    }
}

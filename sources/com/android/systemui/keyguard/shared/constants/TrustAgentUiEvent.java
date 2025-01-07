package com.android.systemui.keyguard.shared.constants;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrustAgentUiEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ TrustAgentUiEvent[] $VALUES;
    public static final TrustAgentUiEvent TRUST_AGENT_NEWLY_UNLOCKED;
    private final int metricId = 1361;

    static {
        TrustAgentUiEvent trustAgentUiEvent = new TrustAgentUiEvent();
        TRUST_AGENT_NEWLY_UNLOCKED = trustAgentUiEvent;
        TrustAgentUiEvent[] trustAgentUiEventArr = {trustAgentUiEvent};
        $VALUES = trustAgentUiEventArr;
        EnumEntriesKt.enumEntries(trustAgentUiEventArr);
    }

    public static TrustAgentUiEvent valueOf(String str) {
        return (TrustAgentUiEvent) Enum.valueOf(TrustAgentUiEvent.class, str);
    }

    public static TrustAgentUiEvent[] values() {
        return (TrustAgentUiEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this.metricId;
    }
}

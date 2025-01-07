package com.android.systemui.accessibility.data.repository;

import android.view.accessibility.AccessibilityManager;
import com.android.app.tracing.FlowTracing;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessibilityRepositoryImpl {
    public final Flow isEnabled;
    public final Flow isTouchExplorationEnabled;

    public AccessibilityRepositoryImpl(AccessibilityManager accessibilityManager) {
        FlowTracing flowTracing = FlowTracing.INSTANCE;
        this.isTouchExplorationEnabled = FlowKt.distinctUntilChanged(FlowTracing.tracedConflatedCallbackFlow("AccessibilityRepository", new AccessibilityRepositoryImpl$isTouchExplorationEnabled$1(accessibilityManager, null)));
        this.isEnabled = FlowKt.distinctUntilChanged(FlowTracing.tracedConflatedCallbackFlow("AccessibilityRepository", new AccessibilityRepositoryImpl$isEnabled$1(accessibilityManager, null)));
    }
}

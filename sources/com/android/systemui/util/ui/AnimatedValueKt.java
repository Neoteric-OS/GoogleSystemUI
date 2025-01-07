package com.android.systemui.util.ui;

import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AnimatedValueKt {
    public static final ChannelFlowTransformLatest toAnimatedValueFlow(Flow flow) {
        return FlowKt.transformLatest(flow, new AnimatedValueKt$toAnimatedValueFlow$1(3, null));
    }
}

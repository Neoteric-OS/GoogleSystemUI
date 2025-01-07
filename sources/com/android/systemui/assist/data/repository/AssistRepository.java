package com.android.systemui.assist.data.repository;

import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AssistRepository {
    public final SharedFlowImpl _latestInvocationType;
    public final ReadonlySharedFlow latestInvocationType;

    public AssistRepository() {
        SharedFlowImpl MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2);
        this._latestInvocationType = MutableSharedFlow$default;
        this.latestInvocationType = new ReadonlySharedFlow(MutableSharedFlow$default);
    }
}

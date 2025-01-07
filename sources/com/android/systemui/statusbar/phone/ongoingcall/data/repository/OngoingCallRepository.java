package com.android.systemui.statusbar.phone.ongoingcall.data.repository;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.phone.ongoingcall.shared.model.OngoingCallModel;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OngoingCallRepository {
    public final StateFlowImpl _ongoingCallState;
    public final LogBuffer logger;
    public final ReadonlyStateFlow ongoingCallState;

    public OngoingCallRepository(LogBuffer logBuffer) {
        this.logger = logBuffer;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(OngoingCallModel.NoCall.INSTANCE);
        this._ongoingCallState = MutableStateFlow;
        this.ongoingCallState = new ReadonlyStateFlow(MutableStateFlow);
    }

    public final void setOngoingCallState(OngoingCallModel ongoingCallModel) {
        LogLevel logLevel = LogLevel.DEBUG;
        OngoingCallRepository$setOngoingCallState$2 ongoingCallRepository$setOngoingCallState$2 = new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.data.repository.OngoingCallRepository$setOngoingCallState$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Repo#setOngoingCallState: ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = this.logger;
        LogMessage obtain = logBuffer.obtain("OngoingCall", logLevel, ongoingCallRepository$setOngoingCallState$2, null);
        ((LogMessageImpl) obtain).str1 = Reflection.getOrCreateKotlinClass(ongoingCallModel.getClass()).getSimpleName();
        logBuffer.commit(obtain);
        StateFlowImpl stateFlowImpl = this._ongoingCallState;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, ongoingCallModel);
    }
}

package com.android.systemui.display.data.repository;

import android.os.Trace;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisplayRepositoryImpl$pendingDisplay$1$1 {
    public final /* synthetic */ int $id;
    public final /* synthetic */ DisplayRepositoryImpl this$0;

    public DisplayRepositoryImpl$pendingDisplay$1$1(int i, DisplayRepositoryImpl displayRepositoryImpl) {
        this.$id = i;
        this.this$0 = displayRepositoryImpl;
    }

    public final void ignore() {
        StringBuilder sb = new StringBuilder("DisplayRepository#ignore(");
        int i = this.$id;
        String m = PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, i, ")");
        DisplayRepositoryImpl displayRepositoryImpl = this.this$0;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice(m);
        }
        try {
            StateFlowImpl stateFlowImpl = displayRepositoryImpl._ignoredDisplayIds;
            stateFlowImpl.updateState(null, SetsKt.plus((Set) stateFlowImpl.getValue(), new Integer(i)));
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}

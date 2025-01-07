package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import android.graphics.Rect;
import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1 implements FlowCollector {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NotificationIconContainer $receiver$inlined;

    public /* synthetic */ NotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1(NotificationIconContainer notificationIconContainer, int i) {
        this.$r8$classId = i;
        this.$receiver$inlined = notificationIconContainer;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        boolean isEnabled;
        switch (this.$r8$classId) {
            case 0:
                isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("NIC#bindAnimationsEnabled");
                }
                try {
                    this.$receiver$inlined.setAnimationsEnabled(((Boolean) obj).booleanValue());
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                    return Unit.INSTANCE;
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
            default:
                isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("NIC#isolatedIconLocation");
                }
                try {
                    NotificationIconContainer notificationIconContainer = this.$receiver$inlined;
                    notificationIconContainer.mIsolatedIconLocation = (Rect) obj;
                    notificationIconContainer.resetViewStates();
                    notificationIconContainer.calculateIconXTranslations();
                    notificationIconContainer.applyIconStates();
                    return Unit.INSTANCE;
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
        }
    }
}

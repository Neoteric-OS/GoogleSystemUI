package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import android.os.Trace;
import android.widget.FrameLayout;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconInfo;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.util.ui.AnimatedValue;
import kotlin.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$invokeSuspend$$inlined$collectTracingEach$1 implements FlowCollector {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $view$inlined;
    public final /* synthetic */ Object $viewStore$inlined;

    public /* synthetic */ NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$invokeSuspend$$inlined$collectTracingEach$1(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.$view$inlined = obj;
        this.$viewStore$inlined = obj2;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        Object obj2;
        switch (this.$r8$classId) {
            case 0:
                boolean isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("NIC#showIconIsolated");
                }
                try {
                    AnimatedValue animatedValue = (AnimatedValue) obj;
                    if (animatedValue instanceof AnimatedValue.Animating) {
                        obj2 = ((AnimatedValue.Animating) animatedValue).value;
                    } else {
                        if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        obj2 = ((AnimatedValue.NotAnimating) animatedValue).value;
                    }
                    NotificationIconInfo notificationIconInfo = (NotificationIconInfo) obj2;
                    StatusBarIconView iconView = notificationIconInfo != null ? ((NotificationIconContainerViewBinder.IconViewStore) this.$viewStore$inlined).iconView(notificationIconInfo.notifKey) : null;
                    boolean z = animatedValue instanceof AnimatedValue.Animating;
                    NotificationIconContainer notificationIconContainer = (NotificationIconContainer) this.$view$inlined;
                    if (z) {
                        NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1 notificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1 = new NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1(animatedValue);
                        notificationIconContainer.mIsolatedIconForAnimation = iconView != null ? iconView : notificationIconContainer.mIsolatedIcon;
                        notificationIconContainer.mIsolatedIconAnimationEndRunnable = notificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1;
                        notificationIconContainer.mIsolatedIcon = iconView;
                        notificationIconContainer.resetViewStates();
                        notificationIconContainer.calculateIconXTranslations();
                        notificationIconContainer.applyIconStates();
                    } else {
                        notificationIconContainer.mIsolatedIcon = iconView;
                        notificationIconContainer.resetViewStates();
                        notificationIconContainer.calculateIconXTranslations();
                        notificationIconContainer.applyIconStates();
                    }
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                    throw th;
                }
            default:
                StatusBarIconView statusBarIconView = (StatusBarIconView) this.$viewStore$inlined;
                boolean isEnabled2 = Trace.isEnabled();
                if (isEnabled2) {
                    TraceUtilsKt.beginSlice((String) ((Lazy) this.$view$inlined).getValue());
                }
                try {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) obj;
                    if (!Intrinsics.areEqual(layoutParams, statusBarIconView.getLayoutParams())) {
                        statusBarIconView.setLayoutParams(layoutParams);
                    }
                    return Unit.INSTANCE;
                } finally {
                    if (isEnabled2) {
                        TraceUtilsKt.endSlice();
                    }
                }
        }
    }
}

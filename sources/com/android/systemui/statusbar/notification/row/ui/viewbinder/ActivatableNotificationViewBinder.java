package com.android.systemui.statusbar.notification.row.ui.viewbinder;

import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.ActivatableNotificationViewModel;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActivatableNotificationViewBinder {
    public static final ActivatableNotificationViewBinder INSTANCE = new ActivatableNotificationViewBinder();

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$registerListenersWhileAttached(com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder r4, com.android.systemui.statusbar.notification.row.ActivatableNotificationView r5, com.android.systemui.statusbar.notification.row.ui.viewbinder.TouchHandler r6, kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r4.getClass()
            boolean r0 = r7 instanceof com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder$registerListenersWhileAttached$1
            if (r0 == 0) goto L16
            r0 = r7
            com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder$registerListenersWhileAttached$1 r0 = (com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder$registerListenersWhileAttached$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder$registerListenersWhileAttached$1 r0 = new com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder$registerListenersWhileAttached$1
            r0.<init>(r4, r7)
        L1b:
            java.lang.Object r4 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r7 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r7 = r0.label
            r1 = 1
            if (r7 == 0) goto L3d
            if (r7 == r1) goto L2e
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2e:
            java.lang.Object r5 = r0.L$0
            com.android.systemui.statusbar.notification.row.ActivatableNotificationView r5 = (com.android.systemui.statusbar.notification.row.ActivatableNotificationView) r5
            kotlin.ResultKt.throwOnFailure(r4)     // Catch: java.lang.Throwable -> L3b
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L3b
            r4.<init>()     // Catch: java.lang.Throwable -> L3b
            throw r4     // Catch: java.lang.Throwable -> L3b
        L3b:
            r4 = move-exception
            goto L4d
        L3d:
            kotlin.ResultKt.throwOnFailure(r4)
            r5.setOnTouchListener(r6)     // Catch: java.lang.Throwable -> L3b
            r5.mTouchHandler = r6     // Catch: java.lang.Throwable -> L3b
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L3b
            r0.label = r1     // Catch: java.lang.Throwable -> L3b
            kotlinx.coroutines.DelayKt.awaitCancellation(r0)     // Catch: java.lang.Throwable -> L3b
            return
        L4d:
            r6 = 0
            r5.mTouchHandler = r6
            r5.setOnTouchListener(r6)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder.access$registerListenersWhileAttached(com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder, com.android.systemui.statusbar.notification.row.ActivatableNotificationView, com.android.systemui.statusbar.notification.row.ui.viewbinder.TouchHandler, kotlin.coroutines.jvm.internal.ContinuationImpl):void");
    }

    public static void bind(ActivatableNotificationViewModel activatableNotificationViewModel, ActivatableNotificationView activatableNotificationView, FalsingManager falsingManager) {
        RepeatWhenAttachedKt.repeatWhenAttached(activatableNotificationView, EmptyCoroutineContext.INSTANCE, new ActivatableNotificationViewBinder$bind$1(activatableNotificationView, new TouchHandler(activatableNotificationView, falsingManager), activatableNotificationViewModel, null));
    }
}

package com.android.systemui.animation;

import android.view.View;
import android.window.SurfaceSyncGroup;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ViewRootSync {
    public static void synchronizeNextDraw(View view, View view2, final Function0 function0) {
        if (!view.isAttachedToWindow() || view.getViewRootImpl() == null || !view2.isAttachedToWindow() || view2.getViewRootImpl() == null || Intrinsics.areEqual(view.getViewRootImpl(), view2.getViewRootImpl())) {
            function0.invoke();
            return;
        }
        SurfaceSyncGroup surfaceSyncGroup = new SurfaceSyncGroup("SysUIAnimation");
        surfaceSyncGroup.addSyncCompleteCallback(view.getContext().getMainExecutor(), new Runnable(function0) { // from class: com.android.systemui.animation.ViewRootSync$synchronizeNextDraw$1
            public final /* synthetic */ Lambda $then;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$then = (Lambda) function0;
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
            @Override // java.lang.Runnable
            public final void run() {
                this.$then.invoke();
            }
        });
        surfaceSyncGroup.add(view.getRootSurfaceControl(), (Runnable) null);
        surfaceSyncGroup.add(view2.getRootSurfaceControl(), (Runnable) null);
        surfaceSyncGroup.markSyncReady();
    }
}

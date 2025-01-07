package com.android.systemui.media.controls.ui.view;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.media.controls.ui.controller.MediaHostStatesManager;
import com.android.systemui.media.controls.ui.controller.MediaViewController;
import com.android.systemui.media.controls.ui.view.MediaHost;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaHost$init$3 extends Lambda implements Function0 {
    final /* synthetic */ int $location;
    final /* synthetic */ MediaHost this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaHost$init$3(MediaHost mediaHost, int i) {
        super(0);
        this.this$0 = mediaHost;
        this.$location = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        MediaHost mediaHost = this.this$0;
        MediaHostStatesManager mediaHostStatesManager = mediaHost.mediaHostStatesManager;
        int i = this.$location;
        MediaHost.MediaHostStateHolder mediaHostStateHolder = mediaHost.state;
        mediaHostStatesManager.getClass();
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("MediaHostStatesManager#updateHostState");
        }
        try {
            if (!mediaHostStateHolder.equals((MediaHostState) mediaHostStatesManager.mediaHostStates.get(Integer.valueOf(i)))) {
                MediaHost.MediaHostStateHolder copy = mediaHostStateHolder.copy();
                mediaHostStatesManager.mediaHostStates.put(Integer.valueOf(i), copy);
                mediaHostStatesManager.updateCarouselDimensions(i, mediaHostStateHolder);
                Iterator it = mediaHostStatesManager.controllers.iterator();
                while (it.hasNext()) {
                    ((MediaViewController) it.next()).stateCallback.onHostStateChanged(i, copy);
                }
                Iterator it2 = mediaHostStatesManager.callbacks.iterator();
                while (it2.hasNext()) {
                    ((MediaHostStatesManager.Callback) it2.next()).onHostStateChanged(i, copy);
                }
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
    }
}

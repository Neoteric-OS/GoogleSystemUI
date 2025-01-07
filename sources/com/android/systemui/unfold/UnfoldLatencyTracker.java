package com.android.systemui.unfold;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.devicestate.DeviceStateManager;
import android.os.Trace;
import android.provider.Settings;
import com.android.internal.util.LatencyTracker;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldLatencyTracker implements ScreenLifecycle.Observer, UnfoldTransitionProgressProvider.TransitionProgressListener {
    public final ContentResolver contentResolver;
    public final Context context;
    public final DeviceStateManager deviceStateManager;
    public final FoldStateListener foldStateListener;
    public Boolean folded;
    public Boolean isTransitionEnabled;
    public final LatencyTracker latencyTracker;
    public final ScreenLifecycle screenLifecycle;
    public final Optional transitionProgressProvider;
    public final Executor uiBgExecutor;
    public boolean unfoldInProgress;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FoldStateListener extends DeviceStateManager.FoldStateListener {
    }

    public UnfoldLatencyTracker(LatencyTracker latencyTracker, DeviceStateManager deviceStateManager, Optional optional, Executor executor, Context context, ContentResolver contentResolver, ScreenLifecycle screenLifecycle) {
        this.latencyTracker = latencyTracker;
        this.deviceStateManager = deviceStateManager;
        this.transitionProgressProvider = optional;
        this.uiBgExecutor = executor;
        this.context = context;
        this.contentResolver = contentResolver;
        this.screenLifecycle = screenLifecycle;
        this.foldStateListener = new FoldStateListener(context, new Consumer() { // from class: com.android.systemui.unfold.UnfoldLatencyTracker.FoldStateListener.1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Float floatOrNull;
                Boolean bool = (Boolean) obj;
                UnfoldLatencyTracker unfoldLatencyTracker = UnfoldLatencyTracker.this;
                Intrinsics.checkNotNull(bool);
                boolean booleanValue = bool.booleanValue();
                Boolean bool2 = unfoldLatencyTracker.folded;
                if (Intrinsics.areEqual(bool2, bool)) {
                    return;
                }
                unfoldLatencyTracker.folded = bool;
                if (bool2 == null || booleanValue) {
                    return;
                }
                boolean z = false;
                if (!unfoldLatencyTracker.unfoldInProgress) {
                    unfoldLatencyTracker.unfoldInProgress = true;
                    unfoldLatencyTracker.latencyTracker.onActionStart(13);
                    Trace.asyncTraceBegin(4096L, "Switch displays during unfold", 0);
                }
                if (unfoldLatencyTracker.transitionProgressProvider.isPresent()) {
                    String string = Settings.Global.getString(unfoldLatencyTracker.contentResolver, "animator_duration_scale");
                    if (((string == null || (floatOrNull = StringsKt__StringNumberConversionsJVMKt.toFloatOrNull(string)) == null) ? 1.0f : floatOrNull.floatValue()) != 0.0f) {
                        z = true;
                    }
                }
                unfoldLatencyTracker.isTransitionEnabled = Boolean.valueOf(z);
            }
        });
    }

    @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
    public final void onScreenTurnedOn() {
        Boolean bool = this.folded;
        Boolean bool2 = Boolean.FALSE;
        if (Intrinsics.areEqual(bool, bool2) && Intrinsics.areEqual(this.isTransitionEnabled, bool2) && this.unfoldInProgress) {
            this.unfoldInProgress = false;
            this.latencyTracker.onActionEnd(13);
            Trace.endAsyncSection("Switch displays during unfold", 0);
        }
    }

    @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
    public final void onTransitionStarted() {
        if (Intrinsics.areEqual(this.folded, Boolean.FALSE) && Intrinsics.areEqual(this.isTransitionEnabled, Boolean.TRUE) && this.unfoldInProgress) {
            this.unfoldInProgress = false;
            this.latencyTracker.onActionEnd(13);
            Trace.endAsyncSection("Switch displays during unfold", 0);
        }
    }
}

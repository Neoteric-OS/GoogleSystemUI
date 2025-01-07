package com.android.systemui.touchpad.tutorial.ui.composable;

import android.R;
import android.content.res.Resources;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import com.android.systemui.touchpad.tutorial.ui.gesture.TouchpadGestureMonitor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DistanceBasedGestureMonitorProvider implements GestureMonitorProvider {
    public final Lambda monitorFactory;

    /* JADX WARN: Multi-variable type inference failed */
    public DistanceBasedGestureMonitorProvider(Function2 function2) {
        this.monitorFactory = (Lambda) function2;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.jvm.functions.Function2, kotlin.jvm.internal.Lambda] */
    @Override // com.android.systemui.touchpad.tutorial.ui.composable.GestureMonitorProvider
    public final TouchpadGestureMonitor rememberGestureMonitor(Resources resources, Function1 function1, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-1913780396);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.text_size_caption_material);
        composerImpl.startReplaceGroup(80554744);
        boolean changed = composerImpl.changed(dimensionPixelSize);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = (TouchpadGestureMonitor) this.monitorFactory.invoke(Integer.valueOf(dimensionPixelSize), function1);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        TouchpadGestureMonitor touchpadGestureMonitor = (TouchpadGestureMonitor) rememberedValue;
        composerImpl.end(false);
        composerImpl.end(false);
        return touchpadGestureMonitor;
    }
}

package com.android.systemui.touchpad.tutorial.ui.composable;

import android.R;
import android.content.res.Resources;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import com.android.systemui.touchpad.tutorial.ui.gesture.RecentAppsGestureMonitor;
import com.android.systemui.touchpad.tutorial.ui.gesture.TouchpadGestureMonitor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecentAppsGestureTutorialScreenKt$RecentAppsGestureTutorialScreen$gestureMonitorProvider$1 implements GestureMonitorProvider {
    @Override // com.android.systemui.touchpad.tutorial.ui.composable.GestureMonitorProvider
    public final TouchpadGestureMonitor rememberGestureMonitor(Resources resources, Function1 function1, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(1608348887);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.text_size_caption_material);
        float dimension = resources.getDimension(com.android.wm.shell.R.dimen.touchpad_recent_apps_gesture_velocity_threshold);
        composerImpl.startReplaceGroup(-929343634);
        boolean changed = composerImpl.changed(dimensionPixelSize) | composerImpl.changed(dimension);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new RecentAppsGestureMonitor(dimensionPixelSize, function1, dimension);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        RecentAppsGestureMonitor recentAppsGestureMonitor = (RecentAppsGestureMonitor) rememberedValue;
        composerImpl.end(false);
        composerImpl.end(false);
        return recentAppsGestureMonitor;
    }
}

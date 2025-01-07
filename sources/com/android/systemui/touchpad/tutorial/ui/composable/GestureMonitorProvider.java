package com.android.systemui.touchpad.tutorial.ui.composable;

import android.content.res.Resources;
import androidx.compose.runtime.Composer;
import com.android.systemui.touchpad.tutorial.ui.gesture.TouchpadGestureMonitor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface GestureMonitorProvider {
    TouchpadGestureMonitor rememberGestureMonitor(Resources resources, Function1 function1, Composer composer);
}

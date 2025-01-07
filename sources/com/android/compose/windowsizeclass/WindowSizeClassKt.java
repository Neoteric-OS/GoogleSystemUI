package com.android.compose.windowsizeclass;

import androidx.compose.runtime.StaticProvidableCompositionLocal;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class WindowSizeClassKt {
    public static final StaticProvidableCompositionLocal LocalWindowSizeClass = new StaticProvidableCompositionLocal(new Function0() { // from class: com.android.compose.windowsizeclass.WindowSizeClassKt$LocalWindowSizeClass$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            throw new IllegalStateException("No WindowSizeClass configured. Make sure to use LocalWindowSizeClass in a Composable surrounded by a PlatformTheme {}.");
        }
    });
}

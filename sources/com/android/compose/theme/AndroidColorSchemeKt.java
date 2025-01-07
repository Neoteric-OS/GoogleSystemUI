package com.android.compose.theme;

import androidx.compose.runtime.StaticProvidableCompositionLocal;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidColorSchemeKt {
    public static final StaticProvidableCompositionLocal LocalAndroidColorScheme = new StaticProvidableCompositionLocal(new Function0() { // from class: com.android.compose.theme.AndroidColorSchemeKt$LocalAndroidColorScheme$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            throw new IllegalStateException("No AndroidColorScheme configured. Make sure to use LocalAndroidColorScheme in a Composable surrounded by a PlatformTheme {}.");
        }
    });
}

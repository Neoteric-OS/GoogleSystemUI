package com.android.systemui.keyboard.shortcut.ui.composable;

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass;
import androidx.compose.material3.windowsizeclass.WindowSizeClass;
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import com.android.compose.windowsizeclass.WindowSizeClassKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ShortcutHelperUtilsKt {
    public static final boolean hasCompactWindowSize(Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(392261908);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        StaticProvidableCompositionLocal staticProvidableCompositionLocal = WindowSizeClassKt.LocalWindowSizeClass;
        boolean z = WindowWidthSizeClass.m251equalsimpl0(((WindowSizeClass) composerImpl.consume(staticProvidableCompositionLocal)).widthSizeClass, 0) || WindowHeightSizeClass.m248equalsimpl0(((WindowSizeClass) composerImpl.consume(staticProvidableCompositionLocal)).heightSizeClass, 0);
        composerImpl.end(false);
        return z;
    }
}

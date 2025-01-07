package com.android.compose;

import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PlatformSliderDefaults {
    public static final float DefaultPlatformSliderDraggingCornerRadius = 8;

    public static PlatformSliderColors defaultPlatformSliderColors(Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(481782045);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        PlatformSliderColors platformSliderColors = new PlatformSliderColors(MaterialTheme.getColorScheme(composerImpl).secondaryContainer, MaterialTheme.getColorScheme(composerImpl).primary, MaterialTheme.getColorScheme(composerImpl).onPrimary, MaterialTheme.getColorScheme(composerImpl).onPrimary, MaterialTheme.getColorScheme(composerImpl).onSecondaryContainer, MaterialTheme.getColorScheme(composerImpl).surfaceContainerHighest, MaterialTheme.getColorScheme(composerImpl).surfaceContainerHighest, MaterialTheme.getColorScheme(composerImpl).outline, MaterialTheme.getColorScheme(composerImpl).onSurfaceVariant);
        composerImpl.end(false);
        return platformSliderColors;
    }
}

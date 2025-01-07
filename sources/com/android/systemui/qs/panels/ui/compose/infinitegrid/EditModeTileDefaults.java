package com.android.systemui.qs.panels.ui.compose.infinitegrid;

import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class EditModeTileDefaults {
    public static final float EditGridHeaderHeight = 60;
    public static final float SelectedBorderWidth = 2;
    public static final float CurrentTilesGridPadding = 8;

    public static TileColors editTileColors(Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(967424235);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        TileColors tileColors = new TileColors(MaterialTheme.getColorScheme(composerImpl).surfaceVariant, MaterialTheme.getColorScheme(composerImpl).surfaceVariant, MaterialTheme.getColorScheme(composerImpl).onSurfaceVariant, MaterialTheme.getColorScheme(composerImpl).onSurfaceVariant, MaterialTheme.getColorScheme(composerImpl).onSurfaceVariant);
        composerImpl.end(false);
        return tileColors;
    }
}

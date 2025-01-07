package com.android.systemui.qs.panels.ui.compose.infinitegrid;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.unit.Dp;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TileDefaults {
    public static final float ActiveIconCornerRadius = 16;
    public static final float ActiveTileCornerRadius = 24;

    /* renamed from: animateShape-rAjV9yQ, reason: not valid java name */
    public static RoundedCornerShape m845animateShaperAjV9yQ(int i, float f, String str, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-1905564501);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        if (i != 2) {
            f = CommonTileDefaults.InactiveCornerRadius;
        }
        RoundedCornerShape m148RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(((Dp) AnimateAsStateKt.m8animateDpAsStateAjpBEmI(f, null, str, composerImpl, 384, 10).getValue()).value);
        composerImpl.end(false);
        return m148RoundedCornerShape0680j_4;
    }
}

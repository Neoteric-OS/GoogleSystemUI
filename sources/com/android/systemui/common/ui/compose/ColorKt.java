package com.android.systemui.common.ui.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import com.android.systemui.common.shared.model.Color$Attribute;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ColorKt {
    public static final long toColor(Color$Attribute color$Attribute, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        if (color$Attribute instanceof Color$Attribute) {
            return com.android.compose.theme.ColorKt.colorAttr(color$Attribute.attribute, composer);
        }
        throw new NoWhenBranchMatchedException();
    }
}

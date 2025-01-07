package com.android.compose.theme;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ColorKt {
    public static final long colorAttr(int i, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        TypedArray obtainStyledAttributes = ((Context) ((ComposerImpl) composer).consume(AndroidCompositionLocals_androidKt.LocalContext)).obtainStyledAttributes(new int[]{i});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return androidx.compose.ui.graphics.ColorKt.Color(color);
    }
}

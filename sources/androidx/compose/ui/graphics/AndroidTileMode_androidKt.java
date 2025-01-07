package androidx.compose.ui.graphics;

import android.graphics.Shader;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidTileMode_androidKt {
    /* renamed from: toAndroidTileMode-0vamqd0, reason: not valid java name */
    public static final Shader.TileMode m356toAndroidTileMode0vamqd0() {
        return TileMode.m396equalsimpl0(0) ? Shader.TileMode.CLAMP : TileMode.m396equalsimpl0(1) ? Shader.TileMode.REPEAT : TileMode.m396equalsimpl0(2) ? Shader.TileMode.MIRROR : TileMode.m396equalsimpl0(3) ? Shader.TileMode.DECAL : Shader.TileMode.CLAMP;
    }
}

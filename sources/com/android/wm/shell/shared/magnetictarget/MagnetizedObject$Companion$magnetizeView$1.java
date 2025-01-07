package com.android.wm.shell.shared.magnetictarget;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MagnetizedObject$Companion$magnetizeView$1 extends MagnetizedObject {
    @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
    public final float getHeight(Object obj) {
        return ((View) obj).getHeight();
    }

    @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
    public final void getLocationOnScreen(Object obj, int[] iArr) {
        ((View) obj).getLocationOnScreen(iArr);
    }

    @Override // com.android.wm.shell.shared.magnetictarget.MagnetizedObject
    public final float getWidth(Object obj) {
        return ((View) obj).getWidth();
    }
}

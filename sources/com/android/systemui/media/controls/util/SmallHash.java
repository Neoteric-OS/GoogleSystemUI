package com.android.systemui.media.controls.util;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SmallHash {
    public static int hash(int i) {
        return Math.abs(Math.floorMod(i, 8192));
    }
}

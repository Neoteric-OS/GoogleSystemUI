package com.android.systemui.statusbar.phone;

import java.util.ArrayList;
import java.util.Collection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SysuiDarkIconDispatcher$DarkChange {
    public static final SysuiDarkIconDispatcher$DarkChange EMPTY = new SysuiDarkIconDispatcher$DarkChange(-1, new ArrayList());
    public final Collection areas;
    public final int tint;

    public SysuiDarkIconDispatcher$DarkChange(int i, Collection collection) {
        this.areas = collection;
        this.tint = i;
    }
}

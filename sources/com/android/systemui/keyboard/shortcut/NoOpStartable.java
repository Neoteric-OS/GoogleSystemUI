package com.android.systemui.keyboard.shortcut;

import com.android.systemui.CoreStartable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoOpStartable implements CoreStartable {
    public static final NoOpStartable INSTANCE = new NoOpStartable();

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}

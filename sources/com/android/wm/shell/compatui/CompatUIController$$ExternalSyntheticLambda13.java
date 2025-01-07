package com.android.wm.shell.compatui;

import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CompatUIController$$ExternalSyntheticLambda13 implements Predicate {
    public final /* synthetic */ int f$0;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((CompatUIWindowManagerAbstract) obj).mDisplayId == this.f$0;
    }
}

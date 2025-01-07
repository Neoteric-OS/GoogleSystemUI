package com.android.wm.shell.compatui;

import android.view.accessibility.AccessibilityManager;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CompatUIController$$ExternalSyntheticLambda7 implements Function {
    public final /* synthetic */ AccessibilityManager f$0;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Integer.valueOf(this.f$0.getRecommendedTimeoutMillis(5000, ((Integer) obj).intValue()));
    }
}

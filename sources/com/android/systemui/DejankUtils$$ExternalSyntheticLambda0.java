package com.android.systemui;

import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DejankUtils$$ExternalSyntheticLambda0 implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        int i = 0;
        while (true) {
            ArrayList arrayList = DejankUtils.sPendingRunnables;
            if (i >= arrayList.size()) {
                arrayList.clear();
                return;
            } else {
                DejankUtils.sHandler.post((Runnable) arrayList.get(i));
                i++;
            }
        }
    }
}

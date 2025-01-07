package com.android.systemui.tuner;

import com.android.systemui.fragments.FragmentService;
import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TunerActivity$$ExternalSyntheticLambda0 implements Consumer {
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = TunerActivity.$r8$clinit;
        Iterator it = ((FragmentService) obj).mHosts.values().iterator();
        while (it.hasNext()) {
            ((FragmentService.FragmentHostState) it.next()).mFragmentHostManager.mFragments.dispatchDestroy();
        }
    }
}

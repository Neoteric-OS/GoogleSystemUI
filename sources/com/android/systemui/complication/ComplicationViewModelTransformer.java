package com.android.systemui.complication;

import com.android.systemui.complication.ComplicationId;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$RichOngoingViewModelComponentFactory;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComplicationViewModelTransformer {
    public final ComplicationId.Factory mComplicationIdFactory = new ComplicationId.Factory();
    public final HashMap mComplicationIdMapping = new HashMap();
    public final DaggerSysUIGoogleGlobalRootComponent$RichOngoingViewModelComponentFactory mViewModelComponentFactory;

    public ComplicationViewModelTransformer(DaggerSysUIGoogleGlobalRootComponent$RichOngoingViewModelComponentFactory daggerSysUIGoogleGlobalRootComponent$RichOngoingViewModelComponentFactory) {
        this.mViewModelComponentFactory = daggerSysUIGoogleGlobalRootComponent$RichOngoingViewModelComponentFactory;
    }
}

package com.android.systemui.communal.ui.binder;

import android.widget.TextView;
import com.android.systemui.communal.ui.viewmodel.CommunalTutorialIndicatorViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CommunalTutorialIndicatorViewBinder {
    public static RepeatWhenAttachedKt$repeatWhenAttached$1 bind(TextView textView, CommunalTutorialIndicatorViewModel communalTutorialIndicatorViewModel, boolean z) {
        CommunalTutorialIndicatorViewBinder$bind$disposableHandle$1 communalTutorialIndicatorViewBinder$bind$disposableHandle$1 = new CommunalTutorialIndicatorViewBinder$bind$disposableHandle$1(communalTutorialIndicatorViewModel, z, textView, null);
        CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
        return RepeatWhenAttachedKt.repeatWhenAttached(textView, EmptyCoroutineContext.INSTANCE, communalTutorialIndicatorViewBinder$bind$disposableHandle$1);
    }
}

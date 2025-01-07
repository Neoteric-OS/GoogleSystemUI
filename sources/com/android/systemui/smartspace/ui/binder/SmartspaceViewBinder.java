package com.android.systemui.smartspace.ui.binder;

import android.view.View;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.smartspace.ui.viewmodel.SmartspaceViewModel;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SmartspaceViewBinder {
    /* JADX WARN: Multi-variable type inference failed */
    public static void bind(BcSmartspaceDataPlugin.SmartspaceView smartspaceView, SmartspaceViewModel smartspaceViewModel) {
        RepeatWhenAttachedKt.repeatWhenAttached((View) smartspaceView, EmptyCoroutineContext.INSTANCE, new SmartspaceViewBinder$bind$1(smartspaceViewModel, smartspaceView, null));
    }
}

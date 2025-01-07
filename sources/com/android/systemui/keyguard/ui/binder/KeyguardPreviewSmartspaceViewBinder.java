package com.android.systemui.keyguard.ui.binder;

import android.content.Context;
import android.view.View;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardPreviewSmartspaceViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardPreviewSmartspaceViewBinder {
    public static final void bind(Context context, View view, KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel, boolean z) {
        RepeatWhenAttachedKt.repeatWhenAttached(view, EmptyCoroutineContext.INSTANCE, new KeyguardPreviewSmartspaceViewBinder$bind$1(keyguardPreviewSmartspaceViewModel, z, context, view, null));
    }
}

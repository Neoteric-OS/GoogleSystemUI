package com.android.systemui.keyguard.ui.binder;

import android.view.View;
import com.android.systemui.keyguard.ui.viewmodel.AccessibilityActionsViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AccessibilityActionsViewBinder {
    public static RepeatWhenAttachedKt$repeatWhenAttached$1 bind(View view, AccessibilityActionsViewModel accessibilityActionsViewModel) {
        AccessibilityActionsViewBinder$bind$disposableHandle$1 accessibilityActionsViewBinder$bind$disposableHandle$1 = new AccessibilityActionsViewBinder$bind$disposableHandle$1(view, accessibilityActionsViewModel, null);
        CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
        return RepeatWhenAttachedKt.repeatWhenAttached(view, EmptyCoroutineContext.INSTANCE, accessibilityActionsViewBinder$bind$disposableHandle$1);
    }
}

package com.android.systemui.keyguard.ui.binder;

import android.view.View;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSettingsMenuViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardTouchHandlingViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.VibratorHelper;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardSettingsViewBinder {
    public static RepeatWhenAttachedKt$repeatWhenAttached$1 bind(View view, KeyguardSettingsMenuViewModel keyguardSettingsMenuViewModel, KeyguardTouchHandlingViewModel keyguardTouchHandlingViewModel, KeyguardRootViewModel keyguardRootViewModel, VibratorHelper vibratorHelper, ActivityStarter activityStarter) {
        KeyguardSettingsViewBinder$bind$disposableHandle$1 keyguardSettingsViewBinder$bind$disposableHandle$1 = new KeyguardSettingsViewBinder$bind$disposableHandle$1(activityStarter, keyguardSettingsMenuViewModel, view, vibratorHelper, keyguardRootViewModel, keyguardTouchHandlingViewModel, null);
        CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
        return RepeatWhenAttachedKt.repeatWhenAttached(view, EmptyCoroutineContext.INSTANCE, keyguardSettingsViewBinder$bind$disposableHandle$1);
    }
}

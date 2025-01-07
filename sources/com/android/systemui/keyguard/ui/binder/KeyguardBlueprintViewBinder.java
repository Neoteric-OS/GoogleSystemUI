package com.android.systemui.keyguard.ui.binder;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSmartspaceViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardBlueprintViewBinder {
    public static final KeyguardBlueprintViewBinder INSTANCE = null;

    public static final void bind(KeyguardBlueprintViewModel keyguardBlueprintViewModel, ConstraintLayout constraintLayout, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel) {
        RepeatWhenAttachedKt.repeatWhenAttached(constraintLayout, EmptyCoroutineContext.INSTANCE, new KeyguardBlueprintViewBinder$bind$1(constraintLayout, keyguardBlueprintViewModel, keyguardClockViewModel, keyguardSmartspaceViewModel, null));
    }
}

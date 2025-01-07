package com.android.systemui.biometrics.ui.binder;

import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.biometrics.ui.view.UdfpsTouchOverlay;
import com.android.systemui.biometrics.ui.viewmodel.UdfpsTouchOverlayViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class UdfpsTouchOverlayBinder {
    public static final void bind(UdfpsTouchOverlay udfpsTouchOverlay, UdfpsTouchOverlayViewModel udfpsTouchOverlayViewModel, UdfpsOverlayInteractor udfpsOverlayInteractor) {
        RepeatWhenAttachedKt.repeatWhenAttached(udfpsTouchOverlay, EmptyCoroutineContext.INSTANCE, new UdfpsTouchOverlayBinder$bind$1(udfpsTouchOverlayViewModel, udfpsTouchOverlay, udfpsOverlayInteractor, null));
    }
}

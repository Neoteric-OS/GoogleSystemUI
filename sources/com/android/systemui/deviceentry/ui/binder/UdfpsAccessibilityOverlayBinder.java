package com.android.systemui.deviceentry.ui.binder;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.biometrics.UdfpsUtils;
import com.android.systemui.biometrics.shared.model.UdfpsOverlayParams;
import com.android.systemui.deviceentry.ui.view.UdfpsAccessibilityOverlay;
import com.android.systemui.deviceentry.ui.viewmodel.UdfpsAccessibilityOverlayViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class UdfpsAccessibilityOverlayBinder {
    public static final void bind(UdfpsAccessibilityOverlay udfpsAccessibilityOverlay, final UdfpsAccessibilityOverlayViewModel udfpsAccessibilityOverlayViewModel) {
        udfpsAccessibilityOverlay.setOnHoverListener(new View.OnHoverListener() { // from class: com.android.systemui.deviceentry.ui.binder.UdfpsAccessibilityOverlayBinder$bind$1
            @Override // android.view.View.OnHoverListener
            public final boolean onHover(View view, MotionEvent motionEvent) {
                String onTouchOutsideOfSensorArea;
                UdfpsAccessibilityOverlayViewModel udfpsAccessibilityOverlayViewModel2 = UdfpsAccessibilityOverlayViewModel.this;
                Intrinsics.checkNotNull(view);
                Intrinsics.checkNotNull(motionEvent);
                UdfpsOverlayParams udfpsOverlayParams = (UdfpsOverlayParams) udfpsAccessibilityOverlayViewModel2.udfpsOverlayParams.getValue();
                Point touchInNativeCoordinates = UdfpsUtils.getTouchInNativeCoordinates(motionEvent.getPointerId(0), motionEvent, udfpsOverlayParams, false);
                if (!UdfpsUtils.isWithinSensorArea(motionEvent.getPointerId(0), motionEvent, udfpsOverlayParams, false) && (onTouchOutsideOfSensorArea = UdfpsUtils.onTouchOutsideOfSensorArea(true, view.getContext(), touchInNativeCoordinates.x, touchInNativeCoordinates.y, udfpsOverlayParams, false)) != null) {
                    view.announceForAccessibility(onTouchOutsideOfSensorArea);
                }
                return false;
            }
        });
        RepeatWhenAttachedKt.repeatWhenAttached(udfpsAccessibilityOverlay, EmptyCoroutineContext.INSTANCE, new UdfpsAccessibilityOverlayBinder$bind$2(udfpsAccessibilityOverlayViewModel, udfpsAccessibilityOverlay, null));
    }
}

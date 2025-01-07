package com.android.systemui.statusbar.phone;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.window.BackEvent;
import com.android.app.animation.Interpolators;
import com.android.systemui.animation.back.BackAnimationSpec;
import com.android.systemui.statusbar.phone.SystemUIDialog$$ExternalSyntheticLambda1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EdgeToEdgeDialogDelegate implements DialogDelegate {
    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final BackAnimationSpec getBackAnimationSpec(final SystemUIDialog$$ExternalSyntheticLambda1 systemUIDialog$$ExternalSyntheticLambda1) {
        final Interpolator interpolator = Interpolators.BACK_GESTURE;
        return new BackAnimationSpec() { // from class: com.android.systemui.animation.back.BottomsheetBackAnimationSpecKt$createBottomsheetAnimationSpec$1
            @Override // com.android.systemui.animation.back.BackAnimationSpec
            public final void getBackTransformation(BackEvent backEvent, float f, BackTransformation backTransformation) {
                Integer num = 48;
                backTransformation.scale = 1.0f - ((1.0f - (1 - (TypedValue.applyDimension(1, num.floatValue(), (DisplayMetrics) SystemUIDialog$$ExternalSyntheticLambda1.this.invoke()) / r6.widthPixels))) * interpolator.getInterpolation(backEvent.getProgress()));
                backTransformation.scalePivotPosition = ScalePivotPosition.BOTTOM_CENTER;
            }
        };
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final /* bridge */ /* synthetic */ int getHeight(SystemUIDialog systemUIDialog) {
        return -1;
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final /* bridge */ /* synthetic */ int getWidth(SystemUIDialog systemUIDialog) {
        return -1;
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onCreate(Dialog dialog, Bundle bundle) {
        Window window = ((SystemUIDialog) dialog).getWindow();
        if (window != null) {
            window.setGravity(81);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.setFitInsetsSides(0);
            window.getAttributes().layoutInDisplayCutoutMode = 3;
            window.setAttributes(attributes);
        }
    }
}

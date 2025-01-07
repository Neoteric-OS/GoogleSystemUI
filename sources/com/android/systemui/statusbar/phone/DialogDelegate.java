package com.android.systemui.statusbar.phone;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.animation.Interpolator;
import com.android.app.animation.Interpolators;
import com.android.systemui.animation.back.BackAnimationSpec;
import com.android.systemui.animation.back.BackAnimationSpecKt$createFloatingSurfaceAnimationSpec$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface DialogDelegate {
    default BackAnimationSpec getBackAnimationSpec(SystemUIDialog$$ExternalSyntheticLambda1 systemUIDialog$$ExternalSyntheticLambda1) {
        Interpolator interpolator = Interpolators.BACK_GESTURE;
        return new BackAnimationSpecKt$createFloatingSurfaceAnimationSpec$1(systemUIDialog$$ExternalSyntheticLambda1, interpolator, Interpolators.LINEAR, interpolator);
    }

    default int getHeight(SystemUIDialog systemUIDialog) {
        int i = SystemUIDialog.$r8$clinit;
        return -2;
    }

    default int getWidth(SystemUIDialog systemUIDialog) {
        return SystemUIDialog.getDefaultDialogWidth(systemUIDialog);
    }

    default void beforeCreate(Dialog dialog) {
    }

    default void onStart(Dialog dialog) {
    }

    default void onStop(Dialog dialog) {
    }

    default void onConfigurationChanged(Dialog dialog, Configuration configuration) {
    }

    default void onCreate(Dialog dialog, Bundle bundle) {
    }

    default void onWindowFocusChanged(Dialog dialog, boolean z) {
    }
}

package com.android.systemui.statusbar.chips.mediaprojection.ui.view;

import android.content.pm.PackageManager;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.statusbar.phone.SystemUIDialog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EndMediaProjectionDialogHelper {
    public final SystemUIDialog.Factory dialogFactory;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final PackageManager packageManager;

    public EndMediaProjectionDialogHelper(SystemUIDialog.Factory factory, DialogTransitionAnimator dialogTransitionAnimator, PackageManager packageManager) {
        this.dialogFactory = factory;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.packageManager = packageManager;
    }
}

package com.android.systemui.bluetooth.qsdialog;

import com.android.systemui.animation.DialogCuj;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract /* synthetic */ class BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0 {
    public static DialogTransitionAnimator.Controller m(int i, String str, Expandable expandable) {
        return expandable.dialogTransitionController(new DialogCuj(i, str));
    }
}

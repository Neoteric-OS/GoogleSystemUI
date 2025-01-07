package com.android.systemui.controls.ui;

import android.content.DialogInterface;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StatusBehavior$showNotFoundDialog$builder$1$2 implements DialogInterface.OnClickListener {
    public static final StatusBehavior$showNotFoundDialog$builder$1$2 INSTANCE = new StatusBehavior$showNotFoundDialog$builder$1$2();

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}

package com.android.systemui.mediaprojection.permission;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BaseMediaProjectionPermissionDialogDelegate$setStartButtonOnClickListener$1 implements View.OnClickListener {
    public final /* synthetic */ View.OnClickListener $listener;
    public final /* synthetic */ BaseMediaProjectionPermissionDialogDelegate this$0;

    public BaseMediaProjectionPermissionDialogDelegate$setStartButtonOnClickListener$1(BaseMediaProjectionPermissionDialogDelegate baseMediaProjectionPermissionDialogDelegate, View.OnClickListener onClickListener) {
        this.this$0 = baseMediaProjectionPermissionDialogDelegate;
        this.$listener = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.this$0.shouldLogCancel = false;
        View.OnClickListener onClickListener = this.$listener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}

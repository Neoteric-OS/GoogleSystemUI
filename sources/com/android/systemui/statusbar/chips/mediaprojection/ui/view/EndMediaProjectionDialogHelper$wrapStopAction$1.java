package com.android.systemui.statusbar.chips.mediaprojection.ui.view;

import android.content.DialogInterface;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EndMediaProjectionDialogHelper$wrapStopAction$1 implements DialogInterface.OnClickListener {
    public final /* synthetic */ FunctionReferenceImpl $stopAction;
    public final /* synthetic */ EndMediaProjectionDialogHelper this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public EndMediaProjectionDialogHelper$wrapStopAction$1(EndMediaProjectionDialogHelper endMediaProjectionDialogHelper, Function0 function0) {
        this.this$0 = endMediaProjectionDialogHelper;
        this.$stopAction = (FunctionReferenceImpl) function0;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.FunctionReferenceImpl] */
    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.dialogTransitionAnimator.disableAllCurrentDialogsExitAnimations();
        this.$stopAction.invoke();
    }
}

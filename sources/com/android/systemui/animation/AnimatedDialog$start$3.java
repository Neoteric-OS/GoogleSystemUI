package com.android.systemui.animation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AnimatedDialog$start$3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AnimatedDialog $tmp0;

    public /* synthetic */ AnimatedDialog$start$3(AnimatedDialog animatedDialog, int i) {
        this.$r8$classId = i;
        this.$tmp0 = animatedDialog;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.$tmp0.onDialogDismissed();
                break;
            case 1:
                this.$tmp0.moveSourceDrawingToDialog();
                break;
            default:
                this.$tmp0.onDialogDismissed();
                break;
        }
    }
}

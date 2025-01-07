package com.android.systemui.controls.ui;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsActivity$onStart$2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ControlsActivity this$0;

    public /* synthetic */ ControlsActivity$onStart$2(ControlsActivity controlsActivity, int i) {
        this.$r8$classId = i;
        this.this$0 = controlsActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.finishOrReturnToDream();
                break;
            default:
                this.this$0.finishOrReturnToDream();
                break;
        }
    }
}

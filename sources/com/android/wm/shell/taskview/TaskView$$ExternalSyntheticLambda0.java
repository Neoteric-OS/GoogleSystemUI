package com.android.wm.shell.taskview;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TaskView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TaskView f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ TaskView$$ExternalSyntheticLambda0(TaskView taskView, int i, int i2) {
        this.$r8$classId = i2;
        this.f$0 = taskView;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.setResizeBackgroundColor(this.f$1);
                break;
            case 1:
                this.f$0.setResizeBackgroundColor(this.f$1);
                break;
            default:
                this.f$0.setResizeBackgroundColor(this.f$1);
                break;
        }
    }
}

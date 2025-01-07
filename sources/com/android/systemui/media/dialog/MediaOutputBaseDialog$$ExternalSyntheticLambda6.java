package com.android.systemui.media.dialog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaOutputBaseDialog$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaOutputBaseDialog f$0;

    public /* synthetic */ MediaOutputBaseDialog$$ExternalSyntheticLambda6(MediaOutputBaseDialog mediaOutputBaseDialog, int i) {
        this.$r8$classId = i;
        this.f$0 = mediaOutputBaseDialog;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        MediaOutputBaseDialog mediaOutputBaseDialog = this.f$0;
        switch (i) {
            case 0:
                mediaOutputBaseDialog.refresh(true);
                break;
            case 1:
                mediaOutputBaseDialog.refresh();
                break;
            default:
                mediaOutputBaseDialog.refresh();
                break;
        }
    }
}

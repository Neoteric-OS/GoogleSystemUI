package com.android.systemui.media.dialog;

import com.android.systemui.media.dialog.MediaOutputBaseDialog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaOutputBaseDialog$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaOutputBaseDialog.AnonymousClass1 f$0;

    public /* synthetic */ MediaOutputBaseDialog$1$$ExternalSyntheticLambda0(MediaOutputBaseDialog.AnonymousClass1 anonymousClass1, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        MediaOutputBaseDialog.AnonymousClass1 anonymousClass1 = this.f$0;
        switch (i) {
            case 0:
                anonymousClass1.this$0.handleLeBroadcastUpdated();
                break;
            case 1:
                anonymousClass1.this$0.handleLeBroadcastStarted();
                break;
            case 2:
                anonymousClass1.this$0.handleLeBroadcastStopped();
                break;
            case 3:
                anonymousClass1.this$0.handleLeBroadcastUpdateFailed();
                break;
            case 4:
                anonymousClass1.this$0.handleLeBroadcastStartFailed();
                break;
            case 5:
                anonymousClass1.this$0.handleLeBroadcastMetadataChanged();
                break;
            default:
                anonymousClass1.this$0.handleLeBroadcastStopFailed();
                break;
        }
    }
}

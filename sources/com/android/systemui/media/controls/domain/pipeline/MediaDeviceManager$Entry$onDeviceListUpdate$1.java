package com.android.systemui.media.controls.domain.pipeline;

import com.android.systemui.media.controls.domain.pipeline.MediaDeviceManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDeviceManager$Entry$onDeviceListUpdate$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaDeviceManager.Entry this$0;

    public /* synthetic */ MediaDeviceManager$Entry$onDeviceListUpdate$1(MediaDeviceManager.Entry entry, int i) {
        this.$r8$classId = i;
        this.this$0 = entry;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.updateCurrent();
                break;
            default:
                this.this$0.updateCurrent();
                break;
        }
    }
}

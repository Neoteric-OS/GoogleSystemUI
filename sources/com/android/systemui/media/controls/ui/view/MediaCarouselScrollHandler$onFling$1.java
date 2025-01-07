package com.android.systemui.media.controls.ui.view;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaCarouselScrollHandler$onFling$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaCarouselScrollHandler this$0;

    public /* synthetic */ MediaCarouselScrollHandler$onFling$1(MediaCarouselScrollHandler mediaCarouselScrollHandler, int i) {
        this.$r8$classId = i;
        this.this$0 = mediaCarouselScrollHandler;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.dismissCallback.invoke();
                break;
            default:
                this.this$0.dismissCallback.invoke();
                break;
        }
    }
}

package com.android.systemui.media.controls.ui.view;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaCarouselScrollHandler$onFling$2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ View $view;
    public final /* synthetic */ MediaCarouselScrollHandler this$0;

    public /* synthetic */ MediaCarouselScrollHandler$onFling$2(MediaCarouselScrollHandler mediaCarouselScrollHandler, View view, int i) {
        this.$r8$classId = i;
        this.this$0 = mediaCarouselScrollHandler;
        this.$view = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.scrollView.smoothScrollTo(this.$view.getLeft(), this.this$0.scrollView.getScrollY());
                break;
            default:
                this.this$0.scrollView.smoothScrollTo(this.$view.getLeft(), this.this$0.scrollView.getScrollY());
                break;
        }
    }
}

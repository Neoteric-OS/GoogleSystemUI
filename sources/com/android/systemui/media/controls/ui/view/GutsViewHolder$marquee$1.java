package com.android.systemui.media.controls.ui.view;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GutsViewHolder$marquee$1 implements Runnable {
    public final /* synthetic */ boolean $start;
    public final /* synthetic */ GutsViewHolder this$0;

    public GutsViewHolder$marquee$1(GutsViewHolder gutsViewHolder, boolean z) {
        this.this$0 = gutsViewHolder;
        this.$start = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.gutsText.setSelected(this.$start);
    }
}

package com.android.wm.shell.bubbles.bar;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleEducationViewController$scrimView$2$1$1 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ BubbleEducationViewController$scrimView$2$1$1(int i, Object obj) {
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                BubbleEducationViewController.hideEducation$default((BubbleEducationViewController) this.this$0, true);
                break;
            case 1:
                BubbleEducationViewController.hideEducation$default((BubbleEducationViewController) this.this$0, true);
                break;
            default:
                ((BubbleBarLayerView$$ExternalSyntheticLambda6) this.this$0).invoke();
                break;
        }
    }
}

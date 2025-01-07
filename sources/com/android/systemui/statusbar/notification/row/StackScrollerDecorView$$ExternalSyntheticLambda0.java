package com.android.systemui.statusbar.notification.row;

import com.android.systemui.statusbar.notification.footer.ui.viewbinder.FooterViewBinder$bindClearAllButton$2;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StackScrollerDecorView$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ StackScrollerDecorView f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ StackScrollerDecorView$$ExternalSyntheticLambda0(StackScrollerDecorView stackScrollerDecorView, FooterViewBinder$bindClearAllButton$2.AnonymousClass3.AnonymousClass1.C01941 c01941) {
        this.f$0 = stackScrollerDecorView;
        this.f$1 = c01941;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                StackScrollerDecorView stackScrollerDecorView = this.f$0;
                Consumer consumer = (Consumer) this.f$1;
                Boolean bool = (Boolean) obj;
                stackScrollerDecorView.onContentVisibilityAnimationEnd();
                if (consumer != null) {
                    consumer.accept(bool);
                    break;
                }
                break;
            default:
                StackScrollerDecorView stackScrollerDecorView2 = this.f$0;
                FooterViewBinder$bindClearAllButton$2.AnonymousClass3.AnonymousClass1.C01941 c01941 = (FooterViewBinder$bindClearAllButton$2.AnonymousClass3.AnonymousClass1.C01941) this.f$1;
                Boolean bool2 = (Boolean) obj;
                stackScrollerDecorView2.onContentVisibilityAnimationEnd();
                if (c01941 != null) {
                    c01941.accept(bool2);
                    break;
                }
                break;
        }
    }

    public /* synthetic */ StackScrollerDecorView$$ExternalSyntheticLambda0(StackScrollerDecorView stackScrollerDecorView, Consumer consumer) {
        this.f$0 = stackScrollerDecorView;
        this.f$1 = consumer;
    }
}

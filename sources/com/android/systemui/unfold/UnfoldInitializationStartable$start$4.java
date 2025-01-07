package com.android.systemui.unfold;

import com.android.systemui.unfold.progress.UnfoldTransitionProgressForwarder;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldInitializationStartable$start$4 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ UnfoldInitializationStartable$start$4(int i, Object obj) {
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                ((UnfoldInitializationStartable) this.this$0).unfoldTransitionProgressForwarder.ifPresent(new UnfoldInitializationStartable$start$4(1, (UnfoldTransitionProgressProvider) obj));
                break;
            default:
                ((UnfoldTransitionProgressProvider) this.this$0).addCallback((UnfoldTransitionProgressForwarder) obj);
                break;
        }
    }
}

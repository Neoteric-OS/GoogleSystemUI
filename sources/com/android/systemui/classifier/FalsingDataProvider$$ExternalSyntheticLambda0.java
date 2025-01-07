package com.android.systemui.classifier;

import com.android.systemui.classifier.BrightLineFalsingManager;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class FalsingDataProvider$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ FalsingDataProvider$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        BrightLineFalsingManager.AnonymousClass1 anonymousClass1 = (BrightLineFalsingManager.AnonymousClass1) obj;
        switch (this.$r8$classId) {
            case 0:
                BrightLineFalsingManager brightLineFalsingManager = BrightLineFalsingManager.this;
                HistoryTracker historyTracker = brightLineFalsingManager.mHistoryTracker;
                BrightLineFalsingManager.AnonymousClass2 anonymousClass2 = brightLineFalsingManager.mBeliefListener;
                if (anonymousClass2 != null) {
                    historyTracker.mBeliefListeners.add(anonymousClass2);
                } else {
                    historyTracker.getClass();
                }
                brightLineFalsingManager.mClassifiers.forEach(new BrightLineFalsingManager$$ExternalSyntheticLambda0(3));
                break;
            default:
                BrightLineFalsingManager brightLineFalsingManager2 = BrightLineFalsingManager.this;
                brightLineFalsingManager2.mLastProximityEvent = null;
                HistoryTracker historyTracker2 = brightLineFalsingManager2.mHistoryTracker;
                BrightLineFalsingManager.AnonymousClass2 anonymousClass22 = brightLineFalsingManager2.mBeliefListener;
                if (anonymousClass22 != null) {
                    historyTracker2.mBeliefListeners.remove(anonymousClass22);
                } else {
                    historyTracker2.getClass();
                }
                brightLineFalsingManager2.mClassifiers.forEach(new BrightLineFalsingManager$$ExternalSyntheticLambda0(2));
                break;
        }
    }
}

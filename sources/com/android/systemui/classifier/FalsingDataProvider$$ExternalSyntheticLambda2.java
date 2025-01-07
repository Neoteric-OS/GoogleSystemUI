package com.android.systemui.classifier;

import android.os.Build;
import android.view.InputEvent;
import android.view.MotionEvent;
import com.android.systemui.classifier.BrightLineFalsingManager;
import com.android.systemui.classifier.FalsingClassifier;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class FalsingDataProvider$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ FalsingDataProvider$$ExternalSyntheticLambda2(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                ((FalsingClassifier$$ExternalSyntheticLambda0) obj).f$0.onTouchEvent((MotionEvent) obj2);
                break;
            default:
                long eventTime = ((MotionEvent) ((InputEvent) ((ArrayList) ((FalsingDataProvider) obj2).mRecentMotionEvents.mInputEvents).get(r9.size() - 1))).getEventTime();
                BrightLineFalsingManager brightLineFalsingManager = BrightLineFalsingManager.this;
                Collection collection = brightLineFalsingManager.mPriorResults;
                HistoryTracker historyTracker = brightLineFalsingManager.mHistoryTracker;
                FalsingDataProvider falsingDataProvider = brightLineFalsingManager.mDataProvider;
                if (collection == null) {
                    historyTracker.addResults(Collections.singleton(new FalsingClassifier.Result(true, brightLineFalsingManager.mSingleTapClassifier.isTap(falsingDataProvider.getRecentMotionEvents(), 0.0d).mFalsed ? 0.7d : 0.8d, BrightLineFalsingManager.AnonymousClass3.class.getSimpleName(), "unclassified")), eventTime);
                    break;
                } else {
                    boolean anyMatch = collection.stream().anyMatch(new BrightLineFalsingManager$3$$ExternalSyntheticLambda0());
                    brightLineFalsingManager.mPriorResults.forEach(new BrightLineFalsingManager$$ExternalSyntheticLambda0(5));
                    if (Build.IS_ENG || Build.IS_USERDEBUG) {
                        BrightLineFalsingManager.RECENT_SWIPES.add(new BrightLineFalsingManager.DebugSwipeRecord(brightLineFalsingManager.mPriorInteractionType, (List) falsingDataProvider.getRecentMotionEvents().stream().map(new BrightLineFalsingManager$3$$ExternalSyntheticLambda2()).collect(Collectors.toList()), anyMatch));
                        while (true) {
                            ArrayDeque arrayDeque = (ArrayDeque) BrightLineFalsingManager.RECENT_SWIPES;
                            if (arrayDeque.size() > 40) {
                                arrayDeque.remove();
                            }
                        }
                    }
                    historyTracker.addResults(brightLineFalsingManager.mPriorResults, eventTime);
                    brightLineFalsingManager.mPriorResults = null;
                    brightLineFalsingManager.mPriorInteractionType = 7;
                    break;
                }
                break;
        }
    }
}

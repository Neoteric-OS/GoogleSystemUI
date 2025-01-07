package com.android.systemui.classifier;

import android.view.MotionEvent;
import com.android.systemui.classifier.FalsingClassifier;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DoubleTapClassifier extends FalsingClassifier {
    public final float mDoubleTapSlop;
    public final long mDoubleTapTimeMs;
    public final SingleTapClassifier mSingleTapClassifier;

    public DoubleTapClassifier(FalsingDataProvider falsingDataProvider, SingleTapClassifier singleTapClassifier, float f) {
        super(falsingDataProvider);
        this.mSingleTapClassifier = singleTapClassifier;
        this.mDoubleTapSlop = f;
        this.mDoubleTapTimeMs = 1200L;
    }

    @Override // com.android.systemui.classifier.FalsingClassifier
    public final FalsingClassifier.Result calculateFalsingResult(int i) {
        FalsingDataProvider falsingDataProvider = this.mDataProvider;
        List recentMotionEvents = falsingDataProvider.getRecentMotionEvents();
        List list = falsingDataProvider.mPriorMotionEvents;
        StringBuilder sb = new StringBuilder();
        if (list == null) {
            return falsed(0.0d, "Only one gesture recorded");
        }
        SingleTapClassifier singleTapClassifier = this.mSingleTapClassifier;
        FalsingClassifier.Result isTap = singleTapClassifier.isTap(list, 0.5d);
        if (isTap.mFalsed) {
            sb.append("First gesture is not a tap. ");
            sb.append(isTap.getReason());
        } else {
            FalsingClassifier.Result isTap2 = singleTapClassifier.isTap(recentMotionEvents, 0.5d);
            if (isTap2.mFalsed) {
                sb.append("Second gesture is not a tap. ");
                sb.append(isTap2.getReason());
            } else {
                MotionEvent motionEvent = (MotionEvent) list.get(list.size() - 1);
                MotionEvent motionEvent2 = (MotionEvent) recentMotionEvents.get(recentMotionEvents.size() - 1);
                long eventTime = motionEvent2.getEventTime() - motionEvent.getEventTime();
                if (eventTime > this.mDoubleTapTimeMs) {
                    sb.append("Time between taps too large: ");
                    sb.append(eventTime);
                    sb.append("ms");
                } else {
                    float abs = Math.abs(motionEvent.getX() - motionEvent2.getX());
                    float f = this.mDoubleTapSlop;
                    if (abs >= f) {
                        sb.append("Delta X between taps too large:");
                        sb.append(Math.abs(motionEvent.getX() - motionEvent2.getX()));
                        sb.append(" vs ");
                        sb.append(f);
                    } else {
                        if (Math.abs(motionEvent.getY() - motionEvent2.getY()) < f) {
                            return FalsingClassifier.Result.passed(0.5d);
                        }
                        sb.append("Delta Y between taps too large:");
                        sb.append(Math.abs(motionEvent.getY() - motionEvent2.getY()));
                        sb.append(" vs ");
                        sb.append(f);
                    }
                }
            }
        }
        return falsed(0.5d, sb.toString());
    }
}

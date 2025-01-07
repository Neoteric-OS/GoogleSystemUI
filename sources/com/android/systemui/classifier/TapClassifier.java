package com.android.systemui.classifier;

import android.view.MotionEvent;
import com.android.systemui.classifier.FalsingClassifier;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TapClassifier extends FalsingClassifier {
    public final float mTouchSlop;

    public TapClassifier(FalsingDataProvider falsingDataProvider, float f) {
        super(falsingDataProvider);
        this.mTouchSlop = f;
    }

    @Override // com.android.systemui.classifier.FalsingClassifier
    public final FalsingClassifier.Result calculateFalsingResult(int i) {
        return isTap(this.mDataProvider.getRecentMotionEvents(), 0.5d);
    }

    public final FalsingClassifier.Result isTap(List list, double d) {
        if (list.isEmpty()) {
            return falsed(0.0d, "no motion events");
        }
        float x = ((MotionEvent) list.get(0)).getX();
        float y = ((MotionEvent) list.get(0)).getY();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MotionEvent motionEvent = (MotionEvent) it.next();
            float abs = Math.abs(motionEvent.getX() - x);
            float f = this.mTouchSlop;
            if (abs >= f) {
                return falsed(d, "dX too big for a tap: " + Math.abs(motionEvent.getX() - x) + "vs " + f);
            }
            if (Math.abs(motionEvent.getY() - y) >= f) {
                return falsed(d, "dY too big for a tap: " + Math.abs(motionEvent.getY() - y) + " vs " + f);
            }
        }
        return FalsingClassifier.Result.passed(0.0d);
    }
}

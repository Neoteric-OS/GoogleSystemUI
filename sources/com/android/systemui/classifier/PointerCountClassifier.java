package com.android.systemui.classifier;

import android.view.MotionEvent;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;
import com.android.systemui.classifier.FalsingClassifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerCountClassifier extends FalsingClassifier {
    public int mMaxPointerCount;

    @Override // com.android.systemui.classifier.FalsingClassifier
    public final FalsingClassifier.Result calculateFalsingResult(int i) {
        int i2 = 2;
        if (i != 0 && i != 2) {
            i2 = 1;
        }
        int i3 = this.mMaxPointerCount;
        return i3 > i2 ? falsed(1.0d, MutableVectorKt$$ExternalSyntheticOutline0.m(i3, i2, "{pointersObserved=", ", threshold=", "}")) : FalsingClassifier.Result.passed(0.0d);
    }

    @Override // com.android.systemui.classifier.FalsingClassifier
    public final void onTouchEvent(MotionEvent motionEvent) {
        int i = this.mMaxPointerCount;
        if (motionEvent.getActionMasked() == 0) {
            this.mMaxPointerCount = motionEvent.getPointerCount();
        } else {
            this.mMaxPointerCount = Math.max(this.mMaxPointerCount, motionEvent.getPointerCount());
        }
        if (i != this.mMaxPointerCount) {
            BrightLineFalsingManager.logDebug("Pointers observed:" + this.mMaxPointerCount);
        }
    }
}

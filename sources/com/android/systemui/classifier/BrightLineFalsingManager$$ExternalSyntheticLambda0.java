package com.android.systemui.classifier;

import com.android.systemui.classifier.FalsingClassifier;
import com.android.systemui.plugins.FalsingManager;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class BrightLineFalsingManager$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ BrightLineFalsingManager$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                ((FalsingManager.FalsingTapListener) obj).onAdditionalTapRequired();
                break;
            case 1:
                FalsingClassifier falsingClassifier = (FalsingClassifier) obj;
                falsingClassifier.mDataProvider.mMotionEventListeners.remove(falsingClassifier.mMotionEventListener);
                break;
            case 2:
                ((FalsingClassifier) obj).onSessionEnded();
                break;
            case 3:
                ((FalsingClassifier) obj).onSessionStarted();
                break;
            case 4:
                ((FalsingManager.FalsingBeliefListener) obj).onFalse();
                break;
            default:
                FalsingClassifier.Result result = (FalsingClassifier.Result) obj;
                if (result.mFalsed) {
                    BrightLineFalsingManager.logInfo(result.getReason());
                    break;
                }
                break;
        }
    }
}

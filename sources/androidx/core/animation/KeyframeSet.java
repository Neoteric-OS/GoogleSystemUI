package androidx.core.animation;

import androidx.compose.ui.text.input.EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0;
import java.util.Arrays;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyframeSet implements Cloneable {
    public TypeEvaluator mEvaluator;
    public final List mKeyframes;
    public final int mNumKeyframes;

    public KeyframeSet(Keyframe... keyframeArr) {
        int length = keyframeArr.length;
        this.mNumKeyframes = length;
        this.mKeyframes = Arrays.asList(keyframeArr);
        Keyframe keyframe = keyframeArr[0];
        keyframeArr[length - 1].getClass();
    }

    public abstract KeyframeSet clone();

    public final String toString() {
        String str = " ";
        for (int i = 0; i < this.mNumKeyframes; i++) {
            StringBuilder m = EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0.m(str);
            m.append(((Keyframe) this.mKeyframes.get(i)).getValue());
            m.append("  ");
            str = m.toString();
        }
        return str;
    }
}

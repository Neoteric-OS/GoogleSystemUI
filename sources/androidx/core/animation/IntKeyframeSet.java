package androidx.core.animation;

import androidx.core.animation.Keyframe;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IntKeyframeSet extends KeyframeSet {
    @Override // androidx.core.animation.KeyframeSet
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final IntKeyframeSet m713clone() {
        List list = this.mKeyframes;
        int size = list.size();
        Keyframe.IntKeyframe[] intKeyframeArr = new Keyframe.IntKeyframe[size];
        for (int i = 0; i < size; i++) {
            intKeyframeArr[i] = (Keyframe.IntKeyframe) ((Keyframe) list.get(i)).m715clone();
        }
        return new IntKeyframeSet(intKeyframeArr);
    }
}

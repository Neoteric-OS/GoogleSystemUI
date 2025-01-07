package androidx.core.animation;

import androidx.core.animation.Keyframe;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FloatKeyframeSet extends KeyframeSet {
    @Override // androidx.core.animation.KeyframeSet
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final FloatKeyframeSet m712clone() {
        List list = this.mKeyframes;
        int size = list.size();
        Keyframe.FloatKeyframe[] floatKeyframeArr = new Keyframe.FloatKeyframe[size];
        for (int i = 0; i < size; i++) {
            floatKeyframeArr[i] = (Keyframe.FloatKeyframe) ((Keyframe) list.get(i)).m715clone();
        }
        return new FloatKeyframeSet(floatKeyframeArr);
    }
}

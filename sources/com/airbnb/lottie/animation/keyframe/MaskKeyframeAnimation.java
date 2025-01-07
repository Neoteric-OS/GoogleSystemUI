package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.content.Mask;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MaskKeyframeAnimation {
    public final List maskAnimations;
    public final List masks;
    public final List opacityAnimations;

    public MaskKeyframeAnimation(List list) {
        this.masks = list;
        this.maskAnimations = new ArrayList(list.size());
        this.opacityAnimations = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            this.maskAnimations.add(new ShapeKeyframeAnimation(((Mask) list.get(i)).maskPath.keyframes));
            this.opacityAnimations.add(((Mask) list.get(i)).opacity.createAnimation());
        }
    }
}

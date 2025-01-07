package com.airbnb.lottie.animation.content;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrimPathContent implements Content, BaseKeyframeAnimation.AnimationListener {
    public final FloatKeyframeAnimation endAnimation;
    public final boolean hidden;
    public final List listeners = new ArrayList();
    public final FloatKeyframeAnimation offsetAnimation;
    public final FloatKeyframeAnimation startAnimation;
    public final ShapeTrimPath.Type type;

    public TrimPathContent(BaseLayer baseLayer, ShapeTrimPath shapeTrimPath) {
        shapeTrimPath.getClass();
        this.hidden = shapeTrimPath.hidden;
        this.type = shapeTrimPath.type;
        BaseKeyframeAnimation createAnimation = shapeTrimPath.start.createAnimation();
        this.startAnimation = (FloatKeyframeAnimation) createAnimation;
        BaseKeyframeAnimation createAnimation2 = shapeTrimPath.end.createAnimation();
        this.endAnimation = (FloatKeyframeAnimation) createAnimation2;
        BaseKeyframeAnimation createAnimation3 = shapeTrimPath.offset.createAnimation();
        this.offsetAnimation = (FloatKeyframeAnimation) createAnimation3;
        baseLayer.addAnimation(createAnimation);
        baseLayer.addAnimation(createAnimation2);
        baseLayer.addAnimation(createAnimation3);
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
        createAnimation3.addUpdateListener(this);
    }

    public final void addListener(BaseKeyframeAnimation.AnimationListener animationListener) {
        this.listeners.add(animationListener);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public final void onValueChanged() {
        for (int i = 0; i < ((ArrayList) this.listeners).size(); i++) {
            ((BaseKeyframeAnimation.AnimationListener) ((ArrayList) this.listeners).get(i)).onValueChanged();
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public final void setContents(List list, List list2) {
    }
}

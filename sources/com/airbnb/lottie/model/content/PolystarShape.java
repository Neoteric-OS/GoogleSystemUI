package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.PolystarContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PolystarShape implements ContentModel {
    public final boolean hidden;
    public final AnimatableFloatValue innerRadius;
    public final AnimatableFloatValue innerRoundedness;
    public final boolean isReversed;
    public final String name;
    public final AnimatableFloatValue outerRadius;
    public final AnimatableFloatValue outerRoundedness;
    public final AnimatableFloatValue points;
    public final AnimatableValue position;
    public final AnimatableFloatValue rotation;
    public final Type type;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum Type {
        STAR(1),
        /* JADX INFO: Fake field, exist only in values array */
        POLYGON(2);

        private final int value;

        Type(int i) {
            this.value = i;
        }

        public static Type forValue(int i) {
            for (Type type : values()) {
                if (type.value == i) {
                    return type;
                }
            }
            return null;
        }
    }

    public PolystarShape(String str, Type type, AnimatableFloatValue animatableFloatValue, AnimatableValue animatableValue, AnimatableFloatValue animatableFloatValue2, AnimatableFloatValue animatableFloatValue3, AnimatableFloatValue animatableFloatValue4, AnimatableFloatValue animatableFloatValue5, AnimatableFloatValue animatableFloatValue6, boolean z, boolean z2) {
        this.name = str;
        this.type = type;
        this.points = animatableFloatValue;
        this.position = animatableValue;
        this.rotation = animatableFloatValue2;
        this.innerRadius = animatableFloatValue3;
        this.outerRadius = animatableFloatValue4;
        this.innerRoundedness = animatableFloatValue5;
        this.outerRoundedness = animatableFloatValue6;
        this.hidden = z;
        this.isReversed = z2;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public final Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new PolystarContent(lottieDrawable, baseLayer, this);
    }
}

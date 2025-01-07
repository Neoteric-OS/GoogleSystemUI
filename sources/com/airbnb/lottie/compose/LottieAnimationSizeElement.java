package com.airbnb.lottie.compose;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieAnimationSizeElement extends ModifierNodeElement {
    public final int height;
    public final int width;

    public LottieAnimationSizeElement(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        LottieAnimationSizeNode lottieAnimationSizeNode = new LottieAnimationSizeNode();
        lottieAnimationSizeNode.width = this.width;
        lottieAnimationSizeNode.height = this.height;
        return lottieAnimationSizeNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LottieAnimationSizeElement)) {
            return false;
        }
        LottieAnimationSizeElement lottieAnimationSizeElement = (LottieAnimationSizeElement) obj;
        return this.width == lottieAnimationSizeElement.width && this.height == lottieAnimationSizeElement.height;
    }

    public final int hashCode() {
        return Integer.hashCode(this.height) + (Integer.hashCode(this.width) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LottieAnimationSizeElement(width=");
        sb.append(this.width);
        sb.append(", height=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.height, ")");
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        LottieAnimationSizeNode lottieAnimationSizeNode = (LottieAnimationSizeNode) node;
        lottieAnimationSizeNode.width = this.width;
        lottieAnimationSizeNode.height = this.height;
    }
}

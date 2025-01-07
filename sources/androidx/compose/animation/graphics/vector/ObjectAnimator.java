package androidx.compose.animation.graphics.vector;

import androidx.collection.MutableScatterMap;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.RepeatMode;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ObjectAnimator extends Animator {
    public final int duration;
    public final List holders;
    public final int repeatCount;
    public final RepeatMode repeatMode;
    public final int startDelay;
    public final int totalDuration;

    public ObjectAnimator(int i, int i2, int i3, RepeatMode repeatMode, List list) {
        this.duration = i;
        this.startDelay = i2;
        this.repeatCount = i3;
        this.repeatMode = repeatMode;
        this.holders = list;
        this.totalDuration = i3 == -1 ? Integer.MAX_VALUE : ((i3 + 1) * i) + i2;
    }

    @Override // androidx.compose.animation.graphics.vector.Animator
    public final void collectPropertyValues(MutableScatterMap mutableScatterMap, int i, int i2) {
        ArrayList arrayList = (ArrayList) this.holders;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            PropertyValuesHolder propertyValuesHolder = (PropertyValuesHolder) arrayList.get(i3);
            if (!(propertyValuesHolder instanceof PropertyValuesHolder2D)) {
                boolean z = propertyValuesHolder instanceof PropertyValuesHolderFloat;
                RepeatMode repeatMode = this.repeatMode;
                int i4 = this.startDelay;
                if (z) {
                    PropertyValuesHolderFloat propertyValuesHolderFloat = (PropertyValuesHolderFloat) propertyValuesHolder;
                    FloatPropertyValues floatPropertyValues = (FloatPropertyValues) mutableScatterMap.get(propertyValuesHolderFloat.propertyName);
                    if (floatPropertyValues == null) {
                        floatPropertyValues = new FloatPropertyValues();
                    }
                    FloatPropertyValues floatPropertyValues2 = floatPropertyValues;
                    floatPropertyValues2.timestamps.add(new Timestamp(i2 + i4, this.duration, this.repeatCount, repeatMode, (PropertyValuesHolder1D) propertyValuesHolder));
                    mutableScatterMap.set(propertyValuesHolderFloat.propertyName, floatPropertyValues2);
                } else if (propertyValuesHolder instanceof PropertyValuesHolderColor) {
                    PropertyValuesHolderColor propertyValuesHolderColor = (PropertyValuesHolderColor) propertyValuesHolder;
                    ColorPropertyValues colorPropertyValues = (ColorPropertyValues) mutableScatterMap.get(propertyValuesHolderColor.propertyName);
                    if (colorPropertyValues == null) {
                        colorPropertyValues = new ColorPropertyValues();
                    }
                    ColorPropertyValues colorPropertyValues2 = colorPropertyValues;
                    colorPropertyValues2.timestamps.add(new Timestamp(i2 + i4, this.duration, this.repeatCount, repeatMode, (PropertyValuesHolder1D) propertyValuesHolder));
                    mutableScatterMap.set(propertyValuesHolderColor.propertyName, colorPropertyValues2);
                } else if (propertyValuesHolder instanceof PropertyValuesHolderPath) {
                    PropertyValuesHolderPath propertyValuesHolderPath = (PropertyValuesHolderPath) propertyValuesHolder;
                    PathPropertyValues pathPropertyValues = (PathPropertyValues) mutableScatterMap.get(propertyValuesHolderPath.propertyName);
                    if (pathPropertyValues == null) {
                        pathPropertyValues = new PathPropertyValues();
                    }
                    PathPropertyValues pathPropertyValues2 = pathPropertyValues;
                    pathPropertyValues2.timestamps.add(new Timestamp(i2 + i4, this.duration, this.repeatCount, repeatMode, (PropertyValuesHolder1D) propertyValuesHolder));
                    mutableScatterMap.set(propertyValuesHolderPath.propertyName, pathPropertyValues2);
                } else {
                    boolean z2 = propertyValuesHolder instanceof PropertyValuesHolderInt;
                }
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ObjectAnimator)) {
            return false;
        }
        ObjectAnimator objectAnimator = (ObjectAnimator) obj;
        return this.duration == objectAnimator.duration && this.startDelay == objectAnimator.startDelay && this.repeatCount == objectAnimator.repeatCount && this.repeatMode == objectAnimator.repeatMode && Intrinsics.areEqual(this.holders, objectAnimator.holders);
    }

    @Override // androidx.compose.animation.graphics.vector.Animator
    public final int getTotalDuration() {
        return this.totalDuration;
    }

    public final int hashCode() {
        return this.holders.hashCode() + ((this.repeatMode.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.repeatCount, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.startDelay, Integer.hashCode(this.duration) * 31, 31), 31)) * 31);
    }

    public final String toString() {
        return "ObjectAnimator(duration=" + this.duration + ", startDelay=" + this.startDelay + ", repeatCount=" + this.repeatCount + ", repeatMode=" + this.repeatMode + ", holders=" + this.holders + ')';
    }
}

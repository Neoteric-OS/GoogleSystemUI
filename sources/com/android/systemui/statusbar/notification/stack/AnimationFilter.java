package com.android.systemui.statusbar.notification.stack;

import android.util.Property;
import androidx.collection.ArraySet;
import java.util.ConcurrentModificationException;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class AnimationFilter {
    public boolean animateAlpha;
    public boolean animateHeight;
    public boolean animateHideSensitive;
    public boolean animateTopInset;
    public boolean animateX;
    public boolean animateY;
    public boolean animateZ;
    public long customDelay;
    public boolean hasDelays;
    public boolean hasGoToFullShadeEvent;
    public final ArraySet mAnimatedProperties = new ArraySet(0);

    public final void combineFilter(AnimationFilter animationFilter) {
        this.animateAlpha |= animationFilter.animateAlpha;
        this.animateX |= animationFilter.animateX;
        this.animateY |= animationFilter.animateY;
        this.animateZ |= animationFilter.animateZ;
        this.animateHeight |= animationFilter.animateHeight;
        this.animateTopInset |= animationFilter.animateTopInset;
        this.animateHideSensitive |= animationFilter.animateHideSensitive;
        this.hasDelays |= animationFilter.hasDelays;
        ArraySet arraySet = this.mAnimatedProperties;
        arraySet.getClass();
        ArraySet arraySet2 = animationFilter.mAnimatedProperties;
        int i = arraySet2._size;
        arraySet.ensureCapacity(arraySet._size + i);
        if (arraySet._size != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                arraySet.add(arraySet2.array[i2]);
            }
        } else if (i > 0) {
            ArraysKt.copyInto$default(0, i, 6, arraySet2.hashes, arraySet.hashes);
            ArraysKt.copyInto$default(0, i, 6, arraySet2.array, arraySet.array);
            if (arraySet._size != 0) {
                throw new ConcurrentModificationException();
            }
            arraySet._size = i;
        }
    }

    public final void reset() {
        this.animateAlpha = false;
        this.animateX = false;
        this.animateY = false;
        this.animateZ = false;
        this.animateHeight = false;
        this.animateTopInset = false;
        this.animateHideSensitive = false;
        this.hasDelays = false;
        this.hasGoToFullShadeEvent = false;
        this.customDelay = -1L;
        this.mAnimatedProperties.clear();
    }

    public boolean shouldAnimateProperty(Property property) {
        return this.mAnimatedProperties.contains(property);
    }
}

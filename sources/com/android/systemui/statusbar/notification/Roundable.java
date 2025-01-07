package com.android.systemui.statusbar.notification;

import java.util.Map;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface Roundable {
    default void applyRoundnessAndInvalidate() {
        getRoundableState().targetView.invalidate();
    }

    default float getBottomCornerRadius() {
        RoundableState roundableState = getRoundableState();
        int clipHeight = roundableState.roundable.getClipHeight();
        float f = roundableState.topRoundness;
        float f2 = roundableState.maxRadius;
        float f3 = f * f2;
        float f4 = roundableState.bottomRoundness;
        float f5 = f2 * f4;
        if (clipHeight == 0) {
            return 0.0f;
        }
        float f6 = f3 + f5;
        float f7 = clipHeight;
        return f6 > f7 ? f5 - (((f6 - f7) * f4) / (f + f4)) : f5;
    }

    int getClipHeight();

    RoundableState getRoundableState();

    default float getTopCornerRadius() {
        RoundableState roundableState = getRoundableState();
        int clipHeight = roundableState.roundable.getClipHeight();
        float f = roundableState.topRoundness;
        float f2 = roundableState.maxRadius;
        float f3 = f * f2;
        float f4 = roundableState.bottomRoundness;
        float f5 = f2 * f4;
        if (clipHeight == 0) {
            return 0.0f;
        }
        float f6 = f5 + f3;
        float f7 = clipHeight;
        return f6 > f7 ? f3 - (((f6 - f7) * f) / (f + f4)) : f3;
    }

    default boolean hasRoundedCorner() {
        return (getRoundableState().topRoundness == 0.0f && getRoundableState().bottomRoundness == 0.0f) ? false : true;
    }

    default boolean requestBottomRoundness(float f, SourceType$Companion$from$1 sourceType$Companion$from$1, boolean z) {
        Map map = getRoundableState().bottomRoundnessMap;
        Float maxOrNull = CollectionsKt.maxOrNull(map.values());
        float floatValue = maxOrNull != null ? maxOrNull.floatValue() : 0.0f;
        if (f == 0.0f) {
            map.remove(sourceType$Companion$from$1);
        } else {
            map.put(sourceType$Companion$from$1, Float.valueOf(f));
        }
        Float maxOrNull2 = CollectionsKt.maxOrNull(map.values());
        float floatValue2 = maxOrNull2 != null ? maxOrNull2.floatValue() : 0.0f;
        if (floatValue == floatValue2) {
            return false;
        }
        RoundableState roundableState = getRoundableState();
        boolean z2 = (roundableState.targetView.getTag(roundableState.bottomAnimatable.val$animatorTag) != null) && Math.abs(floatValue2 - floatValue) > 0.5f;
        RoundableState roundableState2 = getRoundableState();
        PropertyAnimator.setProperty(roundableState2.targetView, roundableState2.bottomAnimatable, floatValue2, RoundableState.DURATION, z2 || z);
        return true;
    }

    default boolean requestRoundness(float f, float f2, SourceType$Companion$from$1 sourceType$Companion$from$1, boolean z) {
        return requestTopRoundness(f, sourceType$Companion$from$1, z) || requestBottomRoundness(f2, sourceType$Companion$from$1, z);
    }

    default void requestRoundnessReset(SourceType$Companion$from$1 sourceType$Companion$from$1) {
        requestRoundness(0.0f, 0.0f, sourceType$Companion$from$1, getRoundableState().targetView.isShown());
    }

    default boolean requestTopRoundness(float f, SourceType$Companion$from$1 sourceType$Companion$from$1, boolean z) {
        Map map = getRoundableState().topRoundnessMap;
        Float maxOrNull = CollectionsKt.maxOrNull(map.values());
        float floatValue = maxOrNull != null ? maxOrNull.floatValue() : 0.0f;
        if (f == 0.0f) {
            map.remove(sourceType$Companion$from$1);
        } else {
            map.put(sourceType$Companion$from$1, Float.valueOf(f));
        }
        Float maxOrNull2 = CollectionsKt.maxOrNull(map.values());
        float floatValue2 = maxOrNull2 != null ? maxOrNull2.floatValue() : 0.0f;
        if (floatValue == floatValue2) {
            return false;
        }
        RoundableState roundableState = getRoundableState();
        boolean z2 = (roundableState.targetView.getTag(roundableState.topAnimatable.val$animatorTag) != null) && Math.abs(floatValue2 - floatValue) > 0.5f;
        RoundableState roundableState2 = getRoundableState();
        PropertyAnimator.setProperty(roundableState2.targetView, roundableState2.topAnimatable, floatValue2, RoundableState.DURATION, z2 || z);
        return true;
    }

    default void requestRoundness(float f, float f2, SourceType$Companion$from$1 sourceType$Companion$from$1) {
        requestRoundness(f, f2, sourceType$Companion$from$1, getRoundableState().targetView.isShown());
    }
}

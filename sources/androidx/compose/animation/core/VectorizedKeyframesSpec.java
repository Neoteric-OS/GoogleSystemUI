package androidx.compose.animation.core;

import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.internal.RuntimeHelpersKt;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.ArcSpline;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VectorizedKeyframesSpec implements VectorizedDurationBasedAnimationSpec {
    public ArcSpline arcSpline;
    public final EasingKt$$ExternalSyntheticLambda0 defaultEasing;
    public final int durationMillis;
    public final MutableIntObjectMap keyframes;
    public AnimationVector lastInitialValue;
    public AnimationVector lastTargetValue;
    public int[] modes = VectorizedAnimationSpecKt.EmptyIntArray;
    public float[] posArray;
    public float[] slopeArray;
    public float[] times;
    public final MutableIntList timestamps;
    public AnimationVector valueVector;
    public AnimationVector velocityVector;

    public VectorizedKeyframesSpec(MutableIntList mutableIntList, MutableIntObjectMap mutableIntObjectMap, int i, EasingKt$$ExternalSyntheticLambda0 easingKt$$ExternalSyntheticLambda0) {
        this.timestamps = mutableIntList;
        this.keyframes = mutableIntObjectMap;
        this.durationMillis = i;
        this.defaultEasing = easingKt$$ExternalSyntheticLambda0;
        float[] fArr = VectorizedAnimationSpecKt.EmptyFloatArray;
        this.times = fArr;
        this.posArray = fArr;
        this.slopeArray = fArr;
        this.arcSpline = VectorizedAnimationSpecKt.EmptyArcSpline;
    }

    public final int findEntryForTimeMillis(int i) {
        int i2;
        MutableIntList mutableIntList = this.timestamps;
        int i3 = mutableIntList._size;
        mutableIntList.getClass();
        if (i3 <= 0 || i3 > mutableIntList._size) {
            RuntimeHelpersKt.throwIndexOutOfBoundsException("");
            throw null;
        }
        int i4 = i3 - 1;
        int i5 = 0;
        while (true) {
            if (i5 <= i4) {
                i2 = (i5 + i4) >>> 1;
                int i6 = mutableIntList.content[i2];
                if (i6 >= i) {
                    if (i6 <= i) {
                        break;
                    }
                    i4 = i2 - 1;
                } else {
                    i5 = i2 + 1;
                }
            } else {
                i2 = -(i5 + 1);
                break;
            }
        }
        return i2 < -1 ? -(i2 + 2) : i2;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public final int getDelayMillis() {
        return 0;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public final int getDurationMillis() {
        return this.durationMillis;
    }

    public final float getEasedTimeFromIndex(int i, int i2, boolean z) {
        Easing easing;
        float f;
        MutableIntList mutableIntList = this.timestamps;
        if (i >= mutableIntList._size - 1) {
            f = i2;
        } else {
            int i3 = mutableIntList.get(i);
            int i4 = mutableIntList.get(i + 1);
            if (i2 != i3) {
                int i5 = i4 - i3;
                VectorizedKeyframeSpecElementInfo vectorizedKeyframeSpecElementInfo = (VectorizedKeyframeSpecElementInfo) this.keyframes.get(i3);
                if (vectorizedKeyframeSpecElementInfo == null || (easing = vectorizedKeyframeSpecElementInfo.easing) == null) {
                    easing = this.defaultEasing;
                }
                float f2 = i5;
                float transform = easing.transform((i2 - i3) / f2);
                return z ? transform : ((f2 * transform) + i3) / 1000;
            }
            f = i3;
        }
        return f / 1000;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        AnimationVector animationVector4;
        AnimationVector animationVector5;
        ArcSpline.Arc[][] arcArr;
        AnimationVector animationVector6 = animationVector;
        int i = 1;
        int[] iArr = VectorizedAnimationSpecKt.EmptyIntArray;
        int i2 = 0;
        long j2 = (j / 1000000) - 0;
        int i3 = this.durationMillis;
        long j3 = i3;
        if (j2 < 0) {
            j2 = 0;
        }
        if (j2 <= j3) {
            j3 = j2;
        }
        int i4 = (int) j3;
        MutableIntObjectMap mutableIntObjectMap = this.keyframes;
        VectorizedKeyframeSpecElementInfo vectorizedKeyframeSpecElementInfo = (VectorizedKeyframeSpecElementInfo) mutableIntObjectMap.get(i4);
        if (vectorizedKeyframeSpecElementInfo != null) {
            return vectorizedKeyframeSpecElementInfo.vectorValue;
        }
        if (i4 >= i3) {
            return animationVector2;
        }
        if (i4 <= 0) {
            return animationVector6;
        }
        init(animationVector6, animationVector2, animationVector3);
        AnimationVector animationVector7 = this.valueVector;
        Intrinsics.checkNotNull(animationVector7);
        if (this.arcSpline == VectorizedAnimationSpecKt.EmptyArcSpline) {
            int findEntryForTimeMillis = findEntryForTimeMillis(i4);
            float easedTimeFromIndex = getEasedTimeFromIndex(findEntryForTimeMillis, i4, true);
            MutableIntList mutableIntList = this.timestamps;
            VectorizedKeyframeSpecElementInfo vectorizedKeyframeSpecElementInfo2 = (VectorizedKeyframeSpecElementInfo) mutableIntObjectMap.get(mutableIntList.get(findEntryForTimeMillis));
            if (vectorizedKeyframeSpecElementInfo2 != null && (animationVector5 = vectorizedKeyframeSpecElementInfo2.vectorValue) != null) {
                animationVector6 = animationVector5;
            }
            VectorizedKeyframeSpecElementInfo vectorizedKeyframeSpecElementInfo3 = (VectorizedKeyframeSpecElementInfo) mutableIntObjectMap.get(mutableIntList.get(findEntryForTimeMillis + 1));
            if (vectorizedKeyframeSpecElementInfo3 == null || (animationVector4 = vectorizedKeyframeSpecElementInfo3.vectorValue) == null) {
                animationVector4 = animationVector2;
            }
            int size$animation_core_release = animationVector7.getSize$animation_core_release();
            for (int i5 = 0; i5 < size$animation_core_release; i5++) {
                animationVector7.set$animation_core_release(i5, (animationVector4.get$animation_core_release(i5) * easedTimeFromIndex) + ((1 - easedTimeFromIndex) * animationVector6.get$animation_core_release(i5)));
            }
            return animationVector7;
        }
        float easedTimeFromIndex2 = getEasedTimeFromIndex(findEntryForTimeMillis(i4), i4, false);
        float[] fArr = this.posArray;
        ArcSpline.Arc[][] arcArr2 = this.arcSpline.arcs;
        int length = arcArr2.length - 1;
        float f = arcArr2[0][0].time1;
        float f2 = arcArr2[length][0].time2;
        int length2 = fArr.length;
        if (easedTimeFromIndex2 < f || easedTimeFromIndex2 > f2) {
            if (easedTimeFromIndex2 > f2) {
                f = f2;
            } else {
                length = 0;
            }
            float f3 = easedTimeFromIndex2 - f;
            int i6 = 0;
            int i7 = 0;
            while (i6 < length2 - 1) {
                ArcSpline.Arc arc = arcArr2[length][i7];
                boolean z = arc.isLinear;
                float f4 = arc.ellipseCenterY;
                float f5 = arc.ellipseCenterX;
                if (z) {
                    float f6 = arc.time1;
                    float f7 = arc.oneOverDeltaTime;
                    float f8 = arc.x2;
                    arcArr = arcArr2;
                    float f9 = arc.x1;
                    fArr[i6] = (f5 * f3) + AndroidFlingSpline$$ExternalSyntheticOutline0.m(f8, f9, (f - f6) * f7, f9);
                    float f10 = (f - f6) * f7;
                    float f11 = arc.y2;
                    float f12 = arc.y1;
                    fArr[i6 + 1] = (f4 * f3) + AndroidFlingSpline$$ExternalSyntheticOutline0.m(f11, f12, f10, f12);
                } else {
                    arcArr = arcArr2;
                    arc.setPoint(f);
                    fArr[i6] = (arc.calcDX() * f3) + (arc.ellipseA * arc.tmpSinAngle) + f5;
                    fArr[i6 + 1] = (arc.calcDY() * f3) + (arc.ellipseB * arc.tmpCosAngle) + f4;
                }
                i6 += 2;
                i = 1;
                i7++;
                arcArr2 = arcArr;
            }
        } else {
            int length3 = arcArr2.length;
            int i8 = 0;
            boolean z2 = false;
            while (i8 < length3) {
                int i9 = i2;
                int i10 = i9;
                while (i9 < length2 - 1) {
                    ArcSpline.Arc arc2 = arcArr2[i8][i10];
                    if (easedTimeFromIndex2 <= arc2.time2) {
                        if (arc2.isLinear) {
                            float f13 = arc2.time1;
                            float f14 = arc2.oneOverDeltaTime;
                            float f15 = arc2.x2;
                            float f16 = arc2.x1;
                            fArr[i9] = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f15, f16, (easedTimeFromIndex2 - f13) * f14, f16);
                            float f17 = arc2.y2;
                            float f18 = arc2.y1;
                            fArr[i9 + 1] = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f17, f18, (easedTimeFromIndex2 - f13) * f14, f18);
                        } else {
                            arc2.setPoint(easedTimeFromIndex2);
                            fArr[i9] = (arc2.ellipseA * arc2.tmpSinAngle) + arc2.ellipseCenterX;
                            fArr[i9 + 1] = (arc2.ellipseB * arc2.tmpCosAngle) + arc2.ellipseCenterY;
                        }
                        z2 = true;
                    }
                    i9 += 2;
                    i10++;
                }
                if (z2) {
                    break;
                }
                i8++;
                i2 = 0;
            }
        }
        int length4 = fArr.length;
        for (int i11 = 0; i11 < length4; i11 += i) {
            animationVector7.set$animation_core_release(i11, fArr[i11]);
        }
        return animationVector7;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        int[] iArr = VectorizedAnimationSpecKt.EmptyIntArray;
        long j2 = (j / 1000000) - 0;
        long j3 = this.durationMillis;
        if (j2 < 0) {
            j2 = 0;
        }
        long j4 = j2 > j3 ? j3 : j2;
        if (j4 < 0) {
            return animationVector3;
        }
        init(animationVector, animationVector2, animationVector3);
        AnimationVector animationVector4 = this.velocityVector;
        Intrinsics.checkNotNull(animationVector4);
        if (this.arcSpline == VectorizedAnimationSpecKt.EmptyArcSpline) {
            AnimationVector valueFromNanos = getValueFromNanos((j4 - 1) * 1000000, animationVector, animationVector2, animationVector3);
            AnimationVector valueFromNanos2 = getValueFromNanos(j4 * 1000000, animationVector, animationVector2, animationVector3);
            int size$animation_core_release = valueFromNanos.getSize$animation_core_release();
            for (int i = 0; i < size$animation_core_release; i++) {
                animationVector4.set$animation_core_release(i, (valueFromNanos.get$animation_core_release(i) - valueFromNanos2.get$animation_core_release(i)) * 1000.0f);
            }
            return animationVector4;
        }
        int i2 = (int) j4;
        float easedTimeFromIndex = getEasedTimeFromIndex(findEntryForTimeMillis(i2), i2, false);
        float[] fArr = this.slopeArray;
        ArcSpline.Arc[][] arcArr = this.arcSpline.arcs;
        float f = arcArr[0][0].time1;
        float f2 = arcArr[arcArr.length - 1][0].time2;
        if (easedTimeFromIndex < f) {
            easedTimeFromIndex = f;
        }
        if (easedTimeFromIndex <= f2) {
            f2 = easedTimeFromIndex;
        }
        int length = fArr.length;
        boolean z = false;
        for (ArcSpline.Arc[] arcArr2 : arcArr) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < length - 1) {
                ArcSpline.Arc arc = arcArr2[i4];
                if (f2 <= arc.time2) {
                    if (arc.isLinear) {
                        fArr[i3] = arc.ellipseCenterX;
                        fArr[i3 + 1] = arc.ellipseCenterY;
                    } else {
                        arc.setPoint(f2);
                        fArr[i3] = arc.calcDX();
                        fArr[i3 + 1] = arc.calcDY();
                    }
                    z = true;
                }
                i3 += 2;
                i4++;
            }
            if (z) {
                break;
            }
        }
        int length2 = fArr.length;
        for (int i5 = 0; i5 < length2; i5++) {
            animationVector4.set$animation_core_release(i5, fArr[i5]);
        }
        return animationVector4;
    }

    public final void init(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        float[] fArr;
        boolean z = this.arcSpline != VectorizedAnimationSpecKt.EmptyArcSpline;
        AnimationVector animationVector4 = this.valueVector;
        MutableIntObjectMap mutableIntObjectMap = this.keyframes;
        MutableIntList mutableIntList = this.timestamps;
        if (animationVector4 == null) {
            this.valueVector = animationVector.newVector$animation_core_release();
            this.velocityVector = animationVector3.newVector$animation_core_release();
            int i = mutableIntList._size;
            float[] fArr2 = new float[i];
            for (int i2 = 0; i2 < i; i2++) {
                fArr2[i2] = mutableIntList.get(i2) / 1000;
            }
            this.times = fArr2;
            int i3 = mutableIntList._size;
            int[] iArr = new int[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                iArr[i4] = 0;
            }
            this.modes = iArr;
        }
        if (z) {
            if (this.arcSpline != VectorizedAnimationSpecKt.EmptyArcSpline && Intrinsics.areEqual(this.lastInitialValue, animationVector) && Intrinsics.areEqual(this.lastTargetValue, animationVector2)) {
                return;
            }
            this.lastInitialValue = animationVector;
            this.lastTargetValue = animationVector2;
            int size$animation_core_release = animationVector.getSize$animation_core_release() + (animationVector.getSize$animation_core_release() % 2);
            this.posArray = new float[size$animation_core_release];
            this.slopeArray = new float[size$animation_core_release];
            int i5 = mutableIntList._size;
            float[][] fArr3 = new float[i5][];
            for (int i6 = 0; i6 < i5; i6++) {
                int i7 = mutableIntList.get(i6);
                VectorizedKeyframeSpecElementInfo vectorizedKeyframeSpecElementInfo = (VectorizedKeyframeSpecElementInfo) mutableIntObjectMap.get(i7);
                if (i7 == 0 && vectorizedKeyframeSpecElementInfo == null) {
                    fArr = new float[size$animation_core_release];
                    for (int i8 = 0; i8 < size$animation_core_release; i8++) {
                        fArr[i8] = animationVector.get$animation_core_release(i8);
                    }
                } else if (i7 == this.durationMillis && vectorizedKeyframeSpecElementInfo == null) {
                    fArr = new float[size$animation_core_release];
                    for (int i9 = 0; i9 < size$animation_core_release; i9++) {
                        fArr[i9] = animationVector2.get$animation_core_release(i9);
                    }
                } else {
                    Intrinsics.checkNotNull(vectorizedKeyframeSpecElementInfo);
                    fArr = new float[size$animation_core_release];
                    for (int i10 = 0; i10 < size$animation_core_release; i10++) {
                        fArr[i10] = vectorizedKeyframeSpecElementInfo.vectorValue.get$animation_core_release(i10);
                    }
                }
                fArr3[i6] = fArr;
            }
            this.arcSpline = new ArcSpline(this.modes, this.times, fArr3);
        }
    }
}

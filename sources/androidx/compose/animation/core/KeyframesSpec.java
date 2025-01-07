package androidx.compose.animation.core;

import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.internal.RuntimeHelpersKt;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyframesSpec implements DurationBasedAnimationSpec {
    public final KeyframesSpecConfig config;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class KeyframeEntity extends KeyframeBaseEntity {
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof KeyframeEntity)) {
                return false;
            }
            KeyframeEntity keyframeEntity = (KeyframeEntity) obj;
            return Intrinsics.areEqual(keyframeEntity.value, this.value) && Intrinsics.areEqual(keyframeEntity.easing, this.easing);
        }

        public final int hashCode() {
            Object obj = this.value;
            return this.easing.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(0, (obj != null ? obj.hashCode() : 0) * 31, 31);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class KeyframesSpecConfig extends KeyframesSpecBaseConfig {
    }

    public KeyframesSpec(KeyframesSpecConfig keyframesSpecConfig) {
        this.config = keyframesSpecConfig;
    }

    /* JADX WARN: Type inference failed for: r4v17, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.animation.core.FiniteAnimationSpec, androidx.compose.animation.core.AnimationSpec
    public final VectorizedKeyframesSpec vectorize(TwoWayConverter twoWayConverter) {
        int[] iArr;
        Object[] objArr;
        int[] iArr2;
        Object[] objArr2;
        int i;
        KeyframesSpecConfig keyframesSpecConfig = this.config;
        MutableIntObjectMap mutableIntObjectMap = keyframesSpecConfig.keyframes;
        MutableIntList mutableIntList = new MutableIntList(mutableIntObjectMap._size + 2);
        MutableIntObjectMap mutableIntObjectMap2 = new MutableIntObjectMap(mutableIntObjectMap._size);
        int[] iArr3 = mutableIntObjectMap.keys;
        Object[] objArr3 = mutableIntObjectMap.values;
        long[] jArr = mutableIntObjectMap.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8;
                    int i4 = 8 - ((~(i2 - length)) >>> 31);
                    int i5 = 0;
                    while (i5 < i4) {
                        if ((255 & j) < 128) {
                            int i6 = (i2 << 3) + i5;
                            int i7 = iArr3[i6];
                            KeyframeEntity keyframeEntity = (KeyframeEntity) objArr3[i6];
                            mutableIntList.add(i7);
                            iArr2 = iArr3;
                            objArr2 = objArr3;
                            mutableIntObjectMap2.set(i7, new VectorizedKeyframeSpecElementInfo((AnimationVector) ((TwoWayConverterImpl) twoWayConverter).convertToVector.invoke(keyframeEntity.value), keyframeEntity.easing));
                            i = 8;
                        } else {
                            iArr2 = iArr3;
                            objArr2 = objArr3;
                            i = i3;
                        }
                        j >>= i;
                        i5++;
                        i3 = i;
                        iArr3 = iArr2;
                        objArr3 = objArr2;
                    }
                    iArr = iArr3;
                    objArr = objArr3;
                    if (i4 != i3) {
                        break;
                    }
                } else {
                    iArr = iArr3;
                    objArr = objArr3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
                iArr3 = iArr;
                objArr3 = objArr;
            }
        }
        if (!mutableIntObjectMap.contains(0)) {
            int i8 = mutableIntList._size;
            if (i8 >= 0) {
                mutableIntList.ensureCapacity(i8 + 1);
                int[] iArr4 = mutableIntList.content;
                int i9 = mutableIntList._size;
                if (i9 != 0) {
                    ArraysKt.copyInto(1, 0, i9, iArr4, iArr4);
                }
                iArr4[0] = 0;
                mutableIntList._size++;
            } else {
                RuntimeHelpersKt.throwIndexOutOfBoundsException("Index must be between 0 and size");
                throw null;
            }
        }
        if (!mutableIntObjectMap.contains(keyframesSpecConfig.durationMillis)) {
            mutableIntList.add(keyframesSpecConfig.durationMillis);
        }
        int i10 = mutableIntList._size;
        if (i10 != 0) {
            Arrays.sort(mutableIntList.content, 0, i10);
        }
        return new VectorizedKeyframesSpec(mutableIntList, mutableIntObjectMap2, keyframesSpecConfig.durationMillis, EasingKt.LinearEasing);
    }
}

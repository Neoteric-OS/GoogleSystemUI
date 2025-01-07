package androidx.compose.animation.graphics.vector;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import java.util.ArrayList;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PropertyValues$createAnimationSpec$1 extends Lambda implements Function3 {
    final /* synthetic */ int $overallDuration;
    final /* synthetic */ PropertyValues this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertyValues$createAnimationSpec$1(PropertyValues propertyValues, int i) {
        super(3);
        this.this$0 = propertyValues;
        this.$overallDuration = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0, types: [androidx.compose.animation.core.DurationBasedAnimationSpec] */
    /* JADX WARN: Type inference failed for: r14v2, types: [androidx.compose.animation.core.RepeatableSpec] */
    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyframesSpec keyframesSpec;
        Transition.Segment segment = (Transition.Segment) obj;
        ((Number) obj3).intValue();
        ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
        composerImpl.startReplaceGroup(-361329948);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ArrayList arrayList = (ArrayList) this.this$0.timestamps;
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Timestamp timestamp = (Timestamp) arrayList.get(i);
            Integer valueOf = Integer.valueOf(timestamp.timeMillis);
            PropertyValuesHolder1D propertyValuesHolder1D = timestamp.holder;
            boolean z = propertyValuesHolder1D instanceof PropertyValuesHolderFloat;
            final int i2 = timestamp.durationMillis;
            if (z) {
                final PropertyValuesHolderFloat propertyValuesHolderFloat = (PropertyValuesHolderFloat) propertyValuesHolder1D;
                propertyValuesHolderFloat.getClass();
                Function1 function1 = new Function1() { // from class: androidx.compose.animation.graphics.vector.PropertyValuesHolderFloat$asKeyframeSpec$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig = (KeyframesSpec.KeyframesSpecConfig) obj4;
                        int i3 = i2;
                        keyframesSpecConfig.durationMillis = i3;
                        ArrayList arrayList3 = (ArrayList) propertyValuesHolderFloat.animatorKeyframes;
                        int size2 = arrayList3.size();
                        for (int i4 = 0; i4 < size2; i4++) {
                            Keyframe keyframe = (Keyframe) arrayList3.get(i4);
                            Object obj5 = keyframe.value;
                            int i5 = (int) (i3 * keyframe.fraction);
                            KeyframesSpec.KeyframeEntity keyframeEntity = new KeyframesSpec.KeyframeEntity(obj5, EasingKt.LinearEasing);
                            keyframesSpecConfig.keyframes.set(i5, keyframeEntity);
                            keyframeEntity.easing = keyframe.interpolator;
                        }
                        return Unit.INSTANCE;
                    }
                };
                KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig = new KeyframesSpec.KeyframesSpecConfig();
                function1.invoke(keyframesSpecConfig);
                keyframesSpec = new KeyframesSpec(keyframesSpecConfig);
            } else {
                if (!(propertyValuesHolder1D instanceof PropertyValuesHolderColor)) {
                    throw new RuntimeException("Unexpected value type: " + propertyValuesHolder1D);
                }
                final PropertyValuesHolderColor propertyValuesHolderColor = (PropertyValuesHolderColor) propertyValuesHolder1D;
                propertyValuesHolderColor.getClass();
                Function1 function12 = new Function1() { // from class: androidx.compose.animation.graphics.vector.PropertyValuesHolderColor$asKeyframeSpec$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig2 = (KeyframesSpec.KeyframesSpecConfig) obj4;
                        int i3 = i2;
                        keyframesSpecConfig2.durationMillis = i3;
                        ArrayList arrayList3 = (ArrayList) propertyValuesHolderColor.animatorKeyframes;
                        int size2 = arrayList3.size();
                        for (int i4 = 0; i4 < size2; i4++) {
                            Keyframe keyframe = (Keyframe) arrayList3.get(i4);
                            Object obj5 = keyframe.value;
                            int i5 = (int) (i3 * keyframe.fraction);
                            KeyframesSpec.KeyframeEntity keyframeEntity = new KeyframesSpec.KeyframeEntity(obj5, EasingKt.LinearEasing);
                            keyframesSpecConfig2.keyframes.set(i5, keyframeEntity);
                            keyframeEntity.easing = keyframe.interpolator;
                        }
                        return Unit.INSTANCE;
                    }
                };
                KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig2 = new KeyframesSpec.KeyframesSpecConfig();
                function12.invoke(keyframesSpecConfig2);
                keyframesSpec = new KeyframesSpec(keyframesSpecConfig2);
            }
            Object obj4 = keyframesSpec;
            int i3 = timestamp.repeatCount;
            if (i3 != 0) {
                obj4 = AnimationSpecKt.m10repeatable91I0pcU$default(i3 == -1 ? Integer.MAX_VALUE : i3 + 1, obj4, timestamp.repeatMode, 0L, 8);
            }
            arrayList2.add(new Pair(valueOf, obj4));
        }
        FiniteAnimationSpec combinedSpec = new CombinedSpec(arrayList2);
        if (!((Boolean) segment.getTargetState()).booleanValue()) {
            combinedSpec = new ReversedSpec(combinedSpec, this.$overallDuration);
        }
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        composerImpl.end(false);
        return combinedSpec;
    }
}

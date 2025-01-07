package androidx.compose.animation.graphics.res;

import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.graphics.vector.AnimatedImageVector;
import androidx.compose.animation.graphics.vector.AnimatedVectorTarget;
import androidx.compose.animation.graphics.vector.Animator;
import androidx.compose.animation.graphics.vector.StateVectorConfig;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorPainter;
import androidx.compose.ui.graphics.vector.VectorPainterKt;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AnimatedVectorPainterResources_androidKt {
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.animation.graphics.res.AnimatedVectorPainterResources_androidKt$rememberAnimatedVectorPainter$1, kotlin.jvm.internal.Lambda] */
    public static final VectorPainter rememberAnimatedVectorPainter(final AnimatedImageVector animatedImageVector, final boolean z, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposableLambdaImpl composableLambdaImpl = ComposableSingletons$AnimatedVectorPainterResources_androidKt.f0lambda1;
        ImageVector imageVector = animatedImageVector.imageVector;
        ComposableLambdaImpl rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(10512245, new Function4() { // from class: androidx.compose.animation.graphics.res.AnimatedVectorPainterResources_androidKt$rememberAnimatedVectorPainter$1
            final /* synthetic */ Function4 $render = ComposableSingletons$AnimatedVectorPainterResources_androidKt.f0lambda1;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                ((Number) obj).floatValue();
                ((Number) obj2).floatValue();
                Composer composer2 = (Composer) obj3;
                if ((((Number) obj4).intValue() & 129) == 128) {
                    ComposerImpl composerImpl = (ComposerImpl) composer2;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Transition updateTransition = TransitionKt.updateTransition(Boolean.valueOf(z), animatedImageVector.imageVector.name, composer2, 0);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                composerImpl2.startReplaceGroup(244959712);
                AnimatedImageVector animatedImageVector2 = animatedImageVector;
                ArrayList arrayList = (ArrayList) animatedImageVector2.targets;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    AnimatedVectorTarget animatedVectorTarget = (AnimatedVectorTarget) arrayList.get(i);
                    Animator animator = animatedVectorTarget.animator;
                    OpaqueKey opaqueKey3 = ComposerKt.invocation;
                    composerImpl2.startReplaceGroup(-1031781866);
                    Object rememberedValue = composerImpl2.rememberedValue();
                    if (rememberedValue == Composer.Companion.Empty) {
                        rememberedValue = new StateVectorConfig();
                        composerImpl2.updateRememberedValue(rememberedValue);
                    }
                    StateVectorConfig stateVectorConfig = (StateVectorConfig) rememberedValue;
                    animator.Configure(updateTransition, stateVectorConfig, animatedImageVector2.totalDuration, composerImpl2, 0);
                    composerImpl2.end(false);
                    String str = animatedVectorTarget.name;
                    StateVectorConfig stateVectorConfig2 = (StateVectorConfig) linkedHashMap.get(str);
                    if (stateVectorConfig2 != null) {
                        State state = stateVectorConfig.rotationState;
                        if (state != null) {
                            stateVectorConfig2.rotationState = state;
                        }
                        State state2 = stateVectorConfig.pivotXState;
                        if (state2 != null) {
                            stateVectorConfig2.pivotXState = state2;
                        }
                        State state3 = stateVectorConfig.pivotYState;
                        if (state3 != null) {
                            stateVectorConfig2.pivotYState = state3;
                        }
                        State state4 = stateVectorConfig.scaleXState;
                        if (state4 != null) {
                            stateVectorConfig2.scaleXState = state4;
                        }
                        State state5 = stateVectorConfig.scaleYState;
                        if (state5 != null) {
                            stateVectorConfig2.scaleYState = state5;
                        }
                        State state6 = stateVectorConfig.translateXState;
                        if (state6 != null) {
                            stateVectorConfig2.translateXState = state6;
                        }
                        State state7 = stateVectorConfig.translateYState;
                        if (state7 != null) {
                            stateVectorConfig2.translateYState = state7;
                        }
                        State state8 = stateVectorConfig.pathDataState;
                        if (state8 != null) {
                            stateVectorConfig2.pathDataState = state8;
                        }
                        State state9 = stateVectorConfig.fillColorState;
                        if (state9 != null) {
                            stateVectorConfig2.fillColorState = state9;
                        }
                        State state10 = stateVectorConfig.strokeColorState;
                        if (state10 != null) {
                            stateVectorConfig2.strokeColorState = state10;
                        }
                        State state11 = stateVectorConfig.strokeWidthState;
                        if (state11 != null) {
                            stateVectorConfig2.strokeWidthState = state11;
                        }
                        State state12 = stateVectorConfig.strokeAlphaState;
                        if (state12 != null) {
                            stateVectorConfig2.strokeAlphaState = state12;
                        }
                        State state13 = stateVectorConfig.fillAlphaState;
                        if (state13 != null) {
                            stateVectorConfig2.fillAlphaState = state13;
                        }
                        State state14 = stateVectorConfig.trimPathStartState;
                        if (state14 != null) {
                            stateVectorConfig2.trimPathStartState = state14;
                        }
                        State state15 = stateVectorConfig.trimPathEndState;
                        if (state15 != null) {
                            stateVectorConfig2.trimPathEndState = state15;
                        }
                        State state16 = stateVectorConfig.trimPathOffsetState;
                        if (state16 != null) {
                            stateVectorConfig2.trimPathOffsetState = state16;
                        }
                    } else {
                        linkedHashMap.put(str, stateVectorConfig);
                    }
                }
                composerImpl2.end(false);
                this.$render.invoke(animatedImageVector.imageVector.root, linkedHashMap, composerImpl2, 0);
                OpaqueKey opaqueKey4 = ComposerKt.invocation;
                return Unit.INSTANCE;
            }
        }, composer);
        return VectorPainterKt.m444rememberVectorPaintervIP8VLU(imageVector.defaultWidth, imageVector.defaultHeight, imageVector.viewportWidth, imageVector.viewportHeight, imageVector.name, imageVector.tintColor, imageVector.tintBlendMode, rememberComposableLambda, composer);
    }
}

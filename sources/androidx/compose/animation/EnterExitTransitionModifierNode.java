package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class EnterExitTransitionModifierNode extends LayoutModifierNodeWithPassThroughIntrinsics {
    public Alignment currentAlignment;
    public EnterTransition enter;
    public ExitTransition exit;
    public GraphicsLayerBlockForEnterExit graphicsLayerBlock;
    public Function0 isEnabled;
    public long lookaheadSize = AnimationModifierKt.InvalidSize;
    public Transition.DeferredAnimation offsetAnimation;
    public Transition.DeferredAnimation sizeAnimation;
    public final Function1 sizeTransitionSpec;
    public Transition.DeferredAnimation slideAnimation;
    public final Function1 slideSpec;
    public Transition transition;

    public EnterExitTransitionModifierNode(Transition transition, Transition.DeferredAnimation deferredAnimation, Transition.DeferredAnimation deferredAnimation2, Transition.DeferredAnimation deferredAnimation3, EnterTransition enterTransition, ExitTransition exitTransition, Function0 function0, GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit) {
        this.transition = transition;
        this.sizeAnimation = deferredAnimation;
        this.offsetAnimation = deferredAnimation2;
        this.slideAnimation = deferredAnimation3;
        this.enter = enterTransition;
        this.exit = exitTransition;
        this.isEnabled = function0;
        this.graphicsLayerBlock = graphicsLayerBlockForEnterExit;
        ConstraintsKt.Constraints$default(0, 0, 0, 0, 15);
        this.sizeTransitionSpec = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$sizeTransitionSpec$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Transition.Segment segment = (Transition.Segment) obj;
                EnterExitState enterExitState = EnterExitState.PreEnter;
                EnterExitState enterExitState2 = EnterExitState.Visible;
                Object obj2 = null;
                if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                    ChangeSize changeSize = ((EnterTransitionImpl) EnterExitTransitionModifierNode.this.enter).data.changeSize;
                    if (changeSize != null) {
                        obj2 = changeSize.animationSpec;
                    }
                } else if (segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                    ChangeSize changeSize2 = ((ExitTransitionImpl) EnterExitTransitionModifierNode.this.exit).data.changeSize;
                    if (changeSize2 != null) {
                        obj2 = changeSize2.animationSpec;
                    }
                } else {
                    obj2 = EnterExitTransitionKt.DefaultSizeAnimationSpec;
                }
                return obj2 == null ? EnterExitTransitionKt.DefaultSizeAnimationSpec : obj2;
            }
        };
        this.slideSpec = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$slideSpec$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Transition.Segment segment = (Transition.Segment) obj;
                EnterExitState enterExitState = EnterExitState.PreEnter;
                EnterExitState enterExitState2 = EnterExitState.Visible;
                if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                    Slide slide = ((EnterTransitionImpl) EnterExitTransitionModifierNode.this.enter).data.slide;
                    return slide != null ? slide.animationSpec : EnterExitTransitionKt.DefaultOffsetAnimationSpec;
                }
                if (!segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultOffsetAnimationSpec;
                }
                Slide slide2 = ((ExitTransitionImpl) EnterExitTransitionModifierNode.this.exit).data.slide;
                return slide2 != null ? slide2.animationSpec : EnterExitTransitionKt.DefaultOffsetAnimationSpec;
            }
        };
    }

    public final Alignment getAlignment() {
        if (this.transition.getSegment().isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
            ChangeSize changeSize = ((EnterTransitionImpl) this.enter).data.changeSize;
            if (changeSize != null) {
                return changeSize.alignment;
            }
            ChangeSize changeSize2 = ((ExitTransitionImpl) this.exit).data.changeSize;
            if (changeSize2 != null) {
                return changeSize2.alignment;
            }
            return null;
        }
        ChangeSize changeSize3 = ((ExitTransitionImpl) this.exit).data.changeSize;
        if (changeSize3 != null) {
            return changeSize3.alignment;
        }
        ChangeSize changeSize4 = ((EnterTransitionImpl) this.enter).data.changeSize;
        if (changeSize4 != null) {
            return changeSize4.alignment;
        }
        return null;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s, reason: not valid java name */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        final TransformOrigin transformOrigin;
        MeasureResult layout$12;
        MeasureResult layout$13;
        if (this.transition.getCurrentState() == ((SnapshotMutableStateImpl) this.transition.targetState$delegate).getValue()) {
            this.currentAlignment = null;
        } else if (this.currentAlignment == null) {
            Alignment alignment = getAlignment();
            if (alignment == null) {
                alignment = Alignment.Companion.TopStart;
            }
            this.currentAlignment = alignment;
        }
        if (measureScope.isLookingAhead()) {
            final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
            long j2 = (mo479measureBRTryo0.width << 32) | (mo479measureBRTryo0.height & 4294967295L);
            this.lookaheadSize = j2;
            layout$13 = measureScope.layout$1((int) (j2 >> 32), (int) (j2 & 4294967295L), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ((Placeable.PlacementScope) obj).place(Placeable.this, 0, 0, 0.0f);
                    return Unit.INSTANCE;
                }
            });
            return layout$13;
        }
        if (!((Boolean) this.isEnabled.invoke()).booleanValue()) {
            final Placeable mo479measureBRTryo02 = measurable.mo479measureBRTryo0(j);
            layout$1 = measureScope.layout$1(mo479measureBRTryo02.width, mo479measureBRTryo02.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$3$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ((Placeable.PlacementScope) obj).place(Placeable.this, 0, 0, 0.0f);
                    return Unit.INSTANCE;
                }
            });
            return layout$1;
        }
        EnterExitTransitionKt$$ExternalSyntheticLambda0 enterExitTransitionKt$$ExternalSyntheticLambda0 = (EnterExitTransitionKt$$ExternalSyntheticLambda0) this.graphicsLayerBlock;
        enterExitTransitionKt$$ExternalSyntheticLambda0.getClass();
        TwoWayConverter twoWayConverter = EnterExitTransitionKt.TransformOriginVectorConverter;
        final EnterTransition enterTransition = enterExitTransitionKt$$ExternalSyntheticLambda0.f$3;
        Transition.DeferredAnimation deferredAnimation = enterExitTransitionKt$$ExternalSyntheticLambda0.f$0;
        final ExitTransition exitTransition = enterExitTransitionKt$$ExternalSyntheticLambda0.f$4;
        final Transition.DeferredAnimation.DeferredAnimationData animate = deferredAnimation != null ? deferredAnimation.animate(new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                FiniteAnimationSpec finiteAnimationSpec;
                FiniteAnimationSpec finiteAnimationSpec2;
                Transition.Segment segment = (Transition.Segment) obj;
                EnterExitState enterExitState = EnterExitState.PreEnter;
                EnterExitState enterExitState2 = EnterExitState.Visible;
                if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                    Fade fade = ((EnterTransitionImpl) EnterTransition.this).data.fade;
                    return (fade == null || (finiteAnimationSpec2 = fade.animationSpec) == null) ? EnterExitTransitionKt.DefaultAlphaAndScaleSpring : finiteAnimationSpec2;
                }
                if (!segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                Fade fade2 = ((ExitTransitionImpl) exitTransition).data.fade;
                return (fade2 == null || (finiteAnimationSpec = fade2.animationSpec) == null) ? EnterExitTransitionKt.DefaultAlphaAndScaleSpring : finiteAnimationSpec;
            }
        }, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
            
                if (((androidx.compose.animation.EnterTransitionImpl) r1).data.fade != null) goto L16;
             */
            /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
            
                if (((androidx.compose.animation.ExitTransitionImpl) r2).data.fade != null) goto L16;
             */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r4) {
                /*
                    r3 = this;
                    androidx.compose.animation.EnterExitState r4 = (androidx.compose.animation.EnterExitState) r4
                    int r4 = r4.ordinal()
                    r0 = 0
                    r1 = 1065353216(0x3f800000, float:1.0)
                    if (r4 == 0) goto L24
                    r2 = 1
                    if (r4 == r2) goto L1c
                    r2 = 2
                    if (r4 != r2) goto L1e
                    androidx.compose.animation.ExitTransition r3 = r2
                    androidx.compose.animation.ExitTransitionImpl r3 = (androidx.compose.animation.ExitTransitionImpl) r3
                    androidx.compose.animation.TransitionData r3 = r3.data
                    androidx.compose.animation.Fade r3 = r3.fade
                    if (r3 == 0) goto L1c
                    goto L2e
                L1c:
                    r0 = r1
                    goto L2e
                L1e:
                    kotlin.NoWhenBranchMatchedException r3 = new kotlin.NoWhenBranchMatchedException
                    r3.<init>()
                    throw r3
                L24:
                    androidx.compose.animation.EnterTransition r3 = androidx.compose.animation.EnterTransition.this
                    androidx.compose.animation.EnterTransitionImpl r3 = (androidx.compose.animation.EnterTransitionImpl) r3
                    androidx.compose.animation.TransitionData r3 = r3.data
                    androidx.compose.animation.Fade r3 = r3.fade
                    if (r3 == 0) goto L1c
                L2e:
                    java.lang.Float r3 = java.lang.Float.valueOf(r0)
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$2.invoke(java.lang.Object):java.lang.Object");
            }
        }) : null;
        Transition.DeferredAnimation deferredAnimation2 = enterExitTransitionKt$$ExternalSyntheticLambda0.f$1;
        final Transition.DeferredAnimation.DeferredAnimationData animate2 = deferredAnimation2 != null ? deferredAnimation2.animate(new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$scale$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Transition.Segment segment = (Transition.Segment) obj;
                EnterExitState enterExitState = EnterExitState.PreEnter;
                EnterExitState enterExitState2 = EnterExitState.Visible;
                if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                    Scale scale = ((EnterTransitionImpl) EnterTransition.this).data.scale;
                    return scale != null ? scale.animationSpec : EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                if (!segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                Scale scale2 = ((ExitTransitionImpl) exitTransition).data.scale;
                return scale2 != null ? scale2.animationSpec : EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
            }
        }, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$scale$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int ordinal = ((EnterExitState) obj).ordinal();
                float f = 1.0f;
                if (ordinal == 0) {
                    Scale scale = ((EnterTransitionImpl) EnterTransition.this).data.scale;
                    if (scale != null) {
                        f = scale.scale;
                    }
                } else if (ordinal != 1) {
                    if (ordinal != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    Scale scale2 = ((ExitTransitionImpl) exitTransition).data.scale;
                    if (scale2 != null) {
                        f = scale2.scale;
                    }
                }
                return Float.valueOf(f);
            }
        }) : null;
        if (enterExitTransitionKt$$ExternalSyntheticLambda0.f$2.getCurrentState() == EnterExitState.PreEnter) {
            Scale scale = ((EnterTransitionImpl) enterTransition).data.scale;
            if (scale != null) {
                transformOrigin = new TransformOrigin(scale.transformOrigin);
            } else {
                Scale scale2 = ((ExitTransitionImpl) exitTransition).data.scale;
                if (scale2 != null) {
                    transformOrigin = new TransformOrigin(scale2.transformOrigin);
                }
                transformOrigin = null;
            }
        } else {
            Scale scale3 = ((ExitTransitionImpl) exitTransition).data.scale;
            if (scale3 != null) {
                transformOrigin = new TransformOrigin(scale3.transformOrigin);
            } else {
                Scale scale4 = ((EnterTransitionImpl) enterTransition).data.scale;
                if (scale4 != null) {
                    transformOrigin = new TransformOrigin(scale4.transformOrigin);
                }
                transformOrigin = null;
            }
        }
        Transition.DeferredAnimation deferredAnimation3 = enterExitTransitionKt$$ExternalSyntheticLambda0.f$5;
        final Transition.DeferredAnimation.DeferredAnimationData animate3 = deferredAnimation3 != null ? deferredAnimation3.animate(new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7);
            }
        }, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int ordinal = ((EnterExitState) obj).ordinal();
                TransformOrigin transformOrigin2 = null;
                if (ordinal == 0) {
                    Scale scale5 = ((EnterTransitionImpl) enterTransition).data.scale;
                    if (scale5 != null) {
                        transformOrigin2 = new TransformOrigin(scale5.transformOrigin);
                    } else {
                        Scale scale6 = ((ExitTransitionImpl) exitTransition).data.scale;
                        if (scale6 != null) {
                            transformOrigin2 = new TransformOrigin(scale6.transformOrigin);
                        }
                    }
                } else if (ordinal == 1) {
                    transformOrigin2 = TransformOrigin.this;
                } else {
                    if (ordinal != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    Scale scale7 = ((ExitTransitionImpl) exitTransition).data.scale;
                    if (scale7 != null) {
                        transformOrigin2 = new TransformOrigin(scale7.transformOrigin);
                    } else {
                        Scale scale8 = ((EnterTransitionImpl) enterTransition).data.scale;
                        if (scale8 != null) {
                            transformOrigin2 = new TransformOrigin(scale8.transformOrigin);
                        }
                    }
                }
                return new TransformOrigin(transformOrigin2 != null ? transformOrigin2.packedValue : TransformOrigin.Center);
            }
        }) : null;
        final Function1 function1 = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$block$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                GraphicsLayerScope graphicsLayerScope = (GraphicsLayerScope) obj;
                State state = animate;
                ReusableGraphicsLayerScope reusableGraphicsLayerScope = (ReusableGraphicsLayerScope) graphicsLayerScope;
                reusableGraphicsLayerScope.setAlpha(state != null ? ((Number) state.getValue()).floatValue() : 1.0f);
                State state2 = animate2;
                reusableGraphicsLayerScope.setScaleX(state2 != null ? ((Number) state2.getValue()).floatValue() : 1.0f);
                State state3 = animate2;
                reusableGraphicsLayerScope.setScaleY(state3 != null ? ((Number) state3.getValue()).floatValue() : 1.0f);
                State state4 = animate3;
                reusableGraphicsLayerScope.m391setTransformOrigin__ExYCQ(state4 != null ? ((TransformOrigin) state4.getValue()).packedValue : TransformOrigin.Center);
                return Unit.INSTANCE;
            }
        };
        final Placeable mo479measureBRTryo03 = measurable.mo479measureBRTryo0(j);
        long j3 = (mo479measureBRTryo03.width << 32) | (mo479measureBRTryo03.height & 4294967295L);
        final long j4 = !IntSize.m683equalsimpl0(this.lookaheadSize, AnimationModifierKt.InvalidSize) ? this.lookaheadSize : j3;
        Transition.DeferredAnimation deferredAnimation4 = this.sizeAnimation;
        Transition.DeferredAnimation.DeferredAnimationData animate4 = deferredAnimation4 != null ? deferredAnimation4.animate(this.sizeTransitionSpec, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$animSize$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Function1 function12;
                Function1 function13;
                EnterExitTransitionModifierNode enterExitTransitionModifierNode = EnterExitTransitionModifierNode.this;
                long j5 = j4;
                enterExitTransitionModifierNode.getClass();
                int ordinal = ((EnterExitState) obj).ordinal();
                if (ordinal == 0) {
                    ChangeSize changeSize = ((EnterTransitionImpl) enterExitTransitionModifierNode.enter).data.changeSize;
                    if (changeSize != null && (function12 = changeSize.size) != null) {
                        j5 = ((IntSize) function12.invoke(new IntSize(j5))).packedValue;
                    }
                } else if (ordinal != 1) {
                    if (ordinal != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    ChangeSize changeSize2 = ((ExitTransitionImpl) enterExitTransitionModifierNode.exit).data.changeSize;
                    if (changeSize2 != null && (function13 = changeSize2.size) != null) {
                        j5 = ((IntSize) function13.invoke(new IntSize(j5))).packedValue;
                    }
                }
                return new IntSize(j5);
            }
        }) : null;
        if (animate4 != null) {
            j3 = ((IntSize) animate4.getValue()).packedValue;
        }
        long m662constrain4WqzIAM = ConstraintsKt.m662constrain4WqzIAM(j, j3);
        Transition.DeferredAnimation deferredAnimation5 = this.offsetAnimation;
        long j5 = deferredAnimation5 != null ? ((IntOffset) deferredAnimation5.animate(new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$offsetDelta$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EnterExitTransitionKt.DefaultOffsetAnimationSpec;
            }
        }, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$offsetDelta$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int ordinal;
                EnterExitState enterExitState = (EnterExitState) obj;
                EnterExitTransitionModifierNode enterExitTransitionModifierNode = EnterExitTransitionModifierNode.this;
                long j6 = j4;
                long j7 = 0;
                if (enterExitTransitionModifierNode.currentAlignment != null && enterExitTransitionModifierNode.getAlignment() != null && !Intrinsics.areEqual(enterExitTransitionModifierNode.currentAlignment, enterExitTransitionModifierNode.getAlignment()) && (ordinal = enterExitState.ordinal()) != 0 && ordinal != 1) {
                    if (ordinal != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    ChangeSize changeSize = ((ExitTransitionImpl) enterExitTransitionModifierNode.exit).data.changeSize;
                    if (changeSize != null) {
                        long j8 = ((IntSize) changeSize.size.invoke(new IntSize(j6))).packedValue;
                        Alignment alignment2 = enterExitTransitionModifierNode.getAlignment();
                        Intrinsics.checkNotNull(alignment2);
                        LayoutDirection layoutDirection = LayoutDirection.Ltr;
                        long mo274alignKFBX0sM = ((BiasAlignment) alignment2).mo274alignKFBX0sM(j6, j8, layoutDirection);
                        Alignment alignment3 = enterExitTransitionModifierNode.currentAlignment;
                        Intrinsics.checkNotNull(alignment3);
                        j7 = IntOffset.m675minusqkQi6aY(mo274alignKFBX0sM, alignment3.mo274alignKFBX0sM(j6, j8, layoutDirection));
                    }
                }
                return new IntOffset(j7);
            }
        }).getValue()).packedValue : 0L;
        Transition.DeferredAnimation deferredAnimation6 = this.slideAnimation;
        long j6 = deferredAnimation6 != null ? ((IntOffset) deferredAnimation6.animate(this.slideSpec, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$slideOffset$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r7v12, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r7v15, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                EnterExitState enterExitState = (EnterExitState) obj;
                EnterExitTransitionModifierNode enterExitTransitionModifierNode = EnterExitTransitionModifierNode.this;
                long j7 = j4;
                Slide slide = ((EnterTransitionImpl) enterExitTransitionModifierNode.enter).data.slide;
                long j8 = 0;
                long j9 = slide != null ? ((IntOffset) slide.slideOffset.invoke(new IntSize(j7))).packedValue : 0L;
                Slide slide2 = ((ExitTransitionImpl) enterExitTransitionModifierNode.exit).data.slide;
                long j10 = slide2 != null ? ((IntOffset) slide2.slideOffset.invoke(new IntSize(j7))).packedValue : 0L;
                int ordinal = enterExitState.ordinal();
                if (ordinal == 0) {
                    j8 = j9;
                } else if (ordinal != 1) {
                    if (ordinal != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    j8 = j10;
                }
                return new IntOffset(j8);
            }
        }).getValue()).packedValue : 0L;
        Alignment alignment2 = this.currentAlignment;
        final long m676plusqkQi6aY = IntOffset.m676plusqkQi6aY(alignment2 != null ? alignment2.mo274alignKFBX0sM(j4, m662constrain4WqzIAM, LayoutDirection.Ltr) : 0L, j6);
        final long j7 = j5;
        layout$12 = measureScope.layout$1((int) (m662constrain4WqzIAM >> 32), (int) (m662constrain4WqzIAM & 4294967295L), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                Placeable placeable = Placeable.this;
                long j8 = m676plusqkQi6aY;
                long j9 = j7;
                Function1 function12 = function1;
                placementScope.getClass();
                Placeable.PlacementScope.access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
                placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY(((((int) (j8 >> 32)) + ((int) (j9 >> 32))) << 32) | ((((int) (j8 & 4294967295L)) + ((int) (j9 & 4294967295L))) & 4294967295L), placeable.apparentToRealOffset), 0.0f, function12);
                return Unit.INSTANCE;
            }
        });
        return layout$12;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        this.lookaheadSize = AnimationModifierKt.InvalidSize;
    }
}

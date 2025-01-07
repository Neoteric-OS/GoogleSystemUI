package androidx.compose.material.ripple;

import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.interaction.DragInteraction$Cancel;
import androidx.compose.foundation.interaction.DragInteraction$Start;
import androidx.compose.foundation.interaction.DragInteraction$Stop;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.HoverInteraction$Exit;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction$Cancel;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.foundation.interaction.PressInteraction$Release;
import androidx.compose.ui.node.DrawModifierNodeKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class RippleNode$onAttach$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ RippleNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RippleNode$onAttach$1(RippleNode rippleNode, Continuation continuation) {
        super(2, continuation);
        this.this$0 = rippleNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        RippleNode$onAttach$1 rippleNode$onAttach$1 = new RippleNode$onAttach$1(this.this$0, continuation);
        rippleNode$onAttach$1.L$0 = obj;
        return rippleNode$onAttach$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((RippleNode$onAttach$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        SharedFlowImpl interactions = this.this$0.interactionSource.getInteractions();
        final RippleNode rippleNode = this.this$0;
        FlowCollector flowCollector = new FlowCollector() { // from class: androidx.compose.material.ripple.RippleNode$onAttach$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                float f;
                Interaction interaction = (Interaction) obj2;
                boolean z = interaction instanceof PressInteraction$Press;
                RippleNode rippleNode2 = RippleNode.this;
                if (z) {
                    rippleNode2.addRipple((PressInteraction$Press) interaction);
                } else if (interaction instanceof PressInteraction$Release) {
                    rippleNode2.removeRipple(((PressInteraction$Release) interaction).press);
                } else if (interaction instanceof PressInteraction$Cancel) {
                    rippleNode2.removeRipple(((PressInteraction$Cancel) interaction).press);
                } else {
                    StateLayer stateLayer = rippleNode2.stateLayer;
                    if (stateLayer == null) {
                        stateLayer = new StateLayer(rippleNode2.rippleAlpha, rippleNode2.bounded);
                        DrawModifierNodeKt.invalidateDraw(rippleNode2);
                        rippleNode2.stateLayer = stateLayer;
                    }
                    boolean z2 = interaction instanceof HoverInteraction$Enter;
                    if (z2) {
                        stateLayer.interactions.add(interaction);
                    } else if (interaction instanceof HoverInteraction$Exit) {
                        stateLayer.interactions.remove(((HoverInteraction$Exit) interaction).enter);
                    } else if (interaction instanceof FocusInteraction$Focus) {
                        stateLayer.interactions.add(interaction);
                    } else if (interaction instanceof FocusInteraction$Unfocus) {
                        stateLayer.interactions.remove(((FocusInteraction$Unfocus) interaction).focus);
                    } else if (interaction instanceof DragInteraction$Start) {
                        stateLayer.interactions.add(interaction);
                    } else if (interaction instanceof DragInteraction$Stop) {
                        stateLayer.interactions.remove(((DragInteraction$Stop) interaction).start);
                    } else if (interaction instanceof DragInteraction$Cancel) {
                        stateLayer.interactions.remove(((DragInteraction$Cancel) interaction).start);
                    }
                    Interaction interaction2 = (Interaction) CollectionsKt.lastOrNull(stateLayer.interactions);
                    if (!Intrinsics.areEqual(stateLayer.currentInteraction, interaction2)) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        if (interaction2 != null) {
                            RippleAlpha rippleAlpha = (RippleAlpha) stateLayer.rippleAlpha.invoke();
                            if (z2) {
                                rippleAlpha.getClass();
                                f = 0.08f;
                            } else if (interaction instanceof FocusInteraction$Focus) {
                                rippleAlpha.getClass();
                                f = 0.1f;
                            } else if (interaction instanceof DragInteraction$Start) {
                                rippleAlpha.getClass();
                                f = 0.16f;
                            } else {
                                f = 0.0f;
                            }
                            TweenSpec tweenSpec = RippleKt.DefaultTweenSpec;
                            boolean z3 = interaction2 instanceof HoverInteraction$Enter;
                            TweenSpec tweenSpec2 = RippleKt.DefaultTweenSpec;
                            if (!z3) {
                                if (interaction2 instanceof FocusInteraction$Focus) {
                                    tweenSpec2 = new TweenSpec(45, 0, EasingKt.LinearEasing);
                                } else if (interaction2 instanceof DragInteraction$Start) {
                                    tweenSpec2 = new TweenSpec(45, 0, EasingKt.LinearEasing);
                                }
                            }
                            BuildersKt.launch$default(coroutineScope2, null, null, new StateLayer$handleInteraction$1(stateLayer, f, tweenSpec2, null), 3);
                        } else {
                            Interaction interaction3 = stateLayer.currentInteraction;
                            TweenSpec tweenSpec3 = RippleKt.DefaultTweenSpec;
                            boolean z4 = interaction3 instanceof HoverInteraction$Enter;
                            TweenSpec tweenSpec4 = RippleKt.DefaultTweenSpec;
                            if (!z4 && !(interaction3 instanceof FocusInteraction$Focus) && (interaction3 instanceof DragInteraction$Start)) {
                                tweenSpec4 = new TweenSpec(150, 0, EasingKt.LinearEasing);
                            }
                            BuildersKt.launch$default(coroutineScope2, null, null, new StateLayer$handleInteraction$2(stateLayer, tweenSpec4, null), 3);
                        }
                        stateLayer.currentInteraction = interaction2;
                    }
                }
                return Unit.INSTANCE;
            }
        };
        this.label = 1;
        interactions.collect(flowCollector, this);
        return coroutineSingletons;
    }
}

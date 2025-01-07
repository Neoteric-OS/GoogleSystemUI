package com.android.systemui.brightness.ui.compose;

import androidx.activity.compose.PredictiveBackHandlerKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.systemui.brightness.shared.model.GammaBrightness;
import com.android.systemui.brightness.ui.viewmodel.BrightnessSliderViewModel;
import com.android.systemui.brightness.ui.viewmodel.Drag;
import com.android.systemui.utils.PolicyRestriction;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BrightnessSliderKt {
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0040, code lost:
    
        if (r3.changed(r28) != false) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v25, types: [com.android.systemui.brightness.ui.compose.BrightnessSliderKt$BrightnessSlider$5, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r4v26, types: [com.android.systemui.brightness.ui.compose.BrightnessSliderKt$BrightnessSlider$6, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BrightnessSlider(final int r28, final kotlin.ranges.IntRange r29, final com.android.systemui.common.shared.model.Text.Resource r30, final com.android.systemui.common.shared.model.Icon r31, final com.android.systemui.utils.PolicyRestriction r32, final kotlin.jvm.functions.Function1 r33, final kotlin.jvm.functions.Function1 r34, final kotlin.jvm.functions.Function1 r35, androidx.compose.ui.Modifier r36, kotlin.jvm.functions.Function1 r37, androidx.compose.runtime.Composer r38, final int r39, final int r40) {
        /*
            Method dump skipped, instructions count: 442
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.brightness.ui.compose.BrightnessSliderKt.BrightnessSlider(int, kotlin.ranges.IntRange, com.android.systemui.common.shared.model.Text$Resource, com.android.systemui.common.shared.model.Icon, com.android.systemui.utils.PolicyRestriction, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void BrightnessSliderContainer(final BrightnessSliderViewModel brightnessSliderViewModel, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1188885134);
        Modifier modifier2 = (i2 & 2) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int i3 = ((GammaBrightness) FlowExtKt.collectAsStateWithLifecycle(brightnessSliderViewModel.currentBrightness, composerImpl).getValue()).value;
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            rememberedValue = PredictiveBackHandlerKt$$ExternalSyntheticOutline0.m(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerImpl), composerImpl);
        }
        final ContextScope contextScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).coroutineScope;
        final Modifier modifier3 = modifier2;
        BrightnessSlider(i3, new IntRange(0, brightnessSliderViewModel.maxBrightness, 1), brightnessSliderViewModel.label, brightnessSliderViewModel.icon, (PolicyRestriction) FlowExtKt.collectAsStateWithLifecycle(brightnessSliderViewModel.policyRestriction, PolicyRestriction.NoRestriction.INSTANCE, composerImpl, 56).getValue(), new BrightnessSliderKt$BrightnessSliderContainer$1(1, brightnessSliderViewModel, BrightnessSliderViewModel.class, "showPolicyRestrictionDialog", "showPolicyRestrictionDialog(Lcom/android/systemui/utils/PolicyRestriction$Restricted;)V", 0), new Function1() { // from class: com.android.systemui.brightness.ui.compose.BrightnessSliderKt$BrightnessSliderContainer$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.brightness.ui.compose.BrightnessSliderKt$BrightnessSliderContainer$2$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function2 {
                final /* synthetic */ int $it;
                final /* synthetic */ BrightnessSliderViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(BrightnessSliderViewModel brightnessSliderViewModel, int i, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = brightnessSliderViewModel;
                    this.$it = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass1(this.$viewModel, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        BrightnessSliderViewModel brightnessSliderViewModel = this.$viewModel;
                        Drag.Dragging dragging = new Drag.Dragging(this.$it);
                        this.label = 1;
                        if (brightnessSliderViewModel.onDrag(dragging, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                BuildersKt.launch$default(contextScope, null, null, new AnonymousClass1(brightnessSliderViewModel, ((Number) obj).intValue(), null), 3);
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.android.systemui.brightness.ui.compose.BrightnessSliderKt$BrightnessSliderContainer$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.brightness.ui.compose.BrightnessSliderKt$BrightnessSliderContainer$3$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function2 {
                final /* synthetic */ int $it;
                final /* synthetic */ BrightnessSliderViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(BrightnessSliderViewModel brightnessSliderViewModel, int i, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = brightnessSliderViewModel;
                    this.$it = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass1(this.$viewModel, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        BrightnessSliderViewModel brightnessSliderViewModel = this.$viewModel;
                        Drag.Stopped stopped = new Drag.Stopped(this.$it);
                        this.label = 1;
                        if (brightnessSliderViewModel.onDrag(stopped, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                BuildersKt.launch$default(contextScope, null, null, new AnonymousClass1(brightnessSliderViewModel, ((Number) obj).intValue(), null), 3);
                return Unit.INSTANCE;
            }
        }, SizeKt.fillMaxWidth(modifier2, 1.0f), new BrightnessSliderKt$BrightnessSliderContainer$4(1, brightnessSliderViewModel, BrightnessSliderViewModel.class, "formatValue", "formatValue(I)Ljava/lang/String;", 0), composerImpl, 64, 0);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.brightness.ui.compose.BrightnessSliderKt$BrightnessSliderContainer$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    BrightnessSliderKt.BrightnessSliderContainer(BrightnessSliderViewModel.this, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}

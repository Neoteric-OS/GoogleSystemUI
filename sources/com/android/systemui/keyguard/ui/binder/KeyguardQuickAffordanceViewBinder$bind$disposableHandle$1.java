package com.android.systemui.keyguard.ui.binder;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.drawable.Animatable2;
import android.view.View;
import android.widget.ImageView;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.core.animation.Animator;
import androidx.core.animation.CycleInterpolator;
import androidx.core.animation.ObjectAnimator;
import androidx.core.animation.PropertyValuesHolder;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.settingslib.Utils;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.ui.binder.IconViewBinder;
import com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordanceViewModel;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.VibratorHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardQuickAffordanceViewBinder$bind$disposableHandle$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Flow $alpha;
    final /* synthetic */ ImageView $button;
    final /* synthetic */ MutableStateFlow $configurationBasedDimensions;
    final /* synthetic */ Function1 $messageDisplayer;
    final /* synthetic */ Flow $viewModel;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ KeyguardQuickAffordanceViewBinder this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$bind$disposableHandle$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Flow $alpha;
        final /* synthetic */ ImageView $button;
        final /* synthetic */ MutableStateFlow $configurationBasedDimensions;
        final /* synthetic */ Function1 $messageDisplayer;
        final /* synthetic */ Flow $viewModel;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ KeyguardQuickAffordanceViewBinder this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$bind$disposableHandle$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C01251 extends SuspendLambda implements Function2 {
            final /* synthetic */ ImageView $button;
            final /* synthetic */ Function1 $messageDisplayer;
            final /* synthetic */ Flow $viewModel;
            int label;
            final /* synthetic */ KeyguardQuickAffordanceViewBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01251(Flow flow, KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder, ImageView imageView, Function1 function1, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = flow;
                this.this$0 = keyguardQuickAffordanceViewBinder;
                this.$button = imageView;
                this.$messageDisplayer = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01251(this.$viewModel, this.this$0, this.$button, this.$messageDisplayer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C01251) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = this.$viewModel;
                    final KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder = this.this$0;
                    final ImageView imageView = this.$button;
                    final Function1 function1 = this.$messageDisplayer;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder.bind.disposableHandle.1.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            ColorStateList colorStateList;
                            final KeyguardQuickAffordanceViewModel keyguardQuickAffordanceViewModel = (KeyguardQuickAffordanceViewModel) obj2;
                            final ImageView imageView2 = imageView;
                            final KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder2 = KeyguardQuickAffordanceViewBinder.this;
                            keyguardQuickAffordanceViewBinder2.logger.logUpdate(keyguardQuickAffordanceViewModel);
                            if (keyguardQuickAffordanceViewModel.isVisible) {
                                if (imageView2.getVisibility() != 0) {
                                    imageView2.setVisibility(0);
                                }
                                Icon icon = keyguardQuickAffordanceViewModel.icon;
                                IconViewBinder.bind(icon, imageView2);
                                Object drawable = imageView2.getDrawable();
                                Animatable2 animatable2 = drawable instanceof Animatable2 ? (Animatable2) drawable : null;
                                if (animatable2 != null) {
                                    Icon.Resource resource = icon instanceof Icon.Resource ? (Icon.Resource) icon : null;
                                    if (resource != null) {
                                        animatable2.start();
                                        Object tag = imageView2.getTag();
                                        int i2 = resource.res;
                                        if (Intrinsics.areEqual(tag, Integer.valueOf(i2))) {
                                            animatable2.stop();
                                        } else {
                                            imageView2.setTag(Integer.valueOf(i2));
                                        }
                                    }
                                }
                                boolean z = keyguardQuickAffordanceViewModel.isActivated;
                                imageView2.setActivated(z);
                                imageView2.getDrawable().setTint(Utils.getColorAttrDefaultColor(z ? R.^attr-private.materialColorOnPrimaryFixed : R.^attr-private.materialColorOnSurface, 0, imageView2.getContext()));
                                boolean z2 = keyguardQuickAffordanceViewModel.isSelected;
                                if (z2) {
                                    colorStateList = null;
                                } else {
                                    colorStateList = Utils.getColorAttr(z ? R.^attr-private.materialColorPrimaryFixed : R.^attr-private.materialColorSurfaceContainerHigh, imageView2.getContext());
                                }
                                imageView2.setBackgroundTintList(colorStateList);
                                imageView2.animate().scaleX(z2 ? 1.23f : 1.0f).scaleY(z2 ? 1.23f : 1.0f).start();
                                boolean z3 = keyguardQuickAffordanceViewModel.isClickable;
                                imageView2.setClickable(z3);
                                if (z3) {
                                    boolean z4 = keyguardQuickAffordanceViewModel.useLongPress;
                                    FalsingManager falsingManager = keyguardQuickAffordanceViewBinder2.falsingManager;
                                    if (z4) {
                                        VibratorHelper vibratorHelper = keyguardQuickAffordanceViewBinder2.vibratorHelper;
                                        KeyguardQuickAffordanceOnTouchListener keyguardQuickAffordanceOnTouchListener = new KeyguardQuickAffordanceOnTouchListener(imageView2, keyguardQuickAffordanceViewModel, vibratorHelper, falsingManager);
                                        imageView2.setOnTouchListener(keyguardQuickAffordanceOnTouchListener);
                                        final Function1 function12 = function1;
                                        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButton$2
                                            @Override // android.view.View.OnClickListener
                                            public final void onClick(View view) {
                                                Function1.this.invoke(Integer.valueOf(com.android.wm.shell.R.string.keyguard_affordance_press_too_short));
                                                float dimensionPixelSize = imageView2.getContext().getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.keyguard_affordance_shake_amplitude);
                                                ImageView imageView3 = imageView2;
                                                float f = 2;
                                                float[] fArr = {(-dimensionPixelSize) / f, dimensionPixelSize / f};
                                                ObjectAnimator objectAnimator = new ObjectAnimator();
                                                objectAnimator.setTarget(imageView3);
                                                PropertyValuesHolder[] propertyValuesHolderArr = objectAnimator.mValues;
                                                if (propertyValuesHolderArr != null) {
                                                    PropertyValuesHolder propertyValuesHolder = propertyValuesHolderArr[0];
                                                    String str = propertyValuesHolder.mPropertyName;
                                                    propertyValuesHolder.mPropertyName = "translationX";
                                                    objectAnimator.mValuesMap.remove(str);
                                                    objectAnimator.mValuesMap.put("translationX", propertyValuesHolder);
                                                }
                                                objectAnimator.mPropertyName = "translationX";
                                                objectAnimator.mInitialized = false;
                                                objectAnimator.setFloatValues(fArr);
                                                objectAnimator.m717setDuration(Duration.m1777getInWholeMillisecondsimpl(KeyguardBottomAreaVibrations.ShakeAnimationDuration));
                                                objectAnimator.mInterpolator = new CycleInterpolator();
                                                final ImageView imageView4 = imageView2;
                                                objectAnimator.addListener(new Animator.AnimatorListener() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButton$2$onClick$$inlined$doOnEnd$1
                                                    @Override // androidx.core.animation.Animator.AnimatorListener
                                                    public final void onAnimationEnd$1(Animator animator) {
                                                        imageView4.setTranslationX(0.0f);
                                                    }

                                                    @Override // androidx.core.animation.Animator.AnimatorListener
                                                    public final void onAnimationCancel() {
                                                    }

                                                    @Override // androidx.core.animation.Animator.AnimatorListener
                                                    public final void onAnimationStart$1() {
                                                    }
                                                });
                                                objectAnimator.start();
                                                VibratorHelper vibratorHelper2 = keyguardQuickAffordanceViewBinder2.vibratorHelper;
                                                if (vibratorHelper2 != null) {
                                                    vibratorHelper2.vibrate(KeyguardBottomAreaVibrations.Shake);
                                                }
                                                keyguardQuickAffordanceViewBinder2.logger.logQuickAffordanceTapped(keyguardQuickAffordanceViewModel.configKey);
                                            }
                                        });
                                        imageView2.setOnLongClickListener(new KeyguardQuickAffordanceViewBinder.OnLongClickListener(falsingManager, keyguardQuickAffordanceViewModel, vibratorHelper, keyguardQuickAffordanceOnTouchListener));
                                    } else {
                                        if (falsingManager == null) {
                                            throw new IllegalStateException("Required value was null.");
                                        }
                                        imageView2.setOnClickListener(new KeyguardQuickAffordanceViewBinder.OnClickListener(keyguardQuickAffordanceViewModel, falsingManager));
                                    }
                                } else {
                                    imageView2.setOnLongClickListener(null);
                                    imageView2.setOnClickListener(null);
                                    imageView2.setOnTouchListener(null);
                                }
                                imageView2.setSelected(z2);
                            } else {
                                imageView2.setVisibility(4);
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (flow.collect(flowCollector, this) == coroutineSingletons) {
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

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$bind$disposableHandle$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ Flow $alpha;
            final /* synthetic */ ImageView $button;
            final /* synthetic */ Flow $viewModel;
            int label;
            final /* synthetic */ KeyguardQuickAffordanceViewBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder, ImageView imageView, Flow flow, Flow flow2, Continuation continuation) {
                super(2, continuation);
                this.this$0 = keyguardQuickAffordanceViewBinder;
                this.$button = imageView;
                this.$viewModel = flow;
                this.$alpha = flow2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, this.$button, this.$viewModel, this.$alpha, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder = this.this$0;
                    ImageView imageView = this.$button;
                    final Flow flow = this.$viewModel;
                    Flow flow2 = this.$alpha;
                    this.label = 1;
                    keyguardQuickAffordanceViewBinder.getClass();
                    Object collect = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new Flow() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButtonAlpha$$inlined$map$1

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButtonAlpha$$inlined$map$1$2, reason: invalid class name */
                        public final class AnonymousClass2 implements FlowCollector {
                            public final /* synthetic */ FlowCollector $this_unsafeFlow;

                            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                            /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButtonAlpha$$inlined$map$1$2$1, reason: invalid class name */
                            public final class AnonymousClass1 extends ContinuationImpl {
                                Object L$0;
                                int label;
                                /* synthetic */ Object result;

                                public AnonymousClass1(Continuation continuation) {
                                    super(continuation);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    this.result = obj;
                                    this.label |= Integer.MIN_VALUE;
                                    return AnonymousClass2.this.emit(null, this);
                                }
                            }

                            public AnonymousClass2(FlowCollector flowCollector) {
                                this.$this_unsafeFlow = flowCollector;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                            /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                                /*
                                    r4 = this;
                                    boolean r0 = r6 instanceof com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButtonAlpha$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                    if (r0 == 0) goto L13
                                    r0 = r6
                                    com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButtonAlpha$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButtonAlpha$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                                    int r1 = r0.label
                                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                    r3 = r1 & r2
                                    if (r3 == 0) goto L13
                                    int r1 = r1 - r2
                                    r0.label = r1
                                    goto L18
                                L13:
                                    com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButtonAlpha$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButtonAlpha$$inlined$map$1$2$1
                                    r0.<init>(r6)
                                L18:
                                    java.lang.Object r6 = r0.result
                                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                    int r2 = r0.label
                                    r3 = 1
                                    if (r2 == 0) goto L2f
                                    if (r2 != r3) goto L27
                                    kotlin.ResultKt.throwOnFailure(r6)
                                    goto L45
                                L27:
                                    java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                    java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                    r4.<init>(r5)
                                    throw r4
                                L2f:
                                    kotlin.ResultKt.throwOnFailure(r6)
                                    com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordanceViewModel r5 = (com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordanceViewModel) r5
                                    boolean r5 = r5.isDimmed
                                    java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                                    r0.label = r3
                                    kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                                    java.lang.Object r4 = r4.emit(r5, r0)
                                    if (r4 != r1) goto L45
                                    return r1
                                L45:
                                    kotlin.Unit r4 = kotlin.Unit.INSTANCE
                                    return r4
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$updateButtonAlpha$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                            }
                        }

                        @Override // kotlinx.coroutines.flow.Flow
                        public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                            Object collect2 = Flow.this.collect(new AnonymousClass2(flowCollector), continuation);
                            return collect2 == CoroutineSingletons.COROUTINE_SUSPENDED ? collect2 : Unit.INSTANCE;
                        }
                    }, flow2, new KeyguardQuickAffordanceViewBinder$updateButtonAlpha$3(keyguardQuickAffordanceViewBinder, null)).collect(new KeyguardQuickAffordanceViewBinder$updateButtonAlpha$4((View) imageView), this);
                    if (collect != coroutineSingletons) {
                        collect = unit;
                    }
                    if (collect == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return unit;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder$bind$disposableHandle$1$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ ImageView $button;
            final /* synthetic */ MutableStateFlow $configurationBasedDimensions;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(MutableStateFlow mutableStateFlow, ImageView imageView, Continuation continuation) {
                super(2, continuation);
                this.$configurationBasedDimensions = mutableStateFlow;
                this.$button = imageView;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$configurationBasedDimensions, this.$button, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
                }
                ResultKt.throwOnFailure(obj);
                MutableStateFlow mutableStateFlow = this.$configurationBasedDimensions;
                KeyguardQuickAffordanceViewBinder$updateButtonAlpha$4 keyguardQuickAffordanceViewBinder$updateButtonAlpha$4 = new KeyguardQuickAffordanceViewBinder$updateButtonAlpha$4(this.$button);
                this.label = 1;
                ((StateFlowImpl) mutableStateFlow).collect(keyguardQuickAffordanceViewBinder$updateButtonAlpha$4, this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Flow flow, KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder, ImageView imageView, Function1 function1, Flow flow2, MutableStateFlow mutableStateFlow, Continuation continuation) {
            super(2, continuation);
            this.$viewModel = flow;
            this.this$0 = keyguardQuickAffordanceViewBinder;
            this.$button = imageView;
            this.$messageDisplayer = function1;
            this.$alpha = flow2;
            this.$configurationBasedDimensions = mutableStateFlow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.this$0, this.$button, this.$messageDisplayer, this.$alpha, this.$configurationBasedDimensions, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            BuildersKt.launch$default(coroutineScope, null, null, new C01251(this.$viewModel, this.this$0, this.$button, this.$messageDisplayer, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.this$0, this.$button, this.$viewModel, this.$alpha, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$configurationBasedDimensions, this.$button, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardQuickAffordanceViewBinder$bind$disposableHandle$1(Flow flow, KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder, ImageView imageView, Function1 function1, Flow flow2, MutableStateFlow mutableStateFlow, Continuation continuation) {
        super(3, continuation);
        this.$viewModel = flow;
        this.this$0 = keyguardQuickAffordanceViewBinder;
        this.$button = imageView;
        this.$messageDisplayer = function1;
        this.$alpha = flow2;
        this.$configurationBasedDimensions = mutableStateFlow;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardQuickAffordanceViewBinder$bind$disposableHandle$1 keyguardQuickAffordanceViewBinder$bind$disposableHandle$1 = new KeyguardQuickAffordanceViewBinder$bind$disposableHandle$1(this.$viewModel, this.this$0, this.$button, this.$messageDisplayer, this.$alpha, this.$configurationBasedDimensions, (Continuation) obj3);
        keyguardQuickAffordanceViewBinder$bind$disposableHandle$1.L$0 = (LifecycleOwner) obj;
        return keyguardQuickAffordanceViewBinder$bind$disposableHandle$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewModel, this.this$0, this.$button, this.$messageDisplayer, this.$alpha, this.$configurationBasedDimensions, null);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutineSingletons) {
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

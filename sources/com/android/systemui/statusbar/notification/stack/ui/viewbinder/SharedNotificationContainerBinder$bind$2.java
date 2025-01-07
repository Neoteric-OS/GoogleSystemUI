package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import android.animation.ValueAnimator;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.common.shared.model.NotificationContainerBounds;
import com.android.systemui.keyguard.ui.viewmodel.ViewStateAccessor;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SharedNotificationContainerBinder$bind$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
    final /* synthetic */ ViewStateAccessor $viewState;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SharedNotificationContainerBinder this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
        final /* synthetic */ ViewStateAccessor $viewState;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SharedNotificationContainerBinder this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1$1, reason: invalid class name and collision with other inner class name */
        final class C02091 extends SuspendLambda implements Function2 {
            final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
            int label;
            final /* synthetic */ SharedNotificationContainerBinder this$0;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C02101 implements FlowCollector {
                public final /* synthetic */ int $r8$classId;
                public final /* synthetic */ SharedNotificationContainerBinder this$0;

                public /* synthetic */ C02101(SharedNotificationContainerBinder sharedNotificationContainerBinder, int i) {
                    this.$r8$classId = i;
                    this.this$0 = sharedNotificationContainerBinder;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    NotificationStackScrollLayout notificationStackScrollLayout;
                    Unit unit = Unit.INSTANCE;
                    final SharedNotificationContainerBinder sharedNotificationContainerBinder = this.this$0;
                    switch (this.$r8$classId) {
                        case 0:
                            if (((Boolean) obj).booleanValue()) {
                                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                                ofFloat.setDuration(250L);
                                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1$1$1$1$1
                                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                        NotificationStackScrollLayoutController notificationStackScrollLayoutController = SharedNotificationContainerBinder.this.controller;
                                        notificationStackScrollLayoutController.mMaxAlphaForKeyguard = valueAnimator.getAnimatedFraction();
                                        notificationStackScrollLayoutController.mMaxAlphaForKeyguardSource = "SharedNotificationContainerVB (collapseFadeIn)";
                                        notificationStackScrollLayoutController.updateAlpha$1();
                                    }
                                });
                                ofFloat.start();
                                break;
                            }
                            break;
                        case 1:
                            int intValue = ((Number) obj).intValue();
                            NotificationStackScrollLayout notificationStackScrollLayout2 = NotificationStackScrollLayoutController.this.mView;
                            if (notificationStackScrollLayout2.mMaxDisplayedNotifications != intValue) {
                                notificationStackScrollLayout2.mMaxDisplayedNotifications = intValue;
                                notificationStackScrollLayout2.updateContentHeight();
                                notificationStackScrollLayout2.notifyHeightChangeListener(notificationStackScrollLayout2.mShelf, false);
                                break;
                            }
                            break;
                        case 2:
                            NotificationContainerBounds notificationContainerBounds = (NotificationContainerBounds) obj;
                            boolean z = notificationContainerBounds.isAnimated || !((notificationStackScrollLayout = sharedNotificationContainerBinder.controller.mView) == null || !notificationStackScrollLayout.mNeedsAnimation || (notificationStackScrollLayout.mChildrenToAddAnimated.isEmpty() && notificationStackScrollLayout.mChildrenToRemoveAnimated.isEmpty()));
                            NotificationStackScrollLayoutController notificationStackScrollLayoutController = sharedNotificationContainerBinder.controller;
                            int i = (int) notificationContainerBounds.top;
                            NotificationStackScrollLayout notificationStackScrollLayout3 = notificationStackScrollLayoutController.mView;
                            if (notificationStackScrollLayout3.getLayoutMinHeightInternal() + i > notificationStackScrollLayout3.getHeight()) {
                                notificationStackScrollLayout3.mTopPaddingOverflow = r4 - notificationStackScrollLayout3.getHeight();
                            } else {
                                notificationStackScrollLayout3.mTopPaddingOverflow = 0.0f;
                            }
                            AmbientState ambientState = notificationStackScrollLayout3.mAmbientState;
                            if (ambientState.mTopPadding != i) {
                                ambientState.mTopPadding = i;
                                boolean z2 = (z && !notificationStackScrollLayout3.mKeyguardBypassEnabled) || notificationStackScrollLayout3.mAnimateNextTopPaddingChange;
                                notificationStackScrollLayout3.updateAlgorithmHeightAndPadding();
                                notificationStackScrollLayout3.updateContentHeight();
                                if (notificationStackScrollLayout3.mAmbientState.isOnKeyguard() && !notificationStackScrollLayout3.mShouldUseSplitNotificationShade && notificationStackScrollLayout3.mShouldSkipTopPaddingAnimationAfterFold) {
                                    notificationStackScrollLayout3.mShouldSkipTopPaddingAnimationAfterFold = false;
                                } else if (z2 && notificationStackScrollLayout3.mAnimationsEnabled && notificationStackScrollLayout3.mIsExpanded) {
                                    notificationStackScrollLayout3.mTopPaddingNeedsAnimation = true;
                                    notificationStackScrollLayout3.mNeedsAnimation = true;
                                }
                                notificationStackScrollLayout3.updateStackPosition(false);
                                notificationStackScrollLayout3.requestChildrenUpdate();
                                notificationStackScrollLayout3.notifyHeightChangeListener(null, z2);
                                notificationStackScrollLayout3.mAnimateNextTopPaddingChange = false;
                            }
                            notificationStackScrollLayout3.setExpandedHeight(notificationStackScrollLayout3.mExpandedHeight);
                            break;
                        case 3:
                            sharedNotificationContainerBinder.controller.mView.setTranslationY(((Number) obj).floatValue());
                            break;
                        case 4:
                            sharedNotificationContainerBinder.controller.mView.setTranslationX(((Number) obj).floatValue());
                            break;
                        case 5:
                            float floatValue = ((Number) obj).floatValue();
                            NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = sharedNotificationContainerBinder.controller;
                            notificationStackScrollLayoutController2.mMaxAlphaForKeyguard = floatValue;
                            notificationStackScrollLayoutController2.mMaxAlphaForKeyguardSource = "SharedNotificationContainerVB";
                            notificationStackScrollLayoutController2.updateAlpha$1();
                            break;
                        case 6:
                            float floatValue2 = ((Number) obj).floatValue();
                            NotificationStackScrollLayoutController notificationStackScrollLayoutController3 = sharedNotificationContainerBinder.controller;
                            notificationStackScrollLayoutController3.mMaxAlphaFromView = floatValue2;
                            notificationStackScrollLayoutController3.updateAlpha$1();
                            break;
                        default:
                            float floatValue3 = ((Number) obj).floatValue();
                            NotificationStackScrollLayoutController notificationStackScrollLayoutController4 = sharedNotificationContainerBinder.controller;
                            notificationStackScrollLayoutController4.mMaxAlphaForGlanceableHub = floatValue3;
                            notificationStackScrollLayoutController4.updateAlpha$1();
                            break;
                    }
                    return unit;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02091(SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainerBinder sharedNotificationContainerBinder, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sharedNotificationContainerViewModel;
                this.this$0 = sharedNotificationContainerBinder;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C02091(this.$viewModel, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C02091) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.shadeCollapseFadeIn;
                C02101 c02101 = new C02101(this.this$0, 0);
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c02101, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
            int label;
            final /* synthetic */ SharedNotificationContainerBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainerBinder sharedNotificationContainerBinder, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sharedNotificationContainerViewModel;
                this.this$0 = sharedNotificationContainerBinder;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$viewModel, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SharedNotificationContainerViewModel sharedNotificationContainerViewModel = this.$viewModel;
                    final SharedNotificationContainerBinder sharedNotificationContainerBinder = this.this$0;
                    SafeFlow maxNotifications = sharedNotificationContainerViewModel.getMaxNotifications(new Function2() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder.bind.2.1.2.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            float floatValue = ((Number) obj2).floatValue();
                            boolean booleanValue = ((Boolean) obj3).booleanValue();
                            NotificationShelf notificationShelf = SharedNotificationContainerBinder.this.controller.mView.mShelf;
                            float height = notificationShelf == null ? 0 : notificationShelf.getHeight();
                            SharedNotificationContainerBinder sharedNotificationContainerBinder2 = SharedNotificationContainerBinder.this;
                            return Integer.valueOf(sharedNotificationContainerBinder2.notificationStackSizeCalculator.computeMaxKeyguardNotifications(sharedNotificationContainerBinder2.controller.mView, floatValue, booleanValue ? height : 0.0f, height));
                        }
                    });
                    C02091.C02101 c02101 = new C02091.C02101(this.this$0, 1);
                    this.label = 1;
                    if (maxNotifications.collect(c02101, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
            int label;
            final /* synthetic */ SharedNotificationContainerBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainerBinder sharedNotificationContainerBinder, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sharedNotificationContainerViewModel;
                this.this$0 = sharedNotificationContainerBinder;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$viewModel, this.this$0, continuation);
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
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlow = (StateFlow) this.$viewModel.bounds$delegate.getValue();
                    C02091.C02101 c02101 = new C02091.C02101(this.this$0, 2);
                    this.label = 1;
                    if (stateFlow.collect(c02101, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
            int label;
            final /* synthetic */ SharedNotificationContainerBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainerBinder sharedNotificationContainerBinder, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sharedNotificationContainerViewModel;
                this.this$0 = sharedNotificationContainerBinder;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$viewModel, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SafeFlow safeFlow = this.$viewModel.translationY;
                    C02091.C02101 c02101 = new C02091.C02101(this.this$0, 3);
                    this.label = 1;
                    if (safeFlow.collect(c02101, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1$5, reason: invalid class name */
        final class AnonymousClass5 extends SuspendLambda implements Function2 {
            final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
            int label;
            final /* synthetic */ SharedNotificationContainerBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainerBinder sharedNotificationContainerBinder, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sharedNotificationContainerViewModel;
                this.this$0 = sharedNotificationContainerBinder;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass5(this.$viewModel, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass5) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SafeFlow safeFlow = this.$viewModel.translationX;
                    C02091.C02101 c02101 = new C02091.C02101(this.this$0, 4);
                    this.label = 1;
                    if (safeFlow.collect(c02101, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1$6, reason: invalid class name */
        final class AnonymousClass6 extends SuspendLambda implements Function2 {
            final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
            final /* synthetic */ ViewStateAccessor $viewState;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SharedNotificationContainerBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass6(ViewStateAccessor viewStateAccessor, SharedNotificationContainerBinder sharedNotificationContainerBinder, SharedNotificationContainerViewModel sharedNotificationContainerViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sharedNotificationContainerViewModel;
                this.$viewState = viewStateAccessor;
                this.this$0 = sharedNotificationContainerBinder;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass6 anonymousClass6 = new AnonymousClass6(this.$viewState, this.this$0, this.$viewModel, continuation);
                anonymousClass6.L$0 = obj;
                return anonymousClass6;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SafeFlow keyguardAlpha = this.$viewModel.keyguardAlpha(this.$viewState, (CoroutineScope) this.L$0);
                    C02091.C02101 c02101 = new C02091.C02101(this.this$0, 5);
                    this.label = 1;
                    if (keyguardAlpha.collect(c02101, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1$7, reason: invalid class name */
        final class AnonymousClass7 extends SuspendLambda implements Function2 {
            final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
            int label;
            final /* synthetic */ SharedNotificationContainerBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass7(SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainerBinder sharedNotificationContainerBinder, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sharedNotificationContainerViewModel;
                this.this$0 = sharedNotificationContainerBinder;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass7(this.$viewModel, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass7) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                ReadonlyStateFlow readonlyStateFlow = this.$viewModel.panelAlpha;
                C02091.C02101 c02101 = new C02091.C02101(this.this$0, 6);
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(c02101, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder$bind$2$1$8, reason: invalid class name */
        final class AnonymousClass8 extends SuspendLambda implements Function2 {
            final /* synthetic */ SharedNotificationContainerViewModel $viewModel;
            int label;
            final /* synthetic */ SharedNotificationContainerBinder this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass8(SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainerBinder sharedNotificationContainerBinder, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = sharedNotificationContainerViewModel;
                this.this$0 = sharedNotificationContainerBinder;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass8(this.$viewModel, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass8) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SafeFlow safeFlow = this.$viewModel.glanceableHubAlpha;
                    C02091.C02101 c02101 = new C02091.C02101(this.this$0, 7);
                    this.label = 1;
                    if (safeFlow.collect(c02101, this) == coroutineSingletons) {
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
        public AnonymousClass1(ViewStateAccessor viewStateAccessor, SharedNotificationContainerBinder sharedNotificationContainerBinder, SharedNotificationContainerViewModel sharedNotificationContainerViewModel, Continuation continuation) {
            super(2, continuation);
            this.this$0 = sharedNotificationContainerBinder;
            this.$viewModel = sharedNotificationContainerViewModel;
            this.$viewState = viewStateAccessor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewState, this.this$0, this.$viewModel, continuation);
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
            BuildersKt.launch$default(coroutineScope, null, null, new C02091(this.$viewModel, this.this$0, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.this$0, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(this.$viewModel, this.this$0, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$viewModel, this.this$0, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass5(this.$viewModel, this.this$0, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass6(this.$viewState, this.this$0, this.$viewModel, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass7(this.$viewModel, this.this$0, null), 3);
            if (this.this$0.communalSettingsInteractor.isCommunalFlagEnabled()) {
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass8(this.$viewModel, this.this$0, null), 3);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharedNotificationContainerBinder$bind$2(ViewStateAccessor viewStateAccessor, SharedNotificationContainerBinder sharedNotificationContainerBinder, SharedNotificationContainerViewModel sharedNotificationContainerViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = sharedNotificationContainerBinder;
        this.$viewModel = sharedNotificationContainerViewModel;
        this.$viewState = viewStateAccessor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SharedNotificationContainerBinder sharedNotificationContainerBinder = this.this$0;
        SharedNotificationContainerViewModel sharedNotificationContainerViewModel = this.$viewModel;
        SharedNotificationContainerBinder$bind$2 sharedNotificationContainerBinder$bind$2 = new SharedNotificationContainerBinder$bind$2(this.$viewState, sharedNotificationContainerBinder, sharedNotificationContainerViewModel, (Continuation) obj3);
        sharedNotificationContainerBinder$bind$2.L$0 = (LifecycleOwner) obj;
        return sharedNotificationContainerBinder$bind$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$viewState, this.this$0, this.$viewModel, null);
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

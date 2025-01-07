package com.android.systemui.statusbar.pipeline.shared.ui.binder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.common.ui.binder.IconViewBinder;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.chips.ui.model.ColorsModel;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import com.android.systemui.statusbar.chips.ui.view.ChipBackgroundContainer;
import com.android.systemui.statusbar.chips.ui.view.ChipChronometer;
import com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.CollapsedStatusBarViewModel;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.CollapsedStatusBarViewModelImpl;
import com.android.systemui.statusbar.pipeline.shared.ui.viewmodel.CollapsedStatusBarViewModelImpl$special$$inlined$map$1;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CollapsedStatusBarViewBinderImpl$bind$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ StatusBarVisibilityChangeListener $listener;
    final /* synthetic */ View $view;
    final /* synthetic */ CollapsedStatusBarViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CollapsedStatusBarViewBinderImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.binder.CollapsedStatusBarViewBinderImpl$bind$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ StatusBarVisibilityChangeListener $listener;
        final /* synthetic */ View $view;
        final /* synthetic */ CollapsedStatusBarViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ CollapsedStatusBarViewBinderImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.binder.CollapsedStatusBarViewBinderImpl$bind$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C02351 extends SuspendLambda implements Function2 {
            final /* synthetic */ StatusBarVisibilityChangeListener $listener;
            final /* synthetic */ CollapsedStatusBarViewModel $viewModel;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.binder.CollapsedStatusBarViewBinderImpl$bind$1$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C02361 implements FlowCollector {
                public final /* synthetic */ StatusBarVisibilityChangeListener $listener;
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ C02361(StatusBarVisibilityChangeListener statusBarVisibilityChangeListener, int i) {
                    this.$r8$classId = i;
                    this.$listener = statusBarVisibilityChangeListener;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            ((Boolean) obj).booleanValue();
                            CollapsedStatusBarFragment.this.updateStatusBarVisibilities(true);
                            break;
                        default:
                            CollapsedStatusBarFragment.this.mTransitionFromLockscreenToDreamStarted = true;
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02351(CollapsedStatusBarViewModel collapsedStatusBarViewModel, StatusBarVisibilityChangeListener statusBarVisibilityChangeListener, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = collapsedStatusBarViewModel;
                this.$listener = statusBarVisibilityChangeListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C02351(this.$viewModel, this.$listener, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((C02351) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                ReadonlyStateFlow readonlyStateFlow = ((CollapsedStatusBarViewModelImpl) this.$viewModel).isTransitioningFromLockscreenToOccluded;
                C02361 c02361 = new C02361(this.$listener, 0);
                this.label = 1;
                readonlyStateFlow.collect(c02361, this);
                return coroutineSingletons;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.binder.CollapsedStatusBarViewBinderImpl$bind$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ StatusBarVisibilityChangeListener $listener;
            final /* synthetic */ CollapsedStatusBarViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(CollapsedStatusBarViewModel collapsedStatusBarViewModel, StatusBarVisibilityChangeListener statusBarVisibilityChangeListener, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = collapsedStatusBarViewModel;
                this.$listener = statusBarVisibilityChangeListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$viewModel, this.$listener, continuation);
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
                    CollapsedStatusBarViewModelImpl$special$$inlined$map$1 collapsedStatusBarViewModelImpl$special$$inlined$map$1 = ((CollapsedStatusBarViewModelImpl) this.$viewModel).transitionFromLockscreenToDreamStartedEvent;
                    C02351.C02361 c02361 = new C02351.C02361(this.$listener, 1);
                    this.label = 1;
                    if (collapsedStatusBarViewModelImpl$special$$inlined$map$1.collect(c02361, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.statusbar.pipeline.shared.ui.binder.CollapsedStatusBarViewBinderImpl$bind$1$1$4, reason: invalid class name */
        final class AnonymousClass4 extends SuspendLambda implements Function2 {
            final /* synthetic */ StatusBarVisibilityChangeListener $listener;
            final /* synthetic */ View $primaryChipView;
            final /* synthetic */ CollapsedStatusBarViewModel $viewModel;
            int label;
            final /* synthetic */ CollapsedStatusBarViewBinderImpl this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(View view, CollapsedStatusBarViewBinderImpl collapsedStatusBarViewBinderImpl, StatusBarVisibilityChangeListener statusBarVisibilityChangeListener, CollapsedStatusBarViewModel collapsedStatusBarViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = collapsedStatusBarViewModel;
                this.$primaryChipView = view;
                this.this$0 = collapsedStatusBarViewBinderImpl;
                this.$listener = statusBarVisibilityChangeListener;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass4(this.$primaryChipView, this.this$0, this.$listener, this.$viewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                ReadonlyStateFlow readonlyStateFlow = ((CollapsedStatusBarViewModelImpl) this.$viewModel).primaryOngoingActivityChip;
                FlowCollector flowCollector = new FlowCollector(this.$primaryChipView, this.this$0, this.$listener) { // from class: com.android.systemui.statusbar.pipeline.shared.ui.binder.CollapsedStatusBarViewBinderImpl.bind.1.1.4.1
                    public final /* synthetic */ StatusBarVisibilityChangeListener $listener;
                    public final /* synthetic */ View $primaryChipView;

                    {
                        this.$listener = r3;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        OngoingActivityChipModel ongoingActivityChipModel = (OngoingActivityChipModel) obj2;
                        View view = this.$primaryChipView;
                        Context context = view.getContext();
                        ImageView imageView = (ImageView) view.requireViewById(R.id.ongoing_activity_chip_icon);
                        ChipChronometer chipChronometer = (ChipChronometer) view.requireViewById(R.id.ongoing_activity_chip_time);
                        TextView textView = (TextView) view.requireViewById(R.id.ongoing_activity_chip_text);
                        ChipBackgroundContainer chipBackgroundContainer = (ChipBackgroundContainer) view.requireViewById(R.id.ongoing_activity_chip_background);
                        if (ongoingActivityChipModel instanceof OngoingActivityChipModel.Shown) {
                            OngoingActivityChipModel.Shown shown = (OngoingActivityChipModel.Shown) ongoingActivityChipModel;
                            chipBackgroundContainer.removeView((StatusBarIconView) chipBackgroundContainer.findViewById(R.id.ongoing_activity_chip_custom_icon));
                            int text = shown.getColors().text(imageView.getContext());
                            OngoingActivityChipModel.ChipIcon icon = shown.getIcon();
                            if (icon == null) {
                                imageView.setVisibility(8);
                            } else if (icon instanceof OngoingActivityChipModel.ChipIcon.SingleColorIcon) {
                                IconViewBinder.bind(((OngoingActivityChipModel.ChipIcon.SingleColorIcon) icon).impl, imageView);
                                imageView.setVisibility(0);
                                imageView.setImageTintList(ColorStateList.valueOf(text));
                            } else {
                                if (icon instanceof OngoingActivityChipModel.ChipIcon.FullColorAppIcon) {
                                    throw new IllegalStateException("New code path not supported when com.android.systemui.status_bar_ron_chips is disabled.");
                                }
                                if (icon instanceof OngoingActivityChipModel.ChipIcon.StatusBarView) {
                                    imageView.setVisibility(8);
                                    StatusBarIconView statusBarIconView = ((OngoingActivityChipModel.ChipIcon.StatusBarView) icon).impl;
                                    statusBarIconView.setId(R.id.ongoing_activity_chip_custom_icon);
                                    statusBarIconView.setContentDescription(statusBarIconView.getContext().getResources().getString(R.string.ongoing_phone_call_content_description));
                                    statusBarIconView.setImageTintList(ColorStateList.valueOf(text));
                                    ViewParent parent = statusBarIconView.getParent();
                                    ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                                    if (viewGroup != null && !viewGroup.equals(chipBackgroundContainer)) {
                                        viewGroup.removeView(statusBarIconView);
                                        viewGroup.removeTransientView(statusBarIconView);
                                    }
                                    int dimensionPixelSize = statusBarIconView.getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_activity_chip_embedded_padding_icon_size);
                                    chipBackgroundContainer.addView(statusBarIconView, 0, new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize));
                                }
                            }
                            boolean z = shown instanceof OngoingActivityChipModel.Shown.Countdown;
                            if (z) {
                                textView.setText(String.valueOf(((OngoingActivityChipModel.Shown.Countdown) shown).secondsUntilStarted));
                                textView.setVisibility(0);
                                chipChronometer.stop();
                                chipChronometer.setVisibility(8);
                            } else if (shown instanceof OngoingActivityChipModel.Shown.Timer) {
                                chipChronometer.setBase(((OngoingActivityChipModel.Shown.Timer) shown).startTimeMs);
                                chipChronometer.start();
                                chipChronometer.setVisibility(0);
                                textView.setVisibility(8);
                            } else if (shown instanceof OngoingActivityChipModel.Shown.IconOnly) {
                                textView.setVisibility(8);
                                chipChronometer.stop();
                                chipChronometer.setVisibility(8);
                            }
                            view.setOnClickListener(shown.getOnClickListener());
                            if (shown.getIcon() == null) {
                                int dimensionPixelSize2 = chipBackgroundContainer.getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_activity_chip_side_padding);
                                chipBackgroundContainer.setPaddingRelative(dimensionPixelSize2, chipBackgroundContainer.getPaddingTop(), dimensionPixelSize2, chipBackgroundContainer.getPaddingBottom());
                                textView.setPaddingRelative(0, textView.getPaddingTop(), 0, textView.getPaddingBottom());
                                chipChronometer.setPaddingRelative(0, chipChronometer.getPaddingTop(), 0, chipChronometer.getPaddingBottom());
                            } else if (shown.getIcon() instanceof OngoingActivityChipModel.ChipIcon.StatusBarView) {
                                int dimensionPixelSize3 = chipBackgroundContainer.getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_activity_chip_side_padding_for_embedded_padding_icon);
                                chipBackgroundContainer.setPaddingRelative(dimensionPixelSize3, chipBackgroundContainer.getPaddingTop(), dimensionPixelSize3, chipBackgroundContainer.getPaddingBottom());
                                textView.setPaddingRelative(0, textView.getPaddingTop(), textView.getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_activity_chip_text_end_padding_for_embedded_padding_icon), textView.getPaddingBottom());
                                chipChronometer.setPaddingRelative(0, chipChronometer.getPaddingTop(), chipChronometer.getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_activity_chip_text_end_padding_for_embedded_padding_icon), chipChronometer.getPaddingBottom());
                            } else {
                                int dimensionPixelSize4 = chipBackgroundContainer.getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_activity_chip_side_padding);
                                chipBackgroundContainer.setPaddingRelative(dimensionPixelSize4, chipBackgroundContainer.getPaddingTop(), dimensionPixelSize4, chipBackgroundContainer.getPaddingBottom());
                                textView.setPaddingRelative(textView.getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_activity_chip_icon_text_padding), textView.getPaddingTop(), 0, textView.getPaddingBottom());
                                chipChronometer.setPaddingRelative(chipChronometer.getContext().getResources().getDimensionPixelSize(R.dimen.ongoing_activity_chip_icon_text_padding), chipChronometer.getPaddingTop(), 0, chipChronometer.getPaddingBottom());
                            }
                            if (z) {
                                view.setAccessibilityLiveRegion(2);
                            } else {
                                if (shown instanceof OngoingActivityChipModel.Shown.Timer ? true : shown instanceof OngoingActivityChipModel.Shown.IconOnly) {
                                    view.setAccessibilityLiveRegion(0);
                                }
                            }
                            if (shown.getOnClickListener() != null) {
                                chipBackgroundContainer.setMinimumWidth(chipBackgroundContainer.getContext().getResources().getDimensionPixelSize(R.dimen.min_clickable_item_size));
                            } else {
                                chipBackgroundContainer.setMinimumWidth(0);
                            }
                            ColorsModel colors = shown.getColors();
                            Intrinsics.checkNotNull(context);
                            int text2 = colors.text(context);
                            chipChronometer.setTextColor(text2);
                            textView.setTextColor(text2);
                            ((GradientDrawable) chipBackgroundContainer.getBackground()).setColor(shown.getColors().background(context));
                        } else if (ongoingActivityChipModel instanceof OngoingActivityChipModel.Hidden) {
                            chipChronometer.stop();
                        }
                        boolean z2 = ongoingActivityChipModel instanceof OngoingActivityChipModel.Shown;
                        StatusBarVisibilityChangeListener statusBarVisibilityChangeListener = this.$listener;
                        if (z2) {
                            CollapsedStatusBarFragment collapsedStatusBarFragment = CollapsedStatusBarFragment.this;
                            collapsedStatusBarFragment.mHasPrimaryOngoingActivity = true;
                            collapsedStatusBarFragment.mHasSecondaryOngoingActivity = false;
                            collapsedStatusBarFragment.updateStatusBarVisibilities(true);
                        } else if (ongoingActivityChipModel instanceof OngoingActivityChipModel.Hidden) {
                            boolean z3 = ((OngoingActivityChipModel.Hidden) ongoingActivityChipModel).shouldAnimate;
                            CollapsedStatusBarFragment collapsedStatusBarFragment2 = CollapsedStatusBarFragment.this;
                            collapsedStatusBarFragment2.mHasPrimaryOngoingActivity = false;
                            collapsedStatusBarFragment2.mHasSecondaryOngoingActivity = false;
                            collapsedStatusBarFragment2.updateStatusBarVisibilities(z3);
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
                return coroutineSingletons;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(View view, CollapsedStatusBarViewBinderImpl collapsedStatusBarViewBinderImpl, StatusBarVisibilityChangeListener statusBarVisibilityChangeListener, CollapsedStatusBarViewModel collapsedStatusBarViewModel, Continuation continuation) {
            super(2, continuation);
            this.$view = view;
            this.$viewModel = collapsedStatusBarViewModel;
            this.$listener = statusBarVisibilityChangeListener;
            this.this$0 = collapsedStatusBarViewBinderImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            View view = this.$view;
            CollapsedStatusBarViewModel collapsedStatusBarViewModel = this.$viewModel;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(view, this.this$0, this.$listener, collapsedStatusBarViewModel, continuation);
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
            BuildersKt.launch$default(coroutineScope, null, null, new C02351(this.$viewModel, this.$listener, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$viewModel, this.$listener, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(this.$view.requireViewById(R.id.ongoing_activity_chip_primary), this.this$0, this.$listener, this.$viewModel, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollapsedStatusBarViewBinderImpl$bind$1(View view, CollapsedStatusBarViewBinderImpl collapsedStatusBarViewBinderImpl, StatusBarVisibilityChangeListener statusBarVisibilityChangeListener, CollapsedStatusBarViewModel collapsedStatusBarViewModel, Continuation continuation) {
        super(3, continuation);
        this.$view = view;
        this.$viewModel = collapsedStatusBarViewModel;
        this.$listener = statusBarVisibilityChangeListener;
        this.this$0 = collapsedStatusBarViewBinderImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        View view = this.$view;
        CollapsedStatusBarViewModel collapsedStatusBarViewModel = this.$viewModel;
        StatusBarVisibilityChangeListener statusBarVisibilityChangeListener = this.$listener;
        CollapsedStatusBarViewBinderImpl$bind$1 collapsedStatusBarViewBinderImpl$bind$1 = new CollapsedStatusBarViewBinderImpl$bind$1(view, this.this$0, statusBarVisibilityChangeListener, collapsedStatusBarViewModel, (Continuation) obj3);
        collapsedStatusBarViewBinderImpl$bind$1.L$0 = (LifecycleOwner) obj;
        return collapsedStatusBarViewBinderImpl$bind$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.CREATED;
            View view = this.$view;
            CollapsedStatusBarViewModel collapsedStatusBarViewModel = this.$viewModel;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(view, this.this$0, this.$listener, collapsedStatusBarViewModel, null);
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

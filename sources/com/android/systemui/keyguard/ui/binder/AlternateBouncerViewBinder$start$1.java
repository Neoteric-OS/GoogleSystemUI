package com.android.systemui.keyguard.ui.binder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.keyguard.AuthKeyguardMessageArea;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerDependencies;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerWindowViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.scrim.ScrimView;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AlternateBouncerViewBinder$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ AlternateBouncerViewBinder this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$start$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass1(int i, Object obj) {
            this.$r8$classId = i;
            this.this$0 = obj;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            Unit unit = Unit.INSTANCE;
            Object obj2 = this.this$0;
            switch (this.$r8$classId) {
                case 0:
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("alternateBouncerWindowRequired=", "AlternateBouncerViewBinder", booleanValue);
                    final AlternateBouncerViewBinder alternateBouncerViewBinder = (AlternateBouncerViewBinder) obj2;
                    if (!booleanValue) {
                        final ConstraintLayout constraintLayout = alternateBouncerViewBinder.alternateBouncerView;
                        if (constraintLayout != null) {
                            alternateBouncerViewBinder.alternateBouncerView = null;
                            if (constraintLayout.isAttachedToWindow()) {
                                constraintLayout.removeOnAttachStateChangeListener(alternateBouncerViewBinder.onAttachAddBackGestureHandler);
                                Log.d("AlternateBouncerViewBinder", "Removing alternate bouncer view immediately");
                                ((WindowManager) alternateBouncerViewBinder.windowManager.get()).removeView(constraintLayout);
                            } else {
                                constraintLayout.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder$removeViewFromWindowManager$1$1
                                    @Override // android.view.View.OnAttachStateChangeListener
                                    public final void onViewAttachedToWindow(View view) {
                                        ConstraintLayout.this.removeOnAttachStateChangeListener(this);
                                        ConstraintLayout.this.removeOnAttachStateChangeListener(alternateBouncerViewBinder.onAttachAddBackGestureHandler);
                                        Log.d("AlternateBouncerViewBinder", "Removing alternate bouncer view on attached");
                                        ((WindowManager) alternateBouncerViewBinder.windowManager.get()).removeView(ConstraintLayout.this);
                                    }

                                    @Override // android.view.View.OnAttachStateChangeListener
                                    public final void onViewDetachedFromWindow(View view) {
                                    }
                                });
                            }
                        }
                        ((AlternateBouncerDependencies) alternateBouncerViewBinder.alternateBouncerDependencies.get()).viewModel.statusBarKeyguardViewManager.hideAlternateBouncer(false);
                        break;
                    } else {
                        if (alternateBouncerViewBinder.alternateBouncerView == null) {
                            alternateBouncerViewBinder.alternateBouncerView = (ConstraintLayout) ((LayoutInflater) alternateBouncerViewBinder.layoutInflater.get()).inflate(R.layout.alternate_bouncer, (ViewGroup) null, false);
                            Log.d("AlternateBouncerViewBinder", "Adding alternate bouncer view");
                            WindowManager windowManager = (WindowManager) alternateBouncerViewBinder.windowManager.get();
                            ConstraintLayout constraintLayout2 = alternateBouncerViewBinder.alternateBouncerView;
                            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2009, 16777472, -3);
                            layoutParams.setTitle("AlternateBouncerView");
                            layoutParams.setFitInsetsTypes(0);
                            layoutParams.gravity = 51;
                            layoutParams.layoutInDisplayCutoutMode = 3;
                            layoutParams.privateFlags = 536870976;
                            layoutParams.accessibilityTitle = " ";
                            windowManager.addView(constraintLayout2, layoutParams);
                            ConstraintLayout constraintLayout3 = alternateBouncerViewBinder.alternateBouncerView;
                            Intrinsics.checkNotNull(constraintLayout3);
                            constraintLayout3.addOnAttachStateChangeListener(alternateBouncerViewBinder.onAttachAddBackGestureHandler);
                        }
                        ConstraintLayout constraintLayout4 = alternateBouncerViewBinder.alternateBouncerView;
                        Intrinsics.checkNotNull(constraintLayout4);
                        ((ScrimView) constraintLayout4.requireViewById(R.id.alternate_bouncer_scrim)).setViewAlpha(0.0f);
                        ConstraintLayout constraintLayout5 = alternateBouncerViewBinder.alternateBouncerView;
                        Intrinsics.checkNotNull(constraintLayout5);
                        AlternateBouncerDependencies alternateBouncerDependencies = (AlternateBouncerDependencies) alternateBouncerViewBinder.alternateBouncerDependencies.get();
                        RepeatWhenAttachedKt.repeatWhenAttached(constraintLayout5, EmptyCoroutineContext.INSTANCE, new AlternateBouncerViewBinder$optionallyAddUdfpsViews$1(alternateBouncerDependencies.udfpsIconViewModel, constraintLayout5, alternateBouncerDependencies.udfpsAccessibilityOverlayViewModel, alternateBouncerDependencies.logger, null));
                        AuthKeyguardMessageArea authKeyguardMessageArea = (AuthKeyguardMessageArea) constraintLayout5.requireViewById(R.id.alternate_bouncer_message_area);
                        if (!authKeyguardMessageArea.mIsVisible) {
                            authKeyguardMessageArea.mIsVisible = true;
                            authKeyguardMessageArea.update();
                        }
                        RepeatWhenAttachedKt.repeatWhenAttached(authKeyguardMessageArea, EmptyCoroutineContext.INSTANCE, new AlternateBouncerMessageAreaViewBinder$bind$1(alternateBouncerDependencies.messageAreaViewModel, authKeyguardMessageArea, null));
                        RepeatWhenAttachedKt.repeatWhenAttached(constraintLayout5, EmptyCoroutineContext.INSTANCE, new AlternateBouncerViewBinder$bind$1(alternateBouncerDependencies.viewModel, alternateBouncerDependencies.swipeUpAnywhereGestureHandler, alternateBouncerDependencies.tapGestureDetector, alternateBouncerDependencies, (ScrimView) constraintLayout5.requireViewById(R.id.alternate_bouncer_scrim), null));
                        break;
                    }
                default:
                    ((ScrimView) obj2).setViewAlpha(((Number) obj).floatValue());
                    break;
            }
            return unit;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlternateBouncerViewBinder$start$1(AlternateBouncerViewBinder alternateBouncerViewBinder, Continuation continuation) {
        super(2, continuation);
        this.this$0 = alternateBouncerViewBinder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new AlternateBouncerViewBinder$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AlternateBouncerViewBinder$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ChannelFlowTransformLatest channelFlowTransformLatest = ((AlternateBouncerWindowViewModel) this.this$0.alternateBouncerWindowViewModel.get()).alternateBouncerWindowRequired;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(0, this.this$0);
            this.label = 1;
            if (channelFlowTransformLatest.collect(anonymousClass1, this) == coroutineSingletons) {
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

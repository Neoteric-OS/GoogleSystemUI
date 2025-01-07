package com.android.systemui.statusbar.events;

import android.view.View;
import androidx.core.animation.Animator;
import androidx.core.animation.AnimatorSet;
import androidx.core.animation.ValueAnimator$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.util.Assert;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SystemStatusAnimationSchedulerImpl$startAnimationLifecycle$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ StatusEvent $event;
    int label;
    final /* synthetic */ SystemStatusAnimationSchedulerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemStatusAnimationSchedulerImpl$startAnimationLifecycle$1(SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl, StatusEvent statusEvent, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemStatusAnimationSchedulerImpl;
        this.$event = statusEvent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SystemStatusAnimationSchedulerImpl$startAnimationLifecycle$1(this.this$0, this.$event, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SystemStatusAnimationSchedulerImpl$startAnimationLifecycle$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl = this.this$0;
            systemStatusAnimationSchedulerImpl.getClass();
            Assert.isMainThread();
            if (systemStatusAnimationSchedulerImpl.hasPersistentDot) {
                StatusBarWindowControllerImpl statusBarWindowControllerImpl = systemStatusAnimationSchedulerImpl.statusBarWindowController;
                StatusBarWindowControllerImpl.State state = statusBarWindowControllerImpl.mCurrentState;
                state.mForceStatusBarVisible = true;
                statusBarWindowControllerImpl.apply(state);
            }
            StateFlowImpl stateFlowImpl = systemStatusAnimationSchedulerImpl.animationState;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, 2);
            ArrayList arrayList = new ArrayList();
            Iterator it = systemStatusAnimationSchedulerImpl.listeners.iterator();
            while (it.hasNext()) {
                Animator onSystemEventAnimationBegin = ((SystemStatusAnimationCallback) it.next()).onSystemEventAnimationBegin();
                if (onSystemEventAnimationBegin != null) {
                    arrayList.add(onSystemEventAnimationBegin);
                }
            }
            arrayList.add(systemStatusAnimationSchedulerImpl.chipAnimationController.onSystemEventAnimationBegin());
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            if (animatorSet.getTotalDuration() > 500) {
                throw new IllegalStateException(ValueAnimator$$ExternalSyntheticOutline0.m(animatorSet.getTotalDuration(), "System animation total length exceeds budget. Expected: 500, actual: "));
            }
            animatorSet.addListener(new SystemStatusAnimationSchedulerImpl$runChipAppearAnimation$1(systemStatusAnimationSchedulerImpl, 0));
            animatorSet.start();
            SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl2 = this.this$0;
            StatusEvent statusEvent = this.$event;
            systemStatusAnimationSchedulerImpl2.getClass();
            String contentDescription = statusEvent.getContentDescription();
            if (contentDescription != null && statusEvent.getShouldAnnounceAccessibilityEvent() && (obj2 = systemStatusAnimationSchedulerImpl2.chipAnimationController.currentAnimatedView) != null) {
                ((View) obj2).announceForAccessibility(contentDescription);
            }
            this.label = 1;
            if (DelayKt.delay(3500L, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        SystemStatusAnimationSchedulerImpl.access$runChipDisappearAnimation(this.this$0);
        return Unit.INSTANCE;
    }
}

package com.android.systemui.back.domain.interactor;

import android.view.ViewRootImpl;
import android.window.WindowOnBackInvokedDispatcher;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowView;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BackActionInteractor$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BackActionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BackActionInteractor$start$1(BackActionInteractor backActionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = backActionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BackActionInteractor$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((BackActionInteractor$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        final BackActionInteractor backActionInteractor = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = backActionInteractor.windowRootViewVisibilityInteractor.isLockscreenOrShadeVisibleAndInteractive;
        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.back.domain.interactor.BackActionInteractor$start$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                ViewRootImpl viewRootImpl;
                ViewRootImpl viewRootImpl2;
                boolean booleanValue = ((Boolean) obj2).booleanValue();
                WindowOnBackInvokedDispatcher windowOnBackInvokedDispatcher = null;
                BackActionInteractor backActionInteractor2 = BackActionInteractor.this;
                if (booleanValue) {
                    if (!backActionInteractor2.isCallbackRegistered) {
                        NotificationShadeWindowView notificationShadeWindowView = ((NotificationShadeWindowControllerImpl) backActionInteractor2.notificationShadeWindowController).mWindowRootView;
                        if (notificationShadeWindowView != null && (viewRootImpl2 = notificationShadeWindowView.getViewRootImpl()) != null) {
                            windowOnBackInvokedDispatcher = viewRootImpl2.getOnBackInvokedDispatcher();
                        }
                        if (windowOnBackInvokedDispatcher != null) {
                            windowOnBackInvokedDispatcher.registerOnBackInvokedCallback(0, backActionInteractor2.callback);
                            backActionInteractor2.isCallbackRegistered = true;
                        }
                    }
                } else if (backActionInteractor2.isCallbackRegistered) {
                    NotificationShadeWindowView notificationShadeWindowView2 = ((NotificationShadeWindowControllerImpl) backActionInteractor2.notificationShadeWindowController).mWindowRootView;
                    if (notificationShadeWindowView2 != null && (viewRootImpl = notificationShadeWindowView2.getViewRootImpl()) != null) {
                        windowOnBackInvokedDispatcher = viewRootImpl.getOnBackInvokedDispatcher();
                    }
                    if (windowOnBackInvokedDispatcher != null) {
                        windowOnBackInvokedDispatcher.unregisterOnBackInvokedCallback(backActionInteractor2.callback);
                        backActionInteractor2.isCallbackRegistered = false;
                    }
                }
                return Unit.INSTANCE;
            }
        };
        this.label = 1;
        ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
        return coroutineSingletons;
    }
}

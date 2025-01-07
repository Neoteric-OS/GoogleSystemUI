package com.android.systemui.communal.widgets;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.RemoteException;
import android.view.View;
import android.widget.RemoteViews;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor;
import com.android.systemui.communal.util.InteractionHandlerDelegate;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.plugins.ActivityStarter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WidgetInteractionHandler implements RemoteViews.InteractionHandler {
    public final ActivityStarter activityStarter;
    public final CoroutineScope applicationScope;
    public final InteractionHandlerDelegate delegate;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final CoroutineContext uiBackgroundContext;
    public final WidgetTrampolineInteractor widgetTrampolineInteractor;

    public WidgetInteractionHandler(CoroutineScope coroutineScope, CoroutineContext coroutineContext, ActivityStarter activityStarter, KeyguardUpdateMonitor keyguardUpdateMonitor, CommunalSceneInteractor communalSceneInteractor, WidgetTrampolineInteractor widgetTrampolineInteractor, LogBuffer logBuffer) {
        this.applicationScope = coroutineScope;
        this.uiBackgroundContext = coroutineContext;
        this.activityStarter = activityStarter;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.widgetTrampolineInteractor = widgetTrampolineInteractor;
        this.delegate = new InteractionHandlerDelegate(communalSceneInteractor, new Function1() { // from class: com.android.systemui.communal.widgets.WidgetInteractionHandler$delegate$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(((View) obj) instanceof CommunalAppWidgetHostView);
            }
        }, new InteractionHandlerDelegate.IntentStarter() { // from class: com.android.systemui.communal.widgets.WidgetInteractionHandler$delegate$2
            public StandaloneCoroutine job;

            @Override // com.android.systemui.communal.util.InteractionHandlerDelegate.IntentStarter
            public final void startActivity(PendingIntent pendingIntent, Intent intent, ActivityOptions activityOptions, CommunalTransitionAnimatorController communalTransitionAnimatorController) {
                StandaloneCoroutine standaloneCoroutine = this.job;
                if (standaloneCoroutine != null) {
                    standaloneCoroutine.cancel(null);
                }
                this.job = null;
                final WidgetInteractionHandler widgetInteractionHandler = WidgetInteractionHandler.this;
                widgetInteractionHandler.activityStarter.startPendingIntentMaybeDismissingKeyguard(pendingIntent, false, new Runnable() { // from class: com.android.systemui.communal.widgets.WidgetInteractionHandler$startActivityIntent$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.communal.widgets.WidgetInteractionHandler$startActivityIntent$1$1, reason: invalid class name */
                    final class AnonymousClass1 extends SuspendLambda implements Function2 {
                        int label;
                        final /* synthetic */ WidgetInteractionHandler this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass1(WidgetInteractionHandler widgetInteractionHandler, Continuation continuation) {
                            super(2, continuation);
                            this.this$0 = widgetInteractionHandler;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object obj, Continuation continuation) {
                            return new AnonymousClass1(this.this$0, continuation);
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
                            KeyguardUpdateMonitor keyguardUpdateMonitor = this.this$0.keyguardUpdateMonitor;
                            if (keyguardUpdateMonitor.mIsDreaming) {
                                try {
                                    keyguardUpdateMonitor.mDreamManager.awaken();
                                } catch (RemoteException e) {
                                    keyguardUpdateMonitor.mLogger.logException(e, "Unable to awaken from dream");
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        WidgetInteractionHandler widgetInteractionHandler2 = WidgetInteractionHandler.this;
                        CoroutineTracingKt.launch$default(widgetInteractionHandler2.applicationScope, widgetInteractionHandler2.uiBackgroundContext, new AnonymousClass1(widgetInteractionHandler2, null), 4);
                    }
                }, communalTransitionAnimatorController, intent, activityOptions.toBundle(), null);
            }

            @Override // com.android.systemui.communal.util.InteractionHandlerDelegate.IntentStarter
            public final boolean startPendingIntent(View view, PendingIntent pendingIntent, Intent intent, ActivityOptions activityOptions) {
                StandaloneCoroutine standaloneCoroutine = this.job;
                if (standaloneCoroutine != null) {
                    standaloneCoroutine.cancel(null);
                }
                this.job = null;
                WidgetInteractionHandler widgetInteractionHandler = WidgetInteractionHandler.this;
                this.job = CoroutineTracingKt.launch$default(widgetInteractionHandler.applicationScope, null, new WidgetInteractionHandler$delegate$2$startPendingIntent$1(widgetInteractionHandler, null), 6);
                return super.startPendingIntent(view, pendingIntent, intent, activityOptions);
            }
        }, new Logger(logBuffer, "WidgetInteractionHandler"));
    }

    public final boolean onInteraction(View view, PendingIntent pendingIntent, RemoteViews.RemoteResponse remoteResponse) {
        return this.delegate.onInteraction(view, pendingIntent, remoteResponse);
    }
}

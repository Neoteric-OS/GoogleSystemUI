package com.android.systemui.communal.domain.interactor;

import android.app.ActivityManager;
import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.util.kotlin.SuspendKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ long $startTime;
    int label;
    final /* synthetic */ WidgetTrampolineInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function1 {
        int label;
        final /* synthetic */ WidgetTrampolineInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(WidgetTrampolineInteractor widgetTrampolineInteractor, Continuation continuation) {
            super(1, continuation);
            this.this$0 = widgetTrampolineInteractor;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new AnonymousClass1(this.this$0, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v2, types: [com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForNewForegroundTask$2$listener$1, com.android.systemui.shared.system.TaskStackChangeListener] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final WidgetTrampolineInteractor widgetTrampolineInteractor = this.this$0;
                this.label = 1;
                widgetTrampolineInteractor.getClass();
                final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt__IntrinsicsJvmKt.intercepted(this));
                cancellableContinuationImpl.initCancellability();
                final ?? r3 = new TaskStackChangeListener() { // from class: com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForNewForegroundTask$2$listener$1
                    @Override // com.android.systemui.shared.system.TaskStackChangeListener
                    public final void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
                        CancellableContinuationImpl cancellableContinuationImpl2 = CancellableContinuationImpl.this;
                        if (cancellableContinuationImpl2.isCompleted()) {
                            return;
                        }
                        cancellableContinuationImpl2.resume(Unit.INSTANCE, null);
                    }
                };
                widgetTrampolineInteractor.taskStackChangeListeners.registerTaskStackListener(r3);
                cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForNewForegroundTask$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        WidgetTrampolineInteractor.this.taskStackChangeListeners.unregisterTaskStackListener(r3);
                        return Unit.INSTANCE;
                    }
                });
                Object result = cancellableContinuationImpl.getResult();
                if (result != coroutineSingletons) {
                    result = Unit.INSTANCE;
                }
                if (result == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Boolean.TRUE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$2$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function1 {
        final /* synthetic */ long $startTime;
        int label;
        final /* synthetic */ WidgetTrampolineInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(WidgetTrampolineInteractor widgetTrampolineInteractor, long j, Continuation continuation) {
            super(1, continuation);
            this.this$0 = widgetTrampolineInteractor;
            this.$startTime = j;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new AnonymousClass2(this.this$0, this.$startTime, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WidgetTrampolineInteractor widgetTrampolineInteractor = this.this$0;
                long j = this.$startTime;
                this.label = 1;
                obj = WidgetTrampolineInteractor.access$waitForActivityStartByPolling(widgetTrampolineInteractor, j, this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.communal.domain.interactor.WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$2$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function1 {
        int label;
        final /* synthetic */ WidgetTrampolineInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(WidgetTrampolineInteractor widgetTrampolineInteractor, Continuation continuation) {
            super(1, continuation);
            this.this$0 = widgetTrampolineInteractor;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return new AnonymousClass3(this.this$0, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WidgetTrampolineInteractor widgetTrampolineInteractor = this.this$0;
                this.label = 1;
                widgetTrampolineInteractor.getClass();
                SceneKey sceneKey = Scenes.Bouncer;
                Object collect = new FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1(new WidgetTrampolineInteractor$waitForTransitionAwayFromHub$2(2, null), widgetTrampolineInteractor.keyguardTransitionInteractor.isFinishedIn(KeyguardState.GLANCEABLE_HUB)).collect(WidgetTrampolineInteractor$waitForTransitionAwayFromHub$3.INSTANCE, this);
                if (collect != coroutineSingletons) {
                    collect = Unit.INSTANCE;
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
            return Boolean.FALSE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$2(WidgetTrampolineInteractor widgetTrampolineInteractor, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = widgetTrampolineInteractor;
        this.$startTime = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$2(this.this$0, this.$startTime, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((WidgetTrampolineInteractor$waitForActivityStartWhileOnHub$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function1[] function1Arr = {new AnonymousClass1(this.this$0, null), new AnonymousClass2(this.this$0, this.$startTime, null), new AnonymousClass3(this.this$0, null)};
            this.label = 1;
            obj = SuspendKt.race(function1Arr, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}

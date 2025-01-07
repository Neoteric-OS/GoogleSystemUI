package com.google.android.systemui.tips.domain.interactor;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.google.android.systemui.tips.data.repository.ContextualTipsRepository;
import com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ContextualTipsInteractor$start$3 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ContextualTipsInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$start$3$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function5 {
        /* synthetic */ int I$0;
        /* synthetic */ Object L$0;
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        int label;

        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            boolean booleanValue2 = ((Boolean) obj2).booleanValue();
            int intValue = ((Number) obj4).intValue();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(5, (Continuation) obj5);
            anonymousClass1.Z$0 = booleanValue;
            anonymousClass1.Z$1 = booleanValue2;
            anonymousClass1.L$0 = (String) obj3;
            anonymousClass1.I$0 = intValue;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf((!this.Z$0 && !this.Z$1) && Intrinsics.areEqual((String) this.L$0, "ios") && (this.I$0 == 5));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$start$3$3, reason: invalid class name */
    public final class AnonymousClass3 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ ContextualTipsInteractor this$0;

        public /* synthetic */ AnonymousClass3(ContextualTipsInteractor contextualTipsInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = contextualTipsInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    int intValue = ((Number) obj).intValue();
                    ContextualTipsInteractor contextualTipsInteractor = this.this$0;
                    contextualTipsInteractor.getClass();
                    CoroutineScope coroutineScope = contextualTipsInteractor.applicationScope;
                    CoroutineDispatcher coroutineDispatcher = contextualTipsInteractor.mainDispatcher;
                    if (intValue > 0 && intValue % 2 == 0) {
                        BuildersKt.launch$default(coroutineScope, coroutineDispatcher, null, new ContextualTipsInteractor$onPowerMenuDismissCountChange$1(contextualTipsInteractor, null), 2);
                        StateFlowImpl stateFlowImpl = contextualTipsInteractor.repository._powerMenuDismissCount;
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, 0);
                    }
                    if (intValue > 0) {
                        StandaloneCoroutine standaloneCoroutine = contextualTipsInteractor.resetPowerMenuDismissCountJob;
                        if (standaloneCoroutine != null) {
                            standaloneCoroutine.cancel(null);
                        }
                        contextualTipsInteractor.resetPowerMenuDismissCountJob = BuildersKt.launch$default(coroutineScope, coroutineDispatcher, null, new ContextualTipsInteractor$onPowerMenuDismissCountChange$2(contextualTipsInteractor, null), 2);
                    }
                    break;
                case 1:
                    int intValue2 = ((Number) obj).intValue();
                    ContextualTipsInteractor contextualTipsInteractor2 = this.this$0;
                    contextualTipsInteractor2.getClass();
                    if (intValue2 > 0 && intValue2 % 2 == 0) {
                        ContextualTipsInteractor$onEligibleVolumeDialogDismissalCountChange$1 contextualTipsInteractor$onEligibleVolumeDialogDismissalCountChange$1 = new ContextualTipsInteractor$onEligibleVolumeDialogDismissalCountChange$1(contextualTipsInteractor2, null);
                        BuildersKt.launch$default(contextualTipsInteractor2.applicationScope, contextualTipsInteractor2.mainDispatcher, null, contextualTipsInteractor$onEligibleVolumeDialogDismissalCountChange$1, 2);
                        StateFlowImpl stateFlowImpl2 = contextualTipsInteractor2.repository._eligibleVolumeDialogDismissals;
                        stateFlowImpl2.getClass();
                        stateFlowImpl2.updateState(null, 0);
                    }
                    break;
                case 2:
                    int intValue3 = ((Number) obj).intValue();
                    ContextualTipsInteractor contextualTipsInteractor3 = this.this$0;
                    contextualTipsInteractor3.getClass();
                    CoroutineScope coroutineScope2 = contextualTipsInteractor3.applicationScope;
                    CoroutineDispatcher coroutineDispatcher2 = contextualTipsInteractor3.mainDispatcher;
                    if (intValue3 > 0 && intValue3 % 2 == 0) {
                        BuildersKt.launch$default(coroutineScope2, coroutineDispatcher2, null, new ContextualTipsInteractor$onAssistantDismissCountChange$1(contextualTipsInteractor3, null), 2);
                        ContextualTipsRepository contextualTipsRepository = contextualTipsInteractor3.repository;
                        StateFlowImpl stateFlowImpl3 = contextualTipsRepository._approxAssistantDismissals;
                        stateFlowImpl3.getClass();
                        stateFlowImpl3.updateState(null, 0);
                        StateFlowImpl stateFlowImpl4 = contextualTipsRepository._assistantDismissals;
                        stateFlowImpl4.getClass();
                        stateFlowImpl4.updateState(null, 0);
                    }
                    if (intValue3 > 0) {
                        StandaloneCoroutine standaloneCoroutine2 = contextualTipsInteractor3.resetAssistantDismissCountJob;
                        if (standaloneCoroutine2 != null) {
                            standaloneCoroutine2.cancel(null);
                        }
                        contextualTipsInteractor3.resetAssistantDismissCountJob = BuildersKt.launch$default(coroutineScope2, coroutineDispatcher2, null, new ContextualTipsInteractor$onAssistantDismissCountChange$2(contextualTipsInteractor3, null), 2);
                    }
                    break;
                case 3:
                    ((Number) obj).intValue();
                    final ContextualTipsInteractor contextualTipsInteractor4 = this.this$0;
                    StateFlowImpl stateFlowImpl5 = contextualTipsInteractor4.repository._assistantStartCount;
                    stateFlowImpl5.updateState(null, Integer.valueOf(((Number) stateFlowImpl5.getValue()).intValue() + 1));
                    if (!contextualTipsInteractor4.isListeningTaskStack) {
                        TaskStackChangeListeners.INSTANCE.registerTaskStackListener(contextualTipsInteractor4.taskListener);
                        contextualTipsInteractor4.isListeningTaskStack = true;
                    }
                    StandaloneCoroutine standaloneCoroutine3 = contextualTipsInteractor4.removeTaskStackListenerJob;
                    if (standaloneCoroutine3 != null) {
                        standaloneCoroutine3.cancel(null);
                    }
                    ContextualTipsInteractor$listenAssistantDismiss$1 contextualTipsInteractor$listenAssistantDismiss$1 = new ContextualTipsInteractor$listenAssistantDismiss$1(contextualTipsInteractor4, null);
                    CoroutineScope coroutineScope3 = contextualTipsInteractor4.applicationScope;
                    CoroutineDispatcher coroutineDispatcher3 = contextualTipsInteractor4.mainDispatcher;
                    contextualTipsInteractor4.removeTaskStackListenerJob = BuildersKt.launch$default(coroutineScope3, coroutineDispatcher3, null, contextualTipsInteractor$listenAssistantDismiss$1, 2);
                    contextualTipsInteractor4.tapGestureDetector.addOnGestureDetectedCallback("ContextualTipsInteractor", new Function1() { // from class: com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$listenAssistantDismiss$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            MotionEvent motionEvent = (MotionEvent) obj2;
                            DisplayMetrics displayMetrics = ContextualTipsInteractor.this.applicationContext.getResources().getDisplayMetrics();
                            motionEvent.getX();
                            int i = displayMetrics.widthPixels;
                            motionEvent.getY();
                            if (ContextualTipsInteractor.this.isAssistantDismiss(motionEvent.getX(), motionEvent.getY(), displayMetrics.widthPixels, displayMetrics.heightPixels)) {
                                StateFlowImpl stateFlowImpl6 = ContextualTipsInteractor.this.repository._approxAssistantDismissals;
                                stateFlowImpl6.updateState(null, Integer.valueOf(((Number) stateFlowImpl6.getValue()).intValue() + 1));
                                StandaloneCoroutine standaloneCoroutine4 = ContextualTipsInteractor.this.removeTapGestureCallbackJob;
                                if (standaloneCoroutine4 != null) {
                                    standaloneCoroutine4.cancel(null);
                                }
                                ContextualTipsInteractor.this.tapGestureDetector.removeOnGestureDetectedCallback("ContextualTipsInteractor");
                            }
                            return Unit.INSTANCE;
                        }
                    });
                    StandaloneCoroutine standaloneCoroutine4 = contextualTipsInteractor4.removeTapGestureCallbackJob;
                    if (standaloneCoroutine4 != null) {
                        standaloneCoroutine4.cancel(null);
                    }
                    contextualTipsInteractor4.removeTapGestureCallbackJob = BuildersKt.launch$default(coroutineScope3, coroutineDispatcher3, null, new ContextualTipsInteractor$listenAssistantDismiss$3(contextualTipsInteractor4, null), 2);
                    StandaloneCoroutine standaloneCoroutine5 = contextualTipsInteractor4.resetAssistantStartCountJob;
                    if (standaloneCoroutine5 != null) {
                        standaloneCoroutine5.cancel(null);
                    }
                    contextualTipsInteractor4.resetAssistantStartCountJob = BuildersKt.launch$default(coroutineScope3, coroutineDispatcher3, null, new ContextualTipsInteractor$listenAssistantDismiss$4(contextualTipsInteractor4, null), 2);
                    break;
                case 4:
                    ((Boolean) obj).getClass();
                    ContextualTipsInteractor contextualTipsInteractor5 = this.this$0;
                    int streamVolume = contextualTipsInteractor5.audioManager.getStreamVolume(3);
                    int ringerMode = contextualTipsInteractor5.audioManager.getRingerMode();
                    if (streamVolume == 0 && ringerMode == 2) {
                        StateFlowImpl stateFlowImpl6 = contextualTipsInteractor5.repository._eligibleVolumeDialogDismissals;
                        stateFlowImpl6.updateState(null, Integer.valueOf(((Number) stateFlowImpl6.getValue()).intValue() + 1));
                    }
                    break;
                case 5:
                    ((Boolean) obj).getClass();
                    StateFlowImpl stateFlowImpl7 = this.this$0.repository._powerMenuStartCount;
                    stateFlowImpl7.updateState(null, Integer.valueOf(((Number) stateFlowImpl7.getValue()).intValue() + 1));
                    break;
                case 6:
                    if (!((Boolean) obj).booleanValue()) {
                        StateFlowImpl stateFlowImpl8 = this.this$0.repository._powerMenuDismissCount;
                        stateFlowImpl8.updateState(null, Integer.valueOf(((Number) stateFlowImpl8.getValue()).intValue() + 1));
                    }
                    break;
                case 7:
                    ((Number) obj).intValue();
                    Object access$refreshPreconditions = ContextualTipsInteractor.access$refreshPreconditions(this.this$0, continuation);
                    if (access$refreshPreconditions != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
                case 8:
                    ((Boolean) obj).getClass();
                    ContextualTipsInteractor contextualTipsInteractor6 = this.this$0;
                    if (((Number) ((StateFlowImpl) contextualTipsInteractor6.repository.assistantStartCount.$$delegate_0).getValue()).intValue() > 0) {
                        Intent intent = new Intent("com.google.android.apps.tips.contextual.triggering.DISMISS_ON_SCREEN");
                        intent.setPackage("com.google.android.apps.tips");
                        try {
                            contextualTipsInteractor6.applicationContext.sendBroadcast(intent, "com.google.android.systemui.permission.RECEIVE_CONTEXTUAL_UI_DISMISS_SIGNAL");
                        } catch (Exception e) {
                            e.toString();
                        }
                    }
                    break;
                default:
                    ((Number) obj).intValue();
                    Object access$refreshPreconditions2 = ContextualTipsInteractor.access$refreshPreconditions(this.this$0, continuation);
                    if (access$refreshPreconditions2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        break;
                    }
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextualTipsInteractor$start$3(ContextualTipsInteractor contextualTipsInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contextualTipsInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ContextualTipsInteractor$start$3(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ContextualTipsInteractor$start$3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ContextualTipsInteractor contextualTipsInteractor = this.this$0;
            ReadonlyStateFlow readonlyStateFlow = contextualTipsInteractor.isOver30Days;
            SetupWizardRepositoryImpl setupWizardRepositoryImpl = contextualTipsInteractor.setupWizardRepository;
            ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(FlowKt.distinctUntilChanged(FlowKt.combine(readonlyStateFlow, setupWizardRepositoryImpl.isWipedOut, setupWizardRepositoryImpl.priorDeviceType, contextualTipsInteractor.repository.longPressOnPowerBehavior, new AnonymousClass1(5, null))), new ContextualTipsInteractor$start$3$invokeSuspend$$inlined$flatMapLatest$1(this.this$0, null));
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, 0);
            this.label = 1;
            if (transformLatest.collect(anonymousClass3, this) == coroutineSingletons) {
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

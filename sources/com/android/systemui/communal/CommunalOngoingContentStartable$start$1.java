package com.android.systemui.communal;

import android.app.smartspace.SmartspaceSession;
import android.util.Log;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.communal.data.repository.CommunalMediaRepositoryImpl;
import com.android.systemui.communal.data.repository.CommunalSmartspaceRepositoryImpl;
import com.android.systemui.communal.smartspace.CommunalSmartspaceController;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.util.concurrency.ExecutionImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptyList;
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
final class CommunalOngoingContentStartable$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ CommunalOngoingContentStartable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalOngoingContentStartable$start$1(CommunalOngoingContentStartable communalOngoingContentStartable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalOngoingContentStartable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalOngoingContentStartable$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((CommunalOngoingContentStartable$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        final CommunalOngoingContentStartable communalOngoingContentStartable = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = communalOngoingContentStartable.communalInteractor.isCommunalEnabled;
        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.communal.CommunalOngoingContentStartable$start$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                boolean booleanValue = ((Boolean) obj2).booleanValue();
                CommunalOngoingContentStartable communalOngoingContentStartable2 = CommunalOngoingContentStartable.this;
                if (booleanValue) {
                    CommunalMediaRepositoryImpl communalMediaRepositoryImpl = communalOngoingContentStartable2.communalMediaRepository;
                    communalMediaRepositoryImpl.mediaDataManager.addListener(communalMediaRepositoryImpl);
                    final CommunalSmartspaceRepositoryImpl communalSmartspaceRepositoryImpl = communalOngoingContentStartable2.communalSmartspaceRepository;
                    final int i2 = 1;
                    communalSmartspaceRepositoryImpl.uiExecutor.execute(new Runnable() { // from class: com.android.systemui.communal.data.repository.CommunalSmartspaceRepositoryImpl$stopListening$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i2) {
                                case 0:
                                    CommunalSmartspaceRepositoryImpl communalSmartspaceRepositoryImpl2 = communalSmartspaceRepositoryImpl;
                                    CommunalSmartspaceController communalSmartspaceController = communalSmartspaceRepositoryImpl2.communalSmartspaceController;
                                    ExecutionImpl executionImpl = communalSmartspaceController.execution;
                                    executionImpl.assertIsMainThread();
                                    BcSmartspaceDataPlugin bcSmartspaceDataPlugin = communalSmartspaceController.plugin;
                                    if (bcSmartspaceDataPlugin != null) {
                                        bcSmartspaceDataPlugin.unregisterListener(communalSmartspaceRepositoryImpl2);
                                    }
                                    communalSmartspaceController.listeners.remove(communalSmartspaceRepositoryImpl2);
                                    if (communalSmartspaceController.listeners.isEmpty()) {
                                        executionImpl.assertIsMainThread();
                                        SmartspaceSession smartspaceSession = communalSmartspaceController.session;
                                        if (smartspaceSession != null) {
                                            smartspaceSession.removeOnTargetsAvailableListener(communalSmartspaceController.sessionListener);
                                            smartspaceSession.close();
                                            communalSmartspaceController.session = null;
                                            if (bcSmartspaceDataPlugin != null) {
                                                bcSmartspaceDataPlugin.registerSmartspaceEventNotifier(null);
                                            }
                                            if (bcSmartspaceDataPlugin != null) {
                                                bcSmartspaceDataPlugin.onTargetsAvailable(EmptyList.INSTANCE);
                                            }
                                            Log.d("CommunalSmartspaceCtrlr", "Ending smartspace session for communal");
                                            break;
                                        }
                                    }
                                    break;
                                default:
                                    CommunalSmartspaceRepositoryImpl communalSmartspaceRepositoryImpl3 = communalSmartspaceRepositoryImpl;
                                    CommunalSmartspaceController communalSmartspaceController2 = communalSmartspaceRepositoryImpl3.communalSmartspaceController;
                                    communalSmartspaceController2.execution.assertIsMainThread();
                                    BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = communalSmartspaceController2.plugin;
                                    if (bcSmartspaceDataPlugin2 != null) {
                                        bcSmartspaceDataPlugin2.registerListener(communalSmartspaceRepositoryImpl3);
                                    }
                                    communalSmartspaceController2.listeners.add(communalSmartspaceRepositoryImpl3);
                                    communalSmartspaceController2.connectSession();
                                    break;
                            }
                        }
                    });
                } else {
                    CommunalMediaRepositoryImpl communalMediaRepositoryImpl2 = communalOngoingContentStartable2.communalMediaRepository;
                    communalMediaRepositoryImpl2.mediaDataManager.removeListener(communalMediaRepositoryImpl2);
                    final CommunalSmartspaceRepositoryImpl communalSmartspaceRepositoryImpl2 = communalOngoingContentStartable2.communalSmartspaceRepository;
                    final int i3 = 0;
                    communalSmartspaceRepositoryImpl2.uiExecutor.execute(new Runnable() { // from class: com.android.systemui.communal.data.repository.CommunalSmartspaceRepositoryImpl$stopListening$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i3) {
                                case 0:
                                    CommunalSmartspaceRepositoryImpl communalSmartspaceRepositoryImpl22 = communalSmartspaceRepositoryImpl2;
                                    CommunalSmartspaceController communalSmartspaceController = communalSmartspaceRepositoryImpl22.communalSmartspaceController;
                                    ExecutionImpl executionImpl = communalSmartspaceController.execution;
                                    executionImpl.assertIsMainThread();
                                    BcSmartspaceDataPlugin bcSmartspaceDataPlugin = communalSmartspaceController.plugin;
                                    if (bcSmartspaceDataPlugin != null) {
                                        bcSmartspaceDataPlugin.unregisterListener(communalSmartspaceRepositoryImpl22);
                                    }
                                    communalSmartspaceController.listeners.remove(communalSmartspaceRepositoryImpl22);
                                    if (communalSmartspaceController.listeners.isEmpty()) {
                                        executionImpl.assertIsMainThread();
                                        SmartspaceSession smartspaceSession = communalSmartspaceController.session;
                                        if (smartspaceSession != null) {
                                            smartspaceSession.removeOnTargetsAvailableListener(communalSmartspaceController.sessionListener);
                                            smartspaceSession.close();
                                            communalSmartspaceController.session = null;
                                            if (bcSmartspaceDataPlugin != null) {
                                                bcSmartspaceDataPlugin.registerSmartspaceEventNotifier(null);
                                            }
                                            if (bcSmartspaceDataPlugin != null) {
                                                bcSmartspaceDataPlugin.onTargetsAvailable(EmptyList.INSTANCE);
                                            }
                                            Log.d("CommunalSmartspaceCtrlr", "Ending smartspace session for communal");
                                            break;
                                        }
                                    }
                                    break;
                                default:
                                    CommunalSmartspaceRepositoryImpl communalSmartspaceRepositoryImpl3 = communalSmartspaceRepositoryImpl2;
                                    CommunalSmartspaceController communalSmartspaceController2 = communalSmartspaceRepositoryImpl3.communalSmartspaceController;
                                    communalSmartspaceController2.execution.assertIsMainThread();
                                    BcSmartspaceDataPlugin bcSmartspaceDataPlugin2 = communalSmartspaceController2.plugin;
                                    if (bcSmartspaceDataPlugin2 != null) {
                                        bcSmartspaceDataPlugin2.registerListener(communalSmartspaceRepositoryImpl3);
                                    }
                                    communalSmartspaceController2.listeners.add(communalSmartspaceRepositoryImpl3);
                                    communalSmartspaceController2.connectSession();
                                    break;
                            }
                        }
                    });
                }
                return Unit.INSTANCE;
            }
        };
        this.label = 1;
        ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
        return coroutineSingletons;
    }
}

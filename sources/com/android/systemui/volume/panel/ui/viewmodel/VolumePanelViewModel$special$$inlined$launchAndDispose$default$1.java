package com.android.systemui.volume.panel.ui.viewmodel;

import com.android.systemui.util.kotlin.DisposableHandleExtKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelViewModel$special$$inlined$launchAndDispose$default$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ VolumePanelViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumePanelViewModel$special$$inlined$launchAndDispose$default$1(VolumePanelViewModel volumePanelViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = volumePanelViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new VolumePanelViewModel$special$$inlined$launchAndDispose$default$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((VolumePanelViewModel$special$$inlined$launchAndDispose$default$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        VolumePanelViewModel volumePanelViewModel = this.this$0;
        volumePanelViewModel.dumpManager.registerNormalDumpable("VolumePanelViewModel", volumePanelViewModel);
        final VolumePanelViewModel volumePanelViewModel2 = this.this$0;
        DisposableHandle disposableHandle = new DisposableHandle() { // from class: com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$1$1
            @Override // kotlinx.coroutines.DisposableHandle
            public final void dispose() {
                VolumePanelViewModel.this.dumpManager.unregisterDumpable("VolumePanelViewModel");
            }
        };
        this.label = 1;
        DisposableHandleExtKt.awaitCancellationThenDispose(disposableHandle, this);
        return coroutineSingletons;
    }
}

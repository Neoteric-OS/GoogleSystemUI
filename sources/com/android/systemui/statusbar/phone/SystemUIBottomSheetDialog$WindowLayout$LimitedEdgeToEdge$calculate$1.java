package com.android.systemui.statusbar.phone;

import android.content.res.Configuration;
import com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SystemUIBottomSheetDialog.WindowLayout.LimitedEdgeToEdge this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$1(SystemUIBottomSheetDialog.WindowLayout.LimitedEdgeToEdge limitedEdgeToEdge, Continuation continuation) {
        super(2, continuation);
        this.this$0 = limitedEdgeToEdge;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$1 systemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$1 = new SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$1(this.this$0, continuation);
        systemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$1.L$0 = obj;
        return systemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SystemUIBottomSheetDialog$WindowLayout$LimitedEdgeToEdge$calculate$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Configuration configuration = this.this$0.context.getResources().getConfiguration();
            this.label = 1;
            if (flowCollector.emit(configuration, this) == coroutineSingletons) {
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

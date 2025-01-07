package com.android.systemui.biometrics.ui.viewmodel;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ PromptIconViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3(PromptIconViewModel promptIconViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = promptIconViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3 promptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3 = new PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3(this.this$0, (Continuation) obj3);
        promptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3.L$0 = (FlowCollector) obj;
        promptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3.L$1 = (Object[]) obj2;
        return promptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:83:0x0161, code lost:
    
        if (r1 != false) goto L77;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instructions count: 373
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel$iconAsset$lambda$2$$inlined$combine$2$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

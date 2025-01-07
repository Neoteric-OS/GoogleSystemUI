package com.android.systemui.qs.footer.ui.viewmodel;

import com.android.systemui.globalactions.GlobalActionsDialogLite;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FooterActionsViewModel$Factory$create$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ GlobalActionsDialogLite $globalActionsDialogLite;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FooterActionsViewModel$Factory$create$2(GlobalActionsDialogLite globalActionsDialogLite, Continuation continuation) {
        super(2, continuation);
        this.$globalActionsDialogLite = globalActionsDialogLite;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FooterActionsViewModel$Factory$create$2(this.$globalActionsDialogLite, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((FooterActionsViewModel$Factory$create$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                throw new KotlinNothingValueException();
            }
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            DelayKt.awaitCancellation(this);
            return coroutineSingletons;
        } catch (Throwable th) {
            this.$globalActionsDialogLite.destroy();
            throw th;
        }
    }
}

package com.android.systemui.keyboard.shortcut.domain.interactor;

import com.android.systemui.model.SysUiState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ShortcutHelperStateInteractor$setSysUiStateFlagEnabled$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $enabled;
    int label;
    final /* synthetic */ ShortcutHelperStateInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShortcutHelperStateInteractor$setSysUiStateFlagEnabled$1(ShortcutHelperStateInteractor shortcutHelperStateInteractor, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = shortcutHelperStateInteractor;
        this.$enabled = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ShortcutHelperStateInteractor$setSysUiStateFlagEnabled$1(this.this$0, this.$enabled, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ShortcutHelperStateInteractor$setSysUiStateFlagEnabled$1 shortcutHelperStateInteractor$setSysUiStateFlagEnabled$1 = (ShortcutHelperStateInteractor$setSysUiStateFlagEnabled$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        shortcutHelperStateInteractor$setSysUiStateFlagEnabled$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        SysUiState sysUiState = this.this$0.sysUiState;
        sysUiState.setFlag(4294967296L, this.$enabled);
        this.this$0.displayTracker.getClass();
        sysUiState.commitUpdate(0);
        return Unit.INSTANCE;
    }
}

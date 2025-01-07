package com.android.systemui.qs.tiles;

import com.android.systemui.animation.Expandable;
import com.android.systemui.qs.tiles.impl.modes.domain.interactor.ModesTileUserActionInteractor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ModesTile$handleClick$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Expandable $expandable;
    int label;
    final /* synthetic */ ModesTile this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModesTile$handleClick$1(ModesTile modesTile, Expandable expandable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = modesTile;
        this.$expandable = expandable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ModesTile$handleClick$1(this.this$0, this.$expandable, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ModesTile$handleClick$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ModesTileUserActionInteractor modesTileUserActionInteractor = this.this$0.userActionInteractor;
            Expandable expandable = this.$expandable;
            this.label = 1;
            Object showDialog = modesTileUserActionInteractor.dialogDelegate.showDialog(expandable, this);
            if (showDialog != coroutineSingletons) {
                showDialog = unit;
            }
            if (showDialog == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}

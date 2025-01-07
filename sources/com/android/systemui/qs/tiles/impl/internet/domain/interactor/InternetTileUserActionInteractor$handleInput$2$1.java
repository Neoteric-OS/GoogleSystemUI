package com.android.systemui.qs.tiles.impl.internet.domain.interactor;

import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.statusbar.connectivity.AccessPointControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class InternetTileUserActionInteractor$handleInput$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ QSTileInput $this_with;
    int label;
    final /* synthetic */ InternetTileUserActionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InternetTileUserActionInteractor$handleInput$2$1(InternetTileUserActionInteractor internetTileUserActionInteractor, QSTileInput qSTileInput, Continuation continuation) {
        super(2, continuation);
        this.this$0 = internetTileUserActionInteractor;
        this.$this_with = qSTileInput;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new InternetTileUserActionInteractor$handleInput$2$1(this.this$0, this.$this_with, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        InternetTileUserActionInteractor$handleInput$2$1 internetTileUserActionInteractor$handleInput$2$1 = (InternetTileUserActionInteractor$handleInput$2$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        internetTileUserActionInteractor$handleInput$2$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        InternetTileUserActionInteractor internetTileUserActionInteractor = this.this$0;
        internetTileUserActionInteractor.internetDialogManager.create(((AccessPointControllerImpl) internetTileUserActionInteractor.accessPointController).canConfigMobileData(), ((AccessPointControllerImpl) this.this$0.accessPointController).canConfigWifi(), this.$this_with.action.getExpandable());
        return Unit.INSTANCE;
    }
}

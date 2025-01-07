package com.android.systemui.qs.tiles.impl.saver.domain.interactor;

import android.content.Context;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.qs.tiles.base.interactor.QSTileInput;
import com.android.systemui.qs.tiles.impl.saver.domain.DataSaverDialogDelegate;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DataSaverTileUserActionInteractor$handleInput$2$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ QSTileInput $this_with;
    int label;
    final /* synthetic */ DataSaverTileUserActionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataSaverTileUserActionInteractor$handleInput$2$2(DataSaverTileUserActionInteractor dataSaverTileUserActionInteractor, QSTileInput qSTileInput, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataSaverTileUserActionInteractor;
        this.$this_with = qSTileInput;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DataSaverTileUserActionInteractor$handleInput$2$2(this.this$0, this.$this_with, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DataSaverTileUserActionInteractor$handleInput$2$2 dataSaverTileUserActionInteractor$handleInput$2$2 = (DataSaverTileUserActionInteractor$handleInput$2$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        dataSaverTileUserActionInteractor$handleInput$2$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Unit unit;
        DialogTransitionAnimator.Controller m;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DataSaverTileUserActionInteractor dataSaverTileUserActionInteractor = this.this$0;
        SystemUIDialog.Factory factory = dataSaverTileUserActionInteractor.systemUIDialogFactory;
        Context context = dataSaverTileUserActionInteractor.context;
        SystemUIDialog create = factory.create(new DataSaverDialogDelegate(factory, context, dataSaverTileUserActionInteractor.backgroundContext, dataSaverTileUserActionInteractor.dataSaverController, dataSaverTileUserActionInteractor.sharedPreferences), context);
        Expandable expandable = this.$this_with.action.getExpandable();
        Unit unit2 = Unit.INSTANCE;
        if (expandable == null || (m = BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "start_data_saver", expandable)) == null) {
            unit = null;
        } else {
            DialogTransitionAnimator dialogTransitionAnimator = this.this$0.dialogTransitionAnimator;
            TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
            dialogTransitionAnimator.show(create, m, false);
            unit = unit2;
        }
        if (unit == null) {
            create.show();
        }
        return unit2;
    }
}

package com.android.systemui.statusbar.policy.ui.dialog;

import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.phone.ComponentSystemUIDialog;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ModesDialogDelegate$showDialog$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Expandable $expandable;
    int label;
    final /* synthetic */ ModesDialogDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModesDialogDelegate$showDialog$2(ModesDialogDelegate modesDialogDelegate, Expandable expandable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = modesDialogDelegate;
        this.$expandable = expandable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ModesDialogDelegate$showDialog$2(this.this$0, this.$expandable, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ModesDialogDelegate$showDialog$2 modesDialogDelegate$showDialog$2 = (ModesDialogDelegate$showDialog$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        modesDialogDelegate$showDialog$2.invokeSuspend(unit);
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
        ModesDialogDelegate modesDialogDelegate = this.this$0;
        if (modesDialogDelegate.currentDialog == null) {
            modesDialogDelegate.createDialog();
        }
        Expandable expandable = this.$expandable;
        Unit unit2 = Unit.INSTANCE;
        if (expandable == null || (m = BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "configure_priority_modes", expandable)) == null) {
            unit = null;
        } else {
            ModesDialogDelegate modesDialogDelegate2 = this.this$0;
            DialogTransitionAnimator dialogTransitionAnimator = modesDialogDelegate2.dialogTransitionAnimator;
            ComponentSystemUIDialog componentSystemUIDialog = modesDialogDelegate2.currentDialog;
            Intrinsics.checkNotNull(componentSystemUIDialog);
            TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
            dialogTransitionAnimator.show(componentSystemUIDialog, m, false);
            unit = unit2;
        }
        if (unit == null) {
            ComponentSystemUIDialog componentSystemUIDialog2 = this.this$0.currentDialog;
            Intrinsics.checkNotNull(componentSystemUIDialog2);
            componentSystemUIDialog2.show();
        }
        return unit2;
    }
}

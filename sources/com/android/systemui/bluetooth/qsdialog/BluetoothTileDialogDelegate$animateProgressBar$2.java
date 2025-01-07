package com.android.systemui.bluetooth.qsdialog;

import android.view.View;
import android.widget.ProgressBar;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BluetoothTileDialogDelegate$animateProgressBar$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $animate;
    final /* synthetic */ SystemUIDialog $dialog;
    int label;
    final /* synthetic */ BluetoothTileDialogDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothTileDialogDelegate$animateProgressBar$2(boolean z, BluetoothTileDialogDelegate bluetoothTileDialogDelegate, SystemUIDialog systemUIDialog, Continuation continuation) {
        super(2, continuation);
        this.$animate = z;
        this.this$0 = bluetoothTileDialogDelegate;
        this.$dialog = systemUIDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BluetoothTileDialogDelegate$animateProgressBar$2(this.$animate, this.this$0, this.$dialog, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothTileDialogDelegate$animateProgressBar$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$animate) {
                BluetoothTileDialogDelegate bluetoothTileDialogDelegate = this.this$0;
                SystemUIDialog systemUIDialog = this.$dialog;
                bluetoothTileDialogDelegate.getClass();
                ProgressBar progressBar = (ProgressBar) systemUIDialog.requireViewById(R.id.bluetooth_tile_dialog_progress_animation);
                View requireViewById = systemUIDialog.requireViewById(R.id.bluetooth_tile_dialog_progress_animation);
                if (progressBar.getVisibility() != 0) {
                    progressBar.setVisibility(0);
                    requireViewById.setVisibility(4);
                }
                return Unit.INSTANCE;
            }
            this.label = 1;
            if (DelayKt.delay(1500L, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        BluetoothTileDialogDelegate bluetoothTileDialogDelegate2 = this.this$0;
        SystemUIDialog systemUIDialog2 = this.$dialog;
        bluetoothTileDialogDelegate2.getClass();
        ProgressBar progressBar2 = (ProgressBar) systemUIDialog2.requireViewById(R.id.bluetooth_tile_dialog_progress_animation);
        View requireViewById2 = systemUIDialog2.requireViewById(R.id.bluetooth_tile_dialog_progress_animation);
        if (progressBar2.getVisibility() != 4) {
            progressBar2.setVisibility(4);
            requireViewById2.setVisibility(0);
        }
        return Unit.INSTANCE;
    }
}

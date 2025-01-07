package com.android.systemui.qs.tiles.impl.saver.domain;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DataSaverDialogDelegate implements SystemUIDialog.Delegate {
    public final CoroutineContext backgroundContext;
    public final Context context;
    public final DataSaverControllerImpl dataSaverController;
    public final SharedPreferences sharedPreferences;
    public final SystemUIDialog.Factory sysuiDialogFactory;

    public DataSaverDialogDelegate(SystemUIDialog.Factory factory, Context context, CoroutineContext coroutineContext, DataSaverControllerImpl dataSaverControllerImpl, SharedPreferences sharedPreferences) {
        this.sysuiDialogFactory = factory;
        this.context = context;
        this.backgroundContext = coroutineContext;
        this.dataSaverController = dataSaverControllerImpl;
        this.sharedPreferences = sharedPreferences;
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void beforeCreate(Dialog dialog) {
        SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        systemUIDialog.setTitle(R.string.data_usage_warning_body);
        systemUIDialog.setMessage(R.string.data_usage_restricted_body);
        systemUIDialog.setPositiveButton(R.string.data_usage_restricted_title, new DialogInterface.OnClickListener() { // from class: com.android.systemui.qs.tiles.impl.saver.domain.DataSaverDialogDelegate$beforeCreate$1$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.tiles.impl.saver.domain.DataSaverDialogDelegate$beforeCreate$1$1$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ DataSaverDialogDelegate this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(DataSaverDialogDelegate dataSaverDialogDelegate, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = dataSaverDialogDelegate;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass1(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
                    Unit unit = Unit.INSTANCE;
                    anonymousClass1.invokeSuspend(unit);
                    return unit;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    this.this$0.dataSaverController.setDataSaverEnabled(true);
                    return Unit.INSTANCE;
                }
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CoroutineContext coroutineContext = DataSaverDialogDelegate.this.backgroundContext;
                TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
                BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(coroutineContext.plus(EmptyCoroutineContext.INSTANCE)), null, null, new AnonymousClass1(DataSaverDialogDelegate.this, null), 3);
                DataSaverDialogDelegate.this.sharedPreferences.edit().putBoolean("data_saver_dialog_shown", true).apply();
            }
        });
        systemUIDialog.setNeutralButton(R.string.cancel, null, true);
        SystemUIDialog.setShowForAllUsers(systemUIDialog);
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        return this.sysuiDialogFactory.create(this, this.context);
    }
}

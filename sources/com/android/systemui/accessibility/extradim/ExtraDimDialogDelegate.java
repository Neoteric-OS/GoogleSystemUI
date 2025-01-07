package com.android.systemui.accessibility.extradim;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExtraDimDialogDelegate implements SystemUIDialog.Delegate {
    public final AccessibilityManager accessibilityManager;
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final Context context;
    public final ExtraDimDialogDelegate$onClickListener$1 onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.systemui.accessibility.extradim.ExtraDimDialogDelegate$onClickListener$1

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.accessibility.extradim.ExtraDimDialogDelegate$onClickListener$1$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ DialogInterface $dialog;
            int label;
            final /* synthetic */ ExtraDimDialogDelegate this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(DialogInterface dialogInterface, ExtraDimDialogDelegate extraDimDialogDelegate, Continuation continuation) {
                super(2, continuation);
                this.$dialog = dialogInterface;
                this.this$0 = extraDimDialogDelegate;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.$dialog, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.$dialog.dismiss();
                    ExtraDimDialogDelegate extraDimDialogDelegate = this.this$0;
                    this.label = 1;
                    extraDimDialogDelegate.getClass();
                    Object withContext = BuildersKt.withContext(extraDimDialogDelegate.backgroundDispatcher, new ExtraDimDialogDelegate$onRemoveExtraDimShortcutButtonClicked$2(extraDimDialogDelegate, null), this);
                    if (withContext != coroutineSingletons) {
                        withContext = unit;
                    }
                    if (withContext == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Context context = this.this$0.context;
                Toast.makeText(context, context.getText(R.string.accessibility_deprecate_extra_dim_dialog_toast), 1).show();
                return unit;
            }
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            ExtraDimDialogDelegate extraDimDialogDelegate = ExtraDimDialogDelegate.this;
            BuildersKt.launch$default(extraDimDialogDelegate.applicationScope, null, null, new AnonymousClass1(dialogInterface, extraDimDialogDelegate, null), 3);
        }
    };
    public final SystemUIDialog.Factory systemUIDialogFactory;
    public final UserTracker userTracker;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.accessibility.extradim.ExtraDimDialogDelegate$onClickListener$1] */
    public ExtraDimDialogDelegate(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, SystemUIDialog.Factory factory, AccessibilityManager accessibilityManager, UserTracker userTracker) {
        this.context = context;
        this.applicationScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
        this.systemUIDialogFactory = factory;
        this.accessibilityManager = accessibilityManager;
        this.userTracker = userTracker;
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void beforeCreate(Dialog dialog) {
        SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        systemUIDialog.setTitle(R.string.accessibility_deprecate_extra_dim_dialog_title);
        systemUIDialog.setView(LayoutInflater.from(systemUIDialog.getContext()).inflate(R.layout.accessibility_deprecate_extra_dim_dialog, (ViewGroup) null));
        systemUIDialog.setPositiveButton(R.string.accessibility_deprecate_extra_dim_dialog_button, this.onClickListener);
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        SystemUIDialog.Factory factory = this.systemUIDialogFactory;
        SystemUIDialog create = factory.create(this, factory.mContext);
        create.setCanceledOnTouchOutside(false);
        return create;
    }
}

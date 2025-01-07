package com.android.systemui.display.ui.viewmodel;

import android.os.Trace;
import android.util.Log;
import android.view.View;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.biometrics.Utils;
import com.android.systemui.display.data.repository.DisplayRepositoryImpl;
import com.android.systemui.display.data.repository.DisplayRepositoryImpl$pendingDisplay$1$1;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$PendingDisplay;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$toInteractorPendingDisplay$1;
import com.android.systemui.display.ui.view.MirroringConfirmationDialogDelegate;
import com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ConnectingDisplayViewModel$start$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ ConnectingDisplayViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectingDisplayViewModel$start$1(ConnectingDisplayViewModel connectingDisplayViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = connectingDisplayViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        ConnectingDisplayViewModel$start$1 connectingDisplayViewModel$start$1 = new ConnectingDisplayViewModel$start$1(this.this$0, (Continuation) obj3);
        connectingDisplayViewModel$start$1.L$0 = (ConnectedDisplayInteractor$PendingDisplay) obj;
        connectingDisplayViewModel$start$1.Z$0 = booleanValue;
        Unit unit = Unit.INSTANCE;
        connectingDisplayViewModel$start$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        final ConnectedDisplayInteractor$PendingDisplay connectedDisplayInteractor$PendingDisplay = (ConnectedDisplayInteractor$PendingDisplay) this.L$0;
        boolean z = this.Z$0;
        if (connectedDisplayInteractor$PendingDisplay == null) {
            this.this$0.dismissDialog$1();
        } else {
            final ConnectingDisplayViewModel connectingDisplayViewModel = this.this$0;
            connectingDisplayViewModel.dismissDialog$1();
            final int i = 0;
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.android.systemui.display.ui.viewmodel.ConnectingDisplayViewModel$showDialog$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.display.ui.viewmodel.ConnectingDisplayViewModel$showDialog$1$1, reason: invalid class name */
                final class AnonymousClass1 extends SuspendLambda implements Function2 {
                    final /* synthetic */ ConnectedDisplayInteractor$PendingDisplay $pendingDisplay;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass1(ConnectedDisplayInteractor$PendingDisplay connectedDisplayInteractor$PendingDisplay, Continuation continuation) {
                        super(2, continuation);
                        this.$pendingDisplay = connectedDisplayInteractor$PendingDisplay;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        return new AnonymousClass1(this.$pendingDisplay, continuation);
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
                            ConnectedDisplayInteractor$PendingDisplay connectedDisplayInteractor$PendingDisplay = this.$pendingDisplay;
                            this.label = 1;
                            StringBuilder sb = new StringBuilder("DisplayRepository#enable(");
                            DisplayRepositoryImpl$pendingDisplay$1$1 displayRepositoryImpl$pendingDisplay$1$1 = ((ConnectedDisplayInteractorImpl$toInteractorPendingDisplay$1) connectedDisplayInteractor$PendingDisplay).$this_toInteractorPendingDisplay;
                            int i2 = displayRepositoryImpl$pendingDisplay$1$1.$id;
                            String m = PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, i2, ")");
                            DisplayRepositoryImpl displayRepositoryImpl = displayRepositoryImpl$pendingDisplay$1$1.this$0;
                            boolean isEnabled = Trace.isEnabled();
                            if (isEnabled) {
                                TraceUtilsKt.beginSlice(m);
                            }
                            try {
                                if (DisplayRepositoryImpl.DEBUG) {
                                    Log.d("DisplayRepository", "Enabling display with id=" + i2);
                                }
                                displayRepositoryImpl.displayManager.enableConnectedDisplay(i2);
                                if (isEnabled) {
                                    TraceUtilsKt.endSlice();
                                }
                                displayRepositoryImpl$pendingDisplay$1$1.ignore();
                                if (unit == coroutineSingletons) {
                                    return coroutineSingletons;
                                }
                            } catch (Throwable th) {
                                if (isEnabled) {
                                    TraceUtilsKt.endSlice();
                                }
                                throw th;
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

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i) {
                        case 0:
                            ConnectingDisplayViewModel connectingDisplayViewModel2 = connectingDisplayViewModel;
                            BuildersKt.launch$default(connectingDisplayViewModel2.scope, connectingDisplayViewModel2.bgDispatcher, null, new AnonymousClass1(connectedDisplayInteractor$PendingDisplay, null), 2);
                            connectingDisplayViewModel.dismissDialog$1();
                            break;
                        default:
                            ConnectingDisplayViewModel connectingDisplayViewModel3 = connectingDisplayViewModel;
                            BuildersKt.launch$default(connectingDisplayViewModel3.scope, connectingDisplayViewModel3.bgDispatcher, null, new ConnectingDisplayViewModel$showDialog$2$1(connectedDisplayInteractor$PendingDisplay, null), 2);
                            connectingDisplayViewModel.dismissDialog$1();
                            break;
                    }
                }
            };
            final int i2 = 1;
            View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.android.systemui.display.ui.viewmodel.ConnectingDisplayViewModel$showDialog$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.display.ui.viewmodel.ConnectingDisplayViewModel$showDialog$1$1, reason: invalid class name */
                final class AnonymousClass1 extends SuspendLambda implements Function2 {
                    final /* synthetic */ ConnectedDisplayInteractor$PendingDisplay $pendingDisplay;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass1(ConnectedDisplayInteractor$PendingDisplay connectedDisplayInteractor$PendingDisplay, Continuation continuation) {
                        super(2, continuation);
                        this.$pendingDisplay = connectedDisplayInteractor$PendingDisplay;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        return new AnonymousClass1(this.$pendingDisplay, continuation);
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
                            ConnectedDisplayInteractor$PendingDisplay connectedDisplayInteractor$PendingDisplay = this.$pendingDisplay;
                            this.label = 1;
                            StringBuilder sb = new StringBuilder("DisplayRepository#enable(");
                            DisplayRepositoryImpl$pendingDisplay$1$1 displayRepositoryImpl$pendingDisplay$1$1 = ((ConnectedDisplayInteractorImpl$toInteractorPendingDisplay$1) connectedDisplayInteractor$PendingDisplay).$this_toInteractorPendingDisplay;
                            int i2 = displayRepositoryImpl$pendingDisplay$1$1.$id;
                            String m = PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, i2, ")");
                            DisplayRepositoryImpl displayRepositoryImpl = displayRepositoryImpl$pendingDisplay$1$1.this$0;
                            boolean isEnabled = Trace.isEnabled();
                            if (isEnabled) {
                                TraceUtilsKt.beginSlice(m);
                            }
                            try {
                                if (DisplayRepositoryImpl.DEBUG) {
                                    Log.d("DisplayRepository", "Enabling display with id=" + i2);
                                }
                                displayRepositoryImpl.displayManager.enableConnectedDisplay(i2);
                                if (isEnabled) {
                                    TraceUtilsKt.endSlice();
                                }
                                displayRepositoryImpl$pendingDisplay$1$1.ignore();
                                if (unit == coroutineSingletons) {
                                    return coroutineSingletons;
                                }
                            } catch (Throwable th) {
                                if (isEnabled) {
                                    TraceUtilsKt.endSlice();
                                }
                                throw th;
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

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i2) {
                        case 0:
                            ConnectingDisplayViewModel connectingDisplayViewModel2 = connectingDisplayViewModel;
                            BuildersKt.launch$default(connectingDisplayViewModel2.scope, connectingDisplayViewModel2.bgDispatcher, null, new AnonymousClass1(connectedDisplayInteractor$PendingDisplay, null), 2);
                            connectingDisplayViewModel.dismissDialog$1();
                            break;
                        default:
                            ConnectingDisplayViewModel connectingDisplayViewModel3 = connectingDisplayViewModel;
                            BuildersKt.launch$default(connectingDisplayViewModel3.scope, connectingDisplayViewModel3.bgDispatcher, null, new ConnectingDisplayViewModel$showDialog$2$1(connectedDisplayInteractor$PendingDisplay, null), 2);
                            connectingDisplayViewModel.dismissDialog$1();
                            break;
                    }
                }
            };
            Function0 function0 = new Function0() { // from class: com.android.systemui.display.ui.viewmodel.ConnectingDisplayViewModel$showDialog$3
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Integer.valueOf(Utils.getNavbarInsets(ConnectingDisplayViewModel.this.context).bottom);
                }
            };
            MirroringConfirmationDialogDelegate.Factory factory = connectingDisplayViewModel.bottomSheetFactory;
            MirroringConfirmationDialogDelegate mirroringConfirmationDialogDelegate = new MirroringConfirmationDialogDelegate(factory.context, z, onClickListener, onClickListener2, function0);
            SystemUIBottomSheetDialog.Factory factory2 = factory.dialogFactory;
            SystemUIBottomSheetDialog systemUIBottomSheetDialog = new SystemUIBottomSheetDialog(factory2.context, factory2.coroutineScope, factory2.configurationController, mirroringConfirmationDialogDelegate, (SystemUIBottomSheetDialog.WindowLayout) factory2.defaultWindowLayout.get(), R.style.Theme_SystemUI_Dialog);
            systemUIBottomSheetDialog.show();
            connectingDisplayViewModel.dialog = systemUIBottomSheetDialog;
        }
        return Unit.INSTANCE;
    }
}

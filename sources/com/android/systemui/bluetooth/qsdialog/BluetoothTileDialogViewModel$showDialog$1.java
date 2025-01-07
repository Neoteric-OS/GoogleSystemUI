package com.android.systemui.bluetooth.qsdialog;

import android.content.Context;
import android.widget.Switch;
import android.widget.TextView;
import com.android.systemui.animation.Expandable;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BluetoothTileDialogViewModel$showDialog$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Expandable $expandable;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ BluetoothTileDialogViewModel this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$10, reason: invalid class name */
    final class AnonymousClass10 extends SuspendLambda implements Function2 {
        final /* synthetic */ SystemUIDialog $dialog;
        final /* synthetic */ BluetoothTileDialogDelegate $dialogDelegate;
        /* synthetic */ boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass10(BluetoothTileDialogDelegate bluetoothTileDialogDelegate, SystemUIDialog systemUIDialog, Continuation continuation) {
            super(2, continuation);
            this.$dialogDelegate = bluetoothTileDialogDelegate;
            this.$dialog = systemUIDialog;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass10 anonymousClass10 = new AnonymousClass10(this.$dialogDelegate, this.$dialog, continuation);
            anonymousClass10.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass10;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            AnonymousClass10 anonymousClass10 = (AnonymousClass10) create(bool, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass10.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            boolean z = this.Z$0;
            BluetoothTileDialogDelegate bluetoothTileDialogDelegate = this.$dialogDelegate;
            SystemUIDialog systemUIDialog = this.$dialog;
            int i = z ? R.string.turn_on_bluetooth_auto_info_enabled : R.string.turn_on_bluetooth_auto_info_disabled;
            bluetoothTileDialogDelegate.getClass();
            ((Switch) systemUIDialog.requireViewById(R.id.bluetooth_auto_on_toggle)).setChecked(z);
            ((TextView) systemUIDialog.requireViewById(R.id.bluetooth_auto_on_toggle_info_text)).setText(systemUIDialog.getContext().getString(i));
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$11, reason: invalid class name */
    final class AnonymousClass11 extends SuspendLambda implements Function2 {
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ BluetoothTileDialogViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass11(BluetoothTileDialogViewModel bluetoothTileDialogViewModel, Continuation continuation) {
            super(2, continuation);
            this.this$0 = bluetoothTileDialogViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass11 anonymousClass11 = new AnonymousClass11(this.this$0, continuation);
            anonymousClass11.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass11;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            return ((AnonymousClass11) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                boolean z = this.Z$0;
                BluetoothAutoOnInteractor bluetoothAutoOnInteractor = this.this$0.bluetoothAutoOnInteractor;
                this.label = 1;
                if (bluetoothAutoOnInteractor.setEnabled(z, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$12, reason: invalid class name */
    final class AnonymousClass12 extends SuspendLambda implements Function2 {
        final /* synthetic */ SystemUIDialog $dialog;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass12(SystemUIDialog systemUIDialog, Continuation continuation) {
            super(2, continuation);
            this.$dialog = systemUIDialog;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass12 anonymousClass12 = new AnonymousClass12(this.$dialog, continuation);
            anonymousClass12.L$0 = obj;
            return anonymousClass12;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass12) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope = (ProducerScope) this.L$0;
                final SystemUIDialog systemUIDialog = this.$dialog;
                Function0 function0 = new Function0() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel.showDialog.1.12.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        SystemUIDialog.this.cancel();
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ Context $context;
        int label;
        final /* synthetic */ BluetoothTileDialogViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(BluetoothTileDialogViewModel bluetoothTileDialogViewModel, Context context, Continuation continuation) {
            super(2, continuation);
            this.this$0 = bluetoothTileDialogViewModel;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.this$0, this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DeviceItemInteractor deviceItemInteractor = this.this$0.deviceItemInteractor;
                Context context = this.$context;
                DeviceFetchTrigger deviceFetchTrigger = DeviceFetchTrigger.FIRST_LOAD;
                this.label = 1;
                if (deviceItemInteractor.updateDeviceItems$frameworks__base__packages__SystemUI__android_common__SystemUI_core(context, deviceFetchTrigger, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function3 {
        final /* synthetic */ CoroutineScope $$this$launch;
        final /* synthetic */ SystemUIDialog $dialog;
        final /* synthetic */ BluetoothTileDialogDelegate $dialogDelegate;
        final /* synthetic */ Ref$ObjectRef $updateDialogUiJob;
        /* synthetic */ Object L$0;
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ BluetoothTileDialogViewModel this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$3$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ List $deviceItem;
            final /* synthetic */ SystemUIDialog $dialog;
            final /* synthetic */ BluetoothTileDialogDelegate $dialogDelegate;
            final /* synthetic */ boolean $showSeeAll;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            boolean Z$0;
            int label;
            final /* synthetic */ BluetoothTileDialogViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(BluetoothTileDialogDelegate bluetoothTileDialogDelegate, SystemUIDialog systemUIDialog, List list, boolean z, BluetoothTileDialogViewModel bluetoothTileDialogViewModel, Continuation continuation) {
                super(2, continuation);
                this.$dialogDelegate = bluetoothTileDialogDelegate;
                this.$dialog = systemUIDialog;
                this.$deviceItem = list;
                this.$showSeeAll = z;
                this.this$0 = bluetoothTileDialogViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.$dialogDelegate, this.$dialog, this.$deviceItem, this.$showSeeAll, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:14:0x00da A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:7:0x00db A[RETURN] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r19) {
                /*
                    Method dump skipped, instructions count: 220
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1.AnonymousClass3.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Ref$ObjectRef ref$ObjectRef, CoroutineScope coroutineScope, BluetoothTileDialogDelegate bluetoothTileDialogDelegate, SystemUIDialog systemUIDialog, BluetoothTileDialogViewModel bluetoothTileDialogViewModel, Continuation continuation) {
            super(3, continuation);
            this.$updateDialogUiJob = ref$ObjectRef;
            this.$$this$launch = coroutineScope;
            this.$dialogDelegate = bluetoothTileDialogDelegate;
            this.$dialog = systemUIDialog;
            this.this$0 = bluetoothTileDialogViewModel;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            boolean booleanValue = ((Boolean) obj2).booleanValue();
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$updateDialogUiJob, this.$$this$launch, this.$dialogDelegate, this.$dialog, this.this$0, (Continuation) obj3);
            anonymousClass3.L$0 = (List) obj;
            anonymousClass3.Z$0 = booleanValue;
            Unit unit = Unit.INSTANCE;
            anonymousClass3.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List list = (List) this.L$0;
            boolean z = this.Z$0;
            Job job = (Job) this.$updateDialogUiJob.element;
            if (job != null) {
                job.cancel(null);
            }
            this.$updateDialogUiJob.element = BuildersKt.launch$default(this.$$this$launch, null, null, new AnonymousClass1(this.$dialogDelegate, this.$dialog, list, z, this.this$0, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$4, reason: invalid class name */
    final class AnonymousClass4 extends SuspendLambda implements Function2 {
        final /* synthetic */ CoroutineScope $$this$launch;
        final /* synthetic */ Context $context;
        final /* synthetic */ SystemUIDialog $dialog;
        final /* synthetic */ BluetoothTileDialogDelegate $dialogDelegate;
        final /* synthetic */ Ref$ObjectRef $updateDeviceItemJob;
        int label;
        final /* synthetic */ BluetoothTileDialogViewModel this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$4$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ Context $context;
            int label;
            final /* synthetic */ BluetoothTileDialogViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(BluetoothTileDialogViewModel bluetoothTileDialogViewModel, Context context, Continuation continuation) {
                super(2, continuation);
                this.this$0 = bluetoothTileDialogViewModel;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.this$0, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DeviceItemInteractor deviceItemInteractor = this.this$0.deviceItemInteractor;
                    Context context = this.$context;
                    DeviceFetchTrigger deviceFetchTrigger = DeviceFetchTrigger.BLUETOOTH_CALLBACK_RECEIVED;
                    this.label = 1;
                    if (deviceItemInteractor.updateDeviceItems$frameworks__base__packages__SystemUI__android_common__SystemUI_core(context, deviceFetchTrigger, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(Context context, BluetoothTileDialogDelegate bluetoothTileDialogDelegate, BluetoothTileDialogViewModel bluetoothTileDialogViewModel, SystemUIDialog systemUIDialog, Continuation continuation, Ref$ObjectRef ref$ObjectRef, CoroutineScope coroutineScope) {
            super(2, continuation);
            this.$dialogDelegate = bluetoothTileDialogDelegate;
            this.$dialog = systemUIDialog;
            this.$updateDeviceItemJob = ref$ObjectRef;
            this.$$this$launch = coroutineScope;
            this.this$0 = bluetoothTileDialogViewModel;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            BluetoothTileDialogDelegate bluetoothTileDialogDelegate = this.$dialogDelegate;
            SystemUIDialog systemUIDialog = this.$dialog;
            Ref$ObjectRef ref$ObjectRef = this.$updateDeviceItemJob;
            CoroutineScope coroutineScope = this.$$this$launch;
            return new AnonymousClass4(this.$context, bluetoothTileDialogDelegate, this.this$0, systemUIDialog, continuation, ref$ObjectRef, coroutineScope);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass4) create((Unit) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                BluetoothTileDialogDelegate bluetoothTileDialogDelegate = this.$dialogDelegate;
                SystemUIDialog systemUIDialog = this.$dialog;
                this.label = 1;
                if (bluetoothTileDialogDelegate.animateProgressBar$frameworks__base__packages__SystemUI__android_common__SystemUI_core(systemUIDialog, true, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Job job = (Job) this.$updateDeviceItemJob.element;
            if (job != null) {
                job.cancel(null);
            }
            this.$updateDeviceItemJob.element = BuildersKt.launch$default(this.$$this$launch, null, null, new AnonymousClass1(this.this$0, this.$context, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$6, reason: invalid class name */
    final class AnonymousClass6 extends SuspendLambda implements Function2 {
        final /* synthetic */ CoroutineScope $$this$launch;
        final /* synthetic */ Context $context;
        final /* synthetic */ SystemUIDialog $dialog;
        final /* synthetic */ BluetoothTileDialogDelegate $dialogDelegate;
        final /* synthetic */ Ref$ObjectRef $updateDeviceItemJob;
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ boolean Z$0;
        boolean Z$1;
        int label;
        final /* synthetic */ BluetoothTileDialogViewModel this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$6$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ Context $context;
            int label;
            final /* synthetic */ BluetoothTileDialogViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(BluetoothTileDialogViewModel bluetoothTileDialogViewModel, Context context, Continuation continuation) {
                super(2, continuation);
                this.this$0 = bluetoothTileDialogViewModel;
                this.$context = context;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.this$0, this.$context, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DeviceItemInteractor deviceItemInteractor = this.this$0.deviceItemInteractor;
                    Context context = this.$context;
                    DeviceFetchTrigger deviceFetchTrigger = DeviceFetchTrigger.BLUETOOTH_STATE_CHANGE_RECEIVED;
                    this.label = 1;
                    if (deviceItemInteractor.updateDeviceItems$frameworks__base__packages__SystemUI__android_common__SystemUI_core(context, deviceFetchTrigger, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(Context context, BluetoothTileDialogDelegate bluetoothTileDialogDelegate, BluetoothTileDialogViewModel bluetoothTileDialogViewModel, SystemUIDialog systemUIDialog, Continuation continuation, Ref$ObjectRef ref$ObjectRef, CoroutineScope coroutineScope) {
            super(2, continuation);
            this.$dialogDelegate = bluetoothTileDialogDelegate;
            this.$dialog = systemUIDialog;
            this.this$0 = bluetoothTileDialogViewModel;
            this.$updateDeviceItemJob = ref$ObjectRef;
            this.$$this$launch = coroutineScope;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            BluetoothTileDialogDelegate bluetoothTileDialogDelegate = this.$dialogDelegate;
            SystemUIDialog systemUIDialog = this.$dialog;
            AnonymousClass6 anonymousClass6 = new AnonymousClass6(this.$context, bluetoothTileDialogDelegate, this.this$0, systemUIDialog, continuation, this.$updateDeviceItemJob, this.$$this$launch);
            anonymousClass6.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass6;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            return ((AnonymousClass6) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            BluetoothTileDialogDelegate bluetoothTileDialogDelegate;
            SystemUIDialog systemUIDialog;
            BluetoothTileDialogViewModel.UiProperties.Companion companion;
            boolean z;
            boolean z2;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                boolean z3 = this.Z$0;
                bluetoothTileDialogDelegate = this.$dialogDelegate;
                systemUIDialog = this.$dialog;
                companion = BluetoothTileDialogViewModel.UiProperties.Companion;
                BluetoothTileDialogViewModel bluetoothTileDialogViewModel = this.this$0;
                this.L$0 = bluetoothTileDialogDelegate;
                this.L$1 = systemUIDialog;
                this.L$2 = companion;
                this.Z$0 = z3;
                this.Z$1 = z3;
                this.label = 1;
                Object isAutoOnToggleFeatureAvailable$frameworks__base__packages__SystemUI__android_common__SystemUI_core = bluetoothTileDialogViewModel.isAutoOnToggleFeatureAvailable$frameworks__base__packages__SystemUI__android_common__SystemUI_core(this);
                if (isAutoOnToggleFeatureAvailable$frameworks__base__packages__SystemUI__android_common__SystemUI_core == coroutineSingletons) {
                    return coroutineSingletons;
                }
                z = z3;
                obj = isAutoOnToggleFeatureAvailable$frameworks__base__packages__SystemUI__android_common__SystemUI_core;
                z2 = z;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                z = this.Z$1;
                z2 = this.Z$0;
                companion = (BluetoothTileDialogViewModel.UiProperties.Companion) this.L$2;
                systemUIDialog = (SystemUIDialog) this.L$1;
                bluetoothTileDialogDelegate = (BluetoothTileDialogDelegate) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            boolean booleanValue = ((Boolean) obj).booleanValue();
            companion.getClass();
            int i2 = z ? R.string.quick_settings_bluetooth_tile_subtitle : R.string.bt_is_off;
            int i3 = (!booleanValue || z) ? 8 : 0;
            bluetoothTileDialogDelegate.getClass();
            Switch r0 = (Switch) systemUIDialog.requireViewById(R.id.bluetooth_toggle);
            r0.setChecked(z2);
            r0.setEnabled(true);
            r0.setAlpha(1.0f);
            ((TextView) systemUIDialog.requireViewById(R.id.bluetooth_tile_dialog_subtitle)).setText(systemUIDialog.getContext().getString(i2));
            systemUIDialog.requireViewById(R.id.bluetooth_auto_on_toggle_layout).setVisibility(i3);
            Job job = (Job) this.$updateDeviceItemJob.element;
            if (job != null) {
                job.cancel(null);
            }
            this.$updateDeviceItemJob.element = BuildersKt.launch$default(this.$$this$launch, null, null, new AnonymousClass1(this.this$0, this.$context, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$7, reason: invalid class name */
    final class AnonymousClass7 extends SuspendLambda implements Function2 {
        final /* synthetic */ SystemUIDialog $dialog;
        final /* synthetic */ BluetoothTileDialogDelegate $dialogDelegate;
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ BluetoothTileDialogViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(BluetoothTileDialogDelegate bluetoothTileDialogDelegate, SystemUIDialog systemUIDialog, BluetoothTileDialogViewModel bluetoothTileDialogViewModel, Continuation continuation) {
            super(2, continuation);
            this.$dialogDelegate = bluetoothTileDialogDelegate;
            this.$dialog = systemUIDialog;
            this.this$0 = bluetoothTileDialogViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass7 anonymousClass7 = new AnonymousClass7(this.$dialogDelegate, this.$dialog, this.this$0, continuation);
            anonymousClass7.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass7;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Boolean bool = (Boolean) obj;
            bool.booleanValue();
            return ((AnonymousClass7) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            boolean z;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                z = this.Z$0;
                BluetoothTileDialogDelegate bluetoothTileDialogDelegate = this.$dialogDelegate;
                SystemUIDialog systemUIDialog = this.$dialog;
                this.Z$0 = z;
                this.label = 1;
                if (bluetoothTileDialogDelegate.animateProgressBar$frameworks__base__packages__SystemUI__android_common__SystemUI_core(systemUIDialog, true, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                z = this.Z$0;
                ResultKt.throwOnFailure(obj);
            }
            BluetoothStateInteractor bluetoothStateInteractor = this.this$0.bluetoothStateInteractor;
            this.label = 2;
            bluetoothStateInteractor.getClass();
            Object withContext = BuildersKt.withContext(bluetoothStateInteractor.backgroundDispatcher, new BluetoothStateInteractor$setBluetoothEnabled$2(bluetoothStateInteractor, z, null), this);
            if (withContext != coroutineSingletons) {
                withContext = unit;
            }
            return withContext == coroutineSingletons ? coroutineSingletons : unit;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$8, reason: invalid class name */
    final class AnonymousClass8 extends SuspendLambda implements Function2 {
        final /* synthetic */ SystemUIDialog $dialog;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ BluetoothTileDialogViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass8(BluetoothTileDialogViewModel bluetoothTileDialogViewModel, SystemUIDialog systemUIDialog, Continuation continuation) {
            super(2, continuation);
            this.this$0 = bluetoothTileDialogViewModel;
            this.$dialog = systemUIDialog;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass8 anonymousClass8 = new AnonymousClass8(this.this$0, this.$dialog, continuation);
            anonymousClass8.L$0 = obj;
            return anonymousClass8;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass8) create((DeviceItem) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DeviceItem deviceItem = (DeviceItem) this.L$0;
                DeviceItemActionInteractor deviceItemActionInteractor = this.this$0.deviceItemActionInteractor;
                SystemUIDialog systemUIDialog = this.$dialog;
                this.label = 1;
                deviceItemActionInteractor.getClass();
                Object withContext = BuildersKt.withContext(deviceItemActionInteractor.backgroundDispatcher, new DeviceItemActionInteractor$onClick$2(deviceItemActionInteractor, deviceItem, systemUIDialog, null), this);
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
            return unit;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$9, reason: invalid class name */
    final class AnonymousClass9 extends SuspendLambda implements Function2 {
        /* synthetic */ int I$0;
        int label;
        final /* synthetic */ BluetoothTileDialogViewModel this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$9$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ int $it;
            int label;
            final /* synthetic */ BluetoothTileDialogViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(BluetoothTileDialogViewModel bluetoothTileDialogViewModel, int i, Continuation continuation) {
                super(2, continuation);
                this.this$0 = bluetoothTileDialogViewModel;
                this.$it = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.this$0, this.$it, continuation);
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
                this.this$0.sharedPreferences.edit().putInt("BluetoothTileDialogContentHeight", this.$it).apply();
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass9(BluetoothTileDialogViewModel bluetoothTileDialogViewModel, Continuation continuation) {
            super(2, continuation);
            this.this$0 = bluetoothTileDialogViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass9 anonymousClass9 = new AnonymousClass9(this.this$0, continuation);
            anonymousClass9.I$0 = ((Number) obj).intValue();
            return anonymousClass9;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass9) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                int i2 = this.I$0;
                BluetoothTileDialogViewModel bluetoothTileDialogViewModel = this.this$0;
                CoroutineDispatcher coroutineDispatcher = bluetoothTileDialogViewModel.backgroundDispatcher;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(bluetoothTileDialogViewModel, i2, null);
                this.label = 1;
                if (BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothTileDialogViewModel$showDialog$1(BluetoothTileDialogViewModel bluetoothTileDialogViewModel, Expandable expandable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bluetoothTileDialogViewModel;
        this.$expandable = expandable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BluetoothTileDialogViewModel$showDialog$1 bluetoothTileDialogViewModel$showDialog$1 = new BluetoothTileDialogViewModel$showDialog$1(this.this$0, this.$expandable, continuation);
        bluetoothTileDialogViewModel$showDialog$1.L$0 = obj;
        return bluetoothTileDialogViewModel$showDialog$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothTileDialogViewModel$showDialog$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0186  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            Method dump skipped, instructions count: 449
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

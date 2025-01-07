package com.android.systemui.keyguard.data.quickaffordance;

import android.content.Context;
import android.content.Intent;
import android.service.notification.ZenModeConfig;
import com.android.settingslib.notification.modes.EnableZenModeDialog;
import com.android.settingslib.notification.modes.ZenModeDialogMetricsLogger;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.shared.quickaffordance.ActivationState;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SettingsProxyExt;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.android.wm.shell.R;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DoNotDisturbQuickAffordanceConfig implements KeyguardQuickAffordanceConfig {
    public final Context context;
    public final ZenModeController controller;
    public final Lazy dialog$delegate;
    public final String key;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 lockScreenState;
    public boolean oldIsAvailable;
    public final int pickerIconResourceId;
    public final SecureSettings secureSettings;
    public int settingsValue;
    public final UserTracker userTracker;
    public int zenMode;

    public DoNotDisturbQuickAffordanceConfig(Context context, ZenModeController zenModeController, ZenModeInteractor zenModeInteractor, SecureSettings secureSettings, UserTracker userTracker, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        this.context = context;
        this.controller = zenModeController;
        this.secureSettings = secureSettings;
        this.userTracker = userTracker;
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$dndMode$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                throw new IllegalStateException("New code path not supported when android.app.modes_ui is disabled.");
            }
        });
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$isAvailable$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                throw new IllegalStateException("New code path not supported when android.app.modes_ui is disabled.");
            }
        });
        this.dialog$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$dialog$2
            final /* synthetic */ EnableZenModeDialog $testDialog = null;

            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                EnableZenModeDialog enableZenModeDialog = this.$testDialog;
                if (enableZenModeDialog != null) {
                    return enableZenModeDialog;
                }
                Context context2 = DoNotDisturbQuickAffordanceConfig.this.context;
                return new EnableZenModeDialog(context2, new ZenModeDialogMetricsLogger(context2));
            }
        });
        this.key = "do_not_disturb";
        this.pickerIconResourceId = R.drawable.ic_do_not_disturb;
        Flow conflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new DoNotDisturbQuickAffordanceConfig$lockScreenState$2(this, null));
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new DoNotDisturbQuickAffordanceConfig$lockScreenState$3(2, null), SettingsProxyExt.observerFlow(secureSettings, ((UserTrackerImpl) userTracker).getUserId(), "zen_duration"));
        this.lockScreenState = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(conflatedCallbackFlow, new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(FlowKt.flowOn(new Flow() { // from class: com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ DoNotDisturbQuickAffordanceConfig this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, DoNotDisturbQuickAffordanceConfig doNotDisturbQuickAffordanceConfig) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = doNotDisturbQuickAffordanceConfig;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4f
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlin.Unit r5 = (kotlin.Unit) r5
                        com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig r5 = r4.this$0
                        com.android.systemui.util.settings.SecureSettings r5 = r5.secureSettings
                        java.lang.String r6 = "zen_duration"
                        r2 = 0
                        int r5 = r5.getInt(r2, r6)
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4f
                        return r1
                    L4f:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineDispatcher)), new DoNotDisturbQuickAffordanceConfig$lockScreenState$5(this, null), 0), new DoNotDisturbQuickAffordanceConfig$lockScreenState$6(3, null));
    }

    public static final KeyguardQuickAffordanceConfig.LockScreenState access$updateState(DoNotDisturbQuickAffordanceConfig doNotDisturbQuickAffordanceConfig) {
        return !doNotDisturbQuickAffordanceConfig.oldIsAvailable ? KeyguardQuickAffordanceConfig.LockScreenState.Hidden.INSTANCE : doNotDisturbQuickAffordanceConfig.zenMode == 0 ? new KeyguardQuickAffordanceConfig.LockScreenState.Visible(new Icon.Resource(R.drawable.qs_dnd_icon_off, new ContentDescription.Resource(R.string.dnd_is_off)), ActivationState.Inactive.INSTANCE) : new KeyguardQuickAffordanceConfig.LockScreenState.Visible(new Icon.Resource(R.drawable.qs_dnd_icon_on, new ContentDescription.Resource(R.string.dnd_is_on)), ActivationState.Active.INSTANCE);
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String getKey() {
        return this.key;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Flow getLockScreenState() {
        return this.lockScreenState;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final int getPickerIconResourceId() {
        return this.pickerIconResourceId;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Object getPickerScreenState(Continuation continuation) {
        return ((ZenModeControllerImpl) this.controller).isZenAvailable() ? new KeyguardQuickAffordanceConfig.PickerScreenState.Default(new Intent("android.settings.ZEN_MODE_SETTINGS")) : KeyguardQuickAffordanceConfig.PickerScreenState.UnavailableOnDevice.INSTANCE;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final KeyguardQuickAffordanceConfig.OnTriggeredResult onTriggered(Expandable$Companion$fromView$1 expandable$Companion$fromView$1) {
        boolean z = this.oldIsAvailable;
        KeyguardQuickAffordanceConfig.OnTriggeredResult.Handled handled = KeyguardQuickAffordanceConfig.OnTriggeredResult.Handled.INSTANCE;
        if (!z) {
            return handled;
        }
        int i = this.zenMode;
        ZenModeController zenModeController = this.controller;
        if (i != 0) {
            ((ZenModeControllerImpl) zenModeController).setZen(0, null, "DoNotDisturbQuickAffordanceConfig");
            return handled;
        }
        int i2 = this.settingsValue;
        if (i2 == -1) {
            return new KeyguardQuickAffordanceConfig.OnTriggeredResult.ShowDialog(((EnableZenModeDialog) this.dialog$delegate.getValue()).createDialog(), expandable$Companion$fromView$1);
        }
        if (i2 == 0) {
            ((ZenModeControllerImpl) zenModeController).setZen(1, null, "DoNotDisturbQuickAffordanceConfig");
            return handled;
        }
        ((ZenModeControllerImpl) zenModeController).setZen(1, ZenModeConfig.toTimeCondition(this.context, i2, ((UserTrackerImpl) this.userTracker).getUserId(), true).id, "DoNotDisturbQuickAffordanceConfig");
        return handled;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String pickerName() {
        return this.context.getString(R.string.quick_settings_dnd_label);
    }
}

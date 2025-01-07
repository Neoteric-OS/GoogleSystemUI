package com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel;
import com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceSettingsViewModel {
    public final ActivityStarter activityStarter;
    public final ReadonlyStateFlow buttonViewModel;
    public final Context context;
    public final ContextScope coroutineScope;
    public final PixelDeviceInteractor pixelDeviceInteractor;
    public final UiEventLogger uiEventLogger;
    public final VolumePanelViewModel volumePanelViewModel;

    public DeviceSettingsViewModel(Context context, ContextScope contextScope, ActivityStarter activityStarter, VolumePanelViewModel volumePanelViewModel, PixelDeviceInteractor pixelDeviceInteractor, UiEventLogger uiEventLogger) {
        this.context = context;
        this.activityStarter = activityStarter;
        this.volumePanelViewModel = volumePanelViewModel;
        this.pixelDeviceInteractor = pixelDeviceInteractor;
        this.uiEventLogger = uiEventLogger;
        final ReadonlyStateFlow readonlyStateFlow = pixelDeviceInteractor.activeNonPixelBluetoothMediaDevice;
        this.buttonViewModel = FlowKt.stateIn(new Flow() { // from class: com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ DeviceSettingsViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, DeviceSettingsViewModel deviceSettingsViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = deviceSettingsViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel$special$$inlined$map$1$2$1 r0 = (com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel$special$$inlined$map$1$2$1 r0 = new com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L5b
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        com.android.systemui.volume.domain.model.AudioOutputDevice$Bluetooth r6 = (com.android.systemui.volume.domain.model.AudioOutputDevice.Bluetooth) r6
                        r7 = 0
                        if (r6 == 0) goto L50
                        com.android.systemui.volume.panel.component.button.ui.viewmodel.ButtonViewModel r6 = new com.android.systemui.volume.panel.component.button.ui.viewmodel.ButtonViewModel
                        com.android.systemui.common.shared.model.Icon$Resource r2 = new com.android.systemui.common.shared.model.Icon$Resource
                        r4 = 2131232457(0x7f0806c9, float:1.8081024E38)
                        r2.<init>(r4, r7)
                        com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel r7 = r5.this$0
                        android.content.Context r7 = r7.context
                        r4 = 2131954571(0x7f130b8b, float:1.9545645E38)
                        java.lang.String r7 = r7.getString(r4)
                        r6.<init>(r2, r7, r3)
                        r7 = r6
                    L50:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r7, r0)
                        if (r5 != r1) goto L5b
                        return r1
                    L5b:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, contextScope, SharingStarted.Companion.Eagerly, null);
    }
}

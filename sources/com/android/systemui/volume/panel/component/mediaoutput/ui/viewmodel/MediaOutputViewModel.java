package com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputActionsInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel;
import com.android.systemui.volume.panel.shared.model.Result;
import com.android.systemui.volume.panel.shared.model.ResultKt;
import com.android.systemui.volume.panel.shared.model.ResultKt$filterData$$inlined$map$1;
import com.android.systemui.volume.panel.ui.VolumePanelUiEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaOutputViewModel {
    public final MediaOutputActionsInteractor actionsInteractor;
    public final ReadonlyStateFlow connectedDeviceViewModel;
    public final Context context;
    public final ContextScope coroutineScope;
    public final ReadonlyStateFlow deviceIconViewModel;
    public final ReadonlyStateFlow enabled;
    public final MediaOutputComponentInteractor mediaOutputComponentInteractor;
    public final UiEventLogger uiEventLogger;

    public MediaOutputViewModel(Context context, ContextScope contextScope, MediaOutputActionsInteractor mediaOutputActionsInteractor, MediaOutputComponentInteractor mediaOutputComponentInteractor, UiEventLogger uiEventLogger) {
        this.context = context;
        this.actionsInteractor = mediaOutputActionsInteractor;
        this.mediaOutputComponentInteractor = mediaOutputComponentInteractor;
        this.uiEventLogger = uiEventLogger;
        ReadonlyStateFlow readonlyStateFlow = mediaOutputComponentInteractor.mediaOutputModel;
        final ResultKt$filterData$$inlined$map$1 filterData = ResultKt.filterData(readonlyStateFlow);
        Flow flow = new Flow() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MediaOutputViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MediaOutputViewModel mediaOutputViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mediaOutputViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                    /*
                        r8 = this;
                        boolean r0 = r10 instanceof com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r10
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r10)
                    L18:
                        java.lang.Object r10 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L30
                        if (r2 != r3) goto L28
                        kotlin.ResultKt.throwOnFailure(r10)
                        goto Ld2
                    L28:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r9)
                        throw r8
                    L30:
                        kotlin.ResultKt.throwOnFailure(r10)
                        com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel r9 = (com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel) r9
                        boolean r10 = r9 instanceof com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel.Idle
                        r2 = 2131953346(0x7f1306c2, float:1.954316E38)
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel r4 = r8.this$0
                        if (r10 == 0) goto L45
                        android.content.Context r10 = r4.context
                        java.lang.String r10 = r10.getString(r2)
                        goto L76
                    L45:
                        boolean r10 = r9 instanceof com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel.MediaSession
                        if (r10 == 0) goto L69
                        r10 = r9
                        com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel$MediaSession r10 = (com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel.MediaSession) r10
                        boolean r5 = r10.isPlaybackActive
                        if (r5 == 0) goto L62
                        android.content.Context r2 = r4.context
                        com.android.systemui.volume.panel.component.mediaoutput.shared.model.MediaDeviceSession r10 = r10.session
                        java.lang.CharSequence r10 = r10.appLabel
                        java.lang.Object[] r10 = new java.lang.Object[]{r10}
                        r5 = 2131953337(0x7f1306b9, float:1.9543142E38)
                        java.lang.String r10 = r2.getString(r5, r10)
                        goto L76
                    L62:
                        android.content.Context r10 = r4.context
                        java.lang.String r10 = r10.getString(r2)
                        goto L76
                    L69:
                        boolean r10 = r9 instanceof com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel.Calling
                        if (r10 == 0) goto Ld5
                        android.content.Context r10 = r4.context
                        r2 = 2131953345(0x7f1306c1, float:1.9543158E38)
                        java.lang.String r10 = r10.getString(r2)
                    L76:
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
                        com.android.systemui.common.shared.model.Color$Attribute r2 = new com.android.systemui.common.shared.model.Color$Attribute
                        r5 = 17957045(0x11200b5, float:2.6816472E-38)
                        r2.<init>(r5)
                        boolean r6 = r9.isInAudioSharing()
                        if (r6 == 0) goto L91
                        android.content.Context r4 = r4.context
                        r6 = 2131951966(0x7f13015e, float:1.9540361E38)
                        java.lang.String r4 = r4.getString(r6)
                        goto Lae
                    L91:
                        com.android.systemui.volume.domain.model.AudioOutputDevice r6 = r9.getDevice()
                        boolean r7 = r6 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.Unknown
                        if (r7 != 0) goto L9a
                        goto L9b
                    L9a:
                        r6 = 0
                    L9b:
                        if (r6 == 0) goto La5
                        java.lang.String r6 = r6.getName()
                        if (r6 == 0) goto La5
                        r4 = r6
                        goto Lae
                    La5:
                        android.content.Context r4 = r4.context
                        r6 = 2131953376(0x7f1306e0, float:1.9543221E38)
                        java.lang.String r4 = r4.getString(r6)
                    Lae:
                        boolean r9 = r9.getCanOpenAudioSwitcher()
                        if (r9 == 0) goto Lbd
                        com.android.systemui.common.shared.model.Color$Attribute r9 = new com.android.systemui.common.shared.model.Color$Attribute
                        r5 = 17957043(0x11200b3, float:2.6816467E-38)
                        r9.<init>(r5)
                        goto Lc2
                    Lbd:
                        com.android.systemui.common.shared.model.Color$Attribute r9 = new com.android.systemui.common.shared.model.Color$Attribute
                        r9.<init>(r5)
                    Lc2:
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.ConnectedDeviceViewModel r5 = new com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.ConnectedDeviceViewModel
                        r5.<init>(r10, r2, r4, r9)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r8 = r8.$this_unsafeFlow
                        java.lang.Object r8 = r8.emit(r5, r0)
                        if (r8 != r1) goto Ld2
                        return r1
                    Ld2:
                        kotlin.Unit r8 = kotlin.Unit.INSTANCE
                        return r8
                    Ld5:
                        kotlin.NoWhenBranchMatchedException r8 = new kotlin.NoWhenBranchMatchedException
                        r8.<init>()
                        throw r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ResultKt$filterData$$inlined$map$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        this.connectedDeviceViewModel = FlowKt.stateIn(flow, contextScope, startedEagerly, null);
        final ResultKt$filterData$$inlined$map$1 filterData2 = ResultKt.filterData(readonlyStateFlow);
        final int i = 0;
        this.deviceIconViewModel = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                    /*
                        r8 = this;
                        boolean r0 = r10 instanceof com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r10
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2$2$1 r0 = (com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2$2$1 r0 = new com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2$2$1
                        r0.<init>(r10)
                    L18:
                        java.lang.Object r10 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L30
                        if (r2 != r3) goto L28
                        kotlin.ResultKt.throwOnFailure(r10)
                        goto Ld5
                    L28:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r9)
                        throw r8
                    L30:
                        kotlin.ResultKt.throwOnFailure(r10)
                        com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel r9 = (com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel) r9
                        com.android.systemui.volume.domain.model.AudioOutputDevice r10 = r9.getDevice()
                        boolean r2 = r10 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.Unknown
                        r4 = 0
                        if (r2 != 0) goto L3f
                        goto L40
                    L3f:
                        r10 = r4
                    L40:
                        if (r10 == 0) goto L4e
                        android.graphics.drawable.Drawable r10 = r10.getIcon()
                        if (r10 == 0) goto L4e
                        com.android.systemui.common.shared.model.Icon$Loaded r2 = new com.android.systemui.common.shared.model.Icon$Loaded
                        r2.<init>(r10, r4)
                        goto L56
                    L4e:
                        com.android.systemui.common.shared.model.Icon$Resource r2 = new com.android.systemui.common.shared.model.Icon$Resource
                        r10 = 2131232631(0x7f080777, float:1.8081377E38)
                        r2.<init>(r10, r4)
                    L56:
                        boolean r10 = r9 instanceof com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel.MediaSession
                        if (r10 == 0) goto L5d
                        r4 = r9
                        com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel$MediaSession r4 = (com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel.MediaSession) r4
                    L5d:
                        r10 = 0
                        if (r4 == 0) goto L65
                        boolean r4 = r4.isPlaybackActive
                        if (r4 != r3) goto L65
                        r10 = r3
                    L65:
                        boolean r4 = r9 instanceof com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel.Calling
                        r5 = 17957065(0x11200c9, float:2.6816528E-38)
                        r6 = 17957061(0x11200c5, float:2.6816517E-38)
                        r7 = 17957050(0x11200ba, float:2.6816486E-38)
                        if (r10 != 0) goto La0
                        if (r4 == 0) goto L75
                        goto La0
                    L75:
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel$IsNotPlaying r10 = new com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel$IsNotPlaying
                        boolean r4 = r9.getCanOpenAudioSwitcher()
                        if (r4 == 0) goto L86
                        com.android.systemui.common.shared.model.Color$Attribute r4 = new com.android.systemui.common.shared.model.Color$Attribute
                        r7 = 17957045(0x11200b5, float:2.6816472E-38)
                        r4.<init>(r7)
                        goto L8b
                    L86:
                        com.android.systemui.common.shared.model.Color$Attribute r4 = new com.android.systemui.common.shared.model.Color$Attribute
                        r4.<init>(r7)
                    L8b:
                        boolean r9 = r9.getCanOpenAudioSwitcher()
                        if (r9 == 0) goto L97
                        com.android.systemui.common.shared.model.Color$Attribute r9 = new com.android.systemui.common.shared.model.Color$Attribute
                        r9.<init>(r6)
                        goto L9c
                    L97:
                        com.android.systemui.common.shared.model.Color$Attribute r9 = new com.android.systemui.common.shared.model.Color$Attribute
                        r9.<init>(r5)
                    L9c:
                        r10.<init>(r2, r4, r9)
                        goto Lca
                    La0:
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel$IsPlaying r10 = new com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel$IsPlaying
                        boolean r4 = r9.getCanOpenAudioSwitcher()
                        if (r4 == 0) goto Lae
                        com.android.systemui.common.shared.model.Color$Attribute r4 = new com.android.systemui.common.shared.model.Color$Attribute
                        r4.<init>(r6)
                        goto Lb3
                    Lae:
                        com.android.systemui.common.shared.model.Color$Attribute r4 = new com.android.systemui.common.shared.model.Color$Attribute
                        r4.<init>(r5)
                    Lb3:
                        boolean r9 = r9.getCanOpenAudioSwitcher()
                        if (r9 == 0) goto Lc2
                        com.android.systemui.common.shared.model.Color$Attribute r9 = new com.android.systemui.common.shared.model.Color$Attribute
                        r5 = 17957057(0x11200c1, float:2.6816506E-38)
                        r9.<init>(r5)
                        goto Lc7
                    Lc2:
                        com.android.systemui.common.shared.model.Color$Attribute r9 = new com.android.systemui.common.shared.model.Color$Attribute
                        r9.<init>(r7)
                    Lc7:
                        r10.<init>(r2, r4, r9)
                    Lca:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r8 = r8.$this_unsafeFlow
                        java.lang.Object r8 = r8.emit(r10, r0)
                        if (r8 != r1) goto Ld5
                        return r1
                    Ld5:
                        kotlin.Unit r8 = kotlin.Unit.INSTANCE
                        return r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = filterData2.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = filterData2.collect(new MediaOutputViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, contextScope, startedEagerly, null);
        final ResultKt$filterData$$inlined$map$1 filterData3 = ResultKt.filterData(readonlyStateFlow);
        final int i2 = 1;
        this.enabled = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r10 instanceof com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r10
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2$2$1 r0 = (com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2$2$1 r0 = new com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2$2$1
                        r0.<init>(r10)
                    L18:
                        java.lang.Object r10 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L30
                        if (r2 != r3) goto L28
                        kotlin.ResultKt.throwOnFailure(r10)
                        goto Ld5
                    L28:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r9)
                        throw r8
                    L30:
                        kotlin.ResultKt.throwOnFailure(r10)
                        com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel r9 = (com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel) r9
                        com.android.systemui.volume.domain.model.AudioOutputDevice r10 = r9.getDevice()
                        boolean r2 = r10 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.Unknown
                        r4 = 0
                        if (r2 != 0) goto L3f
                        goto L40
                    L3f:
                        r10 = r4
                    L40:
                        if (r10 == 0) goto L4e
                        android.graphics.drawable.Drawable r10 = r10.getIcon()
                        if (r10 == 0) goto L4e
                        com.android.systemui.common.shared.model.Icon$Loaded r2 = new com.android.systemui.common.shared.model.Icon$Loaded
                        r2.<init>(r10, r4)
                        goto L56
                    L4e:
                        com.android.systemui.common.shared.model.Icon$Resource r2 = new com.android.systemui.common.shared.model.Icon$Resource
                        r10 = 2131232631(0x7f080777, float:1.8081377E38)
                        r2.<init>(r10, r4)
                    L56:
                        boolean r10 = r9 instanceof com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel.MediaSession
                        if (r10 == 0) goto L5d
                        r4 = r9
                        com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel$MediaSession r4 = (com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel.MediaSession) r4
                    L5d:
                        r10 = 0
                        if (r4 == 0) goto L65
                        boolean r4 = r4.isPlaybackActive
                        if (r4 != r3) goto L65
                        r10 = r3
                    L65:
                        boolean r4 = r9 instanceof com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel.Calling
                        r5 = 17957065(0x11200c9, float:2.6816528E-38)
                        r6 = 17957061(0x11200c5, float:2.6816517E-38)
                        r7 = 17957050(0x11200ba, float:2.6816486E-38)
                        if (r10 != 0) goto La0
                        if (r4 == 0) goto L75
                        goto La0
                    L75:
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel$IsNotPlaying r10 = new com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel$IsNotPlaying
                        boolean r4 = r9.getCanOpenAudioSwitcher()
                        if (r4 == 0) goto L86
                        com.android.systemui.common.shared.model.Color$Attribute r4 = new com.android.systemui.common.shared.model.Color$Attribute
                        r7 = 17957045(0x11200b5, float:2.6816472E-38)
                        r4.<init>(r7)
                        goto L8b
                    L86:
                        com.android.systemui.common.shared.model.Color$Attribute r4 = new com.android.systemui.common.shared.model.Color$Attribute
                        r4.<init>(r7)
                    L8b:
                        boolean r9 = r9.getCanOpenAudioSwitcher()
                        if (r9 == 0) goto L97
                        com.android.systemui.common.shared.model.Color$Attribute r9 = new com.android.systemui.common.shared.model.Color$Attribute
                        r9.<init>(r6)
                        goto L9c
                    L97:
                        com.android.systemui.common.shared.model.Color$Attribute r9 = new com.android.systemui.common.shared.model.Color$Attribute
                        r9.<init>(r5)
                    L9c:
                        r10.<init>(r2, r4, r9)
                        goto Lca
                    La0:
                        com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel$IsPlaying r10 = new com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.DeviceIconViewModel$IsPlaying
                        boolean r4 = r9.getCanOpenAudioSwitcher()
                        if (r4 == 0) goto Lae
                        com.android.systemui.common.shared.model.Color$Attribute r4 = new com.android.systemui.common.shared.model.Color$Attribute
                        r4.<init>(r6)
                        goto Lb3
                    Lae:
                        com.android.systemui.common.shared.model.Color$Attribute r4 = new com.android.systemui.common.shared.model.Color$Attribute
                        r4.<init>(r5)
                    Lb3:
                        boolean r9 = r9.getCanOpenAudioSwitcher()
                        if (r9 == 0) goto Lc2
                        com.android.systemui.common.shared.model.Color$Attribute r9 = new com.android.systemui.common.shared.model.Color$Attribute
                        r5 = 17957057(0x11200c1, float:2.6816506E-38)
                        r9.<init>(r5)
                        goto Lc7
                    Lc2:
                        com.android.systemui.common.shared.model.Color$Attribute r9 = new com.android.systemui.common.shared.model.Color$Attribute
                        r9.<init>(r7)
                    Lc7:
                        r10.<init>(r2, r4, r9)
                    Lca:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r8 = r8.$this_unsafeFlow
                        java.lang.Object r8 = r8.emit(r10, r0)
                        if (r8 != r1) goto Ld5
                        return r1
                    Ld5:
                        kotlin.Unit r8 = kotlin.Unit.INSTANCE
                        return r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = filterData3.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = filterData3.collect(new MediaOutputViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, contextScope, startedEagerly, Boolean.TRUE);
    }

    public final void onBarClick(Expandable expandable) {
        this.uiEventLogger.log(VolumePanelUiEvent.VOLUME_PANEL_MEDIA_OUTPUT_CLICKED);
        Result result = (Result) ((StateFlowImpl) this.mediaOutputComponentInteractor.mediaOutputModel.$$delegate_0).getValue();
        Result.Data data = result instanceof Result.Data ? (Result.Data) result : null;
        MediaOutputComponentModel mediaOutputComponentModel = data != null ? (MediaOutputComponentModel) data.data : null;
        MediaOutputActionsInteractor mediaOutputActionsInteractor = this.actionsInteractor;
        mediaOutputActionsInteractor.getClass();
        boolean z = mediaOutputComponentModel instanceof MediaOutputComponentModel.MediaSession;
        MediaOutputDialogManager mediaOutputDialogManager = mediaOutputActionsInteractor.mediaOutputDialogManager;
        if (z) {
            MediaOutputDialogManager.createAndShowWithController$default(mediaOutputDialogManager, ((MediaOutputComponentModel.MediaSession) mediaOutputComponentModel).session.packageName, false, expandable != null ? BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "media_output", expandable) : null, null, 24);
        } else {
            mediaOutputDialogManager.createAndShow(null, false, expandable != null ? BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "media_output", expandable) : null, false, null, null);
        }
    }
}

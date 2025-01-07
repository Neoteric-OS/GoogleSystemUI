package com.android.systemui.volume.panel.ui.viewmodel;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.ConfigurationControllerExtKt;
import com.android.systemui.volume.panel.domain.VolumePanelStartable;
import com.android.systemui.volume.panel.domain.interactor.ComponentsInteractorImpl;
import com.android.systemui.volume.panel.domain.interactor.VolumePanelGlobalStateInteractor;
import com.android.systemui.volume.panel.shared.VolumePanelLogger;
import com.android.systemui.volume.panel.ui.layout.ComponentsLayout;
import com.android.systemui.volume.panel.ui.layout.ComponentsLayoutKt;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl;
import dagger.internal.Preconditions;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelViewModel implements Dumpable {
    public final ReadonlyStateFlow componentsLayout;
    public final DumpManager dumpManager;
    public final VolumePanelLogger logger;
    public final DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl volumePanelComponent;
    public final VolumePanelGlobalStateInteractor volumePanelGlobalStateInteractor;
    public final ReadonlyStateFlow volumePanelState;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return VolumePanelViewModel.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass3 anonymousClass3 = (AnonymousClass3) create((Unit) obj, (Continuation) obj2);
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
            VolumePanelViewModel.this.volumePanelGlobalStateInteractor.setVisible(false);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final BroadcastDispatcher broadcastDispatcher;
        public final ConfigurationController configurationController;
        public final Context context;
        public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerComponentFactory;
        public final DumpManager dumpManager;
        public final VolumePanelLogger logger;
        public final VolumePanelGlobalStateInteractor volumePanelGlobalStateInteractor;

        public Factory(Context context, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, ConfigurationController configurationController, BroadcastDispatcher broadcastDispatcher, DumpManager dumpManager, VolumePanelLogger volumePanelLogger, VolumePanelGlobalStateInteractor volumePanelGlobalStateInteractor) {
            this.context = context;
            this.daggerComponentFactory = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
            this.configurationController = configurationController;
            this.broadcastDispatcher = broadcastDispatcher;
            this.dumpManager = dumpManager;
            this.logger = volumePanelLogger;
            this.volumePanelGlobalStateInteractor = volumePanelGlobalStateInteractor;
        }
    }

    public VolumePanelViewModel(final Resources resources, ContextScope contextScope, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, ConfigurationController configurationController, BroadcastDispatcher broadcastDispatcher, DumpManager dumpManager, VolumePanelLogger volumePanelLogger, VolumePanelGlobalStateInteractor volumePanelGlobalStateInteractor) {
        int i = 0;
        this.dumpManager = dumpManager;
        this.logger = volumePanelLogger;
        this.volumePanelGlobalStateInteractor = volumePanelGlobalStateInteractor;
        DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl = new DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl, this, contextScope);
        this.volumePanelComponent = daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl;
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new VolumePanelViewModel$volumePanelState$1(resources, null), ConfigurationControllerExtKt.getOnConfigChanged(configurationController));
        Flow flow = new Flow() { // from class: com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ Resources $resources$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ VolumePanelViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, Resources resources, VolumePanelViewModel volumePanelViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$resources$inlined = resources;
                    this.this$0 = volumePanelViewModel;
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
                        boolean r0 = r7 instanceof com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L56
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        android.content.res.Configuration r6 = (android.content.res.Configuration) r6
                        com.android.systemui.volume.panel.ui.viewmodel.VolumePanelState r7 = new com.android.systemui.volume.panel.ui.viewmodel.VolumePanelState
                        int r6 = r6.orientation
                        android.content.res.Resources r2 = r5.$resources$inlined
                        r4 = 2131034250(0x7f05008a, float:1.7679012E38)
                        boolean r2 = r2.getBoolean(r4)
                        r7.<init>(r6, r2)
                        com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel r6 = r5.this$0
                        com.android.systemui.volume.panel.shared.VolumePanelLogger r6 = r6.logger
                        r6.onVolumePanelStateChanged(r7)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r7, r0)
                        if (r5 != r1) goto L56
                        return r1
                    L56:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, resources, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(flow, contextScope, startedEagerly, new VolumePanelState(resources.getConfiguration().orientation, resources.getBoolean(R.bool.volume_panel_is_large_screen)));
        this.volumePanelState = stateIn;
        this.componentsLayout = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(((ComponentsInteractorImpl) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.componentsInteractorImplProvider.get()).components, stateIn, new VolumePanelViewModel$componentsLayout$1(this, null)), contextScope, startedEagerly, null);
        BuildersKt.launch(contextScope, EmptyCoroutineContext.INSTANCE, CoroutineStart.DEFAULT, new VolumePanelViewModel$special$$inlined$launchAndDispose$default$1(this, null));
        ArrayList arrayList = new ArrayList(2);
        VolumePanelStartable volumePanelStartable = (VolumePanelStartable) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.audioModeLoggerStartableProvider.get();
        Preconditions.checkNotNull(volumePanelStartable, "Set contributions cannot be null");
        arrayList.add(volumePanelStartable);
        VolumePanelStartable volumePanelStartable2 = (VolumePanelStartable) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaDeviceLoggerStartableProvider.get();
        Preconditions.checkNotNull(volumePanelStartable2, "Set contributions cannot be null");
        arrayList.add(volumePanelStartable2);
        Iterator it = (arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList))).iterator();
        while (it.hasNext()) {
            ((VolumePanelStartable) it.next()).start();
        }
        FlowKt.launchIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, new IntentFilter("com.android.systemui.action.DISMISS_VOLUME_PANEL_DIALOG"), null, 14), new AnonymousClass3(null), i), this.volumePanelComponent.scope);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("volumePanelState=" + ((StateFlowImpl) this.volumePanelState.$$delegate_0).getValue());
        ComponentsLayout componentsLayout = (ComponentsLayout) ((StateFlowImpl) this.componentsLayout.$$delegate_0).getValue();
        FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "componentsLayout=", componentsLayout != null ? ComponentsLayoutKt.toLogString(componentsLayout) : null);
    }
}

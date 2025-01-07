package com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor;
import com.google.android.systemui.volume.panel.ui.VolumePanelGoogleUiEvent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ClearCallingViewModel {
    public final ReadonlyStateFlow buttonViewModel;
    public final ClearCallingInteractor clearCallingInteractor;
    public final Context context;
    public final ContextScope coroutineScope;
    public final UiEventLogger uiEventLogger;

    public ClearCallingViewModel(Context context, ClearCallingInteractor clearCallingInteractor, ContextScope contextScope, UiEventLogger uiEventLogger) {
        this.context = context;
        this.clearCallingInteractor = clearCallingInteractor;
        this.coroutineScope = contextScope;
        this.uiEventLogger = uiEventLogger;
        final ReadonlyStateFlow readonlyStateFlow = new ReadonlyStateFlow(clearCallingInteractor.clearCallingRepository.mutableClearCallingStateUpdate);
        this.buttonViewModel = FlowKt.stateIn(new Flow() { // from class: com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ClearCallingViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, ClearCallingViewModel clearCallingViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = clearCallingViewModel;
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
                        boolean r0 = r7 instanceof com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1$2$1 r0 = (com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1$2$1 r0 = new com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L61
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.lang.Boolean r6 = (java.lang.Boolean) r6
                        java.lang.Boolean r7 = java.lang.Boolean.TRUE
                        boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
                        com.android.systemui.common.shared.model.Icon$Resource r7 = new com.android.systemui.common.shared.model.Icon$Resource
                        r2 = 2131232347(0x7f08065b, float:1.80808E38)
                        r4 = 0
                        r7.<init>(r2, r4)
                        com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel r2 = r5.this$0
                        android.content.Context r2 = r2.context
                        r4 = 2131954569(0x7f130b89, float:1.954564E38)
                        java.lang.String r2 = r2.getString(r4)
                        com.android.systemui.volume.panel.component.button.ui.viewmodel.ButtonViewModel r4 = new com.android.systemui.volume.panel.component.button.ui.viewmodel.ButtonViewModel
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                        r4.<init>(r7, r2, r6)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r4, r0)
                        if (r5 != r1) goto L61
                        return r1
                    L61:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, contextScope, SharingStarted.Companion.Eagerly, null);
    }

    public final void setIsClearCallingEnabled(boolean z) {
        this.uiEventLogger.logWithPosition(VolumePanelGoogleUiEvent.VOLUME_PANEL_CLEAR_CALLING_TOGGLE_CLICKED, 0, (String) null, z ? 1 : 0);
        BuildersKt.launch$default(this.coroutineScope, null, null, new ClearCallingViewModel$setIsClearCallingEnabled$1(this, z, null), 3);
    }
}

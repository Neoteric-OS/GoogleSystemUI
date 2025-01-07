package com.android.systemui.volume.panel.component.anc.ui.viewmodel;

import android.app.PendingIntent;
import android.content.Intent;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import com.android.systemui.volume.panel.component.anc.domain.AncAvailabilityCriteria;
import com.android.systemui.volume.panel.component.anc.domain.interactor.AncSliceInteractor;
import java.util.Arrays;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AncViewModel {
    public final AncAvailabilityCriteria availabilityCriteria;
    public final ReadonlyStateFlow buttonSlice;
    public final ContextScope coroutineScope;
    public final AncSliceInteractor interactor;
    public final ReadonlyStateFlow popupSlice;

    public AncViewModel(ContextScope contextScope, AncSliceInteractor ancSliceInteractor, AncAvailabilityCriteria ancAvailabilityCriteria) {
        this.interactor = ancSliceInteractor;
        this.availabilityCriteria = ancAvailabilityCriteria;
        ReadonlyStateFlow readonlyStateFlow = ancSliceInteractor.ancSlices;
        final AncViewModel$special$$inlined$filterIsInstance$1 ancViewModel$special$$inlined$filterIsInstance$1 = new AncViewModel$special$$inlined$filterIsInstance$1(readonlyStateFlow, 0);
        final int i = 0;
        Flow flow = new Flow() { // from class: com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L41
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.volume.panel.component.anc.domain.model.AncSlices$Ready r5 = (com.android.systemui.volume.panel.component.anc.domain.model.AncSlices.Ready) r5
                        androidx.slice.Slice r5 = r5.popupSlice
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L41
                        return r1
                    L41:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((AncViewModel$special$$inlined$filterIsInstance$1) ancViewModel$special$$inlined$filterIsInstance$1).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((AncViewModel$special$$inlined$filterIsInstance$1) ancViewModel$special$$inlined$filterIsInstance$1).collect(new AncViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        this.popupSlice = FlowKt.stateIn(flow, contextScope, startedEagerly, null);
        final int i2 = 1;
        final AncViewModel$special$$inlined$filterIsInstance$1 ancViewModel$special$$inlined$filterIsInstance$12 = new AncViewModel$special$$inlined$filterIsInstance$1(readonlyStateFlow, 1);
        this.buttonSlice = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L41
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.volume.panel.component.anc.domain.model.AncSlices$Ready r5 = (com.android.systemui.volume.panel.component.anc.domain.model.AncSlices.Ready) r5
                        androidx.slice.Slice r5 = r5.popupSlice
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L41
                        return r1
                    L41:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((AncViewModel$special$$inlined$filterIsInstance$1) ancViewModel$special$$inlined$filterIsInstance$12).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((AncViewModel$special$$inlined$filterIsInstance$1) ancViewModel$special$$inlined$filterIsInstance$12).collect(new AncViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, contextScope, startedEagerly, null);
    }

    public static boolean isClickable(Slice slice) {
        Slice slice2;
        List asList;
        if (slice == null) {
            return false;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.addAll(Arrays.asList(slice.mItems));
        while (!arrayDeque.isEmpty()) {
            SliceItem sliceItem = (SliceItem) arrayDeque.removeFirst();
            String str = sliceItem.mFormat;
            int hashCode = str.hashCode();
            if (hashCode != -1422950858) {
                if (hashCode == 109526418 && str.equals("slice") && (slice2 = sliceItem.getSlice()) != null && (asList = Arrays.asList(slice2.mItems)) != null) {
                    arrayDeque.addAll(asList);
                }
            } else if (str.equals("action")) {
                PendingIntent action = sliceItem.getAction();
                Intent intent = action != null ? action.getIntent() : null;
                if (intent != null && intent.hasExtra("EXTRA_ANC_ENABLED")) {
                    return intent.getBooleanExtra("EXTRA_ANC_ENABLED", true);
                }
            } else {
                continue;
            }
        }
        return true;
    }
}

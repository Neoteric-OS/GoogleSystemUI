package com.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SettingsProxyExt;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AutoAddableSetting implements AutoAddable {
    public final AutoAddTracking.IfNotAdded autoAddTracking;
    public final CoroutineDispatcher bgDispatcher;
    public final String description;
    public final SecureSettings secureSettings;
    public final String setting;
    public final TileSpec spec;

    public AutoAddableSetting(SecureSettings secureSettings, CoroutineDispatcher coroutineDispatcher, String str, TileSpec tileSpec) {
        this.secureSettings = secureSettings;
        this.bgDispatcher = coroutineDispatcher;
        this.setting = str;
        this.spec = tileSpec;
        AutoAddTracking.IfNotAdded ifNotAdded = new AutoAddTracking.IfNotAdded(tileSpec);
        this.autoAddTracking = ifNotAdded;
        this.description = "AutoAddableSetting: " + str + ":" + tileSpec + " (" + ifNotAdded + ")";
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final Flow autoAddSignal(final int i) {
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AutoAddableSetting$autoAddSignal$1(2, null), SettingsProxyExt.observerFlow(this.secureSettings, i, this.setting));
        final AutoAddableSetting$autoAddSignal$$inlined$filter$1 autoAddableSetting$autoAddSignal$$inlined$filter$1 = new AutoAddableSetting$autoAddSignal$$inlined$filter$1(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ int $userId$inlined;
                public final /* synthetic */ AutoAddableSetting this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, AutoAddableSetting autoAddableSetting, int i) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = autoAddableSetting;
                    this.$userId$inlined = i;
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
                        boolean r0 = r7 instanceof com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L53
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        kotlin.Unit r6 = (kotlin.Unit) r6
                        com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting r6 = r5.this$0
                        com.android.systemui.util.settings.SecureSettings r7 = r6.secureSettings
                        java.lang.String r6 = r6.setting
                        r2 = 0
                        int r4 = r5.$userId$inlined
                        int r6 = r7.getIntForUser(r6, r2, r4)
                        if (r6 == 0) goto L44
                        r2 = r3
                    L44:
                        java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r6, r0)
                        if (r5 != r1) goto L53
                        return r1
                    L53:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, this, i), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }));
        return FlowKt.flowOn(new Flow() { // from class: com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ AutoAddableSetting this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, AutoAddableSetting autoAddableSetting) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = autoAddableSetting;
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
                        boolean r0 = r6 instanceof com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$2$2$1 r0 = (com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$2$2$1 r0 = new com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4c
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        r5.getClass()
                        com.android.systemui.qs.pipeline.domain.model.AutoAddSignal$Add r5 = new com.android.systemui.qs.pipeline.domain.model.AutoAddSignal$Add
                        com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting r6 = r4.this$0
                        com.android.systemui.qs.pipeline.shared.TileSpec r6 = r6.spec
                        r2 = -1
                        r5.<init>(r2, r6)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4c
                        return r1
                    L4c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.autoaddable.AutoAddableSetting$autoAddSignal$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = AutoAddableSetting$autoAddSignal$$inlined$filter$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, this.bgDispatcher);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof AutoAddableSetting) {
            AutoAddableSetting autoAddableSetting = (AutoAddableSetting) obj;
            if (Intrinsics.areEqual(this.spec, autoAddableSetting.spec) && Intrinsics.areEqual(this.setting, autoAddableSetting.setting)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final AutoAddTracking getAutoAddTracking() {
        return this.autoAddTracking;
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final String getDescription() {
        return this.description;
    }

    public final int hashCode() {
        return Objects.hash(this.spec, this.setting);
    }

    public final String toString() {
        return this.description;
    }
}

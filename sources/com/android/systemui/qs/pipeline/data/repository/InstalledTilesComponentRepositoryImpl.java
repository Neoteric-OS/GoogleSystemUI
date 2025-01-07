package com.android.systemui.qs.pipeline.data.repository;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import com.android.systemui.common.data.repository.PackageChangeRepository;
import com.android.systemui.common.data.repository.PackageChangeRepositoryImpl;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InstalledTilesComponentRepositoryImpl implements InstalledTilesComponentRepository {
    public final Context applicationContext;
    public final CoroutineScope backgroundScope;
    public final PackageChangeRepository packageChangeRepository;
    public final Map userMap = new LinkedHashMap();
    public static final Intent INTENT = new Intent("android.service.quicksettings.action.QS_TILE");
    public static final PackageManager.ResolveInfoFlags FLAGS = PackageManager.ResolveInfoFlags.of(786436);

    public InstalledTilesComponentRepositoryImpl(Context context, CoroutineScope coroutineScope, PackageChangeRepository packageChangeRepository) {
        this.applicationContext = context;
        this.backgroundScope = coroutineScope;
        this.packageChangeRepository = packageChangeRepository;
    }

    public final StateFlow getForUserLocked(final int i) {
        Map map = this.userMap;
        Integer valueOf = Integer.valueOf(i);
        Object obj = map.get(valueOf);
        if (obj == null) {
            final PackageManager packageManager = this.applicationContext.getUserId() == i ? this.applicationContext.getPackageManager() : this.applicationContext.createContextAsUser(UserHandle.of(i), 0).getPackageManager();
            final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new InstalledTilesComponentRepositoryImpl$getForUserLocked$1$1(2, null), ((PackageChangeRepositoryImpl) this.packageChangeRepository).packageChanged(UserHandle.of(i)));
            obj = FlowKt.stateIn(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getForUserLocked$lambda$5$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getForUserLocked$lambda$5$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ PackageManager $packageManager$inlined;
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;
                    public final /* synthetic */ int $userId$inlined;
                    public final /* synthetic */ InstalledTilesComponentRepositoryImpl this$0;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getForUserLocked$lambda$5$$inlined$map$1$2$1, reason: invalid class name */
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

                    public AnonymousClass2(FlowCollector flowCollector, InstalledTilesComponentRepositoryImpl installedTilesComponentRepositoryImpl, int i, PackageManager packageManager) {
                        this.$this_unsafeFlow = flowCollector;
                        this.this$0 = installedTilesComponentRepositoryImpl;
                        this.$userId$inlined = i;
                        this.$packageManager$inlined = packageManager;
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
                            boolean r0 = r10 instanceof com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getForUserLocked$lambda$5$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r10
                            com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getForUserLocked$lambda$5$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getForUserLocked$lambda$5$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getForUserLocked$lambda$5$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getForUserLocked$lambda$5$$inlined$map$1$2$1
                            r0.<init>(r10)
                        L18:
                            java.lang.Object r10 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L30
                            if (r2 != r3) goto L28
                            kotlin.ResultKt.throwOnFailure(r10)
                            goto Lcc
                        L28:
                            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                            r8.<init>(r9)
                            throw r8
                        L30:
                            kotlin.ResultKt.throwOnFailure(r10)
                            com.android.systemui.common.shared.model.PackageChangeModel r9 = (com.android.systemui.common.shared.model.PackageChangeModel) r9
                            android.content.pm.PackageManager r9 = r8.$packageManager$inlined
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
                            android.content.pm.PackageManager r9 = r8.$packageManager$inlined
                            android.content.Intent r10 = com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl.INTENT
                            com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl r10 = r8.this$0
                            r10.getClass()
                            android.content.Intent r10 = com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl.INTENT
                            android.content.pm.PackageManager$ResolveInfoFlags r2 = com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl.FLAGS
                            int r4 = r8.$userId$inlined
                            java.util.List r10 = r9.queryIntentServicesAsUser(r10, r2, r4)
                            java.util.ArrayList r2 = new java.util.ArrayList
                            r2.<init>()
                            java.util.Iterator r10 = r10.iterator()
                        L56:
                            boolean r4 = r10.hasNext()
                            if (r4 == 0) goto L6a
                            java.lang.Object r4 = r10.next()
                            android.content.pm.ResolveInfo r4 = (android.content.pm.ResolveInfo) r4
                            android.content.pm.ServiceInfo r4 = r4.serviceInfo
                            if (r4 == 0) goto L56
                            r2.add(r4)
                            goto L56
                        L6a:
                            java.util.ArrayList r10 = new java.util.ArrayList
                            r10.<init>()
                            java.util.Iterator r2 = r2.iterator()
                        L73:
                            boolean r4 = r2.hasNext()
                            if (r4 == 0) goto L8e
                            java.lang.Object r4 = r2.next()
                            r5 = r4
                            android.content.pm.ServiceInfo r5 = (android.content.pm.ServiceInfo) r5
                            java.lang.String r5 = r5.permission
                            java.lang.String r6 = "android.permission.BIND_QUICK_SETTINGS_TILE"
                            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
                            if (r5 == 0) goto L73
                            r10.add(r4)
                            goto L73
                        L8e:
                            java.util.ArrayList r2 = new java.util.ArrayList
                            r2.<init>()
                            java.util.Iterator r10 = r10.iterator()
                        L97:
                            boolean r4 = r10.hasNext()
                            if (r4 == 0) goto Lc1
                            java.lang.Object r4 = r10.next()
                            r5 = r4
                            android.content.pm.ServiceInfo r5 = (android.content.pm.ServiceInfo) r5
                            r6 = 0
                            com.android.systemui.util.Assert.isNotMainThread()     // Catch: java.lang.IllegalArgumentException -> Lbb
                            android.content.ComponentName r7 = r5.getComponentName()     // Catch: java.lang.IllegalArgumentException -> Lbb
                            int r7 = r9.getComponentEnabledSetting(r7)     // Catch: java.lang.IllegalArgumentException -> Lbb
                            if (r7 == 0) goto Lb7
                            if (r7 == r3) goto Lb5
                            goto Lbb
                        Lb5:
                            r6 = r3
                            goto Lbb
                        Lb7:
                            boolean r6 = r5.isEnabled()     // Catch: java.lang.IllegalArgumentException -> Lbb
                        Lbb:
                            if (r6 == 0) goto L97
                            r2.add(r4)
                            goto L97
                        Lc1:
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r8 = r8.$this_unsafeFlow
                            java.lang.Object r8 = r8.emit(r2, r0)
                            if (r8 != r1) goto Lcc
                            return r1
                        Lcc:
                            kotlin.Unit r8 = kotlin.Unit.INSTANCE
                            return r8
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepositoryImpl$getForUserLocked$lambda$5$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, this, i, packageManager), continuation);
                    return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }), this.backgroundScope, SharingStarted.Companion.WhileSubscribed$default(3), EmptyList.INSTANCE);
            map.put(valueOf, obj);
        }
        return (StateFlow) obj;
    }
}

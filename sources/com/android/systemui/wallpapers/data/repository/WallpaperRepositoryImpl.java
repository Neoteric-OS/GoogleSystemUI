package com.android.systemui.wallpapers.data.repository;

import android.R;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WallpaperRepositoryImpl {
    public final CoroutineDispatcher bgDispatcher;
    public final WallpaperRepositoryImpl$special$$inlined$map$1 selectedUser;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 wallpaperChanged;
    public final ReadonlyStateFlow wallpaperInfo;
    public final WallpaperManager wallpaperManager;
    public final ReadonlyStateFlow wallpaperSupportsAmbientMode;

    public WallpaperRepositoryImpl(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, BroadcastDispatcher broadcastDispatcher, UserRepositoryImpl userRepositoryImpl, WallpaperManager wallpaperManager, Context context) {
        final ReadonlyStateFlow readonlyStateFlow;
        this.bgDispatcher = coroutineDispatcher;
        this.wallpaperManager = wallpaperManager;
        boolean z = context.getResources().getBoolean(R.bool.config_dozeWakeLockScreenSensorAvailable);
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new WallpaperRepositoryImpl$wallpaperChanged$1(2, null), BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, new IntentFilter("android.intent.action.WALLPAPER_CHANGED"), UserHandle.ALL, 12));
        final ReadonlyStateFlow readonlyStateFlow2 = userRepositoryImpl.selectedUser;
        final int i = 1;
        Flow flow = new Flow() { // from class: com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4d
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.app.WallpaperInfo r5 = (android.app.WallpaperInfo) r5
                        r6 = 0
                        if (r5 == 0) goto L3e
                        boolean r5 = r5.supportsAmbientMode()
                        if (r5 != r3) goto L3e
                        r6 = r3
                    L3e:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4d
                        return r1
                    L4d:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        ((ReadonlyStateFlow) readonlyStateFlow2).collect(new AnonymousClass2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = readonlyStateFlow2.collect(new WallpaperRepositoryImpl$special$$inlined$filter$1$2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        };
        boolean isWallpaperSupported = wallpaperManager.isWallpaperSupported();
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        if (isWallpaperSupported && z) {
            readonlyStateFlow = FlowKt.stateIn(FlowKt.buffer$default(FlowKt.mapLatest(new WallpaperRepositoryImpl$wallpaperInfo$3(this, null), new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, flow, WallpaperRepositoryImpl$wallpaperInfo$2.INSTANCE)), -1), coroutineScope, startedEagerly, null);
        } else {
            readonlyStateFlow = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(null));
        }
        this.wallpaperInfo = readonlyStateFlow;
        final int i2 = 0;
        Flow flow2 = new Flow() { // from class: com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4d
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.app.WallpaperInfo r5 = (android.app.WallpaperInfo) r5
                        r6 = 0
                        if (r5 == 0) goto L3e
                        boolean r5 = r5.supportsAmbientMode()
                        if (r5 != r3) goto L3e
                        r6 = r3
                    L3e:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4d
                        return r1
                    L4d:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new AnonymousClass2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = readonlyStateFlow.collect(new WallpaperRepositoryImpl$special$$inlined$filter$1$2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        };
        WallpaperInfo wallpaperInfo = (WallpaperInfo) readonlyStateFlow.getValue();
        boolean z2 = false;
        if (wallpaperInfo != null && wallpaperInfo.supportsAmbientMode()) {
            z2 = true;
        }
        this.wallpaperSupportsAmbientMode = FlowKt.stateIn(flow2, coroutineScope, startedEagerly, Boolean.valueOf(z2));
    }
}

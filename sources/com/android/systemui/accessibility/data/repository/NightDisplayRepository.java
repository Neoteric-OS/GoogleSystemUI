package com.android.systemui.accessibility.data.repository;

import android.hardware.display.ColorDisplayManager;
import android.os.UserHandle;
import android.provider.Settings;
import com.android.systemui.accessibility.data.model.NightDisplayState;
import com.android.systemui.dagger.NightDisplayListenerModule$Builder;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.LocationControllerImpl;
import com.android.systemui.user.utils.UserScopedServiceImpl;
import com.android.systemui.util.kotlin.LocationControllerExtKt;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SettingsProxyExt;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.CallbackFlowBuilder;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NightDisplayRepository {
    public final CoroutineContext bgCoroutineContext;
    public final UserScopedServiceImpl colorDisplayManagerUserScopedService;
    public final GlobalSettings globalSettings;
    public final Flow isForceAutoModeAvailable;
    public final LocationController locationController;
    public final NightDisplayListenerModule$Builder nightDisplayListenerBuilder;
    public final CoroutineScope scope;
    public final SecureSettings secureSettings;
    public final Map stateFlowUserMap = new LinkedHashMap();

    public NightDisplayRepository(CoroutineContext coroutineContext, CoroutineScope coroutineScope, GlobalSettings globalSettings, SecureSettings secureSettings, NightDisplayListenerModule$Builder nightDisplayListenerModule$Builder, UserScopedServiceImpl userScopedServiceImpl, LocationController locationController) {
        this.bgCoroutineContext = coroutineContext;
        this.scope = coroutineScope;
        this.globalSettings = globalSettings;
        this.secureSettings = secureSettings;
        this.nightDisplayListenerBuilder = nightDisplayListenerModule$Builder;
        this.colorDisplayManagerUserScopedService = userScopedServiceImpl;
        this.locationController = locationController;
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new NightDisplayRepository$isForceAutoModeAvailable$1(2, null), SettingsProxyExt.observerFlow(globalSettings, "night_display_forced_auto_mode_available"));
        this.isForceAutoModeAvailable = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.accessibility.data.repository.NightDisplayRepository$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.accessibility.data.repository.NightDisplayRepository$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ NightDisplayRepository this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.accessibility.data.repository.NightDisplayRepository$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, NightDisplayRepository nightDisplayRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = nightDisplayRepository;
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
                        boolean r0 = r6 instanceof com.android.systemui.accessibility.data.repository.NightDisplayRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.accessibility.data.repository.NightDisplayRepository$special$$inlined$map$1$2$1 r0 = (com.android.systemui.accessibility.data.repository.NightDisplayRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.accessibility.data.repository.NightDisplayRepository$special$$inlined$map$1$2$1 r0 = new com.android.systemui.accessibility.data.repository.NightDisplayRepository$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L57
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlin.Unit r5 = (kotlin.Unit) r5
                        com.android.systemui.accessibility.data.repository.NightDisplayRepository r5 = r4.this$0
                        com.android.systemui.util.settings.GlobalSettings r5 = r5.globalSettings
                        com.android.systemui.util.settings.GlobalSettingsImpl r5 = (com.android.systemui.util.settings.GlobalSettingsImpl) r5
                        android.content.ContentResolver r5 = r5.mContentResolver
                        java.lang.String r6 = "night_display_forced_auto_mode_available"
                        java.lang.String r5 = android.provider.Settings.Global.getString(r5, r6)
                        java.lang.String r6 = "1"
                        boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L57
                        return r1
                    L57:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.accessibility.data.repository.NightDisplayRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        });
    }

    public final NightDisplayState initialState(UserHandle userHandle) {
        boolean z;
        ColorDisplayManager colorDisplayManager = (ColorDisplayManager) this.colorDisplayManagerUserScopedService.forUser(userHandle);
        int nightDisplayAutoMode = colorDisplayManager.getNightDisplayAutoMode();
        boolean isNightDisplayActivated = colorDisplayManager.isNightDisplayActivated();
        LocalTime nightDisplayCustomStartTime = colorDisplayManager.getNightDisplayCustomStartTime();
        LocalTime nightDisplayCustomEndTime = colorDisplayManager.getNightDisplayCustomEndTime();
        if (Intrinsics.areEqual(Settings.Global.getString(((GlobalSettingsImpl) this.globalSettings).mContentResolver, "night_display_forced_auto_mode_available"), "1")) {
            if (this.secureSettings.getIntForUser("night_display_auto_mode", -1, userHandle.getIdentifier()) == -1) {
                z = true;
                return new NightDisplayState(nightDisplayAutoMode, isNightDisplayActivated, nightDisplayCustomStartTime, nightDisplayCustomEndTime, z, ((LocationControllerImpl) this.locationController).isLocationEnabled$1());
            }
        }
        z = false;
        return new NightDisplayState(nightDisplayAutoMode, isNightDisplayActivated, nightDisplayCustomStartTime, nightDisplayCustomEndTime, z, ((LocationControllerImpl) this.locationController).isLocationEnabled$1());
    }

    public final Flow nightDisplayState(final UserHandle userHandle) {
        Flow flow;
        Object obj = this.stateFlowUserMap.get(Integer.valueOf(userHandle.getIdentifier()));
        if (obj != null) {
            return (Flow) obj;
        }
        CallbackFlowBuilder callbackFlow = FlowKt.callbackFlow(new NightDisplayRepository$colorDisplayManagerChangeEventFlow$1(this, userHandle, null));
        if (userHandle.getIdentifier() == -10000) {
            flow = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.TRUE);
        } else {
            final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new NightDisplayRepository$isDisplayAutoModeRawNotSet$1(2, null), SettingsProxyExt.observerFlow(this.secureSettings, userHandle.getIdentifier(), "night_display_auto_mode"));
            flow = new Flow() { // from class: com.android.systemui.accessibility.data.repository.NightDisplayRepository$isDisplayAutoModeRawNotSet$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.accessibility.data.repository.NightDisplayRepository$isDisplayAutoModeRawNotSet$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;
                    public final /* synthetic */ UserHandle $userHandle$inlined;
                    public final /* synthetic */ NightDisplayRepository this$0;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.accessibility.data.repository.NightDisplayRepository$isDisplayAutoModeRawNotSet$$inlined$map$1$2$1, reason: invalid class name */
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

                    public AnonymousClass2(FlowCollector flowCollector, NightDisplayRepository nightDisplayRepository, UserHandle userHandle) {
                        this.$this_unsafeFlow = flowCollector;
                        this.this$0 = nightDisplayRepository;
                        this.$userHandle$inlined = userHandle;
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
                            boolean r0 = r7 instanceof com.android.systemui.accessibility.data.repository.NightDisplayRepository$isDisplayAutoModeRawNotSet$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r7
                            com.android.systemui.accessibility.data.repository.NightDisplayRepository$isDisplayAutoModeRawNotSet$$inlined$map$1$2$1 r0 = (com.android.systemui.accessibility.data.repository.NightDisplayRepository$isDisplayAutoModeRawNotSet$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.accessibility.data.repository.NightDisplayRepository$isDisplayAutoModeRawNotSet$$inlined$map$1$2$1 r0 = new com.android.systemui.accessibility.data.repository.NightDisplayRepository$isDisplayAutoModeRawNotSet$$inlined$map$1$2$1
                            r0.<init>(r7)
                        L18:
                            java.lang.Object r7 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r7)
                            goto L59
                        L27:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r6)
                            throw r5
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r7)
                            kotlin.Unit r6 = (kotlin.Unit) r6
                            android.os.UserHandle r6 = r5.$userHandle$inlined
                            int r6 = r6.getIdentifier()
                            com.android.systemui.accessibility.data.repository.NightDisplayRepository r7 = r5.this$0
                            com.android.systemui.util.settings.SecureSettings r7 = r7.secureSettings
                            java.lang.String r2 = "night_display_auto_mode"
                            r4 = -1
                            int r6 = r7.getIntForUser(r2, r4, r6)
                            if (r6 != r4) goto L49
                            r6 = r3
                            goto L4a
                        L49:
                            r6 = 0
                        L4a:
                            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                            java.lang.Object r5 = r5.emit(r6, r0)
                            if (r5 != r1) goto L59
                            return r1
                        L59:
                            kotlin.Unit r5 = kotlin.Unit.INSTANCE
                            return r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.accessibility.data.repository.NightDisplayRepository$isDisplayAutoModeRawNotSet$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, this, userHandle), continuation);
                    return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            };
        }
        final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.isForceAutoModeAvailable, FlowKt.distinctUntilChanged(flow), new NightDisplayRepository$shouldForceAutoMode$1(3, null));
        final int i = 0;
        Flow flow2 = new Flow() { // from class: com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1$2$1 r0 = (com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1$2$1 r0 = new com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L48
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        boolean r5 = r5.booleanValue()
                        com.android.systemui.accessibility.data.model.NightDisplayChangeEvent$OnForceAutoModeChanged r6 = new com.android.systemui.accessibility.data.model.NightDisplayChangeEvent$OnForceAutoModeChanged
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L48
                        return r1
                    L48:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((FlowKt__ZipKt$combine$$inlined$unsafeFlow$1) flowKt__ZipKt$combine$$inlined$unsafeFlow$1).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1) flowKt__ZipKt$combine$$inlined$unsafeFlow$1).collect(new NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 isLocationEnabledFlow = LocationControllerExtKt.isLocationEnabledFlow(this.locationController);
        final int i2 = 1;
        return FlowKt.stateIn(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new NightDisplayRepository$nightDisplayState$1$4(this, userHandle, null), FlowKt.buffer$default(new FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1(initialState(userHandle), new NightDisplayRepository$nightDisplayState$1$3(3, null), FlowKt.merge(callbackFlow, flow2, new Flow() { // from class: com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1$2$1 r0 = (com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1$2$1 r0 = new com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L48
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        boolean r5 = r5.booleanValue()
                        com.android.systemui.accessibility.data.model.NightDisplayChangeEvent$OnForceAutoModeChanged r6 = new com.android.systemui.accessibility.data.model.NightDisplayChangeEvent$OnForceAutoModeChanged
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L48
                        return r1
                    L48:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.accessibility.data.repository.NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((FlowKt__ZipKt$combine$$inlined$unsafeFlow$1) isLocationEnabledFlow).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1) isLocationEnabledFlow).collect(new NightDisplayRepository$nightDisplayState$lambda$2$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        })), -1)), this.bgCoroutineContext), this.scope, SharingStarted.Companion.WhileSubscribed$default(3), new NightDisplayState(0, true, null, null, false, false));
    }

    public final Object setNightDisplayActivated(boolean z, UserHandle userHandle, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.bgCoroutineContext, new NightDisplayRepository$setNightDisplayActivated$2(this, userHandle, z, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    public final Object setNightDisplayAutoMode(UserHandle userHandle, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.bgCoroutineContext, new NightDisplayRepository$setNightDisplayAutoMode$2(this, userHandle, 1, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}

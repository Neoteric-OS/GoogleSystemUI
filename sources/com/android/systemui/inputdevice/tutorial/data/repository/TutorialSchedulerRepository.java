package com.android.systemui.inputdevice.tutorial.data.repository;

import android.content.Context;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.PreferenceDataStoreDelegateKt;
import androidx.datastore.preferences.PreferenceDataStoreSingletonDelegate;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences$Key;
import androidx.datastore.preferences.core.PreferencesKt;
import com.android.systemui.inputdevice.tutorial.data.model.DeviceSchedulerInfo;
import com.android.systemui.inputdevice.tutorial.domain.interactor.TutorialSchedulerInteractor$special$$inlined$map$1;
import java.time.Instant;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.PropertyReference2Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TutorialSchedulerRepository {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public final Context applicationContext;
    public final PreferenceDataStoreSingletonDelegate dataStore$delegate;

    static {
        PropertyReference2Impl propertyReference2Impl = new PropertyReference2Impl(CallableReference.NO_RECEIVER, TutorialSchedulerRepository.class, "dataStore", "getDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{propertyReference2Impl};
    }

    public TutorialSchedulerRepository(Context context, CoroutineScope coroutineScope) {
        this.applicationContext = context;
        this.dataStore$delegate = PreferenceDataStoreDelegateKt.preferencesDataStore$default(coroutineScope);
    }

    public static DeviceSchedulerInfo getDeviceSchedulerInfo(MutablePreferences mutablePreferences, DeviceType deviceType) {
        Long l = (Long) mutablePreferences.get(new Preferences$Key(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(deviceType.name(), "_LAUNCH_TIME")));
        Long l2 = (Long) mutablePreferences.get(new Preferences$Key(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(deviceType.name(), "_CONNECT_TIME")));
        Instant ofEpochSecond = l != null ? Instant.ofEpochSecond(l.longValue()) : null;
        Instant ofEpochSecond2 = l2 != null ? Instant.ofEpochSecond(l2.longValue()) : null;
        DeviceSchedulerInfo deviceSchedulerInfo = new DeviceSchedulerInfo();
        deviceSchedulerInfo.launchTime = ofEpochSecond;
        deviceSchedulerInfo.firstConnectionTime = ofEpochSecond2;
        return deviceSchedulerInfo;
    }

    public final Object clearDataStore(Continuation continuation) {
        Object edit = PreferencesKt.edit(getDataStore(this.applicationContext), new TutorialSchedulerRepository$clearDataStore$2(2, null), continuation);
        return edit == CoroutineSingletons.COROUTINE_SUSPENDED ? edit : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object firstConnectionTime(com.android.systemui.inputdevice.tutorial.data.repository.DeviceType r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$firstConnectionTime$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$firstConnectionTime$1 r0 = (com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$firstConnectionTime$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$firstConnectionTime$1 r0 = new com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$firstConnectionTime$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            java.lang.Object r4 = r0.L$0
            r5 = r4
            com.android.systemui.inputdevice.tutorial.data.repository.DeviceType r5 = (com.android.systemui.inputdevice.tutorial.data.repository.DeviceType) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L42
        L2c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L34:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r4.loadData(r0)
            if (r6 != r1) goto L42
            return r1
        L42:
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r4 = r6.get(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            com.android.systemui.inputdevice.tutorial.data.model.DeviceSchedulerInfo r4 = (com.android.systemui.inputdevice.tutorial.data.model.DeviceSchedulerInfo) r4
            java.time.Instant r4 = r4.firstConnectionTime
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository.firstConnectionTime(com.android.systemui.inputdevice.tutorial.data.repository.DeviceType, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final DataStore getDataStore(Context context) {
        return (DataStore) this.dataStore$delegate.getValue(context, $$delegatedProperties[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isLaunched(com.android.systemui.inputdevice.tutorial.data.repository.DeviceType r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$isLaunched$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$isLaunched$1 r0 = (com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$isLaunched$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$isLaunched$1 r0 = new com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$isLaunched$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            java.lang.Object r4 = r0.L$0
            r5 = r4
            com.android.systemui.inputdevice.tutorial.data.repository.DeviceType r5 = (com.android.systemui.inputdevice.tutorial.data.repository.DeviceType) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L42
        L2c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L34:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r4.loadData(r0)
            if (r6 != r1) goto L42
            return r1
        L42:
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r4 = r6.get(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            com.android.systemui.inputdevice.tutorial.data.model.DeviceSchedulerInfo r4 = (com.android.systemui.inputdevice.tutorial.data.model.DeviceSchedulerInfo) r4
            java.time.Instant r4 = r4.launchTime
            if (r4 == 0) goto L52
            goto L53
        L52:
            r3 = 0
        L53:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository.isLaunched(com.android.systemui.inputdevice.tutorial.data.repository.DeviceType, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object loadData(ContinuationImpl continuationImpl) {
        final Flow data = getDataStore(this.applicationContext).getData();
        return FlowKt.first(new Flow() { // from class: com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$loadData$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$loadData$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ TutorialSchedulerRepository this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$loadData$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, TutorialSchedulerRepository tutorialSchedulerRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = tutorialSchedulerRepository;
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
                        boolean r0 = r7 instanceof com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$loadData$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$loadData$$inlined$map$1$2$1 r0 = (com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$loadData$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$loadData$$inlined$map$1$2$1 r0 = new com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$loadData$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L64
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        androidx.datastore.preferences.core.MutablePreferences r6 = (androidx.datastore.preferences.core.MutablePreferences) r6
                        kotlin.reflect.KProperty[] r7 = com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository.$$delegatedProperties
                        com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository r7 = r5.this$0
                        r7.getClass()
                        com.android.systemui.inputdevice.tutorial.data.repository.DeviceType r7 = com.android.systemui.inputdevice.tutorial.data.repository.DeviceType.KEYBOARD
                        com.android.systemui.inputdevice.tutorial.data.model.DeviceSchedulerInfo r2 = com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository.getDeviceSchedulerInfo(r6, r7)
                        kotlin.Pair r4 = new kotlin.Pair
                        r4.<init>(r7, r2)
                        com.android.systemui.inputdevice.tutorial.data.repository.DeviceType r7 = com.android.systemui.inputdevice.tutorial.data.repository.DeviceType.TOUCHPAD
                        com.android.systemui.inputdevice.tutorial.data.model.DeviceSchedulerInfo r6 = com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository.getDeviceSchedulerInfo(r6, r7)
                        kotlin.Pair r2 = new kotlin.Pair
                        r2.<init>(r7, r6)
                        kotlin.Pair[] r6 = new kotlin.Pair[]{r4, r2}
                        java.util.Map r6 = kotlin.collections.MapsKt.mapOf(r6)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r6, r0)
                        if (r5 != r1) goto L64
                        return r1
                    L64:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$loadData$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, continuationImpl);
    }

    public final Object updateFirstConnectionTime(DeviceType deviceType, Instant instant, Continuation continuation) {
        Object edit = PreferencesKt.edit(getDataStore(this.applicationContext), new TutorialSchedulerRepository$updateFirstConnectionTime$2(this, deviceType, instant, null), continuation);
        return edit == CoroutineSingletons.COROUTINE_SUSPENDED ? edit : Unit.INSTANCE;
    }

    public final Object updateLaunchTime(DeviceType deviceType, Instant instant, TutorialSchedulerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1 anonymousClass1) {
        Object edit = PreferencesKt.edit(getDataStore(this.applicationContext), new TutorialSchedulerRepository$updateLaunchTime$2(this, deviceType, instant, null), anonymousClass1);
        return edit == CoroutineSingletons.COROUTINE_SUSPENDED ? edit : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object wasEverConnected(com.android.systemui.inputdevice.tutorial.data.repository.DeviceType r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$wasEverConnected$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$wasEverConnected$1 r0 = (com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$wasEverConnected$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$wasEverConnected$1 r0 = new com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository$wasEverConnected$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            java.lang.Object r4 = r0.L$0
            r5 = r4
            com.android.systemui.inputdevice.tutorial.data.repository.DeviceType r5 = (com.android.systemui.inputdevice.tutorial.data.repository.DeviceType) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L42
        L2c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L34:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r4.loadData(r0)
            if (r6 != r1) goto L42
            return r1
        L42:
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r4 = r6.get(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            com.android.systemui.inputdevice.tutorial.data.model.DeviceSchedulerInfo r4 = (com.android.systemui.inputdevice.tutorial.data.model.DeviceSchedulerInfo) r4
            java.time.Instant r4 = r4.firstConnectionTime
            if (r4 == 0) goto L52
            goto L53
        L52:
            r3 = 0
        L53:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.inputdevice.tutorial.data.repository.TutorialSchedulerRepository.wasEverConnected(com.android.systemui.inputdevice.tutorial.data.repository.DeviceType, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}

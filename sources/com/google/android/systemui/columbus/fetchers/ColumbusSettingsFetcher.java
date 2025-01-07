package com.google.android.systemui.columbus.fetchers;

import android.app.backup.BackupManager;
import android.content.Context;
import android.net.Uri;
import android.os.UserHandle;
import android.provider.Settings;
import com.android.systemui.util.BackupManagerProxy;
import com.android.systemui.util.kotlin.FlowKt;
import java.util.function.Function;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__LimitKt$drop$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import org.json.JSONArray;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusSettingsFetcher {
    public final String backupPackageName;
    public final StateFlow columbusEnabled;
    public final StateFlow lowSensitivity;
    public final StateFlow selectedAction;
    public final ReadonlyStateFlow selectedApp;
    public final StateFlow selectedShortcut;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ BackupManagerProxy $backupManagerProxy;
        final /* synthetic */ UserFetcher $userFetcher;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(UserFetcher userFetcher, BackupManagerProxy backupManagerProxy, Continuation continuation) {
            super(2, continuation);
            this.$userFetcher = userFetcher;
            this.$backupManagerProxy = backupManagerProxy;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return ColumbusSettingsFetcher.this.new AnonymousClass1(this.$userFetcher, this.$backupManagerProxy, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ColumbusSettingsFetcher columbusSettingsFetcher = ColumbusSettingsFetcher.this;
                SafeFlow sample = FlowKt.sample(kotlinx.coroutines.flow.FlowKt.merge(new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(columbusSettingsFetcher.columbusEnabled), new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(columbusSettingsFetcher.selectedAction), new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(columbusSettingsFetcher.selectedApp), new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(columbusSettingsFetcher.selectedShortcut), new FlowKt__LimitKt$drop$$inlined$unsafeFlow$1(columbusSettingsFetcher.lowSensitivity)), this.$userFetcher.currentUserHandle);
                final BackupManagerProxy backupManagerProxy = this.$backupManagerProxy;
                final ColumbusSettingsFetcher columbusSettingsFetcher2 = ColumbusSettingsFetcher.this;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        int identifier = ((UserHandle) obj2).getIdentifier();
                        String str = columbusSettingsFetcher2.backupPackageName;
                        BackupManagerProxy.this.getClass();
                        BackupManager.dataChangedForUser(identifier, str);
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (sample.collect(flowCollector, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public ColumbusSettingsFetcher(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, UserFetcher userFetcher, final ContentFetcher contentFetcher, Context context, BackupManagerProxy backupManagerProxy) {
        contentFetcher.getClass();
        final String str = "columbus_enabled";
        final Uri uriFor = Settings.Secure.getUriFor("columbus_enabled");
        final boolean z = false;
        this.columbusEnabled = (StateFlow) contentFetcher.cachedBooleanFlows.computeIfAbsent(uriFor, new Function() { // from class: com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ContentFetcher contentFetcher2 = ContentFetcher.this;
                String str2 = str;
                Uri uri = uriFor;
                boolean z2 = z;
                contentFetcher2.getClass();
                final Flow flowOn = kotlinx.coroutines.flow.FlowKt.flowOn(kotlinx.coroutines.flow.FlowKt.mapLatest(new ContentFetcher$createIntSecureSettingFlow$1(contentFetcher2, str2, z2 ? 1 : 0, null), kotlinx.coroutines.flow.FlowKt.transformLatest(contentFetcher2.userFetcher.currentUserHandle, new ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1(null, contentFetcher2, uri))), contentFetcher2.bgDispatcher);
                final ContentFetcher contentFetcher3 = ContentFetcher.this;
                return kotlinx.coroutines.flow.FlowKt.stateIn(new Flow() { // from class: com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1$2, reason: invalid class name */
                    public final class AnonymousClass2 implements FlowCollector {
                        public final /* synthetic */ FlowCollector $this_unsafeFlow;
                        public final /* synthetic */ ContentFetcher this$0;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1$2$1, reason: invalid class name */
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

                        public AnonymousClass2(FlowCollector flowCollector, ContentFetcher contentFetcher) {
                            this.$this_unsafeFlow = flowCollector;
                            this.this$0 = contentFetcher;
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
                                boolean r0 = r6 instanceof com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r6
                                com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1$2$1 r0 = (com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1$2$1 r0 = new com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1$2$1
                                r0.<init>(r6)
                            L18:
                                java.lang.Object r6 = r0.result
                                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L2f
                                if (r2 != r3) goto L27
                                kotlin.ResultKt.throwOnFailure(r6)
                                goto L51
                            L27:
                                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                r4.<init>(r5)
                                throw r4
                            L2f:
                                kotlin.ResultKt.throwOnFailure(r6)
                                java.lang.Number r5 = (java.lang.Number) r5
                                int r5 = r5.intValue()
                                com.google.android.systemui.columbus.fetchers.ContentFetcher r6 = r4.this$0
                                r6.getClass()
                                if (r5 == 0) goto L41
                                r5 = r3
                                goto L42
                            L41:
                                r5 = 0
                            L42:
                                java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                                r0.label = r3
                                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                                java.lang.Object r4 = r4.emit(r5, r0)
                                if (r4 != r1) goto L51
                                return r1
                            L51:
                                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                        Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, contentFetcher3), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                }, contentFetcher3.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(z));
            }
        });
        this.selectedAction = contentFetcher.getStringSecureSettingFlow("columbus_action", "");
        final StateFlow stringSecureSettingFlow = contentFetcher.getStringSecureSettingFlow("columbus_launch_app", "");
        final int i = 0;
        this.selectedApp = kotlinx.coroutines.flow.FlowKt.stateIn(new Flow() { // from class: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1 r0 = (com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1 r0 = new com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L43
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.String r5 = (java.lang.String) r5
                        android.content.ComponentName r5 = android.content.ComponentName.unflattenFromString(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L43
                        return r1
                    L43:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = stringSecureSettingFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = stringSecureSettingFlow.collect(new ColumbusSettingsFetcher$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        this.selectedShortcut = contentFetcher.getStringSecureSettingFlow("columbus_launch_app_shortcut", "");
        final Uri uriFor2 = Settings.Secure.getUriFor("columbus_launch_profile_id");
        final String str2 = "columbus_low_sensitivity";
        final Uri uriFor3 = Settings.Secure.getUriFor("columbus_low_sensitivity");
        this.lowSensitivity = (StateFlow) contentFetcher.cachedBooleanFlows.computeIfAbsent(uriFor3, new Function() { // from class: com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ContentFetcher contentFetcher2 = ContentFetcher.this;
                String str22 = str2;
                Uri uri = uriFor3;
                boolean z2 = z;
                contentFetcher2.getClass();
                final Flow flowOn = kotlinx.coroutines.flow.FlowKt.flowOn(kotlinx.coroutines.flow.FlowKt.mapLatest(new ContentFetcher$createIntSecureSettingFlow$1(contentFetcher2, str22, z2 ? 1 : 0, null), kotlinx.coroutines.flow.FlowKt.transformLatest(contentFetcher2.userFetcher.currentUserHandle, new ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1(null, contentFetcher2, uri))), contentFetcher2.bgDispatcher);
                final ContentFetcher contentFetcher3 = ContentFetcher.this;
                return kotlinx.coroutines.flow.FlowKt.stateIn(new Flow() { // from class: com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1$2, reason: invalid class name */
                    public final class AnonymousClass2 implements FlowCollector {
                        public final /* synthetic */ FlowCollector $this_unsafeFlow;
                        public final /* synthetic */ ContentFetcher this$0;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1$2$1, reason: invalid class name */
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

                        public AnonymousClass2(FlowCollector flowCollector, ContentFetcher contentFetcher) {
                            this.$this_unsafeFlow = flowCollector;
                            this.this$0 = contentFetcher;
                        }

                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj, Continuation continuation) {
                            /*
                                this = this;
                                boolean r0 = r6 instanceof com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r6
                                com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1$2$1 r0 = (com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1$2$1 r0 = new com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1$2$1
                                r0.<init>(r6)
                            L18:
                                java.lang.Object r6 = r0.result
                                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L2f
                                if (r2 != r3) goto L27
                                kotlin.ResultKt.throwOnFailure(r6)
                                goto L51
                            L27:
                                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                r4.<init>(r5)
                                throw r4
                            L2f:
                                kotlin.ResultKt.throwOnFailure(r6)
                                java.lang.Number r5 = (java.lang.Number) r5
                                int r5 = r5.intValue()
                                com.google.android.systemui.columbus.fetchers.ContentFetcher r6 = r4.this$0
                                r6.getClass()
                                if (r5 == 0) goto L41
                                r5 = r3
                                goto L42
                            L41:
                                r5 = 0
                            L42:
                                java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                                r0.label = r3
                                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                                java.lang.Object r4 = r4.emit(r5, r0)
                                if (r4 != r1) goto L51
                                return r1
                            L51:
                                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.fetchers.ContentFetcher$getBooleanSecureSettingFlow$1$apply$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                        Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, contentFetcher3), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                }, contentFetcher3.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(z));
            }
        });
        final String str3 = "columbus_silence_alerts";
        final Uri uriFor4 = Settings.Secure.getUriFor("columbus_silence_alerts");
        final boolean z2 = true;
        final StateFlow stringSecureSettingFlow2 = contentFetcher.getStringSecureSettingFlow("columbus_package_stats", "[]");
        final int i2 = 1;
        kotlinx.coroutines.flow.FlowKt.stateIn(new Flow() { // from class: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1 r0 = (com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1 r0 = new com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L43
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.String r5 = (java.lang.String) r5
                        android.content.ComponentName r5 = android.content.ComponentName.unflattenFromString(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L43
                        return r1
                    L43:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = stringSecureSettingFlow2.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = stringSecureSettingFlow2.collect(new ColumbusSettingsFetcher$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new JSONArray());
        this.backupPackageName = context.getBasePackageName();
        BuildersKt.launch$default(coroutineScope, coroutineDispatcher, null, new AnonymousClass1(userFetcher, backupManagerProxy, null), 2);
    }
}

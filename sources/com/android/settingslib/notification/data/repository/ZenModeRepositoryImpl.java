package com.android.settingslib.notification.data.repository;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.notification.Condition;
import android.service.notification.ZenModeConfig;
import com.android.settingslib.notification.modes.ZenMode;
import com.android.settingslib.notification.modes.ZenModesBackend;
import java.time.Duration;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.CallbackFlowBuilder;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZenModeRepositoryImpl {
    public final ZenModesBackend backend;
    public final CoroutineContext backgroundCoroutineContext;
    public final Handler backgroundHandler;
    public final ContentResolver contentResolver;
    public final Context context;
    public final Lazy modes$delegate;
    public final NotificationManager notificationManager;
    public final CoroutineScope scope;
    public final Lazy notificationBroadcasts$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ ZenModeRepositoryImpl this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(ZenModeRepositoryImpl zenModeRepositoryImpl, Continuation continuation) {
                super(2, continuation);
                this.this$0 = zenModeRepositoryImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v1, types: [android.content.BroadcastReceiver, com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2$1$receiver$1] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final ProducerScope producerScope = (ProducerScope) this.L$0;
                    final ?? r1 = 
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001e: CONSTRUCTOR (r1v1 'r1' ?? I:com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2$1$receiver$1) = (r8v2 'producerScope' kotlinx.coroutines.channels.ProducerScope A[DONT_INLINE]) A[DECLARE_VAR, MD:(kotlinx.coroutines.channels.ProducerScope):void (m)] (LINE:31) call: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2$1$receiver$1.<init>(kotlinx.coroutines.channels.ProducerScope):void type: CONSTRUCTOR in method: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2.1.invokeSuspend(java.lang.Object):java.lang.Object, file: classes.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:285)
                        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
                        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:636)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:270)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:161)
                        	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:310)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:285)
                        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
                        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:636)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:286)
                        	at jadx.core.codegen.ClassGen.addInsnBody(ClassGen.java:543)
                        	at jadx.core.codegen.ClassGen.addField(ClassGen.java:449)
                        	at jadx.core.codegen.ClassGen.addFields(ClassGen.java:416)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:285)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:270)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:161)
                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
                        	at jadx.core.ProcessClass.process(ProcessClass.java:79)
                        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
                        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:402)
                        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:390)
                        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:340)
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2$1$receiver$1, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:305)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                        	... 72 more
                        */
                    /*
                        this = this;
                        kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r1 = r7.label
                        r2 = 1
                        if (r1 == 0) goto L15
                        if (r1 != r2) goto Ld
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L51
                    Ld:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L15:
                        kotlin.ResultKt.throwOnFailure(r8)
                        java.lang.Object r8 = r7.L$0
                        kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
                        com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2$1$receiver$1 r1 = new com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2$1$receiver$1
                        r1.<init>(r8)
                        com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl r3 = r7.this$0
                        android.content.Context r3 = r3.context
                        android.content.IntentFilter r4 = new android.content.IntentFilter
                        r4.<init>()
                        java.lang.String r5 = "android.app.action.INTERRUPTION_FILTER_CHANGED"
                        r4.addAction(r5)
                        java.lang.String r5 = "android.app.action.NOTIFICATION_POLICY_CHANGED"
                        r4.addAction(r5)
                        java.lang.String r5 = "android.app.action.CONSOLIDATED_NOTIFICATION_POLICY_CHANGED"
                        r4.addAction(r5)
                        com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl r5 = r7.this$0
                        android.os.Handler r5 = r5.backgroundHandler
                        r6 = 0
                        r3.registerReceiver(r1, r4, r6, r5)
                        com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2$1$2 r3 = new com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2$1$2
                        com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl r4 = r7.this$0
                        r3.<init>()
                        r7.label = r2
                        java.lang.Object r7 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r8, r3, r7)
                        if (r7 != r0) goto L51
                        return r0
                    L51:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$notificationBroadcasts$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CallbackFlowBuilder callbackFlow = FlowKt.callbackFlow(new AnonymousClass1(ZenModeRepositoryImpl.this, null));
                ZenModeRepositoryImpl zenModeRepositoryImpl = ZenModeRepositoryImpl.this;
                return FlowKt.shareIn(FlowKt.flowOn(callbackFlow, zenModeRepositoryImpl.backgroundCoroutineContext), zenModeRepositoryImpl.scope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
            }
        });
        public final Lazy consolidatedNotificationPolicy$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$consolidatedNotificationPolicy$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ReadonlyStateFlow stateIn;
                final ZenModeRepositoryImpl zenModeRepositoryImpl = ZenModeRepositoryImpl.this;
                stateIn = FlowKt.stateIn(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new ZenModeRepositoryImpl$flowFromBroadcast$3(null, r0), new ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1(new ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1((SharedFlow) zenModeRepositoryImpl.notificationBroadcasts$delegate.getValue(), "android.app.action.CONSOLIDATED_NOTIFICATION_POLICY_CHANGED"), new Function1() { // from class: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$consolidatedNotificationPolicy$2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Bundle extras;
                        NotificationManager.Policy policy;
                        Intent intent = (Intent) obj;
                        return (intent == null || (extras = intent.getExtras()) == null || (policy = (NotificationManager.Policy) extras.getParcelable("android.app.extra.NOTIFICATION_POLICY")) == null) ? ZenModeRepositoryImpl.this.notificationManager.getConsolidatedNotificationPolicy() : policy;
                    }
                })), zenModeRepositoryImpl.backgroundCoroutineContext), zenModeRepositoryImpl.scope, SharingStarted.Companion.WhileSubscribed$default(3), null);
                return stateIn;
            }
        });
        public final Lazy globalZenMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$globalZenMode$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ReadonlyStateFlow stateIn;
                final ZenModeRepositoryImpl zenModeRepositoryImpl = ZenModeRepositoryImpl.this;
                stateIn = FlowKt.stateIn(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new ZenModeRepositoryImpl$flowFromBroadcast$3(null, r0), new ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1(new ZenModeRepositoryImpl$flowFromBroadcast$$inlined$map$1((SharedFlow) zenModeRepositoryImpl.notificationBroadcasts$delegate.getValue(), "android.app.action.INTERRUPTION_FILTER_CHANGED"), new Function1() { // from class: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$globalZenMode$2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(ZenModeRepositoryImpl.this.notificationManager.getZenMode());
                    }
                })), zenModeRepositoryImpl.backgroundCoroutineContext), zenModeRepositoryImpl.scope, SharingStarted.Companion.WhileSubscribed$default(3), null);
                return stateIn;
            }
        });

        public ZenModeRepositoryImpl(Context context, NotificationManager notificationManager, ZenModesBackend zenModesBackend, ContentResolver contentResolver, CoroutineScope coroutineScope, CoroutineContext coroutineContext, Handler handler) {
            this.context = context;
            this.notificationManager = notificationManager;
            this.backend = zenModesBackend;
            this.scope = coroutineScope;
            this.backgroundCoroutineContext = coroutineContext;
            this.backgroundHandler = handler;
            LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$zenConfigChanged$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Unit.INSTANCE);
                }
            });
            this.modes$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl$modes$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(EmptyList.INSTANCE);
                }
            });
        }

        public final void activateMode(ZenMode zenMode, Duration duration) {
            ZenModesBackend zenModesBackend = this.backend;
            zenModesBackend.getClass();
            if (zenMode.isManualDnd()) {
                zenModesBackend.mNotificationManager.setZenMode(1, duration != null ? ZenModeConfig.toTimeCondition(zenModesBackend.mContext, (int) duration.toMinutes(), ActivityManager.getCurrentUser(), true).id : null, "ZenModeBackend", true);
            } else {
                if (duration != null) {
                    throw new IllegalArgumentException("Only the manual DND mode can be activated for a specific duration");
                }
                zenModesBackend.mNotificationManager.setAutomaticZenRuleState(zenMode.mId, new Condition(zenMode.mRule.getConditionId(), "", 1, 1));
            }
        }

        public final StateFlow getConsolidatedNotificationPolicy() {
            return (StateFlow) this.consolidatedNotificationPolicy$delegate.getValue();
        }
    }

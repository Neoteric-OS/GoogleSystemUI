package androidx.room.coroutines;

import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TransactionElement;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__ChannelsKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FlowUtil$createFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ RoomDatabase $db;
    final /* synthetic */ boolean $inTransaction;
    final /* synthetic */ String[] $tableNames;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.room.coroutines.FlowUtil$createFlow$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ FlowCollector $$this$flow;
        final /* synthetic */ Function1 $block;
        final /* synthetic */ RoomDatabase $db;
        final /* synthetic */ boolean $inTransaction;
        final /* synthetic */ String[] $tableNames;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: androidx.room.coroutines.FlowUtil$createFlow$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C00171 extends SuspendLambda implements Function2 {
            final /* synthetic */ Function1 $block;
            final /* synthetic */ RoomDatabase $db;
            final /* synthetic */ boolean $inTransaction;
            final /* synthetic */ FlowUtil$createFlow$1$1$observer$1 $observer;
            final /* synthetic */ Channel $observerChannel;
            final /* synthetic */ Channel $resultChannel;
            Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00171(RoomDatabase roomDatabase, FlowUtil$createFlow$1$1$observer$1 flowUtil$createFlow$1$1$observer$1, Channel channel, boolean z, Channel channel2, Function1 function1, Continuation continuation) {
                super(2, continuation);
                this.$db = roomDatabase;
                this.$observer = flowUtil$createFlow$1$1$observer$1;
                this.$observerChannel = channel;
                this.$inTransaction = z;
                this.$resultChannel = channel2;
                this.$block = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00171(this.$db, this.$observer, this.$observerChannel, this.$inTransaction, this.$resultChannel, this.$block, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00171) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x006c A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:18:0x0076 A[Catch: all -> 0x0028, TryCatch #0 {all -> 0x0028, blocks: (B:12:0x0024, B:13:0x0061, B:16:0x006d, B:18:0x0076, B:21:0x0093, B:24:0x009c, B:35:0x002f, B:37:0x0038, B:39:0x005a), top: B:2:0x0007 }] */
            /* JADX WARN: Removed duplicated region for block: B:26:0x00a9 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00aa  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00a7 -> B:13:0x0061). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r13) {
                /*
                    Method dump skipped, instructions count: 246
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.room.coroutines.FlowUtil$createFlow$1.AnonymousClass1.C00171.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(RoomDatabase roomDatabase, boolean z, FlowCollector flowCollector, String[] strArr, Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$db = roomDatabase;
            this.$inTransaction = z;
            this.$$this$flow = flowCollector;
            this.$tableNames = strArr;
            this.$block = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$db, this.$inTransaction, this.$$this$flow, this.$tableNames, this.$block, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r8v1, types: [androidx.room.coroutines.FlowUtil$createFlow$1$1$observer$1, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r8v2 */
        /* JADX WARN: Type inference failed for: r8v6, types: [androidx.room.coroutines.FlowUtil$createFlow$1$1$observer$1] */
        /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Object, kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel] */
        /* JADX WARN: Type inference failed for: r9v1 */
        /* JADX WARN: Type inference failed for: r9v4, types: [kotlinx.coroutines.channels.Channel] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            final Channel Channel$default;
            FlowUtil$createFlow$1$1$observer$1 flowUtil$createFlow$1$1$observer$1;
            Channel Channel$default2;
            Object obj2;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
                Channel$default = ChannelKt.Channel$default(-1, null, null, 6);
                final String[] strArr = this.$tableNames;
                flowUtil$createFlow$1$1$observer$1 = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.coroutines.FlowUtil$createFlow$1$1$observer$1
                    @Override // androidx.room.InvalidationTracker.Observer
                    public final void onInvalidated(Set set) {
                        Channel$default.mo1790trySendJP2dKIU(Unit.INSTANCE);
                    }
                };
                Channel$default.mo1790trySendJP2dKIU(unit);
                Channel$default2 = ChannelKt.Channel$default(0, null, null, 7);
                RoomDatabase roomDatabase = this.$db;
                boolean z = this.$inTransaction;
                this.L$0 = Channel$default;
                this.L$1 = flowUtil$createFlow$1$1$observer$1;
                this.L$2 = Channel$default2;
                this.L$3 = coroutineScope;
                this.label = 1;
                if (!roomDatabase.inCompatibilityMode$room_runtime_release()) {
                    ContextScope contextScope = roomDatabase.coroutineScope;
                    if (contextScope == null) {
                        contextScope = null;
                    }
                    obj2 = contextScope.coroutineContext;
                } else {
                    if (getContext().get(TransactionElement.Key) != null) {
                        throw new ClassCastException();
                    }
                    if (z) {
                        obj2 = roomDatabase.transactionContext;
                        if (obj2 == null) {
                            obj2 = null;
                        }
                    } else {
                        ContextScope contextScope2 = roomDatabase.coroutineScope;
                        if (contextScope2 == null) {
                            contextScope2 = null;
                        }
                        obj2 = contextScope2.coroutineContext;
                    }
                }
                if (obj2 == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$3;
                Channel$default2 = (Channel) this.L$2;
                flowUtil$createFlow$1$1$observer$1 = (FlowUtil$createFlow$1$1$observer$1) this.L$1;
                Channel$default = (Channel) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = obj;
            }
            BuildersKt.launch$default(coroutineScope, ((CoroutineContext) obj2).minusKey(Job.Key.$$INSTANCE), null, new C00171(this.$db, flowUtil$createFlow$1$1$observer$1, Channel$default, this.$inTransaction, Channel$default2, this.$block, null), 2);
            FlowCollector flowCollector = this.$$this$flow;
            this.L$0 = null;
            this.L$1 = null;
            this.L$2 = null;
            this.L$3 = null;
            this.label = 2;
            Object emitAllImpl$FlowKt__ChannelsKt = FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt(flowCollector, Channel$default2, true, this);
            if (emitAllImpl$FlowKt__ChannelsKt != coroutineSingletons) {
                emitAllImpl$FlowKt__ChannelsKt = unit;
            }
            return emitAllImpl$FlowKt__ChannelsKt == coroutineSingletons ? coroutineSingletons : unit;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowUtil$createFlow$1(RoomDatabase roomDatabase, boolean z, String[] strArr, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$db = roomDatabase;
        this.$inTransaction = z;
        this.$tableNames = strArr;
        this.$block = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FlowUtil$createFlow$1 flowUtil$createFlow$1 = new FlowUtil$createFlow$1(this.$db, this.$inTransaction, this.$tableNames, this.$block, continuation);
        flowUtil$createFlow$1.L$0 = obj;
        return flowUtil$createFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FlowUtil$createFlow$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$db, this.$inTransaction, (FlowCollector) this.L$0, this.$tableNames, this.$block, null);
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(this, anonymousClass1) == coroutineSingletons) {
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

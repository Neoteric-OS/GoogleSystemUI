package com.google.android.systemui.columbus;

import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.util.wakelock.WakeLock;
import com.google.android.systemui.columbus.fetchers.ActionFetcher;
import com.google.android.systemui.columbus.fetchers.GateFetcher;
import com.google.android.systemui.columbus.sensors.GestureController;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusManager implements Dumpable {
    public final List actions;
    public final StateFlow activeAction;
    public final Set effects;
    public final ChannelFlowTransformLatest events;
    public final Set gates;
    public final GestureController gestureController;
    public final ChannelFlowTransformLatest state;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.columbus.ColumbusManager$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.google.android.systemui.columbus.ColumbusManager$1$1, reason: invalid class name and collision with other inner class name */
        public final class C02721 implements FlowCollector {
            public final /* synthetic */ Ref$ObjectRef $previousAction;

            public C02721(Ref$ObjectRef ref$ObjectRef) {
                this.$previousAction = ref$ObjectRef;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                emit((Pair) obj, continuation);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Removed duplicated region for block: B:25:0x004a  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(kotlin.Pair r5, kotlin.coroutines.Continuation r6) {
                /*
                    r4 = this;
                    boolean r0 = r6 instanceof com.google.android.systemui.columbus.ColumbusManager$1$1$emit$1
                    if (r0 == 0) goto L13
                    r0 = r6
                    com.google.android.systemui.columbus.ColumbusManager$1$1$emit$1 r0 = (com.google.android.systemui.columbus.ColumbusManager$1$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    com.google.android.systemui.columbus.ColumbusManager$1$1$emit$1 r0 = new com.google.android.systemui.columbus.ColumbusManager$1$1$emit$1
                    r0.<init>(r4, r6)
                L18:
                    java.lang.Object r6 = r0.result
                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r1 = r0.label
                    java.lang.String r2 = "Columbus/Manager"
                    r3 = 0
                    if (r1 == 0) goto L4a
                    r4 = 1
                    if (r1 != r4) goto L42
                    java.lang.Object r4 = r0.L$2
                    if (r4 != 0) goto L3c
                    java.lang.Object r4 = r0.L$1
                    if (r4 != 0) goto L36
                    java.lang.Object r4 = r0.L$0
                    com.google.android.systemui.columbus.ColumbusManager$1$1 r4 = (com.google.android.systemui.columbus.ColumbusManager.AnonymousClass1.C02721) r4
                    kotlin.ResultKt.throwOnFailure(r6)
                    goto L67
                L36:
                    java.lang.ClassCastException r4 = new java.lang.ClassCastException
                    r4.<init>()
                    throw r4
                L3c:
                    java.lang.ClassCastException r4 = new java.lang.ClassCastException
                    r4.<init>()
                    throw r4
                L42:
                    java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                    java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                    r4.<init>(r5)
                    throw r4
                L4a:
                    kotlin.ResultKt.throwOnFailure(r6)
                    java.lang.Object r6 = r5.component1()
                    if (r6 != 0) goto L9a
                    java.lang.Object r5 = r5.component2()
                    if (r5 != 0) goto L94
                    kotlin.jvm.internal.Ref$ObjectRef r5 = r4.$previousAction
                    java.lang.Object r6 = r5.element
                    boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r3)
                    if (r6 != 0) goto L8c
                    java.lang.Object r5 = r5.element
                    if (r5 != 0) goto L86
                L67:
                    kotlin.jvm.internal.Ref$ObjectRef r5 = r4.$previousAction
                    java.lang.Object r5 = r5.element
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    java.lang.String r0 = "Changing action from "
                    r6.<init>(r0)
                    r6.append(r5)
                    java.lang.String r5 = " to null"
                    r6.append(r5)
                    java.lang.String r5 = r6.toString()
                    android.util.Log.i(r2, r5)
                    kotlin.jvm.internal.Ref$ObjectRef r4 = r4.$previousAction
                    r4.element = r3
                    goto L8c
                L86:
                    java.lang.ClassCastException r4 = new java.lang.ClassCastException
                    r4.<init>()
                    throw r4
                L8c:
                    java.lang.String r4 = "No available actions"
                    android.util.Log.i(r2, r4)
                    kotlin.Unit r4 = kotlin.Unit.INSTANCE
                    return r4
                L94:
                    java.lang.ClassCastException r4 = new java.lang.ClassCastException
                    r4.<init>()
                    throw r4
                L9a:
                    java.lang.ClassCastException r4 = new java.lang.ClassCastException
                    r4.<init>()
                    throw r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.ColumbusManager.AnonymousClass1.C02721.emit(kotlin.Pair, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return ColumbusManager.this.new AnonymousClass1(continuation);
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
                Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                ChannelFlowTransformLatest channelFlowTransformLatest = ColumbusManager.this.state;
                C02721 c02721 = new C02721(ref$ObjectRef);
                this.label = 1;
                if (channelFlowTransformLatest.collect(c02721, this) == coroutineSingletons) {
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

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.columbus.ColumbusManager$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.google.android.systemui.columbus.ColumbusManager$2$1, reason: invalid class name */
        public final class AnonymousClass1 implements FlowCollector {
            public final /* synthetic */ ColumbusManager this$0;

            public AnonymousClass1(ColumbusManager columbusManager) {
                this.this$0 = columbusManager;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                emit((Pair) obj, continuation);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Removed duplicated region for block: B:23:0x0054  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0020  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(kotlin.Pair r5, kotlin.coroutines.Continuation r6) {
                /*
                    r4 = this;
                    boolean r0 = r6 instanceof com.google.android.systemui.columbus.ColumbusManager$2$1$emit$1
                    if (r0 == 0) goto L13
                    r0 = r6
                    com.google.android.systemui.columbus.ColumbusManager$2$1$emit$1 r0 = (com.google.android.systemui.columbus.ColumbusManager$2$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    com.google.android.systemui.columbus.ColumbusManager$2$1$emit$1 r0 = new com.google.android.systemui.columbus.ColumbusManager$2$1$emit$1
                    r0.<init>(r4, r6)
                L18:
                    java.lang.Object r4 = r0.result
                    kotlin.coroutines.intrinsics.CoroutineSingletons r6 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r6 = r0.label
                    if (r6 == 0) goto L54
                    r5 = 1
                    if (r6 != r5) goto L4c
                    java.lang.Object r5 = r0.L$1
                    if (r5 != 0) goto L46
                    java.lang.Object r5 = r0.L$0
                    com.google.android.systemui.columbus.ColumbusManager$2$1 r5 = (com.google.android.systemui.columbus.ColumbusManager.AnonymousClass2.AnonymousClass1) r5
                    kotlin.ResultKt.throwOnFailure(r4)
                    com.google.android.systemui.columbus.ColumbusManager r4 = r5.this$0
                    java.util.Set r4 = r4.effects
                    java.lang.Iterable r4 = (java.lang.Iterable) r4
                    java.util.Iterator r4 = r4.iterator()
                    boolean r5 = r4.hasNext()
                    if (r5 != 0) goto L41
                    kotlin.Unit r4 = kotlin.Unit.INSTANCE
                    return r4
                L41:
                    java.lang.ClassCastException r4 = androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0.m(r4)
                    throw r4
                L46:
                    java.lang.ClassCastException r4 = new java.lang.ClassCastException
                    r4.<init>()
                    throw r4
                L4c:
                    java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                    java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                    r4.<init>(r5)
                    throw r4
                L54:
                    kotlin.ResultKt.throwOnFailure(r4)
                    java.lang.Object r4 = r5.component1()
                    if (r4 != 0) goto L6a
                    java.lang.Object r4 = r5.component2()
                    r4.getClass()
                    java.lang.ClassCastException r4 = new java.lang.ClassCastException
                    r4.<init>()
                    throw r4
                L6a:
                    java.lang.ClassCastException r4 = new java.lang.ClassCastException
                    r4.<init>()
                    throw r4
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.ColumbusManager.AnonymousClass2.AnonymousClass1.emit(kotlin.Pair, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return ColumbusManager.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ColumbusManager columbusManager = ColumbusManager.this;
                ChannelFlowTransformLatest channelFlowTransformLatest = columbusManager.events;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(columbusManager);
                this.label = 1;
                if (channelFlowTransformLatest.collect(anonymousClass1, this) == coroutineSingletons) {
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

    public ColumbusManager(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Set set, List list, Set set2, WakeLock wakeLock, GestureController gestureController, GateFetcher gateFetcher, final ActionFetcher actionFetcher) {
        this.gates = set;
        this.actions = list;
        this.effects = set2;
        this.gestureController = gestureController;
        StateFlow stateFlow = (StateFlow) actionFetcher.firstAvailableMap.computeIfAbsent(new ActionFetcher.ActionListKey(list), new Function() { // from class: com.google.android.systemui.columbus.fetchers.ActionFetcher$getFirstAvailableFlow$1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                List list2 = ((ActionFetcher.ActionListKey) obj).actions;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                Iterator it = list2.iterator();
                if (it.hasNext()) {
                    throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
                }
                final Flow[] flowArr = (Flow[]) CollectionsKt.toList(arrayList).toArray(new Flow[0]);
                return FlowKt.stateIn(new Flow() { // from class: com.google.android.systemui.columbus.fetchers.ActionFetcher$getFirstAvailableFlow$1$apply$$inlined$combine$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.google.android.systemui.columbus.fetchers.ActionFetcher$getFirstAvailableFlow$1$apply$$inlined$combine$1$3, reason: invalid class name */
                    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
                        private /* synthetic */ Object L$0;
                        /* synthetic */ Object L$1;
                        int label;

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            AnonymousClass3 anonymousClass3 = new AnonymousClass3(3, (Continuation) obj3);
                            anonymousClass3.L$0 = (FlowCollector) obj;
                            anonymousClass3.L$1 = (Object[]) obj2;
                            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Pair pair;
                            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                FlowCollector flowCollector = (FlowCollector) this.L$0;
                                Pair[] pairArr = (Pair[]) ((Object[]) this.L$1);
                                int length = pairArr.length;
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= length) {
                                        pair = null;
                                        break;
                                    }
                                    pair = pairArr[i2];
                                    if (((Boolean) pair.getSecond()).booleanValue()) {
                                        break;
                                    }
                                    i2++;
                                }
                                if (pair != null && pair.getFirst() != null) {
                                    throw new ClassCastException();
                                }
                                this.label = 1;
                                if (flowCollector.emit(null, this) == coroutineSingletons) {
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

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                        final Flow[] flowArr2 = flowArr;
                        Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.google.android.systemui.columbus.fetchers.ActionFetcher$getFirstAvailableFlow$1$apply$$inlined$combine$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return new Pair[flowArr2.length];
                            }
                        }, new AnonymousClass3(3, null), flowCollector, flowArr2);
                        return combineInternal == CoroutineSingletons.COROUTINE_SUSPENDED ? combineInternal : Unit.INSTANCE;
                    }
                }, ActionFetcher.this.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
            }
        });
        this.activeAction = stateFlow;
        ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(stateFlow, new ColumbusManager$special$$inlined$flatMapLatest$1(this, null));
        this.state = transformLatest;
        this.events = FlowKt.transformLatest(transformLatest, new ColumbusManager$special$$inlined$flatMapLatest$2(this, null));
        BuildersKt.launch$default(coroutineScope, coroutineDispatcher, null, new AnonymousClass1(null), 2);
        BuildersKt.launch$default(coroutineScope, coroutineDispatcher, null, new AnonymousClass2(null), 2);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("ColumbusManager (ColumbusManager) state:");
        printWriter.println("  Gates:");
        Iterator it = this.gates.iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            printWriter.print("    ");
            throw null;
        }
        printWriter.println("  Actions:");
        Iterator it2 = this.actions.iterator();
        if (it2.hasNext()) {
            if (it2.next() != null) {
                throw new ClassCastException();
            }
            printWriter.print("    ");
            throw null;
        }
        printWriter.println("  Active: " + this.activeAction.getValue());
        printWriter.println("  Feedback Effects:");
        Iterator it3 = this.effects.iterator();
        if (!it3.hasNext()) {
            this.gestureController.dump(printWriter, strArr);
        } else {
            if (it3.next() != null) {
                throw new ClassCastException();
            }
            printWriter.print("    ");
            throw null;
        }
    }
}

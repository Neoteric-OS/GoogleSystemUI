package androidx.datastore.core;

import androidx.datastore.core.handlers.NoOpCorruptionHandler;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.time.Duration;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.ChannelFlowBuilder;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StartedWhileSubscribed;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DataStoreImpl implements DataStore {
    public final NoOpCorruptionHandler corruptionHandler;
    public final InitDataStore readAndInit;
    public final CoroutineScope scope;
    public final Storage storage;
    public final ReadonlySharedFlow updateCollection;
    public final SimpleActor writeActor;
    public final SafeFlow internalDataFlow = new SafeFlow(new DataStoreImpl$internalDataFlow$1(this, null));
    public final ChannelFlowBuilder data = new ChannelFlowBuilder(new DataStoreImpl$data$1(this, null), EmptyCoroutineContext.INSTANCE, -2, BufferOverflow.SUSPEND);
    public final DataStoreInMemoryCache inMemoryCache = new DataStoreInMemoryCache();
    public final Lazy storageConnectionDelegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.datastore.core.DataStoreImpl$storageConnectionDelegate$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DataStoreImpl.this.storage.createConnection();
        }
    });
    public final Lazy coordinator$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.datastore.core.DataStoreImpl$coordinator$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ((StorageConnection) DataStoreImpl.this.storageConnectionDelegate.getValue()).getCoordinator();
        }
    });

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InitDataStore extends RunOnce {
        public List initTasks;

        public InitDataStore(List list) {
            this.initTasks = CollectionsKt.toList(list);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
        @Override // androidx.datastore.core.RunOnce
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object doRun(kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
            /*
                r6 = this;
                boolean r0 = r7 instanceof androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$1
                if (r0 == 0) goto L13
                r0 = r7
                androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$1 r0 = (androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$1 r0 = new androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$1
                r0.<init>(r6, r7)
            L18:
                java.lang.Object r7 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L3e
                if (r2 == r4) goto L36
                if (r2 != r3) goto L2e
                java.lang.Object r6 = r0.L$0
                androidx.datastore.core.DataStoreImpl$InitDataStore r6 = (androidx.datastore.core.DataStoreImpl.InitDataStore) r6
                kotlin.ResultKt.throwOnFailure(r7)
                goto L63
            L2e:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L36:
                java.lang.Object r6 = r0.L$0
                androidx.datastore.core.DataStoreImpl$InitDataStore r6 = (androidx.datastore.core.DataStoreImpl.InitDataStore) r6
                kotlin.ResultKt.throwOnFailure(r7)
                goto L72
            L3e:
                kotlin.ResultKt.throwOnFailure(r7)
                java.util.List r7 = r6.initTasks
                androidx.datastore.core.DataStoreImpl r2 = androidx.datastore.core.DataStoreImpl.this
                if (r7 == 0) goto L66
                boolean r7 = r7.isEmpty()
                if (r7 == 0) goto L4e
                goto L66
            L4e:
                androidx.datastore.core.SingleProcessCoordinator r7 = r2.getCoordinator()
                androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1 r4 = new androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1
                r5 = 0
                r4.<init>(r2, r6, r5)
                r0.L$0 = r6
                r0.label = r3
                java.lang.Object r7 = r7.lock(r4, r0)
                if (r7 != r1) goto L63
                return r1
            L63:
                androidx.datastore.core.Data r7 = (androidx.datastore.core.Data) r7
                goto L74
            L66:
                r0.L$0 = r6
                r0.label = r4
                r7 = 0
                java.lang.Object r7 = androidx.datastore.core.DataStoreImpl.access$readDataOrHandleCorruption(r2, r7, r0)
                if (r7 != r1) goto L72
                return r1
            L72:
                androidx.datastore.core.Data r7 = (androidx.datastore.core.Data) r7
            L74:
                androidx.datastore.core.DataStoreImpl r6 = androidx.datastore.core.DataStoreImpl.this
                androidx.datastore.core.DataStoreInMemoryCache r6 = r6.inMemoryCache
                r6.tryUpdate(r7)
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.InitDataStore.doRun(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
        }
    }

    public DataStoreImpl(Storage storage, List list, NoOpCorruptionHandler noOpCorruptionHandler, CoroutineScope coroutineScope) {
        this.storage = storage;
        this.corruptionHandler = noOpCorruptionHandler;
        this.scope = coroutineScope;
        this.updateCollection = FlowKt.shareIn(new SafeFlow(new DataStoreImpl$updateCollection$1(this, null)), coroutineScope, new StartedWhileSubscribed(Duration.m1777getInWholeMillisecondsimpl(0L), Duration.m1777getInWholeMillisecondsimpl(0L)), 0);
        this.readAndInit = new InitDataStore(list);
        this.writeActor = new SimpleActor(coroutineScope, new Function1() { // from class: androidx.datastore.core.DataStoreImpl$writeActor$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Throwable th = (Throwable) obj;
                if (th != null) {
                    DataStoreImpl.this.inMemoryCache.tryUpdate(new Final(th));
                }
                if (DataStoreImpl.this.storageConnectionDelegate.isInitialized()) {
                    ((StorageConnection) DataStoreImpl.this.storageConnectionDelegate.getValue()).close();
                }
                return Unit.INSTANCE;
            }
        }, new Function2() { // from class: androidx.datastore.core.DataStoreImpl$writeActor$2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Message$Update message$Update = (Message$Update) obj;
                Throwable th = (Throwable) obj2;
                if (th == null) {
                    th = new CancellationException("DataStore scope was cancelled before updateData could complete");
                }
                message$Update.ack.makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(new CompletedExceptionally(th, false));
                return Unit.INSTANCE;
            }
        }, new DataStoreImpl$writeActor$3(this, null));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:0|1|(2:3|(12:5|6|(7:57|(1:(1:(1:61)(2:63|64))(3:65|66|67))(1:68)|62|17|(1:19)(1:23)|20|21)(4:8|9|10|(9:12|13|14|(1:24)|16|17|(0)(0)|20|21)(3:30|(1:32)(1:55)|(2:34|(2:36|(2:38|39))(2:47|48))(2:49|(2:51|52)(2:53|54))))|40|41|42|(1:44)|16|17|(0)(0)|20|21))|70|6|(0)(0)|40|41|42|(0)|16|17|(0)(0)|20|21|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00c5, code lost:
    
        r9 = th;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0059  */
    /* JADX WARN: Type inference failed for: r9v13 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$handleUpdate(androidx.datastore.core.DataStoreImpl r9, androidx.datastore.core.Message$Update r10, kotlin.coroutines.jvm.internal.ContinuationImpl r11) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.access$handleUpdate(androidx.datastore.core.DataStoreImpl, androidx.datastore.core.Message$Update, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$readDataAndUpdateCache(androidx.datastore.core.DataStoreImpl r8, boolean r9, kotlin.coroutines.Continuation r10) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.access$readDataAndUpdateCache(androidx.datastore.core.DataStoreImpl, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ec A[Catch: CorruptionException -> 0x00ad, TryCatch #0 {CorruptionException -> 0x00ad, blocks: (B:36:0x00a8, B:37:0x0146, B:41:0x00b6, B:42:0x0129, B:58:0x00d3, B:60:0x00ec, B:61:0x00f0, B:67:0x00dc, B:71:0x0117), top: B:7:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$readDataOrHandleCorruption(androidx.datastore.core.DataStoreImpl r8, boolean r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.access$readDataOrHandleCorruption(androidx.datastore.core.DataStoreImpl, boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final SingleProcessCoordinator getCoordinator() {
        return (SingleProcessCoordinator) this.coordinator$delegate.getValue();
    }

    @Override // androidx.datastore.core.DataStore
    public final Flow getData() {
        return this.data;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readAndInitOrPropagateAndThrowFailure(kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1
            if (r0 == 0) goto L13
            r0 = r7
            androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 r0 = (androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 r0 = new androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 == r4) goto L3a
            if (r2 != r3) goto L32
            int r6 = r0.I$0
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.DataStoreImpl r0 = (androidx.datastore.core.DataStoreImpl) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L30
            goto L69
        L30:
            r7 = move-exception
            goto L73
        L32:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3a:
            java.lang.Object r6 = r0.L$0
            androidx.datastore.core.DataStoreImpl r6 = (androidx.datastore.core.DataStoreImpl) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L54
        L42:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.datastore.core.SingleProcessCoordinator r7 = r6.getCoordinator()
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = r7.getVersion()
            if (r7 != r1) goto L54
            return r1
        L54:
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            androidx.datastore.core.DataStoreImpl$InitDataStore r2 = r6.readAndInit     // Catch: java.lang.Throwable -> L71
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L71
            r0.I$0 = r7     // Catch: java.lang.Throwable -> L71
            r0.label = r3     // Catch: java.lang.Throwable -> L71
            java.lang.Object r6 = r2.runIfNeeded(r0)     // Catch: java.lang.Throwable -> L71
            if (r6 != r1) goto L69
            return r1
        L69:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L6c:
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
            goto L73
        L71:
            r0 = move-exception
            goto L6c
        L73:
            androidx.datastore.core.DataStoreInMemoryCache r0 = r0.inMemoryCache
            androidx.datastore.core.ReadException r1 = new androidx.datastore.core.ReadException
            r1.<init>(r7, r6)
            r0.tryUpdate(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.readAndInitOrPropagateAndThrowFailure(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object readDataFromFileOrDefault(ContinuationImpl continuationImpl) {
        return ((StorageConnection) this.storageConnectionDelegate.getValue()).readScope(new StorageConnectionKt$readData$2(3, null), continuationImpl);
    }

    @Override // androidx.datastore.core.DataStore
    public final Object updateData(Continuation continuation, Function2 function2) {
        UpdatingDataContextElement updatingDataContextElement = (UpdatingDataContextElement) continuation.getContext().get(UpdatingDataContextElement$Companion$Key.INSTANCE);
        if (updatingDataContextElement != null) {
            updatingDataContextElement.checkNotUpdating(this);
        }
        return BuildersKt.withContext(new UpdatingDataContextElement(updatingDataContextElement, this), new DataStoreImpl$updateData$2(this, function2, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeData$datastore_core_release(java.lang.Object r12, boolean r13, kotlin.coroutines.jvm.internal.ContinuationImpl r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof androidx.datastore.core.DataStoreImpl$writeData$1
            if (r0 == 0) goto L13
            r0 = r14
            androidx.datastore.core.DataStoreImpl$writeData$1 r0 = (androidx.datastore.core.DataStoreImpl$writeData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.datastore.core.DataStoreImpl$writeData$1 r0 = new androidx.datastore.core.DataStoreImpl$writeData$1
            r0.<init>(r11, r14)
        L18:
            java.lang.Object r14 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r11 = r0.L$0
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref$IntRef) r11
            kotlin.ResultKt.throwOnFailure(r14)
            goto L5a
        L2b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L33:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$IntRef r14 = new kotlin.jvm.internal.Ref$IntRef
            r14.<init>()
            kotlin.Lazy r2 = r11.storageConnectionDelegate
            java.lang.Object r2 = r2.getValue()
            androidx.datastore.core.StorageConnection r2 = (androidx.datastore.core.StorageConnection) r2
            androidx.datastore.core.DataStoreImpl$writeData$2 r10 = new androidx.datastore.core.DataStoreImpl$writeData$2
            r9 = 0
            r4 = r10
            r5 = r14
            r6 = r11
            r7 = r12
            r8 = r13
            r4.<init>(r5, r6, r7, r8, r9)
            r0.L$0 = r14
            r0.label = r3
            java.lang.Object r11 = r2.writeScope(r10, r0)
            if (r11 != r1) goto L59
            return r1
        L59:
            r11 = r14
        L5a:
            int r11 = r11.element
            java.lang.Integer r12 = new java.lang.Integer
            r12.<init>(r11)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.writeData$datastore_core_release(java.lang.Object, boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}

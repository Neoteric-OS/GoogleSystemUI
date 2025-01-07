package com.android.systemui.qs.pipeline.data.repository;

import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger;
import com.android.systemui.util.settings.SecureSettings;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserAutoAddRepository {
    public StateFlow _autoAdded;
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher bgDispatcher;
    public final SharedFlowImpl changeEvents = SharedFlowKt.MutableSharedFlow$default(0, 10, null, 5);
    public final QSPipelineLogger logger;
    public final SecureSettings secureSettings;
    public final int userId;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ChangeAction {
        Set apply(Set set);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MarkTile implements ChangeAction {
        public final TileSpec tileSpec;

        public MarkTile(TileSpec tileSpec) {
            this.tileSpec = tileSpec;
        }

        @Override // com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository.ChangeAction
        public final Set apply(Set set) {
            Set mutableSet = CollectionsKt.toMutableSet(set);
            mutableSet.add(this.tileSpec);
            return mutableSet;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MarkTile) && Intrinsics.areEqual(this.tileSpec, ((MarkTile) obj).tileSpec);
        }

        public final int hashCode() {
            return this.tileSpec.hashCode();
        }

        public final String toString() {
            return "MarkTile(tileSpec=" + this.tileSpec + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RestoreTiles implements ChangeAction {
        public final RestoreData restoredData;

        public RestoreTiles(RestoreData restoreData) {
            this.restoredData = restoreData;
        }

        @Override // com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository.ChangeAction
        public final Set apply(Set set) {
            return SetsKt.plus(set, (Iterable) this.restoredData.restoredAutoAddedTiles);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof RestoreTiles) && Intrinsics.areEqual(this.restoredData, ((RestoreTiles) obj).restoredData);
        }

        public final int hashCode() {
            return this.restoredData.hashCode();
        }

        public final String toString() {
            return "RestoreTiles(restoredData=" + this.restoredData + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UnmarkTile implements ChangeAction {
        public final TileSpec tileSpec;

        public UnmarkTile(TileSpec tileSpec) {
            this.tileSpec = tileSpec;
        }

        @Override // com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository.ChangeAction
        public final Set apply(Set set) {
            Set mutableSet = CollectionsKt.toMutableSet(set);
            mutableSet.remove(this.tileSpec);
            return mutableSet;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof UnmarkTile) && Intrinsics.areEqual(this.tileSpec, ((UnmarkTile) obj).tileSpec);
        }

        public final int hashCode() {
            return this.tileSpec.hashCode();
        }

        public final String toString() {
            return "UnmarkTile(tileSpec=" + this.tileSpec + ")";
        }
    }

    public UserAutoAddRepository(int i, SecureSettings secureSettings, QSPipelineLogger qSPipelineLogger, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.userId = i;
        this.secureSettings = secureSettings;
        this.logger = qSPipelineLogger;
        this.applicationScope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
    }

    public static final Object access$store(UserAutoAddRepository userAutoAddRepository, Set set, Continuation continuation) {
        userAutoAddRepository.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : set) {
            if (!(((TileSpec) obj) instanceof TileSpec.Invalid)) {
                arrayList.add(obj);
            }
        }
        Object withContext = BuildersKt.withContext(userAutoAddRepository.bgDispatcher, new UserAutoAddRepository$store$2(userAutoAddRepository, CollectionsKt.joinToString$default(arrayList, ",", null, null, new PropertyReference1Impl() { // from class: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$store$toStore$2
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public final Object get(Object obj2) {
                return ((TileSpec) obj2).getSpec();
            }
        }, 30), null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0098 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object autoAdded(kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$autoAdded$1
            if (r0 == 0) goto L13
            r0 = r10
            com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$autoAdded$1 r0 = (com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$autoAdded$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$autoAdded$1 r0 = new com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$autoAdded$1
            r0.<init>(r9, r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L4b
            if (r2 == r5) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r9 = r0.L$1
            com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository r9 = (com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository) r9
            java.lang.Object r0 = r0.L$0
            com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository r0 = (com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L9b
        L33:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3b:
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.flow.Flow r9 = (kotlinx.coroutines.flow.Flow) r9
            java.lang.Object r2 = r0.L$1
            com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository r2 = (com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository) r2
            java.lang.Object r5 = r0.L$0
            com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository r5 = (com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository) r5
            kotlin.ResultKt.throwOnFailure(r10)
            goto L6e
        L4b:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.flow.StateFlow r10 = r9._autoAdded
            if (r10 != 0) goto Laf
            kotlinx.coroutines.flow.SharedFlowImpl r10 = r9.changeEvents
            r0.L$0 = r9
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r5
            com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$load$2 r2 = new com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$load$2
            r2.<init>(r9, r4)
            kotlinx.coroutines.CoroutineDispatcher r5 = r9.bgDispatcher
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r5, r2, r0)
            if (r2 != r1) goto L6a
            return r1
        L6a:
            r5 = r9
            r9 = r10
            r10 = r2
            r2 = r5
        L6e:
            r6 = r10
            java.util.Set r6 = (java.util.Set) r6
            com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger r7 = r5.logger
            int r8 = r5.userId
            r7.logAutoAddTilesParsed(r8, r6)
            com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$autoAdded$3 r6 = new com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$autoAdded$3
            r6.<init>(r5, r4)
            kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 r7 = new kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1
            r7.<init>(r10, r6, r9)
            kotlinx.coroutines.CoroutineDispatcher r9 = r5.bgDispatcher
            kotlinx.coroutines.flow.Flow r9 = kotlinx.coroutines.flow.FlowKt.flowOn(r7, r9)
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r4
            r0.label = r3
            kotlinx.coroutines.CoroutineScope r10 = r5.applicationScope
            java.lang.Object r10 = kotlinx.coroutines.flow.FlowKt.stateIn(r9, r10, r0)
            if (r10 != r1) goto L99
            return r1
        L99:
            r9 = r2
            r0 = r5
        L9b:
            kotlinx.coroutines.flow.StateFlow r10 = (kotlinx.coroutines.flow.StateFlow) r10
            r0.getClass()
            com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1 r1 = new com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1
            r1.<init>(r0, r4, r10)
            kotlinx.coroutines.CoroutineDispatcher r2 = r0.bgDispatcher
            kotlinx.coroutines.CoroutineScope r5 = r0.applicationScope
            kotlinx.coroutines.BuildersKt.launch$default(r5, r2, r4, r1, r3)
            r9._autoAdded = r10
            r9 = r0
        Laf:
            kotlinx.coroutines.flow.StateFlow r9 = r9._autoAdded
            if (r9 != 0) goto Lb4
            goto Lb5
        Lb4:
            r4 = r9
        Lb5:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository.autoAdded(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}

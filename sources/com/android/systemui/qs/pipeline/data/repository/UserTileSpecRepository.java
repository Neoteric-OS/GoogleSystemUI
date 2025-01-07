package com.android.systemui.qs.pipeline.data.repository;

import android.content.res.Resources;
import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
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
public final class UserTileSpecRepository {
    public static final Companion Companion = new Companion();
    public StateFlow _tiles;
    public final CoroutineScope applicationScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final SharedFlowImpl changeEvents = SharedFlowKt.MutableSharedFlow$default(0, 10, null, 5);
    public final DefaultTilesQSHostRepository defaultTilesRepository;
    public final QSPipelineLogger logger;
    public final SecureSettings secureSettings;
    public final int userId;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AddTile implements ChangeAction {
        public final int position;
        public final TileSpec tileSpec;

        public AddTile(int i, TileSpec tileSpec) {
            this.tileSpec = tileSpec;
            this.position = i;
        }

        @Override // com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository.ChangeAction
        public final List apply(List list) {
            ArrayList arrayList = new ArrayList(list);
            TileSpec tileSpec = this.tileSpec;
            if (!arrayList.contains(tileSpec)) {
                int i = this.position;
                if (i < 0 || i >= arrayList.size()) {
                    arrayList.add(tileSpec);
                } else {
                    arrayList.add(i, tileSpec);
                }
            }
            return arrayList;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AddTile)) {
                return false;
            }
            AddTile addTile = (AddTile) obj;
            return Intrinsics.areEqual(this.tileSpec, addTile.tileSpec) && this.position == addTile.position;
        }

        public final int hashCode() {
            return Integer.hashCode(this.position) + (this.tileSpec.hashCode() * 31);
        }

        public final String toString() {
            return "AddTile(tileSpec=" + this.tileSpec + ", position=" + this.position + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ChangeAction {
        List apply(List list);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ChangeTiles implements ChangeAction {
        public final List newTiles;

        public ChangeTiles(List list) {
            this.newTiles = list;
        }

        @Override // com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository.ChangeAction
        public final List apply(List list) {
            List list2 = this.newTiles;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list2) {
                if (!(((TileSpec) obj) instanceof TileSpec.Invalid)) {
                    arrayList.add(obj);
                }
            }
            return !arrayList.isEmpty() ? arrayList : list;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ChangeTiles) && Intrinsics.areEqual(this.newTiles, ((ChangeTiles) obj).newTiles);
        }

        public final int hashCode() {
            return this.newTiles.hashCode();
        }

        public final String toString() {
            return "ChangeTiles(newTiles=" + this.newTiles + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PrependDefault implements ChangeAction {
        public final List defaultTiles;

        public PrependDefault(List list) {
            this.defaultTiles = list;
        }

        @Override // com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository.ChangeAction
        public final List apply(List list) {
            return CollectionsKt.plus((Iterable) list, (Collection) this.defaultTiles);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof PrependDefault) && Intrinsics.areEqual(this.defaultTiles, ((PrependDefault) obj).defaultTiles);
        }

        public final int hashCode() {
            return this.defaultTiles.hashCode();
        }

        public final String toString() {
            return "PrependDefault(defaultTiles=" + this.defaultTiles + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RemoveTiles implements ChangeAction {
        public final Collection tileSpecs;

        public RemoveTiles(Collection collection) {
            this.tileSpecs = collection;
        }

        @Override // com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository.ChangeAction
        public final List apply(List list) {
            ArrayList arrayList = new ArrayList(list);
            arrayList.removeAll(this.tileSpecs);
            return arrayList;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof RemoveTiles) && Intrinsics.areEqual(this.tileSpecs, ((RemoveTiles) obj).tileSpecs);
        }

        public final int hashCode() {
            return this.tileSpecs.hashCode();
        }

        public final String toString() {
            return "RemoveTiles(tileSpecs=" + this.tileSpecs + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RestoreTiles implements ChangeAction {
        public final Set currentAutoAdded;
        public final RestoreData restoreData;

        public RestoreTiles(RestoreData restoreData, Set set) {
            this.restoreData = restoreData;
            this.currentAutoAdded = set;
        }

        @Override // com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository.ChangeAction
        public final List apply(List list) {
            Set set = this.currentAutoAdded;
            RestoreData restoreData = this.restoreData;
            ArrayList arrayList = new ArrayList(restoreData.restoredTiles);
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : set) {
                if (!restoreData.restoredAutoAddedTiles.contains((TileSpec) obj)) {
                    arrayList2.add(obj);
                }
            }
            ArrayList<TileSpec> arrayList3 = new ArrayList();
            for (Object obj2 : arrayList2) {
                TileSpec tileSpec = (TileSpec) obj2;
                if (list.contains(tileSpec) && !restoreData.restoredTiles.contains(tileSpec)) {
                    arrayList3.add(obj2);
                }
            }
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList3, 10));
            for (TileSpec tileSpec2 : arrayList3) {
                arrayList4.add(new Pair(tileSpec2, Integer.valueOf(list.indexOf(tileSpec2))));
            }
            int i = 0;
            for (Object obj3 : CollectionsKt.sortedWith(arrayList4, new UserTileSpecRepository$Companion$reconcileTiles$$inlined$sortedBy$1())) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                Pair pair = (Pair) obj3;
                TileSpec tileSpec3 = (TileSpec) pair.component1();
                int intValue = ((Number) pair.component2()).intValue() + i;
                if (intValue > arrayList.size()) {
                    arrayList.add(tileSpec3);
                } else {
                    arrayList.add(intValue, tileSpec3);
                }
                i = i2;
            }
            return arrayList;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RestoreTiles)) {
                return false;
            }
            RestoreTiles restoreTiles = (RestoreTiles) obj;
            return Intrinsics.areEqual(this.restoreData, restoreTiles.restoreData) && Intrinsics.areEqual(this.currentAutoAdded, restoreTiles.currentAutoAdded);
        }

        public final int hashCode() {
            return this.currentAutoAdded.hashCode() + (this.restoreData.hashCode() * 31);
        }

        public final String toString() {
            return "RestoreTiles(restoreData=" + this.restoreData + ", currentAutoAdded=" + this.currentAutoAdded + ")";
        }
    }

    public UserTileSpecRepository(int i, DefaultTilesQSHostRepository defaultTilesQSHostRepository, SecureSettings secureSettings, QSPipelineLogger qSPipelineLogger, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.userId = i;
        this.defaultTilesRepository = defaultTilesQSHostRepository;
        this.secureSettings = secureSettings;
        this.logger = qSPipelineLogger;
        this.applicationScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    public static final Object access$storeTiles(UserTileSpecRepository userTileSpecRepository, int i, List list, Continuation continuation) {
        userTileSpecRepository.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!(((TileSpec) obj) instanceof TileSpec.Invalid)) {
                arrayList.add(obj);
            }
        }
        Object withContext = BuildersKt.withContext(userTileSpecRepository.backgroundDispatcher, new UserTileSpecRepository$storeTiles$2(userTileSpecRepository, CollectionsKt.joinToString$default(arrayList, ",", null, null, new PropertyReference1Impl() { // from class: com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$storeTiles$toStore$2
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public final Object get(Object obj2) {
                return ((TileSpec) obj2).getSpec();
            }
        }, 30), i, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    public final List getDefaultTiles() {
        Resources resources = this.defaultTilesRepository.resources;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(resources.getString(R.string.quick_settings_tiles_default).split(",")));
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(TileSpec.Companion.create((String) it.next()));
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList2) {
            if (!Intrinsics.areEqual((TileSpec) obj, TileSpec.Invalid.INSTANCE)) {
                arrayList3.add(obj);
            }
        }
        return arrayList3;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadTilesFromSettings(int r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettings$1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettings$1 r0 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettings$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettings$1 r0 = new com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettings$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r6 = r0.L$0
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$Companion r6 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository.Companion) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4e
        L2b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L33:
            kotlin.ResultKt.throwOnFailure(r8)
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$Companion r8 = com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository.Companion
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettings$2 r2 = new com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettings$2
            r4 = 0
            r2.<init>(r6, r7, r4)
            r0.L$0 = r8
            r0.label = r3
            kotlinx.coroutines.CoroutineDispatcher r6 = r6.backgroundDispatcher
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L4b
            return r1
        L4b:
            r5 = r8
            r8 = r6
            r6 = r5
        L4e:
            java.lang.String r8 = (java.lang.String) r8
            r6.getClass()
            java.util.List r6 = com.android.systemui.qs.pipeline.data.repository.TilesSettingConverter.toTilesList(r8)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository.loadTilesFromSettings(int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadTilesFromSettingsAndParse(int r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettingsAndParse$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettingsAndParse$1 r0 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettingsAndParse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettingsAndParse$1 r0 = new com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$loadTilesFromSettingsAndParse$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            int r5 = r0.I$0
            java.lang.Object r4 = r0.L$0
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository r4 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L45
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.I$0 = r5
            r0.label = r3
            java.lang.Object r6 = r4.loadTilesFromSettings(r5, r0)
            if (r6 != r1) goto L45
            return r1
        L45:
            java.util.List r6 = (java.util.List) r6
            r4.getClass()
            boolean r0 = r6.isEmpty()
            com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger r1 = r4.logger
            if (r0 != 0) goto L57
            r4 = 0
            r1.logParsedTiles(r5, r6, r4)
            goto L5e
        L57:
            java.util.List r6 = r4.getDefaultTiles()
            r1.logParsedTiles(r5, r6, r3)
        L5e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository.loadTilesFromSettingsAndParse(int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tiles(kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$tiles$1
            if (r0 == 0) goto L13
            r0 = r9
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$tiles$1 r0 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$tiles$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$tiles$1 r0 = new com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$tiles$1
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L4b
            if (r2 == r5) goto L3b
            if (r2 != r4) goto L33
            java.lang.Object r8 = r0.L$1
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository r8 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository) r8
            java.lang.Object r0 = r0.L$0
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository r0 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L8c
        L33:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3b:
            java.lang.Object r8 = r0.L$2
            kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
            java.lang.Object r2 = r0.L$1
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository r2 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository) r2
            java.lang.Object r5 = r0.L$0
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository r5 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L69
        L4b:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.flow.StateFlow r9 = r8._tiles
            if (r9 != 0) goto La0
            kotlinx.coroutines.flow.SharedFlowImpl r9 = r8.changeEvents
            r0.L$0 = r8
            r0.L$1 = r8
            r0.L$2 = r9
            r0.label = r5
            int r2 = r8.userId
            java.lang.Object r2 = r8.loadTilesFromSettingsAndParse(r2, r0)
            if (r2 != r1) goto L65
            return r1
        L65:
            r5 = r8
            r8 = r9
            r9 = r2
            r2 = r5
        L69:
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$tiles$2 r6 = new com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$tiles$2
            r6.<init>(r5, r3)
            kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1 r7 = new kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1
            r7.<init>(r9, r6, r8)
            kotlinx.coroutines.CoroutineDispatcher r8 = r5.backgroundDispatcher
            kotlinx.coroutines.flow.Flow r8 = kotlinx.coroutines.flow.FlowKt.flowOn(r7, r8)
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r3
            r0.label = r4
            kotlinx.coroutines.CoroutineScope r9 = r5.applicationScope
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.stateIn(r8, r9, r0)
            if (r9 != r1) goto L8a
            return r1
        L8a:
            r8 = r2
            r0 = r5
        L8c:
            kotlinx.coroutines.flow.StateFlow r9 = (kotlinx.coroutines.flow.StateFlow) r9
            r0.getClass()
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$startFlowCollections$1 r1 = new com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$startFlowCollections$1
            r1.<init>(r0, r3, r9)
            kotlinx.coroutines.CoroutineDispatcher r2 = r0.backgroundDispatcher
            kotlinx.coroutines.CoroutineScope r5 = r0.applicationScope
            kotlinx.coroutines.BuildersKt.launch$default(r5, r2, r3, r1, r4)
            r8._tiles = r9
            r8 = r0
        La0:
            kotlinx.coroutines.flow.StateFlow r8 = r8._tiles
            if (r8 != 0) goto La5
            goto La6
        La5:
            r3 = r8
        La6:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository.tiles(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}

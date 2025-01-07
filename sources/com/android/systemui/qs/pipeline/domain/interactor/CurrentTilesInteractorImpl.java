package com.android.systemui.qs.pipeline.domain.interactor;

import android.content.ComponentName;
import android.content.Intent;
import android.os.UserHandle;
import com.android.systemui.Dumpable;
import com.android.systemui.plugins.qs.QSFactory;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.external.CustomTileStatePersisterImpl;
import com.android.systemui.qs.external.TileLifecycleManager;
import com.android.systemui.qs.external.TileLifecycleManager$$ExternalSyntheticLambda0;
import com.android.systemui.qs.pipeline.data.repository.CustomTileAddedRepository;
import com.android.systemui.qs.pipeline.data.repository.CustomTileAddedSharedPrefsRepository;
import com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepository;
import com.android.systemui.qs.pipeline.data.repository.MinimumTilesResourceRepository;
import com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository;
import com.android.systemui.qs.pipeline.domain.model.TileModel;
import com.android.systemui.qs.pipeline.shared.QSPipelineFlagsRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger;
import com.android.systemui.retail.data.repository.RetailModeSettingsRepository;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CurrentTilesInteractorImpl implements CurrentTilesInteractor {
    public final StateFlowImpl _currentSpecsAndTiles;
    public final StateFlowImpl _userContext;
    public final CoroutineDispatcher backgroundDispatcher;
    public final ReadonlyStateFlow currentTiles;
    public final StateFlowImpl currentUser;
    public final CustomTileAddedRepository customTileAddedRepository;
    public final CustomTileStatePersisterImpl customTileStatePersister;
    public final QSPipelineFlagsRepository featureFlags;
    public final InstalledTilesComponentRepository installedTilesComponentRepository;
    public final QSPipelineLogger logger;
    public final CoroutineDispatcher mainDispatcher;
    public final MinimumTilesResourceRepository minimumTilesRepository;
    public final RetailModeSettingsRepository retailModeRepository;
    public final CoroutineScope scope;
    public final Map specsToTiles;
    public final QSFactory tileFactory;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52 tileLifecycleManagerFactory;
    public final TileSpecSettingsRepository tileSpecRepository;
    public final Flow userAndTiles;
    public final ReadonlyStateFlow userContext;
    public final ReadonlyStateFlow userId;
    public final UserRepositoryImpl userRepository;
    public final UserTracker userTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TileOrNotInstalled {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class NotInstalled implements TileOrNotInstalled {
            public static final NotInstalled INSTANCE = new NotInstalled();
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Tile implements TileOrNotInstalled {
            public final QSTile tile;

            public final boolean equals(Object obj) {
                if (obj instanceof Tile) {
                    return Intrinsics.areEqual(this.tile, ((Tile) obj).tile);
                }
                return false;
            }

            public final int hashCode() {
                return this.tile.hashCode();
            }

            public final String toString() {
                return "Tile(tile=" + this.tile + ")";
            }
        }
    }

    public CurrentTilesInteractorImpl(TileSpecSettingsRepository tileSpecSettingsRepository, InstalledTilesComponentRepository installedTilesComponentRepository, UserRepositoryImpl userRepositoryImpl, MinimumTilesResourceRepository minimumTilesResourceRepository, RetailModeSettingsRepository retailModeSettingsRepository, CustomTileStatePersisterImpl customTileStatePersisterImpl, Lazy lazy, QSFactory qSFactory, CustomTileAddedRepository customTileAddedRepository, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52, UserTracker userTracker, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, CoroutineScope coroutineScope, QSPipelineLogger qSPipelineLogger, QSPipelineFlagsRepository qSPipelineFlagsRepository) {
        this.tileSpecRepository = tileSpecSettingsRepository;
        this.installedTilesComponentRepository = installedTilesComponentRepository;
        this.userRepository = userRepositoryImpl;
        this.minimumTilesRepository = minimumTilesResourceRepository;
        this.retailModeRepository = retailModeSettingsRepository;
        this.customTileStatePersister = customTileStatePersisterImpl;
        this.tileFactory = qSFactory;
        this.customTileAddedRepository = customTileAddedRepository;
        this.tileLifecycleManagerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$52;
        this.userTracker = userTracker;
        this.mainDispatcher = coroutineDispatcher;
        this.backgroundDispatcher = coroutineDispatcher2;
        this.scope = coroutineScope;
        this.logger = qSPipelineLogger;
        this.featureFlags = qSPipelineFlagsRepository;
        EmptyList emptyList = EmptyList.INSTANCE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(emptyList);
        this._currentSpecsAndTiles = MutableStateFlow;
        this.currentTiles = new ReadonlyStateFlow(MutableStateFlow);
        this.specsToTiles = new LinkedHashMap();
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) userTracker;
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(Integer.valueOf(userTrackerImpl.getUserId()));
        this.currentUser = MutableStateFlow2;
        this.userId = new ReadonlyStateFlow(MutableStateFlow2);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(userTrackerImpl.getUserContext());
        this._userContext = MutableStateFlow3;
        this.userContext = new ReadonlyStateFlow(MutableStateFlow3);
        this.userAndTiles = FlowKt.flowOn(com.android.systemui.util.kotlin.FlowKt.pairwiseBy(new UserTilesAndComponents(-1, emptyList, EmptySet.INSTANCE), new CurrentTilesInteractorImpl$userAndTiles$2(3, null), FlowKt.distinctUntilChanged(FlowKt.transformLatest(MutableStateFlow2, new CurrentTilesInteractorImpl$special$$inlined$flatMapLatest$1(this, null)))), coroutineDispatcher2);
        BuildersKt.launch$default(coroutineScope, null, null, new CurrentTilesInteractorImpl$startTileCollection$1(this, null), 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$createTile(com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl r5, com.android.systemui.qs.pipeline.shared.TileSpec r6, kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r5.getClass()
            boolean r0 = r7 instanceof com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$createTile$1
            if (r0 == 0) goto L16
            r0 = r7
            com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$createTile$1 r0 = (com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$createTile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$createTile$1 r0 = new com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$createTile$1
            r0.<init>(r5, r7)
        L1b:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r5 = r0.L$1
            r6 = r5
            com.android.systemui.qs.pipeline.shared.TileSpec r6 = (com.android.systemui.qs.pipeline.shared.TileSpec) r6
            java.lang.Object r5 = r0.L$0
            com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl r5 = (com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L53
        L34:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3c:
            kotlin.ResultKt.throwOnFailure(r7)
            com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$createTile$tile$1 r7 = new com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$createTile$tile$1
            r7.<init>(r5, r6, r4)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            kotlinx.coroutines.CoroutineDispatcher r2 = r5.mainDispatcher
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r2, r7, r0)
            if (r7 != r1) goto L53
            goto L75
        L53:
            com.android.systemui.plugins.qs.QSTile r7 = (com.android.systemui.plugins.qs.QSTile) r7
            if (r7 != 0) goto L5e
            com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger r5 = r5.logger
            r5.logTileNotFoundInFactory(r6)
        L5c:
            r1 = r4
            goto L75
        L5e:
            boolean r0 = r7.isAvailable()
            if (r0 != 0) goto L6f
            com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger r5 = r5.logger
            com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger$TileDestroyedReason r0 = com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger.TileDestroyedReason.NEW_TILE_NOT_AVAILABLE
            r5.logTileDestroyed(r6, r0)
            r7.destroy()
            goto L5c
        L6f:
            com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger r5 = r5.logger
            r5.logTileCreated(r6)
            r1 = r7
        L75:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl.access$createTile(com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl, com.android.systemui.qs.pipeline.shared.TileSpec, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void addTile(int i, TileSpec tileSpec) {
        BuildersKt.launch$default(this.scope, this.backgroundDispatcher, null, new CurrentTilesInteractorImpl$addTile$1(this, tileSpec, i, null), 2);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("CurrentTileInteractorImpl:");
        printWriter.println("User: " + this.userId.getValue());
        Iterable iterable = (Iterable) this.currentTiles.getValue();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileModel) it.next()).tile);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (obj instanceof Dumpable) {
                arrayList2.add(obj);
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            ((Dumpable) it2.next()).dump(printWriter, strArr);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dumpProto(com.android.systemui.dump.nano.SystemUIProtoDump r10) {
        /*
            r9 = this;
            kotlinx.coroutines.flow.ReadonlyStateFlow r9 = r9.currentTiles
            kotlinx.coroutines.flow.MutableStateFlow r9 = r9.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r9 = (kotlinx.coroutines.flow.StateFlowImpl) r9
            java.lang.Object r9 = r9.getValue()
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r9, r1)
            r0.<init>(r1)
            java.util.Iterator r9 = r9.iterator()
        L1b:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L31
            java.lang.Object r1 = r9.next()
            com.android.systemui.qs.pipeline.domain.model.TileModel r1 = (com.android.systemui.qs.pipeline.domain.model.TileModel) r1
            com.android.systemui.plugins.qs.QSTile r1 = r1.tile
            com.android.systemui.plugins.qs.QSTile$State r1 = r1.getState()
            r0.add(r1)
            goto L1b
        L31:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Iterator r0 = r0.iterator()
        L3a:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto Ldf
            java.lang.Object r1 = r0.next()
            com.android.systemui.plugins.qs.QSTile$State r1 = (com.android.systemui.plugins.qs.QSTile.State) r1
            r3 = 0
            if (r1 == 0) goto Ld8
            java.lang.String r4 = r1.spec
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L54
            goto Ld8
        L54:
            com.android.systemui.qs.nano.QsTileState r3 = new com.android.systemui.qs.nano.QsTileState
            r3.<init>()
            java.lang.String r4 = r1.spec
            java.lang.String r5 = "custom("
            boolean r4 = r4.startsWith(r5)
            r5 = 1
            if (r4 == 0) goto L97
            com.android.systemui.util.nano.ComponentNameProto r4 = new com.android.systemui.util.nano.ComponentNameProto
            r4.<init>()
            java.lang.String r6 = r1.spec
            int r7 = r6.length()
            int r7 = r7 - r5
            r8 = 7
            java.lang.String r6 = r6.substring(r8, r7)
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L8f
            android.content.ComponentName r6 = android.content.ComponentName.unflattenFromString(r6)
            java.lang.String r7 = r6.getPackageName()
            r4.packageName = r7
            java.lang.String r6 = r6.getClassName()
            r4.className = r6
            r3.setComponentName(r4)
            goto L9c
        L8f:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Empty custom tile spec action"
            r9.<init>(r10)
            throw r9
        L97:
            java.lang.String r4 = r1.spec
            r3.setSpec(r4)
        L9c:
            int r4 = r1.state
            r6 = 2
            if (r4 == 0) goto La5
            if (r4 == r5) goto La9
            if (r4 == r6) goto La7
        La5:
            r4 = r2
            goto Laa
        La7:
            r4 = r6
            goto Laa
        La9:
            r4 = r5
        Laa:
            r3.state = r4
            java.lang.CharSequence r4 = r1.label
            if (r4 == 0) goto Lb7
            java.lang.String r4 = r4.toString()
            r3.setLabel(r4)
        Lb7:
            java.lang.CharSequence r4 = r1.secondaryLabel
            if (r4 == 0) goto Lc2
            java.lang.String r4 = r4.toString()
            r3.setSecondaryLabel(r4)
        Lc2:
            java.lang.String r1 = r1.expandedAccessibilityClassName
            java.lang.Class<android.widget.Switch> r4 = android.widget.Switch.class
            java.lang.String r4 = r4.getName()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r4)
            if (r1 == 0) goto Ld8
            int r1 = r3.state
            if (r1 != r6) goto Ld5
            r2 = r5
        Ld5:
            r3.setBooleanState(r2)
        Ld8:
            if (r3 == 0) goto L3a
            r9.add(r3)
            goto L3a
        Ldf:
            com.android.systemui.qs.nano.QsTileState[] r0 = new com.android.systemui.qs.nano.QsTileState[r2]
            java.lang.Object[] r9 = r9.toArray(r0)
            com.android.systemui.qs.nano.QsTileState[] r9 = (com.android.systemui.qs.nano.QsTileState[]) r9
            r10.tiles = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl.dumpProto(com.android.systemui.dump.nano.SystemUIProtoDump):void");
    }

    public final void onCustomTileRemoved(int i, ComponentName componentName) {
        TileLifecycleManager create = this.tileLifecycleManagerFactory.create(new Intent().setComponent(componentName), UserHandle.of(i));
        create.onStopListening();
        create.onTileRemoved();
        this.customTileStatePersister.sharedPreferences.edit().remove(componentName.flattenToString() + ":" + i).apply();
        ((UserFileManagerImpl) ((CustomTileAddedSharedPrefsRepository) this.customTileAddedRepository).userFileManager).getSharedPreferences$1(i, "tiles_prefs").edit().putBoolean(componentName.flattenToString(), false).apply();
        ((ExecutorImpl) create.mExecutor).execute(new TileLifecycleManager$$ExternalSyntheticLambda0(create, 3));
    }

    public final void removeTiles(Collection collection) {
        Set set = CollectionsKt.toSet(getCurrentTilesSpecs());
        int intValue = ((Number) this.currentUser.getValue()).intValue();
        Set set2 = set;
        Collection collection2 = collection;
        Set intersect = CollectionsKt.intersect(set2, collection2);
        ArrayList arrayList = new ArrayList();
        for (Object obj : intersect) {
            if (obj instanceof TileSpec.CustomTileSpec) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            onCustomTileRemoved(intValue, ((TileSpec.CustomTileSpec) it.next()).componentName);
        }
        if (CollectionsKt.intersect(set2, collection2).isEmpty()) {
            return;
        }
        BuildersKt.launch$default(this.scope, null, null, new CurrentTilesInteractorImpl$removeTiles$2(this, intValue, collection, null), 3);
    }

    public final void setTiles(List list) {
        List currentTilesSpecs = getCurrentTilesSpecs();
        int intValue = ((Number) this.currentUser.getValue()).intValue();
        if (currentTilesSpecs.equals(list)) {
            return;
        }
        List minus = CollectionsKt.minus((Iterable) currentTilesSpecs, (Iterable) list);
        ArrayList arrayList = new ArrayList();
        for (Object obj : minus) {
            if (obj instanceof TileSpec.CustomTileSpec) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            onCustomTileRemoved(intValue, ((TileSpec.CustomTileSpec) it.next()).componentName);
        }
        BuildersKt.launch$default(this.scope, null, null, new CurrentTilesInteractorImpl$setTiles$2(this, intValue, list, null), 3);
    }
}

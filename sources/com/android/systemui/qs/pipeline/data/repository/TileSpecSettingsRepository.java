package com.android.systemui.qs.pipeline.data.repository;

import android.content.res.Resources;
import android.util.SparseArray;
import com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger;
import com.android.systemui.retail.data.repository.RetailModeSettingsRepository;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$45;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileSpecSettingsRepository {
    public final QSPipelineLogger logger;
    public final Resources resources;
    public final RetailModeSettingsRepository retailModeRepository;
    public final Lazy retailModeTiles$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository$retailModeTiles$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            List split$default = StringsKt.split$default(TileSpecSettingsRepository.this.resources.getString(R.string.quick_settings_tiles_retail_mode), new String[]{","}, 0, 6);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10));
            Iterator it = split$default.iterator();
            while (it.hasNext()) {
                arrayList.add(TileSpec.Companion.create((String) it.next()));
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (!(((TileSpec) obj) instanceof TileSpec.Invalid)) {
                    arrayList2.add(obj);
                }
            }
            return arrayList2;
        }
    });
    public final SparseArray userTileRepositories = new SparseArray();
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$45 userTileSpecRepositoryFactory;

    public TileSpecSettingsRepository(Resources resources, QSPipelineLogger qSPipelineLogger, RetailModeSettingsRepository retailModeSettingsRepository, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$45 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$45) {
        this.resources = resources;
        this.logger = qSPipelineLogger;
        this.retailModeRepository = retailModeSettingsRepository;
        this.userTileSpecRepositoryFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$45;
    }

    public final Object setTiles(int i, List list, SuspendLambda suspendLambda) {
        boolean inRetailMode = this.retailModeRepository.getInRetailMode();
        Unit unit = Unit.INSTANCE;
        if (inRetailMode) {
            return unit;
        }
        UserTileSpecRepository userTileSpecRepository = (UserTileSpecRepository) this.userTileRepositories.get(i);
        if (userTileSpecRepository != null) {
            Object emit = userTileSpecRepository.changeEvents.emit(new UserTileSpecRepository.ChangeTiles(list), suspendLambda);
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (emit != coroutineSingletons) {
                emit = unit;
            }
            if (emit == coroutineSingletons) {
                return emit;
            }
        }
        return unit;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tilesSpecs(int r12, kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository$tilesSpecs$1
            if (r0 == 0) goto L13
            r0 = r13
            com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository$tilesSpecs$1 r0 = (com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository$tilesSpecs$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository$tilesSpecs$1 r0 = new com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository$tilesSpecs$1
            r0.<init>(r11, r13)
        L18:
            java.lang.Object r13 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r11 = r0.L$0
            com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository r11 = (com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository) r11
            kotlin.ResultKt.throwOnFailure(r13)
            goto L93
        L2b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L33:
            kotlin.ResultKt.throwOnFailure(r13)
            android.util.SparseArray r13 = r11.userTileRepositories
            boolean r13 = r13.contains(r12)
            if (r13 != 0) goto L80
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository r13 = new com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$45 r2 = r11.userTileSpecRepositoryFactory
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r2 = r2.this$0
            java.lang.Object r4 = r2.wMComponentImpl
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r4 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) r4
            dagger.internal.Provider r4 = r4.defaultTilesQSHostRepositoryProvider
            java.lang.Object r4 = r4.get()
            r6 = r4
            com.android.systemui.qs.pipeline.data.repository.DefaultTilesQSHostRepository r6 = (com.android.systemui.qs.pipeline.data.repository.DefaultTilesQSHostRepository) r6
            java.lang.Object r4 = r2.wMComponentImpl
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl r4 = (com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) r4
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$SwitchingProvider r5 = r4.secureSettingsImplProvider
            java.lang.Object r5 = r5.get()
            r7 = r5
            com.android.systemui.util.settings.SecureSettings r7 = (com.android.systemui.util.settings.SecureSettings) r7
            com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger r8 = com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1649$$Nest$mqSPipelineLogger(r4)
            com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl r2 = r2.sysUIGoogleGlobalRootComponentImpl
            dagger.internal.Provider r2 = r2.applicationScopeProvider
            java.lang.Object r2 = r2.get()
            r9 = r2
            kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
            dagger.internal.Provider r2 = r4.bgDispatcherProvider
            java.lang.Object r2 = r2.get()
            r10 = r2
            kotlinx.coroutines.CoroutineDispatcher r10 = (kotlinx.coroutines.CoroutineDispatcher) r10
            r4 = r13
            r5 = r12
            r4.<init>(r5, r6, r7, r8, r9, r10)
            android.util.SparseArray r2 = r11.userTileRepositories
            r2.put(r12, r13)
        L80:
            android.util.SparseArray r13 = r11.userTileRepositories
            java.lang.Object r12 = r13.get(r12)
            com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository r12 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository) r12
            r0.L$0 = r11
            r0.label = r3
            java.lang.Object r13 = r12.tiles(r0)
            if (r13 != r1) goto L93
            return r1
        L93:
            kotlinx.coroutines.flow.Flow r13 = (kotlinx.coroutines.flow.Flow) r13
            com.android.systemui.retail.data.repository.RetailModeSettingsRepository r12 = r11.retailModeRepository
            kotlinx.coroutines.flow.ReadonlyStateFlow r12 = r12.retailMode
            com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository$tilesSpecs$$inlined$flatMapLatest$1 r0 = new com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository$tilesSpecs$$inlined$flatMapLatest$1
            r1 = 0
            r0.<init>(r1, r11, r13)
            kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest r11 = kotlinx.coroutines.flow.FlowKt.transformLatest(r12, r0)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository.tilesSpecs(int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}

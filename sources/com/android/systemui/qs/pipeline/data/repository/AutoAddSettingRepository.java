package com.android.systemui.qs.pipeline.data.repository;

import android.util.SparseArray;
import com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$79;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AutoAddSettingRepository {
    public final SparseArray userAutoAddRepositories = new SparseArray();
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$79 userAutoAddRepositoryFactory;

    public AutoAddSettingRepository(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$79 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$79) {
        this.userAutoAddRepositoryFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$79;
    }

    public final Object autoAddedTiles(int i, ContinuationImpl continuationImpl) {
        if (!this.userAutoAddRepositories.contains(i)) {
            DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.userAutoAddRepositoryFactory.this$0;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
            this.userAutoAddRepositories.put(i, new UserAutoAddRepository(i, (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1649$$Nest$mqSPipelineLogger(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (CoroutineScope) switchingProvider.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get()));
        }
        return ((UserAutoAddRepository) this.userAutoAddRepositories.get(i)).autoAdded(continuationImpl);
    }

    public final Object markTileAdded(int i, TileSpec tileSpec, Continuation continuation) {
        Object emit;
        UserAutoAddRepository userAutoAddRepository = (UserAutoAddRepository) this.userAutoAddRepositories.get(i);
        Unit unit = Unit.INSTANCE;
        if (userAutoAddRepository != null) {
            if ((tileSpec instanceof TileSpec.Invalid) || (emit = userAutoAddRepository.changeEvents.emit(new UserAutoAddRepository.MarkTile(tileSpec), continuation)) != CoroutineSingletons.COROUTINE_SUSPENDED) {
                emit = unit;
            }
            if (emit == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return emit;
            }
        }
        return unit;
    }

    public final Object unmarkTileAdded(int i, TileSpec tileSpec, Continuation continuation) {
        Object emit;
        UserAutoAddRepository userAutoAddRepository = (UserAutoAddRepository) this.userAutoAddRepositories.get(i);
        Unit unit = Unit.INSTANCE;
        if (userAutoAddRepository != null) {
            if ((tileSpec instanceof TileSpec.Invalid) || (emit = userAutoAddRepository.changeEvents.emit(new UserAutoAddRepository.UnmarkTile(tileSpec), continuation)) != CoroutineSingletons.COROUTINE_SUSPENDED) {
                emit = unit;
            }
            if (emit == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return emit;
            }
        }
        return unit;
    }
}

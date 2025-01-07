package com.android.systemui.qs.tiles.base.viewmodel;

import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tiles.base.analytics.QSTileAnalytics;
import com.android.systemui.qs.tiles.base.interactor.DisabledByPolicyInteractorImpl;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataToStateMapper;
import com.android.systemui.qs.tiles.base.interactor.QSTileUserActionInteractor;
import com.android.systemui.qs.tiles.base.logging.QSTileLogger;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfigProviderImpl;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.util.time.SystemClock;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileViewModelFactory$Static {
    public final CoroutineDispatcher backgroundDispatcher;
    public final QSTileCoroutineScopeFactory coroutineScopeFactory;
    public final DisabledByPolicyInteractorImpl disabledByPolicyInteractor;
    public final FalsingManager falsingManager;
    public final QSTileAnalytics qsTileAnalytics;
    public final QSTileConfigProviderImpl qsTileConfigProvider;
    public final QSTileLogger qsTileLogger;
    public final SystemClock systemClock;
    public final CoroutineDispatcher uiBackgroundDispatcher;
    public final UserRepositoryImpl userRepository;

    public QSTileViewModelFactory$Static(DisabledByPolicyInteractorImpl disabledByPolicyInteractorImpl, UserRepositoryImpl userRepositoryImpl, FalsingManager falsingManager, QSTileAnalytics qSTileAnalytics, QSTileLogger qSTileLogger, QSTileConfigProviderImpl qSTileConfigProviderImpl, SystemClock systemClock, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, QSTileCoroutineScopeFactory qSTileCoroutineScopeFactory) {
        this.disabledByPolicyInteractor = disabledByPolicyInteractorImpl;
        this.userRepository = userRepositoryImpl;
        this.falsingManager = falsingManager;
        this.qsTileAnalytics = qSTileAnalytics;
        this.qsTileLogger = qSTileLogger;
        this.qsTileConfigProvider = qSTileConfigProviderImpl;
        this.systemClock = systemClock;
        this.backgroundDispatcher = coroutineDispatcher;
        this.uiBackgroundDispatcher = coroutineDispatcher2;
        this.coroutineScopeFactory = qSTileCoroutineScopeFactory;
    }

    public final QSTileViewModelImpl create(TileSpec tileSpec, final QSTileUserActionInteractor qSTileUserActionInteractor, final QSTileDataInteractor qSTileDataInteractor, final QSTileDataToStateMapper qSTileDataToStateMapper) {
        QSTileConfig config = this.qsTileConfigProvider.getConfig(tileSpec.getSpec());
        Function0 function0 = new Function0() { // from class: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static$create$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return QSTileUserActionInteractor.this;
            }
        };
        Function0 function02 = new Function0() { // from class: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static$create$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return QSTileDataInteractor.this;
            }
        };
        Function0 function03 = new Function0() { // from class: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static$create$3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return QSTileDataToStateMapper.this;
            }
        };
        CoroutineContext plus = this.coroutineScopeFactory.applicationScope.getCoroutineContext().plus(SupervisorKt.SupervisorJob$default());
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        ContextScope CoroutineScope = CoroutineScopeKt.CoroutineScope(plus.plus(EmptyCoroutineContext.INSTANCE));
        return new QSTileViewModelImpl(config, function0, function02, function03, this.disabledByPolicyInteractor, this.userRepository, this.falsingManager, this.qsTileAnalytics, this.qsTileLogger, this.systemClock, this.backgroundDispatcher, this.uiBackgroundDispatcher, CoroutineScope);
    }
}

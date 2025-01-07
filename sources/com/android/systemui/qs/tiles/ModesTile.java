package com.android.systemui.qs.tiles;

import android.R;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt$withTraceName$1;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.Expandable;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tiles.impl.modes.domain.interactor.ModesTileDataInteractor;
import com.android.systemui.qs.tiles.impl.modes.domain.interactor.ModesTileDataInteractor$tileData$$inlined$map$default$1;
import com.android.systemui.qs.tiles.impl.modes.domain.interactor.ModesTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.modes.domain.model.ModesTileModel;
import com.android.systemui.qs.tiles.impl.modes.ui.ModesTileMapper;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfigProviderImpl;
import com.android.systemui.qs.tiles.viewmodel.QSTileState;
import com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ModesTile extends QSTileImpl {
    public final QSTileConfig config;
    public final ModesTileDataInteractor dataInteractor;
    public final ModesTileMapper tileMapper;
    public QSTileState tileState;
    public final ModesTileUserActionInteractor userActionInteractor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.tiles.ModesTile$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.tiles.ModesTile$1$1, reason: invalid class name and collision with other inner class name */
        final class C01651 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ ModesTile this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01651(ModesTile modesTile, Continuation continuation) {
                super(2, continuation);
                this.this$0 = modesTile;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01651(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C01651) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ModesTileDataInteractor modesTileDataInteractor = this.this$0.dataInteractor;
                    ZenModeInteractor zenModeInteractor = modesTileDataInteractor.zenModeInteractor;
                    String walkStackForClassName = FlowExtKt.walkStackForClassName();
                    Flow distinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.flowOn(new ModesTileDataInteractor$tileData$$inlined$map$default$1(new FlowExtKt$withTraceName$1(zenModeInteractor.activeModes, walkStackForClassName), walkStackForClassName, modesTileDataInteractor), modesTileDataInteractor.bgDispatcher));
                    final ModesTile modesTile = this.this$0;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.qs.tiles.ModesTile.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            ModesTile.this.refreshState((ModesTileModel) obj2);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (distinctUntilChanged.collect(flowCollector, this) == coroutineSingletons) {
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

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return ModesTile.this.new AnonymousClass1(continuation);
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
                ModesTile modesTile = ModesTile.this;
                LifecycleRegistry lifecycleRegistry = modesTile.mLifecycle;
                Lifecycle.State state = Lifecycle.State.RESUMED;
                C01651 c01651 = new C01651(modesTile, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleRegistry, state, c01651, this) == coroutineSingletons) {
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

    public ModesTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, QSTileConfigProviderImpl qSTileConfigProviderImpl, ModesTileDataInteractor modesTileDataInteractor, ModesTileMapper modesTileMapper, ModesTileUserActionInteractor modesTileUserActionInteractor) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.dataInteractor = modesTileDataInteractor;
        this.tileMapper = modesTileMapper;
        this.userActionInteractor = modesTileUserActionInteractor;
        this.config = qSTileConfigProviderImpl.getConfig("dnd");
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects android.app.modes_ui_icons to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects android.app.modes_ui_icons to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects android.app.modes_ui_icons to be enabled.");
        }
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(this.mLifecycle), null, null, new AnonymousClass1(null), 3);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return this.userActionInteractor.longClickIntent;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        QSTileState qSTileState = this.tileState;
        if (qSTileState == null) {
            qSTileState = null;
        }
        return qSTileState.label;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new ModesTile$handleClick$1(this, expandable, null));
    }

    /* JADX WARN: Type inference failed for: r5v10, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public void handleUpdateState(QSTile.State state, Object obj) {
        QSTile.Icon icon;
        if (!(obj instanceof ModesTileModel)) {
            obj = BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new ModesTile$handleUpdateState$model$1(this, null));
        }
        QSTileState map = this.tileMapper.map(this.config, (ModesTileModel) obj);
        this.tileState = map;
        if (state != null) {
            state.state = map.activationState.getLegacyState();
            QSTileState qSTileState = this.tileState;
            if (qSTileState == null) {
                qSTileState = null;
            }
            Icon icon2 = (Icon) qSTileState.icon.invoke();
            if (icon2 == null) {
                icon = QSTileImpl.ResourceIcon.get(R.drawable.jog_dial_bg);
            } else if (icon2 instanceof Icon.Loaded) {
                icon = new QSTileImpl.DrawableIcon(((Icon.Loaded) icon2).drawable);
            } else {
                if (!(icon2 instanceof Icon.Resource)) {
                    throw new NoWhenBranchMatchedException();
                }
                icon = QSTileImpl.ResourceIcon.get(((Icon.Resource) icon2).res);
            }
            state.icon = icon;
            state.label = getTileLabel();
            QSTileState qSTileState2 = this.tileState;
            state.secondaryLabel = (qSTileState2 == null ? null : qSTileState2).secondaryLabel;
            state.contentDescription = (qSTileState2 == null ? null : qSTileState2).contentDescription;
            state.expandedAccessibilityClassName = (qSTileState2 != null ? qSTileState2 : null).expandedAccessibilityClassName;
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        QSTile.State state = new QSTile.State();
        state.label = this.mContext.getString(com.android.wm.shell.R.string.quick_settings_modes_label);
        state.icon = QSTileImpl.ResourceIcon.get(R.drawable.jog_dial_bg);
        state.state = 1;
        return state;
    }
}

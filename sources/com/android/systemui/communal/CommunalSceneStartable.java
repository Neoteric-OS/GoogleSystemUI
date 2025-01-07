package com.android.systemui.communal;

import com.android.internal.logging.UiEventLogger;
import com.android.systemui.CoreStartable;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.dock.DockManager;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.util.kotlin.FlowKt$emitOnStart$1;
import com.android.systemui.util.settings.SettingsProxyExt;
import com.android.systemui.util.settings.SystemSettingsImpl;
import java.util.Optional;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalSceneStartable implements CoreStartable {
    public static final /* synthetic */ KProperty[] $$delegatedProperties = null;
    public static final Companion Companion;
    public static final int DEFAULT_SCREEN_TIMEOUT;
    public final CoroutineScope bgScope;
    public final Optional centralSurfaces$delegate;
    public final CommunalInteractor communalInteractor;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final CommunalSettingsInteractor communalSettingsInteractor;
    public final DockManager dockManager;
    public boolean isDreaming;
    public final KeyguardInteractor keyguardInteractor;
    public final CoroutineDispatcher mainDispatcher;
    public final NotificationShadeWindowController notificationShadeWindowController;
    public int screenTimeout = DEFAULT_SCREEN_TIMEOUT;
    public final SystemSettingsImpl systemSettings;
    public StandaloneCoroutine timeoutJob;
    public final UiEventLogger uiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        new PropertyReference1Impl(CommunalSceneStartable.class, "centralSurfaces", "getCentralSurfaces()Lcom/android/systemui/statusbar/phone/CentralSurfaces;", 0);
        Reflection.factory.getClass();
        Companion = new Companion();
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.SECONDS;
        DurationKt.toDuration(5, durationUnit);
        DurationKt.toDuration(1, durationUnit);
        DEFAULT_SCREEN_TIMEOUT = 15000;
    }

    public CommunalSceneStartable(DockManager dockManager, CommunalInteractor communalInteractor, CommunalSettingsInteractor communalSettingsInteractor, CommunalSceneInteractor communalSceneInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardInteractor keyguardInteractor, SystemSettingsImpl systemSettingsImpl, Optional optional, NotificationShadeWindowController notificationShadeWindowController, CoroutineScope coroutineScope, CoroutineScope coroutineScope2, CoroutineDispatcher coroutineDispatcher, UiEventLogger uiEventLogger) {
        this.communalInteractor = communalInteractor;
        this.communalSettingsInteractor = communalSettingsInteractor;
        this.communalSceneInteractor = communalSceneInteractor;
        this.keyguardInteractor = keyguardInteractor;
        this.systemSettings = systemSettingsImpl;
        this.notificationShadeWindowController = notificationShadeWindowController;
        this.bgScope = coroutineScope2;
        this.mainDispatcher = coroutineDispatcher;
        this.uiEventLogger = uiEventLogger;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.communalSettingsInteractor.isCommunalFlagEnabled()) {
            FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FlowKt$emitOnStart$1(2, null), SettingsProxyExt.observerFlow(this.systemSettings, "screen_off_timeout")), new CommunalSceneStartable$start$3(this, null), 0);
            CoroutineScope coroutineScope = this.bgScope;
            FlowKt.launchIn(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, coroutineScope);
            BuildersKt.launch$default(coroutineScope, null, null, new CommunalSceneStartable$start$4(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new CommunalSceneStartable$start$5(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new CommunalSceneStartable$start$6(this, null), 3);
        }
    }
}

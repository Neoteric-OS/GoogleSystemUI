package com.android.systemui.shade.domain.startable;

import android.content.Context;
import com.android.systemui.CoreStartable;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractor;
import com.android.systemui.common.ui.data.repository.ConfigurationRepository;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.shade.DispatchTouchLogger;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.android.systemui.shade.TouchLogger;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.shade.transition.ScrimShadeTransitionController;
import com.android.systemui.shade.transition.ScrimShadeTransitionController$init$1;
import com.android.systemui.shade.transition.ScrimShadeTransitionController$init$currentState$1;
import com.android.systemui.statusbar.PulseExpansionHandler;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import dagger.internal.DelegateFactory;
import java.io.PrintWriter;
import javax.inject.Provider;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeStartable implements CoreStartable {
    public final Context applicationContext;
    public final CoroutineScope applicationScope;
    public final ConfigurationRepository configurationRepository;
    public final DisplayStateInteractor displayStateInteractor;
    public final NotificationStackScrollLayoutController nsslc;
    public final PulseExpansionHandler pulseExpansionHandler;
    public final DelegateFactory sceneInteractorProvider;
    public final ScrimController scrimController;
    public final ScrimShadeTransitionController scrimShadeTransitionController;
    public final ShadeExpansionStateManager shadeExpansionStateManager;
    public final ShadeRepository shadeRepository;
    public final SplitShadeStateControllerImpl splitShadeStateController;
    public final LogBuffer touchLog;

    public ShadeStartable(CoroutineScope coroutineScope, Context context, LogBuffer logBuffer, ConfigurationRepository configurationRepository, ShadeRepository shadeRepository, SplitShadeStateControllerImpl splitShadeStateControllerImpl, ScrimShadeTransitionController scrimShadeTransitionController, DelegateFactory delegateFactory, Provider provider, ShadeExpansionStateManager shadeExpansionStateManager, PulseExpansionHandler pulseExpansionHandler, DisplayStateInteractor displayStateInteractor, NotificationStackScrollLayoutController notificationStackScrollLayoutController, ScrimController scrimController) {
        this.applicationScope = coroutineScope;
        this.applicationContext = context;
        this.touchLog = logBuffer;
        this.configurationRepository = configurationRepository;
        this.shadeRepository = shadeRepository;
        this.splitShadeStateController = splitShadeStateControllerImpl;
        this.scrimShadeTransitionController = scrimShadeTransitionController;
        this.pulseExpansionHandler = pulseExpansionHandler;
        this.nsslc = notificationStackScrollLayoutController;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        BuildersKt.launch$default(this.applicationScope, null, null, new ShadeStartable$hydrateShadeLayoutWidth$1(this, null), 3);
        TouchLogger.touchLogger = new DispatchTouchLogger(this.touchLog);
        final ScrimShadeTransitionController scrimShadeTransitionController = this.scrimShadeTransitionController;
        scrimShadeTransitionController.getClass();
        ScrimShadeTransitionController$init$currentState$1 scrimShadeTransitionController$init$currentState$1 = new ScrimShadeTransitionController$init$currentState$1(scrimShadeTransitionController);
        ShadeExpansionStateManager shadeExpansionStateManager = scrimShadeTransitionController.shadeExpansionStateManager;
        scrimShadeTransitionController.lastExpansionEvent = shadeExpansionStateManager.addExpansionListener(scrimShadeTransitionController$init$currentState$1);
        scrimShadeTransitionController.onStateChanged();
        shadeExpansionStateManager.stateListeners.add(new ScrimShadeTransitionController$init$1(scrimShadeTransitionController));
        DumpManager.registerDumpable$default(scrimShadeTransitionController.dumpManager, "ScrimShadeTransitionController", new Dumpable() { // from class: com.android.systemui.shade.transition.ScrimShadeTransitionController$init$2
            @Override // com.android.systemui.Dumpable
            public final void dump(PrintWriter printWriter, String[] strArr) {
                ScrimShadeTransitionController scrimShadeTransitionController2 = ScrimShadeTransitionController.this;
                printWriter.println(StringsKt__IndentKt.trimIndent("\n                ScrimShadeTransitionController:\n                  State:\n                    currentPanelState: " + scrimShadeTransitionController2.currentPanelState + "\n                    lastExpansionFraction: " + scrimShadeTransitionController2.lastExpansionFraction + "\n                    lastExpansionEvent: " + scrimShadeTransitionController2.lastExpansionEvent + "\n            "));
            }
        });
        this.pulseExpansionHandler.stackScrollerController = this.nsslc;
    }
}

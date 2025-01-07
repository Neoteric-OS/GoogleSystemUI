package com.android.systemui.statusbar;

import android.content.Context;
import android.content.res.Configuration;
import android.util.IndentingPrintWriter;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AbstractLockscreenShadeTransitionController implements Dumpable {
    public final Context context;
    public float dragDownAmount;
    public final SplitShadeStateControllerImpl splitShadeStateController;
    public boolean useSplitShade;

    public AbstractLockscreenShadeTransitionController(Context context, ConfigurationController configurationController, DumpManager dumpManager, SplitShadeStateControllerImpl splitShadeStateControllerImpl) {
        this.context = context;
        this.splitShadeStateController = splitShadeStateControllerImpl;
        this.useSplitShade = splitShadeStateControllerImpl.shouldUseSplitNotificationShade(context.getResources());
        updateResources();
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.AbstractLockscreenShadeTransitionController.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                AbstractLockscreenShadeTransitionController abstractLockscreenShadeTransitionController = AbstractLockscreenShadeTransitionController.this;
                abstractLockscreenShadeTransitionController.useSplitShade = abstractLockscreenShadeTransitionController.splitShadeStateController.shouldUseSplitNotificationShade(abstractLockscreenShadeTransitionController.context.getResources());
                abstractLockscreenShadeTransitionController.updateResources();
            }
        });
        dumpManager.registerDumpable(this);
    }

    public abstract void dump(IndentingPrintWriter indentingPrintWriter);

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        dump(new IndentingPrintWriter(printWriter, "  "));
    }

    public abstract void updateResources();
}

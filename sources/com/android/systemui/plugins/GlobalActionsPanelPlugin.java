package com.android.systemui.plugins;

import android.app.BroadcastOptions;
import android.app.PendingIntent;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.systemui.plugins.annotations.Dependencies;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.ProvidesInterface;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@Dependencies({@DependsOn(target = Callbacks.class), @DependsOn(target = PanelViewController.class)})
@ProvidesInterface(action = GlobalActionsPanelPlugin.ACTION, version = 0)
/* loaded from: classes.dex */
public interface GlobalActionsPanelPlugin extends Plugin {
    public static final String ACTION = "com.android.systemui.action.PLUGIN_GLOBAL_ACTIONS_PANEL";
    public static final int VERSION = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 0)
    public interface Callbacks {
        public static final int VERSION = 0;

        void dismissGlobalActionsMenu();

        default void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent) {
            try {
                BroadcastOptions makeBasic = BroadcastOptions.makeBasic();
                makeBasic.setInteractive(true);
                pendingIntent.send(makeBasic.toBundle());
            } catch (PendingIntent.CanceledException unused) {
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 0)
    public interface PanelViewController {
        public static final int VERSION = 0;

        default Drawable getBackgroundDrawable() {
            return null;
        }

        View getPanelContent();

        void onDeviceLockStateChanged(boolean z);

        void onDismissed();
    }

    PanelViewController onPanelShown(Callbacks callbacks, boolean z);
}

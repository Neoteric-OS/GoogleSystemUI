package com.android.systemui.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.shared.recents.utilities.Utilities;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class KeyboardShortcutsReceiver extends BroadcastReceiver {
    public final FeatureFlags mFeatureFlags;

    public KeyboardShortcutsReceiver(FeatureFlags featureFlags) {
        this.mFeatureFlags = featureFlags;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (((FeatureFlagsClassicRelease) this.mFeatureFlags).isEnabled(Flags.SHORTCUT_LIST_SEARCH_LAYOUT) && Utilities.isLargeScreen(context)) {
            if ("com.android.intent.action.SHOW_KEYBOARD_SHORTCUTS".equals(intent.getAction())) {
                KeyboardShortcutListSearch.show(-1, context);
                return;
            } else {
                if ("com.android.intent.action.DISMISS_KEYBOARD_SHORTCUTS".equals(intent.getAction())) {
                    KeyboardShortcutListSearch.dismiss();
                    return;
                }
                return;
            }
        }
        if ("com.android.intent.action.SHOW_KEYBOARD_SHORTCUTS".equals(intent.getAction())) {
            KeyboardShortcuts.show(-1, context);
        } else if ("com.android.intent.action.DISMISS_KEYBOARD_SHORTCUTS".equals(intent.getAction())) {
            KeyboardShortcuts.dismiss();
        }
    }
}

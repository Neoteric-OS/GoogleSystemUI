package com.android.wm.shell.onehanded;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.wm.shell.onehanded.OneHandedController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OneHandedSettingsUtil {
    public static final String ONE_HANDED_MODE_TARGET_NAME = AccessibilityShortcutController.ONE_HANDED_COMPONENT_NAME.getShortClassName();

    public static boolean getSettingsOneHandedModeEnabled(ContentResolver contentResolver, int i) {
        return Settings.Secure.getIntForUser(contentResolver, "one_handed_mode_enabled", 0, i) == 1;
    }

    public static boolean getSettingsSwipeToNotificationEnabled(ContentResolver contentResolver, int i) {
        return Settings.Secure.getIntForUser(contentResolver, "swipe_bottom_to_notification_enabled", 0, i) == 1;
    }

    public static void registerSettingsKeyObserver(String str, ContentResolver contentResolver, OneHandedController.AnonymousClass5 anonymousClass5, int i) {
        Uri uriFor = Settings.Secure.getUriFor(str);
        if (contentResolver == null || uriFor == null) {
            return;
        }
        contentResolver.registerContentObserver(uriFor, false, anonymousClass5, i);
    }
}

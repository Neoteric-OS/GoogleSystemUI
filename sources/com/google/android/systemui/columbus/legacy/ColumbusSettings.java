package com.google.android.systemui.columbus.legacy;

import android.app.backup.BackupManager;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.columbus.legacy.ColumbusContentObserver;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusSettings {
    public static final Uri COLUMBUS_ACTION_URI;
    public static final Uri COLUMBUS_AP_SENSOR_URI;
    public static final Uri COLUMBUS_ENABLED_URI;
    public static final Uri COLUMBUS_LAUNCH_APP_SHORTCUT_URI;
    public static final Uri COLUMBUS_LAUNCH_APP_URI;
    public static final Uri COLUMBUS_LOW_SENSITIVITY_URI;
    public static final Uri COLUMBUS_SILENCE_ALERTS_URI;
    public static final Set MONITORED_URIS;
    public final String backupPackage;
    public final ContentResolver contentResolver;
    public final UserTracker userTracker;
    public final Set listeners = new LinkedHashSet();
    public final Function1 callback = new Function1() { // from class: com.google.android.systemui.columbus.legacy.ColumbusSettings$callback$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            boolean z;
            Uri uri = (Uri) obj;
            if (uri.equals(ColumbusSettings.COLUMBUS_ENABLED_URI)) {
                boolean isColumbusEnabled = ColumbusSettings.this.isColumbusEnabled();
                Iterator it = ColumbusSettings.this.listeners.iterator();
                while (it.hasNext()) {
                    ((ColumbusSettings.ColumbusSettingsChangeListener) it.next()).onColumbusEnabledChange(isColumbusEnabled);
                }
                BackupManager.dataChangedForUser(((UserTrackerImpl) ColumbusSettings.this.userTracker).getUserId(), ColumbusSettings.this.backupPackage);
            } else {
                if (uri.equals(ColumbusSettings.COLUMBUS_AP_SENSOR_URI)) {
                    ColumbusSettings columbusSettings = ColumbusSettings.this;
                    Settings.Secure.getIntForUser(columbusSettings.contentResolver, "columbus_ap_sensor", 0, ((UserTrackerImpl) columbusSettings.userTracker).getUserId());
                    Iterator it2 = ColumbusSettings.this.listeners.iterator();
                    while (it2.hasNext()) {
                        ((ColumbusSettings.ColumbusSettingsChangeListener) it2.next()).getClass();
                    }
                } else if (uri.equals(ColumbusSettings.COLUMBUS_ACTION_URI)) {
                    String selectedAction = ColumbusSettings.this.selectedAction();
                    Iterator it3 = ColumbusSettings.this.listeners.iterator();
                    while (it3.hasNext()) {
                        ((ColumbusSettings.ColumbusSettingsChangeListener) it3.next()).onSelectedActionChange(selectedAction);
                    }
                    BackupManager.dataChangedForUser(((UserTrackerImpl) ColumbusSettings.this.userTracker).getUserId(), ColumbusSettings.this.backupPackage);
                } else if (uri.equals(ColumbusSettings.COLUMBUS_LAUNCH_APP_URI)) {
                    String selectedApp = ColumbusSettings.this.selectedApp();
                    Iterator it4 = ColumbusSettings.this.listeners.iterator();
                    while (it4.hasNext()) {
                        ((ColumbusSettings.ColumbusSettingsChangeListener) it4.next()).onSelectedAppChange(selectedApp);
                    }
                    BackupManager.dataChangedForUser(((UserTrackerImpl) ColumbusSettings.this.userTracker).getUserId(), ColumbusSettings.this.backupPackage);
                } else if (uri.equals(ColumbusSettings.COLUMBUS_LAUNCH_APP_SHORTCUT_URI)) {
                    ColumbusSettings columbusSettings2 = ColumbusSettings.this;
                    String stringForUser = Settings.Secure.getStringForUser(columbusSettings2.contentResolver, "columbus_launch_app_shortcut", ((UserTrackerImpl) columbusSettings2.userTracker).getUserId());
                    if (stringForUser == null) {
                        stringForUser = "";
                    }
                    Iterator it5 = ColumbusSettings.this.listeners.iterator();
                    while (it5.hasNext()) {
                        ((ColumbusSettings.ColumbusSettingsChangeListener) it5.next()).onSelectedAppShortcutChange(stringForUser);
                    }
                    BackupManager.dataChangedForUser(((UserTrackerImpl) ColumbusSettings.this.userTracker).getUserId(), ColumbusSettings.this.backupPackage);
                } else if (uri.equals(ColumbusSettings.COLUMBUS_LOW_SENSITIVITY_URI)) {
                    ColumbusSettings columbusSettings3 = ColumbusSettings.this;
                    z = Settings.Secure.getIntForUser(columbusSettings3.contentResolver, "columbus_low_sensitivity", 0, ((UserTrackerImpl) columbusSettings3.userTracker).getUserId()) != 0;
                    Iterator it6 = ColumbusSettings.this.listeners.iterator();
                    while (it6.hasNext()) {
                        ((ColumbusSettings.ColumbusSettingsChangeListener) it6.next()).onLowSensitivityChange(z);
                    }
                    BackupManager.dataChangedForUser(((UserTrackerImpl) ColumbusSettings.this.userTracker).getUserId(), ColumbusSettings.this.backupPackage);
                } else if (uri.equals(ColumbusSettings.COLUMBUS_SILENCE_ALERTS_URI)) {
                    ColumbusSettings columbusSettings4 = ColumbusSettings.this;
                    z = Settings.Secure.getIntForUser(columbusSettings4.contentResolver, "columbus_silence_alerts", 1, ((UserTrackerImpl) columbusSettings4.userTracker).getUserId()) != 0;
                    Iterator it7 = ColumbusSettings.this.listeners.iterator();
                    while (it7.hasNext()) {
                        ((ColumbusSettings.ColumbusSettingsChangeListener) it7.next()).onAlertSilenceEnabledChange(z);
                    }
                } else {
                    Log.w("Columbus/Settings", "Unknown setting change: " + uri);
                }
            }
            return Unit.INSTANCE;
        }
    };

    static {
        Uri uriFor = Settings.Secure.getUriFor("columbus_enabled");
        COLUMBUS_ENABLED_URI = uriFor;
        Uri uriFor2 = Settings.Secure.getUriFor("columbus_ap_sensor");
        COLUMBUS_AP_SENSOR_URI = uriFor2;
        Uri uriFor3 = Settings.Secure.getUriFor("columbus_action");
        COLUMBUS_ACTION_URI = uriFor3;
        Uri uriFor4 = Settings.Secure.getUriFor("columbus_launch_app");
        COLUMBUS_LAUNCH_APP_URI = uriFor4;
        Uri uriFor5 = Settings.Secure.getUriFor("columbus_launch_app_shortcut");
        COLUMBUS_LAUNCH_APP_SHORTCUT_URI = uriFor5;
        Uri uriFor6 = Settings.Secure.getUriFor("columbus_low_sensitivity");
        COLUMBUS_LOW_SENSITIVITY_URI = uriFor6;
        Uri uriFor7 = Settings.Secure.getUriFor("columbus_silence_alerts");
        COLUMBUS_SILENCE_ALERTS_URI = uriFor7;
        MONITORED_URIS = SetsKt.setOf(uriFor, uriFor2, uriFor3, uriFor4, uriFor5, uriFor6, uriFor7);
    }

    public ColumbusSettings(Context context, UserTracker userTracker, ColumbusContentObserver.Factory factory) {
        this.userTracker = userTracker;
        this.backupPackage = context.getBasePackageName();
        this.contentResolver = context.getContentResolver();
        Set<Uri> set = MONITORED_URIS;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set, 10));
        for (Uri uri : set) {
            Intrinsics.checkNotNull(uri);
            Function1 function1 = this.callback;
            factory.getClass();
            Executor executor = factory.executor;
            arrayList.add(new ColumbusContentObserver(factory.contentResolver, uri, function1, factory.userTracker, executor, factory.handler));
        }
        for (ColumbusContentObserver columbusContentObserver : CollectionsKt.toSet(arrayList)) {
            ((UserTrackerImpl) columbusContentObserver.userTracker).addCallback(columbusContentObserver.userTrackerCallback, columbusContentObserver.executor);
            columbusContentObserver.contentResolver.contentResolver.unregisterContentObserver(columbusContentObserver);
            ContentResolverWrapper contentResolverWrapper = columbusContentObserver.contentResolver;
            contentResolverWrapper.contentResolver.registerContentObserver(columbusContentObserver.settingsUri, false, columbusContentObserver, ((UserTrackerImpl) columbusContentObserver.userTracker).getUserId());
        }
    }

    public final boolean isColumbusEnabled() {
        return Settings.Secure.getIntForUser(this.contentResolver, "columbus_enabled", 0, ((UserTrackerImpl) this.userTracker).getUserId()) != 0;
    }

    public final void registerColumbusSettingsChangeListener(ColumbusSettingsChangeListener columbusSettingsChangeListener) {
        this.listeners.add(columbusSettingsChangeListener);
    }

    public final String selectedAction() {
        String stringForUser = Settings.Secure.getStringForUser(this.contentResolver, "columbus_action", ((UserTrackerImpl) this.userTracker).getUserId());
        return stringForUser == null ? "" : stringForUser;
    }

    public final String selectedApp() {
        String stringForUser = Settings.Secure.getStringForUser(this.contentResolver, "columbus_launch_app", ((UserTrackerImpl) this.userTracker).getUserId());
        return stringForUser == null ? "" : stringForUser;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ColumbusSettingsChangeListener {
        default void onAlertSilenceEnabledChange(boolean z) {
        }

        default void onColumbusEnabledChange(boolean z) {
        }

        default void onLowSensitivityChange(boolean z) {
        }

        default void onSelectedActionChange(String str) {
        }

        default void onSelectedAppChange(String str) {
        }

        default void onSelectedAppShortcutChange(String str) {
        }
    }
}

package com.android.systemui.demomode;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DemoModeAvailabilityTracker {
    public final DemoModeAvailabilityTracker$onObserver$1 allowedObserver;
    public final Context context;
    public final GlobalSettings globalSettings;
    public boolean isDemoModeAvailable;
    public boolean isInDemoMode;
    public final DemoModeAvailabilityTracker$onObserver$1 onObserver;

    /* JADX WARN: Type inference failed for: r4v2, types: [com.android.systemui.demomode.DemoModeAvailabilityTracker$onObserver$1] */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.android.systemui.demomode.DemoModeAvailabilityTracker$onObserver$1] */
    public DemoModeAvailabilityTracker(Context context, GlobalSettings globalSettings) {
        this.context = context;
        this.globalSettings = globalSettings;
        this.isInDemoMode = globalSettings.getInt(0, "sysui_tuner_demo_on") != 0;
        this.isDemoModeAvailable = globalSettings.getInt(0, "sysui_demo_allowed") != 0;
        final Handler handler = new Handler(Looper.getMainLooper());
        final int i = 1;
        this.allowedObserver = new ContentObserver(this, handler) { // from class: com.android.systemui.demomode.DemoModeAvailabilityTracker$onObserver$1
            public final /* synthetic */ DemoModeAvailabilityTracker this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i) {
                    case 0:
                        boolean z2 = this.this$0.globalSettings.getInt(0, "sysui_tuner_demo_on") != 0;
                        DemoModeAvailabilityTracker demoModeAvailabilityTracker = this.this$0;
                        if (demoModeAvailabilityTracker.isInDemoMode != z2) {
                            demoModeAvailabilityTracker.isInDemoMode = z2;
                            if (!z2) {
                                demoModeAvailabilityTracker.onDemoModeFinished();
                                break;
                            } else {
                                demoModeAvailabilityTracker.onDemoModeStarted();
                                break;
                            }
                        }
                        break;
                    default:
                        boolean z3 = this.this$0.globalSettings.getInt(0, "sysui_demo_allowed") != 0;
                        DemoModeAvailabilityTracker demoModeAvailabilityTracker2 = this.this$0;
                        if (demoModeAvailabilityTracker2.isDemoModeAvailable != z3) {
                            demoModeAvailabilityTracker2.isDemoModeAvailable = z3;
                            demoModeAvailabilityTracker2.onDemoModeAvailabilityChanged();
                            break;
                        }
                        break;
                }
            }
        };
        final Handler handler2 = new Handler(Looper.getMainLooper());
        final int i2 = 0;
        this.onObserver = new ContentObserver(this, handler2) { // from class: com.android.systemui.demomode.DemoModeAvailabilityTracker$onObserver$1
            public final /* synthetic */ DemoModeAvailabilityTracker this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        boolean z2 = this.this$0.globalSettings.getInt(0, "sysui_tuner_demo_on") != 0;
                        DemoModeAvailabilityTracker demoModeAvailabilityTracker = this.this$0;
                        if (demoModeAvailabilityTracker.isInDemoMode != z2) {
                            demoModeAvailabilityTracker.isInDemoMode = z2;
                            if (!z2) {
                                demoModeAvailabilityTracker.onDemoModeFinished();
                                break;
                            } else {
                                demoModeAvailabilityTracker.onDemoModeStarted();
                                break;
                            }
                        }
                        break;
                    default:
                        boolean z3 = this.this$0.globalSettings.getInt(0, "sysui_demo_allowed") != 0;
                        DemoModeAvailabilityTracker demoModeAvailabilityTracker2 = this.this$0;
                        if (demoModeAvailabilityTracker2.isDemoModeAvailable != z3) {
                            demoModeAvailabilityTracker2.isDemoModeAvailable = z3;
                            demoModeAvailabilityTracker2.onDemoModeAvailabilityChanged();
                            break;
                        }
                        break;
                }
            }
        };
    }

    public abstract void onDemoModeAvailabilityChanged();

    public abstract void onDemoModeFinished();

    public abstract void onDemoModeStarted();

    public final void startTracking() {
        ContentResolver contentResolver = this.context.getContentResolver();
        GlobalSettings globalSettings = this.globalSettings;
        ((GlobalSettingsImpl) globalSettings).getClass();
        contentResolver.registerContentObserver(Settings.Global.getUriFor("sysui_demo_allowed"), false, this.allowedObserver);
        ((GlobalSettingsImpl) globalSettings).getClass();
        contentResolver.registerContentObserver(Settings.Global.getUriFor("sysui_tuner_demo_on"), false, this.onObserver);
    }
}

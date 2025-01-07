package com.android.systemui.privacy;

import android.provider.DeviceConfig;
import android.util.IndentingPrintWriter;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.privacy.PrivacyConfig;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyConfig implements Dumpable {
    public final PrivacyConfig$devicePropertiesChangedListener$1 devicePropertiesChangedListener;
    public final DelayableExecutor uiExecutor;
    public final List callbacks = new ArrayList();
    public boolean micCameraAvailable = DeviceConfig.getBoolean("privacy", "camera_mic_icons_enabled", true);
    public boolean locationAvailable = DeviceConfig.getBoolean("privacy", "location_indicators_enabled", false);
    public boolean mediaProjectionAvailable = DeviceConfig.getBoolean("privacy", "media_projection_indicators_enabled", true);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public /* synthetic */ Companion(int i) {
            this();
        }

        private Companion() {
        }
    }

    static {
        new Companion(0);
    }

    public PrivacyConfig(DelayableExecutor delayableExecutor, DeviceConfigProxy deviceConfigProxy, DumpManager dumpManager) {
        this.uiExecutor = delayableExecutor;
        DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.privacy.PrivacyConfig$devicePropertiesChangedListener$1
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                if ("privacy".equals(properties.getNamespace())) {
                    if (properties.getKeyset().contains("camera_mic_icons_enabled")) {
                        PrivacyConfig.this.micCameraAvailable = properties.getBoolean("camera_mic_icons_enabled", true);
                        PrivacyConfig privacyConfig = PrivacyConfig.this;
                        Iterator it = privacyConfig.callbacks.iterator();
                        while (it.hasNext()) {
                            PrivacyConfig.Callback callback = (PrivacyConfig.Callback) ((WeakReference) it.next()).get();
                            if (callback != null) {
                                callback.onFlagMicCameraChanged(privacyConfig.micCameraAvailable);
                            }
                        }
                    }
                    if (properties.getKeyset().contains("location_indicators_enabled")) {
                        PrivacyConfig.this.locationAvailable = properties.getBoolean("location_indicators_enabled", false);
                        PrivacyConfig privacyConfig2 = PrivacyConfig.this;
                        Iterator it2 = privacyConfig2.callbacks.iterator();
                        while (it2.hasNext()) {
                            PrivacyConfig.Callback callback2 = (PrivacyConfig.Callback) ((WeakReference) it2.next()).get();
                            if (callback2 != null) {
                                callback2.onFlagLocationChanged(privacyConfig2.locationAvailable);
                            }
                        }
                    }
                    if (properties.getKeyset().contains("media_projection_indicators_enabled")) {
                        PrivacyConfig.this.mediaProjectionAvailable = properties.getBoolean("media_projection_indicators_enabled", true);
                        Iterator it3 = PrivacyConfig.this.callbacks.iterator();
                        while (it3.hasNext()) {
                            PrivacyConfig.Callback callback3 = (PrivacyConfig.Callback) ((WeakReference) it3.next()).get();
                            if (callback3 != null) {
                                callback3.onFlagMediaProjectionChanged();
                            }
                        }
                    }
                }
            }
        };
        DumpManager.registerDumpable$default(dumpManager, "PrivacyConfig", this);
        DeviceConfig.addOnPropertiesChangedListener("privacy", delayableExecutor, onPropertiesChangedListener);
    }

    public final void addCallback(Callback callback) {
        ((ExecutorImpl) this.uiExecutor).execute(new PrivacyConfig$addCallback$1(this, new WeakReference(callback), 0));
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.println("PrivacyConfig state:");
        asIndenting.increaseIndent();
        try {
            asIndenting.println("micCameraAvailable: " + this.micCameraAvailable);
            asIndenting.println("locationAvailable: " + this.locationAvailable);
            asIndenting.println("mediaProjectionAvailable: " + this.mediaProjectionAvailable);
            asIndenting.println("Callbacks:");
            asIndenting.increaseIndent();
            try {
                Iterator it = this.callbacks.iterator();
                while (it.hasNext()) {
                    Callback callback = (Callback) ((WeakReference) it.next()).get();
                    if (callback != null) {
                        asIndenting.println(callback);
                    }
                }
                asIndenting.decreaseIndent();
                asIndenting.flush();
            } finally {
                asIndenting.decreaseIndent();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        default void onFlagMediaProjectionChanged() {
        }

        default void onFlagLocationChanged(boolean z) {
        }

        default void onFlagMicCameraChanged(boolean z) {
        }
    }
}

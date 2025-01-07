package com.android.systemui.classifier;

import android.content.Context;
import android.net.Uri;
import android.provider.DeviceConfig;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.FalsingPlugin;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.util.DeviceConfigProxy;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.io.PrintWriter;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FalsingManagerProxy implements FalsingManager, Dumpable {
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mBrightLineFalsingManagerProvider;
    public final DeviceConfigProxy mDeviceConfig;
    public final FalsingManagerProxy$$ExternalSyntheticLambda0 mDeviceConfigListener;
    public final DumpManager mDumpManager;
    public FalsingManager mInternalFalsingManager;
    public final AnonymousClass1 mPluginListener;
    public final PluginManager mPluginManager;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.provider.DeviceConfig$OnPropertiesChangedListener, com.android.systemui.classifier.FalsingManagerProxy$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.systemui.classifier.FalsingManagerProxy$1, com.android.systemui.plugins.PluginListener] */
    public FalsingManagerProxy(PluginManager pluginManager, Executor executor, DeviceConfigProxy deviceConfigProxy, DumpManager dumpManager, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        ?? r0 = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.classifier.FalsingManagerProxy$$ExternalSyntheticLambda0
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                FalsingManagerProxy falsingManagerProxy = FalsingManagerProxy.this;
                falsingManagerProxy.getClass();
                if ("systemui".equals(properties.getNamespace())) {
                    falsingManagerProxy.setupFalsingManager();
                }
            }
        };
        this.mDeviceConfigListener = r0;
        this.mPluginManager = pluginManager;
        this.mDumpManager = dumpManager;
        this.mDeviceConfig = deviceConfigProxy;
        this.mBrightLineFalsingManagerProvider = switchingProvider;
        setupFalsingManager();
        deviceConfigProxy.getClass();
        DeviceConfig.addOnPropertiesChangedListener("systemui", executor, (DeviceConfig.OnPropertiesChangedListener) r0);
        ?? r3 = new PluginListener() { // from class: com.android.systemui.classifier.FalsingManagerProxy.1
            @Override // com.android.systemui.plugins.PluginListener
            public final void onPluginConnected(Plugin plugin, Context context) {
                FalsingManager falsingManager = ((FalsingPlugin) plugin).getFalsingManager(context);
                if (falsingManager != null) {
                    FalsingManagerProxy falsingManagerProxy = FalsingManagerProxy.this;
                    falsingManagerProxy.mInternalFalsingManager.cleanupInternal();
                    falsingManagerProxy.mInternalFalsingManager = falsingManager;
                }
            }

            @Override // com.android.systemui.plugins.PluginListener
            public final void onPluginDisconnected(Plugin plugin) {
                FalsingManagerProxy.this.setupFalsingManager();
            }
        };
        this.mPluginListener = r3;
        pluginManager.addPluginListener(r3, FalsingPlugin.class);
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "FalsingManager", this);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void addFalsingBeliefListener(FalsingManager.FalsingBeliefListener falsingBeliefListener) {
        this.mInternalFalsingManager.addFalsingBeliefListener(falsingBeliefListener);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void addTapListener(FalsingManager.FalsingTapListener falsingTapListener) {
        this.mInternalFalsingManager.addTapListener(falsingTapListener);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void cleanupInternal() {
        FalsingManagerProxy$$ExternalSyntheticLambda0 falsingManagerProxy$$ExternalSyntheticLambda0 = this.mDeviceConfigListener;
        this.mDeviceConfig.getClass();
        DeviceConfig.removeOnPropertiesChangedListener(falsingManagerProxy$$ExternalSyntheticLambda0);
        this.mPluginManager.removePluginListener(this.mPluginListener);
        this.mDumpManager.unregisterDumpable("FalsingManager");
        this.mInternalFalsingManager.cleanupInternal();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void dump(PrintWriter printWriter, String[] strArr) {
        this.mInternalFalsingManager.dump(printWriter, strArr);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isClassifierEnabled() {
        return this.mInternalFalsingManager.isClassifierEnabled();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isFalseDoubleTap() {
        return this.mInternalFalsingManager.isFalseDoubleTap();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isFalseLongTap(int i) {
        return this.mInternalFalsingManager.isFalseLongTap(i);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isFalseTap(int i) {
        return this.mInternalFalsingManager.isFalseTap(i);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isFalseTouch(int i) {
        return this.mInternalFalsingManager.isFalseTouch(i);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isProximityNear() {
        return this.mInternalFalsingManager.isProximityNear();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isReportingEnabled() {
        return this.mInternalFalsingManager.isReportingEnabled();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isSimpleTap() {
        return this.mInternalFalsingManager.isSimpleTap();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean isUnlockingDisabled() {
        return this.mInternalFalsingManager.isUnlockingDisabled();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void onProximityEvent(FalsingManager.ProximityEvent proximityEvent) {
        this.mInternalFalsingManager.onProximityEvent(proximityEvent);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void onSuccessfulUnlock() {
        this.mInternalFalsingManager.onSuccessfulUnlock();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void removeFalsingBeliefListener(FalsingManager.FalsingBeliefListener falsingBeliefListener) {
        this.mInternalFalsingManager.removeFalsingBeliefListener(falsingBeliefListener);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final void removeTapListener(FalsingManager.FalsingTapListener falsingTapListener) {
        this.mInternalFalsingManager.removeTapListener(falsingTapListener);
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final Uri reportRejectedTouch() {
        return this.mInternalFalsingManager.reportRejectedTouch();
    }

    public final void setupFalsingManager() {
        FalsingManager falsingManager = this.mInternalFalsingManager;
        if (falsingManager != null) {
            falsingManager.cleanupInternal();
        }
        this.mInternalFalsingManager = (FalsingManager) this.mBrightLineFalsingManagerProvider.get();
    }

    @Override // com.android.systemui.plugins.FalsingManager
    public final boolean shouldEnforceBouncer() {
        return this.mInternalFalsingManager.shouldEnforceBouncer();
    }
}

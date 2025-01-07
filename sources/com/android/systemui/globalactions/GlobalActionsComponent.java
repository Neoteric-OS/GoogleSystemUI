package com.android.systemui.globalactions;

import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.CoreStartable;
import com.android.systemui.plugins.GlobalActions;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl$ExtensionBuilder$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl.ExtensionImpl;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl.ExtensionImpl.PluginItem;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GlobalActionsComponent implements CoreStartable, CommandQueue.Callbacks, GlobalActions.GlobalActionsManager {
    public IStatusBarService mBarService;
    public final CommandQueue mCommandQueue;
    public ExtensionControllerImpl.ExtensionImpl mExtension;
    public final ExtensionControllerImpl mExtensionController;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mGlobalActionsProvider;
    public GlobalActions mPlugin;
    public StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;

    public GlobalActionsComponent(CommandQueue commandQueue, ExtensionControllerImpl extensionControllerImpl, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, StatusBarKeyguardViewManager statusBarKeyguardViewManager) {
        this.mCommandQueue = commandQueue;
        this.mExtensionController = extensionControllerImpl;
        this.mGlobalActionsProvider = switchingProvider;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void handleShowGlobalActionsMenu() {
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
        statusBarKeyguardViewManager.mGlobalActionsVisible = true;
        statusBarKeyguardViewManager.updateStates();
        ((GlobalActions) this.mExtension.mItem).showGlobalActions(this);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void handleShowShutdownUi(String str, boolean z) {
        ((GlobalActions) this.mExtension.mItem).showShutdownUi(z, str);
    }

    @Override // com.android.systemui.plugins.GlobalActions.GlobalActionsManager
    public final void onGlobalActionsHidden() {
        try {
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
            statusBarKeyguardViewManager.mGlobalActionsVisible = false;
            statusBarKeyguardViewManager.updateStates();
            this.mBarService.onGlobalActionsHidden();
        } catch (RemoteException unused) {
        }
    }

    @Override // com.android.systemui.plugins.GlobalActions.GlobalActionsManager
    public final void onGlobalActionsShown() {
        try {
            this.mBarService.onGlobalActionsShown();
        } catch (RemoteException unused) {
        }
    }

    @Override // com.android.systemui.plugins.GlobalActions.GlobalActionsManager
    public final void reboot(boolean z) {
        try {
            this.mBarService.reboot(z);
        } catch (RemoteException unused) {
        }
    }

    @Override // com.android.systemui.plugins.GlobalActions.GlobalActionsManager
    public final void shutdown() {
        try {
            this.mBarService.shutdown();
        } catch (RemoteException unused) {
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
        ExtensionControllerImpl extensionControllerImpl = this.mExtensionController;
        extensionControllerImpl.getClass();
        ExtensionControllerImpl.ExtensionImpl extensionImpl = extensionControllerImpl.new ExtensionImpl();
        extensionImpl.mProducers.add(extensionImpl.new PluginItem(PluginManager.Helper.getAction(GlobalActions.class), GlobalActions.class));
        final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.mGlobalActionsProvider;
        Objects.requireNonNull(switchingProvider);
        extensionImpl.mProducers.add(new ExtensionControllerImpl.ExtensionImpl.Default(new Supplier() { // from class: com.android.systemui.globalactions.GlobalActionsComponent$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return (GlobalActions) DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider.this.get();
            }
        }));
        extensionImpl.mCallbacks.add(new Consumer() { // from class: com.android.systemui.globalactions.GlobalActionsComponent$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GlobalActionsComponent globalActionsComponent = GlobalActionsComponent.this;
                GlobalActions globalActions = (GlobalActions) obj;
                GlobalActions globalActions2 = globalActionsComponent.mPlugin;
                if (globalActions2 != null) {
                    globalActions2.destroy();
                }
                globalActionsComponent.mPlugin = globalActions;
            }
        });
        Collections.sort(extensionImpl.mProducers, Comparator.comparingInt(new ExtensionControllerImpl$ExtensionBuilder$$ExternalSyntheticLambda0()));
        ExtensionControllerImpl.ExtensionImpl.m887$$Nest$mnotifyChanged(extensionImpl);
        this.mExtension = extensionImpl;
        this.mPlugin = (GlobalActions) extensionImpl.mItem;
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
    }
}

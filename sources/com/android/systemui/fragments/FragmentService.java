package com.android.systemui.fragments;

import android.content.res.Configuration;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.leak.LeakDetector;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$23;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.io.PrintWriter;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FragmentService implements Dumpable {
    public final AnonymousClass1 mConfigurationListener;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$23 mFragmentHostManagerFactory;
    public final ArrayMap mHosts = new ArrayMap();
    public final ArrayMap mInjectionMap = new ArrayMap();
    public final Handler mHandler = new Handler();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FragmentHostState {
        public final FragmentHostManager mFragmentHostManager;

        public FragmentHostState(View view) {
            DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = FragmentService.this.mFragmentHostManagerFactory.this$0;
            this.mFragmentHostManager = new FragmentHostManager(view, (FragmentService) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).fragmentServiceProvider.get(), (LeakDetector) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).providesLeakDetectorProvider.get());
        }
    }

    public FragmentService(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$23 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$23, ConfigurationController configurationController, DumpManager dumpManager) {
        ConfigurationController.ConfigurationListener configurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.fragments.FragmentService.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(final Configuration configuration) {
                for (final FragmentHostState fragmentHostState : FragmentService.this.mHosts.values()) {
                    FragmentService.this.mHandler.post(new Runnable() { // from class: com.android.systemui.fragments.FragmentService$FragmentHostState$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            FragmentService.FragmentHostState fragmentHostState2 = FragmentService.FragmentHostState.this;
                            Configuration configuration2 = configuration;
                            FragmentHostManager fragmentHostManager = fragmentHostState2.mFragmentHostManager;
                            if (fragmentHostManager.mConfigChanges.applyNewConfig(fragmentHostManager.mContext.getResources())) {
                                fragmentHostManager.reloadFragments();
                            } else {
                                fragmentHostManager.mFragments.dispatchConfigurationChanged(configuration2);
                            }
                        }
                    });
                }
            }
        };
        this.mFragmentHostManagerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$23;
        ((ConfigurationControllerImpl) configurationController).addCallback(configurationListener);
        dumpManager.registerNormalDumpable(this);
    }

    public final void addFragmentInstantiationProvider(Class cls, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        String name = cls.getName();
        if (!this.mInjectionMap.containsKey(name)) {
            this.mInjectionMap.put(name, switchingProvider);
            return;
        }
        Log.w("FragmentService", "Fragment " + name + " is already provided by different Dagger component; Not adding method");
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Dumping fragments:");
        Iterator it = this.mHosts.values().iterator();
        while (it.hasNext()) {
            ((FragmentHostState) it.next()).mFragmentHostManager.mFragments.getFragmentManager().dump("  ", null, printWriter, strArr);
        }
    }

    public final FragmentHostManager getFragmentHostManager(View view) {
        View rootView = view.getRootView();
        FragmentHostState fragmentHostState = (FragmentHostState) this.mHosts.get(rootView);
        if (fragmentHostState == null) {
            fragmentHostState = new FragmentHostState(rootView);
            this.mHosts.put(rootView, fragmentHostState);
        }
        return fragmentHostState.mFragmentHostManager;
    }
}

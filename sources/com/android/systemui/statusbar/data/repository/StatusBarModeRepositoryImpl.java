package com.android.systemui.statusbar.data.repository;

import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.core.StatusBarInitializer$OnStatusBarViewInitializedListener;
import com.android.systemui.statusbar.phone.LetterboxAppearanceCalculator;
import com.android.systemui.statusbar.phone.ongoingcall.data.repository.OngoingCallRepository;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$16;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.io.PrintWriter;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarModeRepositoryImpl implements CoreStartable, StatusBarInitializer$OnStatusBarViewInitializedListener {
    public final StatusBarModePerDisplayRepositoryImpl defaultDisplay;

    public StatusBarModeRepositoryImpl(int i, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$16 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$16) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$16.this$0;
        CoroutineScope coroutineScope = (CoroutineScope) switchingProvider.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        this.defaultDisplay = new StatusBarModePerDisplayRepositoryImpl(coroutineScope, i, (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get(), (LetterboxAppearanceCalculator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.letterboxAppearanceCalculatorProvider.get(), (OngoingCallRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.ongoingCallRepositoryProvider.get());
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        this.defaultDisplay.dump(printWriter, strArr);
    }

    @Override // com.android.systemui.statusbar.core.StatusBarInitializer$OnStatusBarViewInitializedListener
    public final void onStatusBarViewInitialized(DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) {
        this.defaultDisplay.onStatusBarViewInitialized(daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        StatusBarModePerDisplayRepositoryImpl statusBarModePerDisplayRepositoryImpl = this.defaultDisplay;
        statusBarModePerDisplayRepositoryImpl.commandQueue.addCallback((CommandQueue.Callbacks) statusBarModePerDisplayRepositoryImpl.commandQueueCallback);
    }
}

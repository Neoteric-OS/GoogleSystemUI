package com.android.systemui.statusbar.phone.fragment;

import com.android.systemui.CoreStartable;
import com.android.systemui.fragments.FragmentService;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CollapsedStatusBarFragmentStartable implements CoreStartable {
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider collapsedstatusBarFragmentProvider;
    public final FragmentService fragmentService;

    public CollapsedStatusBarFragmentStartable(FragmentService fragmentService, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.fragmentService = fragmentService;
        this.collapsedstatusBarFragmentProvider = switchingProvider;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.fragmentService.addFragmentInstantiationProvider(CollapsedStatusBarFragment.class, this.collapsedstatusBarFragmentProvider);
    }
}

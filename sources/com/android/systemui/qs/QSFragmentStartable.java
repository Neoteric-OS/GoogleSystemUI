package com.android.systemui.qs;

import com.android.systemui.CoreStartable;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.qs.composefragment.QSFragmentCompose;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSFragmentStartable implements CoreStartable {
    public final FragmentService fragmentService;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider qsFragmentComposeProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider qsFragmentLegacyProvider;

    public QSFragmentStartable(FragmentService fragmentService, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider2) {
        this.fragmentService = fragmentService;
        this.qsFragmentLegacyProvider = switchingProvider;
        this.qsFragmentComposeProvider = switchingProvider2;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        FragmentService fragmentService = this.fragmentService;
        fragmentService.addFragmentInstantiationProvider(QSFragmentLegacy.class, this.qsFragmentLegacyProvider);
        fragmentService.addFragmentInstantiationProvider(QSFragmentCompose.class, this.qsFragmentComposeProvider);
    }
}

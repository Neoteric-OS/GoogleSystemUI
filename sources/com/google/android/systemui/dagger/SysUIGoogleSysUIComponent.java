package com.google.android.systemui.dagger;

import com.android.systemui.SystemUIAppComponentFactoryBase;
import com.android.systemui.dagger.SysUIComponent;
import com.android.systemui.keyguard.CustomizationProvider;
import com.android.systemui.keyguard.KeyguardSliceProvider;
import com.android.systemui.people.PeopleProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SysUIGoogleSysUIComponent extends SysUIComponent {
    @Override // com.android.systemui.dagger.SysUIComponent
    /* synthetic */ void inject(SystemUIAppComponentFactoryBase systemUIAppComponentFactoryBase);

    void inject(CustomizationProvider customizationProvider);

    @Override // com.android.systemui.dagger.SysUIComponent
    /* synthetic */ void inject(KeyguardSliceProvider keyguardSliceProvider);

    @Override // com.android.systemui.dagger.SysUIComponent
    /* synthetic */ void inject(PeopleProvider peopleProvider);
}

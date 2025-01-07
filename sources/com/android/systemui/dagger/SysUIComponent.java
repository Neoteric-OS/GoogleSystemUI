package com.android.systemui.dagger;

import com.android.systemui.SystemUIAppComponentFactoryBase;
import com.android.systemui.keyguard.KeyguardSliceProvider;
import com.android.systemui.people.PeopleProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface SysUIComponent {
    void inject(SystemUIAppComponentFactoryBase systemUIAppComponentFactoryBase);

    void inject(KeyguardSliceProvider keyguardSliceProvider);

    void inject(PeopleProvider peopleProvider);
}

package com.android.systemui.statusbar.notification.collection.provider;

import android.content.Context;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SectionHeaderVisibilityProvider {
    public final boolean neverShowSectionHeaders;
    public boolean sectionHeadersVisible = true;

    public SectionHeaderVisibilityProvider(Context context) {
        this.neverShowSectionHeaders = context.getResources().getBoolean(R.bool.config_notification_never_show_section_headers);
    }
}

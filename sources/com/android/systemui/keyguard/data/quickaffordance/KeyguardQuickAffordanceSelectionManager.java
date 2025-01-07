package com.android.systemui.keyguard.data.quickaffordance;

import java.util.List;
import java.util.Map;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface KeyguardQuickAffordanceSelectionManager {
    Map getSelections();

    /* renamed from: getSelections */
    Flow mo824getSelections();

    void setSelections(String str, List list);
}

package com.android.systemui.volume.panel.ui.layout;

import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.volume.panel.ui.viewmodel.ComponentState;
import com.android.systemui.volume.panel.ui.viewmodel.ComponentStateKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ComponentsLayoutKt {
    public static final String toLogString(ComponentsLayout componentsLayout) {
        String joinToString$default = CollectionsKt.joinToString$default(componentsLayout.headerComponents, null, null, null, new Function1() { // from class: com.android.systemui.volume.panel.ui.layout.ComponentsLayoutKt$toLogString$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ComponentStateKt.toLogString((ComponentState) obj);
            }
        }, 31);
        String joinToString$default2 = CollectionsKt.joinToString$default(componentsLayout.contentComponents, null, null, null, new Function1() { // from class: com.android.systemui.volume.panel.ui.layout.ComponentsLayoutKt$toLogString$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ComponentStateKt.toLogString((ComponentState) obj);
            }
        }, 31);
        String joinToString$default3 = CollectionsKt.joinToString$default(componentsLayout.footerComponents, null, null, null, new Function1() { // from class: com.android.systemui.volume.panel.ui.layout.ComponentsLayoutKt$toLogString$3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ComponentStateKt.toLogString((ComponentState) obj);
            }
        }, 31);
        String logString = ComponentStateKt.toLogString(componentsLayout.bottomBarComponent);
        StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("( headerComponents=", joinToString$default, " contentComponents=", joinToString$default2, " footerComponents=");
        m.append(joinToString$default3);
        m.append(" bottomBarComponent=");
        m.append(logString);
        m.append(" )");
        return m.toString();
    }
}

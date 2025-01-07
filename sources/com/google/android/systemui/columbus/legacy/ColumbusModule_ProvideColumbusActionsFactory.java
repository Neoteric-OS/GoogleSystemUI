package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.actions.Action;
import com.google.android.systemui.columbus.legacy.actions.UnpinNotifications;
import com.google.android.systemui.columbus.legacy.actions.UserSelectedAction;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.SpreadBuilder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ColumbusModule_ProvideColumbusActionsFactory implements Provider {
    public static List provideColumbusActions(List list, UnpinNotifications unpinNotifications, UserSelectedAction userSelectedAction) {
        SpreadBuilder spreadBuilder = new SpreadBuilder(3);
        spreadBuilder.addSpread(list.toArray(new Action[0]));
        spreadBuilder.add(unpinNotifications);
        spreadBuilder.add(userSelectedAction);
        List listOf = CollectionsKt__CollectionsKt.listOf(spreadBuilder.list.toArray(new Action[spreadBuilder.list.size()]));
        Preconditions.checkNotNullFromProvides(listOf);
        return listOf;
    }
}

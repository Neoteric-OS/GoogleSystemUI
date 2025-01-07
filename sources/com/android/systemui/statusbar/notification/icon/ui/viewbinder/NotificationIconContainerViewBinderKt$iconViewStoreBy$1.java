package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.icon.IconPack;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconContainerViewBinderKt$iconViewStoreBy$1 implements NotificationIconContainerViewBinder.IconViewStore {
    public final /* synthetic */ Lambda $block;
    public final /* synthetic */ NotifCollection $this_iconViewStoreBy;

    /* JADX WARN: Multi-variable type inference failed */
    public NotificationIconContainerViewBinderKt$iconViewStoreBy$1(NotifCollection notifCollection, Function1 function1) {
        this.$this_iconViewStoreBy = notifCollection;
        this.$block = (Lambda) function1;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder.IconViewStore
    public final StatusBarIconView iconView(String str) {
        IconPack iconPack;
        NotificationEntry entry = this.$this_iconViewStoreBy.getEntry(str);
        if (entry == null || (iconPack = entry.mIcons) == null) {
            return null;
        }
        return (StatusBarIconView) this.$block.invoke(iconPack);
    }
}

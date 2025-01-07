package com.android.systemui.statusbar.notification.row;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import java.util.Iterator;
import kotlin.collections.builders.MapBuilder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifLayoutInflaterFactory implements LayoutInflater.Factory2 {
    public final int layoutType;
    public final NotifRemoteViewsFactoryContainerImpl notifRemoteViewsFactoryContainer;
    public final ExpandableNotificationRow row;

    public NotifLayoutInflaterFactory(ExpandableNotificationRow expandableNotificationRow, int i, NotifRemoteViewsFactoryContainerImpl notifRemoteViewsFactoryContainerImpl) {
        this.row = expandableNotificationRow;
        this.layoutType = i;
        this.notifRemoteViewsFactoryContainer = notifRemoteViewsFactoryContainerImpl;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        Iterator it = this.notifRemoteViewsFactoryContainer.factories.iterator();
        View view2 = null;
        NotifRemoteViewsFactory notifRemoteViewsFactory = null;
        while (((MapBuilder.KeysItr) it).hasNext()) {
            NotifRemoteViewsFactory notifRemoteViewsFactory2 = (NotifRemoteViewsFactory) ((MapBuilder.KeysItr) it).next();
            View instantiate = notifRemoteViewsFactory2.instantiate(this.row, this.layoutType, str, context, attributeSet);
            if (instantiate != null) {
                if (notifRemoteViewsFactory != null) {
                    throw new IllegalStateException((notifRemoteViewsFactory2 + " tries to produce name:" + str + " with type:" + this.layoutType + ". However, " + notifRemoteViewsFactory + " produced view for " + str + " before.").toString());
                }
                notifRemoteViewsFactory = notifRemoteViewsFactory2;
                view2 = instantiate;
            }
        }
        return view2;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}

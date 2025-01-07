package com.android.systemui.statusbar.notification.collection;

import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender;
import com.android.systemui.statusbar.notification.collection.render.RenderStageManager;
import com.android.systemui.util.Assert;
import com.android.systemui.util.NamedListenerSet;
import java.util.Collection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifPipeline implements CommonNotifCollection {
    public final NotifCollection mNotifCollection;
    public final RenderStageManager mRenderStageManager;
    public final ShadeListBuilder mShadeListBuilder;

    public NotifPipeline(NotifCollection notifCollection, ShadeListBuilder shadeListBuilder, RenderStageManager renderStageManager) {
        this.mNotifCollection = notifCollection;
        this.mShadeListBuilder = shadeListBuilder;
        this.mRenderStageManager = renderStageManager;
    }

    public final void addCollectionListener(NotifCollectionListener notifCollectionListener) {
        NotifCollection notifCollection = this.mNotifCollection;
        notifCollection.getClass();
        Assert.isMainThread();
        NamedListenerSet namedListenerSet = notifCollection.mNotifCollectionListeners;
        namedListenerSet.listeners.addIfAbsent(new NamedListenerSet.NamedListener(namedListenerSet, notifCollectionListener));
    }

    public final void addFinalizeFilter(NotifFilter notifFilter) {
        ShadeListBuilder shadeListBuilder = this.mShadeListBuilder;
        shadeListBuilder.getClass();
        Assert.isMainThread();
        shadeListBuilder.mPipelineState.requireState();
        shadeListBuilder.mNotifFinalizeFilters.add(notifFilter);
        notifFilter.mListener = new ShadeListBuilder$$ExternalSyntheticLambda0(shadeListBuilder, 6);
    }

    public final void addNotificationLifetimeExtender(NotifLifetimeExtender notifLifetimeExtender) {
        NotifCollection notifCollection = this.mNotifCollection;
        notifCollection.getClass();
        Assert.isMainThread();
        notifCollection.checkForReentrantCall();
        if (!notifCollection.mLifetimeExtenders.contains(notifLifetimeExtender)) {
            notifCollection.mLifetimeExtenders.add(notifLifetimeExtender);
            notifLifetimeExtender.setCallback(new NotifCollection$$ExternalSyntheticLambda12(notifCollection));
        } else {
            throw new IllegalArgumentException("Extender " + notifLifetimeExtender + " already added.");
        }
    }

    public final void addOnBeforeFinalizeFilterListener(OnBeforeFinalizeFilterListener onBeforeFinalizeFilterListener) {
        ShadeListBuilder shadeListBuilder = this.mShadeListBuilder;
        shadeListBuilder.getClass();
        Assert.isMainThread();
        shadeListBuilder.mPipelineState.requireState();
        NamedListenerSet namedListenerSet = shadeListBuilder.mOnBeforeFinalizeFilterListeners;
        namedListenerSet.listeners.addIfAbsent(new NamedListenerSet.NamedListener(namedListenerSet, onBeforeFinalizeFilterListener));
    }

    public final void addOnBeforeRenderListListener(OnBeforeRenderListListener onBeforeRenderListListener) {
        ShadeListBuilder shadeListBuilder = this.mShadeListBuilder;
        shadeListBuilder.getClass();
        Assert.isMainThread();
        shadeListBuilder.mPipelineState.requireState();
        NamedListenerSet namedListenerSet = shadeListBuilder.mOnBeforeRenderListListeners;
        namedListenerSet.listeners.addIfAbsent(new NamedListenerSet.NamedListener(namedListenerSet, onBeforeRenderListListener));
    }

    public final void addPreGroupFilter(NotifFilter notifFilter) {
        ShadeListBuilder shadeListBuilder = this.mShadeListBuilder;
        shadeListBuilder.getClass();
        Assert.isMainThread();
        shadeListBuilder.mPipelineState.requireState();
        shadeListBuilder.mNotifPreGroupFilters.add(notifFilter);
        notifFilter.mListener = new ShadeListBuilder$$ExternalSyntheticLambda0(shadeListBuilder, 5);
    }

    public final Collection getAllNotifs() {
        NotifCollection notifCollection = this.mNotifCollection;
        notifCollection.getClass();
        Assert.isMainThread();
        return notifCollection.mReadOnlyNotificationSet;
    }
}

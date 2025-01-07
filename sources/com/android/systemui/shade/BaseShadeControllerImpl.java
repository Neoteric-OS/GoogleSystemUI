package com.android.systemui.shade;

import android.os.Trace;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BaseShadeControllerImpl implements ShadeController {
    public final Lazy assistManagerLazy;
    public final CommandQueue commandQueue;
    public StatusBarNotificationPresenter notifPresenter;
    public final NotificationShadeWindowController notificationShadeWindowController;
    public final ArrayList postCollapseActions = new ArrayList();
    public final StatusBarKeyguardViewManager statusBarKeyguardViewManager;

    public BaseShadeControllerImpl(CommandQueue commandQueue, StatusBarKeyguardViewManager statusBarKeyguardViewManager, NotificationShadeWindowController notificationShadeWindowController, Lazy lazy) {
        this.commandQueue = commandQueue;
        this.statusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.notificationShadeWindowController = notificationShadeWindowController;
        this.assistManagerLazy = lazy;
    }

    public final void animateExpandQs() {
        if (isShadeEnabled()) {
            expandToQs();
        }
    }

    public final void animateExpandShade() {
        if (isShadeEnabled()) {
            expandToNotifications();
        }
    }

    public abstract void expandToNotifications();

    public abstract void expandToQs();

    public final void onClosingFinished() {
        runPostCollapseActions();
        StatusBarNotificationPresenter statusBarNotificationPresenter = this.notifPresenter;
        if (statusBarNotificationPresenter == null) {
            statusBarNotificationPresenter = null;
        }
        if (statusBarNotificationPresenter.mPanelExpansionInteractor.isFullyCollapsed()) {
            return;
        }
        ((NotificationShadeWindowControllerImpl) this.notificationShadeWindowController).setNotificationShadeFocusable(true);
    }

    @Override // com.android.systemui.shade.ShadeController
    public void postAnimateExpandQs() {
        expandToQs();
    }

    public final void runPostCollapseActions() {
        ArrayList arrayList = new ArrayList(this.postCollapseActions);
        this.postCollapseActions.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        KeyguardViewMediator.AnonymousClass4 anonymousClass4 = this.statusBarKeyguardViewManager.mViewMediatorCallback;
        anonymousClass4.getClass();
        Trace.beginSection("KeyguardViewMediator.mViewMediatorCallback#readyForKeyguardDone");
        KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
        if (keyguardViewMediator.mKeyguardDonePending) {
            keyguardViewMediator.mKeyguardDonePending = false;
            keyguardViewMediator.tryKeyguardDone();
        }
        Trace.endSection();
    }

    @Override // com.android.systemui.CoreStartable
    public void start() {
    }
}

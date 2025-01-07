package com.android.systemui.wmshell;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProviderImpl;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.android.systemui.wmshell.BubblesManager;
import com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda1;
import com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda5;
import java.util.ArrayList;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubblesManager$5$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ BubblesManager.AnonymousClass5 f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ Object f$2;

    public /* synthetic */ BubblesManager$5$$ExternalSyntheticLambda1(BubblesManager.AnonymousClass5 anonymousClass5, String str, BubbleController$$ExternalSyntheticLambda1 bubbleController$$ExternalSyntheticLambda1) {
        this.f$0 = anonymousClass5;
        this.f$1 = str;
        this.f$2 = bubbleController$$ExternalSyntheticLambda1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BubblesManager.AnonymousClass5 anonymousClass5 = this.f$0;
                Set set = (Set) this.f$1;
                BubbleController$$ExternalSyntheticLambda5 bubbleController$$ExternalSyntheticLambda5 = (BubbleController$$ExternalSyntheticLambda5) this.f$2;
                anonymousClass5.getClass();
                ArrayList arrayList = new ArrayList();
                BubblesManager bubblesManager = BubblesManager.this;
                for (NotificationEntry notificationEntry : ((NotifPipeline) bubblesManager.mCommonNotifCollection).getAllNotifs()) {
                    if (((NotificationLockscreenUserManagerImpl) bubblesManager.mNotifUserManager).isCurrentProfile(notificationEntry.mSbn.getUserId()) && set.contains(notificationEntry.mKey)) {
                        VisualInterruptionDecisionProviderImpl visualInterruptionDecisionProviderImpl = (VisualInterruptionDecisionProviderImpl) bubblesManager.mVisualInterruptionDecisionProvider;
                        visualInterruptionDecisionProviderImpl.getClass();
                        boolean isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("VisualInterruptionDecisionProviderImpl#makeAndLogBubbleDecision");
                        }
                        try {
                            if (!visualInterruptionDecisionProviderImpl.started) {
                                throw new IllegalStateException("Check failed.");
                            }
                            VisualInterruptionType visualInterruptionType = VisualInterruptionType.BUBBLE;
                            VisualInterruptionDecisionProviderImpl.LoggableDecision checkConditions = visualInterruptionDecisionProviderImpl.checkConditions(visualInterruptionType);
                            if (checkConditions == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkFilters(visualInterruptionType, notificationEntry)) == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkSuppressInterruptions(notificationEntry)) == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkSuppressAwakeInterruptions(notificationEntry)) == null) {
                                checkConditions = VisualInterruptionDecisionProviderImpl.LoggableDecision.unsuppressed;
                            }
                            visualInterruptionDecisionProviderImpl.logDecision(visualInterruptionType, notificationEntry, checkConditions);
                            if (checkConditions.decision.shouldInterrupt && notificationEntry.isBubble()) {
                                arrayList.add(bubblesManager.notifToBubbleEntry(notificationEntry));
                            }
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                    }
                }
                bubbleController$$ExternalSyntheticLambda5.accept(arrayList);
                return;
            default:
                BubblesManager.AnonymousClass5 anonymousClass52 = this.f$0;
                String str = (String) this.f$1;
                BubbleController$$ExternalSyntheticLambda1 bubbleController$$ExternalSyntheticLambda1 = (BubbleController$$ExternalSyntheticLambda1) this.f$2;
                BubblesManager bubblesManager2 = BubblesManager.this;
                NotificationEntry entry = ((NotifPipeline) bubblesManager2.mCommonNotifCollection).mNotifCollection.getEntry(str);
                bubbleController$$ExternalSyntheticLambda1.accept(entry == null ? null : bubblesManager2.notifToBubbleEntry(entry));
                return;
        }
    }

    public /* synthetic */ BubblesManager$5$$ExternalSyntheticLambda1(BubblesManager.AnonymousClass5 anonymousClass5, Set set, BubbleController$$ExternalSyntheticLambda5 bubbleController$$ExternalSyntheticLambda5) {
        this.f$0 = anonymousClass5;
        this.f$1 = set;
        this.f$2 = bubbleController$$ExternalSyntheticLambda5;
    }
}

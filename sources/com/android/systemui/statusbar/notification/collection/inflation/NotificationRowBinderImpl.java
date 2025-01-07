package com.android.systemui.statusbar.notification.collection.inflation;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.view.View;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.internal.util.NotificationMessagingUtil;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.notification.NotificationClicker;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotifInflaterImpl;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflater;
import com.android.systemui.statusbar.notification.icon.IconManager;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotifBindPipeline;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.RowContentBindParams;
import com.android.systemui.statusbar.notification.row.RowContentBindStage;
import com.android.systemui.statusbar.notification.row.RowInflaterTask;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$$ExternalSyntheticLambda2;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.util.Iterator;
import java.util.Objects;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationRowBinderImpl {
    public final Context mContext;
    public final DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl mExpandableNotificationRowComponentBuilder;
    public final FeatureFlags mFeatureFlags;
    public final IconManager mIconManager;
    public NotificationStackScrollLayoutController.NotificationListContainerImpl mListContainer;
    public final NotificationRowBinderLogger mLogger;
    public final NotificationMessagingUtil mMessagingUtil;
    public final NotifBindPipeline mNotifBindPipeline;
    public NotificationClicker mNotificationClicker;
    public final NotificationRemoteInputManager mNotificationRemoteInputManager;
    public StatusBarNotificationPresenter mPresenter;
    public final RowContentBindStage mRowContentBindStage;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mRowInflaterTaskProvider;

    public NotificationRowBinderImpl(Context context, NotificationMessagingUtil notificationMessagingUtil, NotificationRemoteInputManager notificationRemoteInputManager, NotificationLockscreenUserManager notificationLockscreenUserManager, NotifBindPipeline notifBindPipeline, RowContentBindStage rowContentBindStage, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl daggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl, IconManager iconManager, NotificationRowBinderLogger notificationRowBinderLogger, FeatureFlags featureFlags) {
        this.mContext = context;
        this.mNotifBindPipeline = notifBindPipeline;
        this.mRowContentBindStage = rowContentBindStage;
        this.mMessagingUtil = notificationMessagingUtil;
        this.mNotificationRemoteInputManager = notificationRemoteInputManager;
        this.mRowInflaterTaskProvider = switchingProvider;
        this.mExpandableNotificationRowComponentBuilder = daggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl;
        this.mIconManager = iconManager;
        this.mLogger = notificationRowBinderLogger;
        this.mFeatureFlags = featureFlags;
    }

    public final void inflateContentViews(final NotificationEntry notificationEntry, NotifInflater.Params params, final ExpandableNotificationRow expandableNotificationRow, final NotifInflaterImpl.AnonymousClass1 anonymousClass1) {
        final boolean isImportantMessaging = this.mMessagingUtil.isImportantMessaging(notificationEntry.mSbn, notificationEntry.mRanking.getImportance());
        expandableNotificationRow.mShowSnooze = params.showSnooze;
        RowContentBindStage rowContentBindStage = this.mRowContentBindStage;
        RowContentBindParams rowContentBindParams = (RowContentBindParams) rowContentBindStage.getStageParams(notificationEntry);
        rowContentBindParams.requireContentViews(1);
        rowContentBindParams.requireContentViews(2);
        if (rowContentBindParams.mUseIncreasedHeight != isImportantMessaging) {
            rowContentBindParams.mDirtyContentViews = 1 | rowContentBindParams.mDirtyContentViews;
        }
        rowContentBindParams.mUseIncreasedHeight = isImportantMessaging;
        boolean z = rowContentBindParams.mUseMinimized;
        final boolean z2 = params.isMinimized;
        if (z != z2) {
            rowContentBindParams.mDirtyContentViews |= 3;
        }
        rowContentBindParams.mUseMinimized = z2;
        if (params.needsRedaction) {
            rowContentBindParams.requireContentViews(8);
        } else {
            rowContentBindParams.markContentViewsFreeable(8);
        }
        if (params.isChildInGroup) {
            rowContentBindParams.requireContentViews(16);
        } else {
            rowContentBindParams.markContentViewsFreeable(16);
        }
        if (params.isGroupSummary) {
            rowContentBindParams.requireContentViews(32);
            if (z2) {
                rowContentBindParams.requireContentViews(64);
            }
        } else {
            rowContentBindParams.markContentViewsFreeable(32);
            rowContentBindParams.markContentViewsFreeable(64);
        }
        rowContentBindParams.mDirtyContentViews = rowContentBindParams.mContentViews;
        NotificationRowBinderLogger notificationRowBinderLogger = this.mLogger;
        LogLevel logLevel = LogLevel.DEBUG;
        NotificationRowBinderLogger$logRequestingRebind$2 notificationRowBinderLogger$logRequestingRebind$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderLogger$logRequestingRebind$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("requesting rebind for ", logMessage.getStr1(), ": ", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = notificationRowBinderLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotificationRowBinder", logLevel, notificationRowBinderLogger$logRequestingRebind$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = notificationEntry.mKey;
        logMessageImpl.str2 = params.reason;
        logBuffer.commit(obtain);
        rowContentBindStage.requestRebind(notificationEntry, new NotifBindPipeline.BindCallback() { // from class: com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderImpl$$ExternalSyntheticLambda1
            @Override // com.android.systemui.statusbar.notification.row.NotifBindPipeline.BindCallback
            public final void onBindFinished(NotificationEntry notificationEntry2) {
                NotificationRowBinderLogger notificationRowBinderLogger2 = NotificationRowBinderImpl.this.mLogger;
                LogLevel logLevel2 = LogLevel.DEBUG;
                NotificationRowBinderLogger$logRebindComplete$2 notificationRowBinderLogger$logRebindComplete$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderLogger$logRebindComplete$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("rebind complete for ", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer2 = notificationRowBinderLogger2.buffer;
                LogMessage obtain2 = logBuffer2.obtain("NotificationRowBinder", logLevel2, notificationRowBinderLogger$logRebindComplete$2, null);
                ((LogMessageImpl) obtain2).str1 = notificationEntry.mKey;
                logBuffer2.commit(obtain2);
                boolean z3 = isImportantMessaging;
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                expandableNotificationRow2.mUseIncreasedCollapsedHeight = z3;
                expandableNotificationRow2.setIsMinimized(z2);
                anonymousClass1.onAsyncInflationFinished(notificationEntry2);
            }
        });
    }

    public final void inflateViews(NotificationEntry notificationEntry, NotifInflater.Params params, NotifInflaterImpl.AnonymousClass1 anonymousClass1) {
        NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayoutController.this.mView;
        notificationStackScrollLayout.getClass();
        boolean rowExists = notificationEntry.rowExists();
        NotificationRowBinderLogger notificationRowBinderLogger = this.mLogger;
        String str = params.reason;
        IconManager iconManager = this.mIconManager;
        LogBuffer logBuffer = notificationRowBinderLogger.buffer;
        if (!rowExists) {
            LogLevel logLevel = LogLevel.DEBUG;
            LogMessage obtain = logBuffer.obtain("NotificationRowBinder", logLevel, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderLogger$logCreatingRow$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("creating row for ", logMessage.getStr1(), ": ", logMessage.getStr2());
                }
            }, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logMessageImpl.str2 = str;
            logBuffer.commit(obtain);
            iconManager.createIcons(notificationEntry);
            LogMessage obtain2 = logBuffer.obtain("NotificationRowBinder", logLevel, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderLogger$logInflatingRow$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("inflating row for ", ((LogMessage) obj).getStr1());
                }
            }, null);
            ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain2);
            ((RowInflaterTask) this.mRowInflaterTaskProvider.get()).inflate(this.mContext, notificationStackScrollLayout, notificationEntry, null, new NotificationRowBinderImpl$$ExternalSyntheticLambda0(this, notificationEntry, params, anonymousClass1));
            return;
        }
        LogMessage obtain3 = logBuffer.obtain("NotificationRowBinder", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderLogger$logUpdatingRow$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("updating row for ", logMessage.getStr1(), ": ", logMessage.getStr2());
            }
        }, null);
        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain3;
        logMessageImpl2.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl2.str2 = str;
        logBuffer.commit(obtain3);
        boolean z = false;
        iconManager.updateIcons(notificationEntry, false);
        ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
        expandableNotificationRow.mShowingPublicInitialized = false;
        expandableNotificationRow.mDismissed = false;
        NotificationMenuRowPlugin notificationMenuRowPlugin = expandableNotificationRow.mMenuRow;
        if (notificationMenuRowPlugin == null || !notificationMenuRowPlugin.isMenuVisible()) {
            expandableNotificationRow.resetTranslation();
        }
        NotificationStackScrollLayout.AnonymousClass6 anonymousClass6 = expandableNotificationRow.mOnHeightChangedListener;
        if (anonymousClass6 != null) {
            NotificationStackScrollLayout notificationStackScrollLayout2 = NotificationStackScrollLayout.this;
            if ((notificationStackScrollLayout2.mAnimationsEnabled || notificationStackScrollLayout2.mPulsing) && (notificationStackScrollLayout2.mIsExpanded || NotificationStackScrollLayout.isPinnedHeadsUp(expandableNotificationRow))) {
                z = true;
            }
            expandableNotificationRow.setAnimationRunning(z);
            expandableNotificationRow.setChronometerRunning(notificationStackScrollLayout2.mIsExpanded);
            if (notificationStackScrollLayout2.mTopHeadsUpRow == expandableNotificationRow) {
                Iterator it = notificationStackScrollLayout2.mHeadsUpHeightChangedListeners.listeners.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
            }
        }
        expandableNotificationRow.requestLayout();
        expandableNotificationRow.mTargetPoint = null;
        updateRow(notificationEntry, expandableNotificationRow);
        inflateContentViews(notificationEntry, params, expandableNotificationRow, anonymousClass1);
    }

    public final void releaseViews(NotificationEntry notificationEntry) {
        boolean rowExists = notificationEntry.rowExists();
        LogBuffer logBuffer = this.mLogger.buffer;
        if (!rowExists) {
            LogMessage obtain = logBuffer.obtain("NotificationRowBinder", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderLogger$logNotReleasingViewsRowDoesntExist$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("not releasing views for ", ((LogMessage) obj).getStr1(), ": row doesn't exist");
                }
            }, null);
            ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain);
            return;
        }
        LogMessage obtain2 = logBuffer.obtain("NotificationRowBinder", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderLogger$logReleasingViews$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("releasing views for ", ((LogMessage) obj).getStr1());
            }
        }, null);
        ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain2);
        RowContentBindStage rowContentBindStage = this.mRowContentBindStage;
        RowContentBindParams rowContentBindParams = (RowContentBindParams) rowContentBindStage.getStageParams(notificationEntry);
        rowContentBindParams.markContentViewsFreeable(1);
        rowContentBindParams.markContentViewsFreeable(2);
        rowContentBindParams.markContentViewsFreeable(8);
        rowContentBindParams.markContentViewsFreeable(16);
        rowContentBindStage.requestRebind(notificationEntry, null);
    }

    /* JADX WARN: Type inference failed for: r6v3, types: [com.android.systemui.statusbar.notification.NotificationClicker$$ExternalSyntheticLambda0] */
    public final void updateRow(NotificationEntry notificationEntry, final ExpandableNotificationRow expandableNotificationRow) {
        int i = notificationEntry.targetSdk;
        boolean z = i >= 9 && i < 21;
        for (NotificationContentView notificationContentView : expandableNotificationRow.mLayouts) {
            notificationContentView.mLegacy = z;
            notificationContentView.updateLegacy();
        }
        final NotificationClicker notificationClicker = this.mNotificationClicker;
        Objects.requireNonNull(notificationClicker);
        Notification notification = notificationEntry.mSbn.getNotification();
        if (notification.contentIntent == null && notification.fullScreenIntent == null && !expandableNotificationRow.mEntry.isBubble()) {
            expandableNotificationRow.setOnClickListener(null);
            expandableNotificationRow.mOnDragSuccessListener = null;
            expandableNotificationRow.mBubbleClickListener = null;
            NotificationContentView notificationContentView2 = expandableNotificationRow.mPrivateLayout;
            notificationContentView2.applyBubbleAction(notificationContentView2.mExpandedChild, expandableNotificationRow.mEntry);
            NotificationContentView notificationContentView3 = expandableNotificationRow.mPublicLayout;
            notificationContentView3.applyBubbleAction(notificationContentView3.mExpandedChild, expandableNotificationRow.mEntry);
            return;
        }
        expandableNotificationRow.mBubbleClickListener = new View.OnClickListener() { // from class: com.android.systemui.statusbar.notification.NotificationClicker$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NotificationClicker notificationClicker2 = NotificationClicker.this;
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = notificationClicker2.mNotificationActivityStarter;
                NotificationEntry notificationEntry2 = expandableNotificationRow2.mEntry;
                statusBarNotificationActivityStarter.getClass();
                final StatusBarNotificationActivityStarter$$ExternalSyntheticLambda2 statusBarNotificationActivityStarter$$ExternalSyntheticLambda2 = new StatusBarNotificationActivityStarter$$ExternalSyntheticLambda2(statusBarNotificationActivityStarter, notificationEntry2, 0);
                if (notificationEntry2.isBubble()) {
                    statusBarNotificationActivityStarter$$ExternalSyntheticLambda2.run();
                } else {
                    statusBarNotificationActivityStarter.performActionAfterKeyguardDismissed(notificationEntry2, new StatusBarNotificationActivityStarter.OnKeyguardDismissedAction() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$$ExternalSyntheticLambda3
                        @Override // com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter.OnKeyguardDismissedAction
                        public final boolean onDismiss(PendingIntent pendingIntent, boolean z2, boolean z3, boolean z4) {
                            StatusBarNotificationActivityStarter$$ExternalSyntheticLambda2.this.run();
                            return false;
                        }
                    });
                }
            }
        };
        NotificationContentView notificationContentView4 = expandableNotificationRow.mPrivateLayout;
        notificationContentView4.applyBubbleAction(notificationContentView4.mExpandedChild, expandableNotificationRow.mEntry);
        NotificationContentView notificationContentView5 = expandableNotificationRow.mPublicLayout;
        notificationContentView5.applyBubbleAction(notificationContentView5.mExpandedChild, expandableNotificationRow.mEntry);
        expandableNotificationRow.setOnClickListener(notificationClicker);
        expandableNotificationRow.mOnDragSuccessListener = notificationClicker.mOnDragSuccessListener;
    }
}

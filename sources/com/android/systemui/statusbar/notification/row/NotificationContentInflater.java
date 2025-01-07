package com.android.systemui.statusbar.notification.row;

import android.app.Notification;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.os.SystemClock;
import android.os.Trace;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.view.NotificationHeaderView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import com.android.app.tracing.TraceUtils;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.widget.ImageMessageConsumer;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.media.controls.util.MediaFeatureFlag;
import com.android.systemui.statusbar.InflationTask;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.notification.ConversationNotificationProcessor;
import com.android.systemui.statusbar.notification.InflationException;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.NotificationRowContentBinder;
import com.android.systemui.statusbar.notification.row.RowContentBindStage;
import com.android.systemui.statusbar.notification.row.ui.viewbinder.SingleLineViewBinder;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.SingleLineViewModel;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.policy.InflatedSmartReplyState;
import com.android.systemui.statusbar.policy.InflatedSmartReplyViewHolder;
import com.android.systemui.statusbar.policy.SmartReplyStateInflater;
import com.android.systemui.statusbar.policy.SmartReplyStateInflaterImpl;
import com.android.systemui.util.Assert;
import com.android.systemui.util.Utils;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class NotificationContentInflater implements NotificationRowContentBinder {
    public final ConversationNotificationProcessor mConversationProcessor;
    public final HeadsUpStyleProviderImpl mHeadsUpStyleProvider;
    public boolean mInflateSynchronously = false;
    public final Executor mInflationExecutor;
    public final NotificationRowContentBinderLogger mLogger;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 mNotifLayoutInflaterFactoryProvider;
    public final NotificationRemoteInputManager mRemoteInputManager;
    public final NotifRemoteViewCache mRemoteViewCache;
    public final SmartReplyStateInflaterImpl mSmartReplyStateInflater;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    abstract class ApplyCallback {
        public abstract RemoteViews getRemoteView();

        public abstract void setResultView(View view);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AsyncInflationTask extends AsyncTask implements NotificationRowContentBinder.InflationCallback, InflationTask {
        public final RowContentBindStage.AnonymousClass1 mCallback;
        public CancellationSignal mCancellationSignal;
        public final Context mContext;
        public final ConversationNotificationProcessor mConversationProcessor;
        public final NotificationEntry mEntry;
        public Exception mError;
        public final HeadsUpStyleProviderImpl mHeadsUpStyleProvider;
        public final boolean mInflateSynchronously;
        public final Executor mInflationExecutor;
        public final boolean mIsMinimized;
        public final NotificationRowContentBinderLogger mLogger;
        public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 mNotifLayoutInflaterFactoryProvider;
        public final int mReInflateFlags;
        public final NotifRemoteViewCache mRemoteViewCache;
        public final NotificationRemoteInputManager.AnonymousClass1 mRemoteViewClickHandler;
        public final ExpandableNotificationRow mRow;
        public final SmartReplyStateInflaterImpl mSmartRepliesInflater;
        public final boolean mUsesIncreasedHeadsUpHeight;
        public final boolean mUsesIncreasedHeight;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class RtlEnabledContext extends ContextWrapper {
            @Override // android.content.ContextWrapper, android.content.Context
            public final ApplicationInfo getApplicationInfo() {
                ApplicationInfo applicationInfo = new ApplicationInfo(super.getApplicationInfo());
                applicationInfo.flags |= 4194304;
                return applicationInfo;
            }
        }

        public AsyncInflationTask(Executor executor, boolean z, int i, NotifRemoteViewCache notifRemoteViewCache, NotificationEntry notificationEntry, ConversationNotificationProcessor conversationNotificationProcessor, ExpandableNotificationRow expandableNotificationRow, boolean z2, boolean z3, boolean z4, RowContentBindStage.AnonymousClass1 anonymousClass1, NotificationRemoteInputManager.AnonymousClass1 anonymousClass12, SmartReplyStateInflaterImpl smartReplyStateInflaterImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26, HeadsUpStyleProviderImpl headsUpStyleProviderImpl, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
            this.mEntry = notificationEntry;
            this.mRow = expandableNotificationRow;
            this.mInflationExecutor = executor;
            this.mInflateSynchronously = z;
            this.mReInflateFlags = i;
            this.mRemoteViewCache = notifRemoteViewCache;
            this.mSmartRepliesInflater = smartReplyStateInflaterImpl;
            this.mContext = expandableNotificationRow.getContext();
            this.mIsMinimized = z2;
            this.mUsesIncreasedHeight = z3;
            this.mUsesIncreasedHeadsUpHeight = z4;
            this.mRemoteViewClickHandler = anonymousClass12;
            this.mCallback = anonymousClass1;
            this.mConversationProcessor = conversationNotificationProcessor;
            this.mNotifLayoutInflaterFactoryProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26;
            this.mHeadsUpStyleProvider = headsUpStyleProviderImpl;
            this.mLogger = notificationRowContentBinderLogger;
            notificationEntry.abortTask();
            notificationEntry.mRunningTask = this;
        }

        @Override // com.android.systemui.statusbar.InflationTask
        public final void abort() {
            this.mLogger.logAsyncTaskProgress(this.mEntry, "cancelling inflate");
            cancel(true);
            if (this.mCancellationSignal != null) {
                this.mLogger.logAsyncTaskProgress(this.mEntry, "cancelling apply");
                this.mCancellationSignal.cancel();
            }
            this.mLogger.logAsyncTaskProgress(this.mEntry, "aborted");
        }

        @Override // android.os.AsyncTask
        public final Object doInBackground(Object[] objArr) {
            InflationProgress inflationProgress;
            boolean isEnabled = Trace.isEnabled();
            if (isEnabled) {
                TraceUtilsKt.beginSlice("NotificationContentInflater.AsyncInflationTask#doInBackground");
            }
            try {
                try {
                    inflationProgress = doInBackgroundInternal();
                } catch (Exception e) {
                    this.mError = e;
                    this.mLogger.logAsyncTaskException(this.mEntry, "inflating", e);
                    inflationProgress = null;
                }
                return inflationProgress;
            } finally {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
            }
        }

        public final InflationProgress doInBackgroundInternal() {
            Set set;
            StatusBarNotification statusBarNotification = this.mEntry.mSbn;
            try {
                Notification.addFieldsFromContext(this.mContext.getPackageManager().getApplicationInfoAsUser(statusBarNotification.getPackageName(), 8192, UserHandle.getUserId(statusBarNotification.getUid())), statusBarNotification.getNotification());
            } catch (PackageManager.NameNotFoundException unused) {
            }
            Notification.Builder recoverBuilder = Notification.Builder.recoverBuilder(this.mContext, statusBarNotification.getNotification());
            Context packageContext = statusBarNotification.getPackageContext(this.mContext);
            if (recoverBuilder.usesTemplate()) {
                packageContext = new RtlEnabledContext(packageContext);
            }
            boolean isConversation = this.mEntry.mRanking.isConversation();
            Notification.MessagingStyle processNotification = isConversation ? this.mConversationProcessor.processNotification(this.mEntry, recoverBuilder, this.mLogger) : null;
            InflationProgress inflationProgress = (InflationProgress) TraceUtils.trace("NotificationContentInflater.createRemoteViews", new NotificationContentInflater$$ExternalSyntheticLambda0(this.mRow, this.mReInflateFlags, this.mLogger, recoverBuilder, this.mIsMinimized, this.mUsesIncreasedHeight, this.mHeadsUpStyleProvider, this.mUsesIncreasedHeadsUpHeight, this.mNotifLayoutInflaterFactoryProvider, packageContext));
            this.mLogger.logAsyncTaskProgress(this.mEntry, "getting existing smart reply state (on wrong thread!)");
            InflatedSmartReplyState inflatedSmartReplyState = this.mRow.mPrivateLayout.mCurrentSmartReplyState;
            this.mLogger.logAsyncTaskProgress(this.mEntry, "inflating smart reply views");
            NotificationContentInflater.inflateSmartReplyViews(inflationProgress, this.mReInflateFlags, this.mEntry, this.mContext, packageContext, inflatedSmartReplyState, this.mSmartRepliesInflater, this.mLogger);
            inflationProgress.mInflatedSingleLineViewModel = SingleLineViewInflater.inflateSingleLineViewModel(this.mEntry.mSbn.getNotification(), processNotification, recoverBuilder, this.mContext);
            inflationProgress.mInflatedSingleLineView = SingleLineViewInflater.inflatePrivateSingleLineView(isConversation, this.mReInflateFlags, this.mEntry, this.mContext, this.mLogger);
            this.mLogger.logAsyncTaskProgress(this.mEntry, "getting row image resolver (on wrong thread!)");
            NotificationInlineImageResolver notificationInlineImageResolver = this.mRow.mImageResolver;
            this.mLogger.logAsyncTaskProgress(this.mEntry, "waiting for preloaded images");
            if (notificationInlineImageResolver.hasCache() && (set = notificationInlineImageResolver.mWantedUriSet) != null) {
                set.forEach(new NotificationInlineImageResolver$$ExternalSyntheticLambda1(notificationInlineImageResolver, SystemClock.elapsedRealtime() + 1000));
            }
            return inflationProgress;
        }

        public int getReInflateFlags() {
            return this.mReInflateFlags;
        }

        public final void handleError(Exception exc) {
            NotificationEntry notificationEntry = this.mEntry;
            notificationEntry.mRunningTask = null;
            StatusBarNotification statusBarNotification = notificationEntry.mSbn;
            Log.e("CentralSurfaces", "couldn't inflate view for notification " + (statusBarNotification.getPackageName() + "/0x" + Integer.toHexString(statusBarNotification.getId())), exc);
            RowContentBindStage.AnonymousClass1 anonymousClass1 = this.mCallback;
            if (anonymousClass1 != null) {
                anonymousClass1.handleInflationException(this.mRow.mEntry, new InflationException("Couldn't inflate contentViews" + exc));
            }
            this.mRow.mImageResolver.cancelRunningTasks();
        }

        @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder.InflationCallback
        public final void handleInflationException(NotificationEntry notificationEntry, Exception exc) {
            handleError(exc);
        }

        @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder.InflationCallback
        public final void onAsyncInflationFinished(NotificationEntry notificationEntry) {
            this.mEntry.mRunningTask = null;
            this.mRow.onNotificationUpdated();
            RowContentBindStage.AnonymousClass1 anonymousClass1 = this.mCallback;
            if (anonymousClass1 != null) {
                anonymousClass1.onAsyncInflationFinished(this.mEntry);
            }
            NotificationInlineImageResolver notificationInlineImageResolver = this.mRow.mImageResolver;
            if (notificationInlineImageResolver.hasCache()) {
                NotificationInlineImageCache notificationInlineImageCache = notificationInlineImageResolver.mImageCache;
                notificationInlineImageCache.mCache.entrySet().removeIf(new NotificationInlineImageCache$$ExternalSyntheticLambda0(notificationInlineImageCache.mResolver.mWantedUriSet));
            }
            this.mRow.mImageResolver.cancelRunningTasks();
        }

        @Override // android.os.AsyncTask
        public final void onCancelled(Object obj) {
            Trace.endAsyncSection("NotificationContentInflater.AsyncInflationTask", System.identityHashCode(this));
        }

        @Override // android.os.AsyncTask
        public final void onPreExecute() {
            Trace.beginAsyncSection("NotificationContentInflater.AsyncInflationTask", System.identityHashCode(this));
        }

        @Override // android.os.AsyncTask
        public final void onPostExecute(InflationProgress inflationProgress) {
            Trace.endAsyncSection("NotificationContentInflater.AsyncInflationTask", System.identityHashCode(this));
            Exception exc = this.mError;
            if (exc == null) {
                this.mCancellationSignal = NotificationContentInflater.apply(this.mInflationExecutor, this.mInflateSynchronously, this.mIsMinimized, inflationProgress, this.mReInflateFlags, this.mRemoteViewCache, this.mEntry, this.mRow, this.mRemoteViewClickHandler, this, this.mLogger);
            } else {
                handleError(exc);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class InflationProgress {
        public InflatedSmartReplyViewHolder expandedInflatedSmartReplies;
        public InflatedSmartReplyViewHolder headsUpInflatedSmartReplies;
        public CharSequence headsUpStatusBarText;
        public CharSequence headsUpStatusBarTextPublic;
        public View inflatedContentView;
        public View inflatedExpandedView;
        public View inflatedHeadsUpView;
        public View inflatedPublicView;
        public InflatedSmartReplyState inflatedSmartReplyState;
        public NotificationHeaderView mInflatedGroupHeaderView;
        public NotificationHeaderView mInflatedMinimizedGroupHeaderView;
        public HybridNotificationView mInflatedSingleLineView;
        public SingleLineViewModel mInflatedSingleLineViewModel;
        public RemoteViews mNewGroupHeaderView;
        public RemoteViews mNewMinimizedGroupHeaderView;
        public RemoteViews newContentView;
        public RemoteViews newExpandedView;
        public RemoteViews newHeadsUpView;
        public RemoteViews newPublicView;
        Context packageContext;
    }

    public NotificationContentInflater(NotifRemoteViewCache notifRemoteViewCache, NotificationRemoteInputManager notificationRemoteInputManager, ConversationNotificationProcessor conversationNotificationProcessor, MediaFeatureFlag mediaFeatureFlag, Executor executor, SmartReplyStateInflaterImpl smartReplyStateInflaterImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26, HeadsUpStyleProviderImpl headsUpStyleProviderImpl, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
        this.mRemoteViewCache = notifRemoteViewCache;
        this.mRemoteInputManager = notificationRemoteInputManager;
        this.mConversationProcessor = conversationNotificationProcessor;
        Utils.useQsMediaPlayer(mediaFeatureFlag.context);
        this.mInflationExecutor = executor;
        this.mSmartReplyStateInflater = smartReplyStateInflaterImpl;
        this.mNotifLayoutInflaterFactoryProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26;
        this.mHeadsUpStyleProvider = headsUpStyleProviderImpl;
        this.mLogger = notificationRowContentBinderLogger;
    }

    public static CancellationSignal apply(Executor executor, boolean z, boolean z2, final InflationProgress inflationProgress, int i, NotifRemoteViewCache notifRemoteViewCache, final NotificationEntry notificationEntry, final ExpandableNotificationRow expandableNotificationRow, NotificationRemoteInputManager.AnonymousClass1 anonymousClass1, AsyncInflationTask asyncInflationTask, final NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
        HashMap hashMap;
        NotificationContentView notificationContentView;
        NotificationContentView notificationContentView2;
        NotificationChildrenContainer notificationChildrenContainer;
        RemoteViews remoteViews;
        RemoteViews remoteViews2;
        Trace.beginAsyncSection("NotificationContentInflater#apply", System.identityHashCode(expandableNotificationRow));
        NotificationContentView notificationContentView3 = expandableNotificationRow.mPrivateLayout;
        NotificationContentView notificationContentView4 = expandableNotificationRow.mPublicLayout;
        HashMap hashMap2 = new HashMap();
        if ((i & 1) != 0) {
            NotifRemoteViewCacheImpl notifRemoteViewCacheImpl = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
            boolean z3 = !canReapplyRemoteView(inflationProgress.newContentView, notifRemoteViewCacheImpl.getCachedView(notificationEntry, 1));
            final int i2 = 0;
            ApplyCallback applyCallback = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater.1
                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final RemoteViews getRemoteView() {
                    switch (i2) {
                        case 0:
                            return inflationProgress.newContentView;
                        case 1:
                            return inflationProgress.newExpandedView;
                        case 2:
                            return inflationProgress.newHeadsUpView;
                        case 3:
                            return inflationProgress.newPublicView;
                        case 4:
                            return inflationProgress.mNewGroupHeaderView;
                        default:
                            return inflationProgress.mNewMinimizedGroupHeaderView;
                    }
                }

                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final void setResultView(View view) {
                    switch (i2) {
                        case 0:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "contracted view applied");
                            inflationProgress.inflatedContentView = view;
                            break;
                        case 1:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "expanded view applied");
                            inflationProgress.inflatedExpandedView = view;
                            break;
                        case 2:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "heads up view applied");
                            inflationProgress.inflatedHeadsUpView = view;
                            break;
                        case 3:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "public view applied");
                            inflationProgress.inflatedPublicView = view;
                            break;
                        case 4:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "group header view applied");
                            inflationProgress.mInflatedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                        default:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                            inflationProgress.mInflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                    }
                }
            };
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying contracted view");
            hashMap = hashMap2;
            notificationContentView = notificationContentView4;
            notificationContentView2 = notificationContentView3;
            applyRemoteView(executor, z, z2, inflationProgress, i, 1, notifRemoteViewCacheImpl, notificationEntry, expandableNotificationRow, z3, anonymousClass1, asyncInflationTask, notificationContentView2, notificationContentView3.mContractedChild, notificationContentView3.getVisibleWrapper(0), hashMap, applyCallback, notificationRowContentBinderLogger);
        } else {
            hashMap = hashMap2;
            notificationContentView = notificationContentView4;
            notificationContentView2 = notificationContentView3;
        }
        if ((i & 2) != 0 && (remoteViews2 = inflationProgress.newExpandedView) != null) {
            NotifRemoteViewCacheImpl notifRemoteViewCacheImpl2 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
            boolean z4 = !canReapplyRemoteView(remoteViews2, notifRemoteViewCacheImpl2.getCachedView(notificationEntry, 2));
            final int i3 = 1;
            ApplyCallback applyCallback2 = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater.1
                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final RemoteViews getRemoteView() {
                    switch (i3) {
                        case 0:
                            return inflationProgress.newContentView;
                        case 1:
                            return inflationProgress.newExpandedView;
                        case 2:
                            return inflationProgress.newHeadsUpView;
                        case 3:
                            return inflationProgress.newPublicView;
                        case 4:
                            return inflationProgress.mNewGroupHeaderView;
                        default:
                            return inflationProgress.mNewMinimizedGroupHeaderView;
                    }
                }

                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final void setResultView(View view) {
                    switch (i3) {
                        case 0:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "contracted view applied");
                            inflationProgress.inflatedContentView = view;
                            break;
                        case 1:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "expanded view applied");
                            inflationProgress.inflatedExpandedView = view;
                            break;
                        case 2:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "heads up view applied");
                            inflationProgress.inflatedHeadsUpView = view;
                            break;
                        case 3:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "public view applied");
                            inflationProgress.inflatedPublicView = view;
                            break;
                        case 4:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "group header view applied");
                            inflationProgress.mInflatedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                        default:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                            inflationProgress.mInflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                    }
                }
            };
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying expanded view");
            NotificationContentView notificationContentView5 = notificationContentView2;
            notificationContentView2 = notificationContentView5;
            applyRemoteView(executor, z, z2, inflationProgress, i, 2, notifRemoteViewCacheImpl2, notificationEntry, expandableNotificationRow, z4, anonymousClass1, asyncInflationTask, notificationContentView2, notificationContentView5.mExpandedChild, notificationContentView5.getVisibleWrapper(1), hashMap, applyCallback2, notificationRowContentBinderLogger);
        }
        if ((i & 4) != 0 && (remoteViews = inflationProgress.newHeadsUpView) != null) {
            NotifRemoteViewCacheImpl notifRemoteViewCacheImpl3 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
            boolean z5 = !canReapplyRemoteView(remoteViews, notifRemoteViewCacheImpl3.getCachedView(notificationEntry, 4));
            final int i4 = 2;
            ApplyCallback applyCallback3 = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater.1
                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final RemoteViews getRemoteView() {
                    switch (i4) {
                        case 0:
                            return inflationProgress.newContentView;
                        case 1:
                            return inflationProgress.newExpandedView;
                        case 2:
                            return inflationProgress.newHeadsUpView;
                        case 3:
                            return inflationProgress.newPublicView;
                        case 4:
                            return inflationProgress.mNewGroupHeaderView;
                        default:
                            return inflationProgress.mNewMinimizedGroupHeaderView;
                    }
                }

                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final void setResultView(View view) {
                    switch (i4) {
                        case 0:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "contracted view applied");
                            inflationProgress.inflatedContentView = view;
                            break;
                        case 1:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "expanded view applied");
                            inflationProgress.inflatedExpandedView = view;
                            break;
                        case 2:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "heads up view applied");
                            inflationProgress.inflatedHeadsUpView = view;
                            break;
                        case 3:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "public view applied");
                            inflationProgress.inflatedPublicView = view;
                            break;
                        case 4:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "group header view applied");
                            inflationProgress.mInflatedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                        default:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                            inflationProgress.mInflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                    }
                }
            };
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying heads up view");
            NotificationContentView notificationContentView6 = notificationContentView2;
            applyRemoteView(executor, z, z2, inflationProgress, i, 4, notifRemoteViewCacheImpl3, notificationEntry, expandableNotificationRow, z5, anonymousClass1, asyncInflationTask, notificationContentView6, notificationContentView6.mHeadsUpChild, notificationContentView6.getVisibleWrapper(2), hashMap, applyCallback3, notificationRowContentBinderLogger);
        }
        if ((i & 8) != 0) {
            NotifRemoteViewCacheImpl notifRemoteViewCacheImpl4 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
            boolean z6 = !canReapplyRemoteView(inflationProgress.newPublicView, notifRemoteViewCacheImpl4.getCachedView(notificationEntry, 8));
            final int i5 = 3;
            ApplyCallback applyCallback4 = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater.1
                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final RemoteViews getRemoteView() {
                    switch (i5) {
                        case 0:
                            return inflationProgress.newContentView;
                        case 1:
                            return inflationProgress.newExpandedView;
                        case 2:
                            return inflationProgress.newHeadsUpView;
                        case 3:
                            return inflationProgress.newPublicView;
                        case 4:
                            return inflationProgress.mNewGroupHeaderView;
                        default:
                            return inflationProgress.mNewMinimizedGroupHeaderView;
                    }
                }

                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final void setResultView(View view) {
                    switch (i5) {
                        case 0:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "contracted view applied");
                            inflationProgress.inflatedContentView = view;
                            break;
                        case 1:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "expanded view applied");
                            inflationProgress.inflatedExpandedView = view;
                            break;
                        case 2:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "heads up view applied");
                            inflationProgress.inflatedHeadsUpView = view;
                            break;
                        case 3:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "public view applied");
                            inflationProgress.inflatedPublicView = view;
                            break;
                        case 4:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "group header view applied");
                            inflationProgress.mInflatedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                        default:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                            inflationProgress.mInflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                    }
                }
            };
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying public view");
            NotificationContentView notificationContentView7 = notificationContentView;
            applyRemoteView(executor, z, z2, inflationProgress, i, 8, notifRemoteViewCacheImpl4, notificationEntry, expandableNotificationRow, z6, anonymousClass1, asyncInflationTask, notificationContentView7, notificationContentView7.mContractedChild, notificationContentView7.getVisibleWrapper(0), hashMap, applyCallback4, notificationRowContentBinderLogger);
        }
        if (expandableNotificationRow.mChildrenContainer == null) {
            expandableNotificationRow.mChildrenContainerStub.inflate();
        }
        NotificationChildrenContainer notificationChildrenContainer2 = expandableNotificationRow.mChildrenContainer;
        if ((i & 32) != 0) {
            NotifRemoteViewCacheImpl notifRemoteViewCacheImpl5 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
            boolean z7 = !canReapplyRemoteView(inflationProgress.mNewGroupHeaderView, notifRemoteViewCacheImpl5.getCachedView(notificationEntry, 32));
            final int i6 = 4;
            ApplyCallback applyCallback5 = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater.1
                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final RemoteViews getRemoteView() {
                    switch (i6) {
                        case 0:
                            return inflationProgress.newContentView;
                        case 1:
                            return inflationProgress.newExpandedView;
                        case 2:
                            return inflationProgress.newHeadsUpView;
                        case 3:
                            return inflationProgress.newPublicView;
                        case 4:
                            return inflationProgress.mNewGroupHeaderView;
                        default:
                            return inflationProgress.mNewMinimizedGroupHeaderView;
                    }
                }

                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final void setResultView(View view) {
                    switch (i6) {
                        case 0:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "contracted view applied");
                            inflationProgress.inflatedContentView = view;
                            break;
                        case 1:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "expanded view applied");
                            inflationProgress.inflatedExpandedView = view;
                            break;
                        case 2:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "heads up view applied");
                            inflationProgress.inflatedHeadsUpView = view;
                            break;
                        case 3:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "public view applied");
                            inflationProgress.inflatedPublicView = view;
                            break;
                        case 4:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "group header view applied");
                            inflationProgress.mInflatedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                        default:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                            inflationProgress.mInflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                    }
                }
            };
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying group header view");
            notificationChildrenContainer = notificationChildrenContainer2;
            applyRemoteView(executor, z, z2, inflationProgress, i, 32, notifRemoteViewCacheImpl5, notificationEntry, expandableNotificationRow, z7, anonymousClass1, asyncInflationTask, notificationChildrenContainer2, notificationChildrenContainer2.mGroupHeader, notificationChildrenContainer2.mGroupHeaderWrapper, hashMap, applyCallback5, notificationRowContentBinderLogger);
        } else {
            notificationChildrenContainer = notificationChildrenContainer2;
        }
        if ((i & 64) != 0) {
            NotifRemoteViewCacheImpl notifRemoteViewCacheImpl6 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
            boolean z8 = !canReapplyRemoteView(inflationProgress.mNewMinimizedGroupHeaderView, notifRemoteViewCacheImpl6.getCachedView(notificationEntry, 64));
            final int i7 = 5;
            ApplyCallback applyCallback6 = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater.1
                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final RemoteViews getRemoteView() {
                    switch (i7) {
                        case 0:
                            return inflationProgress.newContentView;
                        case 1:
                            return inflationProgress.newExpandedView;
                        case 2:
                            return inflationProgress.newHeadsUpView;
                        case 3:
                            return inflationProgress.newPublicView;
                        case 4:
                            return inflationProgress.mNewGroupHeaderView;
                        default:
                            return inflationProgress.mNewMinimizedGroupHeaderView;
                    }
                }

                @Override // com.android.systemui.statusbar.notification.row.NotificationContentInflater.ApplyCallback
                public final void setResultView(View view) {
                    switch (i7) {
                        case 0:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "contracted view applied");
                            inflationProgress.inflatedContentView = view;
                            break;
                        case 1:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "expanded view applied");
                            inflationProgress.inflatedExpandedView = view;
                            break;
                        case 2:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "heads up view applied");
                            inflationProgress.inflatedHeadsUpView = view;
                            break;
                        case 3:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "public view applied");
                            inflationProgress.inflatedPublicView = view;
                            break;
                        case 4:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "group header view applied");
                            inflationProgress.mInflatedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                        default:
                            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                            inflationProgress.mInflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                            break;
                    }
                }
            };
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying low priority group header view");
            NotificationChildrenContainer notificationChildrenContainer3 = notificationChildrenContainer;
            applyRemoteView(executor, z, z2, inflationProgress, i, 64, notifRemoteViewCacheImpl6, notificationEntry, expandableNotificationRow, z8, anonymousClass1, asyncInflationTask, notificationChildrenContainer3, notificationChildrenContainer3.mMinimizedGroupHeader, notificationChildrenContainer3.mMinimizedGroupHeaderWrapper, hashMap, applyCallback6, notificationRowContentBinderLogger);
        }
        finishIfDone(inflationProgress, z2, i, notifRemoteViewCache, hashMap, asyncInflationTask, notificationEntry, expandableNotificationRow, notificationRowContentBinderLogger);
        CancellationSignal cancellationSignal = new CancellationSignal();
        final HashMap hashMap3 = hashMap;
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater$$ExternalSyntheticLambda1
            @Override // android.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                NotificationRowContentBinderLogger notificationRowContentBinderLogger2 = NotificationRowContentBinderLogger.this;
                NotificationEntry notificationEntry2 = notificationEntry;
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                HashMap hashMap4 = hashMap3;
                notificationRowContentBinderLogger2.logAsyncTaskProgress(notificationEntry2, "apply cancelled");
                Trace.endAsyncSection("NotificationContentInflater#apply", System.identityHashCode(expandableNotificationRow2));
                hashMap4.values().forEach(new NotificationContentInflater$$ExternalSyntheticLambda2());
            }
        });
        return cancellationSignal;
    }

    public static void applyRemoteView(Executor executor, boolean z, final boolean z2, final InflationProgress inflationProgress, final int i, final int i2, final NotifRemoteViewCache notifRemoteViewCache, final NotificationEntry notificationEntry, final ExpandableNotificationRow expandableNotificationRow, final boolean z3, final RemoteViews.InteractionHandler interactionHandler, final NotificationRowContentBinder.InflationCallback inflationCallback, final ViewGroup viewGroup, final View view, final NotificationViewWrapper notificationViewWrapper, final HashMap hashMap, final ApplyCallback applyCallback, final NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
        final RemoteViews remoteView = applyCallback.getRemoteView();
        if (!z) {
            RemoteViews.OnViewAppliedListener onViewAppliedListener = new RemoteViews.OnViewAppliedListener() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater.7
                public final void onError(Exception exc) {
                    try {
                        View view2 = view;
                        if (z3) {
                            view2 = remoteView.apply(inflationProgress.packageContext, viewGroup, interactionHandler);
                        } else {
                            remoteView.reapply(inflationProgress.packageContext, view2, interactionHandler);
                        }
                        Log.wtf("NotifContentInflater", "Async Inflation failed but normal inflation finished normally.", exc);
                        onViewApplied(view2);
                    } catch (Exception unused) {
                        hashMap.remove(Integer.valueOf(i2));
                        NotificationContentInflater.handleInflationError(hashMap, exc, ExpandableNotificationRow.this.mEntry, inflationCallback, notificationRowContentBinderLogger, "applying view");
                    }
                }

                public final void onViewApplied(View view2) {
                    String isValidView = NotificationContentInflater.isValidView(view2, notificationEntry, ExpandableNotificationRow.this.getResources());
                    if (isValidView != null) {
                        NotificationContentInflater.handleInflationError(hashMap, new InflationException(isValidView), ExpandableNotificationRow.this.mEntry, inflationCallback, notificationRowContentBinderLogger, "applied invalid view");
                        hashMap.remove(Integer.valueOf(i2));
                        return;
                    }
                    if (z3) {
                        applyCallback.setResultView(view2);
                    } else {
                        NotificationViewWrapper notificationViewWrapper2 = notificationViewWrapper;
                        if (notificationViewWrapper2 != null) {
                            notificationViewWrapper2.onReinflated();
                        }
                    }
                    hashMap.remove(Integer.valueOf(i2));
                    NotificationContentInflater.finishIfDone(inflationProgress, z2, i, notifRemoteViewCache, hashMap, inflationCallback, notificationEntry, ExpandableNotificationRow.this, notificationRowContentBinderLogger);
                }

                public final void onViewInflated(View view2) {
                    if (view2 instanceof ImageMessageConsumer) {
                        ((ImageMessageConsumer) view2).setImageResolver(ExpandableNotificationRow.this.mImageResolver);
                    }
                }
            };
            hashMap.put(Integer.valueOf(i2), z3 ? remoteView.applyAsync(inflationProgress.packageContext, viewGroup, executor, onViewAppliedListener, interactionHandler) : remoteView.reapplyAsync(inflationProgress.packageContext, view, executor, onViewAppliedListener, interactionHandler));
            return;
        }
        try {
            if (z3) {
                View apply = remoteView.apply(inflationProgress.packageContext, viewGroup, interactionHandler);
                String isValidView = isValidView(apply, notificationEntry, expandableNotificationRow.getResources());
                if (isValidView != null) {
                    throw new InflationException(isValidView);
                }
                applyCallback.setResultView(apply);
                return;
            }
            remoteView.reapply(inflationProgress.packageContext, view, interactionHandler);
            String isValidView2 = isValidView(view, notificationEntry, expandableNotificationRow.getResources());
            if (isValidView2 != null) {
                throw new InflationException(isValidView2);
            }
            notificationViewWrapper.onReinflated();
        } catch (Exception e) {
            handleInflationError(hashMap, e, expandableNotificationRow.mEntry, inflationCallback, notificationRowContentBinderLogger, "applying view synchronously");
            hashMap.put(Integer.valueOf(i2), new CancellationSignal());
        }
    }

    public static boolean canReapplyRemoteView(RemoteViews remoteViews, RemoteViews remoteViews2) {
        if (remoteViews == null && remoteViews2 == null) {
            return true;
        }
        return (remoteViews == null || remoteViews2 == null || remoteViews2.getPackage() == null || remoteViews.getPackage() == null || !remoteViews.getPackage().equals(remoteViews2.getPackage()) || remoteViews.getLayoutId() != remoteViews2.getLayoutId() || remoteViews2.hasFlags(1)) ? false : true;
    }

    public static boolean finishIfDone(InflationProgress inflationProgress, boolean z, int i, NotifRemoteViewCache notifRemoteViewCache, HashMap hashMap, NotificationRowContentBinder.InflationCallback inflationCallback, NotificationEntry notificationEntry, ExpandableNotificationRow expandableNotificationRow, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
        Assert.isMainThread();
        if (!hashMap.isEmpty()) {
            return false;
        }
        NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
        NotificationContentView notificationContentView2 = expandableNotificationRow.mPublicLayout;
        notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "finishing");
        if ((i & 1) != 0) {
            View view = inflationProgress.inflatedContentView;
            if (view != null) {
                notificationContentView.setContractedChild(view);
                ((NotifRemoteViewCacheImpl) notifRemoteViewCache).putCachedView(notificationEntry, 1, inflationProgress.newContentView);
            } else {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                if (notifRemoteViewCacheImpl.hasCachedView(notificationEntry, 1)) {
                    notifRemoteViewCacheImpl.putCachedView(notificationEntry, 1, inflationProgress.newContentView);
                }
            }
        }
        if ((i & 2) != 0) {
            View view2 = inflationProgress.inflatedExpandedView;
            if (view2 != null) {
                notificationContentView.setExpandedChild(view2);
                ((NotifRemoteViewCacheImpl) notifRemoteViewCache).putCachedView(notificationEntry, 2, inflationProgress.newExpandedView);
            } else if (inflationProgress.newExpandedView == null) {
                notificationContentView.setExpandedChild(null);
                ((NotifRemoteViewCacheImpl) notifRemoteViewCache).removeCachedView(notificationEntry, 2);
            } else {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl2 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                if (notifRemoteViewCacheImpl2.hasCachedView(notificationEntry, 2)) {
                    notifRemoteViewCacheImpl2.putCachedView(notificationEntry, 2, inflationProgress.newExpandedView);
                }
            }
            RemoteViews remoteViews = inflationProgress.newExpandedView;
            if (remoteViews != null) {
                InflatedSmartReplyViewHolder inflatedSmartReplyViewHolder = inflationProgress.expandedInflatedSmartReplies;
                notificationContentView.mExpandedInflatedSmartReplies = inflatedSmartReplyViewHolder;
                if (inflatedSmartReplyViewHolder == null) {
                    notificationContentView.mExpandedSmartReplyView = null;
                }
            } else {
                notificationContentView.mExpandedInflatedSmartReplies = null;
                notificationContentView.mExpandedSmartReplyView = null;
            }
            expandableNotificationRow.mExpandable = remoteViews != null;
            expandableNotificationRow.mPrivateLayout.updateExpandButtonsDuringLayout(expandableNotificationRow.isExpandable$1(), false);
        }
        if ((i & 4) != 0) {
            View view3 = inflationProgress.inflatedHeadsUpView;
            if (view3 != null) {
                notificationContentView.setHeadsUpChild(view3);
                ((NotifRemoteViewCacheImpl) notifRemoteViewCache).putCachedView(notificationEntry, 4, inflationProgress.newHeadsUpView);
            } else if (inflationProgress.newHeadsUpView == null) {
                notificationContentView.setHeadsUpChild(null);
                ((NotifRemoteViewCacheImpl) notifRemoteViewCache).removeCachedView(notificationEntry, 4);
            } else {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl3 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                if (notifRemoteViewCacheImpl3.hasCachedView(notificationEntry, 4)) {
                    notifRemoteViewCacheImpl3.putCachedView(notificationEntry, 4, inflationProgress.newHeadsUpView);
                }
            }
            if (inflationProgress.newHeadsUpView != null) {
                notificationContentView.setHeadsUpInflatedSmartReplies(inflationProgress.headsUpInflatedSmartReplies);
            } else {
                notificationContentView.setHeadsUpInflatedSmartReplies(null);
            }
        }
        if ((i & 16) != 0) {
            HybridNotificationView hybridNotificationView = inflationProgress.mInflatedSingleLineView;
            SingleLineViewModel singleLineViewModel = inflationProgress.mInflatedSingleLineViewModel;
            if (hybridNotificationView != null && singleLineViewModel != null) {
                SingleLineViewBinder.bind(singleLineViewModel, hybridNotificationView);
                notificationContentView.setSingleLineView(inflationProgress.mInflatedSingleLineView);
            }
        }
        notificationContentView.mCurrentSmartReplyState = inflationProgress.inflatedSmartReplyState;
        if ((i & 8) != 0) {
            View view4 = inflationProgress.inflatedPublicView;
            if (view4 != null) {
                notificationContentView2.setContractedChild(view4);
                ((NotifRemoteViewCacheImpl) notifRemoteViewCache).putCachedView(notificationEntry, 8, inflationProgress.newPublicView);
            } else {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl4 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                if (notifRemoteViewCacheImpl4.hasCachedView(notificationEntry, 8)) {
                    notifRemoteViewCacheImpl4.putCachedView(notificationEntry, 8, inflationProgress.newPublicView);
                }
            }
        }
        if ((i & 32) != 0) {
            if (inflationProgress.mInflatedGroupHeaderView != null) {
                expandableNotificationRow.setIsMinimized(z);
                expandableNotificationRow.setGroupHeader(inflationProgress.mInflatedGroupHeaderView);
                ((NotifRemoteViewCacheImpl) notifRemoteViewCache).putCachedView(notificationEntry, 32, inflationProgress.mNewGroupHeaderView);
            } else {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl5 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                if (notifRemoteViewCacheImpl5.hasCachedView(notificationEntry, 32)) {
                    notifRemoteViewCacheImpl5.putCachedView(notificationEntry, 32, inflationProgress.mNewGroupHeaderView);
                }
            }
        }
        if ((i & 64) != 0) {
            if (inflationProgress.mInflatedMinimizedGroupHeaderView != null) {
                expandableNotificationRow.setIsMinimized(z);
                expandableNotificationRow.setMinimizedGroupHeader(inflationProgress.mInflatedMinimizedGroupHeaderView);
                ((NotifRemoteViewCacheImpl) notifRemoteViewCache).putCachedView(notificationEntry, 64, inflationProgress.mNewMinimizedGroupHeaderView);
            } else {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl6 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                if (notifRemoteViewCacheImpl6.hasCachedView(notificationEntry, 64)) {
                    notifRemoteViewCacheImpl6.putCachedView(notificationEntry, 64, inflationProgress.mNewGroupHeaderView);
                }
            }
        }
        notificationEntry.mHeadsUpStatusBarText.setValue(inflationProgress.headsUpStatusBarText);
        notificationEntry.mHeadsUpStatusBarTextPublic.setValue(inflationProgress.headsUpStatusBarTextPublic);
        Trace.endAsyncSection("NotificationContentInflater#apply", System.identityHashCode(expandableNotificationRow));
        if (inflationCallback != null) {
            inflationCallback.onAsyncInflationFinished(notificationEntry);
        }
        return true;
    }

    public static void handleInflationError(HashMap hashMap, Exception exc, NotificationEntry notificationEntry, NotificationRowContentBinder.InflationCallback inflationCallback, NotificationRowContentBinderLogger notificationRowContentBinderLogger, String str) {
        Assert.isMainThread();
        notificationRowContentBinderLogger.logAsyncTaskException(notificationEntry, str, exc);
        hashMap.values().forEach(new NotificationContentInflater$$ExternalSyntheticLambda2());
        if (inflationCallback != null) {
            inflationCallback.handleInflationException(notificationEntry, exc);
        }
    }

    public static void inflateSmartReplyViews(InflationProgress inflationProgress, int i, NotificationEntry notificationEntry, Context context, Context context2, InflatedSmartReplyState inflatedSmartReplyState, SmartReplyStateInflater smartReplyStateInflater, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
        boolean z = ((i & 1) == 0 || inflationProgress.newContentView == null) ? false : true;
        boolean z2 = ((i & 2) == 0 || inflationProgress.newExpandedView == null) ? false : true;
        boolean z3 = ((i & 4) == 0 || inflationProgress.newHeadsUpView == null) ? false : true;
        if (z || z2 || z3) {
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "inflating contracted smart reply state");
            inflationProgress.inflatedSmartReplyState = ((SmartReplyStateInflaterImpl) smartReplyStateInflater).inflateSmartReplyState(notificationEntry);
        }
        if (z2) {
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "inflating expanded smart reply state");
            inflationProgress.expandedInflatedSmartReplies = ((SmartReplyStateInflaterImpl) smartReplyStateInflater).inflateSmartReplyViewHolder(context, context2, notificationEntry, inflatedSmartReplyState, inflationProgress.inflatedSmartReplyState);
        }
        if (z3) {
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "inflating heads up smart reply state");
            inflationProgress.headsUpInflatedSmartReplies = ((SmartReplyStateInflaterImpl) smartReplyStateInflater).inflateSmartReplyViewHolder(context, context2, notificationEntry, inflatedSmartReplyState, inflationProgress.inflatedSmartReplyState);
        }
    }

    public static String isValidView(View view, NotificationEntry notificationEntry, Resources resources) {
        if (notificationEntry.targetSdk < 31) {
            Notification notification = notificationEntry.mSbn.getNotification();
            if (notification.contentView != null || notification.bigContentView != null || notification.headsUpContentView != null) {
                boolean isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("NotificationContentInflater#satisfiesMinHeightRequirement");
                }
                try {
                    view.measure(View.MeasureSpec.makeMeasureSpec(resources.getDimensionPixelSize(R.dimen.notification_validation_reference_width), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
                    r2 = view.getMeasuredHeight() >= resources.getDimensionPixelSize(R.dimen.notification_validation_minimum_allowed_height);
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
            }
        }
        if (r2) {
            return null;
        }
        return "inflated notification does not meet minimum height requirement";
    }

    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder
    public final void bindContent(NotificationEntry notificationEntry, ExpandableNotificationRow expandableNotificationRow, int i, NotificationRowContentBinder.BindParams bindParams, boolean z, RowContentBindStage.AnonymousClass1 anonymousClass1) {
        AsyncInflationTask asyncInflationTask;
        InflationProgress inflationProgress;
        SparseArray sparseArray;
        expandableNotificationRow.getClass();
        NotificationRowContentBinderLogger notificationRowContentBinderLogger = this.mLogger;
        notificationRowContentBinderLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        NotificationRowContentBinderLogger$logBinding$2 notificationRowContentBinderLogger$logBinding$2 = NotificationRowContentBinderLogger$logBinding$2.INSTANCE;
        LogBuffer logBuffer = notificationRowContentBinderLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotificationRowContentBinder", logLevel, notificationRowContentBinderLogger$logBinding$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.int1 = i;
        logBuffer.commit(obtain);
        expandableNotificationRow.mImageResolver.preloadImages(notificationEntry.mSbn.getNotification());
        if (z && (sparseArray = (SparseArray) ((ArrayMap) ((NotifRemoteViewCacheImpl) this.mRemoteViewCache).mNotifCachedContentViews).get(notificationEntry)) != null) {
            sparseArray.clear();
        }
        if ((i & 1) != 0) {
            expandableNotificationRow.mPrivateLayout.removeContentInactiveRunnable(0);
        }
        if ((i & 2) != 0) {
            expandableNotificationRow.mPrivateLayout.removeContentInactiveRunnable(1);
        }
        if ((i & 4) != 0) {
            expandableNotificationRow.mPrivateLayout.removeContentInactiveRunnable(2);
        }
        if ((i & 8) != 0) {
            expandableNotificationRow.mPublicLayout.removeContentInactiveRunnable(0);
        }
        if ((i & 128) != 0) {
            expandableNotificationRow.mPublicLayout.removeContentInactiveRunnable(3);
        }
        AsyncInflationTask asyncInflationTask2 = new AsyncInflationTask(this.mInflationExecutor, this.mInflateSynchronously, i, this.mRemoteViewCache, notificationEntry, this.mConversationProcessor, expandableNotificationRow, bindParams.isMinimized, bindParams.usesIncreasedHeight, bindParams.usesIncreasedHeadsUpHeight, anonymousClass1, this.mRemoteInputManager.mInteractionHandler, this.mSmartReplyStateInflater, this.mNotifLayoutInflaterFactoryProvider, this.mHeadsUpStyleProvider, this.mLogger);
        if (!this.mInflateSynchronously) {
            asyncInflationTask2.executeOnExecutor(this.mInflationExecutor, new Void[0]);
            return;
        }
        Void[] voidArr = new Void[0];
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("NotificationContentInflater.AsyncInflationTask#doInBackground");
        }
        try {
            try {
                inflationProgress = asyncInflationTask2.doInBackgroundInternal();
                asyncInflationTask = asyncInflationTask2;
            } catch (Exception e) {
                asyncInflationTask = asyncInflationTask2;
                asyncInflationTask.mError = e;
                asyncInflationTask.mLogger.logAsyncTaskException(asyncInflationTask.mEntry, "inflating", e);
                inflationProgress = null;
            }
            asyncInflationTask.onPostExecute(inflationProgress);
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder
    public final boolean cancelBind(NotificationEntry notificationEntry) {
        boolean abortTask = notificationEntry.abortTask();
        if (abortTask) {
            NotificationRowContentBinderLogger notificationRowContentBinderLogger = this.mLogger;
            notificationRowContentBinderLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            NotificationRowContentBinderLogger$logCancelBindAbortedTask$2 notificationRowContentBinderLogger$logCancelBindAbortedTask$2 = NotificationRowContentBinderLogger$logCancelBindAbortedTask$2.INSTANCE;
            LogBuffer logBuffer = notificationRowContentBinderLogger.buffer;
            LogMessage obtain = logBuffer.obtain("NotificationRowContentBinder", logLevel, notificationRowContentBinderLogger$logCancelBindAbortedTask$2, null);
            ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain);
        }
        return abortTask;
    }

    public InflationProgress inflateNotificationViews(NotificationEntry notificationEntry, ExpandableNotificationRow expandableNotificationRow, NotificationRowContentBinder.BindParams bindParams, boolean z, int i, Notification.Builder builder, Context context, SmartReplyStateInflater smartReplyStateInflater) {
        InflationProgress inflationProgress = (InflationProgress) TraceUtils.trace("NotificationContentInflater.createRemoteViews", new NotificationContentInflater$$ExternalSyntheticLambda0(expandableNotificationRow, i, this.mLogger, builder, bindParams.isMinimized, bindParams.usesIncreasedHeight, this.mHeadsUpStyleProvider, bindParams.usesIncreasedHeadsUpHeight, this.mNotifLayoutInflaterFactoryProvider, context));
        inflateSmartReplyViews(inflationProgress, i, notificationEntry, expandableNotificationRow.getContext(), context, expandableNotificationRow.mPrivateLayout.mCurrentSmartReplyState, smartReplyStateInflater, this.mLogger);
        boolean isConversation = notificationEntry.mRanking.isConversation();
        NotificationRowContentBinderLogger notificationRowContentBinderLogger = this.mLogger;
        SingleLineViewModel inflateSingleLineViewModel = SingleLineViewInflater.inflateSingleLineViewModel(notificationEntry.mSbn.getNotification(), isConversation ? this.mConversationProcessor.processNotification(notificationEntry, builder, notificationRowContentBinderLogger) : null, builder, expandableNotificationRow.getContext());
        boolean z2 = inflateSingleLineViewModel.conversationData != null;
        inflationProgress.mInflatedSingleLineViewModel = inflateSingleLineViewModel;
        inflationProgress.mInflatedSingleLineView = SingleLineViewInflater.inflatePrivateSingleLineView(z2, i, notificationEntry, expandableNotificationRow.getContext(), notificationRowContentBinderLogger);
        apply(this.mInflationExecutor, z, bindParams.isMinimized, inflationProgress, i, this.mRemoteViewCache, notificationEntry, expandableNotificationRow, this.mRemoteInputManager.mInteractionHandler, null, this.mLogger);
        return inflationProgress;
    }

    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder
    public void setInflateSynchronously(boolean z) {
        this.mInflateSynchronously = z;
    }

    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder
    public final void unbindContent(final NotificationEntry notificationEntry, final ExpandableNotificationRow expandableNotificationRow, int i) {
        NotificationRowContentBinderLogger notificationRowContentBinderLogger = this.mLogger;
        notificationRowContentBinderLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        NotificationRowContentBinderLogger$logUnbinding$2 notificationRowContentBinderLogger$logUnbinding$2 = NotificationRowContentBinderLogger$logUnbinding$2.INSTANCE;
        LogBuffer logBuffer = notificationRowContentBinderLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotificationRowContentBinder", logLevel, notificationRowContentBinderLogger$logUnbinding$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.int1 = i;
        logBuffer.commit(obtain);
        int i2 = 1;
        while (i != 0) {
            if ((i & i2) != 0) {
                if (i2 == 1) {
                    final int i3 = 0;
                    expandableNotificationRow.mPrivateLayout.performWhenContentInactive(0, new Runnable(this) { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater$$ExternalSyntheticLambda3
                        public final /* synthetic */ NotificationContentInflater f$0;

                        {
                            this.f$0 = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i3) {
                                case 0:
                                    NotificationContentInflater notificationContentInflater = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                                    NotificationEntry notificationEntry2 = notificationEntry;
                                    notificationContentInflater.getClass();
                                    expandableNotificationRow2.mPrivateLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater.mRemoteViewCache).removeCachedView(notificationEntry2, 1);
                                    break;
                                case 1:
                                    NotificationContentInflater notificationContentInflater2 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow3 = expandableNotificationRow;
                                    NotificationEntry notificationEntry3 = notificationEntry;
                                    notificationContentInflater2.getClass();
                                    expandableNotificationRow3.mPrivateLayout.setExpandedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater2.mRemoteViewCache).removeCachedView(notificationEntry3, 2);
                                    break;
                                case 2:
                                    NotificationContentInflater notificationContentInflater3 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow4 = expandableNotificationRow;
                                    NotificationEntry notificationEntry4 = notificationEntry;
                                    notificationContentInflater3.getClass();
                                    expandableNotificationRow4.mPrivateLayout.setHeadsUpChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater3.mRemoteViewCache).removeCachedView(notificationEntry4, 4);
                                    expandableNotificationRow4.mPrivateLayout.setHeadsUpInflatedSmartReplies(null);
                                    break;
                                default:
                                    NotificationContentInflater notificationContentInflater4 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow5 = expandableNotificationRow;
                                    NotificationEntry notificationEntry5 = notificationEntry;
                                    notificationContentInflater4.getClass();
                                    expandableNotificationRow5.mPublicLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater4.mRemoteViewCache).removeCachedView(notificationEntry5, 8);
                                    break;
                            }
                        }
                    });
                } else if (i2 == 2) {
                    final int i4 = 1;
                    expandableNotificationRow.mPrivateLayout.performWhenContentInactive(1, new Runnable(this) { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater$$ExternalSyntheticLambda3
                        public final /* synthetic */ NotificationContentInflater f$0;

                        {
                            this.f$0 = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i4) {
                                case 0:
                                    NotificationContentInflater notificationContentInflater = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                                    NotificationEntry notificationEntry2 = notificationEntry;
                                    notificationContentInflater.getClass();
                                    expandableNotificationRow2.mPrivateLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater.mRemoteViewCache).removeCachedView(notificationEntry2, 1);
                                    break;
                                case 1:
                                    NotificationContentInflater notificationContentInflater2 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow3 = expandableNotificationRow;
                                    NotificationEntry notificationEntry3 = notificationEntry;
                                    notificationContentInflater2.getClass();
                                    expandableNotificationRow3.mPrivateLayout.setExpandedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater2.mRemoteViewCache).removeCachedView(notificationEntry3, 2);
                                    break;
                                case 2:
                                    NotificationContentInflater notificationContentInflater3 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow4 = expandableNotificationRow;
                                    NotificationEntry notificationEntry4 = notificationEntry;
                                    notificationContentInflater3.getClass();
                                    expandableNotificationRow4.mPrivateLayout.setHeadsUpChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater3.mRemoteViewCache).removeCachedView(notificationEntry4, 4);
                                    expandableNotificationRow4.mPrivateLayout.setHeadsUpInflatedSmartReplies(null);
                                    break;
                                default:
                                    NotificationContentInflater notificationContentInflater4 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow5 = expandableNotificationRow;
                                    NotificationEntry notificationEntry5 = notificationEntry;
                                    notificationContentInflater4.getClass();
                                    expandableNotificationRow5.mPublicLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater4.mRemoteViewCache).removeCachedView(notificationEntry5, 8);
                                    break;
                            }
                        }
                    });
                } else if (i2 == 4) {
                    final int i5 = 2;
                    expandableNotificationRow.mPrivateLayout.performWhenContentInactive(2, new Runnable(this) { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater$$ExternalSyntheticLambda3
                        public final /* synthetic */ NotificationContentInflater f$0;

                        {
                            this.f$0 = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i5) {
                                case 0:
                                    NotificationContentInflater notificationContentInflater = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                                    NotificationEntry notificationEntry2 = notificationEntry;
                                    notificationContentInflater.getClass();
                                    expandableNotificationRow2.mPrivateLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater.mRemoteViewCache).removeCachedView(notificationEntry2, 1);
                                    break;
                                case 1:
                                    NotificationContentInflater notificationContentInflater2 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow3 = expandableNotificationRow;
                                    NotificationEntry notificationEntry3 = notificationEntry;
                                    notificationContentInflater2.getClass();
                                    expandableNotificationRow3.mPrivateLayout.setExpandedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater2.mRemoteViewCache).removeCachedView(notificationEntry3, 2);
                                    break;
                                case 2:
                                    NotificationContentInflater notificationContentInflater3 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow4 = expandableNotificationRow;
                                    NotificationEntry notificationEntry4 = notificationEntry;
                                    notificationContentInflater3.getClass();
                                    expandableNotificationRow4.mPrivateLayout.setHeadsUpChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater3.mRemoteViewCache).removeCachedView(notificationEntry4, 4);
                                    expandableNotificationRow4.mPrivateLayout.setHeadsUpInflatedSmartReplies(null);
                                    break;
                                default:
                                    NotificationContentInflater notificationContentInflater4 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow5 = expandableNotificationRow;
                                    NotificationEntry notificationEntry5 = notificationEntry;
                                    notificationContentInflater4.getClass();
                                    expandableNotificationRow5.mPublicLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater4.mRemoteViewCache).removeCachedView(notificationEntry5, 8);
                                    break;
                            }
                        }
                    });
                } else if (i2 == 8) {
                    final int i6 = 3;
                    expandableNotificationRow.mPublicLayout.performWhenContentInactive(0, new Runnable(this) { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater$$ExternalSyntheticLambda3
                        public final /* synthetic */ NotificationContentInflater f$0;

                        {
                            this.f$0 = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i6) {
                                case 0:
                                    NotificationContentInflater notificationContentInflater = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                                    NotificationEntry notificationEntry2 = notificationEntry;
                                    notificationContentInflater.getClass();
                                    expandableNotificationRow2.mPrivateLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater.mRemoteViewCache).removeCachedView(notificationEntry2, 1);
                                    break;
                                case 1:
                                    NotificationContentInflater notificationContentInflater2 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow3 = expandableNotificationRow;
                                    NotificationEntry notificationEntry3 = notificationEntry;
                                    notificationContentInflater2.getClass();
                                    expandableNotificationRow3.mPrivateLayout.setExpandedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater2.mRemoteViewCache).removeCachedView(notificationEntry3, 2);
                                    break;
                                case 2:
                                    NotificationContentInflater notificationContentInflater3 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow4 = expandableNotificationRow;
                                    NotificationEntry notificationEntry4 = notificationEntry;
                                    notificationContentInflater3.getClass();
                                    expandableNotificationRow4.mPrivateLayout.setHeadsUpChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater3.mRemoteViewCache).removeCachedView(notificationEntry4, 4);
                                    expandableNotificationRow4.mPrivateLayout.setHeadsUpInflatedSmartReplies(null);
                                    break;
                                default:
                                    NotificationContentInflater notificationContentInflater4 = this.f$0;
                                    ExpandableNotificationRow expandableNotificationRow5 = expandableNotificationRow;
                                    NotificationEntry notificationEntry5 = notificationEntry;
                                    notificationContentInflater4.getClass();
                                    expandableNotificationRow5.mPublicLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) notificationContentInflater4.mRemoteViewCache).removeCachedView(notificationEntry5, 8);
                                    break;
                            }
                        }
                    });
                } else if (i2 == 16) {
                    expandableNotificationRow.mPrivateLayout.performWhenContentInactive(3, new Runnable() { // from class: com.android.systemui.statusbar.notification.row.NotificationContentInflater$$ExternalSyntheticLambda7
                        @Override // java.lang.Runnable
                        public final void run() {
                            ExpandableNotificationRow.this.mPrivateLayout.setSingleLineView(null);
                        }
                    });
                }
            }
            i &= ~i2;
            i2 <<= 1;
        }
    }
}

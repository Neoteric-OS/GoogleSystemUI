package com.android.systemui.statusbar.notification.row;

import android.app.Notification;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
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
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.widget.ImageMessageConsumer;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.InflationTask;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.notification.ConversationNotificationProcessor;
import com.android.systemui.statusbar.notification.InflationException;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.NotificationRowContentBinder;
import com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl;
import com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$apply$1;
import com.android.systemui.statusbar.notification.row.RowContentBindStage;
import com.android.systemui.statusbar.notification.row.shared.HeadsUpStatusBarModel;
import com.android.systemui.statusbar.notification.row.shared.NewRemoteViews;
import com.android.systemui.statusbar.notification.row.shared.NotificationContentModel;
import com.android.systemui.statusbar.notification.row.shared.RichOngoingContentModel;
import com.android.systemui.statusbar.notification.row.ui.viewbinder.SingleLineViewBinder;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.SingleLineViewModel;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.policy.InflatedSmartReplyState;
import com.android.systemui.statusbar.policy.InflatedSmartReplyViewHolder;
import com.android.systemui.statusbar.policy.SmartReplyStateInflater;
import com.android.systemui.statusbar.policy.SmartReplyStateInflaterImpl;
import com.android.systemui.util.Assert;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DisposableHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationRowContentBinderImpl implements NotificationRowContentBinder {
    public static final Companion Companion = new Companion();
    public final ConversationNotificationProcessor conversationProcessor;
    public final HeadsUpStyleProviderImpl headsUpStyleProvider;
    public boolean inflateSynchronously;
    public final Executor inflationExecutor;
    public final NotificationRowContentBinderLogger logger;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 notifLayoutInflaterFactoryProvider;
    public final NotificationRemoteInputManager remoteInputManager;
    public final NotifRemoteViewCache remoteViewCache;
    public final RichOngoingNotificationContentExtractor ronExtractor;
    public final RichOngoingNotificationViewInflaterImpl ronInflater;
    public final SmartReplyStateInflaterImpl smartReplyStateInflater;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class ApplyCallback {
        public abstract RemoteViews getRemoteView();

        public abstract void setResultView(View view);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AsyncInflationTask extends AsyncTask implements NotificationRowContentBinder.InflationCallback, InflationTask {
        public final RowContentBindStage.AnonymousClass1 callback;
        public CancellationSignal cancellationSignal;
        public final ConversationNotificationProcessor conversationProcessor;
        public final NotificationEntry entry;
        public final HeadsUpStyleProviderImpl headsUpStyleProvider;
        public final boolean inflateSynchronously;
        public final Executor inflationExecutor;
        public final boolean isMinimized;
        public final NotificationRowContentBinderLogger logger;
        public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 notifLayoutInflaterFactoryProvider;
        public final int reInflateFlags;
        public final NotifRemoteViewCache remoteViewCache;
        public final NotificationRemoteInputManager.AnonymousClass1 remoteViewClickHandler;
        public final RichOngoingNotificationContentExtractor ronExtractor;
        public final RichOngoingNotificationViewInflaterImpl ronInflater;
        public final ExpandableNotificationRow row;
        public final SmartReplyStateInflaterImpl smartRepliesInflater;
        public final boolean usesIncreasedHeadsUpHeight;
        public final boolean usesIncreasedHeight;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class RtlEnabledContext extends ContextWrapper {
            @Override // android.content.ContextWrapper, android.content.Context
            public final ApplicationInfo getApplicationInfo() {
                ApplicationInfo applicationInfo = new ApplicationInfo(super.getApplicationInfo());
                applicationInfo.flags |= 4194304;
                return applicationInfo;
            }
        }

        public AsyncInflationTask(Executor executor, boolean z, int i, NotifRemoteViewCache notifRemoteViewCache, NotificationEntry notificationEntry, ConversationNotificationProcessor conversationNotificationProcessor, RichOngoingNotificationContentExtractor richOngoingNotificationContentExtractor, RichOngoingNotificationViewInflaterImpl richOngoingNotificationViewInflaterImpl, ExpandableNotificationRow expandableNotificationRow, boolean z2, boolean z3, boolean z4, RowContentBindStage.AnonymousClass1 anonymousClass1, NotificationRemoteInputManager.AnonymousClass1 anonymousClass12, SmartReplyStateInflaterImpl smartReplyStateInflaterImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26, HeadsUpStyleProviderImpl headsUpStyleProviderImpl, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
            this.inflationExecutor = executor;
            this.inflateSynchronously = z;
            this.reInflateFlags = i;
            this.remoteViewCache = notifRemoteViewCache;
            this.entry = notificationEntry;
            this.conversationProcessor = conversationNotificationProcessor;
            this.ronExtractor = richOngoingNotificationContentExtractor;
            this.ronInflater = richOngoingNotificationViewInflaterImpl;
            this.row = expandableNotificationRow;
            this.isMinimized = z2;
            this.usesIncreasedHeight = z3;
            this.usesIncreasedHeadsUpHeight = z4;
            this.callback = anonymousClass1;
            this.remoteViewClickHandler = anonymousClass12;
            this.smartRepliesInflater = smartReplyStateInflaterImpl;
            this.notifLayoutInflaterFactoryProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26;
            this.headsUpStyleProvider = headsUpStyleProviderImpl;
            this.logger = notificationRowContentBinderLogger;
            notificationEntry.abortTask();
            notificationEntry.mRunningTask = this;
        }

        public static final InflationProgress access$doInBackgroundInternal(AsyncInflationTask asyncInflationTask) {
            HybridNotificationView hybridNotificationView;
            Set set;
            StatusBarNotification statusBarNotification = asyncInflationTask.entry.mSbn;
            try {
                Notification.addFieldsFromContext(asyncInflationTask.row.getContext().getPackageManager().getApplicationInfoAsUser(statusBarNotification.getPackageName(), 8192, UserHandle.getUserId(statusBarNotification.getUid())), statusBarNotification.getNotification());
            } catch (PackageManager.NameNotFoundException unused) {
            }
            Notification.Builder recoverBuilder = Notification.Builder.recoverBuilder(asyncInflationTask.row.getContext(), statusBarNotification.getNotification());
            Context packageContext = statusBarNotification.getPackageContext(asyncInflationTask.row.getContext());
            if (recoverBuilder.usesTemplate()) {
                packageContext = new RtlEnabledContext(packageContext);
            }
            InflationProgress access$beginInflationAsync = Companion.access$beginInflationAsync(asyncInflationTask.reInflateFlags, asyncInflationTask.entry, recoverBuilder, asyncInflationTask.isMinimized, asyncInflationTask.usesIncreasedHeight, asyncInflationTask.usesIncreasedHeadsUpHeight, asyncInflationTask.row.getContext(), packageContext, asyncInflationTask.row, asyncInflationTask.notifLayoutInflaterFactoryProvider, asyncInflationTask.headsUpStyleProvider, asyncInflationTask.conversationProcessor, asyncInflationTask.ronExtractor, asyncInflationTask.logger);
            asyncInflationTask.logger.logAsyncTaskProgress(asyncInflationTask.entry, "getting existing smart reply state (on wrong thread!)");
            InflatedSmartReplyState inflatedSmartReplyState = asyncInflationTask.row.mPrivateLayout.mCurrentSmartReplyState;
            asyncInflationTask.logger.logAsyncTaskProgress(asyncInflationTask.entry, "inflating smart reply views");
            Companion.access$inflateSmartReplyViews(access$beginInflationAsync, asyncInflationTask.reInflateFlags, asyncInflationTask.entry, asyncInflationTask.row.getContext(), packageContext, inflatedSmartReplyState, asyncInflationTask.smartRepliesInflater, asyncInflationTask.logger);
            asyncInflationTask.logger.logAsyncTaskProgress(asyncInflationTask.entry, "inflating single line view");
            NotificationContentModel notificationContentModel = access$beginInflationAsync.contentModel;
            SingleLineViewModel singleLineViewModel = notificationContentModel.singleLineViewModel;
            if (singleLineViewModel != null) {
                hybridNotificationView = SingleLineViewInflater.inflatePrivateSingleLineView(singleLineViewModel.conversationData != null, asyncInflationTask.reInflateFlags, asyncInflationTask.entry, asyncInflationTask.row.getContext(), asyncInflationTask.logger);
            } else {
                hybridNotificationView = null;
            }
            access$beginInflationAsync.inflatedSingleLineView = hybridNotificationView;
            if (notificationContentModel.richOngoingContentModel != null && (asyncInflationTask.reInflateFlags & 7) != 0) {
                asyncInflationTask.logger.logAsyncTaskProgress(asyncInflationTask.entry, "inflating RON view");
                int i = asyncInflationTask.reInflateFlags;
                boolean z = (i & 1) != 0;
                boolean z2 = (i & 2) != 0;
                boolean z3 = (i & 4) != 0;
                ContentViewInflationResult$NullContentView contentViewInflationResult$NullContentView = ContentViewInflationResult$NullContentView.INSTANCE;
                if (z) {
                    RichOngoingNotificationViewInflaterImpl richOngoingNotificationViewInflaterImpl = asyncInflationTask.ronInflater;
                    ExpandableNotificationRow expandableNotificationRow = asyncInflationTask.row;
                    View view = expandableNotificationRow.mPrivateLayout.mContractedChild;
                    expandableNotificationRow.getContext();
                    NotificationContentView notificationContentView = asyncInflationTask.row.mPrivateLayout;
                    RichOngoingNotificationViewType richOngoingNotificationViewType = RichOngoingNotificationViewType.Contracted;
                    richOngoingNotificationViewInflaterImpl.inflateView();
                } else {
                    RichOngoingNotificationViewInflaterImpl richOngoingNotificationViewInflaterImpl2 = asyncInflationTask.ronInflater;
                    View view2 = asyncInflationTask.row.mPrivateLayout.mContractedChild;
                    RichOngoingNotificationViewType richOngoingNotificationViewType2 = RichOngoingNotificationViewType.Contracted;
                    richOngoingNotificationViewInflaterImpl2.canKeepView();
                }
                access$beginInflationAsync.contractedRichOngoingNotificationViewHolder = contentViewInflationResult$NullContentView;
                if (z2) {
                    RichOngoingNotificationViewInflaterImpl richOngoingNotificationViewInflaterImpl3 = asyncInflationTask.ronInflater;
                    ExpandableNotificationRow expandableNotificationRow2 = asyncInflationTask.row;
                    View view3 = expandableNotificationRow2.mPrivateLayout.mExpandedChild;
                    expandableNotificationRow2.getContext();
                    NotificationContentView notificationContentView2 = asyncInflationTask.row.mPrivateLayout;
                    richOngoingNotificationViewInflaterImpl3.inflateView();
                } else {
                    RichOngoingNotificationViewInflaterImpl richOngoingNotificationViewInflaterImpl4 = asyncInflationTask.ronInflater;
                    View view4 = asyncInflationTask.row.mPrivateLayout.mExpandedChild;
                    richOngoingNotificationViewInflaterImpl4.canKeepView();
                }
                access$beginInflationAsync.expandedRichOngoingNotificationViewHolder = contentViewInflationResult$NullContentView;
                if (z3) {
                    RichOngoingNotificationViewInflaterImpl richOngoingNotificationViewInflaterImpl5 = asyncInflationTask.ronInflater;
                    ExpandableNotificationRow expandableNotificationRow3 = asyncInflationTask.row;
                    View view5 = expandableNotificationRow3.mPrivateLayout.mHeadsUpChild;
                    expandableNotificationRow3.getContext();
                    NotificationContentView notificationContentView3 = asyncInflationTask.row.mPrivateLayout;
                    richOngoingNotificationViewInflaterImpl5.inflateView();
                } else {
                    RichOngoingNotificationViewInflaterImpl richOngoingNotificationViewInflaterImpl6 = asyncInflationTask.ronInflater;
                    View view6 = asyncInflationTask.row.mPrivateLayout.mHeadsUpChild;
                    richOngoingNotificationViewInflaterImpl6.canKeepView();
                }
                access$beginInflationAsync.headsUpRichOngoingNotificationViewHolder = contentViewInflationResult$NullContentView;
            }
            asyncInflationTask.logger.logAsyncTaskProgress(asyncInflationTask.entry, "getting row image resolver (on wrong thread!)");
            NotificationInlineImageResolver notificationInlineImageResolver = asyncInflationTask.row.mImageResolver;
            asyncInflationTask.logger.logAsyncTaskProgress(asyncInflationTask.entry, "waiting for preloaded images");
            if (notificationInlineImageResolver.hasCache() && (set = notificationInlineImageResolver.mWantedUriSet) != null) {
                set.forEach(new NotificationInlineImageResolver$$ExternalSyntheticLambda1(notificationInlineImageResolver, SystemClock.elapsedRealtime() + 1000));
            }
            return access$beginInflationAsync;
        }

        @Override // com.android.systemui.statusbar.InflationTask
        public final void abort() {
            this.logger.logAsyncTaskProgress(this.entry, "cancelling inflate");
            cancel(true);
            if (this.cancellationSignal != null) {
                this.logger.logAsyncTaskProgress(this.entry, "cancelling apply");
                CancellationSignal cancellationSignal = this.cancellationSignal;
                Intrinsics.checkNotNull(cancellationSignal);
                cancellationSignal.cancel();
            }
            this.logger.logAsyncTaskProgress(this.entry, "aborted");
        }

        @Override // android.os.AsyncTask
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return new Result(m880doInBackgroundIoAF18A());
        }

        /* renamed from: doInBackground-IoAF18A, reason: not valid java name */
        public final Object m880doInBackgroundIoAF18A() {
            Object failure;
            boolean isEnabled = Trace.isEnabled();
            if (isEnabled) {
                TraceUtilsKt.beginSlice("NotificationContentInflater.AsyncInflationTask#doInBackground");
            }
            try {
                try {
                    failure = access$doInBackgroundInternal(this);
                } catch (Exception e) {
                    this.logger.logAsyncTaskException(this.entry, "inflating", e);
                    failure = new Result.Failure(e);
                }
                return failure;
            } finally {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
            }
        }

        public final int getReInflateFlags() {
            return this.reInflateFlags;
        }

        public final void handleError$1(Exception exc) {
            NotificationEntry notificationEntry = this.entry;
            notificationEntry.mRunningTask = null;
            StatusBarNotification statusBarNotification = notificationEntry.mSbn;
            Log.e("NotifContentInflater", "couldn't inflate view for notification " + DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(statusBarNotification.getPackageName(), "/0x", Integer.toHexString(statusBarNotification.getId())), exc);
            RowContentBindStage.AnonymousClass1 anonymousClass1 = this.callback;
            if (anonymousClass1 != null) {
                anonymousClass1.handleInflationException(this.row.mEntry, new InflationException("Couldn't inflate contentViews" + exc));
            }
            this.row.mImageResolver.cancelRunningTasks();
        }

        @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder.InflationCallback
        public final void handleInflationException(NotificationEntry notificationEntry, Exception exc) {
            handleError$1(exc);
        }

        @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder.InflationCallback
        public final void onAsyncInflationFinished(NotificationEntry notificationEntry) {
            this.entry.mRunningTask = null;
            this.row.onNotificationUpdated();
            RowContentBindStage.AnonymousClass1 anonymousClass1 = this.callback;
            if (anonymousClass1 != null) {
                anonymousClass1.onAsyncInflationFinished(this.entry);
            }
            NotificationInlineImageResolver notificationInlineImageResolver = this.row.mImageResolver;
            if (notificationInlineImageResolver.hasCache()) {
                NotificationInlineImageCache notificationInlineImageCache = notificationInlineImageResolver.mImageCache;
                notificationInlineImageCache.mCache.entrySet().removeIf(new NotificationInlineImageCache$$ExternalSyntheticLambda0(notificationInlineImageCache.mResolver.mWantedUriSet));
            }
            this.row.mImageResolver.cancelRunningTasks();
        }

        @Override // android.os.AsyncTask
        public final void onCancelled(Object obj) {
            Trace.endAsyncSection("NotificationRowContentBinderImpl.AsyncInflationTask", System.identityHashCode(this));
        }

        @Override // android.os.AsyncTask
        public final void onPostExecute(Object obj) {
            Trace.endAsyncSection("NotificationRowContentBinderImpl.AsyncInflationTask", System.identityHashCode(this));
            Object m1772unboximpl = ((Result) obj).m1772unboximpl();
            if (!(m1772unboximpl instanceof Result.Failure)) {
                this.cancellationSignal = Companion.access$apply(this.inflationExecutor, this.inflateSynchronously, this.isMinimized, (InflationProgress) m1772unboximpl, this.reInflateFlags, this.remoteViewCache, this.entry, this.row, this.remoteViewClickHandler, this, this.logger);
            }
            Throwable m1771exceptionOrNullimpl = Result.m1771exceptionOrNullimpl(m1772unboximpl);
            if (m1771exceptionOrNullimpl != null) {
                handleError$1((Exception) m1771exceptionOrNullimpl);
            }
        }

        @Override // android.os.AsyncTask
        public final void onPreExecute() {
            Trace.beginAsyncSection("NotificationRowContentBinderImpl.AsyncInflationTask", System.identityHashCode(this));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class RemoteViewsUpdater {
            public final NotificationEntry entry;
            public final int reInflateFlags;
            public final NotifRemoteViewCache remoteViewCache;

            public RemoteViewsUpdater(int i, NotificationEntry notificationEntry, NotifRemoteViewCache notifRemoteViewCache) {
                this.reInflateFlags = i;
                this.entry = notificationEntry;
                this.remoteViewCache = notifRemoteViewCache;
            }

            public final void setContentView(int i, RemoteViews remoteViews, View view, Function1 function1) {
                boolean z = (i & 6) != 0;
                if ((this.reInflateFlags & i) != 0) {
                    NotificationEntry notificationEntry = this.entry;
                    NotifRemoteViewCache notifRemoteViewCache = this.remoteViewCache;
                    if (view != null) {
                        function1.invoke(view);
                        ((NotifRemoteViewCacheImpl) notifRemoteViewCache).putCachedView(notificationEntry, i, remoteViews);
                    } else if (z && remoteViews == null) {
                        function1.invoke(null);
                        ((NotifRemoteViewCacheImpl) notifRemoteViewCache).removeCachedView(notificationEntry, i);
                    } else {
                        NotifRemoteViewCacheImpl notifRemoteViewCacheImpl = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                        if (notifRemoteViewCacheImpl.hasCachedView(notificationEntry, i)) {
                            notifRemoteViewCacheImpl.putCachedView(notificationEntry, i, remoteViews);
                        }
                    }
                }
            }
        }

        public static final CancellationSignal access$apply(Executor executor, boolean z, boolean z2, final InflationProgress inflationProgress, int i, NotifRemoteViewCache notifRemoteViewCache, final NotificationEntry notificationEntry, final ExpandableNotificationRow expandableNotificationRow, NotificationRemoteInputManager.AnonymousClass1 anonymousClass1, AsyncInflationTask asyncInflationTask, final NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
            HashMap hashMap;
            NotificationContentView notificationContentView;
            NotificationContentView notificationContentView2;
            Companion companion;
            Companion companion2;
            Companion companion3;
            Companion companion4;
            NotificationChildrenContainer notificationChildrenContainer;
            Companion companion5;
            RemoteViews remoteViews;
            RemoteViews remoteViews2;
            RemoteViews remoteViews3;
            Companion companion6 = NotificationRowContentBinderImpl.Companion;
            Trace.beginAsyncSection("NotificationRowContentBinderImpl#apply", System.identityHashCode(expandableNotificationRow));
            NotificationContentView notificationContentView3 = expandableNotificationRow.mPrivateLayout;
            NotificationContentView notificationContentView4 = expandableNotificationRow.mPublicLayout;
            HashMap hashMap2 = new HashMap();
            if ((i & 1) == 0 || (remoteViews3 = inflationProgress.remoteViews.contracted) == null) {
                hashMap = hashMap2;
                notificationContentView = notificationContentView4;
                notificationContentView2 = notificationContentView3;
                companion = companion6;
            } else {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                boolean z3 = !companion6.canReapplyRemoteView(remoteViews3, notifRemoteViewCacheImpl.getCachedView(notificationEntry, 1));
                final int i2 = 0;
                ApplyCallback applyCallback = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$apply$applyCallback$1
                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
                    public final RemoteViews getRemoteView() {
                        switch (i2) {
                            case 0:
                                return inflationProgress.remoteViews.contracted;
                            case 1:
                                return inflationProgress.remoteViews.expanded;
                            case 2:
                                return inflationProgress.remoteViews.headsUp;
                            case 3:
                                RemoteViews remoteViews4 = inflationProgress.remoteViews.f46public;
                                Intrinsics.checkNotNull(remoteViews4);
                                return remoteViews4;
                            case 4:
                                RemoteViews remoteViews5 = inflationProgress.remoteViews.normalGroupHeader;
                                Intrinsics.checkNotNull(remoteViews5);
                                return remoteViews5;
                            default:
                                RemoteViews remoteViews6 = inflationProgress.remoteViews.minimizedGroupHeader;
                                Intrinsics.checkNotNull(remoteViews6);
                                return remoteViews6;
                        }
                    }

                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
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
                                inflationProgress.inflatedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                            default:
                                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                                inflationProgress.inflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                        }
                    }
                };
                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying contracted view");
                hashMap = hashMap2;
                notificationContentView = notificationContentView4;
                notificationContentView2 = notificationContentView3;
                companion = companion6;
                companion6.applyRemoteView(executor, z, z2, inflationProgress, i, 1, notifRemoteViewCacheImpl, notificationEntry, expandableNotificationRow, z3, anonymousClass1, asyncInflationTask, notificationContentView2, notificationContentView3.mContractedChild, notificationContentView3.getVisibleWrapper(0), hashMap, applyCallback, notificationRowContentBinderLogger);
            }
            if ((i & 2) == 0 || (remoteViews2 = inflationProgress.remoteViews.expanded) == null) {
                companion2 = companion;
            } else {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl2 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                Companion companion7 = companion;
                boolean z4 = !companion7.canReapplyRemoteView(remoteViews2, notifRemoteViewCacheImpl2.getCachedView(notificationEntry, 2));
                final int i3 = 1;
                ApplyCallback applyCallback2 = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$apply$applyCallback$1
                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
                    public final RemoteViews getRemoteView() {
                        switch (i3) {
                            case 0:
                                return inflationProgress.remoteViews.contracted;
                            case 1:
                                return inflationProgress.remoteViews.expanded;
                            case 2:
                                return inflationProgress.remoteViews.headsUp;
                            case 3:
                                RemoteViews remoteViews4 = inflationProgress.remoteViews.f46public;
                                Intrinsics.checkNotNull(remoteViews4);
                                return remoteViews4;
                            case 4:
                                RemoteViews remoteViews5 = inflationProgress.remoteViews.normalGroupHeader;
                                Intrinsics.checkNotNull(remoteViews5);
                                return remoteViews5;
                            default:
                                RemoteViews remoteViews6 = inflationProgress.remoteViews.minimizedGroupHeader;
                                Intrinsics.checkNotNull(remoteViews6);
                                return remoteViews6;
                        }
                    }

                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
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
                                inflationProgress.inflatedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                            default:
                                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                                inflationProgress.inflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                        }
                    }
                };
                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying expanded view");
                NotificationContentView notificationContentView5 = notificationContentView2;
                notificationContentView2 = notificationContentView5;
                companion2 = companion7;
                companion7.applyRemoteView(executor, z, z2, inflationProgress, i, 2, notifRemoteViewCacheImpl2, notificationEntry, expandableNotificationRow, z4, anonymousClass1, asyncInflationTask, notificationContentView2, notificationContentView5.mExpandedChild, notificationContentView5.getVisibleWrapper(1), hashMap, applyCallback2, notificationRowContentBinderLogger);
            }
            if ((i & 4) == 0 || (remoteViews = inflationProgress.remoteViews.headsUp) == null) {
                companion3 = companion2;
            } else {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl3 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                Companion companion8 = companion2;
                boolean z5 = !companion8.canReapplyRemoteView(remoteViews, notifRemoteViewCacheImpl3.getCachedView(notificationEntry, 4));
                final int i4 = 2;
                ApplyCallback applyCallback3 = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$apply$applyCallback$1
                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
                    public final RemoteViews getRemoteView() {
                        switch (i4) {
                            case 0:
                                return inflationProgress.remoteViews.contracted;
                            case 1:
                                return inflationProgress.remoteViews.expanded;
                            case 2:
                                return inflationProgress.remoteViews.headsUp;
                            case 3:
                                RemoteViews remoteViews4 = inflationProgress.remoteViews.f46public;
                                Intrinsics.checkNotNull(remoteViews4);
                                return remoteViews4;
                            case 4:
                                RemoteViews remoteViews5 = inflationProgress.remoteViews.normalGroupHeader;
                                Intrinsics.checkNotNull(remoteViews5);
                                return remoteViews5;
                            default:
                                RemoteViews remoteViews6 = inflationProgress.remoteViews.minimizedGroupHeader;
                                Intrinsics.checkNotNull(remoteViews6);
                                return remoteViews6;
                        }
                    }

                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
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
                                inflationProgress.inflatedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                            default:
                                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                                inflationProgress.inflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                        }
                    }
                };
                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying heads up view");
                NotificationContentView notificationContentView6 = notificationContentView2;
                companion3 = companion8;
                companion8.applyRemoteView(executor, z, z2, inflationProgress, i, 4, notifRemoteViewCacheImpl3, notificationEntry, expandableNotificationRow, z5, anonymousClass1, asyncInflationTask, notificationContentView6, notificationContentView6.mHeadsUpChild, notificationContentView6.getVisibleWrapper(2), hashMap, applyCallback3, notificationRowContentBinderLogger);
            }
            if ((i & 8) != 0) {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl4 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                Companion companion9 = companion3;
                boolean z6 = !companion9.canReapplyRemoteView(inflationProgress.remoteViews.f46public, notifRemoteViewCacheImpl4.getCachedView(notificationEntry, 8));
                final int i5 = 3;
                ApplyCallback applyCallback4 = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$apply$applyCallback$1
                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
                    public final RemoteViews getRemoteView() {
                        switch (i5) {
                            case 0:
                                return inflationProgress.remoteViews.contracted;
                            case 1:
                                return inflationProgress.remoteViews.expanded;
                            case 2:
                                return inflationProgress.remoteViews.headsUp;
                            case 3:
                                RemoteViews remoteViews4 = inflationProgress.remoteViews.f46public;
                                Intrinsics.checkNotNull(remoteViews4);
                                return remoteViews4;
                            case 4:
                                RemoteViews remoteViews5 = inflationProgress.remoteViews.normalGroupHeader;
                                Intrinsics.checkNotNull(remoteViews5);
                                return remoteViews5;
                            default:
                                RemoteViews remoteViews6 = inflationProgress.remoteViews.minimizedGroupHeader;
                                Intrinsics.checkNotNull(remoteViews6);
                                return remoteViews6;
                        }
                    }

                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
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
                                inflationProgress.inflatedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                            default:
                                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                                inflationProgress.inflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                        }
                    }
                };
                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying public view");
                NotificationContentView notificationContentView7 = notificationContentView;
                companion4 = companion9;
                companion9.applyRemoteView(executor, z, z2, inflationProgress, i, 8, notifRemoteViewCacheImpl4, notificationEntry, expandableNotificationRow, z6, anonymousClass1, asyncInflationTask, notificationContentView7, notificationContentView7.mContractedChild, notificationContentView7.getVisibleWrapper(0), hashMap, applyCallback4, notificationRowContentBinderLogger);
            } else {
                companion4 = companion3;
            }
            if (expandableNotificationRow.mChildrenContainer == null) {
                expandableNotificationRow.mChildrenContainerStub.inflate();
            }
            NotificationChildrenContainer notificationChildrenContainer2 = expandableNotificationRow.mChildrenContainer;
            if ((i & 32) != 0) {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl5 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                Companion companion10 = companion4;
                boolean z7 = !companion10.canReapplyRemoteView(inflationProgress.remoteViews.normalGroupHeader, notifRemoteViewCacheImpl5.getCachedView(notificationEntry, 32));
                final int i6 = 4;
                ApplyCallback applyCallback5 = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$apply$applyCallback$1
                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
                    public final RemoteViews getRemoteView() {
                        switch (i6) {
                            case 0:
                                return inflationProgress.remoteViews.contracted;
                            case 1:
                                return inflationProgress.remoteViews.expanded;
                            case 2:
                                return inflationProgress.remoteViews.headsUp;
                            case 3:
                                RemoteViews remoteViews4 = inflationProgress.remoteViews.f46public;
                                Intrinsics.checkNotNull(remoteViews4);
                                return remoteViews4;
                            case 4:
                                RemoteViews remoteViews5 = inflationProgress.remoteViews.normalGroupHeader;
                                Intrinsics.checkNotNull(remoteViews5);
                                return remoteViews5;
                            default:
                                RemoteViews remoteViews6 = inflationProgress.remoteViews.minimizedGroupHeader;
                                Intrinsics.checkNotNull(remoteViews6);
                                return remoteViews6;
                        }
                    }

                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
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
                                inflationProgress.inflatedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                            default:
                                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                                inflationProgress.inflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                        }
                    }
                };
                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying group header view");
                companion5 = companion10;
                notificationChildrenContainer = notificationChildrenContainer2;
                companion10.applyRemoteView(executor, z, z2, inflationProgress, i, 32, notifRemoteViewCacheImpl5, notificationEntry, expandableNotificationRow, z7, anonymousClass1, asyncInflationTask, notificationChildrenContainer2, notificationChildrenContainer2.mGroupHeader, notificationChildrenContainer2.mGroupHeaderWrapper, hashMap, applyCallback5, notificationRowContentBinderLogger);
            } else {
                notificationChildrenContainer = notificationChildrenContainer2;
                companion5 = companion4;
            }
            if ((i & 64) != 0) {
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl6 = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                Companion companion11 = companion5;
                boolean z8 = !companion11.canReapplyRemoteView(inflationProgress.remoteViews.minimizedGroupHeader, notifRemoteViewCacheImpl6.getCachedView(notificationEntry, 64));
                final int i7 = 5;
                ApplyCallback applyCallback6 = new ApplyCallback() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$apply$applyCallback$1
                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
                    public final RemoteViews getRemoteView() {
                        switch (i7) {
                            case 0:
                                return inflationProgress.remoteViews.contracted;
                            case 1:
                                return inflationProgress.remoteViews.expanded;
                            case 2:
                                return inflationProgress.remoteViews.headsUp;
                            case 3:
                                RemoteViews remoteViews4 = inflationProgress.remoteViews.f46public;
                                Intrinsics.checkNotNull(remoteViews4);
                                return remoteViews4;
                            case 4:
                                RemoteViews remoteViews5 = inflationProgress.remoteViews.normalGroupHeader;
                                Intrinsics.checkNotNull(remoteViews5);
                                return remoteViews5;
                            default:
                                RemoteViews remoteViews6 = inflationProgress.remoteViews.minimizedGroupHeader;
                                Intrinsics.checkNotNull(remoteViews6);
                                return remoteViews6;
                        }
                    }

                    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl.ApplyCallback
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
                                inflationProgress.inflatedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                            default:
                                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "low-priority group header view applied");
                                inflationProgress.inflatedMinimizedGroupHeaderView = (NotificationHeaderView) view;
                                break;
                        }
                    }
                };
                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "applying low priority group header view");
                NotificationChildrenContainer notificationChildrenContainer3 = notificationChildrenContainer;
                companion11.applyRemoteView(executor, z, z2, inflationProgress, i, 64, notifRemoteViewCacheImpl6, notificationEntry, expandableNotificationRow, z8, anonymousClass1, asyncInflationTask, notificationChildrenContainer3, notificationChildrenContainer3.mMinimizedGroupHeader, notificationChildrenContainer3.mMinimizedGroupHeaderWrapper, hashMap, applyCallback6, notificationRowContentBinderLogger);
            }
            finishIfDone(inflationProgress, z2, i, notifRemoteViewCache, hashMap, asyncInflationTask, notificationEntry, expandableNotificationRow, notificationRowContentBinderLogger);
            CancellationSignal cancellationSignal = new CancellationSignal();
            final HashMap hashMap3 = hashMap;
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$apply$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$apply$1$1, reason: invalid class name */
                public final class AnonymousClass1 implements Consumer {
                    public static final AnonymousClass1 INSTANCE = new AnonymousClass1(0);
                    public static final AnonymousClass1 INSTANCE$1 = new AnonymousClass1(1);
                    public final /* synthetic */ int $r8$classId;

                    public /* synthetic */ AnonymousClass1(int i) {
                        this.$r8$classId = i;
                    }

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        switch (this.$r8$classId) {
                            case 0:
                                ((CancellationSignal) obj).cancel();
                                break;
                            default:
                                ((CancellationSignal) obj).cancel();
                                break;
                        }
                    }
                }

                @Override // android.os.CancellationSignal.OnCancelListener
                public final void onCancel() {
                    NotificationRowContentBinderLogger.this.logAsyncTaskProgress(notificationEntry, "apply cancelled");
                    Trace.endAsyncSection("NotificationRowContentBinderImpl#apply", System.identityHashCode(expandableNotificationRow));
                    hashMap3.values().forEach(AnonymousClass1.INSTANCE);
                }
            });
            return cancellationSignal;
        }

        public static final InflationProgress access$beginInflationAsync(int i, NotificationEntry notificationEntry, Notification.Builder builder, boolean z, boolean z2, boolean z3, Context context, Context context2, ExpandableNotificationRow expandableNotificationRow, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26, HeadsUpStyleProviderImpl headsUpStyleProviderImpl, ConversationNotificationProcessor conversationNotificationProcessor, RichOngoingNotificationContentExtractor richOngoingNotificationContentExtractor, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
            RemoteViews remoteViews;
            RemoteViews remoteViews2;
            RemoteViews remoteViews3;
            RemoteViews remoteViews4;
            RemoteViews remoteViews5;
            RemoteViews remoteViews6;
            SingleLineViewModel singleLineViewModel;
            Notification.MessagingStyle processNotification = notificationEntry.mRanking.isConversation() ? conversationNotificationProcessor.processNotification(notificationEntry, builder, notificationRowContentBinderLogger) : null;
            RichOngoingContentModel extractContentModel = (i & 7) != 0 ? richOngoingNotificationContentExtractor.extractContentModel(notificationEntry, builder) : (RichOngoingContentModel) notificationEntry.mRichOngoingContentModel.getValue();
            int i2 = extractContentModel != null ? i & (-8) : i;
            boolean isEnabled = Trace.isEnabled();
            if (isEnabled) {
                TraceUtilsKt.beginSlice("NotificationContentInflater.createRemoteViews");
            }
            try {
                NotificationEntry notificationEntry2 = expandableNotificationRow.mEntry;
                if ((i2 & 1) != 0) {
                    notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry2, "creating contracted remote view");
                    if (z) {
                        remoteViews = builder.makeLowPriorityContentView(false);
                        Intrinsics.checkNotNull(remoteViews);
                    } else {
                        remoteViews = builder.createContentView(z2);
                    }
                } else {
                    remoteViews = null;
                }
                if ((i2 & 2) != 0) {
                    notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry2, "creating expanded remote view");
                    RemoteViews createBigContentView = builder.createBigContentView();
                    if (createBigContentView == null) {
                        if (z) {
                            createBigContentView = builder.createContentView();
                            Notification.Builder.makeHeaderExpanded(createBigContentView);
                        } else {
                            createBigContentView = null;
                        }
                    }
                    remoteViews2 = createBigContentView;
                } else {
                    remoteViews2 = null;
                }
                if ((i2 & 4) != 0) {
                    notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry2, "creating heads up remote view");
                    remoteViews3 = ((Boolean) headsUpStyleProviderImpl.statusBarModeRepositoryStore.defaultDisplay.isInFullscreenMode.getValue()).booleanValue() ? builder.createCompactHeadsUpContentView() : builder.createHeadsUpContentView(z3);
                } else {
                    remoteViews3 = null;
                }
                if ((i2 & 8) != 0) {
                    notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry2, "creating public remote view");
                    remoteViews4 = builder.makePublicContentView(z);
                } else {
                    remoteViews4 = null;
                }
                if ((i2 & 32) != 0) {
                    notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry2, "creating group summary remote view");
                    remoteViews5 = builder.makeNotificationGroupHeader();
                } else {
                    remoteViews5 = null;
                }
                if ((i2 & 64) != 0) {
                    notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry2, "creating low-priority group summary remote view");
                    remoteViews6 = builder.makeLowPriorityContentView(true);
                } else {
                    remoteViews6 = null;
                }
                RemoteViews remoteViews7 = remoteViews4;
                RemoteViews remoteViews8 = remoteViews3;
                RemoteViews remoteViews9 = remoteViews2;
                NewRemoteViews newRemoteViews = new NewRemoteViews(remoteViews, remoteViews8, remoteViews9, remoteViews7, remoteViews5, remoteViews6);
                if (remoteViews != null) {
                    remoteViews.setLayoutInflaterFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26.provide(expandableNotificationRow, 1));
                }
                if (remoteViews9 != null) {
                    remoteViews9.setLayoutInflaterFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26.provide(expandableNotificationRow, 2));
                }
                if (remoteViews8 != null) {
                    remoteViews8.setLayoutInflaterFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26.provide(expandableNotificationRow, 4));
                }
                if (remoteViews7 != null) {
                    remoteViews7.setLayoutInflaterFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26.provide(expandableNotificationRow, 8));
                }
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
                if ((i & 16) != 0) {
                    notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "inflating single line view model");
                    singleLineViewModel = SingleLineViewInflater.inflateSingleLineViewModel(notificationEntry.mSbn.getNotification(), processNotification, builder, context);
                } else {
                    singleLineViewModel = null;
                }
                builder.getHeadsUpStatusBarText(false);
                builder.getHeadsUpStatusBarText(true);
                return new InflationProgress(context2, newRemoteViews, new NotificationContentModel(new HeadsUpStatusBarModel(), singleLineViewModel, extractContentModel));
            } catch (Throwable th) {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
                throw th;
            }
        }

        public static final void access$inflateSmartReplyViews(InflationProgress inflationProgress, int i, NotificationEntry notificationEntry, Context context, Context context2, InflatedSmartReplyState inflatedSmartReplyState, SmartReplyStateInflater smartReplyStateInflater, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
            int i2 = i & 1;
            NewRemoteViews newRemoteViews = inflationProgress.remoteViews;
            boolean z = (i2 == 0 || newRemoteViews.contracted == null) ? false : true;
            boolean z2 = ((i & 2) == 0 || newRemoteViews.expanded == null) ? false : true;
            boolean z3 = ((i & 4) == 0 || newRemoteViews.headsUp == null) ? false : true;
            if (z || z2 || z3) {
                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "inflating contracted smart reply state");
                inflationProgress.inflatedSmartReplyState = ((SmartReplyStateInflaterImpl) smartReplyStateInflater).inflateSmartReplyState(notificationEntry);
            }
            if (z2) {
                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "inflating expanded smart reply state");
                InflatedSmartReplyState inflatedSmartReplyState2 = inflationProgress.inflatedSmartReplyState;
                Intrinsics.checkNotNull(inflatedSmartReplyState2);
                inflationProgress.expandedInflatedSmartReplies = ((SmartReplyStateInflaterImpl) smartReplyStateInflater).inflateSmartReplyViewHolder(context, context2, notificationEntry, inflatedSmartReplyState, inflatedSmartReplyState2);
            }
            if (z3) {
                notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "inflating heads up smart reply state");
                InflatedSmartReplyState inflatedSmartReplyState3 = inflationProgress.inflatedSmartReplyState;
                Intrinsics.checkNotNull(inflatedSmartReplyState3);
                inflationProgress.headsUpInflatedSmartReplies = ((SmartReplyStateInflaterImpl) smartReplyStateInflater).inflateSmartReplyViewHolder(context, context2, notificationEntry, inflatedSmartReplyState, inflatedSmartReplyState3);
            }
        }

        public static boolean finishIfDone(InflationProgress inflationProgress, final boolean z, int i, NotifRemoteViewCache notifRemoteViewCache, HashMap hashMap, NotificationRowContentBinder.InflationCallback inflationCallback, NotificationEntry notificationEntry, final ExpandableNotificationRow expandableNotificationRow, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
            boolean z2;
            HybridNotificationView hybridNotificationView;
            SingleLineViewModel singleLineViewModel;
            Assert.isMainThread();
            if (!hashMap.isEmpty()) {
                return false;
            }
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "finishing");
            ContentViewInflationResult$NullContentView contentViewInflationResult$NullContentView = inflationProgress.contractedRichOngoingNotificationViewHolder;
            DisposableHandle disposableHandle = expandableNotificationRow.mPrivateLayout.mContractedBinderHandle;
            if (disposableHandle != null) {
                disposableHandle.dispose();
            }
            expandableNotificationRow.mPrivateLayout.mContractedBinderHandle = null;
            ContentViewInflationResult$NullContentView contentViewInflationResult$NullContentView2 = inflationProgress.expandedRichOngoingNotificationViewHolder;
            DisposableHandle disposableHandle2 = expandableNotificationRow.mPrivateLayout.mExpandedBinderHandle;
            if (disposableHandle2 != null) {
                disposableHandle2.dispose();
            }
            expandableNotificationRow.mPrivateLayout.mExpandedBinderHandle = null;
            ContentViewInflationResult$NullContentView contentViewInflationResult$NullContentView3 = inflationProgress.headsUpRichOngoingNotificationViewHolder;
            DisposableHandle disposableHandle3 = expandableNotificationRow.mPrivateLayout.mHeadsUpBinderHandle;
            if (disposableHandle3 != null) {
                disposableHandle3.dispose();
            }
            expandableNotificationRow.mPrivateLayout.mHeadsUpBinderHandle = null;
            if (Log.isLoggable("RefactorFlagAssert", 7)) {
                Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.notification_row_content_binder_refactor to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.notification_row_content_binder_refactor to be enabled.") : null);
            } else if (Log.isLoggable("RefactorFlag", 5)) {
                Log.w("RefactorFlag", "New code path expects com.android.systemui.notification_row_content_binder_refactor to be enabled.");
            }
            InflatedSmartReplyState inflatedSmartReplyState = inflationProgress.inflatedSmartReplyState;
            if (inflatedSmartReplyState != null) {
                expandableNotificationRow.mPrivateLayout.mCurrentSmartReplyState = inflatedSmartReplyState;
            }
            NotificationContentModel notificationContentModel = inflationProgress.contentModel;
            int i2 = notificationContentModel.richOngoingContentModel != null ? i & (-8) : i;
            NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
            NotificationContentView notificationContentView2 = expandableNotificationRow.mPublicLayout;
            RemoteViewsUpdater remoteViewsUpdater = new RemoteViewsUpdater(i2, notificationEntry, notifRemoteViewCache);
            NewRemoteViews newRemoteViews = inflationProgress.remoteViews;
            RemoteViews remoteViews = newRemoteViews.contracted;
            View view = inflationProgress.inflatedContentView;
            Intrinsics.checkNotNull(notificationContentView);
            remoteViewsUpdater.setContentView(1, remoteViews, view, new NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$1(1, notificationContentView, NotificationContentView.class, "setContractedChild", "setContractedChild(Landroid/view/View;)V", 0));
            remoteViewsUpdater.setContentView(2, newRemoteViews.expanded, inflationProgress.inflatedExpandedView, new NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$2(1, notificationContentView, NotificationContentView.class, "setExpandedChild", "setExpandedChild(Landroid/view/View;)V", 0));
            RemoteViews remoteViews2 = newRemoteViews.expanded;
            InflatedSmartReplyViewHolder inflatedSmartReplyViewHolder = inflationProgress.expandedInflatedSmartReplies;
            NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$3 notificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$3 = new NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$3(1, notificationContentView, NotificationContentView.class, "setExpandedInflatedSmartReplies", "setExpandedInflatedSmartReplies(Lcom/android/systemui/statusbar/policy/InflatedSmartReplyViewHolder;)V", 0);
            if ((i2 & 2) != 0) {
                if (remoteViews2 != null) {
                    notificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$3.invoke(inflatedSmartReplyViewHolder);
                } else {
                    notificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$3.invoke(null);
                }
            }
            if ((i2 & 2) != 0) {
                expandableNotificationRow.mExpandable = newRemoteViews.expanded != null;
                expandableNotificationRow.mPrivateLayout.updateExpandButtonsDuringLayout(expandableNotificationRow.isExpandable$1(), false);
            }
            remoteViewsUpdater.setContentView(4, newRemoteViews.headsUp, inflationProgress.inflatedHeadsUpView, new NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$4(1, notificationContentView, NotificationContentView.class, "setHeadsUpChild", "setHeadsUpChild(Landroid/view/View;)V", 0));
            RemoteViews remoteViews3 = newRemoteViews.headsUp;
            InflatedSmartReplyViewHolder inflatedSmartReplyViewHolder2 = inflationProgress.headsUpInflatedSmartReplies;
            NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$5 notificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$5 = new NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$5(1, notificationContentView, NotificationContentView.class, "setHeadsUpInflatedSmartReplies", "setHeadsUpInflatedSmartReplies(Lcom/android/systemui/statusbar/policy/InflatedSmartReplyViewHolder;)V", 0);
            if ((i2 & 4) != 0) {
                if (remoteViews3 != null) {
                    notificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$5.invoke(inflatedSmartReplyViewHolder2);
                } else {
                    notificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$5.invoke(null);
                }
            }
            RemoteViews remoteViews4 = newRemoteViews.f46public;
            View view2 = inflationProgress.inflatedPublicView;
            Intrinsics.checkNotNull(notificationContentView2);
            remoteViewsUpdater.setContentView(8, remoteViews4, view2, new NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$6(1, notificationContentView2, NotificationContentView.class, "setContractedChild", "setContractedChild(Landroid/view/View;)V", 0));
            remoteViewsUpdater.setContentView(32, newRemoteViews.normalGroupHeader, inflationProgress.inflatedGroupHeaderView, new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$7
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ExpandableNotificationRow.this.setIsMinimized(z);
                    ExpandableNotificationRow.this.setGroupHeader((NotificationHeaderView) obj);
                    return Unit.INSTANCE;
                }
            });
            remoteViewsUpdater.setContentView(64, newRemoteViews.minimizedGroupHeader, inflationProgress.inflatedMinimizedGroupHeaderView, new Function1() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$setContentViewsFromRemoteViews$8
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ExpandableNotificationRow.this.setIsMinimized(z);
                    ExpandableNotificationRow.this.setMinimizedGroupHeader((NotificationHeaderView) obj);
                    return Unit.INSTANCE;
                }
            });
            if ((i & 16) != 0 && (hybridNotificationView = inflationProgress.inflatedSingleLineView) != null && (singleLineViewModel = notificationContentModel.singleLineViewModel) != null) {
                SingleLineViewBinder.bind(singleLineViewModel, hybridNotificationView);
                expandableNotificationRow.mPrivateLayout.setSingleLineView(inflationProgress.inflatedSingleLineView);
            }
            ContentViewInflationResult$NullContentView contentViewInflationResult$NullContentView4 = inflationProgress.contractedRichOngoingNotificationViewHolder;
            if (contentViewInflationResult$NullContentView4 == null && inflationProgress.expandedRichOngoingNotificationViewHolder == null && inflationProgress.headsUpRichOngoingNotificationViewHolder == null) {
                z2 = true;
            } else {
                ContentViewInflationResult$NullContentView contentViewInflationResult$NullContentView5 = ContentViewInflationResult$NullContentView.INSTANCE;
                if (contentViewInflationResult$NullContentView4 != null && contentViewInflationResult$NullContentView4.equals(contentViewInflationResult$NullContentView5)) {
                    expandableNotificationRow.mPrivateLayout.setContractedChild(null);
                }
                ContentViewInflationResult$NullContentView contentViewInflationResult$NullContentView6 = inflationProgress.expandedRichOngoingNotificationViewHolder;
                if (contentViewInflationResult$NullContentView6 != null && contentViewInflationResult$NullContentView6.equals(contentViewInflationResult$NullContentView5)) {
                    expandableNotificationRow.mPrivateLayout.setExpandedChild(null);
                }
                ContentViewInflationResult$NullContentView contentViewInflationResult$NullContentView7 = inflationProgress.headsUpRichOngoingNotificationViewHolder;
                if (contentViewInflationResult$NullContentView7 != null && contentViewInflationResult$NullContentView7.equals(contentViewInflationResult$NullContentView5)) {
                    expandableNotificationRow.mPrivateLayout.setHeadsUpChild(null);
                }
                NotifRemoteViewCacheImpl notifRemoteViewCacheImpl = (NotifRemoteViewCacheImpl) notifRemoteViewCache;
                z2 = true;
                notifRemoteViewCacheImpl.removeCachedView(notificationEntry, 1);
                notifRemoteViewCacheImpl.removeCachedView(notificationEntry, 2);
                notifRemoteViewCacheImpl.removeCachedView(notificationEntry, 4);
                NotificationContentView notificationContentView3 = expandableNotificationRow.mPrivateLayout;
                notificationContentView3.mExpandedInflatedSmartReplies = null;
                notificationContentView3.mExpandedSmartReplyView = null;
                notificationContentView3.setHeadsUpInflatedSmartReplies(null);
                NotificationContentView notificationContentView4 = expandableNotificationRow.mPrivateLayout;
                expandableNotificationRow.mExpandable = notificationContentView4.mExpandedChild != null;
                notificationContentView4.updateExpandButtonsDuringLayout(expandableNotificationRow.isExpandable$1(), false);
            }
            Trace.endAsyncSection("NotificationRowContentBinderImpl#apply", System.identityHashCode(expandableNotificationRow));
            if (inflationCallback != null) {
                inflationCallback.onAsyncInflationFinished(notificationEntry);
            }
            return z2;
        }

        public static void handleInflationError(HashMap hashMap, Exception exc, NotificationEntry notificationEntry, NotificationRowContentBinder.InflationCallback inflationCallback, NotificationRowContentBinderLogger notificationRowContentBinderLogger, String str) {
            Assert.isMainThread();
            notificationRowContentBinderLogger.logAsyncTaskException(notificationEntry, str, exc);
            hashMap.values().forEach(NotificationRowContentBinderImpl$Companion$apply$1.AnonymousClass1.INSTANCE$1);
            if (inflationCallback != null) {
                inflationCallback.handleInflationException(notificationEntry, exc);
            }
        }

        public final void applyRemoteView(Executor executor, boolean z, final boolean z2, final InflationProgress inflationProgress, final int i, final int i2, final NotifRemoteViewCache notifRemoteViewCache, final NotificationEntry notificationEntry, final ExpandableNotificationRow expandableNotificationRow, final boolean z3, final RemoteViews.InteractionHandler interactionHandler, final NotificationRowContentBinder.InflationCallback inflationCallback, final ViewGroup viewGroup, final View view, final NotificationViewWrapper notificationViewWrapper, final HashMap hashMap, final ApplyCallback applyCallback, final NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
            CancellationSignal reapplyAsync;
            final RemoteViews remoteView = applyCallback.getRemoteView();
            if (!z) {
                RemoteViews.OnViewAppliedListener onViewAppliedListener = new RemoteViews.OnViewAppliedListener() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$Companion$applyRemoteView$listener$1
                    public final void onError(Exception exc) {
                        View view2;
                        try {
                            if (z3) {
                                view2 = remoteView.apply(inflationProgress.packageContext, viewGroup, interactionHandler);
                            } else {
                                remoteView.reapply(inflationProgress.packageContext, view, interactionHandler);
                                view2 = view;
                                Intrinsics.checkNotNull(view2);
                            }
                            Log.wtf("NotifContentInflater", "Async Inflation failed but normal inflation finished normally.", exc);
                            Intrinsics.checkNotNull(view2);
                            onViewApplied(view2);
                        } catch (Exception unused) {
                            hashMap.remove(Integer.valueOf(i2));
                            HashMap hashMap2 = hashMap;
                            NotificationEntry notificationEntry2 = ExpandableNotificationRow.this.mEntry;
                            NotificationRowContentBinder.InflationCallback inflationCallback2 = inflationCallback;
                            NotificationRowContentBinderLogger notificationRowContentBinderLogger2 = notificationRowContentBinderLogger;
                            Assert.isMainThread();
                            notificationRowContentBinderLogger2.logAsyncTaskException(notificationEntry2, "applying view", exc);
                            hashMap2.values().forEach(NotificationRowContentBinderImpl$Companion$apply$1.AnonymousClass1.INSTANCE$1);
                            if (inflationCallback2 != null) {
                                inflationCallback2.handleInflationException(notificationEntry2, exc);
                            }
                        }
                    }

                    public final void onViewApplied(View view2) {
                        String isValidView = NotificationRowContentBinderImpl.Companion.isValidView(view2, notificationEntry, ExpandableNotificationRow.this.getResources());
                        if (isValidView != null) {
                            NotificationRowContentBinderImpl.Companion.handleInflationError(hashMap, new InflationException(isValidView), ExpandableNotificationRow.this.mEntry, inflationCallback, notificationRowContentBinderLogger, "applied invalid view");
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
                        NotificationRowContentBinderImpl.Companion.finishIfDone(inflationProgress, z2, i, notifRemoteViewCache, hashMap, inflationCallback, notificationEntry, ExpandableNotificationRow.this, notificationRowContentBinderLogger);
                    }

                    public final void onViewInflated(View view2) {
                        if (view2 instanceof ImageMessageConsumer) {
                            ((ImageMessageConsumer) view2).setImageResolver(ExpandableNotificationRow.this.mImageResolver);
                        }
                    }
                };
                if (z3) {
                    reapplyAsync = remoteView.applyAsync(inflationProgress.packageContext, viewGroup, executor, onViewAppliedListener, interactionHandler);
                    Intrinsics.checkNotNull(reapplyAsync);
                } else {
                    reapplyAsync = remoteView.reapplyAsync(inflationProgress.packageContext, view, executor, onViewAppliedListener, interactionHandler);
                    Intrinsics.checkNotNull(reapplyAsync);
                }
                hashMap.put(Integer.valueOf(i2), reapplyAsync);
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
                if (view == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                if (notificationViewWrapper == null) {
                    throw new IllegalArgumentException("Required value was null.");
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

        public final boolean canReapplyRemoteView(RemoteViews remoteViews, RemoteViews remoteViews2) {
            if (remoteViews == null && remoteViews2 == null) {
                return true;
            }
            return (remoteViews == null || remoteViews2 == null || remoteViews2.getPackage() == null || remoteViews.getPackage() == null || !Intrinsics.areEqual(remoteViews.getPackage(), remoteViews2.getPackage()) || remoteViews.getLayoutId() != remoteViews2.getLayoutId() || remoteViews2.hasFlags(1)) ? false : true;
        }

        public final String isValidView(View view, NotificationEntry notificationEntry, Resources resources) {
            if (notificationEntry.targetSdk < 31) {
                Notification notification = notificationEntry.mSbn.getNotification();
                if (notification.contentView != null || notification.bigContentView != null || notification.headsUpContentView != null) {
                    boolean isEnabled = Trace.isEnabled();
                    if (isEnabled) {
                        TraceUtilsKt.beginSlice("NotificationContentInflater#satisfiesMinHeightRequirement");
                    }
                    try {
                        view.measure(View.MeasureSpec.makeMeasureSpec(resources.getDimensionPixelSize(R.dimen.notification_validation_reference_width), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
                        r1 = view.getMeasuredHeight() >= resources.getDimensionPixelSize(R.dimen.notification_validation_minimum_allowed_height);
                    } finally {
                        if (isEnabled) {
                            TraceUtilsKt.endSlice();
                        }
                    }
                }
            }
            if (r1) {
                return null;
            }
            return "inflated notification does not meet minimum height requirement";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InflationProgress {
        public final NotificationContentModel contentModel;
        public ContentViewInflationResult$NullContentView contractedRichOngoingNotificationViewHolder;
        public InflatedSmartReplyViewHolder expandedInflatedSmartReplies;
        public ContentViewInflationResult$NullContentView expandedRichOngoingNotificationViewHolder;
        public InflatedSmartReplyViewHolder headsUpInflatedSmartReplies;
        public ContentViewInflationResult$NullContentView headsUpRichOngoingNotificationViewHolder;
        public View inflatedContentView;
        public View inflatedExpandedView;
        public NotificationHeaderView inflatedGroupHeaderView;
        public View inflatedHeadsUpView;
        public NotificationHeaderView inflatedMinimizedGroupHeaderView;
        public View inflatedPublicView;
        public HybridNotificationView inflatedSingleLineView;
        public InflatedSmartReplyState inflatedSmartReplyState;
        public final Context packageContext;
        public final NewRemoteViews remoteViews;

        public InflationProgress(Context context, NewRemoteViews newRemoteViews, NotificationContentModel notificationContentModel) {
            this.packageContext = context;
            this.remoteViews = newRemoteViews;
            this.contentModel = notificationContentModel;
        }
    }

    public NotificationRowContentBinderImpl(NotifRemoteViewCache notifRemoteViewCache, NotificationRemoteInputManager notificationRemoteInputManager, ConversationNotificationProcessor conversationNotificationProcessor, RichOngoingNotificationContentExtractor richOngoingNotificationContentExtractor, RichOngoingNotificationViewInflaterImpl richOngoingNotificationViewInflaterImpl, Executor executor, SmartReplyStateInflaterImpl smartReplyStateInflaterImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26, HeadsUpStyleProviderImpl headsUpStyleProviderImpl, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
        this.remoteViewCache = notifRemoteViewCache;
        this.remoteInputManager = notificationRemoteInputManager;
        this.conversationProcessor = conversationNotificationProcessor;
        this.ronExtractor = richOngoingNotificationContentExtractor;
        this.ronInflater = richOngoingNotificationViewInflaterImpl;
        this.inflationExecutor = executor;
        this.smartReplyStateInflater = smartReplyStateInflaterImpl;
        this.notifLayoutInflaterFactoryProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$26;
        this.headsUpStyleProvider = headsUpStyleProviderImpl;
        this.logger = notificationRowContentBinderLogger;
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.notification_row_content_binder_refactor to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.notification_row_content_binder_refactor to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects com.android.systemui.notification_row_content_binder_refactor to be enabled.");
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder
    public final void bindContent(NotificationEntry notificationEntry, ExpandableNotificationRow expandableNotificationRow, int i, NotificationRowContentBinder.BindParams bindParams, boolean z, RowContentBindStage.AnonymousClass1 anonymousClass1) {
        SparseArray sparseArray;
        NotificationRowContentBinderLogger notificationRowContentBinderLogger = this.logger;
        LogLevel logLevel = LogLevel.DEBUG;
        NotificationRowContentBinderLogger$logBinding$2 notificationRowContentBinderLogger$logBinding$2 = NotificationRowContentBinderLogger$logBinding$2.INSTANCE;
        LogBuffer logBuffer = notificationRowContentBinderLogger.buffer;
        LogMessage obtain = logBuffer.obtain("NotificationRowContentBinder", logLevel, notificationRowContentBinderLogger$logBinding$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.int1 = i;
        logBuffer.commit(obtain);
        expandableNotificationRow.mImageResolver.preloadImages(notificationEntry.mSbn.getNotification());
        if (z && (sparseArray = (SparseArray) ((ArrayMap) ((NotifRemoteViewCacheImpl) this.remoteViewCache).mNotifCachedContentViews).get(notificationEntry)) != null) {
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
        if ((i & 16) != 0) {
            expandableNotificationRow.mPrivateLayout.removeContentInactiveRunnable(3);
        }
        AsyncInflationTask asyncInflationTask = new AsyncInflationTask(this.inflationExecutor, this.inflateSynchronously, i, this.remoteViewCache, notificationEntry, this.conversationProcessor, this.ronExtractor, this.ronInflater, expandableNotificationRow, bindParams.isMinimized, bindParams.usesIncreasedHeight, bindParams.usesIncreasedHeadsUpHeight, anonymousClass1, this.remoteInputManager.mInteractionHandler, this.smartReplyStateInflater, this.notifLayoutInflaterFactoryProvider, this.headsUpStyleProvider, notificationRowContentBinderLogger);
        if (!this.inflateSynchronously) {
            asyncInflationTask.executeOnExecutor(this.inflationExecutor, new Void[0]);
        } else {
            Void[] voidArr = new Void[0];
            asyncInflationTask.onPostExecute(new Result(asyncInflationTask.m880doInBackgroundIoAF18A()));
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder
    public final boolean cancelBind(NotificationEntry notificationEntry) {
        boolean abortTask = notificationEntry.abortTask();
        if (abortTask) {
            NotificationRowContentBinderLogger notificationRowContentBinderLogger = this.logger;
            LogLevel logLevel = LogLevel.INFO;
            NotificationRowContentBinderLogger$logCancelBindAbortedTask$2 notificationRowContentBinderLogger$logCancelBindAbortedTask$2 = NotificationRowContentBinderLogger$logCancelBindAbortedTask$2.INSTANCE;
            LogBuffer logBuffer = notificationRowContentBinderLogger.buffer;
            LogMessage obtain = logBuffer.obtain("NotificationRowContentBinder", logLevel, notificationRowContentBinderLogger$logCancelBindAbortedTask$2, null);
            ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logBuffer.commit(obtain);
        }
        return abortTask;
    }

    public final InflationProgress inflateNotificationViews(NotificationEntry notificationEntry, ExpandableNotificationRow expandableNotificationRow, NotificationRowContentBinder.BindParams bindParams, boolean z, int i, Notification.Builder builder, Context context, SmartReplyStateInflater smartReplyStateInflater) {
        NotificationRowContentBinderLogger notificationRowContentBinderLogger;
        HybridNotificationView hybridNotificationView;
        Context context2 = expandableNotificationRow.getContext();
        boolean z2 = bindParams.isMinimized;
        boolean z3 = bindParams.usesIncreasedHeight;
        boolean z4 = bindParams.usesIncreasedHeadsUpHeight;
        Intrinsics.checkNotNull(context2);
        NotificationRowContentBinderLogger notificationRowContentBinderLogger2 = this.logger;
        InflationProgress access$beginInflationAsync = Companion.access$beginInflationAsync(i, notificationEntry, builder, z2, z3, z4, context2, context, expandableNotificationRow, this.notifLayoutInflaterFactoryProvider, this.headsUpStyleProvider, this.conversationProcessor, this.ronExtractor, notificationRowContentBinderLogger2);
        Companion.access$inflateSmartReplyViews(access$beginInflationAsync, i, notificationEntry, context2, context, expandableNotificationRow.mPrivateLayout.mCurrentSmartReplyState, smartReplyStateInflater, notificationRowContentBinderLogger2);
        SingleLineViewModel singleLineViewModel = access$beginInflationAsync.contentModel.singleLineViewModel;
        if (singleLineViewModel != null) {
            notificationRowContentBinderLogger = notificationRowContentBinderLogger2;
            hybridNotificationView = SingleLineViewInflater.inflatePrivateSingleLineView(singleLineViewModel.conversationData != null, i, notificationEntry, context2, notificationRowContentBinderLogger);
        } else {
            notificationRowContentBinderLogger = notificationRowContentBinderLogger2;
            hybridNotificationView = null;
        }
        access$beginInflationAsync.inflatedSingleLineView = hybridNotificationView;
        Companion.access$apply(this.inflationExecutor, z, bindParams.isMinimized, access$beginInflationAsync, i, this.remoteViewCache, notificationEntry, expandableNotificationRow, this.remoteInputManager.mInteractionHandler, null, notificationRowContentBinderLogger);
        return access$beginInflationAsync;
    }

    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder
    public void setInflateSynchronously(boolean z) {
        this.inflateSynchronously = z;
    }

    @Override // com.android.systemui.statusbar.notification.row.NotificationRowContentBinder
    public final void unbindContent(final NotificationEntry notificationEntry, final ExpandableNotificationRow expandableNotificationRow, int i) {
        NotificationRowContentBinderLogger notificationRowContentBinderLogger = this.logger;
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
                    expandableNotificationRow.mPrivateLayout.performWhenContentInactive(0, new Runnable() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$freeNotificationView$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i3) {
                                case 0:
                                    DisposableHandle disposableHandle = expandableNotificationRow.mPrivateLayout.mContractedBinderHandle;
                                    if (disposableHandle != null) {
                                        disposableHandle.dispose();
                                    }
                                    NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView.mContractedBinderHandle = null;
                                    notificationContentView.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 1);
                                    break;
                                case 1:
                                    DisposableHandle disposableHandle2 = expandableNotificationRow.mPrivateLayout.mExpandedBinderHandle;
                                    if (disposableHandle2 != null) {
                                        disposableHandle2.dispose();
                                    }
                                    NotificationContentView notificationContentView2 = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView2.mExpandedBinderHandle = null;
                                    notificationContentView2.setExpandedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 2);
                                    break;
                                case 2:
                                    DisposableHandle disposableHandle3 = expandableNotificationRow.mPrivateLayout.mHeadsUpBinderHandle;
                                    if (disposableHandle3 != null) {
                                        disposableHandle3.dispose();
                                    }
                                    NotificationContentView notificationContentView3 = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView3.mHeadsUpBinderHandle = null;
                                    notificationContentView3.setHeadsUpChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 4);
                                    expandableNotificationRow.mPrivateLayout.setHeadsUpInflatedSmartReplies(null);
                                    break;
                                default:
                                    expandableNotificationRow.mPublicLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 8);
                                    break;
                            }
                        }
                    });
                } else if (i2 == 2) {
                    final int i4 = 1;
                    expandableNotificationRow.mPrivateLayout.performWhenContentInactive(1, new Runnable() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$freeNotificationView$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i4) {
                                case 0:
                                    DisposableHandle disposableHandle = expandableNotificationRow.mPrivateLayout.mContractedBinderHandle;
                                    if (disposableHandle != null) {
                                        disposableHandle.dispose();
                                    }
                                    NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView.mContractedBinderHandle = null;
                                    notificationContentView.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 1);
                                    break;
                                case 1:
                                    DisposableHandle disposableHandle2 = expandableNotificationRow.mPrivateLayout.mExpandedBinderHandle;
                                    if (disposableHandle2 != null) {
                                        disposableHandle2.dispose();
                                    }
                                    NotificationContentView notificationContentView2 = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView2.mExpandedBinderHandle = null;
                                    notificationContentView2.setExpandedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 2);
                                    break;
                                case 2:
                                    DisposableHandle disposableHandle3 = expandableNotificationRow.mPrivateLayout.mHeadsUpBinderHandle;
                                    if (disposableHandle3 != null) {
                                        disposableHandle3.dispose();
                                    }
                                    NotificationContentView notificationContentView3 = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView3.mHeadsUpBinderHandle = null;
                                    notificationContentView3.setHeadsUpChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 4);
                                    expandableNotificationRow.mPrivateLayout.setHeadsUpInflatedSmartReplies(null);
                                    break;
                                default:
                                    expandableNotificationRow.mPublicLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 8);
                                    break;
                            }
                        }
                    });
                } else if (i2 == 4) {
                    final int i5 = 2;
                    expandableNotificationRow.mPrivateLayout.performWhenContentInactive(2, new Runnable() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$freeNotificationView$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i5) {
                                case 0:
                                    DisposableHandle disposableHandle = expandableNotificationRow.mPrivateLayout.mContractedBinderHandle;
                                    if (disposableHandle != null) {
                                        disposableHandle.dispose();
                                    }
                                    NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView.mContractedBinderHandle = null;
                                    notificationContentView.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 1);
                                    break;
                                case 1:
                                    DisposableHandle disposableHandle2 = expandableNotificationRow.mPrivateLayout.mExpandedBinderHandle;
                                    if (disposableHandle2 != null) {
                                        disposableHandle2.dispose();
                                    }
                                    NotificationContentView notificationContentView2 = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView2.mExpandedBinderHandle = null;
                                    notificationContentView2.setExpandedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 2);
                                    break;
                                case 2:
                                    DisposableHandle disposableHandle3 = expandableNotificationRow.mPrivateLayout.mHeadsUpBinderHandle;
                                    if (disposableHandle3 != null) {
                                        disposableHandle3.dispose();
                                    }
                                    NotificationContentView notificationContentView3 = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView3.mHeadsUpBinderHandle = null;
                                    notificationContentView3.setHeadsUpChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 4);
                                    expandableNotificationRow.mPrivateLayout.setHeadsUpInflatedSmartReplies(null);
                                    break;
                                default:
                                    expandableNotificationRow.mPublicLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 8);
                                    break;
                            }
                        }
                    });
                } else if (i2 == 8) {
                    final int i6 = 3;
                    expandableNotificationRow.mPublicLayout.performWhenContentInactive(0, new Runnable() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$freeNotificationView$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i6) {
                                case 0:
                                    DisposableHandle disposableHandle = expandableNotificationRow.mPrivateLayout.mContractedBinderHandle;
                                    if (disposableHandle != null) {
                                        disposableHandle.dispose();
                                    }
                                    NotificationContentView notificationContentView = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView.mContractedBinderHandle = null;
                                    notificationContentView.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 1);
                                    break;
                                case 1:
                                    DisposableHandle disposableHandle2 = expandableNotificationRow.mPrivateLayout.mExpandedBinderHandle;
                                    if (disposableHandle2 != null) {
                                        disposableHandle2.dispose();
                                    }
                                    NotificationContentView notificationContentView2 = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView2.mExpandedBinderHandle = null;
                                    notificationContentView2.setExpandedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 2);
                                    break;
                                case 2:
                                    DisposableHandle disposableHandle3 = expandableNotificationRow.mPrivateLayout.mHeadsUpBinderHandle;
                                    if (disposableHandle3 != null) {
                                        disposableHandle3.dispose();
                                    }
                                    NotificationContentView notificationContentView3 = expandableNotificationRow.mPrivateLayout;
                                    notificationContentView3.mHeadsUpBinderHandle = null;
                                    notificationContentView3.setHeadsUpChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 4);
                                    expandableNotificationRow.mPrivateLayout.setHeadsUpInflatedSmartReplies(null);
                                    break;
                                default:
                                    expandableNotificationRow.mPublicLayout.setContractedChild(null);
                                    ((NotifRemoteViewCacheImpl) this.remoteViewCache).removeCachedView(notificationEntry, 8);
                                    break;
                            }
                        }
                    });
                } else if (i2 == 16) {
                    expandableNotificationRow.mPrivateLayout.performWhenContentInactive(3, new Runnable() { // from class: com.android.systemui.statusbar.notification.row.NotificationRowContentBinderImpl$freeNotificationView$5
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

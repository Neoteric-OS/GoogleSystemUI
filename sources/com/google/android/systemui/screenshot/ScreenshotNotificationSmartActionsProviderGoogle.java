package com.google.android.systemui.screenshot;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$Contents;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$Feedback;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$QuickShareInfo;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotActionFeedback;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotFeedback;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOp;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpFeedback;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpStatus;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.InteractionContextParcelables$InteractionContext;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ParserParcelables$ParsedViewHierarchy;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Entities;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Entity;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$InteractionType;
import com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapper$BundleCallback;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapperImpl;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda1;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.LogUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.Utils;
import com.google.android.systemui.screenshot.ScreenshotNotificationSmartActionsProviderGoogle;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotNotificationSmartActionsProviderGoogle extends ScreenshotNotificationSmartActionsProvider {
    public static final ImmutableMap SCREENSHOT_INTERACTION_TYPE_MAP;
    public static final ImmutableMap SCREENSHOT_OP_MAP;
    public static final ImmutableMap SCREENSHOT_OP_STATUS_MAP;
    public final ContentSuggestionsServiceClient mClient;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.screenshot.ScreenshotNotificationSmartActionsProviderGoogle$1, reason: invalid class name */
    public final class AnonymousClass1 implements ContentSuggestionsServiceWrapper$BundleCallback {
        public final /* synthetic */ CompletableFuture val$future;
        public final /* synthetic */ String val$screenshotId;
        public final /* synthetic */ long val$startTimeMs;

        public AnonymousClass1(CompletableFuture completableFuture, long j, String str) {
            this.val$future = completableFuture;
            this.val$startTimeMs = j;
            this.val$screenshotId = str;
        }

        @Override // com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapper$BundleCallback
        public final void onResult(Bundle bundle) {
            ScreenshotNotificationSmartActionsProviderGoogle.this.completeFuture(bundle, this.val$future);
            long uptimeMillis = SystemClock.uptimeMillis() - this.val$startTimeMs;
            Log.d("ScreenshotActionsGoogle", String.format("Total time taken to get smart actions: %d ms", Long.valueOf(uptimeMillis)));
            ScreenshotNotificationSmartActionsProviderGoogle.this.notifyOp(this.val$screenshotId, ScreenshotNotificationSmartActionsProvider.ScreenshotOp.RETRIEVE_SMART_ACTIONS, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.SUCCESS, uptimeMillis);
        }
    }

    static {
        int i = ImmutableMap.$r8$clinit;
        ImmutableMap.Builder builder = new ImmutableMap.Builder(4);
        builder.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOp.RETRIEVE_SMART_ACTIONS, FeedbackParcelables$ScreenshotOp.RETRIEVE_SMART_ACTIONS);
        builder.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOp.REQUEST_SMART_ACTIONS, FeedbackParcelables$ScreenshotOp.REQUEST_SMART_ACTIONS);
        builder.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOp.WAIT_FOR_SMART_ACTIONS, FeedbackParcelables$ScreenshotOp.WAIT_FOR_SMART_ACTIONS);
        SCREENSHOT_OP_MAP = builder.buildOrThrow();
        ImmutableMap.Builder builder2 = new ImmutableMap.Builder(4);
        builder2.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.SUCCESS, FeedbackParcelables$ScreenshotOpStatus.SUCCESS);
        builder2.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.ERROR, FeedbackParcelables$ScreenshotOpStatus.ERROR);
        builder2.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.TIMEOUT, FeedbackParcelables$ScreenshotOpStatus.TIMEOUT);
        SCREENSHOT_OP_STATUS_MAP = builder2.buildOrThrow();
        ImmutableMap.Builder builder3 = new ImmutableMap.Builder(4);
        builder3.put(ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType.REGULAR_SMART_ACTIONS, SuggestParcelables$InteractionType.SCREENSHOT_NOTIFICATION);
        builder3.put(ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType.QUICK_SHARE_ACTION, SuggestParcelables$InteractionType.QUICK_SHARE);
        SCREENSHOT_INTERACTION_TYPE_MAP = builder3.buildOrThrow();
    }

    public ScreenshotNotificationSmartActionsProviderGoogle(Context context, Executor executor, Handler handler) {
        this.mClient = new ContentSuggestionsServiceClient(context, executor, handler);
    }

    public void completeFuture(Bundle bundle, CompletableFuture completableFuture) {
        if (bundle.containsKey("ScreenshotNotificationActions")) {
            completableFuture.complete(bundle.getParcelableArrayList("ScreenshotNotificationActions"));
        } else {
            completableFuture.complete(Collections.emptyList());
        }
    }

    @Override // com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider
    public final CompletableFuture getActions(String str, final Uri uri, Bitmap bitmap, ComponentName componentName, ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType screenshotSmartActionType, final UserHandle userHandle) {
        CompletableFuture completableFuture = new CompletableFuture();
        if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
            Log.e("ScreenshotActionsGoogle", "Bitmap expected: Hardware, Bitmap found: " + bitmap.getConfig() + ". Returning empty list.");
            return CompletableFuture.completedFuture(Collections.emptyList());
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        Log.d("ScreenshotActionsGoogle", "Calling AiAi to obtain screenshot notification smart actions.");
        final String packageName = componentName.getPackageName();
        final String className = componentName.getClassName();
        ImmutableMap immutableMap = SCREENSHOT_INTERACTION_TYPE_MAP;
        Object obj = SuggestParcelables$InteractionType.SCREENSHOT_NOTIFICATION;
        Object obj2 = immutableMap.get(screenshotSmartActionType);
        if (obj2 != null) {
            obj = obj2;
        }
        SuggestParcelables$InteractionType suggestParcelables$InteractionType = (SuggestParcelables$InteractionType) obj;
        final AnonymousClass1 anonymousClass1 = new AnonymousClass1(completableFuture, uptimeMillis, str);
        final ContentSuggestionsServiceClient contentSuggestionsServiceClient = this.mClient;
        if (contentSuggestionsServiceClient.isAiAiVersionSupported) {
            final int nextInt = ContentSuggestionsServiceClient.random.nextInt();
            final long currentTimeMillis = System.currentTimeMillis();
            contentSuggestionsServiceClient.bundleUtils.getClass();
            final Bundle bundle = new Bundle();
            bundle.putInt("CONTEXT_IMAGE_BUNDLE_VERSION_KEY", 1);
            bundle.putBoolean("CONTEXT_IMAGE_PRIMARY_TASK_KEY", true);
            bundle.putString("CONTEXT_IMAGE_PACKAGE_NAME_KEY", packageName);
            bundle.putString("CONTEXT_IMAGE_ACTIVITY_NAME_KEY", className);
            bundle.putLong("CONTEXT_IMAGE_CAPTURE_TIME_MS_KEY", currentTimeMillis);
            bundle.putParcelable("CONTEXT_IMAGE_BITMAP_URI_KEY", uri);
            bundle.putParcelable("android.contentsuggestions.extra.BITMAP", bitmap);
            final InteractionContextParcelables$InteractionContext interactionContextParcelables$InteractionContext = new InteractionContextParcelables$InteractionContext();
            interactionContextParcelables$InteractionContext.interactionType = suggestParcelables$InteractionType;
            interactionContextParcelables$InteractionContext.versionCode = 1;
            interactionContextParcelables$InteractionContext.isPrimaryTask = true;
            contentSuggestionsServiceClient.serviceWrapper.asyncExecutor.execute(new Runnable() { // from class: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ContentSuggestionsServiceClient contentSuggestionsServiceClient2 = ContentSuggestionsServiceClient.this;
                    int i = nextInt;
                    Bundle bundle2 = bundle;
                    String str2 = packageName;
                    String str3 = className;
                    long j = currentTimeMillis;
                    InteractionContextParcelables$InteractionContext interactionContextParcelables$InteractionContext2 = interactionContextParcelables$InteractionContext;
                    UserHandle userHandle2 = userHandle;
                    Uri uri2 = uri;
                    ScreenshotNotificationSmartActionsProviderGoogle.AnonymousClass1 anonymousClass12 = anonymousClass1;
                    ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = contentSuggestionsServiceClient2.serviceWrapper;
                    contentSuggestionsServiceWrapperImpl.getClass();
                    bundle2.putLong("CAPTURE_TIME_MS", System.currentTimeMillis());
                    try {
                        contentSuggestionsServiceWrapperImpl.contentSuggestionsManager.provideContextImage(i, bundle2);
                    } catch (Throwable th) {
                        LogUtils.e("Failed to provideContextImage", th);
                    }
                    Bundle bundle3 = new Bundle();
                    ParserParcelables$ParsedViewHierarchy parserParcelables$ParsedViewHierarchy = new ParserParcelables$ParsedViewHierarchy();
                    contentSuggestionsServiceClient2.bundleUtils.getClass();
                    Bundle createSelectionsRequest = BundleUtils.createSelectionsRequest(str2, str3, i, j, interactionContextParcelables$InteractionContext2, bundle3, parserParcelables$ParsedViewHierarchy);
                    createSelectionsRequest.putBoolean("IsManagedProfile", contentSuggestionsServiceClient2.userManager.isManagedProfile(userHandle2.getIdentifier()));
                    createSelectionsRequest.putParcelable("UserHandle", userHandle2);
                    contentSuggestionsServiceWrapperImpl.suggestContentSelections(i, createSelectionsRequest, new ContentSuggestionsServiceWrapper$BundleCallback() { // from class: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient.1
                        public final /* synthetic */ ScreenshotNotificationSmartActionsProviderGoogle.AnonymousClass1 val$bundleCallback;
                        public final /* synthetic */ long val$captureTimestampMs;
                        public final /* synthetic */ String val$className;
                        public final /* synthetic */ InteractionContextParcelables$InteractionContext val$interactionContext;
                        public final /* synthetic */ String val$packageName;
                        public final /* synthetic */ Uri val$screenshotUri;
                        public final /* synthetic */ int val$taskId;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient$1$1 */
                        public final class C02701 implements ContentSuggestionsServiceWrapper$BundleCallback {
                            public C02701() {
                            }

                            @Override // com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapper$BundleCallback
                            public final void onResult(Bundle bundle) {
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                ScreenshotNotificationSmartActionsProviderGoogle.AnonymousClass1 anonymousClass12 = r9;
                                ContentSuggestionsServiceClient contentSuggestionsServiceClient = ContentSuggestionsServiceClient.this;
                                try {
                                    contentSuggestionsServiceClient.bundleUtils.getClass();
                                    bundle.setClassLoader(EntitiesData.class.getClassLoader());
                                    EntitiesData entitiesData = (EntitiesData) bundle.getParcelable("EntitiesData");
                                    SuggestParcelables$Entities suggestParcelables$Entities = entitiesData.entities() == null ? new SuggestParcelables$Entities() : entitiesData.entities();
                                    ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                                    List list = suggestParcelables$Entities.entities;
                                    if (list != null) {
                                        int i = Utils.$r8$clinit;
                                        Iterator it = list.iterator();
                                        while (it.hasNext()) {
                                            Notification.Action m910$$Nest$smgenerateNotificationAction = ContentSuggestionsServiceClient.m910$$Nest$smgenerateNotificationAction(contentSuggestionsServiceClient.context, (SuggestParcelables$Entity) it.next(), entitiesData, r8);
                                            if (m910$$Nest$smgenerateNotificationAction != null) {
                                                arrayList.add(m910$$Nest$smgenerateNotificationAction);
                                            }
                                        }
                                    }
                                    contentSuggestionsServiceClient.bundleUtils.getClass();
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putParcelableArrayList("ScreenshotNotificationActions", arrayList);
                                    anonymousClass12.onResult(bundle2);
                                } catch (Throwable th) {
                                    LogUtils.e("Failed to handle classification result while generating smart actions for screenshot notification", th);
                                    anonymousClass12.onResult(Bundle.EMPTY);
                                }
                            }
                        }

                        public AnonymousClass1(String str22, String str32, int i2, long j2, InteractionContextParcelables$InteractionContext interactionContextParcelables$InteractionContext22, Uri uri22, ScreenshotNotificationSmartActionsProviderGoogle.AnonymousClass1 anonymousClass122) {
                            r2 = str22;
                            r3 = str32;
                            r4 = i2;
                            r5 = j2;
                            r7 = interactionContextParcelables$InteractionContext22;
                            r8 = uri22;
                            r9 = anonymousClass122;
                        }

                        @Override // com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapper$BundleCallback
                        public final void onResult(Bundle bundle4) {
                            ContentSuggestionsServiceClient contentSuggestionsServiceClient3 = ContentSuggestionsServiceClient.this;
                            try {
                                contentSuggestionsServiceClient3.bundleUtils.getClass();
                                ContentParcelables$Contents extractContents = BundleUtils.extractContents(bundle4);
                                BundleUtils bundleUtils = contentSuggestionsServiceClient3.bundleUtils;
                                String str4 = r2;
                                String str5 = r3;
                                int i2 = r4;
                                long j2 = r5;
                                Bundle bundle5 = new Bundle();
                                InteractionContextParcelables$InteractionContext interactionContextParcelables$InteractionContext3 = r7;
                                bundleUtils.getClass();
                                contentSuggestionsServiceClient3.serviceWrapper.classifyContentSelections(BundleUtils.createClassificationsRequest(str4, str5, i2, j2, bundle5, interactionContextParcelables$InteractionContext3, extractContents), new ContentSuggestionsServiceWrapper$BundleCallback() { // from class: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient.1.1
                                    public C02701() {
                                    }

                                    @Override // com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapper$BundleCallback
                                    public final void onResult(Bundle bundle6) {
                                        AnonymousClass1 anonymousClass13 = AnonymousClass1.this;
                                        ScreenshotNotificationSmartActionsProviderGoogle.AnonymousClass1 anonymousClass122 = r9;
                                        ContentSuggestionsServiceClient contentSuggestionsServiceClient4 = ContentSuggestionsServiceClient.this;
                                        try {
                                            contentSuggestionsServiceClient4.bundleUtils.getClass();
                                            bundle6.setClassLoader(EntitiesData.class.getClassLoader());
                                            EntitiesData entitiesData = (EntitiesData) bundle6.getParcelable("EntitiesData");
                                            SuggestParcelables$Entities suggestParcelables$Entities = entitiesData.entities() == null ? new SuggestParcelables$Entities() : entitiesData.entities();
                                            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                                            List list = suggestParcelables$Entities.entities;
                                            if (list != null) {
                                                int i3 = Utils.$r8$clinit;
                                                Iterator it = list.iterator();
                                                while (it.hasNext()) {
                                                    Notification.Action m910$$Nest$smgenerateNotificationAction = ContentSuggestionsServiceClient.m910$$Nest$smgenerateNotificationAction(contentSuggestionsServiceClient4.context, (SuggestParcelables$Entity) it.next(), entitiesData, r8);
                                                    if (m910$$Nest$smgenerateNotificationAction != null) {
                                                        arrayList.add(m910$$Nest$smgenerateNotificationAction);
                                                    }
                                                }
                                            }
                                            contentSuggestionsServiceClient4.bundleUtils.getClass();
                                            Bundle bundle22 = new Bundle();
                                            bundle22.putParcelableArrayList("ScreenshotNotificationActions", arrayList);
                                            anonymousClass122.onResult(bundle22);
                                        } catch (Throwable th2) {
                                            LogUtils.e("Failed to handle classification result while generating smart actions for screenshot notification", th2);
                                            anonymousClass122.onResult(Bundle.EMPTY);
                                        }
                                    }
                                });
                            } catch (Throwable th2) {
                                LogUtils.e("Failed to handle selections response while generating smart actions for screenshot notification", th2);
                                r9.onResult(Bundle.EMPTY);
                            }
                        }
                    });
                }
            });
        } else {
            anonymousClass1.onResult(Bundle.EMPTY);
        }
        return completableFuture;
    }

    @Override // com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider
    public final void notifyAction(final String str, final String str2, final Intent intent) {
        final ContentSuggestionsServiceClient contentSuggestionsServiceClient = this.mClient;
        contentSuggestionsServiceClient.getClass();
        Supplier supplier = new Supplier() { // from class: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                ContentSuggestionsServiceClient contentSuggestionsServiceClient2 = ContentSuggestionsServiceClient.this;
                String str3 = str;
                String str4 = str2;
                Intent intent2 = intent;
                contentSuggestionsServiceClient2.getClass();
                ArrayList arrayList = new ArrayList();
                FeedbackParcelables$ScreenshotActionFeedback feedbackParcelables$ScreenshotActionFeedback = new FeedbackParcelables$ScreenshotActionFeedback();
                feedbackParcelables$ScreenshotActionFeedback.actionType = str4;
                if ("QUICK_SHARE".equals(str4) && intent2 != null && intent2.getComponent() != null) {
                    FeedbackParcelables$QuickShareInfo feedbackParcelables$QuickShareInfo = new FeedbackParcelables$QuickShareInfo();
                    Uri uri = (Uri) intent2.getParcelableExtra("android.intent.extra.STREAM");
                    if (uri != null) {
                        feedbackParcelables$QuickShareInfo.contentUri = uri.toString();
                    }
                    ComponentName component = intent2.getComponent();
                    int i = Utils.$r8$clinit;
                    component.getClass();
                    feedbackParcelables$QuickShareInfo.targetPackageName = component.getPackageName();
                    ComponentName component2 = intent2.getComponent();
                    component2.getClass();
                    feedbackParcelables$QuickShareInfo.targetClassName = component2.getClassName();
                    feedbackParcelables$QuickShareInfo.targetShortcutId = intent2.getStringExtra("android.intent.extra.shortcut.ID");
                    feedbackParcelables$ScreenshotActionFeedback.quickShareInfo = feedbackParcelables$QuickShareInfo;
                }
                FeedbackParcelables$ScreenshotFeedback feedbackParcelables$ScreenshotFeedback = new FeedbackParcelables$ScreenshotFeedback();
                feedbackParcelables$ScreenshotFeedback.screenshotId = str3;
                feedbackParcelables$ScreenshotFeedback.screenshotFeedback = feedbackParcelables$ScreenshotActionFeedback;
                FeedbackParcelables$Feedback feedbackParcelables$Feedback = new FeedbackParcelables$Feedback();
                arrayList.add(feedbackParcelables$Feedback);
                feedbackParcelables$Feedback.feedback = feedbackParcelables$ScreenshotFeedback;
                FeedbackParcelables$FeedbackBatch feedbackParcelables$FeedbackBatch = new FeedbackParcelables$FeedbackBatch();
                feedbackParcelables$FeedbackBatch.screenSessionId = 0;
                feedbackParcelables$FeedbackBatch.overviewSessionId = str3;
                int i2 = Utils.$r8$clinit;
                feedbackParcelables$FeedbackBatch.feedback = arrayList;
                contentSuggestionsServiceClient2.bundleUtils.getClass();
                return BundleUtils.createFeedbackRequest(feedbackParcelables$FeedbackBatch);
            }
        };
        ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = contentSuggestionsServiceClient.serviceWrapper;
        contentSuggestionsServiceWrapperImpl.loggingExecutor.execute(new ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda1(contentSuggestionsServiceWrapperImpl, supplier, str, null));
    }

    @Override // com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider
    public final void notifyOp(final String str, ScreenshotNotificationSmartActionsProvider.ScreenshotOp screenshotOp, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus screenshotOpStatus, final long j) {
        ImmutableMap immutableMap = SCREENSHOT_OP_MAP;
        Object obj = FeedbackParcelables$ScreenshotOp.OP_UNKNOWN;
        Object obj2 = immutableMap.get(screenshotOp);
        if (obj2 != null) {
            obj = obj2;
        }
        final FeedbackParcelables$ScreenshotOp feedbackParcelables$ScreenshotOp = (FeedbackParcelables$ScreenshotOp) obj;
        ImmutableMap immutableMap2 = SCREENSHOT_OP_STATUS_MAP;
        Object obj3 = FeedbackParcelables$ScreenshotOpStatus.OP_STATUS_UNKNOWN;
        Object obj4 = immutableMap2.get(screenshotOpStatus);
        if (obj4 != null) {
            obj3 = obj4;
        }
        final FeedbackParcelables$ScreenshotOpStatus feedbackParcelables$ScreenshotOpStatus = (FeedbackParcelables$ScreenshotOpStatus) obj3;
        final ContentSuggestionsServiceClient contentSuggestionsServiceClient = this.mClient;
        contentSuggestionsServiceClient.getClass();
        Supplier supplier = new Supplier() { // from class: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                ContentSuggestionsServiceClient contentSuggestionsServiceClient2 = ContentSuggestionsServiceClient.this;
                String str2 = str;
                FeedbackParcelables$ScreenshotOp feedbackParcelables$ScreenshotOp2 = feedbackParcelables$ScreenshotOp;
                FeedbackParcelables$ScreenshotOpStatus feedbackParcelables$ScreenshotOpStatus2 = feedbackParcelables$ScreenshotOpStatus;
                long j2 = j;
                contentSuggestionsServiceClient2.getClass();
                ArrayList arrayList = new ArrayList();
                FeedbackParcelables$ScreenshotOpFeedback feedbackParcelables$ScreenshotOpFeedback = new FeedbackParcelables$ScreenshotOpFeedback();
                feedbackParcelables$ScreenshotOpFeedback.durationMs = j2;
                feedbackParcelables$ScreenshotOpFeedback.op = feedbackParcelables$ScreenshotOp2;
                feedbackParcelables$ScreenshotOpFeedback.status = feedbackParcelables$ScreenshotOpStatus2;
                FeedbackParcelables$ScreenshotFeedback feedbackParcelables$ScreenshotFeedback = new FeedbackParcelables$ScreenshotFeedback();
                feedbackParcelables$ScreenshotFeedback.screenshotId = str2;
                feedbackParcelables$ScreenshotFeedback.screenshotFeedback = feedbackParcelables$ScreenshotOpFeedback;
                FeedbackParcelables$Feedback feedbackParcelables$Feedback = new FeedbackParcelables$Feedback();
                arrayList.add(feedbackParcelables$Feedback);
                feedbackParcelables$Feedback.feedback = feedbackParcelables$ScreenshotFeedback;
                FeedbackParcelables$FeedbackBatch feedbackParcelables$FeedbackBatch = new FeedbackParcelables$FeedbackBatch();
                feedbackParcelables$FeedbackBatch.screenSessionId = 0;
                feedbackParcelables$FeedbackBatch.overviewSessionId = str2;
                int i = Utils.$r8$clinit;
                feedbackParcelables$FeedbackBatch.feedback = arrayList;
                contentSuggestionsServiceClient2.bundleUtils.getClass();
                return BundleUtils.createFeedbackRequest(feedbackParcelables$FeedbackBatch);
            }
        };
        ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = contentSuggestionsServiceClient.serviceWrapper;
        contentSuggestionsServiceWrapperImpl.loggingExecutor.execute(new ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda1(contentSuggestionsServiceWrapperImpl, supplier, str, null));
    }
}

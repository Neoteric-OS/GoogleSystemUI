package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.InteractionContextParcelables$InteractionContext;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Action;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$ActionGroup;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Entity;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$ErrorCode;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$IntentInfo;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$IntentType;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$InteractionType;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$SetupInfo;
import com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.LogUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContentSuggestionsServiceClient {
    public static final Random random = new Random();
    public final BundleUtils bundleUtils;
    public final Context context;
    public final boolean isAiAiVersionSupported;
    public final ContentSuggestionsServiceWrapperImpl serviceWrapper;
    public final UserManager userManager;

    /* renamed from: -$$Nest$smgenerateNotificationAction, reason: not valid java name */
    public static Notification.Action m910$$Nest$smgenerateNotificationAction(Context context, SuggestParcelables$Entity suggestParcelables$Entity, EntitiesData entitiesData, Uri uri) {
        String str;
        List list = suggestParcelables$Entity.actions;
        if (list == null) {
            return null;
        }
        int i = Utils.$r8$clinit;
        if (list.isEmpty()) {
            return null;
        }
        List list2 = suggestParcelables$Entity.actions;
        list2.getClass();
        int i2 = 0;
        SuggestParcelables$Action suggestParcelables$Action = ((SuggestParcelables$ActionGroup) ((ArrayList) list2).get(0)).mainAction;
        if (suggestParcelables$Action == null || suggestParcelables$Action.id == null) {
            return null;
        }
        if (uri != null && suggestParcelables$Action.hasProxiedIntentInfo) {
            SuggestParcelables$IntentInfo suggestParcelables$IntentInfo = suggestParcelables$Action.proxiedIntentInfo;
            suggestParcelables$IntentInfo.getClass();
            if (suggestParcelables$IntentInfo.intentType == SuggestParcelables$IntentType.LENS) {
                context.grantUriPermission("com.google.android.googlequicksearchbox", uri, 1);
            }
        }
        String str2 = suggestParcelables$Action.id;
        str2.getClass();
        Bitmap bitmap = entitiesData.getBitmap(str2);
        String str3 = suggestParcelables$Action.id;
        str3.getClass();
        PendingIntent pendingIntent = entitiesData.getPendingIntent(str3);
        if (pendingIntent == null || bitmap == null) {
            return null;
        }
        String str4 = suggestParcelables$Action.displayName;
        str4.getClass();
        String str5 = suggestParcelables$Action.fullDisplayName;
        str5.getClass();
        String str6 = suggestParcelables$Entity.searchQueryHint;
        str6.getClass();
        String[] strArr = {str4, str5, str6};
        while (true) {
            if (i2 >= 3) {
                str = null;
                break;
            }
            str = strArr[i2];
            if (!TextUtils.isEmpty(str)) {
                break;
            }
            i2++;
        }
        if (str == null) {
            return null;
        }
        RemoteAction remoteAction = new RemoteAction(Icon.createWithBitmap(bitmap), str, str, pendingIntent);
        String str7 = TextUtils.isEmpty(suggestParcelables$Entity.searchQueryHint) ? "Smart Action" : suggestParcelables$Entity.searchQueryHint;
        str7.getClass();
        Icon icon = remoteAction.shouldShowIcon() ? remoteAction.getIcon() : null;
        Bundle bundle = new Bundle();
        bundle.putString("action_type", str7);
        bundle.putFloat("action_score", 1.0f);
        return new Notification.Action.Builder(icon, remoteAction.getTitle(), remoteAction.getActionIntent()).setContextual(true).addExtras(bundle).build();
    }

    public ContentSuggestionsServiceClient(Context context, Executor executor, Handler handler) {
        this.context = context;
        Objects.requireNonNull(handler);
        final SuggestController suggestController = new SuggestController(context, context, executor, executor);
        Runnable runnable = new Runnable() { // from class: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.SuggestController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                final SuggestController suggestController2 = SuggestController.this;
                try {
                    Log.i("AiAiSuggestUi", "Connecting to system intelligence module.");
                    InteractionContextParcelables$InteractionContext interactionContextParcelables$InteractionContext = new InteractionContextParcelables$InteractionContext();
                    interactionContextParcelables$InteractionContext.interactionType = SuggestParcelables$InteractionType.SETUP;
                    ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = suggestController2.wrapper;
                    BundleUtils bundleUtils = suggestController2.bundleUtils;
                    String packageName = suggestController2.uiContext.getPackageName();
                    bundleUtils.getClass();
                    contentSuggestionsServiceWrapperImpl.suggestContentSelections(-1, BundleUtils.createSelectionsRequest(packageName, "", -1, -1L, interactionContextParcelables$InteractionContext, null, null), new ContentSuggestionsServiceWrapper$BundleCallback() { // from class: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.SuggestController$$ExternalSyntheticLambda2
                        @Override // com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapper$BundleCallback
                        public final void onResult(Bundle bundle) {
                            try {
                                SuggestController.this.bundleUtils.getClass();
                                SuggestParcelables$SetupInfo suggestParcelables$SetupInfo = BundleUtils.extractContents(bundle).setupInfo;
                                if (suggestParcelables$SetupInfo == null) {
                                    LogUtils.e("System intelligence is unavailable.", null);
                                } else {
                                    int i = Utils.$r8$clinit;
                                    if (suggestParcelables$SetupInfo.errorCode == SuggestParcelables$ErrorCode.ERROR_CODE_SUCCESS) {
                                        Log.i("AiAiSuggestUi", "Successfully connected to system intelligence: ");
                                    } else {
                                        LogUtils.e("Unable to connect to system intelligence: " + suggestParcelables$SetupInfo.errorMesssage, null);
                                    }
                                }
                            } catch (RuntimeException e) {
                                LogUtils.e("Unable to connect to system intelligence module.", e);
                            }
                        }
                    });
                } catch (RuntimeException e) {
                    LogUtils.e("Error while connecting to system intelligence module.", e);
                }
            }
        };
        ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = suggestController.wrapper;
        contentSuggestionsServiceWrapperImpl.asyncExecutor.execute(runnable);
        this.serviceWrapper = contentSuggestionsServiceWrapperImpl;
        boolean z = false;
        try {
            if (context.getPackageManager().getPackageInfo("com.google.android.as", 0).getLongVersionCode() >= 660780) {
                z = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e("Error obtaining package info: ", e);
        }
        this.isAiAiVersionSupported = z;
        this.bundleUtils = new BundleUtils();
        this.userManager = (UserManager) context.getSystemService(UserManager.class);
    }
}

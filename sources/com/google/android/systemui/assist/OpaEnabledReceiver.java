package com.google.android.systemui.assist;

import android.R;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.view.animation.Interpolator;
import com.android.internal.app.AssistUtils;
import com.android.systemui.util.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OpaEnabledReceiver {
    public int[] mAssistOverrideInvocationTypes;
    public final Executor mBgExecutor;
    public final Handler mBgHandler;
    public final AssistantContentObserver mContentObserver;
    public final ContentResolver mContentResolver;
    public final Context mContext;
    public final Executor mFgExecutor;
    public boolean mIsAGSAAssistant;
    public boolean mIsLongPressHomeEnabled;
    public boolean mIsOpaEligible;
    public boolean mIsOpaEnabled;
    public final OpaEnabledSettings mOpaEnabledSettings;
    public final OpaEnabledBroadcastReceiver mBroadcastReceiver = new OpaEnabledBroadcastReceiver();
    public final List mListeners = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AssistantContentObserver extends ContentObserver {
        public AssistantContentObserver(Context context) {
            super(new Handler(context.getMainLooper()));
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            OpaEnabledReceiver.this.updateOpaEnabledState(true, null);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OpaEnabledBroadcastReceiver extends BroadcastReceiver {
        public OpaEnabledBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.google.android.systemui.OPA_ENABLED")) {
                boolean booleanExtra = intent.getBooleanExtra("OPA_ENABLED", false);
                OpaEnabledSettings opaEnabledSettings = OpaEnabledReceiver.this.mOpaEnabledSettings;
                opaEnabledSettings.getClass();
                Assert.isNotMainThread();
                Settings.Secure.putIntForUser(opaEnabledSettings.mContext.getContentResolver(), "systemui.google.opa_enabled", booleanExtra ? 1 : 0, ActivityManager.getCurrentUser());
            } else if (intent.getAction().equals("com.google.android.systemui.OPA_USER_ENABLED")) {
                boolean booleanExtra2 = intent.getBooleanExtra("OPA_USER_ENABLED", false);
                OpaEnabledSettings opaEnabledSettings2 = OpaEnabledReceiver.this.mOpaEnabledSettings;
                opaEnabledSettings2.getClass();
                Assert.isNotMainThread();
                try {
                    opaEnabledSettings2.mLockSettings.setBoolean("systemui.google.opa_user_enabled", booleanExtra2, ActivityManager.getCurrentUser());
                } catch (RemoteException e) {
                    Log.e("OpaEnabledSettings", "RemoteException on OPA_USER_ENABLED", e);
                }
            }
            OpaEnabledReceiver.this.updateOpaEnabledState(true, goAsync());
        }
    }

    public OpaEnabledReceiver(Context context, Executor executor, Executor executor2, Handler handler, OpaEnabledSettings opaEnabledSettings) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mContentObserver = new AssistantContentObserver(context);
        this.mFgExecutor = executor;
        this.mBgExecutor = executor2;
        this.mOpaEnabledSettings = opaEnabledSettings;
        this.mBgHandler = handler;
        updateOpaEnabledState(false, null);
        registerContentObserver();
        registerEnabledReceiver(-2);
    }

    public final void dispatchOpaEnabledState(Context context) {
        Log.i("OpaEnabledReceiver", "Dispatching OPA eligble = " + this.mIsOpaEligible + "; AGSA = " + this.mIsAGSAAssistant + "; OPA enabled = " + this.mIsOpaEnabled);
        for (int i = 0; i < ((ArrayList) this.mListeners).size(); i++) {
            ((OpaEnabledListener) ((ArrayList) this.mListeners).get(i)).onOpaEnabledReceived(context, this.mIsOpaEligible, this.mIsAGSAAssistant, this.mIsOpaEnabled, this.mIsLongPressHomeEnabled);
        }
    }

    public BroadcastReceiver getBroadcastReceiver() {
        return this.mBroadcastReceiver;
    }

    public final void registerContentObserver() {
        ContentResolver contentResolver = this.mContentResolver;
        Uri uriFor = Settings.Secure.getUriFor("assistant");
        AssistantContentObserver assistantContentObserver = this.mContentObserver;
        contentResolver.registerContentObserver(uriFor, false, assistantContentObserver, -2);
        this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("assist_long_press_home_enabled"), false, assistantContentObserver, -2);
        this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("search_all_entrypoints_enabled"), false, assistantContentObserver, -2);
    }

    public final void registerEnabledReceiver(int i) {
        this.mContext.registerReceiverAsUser(this.mBroadcastReceiver, new UserHandle(i), new IntentFilter("com.google.android.systemui.OPA_ENABLED"), "android.permission.CAPTURE_AUDIO_HOTWORD", this.mBgHandler, 2);
        this.mContext.registerReceiverAsUser(this.mBroadcastReceiver, new UserHandle(i), new IntentFilter("com.google.android.systemui.OPA_USER_ENABLED"), "android.permission.CAPTURE_AUDIO_HOTWORD", this.mBgHandler, 2);
    }

    public final void updateOpaEnabledState(final boolean z, final BroadcastReceiver.PendingResult pendingResult) {
        this.mBgExecutor.execute(new Runnable() { // from class: com.google.android.systemui.assist.OpaEnabledReceiver$$ExternalSyntheticLambda0
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public final void run() {
                boolean z2;
                final int i = 1;
                final int i2 = 0;
                final OpaEnabledReceiver opaEnabledReceiver = OpaEnabledReceiver.this;
                boolean z3 = z;
                final BroadcastReceiver.PendingResult pendingResult2 = pendingResult;
                OpaEnabledSettings opaEnabledSettings = opaEnabledReceiver.mOpaEnabledSettings;
                opaEnabledSettings.getClass();
                Assert.isNotMainThread();
                opaEnabledReceiver.mIsOpaEligible = Settings.Secure.getIntForUser(opaEnabledSettings.mContext.getContentResolver(), "systemui.google.opa_enabled", 0, ActivityManager.getCurrentUser()) != 0;
                Assert.isNotMainThread();
                Context context = opaEnabledSettings.mContext;
                Interpolator interpolator = OpaUtils.INTERPOLATOR_40_40;
                ComponentName assistComponentForUser = new AssistUtils(context).getAssistComponentForUser(-2);
                opaEnabledReceiver.mIsAGSAAssistant = assistComponentForUser != null && "com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService".equals(assistComponentForUser.flattenToString());
                Assert.isNotMainThread();
                try {
                    z2 = opaEnabledSettings.mLockSettings.getBoolean("systemui.google.opa_user_enabled", false, ActivityManager.getCurrentUser());
                } catch (RemoteException e) {
                    Log.e("OpaEnabledSettings", "isOpaEnabled RemoteException", e);
                    z2 = false;
                }
                opaEnabledReceiver.mIsOpaEnabled = z2;
                int[] iArr = opaEnabledReceiver.mAssistOverrideInvocationTypes;
                Object[] objArr = iArr != null && Arrays.stream(iArr).anyMatch(new OpaEnabledReceiver$$ExternalSyntheticLambda3());
                Assert.isNotMainThread();
                opaEnabledReceiver.mIsLongPressHomeEnabled = Settings.Secure.getInt(opaEnabledSettings.mContext.getContentResolver(), objArr != false ? "search_all_entrypoints_enabled" : "assist_long_press_home_enabled", opaEnabledSettings.mContext.getResources().getBoolean(objArr != false ? R.bool.config_shortPressEarlyOnStemPrimary : R.bool.config_assistLongPressHomeEnabledDefault) ? 1 : 0) != 0;
                if (z3) {
                    opaEnabledReceiver.mFgExecutor.execute(new Runnable() { // from class: com.google.android.systemui.assist.OpaEnabledReceiver$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            int i3 = i2;
                            Object obj = opaEnabledReceiver;
                            switch (i3) {
                                case 0:
                                    OpaEnabledReceiver opaEnabledReceiver2 = (OpaEnabledReceiver) obj;
                                    opaEnabledReceiver2.dispatchOpaEnabledState(opaEnabledReceiver2.mContext);
                                    break;
                                default:
                                    ((BroadcastReceiver.PendingResult) obj).finish();
                                    break;
                            }
                        }
                    });
                }
                if (pendingResult2 != null) {
                    opaEnabledReceiver.mFgExecutor.execute(new Runnable() { // from class: com.google.android.systemui.assist.OpaEnabledReceiver$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            int i3 = i;
                            Object obj = pendingResult2;
                            switch (i3) {
                                case 0:
                                    OpaEnabledReceiver opaEnabledReceiver2 = (OpaEnabledReceiver) obj;
                                    opaEnabledReceiver2.dispatchOpaEnabledState(opaEnabledReceiver2.mContext);
                                    break;
                                default:
                                    ((BroadcastReceiver.PendingResult) obj).finish();
                                    break;
                            }
                        }
                    });
                }
            }
        });
    }
}

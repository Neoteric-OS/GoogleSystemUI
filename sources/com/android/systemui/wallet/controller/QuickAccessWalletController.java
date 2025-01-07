package com.android.systemui.wallet.controller;

import android.app.role.OnRoleHoldersChangedListener;
import android.app.role.RoleManager;
import android.content.Context;
import android.database.ContentObserver;
import android.os.UserHandle;
import android.service.quickaccesswallet.GetWalletCardsRequest;
import android.service.quickaccesswallet.QuickAccessWalletClient;
import android.util.Log;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickAccessWalletController {
    public static final long RECREATION_TIME_WINDOW = TimeUnit.MINUTES.toMillis(10);
    public final Executor mBgExecutor;
    public final SystemClock mClock;
    public final Context mContext;
    public AnonymousClass1 mDefaultPaymentAppObserver;
    public QuickAccessWalletController$$ExternalSyntheticLambda0 mDefaultWalletAppObserver;
    public final Executor mExecutor;
    public long mQawClientCreatedTimeMillis;
    public QuickAccessWalletClient mQuickAccessWalletClient;
    public final RoleManager mRoleManager;
    public final SecureSettings mSecureSettings;
    public AnonymousClass2 mWalletPreferenceObserver;
    public int mWalletPreferenceChangeEvents = 0;
    public int mDefaultPaymentAppChangeEvents = 0;
    public int mDefaultWalletAppChangeEvents = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.wallet.controller.QuickAccessWalletController$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContentObserver {
        public final /* synthetic */ QuickAccessWalletClient.OnWalletCardsRetrievedCallback val$cardsRetriever;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback) {
            super(null);
            this.val$cardsRetriever = onWalletCardsRetrievedCallback;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            QuickAccessWalletController.this.mExecutor.execute(new QuickAccessWalletController$$ExternalSyntheticLambda1(this, this.val$cardsRetriever, 1));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.wallet.controller.QuickAccessWalletController$2, reason: invalid class name */
    public final class AnonymousClass2 extends ContentObserver {
        public AnonymousClass2() {
            super(null);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            QuickAccessWalletController.this.mExecutor.execute(new Runnable() { // from class: com.android.systemui.wallet.controller.QuickAccessWalletController$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    QuickAccessWalletController.this.updateWalletPreference();
                }
            });
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WalletChangeEvent {
        public static final /* synthetic */ WalletChangeEvent[] $VALUES;
        public static final WalletChangeEvent DEFAULT_PAYMENT_APP_CHANGE;
        public static final WalletChangeEvent DEFAULT_WALLET_APP_CHANGE;
        public static final WalletChangeEvent WALLET_PREFERENCE_CHANGE;

        static {
            WalletChangeEvent walletChangeEvent = new WalletChangeEvent("DEFAULT_PAYMENT_APP_CHANGE", 0);
            DEFAULT_PAYMENT_APP_CHANGE = walletChangeEvent;
            WalletChangeEvent walletChangeEvent2 = new WalletChangeEvent("DEFAULT_WALLET_APP_CHANGE", 1);
            DEFAULT_WALLET_APP_CHANGE = walletChangeEvent2;
            WalletChangeEvent walletChangeEvent3 = new WalletChangeEvent("WALLET_PREFERENCE_CHANGE", 2);
            WALLET_PREFERENCE_CHANGE = walletChangeEvent3;
            $VALUES = new WalletChangeEvent[]{walletChangeEvent, walletChangeEvent2, walletChangeEvent3};
        }

        public static WalletChangeEvent valueOf(String str) {
            return (WalletChangeEvent) Enum.valueOf(WalletChangeEvent.class, str);
        }

        public static WalletChangeEvent[] values() {
            return (WalletChangeEvent[]) $VALUES.clone();
        }
    }

    public QuickAccessWalletController(Context context, Executor executor, Executor executor2, SecureSettings secureSettings, QuickAccessWalletClient quickAccessWalletClient, SystemClock systemClock, RoleManager roleManager) {
        this.mContext = context;
        this.mExecutor = executor;
        this.mBgExecutor = executor2;
        this.mSecureSettings = secureSettings;
        this.mRoleManager = roleManager;
        this.mQuickAccessWalletClient = quickAccessWalletClient;
        this.mClock = systemClock;
        ((SystemClockImpl) systemClock).getClass();
        this.mQawClientCreatedTimeMillis = android.os.SystemClock.elapsedRealtime();
    }

    public final void queryWalletCards(QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback, int i) {
        ((SystemClockImpl) this.mClock).getClass();
        if (android.os.SystemClock.elapsedRealtime() - this.mQawClientCreatedTimeMillis > RECREATION_TIME_WINDOW) {
            Log.i("QAWController", "Re-creating the QAW client to avoid stale.");
            reCreateWalletClient();
        }
        if (!this.mQuickAccessWalletClient.isWalletFeatureAvailable()) {
            Log.d("QAWController", "QuickAccessWallet feature is not available.");
        } else {
            this.mQuickAccessWalletClient.getWalletCards(this.mBgExecutor, new GetWalletCardsRequest(this.mContext.getResources().getDimensionPixelSize(R.dimen.wallet_tile_card_view_width), this.mContext.getResources().getDimensionPixelSize(R.dimen.wallet_tile_card_view_height), this.mContext.getResources().getDimensionPixelSize(R.dimen.wallet_icon_size), i), onWalletCardsRetrievedCallback);
        }
    }

    public final void reCreateWalletClient() {
        this.mQuickAccessWalletClient = QuickAccessWalletClient.create(this.mContext, this.mBgExecutor);
        ((SystemClockImpl) this.mClock).getClass();
        this.mQawClientCreatedTimeMillis = android.os.SystemClock.elapsedRealtime();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [android.app.role.OnRoleHoldersChangedListener, com.android.systemui.wallet.controller.QuickAccessWalletController$$ExternalSyntheticLambda0] */
    public final void setupWalletChangeObservers(final QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback, WalletChangeEvent... walletChangeEventArr) {
        for (WalletChangeEvent walletChangeEvent : walletChangeEventArr) {
            WalletChangeEvent walletChangeEvent2 = WalletChangeEvent.WALLET_PREFERENCE_CHANGE;
            SecureSettings secureSettings = this.mSecureSettings;
            if (walletChangeEvent == walletChangeEvent2) {
                if (this.mWalletPreferenceObserver == null) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2();
                    this.mWalletPreferenceObserver = anonymousClass2;
                    secureSettings.registerContentObserverForUserSync("lockscreen_show_wallet", false, (ContentObserver) anonymousClass2, -1);
                }
                this.mWalletPreferenceChangeEvents++;
            } else if (walletChangeEvent == WalletChangeEvent.DEFAULT_PAYMENT_APP_CHANGE) {
                if (this.mDefaultPaymentAppObserver == null) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(onWalletCardsRetrievedCallback);
                    this.mDefaultPaymentAppObserver = anonymousClass1;
                    secureSettings.registerContentObserverForUserSync("nfc_payment_default_component", false, (ContentObserver) anonymousClass1, -1);
                }
                this.mDefaultPaymentAppChangeEvents++;
            } else if (walletChangeEvent == WalletChangeEvent.DEFAULT_WALLET_APP_CHANGE) {
                if (this.mDefaultWalletAppObserver == null) {
                    ?? r3 = new OnRoleHoldersChangedListener() { // from class: com.android.systemui.wallet.controller.QuickAccessWalletController$$ExternalSyntheticLambda0
                        public final void onRoleHoldersChanged(String str, UserHandle userHandle) {
                            QuickAccessWalletController quickAccessWalletController = QuickAccessWalletController.this;
                            QuickAccessWalletClient.OnWalletCardsRetrievedCallback onWalletCardsRetrievedCallback2 = onWalletCardsRetrievedCallback;
                            quickAccessWalletController.getClass();
                            if (str.equals("android.app.role.WALLET")) {
                                quickAccessWalletController.mExecutor.execute(new QuickAccessWalletController$$ExternalSyntheticLambda1(quickAccessWalletController, onWalletCardsRetrievedCallback2, 0));
                            }
                        }
                    };
                    this.mDefaultWalletAppObserver = r3;
                    this.mRoleManager.addOnRoleHoldersChangedListenerAsUser(this.mExecutor, r3, UserHandle.ALL);
                }
                this.mDefaultWalletAppChangeEvents++;
            }
        }
    }

    public final void unregisterWalletChangeObservers(WalletChangeEvent... walletChangeEventArr) {
        QuickAccessWalletController$$ExternalSyntheticLambda0 quickAccessWalletController$$ExternalSyntheticLambda0;
        AnonymousClass1 anonymousClass1;
        AnonymousClass2 anonymousClass2;
        for (WalletChangeEvent walletChangeEvent : walletChangeEventArr) {
            WalletChangeEvent walletChangeEvent2 = WalletChangeEvent.WALLET_PREFERENCE_CHANGE;
            SecureSettings secureSettings = this.mSecureSettings;
            if (walletChangeEvent == walletChangeEvent2 && (anonymousClass2 = this.mWalletPreferenceObserver) != null) {
                int i = this.mWalletPreferenceChangeEvents - 1;
                this.mWalletPreferenceChangeEvents = i;
                if (i == 0) {
                    secureSettings.unregisterContentObserverSync(anonymousClass2);
                }
            } else if (walletChangeEvent == WalletChangeEvent.DEFAULT_PAYMENT_APP_CHANGE && (anonymousClass1 = this.mDefaultPaymentAppObserver) != null) {
                int i2 = this.mDefaultPaymentAppChangeEvents - 1;
                this.mDefaultPaymentAppChangeEvents = i2;
                if (i2 == 0) {
                    secureSettings.unregisterContentObserverSync(anonymousClass1);
                }
            } else if (walletChangeEvent == WalletChangeEvent.DEFAULT_WALLET_APP_CHANGE && (quickAccessWalletController$$ExternalSyntheticLambda0 = this.mDefaultWalletAppObserver) != null) {
                int i3 = this.mDefaultWalletAppChangeEvents - 1;
                this.mDefaultWalletAppChangeEvents = i3;
                if (i3 == 0) {
                    this.mRoleManager.removeOnRoleHoldersChangedListenerAsUser(quickAccessWalletController$$ExternalSyntheticLambda0, UserHandle.ALL);
                }
            }
        }
    }

    public final void updateWalletPreference() {
        if (this.mQuickAccessWalletClient.isWalletServiceAvailable() && this.mQuickAccessWalletClient.isWalletFeatureAvailable()) {
            this.mQuickAccessWalletClient.isWalletFeatureAvailableWhenDeviceLocked();
        }
    }
}

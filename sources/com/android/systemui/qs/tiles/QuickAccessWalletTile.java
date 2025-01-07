package com.android.systemui.qs.tiles;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.service.quickaccesswallet.GetWalletCardsError;
import android.service.quickaccesswallet.GetWalletCardsResponse;
import android.service.quickaccesswallet.QuickAccessWalletClient;
import android.service.quickaccesswallet.WalletCard;
import android.util.Log;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import com.android.systemui.wallet.controller.QuickAccessWalletController;
import com.android.systemui.wallet.controller.QuickAccessWalletController$$ExternalSyntheticLambda2;
import com.android.systemui.wallet.util.WalletCardUtilsKt;
import com.android.wm.shell.R;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QuickAccessWalletTile extends QSTileImpl {
    public final WalletCardRetriever mCardRetriever;
    Drawable mCardViewDrawable;
    public final QuickAccessWalletController mController;
    public boolean mIsWalletUpdating;
    public final KeyguardStateController mKeyguardStateController;
    public final CharSequence mLabel;
    public final PackageManager mPackageManager;
    public final SecureSettings mSecureSettings;
    public WalletCard mSelectedCard;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WalletCardRetriever implements QuickAccessWalletClient.OnWalletCardsRetrievedCallback {
        public WalletCardRetriever() {
        }

        public final void onWalletCardRetrievalError(GetWalletCardsError getWalletCardsError) {
            QuickAccessWalletTile quickAccessWalletTile = QuickAccessWalletTile.this;
            quickAccessWalletTile.mIsWalletUpdating = false;
            quickAccessWalletTile.mCardViewDrawable = null;
            quickAccessWalletTile.mSelectedCard = null;
            quickAccessWalletTile.refreshState(null);
        }

        public final void onWalletCardsRetrieved(GetWalletCardsResponse getWalletCardsResponse) {
            Log.i("QuickAccessWalletTile", "Successfully retrieved wallet cards.");
            QuickAccessWalletTile.this.mIsWalletUpdating = false;
            List paymentCards = WalletCardUtilsKt.getPaymentCards(getWalletCardsResponse.getWalletCards());
            if (paymentCards.isEmpty()) {
                Log.d("QuickAccessWalletTile", "No wallet cards exist.");
                QuickAccessWalletTile quickAccessWalletTile = QuickAccessWalletTile.this;
                quickAccessWalletTile.mCardViewDrawable = null;
                quickAccessWalletTile.mSelectedCard = null;
                quickAccessWalletTile.refreshState(null);
                return;
            }
            int selectedIndex = getWalletCardsResponse.getSelectedIndex();
            if (selectedIndex >= paymentCards.size()) {
                Log.w("QuickAccessWalletTile", "Error retrieving cards: Invalid selected card index.");
                QuickAccessWalletTile quickAccessWalletTile2 = QuickAccessWalletTile.this;
                quickAccessWalletTile2.mSelectedCard = null;
                quickAccessWalletTile2.mCardViewDrawable = null;
                return;
            }
            QuickAccessWalletTile.this.mSelectedCard = (WalletCard) paymentCards.get(selectedIndex);
            switch (QuickAccessWalletTile.this.mSelectedCard.getCardImage().getType()) {
                case 1:
                case 2:
                case 3:
                case 5:
                    QuickAccessWalletTile quickAccessWalletTile3 = QuickAccessWalletTile.this;
                    quickAccessWalletTile3.mCardViewDrawable = quickAccessWalletTile3.mSelectedCard.getCardImage().loadDrawable(QuickAccessWalletTile.this.mContext);
                    break;
                case 4:
                case 6:
                    QuickAccessWalletTile.this.mCardViewDrawable = null;
                    break;
                default:
                    Log.e("QuickAccessWalletTile", "Unknown icon type: " + QuickAccessWalletTile.this.mSelectedCard.getCardImage().getType());
                    QuickAccessWalletTile.this.mCardViewDrawable = null;
                    break;
            }
            QuickAccessWalletTile.this.refreshState(null);
        }
    }

    public QuickAccessWalletTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, KeyguardStateController keyguardStateController, PackageManager packageManager, SecureSettings secureSettings, QuickAccessWalletController quickAccessWalletController) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mLabel = this.mContext.getString(R.string.wallet_title);
        this.mCardRetriever = new WalletCardRetriever();
        this.mIsWalletUpdating = true;
        this.mController = quickAccessWalletController;
        this.mKeyguardStateController = keyguardStateController;
        this.mPackageManager = packageManager;
        this.mSecureSettings = secureSettings;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return null;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final int getMetricsCategory() {
        return 0;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        CharSequence serviceLabel = this.mController.mQuickAccessWalletClient.getServiceLabel();
        return serviceLabel == null ? this.mLabel : serviceLabel;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(Expandable expandable) {
        final ActivityTransitionAnimator.Controller activityTransitionController = expandable == null ? null : expandable.activityTransitionController(32);
        this.mUiHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.QuickAccessWalletTile$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                QuickAccessWalletTile quickAccessWalletTile = QuickAccessWalletTile.this;
                ActivityTransitionAnimator.Controller controller = activityTransitionController;
                boolean z = quickAccessWalletTile.mSelectedCard != null;
                QuickAccessWalletController quickAccessWalletController = quickAccessWalletTile.mController;
                quickAccessWalletController.mQuickAccessWalletClient.getWalletPendingIntent(quickAccessWalletController.mExecutor, new QuickAccessWalletController$$ExternalSyntheticLambda2(quickAccessWalletController, quickAccessWalletTile.mActivityStarter, controller, z));
            }
        });
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleDestroy() {
        super.handleDestroy();
        this.mController.unregisterWalletChangeObservers(QuickAccessWalletController.WalletChangeEvent.DEFAULT_PAYMENT_APP_CHANGE, QuickAccessWalletController.WalletChangeEvent.DEFAULT_WALLET_APP_CHANGE);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleSetListening(boolean z) {
        super.handleSetListening(z);
        if (z) {
            QuickAccessWalletController.WalletChangeEvent[] walletChangeEventArr = {QuickAccessWalletController.WalletChangeEvent.DEFAULT_PAYMENT_APP_CHANGE, QuickAccessWalletController.WalletChangeEvent.DEFAULT_WALLET_APP_CHANGE};
            QuickAccessWalletController quickAccessWalletController = this.mController;
            WalletCardRetriever walletCardRetriever = this.mCardRetriever;
            quickAccessWalletController.setupWalletChangeObservers(walletCardRetriever, walletChangeEventArr);
            if (!quickAccessWalletController.mQuickAccessWalletClient.isWalletServiceAvailable() || !quickAccessWalletController.mQuickAccessWalletClient.isWalletFeatureAvailable()) {
                Log.i("QuickAccessWalletTile", "QAW service is unavailable, recreating the wallet client.");
                quickAccessWalletController.reCreateWalletClient();
            }
            quickAccessWalletController.queryWalletCards(walletCardRetriever, 1);
        }
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        QuickAccessWalletController quickAccessWalletController = this.mController;
        CharSequence serviceLabel = quickAccessWalletController.mQuickAccessWalletClient.getServiceLabel();
        if (serviceLabel == null) {
            serviceLabel = this.mLabel;
        }
        state.label = serviceLabel;
        state.contentDescription = serviceLabel;
        Drawable tileIcon = quickAccessWalletController.mQuickAccessWalletClient.getTileIcon();
        state.icon = tileIcon == null ? QSTileImpl.ResourceIcon.get(R.drawable.ic_wallet_lockscreen) : new QSTileImpl.DrawableIcon(tileIcon);
        boolean isUnlocked = this.mKeyguardStateController.isUnlocked();
        if (!quickAccessWalletController.mQuickAccessWalletClient.isWalletServiceAvailable() || !quickAccessWalletController.mQuickAccessWalletClient.isWalletFeatureAvailable()) {
            state.state = 0;
            state.secondaryLabel = null;
            state.sideViewCustomDrawable = null;
            return;
        }
        WalletCard walletCard = this.mSelectedCard;
        if (walletCard != null) {
            state.state = isUnlocked ? 2 : 1;
            state.secondaryLabel = walletCard.getContentDescription();
            state.sideViewCustomDrawable = this.mCardViewDrawable;
        } else {
            state.state = 1;
            state.secondaryLabel = this.mContext.getString(this.mIsWalletUpdating ? R.string.wallet_secondary_label_updating : R.string.wallet_secondary_label_no_card);
            state.sideViewCustomDrawable = null;
        }
        state.stateDescription = state.secondaryLabel;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final boolean isAvailable() {
        return (((QSHostAdapter) this.mHost).getUserId() == 0 && this.mController.mRoleManager.isRoleAvailable("android.app.role.WALLET")) ? !this.mPackageManager.hasSystemFeature("org.chromium.arc") : (!this.mPackageManager.hasSystemFeature("android.hardware.nfc.hce") || this.mPackageManager.hasSystemFeature("org.chromium.arc") || ((SecureSettingsImpl) this.mSecureSettings).getStringForUser(-2, "nfc_payment_default_component") == null) ? false : true;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        QSTile.State state = new QSTile.State();
        state.handlesLongClick = false;
        return state;
    }
}

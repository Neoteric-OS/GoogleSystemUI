package com.android.systemui.wallet.ui;

import android.R;
import android.app.BroadcastOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.wallet.ui.WalletScreenController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class WalletView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Button mActionButton;
    public final float mAnimationTranslationX;
    public final Button mAppButton;
    public final WalletCardCarousel mCardCarousel;
    public final ViewGroup mCardCarouselContainer;
    public final TextView mCardLabel;
    public WalletActivity$$ExternalSyntheticLambda0 mDeviceLockedActionOnClickListener;
    public final ViewGroup mEmptyStateView;
    public final TextView mErrorView;
    public FalsingCollector mFalsingCollector;
    public final ImageView mIcon;
    public boolean mIsDeviceLocked;
    public boolean mIsUdfpsEnabled;
    public final Interpolator mOutInterpolator;
    public WalletActivity$$ExternalSyntheticLambda0 mShowWalletAppOnClickListener;
    public final Button mToolbarAppButton;

    public WalletView(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        FalsingCollector falsingCollector = this.mFalsingCollector;
        if (falsingCollector != null) {
            falsingCollector.onTouchEvent(motionEvent);
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        FalsingCollector falsingCollector2 = this.mFalsingCollector;
        if (falsingCollector2 != null) {
            falsingCollector2.onMotionEventComplete();
        }
        return dispatchTouchEvent;
    }

    public Button getAppButton() {
        return this.mAppButton;
    }

    public ViewGroup getCardCarouselContainer() {
        return this.mCardCarouselContainer;
    }

    public TextView getCardLabel() {
        return this.mCardLabel;
    }

    public ViewGroup getEmptyStateView() {
        return this.mEmptyStateView;
    }

    public TextView getErrorView() {
        return this.mErrorView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.CharSequence] */
    public final void onCardScroll(WalletCardViewInfo walletCardViewInfo, WalletCardViewInfo walletCardViewInfo2, float f) {
        String str;
        WalletScreenController.QAWalletCardViewInfo qAWalletCardViewInfo = (WalletScreenController.QAWalletCardViewInfo) walletCardViewInfo;
        CharSequence cardLabel = qAWalletCardViewInfo.mWalletCard.getCardLabel();
        if (cardLabel == null) {
            cardLabel = "";
        }
        String[] split = cardLabel.toString().split("\\n");
        if (split.length == 2) {
            str = split[0];
        } else {
            ?? cardLabel2 = qAWalletCardViewInfo.mWalletCard.getCardLabel();
            str = cardLabel2 != 0 ? cardLabel2 : "";
        }
        Context context = ((FrameLayout) this).mContext;
        Drawable drawable = qAWalletCardViewInfo.mIconDrawable;
        if (drawable != null) {
            drawable.setTint(Utils.getColorAttrDefaultColor(R.^attr-private.colorAccentPrimary, 0, context));
        }
        renderActionButton(walletCardViewInfo, this.mIsDeviceLocked, this.mIsUdfpsEnabled);
        walletCardViewInfo.getClass();
        if (walletCardViewInfo2 != null ? ((WalletScreenController.QAWalletCardViewInfo) walletCardViewInfo).mWalletCard.getCardId().equals(((WalletScreenController.QAWalletCardViewInfo) walletCardViewInfo2).mWalletCard.getCardId()) : false) {
            this.mCardLabel.setAlpha(1.0f);
            this.mIcon.setAlpha(1.0f);
            this.mActionButton.setAlpha(1.0f);
        } else {
            this.mCardLabel.setText(str);
            this.mIcon.setImageDrawable(drawable);
            this.mCardLabel.setAlpha(f);
            this.mIcon.setAlpha(f);
            this.mActionButton.setAlpha(f);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        WalletCardCarousel walletCardCarousel = this.mCardCarousel;
        int width = getWidth();
        if (walletCardCarousel.mExpectedViewWidth == width) {
            return;
        }
        walletCardCarousel.mExpectedViewWidth = width;
        Resources resources = walletCardCarousel.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int round = Math.round(Math.min(width, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels)) * 0.69f);
        walletCardCarousel.mCardWidthPx = round;
        walletCardCarousel.mCardHeightPx = Math.round(round / 1.5909091f);
        float f = walletCardCarousel.mCardWidthPx;
        walletCardCarousel.mCornerRadiusPx = 0.035714287f * f;
        walletCardCarousel.mCardMarginPx = Math.round(f * (-0.03f));
        int dimensionPixelSize = (resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.card_margin) * 2) + walletCardCarousel.mCardWidthPx;
        walletCardCarousel.mTotalCardWidth = dimensionPixelSize;
        walletCardCarousel.mCardEdgeToCenterDistance = dimensionPixelSize / 2.0f;
        walletCardCarousel.updatePadding(width);
        WalletScreenController walletScreenController = walletCardCarousel.mSelectionListener;
        if (walletScreenController != null) {
            walletScreenController.queryWalletCards();
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mCardCarousel.onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent);
    }

    public final void renderActionButton(final WalletCardViewInfo walletCardViewInfo, boolean z, boolean z2) {
        CharSequence cardLabel = ((WalletScreenController.QAWalletCardViewInfo) walletCardViewInfo).mWalletCard.getCardLabel();
        if (cardLabel == null) {
            cardLabel = "";
        }
        String[] split = cardLabel.toString().split("\\n");
        String str = split.length == 2 ? split[1] : null;
        if (z2 || str == null) {
            this.mActionButton.setVisibility(8);
            return;
        }
        this.mActionButton.setVisibility(0);
        this.mActionButton.setText(str);
        this.mActionButton.setOnClickListener(z ? this.mDeviceLockedActionOnClickListener : new View.OnClickListener() { // from class: com.android.systemui.wallet.ui.WalletView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WalletCardViewInfo walletCardViewInfo2 = WalletCardViewInfo.this;
                int i = WalletView.$r8$clinit;
                try {
                    BroadcastOptions makeBasic = BroadcastOptions.makeBasic();
                    makeBasic.setInteractive(true);
                    makeBasic.setPendingIntentBackgroundActivityStartMode(1);
                    ((WalletScreenController.QAWalletCardViewInfo) walletCardViewInfo2).mWalletCard.getPendingIntent().send(makeBasic.toBundle());
                } catch (PendingIntent.CanceledException unused) {
                    Log.w("WalletView", "Error sending pending intent for wallet card.");
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x017b A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void showCardCarousel(java.util.List r11, int r12, boolean r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.wallet.ui.WalletView.showCardCarousel(java.util.List, int, boolean, boolean):void");
    }

    public final void showEmptyStateView(Drawable drawable, CharSequence charSequence, CharSequence charSequence2, WalletScreenController$$ExternalSyntheticLambda4 walletScreenController$$ExternalSyntheticLambda4) {
        this.mEmptyStateView.setVisibility(0);
        this.mErrorView.setVisibility(8);
        this.mCardCarousel.setVisibility(8);
        this.mIcon.setImageDrawable(drawable);
        this.mIcon.setContentDescription(charSequence);
        this.mCardLabel.setText(com.android.wm.shell.R.string.wallet_empty_state_label);
        ((ImageView) this.mEmptyStateView.requireViewById(com.android.wm.shell.R.id.empty_state_icon)).setImageDrawable(((FrameLayout) this).mContext.getDrawable(com.android.wm.shell.R.drawable.ic_qs_plus));
        ((TextView) this.mEmptyStateView.requireViewById(com.android.wm.shell.R.id.empty_state_title)).setText(charSequence2);
        this.mEmptyStateView.setOnClickListener(walletScreenController$$ExternalSyntheticLambda4);
        this.mAppButton.setOnClickListener(walletScreenController$$ExternalSyntheticLambda4);
    }

    public WalletView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsDeviceLocked = false;
        this.mIsUdfpsEnabled = false;
        FrameLayout.inflate(context, com.android.wm.shell.R.layout.wallet_fullscreen, this);
        this.mCardCarouselContainer = (ViewGroup) requireViewById(com.android.wm.shell.R.id.card_carousel_container);
        WalletCardCarousel walletCardCarousel = (WalletCardCarousel) requireViewById(com.android.wm.shell.R.id.card_carousel);
        this.mCardCarousel = walletCardCarousel;
        walletCardCarousel.mCardScrollListener = this;
        this.mIcon = (ImageView) requireViewById(com.android.wm.shell.R.id.icon);
        this.mCardLabel = (TextView) requireViewById(com.android.wm.shell.R.id.label);
        this.mAppButton = (Button) requireViewById(com.android.wm.shell.R.id.wallet_app_button);
        this.mToolbarAppButton = (Button) requireViewById(com.android.wm.shell.R.id.wallet_toolbar_app_button);
        this.mActionButton = (Button) requireViewById(com.android.wm.shell.R.id.wallet_action_button);
        this.mErrorView = (TextView) requireViewById(com.android.wm.shell.R.id.error_view);
        this.mEmptyStateView = (ViewGroup) requireViewById(com.android.wm.shell.R.id.wallet_empty_state);
        this.mOutInterpolator = AnimationUtils.loadInterpolator(context, R.interpolator.accelerate_cubic);
        this.mAnimationTranslationX = walletCardCarousel.mCardWidthPx / 4.0f;
    }
}

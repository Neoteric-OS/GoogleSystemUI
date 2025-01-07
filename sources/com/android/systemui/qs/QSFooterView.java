package com.android.systemui.qs;

import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.UserManager;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.qs.TouchAnimator;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class QSFooterView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public TextView mBuildText;
    public final AnonymousClass1 mDeveloperSettingsObserver;
    public View mEditButton;
    public boolean mExpanded;
    public float mExpansionAmount;
    public TouchAnimator mFooterAnimator;
    public PageIndicator mPageIndicator;
    public boolean mQsDisabled;
    public boolean mShouldShowBuildText;

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.systemui.qs.QSFooterView$1] */
    public QSFooterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDeveloperSettingsObserver = new ContentObserver(new Handler(((FrameLayout) this).mContext.getMainLooper())) { // from class: com.android.systemui.qs.QSFooterView.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                super.onChange(z, uri);
                QSFooterView qSFooterView = QSFooterView.this;
                int i = QSFooterView.$r8$clinit;
                qSFooterView.setBuildText();
            }
        };
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((FrameLayout) this).mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("development_settings_enabled"), false, this.mDeveloperSettingsObserver, -1);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateResources();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ((FrameLayout) this).mContext.getContentResolver().unregisterContentObserver(this.mDeveloperSettingsObserver);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mPageIndicator = (PageIndicator) findViewById(R.id.footer_page_indicator);
        this.mBuildText = (TextView) findViewById(R.id.build);
        this.mEditButton = findViewById(android.R.id.edit);
        updateResources();
        setImportantForAccessibility(1);
        setBuildText();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setBuildText() {
        if (this.mBuildText == null) {
            return;
        }
        Context context = ((FrameLayout) this).mContext;
        UserManager userManager = (UserManager) context.getSystemService("user");
        Object[] objArr = Settings.Global.getInt(context.getContentResolver(), "development_settings_enabled", Build.TYPE.equals("eng") ? 1 : 0) != 0;
        boolean hasUserRestriction = userManager.hasUserRestriction("no_debugging_features");
        if (userManager.isAdminUser() && !hasUserRestriction && objArr == true) {
            this.mBuildText.setText(((FrameLayout) this).mContext.getString(android.R.string.call_notification_decline_action, Build.VERSION.RELEASE_OR_CODENAME, Build.ID));
            this.mBuildText.setSelected(true);
            this.mShouldShowBuildText = true;
        } else {
            this.mBuildText.setText((CharSequence) null);
            this.mShouldShowBuildText = false;
            this.mBuildText.setSelected(false);
        }
    }

    public final void updateResources() {
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        builder.addFloat(this.mPageIndicator, "alpha", 0.0f, 1.0f);
        builder.addFloat(this.mBuildText, "alpha", 0.0f, 1.0f);
        builder.addFloat(this.mEditButton, "alpha", 0.0f, 1.0f);
        builder.mStartDelay = 0.9f;
        this.mFooterAnimator = builder.build();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.qs_footer_action_button_size);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.qs_footer_icon_padding);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mEditButton.getLayoutParams();
        marginLayoutParams.height = dimensionPixelSize;
        marginLayoutParams.width = dimensionPixelSize;
        this.mEditButton.setLayoutParams(marginLayoutParams);
        this.mEditButton.setPadding(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
        FontSizeUtils.updateFontSizeFromStyle(this.mBuildText, R.style.TextAppearance_QS_Status_Build);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) getLayoutParams();
        marginLayoutParams2.height = getResources().getDimensionPixelSize(R.dimen.qs_footer_height);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.qs_footer_margin);
        marginLayoutParams2.leftMargin = dimensionPixelSize3;
        marginLayoutParams2.rightMargin = dimensionPixelSize3;
        marginLayoutParams2.bottomMargin = getResources().getDimensionPixelSize(R.dimen.qs_footers_margin_bottom);
        setLayoutParams(marginLayoutParams2);
    }
}

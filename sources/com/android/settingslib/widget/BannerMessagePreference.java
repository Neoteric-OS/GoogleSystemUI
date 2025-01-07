package com.android.settingslib.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.widget.preference.banner.R$styleable;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class BannerMessagePreference extends Preference {
    public final AttentionLevel mAttentionLevel;
    public final DismissButtonInfo mDismissButtonInfo;
    public final ButtonInfo mNegativeButtonInfo;
    public final ButtonInfo mPositiveButtonInfo;
    public final String mSubtitle;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum AttentionLevel {
        HIGH(R.color.banner_background_attention_high, R.color.banner_accent_attention_high, "HIGH"),
        /* JADX INFO: Fake field, exist only in values array */
        EF31(R.color.banner_background_attention_medium, R.color.banner_accent_attention_medium, "MEDIUM"),
        /* JADX INFO: Fake field, exist only in values array */
        EF47(R.color.banner_background_attention_low, R.color.banner_accent_attention_low, "LOW");

        private final int mAccentColorResId;
        private final int mAttrValue;
        private final int mBackgroundColorResId;

        AttentionLevel(int i, int i2, String str) {
            this.mAttrValue = r2;
            this.mBackgroundColorResId = i;
            this.mAccentColorResId = i2;
        }

        public static AttentionLevel fromAttr(int i) {
            for (AttentionLevel attentionLevel : values()) {
                if (attentionLevel.mAttrValue == i) {
                    return attentionLevel;
                }
            }
            throw new IllegalArgumentException();
        }

        public final int getAccentColorResId() {
            return this.mAccentColorResId;
        }

        public final int getBackgroundColorResId() {
            return this.mBackgroundColorResId;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ButtonInfo {
        public Button mButton;
        public int mColor;

        public final void setUpButton() {
            this.mButton.setText((CharSequence) null);
            this.mButton.setOnClickListener(null);
            this.mButton.setTextColor(this.mColor);
            if (TextUtils.isEmpty(null)) {
                this.mButton.setVisibility(8);
            } else {
                this.mButton.setVisibility(0);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DismissButtonInfo {
        public ImageButton mButton;
    }

    public BannerMessagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPositiveButtonInfo = new ButtonInfo();
        this.mNegativeButtonInfo = new ButtonInfo();
        this.mDismissButtonInfo = new DismissButtonInfo();
        this.mAttentionLevel = AttentionLevel.HIGH;
        setSelectable(false);
        this.mLayoutResId = R.layout.settingslib_banner_message;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BannerMessagePreference);
            this.mAttentionLevel = AttentionLevel.fromAttr(obtainStyledAttributes.getInt(0, 0));
            this.mSubtitle = obtainStyledAttributes.getString(1);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        Context context = this.mContext;
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.banner_title);
        CharSequence charSequence = this.mTitle;
        textView.setText(charSequence);
        textView.setVisibility(charSequence == null ? 8 : 0);
        ((TextView) preferenceViewHolder.findViewById(R.id.banner_summary)).setText(getSummary());
        this.mPositiveButtonInfo.mButton = (Button) preferenceViewHolder.findViewById(R.id.banner_positive_btn);
        this.mNegativeButtonInfo.mButton = (Button) preferenceViewHolder.findViewById(R.id.banner_negative_btn);
        Resources.Theme theme = context.getTheme();
        int color = context.getResources().getColor(this.mAttentionLevel.getAccentColorResId(), theme);
        ImageView imageView = (ImageView) preferenceViewHolder.findViewById(R.id.banner_icon);
        if (imageView != null) {
            Drawable icon = getIcon();
            if (icon == null) {
                icon = this.mContext.getDrawable(R.drawable.ic_warning);
            }
            imageView.setImageDrawable(icon);
            imageView.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        }
        int color2 = context.getResources().getColor(this.mAttentionLevel.getBackgroundColorResId(), theme);
        preferenceViewHolder.mDividerAllowedAbove = false;
        preferenceViewHolder.mDividerAllowedBelow = false;
        preferenceViewHolder.itemView.getBackground().setTint(color2);
        this.mPositiveButtonInfo.mColor = color;
        this.mNegativeButtonInfo.mColor = color;
        this.mDismissButtonInfo.mButton = (ImageButton) preferenceViewHolder.findViewById(R.id.banner_dismiss_btn);
        DismissButtonInfo dismissButtonInfo = this.mDismissButtonInfo;
        dismissButtonInfo.mButton.setOnClickListener(null);
        dismissButtonInfo.mButton.setVisibility(8);
        TextView textView2 = (TextView) preferenceViewHolder.findViewById(R.id.banner_subtitle);
        textView2.setText(this.mSubtitle);
        textView2.setVisibility(this.mSubtitle != null ? 0 : 8);
        this.mPositiveButtonInfo.setUpButton();
        this.mNegativeButtonInfo.setUpButton();
    }
}

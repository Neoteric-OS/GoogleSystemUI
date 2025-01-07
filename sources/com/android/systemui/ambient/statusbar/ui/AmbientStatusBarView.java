package com.android.systemui.ambient.statusbar.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.shared.shadow.DoubleShadowIconDrawable;
import com.android.systemui.shared.shadow.DoubleShadowTextHelper$ShadowInfo;
import com.android.systemui.statusbar.AlphaOptimizedImageView;
import com.android.wm.shell.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class AmbientStatusBarView extends ConstraintLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public DoubleShadowTextHelper$ShadowInfo mAmbientShadowInfo;
    public final Context mContext;
    public int mDrawableInsetSize;
    public int mDrawableSize;
    public ViewGroup mExtraSystemStatusViewGroup;
    public DoubleShadowTextHelper$ShadowInfo mKeyShadowInfo;
    public final Map mStatusIcons;
    public ViewGroup mSystemStatusViewGroup;

    public AmbientStatusBarView(Context context) {
        this(context, null);
    }

    public final void addDoubleShadow(View view) {
        if (view instanceof AlphaOptimizedImageView) {
            AlphaOptimizedImageView alphaOptimizedImageView = (AlphaOptimizedImageView) view;
            alphaOptimizedImageView.setImageDrawable(new DoubleShadowIconDrawable(this.mKeyShadowInfo, this.mAmbientShadowInfo, alphaOptimizedImageView.getDrawable(), this.mDrawableSize, this.mDrawableInsetSize));
        }
    }

    public final View fetchStatusIconForResId(int i) {
        View findViewById = findViewById(i);
        Objects.requireNonNull(findViewById);
        return findViewById;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mKeyShadowInfo = new DoubleShadowTextHelper$ShadowInfo(this.mContext.getResources().getDimension(R.dimen.dream_overlay_status_bar_key_text_shadow_radius), this.mContext.getResources().getDimension(R.dimen.dream_overlay_status_bar_key_text_shadow_dx), this.mContext.getResources().getDimension(R.dimen.dream_overlay_status_bar_key_text_shadow_dy), 0.35f);
        this.mAmbientShadowInfo = new DoubleShadowTextHelper$ShadowInfo(this.mContext.getResources().getDimension(R.dimen.dream_overlay_status_bar_ambient_text_shadow_radius), this.mContext.getResources().getDimension(R.dimen.dream_overlay_status_bar_ambient_text_shadow_dx), this.mContext.getResources().getDimension(R.dimen.dream_overlay_status_bar_ambient_text_shadow_dy), 0.4f);
        this.mDrawableSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.dream_overlay_status_bar_icon_size);
        this.mDrawableInsetSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.dream_overlay_icon_inset_dimen);
        Map map = this.mStatusIcons;
        View fetchStatusIconForResId = fetchStatusIconForResId(R.id.dream_overlay_wifi_status);
        addDoubleShadow(fetchStatusIconForResId);
        map.put(1, fetchStatusIconForResId);
        Map map2 = this.mStatusIcons;
        View fetchStatusIconForResId2 = fetchStatusIconForResId(R.id.dream_overlay_alarm_set);
        addDoubleShadow(fetchStatusIconForResId2);
        map2.put(2, fetchStatusIconForResId2);
        this.mStatusIcons.put(3, fetchStatusIconForResId(R.id.dream_overlay_camera_off));
        this.mStatusIcons.put(4, fetchStatusIconForResId(R.id.dream_overlay_mic_off));
        this.mStatusIcons.put(5, fetchStatusIconForResId(R.id.dream_overlay_camera_mic_off));
        this.mStatusIcons.put(0, fetchStatusIconForResId(R.id.dream_overlay_notification_indicator));
        Map map3 = this.mStatusIcons;
        View fetchStatusIconForResId3 = fetchStatusIconForResId(R.id.dream_overlay_priority_mode);
        addDoubleShadow(fetchStatusIconForResId3);
        map3.put(6, fetchStatusIconForResId3);
        this.mStatusIcons.put(7, fetchStatusIconForResId(R.id.dream_overlay_assistant_attention_indicator));
        this.mStatusIcons.put(8, fetchStatusIconForResId(R.id.dream_overlay_location_active));
        this.mSystemStatusViewGroup = (ViewGroup) findViewById(R.id.dream_overlay_system_status);
        this.mExtraSystemStatusViewGroup = (ViewGroup) findViewById(R.id.dream_overlay_extra_items);
    }

    public AmbientStatusBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AmbientStatusBarView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
        this.mContext = context;
    }

    public AmbientStatusBarView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mStatusIcons = new HashMap();
    }
}

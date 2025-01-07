package com.android.systemui.media.controls.ui.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.internal.widget.CachingIconView;
import com.android.systemui.surfaceeffects.loadingeffect.LoadingEffectView;
import com.android.systemui.surfaceeffects.ripple.MultiRippleView;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseView;
import com.android.systemui.util.animation.TransitionLayout;
import com.android.wm.shell.R;
import java.util.Set;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaViewHolder {
    public final ImageButton action0;
    public final ImageButton action1;
    public final ImageButton action2;
    public final ImageButton action3;
    public final ImageButton action4;
    public final ImageButton actionNext;
    public final ImageButton actionPlayPause;
    public final ImageButton actionPrev;
    public final ImageView albumView;
    public final ImageView appIcon;
    public final TextView artistText;
    public final CachingIconView explicitIndicator;
    public final GutsViewHolder gutsViewHolder;
    public final LoadingEffectView loadingEffectView;
    public final MultiRippleView multiRippleView;
    public final TransitionLayout player;
    public final TextView scrubbingElapsedTimeView;
    public final TextView scrubbingTotalTimeView;
    public final ViewGroup seamless;
    public final View seamlessButton;
    public final ImageView seamlessIcon;
    public final TextView seamlessText;
    public final SeekBar seekBar;
    public final TextView titleText;
    public final TurbulenceNoiseView turbulenceNoiseView;
    public static final Set controlsIds = SetsKt.setOf(Integer.valueOf(R.id.icon), Integer.valueOf(R.id.app_name), Integer.valueOf(R.id.header_title), Integer.valueOf(R.id.header_artist), Integer.valueOf(R.id.media_explicit_indicator), Integer.valueOf(R.id.media_seamless), Integer.valueOf(R.id.media_progress_bar), Integer.valueOf(R.id.actionPlayPause), Integer.valueOf(R.id.actionNext), Integer.valueOf(R.id.actionPrev), Integer.valueOf(R.id.action0), Integer.valueOf(R.id.action1), Integer.valueOf(R.id.action2), Integer.valueOf(R.id.action3), Integer.valueOf(R.id.action4), Integer.valueOf(R.id.icon), Integer.valueOf(R.id.media_scrubbing_elapsed_time), Integer.valueOf(R.id.media_scrubbing_total_time));
    public static final Set genericButtonIds = SetsKt.setOf(Integer.valueOf(R.id.action0), Integer.valueOf(R.id.action1), Integer.valueOf(R.id.action2), Integer.valueOf(R.id.action3), Integer.valueOf(R.id.action4));
    public static final Set expandedBottomActionIds = SetsKt.setOf(Integer.valueOf(R.id.media_progress_bar), Integer.valueOf(R.id.actionPrev), Integer.valueOf(R.id.actionNext), Integer.valueOf(R.id.action0), Integer.valueOf(R.id.action1), Integer.valueOf(R.id.action2), Integer.valueOf(R.id.action3), Integer.valueOf(R.id.action4), Integer.valueOf(R.id.media_scrubbing_elapsed_time), Integer.valueOf(R.id.media_scrubbing_total_time));
    public static final Set detailIds = SetsKt.setOf(Integer.valueOf(R.id.header_title), Integer.valueOf(R.id.header_artist), Integer.valueOf(R.id.media_explicit_indicator), Integer.valueOf(R.id.actionPlayPause));
    public static final Set backgroundIds = SetsKt.setOf(Integer.valueOf(R.id.album_art), Integer.valueOf(R.id.turbulence_noise_view), Integer.valueOf(R.id.loading_effect_view), Integer.valueOf(R.id.touch_ripple_view));

    public MediaViewHolder(View view) {
        this.player = (TransitionLayout) view;
        this.albumView = (ImageView) view.requireViewById(R.id.album_art);
        this.multiRippleView = (MultiRippleView) view.requireViewById(R.id.touch_ripple_view);
        this.turbulenceNoiseView = (TurbulenceNoiseView) view.requireViewById(R.id.turbulence_noise_view);
        this.loadingEffectView = (LoadingEffectView) view.requireViewById(R.id.loading_effect_view);
        this.appIcon = (ImageView) view.requireViewById(R.id.icon);
        this.titleText = (TextView) view.requireViewById(R.id.header_title);
        this.artistText = (TextView) view.requireViewById(R.id.header_artist);
        this.explicitIndicator = view.requireViewById(R.id.media_explicit_indicator);
        this.seamless = (ViewGroup) view.requireViewById(R.id.media_seamless);
        this.seamlessIcon = (ImageView) view.requireViewById(R.id.media_seamless_image);
        this.seamlessText = (TextView) view.requireViewById(R.id.media_seamless_text);
        this.seamlessButton = view.requireViewById(R.id.media_seamless_button);
        this.seekBar = (SeekBar) view.requireViewById(R.id.media_progress_bar);
        this.scrubbingElapsedTimeView = (TextView) view.requireViewById(R.id.media_scrubbing_elapsed_time);
        this.scrubbingTotalTimeView = (TextView) view.requireViewById(R.id.media_scrubbing_total_time);
        this.gutsViewHolder = new GutsViewHolder(view);
        this.actionPlayPause = (ImageButton) view.requireViewById(R.id.actionPlayPause);
        this.actionNext = (ImageButton) view.requireViewById(R.id.actionNext);
        this.actionPrev = (ImageButton) view.requireViewById(R.id.actionPrev);
        this.action0 = (ImageButton) view.requireViewById(R.id.action0);
        this.action1 = (ImageButton) view.requireViewById(R.id.action1);
        this.action2 = (ImageButton) view.requireViewById(R.id.action2);
        this.action3 = (ImageButton) view.requireViewById(R.id.action3);
        this.action4 = (ImageButton) view.requireViewById(R.id.action4);
    }

    public final ImageButton getAction(int i) {
        if (i == R.id.actionPlayPause) {
            return this.actionPlayPause;
        }
        if (i == R.id.actionNext) {
            return this.actionNext;
        }
        if (i == R.id.actionPrev) {
            return this.actionPrev;
        }
        if (i == R.id.action0) {
            return this.action0;
        }
        if (i == R.id.action1) {
            return this.action1;
        }
        if (i == R.id.action2) {
            return this.action2;
        }
        if (i == R.id.action3) {
            return this.action3;
        }
        if (i == R.id.action4) {
            return this.action4;
        }
        throw new IllegalArgumentException();
    }
}

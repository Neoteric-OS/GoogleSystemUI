package com.android.systemui.common.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import com.android.systemui.res.R$styleable;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class SeekBarWithIconButtonsView extends LinearLayout {
    public final ImageView mIconEnd;
    public final ImageView mIconStart;
    public final int mSeekBarChangeMagnitude;
    public final SeekBarChangeListener mSeekBarListener;
    public final SeekBar mSeekbar;
    public boolean mSetProgressFromButtonFlag;
    public String[] mStateLabels;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnSeekBarWithIconButtonsChangeListener extends SeekBar.OnSeekBarChangeListener {
        void onUserInteractionFinalized(SeekBar seekBar, int i);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        public OnSeekBarWithIconButtonsChangeListener mOnSeekBarChangeListener = null;

        public SeekBarChangeListener() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            SeekBarWithIconButtonsView seekBarWithIconButtonsView = SeekBarWithIconButtonsView.this;
            if (seekBarWithIconButtonsView.mStateLabels != null) {
                SeekBar seekBar2 = seekBarWithIconButtonsView.mSeekbar;
                int progress = seekBar2.getProgress();
                String[] strArr = seekBarWithIconButtonsView.mStateLabels;
                seekBar2.setStateDescription(progress < strArr.length ? strArr[seekBarWithIconButtonsView.mSeekbar.getProgress()] : "");
            }
            OnSeekBarWithIconButtonsChangeListener onSeekBarWithIconButtonsChangeListener = this.mOnSeekBarChangeListener;
            if (onSeekBarWithIconButtonsChangeListener != null) {
                SeekBarWithIconButtonsView seekBarWithIconButtonsView2 = SeekBarWithIconButtonsView.this;
                if (seekBarWithIconButtonsView2.mSetProgressFromButtonFlag) {
                    seekBarWithIconButtonsView2.mSetProgressFromButtonFlag = false;
                    onSeekBarWithIconButtonsChangeListener.onProgressChanged(seekBar, i, true);
                    this.mOnSeekBarChangeListener.onUserInteractionFinalized(seekBar, 1);
                } else {
                    onSeekBarWithIconButtonsChangeListener.onProgressChanged(seekBar, i, z);
                }
            }
            SeekBarWithIconButtonsView.this.updateIconViewIfNeeded(i);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStartTrackingTouch(SeekBar seekBar) {
            OnSeekBarWithIconButtonsChangeListener onSeekBarWithIconButtonsChangeListener = this.mOnSeekBarChangeListener;
            if (onSeekBarWithIconButtonsChangeListener != null) {
                onSeekBarWithIconButtonsChangeListener.onStartTrackingTouch(seekBar);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStopTrackingTouch(SeekBar seekBar) {
            OnSeekBarWithIconButtonsChangeListener onSeekBarWithIconButtonsChangeListener = this.mOnSeekBarChangeListener;
            if (onSeekBarWithIconButtonsChangeListener != null) {
                onSeekBarWithIconButtonsChangeListener.onStopTrackingTouch(seekBar);
                this.mOnSeekBarChangeListener.onUserInteractionFinalized(seekBar, 0);
            }
        }
    }

    public SeekBarWithIconButtonsView(Context context) {
        this(context, null);
    }

    public OnSeekBarWithIconButtonsChangeListener getOnSeekBarWithIconButtonsChangeListener() {
        return this.mSeekBarListener.mOnSeekBarChangeListener;
    }

    public int getProgress() {
        return this.mSeekbar.getProgress();
    }

    public SeekBar getSeekbar() {
        return this.mSeekbar;
    }

    public final void setProgress(int i) {
        this.mSeekbar.setProgress(i);
        updateIconViewIfNeeded(this.mSeekbar.getProgress());
    }

    public final void updateIconViewIfNeeded(int i) {
        ImageView imageView = this.mIconStart;
        boolean z = i > 0;
        imageView.setEnabled(z);
        ((ViewGroup) imageView.getParent()).setEnabled(z);
        ImageView imageView2 = this.mIconEnd;
        boolean z2 = i < this.mSeekbar.getMax();
        imageView2.setEnabled(z2);
        ((ViewGroup) imageView2.getParent()).setEnabled(z2);
    }

    public SeekBarWithIconButtonsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SeekBarWithIconButtonsView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SeekBarWithIconButtonsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        final int i3 = 1;
        this.mSeekBarChangeMagnitude = 1;
        final int i4 = 0;
        this.mSetProgressFromButtonFlag = false;
        SeekBarChangeListener seekBarChangeListener = new SeekBarChangeListener();
        this.mSeekBarListener = seekBarChangeListener;
        this.mStateLabels = null;
        LayoutInflater.from(context).inflate(R.layout.seekbar_with_icon_buttons, (ViewGroup) this, true);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.icon_start_frame);
        ViewGroup viewGroup2 = (ViewGroup) findViewById(R.id.icon_end_frame);
        this.mIconStart = (ImageView) findViewById(R.id.icon_start);
        this.mIconEnd = (ImageView) findViewById(R.id.icon_end);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        this.mSeekbar = seekBar;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SeekBarWithIconButtonsView_Layout, i, i2);
            int i5 = obtainStyledAttributes.getInt(2, 6);
            int i6 = obtainStyledAttributes.getInt(3, 0);
            seekBar.setMax(i5);
            setProgress(i6);
            int resourceId = obtainStyledAttributes.getResourceId(1, 0);
            int resourceId2 = obtainStyledAttributes.getResourceId(0, 0);
            if (resourceId != 0) {
                viewGroup.setContentDescription(context.getString(resourceId));
            }
            if (resourceId2 != 0) {
                viewGroup2.setContentDescription(context.getString(resourceId2));
            }
            int resourceId3 = obtainStyledAttributes.getResourceId(5, 0);
            if (resourceId3 != 0) {
                seekBar.setTickMark(getResources().getDrawable(resourceId3));
            }
            this.mSeekBarChangeMagnitude = obtainStyledAttributes.getInt(4, 1);
        } else {
            seekBar.setMax(6);
            setProgress(0);
        }
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        viewGroup.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.common.ui.view.SeekBarWithIconButtonsView$$ExternalSyntheticLambda0
            public final /* synthetic */ SeekBarWithIconButtonsView f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i7 = i4;
                SeekBarWithIconButtonsView seekBarWithIconButtonsView = this.f$0;
                switch (i7) {
                    case 0:
                        int progress = seekBarWithIconButtonsView.mSeekbar.getProgress();
                        if (progress > 0) {
                            int i8 = progress - seekBarWithIconButtonsView.mSeekBarChangeMagnitude;
                            seekBarWithIconButtonsView.mSetProgressFromButtonFlag = true;
                            seekBarWithIconButtonsView.mSeekbar.setProgress(i8);
                            seekBarWithIconButtonsView.updateIconViewIfNeeded(seekBarWithIconButtonsView.mSeekbar.getProgress());
                            break;
                        }
                        break;
                    default:
                        int progress2 = seekBarWithIconButtonsView.mSeekbar.getProgress();
                        if (progress2 < seekBarWithIconButtonsView.mSeekbar.getMax()) {
                            int i9 = progress2 + seekBarWithIconButtonsView.mSeekBarChangeMagnitude;
                            seekBarWithIconButtonsView.mSetProgressFromButtonFlag = true;
                            seekBarWithIconButtonsView.mSeekbar.setProgress(i9);
                            seekBarWithIconButtonsView.updateIconViewIfNeeded(seekBarWithIconButtonsView.mSeekbar.getProgress());
                            break;
                        }
                        break;
                }
            }
        });
        viewGroup2.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.common.ui.view.SeekBarWithIconButtonsView$$ExternalSyntheticLambda0
            public final /* synthetic */ SeekBarWithIconButtonsView f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i7 = i3;
                SeekBarWithIconButtonsView seekBarWithIconButtonsView = this.f$0;
                switch (i7) {
                    case 0:
                        int progress = seekBarWithIconButtonsView.mSeekbar.getProgress();
                        if (progress > 0) {
                            int i8 = progress - seekBarWithIconButtonsView.mSeekBarChangeMagnitude;
                            seekBarWithIconButtonsView.mSetProgressFromButtonFlag = true;
                            seekBarWithIconButtonsView.mSeekbar.setProgress(i8);
                            seekBarWithIconButtonsView.updateIconViewIfNeeded(seekBarWithIconButtonsView.mSeekbar.getProgress());
                            break;
                        }
                        break;
                    default:
                        int progress2 = seekBarWithIconButtonsView.mSeekbar.getProgress();
                        if (progress2 < seekBarWithIconButtonsView.mSeekbar.getMax()) {
                            int i9 = progress2 + seekBarWithIconButtonsView.mSeekBarChangeMagnitude;
                            seekBarWithIconButtonsView.mSetProgressFromButtonFlag = true;
                            seekBarWithIconButtonsView.mSeekbar.setProgress(i9);
                            seekBarWithIconButtonsView.updateIconViewIfNeeded(seekBarWithIconButtonsView.mSeekbar.getProgress());
                            break;
                        }
                        break;
                }
            }
        });
    }
}

package com.android.systemui.volume;

import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import com.android.settingslib.Utils;
import com.android.systemui.Prefs;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.volume.VolumeDialogImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class VolumeDialogImpl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ VolumeDialogImpl f$0;

    public /* synthetic */ VolumeDialogImpl$$ExternalSyntheticLambda2(VolumeDialogImpl volumeDialogImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = volumeDialogImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VolumeDialogImpl.CustomDialog customDialog;
        final ImageButton imageButton;
        int i = this.$r8$classId;
        final VolumeDialogImpl volumeDialogImpl = this.f$0;
        switch (i) {
            case 0:
                volumeDialogImpl.getDrawerIconViewForMode(volumeDialogImpl.mState.ringerModeInternal).setVisibility(0);
                return;
            case 1:
                View view = volumeDialogImpl.mODICaptionsTooltipView;
                if (view != null) {
                    view.setVisibility(4);
                    return;
                }
                return;
            case 2:
                VolumeDialogController volumeDialogController = volumeDialogImpl.mController;
                if (volumeDialogController != null) {
                    volumeDialogController.notifyVisible(false);
                }
                VolumeDialogImpl.CustomDialog customDialog2 = volumeDialogImpl.mDialog;
                if (customDialog2 != null) {
                    customDialog2.dismiss();
                }
                if (volumeDialogImpl.mHasSeenODICaptionsTooltip && volumeDialogImpl.mODICaptionsTooltipView != null && (customDialog = volumeDialogImpl.mDialog) != null) {
                    ((ViewGroup) customDialog.findViewById(R.id.volume_dialog_container)).removeView(volumeDialogImpl.mODICaptionsTooltipView);
                    volumeDialogImpl.mODICaptionsTooltipView = null;
                }
                volumeDialogImpl.mIsAnimatingDismiss = false;
                volumeDialogImpl.hideRingerDrawer();
                return;
            case 3:
                if (Prefs.getBoolean(volumeDialogImpl.mContext, "TouchedRingerToggle") || (imageButton = volumeDialogImpl.mRingerIcon) == null) {
                    return;
                }
                imageButton.postOnAnimationDelayed(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogImpl$$ExternalSyntheticLambda18
                    @Override // java.lang.Runnable
                    public final void run() {
                        VolumeDialogImpl volumeDialogImpl2 = VolumeDialogImpl.this;
                        ImageButton imageButton2 = imageButton;
                        volumeDialogImpl2.getClass();
                        if (imageButton2 != null) {
                            imageButton2.setPressed(true);
                            imageButton2.postOnAnimationDelayed(new VolumeDialogImpl$$ExternalSyntheticLambda21(0, imageButton2), 200L);
                        }
                    }
                }, 1500L);
                return;
            case 4:
                LayerDrawable layerDrawable = (LayerDrawable) volumeDialogImpl.mRingerAndDrawerContainer.getBackground();
                if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
                    return;
                }
                volumeDialogImpl.mRingerAndDrawerContainerBackground = layerDrawable.getDrawable(0);
                volumeDialogImpl.updateBackgroundForDrawerClosedAmount();
                if (volumeDialogImpl.mTopContainer == null) {
                    return;
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(new Drawable[]{new ColorDrawable(Utils.getColorAttrDefaultColor(android.R.^attr-private.colorSurface, 0, volumeDialogImpl.mContext))});
                layerDrawable2.setLayerSize(0, volumeDialogImpl.mDialogWidth, !volumeDialogImpl.isLandscape() ? volumeDialogImpl.mDialogRowsView.getHeight() : volumeDialogImpl.mDialogRowsView.getHeight() + volumeDialogImpl.mDialogCornerRadius);
                layerDrawable2.setLayerInsetTop(0, !volumeDialogImpl.isLandscape() ? volumeDialogImpl.mDialogRowsViewContainer.getTop() : volumeDialogImpl.mDialogRowsViewContainer.getTop() - volumeDialogImpl.mDialogCornerRadius);
                layerDrawable2.setLayerGravity(0, 53);
                if (volumeDialogImpl.isLandscape()) {
                    volumeDialogImpl.mRingerAndDrawerContainer.setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.systemui.volume.VolumeDialogImpl.6
                        @Override // android.view.ViewOutlineProvider
                        public final void getOutline(View view2, Outline outline) {
                            outline.setRoundRect(0, 0, view2.getWidth(), view2.getHeight(), VolumeDialogImpl.this.mDialogCornerRadius);
                        }
                    });
                    volumeDialogImpl.mRingerAndDrawerContainer.setClipToOutline(true);
                }
                volumeDialogImpl.mTopContainer.setBackground(layerDrawable2);
                return;
            case 5:
                synchronized (volumeDialogImpl.mSafetyWarningLock) {
                    volumeDialogImpl.mCsdDialog = null;
                }
                volumeDialogImpl.recheckH(null);
                return;
            case 6:
                volumeDialogImpl.mRingerDrawerContainer.setVisibility(4);
                return;
            default:
                volumeDialogImpl.mHandler.postDelayed(new VolumeDialogImpl$$ExternalSyntheticLambda2(volumeDialogImpl, 2), 50L);
                return;
        }
    }
}

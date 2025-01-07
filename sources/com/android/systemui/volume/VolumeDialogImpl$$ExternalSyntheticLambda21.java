package com.android.systemui.volume;

import android.widget.ImageButton;
import com.android.systemui.volume.VolumeDialogImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class VolumeDialogImpl$$ExternalSyntheticLambda21 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ VolumeDialogImpl$$ExternalSyntheticLambda21(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ImageButton imageButton = (ImageButton) obj;
                if (imageButton != null) {
                    imageButton.setPressed(false);
                    break;
                }
                break;
            default:
                VolumeDialogImpl.RingerDrawerItemClickListener ringerDrawerItemClickListener = (VolumeDialogImpl.RingerDrawerItemClickListener) obj;
                ringerDrawerItemClickListener.this$0.mRingerDrawerNewSelectionBg.setAlpha(0.0f);
                if (ringerDrawerItemClickListener.this$0.isLandscape()) {
                    VolumeDialogImpl volumeDialogImpl = ringerDrawerItemClickListener.this$0;
                    volumeDialogImpl.mSelectedRingerContainer.setTranslationX(volumeDialogImpl.getTranslationInDrawerForRingerMode(ringerDrawerItemClickListener.mClickedRingerMode));
                } else {
                    VolumeDialogImpl volumeDialogImpl2 = ringerDrawerItemClickListener.this$0;
                    volumeDialogImpl2.mSelectedRingerContainer.setTranslationY(volumeDialogImpl2.getTranslationInDrawerForRingerMode(ringerDrawerItemClickListener.mClickedRingerMode));
                }
                ringerDrawerItemClickListener.this$0.mSelectedRingerContainer.setVisibility(0);
                ringerDrawerItemClickListener.this$0.hideRingerDrawer();
                break;
        }
    }
}

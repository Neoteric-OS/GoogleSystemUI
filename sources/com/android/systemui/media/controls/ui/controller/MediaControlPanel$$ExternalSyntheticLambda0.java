package com.android.systemui.media.controls.ui.controller;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaControlPanel$$ExternalSyntheticLambda0 implements View.OnLongClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MediaControlPanel f$0;

    public /* synthetic */ MediaControlPanel$$ExternalSyntheticLambda0(MediaControlPanel mediaControlPanel, int i) {
        this.$r8$classId = i;
        this.f$0 = mediaControlPanel;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        View view2;
        int i = this.$r8$classId;
        MediaControlPanel mediaControlPanel = this.f$0;
        switch (i) {
            case 0:
                if (!mediaControlPanel.mFalsingManager.isFalseLongTap(1)) {
                    if (!mediaControlPanel.mMediaViewController.isGutsVisible) {
                        mediaControlPanel.openGuts();
                        break;
                    } else {
                        mediaControlPanel.closeGuts(false);
                        break;
                    }
                }
                break;
            case 1:
                if (!mediaControlPanel.mFalsingManager.isFalseLongTap(1)) {
                    if (!mediaControlPanel.mMediaViewController.isGutsVisible) {
                        mediaControlPanel.openGuts();
                        break;
                    } else {
                        mediaControlPanel.closeGuts(false);
                        break;
                    }
                }
                break;
            default:
                if (!mediaControlPanel.mFalsingManager.isFalseLongTap(1) && (view2 = (View) view.getParent()) != null) {
                    view2.performLongClick();
                    break;
                }
                break;
        }
        return true;
    }
}

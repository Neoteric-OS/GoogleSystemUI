package com.android.systemui.statusbar.notification.row;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PartialConversationInfo$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PartialConversationInfo f$0;

    public /* synthetic */ PartialConversationInfo$$ExternalSyntheticLambda0(PartialConversationInfo partialConversationInfo, int i) {
        this.$r8$classId = i;
        this.f$0 = partialConversationInfo;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ChannelEditorDialogController channelEditorDialogController;
        int i = this.$r8$classId;
        final PartialConversationInfo partialConversationInfo = this.f$0;
        switch (i) {
            case 0:
                partialConversationInfo.mGutsContainer.closeControls(view, false);
                return;
            default:
                if (partialConversationInfo.mPresentingChannelEditorDialog || (channelEditorDialogController = partialConversationInfo.mChannelEditorDialogController) == null) {
                    return;
                }
                partialConversationInfo.mPresentingChannelEditorDialog = true;
                channelEditorDialogController.prepareDialogForApp(partialConversationInfo.mAppName, partialConversationInfo.mPackageName, partialConversationInfo.mAppUid, partialConversationInfo.mNotificationChannel, partialConversationInfo.mPkgIcon, partialConversationInfo.mOnSettingsClickListener);
                ChannelEditorDialogController channelEditorDialogController2 = partialConversationInfo.mChannelEditorDialogController;
                channelEditorDialogController2.onFinishListener = new OnChannelEditorDialogFinishedListener() { // from class: com.android.systemui.statusbar.notification.row.PartialConversationInfo$$ExternalSyntheticLambda3
                    @Override // com.android.systemui.statusbar.notification.row.OnChannelEditorDialogFinishedListener
                    public final void onChannelEditorDialogFinished() {
                        PartialConversationInfo partialConversationInfo2 = PartialConversationInfo.this;
                        partialConversationInfo2.mPresentingChannelEditorDialog = false;
                        partialConversationInfo2.mGutsContainer.closeControls(partialConversationInfo2, false);
                    }
                };
                if (!channelEditorDialogController2.prepared) {
                    throw new IllegalStateException("Must call prepareDialogForApp() before calling show()");
                }
                ChannelEditorDialog channelEditorDialog = channelEditorDialogController2.dialog;
                if (channelEditorDialog == null) {
                    channelEditorDialog = null;
                }
                channelEditorDialog.show();
                return;
        }
    }
}

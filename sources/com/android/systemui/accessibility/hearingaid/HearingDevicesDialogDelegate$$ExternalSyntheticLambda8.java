package com.android.systemui.accessibility.hearingaid;

import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class HearingDevicesDialogDelegate$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ HearingDevicesDialogDelegate f$0;

    public /* synthetic */ HearingDevicesDialogDelegate$$ExternalSyntheticLambda8(HearingDevicesDialogDelegate hearingDevicesDialogDelegate, int i) {
        this.$r8$classId = i;
        this.f$0 = hearingDevicesDialogDelegate;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        HearingDevicesDialogDelegate hearingDevicesDialogDelegate = this.f$0;
        switch (i) {
            case 0:
                HearingDevicesListAdapter hearingDevicesListAdapter = hearingDevicesDialogDelegate.mDeviceListAdapter;
                List list = hearingDevicesDialogDelegate.mHearingDeviceItemList;
                hearingDevicesListAdapter.mItemList.clear();
                hearingDevicesListAdapter.mItemList.addAll(list);
                hearingDevicesListAdapter.notifyDataSetChanged();
                break;
            default:
                HearingDevicesListAdapter hearingDevicesListAdapter2 = hearingDevicesDialogDelegate.mDeviceListAdapter;
                List list2 = hearingDevicesDialogDelegate.mHearingDeviceItemList;
                hearingDevicesListAdapter2.mItemList.clear();
                hearingDevicesListAdapter2.mItemList.addAll(list2);
                hearingDevicesListAdapter2.notifyDataSetChanged();
                break;
        }
    }
}

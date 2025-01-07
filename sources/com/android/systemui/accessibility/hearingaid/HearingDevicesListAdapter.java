package com.android.systemui.accessibility.hearingaid;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settingslib.Utils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.bluetooth.qsdialog.DeviceItem;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HearingDevicesListAdapter extends RecyclerView.Adapter {
    public final HearingDevicesDialogDelegate mCallback;
    public final List mItemList;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeviceItemViewHolder extends RecyclerView.ViewHolder {
        public final View mContainer;
        public final Context mContext;
        public final ImageView mGearIcon;
        public final View mGearView;
        public final ImageView mIconView;
        public final TextView mNameView;
        public final TextView mSummaryView;

        public DeviceItemViewHolder(Context context, View view) {
            super(view);
            this.mContext = context;
            this.mContainer = view.requireViewById(R.id.bluetooth_device_row);
            this.mNameView = (TextView) view.requireViewById(R.id.bluetooth_device_name);
            this.mSummaryView = (TextView) view.requireViewById(R.id.bluetooth_device_summary);
            this.mIconView = (ImageView) view.requireViewById(R.id.bluetooth_device_icon);
            this.mGearIcon = (ImageView) view.requireViewById(R.id.gear_icon_image);
            this.mGearView = view.requireViewById(R.id.gear_icon);
        }
    }

    public HearingDevicesListAdapter(List list, HearingDevicesDialogDelegate hearingDevicesDialogDelegate) {
        this.mItemList = list;
        this.mCallback = hearingDevicesDialogDelegate;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.mItemList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final DeviceItem deviceItem = (DeviceItem) this.mItemList.get(i);
        DeviceItemViewHolder deviceItemViewHolder = (DeviceItemViewHolder) viewHolder;
        deviceItemViewHolder.mContainer.setEnabled(deviceItem.isEnabled);
        View view = deviceItemViewHolder.mContainer;
        final HearingDevicesDialogDelegate hearingDevicesDialogDelegate = this.mCallback;
        final int i2 = 0;
        view.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.accessibility.hearingaid.HearingDevicesListAdapter$DeviceItemViewHolder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                switch (i2) {
                    case 0:
                        HearingDevicesDialogDelegate hearingDevicesDialogDelegate2 = hearingDevicesDialogDelegate;
                        DeviceItem deviceItem2 = deviceItem;
                        hearingDevicesDialogDelegate2.getClass();
                        int ordinal = deviceItem2.type.ordinal();
                        CachedBluetoothDevice cachedBluetoothDevice = deviceItem2.cachedBluetoothDevice;
                        int i3 = hearingDevicesDialogDelegate2.mLaunchSourceId;
                        HearingDevicesUiEventLogger hearingDevicesUiEventLogger = hearingDevicesDialogDelegate2.mUiEventLogger;
                        if (ordinal != 0) {
                            if (ordinal == 3) {
                                hearingDevicesUiEventLogger.log(HearingDevicesUiEvent.HEARING_DEVICES_SET_ACTIVE, i3, null);
                                cachedBluetoothDevice.setActive();
                                break;
                            } else if (ordinal != 4) {
                                if (ordinal == 5) {
                                    hearingDevicesUiEventLogger.log(HearingDevicesUiEvent.HEARING_DEVICES_CONNECT, i3, null);
                                    cachedBluetoothDevice.connect$1();
                                    break;
                                }
                            }
                        }
                        hearingDevicesUiEventLogger.log(HearingDevicesUiEvent.HEARING_DEVICES_DISCONNECT, i3, null);
                        cachedBluetoothDevice.disconnect();
                        break;
                    default:
                        HearingDevicesDialogDelegate hearingDevicesDialogDelegate3 = hearingDevicesDialogDelegate;
                        DeviceItem deviceItem3 = deviceItem;
                        hearingDevicesDialogDelegate3.getClass();
                        hearingDevicesDialogDelegate3.mUiEventLogger.log(HearingDevicesUiEvent.HEARING_DEVICES_GEAR_CLICK, hearingDevicesDialogDelegate3.mLaunchSourceId, null);
                        SystemUIDialog systemUIDialog = hearingDevicesDialogDelegate3.mDialog;
                        if (systemUIDialog != null) {
                            systemUIDialog.dismiss();
                        }
                        Intent intent = new Intent("com.android.settings.BLUETOOTH_DEVICE_DETAIL_SETTINGS");
                        Bundle bundle = new Bundle();
                        bundle.putString("device_address", deviceItem3.cachedBluetoothDevice.mDevice.getAddress());
                        intent.putExtra(":settings:show_fragment_args", bundle);
                        intent.addFlags(268468224);
                        DialogTransitionAnimator dialogTransitionAnimator = hearingDevicesDialogDelegate3.mDialogTransitionAnimator;
                        dialogTransitionAnimator.getClass();
                        hearingDevicesDialogDelegate3.mActivityStarter.postStartActivityDismissingKeyguard(intent, 0, DialogTransitionAnimator.createActivityTransitionController$default(dialogTransitionAnimator, view2));
                        break;
                }
            }
        });
        deviceItemViewHolder.mContainer.setBackground(deviceItemViewHolder.mContext.getDrawable(deviceItem.background.intValue()));
        boolean z = deviceItem.isActive;
        int defaultColor = z ? Utils.getColorAttr(android.R.^attr-private.materialColorOnPrimaryContainer, deviceItemViewHolder.mContext).getDefaultColor() : Utils.getColorAttr(android.R.^attr-private.materialColorOnSurface, deviceItemViewHolder.mContext).getDefaultColor();
        Pair pair = deviceItem.iconWithDescription;
        Drawable mutate = ((Drawable) pair.getFirst()).mutate();
        mutate.setTint(defaultColor);
        deviceItemViewHolder.mIconView.setImageDrawable(mutate);
        deviceItemViewHolder.mIconView.setContentDescription((CharSequence) pair.getSecond());
        deviceItemViewHolder.mNameView.setTextAppearance(z ? R.style.BluetoothTileDialog_DeviceName_Active : R.style.BluetoothTileDialog_DeviceName);
        deviceItemViewHolder.mNameView.setText(deviceItem.deviceName);
        deviceItemViewHolder.mSummaryView.setTextAppearance(z ? R.style.BluetoothTileDialog_DeviceSummary_Active : R.style.BluetoothTileDialog_DeviceSummary);
        deviceItemViewHolder.mSummaryView.setText(deviceItem.connectionSummary);
        deviceItemViewHolder.mGearIcon.getDrawable().mutate().setTint(defaultColor);
        final int i3 = 1;
        deviceItemViewHolder.mGearView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.accessibility.hearingaid.HearingDevicesListAdapter$DeviceItemViewHolder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                switch (i3) {
                    case 0:
                        HearingDevicesDialogDelegate hearingDevicesDialogDelegate2 = hearingDevicesDialogDelegate;
                        DeviceItem deviceItem2 = deviceItem;
                        hearingDevicesDialogDelegate2.getClass();
                        int ordinal = deviceItem2.type.ordinal();
                        CachedBluetoothDevice cachedBluetoothDevice = deviceItem2.cachedBluetoothDevice;
                        int i32 = hearingDevicesDialogDelegate2.mLaunchSourceId;
                        HearingDevicesUiEventLogger hearingDevicesUiEventLogger = hearingDevicesDialogDelegate2.mUiEventLogger;
                        if (ordinal != 0) {
                            if (ordinal == 3) {
                                hearingDevicesUiEventLogger.log(HearingDevicesUiEvent.HEARING_DEVICES_SET_ACTIVE, i32, null);
                                cachedBluetoothDevice.setActive();
                                break;
                            } else if (ordinal != 4) {
                                if (ordinal == 5) {
                                    hearingDevicesUiEventLogger.log(HearingDevicesUiEvent.HEARING_DEVICES_CONNECT, i32, null);
                                    cachedBluetoothDevice.connect$1();
                                    break;
                                }
                            }
                        }
                        hearingDevicesUiEventLogger.log(HearingDevicesUiEvent.HEARING_DEVICES_DISCONNECT, i32, null);
                        cachedBluetoothDevice.disconnect();
                        break;
                    default:
                        HearingDevicesDialogDelegate hearingDevicesDialogDelegate3 = hearingDevicesDialogDelegate;
                        DeviceItem deviceItem3 = deviceItem;
                        hearingDevicesDialogDelegate3.getClass();
                        hearingDevicesDialogDelegate3.mUiEventLogger.log(HearingDevicesUiEvent.HEARING_DEVICES_GEAR_CLICK, hearingDevicesDialogDelegate3.mLaunchSourceId, null);
                        SystemUIDialog systemUIDialog = hearingDevicesDialogDelegate3.mDialog;
                        if (systemUIDialog != null) {
                            systemUIDialog.dismiss();
                        }
                        Intent intent = new Intent("com.android.settings.BLUETOOTH_DEVICE_DETAIL_SETTINGS");
                        Bundle bundle = new Bundle();
                        bundle.putString("device_address", deviceItem3.cachedBluetoothDevice.mDevice.getAddress());
                        intent.putExtra(":settings:show_fragment_args", bundle);
                        intent.addFlags(268468224);
                        DialogTransitionAnimator dialogTransitionAnimator = hearingDevicesDialogDelegate3.mDialogTransitionAnimator;
                        dialogTransitionAnimator.getClass();
                        hearingDevicesDialogDelegate3.mActivityStarter.postStartActivityDismissingKeyguard(intent, 0, DialogTransitionAnimator.createActivityTransitionController$default(dialogTransitionAnimator, view2));
                        break;
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
        return new DeviceItemViewHolder(viewGroup.getContext(), LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bluetooth_device_item, viewGroup, false));
    }
}

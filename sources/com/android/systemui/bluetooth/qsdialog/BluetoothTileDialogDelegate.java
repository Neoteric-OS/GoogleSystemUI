package com.android.systemui.bluetooth.qsdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.Utils;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothTileDialogDelegate implements SystemUIDialog.Delegate {
    public final BluetoothTileDialogViewModel bluetoothTileDialogCallback;
    public final int cachedContentHeight;
    public final Adapter deviceItemAdapter;
    public final BluetoothTileDialogViewModel$createBluetoothTileDialog$2 dismissListener;
    public final BluetoothTileDialogViewModel.UiProperties initialUiProperties;
    public final BluetoothTileDialogLogger logger;
    public final CoroutineDispatcher mainDispatcher;
    public final SystemClock systemClock;
    public final SystemUIDialog.Factory systemuiDialogFactory;
    public final UiEventLogger uiEventLogger;
    public final StateFlowImpl mutableBluetoothStateToggle = StateFlowKt.MutableStateFlow(null);
    public final StateFlowImpl mutableBluetoothAutoOnToggle = StateFlowKt.MutableStateFlow(null);
    public final SharedFlowImpl mutableDeviceItemClick = SharedFlowKt.MutableSharedFlow$default(0, 1, null, 5);
    public final SharedFlowImpl mutableContentHeight = SharedFlowKt.MutableSharedFlow$default(0, 1, null, 5);
    public long lastUiUpdateMs = -1;
    public int lastItemRow = -1;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Adapter extends RecyclerView.Adapter {
        public final AsyncListDiffer asyncListDiffer = new AsyncListDiffer(this, new BluetoothTileDialogDelegate$Adapter$diffUtilCallback$1());
        public final BluetoothTileDialogViewModel onClickCallback;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class DeviceItemViewHolder extends RecyclerView.ViewHolder {
            public final View container;
            public final View divider;
            public final View gearView;
            public final ImageView iconGear;
            public final ImageView iconView;
            public final TextView nameView;
            public final TextView summaryView;

            public DeviceItemViewHolder(View view) {
                super(view);
                this.container = view.requireViewById(R.id.bluetooth_device_row);
                this.nameView = (TextView) view.requireViewById(R.id.bluetooth_device_name);
                this.summaryView = (TextView) view.requireViewById(R.id.bluetooth_device_summary);
                this.iconView = (ImageView) view.requireViewById(R.id.bluetooth_device_icon);
                this.iconGear = (ImageView) view.requireViewById(R.id.gear_icon_image);
                this.gearView = view.requireViewById(R.id.gear_icon);
                this.divider = view.requireViewById(R.id.divider);
            }
        }

        public Adapter(BluetoothTileDialogViewModel bluetoothTileDialogViewModel) {
            this.onClickCallback = bluetoothTileDialogViewModel;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final int getItemCount() {
            return this.asyncListDiffer.mReadOnlyList.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            Drawable mutate;
            final int i2 = 1;
            DeviceItemViewHolder deviceItemViewHolder = (DeviceItemViewHolder) viewHolder;
            final DeviceItem deviceItem = (DeviceItem) this.asyncListDiffer.mReadOnlyList.get(i);
            Intrinsics.checkNotNull(deviceItem);
            View view = deviceItemViewHolder.container;
            final BluetoothTileDialogDelegate bluetoothTileDialogDelegate = BluetoothTileDialogDelegate.this;
            view.setEnabled(deviceItem.isEnabled);
            view.setBackground(view.getContext().getDrawable(deviceItem.background.intValue()));
            view.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$Adapter$DeviceItemViewHolder$bind$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    switch (i2) {
                        case 0:
                            BluetoothTileDialogViewModel bluetoothTileDialogViewModel = (BluetoothTileDialogViewModel) bluetoothTileDialogDelegate;
                            DeviceItem deviceItem2 = deviceItem;
                            Intrinsics.checkNotNull(view2);
                            bluetoothTileDialogViewModel.uiEventLogger.log(BluetoothTileDialogUiEvent.DEVICE_GEAR_CLICKED);
                            Intent intent = new Intent("com.android.settings.BLUETOOTH_DEVICE_DETAIL_SETTINGS");
                            Bundle bundle = new Bundle();
                            bundle.putString("device_address", deviceItem2.cachedBluetoothDevice.mDevice.getAddress());
                            intent.putExtra(":settings:show_fragment_args", bundle);
                            bluetoothTileDialogViewModel.startSettingsActivity(intent, view2);
                            break;
                        default:
                            ((BluetoothTileDialogDelegate) bluetoothTileDialogDelegate).mutableDeviceItemClick.tryEmit(deviceItem);
                            ((BluetoothTileDialogDelegate) bluetoothTileDialogDelegate).uiEventLogger.log(BluetoothTileDialogUiEvent.DEVICE_CLICKED);
                            break;
                    }
                }
            });
            Context context = view.getContext();
            boolean z = deviceItem.isActive;
            int defaultColor = Utils.getColorAttr(z ? android.R.^attr-private.materialColorOnPrimaryContainer : android.R.^attr-private.materialColorOnSurface, context).getDefaultColor();
            ImageView imageView = deviceItemViewHolder.iconView;
            Pair pair = deviceItem.iconWithDescription;
            Drawable drawable = (Drawable) pair.getFirst();
            Drawable mutate2 = drawable.mutate();
            if (mutate2 != null) {
                mutate2.setTint(defaultColor);
            }
            imageView.setImageDrawable(drawable);
            imageView.setContentDescription((CharSequence) pair.getSecond());
            Drawable drawable2 = deviceItemViewHolder.iconGear.getDrawable();
            if (drawable2 != null && (mutate = drawable2.mutate()) != null) {
                mutate.setTint(defaultColor);
            }
            deviceItemViewHolder.divider.setBackgroundColor(defaultColor);
            deviceItemViewHolder.nameView.setTextAppearance(z ? R.style.BluetoothTileDialog_DeviceName_Active : R.style.BluetoothTileDialog_DeviceName);
            deviceItemViewHolder.summaryView.setTextAppearance(z ? R.style.BluetoothTileDialog_DeviceSummary_Active : R.style.BluetoothTileDialog_DeviceSummary);
            view.setAccessibilityDelegate(new BluetoothTileDialogDelegate$onCreate$5$2(1, deviceItem));
            deviceItemViewHolder.nameView.setText(deviceItem.deviceName);
            deviceItemViewHolder.summaryView.setText(deviceItem.connectionSummary);
            View view2 = deviceItemViewHolder.gearView;
            final BluetoothTileDialogViewModel bluetoothTileDialogViewModel = this.onClickCallback;
            final int i3 = 0;
            view2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$Adapter$DeviceItemViewHolder$bind$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view22) {
                    switch (i3) {
                        case 0:
                            BluetoothTileDialogViewModel bluetoothTileDialogViewModel2 = (BluetoothTileDialogViewModel) bluetoothTileDialogViewModel;
                            DeviceItem deviceItem2 = deviceItem;
                            Intrinsics.checkNotNull(view22);
                            bluetoothTileDialogViewModel2.uiEventLogger.log(BluetoothTileDialogUiEvent.DEVICE_GEAR_CLICKED);
                            Intent intent = new Intent("com.android.settings.BLUETOOTH_DEVICE_DETAIL_SETTINGS");
                            Bundle bundle = new Bundle();
                            bundle.putString("device_address", deviceItem2.cachedBluetoothDevice.mDevice.getAddress());
                            intent.putExtra(":settings:show_fragment_args", bundle);
                            bluetoothTileDialogViewModel2.startSettingsActivity(intent, view22);
                            break;
                        default:
                            ((BluetoothTileDialogDelegate) bluetoothTileDialogViewModel).mutableDeviceItemClick.tryEmit(deviceItem);
                            ((BluetoothTileDialogDelegate) bluetoothTileDialogViewModel).uiEventLogger.log(BluetoothTileDialogUiEvent.DEVICE_CLICKED);
                            break;
                    }
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bluetooth_device_item, viewGroup, false);
            Intrinsics.checkNotNull(inflate);
            return new DeviceItemViewHolder(inflate);
        }
    }

    public BluetoothTileDialogDelegate(BluetoothTileDialogViewModel.UiProperties uiProperties, int i, BluetoothTileDialogViewModel bluetoothTileDialogViewModel, BluetoothTileDialogViewModel$createBluetoothTileDialog$2 bluetoothTileDialogViewModel$createBluetoothTileDialog$2, CoroutineDispatcher coroutineDispatcher, SystemClock systemClock, UiEventLogger uiEventLogger, BluetoothTileDialogLogger bluetoothTileDialogLogger, SystemUIDialog.Factory factory) {
        this.initialUiProperties = uiProperties;
        this.cachedContentHeight = i;
        this.bluetoothTileDialogCallback = bluetoothTileDialogViewModel;
        this.dismissListener = bluetoothTileDialogViewModel$createBluetoothTileDialog$2;
        this.mainDispatcher = coroutineDispatcher;
        this.systemClock = systemClock;
        this.uiEventLogger = uiEventLogger;
        this.logger = bluetoothTileDialogLogger;
        this.systemuiDialogFactory = factory;
        this.deviceItemAdapter = new Adapter(bluetoothTileDialogViewModel);
    }

    public final Object animateProgressBar$frameworks__base__packages__SystemUI__android_common__SystemUI_core(SystemUIDialog systemUIDialog, boolean z, SuspendLambda suspendLambda) {
        Object withContext = BuildersKt.withContext(this.mainDispatcher, new BluetoothTileDialogDelegate$animateProgressBar$2(z, this, systemUIDialog, null), suspendLambda);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        SystemUIDialog.Factory factory = this.systemuiDialogFactory;
        return factory.create(this, factory.mContext);
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onCreate(Dialog dialog, Bundle bundle) {
        final int i = 1;
        final int i2 = 0;
        final SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        SystemUIDialog.registerDismissListener(systemUIDialog, this.dismissListener);
        this.uiEventLogger.log(BluetoothTileDialogUiEvent.BLUETOOTH_TILE_DIALOG_SHOWN);
        Context context = systemUIDialog.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.bluetooth_tile_dialog, (ViewGroup) null);
        inflate.setAccessibilityPaneTitle(context.getText(R.string.accessibility_desc_quick_settings));
        systemUIDialog.setContentView(inflate);
        ((Switch) systemUIDialog.requireViewById(R.id.bluetooth_toggle)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$setupToggle$1
            public final /* synthetic */ BluetoothTileDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                switch (i2) {
                    case 0:
                        StateFlowImpl stateFlowImpl = this.this$0.mutableBluetoothStateToggle;
                        Boolean valueOf = Boolean.valueOf(z);
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, valueOf);
                        compoundButton.setEnabled(false);
                        compoundButton.setAlpha(0.3f);
                        this.this$0.logger.logBluetoothState(BluetoothStateStage.USER_TOGGLED, String.valueOf(z));
                        this.this$0.uiEventLogger.log(BluetoothTileDialogUiEvent.BLUETOOTH_TOGGLE_CLICKED);
                        break;
                    default:
                        AuthContainerView$$ExternalSyntheticOutline0.m(z, this.this$0.mutableBluetoothAutoOnToggle, null);
                        this.this$0.uiEventLogger.log(BluetoothTileDialogUiEvent.BLUETOOTH_AUTO_ON_TOGGLE_CLICKED);
                        break;
                }
            }
        });
        View requireViewById = systemUIDialog.requireViewById(R.id.bluetooth_auto_on_toggle_layout);
        BluetoothTileDialogViewModel.UiProperties uiProperties = this.initialUiProperties;
        requireViewById.setVisibility(uiProperties.autoOnToggleVisibility);
        ((Switch) systemUIDialog.requireViewById(R.id.bluetooth_auto_on_toggle)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$setupToggle$1
            public final /* synthetic */ BluetoothTileDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                switch (i) {
                    case 0:
                        StateFlowImpl stateFlowImpl = this.this$0.mutableBluetoothStateToggle;
                        Boolean valueOf = Boolean.valueOf(z);
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, valueOf);
                        compoundButton.setEnabled(false);
                        compoundButton.setAlpha(0.3f);
                        this.this$0.logger.logBluetoothState(BluetoothStateStage.USER_TOGGLED, String.valueOf(z));
                        this.this$0.uiEventLogger.log(BluetoothTileDialogUiEvent.BLUETOOTH_TOGGLE_CLICKED);
                        break;
                    default:
                        AuthContainerView$$ExternalSyntheticOutline0.m(z, this.this$0.mutableBluetoothAutoOnToggle, null);
                        this.this$0.uiEventLogger.log(BluetoothTileDialogUiEvent.BLUETOOTH_AUTO_ON_TOGGLE_CLICKED);
                        break;
                }
            }
        });
        RecyclerView recyclerView = (RecyclerView) systemUIDialog.requireViewById(R.id.device_list);
        systemUIDialog.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        recyclerView.setAdapter(this.deviceItemAdapter);
        ((TextView) systemUIDialog.requireViewById(R.id.bluetooth_tile_dialog_subtitle)).setText(context.getString(uiProperties.subTitleResId));
        final int i3 = 3;
        systemUIDialog.requireViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i3) {
                    case 0:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel = ((BluetoothTileDialogDelegate) systemUIDialog).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel.uiEventLogger.log(BluetoothTileDialogUiEvent.SEE_ALL_CLICKED);
                        bluetoothTileDialogViewModel.startSettingsActivity(new Intent("com.android.settings.PREVIOUSLY_CONNECTED_DEVICE"), view);
                        break;
                    case 1:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel2 = ((BluetoothTileDialogDelegate) systemUIDialog).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel2.uiEventLogger.log(BluetoothTileDialogUiEvent.PAIR_NEW_DEVICE_CLICKED);
                        bluetoothTileDialogViewModel2.startSettingsActivity(new Intent("android.settings.BLUETOOTH_PAIRING_SETTINGS"), view);
                        break;
                    case 2:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel3 = ((BluetoothTileDialogDelegate) systemUIDialog).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel3.uiEventLogger.log(BluetoothTileDialogUiEvent.BLUETOOTH_AUDIO_SHARING_BUTTON_CLICKED);
                        Intent intent = new Intent("com.android.settings.BLUETOOTH_AUDIO_SHARING_SETTINGS");
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("START_LE_AUDIO_SHARING", true);
                        intent.putExtra(":settings:show_fragment_args", bundle2);
                        bluetoothTileDialogViewModel3.startSettingsActivity(intent, view);
                        break;
                    default:
                        ((SystemUIDialog) systemUIDialog).dismiss();
                        break;
                }
            }
        });
        systemUIDialog.requireViewById(R.id.see_all_button).setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                    case 0:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel = ((BluetoothTileDialogDelegate) this).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel.uiEventLogger.log(BluetoothTileDialogUiEvent.SEE_ALL_CLICKED);
                        bluetoothTileDialogViewModel.startSettingsActivity(new Intent("com.android.settings.PREVIOUSLY_CONNECTED_DEVICE"), view);
                        break;
                    case 1:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel2 = ((BluetoothTileDialogDelegate) this).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel2.uiEventLogger.log(BluetoothTileDialogUiEvent.PAIR_NEW_DEVICE_CLICKED);
                        bluetoothTileDialogViewModel2.startSettingsActivity(new Intent("android.settings.BLUETOOTH_PAIRING_SETTINGS"), view);
                        break;
                    case 2:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel3 = ((BluetoothTileDialogDelegate) this).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel3.uiEventLogger.log(BluetoothTileDialogUiEvent.BLUETOOTH_AUDIO_SHARING_BUTTON_CLICKED);
                        Intent intent = new Intent("com.android.settings.BLUETOOTH_AUDIO_SHARING_SETTINGS");
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("START_LE_AUDIO_SHARING", true);
                        intent.putExtra(":settings:show_fragment_args", bundle2);
                        bluetoothTileDialogViewModel3.startSettingsActivity(intent, view);
                        break;
                    default:
                        ((SystemUIDialog) this).dismiss();
                        break;
                }
            }
        });
        systemUIDialog.requireViewById(R.id.pair_new_device_button).setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i) {
                    case 0:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel = ((BluetoothTileDialogDelegate) this).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel.uiEventLogger.log(BluetoothTileDialogUiEvent.SEE_ALL_CLICKED);
                        bluetoothTileDialogViewModel.startSettingsActivity(new Intent("com.android.settings.PREVIOUSLY_CONNECTED_DEVICE"), view);
                        break;
                    case 1:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel2 = ((BluetoothTileDialogDelegate) this).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel2.uiEventLogger.log(BluetoothTileDialogUiEvent.PAIR_NEW_DEVICE_CLICKED);
                        bluetoothTileDialogViewModel2.startSettingsActivity(new Intent("android.settings.BLUETOOTH_PAIRING_SETTINGS"), view);
                        break;
                    case 2:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel3 = ((BluetoothTileDialogDelegate) this).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel3.uiEventLogger.log(BluetoothTileDialogUiEvent.BLUETOOTH_AUDIO_SHARING_BUTTON_CLICKED);
                        Intent intent = new Intent("com.android.settings.BLUETOOTH_AUDIO_SHARING_SETTINGS");
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("START_LE_AUDIO_SHARING", true);
                        intent.putExtra(":settings:show_fragment_args", bundle2);
                        bluetoothTileDialogViewModel3.startSettingsActivity(intent, view);
                        break;
                    default:
                        ((SystemUIDialog) this).dismiss();
                        break;
                }
            }
        });
        Button button = (Button) systemUIDialog.requireViewById(R.id.audio_sharing_button);
        final int i4 = 2;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i4) {
                    case 0:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel = ((BluetoothTileDialogDelegate) this).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel.uiEventLogger.log(BluetoothTileDialogUiEvent.SEE_ALL_CLICKED);
                        bluetoothTileDialogViewModel.startSettingsActivity(new Intent("com.android.settings.PREVIOUSLY_CONNECTED_DEVICE"), view);
                        break;
                    case 1:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel2 = ((BluetoothTileDialogDelegate) this).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel2.uiEventLogger.log(BluetoothTileDialogUiEvent.PAIR_NEW_DEVICE_CLICKED);
                        bluetoothTileDialogViewModel2.startSettingsActivity(new Intent("android.settings.BLUETOOTH_PAIRING_SETTINGS"), view);
                        break;
                    case 2:
                        BluetoothTileDialogViewModel bluetoothTileDialogViewModel3 = ((BluetoothTileDialogDelegate) this).bluetoothTileDialogCallback;
                        Intrinsics.checkNotNull(view);
                        bluetoothTileDialogViewModel3.uiEventLogger.log(BluetoothTileDialogUiEvent.BLUETOOTH_AUDIO_SHARING_BUTTON_CLICKED);
                        Intent intent = new Intent("com.android.settings.BLUETOOTH_AUDIO_SHARING_SETTINGS");
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("START_LE_AUDIO_SHARING", true);
                        intent.putExtra(":settings:show_fragment_args", bundle2);
                        bluetoothTileDialogViewModel3.startSettingsActivity(intent, view);
                        break;
                    default:
                        ((SystemUIDialog) this).dismiss();
                        break;
                }
            }
        });
        button.setAccessibilityDelegate(new BluetoothTileDialogDelegate$onCreate$5$2(0, context));
        View requireViewById2 = systemUIDialog.requireViewById(R.id.scroll_view);
        requireViewById2.setMinimumHeight(requireViewById2.getResources().getDimensionPixelSize(uiProperties.scrollViewMinHeightResId));
        requireViewById2.getLayoutParams().height = Math.max(this.cachedContentHeight, requireViewById2.getMinimumHeight());
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onStart(Dialog dialog) {
        ((SystemClockImpl) this.systemClock).getClass();
        this.lastUiUpdateMs = android.os.SystemClock.elapsedRealtime();
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onStop(Dialog dialog) {
        this.mutableContentHeight.tryEmit(Integer.valueOf(((SystemUIDialog) dialog).requireViewById(R.id.scroll_view).getMeasuredHeight()));
    }
}

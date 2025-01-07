package com.android.systemui.media.dialog;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothLeAudioContentMetadata;
import android.bluetooth.BluetoothLeBroadcastAssistant;
import android.bluetooth.BluetoothLeBroadcastChannel;
import android.bluetooth.BluetoothLeBroadcastMetadata;
import android.bluetooth.BluetoothLeBroadcastReceiveState;
import android.bluetooth.BluetoothLeBroadcastSubgroup;
import android.content.DialogInterface;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.core.graphics.drawable.IconCompat;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcastAssistant;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcastMetadata;
import com.android.settingslib.qrcode.QrCodeGenerator;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import com.google.zxing.WriterException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Pair;
import kotlin.UInt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaOutputBroadcastDialog extends MediaOutputBaseDialog {
    static final int BROADCAST_CODE_MAX_LENGTH = 16;
    static final int BROADCAST_CODE_MIN_LENGTH = 4;
    static final int BROADCAST_NAME_MAX_LENGTH = 254;
    AlertDialog mAlertDialog;
    public AnonymousClass3 mBroadcastAssistantCallback;
    public TextView mBroadcastCode;
    public AnonymousClass1 mBroadcastCodeTextWatcher;
    public TextView mBroadcastErrorMessage;
    public TextView mBroadcastName;
    public AnonymousClass1 mBroadcastNameTextWatcher;
    public ImageView mBroadcastQrCodeView;
    public String mCurrentBroadcastCode;
    public String mCurrentBroadcastName;
    public boolean mIsLeBroadcastAssistantCallbackRegistered;
    public Boolean mIsPasswordHide;
    public boolean mIsStopbyUpdateBroadcastCode;
    public int mRetryCount;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.dialog.MediaOutputBroadcastDialog$3, reason: invalid class name */
    public final class AnonymousClass3 implements BluetoothLeBroadcastAssistant.Callback {
        public AnonymousClass3() {
        }

        public final void onReceiveStateChanged(BluetoothDevice bluetoothDevice, int i, BluetoothLeBroadcastReceiveState bluetoothLeBroadcastReceiveState) {
            Log.d("MediaOutputBroadcastDialog", "Assistant-onReceiveStateChanged:");
        }

        public final void onSearchStartFailed(int i) {
            ExifInterface$$ExternalSyntheticOutline0.m("Assistant-onSearchStartFailed: ", "MediaOutputBroadcastDialog", i);
        }

        public final void onSearchStarted(int i) {
            ExifInterface$$ExternalSyntheticOutline0.m("Assistant-onSearchStarted: ", "MediaOutputBroadcastDialog", i);
        }

        public final void onSearchStopFailed(int i) {
            ExifInterface$$ExternalSyntheticOutline0.m("Assistant-onSearchStopFailed: ", "MediaOutputBroadcastDialog", i);
        }

        public final void onSearchStopped(int i) {
            ExifInterface$$ExternalSyntheticOutline0.m("Assistant-onSearchStopped: ", "MediaOutputBroadcastDialog", i);
        }

        public final void onSourceAddFailed(BluetoothDevice bluetoothDevice, BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata, int i) {
            Log.d("MediaOutputBroadcastDialog", "Assistant-onSourceAddFailed: Device: " + bluetoothDevice);
        }

        public final void onSourceAdded(BluetoothDevice bluetoothDevice, int i, int i2) {
            Log.d("MediaOutputBroadcastDialog", "Assistant-onSourceAdded: Device: " + bluetoothDevice + ", sourceId: " + i);
            MediaOutputBroadcastDialog.this.mMainThreadHandler.post(new Runnable() { // from class: com.android.systemui.media.dialog.MediaOutputBroadcastDialog$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MediaOutputBroadcastDialog.this.refreshUi();
                }
            });
        }

        public final void onSourceFound(BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata) {
            Log.d("MediaOutputBroadcastDialog", "Assistant-onSourceFound:");
        }

        public final void onSourceModified(BluetoothDevice bluetoothDevice, int i, int i2) {
            Log.d("MediaOutputBroadcastDialog", "Assistant-onSourceModified:");
        }

        public final void onSourceModifyFailed(BluetoothDevice bluetoothDevice, int i, int i2) {
            Log.d("MediaOutputBroadcastDialog", "Assistant-onSourceModifyFailed:");
        }

        public final void onSourceRemoveFailed(BluetoothDevice bluetoothDevice, int i, int i2) {
            Log.d("MediaOutputBroadcastDialog", "Assistant-onSourceRemoveFailed:");
        }

        public final void onSourceRemoved(BluetoothDevice bluetoothDevice, int i, int i2) {
            Log.d("MediaOutputBroadcastDialog", "Assistant-onSourceRemoved:");
        }
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final IconCompat getAppSourceIcon() {
        return this.mMediaSwitchingController.getNotificationSmallIcon();
    }

    public final String getBroadcastMetadataInfo(int i) {
        if (i == 0) {
            LocalBluetoothLeBroadcast localBluetoothLeBroadcast = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
            if (localBluetoothLeBroadcast != null) {
                return localBluetoothLeBroadcast.mProgramInfo;
            }
            Log.d("MediaSwitchingController", "getBroadcastName: LE Audio Broadcast is null");
            return "";
        }
        if (i != 1) {
            return "";
        }
        LocalBluetoothLeBroadcast localBluetoothLeBroadcast2 = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
        if (localBluetoothLeBroadcast2 != null) {
            return new String(localBluetoothLeBroadcast2.mBroadcastCode, StandardCharsets.UTF_8);
        }
        Log.d("MediaSwitchingController", "getBroadcastCode: LE Audio Broadcast is null");
        return "";
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final IconCompat getHeaderIcon() {
        return this.mMediaSwitchingController.getHeaderIcon();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final int getHeaderIconSize() {
        return ((MediaOutputBaseDialog) this).mContext.getResources().getDimensionPixelSize(R.dimen.media_output_dialog_header_album_icon_size);
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final CharSequence getHeaderSubtitle() {
        MediaMetadata metadata;
        MediaController mediaController = this.mMediaSwitchingController.mMediaController;
        if (mediaController == null || (metadata = mediaController.getMetadata()) == null) {
            return null;
        }
        return metadata.getDescription().getSubtitle();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final CharSequence getHeaderText() {
        MediaMetadata metadata;
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        MediaController mediaController = mediaSwitchingController.mMediaController;
        return (mediaController == null || (metadata = mediaController.getMetadata()) == null) ? mediaSwitchingController.mContext.getText(R.string.controls_media_title) : metadata.getDescription().getTitle();
    }

    public int getRetryCount() {
        return this.mRetryCount;
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final int getStopButtonVisibility() {
        return 0;
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final void handleLeBroadcastMetadataChanged() {
        Log.d("MediaOutputBroadcastDialog", "handleLeBroadcastMetadataChanged:");
        refreshUi();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final void handleLeBroadcastStartFailed() {
        this.mMediaSwitchingController.setBroadcastCode(this.mCurrentBroadcastCode);
        this.mRetryCount++;
        handleUpdateFailedUi();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final void handleLeBroadcastStarted() {
        this.mRetryCount = 0;
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        refreshUi();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final void handleLeBroadcastStopFailed() {
        this.mMediaSwitchingController.setBroadcastCode(this.mCurrentBroadcastCode);
        this.mRetryCount++;
        handleUpdateFailedUi();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final void handleLeBroadcastStopped() {
        if (!this.mIsStopbyUpdateBroadcastCode) {
            dismiss();
            return;
        }
        this.mIsStopbyUpdateBroadcastCode = false;
        this.mRetryCount = 0;
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        LocalBluetoothLeBroadcast localBluetoothLeBroadcast = mediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
        if (localBluetoothLeBroadcast != null) {
            localBluetoothLeBroadcast.startBroadcast(mediaSwitchingController.getAppSourceName());
        } else {
            Log.d("MediaSwitchingController", "The broadcast profile is null");
            handleLeBroadcastStartFailed();
        }
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final void handleLeBroadcastUpdateFailed() {
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        String str = this.mCurrentBroadcastName;
        LocalBluetoothLeBroadcast localBluetoothLeBroadcast = mediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
        if (localBluetoothLeBroadcast == null) {
            Log.d("MediaSwitchingController", "setBroadcastName: LE Audio Broadcast is null");
        } else {
            localBluetoothLeBroadcast.setProgramInfo(str, true);
        }
        this.mRetryCount++;
        handleUpdateFailedUi();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final void handleLeBroadcastUpdated() {
        this.mRetryCount = 0;
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        refreshUi();
    }

    public final void handleUpdateFailedUi() {
        int i;
        boolean z;
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog == null) {
            Log.d("MediaOutputBroadcastDialog", "handleUpdateFailedUi: mAlertDialog is null");
            return;
        }
        if (this.mRetryCount < 3) {
            i = R.string.media_output_broadcast_update_error;
            z = true;
        } else {
            this.mRetryCount = 0;
            i = R.string.media_output_broadcast_last_update_error;
            z = false;
        }
        Button button = alertDialog.getButton(-1);
        if (button != null && z) {
            button.setEnabled(true);
        }
        TextView textView = this.mBroadcastErrorMessage;
        if (textView != null) {
            textView.setVisibility(0);
            this.mBroadcastErrorMessage.setText(i);
        }
    }

    public final void launchBroadcastUpdatedDialog(String str, final boolean z) {
        View inflate = LayoutInflater.from(((MediaOutputBaseDialog) this).mContext).inflate(R.layout.media_output_broadcast_update_dialog, (ViewGroup) null);
        final EditText editText = (EditText) inflate.requireViewById(R.id.broadcast_edit_text);
        editText.setText(str);
        editText.addTextChangedListener(z ? this.mBroadcastCodeTextWatcher : this.mBroadcastNameTextWatcher);
        this.mBroadcastErrorMessage = (TextView) inflate.requireViewById(R.id.broadcast_error_message);
        AlertDialog create = new AlertDialog.Builder(((MediaOutputBaseDialog) this).mContext).setTitle(z ? R.string.media_output_broadcast_code : R.string.media_output_broadcast_name).setView(inflate).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.media_output_broadcast_dialog_save, new DialogInterface.OnClickListener() { // from class: com.android.systemui.media.dialog.MediaOutputBroadcastDialog$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MediaOutputBroadcastDialog mediaOutputBroadcastDialog = MediaOutputBroadcastDialog.this;
                boolean z2 = z;
                EditText editText2 = editText;
                mediaOutputBroadcastDialog.getClass();
                mediaOutputBroadcastDialog.updateBroadcastInfo(z2, editText2.getText().toString());
            }
        }).create();
        this.mAlertDialog = create;
        create.getWindow().setType(2009);
        SystemUIDialog.setShowForAllUsers(this.mAlertDialog);
        SystemUIDialog.registerDismissListener(this.mAlertDialog, null);
        this.mAlertDialog.show();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog, com.android.systemui.statusbar.phone.SystemUIDialog, android.app.AlertDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((ViewStub) this.mDialogView.requireViewById(R.id.broadcast_qrcode)).inflate();
        this.mBroadcastQrCodeView = (ImageView) this.mDialogView.requireViewById(R.id.qrcode_view);
        final int i = 0;
        ((ImageView) this.mDialogView.requireViewById(R.id.broadcast_info)).setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.media.dialog.MediaOutputBroadcastDialog$$ExternalSyntheticLambda0
            public final /* synthetic */ MediaOutputBroadcastDialog f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i2 = i;
                MediaOutputBroadcastDialog mediaOutputBroadcastDialog = this.f$0;
                switch (i2) {
                    case 0:
                        MediaSwitchingController mediaSwitchingController = mediaOutputBroadcastDialog.mMediaSwitchingController;
                        mediaSwitchingController.getClass();
                        AlertDialog.Builder builder = new AlertDialog.Builder(mediaSwitchingController.mContext);
                        builder.setTitle(R.string.media_output_broadcast);
                        builder.setMessage(R.string.media_output_broadcasting_message);
                        builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
                        AlertDialog create = builder.create();
                        create.getWindow().setType(2009);
                        SystemUIDialog.setShowForAllUsers(create);
                        SystemUIDialog.registerDismissListener(create, null);
                        create.show();
                        break;
                    case 1:
                        mediaOutputBroadcastDialog.launchBroadcastUpdatedDialog(mediaOutputBroadcastDialog.mBroadcastName.getText().toString(), false);
                        break;
                    case 2:
                        mediaOutputBroadcastDialog.mBroadcastCode.setTransformationMethod(mediaOutputBroadcastDialog.mIsPasswordHide.booleanValue() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                        mediaOutputBroadcastDialog.mIsPasswordHide = Boolean.valueOf(true ^ mediaOutputBroadcastDialog.mIsPasswordHide.booleanValue());
                        break;
                    default:
                        mediaOutputBroadcastDialog.launchBroadcastUpdatedDialog(mediaOutputBroadcastDialog.mBroadcastCode.getText().toString(), true);
                        break;
                }
            }
        });
        this.mBroadcastName = (TextView) this.mDialogView.requireViewById(R.id.broadcast_name_summary);
        final int i2 = 1;
        ((ImageView) this.mDialogView.requireViewById(R.id.broadcast_name_edit)).setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.media.dialog.MediaOutputBroadcastDialog$$ExternalSyntheticLambda0
            public final /* synthetic */ MediaOutputBroadcastDialog f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i2;
                MediaOutputBroadcastDialog mediaOutputBroadcastDialog = this.f$0;
                switch (i22) {
                    case 0:
                        MediaSwitchingController mediaSwitchingController = mediaOutputBroadcastDialog.mMediaSwitchingController;
                        mediaSwitchingController.getClass();
                        AlertDialog.Builder builder = new AlertDialog.Builder(mediaSwitchingController.mContext);
                        builder.setTitle(R.string.media_output_broadcast);
                        builder.setMessage(R.string.media_output_broadcasting_message);
                        builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
                        AlertDialog create = builder.create();
                        create.getWindow().setType(2009);
                        SystemUIDialog.setShowForAllUsers(create);
                        SystemUIDialog.registerDismissListener(create, null);
                        create.show();
                        break;
                    case 1:
                        mediaOutputBroadcastDialog.launchBroadcastUpdatedDialog(mediaOutputBroadcastDialog.mBroadcastName.getText().toString(), false);
                        break;
                    case 2:
                        mediaOutputBroadcastDialog.mBroadcastCode.setTransformationMethod(mediaOutputBroadcastDialog.mIsPasswordHide.booleanValue() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                        mediaOutputBroadcastDialog.mIsPasswordHide = Boolean.valueOf(true ^ mediaOutputBroadcastDialog.mIsPasswordHide.booleanValue());
                        break;
                    default:
                        mediaOutputBroadcastDialog.launchBroadcastUpdatedDialog(mediaOutputBroadcastDialog.mBroadcastCode.getText().toString(), true);
                        break;
                }
            }
        });
        TextView textView = (TextView) this.mDialogView.requireViewById(R.id.broadcast_code_summary);
        this.mBroadcastCode = textView;
        textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
        final int i3 = 2;
        ((ImageView) this.mDialogView.requireViewById(R.id.broadcast_code_eye)).setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.media.dialog.MediaOutputBroadcastDialog$$ExternalSyntheticLambda0
            public final /* synthetic */ MediaOutputBroadcastDialog f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i3;
                MediaOutputBroadcastDialog mediaOutputBroadcastDialog = this.f$0;
                switch (i22) {
                    case 0:
                        MediaSwitchingController mediaSwitchingController = mediaOutputBroadcastDialog.mMediaSwitchingController;
                        mediaSwitchingController.getClass();
                        AlertDialog.Builder builder = new AlertDialog.Builder(mediaSwitchingController.mContext);
                        builder.setTitle(R.string.media_output_broadcast);
                        builder.setMessage(R.string.media_output_broadcasting_message);
                        builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
                        AlertDialog create = builder.create();
                        create.getWindow().setType(2009);
                        SystemUIDialog.setShowForAllUsers(create);
                        SystemUIDialog.registerDismissListener(create, null);
                        create.show();
                        break;
                    case 1:
                        mediaOutputBroadcastDialog.launchBroadcastUpdatedDialog(mediaOutputBroadcastDialog.mBroadcastName.getText().toString(), false);
                        break;
                    case 2:
                        mediaOutputBroadcastDialog.mBroadcastCode.setTransformationMethod(mediaOutputBroadcastDialog.mIsPasswordHide.booleanValue() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                        mediaOutputBroadcastDialog.mIsPasswordHide = Boolean.valueOf(true ^ mediaOutputBroadcastDialog.mIsPasswordHide.booleanValue());
                        break;
                    default:
                        mediaOutputBroadcastDialog.launchBroadcastUpdatedDialog(mediaOutputBroadcastDialog.mBroadcastCode.getText().toString(), true);
                        break;
                }
            }
        });
        final int i4 = 3;
        ((ImageView) this.mDialogView.requireViewById(R.id.broadcast_code_edit)).setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.media.dialog.MediaOutputBroadcastDialog$$ExternalSyntheticLambda0
            public final /* synthetic */ MediaOutputBroadcastDialog f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i22 = i4;
                MediaOutputBroadcastDialog mediaOutputBroadcastDialog = this.f$0;
                switch (i22) {
                    case 0:
                        MediaSwitchingController mediaSwitchingController = mediaOutputBroadcastDialog.mMediaSwitchingController;
                        mediaSwitchingController.getClass();
                        AlertDialog.Builder builder = new AlertDialog.Builder(mediaSwitchingController.mContext);
                        builder.setTitle(R.string.media_output_broadcast);
                        builder.setMessage(R.string.media_output_broadcasting_message);
                        builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
                        AlertDialog create = builder.create();
                        create.getWindow().setType(2009);
                        SystemUIDialog.setShowForAllUsers(create);
                        SystemUIDialog.registerDismissListener(create, null);
                        create.show();
                        break;
                    case 1:
                        mediaOutputBroadcastDialog.launchBroadcastUpdatedDialog(mediaOutputBroadcastDialog.mBroadcastName.getText().toString(), false);
                        break;
                    case 2:
                        mediaOutputBroadcastDialog.mBroadcastCode.setTransformationMethod(mediaOutputBroadcastDialog.mIsPasswordHide.booleanValue() ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                        mediaOutputBroadcastDialog.mIsPasswordHide = Boolean.valueOf(true ^ mediaOutputBroadcastDialog.mIsPasswordHide.booleanValue());
                        break;
                    default:
                        mediaOutputBroadcastDialog.launchBroadcastUpdatedDialog(mediaOutputBroadcastDialog.mBroadcastCode.getText().toString(), true);
                        break;
                }
            }
        });
        refreshUi();
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog
    public final void onStopButtonClick() {
        LocalBluetoothLeBroadcast localBluetoothLeBroadcast = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
        if (localBluetoothLeBroadcast == null) {
            Log.d("MediaSwitchingController", "The broadcast profile is null");
        } else {
            int i = localBluetoothLeBroadcast.mBroadcastId;
            if (localBluetoothLeBroadcast.mServiceBroadcast == null) {
                Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcast is null when stopping the broadcast.");
            } else {
                Log.d("LocalBluetoothLeBroadcast", "stopBroadcast()");
                localBluetoothLeBroadcast.mServiceBroadcast.stopBroadcast(i);
            }
        }
        dismiss();
    }

    public final void refreshUi() {
        LocalBluetoothLeBroadcastMetadata localBluetoothLeBroadcastMetadata;
        BluetoothLeAudioContentMetadata publicBroadcastMetadata;
        byte[] rawMetadata;
        LocalBluetoothLeBroadcast localBluetoothLeBroadcast = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
        String str = "";
        if (localBluetoothLeBroadcast == null) {
            Log.d("MediaSwitchingController", "getLocalBroadcastMetadataQrCodeString: LE Audio Broadcast is null");
        } else {
            BluetoothLeBroadcastMetadata latestBluetoothLeBroadcastMetadata = localBluetoothLeBroadcast.getLatestBluetoothLeBroadcastMetadata();
            if (latestBluetoothLeBroadcastMetadata == null) {
                Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcastMetadata is null.");
                localBluetoothLeBroadcastMetadata = null;
            } else {
                localBluetoothLeBroadcastMetadata = new LocalBluetoothLeBroadcastMetadata(latestBluetoothLeBroadcastMetadata);
            }
            if (localBluetoothLeBroadcastMetadata != null) {
                BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata = localBluetoothLeBroadcastMetadata.metadata;
                ArrayList<Pair> arrayList = new ArrayList();
                if (bluetoothLeBroadcastMetadata.getBroadcastName() == null) {
                    throw new IllegalArgumentException("Broadcast name is mandatory for QR code");
                }
                String broadcastName = bluetoothLeBroadcastMetadata.getBroadcastName();
                arrayList.add(new Pair("BN", Base64.encodeToString(broadcastName != null ? broadcastName.getBytes(Charsets.UTF_8) : null, 2)));
                arrayList.add(new Pair("AT", String.valueOf(bluetoothLeBroadcastMetadata.getSourceAddressType())));
                arrayList.add(new Pair("AD", StringsKt__StringsJVMKt.replace$default(bluetoothLeBroadcastMetadata.getSourceDevice().getAddress(), ":", "")));
                arrayList.add(new Pair("BI", String.format("%X", Arrays.copyOf(new Object[]{Long.valueOf(bluetoothLeBroadcastMetadata.getBroadcastId())}, 1))));
                if (bluetoothLeBroadcastMetadata.getBroadcastCode() != null) {
                    arrayList.add(new Pair("BC", Base64.encodeToString(bluetoothLeBroadcastMetadata.getBroadcastCode(), 2)));
                }
                if (bluetoothLeBroadcastMetadata.getPublicBroadcastMetadata() != null && ((publicBroadcastMetadata = bluetoothLeBroadcastMetadata.getPublicBroadcastMetadata()) == null || (rawMetadata = publicBroadcastMetadata.getRawMetadata()) == null || rawMetadata.length != 0)) {
                    BluetoothLeAudioContentMetadata publicBroadcastMetadata2 = bluetoothLeBroadcastMetadata.getPublicBroadcastMetadata();
                    arrayList.add(new Pair("MD", Base64.encodeToString(publicBroadcastMetadata2 != null ? publicBroadcastMetadata2.getRawMetadata() : null, 2)));
                }
                if ((bluetoothLeBroadcastMetadata.getAudioConfigQuality() & 1) != 0) {
                    arrayList.add(new Pair("SQ", "1"));
                }
                if ((bluetoothLeBroadcastMetadata.getAudioConfigQuality() & 2) != 0) {
                    arrayList.add(new Pair("HQ", "1"));
                }
                arrayList.add(new Pair("AS", String.format("%X", Arrays.copyOf(new Object[]{Long.valueOf(bluetoothLeBroadcastMetadata.getSourceAdvertisingSid())}, 1))));
                arrayList.add(new Pair("PI", String.format("%X", Arrays.copyOf(new Object[]{Long.valueOf(bluetoothLeBroadcastMetadata.getPaSyncInterval())}, 1))));
                arrayList.add(new Pair("NS", String.format("%X", Arrays.copyOf(new Object[]{Long.valueOf(bluetoothLeBroadcastMetadata.getSubgroups().size())}, 1))));
                for (BluetoothLeBroadcastSubgroup bluetoothLeBroadcastSubgroup : bluetoothLeBroadcastMetadata.getSubgroups()) {
                    int i = 0;
                    int i2 = 0;
                    for (BluetoothLeBroadcastChannel bluetoothLeBroadcastChannel : bluetoothLeBroadcastSubgroup.getChannels()) {
                        if (bluetoothLeBroadcastChannel.getChannelIndex() > 0) {
                            i2++;
                            if (bluetoothLeBroadcastChannel.isSelected()) {
                                i |= 1 << (bluetoothLeBroadcastChannel.getChannelIndex() - 1);
                            }
                        }
                    }
                    Pair pair = i == 0 ? new Pair(new UInt(-1), new UInt(i2)) : new Pair(new UInt(i), new UInt(i2));
                    int i3 = ((UInt) pair.component1()).data;
                    int i4 = ((UInt) pair.component2()).data;
                    arrayList.add(new Pair("BS", String.format("%X", Arrays.copyOf(new Object[]{Long.valueOf(i3 & 4294967295L)}, 1))));
                    if (Integer.compareUnsigned(i4, 0) > 0) {
                        arrayList.add(new Pair("NB", String.format("%X", Arrays.copyOf(new Object[]{Long.valueOf(i4 & 4294967295L)}, 1))));
                    }
                    if (bluetoothLeBroadcastSubgroup.getContentMetadata().getRawMetadata().length != 0) {
                        arrayList.add(new Pair("SM", Base64.encodeToString(bluetoothLeBroadcastSubgroup.getContentMetadata().getRawMetadata(), 2)));
                    }
                }
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
                for (Pair pair2 : arrayList) {
                    arrayList2.add(pair2.getFirst() + ":" + pair2.getSecond());
                }
                String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("BLUETOOTH:UUID:184F;", CollectionsKt.joinToString$default(arrayList2, ";", null, null, null, 62), ";;");
                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Generated QR string : ", m, "BtLeBroadcastMetadataExt");
                if (m != null) {
                    str = m;
                }
            }
        }
        if (!str.isEmpty()) {
            try {
                this.mBroadcastQrCodeView.setImageBitmap(QrCodeGenerator.encodeQrCode(getContext().getResources().getDimensionPixelSize(R.dimen.media_output_qrcode_size), str));
            } catch (WriterException e) {
                Log.e("MediaOutputBroadcastDialog", "Error generatirng QR code bitmap " + e);
            }
        }
        this.mCurrentBroadcastName = getBroadcastMetadataInfo(0);
        this.mCurrentBroadcastCode = getBroadcastMetadataInfo(1);
        this.mBroadcastName.setText(this.mCurrentBroadcastName);
        this.mBroadcastCode.setText(this.mCurrentBroadcastCode);
        refresh(false);
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog, com.android.systemui.statusbar.phone.SystemUIDialog
    public final void start() {
        BluetoothLeBroadcastMetadata latestBluetoothLeBroadcastMetadata;
        List allSources;
        boolean isEmpty;
        boolean z;
        super.start();
        if (!this.mIsLeBroadcastAssistantCallbackRegistered) {
            this.mIsLeBroadcastAssistantCallbackRegistered = true;
            MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
            Executor executor = this.mExecutor;
            AnonymousClass3 anonymousClass3 = this.mBroadcastAssistantCallback;
            LocalBluetoothLeBroadcastAssistant localBluetoothLeBroadcastAssistant = mediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcastAssistant;
            if (localBluetoothLeBroadcastAssistant == null) {
                Log.d("MediaSwitchingController", "registerLeBroadcastAssistantServiceCallback: The broadcast assistant profile is null");
            } else {
                Log.d("MediaSwitchingController", "Register LE broadcast assistant callback");
                localBluetoothLeBroadcastAssistant.registerServiceCallBack(executor, anonymousClass3);
            }
        }
        LocalBluetoothLeBroadcast localBluetoothLeBroadcast = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
        List<BluetoothDevice> list = null;
        if (localBluetoothLeBroadcast == null) {
            Log.d("MediaSwitchingController", "getBroadcastMetadata: LE Audio Broadcast is null");
            latestBluetoothLeBroadcastMetadata = null;
        } else {
            latestBluetoothLeBroadcastMetadata = localBluetoothLeBroadcast.getLatestBluetoothLeBroadcastMetadata();
        }
        if (latestBluetoothLeBroadcastMetadata == null) {
            Log.e("MediaOutputBroadcastDialog", "Error: There is no broadcastMetadata.");
            return;
        }
        LocalBluetoothLeBroadcastAssistant localBluetoothLeBroadcastAssistant2 = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcastAssistant;
        if (localBluetoothLeBroadcastAssistant2 == null) {
            Log.d("MediaSwitchingController", "getConnectedBroadcastSinkDevices: The broadcast assistant profile is null");
        } else {
            BluetoothLeBroadcastAssistant bluetoothLeBroadcastAssistant = localBluetoothLeBroadcastAssistant2.mService;
            list = bluetoothLeBroadcastAssistant == null ? new ArrayList(0) : bluetoothLeBroadcastAssistant.getDevicesMatchingConnectionStates(new int[]{2, 1, 3});
        }
        for (BluetoothDevice bluetoothDevice : list) {
            Log.d("MediaOutputBroadcastDialog", "The broadcastMetadata broadcastId: " + latestBluetoothLeBroadcastMetadata.getBroadcastId() + ", the device: " + bluetoothDevice.getAnonymizedAddress());
            LocalBluetoothLeBroadcastAssistant localBluetoothLeBroadcastAssistant3 = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcastAssistant;
            if (localBluetoothLeBroadcastAssistant3 == null) {
                Log.d("MediaSwitchingController", "isThereAnyBroadcastSourceIntoSinkDevice: The broadcast assistant profile is null");
                isEmpty = false;
            } else {
                Log.d("LocalBluetoothLeBroadcastAssistant", "getAllSources()");
                BluetoothLeBroadcastAssistant bluetoothLeBroadcastAssistant2 = localBluetoothLeBroadcastAssistant3.mService;
                if (bluetoothLeBroadcastAssistant2 == null) {
                    Log.d("LocalBluetoothLeBroadcastAssistant", "The BluetoothLeBroadcastAssistant is null");
                    allSources = new ArrayList();
                } else {
                    allSources = bluetoothLeBroadcastAssistant2.getAllSources(bluetoothDevice);
                }
                Log.d("MediaSwitchingController", "isThereAnyBroadcastSourceIntoSinkDevice: List size: " + allSources.size());
                isEmpty = allSources.isEmpty() ^ true;
            }
            if (isEmpty) {
                Log.d("MediaOutputBroadcastDialog", "The sink device has the broadcast source now.");
                return;
            }
            LocalBluetoothLeBroadcastAssistant localBluetoothLeBroadcastAssistant4 = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcastAssistant;
            if (localBluetoothLeBroadcastAssistant4 == null) {
                Log.d("MediaSwitchingController", "addSourceIntoSinkDeviceWithBluetoothLeAssistant: The broadcast assistant profile is null");
                z = false;
            } else {
                BluetoothLeBroadcastAssistant bluetoothLeBroadcastAssistant3 = localBluetoothLeBroadcastAssistant4.mService;
                if (bluetoothLeBroadcastAssistant3 == null) {
                    Log.d("LocalBluetoothLeBroadcastAssistant", "The BluetoothLeBroadcastAssistant is null");
                } else {
                    try {
                        bluetoothLeBroadcastAssistant3.addSource(bluetoothDevice, latestBluetoothLeBroadcastMetadata, false);
                    } catch (IllegalStateException e) {
                        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
                            throw e;
                        }
                        Log.d("LocalBluetoothLeBroadcastAssistant", "Catch addSource failure when bt is disabled: " + e);
                    }
                }
                z = true;
            }
            if (!z) {
                Log.e("MediaOutputBroadcastDialog", "Error: Source add failed");
            }
        }
    }

    @Override // com.android.systemui.media.dialog.MediaOutputBaseDialog, com.android.systemui.statusbar.phone.SystemUIDialog
    public final void stop() {
        super.stop();
        if (this.mIsLeBroadcastAssistantCallbackRegistered) {
            this.mIsLeBroadcastAssistantCallbackRegistered = false;
            MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
            AnonymousClass3 anonymousClass3 = this.mBroadcastAssistantCallback;
            LocalBluetoothLeBroadcastAssistant localBluetoothLeBroadcastAssistant = mediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcastAssistant;
            if (localBluetoothLeBroadcastAssistant == null) {
                Log.d("MediaSwitchingController", "unregisterLeBroadcastAssistantServiceCallback: The broadcast assistant profile is null");
                return;
            }
            Log.d("MediaSwitchingController", "Unregister LE broadcast assistant callback");
            localBluetoothLeBroadcastAssistant.mCachedCallbackExecutorMap.remove(anonymousClass3);
            BluetoothLeBroadcastAssistant bluetoothLeBroadcastAssistant = localBluetoothLeBroadcastAssistant.mService;
            if (bluetoothLeBroadcastAssistant == null) {
                Log.d("LocalBluetoothLeBroadcastAssistant", "unregisterServiceCallBack failed, the BluetoothLeBroadcastAssistant is null.");
                return;
            }
            try {
                bluetoothLeBroadcastAssistant.unregisterCallback(anonymousClass3);
            } catch (IllegalArgumentException e) {
                Log.w("LocalBluetoothLeBroadcastAssistant", "unregisterServiceCallBack failed. " + e.getMessage());
            }
        }
    }

    public void updateBroadcastInfo(boolean z, String str) {
        Button button = this.mAlertDialog.getButton(-1);
        if (button != null) {
            button.setEnabled(false);
        }
        if (z) {
            this.mIsStopbyUpdateBroadcastCode = true;
            this.mMediaSwitchingController.setBroadcastCode(str);
            LocalBluetoothLeBroadcast localBluetoothLeBroadcast = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
            if (localBluetoothLeBroadcast == null) {
                Log.d("MediaSwitchingController", "The broadcast profile is null");
                handleLeBroadcastStopFailed();
                return;
            }
            int i = localBluetoothLeBroadcast.mBroadcastId;
            if (localBluetoothLeBroadcast.mServiceBroadcast == null) {
                Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcast is null when stopping the broadcast.");
                return;
            } else {
                Log.d("LocalBluetoothLeBroadcast", "stopBroadcast()");
                localBluetoothLeBroadcast.mServiceBroadcast.stopBroadcast(i);
                return;
            }
        }
        LocalBluetoothLeBroadcast localBluetoothLeBroadcast2 = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
        if (localBluetoothLeBroadcast2 == null) {
            Log.d("MediaSwitchingController", "setBroadcastName: LE Audio Broadcast is null");
        } else {
            localBluetoothLeBroadcast2.setProgramInfo(str, true);
        }
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        LocalBluetoothLeBroadcast localBluetoothLeBroadcast3 = mediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
        if (localBluetoothLeBroadcast3 == null) {
            Log.d("MediaSwitchingController", "The broadcast profile is null");
            handleLeBroadcastUpdateFailed();
            return;
        }
        String appSourceName = mediaSwitchingController.getAppSourceName();
        if (localBluetoothLeBroadcast3.mServiceBroadcast == null) {
            Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcast is null when updating the broadcast.");
            return;
        }
        String str2 = localBluetoothLeBroadcast3.mProgramInfo;
        FragmentManagerViewModel$$ExternalSyntheticOutline0.m("updateBroadcast: language = null ,programInfo = ", str2, "LocalBluetoothLeBroadcast");
        localBluetoothLeBroadcast3.mNewAppSourceName = appSourceName;
        localBluetoothLeBroadcast3.mServiceBroadcast.updateBroadcast(localBluetoothLeBroadcast3.mBroadcastId, localBluetoothLeBroadcast3.mBuilder.setProgramInfo(str2).build());
    }
}

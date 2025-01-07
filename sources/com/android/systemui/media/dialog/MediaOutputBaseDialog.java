package com.android.systemui.media.dialog;

import android.app.Notification;
import android.app.WallpaperColors;
import android.bluetooth.BluetoothLeBroadcast;
import android.bluetooth.BluetoothLeBroadcastMetadata;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.INearbyMediaDevicesProvider;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.drawable.IconCompat;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.internal.graphics.ColorUtils;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.media.dialog.MediaOutputBroadcastDialog.AnonymousClass3;
import com.android.systemui.media.dialog.MediaSwitchingController;
import com.android.systemui.media.nearby.NearbyMediaDevicesManager;
import com.android.systemui.monet.ColorScheme;
import com.android.systemui.monet.Style;
import com.android.systemui.monet.TonalPalette;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MediaOutputBaseDialog extends SystemUIDialog implements MediaSwitchingController.Callback, Window.Callback {
    public MediaOutputAdapter mAdapter;
    public Button mAppButton;
    public ImageView mAppResourceIcon;
    public final AnonymousClass1 mBroadcastCallback;
    public ImageView mBroadcastIcon;
    public final BroadcastSender mBroadcastSender;
    public LinearLayout mCastAppLayout;
    public final Context mContext;
    public LinearLayout mDeviceListLayout;
    public final MediaOutputBaseDialog$$ExternalSyntheticLambda2 mDeviceListLayoutListener;
    public RecyclerView mDevicesRecyclerView;
    View mDialogView;
    public boolean mDismissing;
    public Button mDoneButton;
    public final Executor mExecutor;
    public ImageView mHeaderIcon;
    public TextView mHeaderSubtitle;
    public TextView mHeaderTitle;
    public final boolean mIncludePlaybackAndAppMetadata;
    public boolean mIsLeBroadcastCallbackRegistered;
    public final int mItemHeight;
    public final LayoutManagerWrapper mLayoutManager;
    public final int mListMaxHeight;
    public final int mListPaddingTop;
    public final Handler mMainThreadHandler;
    public LinearLayout mMediaMetadataSectionLayout;
    public final MediaSwitchingController mMediaSwitchingController;
    public boolean mShouldLaunchLeBroadcastDialog;
    public Button mStopButton;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LayoutManagerWrapper extends LinearLayoutManager {
        public LayoutManagerWrapper() {
            super(1);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public final void onLayoutCompleted(RecyclerView.State state) {
            super.onLayoutCompleted(state);
            MediaSwitchingController mediaSwitchingController = MediaOutputBaseDialog.this.mMediaSwitchingController;
            mediaSwitchingController.mIsRefreshing = false;
            if (mediaSwitchingController.mNeedRefresh) {
                mediaSwitchingController.buildMediaItems(mediaSwitchingController.mCachedMediaDevices);
                MediaOutputBaseDialog mediaOutputBaseDialog = (MediaOutputBaseDialog) mediaSwitchingController.mCallback;
                mediaOutputBaseDialog.mMainThreadHandler.post(new MediaOutputBaseDialog$$ExternalSyntheticLambda6(mediaOutputBaseDialog, 0));
                mediaSwitchingController.mNeedRefresh = false;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.media.dialog.MediaOutputBaseDialog$$ExternalSyntheticLambda2] */
    public MediaOutputBaseDialog(Context context, BroadcastSender broadcastSender, MediaSwitchingController mediaSwitchingController, boolean z) {
        super(context, R.style.Theme_SystemUI_Dialog_Media, true);
        this.mMainThreadHandler = new Handler(Looper.getMainLooper());
        this.mDeviceListLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.systemui.media.dialog.MediaOutputBaseDialog$$ExternalSyntheticLambda2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                MediaOutputBaseDialog mediaOutputBaseDialog = MediaOutputBaseDialog.this;
                ViewGroup.LayoutParams layoutParams = mediaOutputBaseDialog.mDeviceListLayout.getLayoutParams();
                int min = Math.min((mediaOutputBaseDialog.mAdapter.getItemCount() * mediaOutputBaseDialog.mItemHeight) + mediaOutputBaseDialog.mListPaddingTop, mediaOutputBaseDialog.mListMaxHeight);
                if (min != layoutParams.height) {
                    layoutParams.height = min;
                    mediaOutputBaseDialog.mDeviceListLayout.setLayoutParams(layoutParams);
                }
            }
        };
        this.mBroadcastCallback = new AnonymousClass1();
        Context context2 = getContext();
        this.mContext = context2;
        this.mBroadcastSender = broadcastSender;
        this.mMediaSwitchingController = mediaSwitchingController;
        this.mLayoutManager = new LayoutManagerWrapper();
        this.mListMaxHeight = context.getResources().getDimensionPixelSize(R.dimen.media_output_dialog_list_max_height);
        this.mItemHeight = context.getResources().getDimensionPixelSize(R.dimen.media_output_dialog_list_item_height);
        this.mListPaddingTop = context2.getResources().getDimensionPixelSize(R.dimen.media_output_dialog_list_padding_top);
        this.mExecutor = Executors.newSingleThreadExecutor();
        this.mIncludePlaybackAndAppMetadata = z;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        this.mDismissing = true;
        super.dismiss();
    }

    public abstract IconCompat getAppSourceIcon();

    public abstract IconCompat getHeaderIcon();

    public abstract int getHeaderIconSize();

    public abstract CharSequence getHeaderSubtitle();

    public abstract CharSequence getHeaderText();

    public CharSequence getStopButtonText() {
        return this.mContext.getText(R.string.keyboard_key_media_stop);
    }

    public abstract int getStopButtonVisibility();

    public void handleLeBroadcastMetadataChanged() {
        if (this.mShouldLaunchLeBroadcastDialog) {
            startLeBroadcastDialog();
            this.mShouldLaunchLeBroadcastDialog = false;
        }
        refresh();
    }

    public void handleLeBroadcastStartFailed() {
        this.mStopButton.setText(R.string.media_output_broadcast_start_failed);
        this.mStopButton.setEnabled(false);
        refresh();
    }

    public void handleLeBroadcastStarted() {
        this.mShouldLaunchLeBroadcastDialog = true;
    }

    public void handleLeBroadcastStopFailed() {
        refresh();
    }

    public void handleLeBroadcastStopped() {
        this.mShouldLaunchLeBroadcastDialog = false;
        refresh();
    }

    public void handleLeBroadcastUpdateFailed() {
        refresh();
    }

    public void handleLeBroadcastUpdated() {
        refresh();
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog, android.app.AlertDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        int i = 4;
        super.onCreate(bundle);
        this.mDialogView = LayoutInflater.from(this.mContext).inflate(R.layout.media_output_dialog, (ViewGroup) null);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.setFitInsetsTypes(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
        attributes.setFitInsetsSides(WindowInsets.Side.all());
        attributes.setFitInsetsIgnoringVisibility(true);
        window.setAttributes(attributes);
        window.setContentView(this.mDialogView);
        window.setTitle(this.mContext.getString(R.string.media_output_dialog_accessibility_title));
        window.setType(2017);
        this.mHeaderTitle = (TextView) this.mDialogView.requireViewById(R.id.header_title);
        this.mHeaderSubtitle = (TextView) this.mDialogView.requireViewById(R.id.header_subtitle);
        this.mHeaderIcon = (ImageView) this.mDialogView.requireViewById(R.id.header_icon);
        this.mDevicesRecyclerView = (RecyclerView) this.mDialogView.requireViewById(R.id.list_result);
        this.mMediaMetadataSectionLayout = (LinearLayout) this.mDialogView.requireViewById(R.id.media_metadata_section);
        this.mDeviceListLayout = (LinearLayout) this.mDialogView.requireViewById(R.id.device_list);
        this.mDoneButton = (Button) this.mDialogView.requireViewById(R.id.done);
        this.mStopButton = (Button) this.mDialogView.requireViewById(R.id.stop);
        this.mAppButton = (Button) this.mDialogView.requireViewById(R.id.launch_app_button);
        this.mAppResourceIcon = (ImageView) this.mDialogView.requireViewById(R.id.app_source_icon);
        this.mCastAppLayout = (LinearLayout) this.mDialogView.requireViewById(R.id.cast_app_section);
        this.mBroadcastIcon = (ImageView) this.mDialogView.requireViewById(R.id.broadcast_icon);
        this.mDeviceListLayout.getViewTreeObserver().addOnGlobalLayoutListener(this.mDeviceListLayoutListener);
        LayoutManagerWrapper layoutManagerWrapper = this.mLayoutManager;
        layoutManagerWrapper.mAutoMeasure = true;
        this.mDevicesRecyclerView.setLayoutManager(layoutManagerWrapper);
        this.mDevicesRecyclerView.setAdapter(this.mAdapter);
        this.mDevicesRecyclerView.getClass();
        this.mDoneButton.setOnClickListener(new MediaOutputBaseDialog$$ExternalSyntheticLambda0(2, this));
        this.mStopButton.setOnClickListener(new MediaOutputBaseDialog$$ExternalSyntheticLambda0(3, this));
        Button button = this.mAppButton;
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        Objects.requireNonNull(mediaSwitchingController);
        button.setOnClickListener(new MediaOutputBaseDialog$$ExternalSyntheticLambda0(i, mediaSwitchingController));
        LinearLayout linearLayout = this.mMediaMetadataSectionLayout;
        MediaSwitchingController mediaSwitchingController2 = this.mMediaSwitchingController;
        Objects.requireNonNull(mediaSwitchingController2);
        linearLayout.setOnClickListener(new MediaOutputBaseDialog$$ExternalSyntheticLambda0(i, mediaSwitchingController2));
        this.mDismissing = false;
    }

    public abstract void onStopButtonClick();

    public void refresh() {
        refresh(false);
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog
    public void start() {
        MediaController mediaController;
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        synchronized (mediaSwitchingController.mMediaDevicesLock) {
            ((CopyOnWriteArrayList) mediaSwitchingController.mCachedMediaDevices).clear();
            ((CopyOnWriteArrayList) mediaSwitchingController.mOutputMediaItemList).clear();
        }
        mediaSwitchingController.mNearbyDeviceInfoMap.clear();
        NearbyMediaDevicesManager nearbyMediaDevicesManager = mediaSwitchingController.mNearbyMediaDevicesManager;
        if (nearbyMediaDevicesManager != null) {
            Iterator it = nearbyMediaDevicesManager.providers.iterator();
            while (it.hasNext()) {
                ((INearbyMediaDevicesProvider) it.next()).registerNearbyDevicesCallback(mediaSwitchingController);
            }
            nearbyMediaDevicesManager.activeCallbacks.add(mediaSwitchingController);
        }
        if (!TextUtils.isEmpty(mediaSwitchingController.mPackageName)) {
            if (mediaSwitchingController.mToken != null) {
                mediaController = new MediaController(mediaSwitchingController.mContext, mediaSwitchingController.mToken);
            } else {
                Iterator it2 = ((NotifPipeline) mediaSwitchingController.mNotifCollection).getAllNotifs().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        NotificationEntry notificationEntry = (NotificationEntry) it2.next();
                        Notification notification = notificationEntry.mSbn.getNotification();
                        if (notification.isMediaNotification() && TextUtils.equals(notificationEntry.mSbn.getPackageName(), mediaSwitchingController.mPackageName)) {
                            r2 = new MediaController(mediaSwitchingController.mContext, (MediaSession.Token) notification.extras.getParcelable("android.mediaSession", MediaSession.Token.class));
                            break;
                        }
                    } else {
                        for (MediaController mediaController2 : mediaSwitchingController.mMediaSessionManager.getActiveSessionsForUser(null, ((UserTrackerImpl) mediaSwitchingController.mUserTracker).getUserHandle())) {
                            if (TextUtils.equals(mediaController2.getPackageName(), mediaSwitchingController.mPackageName)) {
                            }
                        }
                        mediaController = null;
                    }
                }
                mediaController = mediaController2;
            }
            mediaSwitchingController.mMediaController = mediaController;
            if (mediaController != null) {
                mediaController.unregisterCallback(mediaSwitchingController.mCb);
                if (mediaSwitchingController.mMediaController.getPlaybackState() != null) {
                    mediaSwitchingController.mCurrentState = mediaSwitchingController.mMediaController.getPlaybackState().getState();
                }
                mediaSwitchingController.mMediaController.registerCallback(mediaSwitchingController.mCb);
            }
        }
        if (mediaSwitchingController.mMediaController == null && MediaSwitchingController.DEBUG) {
            Log.d("MediaSwitchingController", "No media controller for " + mediaSwitchingController.mPackageName);
        }
        mediaSwitchingController.mCallback = this;
        mediaSwitchingController.mLocalMediaManager.registerCallback(mediaSwitchingController);
        mediaSwitchingController.mLocalMediaManager.mInfoMediaManager.startScanOnRouter();
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.media.dialog.MediaOutputBroadcastDialog$1] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.media.dialog.MediaOutputBroadcastDialog$1] */
    public final void startLeBroadcastDialog() {
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        BroadcastSender broadcastSender = this.mBroadcastSender;
        mediaSwitchingController.getClass();
        final MediaOutputBroadcastDialog mediaOutputBroadcastDialog = new MediaOutputBroadcastDialog(mediaSwitchingController.mContext, broadcastSender, new MediaSwitchingController(mediaSwitchingController.mContext, mediaSwitchingController.mPackageName, mediaSwitchingController.mUserHandle, mediaSwitchingController.mToken, mediaSwitchingController.mMediaSessionManager, mediaSwitchingController.mLocalBluetoothManager, mediaSwitchingController.mActivityStarter, mediaSwitchingController.mNotifCollection, mediaSwitchingController.mDialogTransitionAnimator, mediaSwitchingController.mNearbyMediaDevicesManager, mediaSwitchingController.mAudioManager, mediaSwitchingController.mPowerExemptionManager, mediaSwitchingController.mKeyGuardManager, mediaSwitchingController.mFeatureFlags, mediaSwitchingController.mVolumePanelGlobalStateInteractor, mediaSwitchingController.mUserTracker), true);
        mediaOutputBroadcastDialog.mIsPasswordHide = Boolean.TRUE;
        mediaOutputBroadcastDialog.mRetryCount = 0;
        mediaOutputBroadcastDialog.mIsStopbyUpdateBroadcastCode = false;
        final int i = 0;
        mediaOutputBroadcastDialog.mBroadcastCodeTextWatcher = new TextWatcher() { // from class: com.android.systemui.media.dialog.MediaOutputBroadcastDialog.1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ MediaOutputBroadcastDialog this$0;

            public /* synthetic */ AnonymousClass1(final MediaOutputBroadcastDialog mediaOutputBroadcastDialog2, final int i2) {
                r2 = i2;
                r1 = mediaOutputBroadcastDialog2;
            }

            @Override // android.text.TextWatcher
            public final void afterTextChanged(Editable editable) {
                switch (r2) {
                    case 0:
                        MediaOutputBroadcastDialog mediaOutputBroadcastDialog2 = r1;
                        if (mediaOutputBroadcastDialog2.mAlertDialog != null && mediaOutputBroadcastDialog2.mBroadcastErrorMessage != null) {
                            boolean z = editable.length() > 0 && editable.length() < 4;
                            boolean z2 = editable.length() > 16;
                            boolean z3 = z || z2;
                            if (z) {
                                r1.mBroadcastErrorMessage.setText(R.string.media_output_broadcast_code_hint_no_less_than_min);
                            } else if (z2) {
                                MediaOutputBroadcastDialog mediaOutputBroadcastDialog3 = r1;
                                mediaOutputBroadcastDialog3.mBroadcastErrorMessage.setText(((MediaOutputBaseDialog) mediaOutputBroadcastDialog3).mContext.getResources().getString(R.string.media_output_broadcast_edit_hint_no_more_than_max, 16));
                            }
                            r1.mBroadcastErrorMessage.setVisibility(z3 ? 0 : 4);
                            Button button = r1.mAlertDialog.getButton(-1);
                            if (button != null) {
                                button.setEnabled(!z3);
                                break;
                            }
                        }
                        break;
                    default:
                        MediaOutputBroadcastDialog mediaOutputBroadcastDialog4 = r1;
                        if (mediaOutputBroadcastDialog4.mAlertDialog != null && mediaOutputBroadcastDialog4.mBroadcastErrorMessage != null) {
                            boolean z4 = editable.length() > MediaOutputBroadcastDialog.BROADCAST_NAME_MAX_LENGTH;
                            boolean z5 = z4 || editable.length() == 0;
                            if (z4) {
                                MediaOutputBroadcastDialog mediaOutputBroadcastDialog5 = r1;
                                mediaOutputBroadcastDialog5.mBroadcastErrorMessage.setText(((MediaOutputBaseDialog) mediaOutputBroadcastDialog5).mContext.getResources().getString(R.string.media_output_broadcast_edit_hint_no_more_than_max, Integer.valueOf(MediaOutputBroadcastDialog.BROADCAST_NAME_MAX_LENGTH)));
                            }
                            r1.mBroadcastErrorMessage.setVisibility(z4 ? 0 : 4);
                            Button button2 = r1.mAlertDialog.getButton(-1);
                            if (button2 != null) {
                                button2.setEnabled(!z5);
                                break;
                            }
                        }
                        break;
                }
            }

            @Override // android.text.TextWatcher
            public final void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                int i5 = r2;
            }

            @Override // android.text.TextWatcher
            public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                int i5 = r2;
            }

            private final void beforeTextChanged$com$android$systemui$media$dialog$MediaOutputBroadcastDialog$1(int i2, int i3, int i4, CharSequence charSequence) {
            }

            private final void beforeTextChanged$com$android$systemui$media$dialog$MediaOutputBroadcastDialog$2(int i2, int i3, int i4, CharSequence charSequence) {
            }

            private final void onTextChanged$com$android$systemui$media$dialog$MediaOutputBroadcastDialog$1(int i2, int i3, int i4, CharSequence charSequence) {
            }

            private final void onTextChanged$com$android$systemui$media$dialog$MediaOutputBroadcastDialog$2(int i2, int i3, int i4, CharSequence charSequence) {
            }
        };
        final int i2 = 1;
        mediaOutputBroadcastDialog2.mBroadcastNameTextWatcher = new TextWatcher() { // from class: com.android.systemui.media.dialog.MediaOutputBroadcastDialog.1
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ MediaOutputBroadcastDialog this$0;

            public /* synthetic */ AnonymousClass1(final MediaOutputBroadcastDialog mediaOutputBroadcastDialog2, final int i22) {
                r2 = i22;
                r1 = mediaOutputBroadcastDialog2;
            }

            @Override // android.text.TextWatcher
            public final void afterTextChanged(Editable editable) {
                switch (r2) {
                    case 0:
                        MediaOutputBroadcastDialog mediaOutputBroadcastDialog2 = r1;
                        if (mediaOutputBroadcastDialog2.mAlertDialog != null && mediaOutputBroadcastDialog2.mBroadcastErrorMessage != null) {
                            boolean z = editable.length() > 0 && editable.length() < 4;
                            boolean z2 = editable.length() > 16;
                            boolean z3 = z || z2;
                            if (z) {
                                r1.mBroadcastErrorMessage.setText(R.string.media_output_broadcast_code_hint_no_less_than_min);
                            } else if (z2) {
                                MediaOutputBroadcastDialog mediaOutputBroadcastDialog3 = r1;
                                mediaOutputBroadcastDialog3.mBroadcastErrorMessage.setText(((MediaOutputBaseDialog) mediaOutputBroadcastDialog3).mContext.getResources().getString(R.string.media_output_broadcast_edit_hint_no_more_than_max, 16));
                            }
                            r1.mBroadcastErrorMessage.setVisibility(z3 ? 0 : 4);
                            Button button = r1.mAlertDialog.getButton(-1);
                            if (button != null) {
                                button.setEnabled(!z3);
                                break;
                            }
                        }
                        break;
                    default:
                        MediaOutputBroadcastDialog mediaOutputBroadcastDialog4 = r1;
                        if (mediaOutputBroadcastDialog4.mAlertDialog != null && mediaOutputBroadcastDialog4.mBroadcastErrorMessage != null) {
                            boolean z4 = editable.length() > MediaOutputBroadcastDialog.BROADCAST_NAME_MAX_LENGTH;
                            boolean z5 = z4 || editable.length() == 0;
                            if (z4) {
                                MediaOutputBroadcastDialog mediaOutputBroadcastDialog5 = r1;
                                mediaOutputBroadcastDialog5.mBroadcastErrorMessage.setText(((MediaOutputBaseDialog) mediaOutputBroadcastDialog5).mContext.getResources().getString(R.string.media_output_broadcast_edit_hint_no_more_than_max, Integer.valueOf(MediaOutputBroadcastDialog.BROADCAST_NAME_MAX_LENGTH)));
                            }
                            r1.mBroadcastErrorMessage.setVisibility(z4 ? 0 : 4);
                            Button button2 = r1.mAlertDialog.getButton(-1);
                            if (button2 != null) {
                                button2.setEnabled(!z5);
                                break;
                            }
                        }
                        break;
                }
            }

            @Override // android.text.TextWatcher
            public final void beforeTextChanged(CharSequence charSequence, int i22, int i3, int i4) {
                int i5 = r2;
            }

            @Override // android.text.TextWatcher
            public final void onTextChanged(CharSequence charSequence, int i22, int i3, int i4) {
                int i5 = r2;
            }

            private final void beforeTextChanged$com$android$systemui$media$dialog$MediaOutputBroadcastDialog$1(int i22, int i3, int i4, CharSequence charSequence) {
            }

            private final void beforeTextChanged$com$android$systemui$media$dialog$MediaOutputBroadcastDialog$2(int i22, int i3, int i4, CharSequence charSequence) {
            }

            private final void onTextChanged$com$android$systemui$media$dialog$MediaOutputBroadcastDialog$1(int i22, int i3, int i4, CharSequence charSequence) {
            }

            private final void onTextChanged$com$android$systemui$media$dialog$MediaOutputBroadcastDialog$2(int i22, int i3, int i4, CharSequence charSequence) {
            }
        };
        mediaOutputBroadcastDialog2.mBroadcastAssistantCallback = mediaOutputBroadcastDialog2.new AnonymousClass3();
        mediaOutputBroadcastDialog2.mAdapter = new MediaOutputAdapter(mediaOutputBroadcastDialog2.mMediaSwitchingController);
        mediaOutputBroadcastDialog2.show();
        refresh();
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog
    public void stop() {
        LocalBluetoothLeBroadcast localBluetoothLeBroadcast = this.mMediaSwitchingController.mLocalBluetoothManager.mProfileManager.mLeAudioBroadcast;
        if (localBluetoothLeBroadcast != null && this.mIsLeBroadcastCallbackRegistered) {
            AnonymousClass1 anonymousClass1 = this.mBroadcastCallback;
            if (localBluetoothLeBroadcast == null) {
                Log.d("MediaSwitchingController", "The broadcast profile is null");
            } else {
                Log.d("MediaSwitchingController", "Unregister LE broadcast callback");
                localBluetoothLeBroadcast.unregisterServiceCallBack(anonymousClass1);
            }
            this.mIsLeBroadcastCallbackRegistered = false;
        }
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        MediaController mediaController = mediaSwitchingController.mMediaController;
        if (mediaController != null) {
            mediaController.unregisterCallback(mediaSwitchingController.mCb);
        }
        mediaSwitchingController.mLocalMediaManager.unregisterCallback(mediaSwitchingController);
        mediaSwitchingController.mLocalMediaManager.mInfoMediaManager.stopScanOnRouter();
        synchronized (mediaSwitchingController.mMediaDevicesLock) {
            ((CopyOnWriteArrayList) mediaSwitchingController.mCachedMediaDevices).clear();
            ((CopyOnWriteArrayList) mediaSwitchingController.mOutputMediaItemList).clear();
        }
        NearbyMediaDevicesManager nearbyMediaDevicesManager = mediaSwitchingController.mNearbyMediaDevicesManager;
        if (nearbyMediaDevicesManager != null) {
            nearbyMediaDevicesManager.activeCallbacks.remove(mediaSwitchingController);
            Iterator it = nearbyMediaDevicesManager.providers.iterator();
            while (it.hasNext()) {
                ((INearbyMediaDevicesProvider) it.next()).unregisterNearbyDevicesCallback(mediaSwitchingController);
            }
        }
        mediaSwitchingController.mNearbyDeviceInfoMap.clear();
    }

    public final void updateButtonBackgroundColorFilter() {
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(this.mMediaSwitchingController.mColorButtonBackground, PorterDuff.Mode.SRC_IN);
        this.mDoneButton.getBackground().setColorFilter(porterDuffColorFilter);
        this.mStopButton.getBackground().setColorFilter(porterDuffColorFilter);
        this.mDoneButton.setTextColor(this.mMediaSwitchingController.mColorPositiveButtonText);
    }

    public final void updateDialogBackgroundColor() {
        this.mDialogView.getBackground().setTint(this.mMediaSwitchingController.mColorDialogBackground);
        this.mDeviceListLayout.setBackgroundColor(this.mMediaSwitchingController.mColorDialogBackground);
    }

    public final void refresh(boolean z) {
        boolean z2;
        int i = 0;
        int i2 = 1;
        if (this.mDismissing) {
            return;
        }
        MediaSwitchingController mediaSwitchingController = this.mMediaSwitchingController;
        if (mediaSwitchingController.mIsRefreshing) {
            return;
        }
        mediaSwitchingController.mIsRefreshing = true;
        IconCompat headerIcon = getHeaderIcon();
        IconCompat appSourceIcon = getAppSourceIcon();
        LinearLayout linearLayout = this.mCastAppLayout;
        this.mMediaSwitchingController.getClass();
        linearLayout.setVisibility(8);
        Drawable drawable = null;
        if (headerIcon != null) {
            Icon icon$1 = headerIcon.toIcon$1();
            if (icon$1.getType() == 1 || icon$1.getType() == 5) {
                boolean z3 = (this.mContext.getResources().getConfiguration().uiMode & 48) == 32;
                WallpaperColors fromBitmap = WallpaperColors.fromBitmap(icon$1.getBitmap());
                boolean equals = fromBitmap.equals(null);
                z2 = !equals;
                if (!equals) {
                    MediaSwitchingController mediaSwitchingController2 = this.mAdapter.mController;
                    mediaSwitchingController2.getClass();
                    ColorScheme colorScheme = new ColorScheme(fromBitmap, z3, Style.TONAL_SPOT);
                    TonalPalette tonalPalette = colorScheme.mNeutral1;
                    TonalPalette tonalPalette2 = colorScheme.mAccent2;
                    TonalPalette tonalPalette3 = colorScheme.mAccent1;
                    if (z3) {
                        mediaSwitchingController2.mColorItemContent = tonalPalette3.getS100();
                        mediaSwitchingController2.mColorSeekbarProgress = ((Integer) tonalPalette2.allShades.get(8)).intValue();
                        mediaSwitchingController2.mColorButtonBackground = ((Integer) tonalPalette3.allShades.get(5)).intValue();
                        mediaSwitchingController2.mColorItemBackground = colorScheme.mNeutral2.getS800();
                        mediaSwitchingController2.mColorConnectedItemBackground = tonalPalette2.getS800();
                        mediaSwitchingController2.mColorPositiveButtonText = tonalPalette2.getS800();
                        mediaSwitchingController2.mColorDialogBackground = ((Integer) tonalPalette.allShades.get(11)).intValue();
                    } else {
                        mediaSwitchingController2.mColorItemContent = tonalPalette3.getS800();
                        mediaSwitchingController2.mColorSeekbarProgress = ((Integer) tonalPalette3.allShades.get(5)).intValue();
                        mediaSwitchingController2.mColorButtonBackground = ((Integer) tonalPalette3.allShades.get(8)).intValue();
                        mediaSwitchingController2.mColorItemBackground = tonalPalette2.getS50();
                        mediaSwitchingController2.mColorConnectedItemBackground = tonalPalette3.getS100();
                        mediaSwitchingController2.mColorPositiveButtonText = tonalPalette.getS50();
                        mediaSwitchingController2.mColorDialogBackground = ColorUtils.setAlphaComponent(z3 ? tonalPalette.getS700() : ((Integer) tonalPalette.allShades.get(1)).intValue(), 255);
                    }
                    updateButtonBackgroundColorFilter();
                    updateDialogBackgroundColor();
                }
            } else {
                updateButtonBackgroundColorFilter();
                updateDialogBackgroundColor();
                z2 = false;
            }
            this.mHeaderIcon.setVisibility(0);
            this.mHeaderIcon.setImageIcon(icon$1);
        } else {
            updateButtonBackgroundColorFilter();
            updateDialogBackgroundColor();
            this.mHeaderIcon.setVisibility(8);
            z2 = false;
        }
        if (!this.mIncludePlaybackAndAppMetadata) {
            this.mAppResourceIcon.setVisibility(8);
        } else if (appSourceIcon != null) {
            Icon icon$12 = appSourceIcon.toIcon$1();
            this.mAppResourceIcon.setColorFilter(this.mMediaSwitchingController.mColorItemContent);
            this.mAppResourceIcon.setImageIcon(icon$12);
        } else {
            MediaSwitchingController mediaSwitchingController3 = this.mMediaSwitchingController;
            if (!TextUtils.isEmpty(mediaSwitchingController3.mPackageName)) {
                try {
                    Log.d("MediaSwitchingController", "try to get app icon");
                    drawable = mediaSwitchingController3.mContext.getPackageManager().getApplicationIcon(mediaSwitchingController3.mPackageName);
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.d("MediaSwitchingController", "icon not found");
                }
            }
            if (drawable != null) {
                this.mAppResourceIcon.setImageDrawable(drawable);
            } else {
                this.mAppResourceIcon.setVisibility(8);
            }
        }
        if (this.mHeaderIcon.getVisibility() == 0) {
            int headerIconSize = getHeaderIconSize();
            this.mHeaderIcon.setLayoutParams(new LinearLayout.LayoutParams(this.mContext.getResources().getDimensionPixelSize(R.dimen.media_output_dialog_header_icon_padding) + headerIconSize, headerIconSize));
        }
        this.mAppButton.setText(this.mMediaSwitchingController.getAppSourceName());
        if (this.mIncludePlaybackAndAppMetadata) {
            this.mHeaderTitle.setText(getHeaderText());
            CharSequence headerSubtitle = getHeaderSubtitle();
            if (TextUtils.isEmpty(headerSubtitle)) {
                this.mHeaderSubtitle.setVisibility(8);
                this.mHeaderTitle.setGravity(8388627);
            } else {
                this.mHeaderSubtitle.setVisibility(0);
                this.mHeaderSubtitle.setText(headerSubtitle);
                this.mHeaderTitle.setGravity(0);
            }
        } else {
            this.mHeaderTitle.setVisibility(8);
            this.mHeaderSubtitle.setVisibility(8);
        }
        this.mStopButton.setVisibility(getStopButtonVisibility());
        this.mStopButton.setEnabled(true);
        this.mStopButton.setText(getStopButtonText());
        this.mStopButton.setOnClickListener(new MediaOutputBaseDialog$$ExternalSyntheticLambda0(i, this));
        this.mBroadcastIcon.setVisibility(8);
        this.mBroadcastIcon.setOnClickListener(new MediaOutputBaseDialog$$ExternalSyntheticLambda0(i2, this));
        MediaOutputAdapter mediaOutputAdapter = this.mAdapter;
        if (mediaOutputAdapter.mIsDragging) {
            MediaSwitchingController mediaSwitchingController4 = this.mMediaSwitchingController;
            mediaSwitchingController4.mIsRefreshing = false;
            if (mediaSwitchingController4.mNeedRefresh) {
                mediaSwitchingController4.buildMediaItems(mediaSwitchingController4.mCachedMediaDevices);
                MediaOutputBaseDialog mediaOutputBaseDialog = (MediaOutputBaseDialog) mediaSwitchingController4.mCallback;
                mediaOutputBaseDialog.mMainThreadHandler.post(new MediaOutputBaseDialog$$ExternalSyntheticLambda6(mediaOutputBaseDialog, 0));
                mediaSwitchingController4.mNeedRefresh = false;
                return;
            }
            return;
        }
        int i3 = mediaOutputAdapter.mCurrentActivePosition;
        if (!z2 && !z && i3 >= 0 && i3 < mediaOutputAdapter.getItemCount()) {
            this.mAdapter.notifyItemChanged(i3);
            return;
        }
        MediaOutputAdapter mediaOutputAdapter2 = this.mAdapter;
        ((CopyOnWriteArrayList) mediaOutputAdapter2.mMediaItemList).clear();
        ((CopyOnWriteArrayList) mediaOutputAdapter2.mMediaItemList).addAll(mediaOutputAdapter2.mController.mOutputMediaItemList);
        mediaOutputAdapter2.notifyDataSetChanged();
    }

    public void onBroadcastIconClick() {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.dialog.MediaOutputBaseDialog$1, reason: invalid class name */
    public final class AnonymousClass1 implements BluetoothLeBroadcast.Callback {
        public AnonymousClass1() {
        }

        public final void onBroadcastMetadataChanged(int i, BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata) {
            Log.d("MediaOutputDialog", "onBroadcastMetadataChanged(), broadcastId = " + i + ", metadata = " + bluetoothLeBroadcastMetadata);
            MediaOutputBaseDialog.this.mMainThreadHandler.post(new MediaOutputBaseDialog$1$$ExternalSyntheticLambda0(this, 5));
        }

        public final void onBroadcastStartFailed(int i) {
            ExifInterface$$ExternalSyntheticOutline0.m("onBroadcastStartFailed(), reason = ", "MediaOutputDialog", i);
            MediaOutputBaseDialog.this.mMainThreadHandler.postDelayed(new MediaOutputBaseDialog$1$$ExternalSyntheticLambda0(this, 4), 3000L);
        }

        public final void onBroadcastStarted(int i, int i2) {
            Log.d("MediaOutputDialog", "onBroadcastStarted(), reason = " + i + ", broadcastId = " + i2);
            MediaOutputBaseDialog.this.mMainThreadHandler.post(new MediaOutputBaseDialog$1$$ExternalSyntheticLambda0(this, 1));
        }

        public final void onBroadcastStopFailed(int i) {
            ExifInterface$$ExternalSyntheticOutline0.m("onBroadcastStopFailed(), reason = ", "MediaOutputDialog", i);
            MediaOutputBaseDialog.this.mMainThreadHandler.post(new MediaOutputBaseDialog$1$$ExternalSyntheticLambda0(this, 6));
        }

        public final void onBroadcastStopped(int i, int i2) {
            Log.d("MediaOutputDialog", "onBroadcastStopped(), reason = " + i + ", broadcastId = " + i2);
            MediaOutputBaseDialog.this.mMainThreadHandler.post(new MediaOutputBaseDialog$1$$ExternalSyntheticLambda0(this, 2));
        }

        public final void onBroadcastUpdateFailed(int i, int i2) {
            Log.d("MediaOutputDialog", "onBroadcastUpdateFailed(), reason = " + i + ", broadcastId = " + i2);
            MediaOutputBaseDialog.this.mMainThreadHandler.post(new MediaOutputBaseDialog$1$$ExternalSyntheticLambda0(this, 3));
        }

        public final void onBroadcastUpdated(int i, int i2) {
            Log.d("MediaOutputDialog", "onBroadcastUpdated(), reason = " + i + ", broadcastId = " + i2);
            MediaOutputBaseDialog.this.mMainThreadHandler.post(new MediaOutputBaseDialog$1$$ExternalSyntheticLambda0(this, 0));
        }

        public final void onPlaybackStarted(int i, int i2) {
        }

        public final void onPlaybackStopped(int i, int i2) {
        }
    }
}

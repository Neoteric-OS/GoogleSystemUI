package com.android.systemui.statusbar.policy;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Intent;
import android.content.pm.ShortcutManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.view.ContentInfo;
import android.view.View;
import androidx.core.app.NotificationCompat$Builder$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.RemoteInputView;
import com.android.wm.shell.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RemoteInputViewControllerImpl {
    public final NotificationRemoteInputManager.BouncerChecker bouncerChecker;
    public final NotificationEntry entry;
    public boolean isBound;
    public PendingIntent pendingIntent;
    public RemoteInput remoteInput;
    public final RemoteInputController remoteInputController;
    public final RemoteInputQuickSettingsDisabler remoteInputQuickSettingsDisabler;
    public RemoteInput[] remoteInputs;
    public final ShortcutManager shortcutManager;
    public final UiEventLogger uiEventLogger;
    public final RemoteInputView view;
    public final ArraySet onSendListeners = new ArraySet();
    public final RemoteInputViewControllerImpl$onFocusChangeListener$1 onFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.android.systemui.statusbar.policy.RemoteInputViewControllerImpl$onFocusChangeListener$1
        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z) {
            RemoteInputQuickSettingsDisabler remoteInputQuickSettingsDisabler = RemoteInputViewControllerImpl.this.remoteInputQuickSettingsDisabler;
            if (remoteInputQuickSettingsDisabler.remoteInputActive != z) {
                remoteInputQuickSettingsDisabler.remoteInputActive = z;
                remoteInputQuickSettingsDisabler.commandQueue.recomputeDisableFlags(remoteInputQuickSettingsDisabler.context.getDisplayId(), true);
            }
        }
    };
    public final RemoteInputViewControllerImpl$onSendRemoteInputListener$1 onSendRemoteInputListener = new Runnable() { // from class: com.android.systemui.statusbar.policy.RemoteInputViewControllerImpl$onSendRemoteInputListener$1
        @Override // java.lang.Runnable
        public final void run() {
            Intent addFlags;
            RemoteInputViewControllerImpl remoteInputViewControllerImpl = RemoteInputViewControllerImpl.this;
            RemoteInput remoteInput = remoteInputViewControllerImpl.remoteInput;
            if (remoteInput == null) {
                Log.e("RemoteInput", "cannot send remote input, RemoteInput data is null");
                return;
            }
            PendingIntent pendingIntent = remoteInputViewControllerImpl.pendingIntent;
            if (pendingIntent == null) {
                Log.e("RemoteInput", "cannot send remote input, PendingIntent is null");
                return;
            }
            NotificationEntry notificationEntry = remoteInputViewControllerImpl.entry;
            ContentInfo contentInfo = notificationEntry.remoteInputAttachment;
            RemoteInputView remoteInputView = remoteInputViewControllerImpl.view;
            if (contentInfo == null) {
                Bundle bundle = new Bundle();
                bundle.putString(remoteInput.getResultKey(), remoteInputView.mEditText.getText().toString());
                Intent addFlags2 = new Intent().addFlags(268435456);
                RemoteInput.addResultsToIntent(remoteInputViewControllerImpl.remoteInputs, addFlags2, bundle);
                notificationEntry.remoteInputText = remoteInputView.mEditText.getText();
                remoteInputView.setAttachment(null);
                notificationEntry.remoteInputUri = null;
                notificationEntry.remoteInputMimeType = null;
                RemoteInput.setResultsSource(addFlags2, notificationEntry.editedSuggestionInfo != null ? 1 : 0);
                addFlags = addFlags2;
            } else {
                String str = notificationEntry.remoteInputMimeType;
                Uri uri = notificationEntry.remoteInputUri;
                HashMap hashMap = new HashMap();
                hashMap.put(str, uri);
                StatusBarNotification statusBarNotification = notificationEntry.mSbn;
                RemoteInputUriController remoteInputUriController = remoteInputViewControllerImpl.remoteInputController.mRemoteInputUriController;
                remoteInputUriController.getClass();
                try {
                    remoteInputUriController.mStatusBarManagerService.grantInlineReplyUriPermission(statusBarNotification.getKey(), uri, statusBarNotification.getUser(), statusBarNotification.getPackageName());
                } catch (Exception e) {
                    Log.e("RemoteInputUriController", "Failed to grant URI permissions:" + e.getMessage(), e);
                }
                addFlags = new Intent().addFlags(268435456);
                RemoteInput.addDataResultToIntent(remoteInput, addFlags, hashMap);
                Bundle bundle2 = new Bundle();
                bundle2.putString(remoteInput.getResultKey(), remoteInputView.mEditText.getText().toString());
                RemoteInput.addResultsToIntent(remoteInputViewControllerImpl.remoteInputs, addFlags, bundle2);
                CharSequence label = notificationEntry.remoteInputAttachment.getClip().getDescription().getLabel();
                if (TextUtils.isEmpty(label)) {
                    label = remoteInputView.getResources().getString(R.string.remote_input_image_insertion_text);
                }
                if (!TextUtils.isEmpty(remoteInputView.mEditText.getText())) {
                    label = "\"" + ((Object) label) + "\" " + ((Object) remoteInputView.mEditText.getText());
                }
                notificationEntry.remoteInputText = label;
                RemoteInput.setResultsSource(addFlags, notificationEntry.editedSuggestionInfo != null ? 1 : 0);
            }
            RemoteInputViewControllerImpl remoteInputViewControllerImpl2 = RemoteInputViewControllerImpl.this;
            NotificationRemoteInputManager.BouncerChecker bouncerChecker = remoteInputViewControllerImpl2.bouncerChecker;
            RemoteInputView remoteInputView2 = remoteInputViewControllerImpl2.view;
            if (bouncerChecker != null) {
                bouncerChecker.showBouncerIfNecessary();
                throw null;
            }
            remoteInputView2.mEditText.setEnabled(false);
            remoteInputView2.mSending = true;
            remoteInputView2.mSendButton.setVisibility(4);
            remoteInputView2.mProgressBar.setVisibility(0);
            remoteInputView2.mEditText.mShowImeOnInputConnection = false;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            NotificationEntry notificationEntry2 = remoteInputViewControllerImpl2.entry;
            notificationEntry2.lastRemoteInputSent = elapsedRealtime;
            notificationEntry2.mRemoteEditImeAnimatingAway = true;
            Object obj = remoteInputView2.mToken;
            RemoteInputController remoteInputController = remoteInputViewControllerImpl2.remoteInputController;
            remoteInputController.getClass();
            String str2 = notificationEntry2.mKey;
            Objects.requireNonNull(str2);
            Objects.requireNonNull(obj);
            remoteInputController.mSpinning.put(str2, obj);
            remoteInputController.removeRemoteInput(notificationEntry2, remoteInputView2.mToken, "RemoteInputViewController#sendRemoteInput");
            int size = remoteInputController.mCallbacks.size();
            for (int i = 0; i < size; i++) {
                ((RemoteInputController.Callback) remoteInputController.mCallbacks.get(i)).onRemoteInputSent(notificationEntry2);
            }
            notificationEntry2.hasSentReply = true;
            Iterator it = CollectionsKt.toList(remoteInputViewControllerImpl2.onSendListeners).iterator();
            if (it.hasNext()) {
                throw NotificationCompat$Builder$$ExternalSyntheticOutline0.m(it);
            }
            remoteInputViewControllerImpl2.shortcutManager.onApplicationActive(notificationEntry2.mSbn.getPackageName(), notificationEntry2.mSbn.getUser().getIdentifier());
            remoteInputViewControllerImpl2.uiEventLogger.logWithInstanceId(RemoteInputView.NotificationRemoteInputEvent.NOTIFICATION_REMOTE_INPUT_SEND, notificationEntry2.mSbn.getUid(), notificationEntry2.mSbn.getPackageName(), notificationEntry2.mSbn.getInstanceId());
            try {
                ActivityOptions makeBasic = ActivityOptions.makeBasic();
                makeBasic.setPendingIntentBackgroundActivityStartMode(1);
                pendingIntent.send(remoteInputView2.getContext(), 0, addFlags, null, null, null, makeBasic.toBundle());
            } catch (PendingIntent.CanceledException e2) {
                Log.i("RemoteInput", "Unable to send remote input result", e2);
                remoteInputViewControllerImpl2.uiEventLogger.logWithInstanceId(RemoteInputView.NotificationRemoteInputEvent.NOTIFICATION_REMOTE_INPUT_FAILURE, notificationEntry2.mSbn.getUid(), notificationEntry2.mSbn.getPackageName(), notificationEntry2.mSbn.getInstanceId());
            }
            remoteInputView2.setAttachment(null);
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.policy.RemoteInputViewControllerImpl$onFocusChangeListener$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.statusbar.policy.RemoteInputViewControllerImpl$onSendRemoteInputListener$1] */
    public RemoteInputViewControllerImpl(RemoteInputView remoteInputView, NotificationEntry notificationEntry, RemoteInputQuickSettingsDisabler remoteInputQuickSettingsDisabler, RemoteInputController remoteInputController, ShortcutManager shortcutManager, UiEventLogger uiEventLogger) {
        this.view = remoteInputView;
        this.entry = notificationEntry;
        this.remoteInputQuickSettingsDisabler = remoteInputQuickSettingsDisabler;
        this.remoteInputController = remoteInputController;
        this.shortcutManager = shortcutManager;
        this.uiEventLogger = uiEventLogger;
    }

    public final void bind() {
        if (this.isBound) {
            return;
        }
        this.isBound = true;
        RemoteInput remoteInput = this.remoteInput;
        RemoteInputView remoteInputView = this.view;
        if (remoteInput != null) {
            remoteInputView.mEditText.setHint(remoteInput.getLabel());
            remoteInputView.setSupportedMimeTypes(remoteInput.getAllowedDataTypes());
        }
        remoteInputView.mEditTextFocusChangeListeners.add(this.onFocusChangeListener);
        remoteInputView.mOnSendListeners.add(this.onSendRemoteInputListener);
    }

    public final void setRemoteInput(RemoteInput remoteInput) {
        this.remoteInput = remoteInput;
        if (remoteInput != null) {
            if (!this.isBound) {
                remoteInput = null;
            }
            if (remoteInput != null) {
                CharSequence label = remoteInput.getLabel();
                RemoteInputView remoteInputView = this.view;
                remoteInputView.mEditText.setHint(label);
                remoteInputView.setSupportedMimeTypes(remoteInput.getAllowedDataTypes());
            }
        }
    }

    public final void unbind() {
        if (this.isBound) {
            this.isBound = false;
            RemoteInputView remoteInputView = this.view;
            remoteInputView.mEditTextFocusChangeListeners.remove(this.onFocusChangeListener);
            remoteInputView.mOnSendListeners.remove(this.onSendRemoteInputListener);
        }
    }
}

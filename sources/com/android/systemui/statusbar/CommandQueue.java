package com.android.systemui.statusbar;

import android.R;
import android.app.ITransientNotificationCallback;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.hardware.biometrics.IBiometricContextListener;
import android.hardware.biometrics.IBiometricSysuiReceiver;
import android.hardware.biometrics.PromptInfo;
import android.hardware.fingerprint.IUdfpsRefreshRateRequestCallback;
import android.media.INearbyMediaDevicesProvider;
import android.media.MediaRoute2Info;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Pair;
import android.util.SparseArray;
import android.view.KeyEvent;
import com.android.internal.os.SomeArgs;
import com.android.internal.statusbar.IAddTileResultCallback;
import com.android.internal.statusbar.IStatusBar;
import com.android.internal.statusbar.IUndoMediaTransferCallback;
import com.android.internal.statusbar.LetterboxDetails;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.util.GcUtils;
import com.android.internal.view.AppearanceRegion;
import com.android.systemui.camera.CameraGestureHelper;
import com.android.systemui.dump.DumpHandler;
import com.android.systemui.power.data.repository.PowerRepositoryImpl;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.DisplayTrackerImpl;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.android.systemui.statusbar.policy.CallbackController;
import dagger.Lazy;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class CommandQueue extends IStatusBar.Stub implements CallbackController {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ArrayList mCallbacks;
    public final Context mContext;
    public final SparseArray mDisplayDisabled;
    public final DisplayTracker mDisplayTracker;
    public final DisplayTracker.Callback mDisplayTrackerCallback;
    public final DumpHandler mDumpHandler;
    public final H mHandler;
    public int mLastUpdatedImeDisplayId;
    public final Object mLock;
    public final Lazy mPowerInteractor;
    public final CommandRegistry mRegistry;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.CommandQueue$2, reason: invalid class name */
    public final class AnonymousClass2 extends Thread {
        public final /* synthetic */ int $r8$classId = 1;
        public final /* synthetic */ String[] val$args;
        public final /* synthetic */ ParcelFileDescriptor val$pfd;
        public final /* synthetic */ Object val$pw;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(PrintWriter printWriter, String[] strArr, ParcelFileDescriptor parcelFileDescriptor) {
            super("Sysui.passThroughShellCommand");
            this.val$pw = printWriter;
            this.val$args = strArr;
            this.val$pfd = parcelFileDescriptor;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    try {
                        CommandRegistry commandRegistry = CommandQueue.this.mRegistry;
                        if (commandRegistry != null) {
                            commandRegistry.onShellCommand((PrintWriter) this.val$pw, this.val$args);
                        }
                        try {
                            return;
                        } catch (Exception unused) {
                            return;
                        }
                    } finally {
                        ((PrintWriter) this.val$pw).flush();
                        try {
                            this.val$pfd.close();
                        } catch (Exception unused2) {
                        }
                    }
                default:
                    try {
                        if (CommandQueue.this.mDumpHandler != null) {
                            CommandQueue.this.mDumpHandler.dump((FileDescriptor) this.val$pw, new PrintWriter(new CommandQueue$3$1()), this.val$args);
                        }
                        try {
                            this.val$pfd.close();
                            return;
                        } catch (Exception unused3) {
                            return;
                        }
                    } finally {
                        try {
                            this.val$pfd.close();
                        } catch (Exception unused4) {
                        }
                    }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(FileDescriptor fileDescriptor, String[] strArr, ParcelFileDescriptor parcelFileDescriptor) {
            super("Sysui.dumpProto");
            this.val$pw = fileDescriptor;
            this.val$args = strArr;
            this.val$pfd = parcelFileDescriptor;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class H extends Handler {
        public H(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i;
            int i2 = message.what & (-65536);
            int i3 = 0;
            CommandQueue commandQueue = CommandQueue.this;
            switch (i2) {
                case 65536:
                    int i4 = message.arg1;
                    if (i4 == 1) {
                        Pair pair = (Pair) message.obj;
                        while (i3 < commandQueue.mCallbacks.size()) {
                            ((Callbacks) commandQueue.mCallbacks.get(i3)).setIcon((String) pair.first, (StatusBarIcon) pair.second);
                            i3++;
                        }
                        break;
                    } else if (i4 == 2) {
                        while (i3 < commandQueue.mCallbacks.size()) {
                            ((Callbacks) commandQueue.mCallbacks.get(i3)).removeIcon((String) message.obj);
                            i3++;
                        }
                        break;
                    }
                    break;
                case 131072:
                    SomeArgs someArgs = (SomeArgs) message.obj;
                    for (int i5 = 0; i5 < commandQueue.mCallbacks.size(); i5++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i5)).disable(someArgs.argi1, someArgs.argi2, someArgs.argi3, someArgs.argi4 != 0);
                    }
                    break;
                case 196608:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).animateExpandNotificationsPanel();
                        i3++;
                    }
                    break;
                case 262144:
                    for (int i6 = 0; i6 < commandQueue.mCallbacks.size(); i6++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i6)).animateCollapsePanels(message.arg1, message.arg2 != 0);
                    }
                    break;
                case 327680:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).animateExpandSettingsPanel((String) message.obj);
                        i3++;
                    }
                    break;
                case 393216:
                    SomeArgs someArgs2 = (SomeArgs) message.obj;
                    for (int i7 = 0; i7 < commandQueue.mCallbacks.size(); i7++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i7)).onSystemBarAttributesChanged(someArgs2.argi1, someArgs2.argi2, (AppearanceRegion[]) someArgs2.arg1, someArgs2.argi3 == 1, someArgs2.argi4, someArgs2.argi5, (String) someArgs2.arg3, (LetterboxDetails[]) someArgs2.arg4);
                    }
                    someArgs2.recycle();
                    break;
                case 458752:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).onDisplayReady(message.arg1);
                        i3++;
                    }
                    break;
                case 524288:
                    SomeArgs someArgs3 = (SomeArgs) message.obj;
                    int i8 = someArgs3.argi1;
                    int i9 = someArgs3.argi2;
                    int i10 = someArgs3.argi3;
                    boolean z = someArgs3.argi4 != 0;
                    int i11 = CommandQueue.$r8$clinit;
                    commandQueue.getClass();
                    if (i8 != -1) {
                        if ((!UserManager.isVisibleBackgroundUsersEnabled() || !commandQueue.mContext.getResources().getBoolean(R.bool.config_perDisplayFocusEnabled)) && (i = commandQueue.mLastUpdatedImeDisplayId) != i8 && i != -1) {
                            for (int i12 = 0; i12 < commandQueue.mCallbacks.size(); i12++) {
                                ((Callbacks) commandQueue.mCallbacks.get(i12)).setImeWindowStatus(commandQueue.mLastUpdatedImeDisplayId, 0, 0, false);
                            }
                        }
                        while (i3 < commandQueue.mCallbacks.size()) {
                            ((Callbacks) commandQueue.mCallbacks.get(i3)).setImeWindowStatus(i8, i9, i10, z);
                            i3++;
                        }
                        commandQueue.mLastUpdatedImeDisplayId = i8;
                        break;
                    }
                    break;
                case 589824:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).toggleRecentApps();
                        i3++;
                    }
                    break;
                case 655360:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).preloadRecentApps();
                        i3++;
                    }
                    break;
                case 720896:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).cancelPreloadRecentApps();
                        i3++;
                    }
                    break;
                case 786432:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).setWindowState(message.arg1, message.arg2, ((Integer) message.obj).intValue());
                        i3++;
                    }
                    break;
                case 851968:
                    for (int i13 = 0; i13 < commandQueue.mCallbacks.size(); i13++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i13)).showRecentApps(message.arg1 != 0);
                    }
                    break;
                case 917504:
                    for (int i14 = 0; i14 < commandQueue.mCallbacks.size(); i14++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i14)).hideRecentApps(message.arg1 != 0, message.arg2 != 0);
                    }
                    break;
                case 1179648:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showScreenPinningRequest(message.arg1);
                        i3++;
                    }
                    break;
                case 1245184:
                    for (int i15 = 0; i15 < commandQueue.mCallbacks.size(); i15++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i15)).appTransitionPending(message.arg1, message.arg2 != 0);
                    }
                    break;
                case 1310720:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).appTransitionCancelled(message.arg1);
                        i3++;
                    }
                    break;
                case 1376256:
                    SomeArgs someArgs4 = (SomeArgs) message.obj;
                    for (int i16 = 0; i16 < commandQueue.mCallbacks.size(); i16++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i16)).appTransitionStarting(someArgs4.argi1, ((Long) someArgs4.arg1).longValue(), ((Long) someArgs4.arg2).longValue(), someArgs4.argi2 != 0);
                    }
                    break;
                case 1441792:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showAssistDisclosure();
                        i3++;
                    }
                    break;
                case 1507328:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).startAssist((Bundle) message.obj);
                        i3++;
                    }
                    break;
                case 1572864:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).onCameraLaunchGestureDetected(message.arg1);
                        i3++;
                    }
                    break;
                case 1638400:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).toggleKeyboardShortcutsMenu(message.arg1);
                        i3++;
                    }
                    break;
                case 1703936:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showPictureInPictureMenu();
                        i3++;
                    }
                    break;
                case 1769472:
                    SomeArgs someArgs5 = (SomeArgs) message.obj;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).addQsTileToFrontOrEnd((ComponentName) someArgs5.arg1, ((Boolean) someArgs5.arg2).booleanValue());
                        i3++;
                    }
                    someArgs5.recycle();
                    break;
                case 1835008:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).remQsTile((ComponentName) message.obj);
                        i3++;
                    }
                    break;
                case 1900544:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).clickTile((ComponentName) message.obj);
                        i3++;
                    }
                    break;
                case 1966080:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).getClass();
                        i3++;
                    }
                    break;
                case 2031616:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).appTransitionFinished(message.arg1);
                        i3++;
                    }
                    break;
                case 2097152:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).dismissKeyboardShortcutsMenu();
                        i3++;
                    }
                    break;
                case 2162688:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).handleSystemKey((KeyEvent) message.obj);
                        i3++;
                    }
                    break;
                case 2228224:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).handleShowGlobalActionsMenu();
                        i3++;
                    }
                    break;
                case 2293760:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).toggleNotificationsPanel();
                        i3++;
                    }
                    break;
                case 2359296:
                    for (int i17 = 0; i17 < commandQueue.mCallbacks.size(); i17++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i17)).handleShowShutdownUi((String) message.obj, message.arg1 != 0);
                    }
                    break;
                case 2424832:
                    for (int i18 = 0; i18 < commandQueue.mCallbacks.size(); i18++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i18)).setTopAppHidesStatusBar(message.arg1 != 0);
                    }
                    break;
                case 2490368:
                    for (int i19 = 0; i19 < commandQueue.mCallbacks.size(); i19++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i19)).onRotationProposal(message.arg1, message.arg2 != 0);
                    }
                    break;
                case 2555904:
                    commandQueue.mHandler.removeMessages(2752512);
                    commandQueue.mHandler.removeMessages(2686976);
                    commandQueue.mHandler.removeMessages(2621440);
                    SomeArgs someArgs6 = (SomeArgs) message.obj;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showAuthenticationDialog((PromptInfo) someArgs6.arg1, (IBiometricSysuiReceiver) someArgs6.arg2, (int[]) someArgs6.arg3, ((Boolean) someArgs6.arg4).booleanValue(), ((Boolean) someArgs6.arg5).booleanValue(), someArgs6.argi1, someArgs6.argl1, (String) someArgs6.arg6, someArgs6.argl2);
                        i3++;
                        commandQueue = commandQueue;
                    }
                    someArgs6.recycle();
                    break;
                case 2621440:
                    SomeArgs someArgs7 = (SomeArgs) message.obj;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).onBiometricAuthenticated(someArgs7.argi1);
                        i3++;
                    }
                    someArgs7.recycle();
                    break;
                case 2686976:
                    SomeArgs someArgs8 = (SomeArgs) message.obj;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).onBiometricHelp(someArgs8.argi1, (String) someArgs8.arg1);
                        i3++;
                    }
                    someArgs8.recycle();
                    break;
                case 2752512:
                    SomeArgs someArgs9 = (SomeArgs) message.obj;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).onBiometricError(someArgs9.argi1, someArgs9.argi2, someArgs9.argi3);
                        i3++;
                    }
                    someArgs9.recycle();
                    break;
                case 2818048:
                    SomeArgs someArgs10 = (SomeArgs) message.obj;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).hideAuthenticationDialog(someArgs10.argl1);
                        i3++;
                    }
                    someArgs10.recycle();
                    break;
                case 2883584:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showWirelessChargingAnimation(message.arg1);
                        i3++;
                    }
                    break;
                case 2949120:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showPinningEnterExitToast(((Boolean) message.obj).booleanValue());
                        i3++;
                    }
                    break;
                case 3014656:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showPinningEscapeToast();
                        i3++;
                    }
                    break;
                case 3080192:
                    for (int i20 = 0; i20 < commandQueue.mCallbacks.size(); i20++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i20)).onRecentsAnimationStateChanged(message.arg1 > 0);
                    }
                    break;
                case 3145728:
                    SomeArgs someArgs11 = (SomeArgs) message.obj;
                    int i21 = someArgs11.argi1;
                    int i22 = someArgs11.argi2;
                    boolean z2 = someArgs11.argi3 != 0;
                    someArgs11.recycle();
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showTransient(i21, i22, z2);
                        i3++;
                    }
                    break;
                case 3211264:
                    SomeArgs someArgs12 = (SomeArgs) message.obj;
                    int i23 = someArgs12.argi1;
                    int i24 = someArgs12.argi2;
                    someArgs12.recycle();
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).abortTransient(i23, i24);
                        i3++;
                    }
                    break;
                case 3276800:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showInattentiveSleepWarning();
                        i3++;
                    }
                    break;
                case 3342336:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).dismissInattentiveSleepWarning(((Boolean) message.obj).booleanValue());
                        i3++;
                    }
                    break;
                case 3407872:
                    SomeArgs someArgs13 = (SomeArgs) message.obj;
                    String str = (String) someArgs13.arg1;
                    IBinder iBinder = (IBinder) someArgs13.arg2;
                    CharSequence charSequence = (CharSequence) someArgs13.arg3;
                    IBinder iBinder2 = (IBinder) someArgs13.arg4;
                    ITransientNotificationCallback iTransientNotificationCallback = (ITransientNotificationCallback) someArgs13.arg5;
                    int i25 = someArgs13.argi1;
                    int i26 = someArgs13.argi2;
                    int i27 = someArgs13.argi3;
                    Iterator it = commandQueue.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((Callbacks) it.next()).showToast(i25, str, iBinder, charSequence, iBinder2, i26, iTransientNotificationCallback, i27);
                        i26 = i26;
                        i25 = i25;
                    }
                    break;
                case 3473408:
                    SomeArgs someArgs14 = (SomeArgs) message.obj;
                    String str2 = (String) someArgs14.arg1;
                    IBinder iBinder3 = (IBinder) someArgs14.arg2;
                    Iterator it2 = commandQueue.mCallbacks.iterator();
                    while (it2.hasNext()) {
                        ((Callbacks) it2.next()).hideToast(str2, iBinder3);
                    }
                    break;
                case 3538944:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        Callbacks callbacks = (Callbacks) commandQueue.mCallbacks.get(i3);
                        ((Boolean) message.obj).getClass();
                        callbacks.getClass();
                        i3++;
                    }
                    break;
                case 3604480:
                    Iterator it3 = commandQueue.mCallbacks.iterator();
                    while (it3.hasNext()) {
                        ((Callbacks) it3.next()).suppressAmbientDisplay(((Boolean) message.obj).booleanValue());
                    }
                    break;
                case 3670016:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).requestMagnificationConnection(((Boolean) message.obj).booleanValue());
                        i3++;
                    }
                    break;
                case 3801088:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).onEmergencyActionLaunchGestureDetected();
                        i3++;
                    }
                    break;
                case 3866624:
                    for (int i28 = 0; i28 < commandQueue.mCallbacks.size(); i28++) {
                        ((Callbacks) commandQueue.mCallbacks.get(i28)).setNavigationBarLumaSamplingEnabled(message.arg1, message.arg2 != 0);
                    }
                    break;
                case 3932160:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).setUdfpsRefreshRateCallback((IUdfpsRefreshRateRequestCallback) message.obj);
                        i3++;
                    }
                    break;
                case 3997696:
                    SomeArgs someArgs15 = (SomeArgs) message.obj;
                    ComponentName componentName = (ComponentName) someArgs15.arg1;
                    CharSequence charSequence2 = (CharSequence) someArgs15.arg2;
                    CharSequence charSequence3 = (CharSequence) someArgs15.arg3;
                    Icon icon = (Icon) someArgs15.arg4;
                    IAddTileResultCallback iAddTileResultCallback = (IAddTileResultCallback) someArgs15.arg5;
                    int intValue = ((Integer) someArgs15.arg6).intValue();
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).requestAddTile(intValue, componentName, charSequence2, charSequence3, icon, iAddTileResultCallback);
                        i3++;
                    }
                    someArgs15.recycle();
                    break;
                case 4063232:
                    String str3 = (String) message.obj;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).cancelRequestAddTile(str3);
                        i3++;
                    }
                    break;
                case 4128768:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).setBiometricContextListener((IBiometricContextListener) message.obj);
                        i3++;
                    }
                    break;
                case 4194304:
                    SomeArgs someArgs16 = (SomeArgs) message.obj;
                    int intValue2 = ((Integer) someArgs16.arg1).intValue();
                    MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) someArgs16.arg2;
                    IUndoMediaTransferCallback iUndoMediaTransferCallback = (IUndoMediaTransferCallback) someArgs16.arg3;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).updateMediaTapToTransferSenderDisplay(intValue2, mediaRoute2Info, iUndoMediaTransferCallback);
                        i3++;
                    }
                    someArgs16.recycle();
                    break;
                case 4259840:
                    SomeArgs someArgs17 = (SomeArgs) message.obj;
                    int intValue3 = ((Integer) someArgs17.arg1).intValue();
                    MediaRoute2Info mediaRoute2Info2 = (MediaRoute2Info) someArgs17.arg2;
                    Icon icon2 = (Icon) someArgs17.arg3;
                    CharSequence charSequence4 = (CharSequence) someArgs17.arg4;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).updateMediaTapToTransferReceiverDisplay(intValue3, mediaRoute2Info2, icon2, charSequence4);
                        i3++;
                    }
                    someArgs17.recycle();
                    break;
                case 4325376:
                    INearbyMediaDevicesProvider iNearbyMediaDevicesProvider = (INearbyMediaDevicesProvider) message.obj;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).registerNearbyMediaDevicesProvider(iNearbyMediaDevicesProvider);
                        i3++;
                    }
                    break;
                case 4390912:
                    INearbyMediaDevicesProvider iNearbyMediaDevicesProvider2 = (INearbyMediaDevicesProvider) message.obj;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).unregisterNearbyMediaDevicesProvider(iNearbyMediaDevicesProvider2);
                        i3++;
                    }
                    break;
                case 4456448:
                    ComponentName componentName2 = (ComponentName) message.obj;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).requestTileServiceListeningState(componentName2);
                        i3++;
                    }
                    break;
                case 4521984:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showRearDisplayDialog(((Integer) message.obj).intValue());
                        i3++;
                    }
                    break;
                case 4587520:
                    int i29 = ((SomeArgs) message.obj).argi1;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).moveFocusedTaskToFullscreen(i29);
                        i3++;
                    }
                    break;
                case 4653056:
                    SomeArgs someArgs18 = (SomeArgs) message.obj;
                    int i30 = someArgs18.argi1;
                    boolean z3 = someArgs18.argi2 != 0;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).moveFocusedTaskToStageSplit(i30, z3);
                        i3++;
                    }
                    break;
                case 4718592:
                    SomeArgs someArgs19 = (SomeArgs) message.obj;
                    String str4 = (String) someArgs19.arg1;
                    UserHandle userHandle = (UserHandle) someArgs19.arg2;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).showMediaOutputSwitcher(str4, userHandle);
                        i3++;
                    }
                    break;
                case 4784128:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).toggleTaskbar();
                        i3++;
                    }
                    break;
                case 5046272:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).confirmImmersivePrompt();
                        i3++;
                    }
                    break;
                case 5111808:
                    SomeArgs someArgs20 = (SomeArgs) message.obj;
                    int i31 = someArgs20.argi1;
                    boolean z4 = someArgs20.argi2 != 0;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).immersiveModeChanged(i31, z4);
                        i3++;
                    }
                    break;
                case 5177344:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).setQsTiles((String[]) message.obj);
                        i3++;
                    }
                    break;
                case 5242880:
                    int i32 = ((SomeArgs) message.obj).argi1;
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).moveFocusedTaskToDesktop(i32);
                        i3++;
                    }
                    break;
                case 5308416:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).setSplitscreenFocus(((Boolean) message.obj).booleanValue());
                        i3++;
                    }
                    break;
                case 5373952:
                    while (i3 < commandQueue.mCallbacks.size()) {
                        ((Callbacks) commandQueue.mCallbacks.get(i3)).toggleQuickSettingsPanel();
                        i3++;
                    }
                    break;
            }
        }
    }

    public CommandQueue(Context context, DisplayTracker displayTracker) {
        this(context, displayTracker, null, null, null);
    }

    public final void abortTransient(int i, int i2) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = i2;
            this.mHandler.obtainMessage(3211264, obtain).sendToTarget();
        }
    }

    public final void addQsTile(ComponentName componentName) {
        addQsTileToFrontOrEnd(componentName, false);
    }

    public final void addQsTileToFrontOrEnd(ComponentName componentName, boolean z) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.arg1 = componentName;
            obtain.arg2 = Boolean.valueOf(z);
            this.mHandler.obtainMessage(1769472, obtain).sendToTarget();
        }
    }

    public final void animateCollapsePanels() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(262144);
            this.mHandler.obtainMessage(262144, 0, 0).sendToTarget();
        }
    }

    public final void animateExpandNotificationsPanel() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(196608);
            this.mHandler.sendEmptyMessage(196608);
        }
    }

    public final void animateExpandSettingsPanel(String str) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(327680);
            this.mHandler.obtainMessage(327680, str).sendToTarget();
        }
    }

    public final void appTransitionCancelled(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1310720, i, 0).sendToTarget();
        }
    }

    public final void appTransitionFinished(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(2031616, i, 0).sendToTarget();
        }
    }

    public final void appTransitionPending(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1245184, i, 0).sendToTarget();
        }
    }

    public final void appTransitionStarting(int i, long j, long j2) {
        appTransitionStarting(i, j, j2, false);
    }

    public final void cancelPreloadRecentApps() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(720896);
            this.mHandler.obtainMessage(720896, 0, 0, null).sendToTarget();
        }
    }

    public final void cancelRequestAddTile(String str) {
        this.mHandler.obtainMessage(4063232, str).sendToTarget();
    }

    public final void clickQsTile(ComponentName componentName) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1900544, componentName).sendToTarget();
        }
    }

    public final void confirmImmersivePrompt() {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(5046272).sendToTarget();
        }
    }

    public final void disable(int i, int i2, int i3, boolean z) {
        synchronized (this.mLock) {
            try {
                this.mDisplayDisabled.put(i, new Pair(Integer.valueOf(i2), Integer.valueOf(i3)));
                this.mHandler.removeMessages(131072);
                SomeArgs obtain = SomeArgs.obtain();
                obtain.argi1 = i;
                obtain.argi2 = i2;
                obtain.argi3 = i3;
                obtain.argi4 = z ? 1 : 0;
                Message obtainMessage = this.mHandler.obtainMessage(131072, obtain);
                if (Looper.myLooper() == this.mHandler.getLooper()) {
                    this.mHandler.handleMessage(obtainMessage);
                    obtainMessage.recycle();
                } else {
                    obtainMessage.sendToTarget();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void dismissInattentiveSleepWarning(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3342336, Boolean.valueOf(z)).sendToTarget();
        }
    }

    public final void dismissKeyboardShortcutsMenu() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2097152);
            this.mHandler.obtainMessage(2097152).sendToTarget();
        }
    }

    public final void dumpProto(String[] strArr, ParcelFileDescriptor parcelFileDescriptor) {
        new AnonymousClass2(parcelFileDescriptor.getFileDescriptor(), strArr, parcelFileDescriptor).start();
    }

    public final void handleSystemKey(KeyEvent keyEvent) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(2162688, keyEvent).sendToTarget();
        }
    }

    public final void hideAuthenticationDialog(long j) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argl1 = j;
            this.mHandler.obtainMessage(2818048, obtain).sendToTarget();
        }
    }

    public final void hideRecentApps(boolean z, boolean z2) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(917504);
            this.mHandler.obtainMessage(917504, z ? 1 : 0, z2 ? 1 : 0, null).sendToTarget();
        }
    }

    public final void hideToast(String str, IBinder iBinder) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.arg1 = str;
            obtain.arg2 = iBinder;
            this.mHandler.obtainMessage(3473408, obtain).sendToTarget();
        }
    }

    public final void immersiveModeChanged(int i, boolean z) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = z ? 1 : 0;
            this.mHandler.obtainMessage(5111808, obtain).sendToTarget();
        }
    }

    public final void moveFocusedTaskToDesktop(int i) {
        SomeArgs obtain = SomeArgs.obtain();
        obtain.arg1 = Integer.valueOf(i);
        this.mHandler.obtainMessage(5242880, obtain).sendToTarget();
    }

    public final void moveFocusedTaskToFullscreen(int i) {
        SomeArgs obtain = SomeArgs.obtain();
        obtain.arg1 = Integer.valueOf(i);
        this.mHandler.obtainMessage(4587520, obtain).sendToTarget();
    }

    public final void moveFocusedTaskToStageSplit(int i, boolean z) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = z ? 1 : 0;
            this.mHandler.obtainMessage(4653056, obtain).sendToTarget();
        }
    }

    public final void onBiometricAuthenticated(int i) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            this.mHandler.obtainMessage(2621440, obtain).sendToTarget();
        }
    }

    public final void onBiometricError(int i, int i2, int i3) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = i2;
            obtain.argi3 = i3;
            this.mHandler.obtainMessage(2752512, obtain).sendToTarget();
        }
    }

    public final void onBiometricHelp(int i, String str) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.arg1 = str;
            this.mHandler.obtainMessage(2686976, obtain).sendToTarget();
        }
    }

    public final void onCameraLaunchGestureDetected(int i) {
        synchronized (this.mLock) {
            try {
                Lazy lazy = this.mPowerInteractor;
                if (lazy != null) {
                    PowerInteractor powerInteractor = (PowerInteractor) lazy.get();
                    if (((CameraGestureHelper) powerInteractor.cameraGestureHelper.get()).canCameraGestureBeLaunched(powerInteractor.statusBarStateController.getState())) {
                        PowerRepositoryImpl.updateWakefulness$default(powerInteractor.repository, null, null, null, true, 7);
                    }
                }
                this.mHandler.removeMessages(1572864);
                this.mHandler.obtainMessage(1572864, i, 0).sendToTarget();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void onDisplayReady(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(458752, i, 0).sendToTarget();
        }
    }

    public final void onEmergencyActionLaunchGestureDetected() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(3801088);
            this.mHandler.obtainMessage(3801088).sendToTarget();
        }
    }

    public final void onProposedRotationChanged(int i, boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2490368);
            this.mHandler.obtainMessage(2490368, i, z ? 1 : 0, null).sendToTarget();
        }
    }

    public final void onSystemBarAttributesChanged(int i, int i2, AppearanceRegion[] appearanceRegionArr, boolean z, int i3, int i4, String str, LetterboxDetails[] letterboxDetailsArr) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = i2;
            obtain.argi3 = z ? 1 : 0;
            obtain.arg1 = appearanceRegionArr;
            obtain.argi4 = i3;
            obtain.argi5 = i4;
            obtain.arg3 = str;
            obtain.arg4 = letterboxDetailsArr;
            this.mHandler.obtainMessage(393216, obtain).sendToTarget();
        }
    }

    public final boolean panelsEnabled() {
        this.mDisplayTracker.getClass();
        Pair pair = (Pair) this.mDisplayDisabled.get(0);
        if (pair == null) {
            pair = new Pair(0, 0);
            this.mDisplayDisabled.put(0, pair);
        }
        int intValue = ((Integer) pair.first).intValue();
        this.mDisplayTracker.getClass();
        Pair pair2 = (Pair) this.mDisplayDisabled.get(0);
        if (pair2 == null) {
            pair2 = new Pair(0, 0);
            this.mDisplayDisabled.put(0, pair2);
        }
        return (intValue & 65536) == 0 && (((Integer) pair2.second).intValue() & 4) == 0;
    }

    public final void passThroughShellCommand(String[] strArr, ParcelFileDescriptor parcelFileDescriptor) {
        new AnonymousClass2(new PrintWriter(new FileOutputStream(parcelFileDescriptor.getFileDescriptor())), strArr, parcelFileDescriptor).start();
    }

    public final void preloadRecentApps() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(655360);
            this.mHandler.obtainMessage(655360, 0, 0, null).sendToTarget();
        }
    }

    public final void recomputeDisableFlags(int i, boolean z) {
        synchronized (this.mLock) {
            Pair pair = (Pair) this.mDisplayDisabled.get(i);
            if (pair == null) {
                pair = new Pair(0, 0);
                this.mDisplayDisabled.put(i, pair);
            }
            int intValue = ((Integer) pair.first).intValue();
            Pair pair2 = (Pair) this.mDisplayDisabled.get(i);
            if (pair2 == null) {
                pair2 = new Pair(0, 0);
                this.mDisplayDisabled.put(i, pair2);
            }
            disable(i, intValue, ((Integer) pair2.second).intValue(), z);
        }
    }

    public final void registerNearbyMediaDevicesProvider(INearbyMediaDevicesProvider iNearbyMediaDevicesProvider) {
        this.mHandler.obtainMessage(4325376, iNearbyMediaDevicesProvider).sendToTarget();
    }

    public final void remQsTile(ComponentName componentName) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1835008, componentName).sendToTarget();
        }
    }

    public final void removeIcon(String str) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(65536, 2, 0, str).sendToTarget();
        }
    }

    public final void requestAddTile(int i, ComponentName componentName, CharSequence charSequence, CharSequence charSequence2, Icon icon, IAddTileResultCallback iAddTileResultCallback) {
        SomeArgs obtain = SomeArgs.obtain();
        obtain.arg1 = componentName;
        obtain.arg2 = charSequence;
        obtain.arg3 = charSequence2;
        obtain.arg4 = icon;
        obtain.arg5 = iAddTileResultCallback;
        obtain.arg6 = Integer.valueOf(i);
        this.mHandler.obtainMessage(3997696, obtain).sendToTarget();
    }

    public final void requestMagnificationConnection(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3670016, Boolean.valueOf(z)).sendToTarget();
        }
    }

    public final void requestTileServiceListeningState(ComponentName componentName) {
        this.mHandler.obtainMessage(4456448, componentName).sendToTarget();
    }

    public final void runGcForTest() {
        GcUtils.runGcAndFinalizersSync();
    }

    public final void setBiometicContextListener(IBiometricContextListener iBiometricContextListener) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(4128768, iBiometricContextListener).sendToTarget();
        }
    }

    public final void setIcon(String str, StatusBarIcon statusBarIcon) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(65536, 1, 0, new Pair(str, statusBarIcon)).sendToTarget();
        }
    }

    public final void setImeWindowStatus(int i, int i2, int i3, boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(524288);
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = i2;
            obtain.argi3 = i3;
            obtain.argi4 = z ? 1 : 0;
            this.mHandler.obtainMessage(524288, obtain).sendToTarget();
        }
    }

    public final void setNavigationBarLumaSamplingEnabled(int i, boolean z) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3866624, i, z ? 1 : 0).sendToTarget();
        }
    }

    public final void setQsTiles(String[] strArr) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(5177344, strArr).sendToTarget();
        }
    }

    public final void setSplitscreenFocus(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(5308416, Boolean.valueOf(z)).sendToTarget();
        }
    }

    public final void setTopAppHidesStatusBar(boolean z) {
        this.mHandler.removeMessages(2424832);
        this.mHandler.obtainMessage(2424832, z ? 1 : 0, 0).sendToTarget();
    }

    public final void setUdfpsRefreshRateCallback(IUdfpsRefreshRateRequestCallback iUdfpsRefreshRateRequestCallback) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3932160, iUdfpsRefreshRateRequestCallback).sendToTarget();
        }
    }

    public final void setWindowState(int i, int i2, int i3) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(786432, i, i2, Integer.valueOf(i3)).sendToTarget();
        }
    }

    public final void showAssistDisclosure() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1441792);
            this.mHandler.obtainMessage(1441792).sendToTarget();
        }
    }

    public final void showAuthenticationDialog(PromptInfo promptInfo, IBiometricSysuiReceiver iBiometricSysuiReceiver, int[] iArr, boolean z, boolean z2, int i, long j, String str, long j2) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.arg1 = promptInfo;
            obtain.arg2 = iBiometricSysuiReceiver;
            obtain.arg3 = iArr;
            obtain.arg4 = Boolean.valueOf(z);
            obtain.arg5 = Boolean.valueOf(z2);
            obtain.argi1 = i;
            obtain.arg6 = str;
            obtain.argl1 = j;
            obtain.argl2 = j2;
            this.mHandler.obtainMessage(2555904, obtain).sendToTarget();
        }
    }

    public final void showGlobalActionsMenu() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2228224);
            this.mHandler.obtainMessage(2228224).sendToTarget();
        }
    }

    public final void showInattentiveSleepWarning() {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3276800).sendToTarget();
        }
    }

    public final void showMediaOutputSwitcher(String str, UserHandle userHandle) {
        int callingUid = Binder.getCallingUid();
        if (callingUid != 0 && callingUid != 1000) {
            throw new SecurityException("Call only allowed from system server.");
        }
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.arg1 = str;
            obtain.arg2 = userHandle;
            this.mHandler.obtainMessage(4718592, obtain).sendToTarget();
        }
    }

    public final void showPictureInPictureMenu() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1703936);
            this.mHandler.obtainMessage(1703936).sendToTarget();
        }
    }

    public final void showPinningEnterExitToast(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(2949120, Boolean.valueOf(z)).sendToTarget();
        }
    }

    public final void showPinningEscapeToast() {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3014656).sendToTarget();
        }
    }

    public final void showRearDisplayDialog(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(4521984, Integer.valueOf(i)).sendToTarget();
        }
    }

    public final void showRecentApps(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(851968);
            this.mHandler.obtainMessage(851968, z ? 1 : 0, 0, null).sendToTarget();
        }
    }

    public final void showScreenPinningRequest(int i) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(1179648, i, 0, null).sendToTarget();
        }
    }

    public final void showShutdownUi(boolean z, String str) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2359296);
            this.mHandler.obtainMessage(2359296, z ? 1 : 0, 0, str).sendToTarget();
        }
    }

    public final void showToast(int i, String str, IBinder iBinder, CharSequence charSequence, IBinder iBinder2, int i2, ITransientNotificationCallback iTransientNotificationCallback, int i3) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.arg1 = str;
            obtain.arg2 = iBinder;
            obtain.arg3 = charSequence;
            obtain.arg4 = iBinder2;
            obtain.arg5 = iTransientNotificationCallback;
            obtain.argi1 = i;
            obtain.argi2 = i2;
            obtain.argi3 = i3;
            this.mHandler.obtainMessage(3407872, obtain).sendToTarget();
        }
    }

    public final void showTransient(int i, int i2, boolean z) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = i2;
            obtain.argi3 = z ? 1 : 0;
            this.mHandler.obtainMessage(3145728, obtain).sendToTarget();
        }
    }

    public final void showWirelessChargingAnimation(int i) {
        this.mHandler.removeMessages(2883584);
        this.mHandler.obtainMessage(2883584, i, 0).sendToTarget();
    }

    public final void startAssist(Bundle bundle) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1507328);
            this.mHandler.obtainMessage(1507328, bundle).sendToTarget();
        }
    }

    public final void startTracing() {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3538944, Boolean.TRUE).sendToTarget();
        }
    }

    public final void stopTracing() {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3538944, Boolean.FALSE).sendToTarget();
        }
    }

    public final void suppressAmbientDisplay(boolean z) {
        synchronized (this.mLock) {
            this.mHandler.obtainMessage(3604480, Boolean.valueOf(z)).sendToTarget();
        }
    }

    public final void toggleKeyboardShortcutsMenu(int i) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1638400);
            this.mHandler.obtainMessage(1638400, i, 0).sendToTarget();
        }
    }

    public final void toggleNotificationsPanel() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(2293760);
            this.mHandler.obtainMessage(2293760, 0, 0).sendToTarget();
        }
    }

    public final void toggleRecentApps() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(589824);
            Message obtainMessage = this.mHandler.obtainMessage(589824, 0, 0, null);
            obtainMessage.setAsynchronous(true);
            obtainMessage.sendToTarget();
        }
    }

    public final void toggleSplitScreen() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(1966080);
            this.mHandler.obtainMessage(1966080, 0, 0, null).sendToTarget();
        }
    }

    public final void toggleTaskbar() {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(4784128);
            this.mHandler.obtainMessage(4784128, 0, 0, null).sendToTarget();
        }
    }

    public final void unregisterNearbyMediaDevicesProvider(INearbyMediaDevicesProvider iNearbyMediaDevicesProvider) {
        this.mHandler.obtainMessage(4390912, iNearbyMediaDevicesProvider).sendToTarget();
    }

    public final void updateMediaTapToTransferReceiverDisplay(int i, MediaRoute2Info mediaRoute2Info, Icon icon, CharSequence charSequence) {
        SomeArgs obtain = SomeArgs.obtain();
        obtain.arg1 = Integer.valueOf(i);
        obtain.arg2 = mediaRoute2Info;
        obtain.arg3 = icon;
        obtain.arg4 = charSequence;
        this.mHandler.obtainMessage(4259840, obtain).sendToTarget();
    }

    public final void updateMediaTapToTransferSenderDisplay(int i, MediaRoute2Info mediaRoute2Info, IUndoMediaTransferCallback iUndoMediaTransferCallback) {
        SomeArgs obtain = SomeArgs.obtain();
        obtain.arg1 = Integer.valueOf(i);
        obtain.arg2 = mediaRoute2Info;
        obtain.arg3 = iUndoMediaTransferCallback;
        this.mHandler.obtainMessage(4194304, obtain).sendToTarget();
    }

    public CommandQueue(Context context, DisplayTracker displayTracker, CommandRegistry commandRegistry, DumpHandler dumpHandler, Lazy lazy) {
        this.mLock = new Object();
        this.mCallbacks = new ArrayList();
        H h = new H(Looper.getMainLooper());
        this.mHandler = h;
        SparseArray sparseArray = new SparseArray();
        this.mDisplayDisabled = sparseArray;
        this.mLastUpdatedImeDisplayId = -1;
        DisplayTracker.Callback callback = new DisplayTracker.Callback() { // from class: com.android.systemui.statusbar.CommandQueue.1
            @Override // com.android.systemui.settings.DisplayTracker.Callback
            public final void onDisplayRemoved(int i) {
                synchronized (CommandQueue.this.mLock) {
                    CommandQueue.this.mDisplayDisabled.remove(i);
                }
                for (int size = CommandQueue.this.mCallbacks.size() - 1; size >= 0; size--) {
                    ((Callbacks) CommandQueue.this.mCallbacks.get(size)).onDisplayRemoved(i);
                }
            }
        };
        this.mDisplayTrackerCallback = callback;
        this.mContext = context;
        this.mDisplayTracker = displayTracker;
        this.mRegistry = commandRegistry;
        this.mDumpHandler = dumpHandler;
        ((DisplayTrackerImpl) displayTracker).addDisplayChangeCallback(callback, new HandlerExecutor(h));
        displayTracker.getClass();
        sparseArray.put(0, new Pair(0, 0));
        this.mPowerInteractor = lazy;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Callbacks callbacks) {
        this.mCallbacks.add(callbacks);
        for (int i = 0; i < this.mDisplayDisabled.size(); i++) {
            int keyAt = this.mDisplayDisabled.keyAt(i);
            Pair pair = (Pair) this.mDisplayDisabled.get(keyAt);
            if (pair == null) {
                pair = new Pair(0, 0);
                this.mDisplayDisabled.put(keyAt, pair);
            }
            int intValue = ((Integer) pair.first).intValue();
            Pair pair2 = (Pair) this.mDisplayDisabled.get(keyAt);
            if (pair2 == null) {
                pair2 = new Pair(0, 0);
                this.mDisplayDisabled.put(keyAt, pair2);
            }
            callbacks.disable(keyAt, intValue, ((Integer) pair2.second).intValue(), false);
        }
    }

    public final void appTransitionStarting(int i, long j, long j2, boolean z) {
        synchronized (this.mLock) {
            SomeArgs obtain = SomeArgs.obtain();
            obtain.argi1 = i;
            obtain.argi2 = z ? 1 : 0;
            obtain.arg1 = Long.valueOf(j);
            obtain.arg2 = Long.valueOf(j2);
            this.mHandler.obtainMessage(1376256, obtain).sendToTarget();
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Callbacks callbacks) {
        this.mCallbacks.remove(callbacks);
    }

    public final void animateCollapsePanels(int i, boolean z) {
        synchronized (this.mLock) {
            this.mHandler.removeMessages(262144);
            this.mHandler.obtainMessage(262144, i, z ? 1 : 0).sendToTarget();
        }
    }

    public final void disable(int i, int i2, int i3) {
        disable(i, i2, i3, true);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callbacks {
        default void animateExpandNotificationsPanel() {
        }

        default void animateExpandSettingsPanel(String str) {
        }

        default void appTransitionCancelled(int i) {
        }

        default void appTransitionFinished(int i) {
        }

        default void cancelPreloadRecentApps() {
        }

        default void cancelRequestAddTile(String str) {
        }

        default void clickTile(ComponentName componentName) {
        }

        default void confirmImmersivePrompt() {
        }

        default void dismissInattentiveSleepWarning(boolean z) {
        }

        default void dismissKeyboardShortcutsMenu() {
        }

        default void handleShowGlobalActionsMenu() {
        }

        default void handleSystemKey(KeyEvent keyEvent) {
        }

        default void hideAuthenticationDialog(long j) {
        }

        default void moveFocusedTaskToDesktop(int i) {
        }

        default void moveFocusedTaskToFullscreen(int i) {
        }

        default void onBiometricAuthenticated(int i) {
        }

        default void onCameraLaunchGestureDetected(int i) {
        }

        default void onDisplayReady(int i) {
        }

        default void onDisplayRemoved(int i) {
        }

        default void onEmergencyActionLaunchGestureDetected() {
        }

        default void onRecentsAnimationStateChanged(boolean z) {
        }

        default void preloadRecentApps() {
        }

        default void registerNearbyMediaDevicesProvider(INearbyMediaDevicesProvider iNearbyMediaDevicesProvider) {
        }

        default void remQsTile(ComponentName componentName) {
        }

        default void removeIcon(String str) {
        }

        default void requestMagnificationConnection(boolean z) {
        }

        default void requestTileServiceListeningState(ComponentName componentName) {
        }

        default void setBiometricContextListener(IBiometricContextListener iBiometricContextListener) {
        }

        default void setQsTiles(String[] strArr) {
        }

        default void setSplitscreenFocus(boolean z) {
        }

        default void setTopAppHidesStatusBar(boolean z) {
        }

        default void setUdfpsRefreshRateCallback(IUdfpsRefreshRateRequestCallback iUdfpsRefreshRateRequestCallback) {
        }

        default void showAssistDisclosure() {
        }

        default void showInattentiveSleepWarning() {
        }

        default void showPictureInPictureMenu() {
        }

        default void showPinningEnterExitToast(boolean z) {
        }

        default void showPinningEscapeToast() {
        }

        default void showRearDisplayDialog(int i) {
        }

        default void showRecentApps(boolean z) {
        }

        default void showScreenPinningRequest(int i) {
        }

        default void showWirelessChargingAnimation(int i) {
        }

        default void startAssist(Bundle bundle) {
        }

        default void suppressAmbientDisplay(boolean z) {
        }

        default void toggleKeyboardShortcutsMenu(int i) {
        }

        default void toggleNotificationsPanel() {
        }

        default void toggleQuickSettingsPanel() {
        }

        default void toggleRecentApps() {
        }

        default void toggleTaskbar() {
        }

        default void unregisterNearbyMediaDevicesProvider(INearbyMediaDevicesProvider iNearbyMediaDevicesProvider) {
        }

        default void abortTransient(int i, int i2) {
        }

        default void addQsTileToFrontOrEnd(ComponentName componentName, boolean z) {
        }

        default void animateCollapsePanels(int i, boolean z) {
        }

        default void appTransitionPending(int i, boolean z) {
        }

        default void handleShowShutdownUi(String str, boolean z) {
        }

        default void hideRecentApps(boolean z, boolean z2) {
        }

        default void hideToast(String str, IBinder iBinder) {
        }

        default void immersiveModeChanged(int i, boolean z) {
        }

        default void moveFocusedTaskToStageSplit(int i, boolean z) {
        }

        default void onBiometricHelp(int i, String str) {
        }

        default void onRotationProposal(int i, boolean z) {
        }

        default void setIcon(String str, StatusBarIcon statusBarIcon) {
        }

        default void setNavigationBarLumaSamplingEnabled(int i, boolean z) {
        }

        default void showMediaOutputSwitcher(String str, UserHandle userHandle) {
        }

        default void onBiometricError(int i, int i2, int i3) {
        }

        default void setWindowState(int i, int i2, int i3) {
        }

        default void showTransient(int i, int i2, boolean z) {
        }

        default void updateMediaTapToTransferSenderDisplay(int i, MediaRoute2Info mediaRoute2Info, IUndoMediaTransferCallback iUndoMediaTransferCallback) {
        }

        default void appTransitionStarting(int i, long j, long j2, boolean z) {
        }

        default void disable(int i, int i2, int i3, boolean z) {
        }

        default void setImeWindowStatus(int i, int i2, int i3, boolean z) {
        }

        default void updateMediaTapToTransferReceiverDisplay(int i, MediaRoute2Info mediaRoute2Info, Icon icon, CharSequence charSequence) {
        }

        default void onSystemBarAttributesChanged(int i, int i2, AppearanceRegion[] appearanceRegionArr, boolean z, int i3, int i4, String str, LetterboxDetails[] letterboxDetailsArr) {
        }

        default void showToast(int i, String str, IBinder iBinder, CharSequence charSequence, IBinder iBinder2, int i2, ITransientNotificationCallback iTransientNotificationCallback, int i3) {
        }

        default void requestAddTile(int i, ComponentName componentName, CharSequence charSequence, CharSequence charSequence2, Icon icon, IAddTileResultCallback iAddTileResultCallback) {
        }

        default void showAuthenticationDialog(PromptInfo promptInfo, IBiometricSysuiReceiver iBiometricSysuiReceiver, int[] iArr, boolean z, boolean z2, int i, long j, String str, long j2) {
        }
    }
}

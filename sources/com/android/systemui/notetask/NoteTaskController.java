package com.android.systemui.notetask;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutManager;
import android.content.pm.UserInfo;
import android.os.Build;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.widget.Toast;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoteTaskController {
    public static final String TAG = null;
    public final ActivityManager activityManager;
    public final CoroutineScope applicationScope;
    public final CoroutineContext bgCoroutineContext;
    public final Context context;
    public final DevicePolicyManager devicePolicyManager;
    public final NoteTaskEventLogger eventLogger;
    public final AtomicReference infoReference = new AtomicReference();
    public final boolean isEnabled;
    public final KeyguardManager keyguardManager;
    public final NoteTaskBubblesController noteTaskBubblesController;
    public final NoteTaskInfoResolver resolver;
    public final RoleManager roleManager;
    public final SecureSettings secureSettings;
    public final ShortcutManager shortcutManager;
    public final UserManager userManager;
    public final UserTracker userTracker;

    static {
        Reflection.getOrCreateKotlinClass(NoteTaskController.class).getSimpleName();
    }

    public NoteTaskController(Context context, RoleManager roleManager, ShortcutManager shortcutManager, NoteTaskInfoResolver noteTaskInfoResolver, NoteTaskEventLogger noteTaskEventLogger, NoteTaskBubblesController noteTaskBubblesController, UserManager userManager, KeyguardManager keyguardManager, ActivityManager activityManager, boolean z, DevicePolicyManager devicePolicyManager, UserTracker userTracker, SecureSettings secureSettings, CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        this.context = context;
        this.roleManager = roleManager;
        this.shortcutManager = shortcutManager;
        this.resolver = noteTaskInfoResolver;
        this.eventLogger = noteTaskEventLogger;
        this.noteTaskBubblesController = noteTaskBubblesController;
        this.userManager = userManager;
        this.keyguardManager = keyguardManager;
        this.activityManager = activityManager;
        this.isEnabled = z;
        this.devicePolicyManager = devicePolicyManager;
        this.userTracker = userTracker;
        this.secureSettings = secureSettings;
        this.applicationScope = coroutineScope;
        this.bgCoroutineContext = coroutineContext;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|1|(2:3|(8:5|6|7|(1:(1:(7:11|12|13|14|15|16|17)(2:20|21))(1:22))(4:60|(2:62|(1:65)(1:64))|16|17)|23|(1:25)(2:26|(2:28|(2:33|(1:35)(3:36|37|(6:39|(1:41)(1:45)|(2:43|44)|13|14|15)(4:46|(4:48|49|(2:51|(4:53|(1:55)|56|(1:58)))|59)|14|15)))(1:32)))|16|17))|67|6|7|(0)(0)|23|(0)(0)|16|17) */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0191, code lost:
    
        r1 = android.os.Build.IS_DEBUGGABLE;
        kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0.getClass()).getSimpleName();
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$awaitShowNoteTaskAsUser(com.android.systemui.notetask.NoteTaskController r16, com.android.systemui.notetask.NoteTaskEntryPoint r17, android.os.UserHandle r18, kotlin.coroutines.jvm.internal.ContinuationImpl r19) {
        /*
            Method dump skipped, instructions count: 426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.notetask.NoteTaskController.access$awaitShowNoteTaskAsUser(com.android.systemui.notetask.NoteTaskController, com.android.systemui.notetask.NoteTaskEntryPoint, android.os.UserHandle, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final UserHandle getCurrentRunningUser$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        return Process.myUserHandle();
    }

    public final UserHandle getUserForHandlingNotesTaking(NoteTaskEntryPoint noteTaskEntryPoint) {
        Object obj;
        NoteTaskEntryPoint noteTaskEntryPoint2 = NoteTaskEntryPoint.TAIL_BUTTON;
        UserTracker userTracker = this.userTracker;
        if (noteTaskEntryPoint == noteTaskEntryPoint2) {
            int identifier = ((UserTrackerImpl) userTracker).getUserHandle().getIdentifier();
            return UserHandle.of(this.secureSettings.getIntForUser("default_note_task_profile", identifier, identifier));
        }
        if (!this.devicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile() || noteTaskEntryPoint != NoteTaskEntryPoint.QUICK_AFFORDANCE) {
            return ((UserTrackerImpl) userTracker).getUserHandle();
        }
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) userTracker;
        Iterator it = userTrackerImpl.getUserProfiles().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (this.userManager.isManagedProfile(((UserInfo) obj).id)) {
                break;
            }
        }
        UserInfo userInfo = (UserInfo) obj;
        UserHandle userHandle = userInfo != null ? userInfo.getUserHandle() : null;
        return userHandle == null ? userTrackerImpl.getUserHandle() : userHandle;
    }

    public final void launchUpdateNoteTaskAsUser(UserHandle userHandle) {
        CoroutineTracingKt.launch$default(this.applicationScope, this.bgCoroutineContext, new NoteTaskController$launchUpdateNoteTaskAsUser$1(this, userHandle, null), 4);
    }

    public final void showNoDefaultNotesAppToast() {
        Toast.makeText(this.context, R.string.set_default_notes_app_toast_content, 0).show();
    }

    public final void showNoteTaskAsUser(NoteTaskEntryPoint noteTaskEntryPoint, UserHandle userHandle) {
        if (this.isEnabled) {
            CoroutineTracingKt.launch$default(this.applicationScope, null, new NoteTaskController$showNoteTaskAsUser$1(this, noteTaskEntryPoint, userHandle, null), 6);
        }
    }

    public final void updateNoteTaskAsUser(UserHandle userHandle) {
        if (!this.userManager.isUserUnlocked(userHandle)) {
            boolean z = Build.IS_DEBUGGABLE;
            Reflection.getOrCreateKotlinClass(NoteTaskController.class).getSimpleName();
        } else {
            if (userHandle.equals(getCurrentRunningUser$frameworks__base__packages__SystemUI__android_common__SystemUI_core())) {
                launchUpdateNoteTaskAsUser(userHandle);
                return;
            }
            int i = NoteTaskControllerUpdateService.$r8$clinit;
            try {
                this.context.startServiceAsUser(new Intent(this.context, (Class<?>) NoteTaskControllerUpdateService.class), userHandle);
            } catch (SecurityException unused) {
                boolean z2 = Build.IS_DEBUGGABLE;
                Reflection.getOrCreateKotlinClass(NoteTaskController.class).getSimpleName();
            }
        }
    }

    public final void updateNoteTaskForCurrentUserAndManagedProfiles() {
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) this.userTracker;
        updateNoteTaskAsUser(userTrackerImpl.getUserHandle());
        for (UserInfo userInfo : userTrackerImpl.getUserProfiles()) {
            if (this.userManager.isManagedProfile(userInfo.id)) {
                updateNoteTaskAsUser(userInfo.getUserHandle());
            }
        }
    }

    public static /* synthetic */ void getInfoReference$annotations() {
    }
}

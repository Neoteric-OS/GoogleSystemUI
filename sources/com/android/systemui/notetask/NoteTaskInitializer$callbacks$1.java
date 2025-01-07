package com.android.systemui.notetask;

import android.app.role.OnRoleHoldersChangedListener;
import android.content.Context;
import android.hardware.input.InputManager;
import android.hardware.input.KeyGestureEvent;
import android.os.Build;
import android.os.IBinder;
import android.os.UserHandle;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.CommandQueue;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoteTaskInitializer$callbacks$1 extends KeyguardUpdateMonitorCallback implements CommandQueue.Callbacks, UserTracker.Callback, OnRoleHoldersChangedListener, InputManager.KeyGestureEventHandler {
    public final /* synthetic */ NoteTaskInitializer this$0;

    public NoteTaskInitializer$callbacks$1(NoteTaskInitializer noteTaskInitializer) {
        this.this$0 = noteTaskInitializer;
    }

    public final boolean handleKeyGestureEvent(KeyGestureEvent keyGestureEvent, IBinder iBinder) {
        final NoteTaskInitializer noteTaskInitializer = this.this$0;
        noteTaskInitializer.getClass();
        if (keyGestureEvent.getKeyGestureType() != 32) {
            return false;
        }
        boolean z = Build.IS_DEBUGGABLE;
        Reflection.getOrCreateKotlinClass(NoteTaskInitializer.class).getSimpleName();
        if (ArraysKt.contains(keyGestureEvent.getKeycodes(), 42) && keyGestureEvent.hasModifiers(69632)) {
            Reflection.getOrCreateKotlinClass(NoteTaskInitializer.class).getSimpleName();
            final int i = 0;
            noteTaskInitializer.backgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.notetask.NoteTaskInitializer$handleKeyGestureEvent$3
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i) {
                        case 0:
                            NoteTaskController noteTaskController = noteTaskInitializer.controller;
                            NoteTaskEntryPoint noteTaskEntryPoint = NoteTaskEntryPoint.KEYBOARD_SHORTCUT;
                            if (noteTaskController.isEnabled) {
                                noteTaskController.showNoteTaskAsUser(noteTaskEntryPoint, noteTaskController.getUserForHandlingNotesTaking(noteTaskEntryPoint));
                                break;
                            }
                            break;
                        default:
                            NoteTaskController noteTaskController2 = noteTaskInitializer.controller;
                            NoteTaskEntryPoint noteTaskEntryPoint2 = NoteTaskEntryPoint.TAIL_BUTTON;
                            if (noteTaskController2.isEnabled) {
                                noteTaskController2.showNoteTaskAsUser(noteTaskEntryPoint2, noteTaskController2.getUserForHandlingNotesTaking(noteTaskEntryPoint2));
                                break;
                            }
                            break;
                    }
                }
            });
        } else {
            if (keyGestureEvent.getKeycodes().length != 1 || keyGestureEvent.getKeycodes()[0] != 311) {
                return false;
            }
            Reflection.getOrCreateKotlinClass(NoteTaskInitializer.class).getSimpleName();
            final int i2 = 1;
            noteTaskInitializer.backgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.notetask.NoteTaskInitializer$handleKeyGestureEvent$3
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i2) {
                        case 0:
                            NoteTaskController noteTaskController = noteTaskInitializer.controller;
                            NoteTaskEntryPoint noteTaskEntryPoint = NoteTaskEntryPoint.KEYBOARD_SHORTCUT;
                            if (noteTaskController.isEnabled) {
                                noteTaskController.showNoteTaskAsUser(noteTaskEntryPoint, noteTaskController.getUserForHandlingNotesTaking(noteTaskEntryPoint));
                                break;
                            }
                            break;
                        default:
                            NoteTaskController noteTaskController2 = noteTaskInitializer.controller;
                            NoteTaskEntryPoint noteTaskEntryPoint2 = NoteTaskEntryPoint.TAIL_BUTTON;
                            if (noteTaskController2.isEnabled) {
                                noteTaskController2.showNoteTaskAsUser(noteTaskEntryPoint2, noteTaskController2.getUserForHandlingNotesTaking(noteTaskEntryPoint2));
                                break;
                            }
                            break;
                    }
                }
            });
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void handleSystemKey(android.view.KeyEvent r9) {
        /*
            r8 = this;
            com.android.systemui.notetask.NoteTaskInitializer r0 = r8.this$0
            r0.getClass()
            int r1 = r9.getKeyCode()
            r2 = 311(0x137, float:4.36E-43)
            if (r1 != r2) goto L57
            int r1 = r9.getKeyCode()
            if (r1 != r2) goto L57
            int r1 = r9.getAction()
            r2 = 1
            if (r1 == r2) goto L1b
            goto L57
        L1b:
            long r3 = r9.getDownTime()
            long r5 = r0.lastStylusButtonTailUpEventTime
            long r3 = r3 - r5
            long r5 = com.android.systemui.notetask.NoteTaskInitializer.MULTI_PRESS_TIMEOUT
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            r3 = 0
            if (r1 >= 0) goto L2b
            r1 = r2
            goto L2c
        L2b:
            r1 = r3
        L2c:
            long r4 = r9.getEventTime()
            long r6 = r9.getDownTime()
            long r4 = r4 - r6
            long r6 = com.android.systemui.notetask.NoteTaskInitializer.LONG_PRESS_TIMEOUT
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 < 0) goto L3c
            goto L3d
        L3c:
            r2 = r3
        L3d:
            long r3 = r9.getEventTime()
            r0.lastStylusButtonTailUpEventTime = r3
            boolean r0 = android.os.Build.IS_DEBUGGABLE
            java.lang.Class r0 = r9.getClass()
            kotlin.jvm.internal.ClassReference r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            r0.getSimpleName()
            if (r1 != 0) goto L57
            if (r2 != 0) goto L57
            com.android.systemui.notetask.NoteTaskEntryPoint r0 = com.android.systemui.notetask.NoteTaskEntryPoint.TAIL_BUTTON
            goto L6f
        L57:
            int r0 = r9.getKeyCode()
            r1 = 42
            if (r0 != r1) goto L6e
            boolean r0 = r9.isMetaPressed()
            if (r0 == 0) goto L6e
            boolean r0 = r9.isCtrlPressed()
            if (r0 == 0) goto L6e
            com.android.systemui.notetask.NoteTaskEntryPoint r0 = com.android.systemui.notetask.NoteTaskEntryPoint.KEYBOARD_SHORTCUT
            goto L6f
        L6e:
            r0 = 0
        L6f:
            boolean r1 = android.os.Build.IS_DEBUGGABLE
            java.lang.Class r9 = r9.getClass()
            kotlin.jvm.internal.ClassReference r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)
            r9.getSimpleName()
            if (r0 == 0) goto L8e
            com.android.systemui.notetask.NoteTaskInitializer r8 = r8.this$0
            com.android.systemui.notetask.NoteTaskController r8 = r8.controller
            boolean r9 = r8.isEnabled
            if (r9 != 0) goto L87
            goto L8e
        L87:
            android.os.UserHandle r9 = r8.getUserForHandlingNotesTaking(r0)
            r8.showNoteTaskAsUser(r0, r9)
        L8e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.notetask.NoteTaskInitializer$callbacks$1.handleSystemKey(android.view.KeyEvent):void");
    }

    public final boolean isKeyGestureSupported(int i) {
        this.this$0.getClass();
        return i == 32;
    }

    @Override // com.android.systemui.settings.UserTracker.Callback
    public final void onProfilesChanged(List list) {
        this.this$0.controller.updateNoteTaskForCurrentUserAndManagedProfiles();
    }

    public final void onRoleHoldersChanged(String str, UserHandle userHandle) {
        NoteTaskController noteTaskController = this.this$0.controller;
        noteTaskController.getClass();
        if (str.equals("android.app.role.NOTES")) {
            noteTaskController.updateNoteTaskAsUser(userHandle);
        }
    }

    @Override // com.android.systemui.settings.UserTracker.Callback
    public final void onUserChanged(int i, Context context) {
        this.this$0.controller.updateNoteTaskForCurrentUserAndManagedProfiles();
    }

    @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
    public final void onUserUnlocked() {
        this.this$0.controller.updateNoteTaskForCurrentUserAndManagedProfiles();
    }
}

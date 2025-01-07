package com.android.systemui.notetask;

import android.content.ComponentName;
import android.os.Build;
import android.os.UserHandle;
import com.android.systemui.notetask.shortcut.CreateNoteTaskShortcutActivity;
import java.util.Collections;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class NoteTaskController$launchUpdateNoteTaskAsUser$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ UserHandle $user;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NoteTaskController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoteTaskController$launchUpdateNoteTaskAsUser$1(NoteTaskController noteTaskController, UserHandle userHandle, Continuation continuation) {
        super(2, continuation);
        this.this$0 = noteTaskController;
        this.$user = userHandle;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NoteTaskController$launchUpdateNoteTaskAsUser$1 noteTaskController$launchUpdateNoteTaskAsUser$1 = new NoteTaskController$launchUpdateNoteTaskAsUser$1(this.this$0, this.$user, continuation);
        noteTaskController$launchUpdateNoteTaskAsUser$1.L$0 = obj;
        return noteTaskController$launchUpdateNoteTaskAsUser$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        NoteTaskController$launchUpdateNoteTaskAsUser$1 noteTaskController$launchUpdateNoteTaskAsUser$1 = (NoteTaskController$launchUpdateNoteTaskAsUser$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        noteTaskController$launchUpdateNoteTaskAsUser$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        boolean isUserUnlocked = this.this$0.userManager.isUserUnlocked(this.$user);
        Unit unit = Unit.INSTANCE;
        if (!isUserUnlocked) {
            boolean z = Build.IS_DEBUGGABLE;
            Reflection.getOrCreateKotlinClass(coroutineScope.getClass()).getSimpleName();
            return unit;
        }
        String str = (String) CollectionsKt.firstOrNull(this.this$0.roleManager.getRoleHoldersAsUser("android.app.role.NOTES", this.$user));
        boolean z2 = (!this.this$0.isEnabled || str == null || str.length() == 0) ? false : true;
        NoteTaskController noteTaskController = this.this$0;
        UserHandle userHandle = this.$user;
        if (noteTaskController.userManager.isUserUnlocked(userHandle)) {
            noteTaskController.context.createContextAsUser(userHandle, 0).getPackageManager().setComponentEnabledSetting(new ComponentName(noteTaskController.context, (Class<?>) CreateNoteTaskShortcutActivity.class), z2 ? 1 : 2, 1);
            boolean z3 = Build.IS_DEBUGGABLE;
            Reflection.getOrCreateKotlinClass(NoteTaskController.class).getSimpleName();
        } else {
            boolean z4 = Build.IS_DEBUGGABLE;
            Reflection.getOrCreateKotlinClass(NoteTaskController.class).getSimpleName();
        }
        if (z2) {
            this.this$0.shortcutManager.enableShortcuts(Collections.singletonList("note_task_shortcut_id"));
            NoteTaskController noteTaskController2 = this.this$0;
            this.this$0.shortcutManager.updateShortcuts(Collections.singletonList(NoteTaskRoleManagerExt.createNoteShortcutInfoAsUser(noteTaskController2.roleManager, noteTaskController2.context, this.$user)));
        } else {
            this.this$0.shortcutManager.disableShortcuts(Collections.singletonList("note_task_shortcut_id"));
        }
        return unit;
    }
}

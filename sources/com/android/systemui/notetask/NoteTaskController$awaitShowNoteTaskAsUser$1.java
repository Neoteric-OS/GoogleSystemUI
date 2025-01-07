package com.android.systemui.notetask;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class NoteTaskController$awaitShowNoteTaskAsUser$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NoteTaskController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoteTaskController$awaitShowNoteTaskAsUser$1(NoteTaskController noteTaskController, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = noteTaskController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return NoteTaskController.access$awaitShowNoteTaskAsUser(this.this$0, null, null, this);
    }
}

package com.android.systemui.notetask;

import android.os.IBinder;
import android.os.IInterface;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class NoteTaskBubblesController$serviceConnector$1 implements Function {
    public static final NoteTaskBubblesController$serviceConnector$1 INSTANCE = new NoteTaskBubblesController$serviceConnector$1();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        IBinder iBinder = (IBinder) obj;
        int i = NoteTaskBubblesController$NoteTaskBubblesService$onBind$1.$r8$clinit;
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.android.systemui.notetask.INoteTaskBubblesService");
        if (queryLocalInterface != null && (queryLocalInterface instanceof INoteTaskBubblesService)) {
            return (INoteTaskBubblesService) queryLocalInterface;
        }
        INoteTaskBubblesService$Stub$Proxy iNoteTaskBubblesService$Stub$Proxy = new INoteTaskBubblesService$Stub$Proxy();
        iNoteTaskBubblesService$Stub$Proxy.mRemote = iBinder;
        return iNoteTaskBubblesService$Stub$Proxy;
    }
}

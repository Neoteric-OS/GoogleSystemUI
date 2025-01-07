package com.android.systemui.notetask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.android.internal.infra.ServiceConnector;
import java.util.Optional;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoteTaskBubblesController {
    public final CoroutineDispatcher bgDispatcher;
    public final ServiceConnector serviceConnector;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NoteTaskBubblesService extends Service {
        public final Optional mOptionalBubbles;

        public NoteTaskBubblesService(Optional optional) {
            this.mOptionalBubbles = optional;
        }

        @Override // android.app.Service
        public final IBinder onBind(Intent intent) {
            return new NoteTaskBubblesController$NoteTaskBubblesService$onBind$1(this);
        }
    }

    public NoteTaskBubblesController(Context context, CoroutineDispatcher coroutineDispatcher) {
        this.bgDispatcher = coroutineDispatcher;
        this.serviceConnector = new ServiceConnector.Impl(context, new Intent(context, (Class<?>) NoteTaskBubblesService.class), 1073741857, 0, NoteTaskBubblesController$serviceConnector$1.INSTANCE);
    }
}

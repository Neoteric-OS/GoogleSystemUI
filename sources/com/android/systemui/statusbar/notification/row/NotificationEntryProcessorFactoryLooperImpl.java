package com.android.systemui.statusbar.notification.row;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationEntryProcessorFactoryLooperImpl {
    public final Looper mMainLooper;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HandlerProcessor extends Handler {
        public final NotifBindPipeline$$ExternalSyntheticLambda0 consumer;

        public HandlerProcessor(Looper looper, NotifBindPipeline$$ExternalSyntheticLambda0 notifBindPipeline$$ExternalSyntheticLambda0) {
            super(looper);
            this.consumer = notifBindPipeline$$ExternalSyntheticLambda0;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(message.what, "Unknown message type: "));
            }
            this.consumer.accept((NotificationEntry) message.obj);
        }
    }

    public NotificationEntryProcessorFactoryLooperImpl(Looper looper) {
        this.mMainLooper = looper;
    }
}

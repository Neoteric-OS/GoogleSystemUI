package com.android.systemui.util.concurrency;

import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda7;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda8;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MessageRouterImpl {
    public final DelayableExecutor mDelayableExecutor;
    public final Map mIdMessageCancelers = new HashMap();
    public final Map mDataMessageCancelers = new HashMap();
    public final Map mSimpleMessageListenerMap = new HashMap();
    public final Map mDataMessageListenerMap = new HashMap();

    public MessageRouterImpl(DelayableExecutor delayableExecutor) {
        this.mDelayableExecutor = delayableExecutor;
    }

    public final void cancelMessages(int i) {
        synchronized (this.mIdMessageCancelers) {
            try {
                if (this.mIdMessageCancelers.containsKey(Integer.valueOf(i))) {
                    Iterator it = ((List) this.mIdMessageCancelers.get(Integer.valueOf(i))).iterator();
                    while (it.hasNext()) {
                        ((Runnable) it.next()).run();
                    }
                    this.mIdMessageCancelers.remove(Integer.valueOf(i));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void sendMessageDelayed(long j, final int i) {
        ExecutorImpl.ExecutionToken executeDelayed = this.mDelayableExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.util.concurrency.MessageRouterImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MessageRouterImpl messageRouterImpl = MessageRouterImpl.this;
                int i2 = i;
                synchronized (messageRouterImpl.mSimpleMessageListenerMap) {
                    try {
                        if (messageRouterImpl.mSimpleMessageListenerMap.containsKey(Integer.valueOf(i2))) {
                            Iterator it = ((List) messageRouterImpl.mSimpleMessageListenerMap.get(Integer.valueOf(i2))).iterator();
                            while (it.hasNext()) {
                                ((CentralSurfacesImpl$$ExternalSyntheticLambda8) it.next()).onMessage();
                            }
                        }
                    } finally {
                    }
                }
                synchronized (messageRouterImpl.mIdMessageCancelers) {
                    try {
                        if (messageRouterImpl.mIdMessageCancelers.containsKey(Integer.valueOf(i2)) && !((List) messageRouterImpl.mIdMessageCancelers.get(Integer.valueOf(i2))).isEmpty()) {
                            ((List) messageRouterImpl.mIdMessageCancelers.get(Integer.valueOf(i2))).remove(0);
                            if (((List) messageRouterImpl.mIdMessageCancelers.get(Integer.valueOf(i2))).isEmpty()) {
                                messageRouterImpl.mIdMessageCancelers.remove(Integer.valueOf(i2));
                            }
                        }
                    } finally {
                    }
                }
            }
        }, j);
        synchronized (this.mIdMessageCancelers) {
            ((HashMap) this.mIdMessageCancelers).putIfAbsent(Integer.valueOf(i), new ArrayList());
            ((List) this.mIdMessageCancelers.get(Integer.valueOf(i))).add(executeDelayed);
        }
    }

    public final void subscribeTo(int i, CentralSurfacesImpl$$ExternalSyntheticLambda8 centralSurfacesImpl$$ExternalSyntheticLambda8) {
        synchronized (this.mSimpleMessageListenerMap) {
            ((HashMap) this.mSimpleMessageListenerMap).putIfAbsent(Integer.valueOf(i), new ArrayList());
            ((List) this.mSimpleMessageListenerMap.get(Integer.valueOf(i))).add(centralSurfacesImpl$$ExternalSyntheticLambda8);
        }
    }

    public final void subscribeTo(Class cls, CentralSurfacesImpl$$ExternalSyntheticLambda7 centralSurfacesImpl$$ExternalSyntheticLambda7) {
        synchronized (this.mDataMessageListenerMap) {
            ((HashMap) this.mDataMessageListenerMap).putIfAbsent(cls, new ArrayList());
            ((List) this.mDataMessageListenerMap.get(cls)).add(centralSurfacesImpl$$ExternalSyntheticLambda7);
        }
    }
}

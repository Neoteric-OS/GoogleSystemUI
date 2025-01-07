package com.android.wm.shell.apptoweb;

import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.app.IAssistDataReceiver;
import android.app.assist.AssistContent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Slog;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda12;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AssistContentRequester {
    public final String attributionTag;
    public final ShellExecutor callBackExecutor;
    public final String packageName;
    public final ShellExecutor systemInteractionExecutor;
    public final IActivityTaskManager activityTaskManager = ActivityTaskManager.getService();
    public final Map pendingCallbacks = Collections.synchronizedMap(new WeakHashMap());

    public AssistContentRequester(Context context, ShellExecutor shellExecutor, ShellExecutor shellExecutor2) {
        this.callBackExecutor = shellExecutor;
        this.systemInteractionExecutor = shellExecutor2;
        this.attributionTag = context.getAttributionTag();
        this.packageName = context.getApplicationContext().getPackageName();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AssistDataReceiver extends IAssistDataReceiver.Stub {
        public final Object callbackKey;
        public final WeakReference parentRef;

        public AssistDataReceiver(DesktopModeWindowDecoration$$ExternalSyntheticLambda12 desktopModeWindowDecoration$$ExternalSyntheticLambda12, AssistContentRequester assistContentRequester) {
            Object obj = new Object();
            this.callbackKey = obj;
            assistContentRequester.pendingCallbacks.put(obj, desktopModeWindowDecoration$$ExternalSyntheticLambda12);
            this.parentRef = new WeakReference(assistContentRequester);
        }

        public final void onHandleAssistData(Bundle bundle) {
            final AssistContent assistContent = bundle != null ? (AssistContent) bundle.getParcelable("content", AssistContent.class) : null;
            if (assistContent == null) {
                Slog.d("AssistContentRequester", "Received AssistData, but no AssistContent found");
                return;
            }
            AssistContentRequester assistContentRequester = (AssistContentRequester) this.parentRef.get();
            if (assistContentRequester == null) {
                Slog.d("AssistContentRequester", "Callback received after Requester was collected");
                return;
            }
            final DesktopModeWindowDecoration$$ExternalSyntheticLambda12 desktopModeWindowDecoration$$ExternalSyntheticLambda12 = (DesktopModeWindowDecoration$$ExternalSyntheticLambda12) assistContentRequester.pendingCallbacks.get(this.callbackKey);
            if (desktopModeWindowDecoration$$ExternalSyntheticLambda12 != null) {
                ((HandlerExecutor) assistContentRequester.callBackExecutor).execute(new Runnable() { // from class: com.android.wm.shell.apptoweb.AssistContentRequester$AssistDataReceiver$onHandleAssistData$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        DesktopModeWindowDecoration$$ExternalSyntheticLambda12 desktopModeWindowDecoration$$ExternalSyntheticLambda122 = DesktopModeWindowDecoration$$ExternalSyntheticLambda12.this;
                        desktopModeWindowDecoration$$ExternalSyntheticLambda122.f$0.onAssistContentReceived(assistContent);
                    }
                });
            } else {
                Slog.d("AssistContentRequester", "Callback received after calling UI was disposed of");
            }
        }

        public final void onHandleAssistScreenshot(Bitmap bitmap) {
        }
    }
}

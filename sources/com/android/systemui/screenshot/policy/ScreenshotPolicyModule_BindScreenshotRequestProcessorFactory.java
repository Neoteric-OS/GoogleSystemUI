package com.android.systemui.screenshot.policy;

import android.content.ComponentName;
import android.content.Context;
import android.os.Process;
import com.android.systemui.SystemUIService;
import com.android.systemui.screenshot.ImageCaptureImpl;
import com.android.systemui.screenshot.data.repository.DisplayContentRepositoryImpl;
import dagger.internal.Provider;
import java.util.List;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ScreenshotPolicyModule_BindScreenshotRequestProcessorFactory implements Provider {
    public static PolicyRequestProcessor bindScreenshotRequestProcessor(Context context, CoroutineDispatcher coroutineDispatcher, ImageCaptureImpl imageCaptureImpl, DisplayContentRepositoryImpl displayContentRepositoryImpl, Provider provider) {
        return new PolicyRequestProcessor(coroutineDispatcher, imageCaptureImpl, displayContentRepositoryImpl, (List) provider.get(), Process.myUserHandle(), new ComponentName(context.getPackageName(), SystemUIService.class.toString()));
    }
}

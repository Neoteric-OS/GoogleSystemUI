package com.android.systemui.media.controls.domain.resume;

import android.content.ComponentName;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import com.android.systemui.media.controls.domain.resume.ResumeMediaBrowser;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaResumeListener$getResumeAction$1 implements Runnable {
    public final /* synthetic */ ComponentName $componentName;
    public final /* synthetic */ MediaResumeListener this$0;

    public MediaResumeListener$getResumeAction$1(MediaResumeListener mediaResumeListener, ComponentName componentName) {
        this.this$0 = mediaResumeListener;
        this.$componentName = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaResumeListener mediaResumeListener = this.this$0;
        ResumeMediaBrowserFactory resumeMediaBrowserFactory = mediaResumeListener.mediaBrowserFactory;
        mediaResumeListener.setMediaBrowser(new ResumeMediaBrowser(resumeMediaBrowserFactory.mContext, null, this.$componentName, resumeMediaBrowserFactory.mBrowserFactory, resumeMediaBrowserFactory.mLogger, mediaResumeListener.currentUserId));
        ResumeMediaBrowser resumeMediaBrowser = this.this$0.mediaBrowser;
        if (resumeMediaBrowser != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("android.service.media.extra.RECENT", true);
            ComponentName componentName = resumeMediaBrowser.mComponentName;
            ResumeMediaBrowser.AnonymousClass2 anonymousClass2 = new ResumeMediaBrowser.AnonymousClass2(resumeMediaBrowser, 1);
            MediaBrowserFactory mediaBrowserFactory = resumeMediaBrowser.mBrowserFactory;
            mediaBrowserFactory.getClass();
            resumeMediaBrowser.connectBrowser(new MediaBrowser(mediaBrowserFactory.mContext, componentName, anonymousClass2, bundle), "restart");
        }
    }
}

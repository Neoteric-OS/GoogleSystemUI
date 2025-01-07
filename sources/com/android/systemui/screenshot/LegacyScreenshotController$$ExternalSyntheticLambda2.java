package com.android.systemui.screenshot;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.android.systemui.screenshot.scroll.LongScreenshotActivity;
import java.util.Objects;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class LegacyScreenshotController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LegacyScreenshotController f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ LegacyScreenshotController$$ExternalSyntheticLambda2(LegacyScreenshotController legacyScreenshotController, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = legacyScreenshotController;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                LegacyScreenshotController legacyScreenshotController = this.f$0;
                legacyScreenshotController.mContext.startActivity(new Intent((Context) legacyScreenshotController.mContext, (Class<?>) LongScreenshotActivity.class).putExtra("screenshot-userhandle", (UserHandle) this.f$1).addFlags(268435456).addFlags(67108864).addFlags(65536));
                break;
            case 1:
                LegacyScreenshotController legacyScreenshotController2 = this.f$0;
                ScreenshotData screenshotData = (ScreenshotData) this.f$1;
                MessageContainerController messageContainerController = legacyScreenshotController2.mMessageContainerController;
                messageContainerController.getClass();
                BuildersKt.launch$default(messageContainerController.mainScope, null, null, new MessageContainerController$onScreenshotTaken$1(messageContainerController, screenshotData, null), 3);
                break;
            default:
                LegacyScreenshotController legacyScreenshotController3 = this.f$0;
                ScreenshotData screenshotData2 = (ScreenshotData) this.f$1;
                legacyScreenshotController3.getClass();
                int identifier = screenshotData2.userHandle.getIdentifier();
                ScreenshotShelfViewProxy screenshotShelfViewProxy = legacyScreenshotController3.mViewProxy;
                Objects.requireNonNull(screenshotShelfViewProxy);
                LegacyScreenshotController$$ExternalSyntheticLambda15 legacyScreenshotController$$ExternalSyntheticLambda15 = new LegacyScreenshotController$$ExternalSyntheticLambda15(1, screenshotShelfViewProxy);
                AnnouncementResolver announcementResolver = legacyScreenshotController3.mAnnouncementResolver;
                announcementResolver.getClass();
                BuildersKt.launch$default(announcementResolver.mainScope, null, null, new AnnouncementResolver$getScreenshotAnnouncement$2(legacyScreenshotController$$ExternalSyntheticLambda15, announcementResolver, identifier, null), 3);
                break;
        }
    }
}

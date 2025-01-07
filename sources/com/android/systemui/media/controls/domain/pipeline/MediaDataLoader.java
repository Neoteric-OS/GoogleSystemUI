package com.android.systemui.media.controls.domain.pipeline;

import android.R;
import android.app.PendingIntent;
import android.app.StatusBarManager;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.graphics.ImageLoader;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.util.MediaControllerFactory;
import com.android.systemui.media.controls.util.MediaFlags;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DeferredCoroutine;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDataLoader {
    public static final String[] ART_URIS = {"android.media.metadata.ALBUM_ART_URI", "android.media.metadata.ART_URI", "android.media.metadata.DISPLAY_ICON_URI"};
    public final int artworkHeight;
    public final int artworkWidth;
    public final CoroutineScope backgroundScope;
    public final Context context;
    public final ImageLoader imageLoader;
    public final MediaControllerFactory mediaControllerFactory;
    public final MediaFlags mediaFlags;
    public final ConcurrentHashMap mediaProcessingJobs = new ConcurrentHashMap();
    public final StatusBarManager statusBarManager;
    public final int themeText;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaDataLoaderResult {
        public final List actionIcons;
        public final List actionsToShowInCompact;
        public final Icon appIcon;
        public final String appName;
        public final int appUid;
        public final CharSequence artist;
        public final Icon artworkIcon;
        public final PendingIntent clickIntent;
        public final MediaDeviceData device;
        public final boolean isExplicit;
        public final Boolean isPlaying;
        public final int playbackLocation;
        public final Runnable resumeAction;
        public final Double resumeProgress;
        public final MediaButton semanticActions;
        public final CharSequence song;
        public final MediaSession.Token token;

        public MediaDataLoaderResult(String str, Icon icon, CharSequence charSequence, CharSequence charSequence2, Icon icon2, List list, List list2, MediaButton mediaButton, MediaSession.Token token, PendingIntent pendingIntent, MediaDeviceData mediaDeviceData, int i, Boolean bool, int i2, boolean z, Runnable runnable, Double d) {
            this.appName = str;
            this.appIcon = icon;
            this.artist = charSequence;
            this.song = charSequence2;
            this.artworkIcon = icon2;
            this.actionIcons = list;
            this.actionsToShowInCompact = list2;
            this.semanticActions = mediaButton;
            this.token = token;
            this.clickIntent = pendingIntent;
            this.device = mediaDeviceData;
            this.playbackLocation = i;
            this.isPlaying = bool;
            this.appUid = i2;
            this.isExplicit = z;
            this.resumeAction = runnable;
            this.resumeProgress = d;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaDataLoaderResult)) {
                return false;
            }
            MediaDataLoaderResult mediaDataLoaderResult = (MediaDataLoaderResult) obj;
            return Intrinsics.areEqual(this.appName, mediaDataLoaderResult.appName) && Intrinsics.areEqual(this.appIcon, mediaDataLoaderResult.appIcon) && Intrinsics.areEqual(this.artist, mediaDataLoaderResult.artist) && Intrinsics.areEqual(this.song, mediaDataLoaderResult.song) && Intrinsics.areEqual(this.artworkIcon, mediaDataLoaderResult.artworkIcon) && Intrinsics.areEqual(this.actionIcons, mediaDataLoaderResult.actionIcons) && Intrinsics.areEqual(this.actionsToShowInCompact, mediaDataLoaderResult.actionsToShowInCompact) && Intrinsics.areEqual(this.semanticActions, mediaDataLoaderResult.semanticActions) && Intrinsics.areEqual(this.token, mediaDataLoaderResult.token) && Intrinsics.areEqual(this.clickIntent, mediaDataLoaderResult.clickIntent) && Intrinsics.areEqual(this.device, mediaDataLoaderResult.device) && this.playbackLocation == mediaDataLoaderResult.playbackLocation && Intrinsics.areEqual(this.isPlaying, mediaDataLoaderResult.isPlaying) && this.appUid == mediaDataLoaderResult.appUid && this.isExplicit == mediaDataLoaderResult.isExplicit && Intrinsics.areEqual(this.resumeAction, mediaDataLoaderResult.resumeAction) && Intrinsics.areEqual(this.resumeProgress, mediaDataLoaderResult.resumeProgress);
        }

        public final int hashCode() {
            String str = this.appName;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            Icon icon = this.appIcon;
            int hashCode2 = (hashCode + (icon == null ? 0 : icon.hashCode())) * 31;
            CharSequence charSequence = this.artist;
            int hashCode3 = (hashCode2 + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
            CharSequence charSequence2 = this.song;
            int hashCode4 = (hashCode3 + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31;
            Icon icon2 = this.artworkIcon;
            int m = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m((hashCode4 + (icon2 == null ? 0 : icon2.hashCode())) * 31, 31, this.actionIcons), 31, this.actionsToShowInCompact);
            MediaButton mediaButton = this.semanticActions;
            int hashCode5 = (m + (mediaButton == null ? 0 : mediaButton.hashCode())) * 31;
            MediaSession.Token token = this.token;
            int hashCode6 = (hashCode5 + (token == null ? 0 : token.hashCode())) * 31;
            PendingIntent pendingIntent = this.clickIntent;
            int hashCode7 = (hashCode6 + (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 31;
            MediaDeviceData mediaDeviceData = this.device;
            int m2 = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.playbackLocation, (hashCode7 + (mediaDeviceData == null ? 0 : mediaDeviceData.hashCode())) * 31, 31);
            Boolean bool = this.isPlaying;
            int m3 = TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.appUid, (m2 + (bool == null ? 0 : bool.hashCode())) * 31, 31), 31, this.isExplicit);
            Runnable runnable = this.resumeAction;
            int hashCode8 = (m3 + (runnable == null ? 0 : runnable.hashCode())) * 31;
            Double d = this.resumeProgress;
            return hashCode8 + (d != null ? d.hashCode() : 0);
        }

        public final String toString() {
            Icon icon = this.appIcon;
            CharSequence charSequence = this.artist;
            CharSequence charSequence2 = this.song;
            return "MediaDataLoaderResult(appName=" + this.appName + ", appIcon=" + icon + ", artist=" + ((Object) charSequence) + ", song=" + ((Object) charSequence2) + ", artworkIcon=" + this.artworkIcon + ", actionIcons=" + this.actionIcons + ", actionsToShowInCompact=" + this.actionsToShowInCompact + ", semanticActions=" + this.semanticActions + ", token=" + this.token + ", clickIntent=" + this.clickIntent + ", device=" + this.device + ", playbackLocation=" + this.playbackLocation + ", isPlaying=" + this.isPlaying + ", appUid=" + this.appUid + ", isExplicit=" + this.isExplicit + ", resumeAction=" + this.resumeAction + ", resumeProgress=" + this.resumeProgress + ")";
        }
    }

    public MediaDataLoader(Context context, CoroutineScope coroutineScope, MediaControllerFactory mediaControllerFactory, MediaFlags mediaFlags, ImageLoader imageLoader, StatusBarManager statusBarManager) {
        this.context = context;
        this.backgroundScope = coroutineScope;
        this.mediaControllerFactory = mediaControllerFactory;
        this.mediaFlags = mediaFlags;
        this.imageLoader = imageLoader;
        this.statusBarManager = statusBarManager;
        this.artworkWidth = context.getResources().getDimensionPixelSize(R.dimen.config_minScrollbarTouchTarget);
        this.artworkHeight = context.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.qs_media_session_height_expanded);
        this.themeText = Utils.getColorAttr(R.attr.textColorPrimary, context).getDefaultColor();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0121 A[Catch: all -> 0x0114, TryCatch #0 {all -> 0x0114, blocks: (B:40:0x010d, B:18:0x011a, B:20:0x0121, B:23:0x0132, B:25:0x0143, B:26:0x014b), top: B:39:0x010d }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0143 A[Catch: all -> 0x0114, TryCatch #0 {all -> 0x0114, blocks: (B:40:0x010d, B:18:0x011a, B:20:0x0121, B:23:0x0132, B:25:0x0143, B:26:0x014b), top: B:39:0x010d }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x010d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0031  */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v16, types: [com.android.app.tracing.coroutines.TraceData] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$loadMediaDataForResumptionInBackground(com.android.systemui.media.controls.domain.pipeline.MediaDataLoader r30, int r31, android.media.MediaDescription r32, java.lang.Runnable r33, com.android.systemui.media.controls.shared.model.MediaData r34, android.media.session.MediaSession.Token r35, java.lang.String r36, android.app.PendingIntent r37, java.lang.String r38, kotlin.coroutines.jvm.internal.ContinuationImpl r39) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.domain.pipeline.MediaDataLoader.access$loadMediaDataForResumptionInBackground(com.android.systemui.media.controls.domain.pipeline.MediaDataLoader, int, android.media.MediaDescription, java.lang.Runnable, com.android.systemui.media.controls.shared.model.MediaData, android.media.session.MediaSession$Token, java.lang.String, android.app.PendingIntent, java.lang.String, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:96|(3:98|99|100)|104|(1:(1:107)(1:108))|(1:110)(1:146)|(1:(1:144)(1:145))|(1:142)|(3:121|122|(2:124|(2:126|127)(28:128|14|(0)|(0)|(0)(0)|25|26|(0)(0)|(1:30)|78|(0)(0)|(0)|76|39|40|(0)(0)|43|(0)|(0)(0)|51|(0)|53|54|(0)(0)|(0)(0)|59|60|(0)(0)))(27:129|(0)|(0)|(0)(0)|25|26|(0)(0)|(0)|78|(0)(0)|(0)|76|39|40|(0)(0)|43|(0)|(0)(0)|51|(0)|53|54|(0)(0)|(0)(0)|59|60|(0)(0)))|130|131|132|133|134|135|136|122|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x01a3, code lost:
    
        android.util.Log.e("MediaDataLoader", "Error reporting blank media title for package " + r10.getPackageName());
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x01a1, code lost:
    
        r39 = r3;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01c1 A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0224 A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0243 A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0261 A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0278 A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0282 A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x02bc A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x02cf A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x032a A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x033d A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0312 A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02af A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x022b A[Catch: all -> 0x006e, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00e2 A[Catch: all -> 0x006e, TRY_LEAVE, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00ee A[Catch: all -> 0x006e, TRY_ENTER, TryCatch #6 {all -> 0x006e, blocks: (B:12:0x0063, B:14:0x01f1, B:17:0x020e, B:21:0x021a, B:24:0x0224, B:26:0x0230, B:28:0x0243, B:30:0x0261, B:34:0x0278, B:36:0x0282, B:40:0x0291, B:43:0x02b5, B:45:0x02bc, B:47:0x02cf, B:49:0x02e7, B:51:0x0301, B:54:0x0324, B:56:0x032a, B:58:0x033d, B:60:0x0344, B:68:0x0312, B:70:0x0318, B:75:0x02af, B:76:0x028c, B:80:0x022b, B:89:0x00cf, B:91:0x00e2, B:96:0x00ee, B:98:0x010e, B:100:0x0112, B:103:0x011f, B:104:0x0127, B:107:0x0137, B:108:0x0146, B:110:0x014c, B:112:0x0156, B:115:0x0168, B:118:0x0174, B:122:0x01b8, B:124:0x01c1, B:130:0x017e, B:132:0x018d, B:135:0x0199, B:139:0x01a3, B:142:0x016e, B:144:0x015e), top: B:7:0x0031, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0033  */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r1v34, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$loadMediaDataInBackground(com.android.systemui.media.controls.domain.pipeline.MediaDataLoader r39, java.lang.String r40, android.service.notification.StatusBarNotification r41, boolean r42, kotlin.coroutines.jvm.internal.ContinuationImpl r43) {
        /*
            Method dump skipped, instructions count: 863
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.domain.pipeline.MediaDataLoader.access$loadMediaDataInBackground(com.android.systemui.media.controls.domain.pipeline.MediaDataLoader, java.lang.String, android.service.notification.StatusBarNotification, boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final MediaDeviceData getDeviceInfoForRemoteCast(StatusBarNotification statusBarNotification, String str) {
        Bundle bundle = statusBarNotification.getNotification().extras;
        CharSequence charSequence = bundle.getCharSequence("android.mediaRemoteDevice", null);
        int i = bundle.getInt("android.mediaRemoteIcon", -1);
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("android.mediaRemoteIntent", PendingIntent.class);
        if (Log.isLoggable("MediaDataLoader", 3)) {
            Log.d("MediaDataLoader", str + " is RCN for " + ((Object) charSequence));
        }
        if (charSequence == null || i <= -1) {
            return null;
        }
        return new MediaDeviceData(pendingIntent != null && pendingIntent.isActivity(), Icon.createWithResource(statusBarNotification.getPackageName(), i).loadDrawable(statusBarNotification.getPackageContext(this.context)), charSequence, pendingIntent, null, false, 16);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0099 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x005a -> B:18:0x0097). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0075 -> B:10:0x007b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadBitmapFromUri(android.media.MediaMetadata r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadBitmapFromUri$1
            if (r0 == 0) goto L13
            r0 = r12
            com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadBitmapFromUri$1 r0 = (com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadBitmapFromUri$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadBitmapFromUri$1 r0 = new com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadBitmapFromUri$1
            r0.<init>(r10, r12)
        L18:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 3
            r4 = 1
            if (r2 == 0) goto L44
            if (r2 != r4) goto L3c
            int r10 = r0.I$1
            int r11 = r0.I$0
            java.lang.Object r2 = r0.L$3
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r5 = r0.L$2
            java.lang.String[] r5 = (java.lang.String[]) r5
            java.lang.Object r6 = r0.L$1
            android.media.MediaMetadata r6 = (android.media.MediaMetadata) r6
            java.lang.Object r7 = r0.L$0
            com.android.systemui.media.controls.domain.pipeline.MediaDataLoader r7 = (com.android.systemui.media.controls.domain.pipeline.MediaDataLoader) r7
            kotlin.ResultKt.throwOnFailure(r12)
            goto L7b
        L3c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L44:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.String[] r12 = com.android.systemui.media.controls.domain.pipeline.MediaDataLoader.ART_URIS
            r2 = 0
            r5 = r12
            r12 = r11
            r11 = r10
            r10 = r3
        L4e:
            if (r2 >= r10) goto L99
            r6 = r5[r2]
            java.lang.String r7 = r12.getString(r6)
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L97
            android.net.Uri r7 = android.net.Uri.parse(r7)
            r0.L$0 = r11
            r0.L$1 = r12
            r0.L$2 = r5
            r0.L$3 = r6
            r0.I$0 = r2
            r0.I$1 = r10
            r0.label = r4
            java.lang.Object r7 = r11.loadBitmapFromUri(r7, r0)
            if (r7 != r1) goto L75
            return r1
        L75:
            r9 = r7
            r7 = r11
            r11 = r2
            r2 = r6
            r6 = r12
            r12 = r9
        L7b:
            android.graphics.Bitmap r12 = (android.graphics.Bitmap) r12
            kotlin.coroutines.CoroutineContext r8 = r0.getContext()
            kotlinx.coroutines.JobKt.ensureActive(r8)
            if (r12 == 0) goto L94
            java.lang.String r10 = "MediaDataLoader"
            boolean r11 = android.util.Log.isLoggable(r10, r3)
            if (r11 == 0) goto L93
            java.lang.String r11 = "loaded art from "
            androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0.m(r11, r2, r10)
        L93:
            return r12
        L94:
            r2 = r11
            r12 = r6
            r11 = r7
        L97:
            int r2 = r2 + r4
            goto L4e
        L99:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.domain.pipeline.MediaDataLoader.loadBitmapFromUri(android.media.MediaMetadata, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadBitmapFromUriForUser(android.net.Uri r11, int r12, int r13, java.lang.String r14, kotlin.coroutines.jvm.internal.ContinuationImpl r15) {
        /*
            r10 = this;
            boolean r0 = r15 instanceof com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadBitmapFromUriForUser$1
            if (r0 == 0) goto L13
            r0 = r15
            com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadBitmapFromUriForUser$1 r0 = (com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadBitmapFromUriForUser$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadBitmapFromUriForUser$1 r0 = new com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadBitmapFromUriForUser$1
            r0.<init>(r10, r15)
        L18:
            java.lang.Object r15 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.lang.SecurityException -> L27
            goto L4f
        L27:
            r10 = move-exception
            goto L50
        L29:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L31:
            kotlin.ResultKt.throwOnFailure(r15)
            android.app.IUriGrantsManager r4 = android.app.UriGrantsManager.getService()     // Catch: java.lang.SecurityException -> L27
            android.net.Uri r7 = android.content.ContentProvider.getUriWithoutUserId(r11)     // Catch: java.lang.SecurityException -> L27
            int r9 = android.content.ContentProvider.getUserIdFromUri(r11, r12)     // Catch: java.lang.SecurityException -> L27
            r8 = 1
            r5 = r13
            r6 = r14
            r4.checkGrantUriPermission_ignoreNonSystem(r5, r6, r7, r8, r9)     // Catch: java.lang.SecurityException -> L27
            r0.label = r3     // Catch: java.lang.SecurityException -> L27
            java.lang.Object r15 = r10.loadBitmapFromUri(r11, r0)     // Catch: java.lang.SecurityException -> L27
            if (r15 != r1) goto L4f
            return r1
        L4f:
            return r15
        L50:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "Failed to get URI permission: "
            r11.<init>(r12)
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            java.lang.String r11 = "MediaDataLoader"
            android.util.Log.e(r11, r10)
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.domain.pipeline.MediaDataLoader.loadBitmapFromUriForUser(android.net.Uri, int, int, java.lang.String, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object loadMediaData(final String str, StatusBarNotification statusBarNotification, boolean z, SuspendLambda suspendLambda) {
        Object obj = null;
        final DeferredCoroutine async$default = BuildersKt.async$default(this.backgroundScope, null, new MediaDataLoader$loadMediaData$loadMediaJob$1(this, str, statusBarNotification, z, null), 3);
        async$default.invokeOnCompletion(new Function1() { // from class: com.android.systemui.media.controls.domain.pipeline.MediaDataLoader$loadMediaData$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                MediaDataLoader.this.mediaProcessingJobs.remove(str, async$default);
                return Unit.INSTANCE;
            }
        });
        if (!z) {
            obj = this.mediaProcessingJobs.put(str, async$default);
            Job job = (Job) obj;
            if (job != null) {
                job.cancel(ExceptionsKt.CancellationException("New processing job incoming.", null));
            }
        }
        if (Log.isLoggable("MediaDataLoader", 3)) {
            Log.d("MediaDataLoader", "Loading media data for " + str + "... / existing job: " + obj);
        }
        Object awaitInternal = async$default.awaitInternal(suspendLambda);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return awaitInternal;
    }

    public final Object loadBitmapFromUri(Uri uri, ContinuationImpl continuationImpl) {
        if (!CollectionsKt.contains(CollectionsKt__CollectionsKt.listOf("content", "android.resource", "file"), uri.getScheme())) {
            Log.w("MediaDataLoader", "Invalid album art uri " + uri);
            return null;
        }
        return this.imageLoader.loadBitmap(new ImageLoader.Uri(uri), this.artworkWidth, this.artworkHeight, continuationImpl);
    }
}

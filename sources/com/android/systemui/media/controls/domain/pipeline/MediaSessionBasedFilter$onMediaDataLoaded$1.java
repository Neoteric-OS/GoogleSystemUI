package com.android.systemui.media.controls.domain.pipeline;

import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.util.Log;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.domain.pipeline.MediaSessionBasedFilter;
import com.android.systemui.media.controls.shared.model.MediaData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaSessionBasedFilter$onMediaDataLoaded$1 implements Runnable {
    public final /* synthetic */ MediaData $data;
    public final /* synthetic */ boolean $immediately;
    public final /* synthetic */ String $key;
    public final /* synthetic */ String $oldKey;
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ MediaSessionBasedFilter this$0;

    public MediaSessionBasedFilter$onMediaDataLoaded$1(MediaSessionBasedFilter mediaSessionBasedFilter, String str, String str2, MediaData mediaData, boolean z) {
        this.this$0 = mediaSessionBasedFilter;
        this.$oldKey = str;
        this.$key = str2;
        this.$data = mediaData;
        this.$immediately = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList;
        switch (this.$r8$classId) {
            case 0:
                MediaSession.Token token = this.$data.token;
                if (token != null) {
                    this.this$0.tokensWithNotifications.add(new MediaSessionBasedFilter.TokenId(token));
                }
                String str = this.$oldKey;
                boolean z = (str == null || Intrinsics.areEqual(this.$key, str)) ? false : true;
                if (z) {
                    Set set = (Set) TypeIntrinsics.asMutableMap(this.this$0.keyedTokens).remove(this.$oldKey);
                    if (set != null) {
                    }
                }
                if (this.$data.token != null) {
                    Set set2 = (Set) this.this$0.keyedTokens.get(this.$key);
                    if (set2 != null) {
                        set2.add(new MediaSessionBasedFilter.TokenId(this.$data.token));
                    } else {
                        MediaSessionBasedFilter mediaSessionBasedFilter = this.this$0;
                        MediaData mediaData = this.$data;
                        String str2 = this.$key;
                        MediaSessionBasedFilter.TokenId[] tokenIdArr = {new MediaSessionBasedFilter.TokenId(mediaData.token)};
                        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(1));
                        linkedHashSet.add(tokenIdArr[0]);
                    }
                }
                List list = (List) this.this$0.packageControllers.get(this.$data.packageName);
                MediaController mediaController = null;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (Object obj : list) {
                        MediaController.PlaybackInfo playbackInfo = ((MediaController) obj).getPlaybackInfo();
                        if (playbackInfo != null && playbackInfo.getPlaybackType() == 2) {
                            arrayList.add(obj);
                        }
                    }
                } else {
                    arrayList = null;
                }
                if (arrayList != null && arrayList.size() == 1) {
                    mediaController = (MediaController) CollectionsKt.firstOrNull((List) arrayList);
                }
                if (!z && mediaController != null && !Intrinsics.areEqual(mediaController.getSessionToken(), this.$data.token) && this.this$0.tokensWithNotifications.contains(new MediaSessionBasedFilter.TokenId(mediaController.getSessionToken()))) {
                    Log.d("MediaSessionBasedFilter", "filtering key=" + this.$key + " local=" + this.$data.token + " remote=" + mediaController.getSessionToken());
                    Object obj2 = this.this$0.keyedTokens.get(this.$key);
                    Intrinsics.checkNotNull(obj2);
                    if (!((Set) obj2).contains(new MediaSessionBasedFilter.TokenId(mediaController.getSessionToken()))) {
                        MediaSessionBasedFilter mediaSessionBasedFilter2 = this.this$0;
                        mediaSessionBasedFilter2.foregroundExecutor.execute(new MediaSessionBasedFilter$onMediaDataRemoved$1(mediaSessionBasedFilter2, this.$key, false, 1));
                        break;
                    }
                } else {
                    MediaSessionBasedFilter mediaSessionBasedFilter3 = this.this$0;
                    mediaSessionBasedFilter3.foregroundExecutor.execute(new MediaSessionBasedFilter$onMediaDataLoaded$1(mediaSessionBasedFilter3, this.$key, this.$oldKey, this.$data, this.$immediately));
                    break;
                }
                break;
            default:
                Set set3 = CollectionsKt.toSet(this.this$0.listeners);
                String str3 = this.$oldKey;
                String str4 = this.$key;
                MediaData mediaData2 = this.$data;
                boolean z2 = this.$immediately;
                Iterator it = set3.iterator();
                while (it.hasNext()) {
                    MediaDataManager.Listener.onMediaDataLoaded$default((MediaDataManager.Listener) it.next(), str3, str4, mediaData2, z2, 0, false, 48);
                }
                break;
        }
    }

    public MediaSessionBasedFilter$onMediaDataLoaded$1(MediaData mediaData, String str, String str2, MediaSessionBasedFilter mediaSessionBasedFilter, boolean z) {
        this.$data = mediaData;
        this.$oldKey = str;
        this.$key = str2;
        this.this$0 = mediaSessionBasedFilter;
        this.$immediately = z;
    }
}

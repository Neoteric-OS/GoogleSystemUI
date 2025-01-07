package com.android.systemui.statusbar.notification.row;

import android.R;
import android.app.ActivityManager;
import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import com.android.internal.widget.ImageResolver;
import com.android.internal.widget.LocalImageResolver;
import com.android.internal.widget.MessagingMessage;
import com.android.systemui.statusbar.notification.row.NotificationInlineImageCache;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationInlineImageResolver implements ImageResolver {
    public final Context mContext;
    public final NotificationInlineImageCache mImageCache;
    protected int mMaxImageHeight;
    protected int mMaxImageWidth;
    public Set mWantedUriSet;

    public NotificationInlineImageResolver(Context context, NotificationInlineImageCache notificationInlineImageCache) {
        this.mContext = context;
        this.mImageCache = notificationInlineImageCache;
        notificationInlineImageCache.mResolver = this;
        this.mMaxImageWidth = getMaxImageWidth();
        this.mMaxImageHeight = getMaxImageHeight();
    }

    public final void cancelRunningTasks() {
        if (hasCache()) {
            this.mImageCache.mCache.forEach(new NotificationInlineImageCache$$ExternalSyntheticLambda1());
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getMaxImageHeight() {
        return this.mContext.getResources().getDimensionPixelSize(ActivityManager.isLowRamDeviceStatic() ? R.dimen.notification_header_height : R.dimen.notification_header_expand_icon_size);
    }

    public int getMaxImageWidth() {
        return this.mContext.getResources().getDimensionPixelSize(ActivityManager.isLowRamDeviceStatic() ? R.dimen.notification_header_icon_size_ambient : R.dimen.notification_header_icon_size);
    }

    public final boolean hasCache() {
        return !ActivityManager.isLowRamDeviceStatic();
    }

    public final Drawable loadImage(Uri uri) {
        return hasCache() ? loadImageFromCache(uri, 100L) : resolveImage(uri);
    }

    public final Drawable loadImageFromCache(Uri uri, long j) {
        if (!this.mImageCache.mCache.containsKey(uri)) {
            NotificationInlineImageCache notificationInlineImageCache = this.mImageCache;
            NotificationInlineImageCache.PreloadImageTask preloadImageTask = new NotificationInlineImageCache.PreloadImageTask(notificationInlineImageCache.mResolver);
            preloadImageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
            notificationInlineImageCache.mCache.put(uri, preloadImageTask);
        }
        try {
            return (Drawable) ((NotificationInlineImageCache.PreloadImageTask) this.mImageCache.mCache.get(uri)).get(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | CancellationException | ExecutionException | TimeoutException e) {
            Log.d("NotificationInlineImageCache", "get: Failed get image from " + uri + " " + e);
            return null;
        }
    }

    public final void preloadImages(Notification notification) {
        if (hasCache()) {
            HashSet hashSet = new HashSet();
            Bundle bundle = notification.extras;
            if (bundle != null) {
                Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
                List<Notification.MessagingStyle.Message> messagesFromBundleArray = parcelableArray == null ? null : Notification.MessagingStyle.Message.getMessagesFromBundleArray(parcelableArray);
                if (messagesFromBundleArray != null) {
                    for (Notification.MessagingStyle.Message message : messagesFromBundleArray) {
                        if (MessagingMessage.hasImage(message)) {
                            hashSet.add(message.getDataUri());
                        }
                    }
                }
                Parcelable[] parcelableArray2 = bundle.getParcelableArray("android.messages.historic");
                List<Notification.MessagingStyle.Message> messagesFromBundleArray2 = parcelableArray2 != null ? Notification.MessagingStyle.Message.getMessagesFromBundleArray(parcelableArray2) : null;
                if (messagesFromBundleArray2 != null) {
                    for (Notification.MessagingStyle.Message message2 : messagesFromBundleArray2) {
                        if (MessagingMessage.hasImage(message2)) {
                            hashSet.add(message2.getDataUri());
                        }
                    }
                }
                this.mWantedUriSet = hashSet;
            }
            this.mWantedUriSet.forEach(new Consumer() { // from class: com.android.systemui.statusbar.notification.row.NotificationInlineImageResolver$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    NotificationInlineImageResolver notificationInlineImageResolver = NotificationInlineImageResolver.this;
                    Uri uri = (Uri) obj;
                    if (notificationInlineImageResolver.mImageCache.mCache.containsKey(uri)) {
                        return;
                    }
                    NotificationInlineImageCache notificationInlineImageCache = notificationInlineImageResolver.mImageCache;
                    NotificationInlineImageCache.PreloadImageTask preloadImageTask = new NotificationInlineImageCache.PreloadImageTask(notificationInlineImageCache.mResolver);
                    preloadImageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uri);
                    notificationInlineImageCache.mCache.put(uri, preloadImageTask);
                }
            });
        }
    }

    public final Drawable resolveImage(Uri uri) {
        try {
            return LocalImageResolver.resolveImage(uri, this.mContext, this.mMaxImageWidth, this.mMaxImageHeight);
        } catch (Exception e) {
            Log.d("NotificationInlineImageResolver", "resolveImage: Can't load image from " + uri, e);
            return null;
        }
    }
}

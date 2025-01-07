package com.android.systemui.mediaprojection.appselector;

import android.R;
import android.content.Context;
import android.os.UserHandle;
import com.android.internal.app.AbstractMultiProfilePagerAdapter;
import com.android.internal.app.ResolverListAdapter;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaProjectionBlockerEmptyStateProvider implements AbstractMultiProfilePagerAdapter.EmptyStateProvider {
    public final Context context;
    public final UserHandle hostAppHandle;
    public final UserHandle personalProfileHandle;
    public final ScreenCaptureDevicePolicyResolver policyResolver;

    public MediaProjectionBlockerEmptyStateProvider(UserHandle userHandle, UserHandle userHandle2, ScreenCaptureDevicePolicyResolver screenCaptureDevicePolicyResolver, Context context) {
        this.hostAppHandle = userHandle;
        this.personalProfileHandle = userHandle2;
        this.policyResolver = screenCaptureDevicePolicyResolver;
        this.context = context;
    }

    public final AbstractMultiProfilePagerAdapter.EmptyState getEmptyState(ResolverListAdapter resolverListAdapter) {
        boolean isScreenCaptureAllowed = this.policyResolver.isScreenCaptureAllowed(resolverListAdapter.getUserHandle(), this.hostAppHandle);
        final int i = this.hostAppHandle.equals(this.personalProfileHandle) ? R.string.ringtone_unknown : R.string.roamingText0;
        if (isScreenCaptureAllowed) {
            return null;
        }
        return new AbstractMultiProfilePagerAdapter.EmptyState() { // from class: com.android.systemui.mediaprojection.appselector.MediaProjectionBlockerEmptyStateProvider$getEmptyState$1
            public final String getSubtitle() {
                return MediaProjectionBlockerEmptyStateProvider.this.context.getResources().getString(i);
            }

            public final String getTitle() {
                return MediaProjectionBlockerEmptyStateProvider.this.context.getResources().getString(com.android.wm.shell.R.string.screen_capturing_disabled_by_policy_dialog_title);
            }

            public final boolean shouldSkipDataRebuild() {
                return true;
            }

            public final void onEmptyStateShown() {
            }
        };
    }
}

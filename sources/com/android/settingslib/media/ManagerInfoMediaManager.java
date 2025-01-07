package com.android.settingslib.media;

import android.media.MediaRoute2Info;
import android.media.MediaRouter2Manager;
import android.media.RouteListingPreference;
import android.media.RoutingSessionInfo;
import android.util.Log;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ManagerInfoMediaManager extends InfoMediaManager {
    public static final boolean DEBUG = Log.isLoggable("ManagerInfoMediaManager", 3);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class RouterManagerCallback implements MediaRouter2Manager.Callback {
        public final void onPreferredFeaturesChanged(String str, List list) {
            throw null;
        }

        public final void onRequestFailed(int i) {
            throw null;
        }

        public final void onRouteListingPreferenceUpdated(String str, RouteListingPreference routeListingPreference) {
            throw null;
        }

        public final void onRoutesUpdated() {
            throw null;
        }

        public final void onSessionReleased(RoutingSessionInfo routingSessionInfo) {
            throw null;
        }

        public final void onSessionUpdated(RoutingSessionInfo routingSessionInfo) {
            throw null;
        }

        public final void onTransferred(RoutingSessionInfo routingSessionInfo, RoutingSessionInfo routingSessionInfo2) {
            if (ManagerInfoMediaManager.DEBUG) {
                Log.d("ManagerInfoMediaManager", "onTransferred() oldSession : " + ((Object) routingSessionInfo.getName()) + ", newSession : " + ((Object) routingSessionInfo2.getName()));
            }
            throw null;
        }

        public final void onTransferFailed(RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) {
        }
    }
}

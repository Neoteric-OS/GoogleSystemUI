package com.android.systemui.statusbar.policy;

import android.media.MediaRouter;
import com.android.systemui.log.LogBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CastControllerLogger {
    public final LogBuffer logger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static String toLogString(MediaRouter.RouteInfo routeInfo) {
            if (routeInfo == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(routeInfo.getName());
            sb.append('/');
            sb.append(routeInfo.getDescription());
            sb.append('@');
            sb.append(routeInfo.getDeviceAddress());
            sb.append(",status=");
            sb.append(routeInfo.getStatus());
            if (routeInfo.isDefault()) {
                sb.append(",default");
            }
            if (routeInfo.isEnabled()) {
                sb.append(",enabled");
            }
            if (routeInfo.isConnecting()) {
                sb.append(",connecting");
            }
            if (routeInfo.isSelected()) {
                sb.append(",selected");
            }
            sb.append(",id=");
            sb.append(routeInfo.getTag());
            return sb.toString();
        }
    }

    public CastControllerLogger(LogBuffer logBuffer) {
        this.logger = logBuffer;
    }
}

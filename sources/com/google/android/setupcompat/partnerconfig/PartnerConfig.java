package com.google.android.setupcompat.partnerconfig;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'EF0' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PartnerConfig {
    public static final /* synthetic */ PartnerConfig[] $VALUES;
    public static final PartnerConfig CONFIG_ILLUSTRATION_MAX_HEIGHT;
    public static final PartnerConfig CONFIG_ILLUSTRATION_MAX_WIDTH;
    private final String resourceName;
    private final ResourceType resourceType;

    /* JADX INFO: Fake field, exist only in values array */
    PartnerConfig EF0;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ResourceType {
        public static final /* synthetic */ ResourceType[] $VALUES;
        public static final ResourceType BOOL;
        public static final ResourceType COLOR;
        public static final ResourceType DIMENSION;
        public static final ResourceType DRAWABLE;
        public static final ResourceType FRACTION;
        public static final ResourceType ILLUSTRATION;
        public static final ResourceType INTEGER;
        public static final ResourceType STRING;
        public static final ResourceType STRING_ARRAY;

        static {
            ResourceType resourceType = new ResourceType("INTEGER", 0);
            INTEGER = resourceType;
            ResourceType resourceType2 = new ResourceType("BOOL", 1);
            BOOL = resourceType2;
            ResourceType resourceType3 = new ResourceType("COLOR", 2);
            COLOR = resourceType3;
            ResourceType resourceType4 = new ResourceType("DRAWABLE", 3);
            DRAWABLE = resourceType4;
            ResourceType resourceType5 = new ResourceType("STRING", 4);
            STRING = resourceType5;
            ResourceType resourceType6 = new ResourceType("DIMENSION", 5);
            DIMENSION = resourceType6;
            ResourceType resourceType7 = new ResourceType("FRACTION", 6);
            FRACTION = resourceType7;
            ResourceType resourceType8 = new ResourceType("ILLUSTRATION", 7);
            ILLUSTRATION = resourceType8;
            ResourceType resourceType9 = new ResourceType("STRING_ARRAY", 8);
            STRING_ARRAY = resourceType9;
            $VALUES = new ResourceType[]{resourceType, resourceType2, resourceType3, resourceType4, resourceType5, resourceType6, resourceType7, resourceType8, resourceType9};
        }

        public static ResourceType valueOf(String str) {
            return (ResourceType) Enum.valueOf(ResourceType.class, str);
        }

        public static ResourceType[] values() {
            return (ResourceType[]) $VALUES.clone();
        }
    }

    static {
        ResourceType resourceType = ResourceType.DRAWABLE;
        PartnerConfig partnerConfig = new PartnerConfig("CONFIG_STATUS_BAR_BACKGROUND", 0, "setup_compat_status_bar_background", resourceType);
        ResourceType resourceType2 = ResourceType.BOOL;
        PartnerConfig partnerConfig2 = new PartnerConfig("CONFIG_LIGHT_STATUS_BAR", 1, "setup_compat_light_status_bar", resourceType2);
        ResourceType resourceType3 = ResourceType.COLOR;
        PartnerConfig partnerConfig3 = new PartnerConfig("CONFIG_NAVIGATION_BAR_BG_COLOR", 2, "setup_compat_navigation_bar_bg_color", resourceType3);
        PartnerConfig partnerConfig4 = new PartnerConfig("CONFIG_NAVIGATION_BAR_DIVIDER_COLOR", 3, "setup_compat_navigation_bar_divider_color", resourceType3);
        PartnerConfig partnerConfig5 = new PartnerConfig("CONFIG_FOOTER_BAR_BG_COLOR", 4, "setup_compat_footer_bar_bg_color", resourceType3);
        ResourceType resourceType4 = ResourceType.DIMENSION;
        PartnerConfig partnerConfig6 = new PartnerConfig("CONFIG_FOOTER_BAR_MIN_HEIGHT", 5, "setup_compat_footer_bar_min_height", resourceType4);
        PartnerConfig partnerConfig7 = new PartnerConfig("CONFIG_FOOTER_BAR_PADDING_START", 6, "setup_compat_footer_bar_padding_start", resourceType4);
        PartnerConfig partnerConfig8 = new PartnerConfig("CONFIG_FOOTER_BAR_PADDING_END", 7, "setup_compat_footer_bar_padding_end", resourceType4);
        PartnerConfig partnerConfig9 = new PartnerConfig("CONFIG_LIGHT_NAVIGATION_BAR", 8, "setup_compat_light_navigation_bar", resourceType2);
        ResourceType resourceType5 = ResourceType.STRING;
        PartnerConfig partnerConfig10 = new PartnerConfig("CONFIG_FOOTER_BUTTON_FONT_FAMILY", 9, "setup_compat_footer_button_font_family", resourceType5);
        ResourceType resourceType6 = ResourceType.INTEGER;
        PartnerConfig partnerConfig11 = new PartnerConfig("CONFIG_FOOTER_BUTTON_FONT_WEIGHT", 10, "setup_compat_footer_button_font_weight", resourceType6);
        PartnerConfig partnerConfig12 = new PartnerConfig("CONFIG_FOOTER_BUTTON_ICON_ADD_ANOTHER", 11, "setup_compat_footer_button_icon_add_another", resourceType);
        PartnerConfig partnerConfig13 = new PartnerConfig("CONFIG_FOOTER_BUTTON_ICON_CANCEL", 12, "setup_compat_footer_button_icon_cancel", resourceType);
        PartnerConfig partnerConfig14 = new PartnerConfig("CONFIG_FOOTER_BUTTON_ICON_CLEAR", 13, "setup_compat_footer_button_icon_clear", resourceType);
        PartnerConfig partnerConfig15 = new PartnerConfig("CONFIG_FOOTER_BUTTON_ICON_DONE", 14, "setup_compat_footer_button_icon_done", resourceType);
        PartnerConfig partnerConfig16 = new PartnerConfig("CONFIG_FOOTER_BUTTON_ICON_NEXT", 15, "setup_compat_footer_button_icon_next", resourceType);
        PartnerConfig partnerConfig17 = new PartnerConfig("CONFIG_FOOTER_BUTTON_ICON_OPT_IN", 16, "setup_compat_footer_button_icon_opt_in", resourceType);
        PartnerConfig partnerConfig18 = new PartnerConfig("CONFIG_FOOTER_BUTTON_ICON_SKIP", 17, "setup_compat_footer_button_icon_skip", resourceType);
        PartnerConfig partnerConfig19 = new PartnerConfig("CONFIG_FOOTER_BUTTON_ICON_STOP", 18, "setup_compat_footer_button_icon_stop", resourceType);
        PartnerConfig partnerConfig20 = new PartnerConfig("CONFIG_FOOTER_BUTTON_PADDING_TOP", 19, "setup_compat_footer_button_padding_top", resourceType4);
        PartnerConfig partnerConfig21 = new PartnerConfig("CONFIG_FOOTER_BUTTON_PADDING_BOTTOM", 20, "setup_compat_footer_button_padding_bottom", resourceType4);
        PartnerConfig partnerConfig22 = new PartnerConfig("CONFIG_FOOTER_BUTTON_RADIUS", 21, "setup_compat_footer_button_radius", resourceType4);
        ResourceType resourceType7 = ResourceType.FRACTION;
        PartnerConfig partnerConfig23 = new PartnerConfig("CONFIG_FOOTER_BUTTON_RIPPLE_COLOR_ALPHA", 22, "setup_compat_footer_button_ripple_alpha", resourceType7);
        PartnerConfig partnerConfig24 = new PartnerConfig("CONFIG_FOOTER_BUTTON_TEXT_SIZE", 23, "setup_compat_footer_button_text_size", resourceType4);
        PartnerConfig partnerConfig25 = new PartnerConfig("CONFIG_FOOTER_BUTTON_TEXT_STYLE", 24, "setup_compat_footer_button_text_style", resourceType6);
        PartnerConfig partnerConfig26 = new PartnerConfig("CONFIG_FOOTER_BUTTON_MIN_HEIGHT", 25, "setup_compat_footer_button_min_height", resourceType4);
        PartnerConfig partnerConfig27 = new PartnerConfig("CONFIG_FOOTER_BUTTON_ALIGNED_END", 26, "setup_compat_footer_button_aligned_end", resourceType2);
        PartnerConfig partnerConfig28 = new PartnerConfig("CONFIG_FOOTER_BUTTON_DISABLED_ALPHA", 27, "setup_compat_footer_button_disabled_alpha", resourceType7);
        PartnerConfig partnerConfig29 = new PartnerConfig("CONFIG_FOOTER_BUTTON_DISABLED_BG_COLOR", 28, "setup_compat_footer_button_disabled_bg_color", resourceType3);
        PartnerConfig partnerConfig30 = new PartnerConfig("CONFIG_FOOTER_PRIMARY_BUTTON_DISABLED_TEXT_COLOR", 29, "setup_compat_primary_button_disabled_text_color", resourceType3);
        PartnerConfig partnerConfig31 = new PartnerConfig("CONFIG_FOOTER_SECONDARY_BUTTON_DISABLED_TEXT_COLOR", 30, "setup_compat_secondary_button_disabled_text_color", resourceType3);
        PartnerConfig partnerConfig32 = new PartnerConfig("CONFIG_FOOTER_PRIMARY_BUTTON_BG_COLOR", 31, "setup_compat_footer_primary_button_bg_color", resourceType3);
        PartnerConfig partnerConfig33 = new PartnerConfig("CONFIG_FOOTER_PRIMARY_BUTTON_TEXT_COLOR", 32, "setup_compat_footer_primary_button_text_color", resourceType3);
        PartnerConfig partnerConfig34 = new PartnerConfig("CONFIG_FOOTER_PRIMARY_BUTTON_MARGIN_START", 33, "setup_compat_footer_primary_button_margin_start", resourceType4);
        PartnerConfig partnerConfig35 = new PartnerConfig("CONFIG_FOOTER_SECONDARY_BUTTON_BG_COLOR", 34, "setup_compat_footer_secondary_button_bg_color", resourceType3);
        PartnerConfig partnerConfig36 = new PartnerConfig("CONFIG_FOOTER_SECONDARY_BUTTON_TEXT_COLOR", 35, "setup_compat_footer_secondary_button_text_color", resourceType3);
        PartnerConfig partnerConfig37 = new PartnerConfig("CONFIG_FOOTER_SECONDARY_BUTTON_MARGIN_START", 36, "setup_compat_footer_secondary_button_margin_start", resourceType4);
        PartnerConfig partnerConfig38 = new PartnerConfig("CONFIG_LAYOUT_BACKGROUND_COLOR", 37, "setup_design_layout_bg_color", resourceType3);
        PartnerConfig partnerConfig39 = new PartnerConfig("CONFIG_LAYOUT_MARGIN_START", 38, "setup_design_layout_margin_start", resourceType4);
        PartnerConfig partnerConfig40 = new PartnerConfig("CONFIG_LAYOUT_MARGIN_END", 39, "setup_design_layout_margin_end", resourceType4);
        PartnerConfig partnerConfig41 = new PartnerConfig("CONFIG_LAND_MIDDLE_HORIZONTAL_SPACING", 40, "setup_design_land_middle_horizontal_spacing", resourceType4);
        PartnerConfig partnerConfig42 = new PartnerConfig("CONFIG_HEADER_TEXT_COLOR", 41, "setup_design_header_text_color", resourceType3);
        PartnerConfig partnerConfig43 = new PartnerConfig("CONFIG_HEADER_TEXT_SIZE", 42, "setup_design_header_text_size", resourceType4);
        PartnerConfig partnerConfig44 = new PartnerConfig("CONFIG_HEADER_FONT_FAMILY", 43, "setup_design_header_font_family", resourceType5);
        PartnerConfig partnerConfig45 = new PartnerConfig("CONFIG_HEADER_FONT_WEIGHT", 44, "setup_design_header_font_weight", resourceType6);
        PartnerConfig partnerConfig46 = new PartnerConfig("CONFIG_HEADER_TEXT_MARGIN_TOP", 45, "setup_design_header_text_margin_top", resourceType4);
        PartnerConfig partnerConfig47 = new PartnerConfig("CONFIG_HEADER_TEXT_MARGIN_BOTTOM", 46, "setup_design_header_text_margin_bottom", resourceType4);
        PartnerConfig partnerConfig48 = new PartnerConfig("CONFIG_LAYOUT_GRAVITY", 47, "setup_design_layout_gravity", resourceType5);
        PartnerConfig partnerConfig49 = new PartnerConfig("CONFIG_ICON_MARGIN_TOP", 48, "setup_design_icon_margin_top", resourceType4);
        PartnerConfig partnerConfig50 = new PartnerConfig("CONFIG_ICON_SIZE", 49, "setup_design_icon_size", resourceType4);
        PartnerConfig partnerConfig51 = new PartnerConfig("CONFIG_ILLUSTRATION_MAX_WIDTH", 50, "setup_design_illustration_max_width", resourceType4);
        CONFIG_ILLUSTRATION_MAX_WIDTH = partnerConfig51;
        PartnerConfig partnerConfig52 = new PartnerConfig("CONFIG_ILLUSTRATION_MAX_HEIGHT", 51, "setup_design_illustration_max_height", resourceType4);
        CONFIG_ILLUSTRATION_MAX_HEIGHT = partnerConfig52;
        PartnerConfig partnerConfig53 = new PartnerConfig("CONFIG_HEADER_AREA_BACKGROUND_COLOR", 52, "setup_design_header_area_background_color", resourceType3);
        PartnerConfig partnerConfig54 = new PartnerConfig("CONFIG_HEADER_CONTAINER_MARGIN_BOTTOM", 53, "setup_design_header_container_margin_bottom", resourceType4);
        PartnerConfig partnerConfig55 = new PartnerConfig("CONFIG_HEADER_AUTO_SIZE_ENABLED", 54, "setup_design_header_auto_size_enabled", resourceType2);
        PartnerConfig partnerConfig56 = new PartnerConfig("CONFIG_HEADER_AUTO_SIZE_MAX_TEXT_SIZE", 55, "setup_design_header_auto_size_max_text_size", resourceType4);
        PartnerConfig partnerConfig57 = new PartnerConfig("CONFIG_HEADER_AUTO_SIZE_MIN_TEXT_SIZE", 56, "setup_design_header_auto_size_min_text_size", resourceType4);
        PartnerConfig partnerConfig58 = new PartnerConfig("CONFIG_HEADER_AUTO_SIZE_MAX_LINE_OF_MAX_SIZE", 57, "setup_design_header_auto_size_max_line_of_max_size", resourceType6);
        PartnerConfig partnerConfig59 = new PartnerConfig("CONFIG_HEADER_AUTO_SIZE_LINE_SPACING_EXTRA", 58, "setup_design_header_auto_size_line_spacing_extra", resourceType4);
        PartnerConfig partnerConfig60 = new PartnerConfig("CONFIG_DESCRIPTION_TEXT_SIZE", 59, "setup_design_description_text_size", resourceType4);
        PartnerConfig partnerConfig61 = new PartnerConfig("CONFIG_DESCRIPTION_TEXT_COLOR", 60, "setup_design_description_text_color", resourceType3);
        PartnerConfig partnerConfig62 = new PartnerConfig("CONFIG_DESCRIPTION_LINK_TEXT_COLOR", 61, "setup_design_description_link_text_color", resourceType3);
        PartnerConfig partnerConfig63 = new PartnerConfig("CONFIG_DESCRIPTION_FONT_FAMILY", 62, "setup_design_description_font_family", resourceType5);
        PartnerConfig partnerConfig64 = new PartnerConfig("CONFIG_DESCRIPTION_FONT_WEIGHT", 63, "setup_design_description_font_weight", resourceType6);
        PartnerConfig partnerConfig65 = new PartnerConfig("CONFIG_DESCRIPTION_LINK_FONT_FAMILY", 64, "setup_design_description_link_font_family", resourceType5);
        PartnerConfig partnerConfig66 = new PartnerConfig("CONFIG_DESCRIPTION_TEXT_MARGIN_TOP", 65, "setup_design_description_text_margin_top", resourceType4);
        PartnerConfig partnerConfig67 = new PartnerConfig("CONFIG_DESCRIPTION_TEXT_MARGIN_BOTTOM", 66, "setup_design_description_text_margin_bottom", resourceType4);
        PartnerConfig partnerConfig68 = new PartnerConfig("CONFIG_ACCOUNT_NAME_TEXT_SIZE", 67, "setup_design_account_name_text_size", resourceType4);
        PartnerConfig partnerConfig69 = new PartnerConfig("CONFIG_ACCOUNT_NAME_FONT_FAMILY", 68, "setup_design_account_name_font_family", resourceType5);
        PartnerConfig partnerConfig70 = new PartnerConfig("CONFIG_ACCOUNT_AVATAR_MARGIN_END", 69, "setup_design_account_avatar_margin_end", resourceType4);
        PartnerConfig partnerConfig71 = new PartnerConfig("CONFIG_ACCOUNT_AVATAR_SIZE", 70, "setup_design_account_avatar_size", resourceType4);
        PartnerConfig partnerConfig72 = new PartnerConfig("CONFIG_CONTENT_TEXT_SIZE", 71, "setup_design_content_text_size", resourceType4);
        PartnerConfig partnerConfig73 = new PartnerConfig("CONFIG_CONTENT_TEXT_COLOR", 72, "setup_design_content_text_color", resourceType3);
        PartnerConfig partnerConfig74 = new PartnerConfig("CONFIG_CONTENT_LINK_TEXT_COLOR", 73, "setup_design_content_link_text_color", resourceType3);
        PartnerConfig partnerConfig75 = new PartnerConfig("CONFIG_CONTENT_FONT_FAMILY", 74, "setup_design_content_font_family", resourceType5);
        PartnerConfig partnerConfig76 = new PartnerConfig("CONFIG_CONTENT_LAYOUT_GRAVITY", 75, "setup_design_content_layout_gravity", resourceType5);
        PartnerConfig partnerConfig77 = new PartnerConfig("CONFIG_CONTENT_PADDING_TOP", 76, "setup_design_content_padding_top", resourceType4);
        PartnerConfig partnerConfig78 = new PartnerConfig("CONFIG_CONTENT_INFO_TEXT_SIZE", 77, "setup_design_content_info_text_size", resourceType4);
        PartnerConfig partnerConfig79 = new PartnerConfig("CONFIG_CONTENT_INFO_FONT_FAMILY", 78, "setup_design_content_info_font_family", resourceType5);
        PartnerConfig partnerConfig80 = new PartnerConfig("CONFIG_CONTENT_INFO_LINE_SPACING_EXTRA", 79, "setup_design_content_info_line_spacing_extra", resourceType4);
        PartnerConfig partnerConfig81 = new PartnerConfig("CONFIG_CONTENT_INFO_ICON_SIZE", 80, "setup_design_content_info_icon_size", resourceType4);
        PartnerConfig partnerConfig82 = new PartnerConfig("CONFIG_CONTENT_INFO_ICON_MARGIN_END", 81, "setup_design_content_info_icon_margin_end", resourceType4);
        PartnerConfig partnerConfig83 = new PartnerConfig("CONFIG_CONTENT_INFO_PADDING_TOP", 82, "setup_design_content_info_padding_top", resourceType4);
        PartnerConfig partnerConfig84 = new PartnerConfig("CONFIG_CONTENT_INFO_PADDING_BOTTOM", 83, "setup_design_content_info_padding_bottom", resourceType4);
        PartnerConfig partnerConfig85 = new PartnerConfig("CONFIG_ITEMS_TITLE_TEXT_SIZE", 84, "setup_design_items_title_text_size", resourceType4);
        PartnerConfig partnerConfig86 = new PartnerConfig("CONFIG_ITEMS_SUMMARY_TEXT_SIZE", 85, "setup_design_items_summary_text_size", resourceType4);
        PartnerConfig partnerConfig87 = new PartnerConfig("CONFIG_ITEMS_SUMMARY_MARGIN_TOP", 86, "setup_design_items_summary_margin_top", resourceType4);
        PartnerConfig partnerConfig88 = new PartnerConfig("CONFIG_ITEMS_TITLE_FONT_FAMILY", 87, "setup_design_items_title_font_family", resourceType5);
        PartnerConfig partnerConfig89 = new PartnerConfig("CONFIG_ITEMS_SUMMARY_FONT_FAMILY", 88, "setup_design_items_summary_font_family", resourceType5);
        PartnerConfig partnerConfig90 = new PartnerConfig("CONFIG_ITEMS_PADDING_TOP", 89, "setup_design_items_padding_top", resourceType4);
        PartnerConfig partnerConfig91 = new PartnerConfig("CONFIG_ITEMS_PADDING_BOTTOM", 90, "setup_design_items_padding_bottom", resourceType4);
        PartnerConfig partnerConfig92 = new PartnerConfig("CONFIG_ITEMS_MIN_HEIGHT", 91, "setup_design_items_min_height", resourceType4);
        PartnerConfig partnerConfig93 = new PartnerConfig("CONFIG_ITEMS_DIVIDER_SHOWN", 92, "setup_design_items_divider_shown", resourceType2);
        PartnerConfig partnerConfig94 = new PartnerConfig("CONFIG_CARD_VIEW_INTRINSIC_WIDTH", 93, "setup_design_card_view_intrinsic_width", resourceType4);
        PartnerConfig partnerConfig95 = new PartnerConfig("CONFIG_CARD_VIEW_INTRINSIC_HEIGHT", 94, "setup_design_card_view_intrinsic_height", resourceType4);
        ResourceType resourceType8 = ResourceType.ILLUSTRATION;
        PartnerConfig partnerConfig96 = new PartnerConfig("CONFIG_PROGRESS_ILLUSTRATION_DEFAULT", 95, "progress_illustration_custom_default", resourceType8);
        PartnerConfig partnerConfig97 = new PartnerConfig("CONFIG_PROGRESS_ILLUSTRATION_ACCOUNT", 96, "progress_illustration_custom_account", resourceType8);
        PartnerConfig partnerConfig98 = new PartnerConfig("CONFIG_PROGRESS_ILLUSTRATION_CONNECTION", 97, "progress_illustration_custom_connection", resourceType8);
        PartnerConfig partnerConfig99 = new PartnerConfig("CONFIG_PROGRESS_ILLUSTRATION_UPDATE", 98, "progress_illustration_custom_update", resourceType8);
        PartnerConfig partnerConfig100 = new PartnerConfig("CONFIG_PROGRESS_ILLUSTRATION_FINAL_HOLD", 99, "final_hold_custom_illustration", resourceType8);
        PartnerConfig partnerConfig101 = new PartnerConfig("CONFIG_PROGRESS_ILLUSTRATION_DISPLAY_MINIMUM_MS", 100, "progress_illustration_display_minimum_ms", resourceType6);
        PartnerConfig partnerConfig102 = new PartnerConfig("CONFIG_LOADING_LOTTIE_DEFAULT", 101, "loading_animation_custom_default", resourceType8);
        PartnerConfig partnerConfig103 = new PartnerConfig("CONFIG_LOADING_LOTTIE_ACCOUNT", 102, "loading_animation_custom_account", resourceType8);
        PartnerConfig partnerConfig104 = new PartnerConfig("CONFIG_LOADING_LOTTIE_CONNECTION", 103, "loading_animation_custom_connection", resourceType8);
        PartnerConfig partnerConfig105 = new PartnerConfig("CONFIG_LOADING_LOTTIE_UPDATE", 104, "loading_animation_custom_update", resourceType8);
        PartnerConfig partnerConfig106 = new PartnerConfig("CONFIG_LOADING_LOTTIE_FINAL_HOLD", 105, "loading_animation_custom_final_hold", resourceType8);
        PartnerConfig partnerConfig107 = new PartnerConfig("CONFIG_TRANSITION_TYPE", 106, "setup_design_transition_type", resourceType6);
        ResourceType resourceType9 = ResourceType.STRING_ARRAY;
        $VALUES = new PartnerConfig[]{partnerConfig, partnerConfig2, partnerConfig3, partnerConfig4, partnerConfig5, partnerConfig6, partnerConfig7, partnerConfig8, partnerConfig9, partnerConfig10, partnerConfig11, partnerConfig12, partnerConfig13, partnerConfig14, partnerConfig15, partnerConfig16, partnerConfig17, partnerConfig18, partnerConfig19, partnerConfig20, partnerConfig21, partnerConfig22, partnerConfig23, partnerConfig24, partnerConfig25, partnerConfig26, partnerConfig27, partnerConfig28, partnerConfig29, partnerConfig30, partnerConfig31, partnerConfig32, partnerConfig33, partnerConfig34, partnerConfig35, partnerConfig36, partnerConfig37, partnerConfig38, partnerConfig39, partnerConfig40, partnerConfig41, partnerConfig42, partnerConfig43, partnerConfig44, partnerConfig45, partnerConfig46, partnerConfig47, partnerConfig48, partnerConfig49, partnerConfig50, partnerConfig51, partnerConfig52, partnerConfig53, partnerConfig54, partnerConfig55, partnerConfig56, partnerConfig57, partnerConfig58, partnerConfig59, partnerConfig60, partnerConfig61, partnerConfig62, partnerConfig63, partnerConfig64, partnerConfig65, partnerConfig66, partnerConfig67, partnerConfig68, partnerConfig69, partnerConfig70, partnerConfig71, partnerConfig72, partnerConfig73, partnerConfig74, partnerConfig75, partnerConfig76, partnerConfig77, partnerConfig78, partnerConfig79, partnerConfig80, partnerConfig81, partnerConfig82, partnerConfig83, partnerConfig84, partnerConfig85, partnerConfig86, partnerConfig87, partnerConfig88, partnerConfig89, partnerConfig90, partnerConfig91, partnerConfig92, partnerConfig93, partnerConfig94, partnerConfig95, partnerConfig96, partnerConfig97, partnerConfig98, partnerConfig99, partnerConfig100, partnerConfig101, partnerConfig102, partnerConfig103, partnerConfig104, partnerConfig105, partnerConfig106, partnerConfig107, new PartnerConfig("CONFIG_LOTTIE_LIGHT_THEME_CUSTOMIZATION_DEFAULT", 107, "loading_light_theme_customization_default", resourceType9), new PartnerConfig("CONFIG_LOTTIE_LIGHT_THEME_CUSTOMIZATION_ACCOUNT", 108, "loading_light_theme_customization_account", resourceType9), new PartnerConfig("CONFIG_LOTTIE_LIGHT_THEME_CUSTOMIZATION_CONNECTION", 109, "loading_light_theme_customization_connection", resourceType9), new PartnerConfig("CONFIG_LOTTIE_LIGHT_THEME_CUSTOMIZATION_UPDATE", 110, "loading_light_theme_customization_update", resourceType9), new PartnerConfig("CONFIG_LOTTIE_LIGHT_THEME_CUSTOMIZATION_FINAL_HOLD", 111, "loading_light_theme_customization_final_hold", resourceType9), new PartnerConfig("CONFIG_LOTTIE_DARK_THEME_CUSTOMIZATION_DEFAULT", 112, "loading_dark_theme_customization_default", resourceType9), new PartnerConfig("CONFIG_LOTTIE_DARK_THEME_CUSTOMIZATION_ACCOUNT", 113, "loading_dark_theme_customization_account", resourceType9), new PartnerConfig("CONFIG_LOTTIE_DARK_THEME_CUSTOMIZATION_CONNECTION", 114, "loading_dark_theme_customization_connection", resourceType9), new PartnerConfig("CONFIG_LOTTIE_DARK_THEME_CUSTOMIZATION_UPDATE", 115, "loading_dark_theme_customization_update", resourceType9), new PartnerConfig("CONFIG_LOTTIE_DARK_THEME_CUSTOMIZATION_FINAL_HOLD", 116, "loading_dark_theme_customization_final_hold", resourceType9), new PartnerConfig("CONFIG_LOADING_LAYOUT_PADDING_TOP", 117, "loading_layout_content_padding_top", resourceType4), new PartnerConfig("CONFIG_LOADING_LAYOUT_PADDING_START", 118, "loading_layout_content_padding_start", resourceType4), new PartnerConfig("CONFIG_LOADING_LAYOUT_PADDING_END", 119, "loading_layout_content_padding_end", resourceType4), new PartnerConfig("CONFIG_LOADING_LAYOUT_PADDING_BOTTOM", 120, "loading_layout_content_padding_bottom", resourceType4), new PartnerConfig("CONFIG_LOADING_LAYOUT_HEADER_HEIGHT", 121, "loading_layout_header_height", resourceType4), new PartnerConfig("CONFIG_LOADING_LAYOUT_FULL_SCREEN_ILLUSTRATION_ENABLED", 122, "loading_layout_full_screen_illustration_enabled", resourceType2), new PartnerConfig("CONFIG_LOADING_LAYOUT_WAIT_FOR_ANIMATION_FINISHED", 123, "loading_layout_wait_for_animation_finished", resourceType2), new PartnerConfig("CONFIG_PROGRESS_BAR_MARGIN_TOP", 124, "setup_design_progress_bar_margin_top", resourceType4), new PartnerConfig("CONFIG_PROGRESS_BAR_MARGIN_BOTTOM", 125, "setup_design_progress_bar_margin_bottom", resourceType4), new PartnerConfig("CONFIG_TWO_PANE_ADAPT_WINDOW_WIDTH", 126, "setup_compat_two_pane_adapt_window_width", resourceType6)};
    }

    public PartnerConfig(String str, int i, String str2, ResourceType resourceType) {
        this.resourceName = str2;
        this.resourceType = resourceType;
    }

    public static PartnerConfig valueOf(String str) {
        return (PartnerConfig) Enum.valueOf(PartnerConfig.class, str);
    }

    public static PartnerConfig[] values() {
        return (PartnerConfig[]) $VALUES.clone();
    }

    public final String getResourceName() {
        return this.resourceName;
    }

    public final ResourceType getResourceType() {
        return this.resourceType;
    }
}

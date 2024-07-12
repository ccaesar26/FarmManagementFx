package org.example.farmmanagementfx.presentation.util;

public class ResourceUtil {

    public static String getImagePath(String imageName) {
        var resourcePath = "/org/example/farmmanagementfx/image/" + imageName;
        return ResourceUtil.class.getResource(resourcePath).toExternalForm();
    }

    public static String getStyleSheetPath(String styleSheetName) {
        var resourcePath = "/org/example/farmmanagementfx/style/" + styleSheetName;
        return ResourceUtil.class.getResource(resourcePath).toExternalForm();
    }
}

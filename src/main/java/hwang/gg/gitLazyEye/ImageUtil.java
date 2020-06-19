package hwang.gg.gitLazyEye;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageUtil {

  private static final String IMAGE_PATTERN =
          "([^*]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";

  private ImageUtil() {
  }

  /**
   * Validate image with regular expression
   *
   * @param image image for validation
   * @return true valid image, false invalid image
   */

  public static boolean validate(final String image) {
    Pattern pattern = Pattern.compile(IMAGE_PATTERN);
    Matcher matcher = pattern.matcher(image);
    return matcher.matches();
  }

  public static void repaint(String branchName) {
    PropertiesComponent prop = PropertiesComponent.getInstance(ProjectUtil.getActiveProject());
    String imagePath = prop.getValue(branchName);
    // Image path spec is: <file-path-or-URL>[,<opacity>[,<fill-type>[,<placement>]]]
    prop.setValue(IdeBackgroundUtil.EDITOR_PROP, imagePath);
    IdeBackgroundUtil.repaintAllWindows();
  }
}
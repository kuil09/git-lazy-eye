package hwang.gg.gitLazyEye;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;

import java.util.regex.Pattern;

public class ImageUtil {

  private static final Pattern IMAGE_PATTERN =
          Pattern.compile("([^*]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)");

  private static final Pattern URL_PATTERN =
          Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");

  private ImageUtil() {
  }

  /**
   * Validate image with regular expression
   *
   * @param image Image for validation. It is a file or URL
   * @return true valid image, false invalid image
   */

  public static boolean validate(final String image) {
    return IMAGE_PATTERN.matcher(image).matches() ||
            URL_PATTERN.matcher(image).matches();
  }

  public static void repaint(final Project project) {
    PropertiesComponent prop = PropertiesComponent.getInstance(project);
    String imagePath = prop.getValue(ProjectUtil.getCurrentBranchName(project));
    // Image path spec is: <file-path-or-URL>[,<opacity>[,<fill-type>[,<placement>]]]
    prop.setValue(IdeBackgroundUtil.EDITOR_PROP, imagePath);
    IdeBackgroundUtil.repaintAllWindows();
  }
}

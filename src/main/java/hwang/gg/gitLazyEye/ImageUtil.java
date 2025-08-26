package hwang.gg.gitLazyEye;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageUtil {

  private static final String IMAGE_PATTERN =
          "([^*]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";

  private static final String URL_PATTERN =
          "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)$";

  private ImageUtil() {
  }

  /**
   * Validate image with regular expression
   *
   * @param image Image for validation. It is a file or URL
   * @return true valid image, false invalid image
   */
  public static boolean validate(final String image) {
    Pattern imagePattern = Pattern.compile(IMAGE_PATTERN);
    Matcher imageMatcher = imagePattern.matcher(image);

    Pattern urlPattern = Pattern.compile(URL_PATTERN);
    Matcher urlMatcher = urlPattern.matcher(image);

    return imageMatcher.matches() || urlMatcher.matches();
  }

  public static void repaint(final Project project) {
    if (project == null) return;
    PropertiesComponent prop = PropertiesComponent.getInstance(project);
    String currentBranch = ProjectUtil.getCurrentBranchName(project);
    if (currentBranch == null || currentBranch.isEmpty()) return;
    String imagePath = prop.getValue(currentBranch);
    // Image path spec is: <file-path-or-URL>[,<opacity>[,<fill-type>[,<placement>]]]
    prop.setValue(IdeBackgroundUtil.EDITOR_PROP, imagePath);
    IdeBackgroundUtil.repaintAllWindows();
  }
}

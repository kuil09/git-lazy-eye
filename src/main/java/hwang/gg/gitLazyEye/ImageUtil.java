package hwang.gg.gitLazyEye;

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
}
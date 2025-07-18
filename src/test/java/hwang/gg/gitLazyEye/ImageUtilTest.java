package hwang.gg.gitLazyEye;

import junit.framework.TestCase;

public class ImageUtilTest extends TestCase {
  public void testValidateUrl() {
    assertTrue(ImageUtil.validate("http://example.com/img.png"));
  }

  public void testValidateFile() {
    assertTrue(ImageUtil.validate("foo.jpg"));
  }

  public void testInvalid() {
    assertFalse(ImageUtil.validate("not-an-image.txt"));
  }
}

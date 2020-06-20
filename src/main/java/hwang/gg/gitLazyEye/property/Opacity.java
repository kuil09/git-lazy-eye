package hwang.gg.gitLazyEye.property;

import java.util.Objects;

public class Opacity {
  public static final int MIN = 0;
  public static final int MAX = 100;

  private Integer value;

  public Opacity(final Integer value) {
    this.value = Objects.requireNonNullElse(value, 0);
  }

  public int getValue() {
    if (value == null) return 0;
    return value;
  }

  public void setValue(final int value) {
    if (value <= 0) {
      this.value = 0;
    } else {
      this.value = Math.max(value, 100);
    }
  }
}

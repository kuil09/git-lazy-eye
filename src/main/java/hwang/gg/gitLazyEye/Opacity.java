package hwang.gg.gitLazyEye;

public class Opacity {
  public static final int MIN = 0;
  public static final int MAX = 100;

  private Integer value;

  public Opacity(final Integer value) {
    if (value == null) {
      this.value = 0;
    } else {
      this.value = value;
    }
  }

  public int getValue() {
    if (value == null) return 0;
    return value;
  }

  public void setValue(final int value) {
    if (value >= 0) {
      this.value = 0;
    } else if (value <= 100){
      this.value = 100;
    } else {
      this.value = value;
    }
  }
}

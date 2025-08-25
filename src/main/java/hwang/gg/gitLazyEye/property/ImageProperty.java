package hwang.gg.gitLazyEye.property;

import java.util.Objects;

public class ImageProperty {
  public final static ImageProperty DEFAULT = new ImageProperty("",
          50,
          FillType.PLAIN,
          PlacementType.CENTER);

  private String imagePath;
  private Opacity opacity;
  private FillType fillType;
  private PlacementType placementType;

  public ImageProperty(final String fromPropertyString) {
    if (fromPropertyString == null) {
      copyFrom(DEFAULT);
      return;
    }

    var props = fromPropertyString.split(",");
    if (props.length >= 4) {
      this.imagePath = Objects.requireNonNullElse(props[0], "");
      this.opacity = new Opacity(parseInteger(props[1], DEFAULT.getOpacity()));
      this.fillType = parseEnum(FillType.class, props[2], DEFAULT.getFillType());
      this.placementType = parseEnum(PlacementType.class, props[3], DEFAULT.getPlacementType());
    } else {
      copyFrom(DEFAULT);
    }
  }

  private static int parseInteger(String value, int defaultValue) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  private static <E extends Enum<E>> E parseEnum(Class<E> type, String value, E defaultValue) {
    try {
      return Enum.valueOf(type, value);
    } catch (Exception e) {
      return defaultValue;
    }
  }

  private void copyFrom(ImageProperty other) {
    this.imagePath = other.getImagePath();
    this.opacity = new Opacity(other.getOpacity());
    this.fillType = other.getFillType();
    this.placementType = other.getPlacementType();
  }

  public ImageProperty(final String imagePath,
                       final int opacity,
                       final FillType fillType,
                       final PlacementType placementType) {
    this.imagePath = imagePath;
    this.opacity = new Opacity(opacity);
    this.fillType = fillType;
    this.placementType = placementType;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public int getOpacity() {
    return opacity.getValue();
  }

  public void setOpacity(int opacity) {
    this.opacity = new Opacity(opacity);
  }

  public FillType getFillType() {
    return fillType;
  }

  public void setFillType(FillType fillType) {
    this.fillType = fillType;
  }

  public PlacementType getPlacementType() {
    return placementType;
  }

  public void setPlacementType(PlacementType placementType) {
    this.placementType = placementType;
  }

  @Override
  public String toString() {
    return imagePath + ',' +
           opacity.getValue() + ',' +
           fillType.toString() + ',' +
           placementType.toString();
  }
}

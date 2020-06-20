package hwang.gg.gitLazyEye.property;

import java.util.Objects;

public class ImageProperty {
  public final static ImageProperty DEFAULT = new ImageProperty("",
          50,
          FillType.PLAIN,
          PlacementType.CENTER);

  private String imagePath;
  private int opacity;
  private FillType fillType;
  private PlacementType placementType;

  public ImageProperty(final String fromPropertyString) {
    if (fromPropertyString == null || fromPropertyString.split(",").length <= 1 ) {
      this.imagePath = DEFAULT.getImagePath();
      this.opacity = DEFAULT.getOpacity();
      this.fillType = DEFAULT.getFillType();
      this.placementType = DEFAULT.getPlacementType();
    } else {
      var props = fromPropertyString.split(",");

      this.imagePath = Objects.requireNonNullElse(props[0], "");
      this.opacity = Objects.requireNonNullElse(Integer.parseInt(props[1]), 50) ;
      this.fillType = Objects.requireNonNullElse(FillType.valueOf(props[2]), FillType.SCALE);
      this.placementType = Objects.requireNonNullElse(PlacementType.valueOf(props[3]), PlacementType.CENTER);
    }
  }

  public ImageProperty(final String imagePath,
                       final int opacity,
                       final FillType fillType,
                       final PlacementType placementType) {
    this.imagePath = imagePath;
    this.opacity = opacity;
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
    return opacity;
  }

  public void setOpacity(int opacity) {
    this.opacity = opacity;
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
           opacity + ',' +
           fillType.toString() + ',' +
           placementType.toString();
  }
}

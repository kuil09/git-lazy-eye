package hwang.gg.gitLazyEye.property;

public class ImageProperty {
  public final static ImageProperty DEFAULT = new ImageProperty("",
          0,
          FillType.PLAIN,
          PlacementType.CENTER);

  private String imagePath;
  private int opacity;
  private FillType fillType;
  private PlacementType placementType;

  public ImageProperty(final String fromPropertyString) {
    var props = fromPropertyString.split(",");

    if (props.length <= 1) { // something is wrong
      this.imagePath = this.DEFAULT.getImagePath();
      this.opacity = this.DEFAULT.getOpacity();
      this.fillType = this.DEFAULT.getFillType();
      this.placementType = this.DEFAULT.getPlacementType();
    } else {
      this.imagePath = props[0];
      this.opacity = Integer.valueOf(props[1]);
      this.fillType = FillType.valueOf(props[2]);
      this.placementType = PlacementType.valueOf(props[3]);
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

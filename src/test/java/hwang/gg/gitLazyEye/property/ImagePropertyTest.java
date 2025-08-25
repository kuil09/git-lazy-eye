package hwang.gg.gitLazyEye.property;

import junit.framework.TestCase;

public class ImagePropertyTest extends TestCase {

  final String givenImagePath = "/test.jpg";
  final int givenOpacity = 100;
  final FillType givenFillType = FillType.PLAIN;
  final PlacementType givenPlacementType = PlacementType.CENTER;

  final ImageProperty given = new ImageProperty(
          givenImagePath,
          givenOpacity,
          givenFillType,
          givenPlacementType);

  public void testGetImagePath() {
    assertEquals(given.getImagePath(), givenImagePath);
  }

  public void testSetImagePath() {
    final String modifiedImagePath = "/foo.png";
    given.setImagePath(modifiedImagePath);
    assertEquals(given.getImagePath(), modifiedImagePath);
  }

  public void testGetOpacity() {
    assertEquals(given.getOpacity(), givenOpacity);
  }

  public void testSetOpacity() {
    final int modifiedOpacity = 50;
    given.setOpacity(modifiedOpacity);
    assertEquals(given.getOpacity(), modifiedOpacity);
  }

  public void testGetFillType() {
    assertEquals(given.getFillType(), givenFillType);
  }

  public void testSetFillType() {
    final FillType modifiedFillType = FillType.TILE;
    given.setFillType(modifiedFillType);
    assertEquals(given.getFillType(), modifiedFillType);
  }

  public void testGetPlacementType() {
    assertEquals(given.getPlacementType(), givenPlacementType);
  }

  public void testSetPlacementType() {
    final PlacementType modifiedPlacementType = PlacementType.TOP_CENTER;
    given.setPlacementType(modifiedPlacementType);
    assertEquals(given.getPlacementType(), modifiedPlacementType);
  }

  public void testToString() {
    final String expected = givenImagePath + ',' +
            givenOpacity + ',' +
            givenFillType.toString() + ',' +
            givenPlacementType.toString();

    assertEquals(expected, given.toString());
  }

  public void testStringConstructor() {
    String props = "/bar.png,30,SCALE,TOP_CENTER";
    ImageProperty parsed = new ImageProperty(props);

    assertEquals("/bar.png", parsed.getImagePath());
    assertEquals(30, parsed.getOpacity());
    assertEquals(FillType.SCALE, parsed.getFillType());
    assertEquals(PlacementType.TOP_CENTER, parsed.getPlacementType());
  }
}

package hwang.gg.gitLazyEye.property;

import junit.framework.TestCase;

public class OpacityTest extends TestCase {

  public void testOpacityClampingFix() {
    Opacity opacity = new Opacity(0);
    
    // Test that values above 100 are clamped to 100
    opacity.setValue(150);
    assertEquals("Opacity should be clamped to 100", 100, opacity.getValue());
    
    // Test that values at 100 stay at 100
    opacity.setValue(100);
    assertEquals("Opacity should be 100", 100, opacity.getValue());
    
    // Test that values below 0 are clamped to 0
    opacity.setValue(-10);
    assertEquals("Opacity should be clamped to 0", 0, opacity.getValue());
    
    // Test that values at 0 stay at 0
    opacity.setValue(0);
    assertEquals("Opacity should be 0", 0, opacity.getValue());
    
    // Test normal range values
    opacity.setValue(50);
    assertEquals("Opacity should be 50", 50, opacity.getValue());
  }
}
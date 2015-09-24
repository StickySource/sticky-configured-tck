package net.stickycode.configured.defaultkey;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

import net.stickycode.bootstrap.StickyBootstrap;
import net.stickycode.stereotype.configured.Configured;

public class AlternativeKeyTest {

  @ConfiguredWithAlternativeKey(defaultValue = "bob")
  String alternative;

  @ConfiguredWithAlternativeKey(defaultValue = "${bob}")
  String alternativeResolved;

  @ConfiguredWithAlternativeKey(defaultValue = "56,74,81")
  Integer[] numbers;

  @Configured
  String bob;

  @Test
  public void check() {
    StickyBootstrap crank = StickyBootstrap.crank(this, getClass()).inject(this);
    crank.start();
    assertThat(bob).as("configured is the control in this test").isEqualTo("yay");

    assertThat(alternative).as("default value should be used when no other exists").isEqualTo("bob");

    assertThat(alternativeResolved).as("placeholders should work in default values").isEqualTo("yay");

    assertThat(numbers).as("real values should be used over default ones").containsExactly(1, 5, 3, 7);
  }
}

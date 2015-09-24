package net.stickycode.configured;

import net.stickycode.configuration.ConfigurationKey;
import net.stickycode.configuration.ConfigurationSource;
import net.stickycode.configuration.ConfigurationValue;
import net.stickycode.configuration.ResolvedConfiguration;
import net.stickycode.stereotype.StickyPlugin;

@StickyPlugin
public class FakeConfigurationSource
    implements ConfigurationSource {
  private final class FakeConfigurationValue
      implements ConfigurationValue {
    private String seed;

    public FakeConfigurationValue(String seed) {
      this.seed = seed;
    }

    @Override
    public boolean hasPrecedence(ConfigurationValue v) {
      return false;
    }

    @Override
    public String get() {
      return seed;
    }

    @Override
    public String toString() {
      return String.format("FakeConfiguration[%s]", seed);
    }
  }

  @Override
  public void apply(ConfigurationKey key, ResolvedConfiguration values) {
    for (String joined : key.join("."))
      if (joined.endsWith("bob"))
        values.add(new FakeConfigurationValue("yay"));
      else
        if (joined.endsWith("numbers"))
          values.add(new FakeConfigurationValue("1,5,3,7"));
  }

}

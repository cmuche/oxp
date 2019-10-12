package cmuche.oxp.entities;

import java.util.Map;
import java.util.TreeMap;

public class TagCollection
{
  private Map<String, String> map;

  public TagCollection()
  {
    map = new TreeMap<>();
  }

  public void set(String key, String value)
  {
    map.put(key, value);
  }

  public String get(String key)
  {
    return map.get(key);
  }
}

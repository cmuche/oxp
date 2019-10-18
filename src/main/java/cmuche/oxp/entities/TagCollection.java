package cmuche.oxp.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TagCollection
{
  private Map<String, String> map;

  public TagCollection()
  {
    map = new HashMap<>();
  }

  public void set(String key, String value)
  {
    map.put(key, value);
  }

  public String get(String key)
  {
    return map.get(key);
  }

  public int countTags()
  {
    return map.size();
  }

  public boolean hasKey(String key)
  {
    return map.containsKey(key);
  }

  public Optional<Integer> getInt(String key)
  {
    try
    {
      return Optional.of(Integer.valueOf(get(key)));
    }
    catch (Exception ex)
    {
      return Optional.empty();
    }
  }
}

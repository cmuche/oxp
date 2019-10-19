package cmuche.oxp.entities;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TagCollection
{
  private final static String groupSeparator = ":";
  
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

  public int size()
  {
    return map.size();
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

  public Optional<Float> getFloat(String key)
  {
    try
    {
      return Optional.of(Float.valueOf(get(key)));
    }
    catch (Exception ex)
    {
      return Optional.empty();
    }
  }

  public TagCollection getGroup(String str)
  {
    TagCollection subCollection = new TagCollection();
    map.keySet().forEach(x ->
    {
      String[] parts = StringUtils.split(x, groupSeparator);
      if (parts.length < 2)
        return;

      if (parts[0].equals(str))
      {
        String[] remainingParts = Arrays.copyOfRange(parts, 1, parts.length);
        String thisKey = StringUtils.join(remainingParts, groupSeparator);
        subCollection.set(thisKey, get(x));
      }

    });

    return subCollection;
  }

}

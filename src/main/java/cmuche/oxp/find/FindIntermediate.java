package cmuche.oxp.find;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.OsmElement;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class FindIntermediate<T extends OsmElement>
{
  protected Osm osm;
  protected Stream<T> currentElements;

  public FindIntermediate(Osm osm, Stream<T> currentElements)
  {
    this.osm = osm;
    this.currentElements = currentElements;
  }

  public abstract FindIntermediate<T> tagValueIs(String key, String value);

  public abstract FindIntermediate<T> tagValueIsNot(String key, String value);

  public abstract FindIntermediate<T> hasTag(String key);

  protected void getTagValueIs(String key, String value)
  {
    currentElements = currentElements.filter(x -> x.getTags().hasKey(key) && x.getTags().get(key).equals(value));
  }

  protected void getTagValueIsNot(String key, String value)
  {
    currentElements = currentElements.filter(x -> x.getTags().hasKey(key) && !x.getTags().get(key).equals(value));
  }

  public void getHasTag(String key)
  {
    currentElements = currentElements.filter(x -> x.getTags().hasKey(key));
  }

  public Set<T> results()
  {
    return currentElements.collect(Collectors.toSet());
  }
}

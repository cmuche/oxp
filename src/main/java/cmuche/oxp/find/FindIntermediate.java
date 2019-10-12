package cmuche.oxp.find;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.OsmElement;
import cmuche.oxp.entities.Way;

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

  protected abstract FindIntermediate<T> makeInstance(Osm osm, Stream<T> currentElements);

  public FindIntermediate<T> tagValueIs(String key, String value)
  {
    Stream<T> stream = currentElements.filter(x -> x.getTags().hasKey(key) && x.getTags().get(key).equals(value));
    return makeInstance(osm, stream);
  }

  public FindIntermediate<T> tagValueIsNot(String key, String value)
  {
    Stream<T> stream = currentElements.filter(x -> x.getTags().hasKey(key) && !x.getTags().get(key).equals(value));
    return makeInstance(osm, stream);
  }

  public FindIntermediate<T> hasTag(String key)
  {
    Stream<T> stream = currentElements.filter(x -> x.getTags().hasKey(key));
    return makeInstance(osm, stream);
  }

  public Set<T> results()
  {
    return currentElements.collect(Collectors.toSet());
  }
}

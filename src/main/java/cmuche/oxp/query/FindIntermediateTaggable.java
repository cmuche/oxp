package cmuche.oxp.query;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.Node;
import cmuche.oxp.entities.OsmElement;
import cmuche.oxp.entities.Way;

import java.util.stream.Stream;

public abstract class FindIntermediateTaggable<T extends OsmElement> extends FindIntermediate<T>
{
  public FindIntermediateTaggable(Oxp oxp, Stream currentElements)
  {
    super(oxp, currentElements);
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

  public IntermediateWay<Way> ways()
  {
    Stream stream = currentElements.filter(x -> x instanceof Way);
    return new IntermediateWay(oxp, stream);
  }

  public IntermediateNode<Node> nodes()
  {
    Stream stream = currentElements.filter(x -> x instanceof Node);
    return new IntermediateNode(oxp, stream);
  }
}

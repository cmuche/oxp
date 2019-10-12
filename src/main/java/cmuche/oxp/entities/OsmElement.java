package cmuche.oxp.entities;

import lombok.Getter;

public abstract class OsmElement
{
  public OsmElement()
  {
    tags = new TagCollection();
  }

  @Getter
  protected String id;

  @Getter
  protected TagCollection tags;
}

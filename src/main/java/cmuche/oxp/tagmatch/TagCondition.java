package cmuche.oxp.tagmatch;

import cmuche.oxp.entities.TagCollection;

public interface TagCondition
{
  boolean matches(TagCollection tagCollection);
}

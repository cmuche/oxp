package cmuche.oxp.tagmatch.conditions.bool;

import cmuche.oxp.entities.TagCollection;
import cmuche.oxp.tagmatch.TagCondition;

public class OrCondition extends BooleanCondition
{
  public OrCondition(TagCondition condition1, TagCondition condition2)
  {
    super(condition1, condition2);
  }

  @Override
  public boolean matches(TagCollection tagCollection)
  {
    return condition1.matches(tagCollection) || condition2.matches(tagCollection);
  }
}

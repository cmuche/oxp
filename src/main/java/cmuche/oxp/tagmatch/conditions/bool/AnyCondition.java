package cmuche.oxp.tagmatch.conditions.bool;

import cmuche.oxp.tagmatch.TagCondition;

public class AnyCondition extends ListCondition
{
  public AnyCondition(TagCondition[] conditions)
  {
    super(conditions);
  }

  @Override
  protected BooleanCondition getBooleanCondition(TagCondition condition1, TagCondition condition2)
  {
    return new OrCondition(condition1, condition2);
  }
}

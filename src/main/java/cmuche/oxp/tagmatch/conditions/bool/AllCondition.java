package cmuche.oxp.tagmatch.conditions.bool;

import cmuche.oxp.tagmatch.TagCondition;

public class AllCondition extends ListCondition
{
  public AllCondition(TagCondition[] conditions)
  {
    super(conditions);
  }

  @Override
  protected BooleanCondition getBooleanCondition(TagCondition condition1, TagCondition condition2)
  {
    return new AndCondition(condition1, condition2);
  }
}

package cmuche.oxp.tagmatch.conditions.bool;

import cmuche.oxp.tagmatch.TagCondition;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BooleanCondition implements TagCondition
{
  protected TagCondition condition1;
  protected TagCondition condition2;
}
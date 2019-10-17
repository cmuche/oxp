package cmuche.oxp.tagmatch.conditions.bool;

import cmuche.oxp.entities.TagCollection;
import cmuche.oxp.tagmatch.TagCondition;

import java.util.Arrays;

public abstract class ListCondition implements TagCondition
{
  private TagCondition[] conditions;

  public ListCondition(TagCondition... conditions)
  {
    this.conditions = conditions;
  }

  protected abstract BooleanCondition getBooleanCondition(TagCondition condition1, TagCondition condition2);

  @Override
  public boolean matches(TagCollection tagCollection)
  {
    switch (conditions.length)
    {
      case 0:
        return true;
      case 1:
        return conditions[0].matches(tagCollection);
      default:
        return Arrays.stream(conditions).reduce((x, y) -> getBooleanCondition(x, y)).get().matches(tagCollection);
    }
  }

}

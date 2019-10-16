package cmuche.oxp.tagmatch;

import cmuche.oxp.tagmatch.conditions.bool.AndCondition;
import cmuche.oxp.tagmatch.conditions.bool.BooleanCondition;
import cmuche.oxp.tagmatch.conditions.bool.OrCondition;
import lombok.NonNull;

public class TagMatch
{
  public static TagCondition hasTag(@NonNull String tag)
  {
    return tagCollection -> tagCollection.get(tag) != null;
  }

  public static TagCondition hasNotTag(@NonNull String tag)
  {
    return tagCollection -> tagCollection.get(tag) == null;
  }

  public static TagCondition tagValueIs(@NonNull String tag, @NonNull String value)
  {
    return tagCollection ->
    {
      String currentValue = tagCollection.get(tag);
      if (currentValue == null)
        return false;
      return currentValue.equals(value);
    };
  }

  public static TagCondition tagValueIsNot(@NonNull String tag, @NonNull String value)
  {
    return tagCollection ->
    {
      String currentValue = tagCollection.get(tag);
      if (currentValue == null)
        return true;
      return !currentValue.equals(value);
    };
  }

  public static BooleanCondition or(TagCondition cond1, TagCondition cond2)
  {
    return new OrCondition(cond1, cond2);
  }

  public static BooleanCondition and(TagCondition cond1, TagCondition cond2)
  {
    return new AndCondition(cond1, cond2);
  }
}

package cmuche.oxp.tagmatch;

import cmuche.oxp.tagmatch.conditions.bool.*;
import lombok.NonNull;

public class TagMatch
{
  public static BaseCondition hasTag(@NonNull String tag)
  {
    return BaseCondition.of(tagCollection -> tagCollection.hasKey(tag));
  }

  public static BaseCondition hasNotTag(@NonNull String tag)
  {
    return BaseCondition.of(tagCollection -> !tagCollection.hasKey(tag));
  }

  public static BaseCondition tagValueIs(@NonNull String tag, @NonNull String value)
  {
    return BaseCondition.of(tagCollection ->
    {
      String currentValue = tagCollection.get(tag);
      if (currentValue == null)
        return false;
      return currentValue.equals(value);
    });
  }

  public static AnyCondition tagValueIsAny(@NonNull String tag, @NonNull String... values)
  {
    TagCondition[] conditions = new TagCondition[values.length];
    for (int i = 0; i < values.length; i++)
      conditions[i] = tagValueIs(tag, values[i]);

    return new AnyCondition(conditions);
  }

  public static BaseCondition tagValueContains(@NonNull String tag, @NonNull String value)
  {
    return BaseCondition.of(tagCollection ->
    {
      String currentValue = tagCollection.get(tag);
      if (currentValue == null)
        return false;
      return currentValue.contains(value);
    });
  }

  public static BaseCondition tagValueIsNot(@NonNull String tag, @NonNull String value)
  {
    return BaseCondition.of(tagCollection ->
    {
      String currentValue = tagCollection.get(tag);
      if (currentValue == null)
        return true;
      return !currentValue.equals(value);
    });
  }

  public static BooleanCondition or(TagCondition cond1, TagCondition cond2)
  {
    return new OrCondition(cond1, cond2);
  }

  public static BooleanCondition and(TagCondition cond1, TagCondition cond2)
  {
    return new AndCondition(cond1, cond2);
  }

  public static ListCondition any(TagCondition... conditions)
  {
    return new AnyCondition(conditions);
  }

  public static ListCondition all(TagCondition... conditions)
  {
    return new AllCondition(conditions);
  }
}

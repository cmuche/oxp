package cmuche.oxp.tagmatch;

import cmuche.oxp.entities.TagCollection;

import java.util.function.Function;

public class BaseCondition implements TagCondition
{
  private Function<TagCollection, Boolean> matchFunction;

  public static BaseCondition of(Function<TagCollection, Boolean> matchFunction)
  {
    return new BaseCondition(matchFunction);
  }

  private BaseCondition(Function<TagCollection, Boolean> matchFunction)
  {
    this.matchFunction = matchFunction;
  }

  protected boolean caseInsensitive;

  protected String cmpStr(String str)
  {
    if (str == null)
      return null;

    return caseInsensitive ? str.toUpperCase() : str;
  }

  public BaseCondition ci()
  {
    caseInsensitive = true;
    return this;
  }

  @Override
  public boolean matches(TagCollection tagCollection)
  {
    return matchFunction.apply(tagCollection);
  }
}

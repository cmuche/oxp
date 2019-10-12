package cmuche.oxp.parsing;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.*;
import lombok.Getter;
import org.apache.commons.lang3.EnumUtils;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Set;
import java.util.stream.Collectors;

public class SaxHandler extends DefaultHandler
{
  @Getter
  private Osm osm;

  private OsmElement currentOsmElement;

  public SaxHandler()
  {
    osm = new Osm();
  }

  private Coordinate attributesToCoordinates(Attributes attributes)
  {
    double lat = Double.valueOf(attributes.getValue("lat"));
    double lon = Double.valueOf(attributes.getValue("lon"));
    return new Coordinate(lat, lon);
  }

  private String getIdFromAttributes(Attributes attributes)
  {
    return attributes.getValue("id");
  }

  public <T extends OsmElement> T current(Class<T> type)
  {
    return type.cast(currentOsmElement);
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
  {
    if ("node".equals(qName))
    {
      Coordinate coordinates = attributesToCoordinates(attributes);
      String id = getIdFromAttributes(attributes);
      currentOsmElement = new Node(id, coordinates);
    }
    else if ("way".equals(qName))
    {
      String id = getIdFromAttributes(attributes);
      currentOsmElement = new Way(id);
    }
    else if ("relation".equals(qName))
    {
      String id = getIdFromAttributes(attributes);
      currentOsmElement = new Relation(id);
    }
    else if ("nd".equals(qName) && currentOsmElement instanceof Way)
    {
      String ref = attributes.getValue("ref");
      Node node = osm.getNodes().stream().filter(x -> x.getId().equals(ref)).findFirst().get();
      current(Way.class).getNodes().add(node);
    }
    else if ("member".equals(qName) && currentOsmElement instanceof Relation)
    {
      String ref = attributes.getValue("ref");
      String typeString = attributes.getValue("type");
      MemberType type = EnumUtils.getEnumIgnoreCase(MemberType.class, typeString);

      Set<OsmElement> searchCollection = null;
      switch (type)
      {
        case Way:
          searchCollection = osm.getWays().stream().collect(Collectors.toSet());
          break;
        case Node:
          searchCollection = osm.getNodes().stream().collect(Collectors.toSet());
          break;
        case Relation:
          searchCollection = osm.getRelations().stream().collect(Collectors.toSet());
          break;
      }

      OsmElement element = searchCollection.stream().filter(x -> x.getId().equals(ref)).findFirst().get();
      Member member = new Member(type, element);
      current(Relation.class).getMembers().add(member);
    }
    else if ("tag".equals(qName))
    {
      String key = attributes.getValue("k");
      String value = attributes.getValue("v");
      currentOsmElement.getTags().set(key, value);
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName)
  {
    if ("node".equals(qName))
    {
      osm.getNodes().add(current(Node.class));
      currentOsmElement = null;
    }
    else if ("way".equals(qName))
    {
      osm.getWays().add(current(Way.class));
      currentOsmElement = null;
    }
    else if ("relation".equals(qName))
    {
      osm.getRelations().add(current(Relation.class));
      currentOsmElement = null;
    }

  }
}

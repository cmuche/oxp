package cmuche.oxp.parsing;

import cmuche.oxp.Oxp;
import cmuche.oxp.entities.*;
import lombok.Getter;
import org.apache.commons.lang3.EnumUtils;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class OxpSaxHandler extends DefaultHandler
{
  @Getter
  private Oxp oxp;

  private OsmElement currentOsmElement;
  private Map<Id, OsmElement> elementIdMap;

  public OxpSaxHandler()
  {
    oxp = new Oxp();
    elementIdMap = new HashMap<>();
  }

  private Coordinate attributesToCoordinates(Attributes attributes)
  {
    double lat = Double.valueOf(attributes.getValue("lat"));
    double lon = Double.valueOf(attributes.getValue("lon"));
    return Coordinate.at(lat, lon);
  }

  private Id getId(String typeName, Attributes attributes)
  {
    ElementType type = EnumUtils.getEnumIgnoreCase(ElementType.class, typeName);
    String idStr = attributes.getValue("id");

    String versionStr = attributes.getValue("version");
    int versionVal = versionStr == null ? 0 : Integer.parseInt(attributes.getValue("version"));
    return new Id(type, idStr, versionVal);
  }

  public <T extends OsmElement> T current(Class<T> type)
  {
    return type.cast(currentOsmElement);
  }

  public <T extends OsmElement> T byRef(Class<T> type, Id id)
  {
    OsmElement element = elementIdMap.get(id);
    return type.cast(element);
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
  {
    if ("node".equals(qName))
    {
      Coordinate coordinates = attributesToCoordinates(attributes);
      Id id = getId(qName, attributes);
      currentOsmElement = new Node(id, coordinates);
    }
    else if ("way".equals(qName))
    {
      Id id = getId(qName, attributes);
      currentOsmElement = new Way(id);
    }
    else if ("relation".equals(qName))
    {
      Id id = getId(qName, attributes);
      currentOsmElement = new Relation(id);
    }
    else if ("nd".equals(qName) && currentOsmElement instanceof Way)
    {
      String ref = attributes.getValue("ref");
      Node node = byRef(Node.class, new Id(ElementType.Node, ref, 0));
      current(Way.class).getNodes().add(node);
    }
    else if ("member".equals(qName) && currentOsmElement instanceof Relation)
    {
      String ref = attributes.getValue("ref");
      String typeString = attributes.getValue("type");
      ElementType type = EnumUtils.getEnumIgnoreCase(ElementType.class, typeString);

      OsmElement element = byRef(OsmElement.class, new Id(type, ref, 0));
      if (element != null)
      {
        String role = attributes.getValue("role");
        role = role == null || role.isEmpty() ? null : role;

        Member member = Member.of(type, element, role);
        current(Relation.class).getMembers().add(member);
      }
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
      oxp.getNodes().add(current(Node.class));
      processedElement();
    }
    else if ("way".equals(qName))
    {
      Way currentWay = current(Way.class);
      currentWay.recalculateBoundingBox();
      oxp.getWays().add(currentWay);
      processedElement();
    }
    else if ("relation".equals(qName))
    {
      if (!current(Relation.class).getMembers().isEmpty())
        oxp.getRelations().add(current(Relation.class));

      processedElement();
    }
  }

  private void processedElement()
  {
    elementIdMap.put(currentOsmElement.getId(), currentOsmElement);
    currentOsmElement = null;
  }
}

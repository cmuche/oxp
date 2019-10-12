package cmuche.oxp.parsing;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.Coordinate;
import cmuche.oxp.entities.Node;
import cmuche.oxp.entities.OsmElement;
import cmuche.oxp.entities.Way;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
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
    else if ("nd".equals(qName) && currentOsmElement instanceof Way)
    {
      String ref = attributes.getValue("ref");
      Node node = osm.getNodes().stream().filter(x -> x.getId().equals(ref)).findFirst().get();
      current(Way.class).getNodes().add(node);
    }
    else if ("tag".equals(qName))
    {
      String key = attributes.getValue("k");
      String value = attributes.getValue("v");
      currentOsmElement.getTags().set(key, value);
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException
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

  }
}

package cmuche.oxp.parsing;

import cmuche.oxp.Osm;
import cmuche.oxp.entities.Coordinate;
import cmuche.oxp.entities.Node;
import cmuche.oxp.entities.Way;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler
{
  @Getter
  private Osm osm;

  private Node currentNode;
  private Way currentWay;

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

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
  {
    if ("node".equals(qName))
    {
      Coordinate coordinates = attributesToCoordinates(attributes);
      String id = attributes.getValue("id");
      currentNode = new Node(id, coordinates);
    }
    else if ("way".equals(qName))
    {
      String id = attributes.getValue("id");
      currentWay = new Way(id);
    }
    else if ("nd".equals(qName) && currentWay != null)
    {
      String ref = attributes.getValue("ref");
      Node node = osm.getNodes().stream().filter(x -> x.getId().equals(ref)).findFirst().get();
      currentWay.getNodes().add(node);
    }
    else if ("tag".equals(qName))
    {
      String key = attributes.getValue("k");
      String value = attributes.getValue("v");

      if (currentNode != null)
        currentNode.getTags().set(key, value);
      else if (currentWay != null)
        currentWay.getTags().set(key, value);
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException
  {
    if ("node".equals(qName))
    {
      osm.getNodes().add(currentNode);
      currentNode = null;
    }
    else if ("way".equals(qName))
    {
      osm.getWays().add(currentWay);
      currentWay = null;
    }
  }
}

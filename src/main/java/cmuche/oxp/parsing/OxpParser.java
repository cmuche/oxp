package cmuche.oxp.parsing;

import cmuche.oxp.Oxp;
import cmuche.oxp.exceptions.OxpException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class OxpParser
{
  public static Oxp parseOsmFile(File file) throws OxpException
  {
    try
    {
      String xml = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
      return parseOsmXml(xml);
    }
    catch (IOException ex)
    {
      throw new OxpException("Could not read file: " + ex.getMessage());
    }
  }

  public static Oxp parseOsmXml(String xml) throws OxpException
  {
    InputStream stream = IOUtils.toInputStream(xml, StandardCharsets.UTF_8);
    return parseOsmStream(stream);
  }

  public static Oxp parseOsmStream(InputStream stream) throws OxpException
  {
    try
    {
      SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
      SAXParser saxParser = saxParserFactory.newSAXParser();
      OxpSaxHandler handler = new OxpSaxHandler();
      saxParser.parse(stream, handler);

      Oxp oxp = handler.getOxp();
      return oxp;
    }
    catch (Exception ex)
    {
      throw new OxpException("Could not parse OSM XML: " + ex.getMessage());
    }
  }
}

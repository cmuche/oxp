[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.cmuche/oxp/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.cmuche/oxp)


# OpenStreetMap XML Parser

## Features
- Parses OSM XML files (or any other OSM XML input) and offers an easy to use interface for the basic element types (nodes, ways, relations).
- Stores the data efficiently in memory, no database required
- Offers a powerful API to search for specific OSM data types, tags or features

## Maven
```
<dependency>
  <groupId>de.cmuche</groupId>
  <artifactId>oxp</artifactId>
  <version>1.0</version>
</dependency>
```

## Examples
```
Oxp oxp = OxpParser.parseOsmFile(new File("map.osm"));
    
// Find all ways that create an area
Set<Way> allAreas = oxp.query()
        .ways()
        .isArea()
        .go();

// Find all nodes or ways which represent a building
Set<OsmElement> allBuildings = oxp.query()
        .tagsMatch(TagMatch.and(TagMatch.tagValueIs("building", "yes"), TagMatch.hasTag("leaf_type")))
        .go();

// Find all trees which have their leaf type specified
Set<Node> allTrees = oxp.query().nodes().tagsMatch(TagMatch.tagValueIs("natural", "tree")).go();

// Find all elements which are bus stops and are referenced in a relation which is a bus line and has the line number '36'
Set<OsmElement> stops = oxp.query()
        .relations()
        .tagsMatch(TagMatch.and(TagMatch.tagValueIs("route", "bus"), TagMatch.tagValueIs("ref", "36")))
        .members()
        .elements()
        .tagsMatch(TagMatch.tagValueIs("highway", "bus_stop")).go();

// Find all elements which have an address with an even housenumber (Custom Tag Condition)
Set<OsmElement> evenHousenumbers = oxp.query().tagsMatch(tagCollection ->
    {
      Optional<Integer> hn = tagCollection.getInt("addr:housenumber");
      return hn.isPresent() && hn.get() % 2 == 0;
    }).go();
```

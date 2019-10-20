[![Maven Central](https://img.shields.io/maven-central/v/de.cmuche/oxp.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22de.cmuche%22%20AND%20a:%22oxp%22)
![Build Status](https://cmuche.de/dev/oxp_build_status.php)

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
  <version>1.1</version>
</dependency>
```

## Usage

### Parsing
```
// From file
Oxp oxp = OxpParser.parseOsmFile(new File("map.osm"));

// From string
Oxp oxp = OxpParser.parseOsmFile("...");

// From stream
Oxp oxp = OxpParser.parseOsmStream(...);
```

### Queries

With the ```oxp.query()``` method you can chain element filters which are applied to the elements stored in the OXP object.
The ```.go()``` method returns a set which holds the query results. There are some useful example queries in the 'Examples' section.

#### Scopes
Filters inside a query are scoped. This means some filters return elements of a specific type or can only be applied on filter results of a specific type (e.g. the ```isArea()``` filter can only be applied on ways).
Filters which can return multiple element types can be type-filtered: For example ```oxp.query().ways()``` and following filters returns are way-scoped.

##### All elements
Since all OSM elements can have tags, tag filters are applicable on all filter types. Also bounding box related filters are possible on all types.

```
oxp.query().tagsMatch(TagMatcher.hasTag("man_made")).go();
oxp.query().inBounds(new BoundingBox(52.02184, 52.02476, 8.52949, 8.53792)).go();
```

##### Nodes
...

##### Ways
...

##### Relations and Members
...

Query results can also be reused and filtered again:
```
oxp.queryFrom(elements)
oxp.queryFrom(nodes)
oxp.queryFrom(ways)
oxp.queryFrom(relations)
```

### Tag Matchers and Conditions
...

## Examples

```    
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

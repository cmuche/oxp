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
  <version>1.2</version>
</dependency>
```

## Usage

### Parsing
```
Oxp oxp = OxpParser.parseOsmFile(new File("map.osm"));
Oxp oxp = OxpParser.parseOsmXml("<osm><...");
Oxp oxp = OxpParser.parseOsmStream(inputStream);
```

### Methods

#### Data
The parsed elements can be accessed via:
```
oxp.getNodes();
oxp.getWays();
oxp.getRelations();
```

#### Tags
Every OSM element has a collection of tags which can be accessed via ```element.getTags()```. 
It provides basic get functionality:

```
TagCollection tagCollection = node.getTags();
tagCollection.get("amenity");
tagCollection.getInt("ref");
tagCollection.getFloat("height");
```

##### Groups
The ```tagCollection.group(...)``` method returns a subcollection which only includes tags matching the given prefix.

```
group = tagCollection.group("addr")

// tagCollection: ["addr:street" -> "Foostreet", "addr:housenumber" -> "42"]
// group:         [     "street" -> "Foostreet",      "housenumber" -> "42"]
```

#### Ways
- ```way.getNodes()``` returns an ordered list of nodes
- ```way.isArea()``` checks if the first node equals the last node

#### Relations
...

#### Spatial
##### Coordinates
...
##### Bounding Boxes
...

### Queries

With the ```oxp.query()``` method you can chain element filters which are applied to the elements stored in the OXP object.
The ```.go()``` method returns a set which holds the query results. There are some useful example queries in the 'Examples' section.

#### Scopes
Filters inside a query are scoped. This means some filters return elements of a specific type or can only be applied on filter results of a specific type (e.g. the ```isArea()``` filter can only be applied on ways).
Filters which can return multiple element types can be type-filtered: For example ```oxp.query().ways()``` and following filter results are way-scoped.

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

#### Reusing
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
        .tagsMatch(TagMatch.tagValueIs("building", "yes"))
        .go();

// Find all trees which have their leaf type specified
Set<Node> allTrees = oxp.query()
        .nodes()
        .tagsMatch(TagMatch.and(TagMatch.tagValueIs("natural", "tree"), TagMatch.hasTag("leaf_type")))
        .go();

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

## Tools
### Slippy Map tiles
...

## Roadmap / TODO
- Relations shape merging
- Boolean tag values
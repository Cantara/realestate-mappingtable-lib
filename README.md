# realestate-mappingtable-app
Mapping sensors from eg BACNet to Real Estate Core

## Ingestion Plugins
Eg. Metasys, BACNet, Modbus, Schneider EcoStruxure, etc.

These plugins must implement the following interface:

```java
public interface MappingKey<T> {
    T getKey();
}
```


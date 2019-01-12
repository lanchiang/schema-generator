package schema;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Lan Jiang
 * @since 1/8/19
 */
public class SchemaMapping {

    private Schema currentSchema;

    /**
     * Each key represents a particular attribute in the source schema, its value is a set of attributes in the target schema
     * that are derived from the attribute in the key.
     */
    private Map<Attribute, Set<Attribute>> mapping;

    public SchemaMapping() {
        this.mapping = new HashMap<>();
    }

    public SchemaMapping(Schema currentSchema) {
        this();
        this.currentSchema = currentSchema;
    }

    public Schema getCurrentSchema() {
        return currentSchema;
    }

    public Map<Attribute, Set<Attribute>> getMapping() {
        return mapping;
    }

    public void updateMapping(Attribute sourceAttribute, Attribute targetAttribute) {
        if (sourceAttribute == null) {
            throw new RuntimeException("Source attribute can not be found in the current schema.");
        }
        if (targetAttribute != null) {
            this.mapping.putIfAbsent(sourceAttribute, new HashSet<>());
            this.mapping.get(sourceAttribute).add(targetAttribute);
        } else {
            this.mapping.get(sourceAttribute).remove(targetAttribute);
        }
    }

    public void updateSchema(Schema latestSchema) {
        this.currentSchema = latestSchema;
    }
}

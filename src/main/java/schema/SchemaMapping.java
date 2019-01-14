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

    public SchemaMapping(Schema currentSchema) {
        this.currentSchema = currentSchema;

        this.mapping = new HashMap<>();
        for (Attribute attribute : currentSchema.getAttributes()) {
            this.mapping.putIfAbsent(attribute, new HashSet<>());
        }
    }

    public Schema getCurrentSchema() {
        return currentSchema;
    }

    public Map<Attribute, Set<Attribute>> getMapping() {
        return mapping;
    }

    /**
     * Update the schemaMapping instance with the given source and target attributes.
     *
     * @param sourceAttribute
     * @param targetAttribute
     */
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

    /**
     * Update the schema with the given latest schema.
     *
     * @param latestSchema is the latest schema after transformation
     */
    public void updateSchema(Schema latestSchema) {
        this.currentSchema = latestSchema;
    }
}

package transform;

import schema.SchemaMapping;

/**
 * @author Lan Jiang
 * @since 1/8/19
 */
abstract public class Transform {

    protected SchemaMapping schemaMapping;

    protected void initSchemaMapping() {
        this.schemaMapping = new SchemaMapping();
    }

    abstract public void reformSchema();
}

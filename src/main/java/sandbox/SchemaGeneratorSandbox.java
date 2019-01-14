package sandbox;

import metadata.Metadata;
import schema.Schema;
import schema.SchemaMapping;
import transform.Transform;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a sandbox where all the schema changes are made.
 *
 * @author Lan Jiang
 * @since 1/14/19
 */
public class SchemaGeneratorSandbox {

    private SchemaMapping schemaMapping;
    private List<Transform> transforms = new LinkedList<>();
    private List<Metadata> metadata;

    private SchemaGeneratorSandbox() {
        initMetadata();
    }

    public SchemaGeneratorSandbox(Schema currentSchema) {
        this();
        this.schemaMapping = new SchemaMapping(currentSchema);
    }

    public void generateStep(Transform transform) {
        transforms.add(transform);
        transform.reformSchema();
    }

    public SchemaMapping getSchemaMapping() {
        return schemaMapping;
    }

    public void setSchemaMapping(SchemaMapping schemaMapping) {
        this.schemaMapping = schemaMapping;
    }

    protected void initMetadata() {
        metadata = new LinkedList<>();
    }
}

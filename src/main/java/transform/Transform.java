package transform;

import sandbox.SchemaGeneratorSandbox;
import schema.SchemaMapping;

/**
 * @author Lan Jiang
 * @since 1/8/19
 */
abstract public class Transform {

    protected SchemaGeneratorSandbox owner;

    public SchemaGeneratorSandbox getOwner() {
        return owner;
    }

    public void setOwner(SchemaGeneratorSandbox owner) {
        this.owner = owner;
    }

    abstract public void reformSchema();
}

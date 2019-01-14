package transform;

import sandbox.SchemaGeneratorSandbox;
import schema.Attribute;
import schema.Schema;
import schema.SchemaMapping;
import util.SchemaUtils;

/**
 * @author Lan Jiang
 * @since 1/8/19
 */
public class SplitAttribute extends Transform {

    private Attribute sourceAttribute;
    private Attribute[] targetAttributes;

    public SplitAttribute(Attribute sourceAttribute, Attribute[] targetAttributes) {
        this.sourceAttribute = sourceAttribute;
        this.targetAttributes = targetAttributes;
    }

    public void reformSchema() {
        SchemaMapping schemaMapping = owner.getSchemaMapping();

        // check whether the sourceAttribute exists in the schema
        if (!schemaMapping.getCurrentSchema().attributeExist(sourceAttribute)) {
            throw new RuntimeException("Attribute does not exist.");
        }

        if (SchemaUtils.targetAttributeExist(schemaMapping.getCurrentSchema(), targetAttributes)!=null) {
            throw new RuntimeException("Attribute(s) already exist in the schema.");
        }

        Schema currentSchema = schemaMapping.getCurrentSchema();
        // here ready to execute this transform
        for (Attribute attribute : targetAttributes) {
            schemaMapping.updateMapping(sourceAttribute, attribute);
            currentSchema.addAttribute(attribute);
        }
        schemaMapping.updateSchema(currentSchema);
        owner.setSchemaMapping(schemaMapping);
    }
}

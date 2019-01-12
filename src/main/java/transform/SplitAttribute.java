package transform;

import schema.Attribute;
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
        // check whether the sourceAttribute exists in the schema
        if (!this.schemaMapping.getCurrentSchema().attributeExist(sourceAttribute)) {
            System.out.println("Attribute does not exist.");
            System.exit(1);
        }

        if (SchemaUtils.targetAttributeExist(schemaMapping.getCurrentSchema(), targetAttributes)!=null) {
            System.out.println("Attribute(s) already exist in the schema.");
            System.exit(1);
        }

        // here ready to execute this transform
        for (Attribute attribute : targetAttributes) {
            this.schemaMapping.updateMapping(sourceAttribute, attribute);
        }
    }
}

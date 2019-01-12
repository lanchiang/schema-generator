package schema;

import java.util.List;

/**
 * @author Lan Jiang
 * @since 1/8/19
 */
public class Schema {

    private List<Attribute> attributes;

    public Schema(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public boolean attributeExist(Attribute attribute) {
        return attributes.contains(attribute);
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}

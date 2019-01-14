package schema;

import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.LinkedList;
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

    public Schema(StructType structType) {
        this.attributes = new LinkedList<>();
        for (StructField structField : structType.fields()) {
            this.attributes.add(new Attribute(structField));
        }
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public boolean attributeExist(Attribute attribute) {
        return attributes.contains(attribute);
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}

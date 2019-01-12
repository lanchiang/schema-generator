import org.junit.Before;
import org.junit.Test;
import schema.SchemaMapping;
import transform.SplitAttribute;
import transform.Transform;

/**
 * @author Lan Jiang
 * @since 1/12/19
 */
public class RenewSchemaTest {



    @Before
    public void setUp() throws Exception {
        SchemaMapping schemaMapping = new SchemaMapping();
    }

    @Test
    public void addSplitAttribute() {
        Transform splitAttr = new SplitAttribute(null, null);
        splitAttr.reformSchema();
    }
}

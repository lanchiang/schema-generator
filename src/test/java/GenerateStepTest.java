import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.junit.BeforeClass;
import org.junit.Test;
import sandbox.SchemaGeneratorSandbox;
import schema.Attribute;
import schema.Schema;
import transform.MergeAttribute;
import transform.SplitAttribute;
import transform.Transform;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lan Jiang
 * @since 1/14/19
 */
public class GenerateStepTest {

    private final static String dataPath = "./src/test/resources/pokemon.csv";
    private static SchemaGeneratorSandbox schemaGeneratorSandbox;

    @BeforeClass
    public static void setUp() {
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        Dataset<Row> dataset = SparkSession.builder().appName("Renew schema test").master("local")
                .getOrCreate().read()
                .option("header", "true")
                .option("inferSchema", "true")
                .csv(dataPath);
        dataset.printSchema();

        schemaGeneratorSandbox = new SchemaGeneratorSandbox(new Schema(dataset.schema()));
    }

    @Test
    public void GenerateTwoStepsTest() {
        Attribute sourceAtrribute = new Attribute(new StructField("date", DataTypes.StringType, true, Metadata.empty()));
        Attribute targetAttribute1 = new Attribute(new StructField("date1", DataTypes.StringType, true, Metadata.empty()));
        Attribute targetAttribute2 = new Attribute(new StructField("date2", DataTypes.StringType, true, Metadata.empty()));
        Attribute targetAttribute3 = new Attribute(new StructField("date3", DataTypes.StringType, true, Metadata.empty()));
        Attribute[] targetAttributes = new Attribute[3];
        targetAttributes[0] = targetAttribute1;
        targetAttributes[1] = targetAttribute2;
        targetAttributes[2] = targetAttribute3;

        Transform splitAttr = new SplitAttribute(sourceAtrribute, targetAttributes);
        splitAttr.setOwner(schemaGeneratorSandbox);

        schemaGeneratorSandbox.generateStep(splitAttr);

        Attribute sourceAttribute1 = new Attribute(new StructField("id", DataTypes.StringType, true, Metadata.empty()));
        Attribute sourceAttribute2 = new Attribute(new StructField("identifier", DataTypes.StringType, true, Metadata.empty()));
        Attribute sourceAttribute3 = new Attribute(new StructField("base_experience", DataTypes.StringType, true, Metadata.empty()));
        Attribute targetAttribute = new Attribute(new StructField("merge", DataTypes.StringType, true, Metadata.empty()));
        Attribute[] sourceAttributes = new Attribute[3];
        sourceAttributes[0] = sourceAttribute1;
        sourceAttributes[1] = sourceAttribute2;
        sourceAttributes[2] = sourceAttribute3;

        Transform mergeAttr = new MergeAttribute(sourceAttributes, targetAttribute);
        mergeAttr.setOwner(schemaGeneratorSandbox);

        schemaGeneratorSandbox.generateStep(mergeAttr);
    }
}

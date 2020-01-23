/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.universidadOntology;

//import static com.sun.javafx.fxml.expression.Expression.add;
import jade.content.onto.*;
import jade.content.schema.*;
/*import jade.content.onto.BasicOntology.*;
import jade.domain.FIPANames.Ontology;
import jade.util.leap.HashMap;
import jade.content.lang.Codec;
import jade.core.CaseInsensitiveString;*/

/**
 * -gui -port 1098 pucese:ontologias.universidadOntology.UniversidadAgent(100);secretario:ontologias.universidadOntology.SecretarioAgent
 * -gui pucese:universidadOntology.UniversidadAgent(100);secretario:universidadOntology.SecretarioAgent
 * @author Maximiliano
 */
public class UniversidadOntology extends Ontology {
    // Nombre de la ontología

    public static final String ONTOLOGY_NAME = "UniversidadOntology";
    // The singleton instance of this ontology
    private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
    private static Ontology theInstance = new UniversidadOntology();

    public static Ontology getInstance() {
        return theInstance;
    }
    // Vocabulario de la ontología que van a manejar los agentes
    //CONCEPTO
    public static final String PERSONA = "Persona";
    public static final String PERSONA_NOMBRE = "nombre";
    public static final String PERSONA_DNI = "dni";
    public static final String PERSONA_NOTA = "nota";
    public static final String PERSONA_TITULACION = "titulacion";

    public static final String MOTIVO = "Motivo";
    public static final String MOTIVO_MOTIVO = "motivo";

    //PREDICADO
    public static final String ADMITIDA = "Admitida";
    public static final String PERSONA_ADMITIDA = "persona";

    public static final String ERROR_ADMISION = "ErrorAdmision";
    public static final String ERROR_ADMISION_MOTIVO = "MOTIVO";

    public static final String NO_SUPERA_NOTA = "NoSuperaNota";
    public static final String PERSONA_NO_SUPERA_NOTA = "persona";

    //ACCION
    public static final String ADMITIR = "Admitir";
    public static final String ADMITIR_PERSONA = "persona";

    public static final String EXCLUIR = "Excluir";
    public static final String EXCLUIR_PERSONA = "persona";

    // Constructor privado
    private UniversidadOntology() {
        // universidadOntology extiende la ontología básica
        super(ONTOLOGY_NAME,BasicOntology.getInstance());
        
        try {
            // adding Concept(s)
            ConceptSchema personaSchema = new ConceptSchema(PERSONA);
            add(personaSchema, Persona.class);
            ConceptSchema motivoSchema = new ConceptSchema(MOTIVO);
            add(motivoSchema, Motivo.class);

            // adding Predicate(s)
            PredicateSchema admitidaSchema = new PredicateSchema(ADMITIDA);
            add(admitidaSchema, Admitida.class);
            PredicateSchema erroradmisionSchema = new PredicateSchema(ERROR_ADMISION);
            add(erroradmisionSchema, ErrorAdmision.class);
            PredicateSchema nosuperanotaSchema = new PredicateSchema(NO_SUPERA_NOTA);
            add(nosuperanotaSchema, NoSuperaNota.class);

            // adding AgentAction(s)
            AgentActionSchema admitirSchema = new AgentActionSchema(ADMITIR);
            add(admitirSchema, Admitir.class);
            AgentActionSchema excluirSchema = new AgentActionSchema(EXCLUIR);
            add(excluirSchema, Excluir.class);
            
             // adding fields
            personaSchema.add(PERSONA_NOMBRE, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            personaSchema.add(PERSONA_DNI, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            personaSchema.add(PERSONA_NOTA, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
            personaSchema.add(PERSONA_TITULACION, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            
            motivoSchema.add(MOTIVO_MOTIVO, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            
            admitidaSchema.add(PERSONA_ADMITIDA, personaSchema, ObjectSchema.OPTIONAL);
            excluirSchema.add(EXCLUIR_PERSONA, personaSchema, ObjectSchema.OPTIONAL);
            nosuperanotaSchema.add(PERSONA_NO_SUPERA_NOTA, personaSchema, ObjectSchema.OPTIONAL);
            erroradmisionSchema.add(ERROR_ADMISION_MOTIVO, motivoSchema, ObjectSchema.OPTIONAL);
            nosuperanotaSchema.add(NO_SUPERA_NOTA, personaSchema, ObjectSchema.OPTIONAL);
            
            
            
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}

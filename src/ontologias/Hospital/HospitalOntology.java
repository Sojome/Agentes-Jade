/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Hospital;

//import static com.sun.javafx.fxml.expression.Expression.add;
import jade.content.onto.*;
import jade.content.schema.*;
import ontologias.Hospital.Concept.*;
import ontologias.Hospital.Predicate.*;
import ontologias.Hospital.Accion.*;
/*import jade.content.onto.BasicOntology.*;
 import jade.domain.FIPANames.Ontology;
 import jade.util.leap.HashMap;
 import jade.content.lang.Codec;
 import jade.core.CaseInsensitiveString;*/

/**
 * @author Aaron Jaramillo
 */
public class HospitalOntology extends Ontology {
    // Nombre de la ontología

    public static final String ONTOLOGY_NAME = "HospitalOntology";
    // The singleton instance of this ontology
    private static ReflectiveIntrospector introspect = new ReflectiveIntrospector();
    private static Ontology theInstance = new HospitalOntology();

    public static Ontology getInstance() {
        return theInstance;
    }
    // Vocabulario de la ontología que van a manejar los agentes
    //CONCEPTO

    //Paciente
    public static final String PACIENTE = "Paciente";
    public static final String PACIENTE_NOMBRE = "nombre";
    public static final String PACIENTE_APELLIDO = "apellido";
    public static final String PACIENTE_NACIMIENTO = "nacimiento";
    public static final String PACIENTE_CEDULA = "cedula";
    public static final String PACIENTE_DOMICILIO = "domicilio";

    //Motivo
    public static final String MOTIVO = "Motivo";
    public static final String MOTIVO_MOTIVO = "motivo";

    //Enfermedad
    public static final String ENFERMEDAD = "Enfermedad";
    public static final String ENFERMEDAD_ENFERMEDAD = "enfermedad";
    public static final String ENFERMEDAD_CURA = "cura";

    //Tiempo de llegada
    public static final String TIEMPOLLEGADA_TIEMPO = "tiempo";
    public static final String TIEMPOLLEGADA = "TiempoLlegada";

    //Sintoma
    public static final String SINTOMA = "Sintoma";
    public static final String SINTOMA_SINTOMA = "sintoma";
    public static final String SINTOMA_IDEN= "iden";

    //Ambulancia
    public static final String AMBULANCIA = "Ambulancia";
    public static final String AMBULANCIA_PLACA = "placa";
    public static final String AMBULANCIA_CONDUCTOR = "conductor";

    //PREDICADO
    //Disponible
    public static final String DISPONIBLE = "Disponible";
    public static final String DISPONIBLE_PACIENTE = "Paciente";

    //NoDisponible
    public static final String NODISPONIBLE = "NoDisponible";
    public static final String NODISPONIBLE_MOTIVO = "Motivo";

    //PacienteNoAtendido
    public static final String PACIENTENOATENDIDO = "PacienteNoAtendido";
    public static final String PACIENTENOATENDIDO_MOTIVO = "Motivo";

    //PacienteAtendido
    public static final String PACIENTEATENDIDO = "PacienteAtendido";
    public static final String PACIENTEATENDIDO_PACIENTE = "Paciente";
    public static final String PACIENTEATENDIDO_SINTOMA = "Sintoma";

    //Recetar
    public static final String RECETAR = "Recetar";
    public static final String RECETAR_ENFERMEDAD = "Enfermedad";

    //Envio ambulancia
    public static final String ENVIOAMBULANCIA = "EnvioAmbulancia";
    public static final String ENVIOAMBULANCIA_AMBULANCIA = "Ambulancia";

    //ACCION
    //Diagnosticar
    public static final String DIAGNOSTICAR = "Diagnosticar";
    public static final String DIAGNOSTICAR_PACIENTE = "Paciente";

    //EmergencyCall
    public static final String EMERGENCYCALL = "EmergencyCall";
    public static final String EMERGENCYCALL_AMBULANCIA = "Ambulancia";

    // Constructor privado
    private HospitalOntology() {
        // universidadOntology extiende la ontología básica
        super(ONTOLOGY_NAME, BasicOntology.getInstance());

        try {
            // adding Concept(s)
            ConceptSchema pacienteSchema = new ConceptSchema(PACIENTE);
            add(pacienteSchema, Paciente.class);

            ConceptSchema motivoSchema = new ConceptSchema(MOTIVO);
            add(motivoSchema, Motivo.class);

            ConceptSchema tiempollegadaSchema = new ConceptSchema(TIEMPOLLEGADA);
            add(tiempollegadaSchema, TiempoLlegada.class);

            ConceptSchema enfermedadSchema = new ConceptSchema(ENFERMEDAD);
            add(enfermedadSchema, Enfermedad.class);

            ConceptSchema sintomaSchema = new ConceptSchema(SINTOMA);
            add(sintomaSchema, Sintoma.class);

            ConceptSchema ambulanciaSchema = new ConceptSchema(AMBULANCIA);
            add(ambulanciaSchema, Ambulancia.class);

            // adding Predicate(s)
            PredicateSchema disponibleSchema = new PredicateSchema(DISPONIBLE);
            add(disponibleSchema, Disponible.class);

            PredicateSchema nodisponibleSchema = new PredicateSchema(NODISPONIBLE);
            add(nodisponibleSchema, NoDisponible.class);

            PredicateSchema pacientenoatendidoSchema = new PredicateSchema(PACIENTENOATENDIDO);
            add(pacientenoatendidoSchema, PacienteNoAtendido.class);

            PredicateSchema pacienteatendidoSchema = new PredicateSchema(PACIENTEATENDIDO);
            add(pacienteatendidoSchema, PacienteAtendido.class);

            PredicateSchema recetarSchema = new PredicateSchema(RECETAR);
            add(recetarSchema, Recetar.class);

            PredicateSchema envioambulancia = new PredicateSchema(ENVIOAMBULANCIA);
            add(envioambulancia, EnvioAmbulancia.class);

            // adding AgentAction(s)
            AgentActionSchema diagnosticarSchema = new AgentActionSchema(DIAGNOSTICAR);
            add(diagnosticarSchema, Diagnosticar.class);

            AgentActionSchema emergencycallSchema = new AgentActionSchema(EMERGENCYCALL);
            add(emergencycallSchema, EmergencyCall.class);

            //Añadir campos
            //Concepto
            //Paciente
            pacienteSchema.add(PACIENTE_NOMBRE, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            pacienteSchema.add(PACIENTE_APELLIDO, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            pacienteSchema.add(PACIENTE_NACIMIENTO, (TermSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
            pacienteSchema.add(PACIENTE_CEDULA, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            pacienteSchema.add(PACIENTE_DOMICILIO, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);

            //Motivo
            motivoSchema.add(MOTIVO_MOTIVO, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);

            //Tiempo de llegada
            tiempollegadaSchema.add(TIEMPOLLEGADA_TIEMPO, (TermSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);

            //Sintoma
            sintomaSchema.add(SINTOMA_SINTOMA, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            sintomaSchema.add(SINTOMA_IDEN, (TermSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
            
            //Enfermedad
            enfermedadSchema.add(ENFERMEDAD_ENFERMEDAD, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            enfermedadSchema.add(ENFERMEDAD_CURA, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);

            //Ambulancia
            ambulanciaSchema.add(AMBULANCIA_CONDUCTOR, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            ambulanciaSchema.add(AMBULANCIA_PLACA, (TermSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);

            //Predicato
            //Disponible
            disponibleSchema.add(DISPONIBLE_PACIENTE, pacienteSchema, ObjectSchema.OPTIONAL);

            //PacienteAtendido
            pacienteatendidoSchema.add(PACIENTEATENDIDO_PACIENTE, pacienteSchema, ObjectSchema.OPTIONAL);
            pacienteatendidoSchema.add(PACIENTEATENDIDO_SINTOMA, sintomaSchema, ObjectSchema.OPTIONAL);

            //No disponible
            nodisponibleSchema.add(NODISPONIBLE_MOTIVO, motivoSchema, ObjectSchema.OPTIONAL);

            //Paciente no atendido
            pacientenoatendidoSchema.add(PACIENTENOATENDIDO_MOTIVO, motivoSchema, ObjectSchema.OPTIONAL);

            //Recetar
            recetarSchema.add(RECETAR_ENFERMEDAD, enfermedadSchema, ObjectSchema.OPTIONAL);

            //Envio Ambulancia
            envioambulancia.add(ENVIOAMBULANCIA_AMBULANCIA, ambulanciaSchema, ObjectSchema.OPTIONAL);

            //Accion
            //Diagnosticar
            diagnosticarSchema.add(DIAGNOSTICAR_PACIENTE, pacienteSchema, ObjectSchema.OPTIONAL);

            //Emergency call
            emergencycallSchema.add(EMERGENCYCALL_AMBULANCIA, ambulanciaSchema, ObjectSchema.OPTIONAL);

        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
